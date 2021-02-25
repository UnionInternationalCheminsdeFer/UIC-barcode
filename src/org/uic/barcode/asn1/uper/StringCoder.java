package org.uic.barcode.asn1.uper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.Asn1String;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.DefaultAlphabet;
import org.uic.barcode.asn1.datatypes.FixedSize;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.SizeRange;
import org.uic.barcode.logger.Logger;
import org.uic.barcode.logger.LoggerFactory;


class StringCoder implements Decoder, Encoder {

    private static final Logger LOGGER = LoggerFactory.getLogger("asnLogger");

    @Override public <T> boolean canEncode(T obj, Annotation[] extraAnnotations) {
        return obj instanceof String || obj instanceof Asn1String;
    }

    @Override public <T> void encode(BitBuffer bitbuffer, T obj, Annotation[] extraAnnotations) throws Asn1EncodingException {
    	String pos = String.format("Position: %d.%d", bitbuffer.position()/8 , bitbuffer.position() % 8);
        UperEncoder.logger.debug(String.format("%s: encode STRING %s of type %s", pos, obj, obj.getClass().getName()));
    	Class<?> type = obj.getClass();
        AnnotationStore annotations = new AnnotationStore(type.getAnnotations(), extraAnnotations);
        String string = (obj instanceof String) ? ((String) obj) : ((Asn1String) obj).value();
        RestrictedString restrictionAnnotation = annotations.getAnnotation(RestrictedString.class);
        if (restrictionAnnotation == null) {
            throw new UnsupportedOperationException("Unrestricted character strings are not supported yet. All annotations: " + Arrays.asList(type.getAnnotations()));
        }
        
        FixedSize fixedSize = annotations.getAnnotation(FixedSize.class);
        SizeRange sizeRange = annotations.getAnnotation(SizeRange.class);
        if (fixedSize != null && fixedSize.value() != string.length()) {
            throw new IllegalArgumentException(
                "Bad string length, expected " + fixedSize.value() + ", got " + string.length());
        }
        if (sizeRange != null
                && !sizeRange.hasExtensionMarker()
                && (string.length() < sizeRange.minValue() || sizeRange.maxValue() < string
                        .length())) { throw new IllegalArgumentException(
                "Bad string length, expected " + sizeRange.minValue() + ".."
                        + sizeRange.maxValue() + ", got " + string.length()); }
        
        if (restrictionAnnotation.value() == CharacterRestriction.ObjectIdentifier) {
      
        	byte[] oidb = ObjectIdentifierCoder.encodeObjectId(string);
        	
            BitBuffer stringbuffer = ByteBitBuffer.createInfinite();
            
            for (byte b: oidb){
            	UperEncoder.encodeConstrainedInt(stringbuffer, b & 0xff, 0, 255);
            }
            //-for (char c : string.toCharArray()) {
            //-    encodeChar(stringbuffer, c, restrictionAnnotation);
            //-}
            //char array replaced - end

            stringbuffer.flip();
            if (stringbuffer.limit() % 8 != 0) { 
            		throw new AssertionError("encoding resulted not in multiple of 8 bits"); 
            }
            int numOctets = (stringbuffer.limit() + 7) / 8;  // Actually +7 is not needed here,
                                                             // since we already checked with %8.
            int position1 = bitbuffer.position();
            UperEncoder.encodeLengthDeterminant(bitbuffer, numOctets);
            UperEncoder.logger.debug(String.format("ObjectIdentifier %s,  length %d octets, encoded as %s", string, numOctets, bitbuffer.toBooleanStringFromPosition(position1)));
            int position2 = bitbuffer.position();
            for (int i = 0; i < stringbuffer.limit(); i++) {
                bitbuffer.put(stringbuffer.get());
            }
            UperEncoder.logger.debug(String.format("UTF8String %s, encoded length %d octets, value bits: %s", string, numOctets, bitbuffer.toBooleanStringFromPosition(position2)));
            return;
        } else  if (restrictionAnnotation.value() == CharacterRestriction.UTF8String) {
            // UTF8 length
            BitBuffer stringbuffer = ByteBitBuffer.createInfinite();
            
            //char array replaced - begin
            byte[] stringasbytearray = string.getBytes(StandardCharsets.UTF_8);
            
            for (byte b: stringasbytearray){
            	UperEncoder.encodeConstrainedInt(stringbuffer, b & 0xff, 0, 255);
            }
            //-for (char c : string.toCharArray()) {
            //-    encodeChar(stringbuffer, c, restrictionAnnotation);
            //-}
            //char array replaced - end

            stringbuffer.flip();
            if (stringbuffer.limit() % 8 != 0) { 
            		throw new AssertionError("utf8 encoding resulted not in multiple of 8 bits"); 
            }
            int numOctets = (stringbuffer.limit() + 7) / 8;  // Actually +7 is not needed here,
                                                             // since we already checked with %8.
            int position1 = bitbuffer.position();
            UperEncoder.encodeLengthDeterminant(bitbuffer, numOctets);
            UperEncoder.logger.debug(String.format("UTF8String %s,  length %d octets, encoded as %s", string, numOctets, bitbuffer.toBooleanStringFromPosition(position1)));
            int position2 = bitbuffer.position();
            for (int i = 0; i < stringbuffer.limit(); i++) {
                bitbuffer.put(stringbuffer.get());
            }
            UperEncoder.logger.debug(String.format("UTF8String %s, encoded length %d octets, value bits: %s", string, numOctets, bitbuffer.toBooleanStringFromPosition(position2)));
            return;
        } else if (fixedSize != null) {
            if (fixedSize.value() != string.length()) { throw new IllegalArgumentException(
                    "String length does not match constraints"); }
            int position = bitbuffer.position();
            for (int i = 0; i < fixedSize.value(); i++) {
                encodeChar(bitbuffer, string.charAt(i), restrictionAnnotation);
            }
            UperEncoder.logger.debug(String.format("string encoded as <%s>", bitbuffer.toBooleanStringFromPosition(position)));
            return;
        } else if (sizeRange != null) {
            UperEncoder.logger.debug("string length");
            int position1 = bitbuffer.position();
            UperEncoder.encodeConstrainedInt(bitbuffer, string.length(), sizeRange.minValue(),sizeRange.maxValue(), sizeRange.hasExtensionMarker());
            int position2 = bitbuffer.position();
            UperEncoder.logger.debug("string content");
            for (int i = 0; i < string.length(); i++) {
                encodeChar(bitbuffer, string.charAt(i), restrictionAnnotation);
            }
            UperEncoder.logger.debug(String.format("STRING %s size %d: %s", obj.getClass().getName(), bitbuffer.toBooleanString(position1, position2 - position1),bitbuffer.toBooleanStringFromPosition(position2)));
            return;
        } else {
            int position1 = bitbuffer.position();
            UperEncoder.encodeLengthDeterminant(bitbuffer, string.length());
            int position2 = bitbuffer.position();
            for (int i = 0; i < string.length(); i++) {
                encodeChar(bitbuffer, string.charAt(i), restrictionAnnotation);
            }
            UperEncoder.logger.debug(String.format("STRING %s size %s: %s", obj.getClass().getName(), bitbuffer.toBooleanString(position1, position2 - position1),bitbuffer.toBooleanStringFromPosition(position2)));
            return;
        }
    }

