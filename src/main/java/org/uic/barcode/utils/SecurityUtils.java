package org.uic.barcode.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Provider.Service;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/**
 * The Class SecurityUtils.
 */
public class SecurityUtils {
	
	/**
	 * Find provider by public key.
	 *
	 * @param algorithmOid the algorithm oid used to generate the key
	 * @param keyBytes the encoded bytes of the public key 
	 * @return the provider
	 */
	public static Provider findPublicKeyProvider(String keyAlgorithmOid, byte[] keyBytes) {
				
 		Provider[] provs = Security.getProviders();
 		for (Provider provider : provs) {
 			try {
 				PublicKey key = ECKeyEncoder.fromEncoded(keyBytes, keyAlgorithmOid, provider);
 				if (key != null) return provider;
			} catch (Exception e1) {
				//try next
			} 
 		}
 		return null;
	}

	/**
	 * Find private key provider.
	 *
	 * @param key the private key
	 * @return the provider
	 */
	public static Provider findPrivateKeyProvider(PrivateKey key) {
		
		String name = key.getAlgorithm();
		byte[] keyBytes = key.getEncoded();
		
		if (keyBytes == null || keyBytes.length == 0) {
			return null;
		}
			
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		
	
		KeyFactory keyFactory = null;
		
 		Provider[] provs = Security.getProviders();
 		for (Provider provider : provs) {
 			try {
 				keyFactory = KeyFactory.getInstance(name,provider);
			} catch (NoSuchAlgorithmException e1) {
				//try next
			} 
 		    if (keyFactory != null) {
 		    	try {
 		    		keyFactory.generatePrivate(keySpec);
 		    		return provider;
 		    	} catch (Exception e) {
 		    		provider = null;
 		    		//try next
 		    	}
 		    }
 		}
 		
 		return null;
	}
	
	
	
	/**
	 * Convert.
	 *
	 * @param key the key
	 * @param provider the provider
	 * @return the public key
	 */
	public static PublicKey convert(PublicKey key, Provider provider) {
		
		PublicKey publicKey;
		KeyFactory keyFactory = null;
		
		try {
			if (key.getAlgorithm() != null && key.getAlgorithm().toUpperCase().contains("EC") ) {
				keyFactory = KeyFactory.getInstance("EC",provider);
			} else if (key.getAlgorithm() != null && key.getAlgorithm().length() > 0 ) {
				keyFactory = KeyFactory.getInstance("DSA",provider);
			} else {
				return key;
			}
			publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(key.getEncoded()));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			return key;
		}
		
