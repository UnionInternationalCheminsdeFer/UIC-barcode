package org.uic.barcode.asn1.uper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.IsExtension;
import org.uic.barcode.asn1.datatypes.NoAsn1Field;
import org.uic.barcode.asn1.datatypes.SizeRange;
import org.uic.barcode.logger.Logger;
import org.uic.barcode.logger.LoggerFactory;



/** A "quick-and-dirty" implementation of ASN.1 encoder for UPER (Unaligned Packed Encoding Rules).
 *
 * @see ITU-T Recommendation <a
 *      href="http://www.itu.int/ITU-T/recommendations/rec.aspx?rec=x.691">X.691</a>
 * <p>
 *      TODO: Cover the rest of (useful) ASN.1 datatypes and PER-visible constraints,
 *      write unit tests for them. Clean-up, do more refactoring.
 **/
public final class UperEncoder {
    public final static Logger logger = LoggerFactory.getLogger("asnLogger");

    private final static int NUM_16K = 16384;
    @SuppressWarnings("unused")
    private final static int NUM_32K = 32768;
    @SuppressWarnings("unused")
    private final static int NUM_48K = 49152;
    @SuppressWarnings("unused")
    private final static int NUM_64K = 65536;
    
    private UperEncoder(){}

    public static <T> byte[] encode(T obj)
            throws IllegalArgumentException, UnsupportedOperationException {
        try {
            BitBuffer bitbuffer = ByteBitBuffer.createInfinite();
            encode2(bitbuffer, obj, new Annotation[] {});
            bitbuffer.flip();
            byte[] result = Arrays.copyOf(bitbuffer.array(), (bitbuffer.limit() + 7) / 8);
            return result;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Can't encode " + obj.getClass().getName() + ": " + e, e);
        } catch (Asn1EncodingException e) {
            throw new IllegalArgumentException("Can't encode " + obj.getClass().getName() + ":" + e.getMessage(), e);
        }
    }
    
    public static <T> T decode(byte[] bytes, Class<T> classOfT) throws IllegalArgumentException,
    UnsupportedOperationException {
    		BitBuffer bitQueue = bitBufferFromBinaryString(binaryStringFromBytes(bytes));
    		T result = decodeAny(bitQueue, classOfT,null, new Annotation[] {}, null);
    		if (bitQueue.remaining() > 7) {
    				throw new IllegalArgumentException("Can't fully decode "
    						+ classOfT.getName() + ", got (" + result.getClass().getName() + "): " + result
    						+ "; remaining " + bitQueue.remaining() + "  bits: " + bitQueue);
    		}
    		return result;
    }
    
    public static <T> T decode(byte[] bytes, Class<T> classOfT,AsnExtractor extractor) throws IllegalArgumentException,
    UnsupportedOperationException {
    		BitBuffer bitQueue = bitBufferFromBinaryString(binaryStringFromBytes(bytes));
    		T result = decodeAny(bitQueue, classOfT,null, new Annotation[] {}, extractor);
    		if (bitQueue.remaining() > 7) {
    				throw new IllegalArgumentException("Can't fully decode "
    						+ classOfT.getName() + ", got (" + result.getClass().getName() + "): " + result
    						+ "; remaining " + bitQueue.remaining() + "  bits: " + bitQueue);
    		}
    		return result;
    }
    
    
    public static <T> byte[] extract(byte[] bytes,String path,Class<T> classOfT) throws IllegalArgumentException, UnsupportedOperationException {
 
    	    BitBuffer bitQueue = bitBufferFromBinaryString(binaryStringFromBytes(bytes));
    	    
    	    AsnExtractor extractor = new AsnExtractor(path,bitQueue);
    	    
    		T result = decodeAny(bitQueue, classOfT,null, new Annotation[] {}, extractor);
    		if (bitQueue.remaining() > 7) {
    				throw new IllegalArgumentException("Can't fully decode "
    						+ classOfT.getName() + ", got (" + result.getClass().getName() + "): " + result
    						+ "; remaining " + bitQueue.remaining() + "  bits: " + bitQueue);
    		}
    		return extractor.getResult();
    }
    
    
    
    

