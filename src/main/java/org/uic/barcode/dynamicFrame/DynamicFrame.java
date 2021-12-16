package org.uic.barcode.dynamicFrame;

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

import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicContent.api.DynamicContentCoder;
import org.uic.barcode.dynamicContent.api.IUicDynamicContent;
import org.uic.barcode.dynamicContent.fdc1.UicDynamicContentDataFDC1;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.utils.AlgorithmNameResolver;


// TODO: Auto-generated Javadoc
/**
 * The DynamicHeader for bar codes 
 * 
 * Implementation of the Draft under discussion, not final.
 */
@Sequence
public class DynamicFrame extends Object{
	
	/**
	 * Instantiates a new dynamic frame.
	 */
	public DynamicFrame() {}

	/** The format. */
	@FieldOrder(order = 0)
	@RestrictedString(CharacterRestriction.IA5String)
	public String format;
	
	/** The level 2 signed data. */
	/*level 2 data*/
	@FieldOrder(order = 1)
	Level2DataType level2SignedData;
	
	
	/**  The signature of level 2 data. */
	@FieldOrder(order = 2)
	@Asn1Optional public OctetString level2Signature;
	
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
	public Level2DataType getLevel2SignedData() {
		return level2SignedData;
	}

	/**
	 * Sets the level 2 signed data.
	 *
	 * @param level2SignedData the new level 2 signed data
	 */
	public void setLevel2SignedData(Level2DataType level2SignedData) {
		this.level2SignedData = level2SignedData;
	}

	/**
	 * Gets the level 2 signature.
	 *
	 * @return the level 2 signature
	 */
	public OctetString getLevel2Signature() {
		return level2Signature;
	}

	/**
	 * Sets the level 2 signature.
	 *
	 * @param level2Signature the new level 2 signature
	 */
	public void setLevel2Signature(OctetString level2Signature) {
		this.level2Signature = level2Signature;
	}

	/**
	 * Encode.
	 * 
	 * Encode the header as ASN.1 PER UNALIGNED byte array
	 *
	 * @return the byte[]
	 */
	public byte[] encode() {
		return UperEncoder.encode(this);
	}
	
	/**
	 * Decode.
	 *
	 * Decode the header from an ASN.1 PER UNALIGNED encoded byte array
	 *
	 * @param bytes the bytes
	 * @return the dynamic header
	 */
	public static DynamicFrame decode(byte[] bytes) {
		return UperEncoder.decode(bytes, DynamicFrame.class);	
	}
	
	/**
	 * Verify the level 2 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @return the int
	 */	
	public int validateLevel2() {

		return validateLevel2(null);
	
	}
	
	/**
	 * Verify the level 2 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param prov the prov
	 * @return the int
	 */	
	public int validateLevel2(Provider prov) {
		
		
		String level2KeyAlg = this.getLevel2SignedData().getLevel1Data().level2KeyAlg;

	 
		if (level2KeyAlg == null || level2KeyAlg.length() == 0) {
			return Constants.LEVEL2_VALIDATION_NO_KEY;
		}
		
		if (this.level2Signature.toByteArray() == null || this.level2Signature.toByteArray().length == 0) {
			return Constants.LEVEL2_VALIDATION_NO_SIGNATURE;
		}
					
		String keyAlgName = null;
		try {
			keyAlgName = AlgorithmNameResolver.getName(AlgorithmNameResolver.TYPE_KEY_GENERATOR_ALG, level2KeyAlg);
		} catch (Exception e1) {
			return Constants.LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED;	
		}
		if (keyAlgName == null || keyAlgName.length() == 0) {
			return Constants.LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED;	
		}
		
		PublicKey key = null;
		try {
			byte[] keyBytes = this.getLevel2SignedData().getLevel1Data().level2publicKey.toByteArray();
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			key = KeyFactory.getInstance(keyAlgName).generatePublic(keySpec);
		} catch (InvalidKeySpecException e1) {
			return Constants.LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED;	
		} catch (NoSuchAlgorithmException e1) {
			return Constants.LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED;	
		}
		
		//find the algorithm name for the signature OID
		String level2SigAlg = this.getLevel2SignedData().getLevel1Data().level2SigningAlg;

		String sigAlgName = null;
		try {
			sigAlgName = AlgorithmNameResolver.getName(AlgorithmNameResolver.TYPE_SIGNATURE_ALG,level2SigAlg);
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
			byte[] data = UperEncoder.encode(level2SignedData);
			sig.update(data);
		} catch (SignatureException e) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		} catch (IllegalArgumentException e) {
			return Constants.LEVEL2_VALIDATION_ENCODING_ERROR;
		} catch (UnsupportedOperationException e) {
			return Constants.LEVEL2_VALIDATION_ENCODING_ERROR;
		}
		
