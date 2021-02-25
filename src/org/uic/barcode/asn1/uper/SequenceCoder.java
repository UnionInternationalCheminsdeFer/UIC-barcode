package org.uic.barcode.asn1.uper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Deque;

import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder.Asn1ContainerFieldSorter;

class SequenceCoder implements Decoder, Encoder {

    @Override public <T> boolean canEncode(T obj, Annotation[] extraAnnotations) {
        Class<?> type = obj.getClass();
        AnnotationStore annotations = new AnnotationStore(type.getAnnotations(), extraAnnotations);      
        
        return annotations.getAnnotation(Sequence.class) != null;
    }

	@Override public <T> void encode(BitBuffer bitbuffer, T obj, Annotation[] extraAnnotations) throws Asn1EncodingException {
        Class<?> type = obj.getClass();
        AnnotationStore annotations = new AnnotationStore(type.getAnnotations(), extraAnnotations);
    	String pos = String.format("%d.%d", bitbuffer.position()/8 , bitbuffer.position() % 8);
        UperEncoder.logger.debug(String.format("Position %s: SEQUENCE %s", pos, type.getSimpleName()));
        
        Asn1ContainerFieldSorter sorter = new Asn1ContainerFieldSorter(type);
        try {
            if (UperEncoder.hasExtensionMarker(annotations)) {
                boolean extensionsPresent = !sorter.extensionFields.isEmpty() 
                		&& UperEncoder.hasNonNullExtensions(obj, sorter);
                UperEncoder.logger.debug(String.format("with extension marker, %s extensions, extensionBit: <%s>",
                         extensionsPresent ? "with" : "without", extensionsPresent));
                bitbuffer.put(extensionsPresent);
            }
            // Bitmask for optional fields.
            for (Field f : sorter.optionalOrdinaryFields) {
            	           	
                boolean fieldPresent = isPresent(f, f.get(obj));
                
                UperEncoder.logger.debug(String.format("with optional field %s %s, presence encoded as bit <%s>",
                        f.getName(), fieldPresent ? "present" : "absent", fieldPresent));
                
                bitbuffer.put(fieldPresent);  // null means the field is absent.
            }
            
            // All ordinary fields (fields within extension root).
            for (Field f : sorter.ordinaryFields) {
            	//CG do not include default values
                if (UperEncoder.isMandatory(f) || isPresent(f,f.get(obj))) {
                	
                	pos = String.format("Position: %d.%d", bitbuffer.position()/8 , bitbuffer.position() % 8);
                    UperEncoder.logger.debug(String.format("%s: Field %s", pos, f.getName()));
                    try {
                        UperEncoder.encode2(bitbuffer, f.get(obj), f.getAnnotations());
                    } catch (Asn1EncodingException e) {
                        throw new Asn1EncodingException("." + f.getName(), e);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Illegal value for field " + f.getName(), e);
                    }
                }             
            }
            // Extension fields.
            if (UperEncoder.hasExtensionMarker(annotations) 
            		&& !sorter.extensionFields.isEmpty()
                    && UperEncoder.hasNonNullExtensions(obj, sorter)) {
                // Total extensions count.
                int numExtensions = sorter.extensionFields.size();
                UperEncoder.logger.debug(String.format("continuing sequence : %d extension(s) are present, encoding length determinant for them...",    numExtensions));
                UperEncoder.encodeLengthOfBitmask(bitbuffer, numExtensions);
                // Bitmask for present extensions.
                for (Field f : sorter.extensionFields) {
                    boolean fieldIsPresent = isPresent(f,f.get(obj));
                    
                    UperEncoder.logger.debug(String.format("Extension %s is %s, presence encoded as <%s>", f.getName(),
                                 fieldIsPresent ? "present" : "absent", fieldIsPresent ? "1" : "0"));
                    
                    bitbuffer.put(fieldIsPresent);
                }
                // Values of extensions themselves.
                for (Field f : sorter.extensionFields) {
                	//CG do not encode default values
                    if (UperEncoder.isMandatory(f) || isPresent(f,f.get(obj))) {
                        UperEncoder.logger.debug(String.format("Encoding extension field %s", f.getName()));
                        try {
                            UperEncoder.encodeAsOpenType(bitbuffer, f.get(obj), f.getAnnotations());
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("Illegal value for extension field " + f.getName(), e);
                        }
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new IllegalArgumentException("can't encode " + obj, e);
        }
        sorter.revertAccess();
    }
    
    @SuppressWarnings("unchecked")
	protected <T> boolean isPresent(Field f, Object fieldObject){
    	    	
     	if (fieldObject == null) return false;
    	
        boolean fieldPresent = fieldObject != null;
        
        if (fieldObject instanceof Asn1SequenceOf) {
        	if (((Asn1SequenceOf<T>)fieldObject).size() == 0){
        		//CG do not encode optional empty sequences
        		fieldPresent = false;
        	}
        }
        
        if (fieldObject instanceof String) {
        	if (((String)fieldObject).length() == 0){
        		//CG do not encode optional empty sequences
        		fieldPresent = false;
        	}
        }  
        
        //CG DEFAULT VALUES
        if (fieldPresent && UperEncoder.isDefault(f,fieldObject)) {
        	UperEncoder.logger.debug(String.format("Field %s has default value", f.getName()));
        	fieldPresent = false;
        }
        //CG No ASN1
        if (UperEncoder.isNotAsn1(f)) {
       		fieldPresent = false;
        }    
        
        return fieldPresent;
        
    }

    @Override public <T> boolean canDecode(Class<T> classOfT, Annotation[] extraAnnotations) {
        AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(),
                extraAnnotations);
        return annotations.getAnnotation(Sequence.class) != null;
    }

    @Override public <T> T decode(BitBuffer bitbuffer,
            Class<T> classOfT,Field f1,
            Annotation[] extraAnnotations) {
        UperEncoder.logger.debug(String.format("decode SEQUENCE %s",classOfT.getSimpleName()));
        AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(),extraAnnotations);
        T result = UperEncoder.instantiate(classOfT);
        Asn1ContainerFieldSorter sorter = new Asn1ContainerFieldSorter(classOfT);
        boolean hasExtensionMarker = UperEncoder.hasExtensionMarker(annotations);
        boolean extensionPresent = false;
        if (hasExtensionMarker) {
            extensionPresent = bitbuffer.get();
            UperEncoder.logger.debug(String.format("with extension marker, extension %s", extensionPresent ? "present!" : "absent"));
        }
        // Bitmask for optional fields.
        Deque<Boolean> optionalFieldsMask = new ArrayDeque<>(sorter.optionalOrdinaryFields.size());     
        for (Field f : sorter.optionalOrdinaryFields) {
            optionalFieldsMask.add(bitbuffer.get());
            UperEncoder.logger.debug(String.format("with optional field %s %s" , f.getName() , optionalFieldsMask.getLast() ? "present" : "absent"));
        }
        // All ordinary fields (fields within extension root).
        
        for (Field f : sorter.ordinaryFields) {
            if (!UperEncoder.isTestInstrumentation(f) && (UperEncoder.isMandatory(f) 
            	|| 
                (UperEncoder.isOptional(f) && optionalFieldsMask.pop()))) {
                UperEncoder.logger.debug(String.format("Field : %s", f.getName()));
                try {
                    f.set(result, UperEncoder.decodeAny(bitbuffer,f.getType(),f, f.getAnnotations()));
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("can't access 'set method' for field " + f + " of class " + classOfT + " " + e, e);
                }
            } else {
            	//CG might have a default value
           		if (f.getAnnotation(Asn1Default.class) != null) {
           			try {
           		        UperEncoder.logger.debug(String.format(String.format("Retrieve default for element : %s",f.getName())));
						f.set(result,UperEncoder.getDefault(f.getType(),f.getAnnotations()));
					} catch (IllegalArgumentException e) {
						throw new IllegalArgumentException("can't decode " + classOfT, e);
					} catch (IllegalAccessException e) {
						throw new IllegalArgumentException("can't decode " + classOfT, e);
					}
           		}
            }
        }
        if (!hasExtensionMarker) {
        	//done
        	sorter.revertAccess();
        	return result; 
        }
        
        // Possible extensions
        int numExtensions = 0;
        if (UperEncoder.hasExtensionMarker(annotations)){
        	if (extensionPresent) {
        		// Number of extensions.
        		numExtensions = (int) UperEncoder.decodeLengthOfBitmask(bitbuffer);
        		UperEncoder.logger.debug(String.format("sequence has %d extension(s)", numExtensions));
        		// Bitmask for extensions.
        		boolean[] bitmaskValueIsPresent = new boolean[numExtensions];
        		for (int i = 0; i < numExtensions; i++) {
        			bitmaskValueIsPresent[i] = bitbuffer.get();
        			UperEncoder.logger.debug(String.format("extension %s is %s", i, bitmaskValueIsPresent[i] ? "present" : "absent"));
        		}
        		// Values.
        		UperEncoder.logger.debug("decoding extensions values...");
        		for (int i = 0; i < numExtensions; i++) {
        			UperEncoder.logger.debug(String.format("sequence extension %s %s", i, bitmaskValueIsPresent[i] ? "present" : "absent"));
        			if (bitmaskValueIsPresent[i]) {
        				UperEncoder.logger.debug(String.format("decoding extension %d...", i));
        				Field field = sorter.extensionFields.size() > i ? sorter.extensionFields.get(i) : null;
        				Class<?> classOfElement = field != null ? field.getType() : null;
        				if (field != null) {
        					try {
        						Object decodedValue = UperEncoder.decodeAsOpenType(bitbuffer, classOfElement,field, field.getAnnotations());
        						if (field != null) {
        							field.set(result, decodedValue);
        						}
        					} catch (IllegalArgumentException | IllegalAccessException e) {
        						throw new IllegalArgumentException("can't decode " + classOfT, e);
        					}
        				} else {
        					//CG skip the unknown extension element
        					UperEncoder.decodeSkipUnknownElement(bitbuffer, classOfT.getSimpleName());      
        				}	
        			} else {
        				//CG the absent extension filed might have a default value
        				Field field = sorter.extensionFields.size() > i ? sorter.extensionFields.get(i) : null;
        				Class<?> classOfElement = field != null ? field.getType() : null;
        				if (field != null && field.getAnnotation(Asn1Default.class) != null) {
        					try {
        						field.set(result,UperEncoder.getDefault(classOfElement,field.getAnnotations()));
        					} catch (IllegalArgumentException e) {
        						throw new IllegalArgumentException("can't decode " + classOfElement.getSimpleName(), e);
        					} catch (IllegalAccessException e) {
        						throw new IllegalArgumentException("can't decode " + classOfElement.getSimpleName(), e);
        					}
        					UperEncoder.logger.debug(String.format("Default set for %s", field.getName()));
        				}            		
        			}
        		}//end of loop on present extension fields 
        	} else {
        		//CG there is an extension marker but the extension is not present
        		//   then there sill might be an element with a default value
        		for (Field field : sorter.extensionFields) {
        			if ( numExtensions <= sorter.extensionFields.indexOf(field)) {
        				if (field.getAnnotation(Asn1Default.class) != null) {
        					Class<?> classOfElement = field != null ? field.getType() : null;
        					try {
        						field.set(result,UperEncoder.getDefault(classOfElement, field.getAnnotations()));
        					} catch (IllegalArgumentException e) {
        						throw new IllegalArgumentException("can't decode default" + classOfElement.getSimpleName(), e);
        					} catch (IllegalAccessException e) {
        						throw new IllegalArgumentException("can't decode default" + classOfElement.getSimpleName(), e);
        					}
        				}
        			}
        		}
        	} // end of extension handling
        }
        sorter.revertAccess();
        return result;       
    }

	@Override
	public <T> T getDefault(Class<T> classOfT, Annotation[] annotations) {
		throw new IllegalArgumentException("Default Sequence not yet implemented");
	}
}