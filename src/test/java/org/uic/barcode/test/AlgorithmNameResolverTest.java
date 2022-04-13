package org.uic.barcode.test;

import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.utils.AlgorithmNameResolver;

public class AlgorithmNameResolverTest {
	
	public String signatureAlgorithmOID = null;
	public String elipticCurve = null;
	public String keyPairAlgorithmOID = null;
	
	
	public Provider provider = null;
	
	
	@Before public void initialize() {
		
	
		
		signatureAlgorithmOID = Constants.ECDSA_SHA256;
		keyPairAlgorithmOID = Constants.KG_EC_256;
		elipticCurve = "secp256k1";
		

		provider = new BouncyCastleProvider();
		Security.addProvider(new BouncyCastleProvider());
        
	}
	
	

	@Test public void testSignatureAlgorithmName() {
		
		String name = null;
		
		try {
			name = AlgorithmNameResolver.getAlgorithmName(AlgorithmNameResolver.TYPE_SIGNATURE_ALG, signatureAlgorithmOID, null);
		} catch (Exception e) {
			assert(false);
		}     
		
		assert(name.equals("SHA256withECDSA"));
		
		//default values:
		
		try {
			name = AlgorithmNameResolver.getAlgorithmName(AlgorithmNameResolver.TYPE_SIGNATURE_ALG, "1.2.840.10045", null);
		} catch (Exception e) {
			assert(false);
		}     
		
		assert(name.equals("ECDSA"));
		
		try {
			name = AlgorithmNameResolver.getAlgorithmName(AlgorithmNameResolver.TYPE_SIGNATURE_ALG, "1.2.840.10040", null);
		} catch (Exception e) {
			assert(false);
		}     
		
		assert(name.equals("DSA"));
		
		//custom value
		
		AlgorithmNameResolver.addMap(AlgorithmNameResolver.TYPE_SIGNATURE_ALG, "1.2.3.4.5", "TEST");
		
		try {
			name = AlgorithmNameResolver.getAlgorithmName(AlgorithmNameResolver.TYPE_SIGNATURE_ALG, "1.2.3.4.5", null);
		} catch (Exception e) {
			assert(false);
		}     
		
		assert(name.equals("TEST"));
		
        
	}	
	
	@Test public void testKeyGeneratorAlgorithmName() {
		
		String name = null;
		
		try {
			name = AlgorithmNameResolver.getAlgorithmName(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, keyPairAlgorithmOID, null);
		} catch (Exception e) {
			assert(false);
		}     
		
		assert(name.equals("EC"));
		
		//default values:
		
		try {
			name = AlgorithmNameResolver.getAlgorithmName(AlgorithmNameResolver.TYPE_SIGNATURE_ALG, "1.2.840.10045.3", null);
		} catch (Exception e) {
			assert(false);
		}     
		
		assert(name.equals("EC"));
		
	
		
		//custom value
		
		AlgorithmNameResolver.addMap(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, "1.2.3.4.5", "TEST");
		
		try {
			name = AlgorithmNameResolver.getAlgorithmName(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, "1.2.3.4.5", null);
		} catch (Exception e) {
			assert(false);
		}     
		
		assert(name.equals("TEST"));
		
        
	}	
	

}
