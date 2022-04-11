package org.uic.barcode.dynamicFrame.api;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

import org.uic.barcode.dynamicContent.api.DynamicContentCoder;
import org.uic.barcode.dynamicContent.api.IUicDynamicContent;
import org.uic.barcode.dynamicContent.fdc1.UicDynamicContentDataFDC1;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.dynamicFrame.v1.DynamicFrameCoderV1;
import org.uic.barcode.dynamicFrame.v2.DynamicFrameCoderV2;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.utils.AlgorithmNameResolver;
import org.uic.barcode.utils.SecurityUtils;



/**
 * The DynamicHeader for bar codes 
 * 
 */
public class SimpleDynamicFrame implements IDynamicFrame {
	
	/**
	 * Instantiates a new dynamic frame.
	 */
	public SimpleDynamicFrame() {}
	
	public SimpleDynamicFrame(String format) {
		this.format = format;
	}

	/** The format. */
	public String format = null;
	
	/** The level 2 signed data. */
	/*level 2 data*/
	public ILevel2Data level2Data;
	
	
	/**  The signature of level 2 data. */
	public byte[] level2Signature;
	
	public Date endOfValidity = null;
	
	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	@Override
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the format.
	 *
	 * @param format the new format
	 */
	@Override
	public void setFormat(String format) {
		this.format = format;
	}
	
	/**
	 * Gets the level 2 signed data.
	 *
	 * @return the level 2 signed data
	 */
	@Override
	public ILevel2Data getLevel2Data() {
		return level2Data;
	}

	/**
	 * Sets the level 2 signed data.
	 *
	 * @param level2SignedData the new level 2 signed data
	 */
	@Override
	public void setLevel2Data(ILevel2Data level2SignedData) {
		this.level2Data = level2SignedData;
	}

	/**
	 * Gets the level 2 signature.
	 *
	 * @return the level 2 signature
	 */
	@Override
	public byte[] getLevel2Signature() {
		return level2Signature;
	}

	/**
	 * Sets the level 2 signature.
	 *
	 * @param level2Signature the new level 2 signature
	 */
	@Override
	public void setLevel2Signature(byte[] level2Signature) {
		this.level2Signature = level2Signature;
	}


	
	/**
	 * Verify the level 2 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @return the int
	 * @throws EncodingFormatException 
	 */	
	@Override
	public int validateLevel2() throws EncodingFormatException {
		return validateLevel2(null);
	
	}
	
	/**
	 * Verify the level 2 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param provider the registered security provider
	 * @return the return error code
	 * @throws EncodingFormatException 
	 */	
	@Override
	public int validateLevel2(Provider prov) throws EncodingFormatException {
		
		Provider provider = prov;
		
		if (getLevel2Data() == null
				|| getLevel2Data().getLevel1Data() == null 
				|| getLevel2Data().getLevel1Data().getLevel2KeyAlg() == null
				|| getLevel2Data().getLevel1Data().getLevel2KeyAlg().length() == 0) {
			return Constants.LEVEL2_VALIDATION_NO_KEY;
		}
		
		String level2KeyAlg = getLevel2Data().getLevel1Data().getLevel2KeyAlg();

	 
		if (level2KeyAlg == null || level2KeyAlg.length() == 0) {
			return Constants.LEVEL2_VALIDATION_NO_KEY;
		}
		
		if (level2Signature == null || level2Signature.length == 0) {
			return Constants.LEVEL2_VALIDATION_NO_SIGNATURE;
		}
					
		String keyAlgName = null;
		try {
			keyAlgName = AlgorithmNameResolver.getName(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, level2KeyAlg,provider);
		} catch (Exception e1) {
			return Constants.LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED;	
		}
		if (keyAlgName == null || keyAlgName.length() == 0) {
			return Constants.LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED;	
		}
		
		PublicKey key = null;
		try {
			byte[] keyBytes = this.getLevel2Data().getLevel1Data().getLevel2publicKey();
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			
			KeyFactory keyFactory = null;
			if (provider == null) {
				keyFactory = SecurityUtils.findKeyFactory(level2KeyAlg, keyBytes);
				provider = keyFactory.getProvider();
			} else {
				keyFactory = KeyFactory.getInstance(keyAlgName,provider);
			}
			if (keyFactory != null) {
				key = keyFactory.generatePublic(keySpec);
			} else {
				return Constants.LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED;	
			}
			
		} catch (InvalidKeySpecException e1) {
			return Constants.LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED;	
		} catch (NoSuchAlgorithmException e1) {
			return Constants.LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED;	
		}
		
		//find the algorithm name for the signature OID
		String level2SigAlg = this.getLevel2Data().getLevel1Data().getLevel2SigningAlg();

		String sigAlgName = null;
		try {
			sigAlgName = AlgorithmNameResolver.getSignatureAlgorithmName(level2SigAlg,provider);
		} catch (Exception e1) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		if (sigAlgName == null) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		
		Signature sig = null;
		try {
			if (provider == null) {
				sig = Signature.getInstance(sigAlgName);
			} else {
				sig = Signature.getInstance(sigAlgName,provider);
			}
		} catch (NoSuchAlgorithmException e) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		try {
			sig.initVerify(key);
		} catch (InvalidKeyException e) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		
		try {
			byte[] signedData2 = getLevel2DataBin();
			sig.update(signedData2);
		} catch (SignatureException e) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		} catch (IllegalArgumentException e) {
			return Constants.LEVEL2_VALIDATION_ENCODING_ERROR;
		} catch (UnsupportedOperationException e) {
			return Constants.LEVEL2_VALIDATION_ENCODING_ERROR;
		}
		
