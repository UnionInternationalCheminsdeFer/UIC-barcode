package org.uic.barcode.utils;

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;


public class ECKeyEncoder {
	

    private static final byte X962_UNCOMPRESSED_POINT_INDICATOR = 0x04;
    private static final byte X962_ODD = 0x02;
    private static final byte X962_EVEN = 0x03;
    
    public static final String ENCODING_X509 = "X509";
    public static final String ENCODING_X962_UNCOMPESSED = "X962_UNCOMPRESSED";
    public static final String ENCODING_X962_COMPRESSED = "X962_COMPRESSED";
    
    public static PublicKey fromEncoded (byte[] keyBytes, String oid, Provider provider) {
    	
    	PublicKey key = null;
    	
    	
		String keyAlgName = null;
		try {
			keyAlgName = AlgorithmNameResolver.getName(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, oid,provider);
		} catch (Exception e1) {
			throw new IllegalArgumentException("algorithm unknown in: " + provider.getName());
		}

		if (keyAlgName == null || keyAlgName.length() == 0) {
			throw new IllegalArgumentException("algorithm unknown in: " + provider.getName());
		}
    	
    	//try standard X.509 encoding first
    	
    	try {
    		
    		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    		    		
    		if (keyAlgName != null && keyAlgName.length() > 0) {
    		
    			KeyFactory keyFactory = KeyFactory.getInstance(keyAlgName,provider);

    			key = keyFactory.generatePublic(keySpec);
    			
    		}
			
    	} catch (Exception e) {
    		
    		//try next option
    		
    	}
    	
    	if (key != null) return key;
    	
    	if (keyBytes[0] ==  X962_UNCOMPRESSED_POINT_INDICATOR) {
    		
    		
    		
    	}
    	
    	//maybe a compressed  X9.62 eliptic key
    	if (keyBytes[0] == X962_ODD || keyBytes[0] == X962_EVEN) {
    		
    		
    		try {
		    	
		    	//we need to know the curve!
				String curveName = EllipticCurveNames.getInstance().getName(oid);
				
				//get the curve parameters
				AlgorithmParameters parameters = AlgorithmParameters.getInstance(keyAlgName, provider);
				parameters.init(new ECGenParameterSpec(curveName));
				ECParameterSpec ecParameters = parameters.getParameterSpec(ECParameterSpec.class);
				EllipticCurve curve = ecParameters.getCurve();
				
				//reconstruct the uncompressed version with the curve
				byte[] uncompressed = decompressPubkey(keyBytes, curve);
				
				//decode the uncompressed key
				return fromUncompressedPoint(uncompressed, ecParameters);

			} catch (Exception e) {
				key = null;
				// failed
			}
 
		
    	}
    	
    	//try X962 uncompressed
    	if (keyBytes[0] == X962_UNCOMPRESSED_POINT_INDICATOR) {
    		
    		try {
    			//we need to know the curve!
    			String curveName = EllipticCurveNames.getInstance().getName(oid);
			
    			//get the curve parameters
    			AlgorithmParameters parameters = AlgorithmParameters.getInstance(keyAlgName, provider);
    			parameters.init(new ECGenParameterSpec(curveName));
    			ECParameterSpec ecParameters = parameters.getParameterSpec(ECParameterSpec.class);
			
    			//decode the uncompressed key
				return fromUncompressedPoint(keyBytes, ecParameters);
    		
    		} catch(Exception e) {
    			//failed
    		}
    	}
    	
    	
    	throw new IllegalArgumentException("public key format unknown");
    }

    
    
    	public static byte[] getEncoded(PublicKey key, String encoding){
    		
    		if (encoding.equals(ENCODING_X509)) {
    			return key.getEncoded();
    		} else if (encoding.equals(ENCODING_X962_UNCOMPESSED)) {
    			
    			if (key instanceof ECPublicKey) {
    			
    				return toUncompressedPoint((ECPublicKey) key);
    				
    			}
    			
    		} else if (encoding.equals(ENCODING_X962_COMPRESSED)) {
    			
    			if (key instanceof ECPublicKey) {
    			
	        		ECPoint point = ((ECPublicKey) key).getW();
	   		   		
	        		byte[] x = toUnsignedBytes(point.getAffineX());
	        		
	        		BigInteger y = point.getAffineY();
	        		
	                byte[] compressed = new byte[x.length + 1];
	                
	                //compression indicator
	        		if (y.testBit(0)) {
	        			compressed[0] = 0x03;
	        		} else {
	        			compressed[0] = 0x02;
	        		}
	                System.arraycopy(x, 0, compressed, 1, x.length);
	                
	                return compressed;
    			}
    		}
    		
    		throw new IllegalArgumentException("unknown encoding");

    		
    	}
    	
 
	    private static ECPublicKey fromUncompressedPoint(
	            final byte[] uncompressedPoint, final ECParameterSpec params)
	            throws Exception {

	        int offset = 0;
	        if (uncompressedPoint[offset++] != X962_UNCOMPRESSED_POINT_INDICATOR) {
	            throw new IllegalArgumentException(
	                    "Invalid uncompressedPoint encoding, no uncompressed point indicator");
	        }

	        int keySizeBytes = (params.getOrder().bitLength() + Byte.SIZE - 1)
	                / Byte.SIZE;

	        if (uncompressedPoint.length != 1 + 2 * keySizeBytes) {
	            throw new IllegalArgumentException(
	                    "Invalid uncompressedPoint encoding, not the correct size");
	        }

	        final BigInteger x = new BigInteger(1, Arrays.copyOfRange(
	                uncompressedPoint, offset, offset + keySizeBytes));
	        offset += keySizeBytes;
	        final BigInteger y = new BigInteger(1, Arrays.copyOfRange(
	                uncompressedPoint, offset, offset + keySizeBytes));
	        final ECPoint w = new ECPoint(x, y);
	        final ECPublicKeySpec ecPublicKeySpec = new ECPublicKeySpec(w, params);
	        final KeyFactory keyFactory = KeyFactory.getInstance("EC");
	        return (ECPublicKey) keyFactory.generatePublic(ecPublicKeySpec);
	    }

