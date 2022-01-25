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

import org.uic.barcode.asn1.uper.AsnUtils;
import org.uic.barcode.dynamicContent.api.DynamicContentCoder;
import org.uic.barcode.dynamicContent.api.IUicDynamicContent;
import org.uic.barcode.dynamicContent.fdc1.UicDynamicContentDataFDC1;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.dynamicFrame.v1.DynamicFrameCoderV1;
import org.uic.barcode.dynamicFrame.v2.DynamicFrameCoderV2;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.utils.AlgorithmNameResolver;



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
	public String format = Constants.DYNAMIC_BARCODE_FORMAT_DEFAULT;
	
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
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the format.
	 *
	 * @param format the new format
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
	/**
	 * Gets the level 2 signed data.
	 *
	 * @return the level 2 signed data
	 */
	public ILevel2Data getLevel2Data() {
		return level2Data;
	}

	/**
	 * Sets the level 2 signed data.
	 *
	 * @param level2SignedData the new level 2 signed data
	 */
	public void setLevel2Data(ILevel2Data level2SignedData) {
		this.level2Data = level2SignedData;
	}

	/**
	 * Gets the level 2 signature.
	 *
	 * @return the level 2 signature
	 */
	public byte[] getLevel2Signature() {
		return level2Signature;
	}

	/**
	 * Sets the level 2 signature.
	 *
	 * @param level2Signature the new level 2 signature
	 */
	public void setLevel2Signature(byte[] level2Signature) {
		this.level2Signature = level2Signature;
	}

	/**
	 * Encode.
	 * 
	 * Encode the header as ASN.1 PER UNALIGNED byte array
	 *
	 * @return the byte[]
	 * @throws EncodingFormatException 
	 */
	public byte[] encode() throws EncodingFormatException {
			
		if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1.equals(format)) {
			
			return DynamicFrameCoderV1.encode(this);
			
		} else if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2.equals(format)) {
			
			return DynamicFrameCoderV2.encode(this);
			
		}
		
		return null;
	}
	
	private byte[] encode(ILevel1Data level1Data) throws EncodingFormatException {
		
		if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1.equals(format)) {
			
			return DynamicFrameCoderV1.encode(level1Data);
			
		} else if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2.equals(format)) {
			
			return DynamicFrameCoderV2.encode(level1Data);
			
		}
		throw new EncodingFormatException("Dynamic Header Version not supported: " + format);
	}
	
	private byte[] getEncoded(String path, byte[] data) throws EncodingFormatException {
		
		if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1.equals(format)) {
			
			return DynamicFrameCoderV1.getEncoded(path, data);
			
		} else if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2.equals(format)) {
			
			return DynamicFrameCoderV2.getEncoded(path, data);
			
		}
		
		throw new EncodingFormatException("Dynamic Header Version not supported: " + format);
	}
	
	
	private byte[] encode(ILevel2Data level2Data) throws EncodingFormatException {
		
		if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1.equals(format)) {
			
			return DynamicFrameCoderV1.encode(level2Data);
			
		} else if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2.equals(format)) {
			
			return DynamicFrameCoderV2.encode(level2Data);
			
		}
		
		throw new EncodingFormatException("Dynamic Header Version not supported: " + format);
	}
	
	/**
	 * Decode.
	 *
	 * Decode the header from an ASN.1 PER UNALIGNED encoded byte array
	 *
	 * @param bytes the bytes
	 * @return the dynamic header
	 * @throws EncodingFormatException 
	 */
	public void decode(byte[] bytes) throws EncodingFormatException {
		
		String format = getFormat(bytes);
			
		if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1.equals(format)) {
			
			DynamicFrameCoderV1.decode(this,bytes);
			return;
			
		} else if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2.equals(format)) {
		
			DynamicFrameCoderV2.decode(this,bytes);
			return;
		}
		
		throw new EncodingFormatException("Dynamic Header Version not supported");

	}
	


	
	/**
	 * Checks if is static header.
	 *
	 * @param data the data
	 * @return true, if is static header
	 */
	private static String getFormat(byte[] data) {
		
		if (data == null || data.length < 4) return null;
		
		byte[] startBits = new byte[4];
		startBits[0] = data[0];
		startBits[1] = data[1];
		startBits[2] = data[2];
		startBits[3] = data[3];

		String start = AsnUtils.toBooleanString(startBits);
		
		/*
		 * bitshift: 
		 * 
		 * version 1:
		 *    optional Level2Data 1 bit
		 *    length of format:   8 bit
		 * 
		 * version 2:
		 *    extensionIndicator  1 bit
		 *    optional Level2Data 1 bit
		 *    length of format:   8 bit
		 */
		
		if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1_BIN.equals(start.substring(9, 23))) {
			return Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1;
		}
		
		if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2_BIN.equals(start.substring(10, 24))) {
			return Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2;
		}
		return null;
	}
	
	/**
	 * Verify the level 2 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @return the int
	 * @throws EncodingFormatException 
	 */	
	public int validateLevel2(byte[] data) throws EncodingFormatException {

		return validateLevel2(null, data);
	
	}
	
	/**
	 * Verify the level 2 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param prov the registered security provider
	 * @return the return error code
	 * @throws EncodingFormatException 
	 */	
	public int validateLevel2(Provider prov, byte[] data) throws EncodingFormatException {
		
		
		String level2KeyAlg = this.getLevel2Data().getLevel1Data().getLevel2KeyAlg();

	 
		if (level2KeyAlg == null || level2KeyAlg.length() == 0) {
			return Constants.LEVEL2_VALIDATION_NO_KEY;
		}
		
		if (level2Signature == null || level2Signature.length == 0) {
			return Constants.LEVEL2_VALIDATION_NO_SIGNATURE;
		}
					
		String keyAlgName = null;
		try {
			keyAlgName = AlgorithmNameResolver.getName(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, level2KeyAlg,prov);
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
			key = KeyFactory.getInstance(keyAlgName).generatePublic(keySpec);
		} catch (InvalidKeySpecException e1) {
			return Constants.LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED;	
		} catch (NoSuchAlgorithmException e1) {
			return Constants.LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED;	
		}
		
		//find the algorithm name for the signature OID
		String level2SigAlg = this.getLevel2Data().getLevel1Data().getLevel2SigningAlg();

		String sigAlgName = null;
		try {
			sigAlgName = AlgorithmNameResolver.getName(AlgorithmNameResolver.TYPE_SIGNATURE_ALG,level2SigAlg,prov);
		} catch (Exception e1) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		if (sigAlgName == null) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		
		Signature sig;
		try {
			if (prov == null) {
				sig = Signature.getInstance(sigAlgName);
			} else {
				sig = Signature.getInstance(sigAlgName, prov);
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
			//TODO
			//byte[] signedData = encode(level2Data);
			//String s1 = AsnUtils.toBooleanString(signedData);
			
			byte[] signedData2 = getEncoded("Level2Data", data);
			//String s2 = AsnUtils.toBooleanString(signedData);
			
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
	
	/**
	 * Verify the level 1 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @param prov the prov
	 * @return the int
	 * @throws EncodingFormatException 
	 */
	public int validateLevel1(PublicKey key, Provider prov, byte[] data) throws EncodingFormatException {
		
		if (level2Data == null) {
			return Constants.LEVEL1_VALIDATION_NO_SIGNATURE;
		}
		
	
		if (level2Data == null || 
			level2Data.getLevel1Signature().length == 0) {
			return Constants.LEVEL1_VALIDATION_NO_SIGNATURE;
		}
		
		byte[] signature = this.getLevel2Data().getLevel1Signature();
				
		//find the algorithm name for the signature OID
		String algo = null;
		try {
			algo = AlgorithmNameResolver.getSignatureAlgorithmName(getLevel2Data().getLevel1Data().getLevel1SigningAlg(), prov);
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
			
			byte[]  encodedData = getEncoded("Level1Data", data);
			
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
				return Constants.LEVEL2_VALIDATION_OK;
			} else {
				return Constants.LEVEL2_VALIDATION_FRAUD;
			}
		} catch (SignatureException e) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
  	}
	




	/**
	 * Verify the level 1 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @return the int
	 * @throws EncodingFormatException 
	 */
	public int validateLevel1(PublicKey key, byte[] data) throws EncodingFormatException {
		
		return validateLevel1(key, null,data);

  	}
	
	/**
	 * Sign level 2 data without a specific security provider.
	 *
	 * @param key the key
	 * @throws Exception the exception
	 */
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
	public void signLevel2(PrivateKey key, Provider prov) throws Exception {
		
		//find the algorithm name for the signature OID
		String algo = AlgorithmNameResolver.getSignatureAlgorithmName(this.getLevel2Data().getLevel1Data().getLevel2SigningAlg(), prov);
		Signature sig = null;
		if (prov != null) {
			sig = Signature.getInstance(algo,prov);
		} else {
			sig = Signature.getInstance(algo);
		}
		sig.initSign(key);
		byte[] signedData = encode(level2Data);
		sig.update(signedData);
		level2Signature = sig.sign();
		
	}

	
	/**
	 * Adds the dynamic content and encodes it. (API level)
	 *
	 * @param content the dynamic content
	 * @throws EncodingFormatException the encoding format exception
	 */
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
	public void addLevel2DynamicData(UicDynamicContentDataFDC1 dynamicData) {
		this.getLevel2Data().setLevel2Data(dynamicData.getApiDataType());	
	}
	
	/**
	 * Gets the dynamic content.
	 *
	 * @return the dynamic content
	 */
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
		
		//find the algorithm name for the signature OID
		String algo = AlgorithmNameResolver.getSignatureAlgorithmName(level1Data.getLevel1SigningAlg());
		Signature sig = null;
		if (prov != null) {
			sig = Signature.getInstance(algo, prov);
		} else {
			sig = Signature.getInstance(algo);
		}
		sig.initSign(key);
				
		byte[] data = encode(level1Data);
		sig.update(data);
		level2Data.setLevel1Signature(sig.sign());	
	}
	
}
