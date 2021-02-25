package org.uic.barcode.asn1.uper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

class BooleanCoder implements Decoder, Encoder {

    @Override public <T> boolean canEncode(T obj, Annotation[] extraAnnotations) {
        return obj instanceof Boolean;
    }

    @Override public <T> void encode(BitBuffer bitbuffer, T obj, Annotation[] extraAnnotations) {
        UperEncoder.logger.debug(String.format("BOOLEAN %s", obj));
        bitbuffer.put((Boolean) obj);
    }

    @Override public <T> boolean canDecode(Class<T> classOfT, Annotation[] extraAnnotations) {
        return Boolean.class.isAssignableFrom(classOfT)
                || boolean.class.isAssignableFrom(classOfT);
    }

    @SuppressWarnings("unchecked")
	@Override public <T> T decode(BitBuffer bitbuffer,
            Class<T> classOfT, Field field,
            Annotation[] extraAnnotations) {
    	Boolean result = new Boolean(bitbuffer.get());
        UperEncoder.logger.debug(String.format("BOOL: decoded as %s",result));
        return (T) result;
    }
    
	@Override
	public <T> T getDefault(Class<T> classOfT, Annotation[] extraAnnotations) {
		throw new IllegalArgumentException("Default Boolean not yet implemented");
	}
}