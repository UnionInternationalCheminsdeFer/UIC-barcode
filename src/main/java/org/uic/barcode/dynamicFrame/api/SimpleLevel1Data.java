package org.uic.barcode.dynamicFrame.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.uic.barcode.asn1.uper.UperEncoder;

/**
 * The Class SignedDataType.
 */

public class SimpleLevel1Data implements ILevel1Data {
	
	/** The security provider */
	public String securityProvider;

	
	/** The key id. */
    public Long	keyId;
	
	
	/** The data. */
	public Collection<IData> dataList;
	
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
	public String level1KeyAlg;		
		
	/** The level 2 key alg. */
	public String level2KeyAlg;		
	
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
	public String level1SigningAlg;	
	
	/** The level 2 signing alg. */
	public String level2SigningAlg;		
	
	
	/**  The level 2 public key. */
	public byte[] level2publicKey;
	
	
	public Date endOfBarcodeValidity = null;
	
	
	
	
	
	/**
	 * Gets the security provider .
	 *
	 * @return the security provider
	 */
	public String getSecurityProvider() {
		return securityProvider;
	}

	/**
	 * Sets the security provider.
	 * 
	 * in case the security provider code is encoded in IA5 this will return null
	 *
	 * @param securityProviderNum the new security provider
	 */
	public void setSecurityProvider(String securityProvider) {
		this.securityProvider = securityProvider;
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
	public Collection<IData> getData() {
		return dataList;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Collection<IData> data) {
		this.dataList = data;
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
	public byte[] getLevel2publicKey() {
		return level2publicKey;
	}

	/**
	 * Sets the level 2 public key.
	 *
	 * @param level2publicKey the new level 2 public key
	 */
	public void setLevel2publicKey(byte[] level2publicKey) {
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
	 * Sets the end of validity date. The validity date has to be provided in UTC.
	 *
	 * @param date the new end of validity date
	 */
	public void setEndOfBarcodeValidity(Date date){
		
		endOfBarcodeValidity = date;
		
		
	}
	
	/**
	 * Gets the end of validity date.
	 *
	 * @return the end of validity date
	 */
	public Date getEndOfBarcodeValidity() {
		
		return endOfBarcodeValidity;
	}

	@Override
	public void addData(IData data) {
		
		if (dataList == null) {
			dataList = new ArrayList<IData>();
		}
		
		dataList.add(data);
		
	}
}
