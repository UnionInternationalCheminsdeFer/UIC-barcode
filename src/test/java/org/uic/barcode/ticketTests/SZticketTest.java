package org.uic.barcode.ticketTests;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.TimeZone;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ssbFrame.SsbClass;
import org.uic.barcode.ssbFrame.SsbFrame;
import org.uic.barcode.ssbFrame.SsbStationCodeTable;
import org.uic.barcode.ssbFrame.SsbTicketType;

public class SZticketTest {
    	
    TimeZone defaulttimeZone = null;
    
    String ticketBase64 = "MSbEQEACWRUQQQRTUTAAAAJbAAAAg8md4D3SgYAAAAAAAAAAAAAAAAAAAAAA"
            + "AAAAAAAAAAAAAAAAAIYJ9h44ZY0Kh/z3y89kgvrmVBIQAodNRwl3wlNU/1q6qcoOOjir/NX8"
            + "tZlBGPMrZNQAKdG5WoJc";

	String publicKeyBase64 = "MIIDQzCCAjUGByqGSM44BAEwggIoAoIBAQDdevkGfuV5U5BmSaaC2ymhw"
            + "+SQQcax2yZRbRExZvaTeOr3NkJlqAgzbvpIAUx5U1rZ3J3ZkFWmkADWds8r1sko8vpqJQDpG"
            + "js0iXP1r7GYAlciPgGRffmfdn5eVCWgFeG381CLFZ4pUVC7SbwusVzcnGRt/V1wxNdRRxCXG"
            + "q1O1L63PiSRNW5RJv/JsVHaqZMbCEigh2NXYkCB0BgDFub+2NTAW7GnllX9F656zpP6gwV6K"
            + "AymUha5bH33c1rDuhmO25iNrWwW60Sxrl8rs93k2FQB4AzelCw/6MS9uHerdABdedzlUqN7w"
            + "UleJCgc25w3eoTPKnbEF4xdaeR3afvfAh0AiNWFRF2DOZZ+koG4K39Vr94q47YILo7LOeuPO"
            + "wKCAQBG0Pt3roTa9Aau2U7hZINGcSUI5hLbpMwtrXtAnDtWkQOqPO11vvXJhYHZQkM4wOmhR"
            + "uT4OxolKvWHjvkvlKoGx4gZMdASio8UuaCbtKo2588xQ4SY1+Cs2lhRRuhfYce5rv9DhOjgf"
            + "Yv9zxR7Skt6UAbndJtpmSo6mxBK/G2w6FIxzsWBPekaZ/nXWMHFNv/6SDtIrQM3W+DqCckj8"
            + "c7tG6zLHcMYh/OIfnc0mVN1EgxsovJz/XmN3LsInIZq8cxQNH9l/TsexLVJrQ3odfA5VmcoH"
            + "inIQV8K1Iak3NcclwOzvk5sup8cPKoMf4p1YFO5OcW3WR5HFB42VimkoyK4A4IBBgACggEBA"
            + "Kr1MuBndeKbZDHyZ4opf0a3dJd00lBgp5dH5SF7Um8LnqY2SGYd7IvXBOjP1fdFub4CLNPXn"
            + "265gQF7HkDBu0zd+Sy2glzCabOz7j1LJezaaEGGHDndBwTsGGrQ0JRcB2SR3cCFdmjmzEFlJ"
            + "xarriD7K7N5jSBT1mJCmNvkTk8dgtoBcIW6qxQe+Q72UFyME+6H/6Nnh+X2tv4CbVnmmTXT1"
            + "ktaZjf+RrFc5eT1nPVFDZcNaDwzUapf4fLqGXw46JmB0WM5+o5zTR7Q+1AWHEn0D4eqEWoui"
            + "wXbIxiV/JQEW55eWnz3oSClRLOFNL3zqEydGrr4RSh0AS4wE8EjNIY=";

            
    String algorithmOID = Constants.DSA_SHA224;
    
    int keySize = 1024;

    Provider provider = null;

    PublicKey publicKey = null;

    
	/**
	 * Prepare tickets.
	 */
	@Before public void prepare() throws Exception {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		defaulttimeZone = TimeZone.getDefault();
    	//decode in local CET time zone
		TimeZone.setDefault(TimeZone.getTimeZone("CET"));

        provider = new BouncyCastleProvider();
		Security.addProvider(provider);

        // decode public key
        byte[] publicKeyContent = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyContent);
        KeyFactory keyFactory = KeyFactory.getInstance("DSA", provider);
        publicKey = keyFactory.generatePublic(publicKeySpec);
    }
	
	
	/**
	 * clean up
	 */
	@After public void resetTimeZone() {
		TimeZone.setDefault(defaulttimeZone);
	}
	
    @Test
    public void testDecoder() throws Exception {
        byte[] content = Base64.getDecoder().decode(ticketBase64);
        
        Decoder decoder = new Decoder(content);
        SsbFrame frame = decoder.getSsbFrame();

        Assert.assertNotNull(frame);       
        Assert.assertNotNull(frame.getNonReservationData());
        Assert.assertNull(frame.getReservationData());
        Assert.assertNull(frame.getPassData());
        Assert.assertNull(frame.getGroupData());
        Assert.assertNull(frame.getNonUicData());
        
        Assert.assertNotNull(frame.getSignaturePart1());
        Assert.assertNotNull(frame.getSignaturePart2());        

        Assert.assertTrue(frame.verifyByAlgorithmOid(publicKey, algorithmOID, provider));

        Assert.assertEquals(frame.getHeader().getVersion(), 3);
        Assert.assertEquals(frame.getHeader().getIssuer(), 1179);
        Assert.assertEquals(frame.getHeader().getKeyId(), 1);
        Assert.assertEquals(frame.getHeader().getTicketType(), SsbTicketType.UIC_2_NRT);
        Assert.assertEquals(frame.getNonReservationData().getNumberOfAdults(), 1);
        Assert.assertEquals(frame.getNonReservationData().getNumberOfChildren(), 0);
        Assert.assertEquals(frame.getNonReservationData().isSpecimen(), false);
        Assert.assertEquals(frame.getNonReservationData().getClassCode(), SsbClass.Second);
        Assert.assertEquals(frame.getNonReservationData().getTicketNumber(), "6140001343");
        Assert.assertEquals(frame.getNonReservationData().getYear(), 2);
        Assert.assertEquals(frame.getNonReservationData().getDay(), 182);
        Assert.assertEquals(frame.getNonReservationData().getFirstDayOfValidity(), 0);
        Assert.assertEquals(frame.getNonReservationData().getLastDayOfValidity(), 0);
        Assert.assertEquals(frame.getNonReservationData().getStations().getCodeTable(), SsbStationCodeTable.NRT);
        Assert.assertEquals(frame.getNonReservationData().getStations().getDepartureStationCode(), "7943100");
        Assert.assertEquals(frame.getNonReservationData().getStations().getArrivalStationCode(), "8103171");
        Assert.assertEquals(frame.getNonReservationData().getInfoCode(), 0);
        Assert.assertEquals(frame.getNonReservationData().getText(), "");
     }
    
}
