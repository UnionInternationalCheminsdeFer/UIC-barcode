package org.uic.barcode.test;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.util.zip.DataFormatException;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.Encoder;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.dynamicFrame.api.IData;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.test.utils.SimpleUICTestTicket;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public class DynamicFrameV2FcbVersion3Test {
	
	public String signatureAlgorithmOID = null;
	public String elipticCurve = null;
	public String keyPairAlgorithmOID = null;
	
	public KeyPair keyPair = null;
	
	public IUicRailTicket testFCBticket = null;
	
	public Provider provider = null;
	
	
	@Before public void initialize() {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		signatureAlgorithmOID = Constants.ECDSA_SHA256;
		keyPairAlgorithmOID = Constants.KG_EC_256;
		elipticCurve = "secp256r1";
		
	    testFCBticket = SimpleUICTestTicket.getUicTestTicket();
		
	    provider = new BouncyCastleProvider();
		Security.addProvider(provider);

		try {
			keyPair  = generateECKeys(Constants.KG_EC, elipticCurve);
			//keyPair  = generateECDSAKeys("ECDSA", "B-571");
		} catch (Exception e) {
			assert(false);
		}

        assert(keyPair != null);
        
	}
	
	
	@Test public void testDynamicHeaderBarcodeEncodingFCB3() {
		
		IUicRailTicket ticket = testFCBticket;

		Encoder enc = null;

		try {
			enc = new Encoder(ticket, null, Encoder.UIC_BARCODE_TYPE_DOSIPAS, 2, 3);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		
		assert(enc != null);
		
		try {
			enc.signLevel1("1080", keyPair.getPrivate(), signatureAlgorithmOID, "1",provider);
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
	
	@Test public void testDynamicHeaderBarcodeDecodingFCB3() {
		
		IUicRailTicket ticket = testFCBticket;

		Encoder enc = null;

		try {
			enc = new Encoder(ticket, null, Encoder.UIC_BARCODE_TYPE_DOSIPAS, 2, 3);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		
		assert(enc != null);
		
		try {
			enc.signLevel1("1080", keyPair.getPrivate(), signatureAlgorithmOID, "1",provider);
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
			signatureCheck = dec.validateLevel1(keyPair.getPublic(),null,provider);
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IllegalArgumentException
				| UnsupportedOperationException | IOException | EncodingFormatException e) {
			assert(false);
		}
		
        assert(signatureCheck == Constants.LEVEL1_VALIDATION_OK);
        
        assert(dec.getDynamicFrame().getFormat().equals("U2"));
        
        for (IData data : dec.getDynamicFrame().getLevel2Data().getLevel1Data().getData()) {
        	assert(data.getFormat().equals("FCB3") );
        }
               
        SimpleUICTestTicket.compare(ticket, dec.getUicTicket());     
        
        
               
        
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
