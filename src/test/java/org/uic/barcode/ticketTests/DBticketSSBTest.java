package org.uic.barcode.ticketTests;

import org.junit.Test;

// import java.io.ByteArrayInputStream;
// import java.security.KeyFactory;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
// import java.security.cert.CertificateFactory;
// import java.security.cert.X509Certificate;
// import java.security.spec.X509EncodedKeySpec;
// import java.util.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.Before;
import org.uic.barcode.Decoder;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicFrame.Constants;


public class DBticketSSBTest {
    String ticketHex = "310e044040408986106525548d55e14d250c0281580f4260a387526a00013c3a5" +
    "374ffdf759c8000000000000000000000000000000000000000302c02147107d4e8e958f082734a3a411" +
    "641101729f48abd021451d9646e819f4cd939bd60df27a719a3ff4e973900000000000000000000";

    String certificate = "";

    String algorithmOID = Constants.DSA_SHA1;

    Provider provider = null;

    PublicKey publicKey = null;
  
    @Before
    public void prepare() throws Exception {
        provider = new BouncyCastleProvider();
		Security.addProvider(provider);
        
        // ByteArrayInputStream in = new ByteArrayInputStream(Base64.getDecoder().decode(certificate));	

    	// CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
    	
    	// X509Certificate cert = (X509Certificate)certFactory.generateCertificate(in);
    	
    	// publicKey = cert.getPublicKey();		
    }

    @Test
    public void testDecoder() throws Exception {
        Decoder decoder = new Decoder(UperEncoder.bytesFromHexString(ticketHex));
        Assert.assertNotNull(decoder.getSsbFrame());
        byte[] signatureR = UperEncoder.bytesFromHexString("7107d4e8e958f082734a3a411641101729f48abd");
        byte[] signatureS = UperEncoder.bytesFromHexString("51d9646e819f4cd939bd60df27a719a3ff4e9739");
        Assert.assertArrayEquals(signatureR, decoder.getSsbFrame().getSignaturePart1());
        Assert.assertArrayEquals(signatureS, decoder.getSsbFrame().getSignaturePart2());
        // Assert.assertTrue(decoder.getSsbFrame().verifyByAlgorithmOid(publicKey, algorithmOID, provider));
    }
}
