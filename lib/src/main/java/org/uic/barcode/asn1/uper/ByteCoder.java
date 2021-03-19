package org.uic.barcode.asn1.uper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

class ByteCoder implements Decoder, Encoder {

    @Override public <T> boolean canEncode(T obj, Annotation[] extraAnnotations) {
        return obj instanceof Byte;
    }

    @Override public <T> void encode(BitBuffer bitbuffer, T obj, Annotation[] extraAnnotations) throws Asn1EncodingException {
        UperEncoder.encodeConstrainedInt(bitbuffer, ((Byte) obj).byteValue() & 0xff, 0, 255);
        UperEncoder.logger.debug(String.format("BYTE %s", ((Byte) obj).byteValue()));
    }

    @Override public <T> boolean canDecode(Class<T> classOfT, Annotation[] extraAnnotations) {
        return Byte.class.isAssignableFrom(classOfT) || byte.class.isAssignableFrom(classOfT);
    }

    @SuppressWarnings("unchecked")
	@Override public <T> T decode(BitBuffer bitbuffer,
            Class<T> classOfT, Field field,
            Annotation[] extraAnnotations) {
        UperEncoder.logger.debug("BYTE");
        return (T) new Byte((byte) UperEncoder.decodeConstrainedInt(bitbuffer, UperEncoder.newRange(0, 255, false)));
    }

	@Override
	public <T> T getDefault(Class<T> classOfT, Annotation[] extraAnnotations) {
		throw new IllegalArgumentException("Default Byte not yet implemented");
	}

}