    public static <T> T decode(byte[] bytes, Class<T> classOfT, Field f,AsnExtractor extractor) throws IllegalArgumentException,
            UnsupportedOperationException {
        BitBuffer bitQueue = bitBufferFromBinaryString(binaryStringFromBytes(bytes));
        T result = decodeAny(bitQueue, classOfT, f, new Annotation[] {}, extractor);
        if (bitQueue.remaining() > 7) {
            throw new IllegalArgumentException("Can't fully decode "
                + classOfT.getName() + ", got (" + result.getClass().getName() + "): " + result
                + "; remaining " + bitQueue.remaining() + "  bits: " + bitQueue);
        }
        return result;
    }


    static <T> void encode2(BitBuffer bitbuffer, T obj, Annotation[] extraAnnotations) throws Asn1EncodingException {
 
    	if (obj == null) {
    		logger.debug(String.format("Object missing"));
    		return;
    	}
    	
    	
    	for (Encoder e : encoders) {
            if (e.canEncode(obj, extraAnnotations)) {
                e.encode(bitbuffer, obj, extraAnnotations);
                return;
            }
        }
        logger.debug(String.format("Can't find encoder for %s",obj.getClass().getSimpleName()));
        
        throw new IllegalArgumentException("Can't find encoder for " + obj.getClass().getName()
                + " with extra annotations " + Arrays.asList(extraAnnotations));
    }

    static <T> T decodeAny(BitBuffer bitbuffer,Class<T> classOfT, Field f, Annotation[] extraAnnotations, AsnExtractor extractor) {
    	   	
        logger.debug(String.format(String.format("Decoding classOfT : %s",classOfT.getCanonicalName())));
        
        for (Decoder e : decoders) {
            if (e.canDecode(classOfT, extraAnnotations)) {
                return e.decode(bitbuffer, classOfT,f, extraAnnotations,extractor);
            }
        }
        
        logger.debug(String.format("Can't find decoder for %s",classOfT.getSimpleName()));

        throw new IllegalArgumentException("Can't find decoder for " + classOfT.getName()
                + " with extra annotations " + Arrays.asList(extraAnnotations));
     }
    
    static <T> T getDefault(Class<T> classOfT, Annotation[] annotations) {
		AnnotationStore annots = new AnnotationStore(classOfT.getAnnotations(), annotations);
        Asn1Default defaultAnnotation = annots.getAnnotation(Asn1Default.class);
               
        if (defaultAnnotation == null){
        	return null;
        }
        
        Annotation[] defaultAnnots = new Annotation[] {defaultAnnotation};
        
        for (Decoder e : decoders) {
            if (e.canDecode(classOfT, defaultAnnots)) {
                return e.getDefault(classOfT, defaultAnnots);
            }
        }
        logger.debug(String.format("Can't find decoder for %s",classOfT.getSimpleName()));

        throw new IllegalArgumentException("Can't find default for " + classOfT.getName()
                + " with extra annotations " + defaultAnnotation.toString());
     }

    static IntRange newRange(
            final long minValue,
            final long maxValue,
            final boolean hasExtensionMarker) {
    	
        return new IntRange() {
            @Override public Class<? extends Annotation> annotationType() {
                return IntRange.class;
            }
            @Override public long minValue() { return minValue; }
            @Override public long maxValue() { return maxValue; }
            @Override public boolean hasExtensionMarker() { return hasExtensionMarker; }
        };
    }

    static IntRange intRangeFromSizeRange(SizeRange sizeRange) {
        return newRange(sizeRange.minValue(), sizeRange.maxValue(), sizeRange.hasExtensionMarker());
    }

    private static List<Encoder> encoders = new ArrayList<>();
    private static List<Decoder> decoders = new ArrayList<>();

    static {
        encoders.add(new IntCoder());
        encoders.add(new ByteCoder());
        encoders.add(new BooleanCoder());
        encoders.add(new SequenceCoder());
        encoders.add(new ChoiceCoder());
        encoders.add(new EnumCoder());
        encoders.add(new BitStringCoder());
        encoders.add(new SeqOfCoder());
        encoders.add(new StringCoder());

        decoders.add(new IntCoder());
        decoders.add(new ByteCoder());
        decoders.add(new BooleanCoder());
        decoders.add(new SequenceCoder());
        decoders.add(new ChoiceCoder());
        decoders.add(new EnumCoder());
        decoders.add(new BitStringCoder());
        decoders.add(new SeqOfCoder());
        decoders.add(new StringCoder());

    }


