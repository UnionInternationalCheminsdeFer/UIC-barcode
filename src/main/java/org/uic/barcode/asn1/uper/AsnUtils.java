package org.uic.barcode.asn1.uper;

import java.math.BigInteger;

public class AsnUtils {
	

    private static byte[] mask = new byte[] {
    	     (byte) 0b1000_0000,
    	            0b0100_0000,
    	            0b0010_0000,
    	            0b0001_0000,
    	            0b0000_1000,
    	            0b0000_0100,
    	            0b0000_0010,
    	            0b0000_0001,
    	    };
    
    
	public static byte[] fromBooleanString(final String s) {
		
		char[] ascii = s.toCharArray();
        if (ascii == null || ascii.length == 0) {
            return null;
        }
        // get length/8 times bytes with 3 bit shifts to the right of the length
        final byte[] l_raw = new byte[ascii.length >> 3];
        /*
         * We decr index jj by 8 as we go along to not recompute indices using multiplication every time inside the
         * loop.
         */
        for (int ii = 0, jj = 0; ii < l_raw.length; ii++, jj += 8) {
            for (int bits = 0; bits < mask.length; ++bits) {
                if (ascii[jj + bits] == '1') {
                    l_raw[ii] |= mask[bits];
                }
            }
        }
        return l_raw;
    }
	
	public static String toBooleanString(byte[] bytes) {
	        StringBuilder sb = new StringBuilder(bytes.length);
	        for (int i = 0; i < bytes.length*8;i++) {
	            sb.append(AsnUtils.get(bytes,i) ? "1" : "0");
	        }
	        return sb.toString();
	}
	
	public static boolean get(byte[] bytes, int index) {
	        
		if (index < 0) {
	            throw new IndexOutOfBoundsException("Index " + index + " is less than 0");
	    } else if (index >= bytes.length * 8) {
	            throw new IndexOutOfBoundsException("Index " + index + " violates the limit " + bytes.length*8);
	    }
	    boolean result = (bytes[index / 8] & mask[index % 8]) != 0;
	    return result;
	}
	
	public static byte[] shiftBytesToLeft(byte[] bytes, int shift) {
		
		// create from array
		BigInteger bigInt = new BigInteger(bytes);

		// shift
		BigInteger shiftInt = bigInt.shiftLeft(shift);

		// back to array
		return shiftInt.toByteArray();

	}
	

}
