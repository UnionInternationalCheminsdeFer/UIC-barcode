package org.uic.barcode.asn1.uper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface Decoder {
    <T> boolean canDecode(Class<T> classOfT, Annotation[] extraAnnotations);
    <T> T decode(BitBuffer bitbuffer, Class<T> classOfT,Field f, Annotation[] extraAnnotations);
    <T> T getDefault(Class<T> classOfT, Annotation[] extraAnnotations);
}