		return publicKey;
		
		
	}
	
	
	/**
	 * Convert.
	 *
	 * @param key the key
	 * @param provider the provider
	 * @return the private key
	 */
	public static PrivateKey convert(PrivateKey key, Provider provider) {
		
		PrivateKey privateKey;
		KeyFactory keyFactory = null;
		
		try {
			if (key.getAlgorithm() != null && key.getAlgorithm().toUpperCase().contains("EC") ) {
				keyFactory = KeyFactory.getInstance("EC",provider);
			} else if (key.getAlgorithm() != null && key.getAlgorithm().length() > 0 ) {
				keyFactory = KeyFactory.getInstance("DSA",provider);
			} else {
				return key;
			}
			privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(key.getEncoded()));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			return key;
		}
		
		return privateKey;
		
		
	}

	/**
	 * Find signature provider.
	 *
	 * @param encoded the encoded
	 * @param oid the oid
	 * @return the provider
	 */
	public static Provider findSignatureProvider(byte[] encoded, String oid) {
		
		KeyFactory keyFactory = null;
		String signatureAlgorithmName = null;
		
		Provider[] provs = Security.getProviders();
 		for (Provider provider : provs) {
 			try {
 				Service service = provider.getService(AlgorithmNameResolver.TYPE_SIGNATURE_ALG, oid);
 				if (service != null) {
 					signatureAlgorithmName = service.getAlgorithm();
 					if (signatureAlgorithmName != null && signatureAlgorithmName.length() > 0) {
 						if (signatureAlgorithmName.toUpperCase().contains("EC") ) {
 							keyFactory = KeyFactory.getInstance("EC",provider);
 						} else {
 							keyFactory = KeyFactory.getInstance("DSA",provider);
 						}
 						if (keyFactory != null) {
 							X509EncodedKeySpec spec = new X509EncodedKeySpec(encoded);
 							//try to encode the key
 							keyFactory.generatePublic(spec);						
 						}
 					}
 				}
			} catch (Exception e1) {
				keyFactory = null;
			} 
 		    if (keyFactory != null) {
 		    	return keyFactory.getProvider();
 		    }
 		}
		
		return null;
	}
	
	/**
	 * Decode signature integer sequence.
	 * 
	 * Support function to decode a DSA signature
	 * Provides the two DSA signature parameter encoded in a DSA signature
	 *
	 * @param bytes the bytes
	 * @return the big integer[]
	 * @throws Exception the exception
	 */
	public static BigInteger[] decodeSignatureIntegerSequence(byte[] bytes) throws Exception {

		int sequenceTag = (int) bytes[0];
		
		if (sequenceTag != 48) throw new Exception("signature is not a sequence");
		
		int sequenceLength = (int) bytes[1];
		
		if (sequenceLength < 6) throw new Exception("signature sequence too short");
		
		BigInteger[] result = new BigInteger[2];
		
		int offset = 2;
		int i = 0;
		while (offset < bytes.length && i < 2) {
			int integerTag = (int) bytes[offset];
			if (integerTag != 2) throw new Exception("signature is not an integer sequence");
			int integerLength = (int) bytes[offset + 1];
			byte[] value = Arrays.copyOfRange(bytes, offset + 2, offset + 2 + integerLength);		
			result[i] = new BigInteger(+1, value);
			offset = offset + integerLength + 2;
			i++;
		}

		return result;
	}
	
	/**
	 * Encode signature integer sequence.
	 *  
	 *  Support function to format two parameters as DER encoded integer list 
	 *  to get a valid formated DSA signature from the signature parameter
	 *
	 * @param i1 the i 1
	 * @param i2 the i 2
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] encodeSignatureIntegerSequence(BigInteger i1, BigInteger i2) throws IOException {
		
		//SEQUENCE OF --> tag 16
		int sequenceTag = 16 + 32;  // (bits 6 = 1 constructed)
		//INTEGER     --> tag 2
		int integerTag = 2;
			
		byte[] b1 = i1.toByteArray();
        int lb1 = b1.length;
		byte[] b2 = i2.toByteArray();
        int lb2 = b2.length;		
        
        int sequenceLength = lb1 + lb2 + 4;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		out.write((byte) sequenceTag);
		out.write((byte) sequenceLength);   
		out.write((byte) integerTag);  
		out.write((byte) lb1);  
		out.write(b1);   
		out.write((byte) integerTag);  
		out.write((byte) lb2);  
		out.write(b2);   

		return out.toByteArray();
	}

	public static byte[] recombineDsaSignature(byte[] sealdata) throws IOException {
		
		//check whether the encoding is wrong and the sealdata contain a signature
		//remove trailing zeroes from the signature
		BigInteger[] bInts = null;
		try {
			bInts = decodeSignatureIntegerSequence(sealdata);
			byte[] sig = encodeSignatureIntegerSequence(bInts[0],bInts[1]);
			//decoding the entire signature was ok, so there was no split
			return sig;
		} catch (Exception e) {
			//the signature is correctly implemented, continue with recombination
		}

		// split the data into two blocks
		int length = sealdata.length / 2;
		byte[] rBytes = Arrays.copyOfRange(sealdata,      0, length);
		byte[] sBytes = Arrays.copyOfRange(sealdata, length, length + length);	
		
		//convert to BigInteger to get rid of leading zeroes
		BigInteger r = new BigInteger(1,rBytes);
		BigInteger s = new BigInteger(1,sBytes);	
		
		//encode as DSA signature structure
		//SEQUENCE OF --> tag 16
		int sequenceTag = 16 + 32;  // (bits 6 = 1 constructed)
		//INTEGER     --> tag 2
		int integerTag = 2;
		
		byte[] b1 = r.toByteArray();
        int lb1 = b1.length;     

        byte[] b2 = s.toByteArray();	
        int lb2 = b2.length;		
        int sequenceLength = lb1 + lb2 + 4;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte) sequenceTag);
		out.write((byte) sequenceLength);   
		out.write((byte) integerTag);  
		out.write((byte) lb1);  
		out.write(b1);   
		out.write((byte) integerTag);  
		out.write((byte) lb2);  
		out.write(b2);   
		return out.toByteArray();		

	}
}
