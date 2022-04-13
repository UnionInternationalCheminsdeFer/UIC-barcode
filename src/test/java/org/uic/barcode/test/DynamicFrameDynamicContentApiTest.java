package org.uic.barcode.test;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.DataFormatException;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.Encoder;
import org.uic.barcode.dynamicContent.api.IUicDynamicContent;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.test.utils.DynamicTestContent;
import org.uic.barcode.test.utils.SimpleUICTestTicket;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public class DynamicFrameDynamicContentApiTest {
	
	public String signatureAlgorithmOID = null;
	public String elipticCurve = null;
	public String keyPairAlgorithmOID = null;
	
	public KeyPair keyPairLevel1 = null;
	public KeyPair keyPairLevel2 = null;
	
	public byte[] passIdHash = "PassId".getBytes();
	public byte[] phoneIdHash = "myPhone".getBytes();
	

	public IUicRailTicket testFCBticket = null;
	
	
	@Before public void initialize() {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		signatureAlgorithmOID = Constants.ECDSA_SHA256;
		keyPairAlgorithmOID = Constants.KG_EC_256;
		elipticCurve = "secp256r1";
		
	    testFCBticket = SimpleUICTestTicket.getUicTestTicket();
		
		Security.addProvider(new BouncyCastleProvider());

		try {
			keyPairLevel1  = generateECKeys(keyPairAlgorithmOID, elipticCurve);
			keyPairLevel2  = generateECKeys(keyPairAlgorithmOID, elipticCurve);
		} catch (Exception e) {
			assert(false);
		}

        assert(keyPairLevel1 != null);
        
        assert(keyPairLevel2 != null);
        
	}
	
	
	@Test public void testDynamicContentEncoding() {
		
		IUicRailTicket ticket = testFCBticket;

		Encoder enc = null;

		try {
			enc = new Encoder(ticket, null, Encoder.UIC_BARCODE_TYPE_DOSIPAS, 1, 13);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		
		assert(enc != null);
		
		enc.setLevel2Algs(signatureAlgorithmOID, keyPairAlgorithmOID, keyPairLevel2.getPublic());
		
		try {
			enc.signLevel1("1080", keyPairLevel1.getPrivate(), signatureAlgorithmOID, "1");
		} catch (Exception e) {
			assert(false);
		}
			
		try {
			enc.setDynamicData(DynamicTestContent.createDynamicTestContent());			
			enc.signLevel2(keyPairLevel2.getPrivate());
			
		} catch (Exception e) {
			assert(false);
		}
		
			
        byte[] encoded = null;
		try {
			encoded = enc.encode();
		} catch (Exception e) {
			assert(false);
		}
        
        assert(encoded != null);
		

		
	}
	
	@Test public void testDynamicContentDecoding() {
		
		IUicRailTicket ticket = testFCBticket;

		Encoder enc = null;

		try {
			enc = new Encoder(ticket, null, Encoder.UIC_BARCODE_TYPE_DOSIPAS, 1, 13);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		
		assert(enc != null);
		
		enc.setLevel2Algs(signatureAlgorithmOID, keyPairAlgorithmOID, keyPairLevel2.getPublic());
		
		try {
			enc.signLevel1("1080", keyPairLevel1.getPrivate(), signatureAlgorithmOID, "1");
		} catch (Exception e) {
			assert(false);
		}
			
		try {
			enc.setDynamicData(DynamicTestContent.createDynamicTestContent());			
			enc.signLevel2(keyPairLevel2.getPrivate());
		} catch (Exception e) {
			assert(false);
		}
		
			
        byte[] encoded = null;
		try {
			encoded = enc.encode();
		} catch (Exception e) {
			assert(false);
		}
        
        assert(encoded != null);
		
        Decoder dec = null;
		try {
			dec = new Decoder(encoded);
		} catch (IOException e) {
			assert(false);
		} catch (EncodingFormatException e) {
			assert(false);
		} catch (DataFormatException e) {
			assert(false);
		}
		assert(dec != null);
        
        int signatureCheck = 0;
		try {
			signatureCheck = dec.validateLevel1(keyPairLevel1.getPublic(),null);
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IllegalArgumentException
				| UnsupportedOperationException | IOException | EncodingFormatException e) {
			assert(false);
		}
		
        assert(signatureCheck == Constants.LEVEL1_VALIDATION_OK);
        
        SimpleUICTestTicket.compare(ticket, dec.getUicTicket());     
        
        int level2check = 0;
        try {
        	level2check = dec.validateLevel2();
        } catch (Exception e) {
        	assert(false);
        }
         
        assert(level2check == Constants.LEVEL2_VALIDATION_OK);
        
        IUicDynamicContent dynamicData = dec.getDynamicContent();
        
        assert(dynamicData.getChallengeString().equals("CHALLENGE"));
        
        assert(dynamicData.getAppId().equals("MyApp"));
               
        assert(Arrays.equals(dynamicData.getPassIdHash(),passIdHash));
        
        assert(Arrays.equals(dynamicData.getPhoneIdHash(),phoneIdHash));
        
		assert(dynamicData.getGeoCoordinate().getLatitude() == 123456L);
		assert(dynamicData.getGeoCoordinate().getLongitude() == 23456L);
        
        Date timeStamp = dynamicData.getTimeStamp();
        ZonedDateTime retrievedTimeStamp = timeStamp.toInstant().atZone(ZoneId.of("UTC"));
        ZonedDateTime originalTimeStamp = ZonedDateTime.now(ZoneId.of("UTC"));
        long diff = ChronoUnit.SECONDS.between(originalTimeStamp, retrievedTimeStamp);
  
        assert(diff > -50 && diff < 50);
	}	
	
	public KeyPair generateECDSAKeys(String keyAlgorithmName, String paramName)  throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException{
		ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec(paramName);
	    KeyPairGenerator g = KeyPairGenerator.getInstance(keyAlgorithmName, "BC");
	    g.initialize(ecSpec, new SecureRandom());
	    return g.generateKeyPair();	    
    }
		
	public KeyPair generateECKeys(String keyAlgorithmOid, String curve)  throws Exception{
		
		String keyAlgorithmName = "ECDSA";
		ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec(curve);
	    KeyPairGenerator g = KeyPairGenerator.getInstance(keyAlgorithmName, "BC");
	    g.initialize(ecSpec, new SecureRandom());
	    return g.generateKeyPair();	    
    }


}