		byte[] signature = level2Signature;
		try {
			if (sig.verify(signature)){
				return Constants.LEVEL2_VALIDATION_OK;
			} else {
				return Constants.LEVEL2_VALIDATION_FRAUD;
			}
		} catch (SignatureException e) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
  	}
	
	@Override
	public int validateLevel1(PublicKey key, Provider prov) throws EncodingFormatException {
		return validateLevel1(key, prov, null);

  	}
	
	@Override
	public int validateLevel1(PublicKey key) throws EncodingFormatException {
		return validateLevel1(key, null, null);
  	}
	
	@Override
	public int validateLevel1(PublicKey key, String signatureAlgorithmOid) throws EncodingFormatException {
		return validateLevel1(key, null, signatureAlgorithmOid);
	}

	@Override
	public int validateLevel1(PublicKey key, Provider prov, String signatureAlgorithmOid) throws EncodingFormatException {

		if (getLevel2Data() == null
				|| getLevel2Data().getLevel1Signature() == null
				|| getLevel2Data().getLevel1Signature() == null
				|| getLevel2Data().getLevel1Signature().length == 0) {
			return Constants.LEVEL1_VALIDATION_NO_SIGNATURE;
		}
		
		byte[] signature = this.getLevel2Data().getLevel1Signature();

	
		//find the algorithm name for the signature OID		
		String signingAlgorithmOid = null;
		if (getLevel2Data() != null 
				&& getLevel2Data().getLevel1Data() != null 
				&& getLevel2Data().getLevel1Data().getLevel1SigningAlg() != null
				&& getLevel2Data().getLevel1Data().getLevel1SigningAlg().length() > 0) {
				signingAlgorithmOid = getLevel2Data().getLevel1Data().getLevel1SigningAlg();	
		} else {
			signingAlgorithmOid = signatureAlgorithmOid;
		}
				
		if (signingAlgorithmOid == null || signingAlgorithmOid.length() == 0) {
			return Constants.LEVEL1_VALIDATION_NO_SIGNATURE;
		}				
		//find the algorithm name for the signature OID
		String algo = null;
		try {
			algo = AlgorithmNameResolver.getSignatureAlgorithmName(signingAlgorithmOid, prov);
		} catch (Exception e1) {
			return Constants.LEVEL1_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}	
		if (algo == null) {
			return Constants.LEVEL1_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		
		Signature sig;
		try {
			if (prov != null) {
				sig = Signature.getInstance(algo, prov);
			} else {
				sig = Signature.getInstance(algo);

			}
		} catch (NoSuchAlgorithmException e) {
			return Constants.LEVEL1_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		try {
			sig.initVerify(key);
		} catch (InvalidKeyException e) {
			return Constants.LEVEL1_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		
		try {
			
			byte[]  encodedData = getLevel1DataBin();			
			
			sig.update(encodedData);	
			
		} catch (SignatureException e) {
			return Constants.LEVEL1_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		} catch (IllegalArgumentException e) {
			return Constants.LEVEL1_VALIDATION_ENCODING_ERROR;
		} catch (UnsupportedOperationException e) {
			return Constants.LEVEL1_VALIDATION_ENCODING_ERROR;
		}
		
		try {
			if (sig.verify(signature)){
				return Constants.LEVEL1_VALIDATION_OK;
			} else {
				return Constants.LEVEL1_VALIDATION_FRAUD;
			}
		} catch (Exception e) {
			return Constants.LEVEL1_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
	}
	
	@Override
	public void signLevel2(PrivateKey key) throws Exception {
		signLevel2(key, null);
	}

	/**
	 * Sign level 2 data.
	 *
	 * @param key the key
	 * @param prov the registered security provider
	 * @throws Exception the exception
	 */
	@Override
	public void signLevel2(PrivateKey key, Provider prov) throws Exception {
		
		//find the algorithm name for the signature OID
		String algo = AlgorithmNameResolver.getSignatureAlgorithmName(this.getLevel2Data().getLevel1Data().getLevel2SigningAlg(), prov);
		Signature sig = null;
		if (prov == null) {
			prov = SecurityUtils.findPrivateKeyProvider(key);
		}
		if (prov != null) {
			sig = Signature.getInstance(algo,prov);
		} else {
			sig = Signature.getInstance(algo);
		}
		sig.initSign(key);
		byte[] signedData = DynamicFrameCoder.encodeLevel2Data(this);
		sig.update(signedData);
		level2Signature = sig.sign();
		
	}

	
	/**
	 * Adds the dynamic content and encodes it. (API level)
	 *
	 * @param content the dynamic content
	 * @throws EncodingFormatException the encoding format exception
	 */
	@Override
	public void addDynamicContent(IUicDynamicContent content) throws EncodingFormatException {
				
		level2Data.setLevel2Data(new SimpleData());
		
		level2Data.getLevel2Data().setFormat(DynamicContentCoder.dynamicContentDataFDC1);
			
		level2Data.getLevel2Data().setData(DynamicContentCoder.encode(content, DynamicContentCoder.dynamicContentDataFDC1));
		
	}
	
	/**
	 * Adds the level 2 dynamic data. (ASN level)
	 *
	 * @param dynamicData the dynamic data
	 */
	@Override
	public void addLevel2DynamicData(UicDynamicContentDataFDC1 dynamicData) {
		this.getLevel2Data().setLevel2Data(dynamicData.getApiDataType());	
	}
	
	/**
	 * Gets the dynamic content.
	 *
	 * @return the dynamic content
	 */
	@Override
	public IUicDynamicContent getDynamicContent() {
		
		if (this.getLevel2Data() == null || 
				this.getLevel2Data().getLevel2Data() == null){
				return null;
		}
		
		return DynamicContentCoder.decode(level2Data.getLevel2Data().getData());
			
	}
	

	/**
	 * Sign the contained data block.
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @return 
	 * @return the byte[]
	 * @throws Exception 
	 */
	@Override
	public void signLevel1(PrivateKey key) throws Exception {
		
		signLevel1(key, null);
		
	}

	/**
	 * Sign the contained data block.
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @param security provider - security provider that must be sued to create the signature
	 * @return 
	 * @return the byte[]
	 * @throws Exception 
	 */
	@Override
	public void signLevel1(PrivateKey key, Provider prov) throws Exception {
		
		if (level2Data == null) return;
		
		ILevel1Data level1Data = level2Data.getLevel1Data();
		
		if (level1Data == null) return;

		if (prov == null) {
			//check for a provider supporting the key
			prov = SecurityUtils.findPrivateKeyProvider(key);
		}
		
		//find the algorithm name for the signature OID
		String algo = AlgorithmNameResolver.getSignatureAlgorithmName(level1Data.getLevel1SigningAlg(), prov);
		Signature sig = null;

		if (prov != null) {
			sig = Signature.getInstance(algo, prov);
		} else {
			sig = Signature.getInstance(algo);
		}
		sig.initSign(key);
				
		byte[] data = DynamicFrameCoder.encodeLevel1(this);
		sig.update(data);
		level2Data.setLevel1Signature(sig.sign());	
	}

	@Override
	public byte[] getLevel1Signature() {
		return getLevel2Data().getLevel1Signature();
	}

	@Override
	public byte[] getLevel1DataBin() throws EncodingFormatException {
		
		if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1.equals(format)) {
			
			return DynamicFrameCoderV1.encode(getLevel2Data().getLevel1Data());
			
		} else if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2.equals(format)) {
			
			return DynamicFrameCoderV2.encode(getLevel2Data().getLevel1Data());
			
		}
		
		throw new EncodingFormatException("Dynamic Header Version not supported");

	}
	
	@Override
	public byte[] getLevel2DataBin() throws EncodingFormatException {
		
		if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1.equals(format)) {
			
			return DynamicFrameCoderV1.encodeLevel2Data(getLevel2Data());
			
		} else if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2.equals(format)) {
			
			return DynamicFrameCoderV2.encodeLevel2Data(getLevel2Data());
			
		}
		
		throw new EncodingFormatException("Dynamic Header Version not supported");

	}



	
}
