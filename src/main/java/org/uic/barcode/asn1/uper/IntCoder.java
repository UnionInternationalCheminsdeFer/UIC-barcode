package org.uic.barcode.asn1.uper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigInteger;

import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.Asn1Integer;
import org.uic.barcode.asn1.datatypes.IntMinValue;
import org.uic.barcode.asn1.datatypes.IntRange;


class IntCoder implements Encoder, Decoder {



    @Override public <T> boolean canDecode(Class<T> classOfT, Annotation[] extraAnnotations) {
        return  classOfT  == Asn1Integer.class ||
        		classOfT  == Asn1BigInteger.class||
        		classOfT  == BigInteger.class ||
        		classOfT  == Long.class ||
        		classOfT  == Integer.class ||
        		classOfT  == Short.class ;
    }
    
    
    @Override public <T> T decode(BitBuffer bitbuffer,
            Class<T> classOfT, Field field,
            Annotation[] extraAnnotations)  {
        AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(),extraAnnotations);
    	String pos = String.format("Position: %d.%d", bitbuffer.position()/8 , bitbuffer.position() % 8);
        UperEncoder.logger.debug(String.format("%s: INTEGER",pos));
        IntRange intRange = annotations.getAnnotation(IntRange.class);    
        IntMinValue minValue = annotations.getAnnotation(IntMinValue.class);     
        
        
        if (intRange == null) {
       		return decodeUnconstrainedInteger(bitbuffer, classOfT, extraAnnotations, minValue);
        }
        UperEncoder.logger.debug(String.format("Integer, range %d..%d", intRange.minValue(), intRange.maxValue()));
       	return decodeConstrainedInteger(bitbuffer, classOfT, intRange, extraAnnotations);
    }

    @SuppressWarnings("unchecked")
	private <T> T decodeConstrainedInteger(BitBuffer bitbuffer, Class<T> classOfT, IntRange intRange,	Annotation[] extraAnnotations) {

     long   value = UperEncoder.decodeConstrainedInt(bitbuffer, intRange);
     UperEncoder.logger.debug(String.format("decoded as %d", value));
     
     try {
    	 if (classOfT == Asn1BigInteger.class) {
    		 return ((T) new Asn1BigInteger(value));
    	 } else if (classOfT == Asn1Integer.class) {
    		 return (T) new Asn1Integer(value);         		 
    	 } else if (classOfT == BigInteger.class) {
    		 return (T) BigInteger.valueOf(value);           		
    	 } else if (classOfT == Long.class) {
    		 return (T) Long.valueOf(value);        			
    	 } else if (classOfT == Integer.class) {
    		 return (T) Integer.valueOf(Long.valueOf(value).intValue());    
    	 } else if (classOfT == Short.class) {
    		 return (T) Short.valueOf(Long.valueOf(value).shortValue());       
    	 } 
     } catch (Exception e) {
    	 throw new IllegalArgumentException("size too small " + classOfT.getName() + ": " + e);
     }
     
	 return null;
    	 

	}

	@Override public <T> boolean canEncode(T obj, Annotation[] extraAnnotations) {
        return  obj instanceof Asn1Integer ||
        		obj instanceof Asn1BigInteger ||
        		obj instanceof BigInteger ||
                obj instanceof Long ||
                obj instanceof Integer ||
                obj instanceof Short;
    }

    @Override public <T> void encode(BitBuffer bitbuffer, T obj, Annotation[] extraAnnotations) throws Asn1EncodingException {
        Class<?> type = obj.getClass();
        AnnotationStore annotations = new AnnotationStore(type.getAnnotations(), extraAnnotations);
        IntRange range = annotations.getAnnotation(IntRange.class);
        IntMinValue minValue = annotations.getAnnotation(IntMinValue.class); 
        int position = bitbuffer.position();
        
        //get value       
        if (range == null) {
        	
        	try {
        		encodeUnconstrainedInteger(bitbuffer, obj, extraAnnotations,minValue);
        	} catch (Asn1EncodingException e) {
            throw new Asn1EncodingException(" " + type.getSimpleName(), e);
        	} catch (Exception e1){
        		throw new Asn1EncodingException(" " + type.getSimpleName() + " - " + e1.getLocalizedMessage());
        	}
        	UperEncoder.logger.debug(String.format("INT(%s): %s", obj, bitbuffer.toBooleanStringFromPosition(position)));

        	
        } else {

        	try {
        		
        		long value = 0;
        		if (obj instanceof BigInteger) {
        			try {
        				value = ((BigInteger) obj).longValue();    
        			} catch (Exception e) {
        				
        				UperEncoder.logger.debug("constrained BigInteger is too big for constrained int");
        				throw new Asn1EncodingException("constrained BigInteger is too big for constrained int" + type.getSimpleName());
        			}
        		} if (obj instanceof Asn1BigInteger) {
        			try {
        				value = ((Asn1BigInteger) obj).longValue();    
        			} catch (Exception e) {
        				
        				UperEncoder.logger.debug("constrained Asn1BigInteger is too big for constrained int");
        				throw new Asn1EncodingException("constrained Asn1BigInteger is too big for constrained int" + type.getSimpleName());
        			}
        		} if (obj instanceof Asn1Integer) {
        			try {
        				value = Asn1Integer.toLong((Asn1Integer) obj);    
        			} catch (Exception e) {
        				
        				UperEncoder.logger.debug("constrained BigInteger is too big for constrained int");
        				throw new Asn1EncodingException("constrained BigInteger is too big for constrained int" + type.getSimpleName());
        			}
        		} else if (obj instanceof Long) {
        			value = ((Long) obj).longValue();        			
        		} else if (obj instanceof Integer) {
        			value = ((Integer) obj).longValue();       
        		} else if (obj instanceof Short) {
        			value = ((Short) obj).longValue();       
        		}         		

        		UperEncoder.encodeConstrainedInt(bitbuffer, value, range.minValue(), range.maxValue(), range.hasExtensionMarker());
        	} catch (Asn1EncodingException e) {
        		throw new Asn1EncodingException(" " + type.getSimpleName(), e);
        	} catch (Exception e1){
        		throw new Asn1EncodingException(" " + type.getSimpleName() + " - " + e1.getLocalizedMessage());
        	}
        	UperEncoder.logger.debug(String.format("INT(%s): %s", obj, bitbuffer.toBooleanStringFromPosition(position)));
        }
        return;
    }
    
    private <T> void encodeUnconstrainedInteger(BitBuffer bitbuffer, Object obj, Annotation[] annotations, IntMinValue minValue) throws Asn1EncodingException {

    	   	
        BigInteger bint = null;
    	try {
    		if (obj instanceof BigInteger) {
    			bint = (BigInteger) obj;
    		} else if (obj instanceof Asn1BigInteger) {
    			bint = BigInteger.valueOf(((Asn1BigInteger) obj).longValue());
    		} else if (obj instanceof Asn1Integer) {
    			bint = BigInteger.valueOf(((Asn1Integer) obj).value());
    		} else if (obj instanceof Long) {
    			bint = BigInteger.valueOf(((Long) obj).longValue());    			
    		} else if (obj instanceof Integer) {
    			bint = BigInteger.valueOf(((Integer) obj).longValue());       
    		} else if (obj instanceof Short) {
    			bint = BigInteger.valueOf(((Short) obj).longValue());       
    		} 	
    	} catch (Exception e1){
    		throw new Asn1EncodingException(" " + obj.getClass().getSimpleName() + " - " + e1.getLocalizedMessage());
    	}
    	
        
    	if (minValue != null) {
    		bint.subtract(BigInteger.valueOf(minValue.minValue()));
    	}

        byte[] array = bint.toByteArray();
        int lengthInOctets = array.length;
        int position1 = bitbuffer.position();
        try {
            UperEncoder.encodeLengthDeterminant(bitbuffer, lengthInOctets);
        } catch (Asn1EncodingException e) {
            throw new Asn1EncodingException(" length determinant of INTEGER", e);
        }
        int position2 = bitbuffer.position();
        for (byte b : array) {
            bitbuffer.putByte(b);
        }
        UperEncoder.logger.debug(String.format("INTEGER Int(%s): len %s, val %s", bint.toString(),
                 bitbuffer.toBooleanString(position1, position2 - position1),
                 bitbuffer.toBooleanStringFromPosition(position2)));
        return;
    }
    
    @SuppressWarnings("unchecked")
	public <T> T decodeUnconstrainedInteger(BitBuffer bitbuffer,  Class<T> classOfT,  Annotation[] extraAnnotations,IntMinValue minValue)  {
        AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(), extraAnnotations);
    	
    	String pos = String.format("%d.%d", bitbuffer.position()/8 , bitbuffer.position() % 8);
        UperEncoder.logger.debug(String.format("Position %s BIG INT",pos));
        IntRange intRange = annotations.getAnnotation(IntRange.class);
        if (intRange != null && intRange.maxValue() > 0) { 
        	throw new UnsupportedOperationException("Big int with upper range is not supported yet"); 
        }

        int lengthInOctets = (int) UperEncoder.decodeLengthDeterminant(bitbuffer);
        BitBuffer valueBits = ByteBitBuffer.allocate(lengthInOctets * 8);
        for (int i = 0; i < lengthInOctets * 8; i++) {
            valueBits.put(bitbuffer.get());
        }
        valueBits.flip();
        BigInteger resultValue = new BigInteger(+1, valueBits.array());
        if (minValue != null) {
        	resultValue.add(BigInteger.valueOf(minValue.minValue()));
        }
        
        UperEncoder.logger.debug(String.format("INTEGER Decoded as %s", resultValue));
 
        try {
        	if (classOfT == Asn1BigInteger.class) {
        		return (T) new Asn1BigInteger(resultValue);
        	} else if (classOfT == BigInteger.class) {
        		return (T) resultValue;           		
        	} else if (classOfT == Long.class) {
        		return (T) Long.valueOf(resultValue.longValueExact());        			
        	} else if (classOfT == Integer.class) {
        		return (T) Integer.valueOf(resultValue.intValueExact());    
        	} else if (classOfT == Short.class) {
        		return (T) Short.valueOf(resultValue.shortValueExact());       
        	} 	
        } catch (Exception e){
        	UperEncoder.logger.debug(String.format("INTEGER Decoded as %s is too big for data type", resultValue));
        }
		return null;
    }

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getDefault(Class<T> classOfT, Annotation[] extraAnnotations) {
		AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(), extraAnnotations);
        Asn1Default defaultAnnotation = annotations.getAnnotation(Asn1Default.class);
        if (defaultAnnotation == null) return null;
        //check whether the class has a constructor for numeric types
        String valueString = defaultAnnotation.value();
        long value = Long.parseLong(valueString);
        
        try {
       	 if (classOfT == Asn1BigInteger.class) {
       		 return ((T) new Asn1BigInteger(value));
       	 } else if (classOfT == BigInteger.class) {
       		 return (T) BigInteger.valueOf(value);           		
       	 } else if (classOfT == Long.class) {
       		 return (T) Long.valueOf(value);        			
       	 } else if (classOfT == Integer.class) {
       		 return (T) Integer.valueOf(Long.valueOf(value).intValue());    
       	 } else if (classOfT == Short.class) {
       		 return (T) Short.valueOf(Long.valueOf(value).shortValue());       
       	 } 
        } catch (Exception e) {
       	 throw new IllegalArgumentException("size too small " + classOfT.getName() + ": " + e);
        }        
        
        return null;
	}

}