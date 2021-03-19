package org.uic.barcode;

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
import java.util.zip.DataFormatException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.Encoder;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;
import org.uic.barcode.utils.SimpleTestTicketLayout;
import org.uic.barcode.utils.SimpleUICTestTicket;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

/**
 * The Class StaticFrameBarcodeTest.
 */
public class StaticFrameBarcodeTestFCB2 {
	
	/** The algorithm OID. */
	public String algorithmOID = Constants.DSA_SHA224;
	
	public int keySize = 2048;
	
	/** The key pair. */
	public KeyPair keyPair = null;
	
	
	public IUicRailTicket testFCBticket = null;
	
	public TicketLayout testLayout = null;
	
	
	/**
	 * Initialize.
	 * 
	 *  set the signature algorithm
	 *  generate a key pair
	 *  set the test content
	 *  for ticket and layout
	 */
	@Before public void initialize() {

		algorithmOID = Constants.DSA_SHA224;
		keySize = 2048;
	    testFCBticket = SimpleUICTestTicket.getUicTestTicket();
		testLayout = SimpleTestTicketLayout.getSimpleTestTicketLayout();		
		
		Security.addProvider(new BouncyCastleProvider());

		try {
			keyPair  = generateDSAKeys(keySize);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

        assert(keyPair != null);
        
	}
	
	
	/**
	 * Test dynamic header barcode encoding.
	 */
	@Test public void testStaticHeaderBarcodeEncoding() {
		
		IUicRailTicket ticket = testFCBticket;
		
		TicketLayout layout = testLayout;
				
		Encoder enc = null;

		try {
			enc = new Encoder(ticket, layout, Encoder.UIC_BARCODE_TYPE_CLASSIC, 2, 2);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		
		enc.setStaticHeaderParams("123456789012", "de");
		
		assert(enc != null);
		
		try {
			enc.signLevel1("1080", keyPair.getPrivate(), algorithmOID, "1");
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
	
	/**
	 * Test dynamic header barcode decoding.
	 */
	@Test public void testStaticHeaderBarcodeDecoding() {
		
		
		IUicRailTicket ticket = testFCBticket;
		
		TicketLayout layout = testLayout;
		
					
		Encoder enc = null;

		try {
			enc = new Encoder(ticket, layout, Encoder.UIC_BARCODE_TYPE_CLASSIC, 2, 2);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		
		enc.setStaticHeaderParams("123456789012", "de");
		
		assert(enc != null);
		
		try {
			enc.signLevel1("1080", keyPair.getPrivate(), algorithmOID, "1");
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
			signatureCheck = dec.validateLevel1(keyPair.getPublic(),algorithmOID);
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
