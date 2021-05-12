package org.uic.barcode.test.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.uic.barcode.utils.AlgorithmNameResolver;

public class TestUtils {
	
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
    public static String hexStringFromBytes(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    
    public static Date parseDate (String source){
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	
    	try {
			return formatter.parse(source);
		} catch (ParseException e) {
			try {
				return formatter.parse("2001-01-01");
			} catch (ParseException e1) {
				return null;
			}
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
	public static KeyPair generateDSAKeys(int keySize)  throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException{
		    KeyPairGenerator g = KeyPairGenerator.getInstance("DSA", "BC");
		    g.initialize(keySize, new SecureRandom());
		    return g.generateKeyPair();	    
	}

	public static KeyPair generateECKeys(String keyAlgorithmOid, String curve)  throws Exception{
		
		String keyAlgorithmName = AlgorithmNameResolver.getName(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG,  keyAlgorithmOid, "BC");
		
		keyAlgorithmName = "ECDSA";
		ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec(curve);
	    KeyPairGenerator g = KeyPairGenerator.getInstance(keyAlgorithmName, "BC");
	    g.initialize(ecSpec, new SecureRandom());
	    return g.generateKeyPair();	    
    }
    
}