		byte[] signature = level2Signature.toByteArray();
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
	 */
	public int validateLevel1(PublicKey key, Provider prov) {
		
		if (this.level2SignedData == null) {
			return Constants.LEVEL1_VALIDATION_NO_SIGNATURE;
		}
		
	
		if (this.level2SignedData.level1Signature == null || this.level2SignedData.level1Signature.toByteArray().length == 0) {
			return Constants.LEVEL1_VALIDATION_NO_SIGNATURE;
		}
		
		byte[] signature = this.getLevel2SignedData().level1Signature.toByteArray();
				
		//find the algorithm name for the signature OID
		String algo = null;
		try {
			algo = AlgorithmNameResolver.getSignatureAlgorithmName(getLevel2SignedData().getLevel1Data().level1SigningAlg);
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
			sig.update(this.level2SignedData.level1Data.encode());
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
	 */
	public int validateLevel1(PublicKey key) {
		
		return validateLevel1(key, null);

  	}
	
	/**
	 * Sign level 2 data without a specific security provider.
	 *
	 * @param key the key
	 * @throws Exception the exception
	 */
	public void signLevel2(PrivateKey key) throws Exception {
		
		//find the algorithm name for the signature OID
		String algo = AlgorithmNameResolver.getSignatureAlgorithmName(this.getLevel2SignedData().getLevel1Data().level2SigningAlg);
		Signature sig = Signature.getInstance(algo);
		sig.initSign(key);
		byte[] data = level2SignedData.encode();
		sig.update(data);
		byte[] signature = sig.sign();
		this.level2Signature = new OctetString(signature);
		
	}

	/**
	 * Sign level 2 data.
	 *
	 * @param key the key
	 * @param prov the security Provider
	 * @throws Exception the exception
	 */
	public void signLevel2(PrivateKey key, Provider prov) throws Exception {
		
		//find the algorithm name for the signature OID
		String algo = AlgorithmNameResolver.getSignatureAlgorithmName(this.getLevel2SignedData().getLevel1Data().level2SigningAlg);
		Signature sig = Signature.getInstance(algo,prov);
		sig.initSign(key);
		byte[] data = level2SignedData.encode();
		sig.update(data);
		this.level2Signature = new OctetString(sig.sign());
		
	}

	
	/**
	 * Adds the dynamic content and encodes it. (API level)
	 *
	 * @param content the dynamic content
	 * @throws EncodingFormatException the encoding format exception
	 */
	public void addDynamicContent(IUicDynamicContent content) throws EncodingFormatException {
		
		
		this.getLevel2SignedData().setLevel2Data(new DataType());
		
		this.getLevel2SignedData().getLevel2Data().setFormat(DynamicContentCoder.dynamicContentDataFDC1);
			
		this.getLevel2SignedData().getLevel2Data().setByteData(DynamicContentCoder.encode(content, DynamicContentCoder.dynamicContentDataFDC1));
		
	}
	
	/**
	 * Adds the level 2 dynamic data. (ASN level)
	 *
	 * @param dynamicData the dynamic data
	 */
	public void addLevel2DynamicData(UicDynamicContentDataFDC1 dynamicData) {
		this.getLevel2SignedData().setLevel2Data( dynamicData.getDataType());	
	}
	
	/**
	 * Gets the dynamic content.
	 *
	 * @return the dynamic content
	 */
	public IUicDynamicContent getDynamicContent() {
		
		if (this.getLevel2SignedData() == null || 
				this.getLevel2SignedData().getLevel2Data() == null){
				return null;
		}
		
		return DynamicContentCoder.decode(this.getLevel2SignedData().getLevel2Data().getByteData());
			
	}
	
	/**
	 * Gets the dynamic data FDC 1.
	 *
	 * @return the dynamic data FDC 1
	 */
	public UicDynamicContentDataFDC1 getDynamicDataFDC1() {
		
		if (this.getLevel2SignedData() == null || 
			this.getLevel2SignedData().getLevel2Data() == null){
			return null;
		}
		
		if ( UicDynamicContentDataFDC1.getFormat().equals(this.getLevel2SignedData().getLevel2Data().getFormat())) {
			return UperEncoder.decode(this.getLevel2SignedData().getLevel2Data().getByteData(), UicDynamicContentDataFDC1.class);		
		}
		return null;
		
	}
	
	
}
