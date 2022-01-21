package org.uic.barcode.dynamicFrame.api;

import java.util.Collection;
import java.util.Date;

/**
 * The Class SignedDataType.
 */
public interface ILevel1Data {
	


	/**
	 * Sets the security provider 
	 *
	 * @param securityProviderNum the new security provider 
	 */
	public void setSecurityProvider(String securityProvider);
	

	/**
	 * Gets the security provider
	 *
	 * @return the security provider
	 */
	public String getSecurityProvider();
	
	

	

	
	/**
	 * Gets the key id.
	 *
	 * @return the key id
	 */
	public Long getKeyId();
	

	/**
	 * Sets the key id.
	 *
	 * @param keyId the new key id
	 */
	public void setKeyId(Long keyId);

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Collection<IData> getData();
	

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Collection<IData> data);
	
	/**
	 * Adds data.
	 *
	 * @param data the new data
	 */
	public void addData(IData data);

	/**
	 * Gets the level 2 key alg.
	 *
	 * @return the level 2 key alg
	 */
	public String getLevel2KeyAlg();
	

	/**
	 * Sets the level 2 key alg.
	 *
	 * @param level2KeyAlg the new level 2 key alg
	 */
	public void setLevel2KeyAlg(String level2KeyAlg);
	

	/**
	 * Gets the level 1 signing alg.
	 *
	 * @return the level 1 signing alg
	 */
	public String getLevel1SigningAlg();
	

	/**
	 * Sets the level 1 signing alg.
	 *
	 * @param level1SigningAlg the new level 1 signing alg
	 */
	public void setLevel1SigningAlg(String level1SigningAlg);
	

	/**
	 * Gets the level 2 signing alg.
	 *
	 * @return the level 2 signing alg
	 */
	public String getLevel2SigningAlg();
	

	/**
	 * Sets the level 2 signing alg.
	 *
	 * @param level2SigningAlg the new level 2 signing alg
	 */
	public void setLevel2SigningAlg(String level2SigningAlg);
	

	/**
	 * Gets the level 2 public key.
	 *
	 * @return the level 2 public key
	 */
	public byte[] getLevel2publicKey();
	

	/**
	 * Sets the level 2 public key.
	 *
	 * @param level2publicKey the new level 2 public key
	 */
	public void setLevel2publicKey(byte[] level2publicKey);

	
	
	/**
	 * Gets the level 1 key alg.
	 *
	 * @return the level 1 key alg
	 */
	public String getLevel1KeyAlg();
	
	/**
	 * Sets the level 1 key alg.
	 *
	 * @param level1KeyAlg the new level 1 key alg
	 */
	public void setLevel1KeyAlg(String level1KeyAlg);
	
	
	/**
	 * Sets the end of validity date. The validity date has to be provided in UTC.
	 *
	 * @param date the new end of validity date
	 */
	public void setEndOfBarcodeValidity(Date date);
	
	
	/**
	 * Gets the end of validity date.
	 *
	 * @return the end of validity date
	 */
	public Date getEndOfBarcodeValidity();
}