    static <T> void encodeAsOpenType(
            BitBuffer bitbuffer, T obj, Annotation[] extraAnnotations)
            throws IllegalArgumentException, IllegalAccessException, Asn1EncodingException {
        logger.debug(String.format("OPEN TYPE for {%s}. Encoding preceedes length determinant" ,obj != null ? obj.getClass().getSimpleName() : "null"));
        BitBuffer tmpbuffer = ByteBitBuffer.createInfinite();
        encode2(tmpbuffer, obj, extraAnnotations);
        int numBytes = (tmpbuffer.position() + 7) / 8;
        logger.debug(String.format("Encoding open type length determinant (%d) for %s (will be inserted before the open type content)", numBytes, obj != null ? obj.getClass().getName() : "null" ));
        try {
            encodeLengthDeterminant(bitbuffer, numBytes);
        } catch (Asn1EncodingException e) {
            throw new Asn1EncodingException(" length of open type ", e);
        }
        tmpbuffer.flip();
        for (int i = 0; i < tmpbuffer.limit(); i++) {
            bitbuffer.put(tmpbuffer.get());
        }
        //CG padding bits to fill the byte: Open Types are wrapped in an OCTET STRING
        int paddingBits = numBytes*8 - tmpbuffer.limit();
        for (int i = 0; i < paddingBits; i++) {
            bitbuffer.put(false);
        }        
        
    }

    static <T> T decodeAsOpenType(BitBuffer bitbuffer, Class<T> classOfT,Field f, Annotation[] extraAnnotations,AsnExtractor extractor) {
        logger.debug(String.format("OPEN TYPE for %s. Encoding preceedes length determinant", classOfT != null ? classOfT.getName() : "null"));
        long numBytes = decodeLengthDeterminant(bitbuffer);
        BitBuffer openTypeBitBuffer = ByteBitBuffer.allocate((int)numBytes * 8);
        for (int i = 0; i < numBytes * 8; i++) {
            openTypeBitBuffer.put(bitbuffer.get());
        }
        openTypeBitBuffer.flip();
        if (classOfT != null) {
            T result = decodeAny(openTypeBitBuffer, classOfT, f, extraAnnotations, extractor);
            // Assert that padding bits are all 0.
            logger.debug(String.format("open type had padding bits"));
            for (int i = 0; i < openTypeBitBuffer.remaining(); i++) {
                boolean paddingBit = openTypeBitBuffer.get();
                logger.debug(String.format("padding bit %d was <%s>", i, paddingBit ? "1" : "0"));
                if (paddingBit) { 
                	throw new IllegalArgumentException("non-zero padding bit " + i + " for open type " + classOfT.getSimpleName()); }
            }
            return result;
        } else {
            return null;
        }
    }
    
    /*
     * skip an unknown extension element
     *  - decode length
     *  - skip the bytes according to the length
     */
    static void decodeSkipUnknownElement(BitBuffer bitbuffer, String name) {
    	logger.debug(String.format("Skip unknown extension in %s. Encoding preceedes length determinant", name));
        long numBytes = decodeLengthDeterminant(bitbuffer);
        for (int i = 0; i < numBytes * 8; i++) {
            bitbuffer.get();
        }
    	logger.debug(String.format(String.format("Skiped %d bytes", numBytes)));
    }

    static <T> boolean hasNonNullExtensions(
            T obj, Asn1ContainerFieldSorter sorter)
                    throws IllegalArgumentException, IllegalAccessException {
        for (Field f : sorter.extensionFields) {
        	//CG elements with a default value will not be included
            if (f.get(obj) != null && !isDefault(f,f.get(obj)) ) { 
            	return true; 
            }
        }
        return false;
    }

