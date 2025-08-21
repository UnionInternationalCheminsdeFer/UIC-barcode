package org.uic.barcode.asn1.uper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1VarSizeBitstring;
import org.uic.barcode.asn1.datatypes.Bitstring;
import org.uic.barcode.asn1.datatypes.FixedSize;
import org.uic.barcode.asn1.datatypes.SizeRange;

class BitStringCoder implements Decoder, Encoder {

    @Override public <T> boolean canEncode(T obj, Annotation[] extraAnnotations) {
        Class<?> type = obj.getClass();
        AnnotationStore annotations = new AnnotationStore(type.getAnnotations(),
                extraAnnotations);
        return annotations.getAnnotation(Bitstring.class) != null;
    }

    @Override public <T> void encode(BitBuffer bitbuffer, T obj, Annotation[] extraAnnotations) throws Asn1EncodingException {
        Class<?> type = obj.getClass();
        AnnotationStore annotations = new AnnotationStore(type.getAnnotations(),
                extraAnnotations);
        if (!(obj instanceof Asn1VarSizeBitstring)) {
            if (UperEncoder.hasExtensionMarker(annotations)) {
                throw new UnsupportedOperationException(
                    "Bitstring with extensions is not implemented yet");
            }
            FixedSize fixedSize = annotations.getAnnotation(FixedSize.class);
            SizeRange sizeRange = annotations.getAnnotation(SizeRange.class);
            int position = bitbuffer.position();
        	@SuppressWarnings("unchecked")
			List<Boolean> list = (List<Boolean>)obj;            
            if (fixedSize != null) {
            	
            	if (!List.class.isAssignableFrom(type)) {
            		throw new AssertionError("BITSTRING Field should be a list of booleans!"); 
            	}
                if (list.size() != fixedSize.value()) { 
                	throw new AssertionError(
                        "BITSTRING Declared size (" + fixedSize.value() +
                                ") and number of fields (" + list.size() +
                                ") do not match!"); 
                }
                for (Boolean f : list) {
                    try {
                        bitbuffer.put(f);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("can't encode" + obj, e);
                    }
                }
                UperEncoder.logger.debug(String.format("BITSTRING %s, encoded as <%s>", obj.getClass().getName(),
                        bitbuffer.toBooleanStringFromPosition(position)));
                return;
            } else if (sizeRange != null) {
                if (list.size() < sizeRange.minValue() 
                    || list.size() > sizeRange.maxValue()) { 
                	throw new AssertionError(
                        "BITSTRING Declared size (" + sizeRange.minValue() + ".." +
                         sizeRange.maxValue() + ") and number of fields (" + list.size() +
                         ") do not match!"); 
                }            	
            	
                int position1 = bitbuffer.position();
                UperEncoder.encodeConstrainedInt(bitbuffer, list.size(), sizeRange.minValue(),
                        sizeRange.maxValue());
                int position2 = bitbuffer.position();
                for (int i = 0; i < sizeRange.maxValue(); i++) {
                    bitbuffer.put(list.get(i));
                }
                UperEncoder.logger.debug(String.format("BITSTRING %s size %s: %S", obj.getClass().getName(),
                        bitbuffer.toBooleanString(position1, position2 - position1),
                        bitbuffer.toBooleanStringFromPosition(position2)));
                return;
            } else {
            	int lastTrueBit = 0;   
                for (int i = 0; i < list.size() ; i++) {
                    if (list.get(i) == true) lastTrueBit = i + 1;
                }
                int position1 = bitbuffer.position();   
                UperEncoder.encodeLengthDeterminant(bitbuffer, lastTrueBit);    
                int position2 = bitbuffer.position(); 
                for (int i = 0; i < lastTrueBit; i++) {
                    bitbuffer.put(list.get(i));
                }   
                UperEncoder.logger.debug(String.format("BITSTRING %s size %s: %S", obj.getClass().getName(),
                        bitbuffer.toBooleanString(position1, position2 - position1),
                        bitbuffer.toBooleanStringFromPosition(position2)));          	
            	return;            	
            }
        } else if (obj instanceof Asn1VarSizeBitstring) {
            int position = bitbuffer.position();
            if (UperEncoder.hasExtensionMarker(annotations)) { throw new UnsupportedOperationException(
                    "Bitstring with extensions is not implemented yet"); }
            Asn1VarSizeBitstring bitstring = (Asn1VarSizeBitstring) obj;
            FixedSize fixedSize = annotations.getAnnotation(FixedSize.class);
            SizeRange sizeRange = annotations.getAnnotation(SizeRange.class);
            if (fixedSize != null) {
                for (int i = 0; i < fixedSize.value(); i++) {
                    bitbuffer.put(bitstring.getBit(i));
                }
                UperEncoder.logger.debug(String.format("BITSTRING %s: %s", obj.getClass().getName(),
                        bitbuffer.toBooleanStringFromPosition(position)));
                return;
            } else if (sizeRange != null) {
                int position1 = bitbuffer.position();
                UperEncoder.encodeConstrainedInt(bitbuffer, bitstring.size(), sizeRange.minValue(),
                        sizeRange.maxValue());
                int position2 = bitbuffer.position();
                for (int i = 0; i < bitstring.size(); i++) {
                    bitbuffer.put(bitstring.getBit(i));
                }
                UperEncoder.logger.debug(String.format("BITSTRING %s size %s: %S", obj.getClass().getName(),
                        bitbuffer.toBooleanString(position1, position2 - position1),
                        bitbuffer.toBooleanStringFromPosition(position2)));
                return;
            } else {
            	int lastTrueBit = 0;   
                for (int i = 0; i < bitstring.size(); i++) {
                    if (bitstring.getBit(i)== true) lastTrueBit = i + 1;
                }
                int position1 = bitbuffer.position();   
                UperEncoder.encodeLengthDeterminant(bitbuffer, lastTrueBit);    
                int position2 = bitbuffer.position(); 
                for (int i = 0; i < lastTrueBit; i++) {
                    bitbuffer.put(bitstring.getBit(i));
                }   
                UperEncoder.logger.debug(String.format("BITSTRING %s size %s: %S", obj.getClass().getName(),
                        bitbuffer.toBooleanString(position1, position2 - position1),
                        bitbuffer.toBooleanStringFromPosition(position2)));          	
            	return;
            }
        }
    }

