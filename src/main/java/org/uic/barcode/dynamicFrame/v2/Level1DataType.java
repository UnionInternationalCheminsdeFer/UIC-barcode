package org.uic.barcode.dynamicFrame.v2;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.utils.UicEncoderUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class SignedDataType.
 */
@Sequence
@HasExtensionMarker
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
	@IntRange(minValue=0,maxValue=99999)
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
  	 *          --    DSA SHA256  2.16.840.1.101.3.4.3.2
  	 *          --    ECC 256     1.2.840.10045.3.1.7
	 *  
	 *  
	 */
	@FieldOrder(order = 4)
	@RestrictedString(CharacterRestriction.ObjectIdentifier)
	@Asn1Optional public String level1KeyAlg;		
		
	/** The level 2 key alg. */
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
  	 *          --    DSA SHA256  2.16.840.1.101.3.4.3.2
  	 *          --    ECC 256     1.2.840.10045.3.1.7
	 *  
	 *  
	 */
	@FieldOrder(order = 6)
	@RestrictedString(CharacterRestriction.ObjectIdentifier)
	@Asn1Optional public String level1SigningAlg;	
	
	/** The level 2 signing alg. */
	@FieldOrder(order = 7)
	@RestrictedString(CharacterRestriction.ObjectIdentifier)
	@Asn1Optional public String level2SigningAlg;		
	
	
	/**  The level 2 public key. */
	@FieldOrder(order = 8)
	@Asn1Optional public OctetString level2publicKey;
	

	/** The End of validity year. */
	@FieldOrder(order = 9)
	@IntRange(minValue=2016,maxValue=2269)
	@Asn1Optional public Long EndOfValidityYear;

	/** The End of validity day. */
	@FieldOrder(order = 10)
	@IntRange(minValue=1,maxValue=366)
	@Asn1Optional public Long EndOfValidityDay;

	/** The End of validity time. */
	@FieldOrder(order = 11)
	@IntRange(minValue=0,maxValue=1439)
	@Asn1Optional public Long EndOfValidityTime;
	
	
	
	
	
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
	
	/**
	 * Gets the key id.
	 *
	 * @return the key id
	 */
	public Long getKeyId() {
		return keyId;
	}

	/**
	 * Sets the key id.
	 *
	 * @param keyId the new key id
	 */
	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public SequenceOfDataType getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(SequenceOfDataType data) {
		this.data = data;
	}

	/**
	 * Gets the level 2 key alg.
	 *
	 * @return the level 2 key alg
	 */
	public String getLevel2KeyAlg() {
		return level2KeyAlg;
	}

	/**
	 * Sets the level 2 key alg.
	 *
	 * @param level2KeyAlg the new level 2 key alg
	 */
	public void setLevel2KeyAlg(String level2KeyAlg) {
		this.level2KeyAlg = level2KeyAlg;
	}

	/**
	 * Gets the level 1 signing alg.
	 *
	 * @return the level 1 signing alg
	 */
	public String getLevel1SigningAlg() {
		return level1SigningAlg;
	}

	/**
	 * Sets the level 1 signing alg.
	 *
	 * @param level1SigningAlg the new level 1 signing alg
	 */
	public void setLevel1SigningAlg(String level1SigningAlg) {
		this.level1SigningAlg = level1SigningAlg;
	}

	/**
	 * Gets the level 2 signing alg.
	 *
	 * @return the level 2 signing alg
	 */
	public String getLevel2SigningAlg() {
		return level2SigningAlg;
	}

	/**
	 * Sets the level 2 signing alg.
	 *
	 * @param level2SigningAlg the new level 2 signing alg
	 */
	public void setLevel2SigningAlg(String level2SigningAlg) {
		this.level2SigningAlg = level2SigningAlg;
	}

	/**
	 * Gets the level 2 public key.
	 *
	 * @return the level 2 public key
	 */
	public OctetString getLevel2publicKey() {
		return level2publicKey;
	}

	/**
	 * Sets the level 2 public key.
	 *
	 * @param level2publicKey the new level 2 public key
	 */
	public void setLevel2publicKey(OctetString level2publicKey) {
		this.level2publicKey = level2publicKey;
	}

	
	
	/**
	 * Gets the level 1 key alg.
	 *
	 * @return the level 1 key alg
	 */
	public String getLevel1KeyAlg() {
		return level1KeyAlg;
	}

	/**
	 * Sets the level 1 key alg.
	 *
	 * @param level1KeyAlg the new level 1 key alg
	 */
	public void setLevel1KeyAlg(String level1KeyAlg) {
		this.level1KeyAlg = level1KeyAlg;
	}
	
	/**
	 * Sets the end of validity date. 
	 * @param date the new end of validity date
	 */
	public void setEndOfValidityDate(Date date){
		
		if (date == null) return;
		
		TimeZone local = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		this.EndOfValidityYear = new Long( cal.get(Calendar.YEAR));
		this.EndOfValidityDay = new Long (cal.get(Calendar.DAY_OF_YEAR));
		int time =  cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
		if (time >= 0) {
			this.EndOfValidityTime = new Long (time );
		}
		TimeZone.setDefault(local);
		
	}
	
	/**
	 * Gets the end of validity date.
	 *
	 * @return the end of validity date
	 */
	public Date getEndOfValidityDate() {
		
		if (this.EndOfValidityYear == null || this.EndOfValidityDay == null) return null;
		
		TimeZone local = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTimeZone(TimeZone.getTimeZone("UTC"));
		cal.set(Calendar.YEAR, this.EndOfValidityYear.intValue());
		cal.set(Calendar.DAY_OF_YEAR, this.EndOfValidityDay.intValue());
		
		if (this.EndOfValidityTime != null) {
		
			int hours = this.EndOfValidityTime.intValue() / 60;
			int minutes = this.EndOfValidityTime.intValue() % 60;
			cal.set(Calendar.HOUR_OF_DAY, hours);
			cal.set(Calendar.MINUTE,minutes);

		}
		
		Date d = cal.getTime();
		
		TimeZone.setDefault(local);
		
		return d;
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
