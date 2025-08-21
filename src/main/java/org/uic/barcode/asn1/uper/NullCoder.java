package org.uic.barcode.asn1.uper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.uic.barcode.asn1.datatypesimpl.Asn1NullObject;

class NullCoder implements Decoder, Encoder {

    @Override public <T> boolean canEncode(T obj, Annotation[] extraAnnotations) {
        return obj instanceof Asn1NullObject;
    }

    @Override public <T> void encode(BitBuffer bitbuffer, T obj, Annotation[] extraAnnotations) throws Asn1EncodingException {
        UperEncoder.logger.debug(String.format("NULL encoded: %s", ((Asn1NullObject) obj).getAttributeName()));
    }

    @Override public <T> boolean canDecode(Class<T> classOfT, Annotation[] extraAnnotations) {
        return Asn1NullObject.class.isAssignableFrom(classOfT);
    }

    @SuppressWarnings("unchecked")
	@Override public <T> T decode(BitBuffer bitbuffer,
            Class<T> classOfT, Field field,
            Annotation[] extraAnnotations, AsnExtractor extractor) {
        UperEncoder.logger.debug("NULL");
        return (T) new Asn1NullObject(field.getName());
    }

	@Override
	public <T> T getDefault(Class<T> classOfT, Annotation[] extraAnnotations) {
		throw new IllegalArgumentException("Default not allowed for NULL object");
	}

}