    @Override public <T> boolean canDecode(Class<T> classOfT, Annotation[] extraAnnotations) {
        return String.class.isAssignableFrom(classOfT) || Asn1String.class.isAssignableFrom(classOfT);
    }

    @Override public <T> T decode(BitBuffer bitbuffer,
            Class<T> classOfT, Field field,
            Annotation[] extraAnnotations) {
        UperEncoder.logger.debug("decode String");
    	AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(), extraAnnotations);
        RestrictedString restrictionAnnotation = annotations.getAnnotation(RestrictedString.class);
        if (restrictionAnnotation == null) { 
        	throw new UnsupportedOperationException(
                "Unrestricted character strings are not supported yet. All annotations: " + Arrays.asList(classOfT.getAnnotations())); 
        }
        if (restrictionAnnotation.value() == CharacterRestriction.ObjectIdentifier) {
        	//decode object identifier
            Long numOctets = UperEncoder.decodeLengthDeterminant(bitbuffer);
            List<Boolean> content = new ArrayList<Boolean>();
            for (int i = 0; i < numOctets * 8; i++) {
                content.add(bitbuffer.get());
            }
            byte[] contentBytes = UperEncoder.bytesFromCollection(content);
            UperEncoder.logger.debug(String.format("Content bytes (hex): %s", UperEncoder.hexStringFromBytes(contentBytes)));   
        	String resultStr = ObjectIdentifierCoder.decodeObjectId(contentBytes);
        	UperEncoder.logger.debug(String.format("Object Identifier: %s", resultStr));
            T result = UperEncoder.instantiate(classOfT, resultStr);
            return result;
        } else if (restrictionAnnotation.value() == CharacterRestriction.UTF8String) {
            Long numOctets = UperEncoder.decodeLengthDeterminant(bitbuffer);
            List<Boolean> content = new ArrayList<Boolean>();
            for (int i = 0; i < numOctets * 8; i++) {
                content.add(bitbuffer.get());
            }
            byte[] contentBytes = UperEncoder.bytesFromCollection(content);
            UperEncoder.logger.debug(String.format("Content bytes (hex): %s", UperEncoder.hexStringFromBytes(contentBytes)));   
            String resultStr = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(contentBytes)).toString();
            UperEncoder.logger.debug(String.format("Decoded as %s", resultStr));   
            T result = UperEncoder.instantiate(classOfT, resultStr);
            return result;
        } else {
            FixedSize fixedSize = annotations.getAnnotation(FixedSize.class);
            SizeRange sizeRange = annotations.getAnnotation(SizeRange.class);
            long numChars = (fixedSize != null) ? fixedSize.value() :
                    (sizeRange != null) ? UperEncoder.decodeConstrainedInt(bitbuffer,
                            UperEncoder.intRangeFromSizeRange(sizeRange)) :
                            UperEncoder.decodeLengthDeterminant(bitbuffer);
            UperEncoder.logger.debug(String.format("known-multiplier string, numchars: %d", numChars));
            StringBuilder stringBuilder = new StringBuilder((int) numChars);
            for (int c = 0; c < numChars; c++) {
                stringBuilder.append(decodeRestrictedChar(bitbuffer, restrictionAnnotation));
            }
            String resultStr = stringBuilder.toString();
            UperEncoder.logger.debug(String.format("Decoded as %s", resultStr));
            T result = UperEncoder.instantiate(classOfT, resultStr);
            return result;
        }
    }

    private static void encodeChar(BitBuffer bitbuffer, char c, RestrictedString restriction) throws Asn1EncodingException {
        UperEncoder.logger.debug(String.format("char %s", c));
        switch (restriction.value()) {
            case IA5String:
                if (restriction.alphabet() != DefaultAlphabet.class) {
                    throw new UnsupportedOperationException("alphabet for IA5String is not supported yet.");
                }
                UperEncoder.encodeConstrainedInt(
                        bitbuffer,
                        StandardCharsets.US_ASCII.encode(CharBuffer.wrap(new char[] { c })).get() & 0xff,
                        0,
                        127);
                return;
            case UTF8String:
                if (restriction.alphabet() != DefaultAlphabet.class) {
                    throw new UnsupportedOperationException("alphabet for UTF8 is not supported yet.");
                }
                ByteBuffer buffer = StandardCharsets.UTF_8.encode(CharBuffer.wrap(new char[] { c }));
                for (int i = 0; i < buffer.limit(); i++) {
                    UperEncoder.encodeConstrainedInt(bitbuffer, buffer.get() & 0xff, 0, 255);
                }
                return;
            case VisibleString:
            case ISO646String:
                if (restriction.alphabet() != DefaultAlphabet.class) {
                    char[] chars;
                    try {
                        chars = UperEncoder.instantiate(restriction.alphabet()).chars().toCharArray();
                    } catch (IllegalArgumentException e) {
                        LOGGER.info("Uninstantinatable alphabet ", e);
                        throw new IllegalArgumentException("Uninstantinatable alphabet" + restriction.alphabet().getName());
                    }
                    if (BigInteger.valueOf(chars.length - 1).bitLength() < BigInteger.valueOf(126)
                            .bitLength()) {
                        Arrays.sort(chars);
                        String strAlphabet = new String(chars);
                        int index = strAlphabet.indexOf(c);
                        if (index < 0) { throw new IllegalArgumentException("can't find character " + c + " in alphabet " + strAlphabet); }
                        UperEncoder.encodeConstrainedInt(
                                bitbuffer,
                                index,
                                0,
                                chars.length - 1);
                        return;
                    } else {
                        UperEncoder.encodeConstrainedInt(
                                bitbuffer,
                                StandardCharsets.US_ASCII.encode(CharBuffer.wrap(new char[] { c }))
                                        .get() & 0xff,
                                0,
                                126);
                        return;
                    }
                } else {
                    UperEncoder.encodeConstrainedInt(
                            bitbuffer,
                            StandardCharsets.US_ASCII.encode(CharBuffer.wrap(new char[] { c }))
                                    .get() & 0xff,
                            0,
                            126);
                    return;
                }
            default:
                throw new UnsupportedOperationException("String type " + restriction
                        + " is not supported yet");
        }
    }

    private static String decodeRestrictedChar(BitBuffer bitqueue,
            RestrictedString restrictionAnnotation) {
        switch (restrictionAnnotation.value()) {
            case IA5String: {
                if (restrictionAnnotation.alphabet() != DefaultAlphabet.class) {
                    throw new UnsupportedOperationException(
                        "alphabet for IA5String is not supported yet.");
                }
                byte charByte = (byte) UperEncoder.decodeConstrainedInt(bitqueue, UperEncoder.newRange(0, 127, false));
                byte[] bytes = new byte[] { charByte };
                String result = StandardCharsets.US_ASCII.decode(ByteBuffer.wrap(bytes)).toString();
                if (result.length() != 1) { 
                	throw new AssertionError("decoded more than one char (" + result + ")"); 
                }
                return result;
            }
            case VisibleString:
            case ISO646String: {
                if (restrictionAnnotation.alphabet() != DefaultAlphabet.class) {
                    char[] chars;
                    try {
                        chars = UperEncoder.instantiate(restrictionAnnotation.alphabet()).chars().toCharArray();
                    } catch (IllegalArgumentException e) {
                        LOGGER.info("Uninstantinatable alphabet ", e);
                        throw new IllegalArgumentException("Uninstantinatable alphabet " + restrictionAnnotation.alphabet().getName());
                    }
                    if (BigInteger.valueOf(chars.length - 1).bitLength() < BigInteger.valueOf(126)
                            .bitLength()) {
                        Arrays.sort(chars);
                        int index = (byte) UperEncoder.decodeConstrainedInt(bitqueue, UperEncoder.newRange(0, chars.length - 1, false));
                        String strAlphabet = new String(chars);
                        char c = strAlphabet.charAt(index);
                        String result = new String("" + c);
                        return result;
                    } else {  // Encode normally
                        byte charByte = (byte) UperEncoder.decodeConstrainedInt(bitqueue, UperEncoder.newRange(0, 126, false));
                        byte[] bytes = new byte[] { charByte };
                        String result = StandardCharsets.US_ASCII.decode(ByteBuffer.wrap(bytes)).toString();
                        if (result.length() != 1) { throw new AssertionError(
                                "decoded more than one char (" + result + ")");
                        }
                        return result;
                    }
                } else {  // Encode normally
                    byte charByte = (byte) UperEncoder.decodeConstrainedInt(bitqueue, UperEncoder.newRange(0, 126, false));
                    byte[] bytes = new byte[] { charByte };
                    String result = StandardCharsets.US_ASCII.decode(ByteBuffer.wrap(bytes)).toString();
                    if (result.length() != 1) {
                        throw new AssertionError("decoded more than one char (" + result + ")");
                    }
                    return result;
                }
            }
            default:
                throw new UnsupportedOperationException("String type " + restrictionAnnotation + " is not supported yet");

        }
    }

	@Override
	public <T> T getDefault(Class<T> classOfT, Annotation[] extraAnnotations) {
		AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(), extraAnnotations);
        Asn1Default defaultAnnotation = annotations.getAnnotation(Asn1Default.class);
        if (defaultAnnotation == null) return null;
		T result = UperEncoder.instantiate(classOfT, defaultAnnotation.value());
		return result;
	}

}