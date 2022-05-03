package org.uic.barcode.utils;

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
		
		
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		
		String name;
		try {
			name = AlgorithmNameResolver.getAlgorithmName(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, keyAlgorithmOid, null);
		} catch (Exception e2) {
			return null;
		}
			
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
 		    		keyFactory.generatePublic(keySpec);
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
}
