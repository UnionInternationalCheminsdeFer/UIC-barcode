package org.uic.barcode.dynamicFrame;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.utils.AlgorithmNameResolver;


/**
 * The DynamicHeader for bar codes 
 * 
 * Implementation of the Draft under discussion, not final.
 */
@Sequence
@HasExtensionMarker
public class DynamicFrame extends Object{
	
	public DynamicFrame() {}

	/** The format. */
	@FieldOrder(order = 0)
	@RestrictedString(CharacterRestriction.IA5String)
	public String format;
	
	/*level 2 data*/
	@FieldOrder(order = 1)
	Level2DataType level2SignedData;
	
	
	/** The signature of level 2 data*/
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
	
	public Level2DataType getLevel2SignedData() {
		return level2SignedData;
	}

	public void setLevel2SignedData(Level2DataType level2SignedData) {
		this.level2SignedData = level2SignedData;
	}

	public OctetString getLevel2Signature() {
		return level2Signature;
	}

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
	 */	
	public int validateLevel2() {
		
		
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
			key = KeyFactory.getInstance(keyAlgName).generatePublic(new X509EncodedKeySpec(this.getLevel2SignedData().getLevel1Data().level2publicKey.toByteArray()));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e1) {
			return Constants.LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED;	
		}
		
		//find the algorithm name for the signature OID
		String algo = null;
		try {
			algo = AlgorithmNameResolver.getName(AlgorithmNameResolver.TYPE_SIGNATURE_ALG,this.getLevel2SignedData().getLevel1Data().level2SigningAlg);
		} catch (Exception e1) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		if (algo == null) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		
		Signature sig;
		try {
			sig = Signature.getInstance(algo);
		} catch (NoSuchAlgorithmException e) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		try {
			sig.initVerify(key);
		} catch (InvalidKeyException e) {
			return Constants.LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED;
		}
		
		try {
			sig.update(UperEncoder.encode(level2SignedData));
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
	 */
	public int validateLevel1(PublicKey key) {
		
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
			sig = Signature.getInstance(algo);
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
	
	public void signLevel2(PrivateKey key) throws Exception {
		
		//find the algorithm name for the signature OID
		String algo = AlgorithmNameResolver.getSignatureAlgorithmName(this.getLevel2SignedData().getLevel1Data().level2SigningAlg);
		Signature sig = Signature.getInstance(algo);
		sig.initSign(key);
		byte[] data = level2SignedData.encode();
		sig.update(data);
		this.level2Signature = new OctetString(sig.sign());
		
	}
	
	
}
