package org.uic.barcode.asn1.uper;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

public class ObjectIdentifierCoder {
	
	
/*
	OID encoding for dummies :) :

	each OID component is encoded to one or more bytes (octets)
	
	OID encoding is just a concatenation of these OID component encodings
	
	first two components are encoded in a special way (see below)
	
	if OID component binary value has less than 7 bits, the encoding is just a single octet, 
	holding the component value (note, most significant bit, leftmost, will always be 0)
	otherwise, if it has 8 and more bits, the value is "spread" into multiple octets - split the 
	binary representation into 7 bit chunks (from right), left-pad the first one with zeroes if needed,
	and form octets from these septets by adding most significant (left) bit 1, except from the last 
	chunk, which will have bit 0 there.
	
	first two components (X.Y) are encoded like it is a single component with a value 40*X + Y
	
	This is a rewording of ITU-T recommendation X.690, chapter 8.19
	
*/
	
	/*
	 * 
The first octet has value 40 * value1 + value2. (This is unambiguous, since value1 is limited to values 0, 1, and 2; value2 is limited to the range 0 to 39 when value1 is 0 or 1; and, according to X.208, n is always at least 2.)

The following octets, if any, encode value3, ..., valuen. 
Each value is encoded base 128, most significant digit first, with as few digits as possible, and the most significant bit of each octet except the last in the value's encoding set to "1."

Example: The first octet of the BER encoding of RSA Data Security, Inc.'s object identifier is 40 * 1 + 2 = 42 = 2a16. The encoding of 840 = 6 * 128 + 4816 is 86 48 and the encoding of 113549 = 6 * 1282 + 7716 * 128 + d16 is 86 f7 0d. This leads to the following BER encoding:

06 06 2a 86 48 86 f7 0d
	 */
	
    private static final Long LONG_LIMIT = (Long.MAX_VALUE >> 7) - 0x7f;	
	
    
    /*
     * adaptation of the bouncy castle implementation available at bouncy castle under APACHE 2.0 license
     */
	public static String decodeObjectId(byte[] bytes) {
		
		StringBuffer objId = new StringBuffer();
	    long value = 0;
	    BigInteger bigValue = null;
	    boolean first = true;

	    for (int i = 0; i != bytes.length; i++)   {
	     
	    	int b = bytes[i] & 0xff;

	        if (value <= LONG_LIMIT)   {
	        	value += (b & 0x7f);
	            if ((b & 0x80) == 0)    {      // end of number reached
	            	
	            	if (first) {
	            		if (value < 40) {
	            			objId.append('0');
	            		} else if (value < 80) {
	            			objId.append('1');
	            			value -= 40;
	                    } else {
	                    	objId.append('2');
	                    	value -= 80;
	                    }
	            		first = false;
	            	}

	            	objId.append('.');
	            	objId.append(value);
	            	value = 0;
	            } else {
	            	value <<= 7;
	            }
	        } else {
	        	if (bigValue == null) {
	        		bigValue = BigInteger.valueOf(value);
	            }
	            bigValue = bigValue.or(BigInteger.valueOf(b & 0x7f));
	            if ((b & 0x80) == 0) {
	            	if (first) {
	            		objId.append('2');
	            		bigValue = bigValue.subtract(BigInteger.valueOf(80));
	            		first = false;
	            	}
	            	objId.append('.');
	            	objId.append(bigValue);
	            	bigValue = null;
	            	value = 0;
	            } else {
	            	bigValue = bigValue.shiftLeft(7);
	            }
	        }
	    }

	    return objId.toString();

	}
	
	
	public static byte[] encodeObjectId(String oids) {
		
		String[] components = oids.split("\\.");
		
		if (components.length < 2)   throw new AssertionError("Object Identifier Format error (" + oids + ")");

		try {
			int first = Integer.parseInt(components[0]) * 40;
		
			ByteArrayOutputStream aOut = new ByteArrayOutputStream();

		
			if (components[1].length() <= 18) {
				writeField(aOut, first + Long.parseLong(components[1]));
			} else {
				writeField(aOut, new BigInteger(components[1]).add(BigInteger.valueOf(first)));
			}
		
			for (int i = 2; i < components.length; i++) {
			
				if (components[i].length() <= 18) {
					writeField(aOut, Long.parseLong(components[i]));
	       		} else {
	       			writeField(aOut, new BigInteger(components[i]));
	       		}
			}

			return aOut.toByteArray();
		
		} catch (NumberFormatException e) {
			 throw new AssertionError("Object Identifier Format error (" + oids + ")");
		}
	}
	

    private static void writeField(ByteArrayOutputStream out, long fieldValue)
    {
        byte[] result = new byte[9];
        int pos = 8;
        result[pos] = (byte)((int)fieldValue & 0x7f);
        while (fieldValue >= (1L << 7)) {
            fieldValue >>= 7;
            result[--pos] = (byte)((int)fieldValue & 0x7f | 0x80);
        }
        out.write(result, pos, 9 - pos);
    }

    private static void writeField(ByteArrayOutputStream out, BigInteger fieldValue)
    {
        int byteCount = (fieldValue.bitLength() + 6) / 7;
        if (byteCount == 0)  {
            out.write(0);
        } else {
            BigInteger tmpValue = fieldValue;
            byte[] tmp = new byte[byteCount];
            for (int i = byteCount - 1; i >= 0; i--) {
                tmp[i] = (byte)((tmpValue.intValue() & 0x7f) | 0x80);
                tmpValue = tmpValue.shiftRight(7);
            }
            tmp[byteCount - 1] &= 0x7f;
            out.write(tmp, 0, tmp.length);
        }
    }
    

	
}
