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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;
import java.util.zip.DataFormatException;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.Encoder;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.test.utils.DynamicTestContent;
import org.uic.barcode.test.utils.SimpleUICTestTicket;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public class DynamicFrameV2SignatureInsert2Test {
	
	public String signatureAlgorithmOID = null;
	public String elipticCurve = null;
	public String keyPairAlgorithmOID = null;
	
	public KeyPair keyPairLevel1 = null;
	public KeyPair keyPairLevel2 = null;
	
	public byte[] passIdHash = "PassId".getBytes();
	public byte[] phoneIdHash = "myPhone".getBytes();
	
	public IUicRailTicket testFCBticket = null;
	
	public Provider provider = null;
	
	ZonedDateTime originalTimeStamp = ZonedDateTime.now(ZoneId.of("UTC"));
	
	@Before public void initialize() {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		signatureAlgorithmOID = Constants.ECDSA_SHA256;
		keyPairAlgorithmOID = Constants.KG_EC_256;
		elipticCurve = "secp256r1";
		
	    testFCBticket = SimpleUICTestTicket.getUicTestTicket();
		
	    provider = new BouncyCastleProvider();
		Security.addProvider(provider);

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

		//---------------------------------------------------------------------------
		//create barcode data
		IUicRailTicket ticket = testFCBticket;

		Encoder enc = null;
		try {
			enc = new Encoder(ticket, null, Encoder.UIC_BARCODE_TYPE_DOSIPAS, 2, 3);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		assert(enc != null);
		
		//complete level 1 data 
		enc.setLevel1Algs(signatureAlgorithmOID, keyPairAlgorithmOID);
		enc.setLevel2Algs(signatureAlgorithmOID, keyPairAlgorithmOID,keyPairLevel2.getPublic());
		enc.getDynamicFrame().getLevel2Data().getLevel1Data().setEndOfBarcodeValidity(getUtcDate("2021.03.04-12:30"));
		enc.getDynamicFrame().getLevel2Data().getLevel1Data().setValidityDuration(100L);
		
		
		//sign level 1 data
		try {
			enc.signLevel1("1080", keyPairLevel1.getPrivate(), signatureAlgorithmOID, "1",provider);
		} catch (Exception e) {
			assert(false);
		}
	
		// encode
        byte[] encoded = null;
		try {
			encoded = enc.encode();
		} catch (Exception e) {
			assert(false);
		}
        assert(encoded != null);
     
        
		
        //----------------------------------------------------------------------------------------------
        //decode and check level 1
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
        
  		String keyId = null;
		try {
			keyId = dec.getLevel1KeyId();
		} catch (EncodingFormatException e3) {
			assert(false);
		}
 		assert(keyId != null);
 		

 		
        int signatureCheck = 0;
 		try {
 			signatureCheck = dec.validateLevel1(keyPairLevel1.getPublic(),null,provider);
 		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IllegalArgumentException
 				| UnsupportedOperationException | IOException | EncodingFormatException e) {
 			assert(false);
 		}
 		
         assert(signatureCheck == Constants.LEVEL1_VALIDATION_OK);
         
         
         
         
         
        //--------------------------------------------------------------------------------------------------
        // get encoder with dynamic frame to continue
		try {
			enc = new Encoder(encoded, 2);
		} catch (Exception e1) {
			assert(false);
		}
	
		
		//set dynamic content
		try {
			enc.setDynamicData(DynamicTestContent.createDynamicTestContent());
		} catch (EncodingFormatException e1) {
			assert(false);
		}
		//-----------
		// sign level 2
		try {			
			enc.signLevel2(keyPairLevel2.getPrivate(),provider);
		} catch (Exception e) {
			assert(false);
		}
		
		//------------------------
		//encode complete bar code
        encoded = null;
		try {
			encoded = enc.encode();
		} catch (Exception e) {
			assert(false);
		}
        assert(encoded != null);
        
        //----------------------------------------------------------------------------------------------------
        //decode full bar code        
        
        dec = null;
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
        
        //---------------------------------------------------------------------------------------------------
        //check level 1 signature   
		
        signatureCheck = 0;
		try {
			signatureCheck = dec.validateLevel1(keyPairLevel1.getPublic(),null,provider);
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IllegalArgumentException
				| UnsupportedOperationException | IOException | EncodingFormatException e) {
			assert(false);
		}
		
        //--------------------------------------------------------------------------------------------------------
        //check level 2 signature   
        
        signatureCheck = 0;
 		try {
 			signatureCheck = dec.validateLevel2(provider);
 		} catch (Exception e) {
 			assert(false);
 		}
        assert(signatureCheck == Constants.LEVEL2_VALIDATION_OK);              

        
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
	
	public Date getUtcDate(String s) {
		
		TimeZone local = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		Date date = null;
		try {
			date = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).parse(s);
		} catch (ParseException e1) {
			assert(false);
		}
		TimeZone.setDefault(local);
		
		return date;

	}
	
	public String formatUTC(Date date) {
	    
		TimeZone local = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		String dateS = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).format(date);
	    TimeZone.setDefault(local);        
	    return dateS;
	    
	}


}
