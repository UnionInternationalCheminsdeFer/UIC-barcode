package org.uic.barcode.test;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.utils.AlgorithmNameResolver;
import org.uic.barcode.utils.ECKeyEncoder;

public class ECKeyEncoderTest521 {
	
	public static Provider provider = null;
	
	@Before public void initialize() {
		
		provider = new BouncyCastleProvider();
		Security.addProvider(provider);
		
		AlgorithmNameResolver.addMap(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, "1.3.132.0.35", "EC");
	}
	
	@Test public void testEncodeCompressed() throws Exception {

        for (int i = 0; i < 10; i++) {
        	
        	String name = "secp521r1";
        	
        	AlgorithmNameResolver.addMap(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, "1.3.132.0.35", "EC");
        	
        	ECPublicKey key = createECPublicKey(name);

            // some additional encoding tricks
            byte[] compressedBC = getCompressed(key,name);
            String compressedHexBC = Hex.toHexString(compressedBC);
 
       		byte[] publicKeyBytes = ECKeyEncoder.getEncoded(key, ECKeyEncoder.ENCODING_X962_COMPRESSED);	
       		String compressedHex = Hex.toHexString(publicKeyBytes);
       		
       		if (!compressedHexBC.equals(compressedHex)) {
       			assert(compressedHexBC.equals(compressedHex));
       		}
       		
    		assert(Arrays.equals(compressedBC, publicKeyBytes));
        
    		assert(compressedHexBC.equals(compressedHex));
        }
    }
	
	@Test public void testDecodeCompressed() throws Exception {

        for (int i = 0; i < 10; i++) {
        	
        	String name = "secp521r1";
        	
        	AlgorithmNameResolver.addMap(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, "1.3.132.0.35", "EC");
        	
        	ECPublicKey key = createECPublicKey(name);

            // some additional encoding tricks
            byte[] compressedBC = getCompressed(key,name);
 
       		PublicKey publicKey = ECKeyEncoder.fromEncoded(compressedBC, "1.3.132.0.35", provider);	
       				
    		compareKeys((ECPublicKey) publicKey, key);
        
        }
    }
	
	@Test public void testEncodeUnCompressed() throws Exception {

        for (int i = 0; i < 10; i++) {
        	
        	String name = "secp521r1";
        	
        	AlgorithmNameResolver.addMap(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, "1.3.132.0.35", "EC");
        	
        	ECPublicKey key = createECPublicKey(name);

            byte[] uncompressedBC = getUnCompressed(key,name);
            //String uncompressedHex = Hex.toHexString(uncompressedBC);
   		  		
       		byte[] publicKeyUnComp = ECKeyEncoder.getEncoded(key, ECKeyEncoder.ENCODING_X962_UNCOMPESSED);		
       		
       		if (!Arrays.equals(uncompressedBC, publicKeyUnComp)) {
       			assert(Arrays.equals(uncompressedBC, publicKeyUnComp));
       		}
       		
            assert(Arrays.equals(uncompressedBC, publicKeyUnComp));
        
        }
    }
	
	@Test public void testDecodeUnCompressed() throws Exception {

        for (int i = 0; i < 10; i++) {
        	
        	String name = "secp521r1";
        	
        	AlgorithmNameResolver.addMap(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, "1.3.132.0.35", "EC");
        	
        	ECPublicKey key = createECPublicKey(name);

            // some additional encoding tricks
            byte[] compressedBC = getUnCompressed(key,name);
            //String compressedHex = Hex.toHexString(compressedBC);
 
       		PublicKey publicKey = ECKeyEncoder.fromEncoded(compressedBC, "1.3.132.0.35", provider);	
       				
    		compareKeys((ECPublicKey) publicKey, key);
        
        }
    }
	
	@Test public void testEncodeX509() throws Exception {

        for (int i = 0; i < 10; i++) {
        	
        	String name = "secp521r1";
        	
        	AlgorithmNameResolver.addMap(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, "1.3.132.0.35", "EC");
        	
        	ECPublicKey key = createECPublicKey(name);

    		byte[] publicKeyBcX509 = key.getEncoded(); 
    		  		
     		byte[] publicKeyBytes = ECKeyEncoder.getEncoded(key, ECKeyEncoder.ENCODING_X509);		
            assert(Arrays.equals(publicKeyBcX509, publicKeyBytes));
        
        }
    }
	
	@Test public void testDecodeX509() throws Exception {

        for (int i = 0; i < 10; i++) {
        	
        	String name = "secp521r1";
        	
        	AlgorithmNameResolver.addMap(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, "1.3.132.0.35", "EC");
        	
        	ECPublicKey key = createECPublicKey(name);

            // some additional encoding tricks
            byte[] compressedBC = key.getEncoded();
            //String compressedHex = Hex.toHexString(compressedBC);
 
       		PublicKey publicKey = ECKeyEncoder.fromEncoded(compressedBC, "1.3.132.0.35", provider);	
       				
    		compareKeys((ECPublicKey) publicKey, key);
    		        
        }
    }
	
	
	

	private ECPublicKey createECPublicKey(String name) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
		ECKeyPairGenerator gen = new ECKeyPairGenerator();
		SecureRandom secureRandom = new SecureRandom();
		X9ECParameters secnamecurves = SECNamedCurves.getByName(name);
		ECDomainParameters ecParams = new ECDomainParameters(secnamecurves.getCurve(), secnamecurves.getG(),
				secnamecurves.getN(), secnamecurves.getH());
		ECKeyGenerationParameters keyGenParam = new ECKeyGenerationParameters(ecParams, secureRandom);
		gen.init(keyGenParam);
		  		
		
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "BC");
        ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp521r1");
        kpg.initialize(ecGenParameterSpec);
        ECPublicKey key = (ECPublicKey) kpg.generateKeyPair().getPublic();
        return key;
	}
	
	private byte[] getCompressed(ECPublicKey key, String name) {
		
        byte[] x = key.getW().getAffineX().toByteArray();
        byte[] y = key.getW().getAffineY().toByteArray();

        // assumes that x and y are (unsigned) big endian encoded
        BigInteger xbi = new BigInteger(1, x);
        BigInteger ybi = new BigInteger(1, y);
        X9ECParameters x9 = ECNamedCurveTable.getByName(name);
        ECCurve curve = x9.getCurve();
        ECPoint point = curve.createPoint(xbi, ybi);

        return point.getEncoded(true);
	}
	
	private byte[] getUnCompressed(ECPublicKey key, String name) {
		
        byte[] x = key.getW().getAffineX().toByteArray();
        byte[] y = key.getW().getAffineY().toByteArray();

        // assumes that x and y are (unsigned) big endian encoded
        BigInteger xbi = new BigInteger(1, x);
        BigInteger ybi = new BigInteger(1, y);
        X9ECParameters x9 = ECNamedCurveTable.getByName(name);
        ECCurve curve = x9.getCurve();
        ECPoint point = curve.createPoint(xbi, ybi);
         
        return point.getEncoded(false);

	}
	
	private void compareKeys(ECPublicKey key1, ECPublicKey key2) {
		
		assert(key1.getW().getAffineX().equals(key2.getW().getAffineX()) );	
		assert(key1.getW().getAffineY().equals(key2.getW().getAffineY()) );
		
	}

}
