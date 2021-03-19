package org.uic.barcode.dynamicFrame;

import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.utils.UicEncoderUtils;

/**
 * The Class SignedDataType.
 */
@Sequence
public class Level1DataType {
	
	/** 
	 * The security provider 
	 * numeric codes 1 ...32000
	 *  
	 *   */
	@FieldOrder(order = 0)
	@IntRange(minValue=1,maxValue=32000)
	@Asn1Optional public Long securityProviderNum;

	/**  The security provider  alphanumeric codes. */
	@FieldOrder(order = 1)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String securityProviderIA5;

	
	/** The key id. */
	@FieldOrder(order = 2)
	@IntRange(minValue=1,maxValue=99999)
	@Asn1Optional public Long	keyId;
	
	
	/** The data. */
	@FieldOrder(order = 3)
	public SequenceOfDataType data;
	
	/** 
	 * The key generator algorithms
	 * Object Identifier of the Algorithm
	 * Number notation:
	 * 
	 *  e.g.:
	 *        	--    DSA SHA224  2.16.840.1.101.3.4.3.1
  	 *          --    DSA SHA248  2.16.840.1.101.3.4.3.2
  	 *          --    ECC 256     1.2.840.10045.3.1.7
	 *  
	 *  
	 */
	@FieldOrder(order = 4)
	@RestrictedString(CharacterRestriction.ObjectIdentifier)
	@Asn1Optional public String level1KeyAlg;		
		
	@FieldOrder(order = 5)
	@RestrictedString(CharacterRestriction.ObjectIdentifier)
	@Asn1Optional public String level2KeyAlg;		
	
	/** 
	 * The signing algorithm
	 * Object Identifier of the Algorithms
	 * Number notation:
	 * 
	 *  e.g.:
	 *        	--    DSA SHA224  2.16.840.1.101.3.4.3.1
  	 *          --    DSA SHA248  2.16.840.1.101.3.4.3.2
  	 *          --    ECC 256     1.2.840.10045.3.1.7
	 *  
	 *  
	 */
	@FieldOrder(order = 6)
	@RestrictedString(CharacterRestriction.ObjectIdentifier)
	@Asn1Optional public String level1SigningAlg;	
	
	@FieldOrder(order = 7)
	@RestrictedString(CharacterRestriction.ObjectIdentifier)
	@Asn1Optional public String level2SigningAlg;		
	
	
	/** The level 2 public key*/
	@FieldOrder(order = 8)
	@Asn1Optional public OctetString level2publicKey;
	

	
	/**
	 * Gets the security provider num.
	 *
	 * @return the security provider num
	 */
	public Long getSecurityProviderNum() {
		return securityProviderNum;
	}

	/**
	 * Sets the security provider num.
	 * 
	 * in case the security provider code is encoded in IA5 this will return null
	 *
	 * @param securityProviderNum the new security provider num
	 */
	public void setSecurityProviderNum(Long securityProviderNum) {
		this.securityProviderNum = securityProviderNum;
	}

	/**
	 * Gets the security provider IA5.
	 * 
	 * in case the security provider code is encoded numerically this will return null
	 *
	 * @return the security provider IA5
	 */
	public String getSecurityProviderIA5() {
		return securityProviderIA5;
	}
	
	/**
	 * Sets the security provider
	 * 
	 * The security provider code must use the IA5 Alphabet .
	 *
	 * @param securityProvider the new security provider
	 * @throws EncodingFormatException the encoding format exception
	 */
	public void setSecurityProvider(String securityProvider) throws EncodingFormatException {
		this.securityProviderNum = UicEncoderUtils.getNum(securityProvider);
		this.securityProviderIA5 = UicEncoderUtils.getIA5NonNum(securityProvider);
	}
	
	
	/**
	 * Gets the security provider.
	 *
	 * @return the security provider
	 */
	public String getSecurityProvider() {
		return UicEncoderUtils.mapToString(this.securityProviderNum, this.securityProviderIA5);
	}
	

	/**
	 * Sets the security provider IA 5.
	 *
	 * @param securityProviderIA5 the new security provider IA 5
	 */
	public void setSecurityProviderIA5(String securityProviderIA5) {
		this.securityProviderIA5 = securityProviderIA5;
	}
	
	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public SequenceOfDataType getData() {
		return data;
	}

	public void setData(SequenceOfDataType data) {
		this.data = data;
	}

	public String getLevel2KeyAlg() {
		return level2KeyAlg;
	}

	public void setLevel2KeyAlg(String level2KeyAlg) {
		this.level2KeyAlg = level2KeyAlg;
	}

	public String getLevel1SigningAlg() {
		return level1SigningAlg;
	}

	public void setLevel1SigningAlg(String level1SigningAlg) {
		this.level1SigningAlg = level1SigningAlg;
	}

	public String getLevel2SigningAlg() {
		return level2SigningAlg;
	}

	public void setLevel2SigningAlg(String level2SigningAlg) {
		this.level2SigningAlg = level2SigningAlg;
	}

	public OctetString getLevel2publicKey() {
		return level2publicKey;
	}

	public void setLevel2publicKey(OctetString level2publicKey) {
		this.level2publicKey = level2publicKey;
	}

	
	
	public String getLevel1KeyAlg() {
		return level1KeyAlg;
	}

	public void setLevel1KeyAlg(String level1KeyAlg) {
		this.level1KeyAlg = level1KeyAlg;
	}

	/**
	 * Gets the data for signature.
	 *
	 * The byte array containing the ASN.1 PER UNALIGNED encoded data of the DataBlock
	 *
	 *
	 * @return the data for signature
	 */
	public byte[] encode() {
		return UperEncoder.encode(this);

	}
}