    private static <T> Constructor<T> findConsturctor(Class<T> classOfT, Object... parameters) {
        @SuppressWarnings("unchecked")
        Constructor<T>[] declaredConstructors = (Constructor<T>[]) classOfT
                .getDeclaredConstructors();
        for (Constructor<T> c : declaredConstructors) {
            Class<?>[] parameterTypes = c.getParameterTypes();
            if (parameterTypes.length == parameters.length) {
                boolean constructorIsOk = true;
                for (int i = 0; i < parameters.length; i++) {
                    if (!parameterTypes[i].isAssignableFrom(parameters[i].getClass())) {
                        constructorIsOk = false;
                        break;
                    }
                }
                if (constructorIsOk) { return c; }
            }
        }
        Class<?>[] parameterTypes = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterTypes[i] = parameters[i].getClass();
        }
        throw new IllegalArgumentException("Can't get the " + parameters.length +
                "-argument constructor for parameter(s) "
                + Arrays.asList(parameters) +
                " of type(s) " + Arrays.asList(parameterTypes) + " for class "
                + classOfT.getName() + " (" + classOfT.getClass().getName() + " or " + Arrays.asList(classOfT.getClasses()) + ")" +
                ", all constructors: " + Arrays.asList(classOfT.getDeclaredConstructors()));
    }

    /** Instantiate a given class T using given parameters. */
    static <T> T instantiate(Class<T> classOfT, Object... parameters) {
        Class<?>[] parameterTypes = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterTypes[i] = parameters[i].getClass();
        }
        Constructor<T> constructor = findConsturctor(classOfT, parameters);
        //boolean constructorIsAccessible = constructor.isAccessible();
        constructor.setAccessible(true);
        T result;
        try {
            result = constructor.newInstance(parameters);
        } catch (IllegalArgumentException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Can't instantiate " + classOfT.getName(), e);
        }
        //constructor.setAccessible(constructorIsAccessible);
        return result;
    }

    static long decodeConstrainedInt(BitBuffer bitqueue, IntRange intRange) {
        long lowerBound = intRange.minValue();
        long upperBound = intRange.maxValue();
        boolean hasExtensionMarker = intRange.hasExtensionMarker();
        if (upperBound < lowerBound) {
            throw new IllegalArgumentException("Lower bound " + lowerBound + " is larger that upper bound " + upperBound);
        }
        if (hasExtensionMarker) {
            boolean extensionIsActive = bitqueue.get();
            if (extensionIsActive) {
            	//in extensions are encoded as uncontraint integers, thius an Asn1BigInteger type should be used (a lower range bound might be applied).
                throw new UnsupportedOperationException("int extension are not supported yet");
            }
        }
        final Long range = upperBound - lowerBound + 1;
        if (range == 1) {
            return lowerBound;
        }
        int bitlength = BigInteger.valueOf(range - 1).bitLength();
        logger.debug(String.format("This int will require %d bits, available %d" , bitlength, bitqueue.remaining()));
        BitBuffer relevantBits = ByteBitBuffer.allocate( ((bitlength + 7) / 8) * 8);  // Full bytes.
        int numPaddingBits = (8 - (bitlength % 8)) % 8;  // Leading padding 0-bits.
        for (int i = 0; i < numPaddingBits; i++) {
            relevantBits.put(false);
        }
        for (int i = 0; i < bitlength; i++) {
            relevantBits.put(bitqueue.get());
        }
        relevantBits.flip();
        final BigInteger big = new BigInteger(+1, relevantBits.array());
        final Long result = lowerBound + big.longValue();
        logger.debug(String.format("bits %s decoded as %d plus lower bound %d  give %d",
                relevantBits.toBooleanStringFromPosition(0), big.longValue(), lowerBound, result));
        if ((result < intRange.minValue() || intRange.maxValue() < result)
                && !intRange.hasExtensionMarker()) {
            throw new AssertionError("Decoded value "
                + result + " is outside of range (" + intRange.minValue() + ".."
                + intRange.maxValue() + ")");
        }
        return result;
    }

    
    //CG Begin
    static boolean isDefault(Field f, Object obj) {
    	
        if (f.getAnnotation(Asn1Default.class) != null) {
        	String value = f.getAnnotation(Asn1Default.class).value();
        	if (obj.toString().equals(value)){
        		return true;
        	}
        }
        return false;
    	
    }
    
   
	public static boolean isNotAsn1(Field f) {
        return (f.getAnnotation(NoAsn1Field.class) != null);
	}    
    //CG End


    static boolean hasExtensionMarker(AnnotationStore annotations) {
        return annotations.getAnnotation(HasExtensionMarker.class) != null;
    }

    private static boolean isExtension(Field f) {
        return f.getAnnotation(IsExtension.class) != null;
    }

    static boolean isMandatory(Field f) {
        return !isOptional(f);
    }

    static boolean isOptional(Field f) {
    	//CG elements with default value are treated as optional as they are not encoded in case of the default value
        if (f.getAnnotation(Asn1Optional.class) != null || 
        	f.getAnnotation(Asn1Default.class) != null) {
        	return true;
        }
        return false;
    }

    static class Asn1ContainerFieldSorter {
        /** "Outside extension root" */
        List<Field> extensionFields = new ArrayList<>();
        List<Field> optionalExtensionFields = new ArrayList<>();        
        List<Field> mandatoryExtensionField = new ArrayList<>();        
        /** "Within extension root" */
        List<Field> ordinaryFields = new ArrayList<>();
        List<Field> mandatoryOrdinaryFields = new ArrayList<>();
        List<Field> optionalOrdinaryFields = new ArrayList<>();
        List<Field> allFields = new ArrayList<>();  // Excluding test instrumentation.

        //Map<Field, Boolean> originalAccess = new HashMap<>();

        Asn1ContainerFieldSorter(Class<?> type) {
        	
        	/*
        	 * 
        	 * sorting of the fields added to compensate the error 
        	 * in the java SDK on android where getDeclaredFields does 
        	 * not return the fields in the order of the class definition
        	 * 
        	 */
            List<Field> fields = Arrays.asList(type.getDeclaredFields());
            Collections.sort(fields, new Comparator<Field>() {
                @Override
                public int compare(Field o1, Field o2) {
                    FieldOrder ao1 = o1.getAnnotation(FieldOrder.class);
                    FieldOrder ao2 = o2.getAnnotation(FieldOrder.class);
                    int order1 = ao1 == null ? Integer.MAX_VALUE : ao1.order();
                    int order2 = ao2 == null ? Integer.MAX_VALUE : ao2.order();
                    if (order1 == Integer.MAX_VALUE || order2 == Integer.MAX_VALUE || order1 < 0 || order2 < 0 || order1 == order2) {
                    	logger.debug(String.format("field order error for %s",type.getSimpleName()));
                    }
                    return Integer.compare(order1, order2);
                }
            });
            
        	
            for (Field f : fields) {
                if (isTestInstrumentation(f) || isNonAsn1Field(f) ) {
                    continue;
                }
                //originalAccess.put(f, f.isAccessible());
                f.setAccessible(true);
                if (isExtension(f)) {
                    extensionFields.add(f);
                    if (isOptional(f)) {
                    	optionalExtensionFields.add(f);
                    } else {
                    	mandatoryExtensionField.add(f);
                    }
                }
                else {
                    ordinaryFields.add(f);
                }
                allFields.add(f);
            }
            for (Field f : ordinaryFields) {
                if (isMandatory(f)) {
                    mandatoryOrdinaryFields.add(f);
                } else {
                    optionalOrdinaryFields.add(f);
                } 
            }
        }

        
        public void revertAccess() {
        	/*
            for (Entry<Field, Boolean> entry : originalAccess.entrySet()) {
                entry.getKey().setAccessible(entry.getValue());
            }
            */
        }
    }

    static boolean isTestInstrumentation(Field f) {
        return (f.getName().startsWith("$") || 
        		f.getName().startsWith("_"));
    }
    
    static boolean isNonAsn1Field(Field f) {
        if (f.getAnnotation(NoAsn1Field.class) != null) {
        	return true;
        }
        return false;
    }

    static void encodeLengthOfBitmask(BitBuffer bitbuffer, int n) throws Asn1EncodingException {
        try {
            if (n <= 63) {
                logger.debug(String.format("normally small length of bitmask, length %d <= 63 indicated as bit <0>", n));
                bitbuffer.put(false);
                encodeConstrainedInt(bitbuffer, n, 1, 63);
                return;
            } else {
                logger.debug(String.format("normally small length of bitmask, length %s > 63 indicated as bit <1>", n));
                bitbuffer.put(true);
                encodeLengthDeterminant(bitbuffer, n);
                return;
            }
        } catch (Asn1EncodingException e) {
            throw new Asn1EncodingException(" length of bitmask ", e);
        }
    }
    
    static void encodeSmallInt(BitBuffer bitbuffer, int n) throws Asn1EncodingException {
        try {
            if (n <= 63) {
                logger.debug(String.format("normally small length of bitmask, length %d <= 63 indicated as bit <0>", n));
                bitbuffer.put(false);
                encodeConstrainedInt(bitbuffer, n, 0, 63);
                return;
            } else {
                logger.debug(String.format("normally small length of bitmask, length %s > 63 indicated as bit <1>", n));
                bitbuffer.put(true);
                encodeLengthDeterminant(bitbuffer, n);
                return;
            }
        } catch (Asn1EncodingException e) {
            throw new Asn1EncodingException(" length of bitmask ", e);
        }
    }
    
    static void encodeLengthDeterminant(BitBuffer bitbuffer, int n) throws Asn1EncodingException  {
        try {
            int position = bitbuffer.position();
            if (n < 128) {
                bitbuffer.put(false);
                encodeConstrainedInt(bitbuffer, n, 0, 127);
                logger.debug(String.format("Length determinant %d, encoded as <%s>", n, bitbuffer.toBooleanStringFromPosition(position)));
                if (bitbuffer.position() - position != 8) {
                    throw new AssertionError("length determinant encoded not as 8 bits");
                }
                return;
            } else if (n < NUM_16K) {
                bitbuffer.put(true);
                bitbuffer.put(false);
                encodeConstrainedInt(bitbuffer, n, 0, NUM_16K - 1);
                logger.debug(String.format("Length determinant %d, encoded as 2bits+14bits: <%s>", n,bitbuffer.toBooleanStringFromPosition(position)));
                if (bitbuffer.position() - position != 16) {
                    throw new AssertionError("length determinant encoded not as 16 bits");
                }
                return;
            } else {
                throw new UnsupportedOperationException("Length greater than 16K is not supported yet.");
            }
        } catch (Asn1EncodingException e) {
            throw new Asn1EncodingException(" length determinant ", e);
        }

    }

    static long decodeLengthOfBitmask(BitBuffer bitbuffer) {
        logger.debug("decoding length of bitmask");
        boolean isGreaterThan63 = bitbuffer.get();
        logger.debug(String.format("length determinant extension preamble size flag: preamble size > 63 is %s", isGreaterThan63));
        if (!isGreaterThan63) {
            Long result = decodeConstrainedInt(bitbuffer, newRange(1, 63, false));
            logger.debug(String.format("normally small length of bitmask, length <= 63, decoded as %d", result));
            return result;
        } else {
            logger.debug(String.format("normally small length of bitmask, length > 63, decoding as ordinary length determinant..."));
            return decodeLengthDeterminant(bitbuffer);
        }
    }
    
    static long decodeSmallInt(BitBuffer bitbuffer) {
        logger.debug("decoding small int");
        boolean isGreaterThan63 = bitbuffer.get();
        logger.debug(String.format("length determinant extension preamble size flag: preamble size > 63 is %s", isGreaterThan63));
        if (!isGreaterThan63) {
            Long result = decodeConstrainedInt(bitbuffer, newRange(0, 63, false));
            logger.debug(String.format("normally small length of bitmask, length <= 63, decoded as %d", result));
            return result;
        } else {
            logger.debug(String.format("normally small length of bitmask, length > 63, decoding as ordinary length determinant..."));
            return decodeLengthDeterminant(bitbuffer);
        }
    }  

    static long decodeLengthDeterminant(BitBuffer bitbuffer) {
            boolean bit8 = bitbuffer.get();
            if (!bit8) {  // then value is less than 128
                Long result = decodeConstrainedInt(bitbuffer, newRange(0, 127, false));
                logger.debug(String.format("length determinant, decoded as %d", result));
                return result;
            } else {
                boolean bit7 = bitbuffer.get();
                if (!bit7) {  // then value is less than 16K
                    Long result = decodeConstrainedInt(bitbuffer, newRange(0, NUM_16K - 1, false));
                    logger.debug(String.format("length determinant, decoded as %d", result));
                    return result;
                } else {  // "Large" n
                	logger.debug("lengthes longer than 16K are not supported yet.");
                    throw new UnsupportedOperationException("lengthes longer than 16K are not supported yet.");
                }
            }

    }

    static void encodeConstrainedInt(
            final BitBuffer bitbuffer,
            final long value,
            final long lowerBound,
            final long upperBound) throws Asn1EncodingException {
        encodeConstrainedInt(bitbuffer, value, lowerBound, upperBound, false);
    }

    static void encodeConstrainedInt(
            final BitBuffer bitbuffer,
            final long value,
            final long lowerBound,
            final long upperBound,
            final boolean hasExtensionMarker
            ) throws Asn1EncodingException {
        if (upperBound < lowerBound) {
            throw new IllegalArgumentException("Lower bound "
                + lowerBound + " is larger than upper bound " + upperBound);
        }
        if (!hasExtensionMarker && (value < lowerBound || value > upperBound)) {
            throw new Asn1EncodingException(
                " Value " + value + " is outside of fixed range " +
                        lowerBound + ".." + upperBound);
        }
        final Long range = upperBound - lowerBound + 1;
        final int position = bitbuffer.position();
        if (hasExtensionMarker) {
            boolean outsideOfRange = value < lowerBound || value > upperBound;
            logger.debug(String.format("constrained int with extension marker, %s extension range",outsideOfRange ? "outside" : "within", outsideOfRange ? "1" : "0"));
            bitbuffer.put(outsideOfRange);
            if (outsideOfRange) {
                throw new UnsupportedOperationException(
                    "INT extensions are not supported yet");
            }
        }
        if (range == 1) {
            logger.debug("constrained int of empty range, resulting in empty encoding <>");
            return;
        }
        final BigInteger big = BigInteger.valueOf(value - lowerBound);
        final int numPaddingBits = BigInteger.valueOf(range - 1).bitLength() - big.bitLength();
        for (int i = 0; i < numPaddingBits; i++) {
            bitbuffer.put(false);
        }
        for (int i = big.bitLength() - 1; i >= 0; i--) {
            bitbuffer.put(big.testBit(i));
        }
        logger.debug(String.format("constrained int %d encoded as <%s>", value, bitbuffer.toBooleanStringFromPosition(position)));
        return;
    }

    public static byte[] bytesFromCollection(List<Boolean> bitlist) {
        int sizeBytes = (bitlist.size() + 7) / 8;
        byte[] result = new byte[sizeBytes];
        int byteId = 0;
        byte bitId = 7;
        logger.debug(String.format("byte: < %s >", bitlist));
        for (Boolean b : bitlist) {
            //logger.debug(String.format("bitId: %s, byteId: %s, value: %s", bitId, byteId, b));
            result[byteId] |= (b ? 1 : 0) << bitId;
            bitId--;
            if (bitId < 0) {
                bitId = 7;
                byteId++;
            }
        }
        int nZeros = sizeBytes * 8 - bitlist.size();
        String zeros = nZeros > 0 ? String.format("%0" + nZeros + "d", 0) : "";
        logger.debug(String.format("Padding bits (%d): <%s>", nZeros, zeros));
        return result;
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String hexStringFromBytes(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] bytesFromHexString(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String binaryStringFromBytes(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for (int i = 0; i < Byte.SIZE * bytes.length; i++)
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        return sb.toString();
    }

    private static BitBuffer bitBufferFromBinaryString(String s) {
        ByteBitBuffer result = ByteBitBuffer.allocate(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '1' && s.charAt(i) != '0') {
                throw new IllegalArgumentException("bad character in 'binary' string " + s.charAt(i));
            }
            result.put(s.charAt(i) == '1');
        }
        result.flip();
        return result;
    }




}
