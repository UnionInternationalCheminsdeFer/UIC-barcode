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
import org.uic.barcode.ssbFrame.SsbFrame;
import org.uic.barcode.test.utils.SsbTicketFactory;
import org.uic.barcode.ticket.EncodingFormatException;

/**
 * The Class StaticFrameBarcodeTest.
 */
public class SsbFrameBarcodeTestNonUic {
	
	/** The algorithm OID. */
	public String algorithmOID = Constants.DSA_SHA224;
	
	public int keySize = 2048;
	
	/** The key pair. */
	public KeyPair keyPair = null;
	
	
	public SsbFrame ssbFrame = null;
	
	public Provider provider = null;

	
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
		keySize = 1024;
		
	    provider = new BouncyCastleProvider();
		Security.addProvider(provider);

		try {
			keyPair  = generateDSAKeys(keySize);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

        assert(keyPair != null);
        
	}
	
	



	/**
	 * Test ssb pass encoding.
	 */
	@Test public void testSsbEncoding() {

				
		Encoder enc = null;

		try {
			enc = new Encoder(null, null, Encoder.UIC_BARCODE_TYPE_SSB, 0, 0);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		
		enc.setSsbFrame(SsbTicketFactory.getSsbNonUic());
		
		assert(enc != null);
		
		try {
			enc.signLevel1("1080", keyPair.getPrivate(), algorithmOID, "1",provider);
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
        
        assert(encoded.length == 114);

	}
	
	/**
	 * Test dynamic header barcode decoding.
	 */
	@Test public void testSsbDecoding() {
		
		

					
		Encoder enc = null;

		try {
			enc = new Encoder(null, null, Encoder.UIC_BARCODE_TYPE_SSB, 1, 0);
		} catch (IOException | EncodingFormatException e1) {
			assert(false);
		}
		
		enc.setSsbFrame( SsbTicketFactory.getSsbNonUic());
		
		assert(enc != null);
		
		try {
			enc.signLevel1("4711", keyPair.getPrivate(), algorithmOID, "1",provider);
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
			signatureCheck = dec.validateLevel1(keyPair.getPublic(),algorithmOID,provider);
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IllegalArgumentException
				| UnsupportedOperationException | IOException | EncodingFormatException e) {
			assert(false);
		}
		
        assert(signatureCheck == Constants.LEVEL1_VALIDATION_OK);
        
        assert(dec.getSsbFrame() != null);

        assert(dec.getSsbFrame().getHeader() != null);
        
        SsbFrame ref = SsbTicketFactory.getSsbNonUic();
        
        assert(dec.getSsbFrame().getHeader().getKeyId() == 1);
        assert(dec.getSsbFrame().getHeader().getIssuer() == 4711);
        assert(dec.getSsbFrame().getHeader().getTicketType().equals(ref.getHeader().getTicketType()));
        assert(dec.getSsbFrame().getHeader().getVersion() == 1);
                
        assert(dec.getSsbFrame().getNonUicData() != null);
        
        assert(dec.getSsbFrame().getNonUicData().getOpenData() != null);
        
        

        for (int i = 0; i < ref.getNonUicData().getOpenData().length; i++) {
        	assert (dec.getSsbFrame().getNonUicData().getOpenData()[i] == (ref.getNonUicData().getOpenData())[i] );
        }

        
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
