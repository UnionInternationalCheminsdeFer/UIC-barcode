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

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.Encoder;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;
import org.uic.barcode.test.utils.SimpleTestTicketLayout;
import org.uic.barcode.test.utils.SimpleUICTestTicket;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

/**
 * The Class StaticFrameBarcodeTest.
 */
public class StaticFrameBarcodeSignatureAlgorithmDetectionTest {
	
	/** The algorithm OID. */
	public String algorithmOID = Constants.DSA_SHA224;
	
	public int keySize = 2048;
	
	/** The key pair. */
	public KeyPair keyPair = null;
	
	
	public IUicRailTicket testFCBticket = null;
	
	public TicketLayout testLayout = null;
	
	public Provider prov = null;
	
	/**
	 * Initialize.
	 * 
	 *  set the signature algorithm
	 *  generate a key pair
	 *  set the test content
	 *  for ticket and layout
	 */
	@Before public void initialize() {

		LoggerFactory.setActivateConsoleLog(true);
		
		prov = new BouncyCastleProvider();
		
		Security.addProvider(prov);
        
	}
	
	
	/**
	 * Test DSA signature algorithm detection DSA SHA1
	 */
	@Test public void testDsaSha1() {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		algorithmOID = Constants.DSA_SHA1;
		keySize = 512;
	    testFCBticket = SimpleUICTestTicket.getUicTestTicket();
		testLayout = SimpleTestTicketLayout.getSimpleTestTicketLayout();		


		try {
			keyPair  = generateDSAKeys(keySize);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

        assert(keyPair != null);
        
		
		IUicRailTicket ticket = testFCBticket;
		
		TicketLayout layout = testLayout;
				
		Encoder enc = null;

		try {
			enc = new Encoder(ticket, layout, Encoder.UIC_BARCODE_TYPE_CLASSIC, 2, 13);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		
		enc.setStaticHeaderParams("123456789012", "de");
		
		assert(enc != null);
		
		try {
			enc.signLevel1("1080", keyPair.getPrivate(), algorithmOID, "1", prov);
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
			signatureCheck = dec.validateLevel1(keyPair.getPublic(),null,prov);
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IllegalArgumentException
				| UnsupportedOperationException | IOException | EncodingFormatException e) {
			assert(false);
		}
		
        assert(signatureCheck == Constants.LEVEL1_VALIDATION_OK);
        
        SimpleUICTestTicket.compare(ticket, dec.getUicTicket());
        
        SimpleTestTicketLayout.compare(layout, dec.getLayout());

        String keyId = null;
        try {
			keyId = dec.getLevel1KeyId();
		} catch (EncodingFormatException e) {
			assert(false);
		}
        String securityProvider = null;
        try {
			securityProvider = dec.getLevel1SecurityProvider();
		} catch (EncodingFormatException e) {
			assert(false);
		}
        
        assert("1".equals(keyId));
        assert("1080".equals(securityProvider));
        
        
	}
	
	/**
	 * Test DSA signature algorithm detection DSA SHA224
	 * DSAwithSHA224 is outdated an most provides don't support it properly!

	@Test public void testDsaSha224() {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		algorithmOID = Constants.DSA_SHA224;
		keySize = 1024;
	    testFCBticket = SimpleUICTestTicket.getUicTestTicket();
		testLayout = SimpleTestTicketLayout.getSimpleTestTicketLayout();		


		try {
			keyPair  = generateDSAKeys(keySize);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

        assert(keyPair != null);
        
		
		IUicRailTicket ticket = testFCBticket;
		
		TicketLayout layout = testLayout;
				
		Encoder enc = null;

		try {
			enc = new Encoder(ticket, layout, Encoder.UIC_BARCODE_TYPE_CLASSIC, 2, 13);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		
		enc.setStaticHeaderParams("123456789012", "de");
		
		assert(enc != null);
		
		try {
			enc.signLevel1("1080", keyPair.getPrivate(), algorithmOID, "1", prov);
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
			signatureCheck = dec.validateLevel1(keyPair.getPublic());
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IllegalArgumentException
				| UnsupportedOperationException | IOException | EncodingFormatException e) {
			assert(false);
		}
		
        assert(signatureCheck == Constants.LEVEL1_VALIDATION_OK);
        
        SimpleUICTestTicket.compare(ticket, dec.getUicTicket());
        
        SimpleTestTicketLayout.compare(layout, dec.getLayout());


	}
		 */
	
	/**
	 * Test DSA signature algorithm detection DSA SHA256
	 */
	@Test public void testDsaSha256() {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		algorithmOID = Constants.DSA_SHA256;
		keySize = 2048;
	    testFCBticket = SimpleUICTestTicket.getUicTestTicket();
		testLayout = SimpleTestTicketLayout.getSimpleTestTicketLayout();		


		try {
			keyPair  = generateDSAKeys(keySize);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

        assert(keyPair != null);
        
		
		IUicRailTicket ticket = testFCBticket;
		
		TicketLayout layout = testLayout;
				
		Encoder enc = null;

		try {
			enc = new Encoder(ticket, layout, Encoder.UIC_BARCODE_TYPE_CLASSIC, 2, 13);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		
		enc.setStaticHeaderParams("123456789012", "de");
		
		assert(enc != null);
		
		try {
			enc.signLevel1("1080", keyPair.getPrivate(), algorithmOID, "1", prov);
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
			signatureCheck = dec.validateLevel1(keyPair.getPublic(),null,prov);
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IllegalArgumentException
				| UnsupportedOperationException | IOException | EncodingFormatException e) {
			assert(false);
		}
		
        assert(signatureCheck == Constants.LEVEL1_VALIDATION_OK);
        
        SimpleUICTestTicket.compare(ticket, dec.getUicTicket());
        
        SimpleTestTicketLayout.compare(layout, dec.getLayout());


	}

	/**
	 * Generate DSA keys.
	 *
	 * @return the key pair
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchProviderException the no such provider exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 */
	public KeyPair generateDSAKeys(int keySize)  throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException{
		    KeyPairGenerator g = KeyPairGenerator.getInstance("DSA", "BC");
		    g.initialize(keySize, new SecureRandom());
		    return g.generateKeyPair();	    
	}

}
