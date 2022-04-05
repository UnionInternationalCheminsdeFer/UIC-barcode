package org.uic.barcode.ticketTestDB;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.TimeZone;
import java.util.zip.DataFormatException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.Encoder;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.staticFrame.StaticFrame;
import org.uic.barcode.test.utils.TestUtils;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public class EncodeSparpreisTicketDBTest {
	
	/** The algorithm OID. */
	public String algorithmOID = Constants.DSA_SHA1;
	
	public int keySize = 1024;
	
	/** The key pair. */
	public KeyPair keyPair = null;
	
	public String securityProvider = null;
	
	/** the test ticket **/
	public IUicRailTicket ticket = null;
	
    TimeZone defaulttimeZone = null;
	
	/**
	 * Prepare tickets.
	 * @throws DataFormatException 
	 * @throws EncodingFormatException 
	 * @throws IOException 
	 */
	@Before public void prepare() throws IOException, EncodingFormatException, DataFormatException {

		LoggerFactory.setActivateConsoleLog(true);
		
		defaulttimeZone = TimeZone.getDefault();
    	//decode in local CET time zone
		TimeZone.setDefault(TimeZone.getTimeZone("CET"));
		
		algorithmOID = Constants.DSA_SHA1;
		keySize = 1024;
		
		Security.addProvider(new BouncyCastleProvider());

		try {
			keyPair  = TestUtils.generateDSAKeys(keySize);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

        assert(keyPair != null);
        
    	//get original ticket
        byte[] content = UperEncoder.bytesFromHexString(getEncodingV2Hex());
        // decode to get the ticket
        Decoder decoder = new Decoder(content);
        ticket = decoder.getUicTicket();
        StaticFrame frame = decoder.getStaticFrame();
        securityProvider = frame.getSecurityProvider();
	}
	
	
	/**
	 * clean up
	 */
	@After public void resetTimeZone() {
		TimeZone.setDefault(defaulttimeZone);
	}
	
    @Test
    public void testDecoder() throws Exception {

        Encoder encoder = new Encoder(ticket, null, Encoder.UIC_BARCODE_TYPE_CLASSIC, 1, 2);
        encoder.signLevel1(securityProvider, keyPair.getPrivate(), algorithmOID, "1");
        
        byte[] encoded =  encoder.encode();
        
        assert(encoded != null);
        
        //TODO check ticket in detail
        
    }
    
	private static String getEncodingV2Hex() {
		
		 return "2355543032313038303030303032782e" + 
				"2fe184a1d85e89e9338b298ec61aeba2" + 
				"48ce722056ca940a967c8a1d39126e2c" + 
				"628c4fcea91ba35216a0a350f894de5e" + 
				"bd7b8909920fde947feede0e20c43031" + 
			    "3939789c01bc0043ff555f464c455831" + 
				"333031383862b20086e10dc125ea2815" + 
				"110881051c844464d985668e23a00a80" + 
				"000e96c2e4e6e8cadc08aed2d8d90104" + 
				"44d7be0100221ce610ea559b64364c38" + 
				"a82361d1cb5e1e5d32a3d0979bd099c8" + 
				"426b0b7373432b4b6852932baba3634b" + 
				"733b2b715ab34b09d101e18981c181f1" + 
				"424221521291521292a17a3a920a1152" + 
				"5a095282314952b20a49529952826278" + 
				"083001a4c38ae5bb303ace7003800700" + 
				"14b00240400f53757065722053706172" + 
				"7072656973c41e4a03";
	}
	


}