package org.uic.barcode;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.dynamicFrame.DynamicFrame;
import org.uic.barcode.utils.SimpleDynamicFrameTestBarcode;

public class DynamicFrameLOwLevelTest {
	
	public String algorithmOID = Constants.ECDSA_SHA256;
	public KeyPair keyPair = null;
	public String publicKeyHex = "3081A7301006072A8648CE3D020106052B81040027038192000405B2797BB27F96EC3769B81E563EEB97A4CE3B5BB4EE19BC90A3F496B805644AA042736E5FA9B3A5FBEA5B01CD1D9EC13C009F9655B31FFF9AA52AC6D90B5D6220B58853A1D18BF20779BE5C52356AE70B19242065F82B76961E2A079F42CA9A41A1AB4D5518446AC3721953AE6323C60E15389498DE5F592A24DDDA45F736D93695C797C0F28A712EC25B9CD8078457";
	public String privateKeyHex = "30820109020100301006072A8648CE3D020106052B810400270481F13081EE020101044801EF44914319A5DD528C06419D7B0FD0CDD7F62A231BEB197E45A0074C02E11FE82ABAD916BE94FD8256260AA9191F19241CFC7E372B3A4E0ADA06CCA51678C54198667DFC9B0DA8A00706052B81040027A18195038192000405B2797BB27F96EC3769B81E563EEB97A4CE3B5BB4EE19BC90A3F496B805644AA042736E5FA9B3A5FBEA5B01CD1D9EC13C009F9655B31FFF9AA52AC6D90B5D6220B58853A1D18BF20779BE5C52356AE70B19242065F82B76961E2A079F42CA9A41A1AB4D5518446AC3721953AE6323C60E15389498DE5F592A24DDDA45F736D93695C797C0F28A712EC25B9CD8078457";
	
	public String hexReferenceContent = "400EAC986010DF80A021DE008808014374F3E7D72F2A9979F4A13A90086280B40020894DED0DC0688DEEE0AC593368D60220DCF2EED3BF903B6BCA3B937BAB801280EB44AC0505B4200000000100E6F70656E5469636B6574496E666F120220103B830B9B9B0B3B28084A0B6B9BA32B93230B680202F698040100B20004C6C8020404E9979F40201620505B402C80A0F68020AA192338F4100C08008097308194024800DA0C61105BAD7E13ADF9D5A00CBC47732865EA67E8371A5FBE38B4FABBBABD37459D12048DA6664700E787C32962A607A784FD2FC669A9A8EC9F91CD53AF2B922EFECE24FF3D68024800A1F7CF1C0625FB19CF089E74D668F5E8C15179BEF7BA79D9D169A12FA47F6340ED50BADB57CD83110060FEC08B1EF978C6DB08A172B0DE20C442D4507442623A74A624457590040";
	
	
	@Before public void initialize() {

		Security.addProvider(new BouncyCastleProvider());

		try {
			keyPair  = generateECDSAKeys();
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	    privateKeyHex = UperEncoder.hexStringFromBytes(keyPair.getPrivate().getEncoded());
	    publicKeyHex = UperEncoder.hexStringFromBytes(keyPair.getPublic().getEncoded());

        assert(keyPair != null);
        
        try {
			PublicKey publicKey = KeyFactory.getInstance("ECDSA").generatePublic(new X509EncodedKeySpec(UperEncoder.bytesFromHexString(publicKeyHex)));
			PrivateKey privateKey = KeyFactory.getInstance("ECDSA").generatePrivate(new PKCS8EncodedKeySpec(UperEncoder.bytesFromHexString(privateKeyHex)));
			keyPair = new KeyPair(publicKey,privateKey);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        assert(keyPair != null);
        
	}
	
	
	@Test public void testDynamicHeaderBarcodeEncoding() {
		
		DynamicFrame barcode1 = SimpleDynamicFrameTestBarcode.getSimpleDynamicHeaderBarcode(algorithmOID, keyPair);
		
        byte[] encoded = barcode1.encode();
        
        //String hex = UperEncoder.hexStringFromBytes(encoded);    
        
        /*
         * check the available implementations
        String s = null;
        try {
			s = AlgorithmNameResolver.getSecurityNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
        
        
        //note: hex is different each time due to the random seed in the signature
        assert(encoded != null);

	}
	
	@Test public void testDynamicHeaderBarcodeDecoding() {
		
		DynamicFrame barcode1 = SimpleDynamicFrameTestBarcode.getSimpleDynamicHeaderBarcode(algorithmOID, keyPair);
		
        byte[] encoded = barcode1.encode();
        
		DynamicFrame barcode = DynamicFrame.decode(encoded);
		
		int signatureCheck = barcode.validateLevel1(keyPair.getPublic());

        assert(signatureCheck == Constants.LEVEL1_VALIDATION_OK);
        
        SimpleDynamicFrameTestBarcode.compareFrame(barcode1, barcode);
        
        
               
        
	}	
	
	public KeyPair generateECDSAKeys()  throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException{
		    ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("B-571");
		    KeyPairGenerator g = KeyPairGenerator.getInstance("ECDSA", "BC");
		    g.initialize(ecSpec, new SecureRandom());
		    return g.generateKeyPair();	    
	}
	


}
