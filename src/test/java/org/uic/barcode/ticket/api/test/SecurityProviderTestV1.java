package org.uic.barcode.ticket.api.test;

import java.io.ByteArrayInputStream;
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
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
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
 * The Class SecurityProviderTest.
 */
public class SecurityProviderTestV1 {
	
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

		LoggerFactory.setActivateConsoleLog(true);
		
		algorithmOID = Constants.DSA_SHA224;
		keySize = 2048;
	    testFCBticket = SimpleUICTestTicket.getUicTestTicket();
		testLayout = SimpleTestTicketLayout.getSimpleTestTicketLayout();		
		testFCBticket.getIssuerDetails().setSecurityProvider("1080");
		testFCBticket.getIssuerDetails().setIssuer("4711");

		
		Security.addProvider(new BouncyCastleProvider());

		try {
			keyPair  = generateDSAKeys(keySize);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

        assert(keyPair != null);
               
	}
	
	
	
	/**
	 * Test security provider encoding decoding.
	 */
	@Test public void testSecurityProviderDecoding() {
		
		
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
        
        
        assert(dec.getUicTicket().getIssuerDetails().getSecurityProvider().equals("1080"));
        assert(dec.getUicTicket().getIssuerDetails().getIssuer().equals("4711"));
        assert(dec.getStaticFrame().getHeaderRecord().getIssuer().equals("1080"));

        
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
