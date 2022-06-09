package org.uic.barcode.utils;

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECFieldFp;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;


// TODO: Auto-generated Javadoc
/**
 * The Class ECKeyEncoder.
 */
public class ECKeyEncoder {
	

    /** The Constant X962_UNCOMPRESSED_POINT_INDICATOR. */
    private static final byte X962_UNCOMPRESSED_POINT_INDICATOR = 0x04;
    
    /** The Constant X962_ODD. */
    private static final byte X962_ODD = 0x02;
    
    /** The Constant X962_EVEN. */
    private static final byte X962_EVEN = 0x03;
    
    /** The Constant ENCODING_X509. */
    public static final String ENCODING_X509 = "X509";
    
    /** The Constant ENCODING_X962_UNCOMPESSED. */
    public static final String ENCODING_X962_UNCOMPESSED = "X962_UNCOMPRESSED";
    
    /** The Constant ENCODING_X962_COMPRESSED. */
    public static final String ENCODING_X962_COMPRESSED = "X962_COMPRESSED";

    
    /**
     * From encoded.
     *
     * @param keyBytes the encoded key
     * @param oid the algorithm OID of the key algorithm
     * @param provider the provider of the security implementation
     * @return the public key
     */
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
    	
   	
    	//maybe a compressed  X9.62 eliptic key
    	if (keyBytes[0] == X962_ODD || keyBytes[0] == X962_EVEN) {
    		
    		
    		try {
		    	
		    	//we need to know the curve!
				String curveName = EllipticCurveNames.getInstance().getName(oid);

				AlgorithmParameters parameters = AlgorithmParameters.getInstance(keyAlgName, provider);
				parameters.init(new ECGenParameterSpec(curveName));
				ECParameterSpec ecParameters = parameters.getParameterSpec(ECParameterSpec.class);			
				
				//reconstruct the uncompressed version with the curve
				byte[] uncompressed = decompressPubkey(keyBytes, ecParameters);
				
				//decode the uncompressed key
				return fromUncompressedPoint(uncompressed, ecParameters, provider);

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
				return fromUncompressedPoint(keyBytes, ecParameters, provider);
    		
    		} catch(Exception e) {
    			//failed
    		}
    	}
    	
    	
    	throw new IllegalArgumentException("public key format unknown");
    }

    
    
    	/**
	     * Gets the encoded.
	     *
	     * @param key the public key
	     * @param encoding the encoding ("X509","X962_UNCOMPRESSED","X962_COMPRESSED")
    	 * @param provider 
	     * @return the encoded key
	     */
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
	        		
	    	    	int fieldSize = (((ECPublicKey)key).getParams().getCurve().getField().getFieldSize() + 7) / 8;
	    	    	int keySizeBytes = fieldSize + 1;
	    	    	final byte[] compressed = new byte[keySizeBytes];
	  
        		
	   		   		
	        		byte[] x = toUnsignedBytes(point.getAffineX());
	        		
	        		BigInteger y = point.getAffineY();
	        		
	                
	                //compression indicator
	        		if (y.testBit(0)) {
	        			compressed[0] = 0x03;
	        		} else {
	        			compressed[0] = 0x02;
	        		}
	    	    	System.arraycopy(x, 0, compressed, 1 + fieldSize - x.length, x.length);
	                
	                return compressed;
    			}
    		}
    		
    		throw new IllegalArgumentException("unknown encoding");

    		
    	}
    	
 
	    /**
    	 * From uncompressed point.
    	 *
    	 * @param encoded the public key in uncompressed encoding
    	 * @param params the elliptic curve parameters 
    	 * @return the EC public key
    	 * @throws Exception the exception
    	 */
    	private static ECPublicKey fromUncompressedPoint(
	            final byte[] encoded, final ECParameterSpec params, Provider provider)
	            throws Exception {

	        int offset = 0;
	        if (encoded[offset++] != X962_UNCOMPRESSED_POINT_INDICATOR) {
	            throw new IllegalArgumentException("Invalid uncompressedPoint encoding, no uncompressed point indicator");
	        }

	        int keySizeBytes = (params.getOrder().bitLength() + Byte.SIZE - 1)
	                / Byte.SIZE;

	        if (encoded.length != 1 + 2 * keySizeBytes) {
	            throw new IllegalArgumentException("Invalid uncompressedPoint encoding, not the correct size");
	        }

	        final BigInteger x = new BigInteger(1, Arrays.copyOfRange(encoded, offset, offset + keySizeBytes));
	        offset += keySizeBytes;
	        final BigInteger y = new BigInteger(1, Arrays.copyOfRange(encoded, offset, offset + keySizeBytes));
	        final ECPoint w = new ECPoint(x, y);
	        final ECPublicKeySpec ecPublicKeySpec = new ECPublicKeySpec(w, params);
	        final KeyFactory keyFactory = KeyFactory.getInstance("EC",provider);
	        return (ECPublicKey) keyFactory.generatePublic(ecPublicKeySpec);
	    }

	    
	    /**
    	 * To uncompressed point.
    	 *
    	 * @param publicKey the public key
    	 * @return the encoded public key
    	 */
    	private static byte[] toUncompressedPoint(final ECPublicKey publicKey) {
	    	
	    	final byte[] xCoordBytes = toUnsignedBytes(publicKey.getW().getAffineX());
	    	final byte[] yCoordBytes = toUnsignedBytes(publicKey.getW().getAffineY());
	    	
	    	int fieldSize = (publicKey.getParams().getCurve().getField().getFieldSize() + 7) / 8;
	    	int keySizeBytes = 2 * fieldSize + 1;
	    	final byte[] uncompressedPoint = new byte[keySizeBytes];
	    	
	    	System.arraycopy(xCoordBytes, 0, uncompressedPoint, 1 + fieldSize - xCoordBytes.length, xCoordBytes.length);
	    	System.arraycopy(yCoordBytes, 0, uncompressedPoint, 1 + 2 * fieldSize - yCoordBytes.length, yCoordBytes.length);
	    	uncompressedPoint[0] = 0x04;

	        return uncompressedPoint;
	    }
	    

	   
	    /**
    	 * Decompress public key.
    	 *
    	 * @param compressedKey the compressed public key
    	 * @param ecParameters the elliptic curve parameters
    	 * @return uncompressed encoded public key
    	 */
    	private static byte[] decompressPubkey(byte[] compressedKey, ECParameterSpec ecParameters ) {
	    		    		
	    		EllipticCurve curve = ecParameters.getCurve();
	    	
	    	    // Check array length and type indicator byte
	    	    if (compressedKey[0] != 2 && compressedKey[0] != 3) {
	    	        throw new IllegalArgumentException();
	    	    }

	    	    final byte[] xCoordBytes = Arrays.copyOfRange(compressedKey, 1, compressedKey.length);
	    	    final BigInteger xCoord = new BigInteger(1, xCoordBytes);  // Range [0, 2^256)	 
	    	    
	    	    ECFieldFp ecf = (ECFieldFp) curve.getField();
	    	    BigInteger modulus = ecf.getP();
	    	    BigInteger pow = modulus.add(BigInteger.ONE).shiftRight(2);
	    	    
	    	    
	    	    BigInteger temp = xCoord.pow(2).add(curve.getA());
	    	    temp = temp.multiply(xCoord);
	    	    temp = temp.add(curve.getB());
		        temp = temp.modPow(pow, modulus);		        
	    	    boolean tempIsOdd = temp.testBit(0);
	    	    boolean yShouldBeOdd = compressedKey[0] == 3;
	    	    if (tempIsOdd != yShouldBeOdd) {
	    	        temp = temp.negate().mod(modulus);
	    	    }
	    	    final BigInteger yCoord = temp;
	    	    
	    	    final byte[] yCoordBytes = toUnsignedBytes(yCoord);
	    	    
		    	int fieldSize = (curve.getField().getFieldSize() + 7) / 8;
		    	byte[] result = new byte[2 * fieldSize + 1];
    	    	System.arraycopy(compressedKey, 0,result, 1 + fieldSize - compressedKey.length, compressedKey.length);
    	    	System.arraycopy(yCoordBytes, 0,result, 1 + 2 * fieldSize - yCoordBytes.length, yCoordBytes.length);
	    	    // set uncompressed key indicator
	    	    result[0] = 0x04;

	    	    return result;
	    }
	        
		/**
		 * To unsigned bytes.
		 *
		 * @param i the i
		 * @return the unsigned bytes of the integer
		 */
		private static byte[] toUnsignedBytes(BigInteger i) {
			byte[] b = i.abs().toByteArray();
			//remove top sign bit  
			if (b[0] == 0) {
				b = Arrays.copyOfRange(b, 1, b.length);
			}
			return b;
		}


}
