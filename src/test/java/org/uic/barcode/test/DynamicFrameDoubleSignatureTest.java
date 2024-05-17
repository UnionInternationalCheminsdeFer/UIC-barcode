package org.uic.barcode.test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.zip.DataFormatException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.Encoder;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.dynamicFrame.api.IData;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.test.utils.Level2TestDataFactory;
import org.uic.barcode.test.utils.SimpleUICTestTicket;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.utils.SecurityUtils;

public class DynamicFrameDoubleSignatureTest {
	
	public String signatureAlgorithmOID = null;
	public String elipticCurve = null;
	public String keyPairAlgorithmOID = null;
	
	public KeyPair keyPairLevel1 = null;
	public KeyPair keyPairLevel2 = null;
	
	public IUicRailTicket testFCBticket = null;
	
	public Provider provider = null;
	
	
	@Before public void initialize() {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		signatureAlgorithmOID = Constants.ECDSA_SHA256;
		keyPairAlgorithmOID = Constants.KG_EC_256;
		elipticCurve = "secp256k1";
		
	    testFCBticket = SimpleUICTestTicket.getUicTestTicket();
		
		provider = new BouncyCastleProvider();
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
	
	

	@Test public void testDynamicHeaderBarcodeDecoding() {
		
		IUicRailTicket ticket = testFCBticket;

		Encoder enc = null;

		try {
			enc = new Encoder(ticket, null, Encoder.UIC_BARCODE_TYPE_DOSIPAS, 1, 13);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		
		assert(enc != null);
		
		try {
			enc.setLevel1Algs(signatureAlgorithmOID, keyPairAlgorithmOID);
			enc.setLevel2Algs(signatureAlgorithmOID, keyPairAlgorithmOID,keyPairLevel2.getPublic());
			enc.signLevel1("1080", keyPairLevel1.getPrivate(), signatureAlgorithmOID, "1",provider);
		} catch (Exception e) {
			assert(false);
		}
		
		assert(enc != null);
		
		
		IData level2Data = Level2TestDataFactory.getLevel2SimpleTestData();
		try {
			enc.setLevel2Data(level2Data);
			enc.signLevel2(keyPairLevel2.getPrivate(),provider);
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
			signatureCheck = dec.validateLevel1(keyPairLevel1.getPublic(), null,provider);
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IllegalArgumentException
				| UnsupportedOperationException | IOException | EncodingFormatException e) {
			assert(false);
		}
        assert(signatureCheck == Constants.LEVEL1_VALIDATION_OK);

        signatureCheck = 0;
		try {
			signatureCheck = dec.validateLevel2(provider);
		} catch (Exception e) {
			assert(false);
		}
        assert(signatureCheck == Constants.LEVEL2_VALIDATION_OK);              
        
        IData level2DataDec = dec.getLevel2Data();
        
        assert(level2Data.getFormat().equals(level2DataDec.getFormat()));
        assert(Arrays.equals(level2Data.getData(),level2DataDec.getData()));        
        
        SimpleUICTestTicket.compare(ticket, dec.getUicTicket());     
        
	}	
	
		
	public KeyPair generateECKeys(String keyAlgorithmOid, String curve)  throws Exception{
		
		//ECNamedCurveGenParameterSpec namedParamSpec = new ECNamedCurveGenParameterSpec(elipticCurve);
		
	    ECGenParameterSpec namedParamSpec = new ECGenParameterSpec(elipticCurve);
	    KeyPairGenerator ecKPGen = KeyPairGenerator.getInstance("EC", "BC");
	    ecKPGen.initialize(namedParamSpec, new SecureRandom());
	    KeyPair keyPair = ecKPGen.generateKeyPair();
	    KeyPair kp = new KeyPair(SecurityUtils.convert(keyPair.getPublic(), provider),SecurityUtils.convert(keyPair.getPrivate(), provider));
	    return kp;
    }


}