	    private static byte[] toUncompressedPoint(final ECPublicKey publicKey) {

	        int keySizeBytes = (publicKey.getParams().getOrder().bitLength() + Byte.SIZE - 1)
	                / Byte.SIZE;

	        final byte[] uncompressedPoint = new byte[1 + 2 * keySizeBytes];
	        int offset = 0;
	        uncompressedPoint[offset++] = 0x04;

	        final byte[] x = publicKey.getW().getAffineX().toByteArray();
	        if (x.length <= keySizeBytes) {
	            System.arraycopy(x, 0, uncompressedPoint, offset + keySizeBytes
	                    - x.length, x.length);
	        } else if (x.length == keySizeBytes + 1 && x[0] == 0) {
	            System.arraycopy(x, 1, uncompressedPoint, offset, keySizeBytes);
	        } else {
	            throw new IllegalStateException("x value is too large");
	        }
	        offset += keySizeBytes;

	        final byte[] y = publicKey.getW().getAffineY().toByteArray();
	        if (y.length <= keySizeBytes) {
	            System.arraycopy(y, 0, uncompressedPoint, offset + keySizeBytes
	                    - y.length, y.length);
	        } else if (y.length == keySizeBytes + 1 && y[0] == 0) {
	            System.arraycopy(y, 1, uncompressedPoint, offset, keySizeBytes);
	        } else {
	            throw new IllegalStateException("y value is too large");
	        }

	        return uncompressedPoint;
	    }
	    
	    static final BigInteger MODULUS =
	    	    new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16);
	    
	    static final BigInteger POW = MODULUS.add(BigInteger.ONE).shiftRight(2);

	    // Given a 33-byte compressed public key, this returns a 65-byte uncompressed key.
	    private static byte[] decompressPubkey(byte[] compressedKey, EllipticCurve curve ) {
	    		    		
	    	    // Check array length and type indicator byte
	    	    if (compressedKey.length != 33 || compressedKey[0] != 2 && compressedKey[0] != 3)
	    	        throw new IllegalArgumentException();

	    	    final byte[] xCoordBytes = Arrays.copyOfRange(compressedKey, 1, compressedKey.length);
	    	    final BigInteger xCoord = new BigInteger(1, xCoordBytes);  // Range [0, 2^256)	 
	    	    
	    	    
	    	    BigInteger temp = xCoord.pow(2).add(curve.getA());
	    	    temp = temp.multiply(xCoord);
	    	    temp = temp.add(curve.getB());
		        temp = temp.modPow(POW, MODULUS);
	    	    
	    	    //temp = sqrtMod(temp.add(curveParamB));
		        
	    	    boolean tempIsOdd = temp.testBit(0);
	    	    boolean yShouldBeOdd = compressedKey[0] == 3;
	    	    if (tempIsOdd != yShouldBeOdd)
	    	        temp = temp.negate().mod(MODULUS);
	    	    final BigInteger yCoord = temp;

	    	    // Copy the x coordinate into the new
	    	    // uncompressed key, and change the type byte
	    	    byte[] result = Arrays.copyOf(compressedKey, 65);
	    	    result[0] = 0x04;

	    	    // Carefully copy the y coordinate into uncompressed key
	    	    final byte[] yCoordBytes = yCoord.toByteArray();
	    	    for (int i = 0; i < 32 && i < yCoordBytes.length; i++)
	    	        result[result.length - 1 - i] = yCoordBytes[yCoordBytes.length - 1 - i];

	    	    return result;
	    }
	        
		private static byte[] toUnsignedBytes(BigInteger i) {
			byte[] b = i.abs().toByteArray();
			//remove top sign bit  
			if (b[0] == 0) {
				b = Arrays.copyOfRange(b, 1, b.length);
			}
			return b;
		}


}
