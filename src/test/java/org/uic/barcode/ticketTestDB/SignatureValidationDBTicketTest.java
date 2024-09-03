package org.uic.barcode.ticketTestDB;

import java.io.ByteArrayInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.TimeZone;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.logger.LoggerFactory;

public class SignatureValidationDBTicketTest {
	
    TimeZone defaulttimeZone = null;
    
    Provider provider= null;
	
	/**
	 * Prepare tickets.
	 */
	@Before public void prepare() {
		
		LoggerFactory.setActivateConsoleLog(true);

	    provider = new BouncyCastleProvider();
		Security.addProvider(provider);

		defaulttimeZone = TimeZone.getDefault();
    	//decode in local CET time zone
		TimeZone.setDefault(TimeZone.getTimeZone("CET"));
	}
	
	
	/**
	 * clean up
	 */
	@After public void resetTimeZone() {
		TimeZone.setDefault(defaulttimeZone);
	}
	
    @Test
    public void testDecoder() throws Exception {


        byte[] content = UperEncoder.bytesFromHexString(getEncodingV2Hex());

          // try to decode
        Decoder decoder = new Decoder(content);
        //TicketLayout layout = decoder.getLayout();
        //IUicRailTicket ticket = decoder.getUicTicket();
        
        String keyVersion = decoder.getStaticFrame().getSignatureKey();
        
        assert("00002".equals(keyVersion));
        
		String algorithmOID = Constants.DSA_SHA256;
		
        int result = decoder.validateLevel1(getPublicKey2(), algorithmOID,provider);
        
        assert(result == 0);
        

    }
    
    
    public static PublicKey getPublicKey2() throws NoSuchAlgorithmException, InvalidKeySpecException, CertificateException {
    	
    	ByteArrayInputStream in = new ByteArrayInputStream(Base64.getDecoder().decode(getDBKey2()));	

    	CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
    	
    	X509Certificate cert = (X509Certificate)certFactory.generateCertificate(in);
    	
    	return cert.getPublicKey();		
    	
    }

	public static String getDBKey2() {
	
		return "MIIFAzCCBKmgAwIBAgIJAL4b6YtdfC1HMAsGCWCGSAFlAwQDAjBkMQswCQYDVQQGEwJERTELMAkGA1UECAwCSEUxFzAVBgNVBAoMDkRCVmVydHJpZWJHbUJIMRswGQYDVQQLDBJNb2JpbGVUaWNrZXRpbmdPcHMxEjAQBgNVBAMMCVRob3JnZUxvaDAeFw0xOTA1MTMwODM3MzBaFw0yNDA1MTEwODM3MzBaMGQxCzAJBgNVBAYTAkRFMQswCQYDVQQIDAJIRTEXMBUGA1UECgwOREJWZXJ0cmllYkdtQkgxGzAZBgNVBAsMEk1vYmlsZVRpY2tldGluZ09wczESMBAGA1UEAwwJVGhvcmdlTG9oMIIDRzCCAjkGByqGSM44BAEwggIsAoIBAQDvBHnyGImsnwD+u7a+4y8Kds6pJvmicDx//g/SXkj366T81luFYw3qWU6fV2F/p81j2PGfKHGIhhS89CPtBtXdt1cntHhs2B6+08Hmtd5RGGvqQiUuun5WrSloxJVWPfZRIp5BVNYnkybi+J10TsAL4xf1Wy5uWIOa8pQsBAl1ARMSz0vtQ9vUARLzzJtkS1QpAy6XiNVF9LodFUgC17m76NxK7htHcyoPhEnwdkHXP0YCYAMoXZEdhBVHL4kuyAj/+S+d/Fr+k/jRRLUdevrpsTbVttOkhO/uDtiOs1Z2Ou8PHqZDUvV7p7QMM45KDMBEhjqqEaVfkqTxr4DU71jDAiEAxl+jg6oBinGD274AOiOgdpVEG+dPuEVc6Ckiyxgx3ycCggEANO//Pafo6cAEtMmR7EsLc1dq+H2Hf4cX0o5pU1wiA5bY5kibcnmwSZynphoxUPZXAsZdoXw0ugx9Zkj68A/RwVZAyg+tfApfaIZVKp9BIQVAGwyOOHaEJBsdUahshpkM2SvuMNCxmZScnq5rherQvebbvkf5bmLvK4ftrve03lhnu92LbF8F4XTV/vHLtDAvJGo/380EA6yQVwe1lIUNET5vU3GYSoOZNsDFIu7ijl/mt0m8sjduFPVK5ueE4XO+Hal9lc5hYpiQq4AwUqtRsA+A1HAR7h3tu+QsqMo8AhbuxGdY/bipGSWyRcSg1mvLDEctev0rpvN5fX8ZymiCIwOCAQYAAoIBAQCkDpFu1+QttJUDSMJPScErldgepOoTaVSWIEkc8UYAmVgxXr+hF4t5/MAHeh4+kO5VXUA2xYbTiV4aA6fUkDm+6LW1aIG0Z4hE+SX6C8Lt8u0hp1UzQhERCobl1kRgMktipKes5h3aLaQ2Spy7+t8wzb0jScWNirrgtVZGUajcyQCuaZb5QIaQdLCPm0q5qD3PTDKaLxI/eFuIHSvNoh5WYTw9bfXxN//UZ1I+KQn7JKRdnkTHBvPm7Ww40Yo5Kcc45cxyUU6WDtmUcahaFOdpmfVBhCkK3H0oFOkTEXUAEd3irW8d38yq1znv/I+W0sBNjbtRpc59g+aBZO4oX1kDo1MwUTAdBgNVHQ4EFgQUp/Ih719wqFM0rDWnrLE5rfXqGxEwHwYDVR0jBBgwFoAUp/Ih719wqFM0rDWnrLE5rfXqGxEwDwYDVR0TAQH/BAUwAwEB/zALBglghkgBZQMEAwIDRwAwRAIgWY1GPRhkC9r8QC7AD0/Meki49G7MTA8Z7PrSsLCUYLoCIA/Lsca8Bal5cWs7siFlTJKWefb77CNRjNLvWqKbVW28";
	}
    
