package org.uic.barcode.utils;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SecurityUtils {
	
	public static KeyFactory findKeyFactory(String oid, byte[] keyBytes) {
		
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		
		String name = null;
		try {
			name = AlgorithmNameResolver.getName(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, oid);
		} catch (Exception e2) {
			return null;
		}
		if (name == null || name.length() == 0) {
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
 		    		return keyFactory;
 		    	} catch (Exception e) {
 		    		//try next
 		    	}
 		    }
 		}
		return null;

	}
	

	public static Provider findPrivateKeyProvider(PrivateKey key) {
		
		String name = key.getAlgorithm();
		byte[] keyBytes = key.getEncoded();
				
			
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
	

	
	public static PublicKey convertPublicKey(PublicKey key) {
		
		
		PublicKey publicKey;
		try {
			publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(key.getEncoded()));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			return key;
		}
		
		return publicKey;
		
	}
	
	
	public static PublicKey convert(PublicKey key, Provider provider) {
		
		PublicKey publicKey;
		try {
			publicKey = KeyFactory.getInstance("RSA", provider).generatePublic(new X509EncodedKeySpec(key.getEncoded()));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			return key;
		}
		
		return publicKey;
		
		
	}
	
	
	public static PrivateKey convertPrivateKey(PrivateKey key) {
		
		
		PrivateKey privateKey;
		try {
			privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(key.getEncoded()));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			return key;
		}
		
		return privateKey;
		
	}
}
