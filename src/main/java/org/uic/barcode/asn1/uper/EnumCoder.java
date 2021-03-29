package org.uic.barcode.asn1.uper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.IsExtension;

class EnumCoder implements Decoder, Encoder {

    @Override public <T> boolean canEncode(T obj, Annotation[] extraAnnotations) {
        Class<?> type = obj.getClass();
        return type.isEnum();
    }

    @Override public <T> void encode(BitBuffer bitbuffer, T obj, Annotation[] extraAnnotations) throws Asn1EncodingException {
        Class<?> type = obj.getClass();
        AnnotationStore annotations = new AnnotationStore(type.getAnnotations(), extraAnnotations);
    	String pos = String.format("%d.%d", bitbuffer.position()/8 , bitbuffer.position() % 8);
        UperEncoder.logger.debug(String.format("Position %s ENUM",pos));
        try {
        int position = bitbuffer.position();        

        List<?> values = Arrays.asList(type.getEnumConstants());
        int enumIndex = values.indexOf(obj);
        
        if (!UperEncoder.hasExtensionMarker(annotations)) {
            UperEncoder.logger.debug(String.format("enum without extension: index %d value %s, encoding index...", enumIndex,obj.toString()));
            UperEncoder.encodeConstrainedInt(bitbuffer, enumIndex, 0, values.size() - 1);
            return;
        } else {
            List<Object> valuesWithinExtensionRoot = new ArrayList<>();
            List<Object> valuesOutsideExtensionRoot = new ArrayList<>();
            for (Object c : type.getEnumConstants()) {
            	String field = c.toString();
            	boolean isExtension = false;
            	try {
            		isExtension = type.getField(field).isAnnotationPresent(IsExtension.class);
				} catch (NoSuchFieldException e) {
					 throw new IllegalArgumentException("Illegal value for enum field " , e);
				} catch (SecurityException e) {
					 throw new IllegalArgumentException("Illegal access restriction for enum field " , e);
				}
            	
                if (!isExtension) { 
                    valuesWithinExtensionRoot.add(c);
                } else {
                    valuesOutsideExtensionRoot.add(c);
                }
            }
            
            if (valuesWithinExtensionRoot.contains(obj)) {
            	UperEncoder.logger.debug(String.format("Extension indicator set to false"));
                bitbuffer.put(false);
                int index = valuesWithinExtensionRoot.indexOf(obj);
                UperEncoder.encodeConstrainedInt(bitbuffer, index, 0, valuesWithinExtensionRoot.size() - 1);
                UperEncoder.logger.debug(String.format("ENUM with extension: index %d value %s, encoded as root value <%s>", index, obj.toString(),
                        bitbuffer.toBooleanStringFromPosition(position)));
                return;
            } else {
            	//CG encode the index in the extension list as small integer
            	UperEncoder.logger.debug(String.format("Extension indicator set to true"));
            	bitbuffer.put(true);
            	int index = valuesOutsideExtensionRoot.indexOf(obj);
            	
            	UperEncoder.encodeSmallInt(bitbuffer, index);    
                UperEncoder.logger.debug(String.format("ENUM with extension: index %d value %s, encoded as extension <%s>", index, obj.toString(),
                        bitbuffer.toBooleanStringFromPosition(position)));                
            }
        }
        } catch (Asn1EncodingException e) {
            throw new Asn1EncodingException(type.getName(), e);
        }
    }

    @Override public <T> boolean canDecode(Class<T> classOfT, Annotation[] extraAnnotations) {
        return classOfT.isEnum();
    }

    @Override public <T> T decode(BitBuffer bitbuffer,
            Class<T> classOfT, Field field,
            Annotation[] extraAnnotations) {
        AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(), extraAnnotations);
        UperEncoder.logger.debug("ENUM");
        boolean extensionPresent = false;
        if (UperEncoder.hasExtensionMarker(annotations)) {
            extensionPresent = bitbuffer.get();
            UperEncoder.logger.debug(String.format("with extension marker, %s" , extensionPresent ? "present" : "absent"));
        }
        T[] enumValues = classOfT.getEnumConstants();
        
        int rootValues = 0;

    	boolean isExtension = false;
        for (Object c : enumValues) {
        	String value = c.toString();
        	try {
        		isExtension = classOfT.getField(value).isAnnotationPresent(IsExtension.class);
			} catch (NoSuchFieldException e) {
				 throw new IllegalArgumentException("Illegal value for extension field " , e);
			} catch (SecurityException e) {
				 throw new IllegalArgumentException("Illegal value for extension field " , e);
			}
        	
            if (!isExtension)  	rootValues++;
        }        
        
        //
        int index = 0;
        if (!extensionPresent){
           //root element
            index = (int) UperEncoder.decodeConstrainedInt(bitbuffer,
                    UperEncoder.newRange(0, rootValues - 1, false));        	
        } else {
        	//extension element, decode as small int without restriction
            index = (int) UperEncoder.decodeSmallInt(bitbuffer);
        	//the encoded index is an index within the extensions list only
        	index = index + rootValues;
        }
        
        if (index > enumValues.length - 1 && extensionPresent) { 
        	//this is an unknown extension
            UperEncoder.logger.debug(String.format("Enum contains unknown extendion index %d" , index));
            return null; 
        }
        if (index > enumValues.length - 1 && !extensionPresent) { 
        	//this should not happen
        	throw new IllegalArgumentException(
                "decoded enum index " + index + " is larger then number of elements (0.."
                        + enumValues.length + ") in " + classOfT.getName());
        }        
        T value = enumValues[index];
        UperEncoder.logger.debug(String.format("Enum decoded as %s" , value.toString()));
        return value;        
    }

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getDefault(Class<T> classOfT, Annotation[] extraAnnotations) {
		AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(), extraAnnotations);
        Asn1Default defaultAnnotation = annotations.getAnnotation(Asn1Default.class);
        if (defaultAnnotation == null) return null;
        
        for (Object c : classOfT.getEnumConstants()) {
        	if (c.toString().equals(defaultAnnotation.value())) {
        		return (T) c;
        	}
        }
        
		return null;
	}

}