    public static String getDBKey6() {
    	
    	return "MIIFAjCCBKigAwIBAgIJAMWX1K7tkYCOMAsGCWCGSAFlAwQDAjBkMQswCQYDVQQGEwJERTELMAkGA1UECAwCSEUxFzAVBgNVBAoMDkRCVmVydHJpZWJHbUJIMRswGQYDVQQLDBJNb2JpbGVUaWNrZXRpbmdPcHMxEjAQBgNVBAMMCVRob3JnZUxvaDAeFw0xOTA1MTMwODM3NDZaFw0zNDA1MDkwODM3NDZaMGQxCzAJBgNVBAYTAkRFMQswCQYDVQQIDAJIRTEXMBUGA1UECgwOREJWZXJ0cmllYkdtQkgxGzAZBgNVBAsMEk1vYmlsZVRpY2tldGluZ09wczESMBAGA1UEAwwJVGhvcmdlTG9oMIIDRjCCAjkGByqGSM44BAEwggIsAoIBAQCA13FkF+uphcQTeKnXOcr+j02+bfvaFyAdLu2rUunDHJAa+ZNBbvnCqOHlzcw+FSiE67AvoipudM4m2VFjFOH94i9XzwCBC7BlHxcM+VyZYZZ6D35Dy27A1trSRliJ/Tsuqj7hAlwUIuhUijHYmPGlPWBQ6s73uWqmCahlPu9Xp/Bq1YbZOaod8/TYRW45XHSDPDxCugQ93flN4eGwjcE9RHeIGSYXB8XvEuzDNdUScxf2VszBNTBIJBcgtWRWquSCt18Usn4wxSawM3vtPAVwIQ4tg25rUIl8nnGyKE7WhJEXnBogq0Y6WMtBo1hcoH7HyKJZFi3TWgT4112MSe+VAiEA16Qg9xFHhTTbEytYIG9B6R/6Om66EegQ+u+djQyuej8CggEAL6ILRNGiV8MAXppeMGpifv3IRmr7FH0oFt3tAE1dqPontnlal9rXI2q/lRH6jBdPyrmegJB9TpSWkAM+Oq3Gf3SNipAIaduMo0PB7q6vgJmA8xf9aXk1tFo2Ov42+cFFVHsN86PIlFsgrBDw/gH53Z82lAjbbkzHwVq0/+Ga2DeuD5OWmbvHSiPv4LM0rfEaE8dkTf09ikykDeyzY4PUwSDmLRLzmWjwzhcc0myek++g4JQJKvXuM5b5GYgEPE/WIs5AC9YUNeHUGb5Ntwfh6rvq/Vfmg4dqNkzwu9KuOh0IXttnSvV5HZVgrTJmdB6VhlIByoqXYSoWVffRNufg1wOCAQUAAoIBAAwe+TWzrxyNXBumUZBdhR4rs2SEbHKv3ygesYLEIsqCH4XfZiLZofYKRmM+DnqPOoGlhYuVONZ5vmuzUPqyyoc3Y6AjaeYDSuSo149VDBi5exVTx7CrboT+yQiKCRMQvibv9vPHIyRay0n2LXvCwUviWB15h4Yr1u+LeaipmiGAg7wYPwBPMZj9E+wWiSHyzS5yH0Is86Z5vNNXXbqO0fQKO51DK9RzBjnwpZW6BWgxnwLU2XnBGpPzXKIH5QuNOC5k8WQ6ZMkOsaPf2343t7bQPbfOFUPbfvu6ZU4J7ypRBoSU0lMZHFlyYJli4neiwrubAUCUKk+OBtJULbzwpnujUzBRMB0GA1UdDgQWBBSc4VnCd08w4F6Oi7kyeqRcxOc1dzAfBgNVHSMEGDAWgBSc4VnCd08w4F6Oi7kyeqRcxOc1dzAPBgNVHRMBAf8EBTADAQH/MAsGCWCGSAFlAwQDAgNHADBEAiBMDsJUndh/zb/hH6X96FS2kggFRHBdDHoppKXxQgfWBQIgZM2HSQioVs4V0eamCT8xUfKApsZmdU/fjqk8UsTz9io=";
    	
    }
    
	public static String getEncodingV2Hex() {
		
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