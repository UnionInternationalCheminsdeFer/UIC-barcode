package org.uic.header;

import net.gcdc.asn1.datatypes.Asn1Optional;
import net.gcdc.asn1.datatypes.CharacterRestriction;
import net.gcdc.asn1.datatypes.HasExtensionMarker;
import net.gcdc.asn1.datatypes.IntRange;
import net.gcdc.asn1.datatypes.RestrictedString;
import net.gcdc.asn1.datatypes.Sequence;
import net.gcdc.asn1.datatypesimpl.OctetString;


/**
 * The DynamicHeader for bar codes 
 * 
 * Implementation of the Draft under discussion, not final
 * 
 */
@Sequence
@HasExtensionMarker
public class DynamicHeader {
	

	/** The format. */
	@RestrictedString(CharacterRestriction.IA5String)
	public String format;
	
	/** The vesion. */
	@IntRange(minValue=1,maxValue=16)
	public Long version;
	
	
	/** 
	 * The security provider 
	 * numeric codes 1 ...32000
	 *  
	 *   */
	@IntRange(minValue=1,maxValue=32000)
	@Asn1Optional public Long securityProviderNum;

	/** The security provider 
	 * alphanumeric codes 
	 */
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String securityProviderIA5;


	/** The static data. */
	public SignedDataType staticData;
	
	/** The dynamic data. */
	@Asn1Optional public SignedDataType dynamicData;	
	
	/** The dynamic public key. */
	@Asn1Optional public OctetString dynamicPublicKey;	
	
	/** The extension. */
	@Asn1Optional public Extension extension;

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
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param vesion the new version
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

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
	 * @param securityProviderNum the new security provider num
	 */
	public void setSecurityProviderNum(Long securityProviderNum) {
		this.securityProviderNum = securityProviderNum;
	}

	/**
	 * Gets the security provider IA 5.
	 *
	 * @return the security provider IA 5
	 */
	public String getSecurityProviderIA5() {
		return securityProviderIA5;
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
	 * Gets the static data.
	 *
	 * @return the static data
	 */
	public SignedDataType getStaticData() {
		return staticData;
	}

	/**
	 * Sets the static data.
	 *
	 * @param staticData the new static data
	 */
	public void setStaticData(SignedDataType staticData) {
		this.staticData = staticData;
	}

	/**
	 * Gets the dynamic data.
	 *
	 * @return the dynamic data
	 */
	public SignedDataType getDynamicData() {
		return dynamicData;
	}

	/**
	 * Sets the dynamic data.
	 *
	 * @param dynamicData the new dynamic data
	 */
	public void setDynamicData(SignedDataType dynamicData) {
		this.dynamicData = dynamicData;
	}

	/**
	 * Gets the dynamic public key.
	 *
	 * @return the dynamic public key
	 */
	public OctetString getDynamicPublicKey() {
		return dynamicPublicKey;
	}

	/**
	 * Sets the dynamic public key.
	 *
	 * @param dynamicPublicKey the new dynamic public key
	 */
	public void setDynamicPublicKey(OctetString dynamicPublicKey) {
		this.dynamicPublicKey = dynamicPublicKey;
	}

	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public Extension getExtension() {
		return extension;
	}

	/**
	 * Sets the extension.
	 *
	 * @param extension the new extension
	 */
	public void setExtension(Extension extension) {
		this.extension = extension;
	}


}