    @Override public <T> boolean canDecode(Class<T> classOfT, Annotation[] extraAnnotations) {
        AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(),
                extraAnnotations);
        return annotations.getAnnotation(Bitstring.class) != null;
    }

    @Override public <T> T decode(BitBuffer bitbuffer,
            Class<T> classOfT, Field field,
            Annotation[] extraAnnotations, AsnExtractor extractor) {
        AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(),
                extraAnnotations);
        if (!Asn1VarSizeBitstring.class.isAssignableFrom(classOfT)) {
            UperEncoder.logger.debug("Bitlist");
            FixedSize fixedSize = annotations.getAnnotation(FixedSize.class);
            SizeRange sizeRange = annotations.getAnnotation(SizeRange.class);

            if (UperEncoder.hasExtensionMarker(annotations)) {
                boolean extensionPresent = bitbuffer.get();
                if (extensionPresent) { throw new UnsupportedOperationException(
                        "extensions in fixed-size bitlist are not supported yet"); }
            }
            T result = UperEncoder.instantiate(classOfT);
            
            long size = 0;
            if (fixedSize != null) {
            	size = fixedSize.value();
                UperEncoder.logger.debug("Bitlist Fixed Size");
            } else if (sizeRange != null) {
                UperEncoder.logger.debug("Bitlist with Size Range");
            	size = UperEncoder.decodeConstrainedInt(bitbuffer, UperEncoder.intRangeFromSizeRange(sizeRange));
            } else {
                UperEncoder.logger.debug("Bitlist unrestricted");
                size = UperEncoder.decodeLengthDeterminant(bitbuffer);
            }
            	
            Method addBooleanMethod;
            try {
                addBooleanMethod = classOfT.getDeclaredMethod("add", Object.class);
                addBooleanMethod.setAccessible(true);
            } catch (SecurityException | NoSuchMethodException e) {
                throw new AssertionError("Can't find/access add " + e);
            }
                
            for (int i = 0; i < size; i++) {
               try {
                  addBooleanMethod.invoke(result, bitbuffer.get());
               } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                  throw new IllegalArgumentException("Can't invoke add", e);
               }
            }            
            return result;            	            
            

        } else {
            FixedSize fixedSize = annotations.getAnnotation(FixedSize.class);
            SizeRange sizeRange = annotations.getAnnotation(SizeRange.class);

            if (UperEncoder.hasExtensionMarker(annotations)) {
                boolean extensionPresent = bitbuffer.get();
                if (extensionPresent) { throw new UnsupportedOperationException(
                        "extensions in fixed-size bitlist are not supported yet"); }
            }
            T result = UperEncoder.instantiate(classOfT);
            
            long size = 0L;
            if (fixedSize != null) {
            	size = fixedSize.value();
                UperEncoder.logger.debug("Bitlist Fixed Size");
            } else if (sizeRange != null) {
                UperEncoder.logger.debug("Bitlist with Size Range");
            	size = UperEncoder.decodeConstrainedInt(bitbuffer, UperEncoder.intRangeFromSizeRange(sizeRange));
            } else {
                UperEncoder.logger.debug("Bitlist unrestricted");
                size = UperEncoder.decodeLengthDeterminant(bitbuffer);
            }
            
            // We use reflection here to access protected method of Asn1VarSizeBitstring.
            // Alternative would be to mandate BitSet constructors for all subclasses of
            // Asn1VarSizeBitstring.
            Method setBitMethod;
            try {
                setBitMethod = Asn1VarSizeBitstring.class.getDeclaredMethod("setBit", int.class,
                        boolean.class);
                setBitMethod.setAccessible(true);
            } catch (SecurityException | NoSuchMethodException e) {
                throw new AssertionError("Can't find/access setBit " + e);
            }

            for (int i = 0; i < size; i++) {
                try {
                    setBitMethod.invoke(result, i, bitbuffer.get());
                } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                    throw new IllegalArgumentException("Can't invoke setBit", e);
                }
            }
            return result;
        }
    }

    /** This function only throws an exception, to be used in ternary (a?b:c) expression. */
    static <T> Long badSize(Class<T> classOfT) {
        throw new IllegalArgumentException("both size range and fixed size are null for "
                + classOfT.getName());
    }
    
	@Override
	public <T> T getDefault(Class<T> classOfT, Annotation[] extraAnnotations) {
		throw new IllegalArgumentException("Default Sequence not yet implemented");
	}
}