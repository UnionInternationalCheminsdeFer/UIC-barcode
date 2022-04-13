package org.uic.barcode.test;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.utils.AlgorithmNameResolver;
import org.uic.barcode.utils.SecurityUtils;

public class SecurityUtilsTest {
	

		
	public KeyPair keyPairCk = null;
	public KeyPair keyPairCr = null;
	public KeyPair keyPairDsa = null;
	public KeyPair keyPairECDSACk = null;
	public KeyPair keyPairECDSACr = null;
	
		
	public Provider provider = null;
	
	
	@Before public void initialize() {
		
		provider = new BouncyCastleProvider();
		Security.addProvider(new BouncyCastleProvider());
		
		try {
			keyPairCk  = generateECKeys(Constants.KG_EC_256, "secp256k1");
			keyPairCr  = generateECKeys(Constants.KG_EC_256, "secp256r1");
			keyPairECDSACk  = generateECDSAKeys(Constants.KG_EC_256, "secp256k1");
			keyPairECDSACr  = generateECDSAKeys(Constants.KG_EC_256, "secp256r1");
			keyPairDsa = generateDsaKeys();
		} catch (Exception e) {
			assert(false);
		}

        assert(keyPairCk != null);
        
        assert(keyPairCr != null);
        
        assert(keyPairDsa != null);
        
	}
	
	




	@Test public void testFindPublicKeyProvider() {
			
		
		Provider p = null;
		boolean canSign = false;
		
		p = SecurityUtils.findPublicKeyProvider(Constants.KG_EC_256,keyPairCk.getPublic().getEncoded());
		assert (p != null);
		canSign = testSignature(p,Constants.ECDSA_SHA256,keyPairCk.getPrivate());
		assert(canSign == true);
		
		p = SecurityUtils.findPublicKeyProvider(Constants.KG_EC_256,keyPairCr.getPublic().getEncoded());
		assert (p != null);
		canSign = testSignature(p,Constants.ECDSA_SHA256,keyPairCr.getPrivate());
		assert(canSign == true);
		
		p = SecurityUtils.findPublicKeyProvider(Constants.KG_EC_256,keyPairECDSACk.getPublic().getEncoded());
		assert (p != null);
		canSign = testSignature(p,Constants.ECDSA_SHA256,keyPairECDSACk.getPrivate());
		assert(canSign == true);
		
		p = SecurityUtils.findPublicKeyProvider(Constants.KG_EC_256,keyPairECDSACr.getPublic().getEncoded());
		assert (p != null);
		canSign = testSignature(p,Constants.ECDSA_SHA256,keyPairECDSACr.getPrivate());
		assert(canSign == true);
		
		p = SecurityUtils.findPublicKeyProvider("1.2.840.10040",keyPairDsa.getPublic().getEncoded());
		assert (p != null);
		canSign = testSignature(p,Constants.DSA_SHA256,keyPairDsa.getPrivate());
		assert(canSign == true);
		
	}	
	





	@Test public void testFindSignatureAlgorithmProvider() {
	
		Provider p = null;
		boolean canSign = false;
		
		p = SecurityUtils.findPrivateKeyProvider(keyPairCk.getPrivate());
		assert (p != null);
		canSign = testSignature(p,Constants.ECDSA_SHA256,keyPairCk.getPrivate());
		assert(canSign == true);

				
		p = SecurityUtils.findPrivateKeyProvider(keyPairCr.getPrivate());
		assert (p != null);
		canSign = testSignature(p,Constants.ECDSA_SHA256,keyPairCr.getPrivate());
		assert(canSign == true);

		p = SecurityUtils.findPrivateKeyProvider(keyPairECDSACk.getPrivate());
		assert (p != null);
		canSign = testSignature(p,Constants.ECDSA_SHA256,keyPairECDSACk.getPrivate());
		assert(canSign == true);

				
		p = SecurityUtils.findPrivateKeyProvider(keyPairECDSACr.getPrivate());
		assert (p != null);
		canSign = testSignature(p,Constants.ECDSA_SHA256,keyPairECDSACr.getPrivate());
		assert(canSign == true);
		
		p = SecurityUtils.findPrivateKeyProvider(keyPairDsa.getPrivate());
		assert (p != null);
		canSign = testSignature(p,Constants.DSA_SHA256,keyPairDsa.getPrivate());
		assert(canSign == true);

		
	}	
	
		
	public KeyPair generateECKeys(String keyAlgorithmOid, String curve)  throws Exception{
		
		//ECNamedCurveGenParameterSpec namedParamSpec = new ECNamedCurveGenParameterSpec(elipticCurve);
		
	    ECGenParameterSpec namedParamSpec = new ECGenParameterSpec(curve);
	    KeyPairGenerator ecKPGen = KeyPairGenerator.getInstance("EC", "BC");
	    ecKPGen.initialize(namedParamSpec, new SecureRandom());
	    KeyPair keyPair = ecKPGen.generateKeyPair();
	    KeyPair kp = new KeyPair(SecurityUtils.convert(keyPair.getPublic(), provider),SecurityUtils.convert(keyPair.getPrivate(), provider));
	    return kp;
    }

	public KeyPair generateECDSAKeys(String keyAlgorithmOid, String curve)  throws Exception{
		
		//ECNamedCurveGenParameterSpec namedParamSpec = new ECNamedCurveGenParameterSpec(elipticCurve);
		
	    ECGenParameterSpec namedParamSpec = new ECGenParameterSpec(curve);
	    KeyPairGenerator ecKPGen = KeyPairGenerator.getInstance("ECDSA", "BC");
	    ecKPGen.initialize(namedParamSpec, new SecureRandom());
	    KeyPair keyPair = ecKPGen.generateKeyPair();
	    KeyPair kp = new KeyPair(SecurityUtils.convert(keyPair.getPublic(), provider),SecurityUtils.convert(keyPair.getPrivate(), provider));
	    return kp;
    }
	
	private KeyPair generateDsaKeys() {
		
		KeyPairGenerator g = null;
		try {
			g = KeyPairGenerator.getInstance("DSA", "BC");
		} catch (NoSuchAlgorithmException e) {
			assert(false);
		} catch (NoSuchProviderException e) {
			assert(false);
		}
	    g.initialize(1024, new SecureRandom());
	    
	    KeyPair keyPair = g.generateKeyPair();
	    KeyPair kp = new KeyPair(SecurityUtils.convert(keyPair.getPublic(), provider),SecurityUtils.convert(keyPair.getPrivate(), provider));
	    return kp;
	   
	}
	
	private boolean testSignature(Provider provider, String signatureAlgorithmOid, PrivateKey privateKey) {
		
		String sigAlgName = null;
		try {
			sigAlgName = AlgorithmNameResolver.getSignatureAlgorithmName(signatureAlgorithmOid,provider);
		} catch (Exception e) {
			assert(false);
		}
		assert(sigAlgName != null);
		
		
		Signature sig = null;
		try {
			sig = Signature.getInstance(sigAlgName,provider);
		} catch (Exception e) {
			assert (false);
		}
			
		try {
			sig.initSign(privateKey);
		} catch (InvalidKeyException e) {
			assert(false);
		}
		try {
			sig.update("ABCDEFGHI".getBytes());
		} catch (SignatureException e) {
			assert(false);
		}
		byte[] signature = null;
		try {
			signature = sig.sign();
		} catch (SignatureException e) {
			assert(false);
		}
		
		assert(signature != null);
		assert(signature.length > 5);
		
		return true;
	}


}
