package org.uic.barcode.asn1.uper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigInteger;

import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.IntRange;

class BigIntCoder implements Encoder, Decoder {

    @Override public <T> boolean canDecode(Class<T> classOfT, Annotation[] extraAnnotations) {
        return Asn1BigInteger.class.isAssignableFrom(classOfT);
    }

    @Override public <T> T decode(BitBuffer bitbuffer,
            Class<T> classOfT, Field f,
            Annotation[] extraAnnotations) {
        AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(),
                extraAnnotations);
    	
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
        UperEncoder.logger.debug(String.format("big int Decoded as %s", resultValue));
        
        
        //CG support for int range
        if (intRange != null){
        	resultValue.add(BigInteger.valueOf(intRange.minValue()));
        }
        
        
        return UperEncoder.instantiate(classOfT, resultValue);
    }

    @Override public <T> boolean canEncode(T obj, Annotation[] extraAnnotations) {
        return obj instanceof Asn1BigInteger;
    }

    @Override public <T> void encode(BitBuffer bitbuffer, T obj, Annotation[] extraAnnotations) throws Asn1EncodingException {
        Class<?> type = obj.getClass();
        AnnotationStore annotations = new AnnotationStore(type.getAnnotations(), extraAnnotations);
        IntRange range = annotations.getAnnotation(IntRange.class);
        
        //CG implementation with lower range limit added
        BigInteger bint = ((Asn1BigInteger) obj).toBigInteger();
        if (range != null) { 
       		throw new UnsupportedOperationException("Asn1 BigInteger with range is not supported"); 
        }
        byte[] array = bint.toByteArray();
        int lengthInOctets = array.length;
        int position1 = bitbuffer.position();
        try {
            UperEncoder.encodeLengthDeterminant(bitbuffer, lengthInOctets);
        } catch (Asn1EncodingException e) {
            throw new Asn1EncodingException(" length determinant of " + type.getName(), e);
        }
        int position2 = bitbuffer.position();
        for (byte b : array) {
            bitbuffer.putByte(b);
        }
        UperEncoder.logger.debug(String.format("Big Int(%s): len %s, val %s", obj,
                 bitbuffer.toBooleanString(position1, position2 - position1),
                 bitbuffer.toBooleanStringFromPosition(position2)));
        return;
    }

	@Override
	public <T> T getDefault(Class<T> classOfT, Annotation[] extraAnnotations) {
			AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(), extraAnnotations);
	        Asn1Default defaultAnnotation = annotations.getAnnotation(Asn1Default.class);
	        if (defaultAnnotation == null) return null;
	        //check whether the class has a constructor for numeric types
	        String valueString = defaultAnnotation.value();
	        long value = Long.parseLong(valueString);
	        UperEncoder.logger.debug(String.format("Default INTEGER: %d",value ));
	        
	        @SuppressWarnings("unchecked")
			T t = (T) new Asn1BigInteger(value);
			return t;

	}

}