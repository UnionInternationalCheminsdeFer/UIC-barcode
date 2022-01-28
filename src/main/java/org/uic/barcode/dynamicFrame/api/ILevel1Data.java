package org.uic.barcode.dynamicFrame.api;

import java.util.Collection;
import java.util.Date;

/**
 * The Class SignedDataType.
 */
public interface ILevel1Data {
	


	/**
	 * Sets the security provider .
	 *
	 * @param securityProvider the new security provider
	 */
	public void setSecurityProvider(String securityProvider);
	

	/**
	 * Gets the security provider.
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
	 * Gets the level 2 key algorithm OID.
	 *
	 * @return the level 2 key alg
	 */
	public String getLevel2KeyAlg();
	

	/**
	 * Sets the level 2 key algorithm OID.
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
	 * Sets the level 1 signing algorithm OID.
	 *
	 * @param level1SigningAlg the new level 1 signing alg
	 */
	public void setLevel1SigningAlg(String level1SigningAlg);
	

	/**
	 * Gets the level 2 signing algorithm OID.
	 *
	 * @return the level 2 signing alg
	 */
	public String getLevel2SigningAlg();
	

	/**
	 * Sets the level 2 signing algorithm OID.
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
	 * Gets the level 1 key algorithm OID.
	 *
	 * @return the level 1 key alg
	 */
	public String getLevel1KeyAlg();
	
	/**
	 * Sets the level 1 key algorithm OID.
	 *
	 * @param level1KeyAlg the new level 1 key alg
	 */
	public void setLevel1KeyAlg(String level1KeyAlg);
	
	
	/**
	 * Sets the end of validity date. The validity date has to be provided in UTC.
	 * 
	 * 	 -- end of the validity of the bar code, after this date and time the bar code needs to be regenerated 
     *   -- by the provider of the ticket
     *   -- if end of validity is provided year day and time must be provided.
     *   -- year, day, time are in UTC
     *   -- the provider of the bar code should ensure that the endOfValidity given here does not exceed 
     *   --     the validity of the key pair used on level 2.
	 *
	 * @param date the new end of validity date
	 */
	public void setEndOfBarcodeValidity(Date date);
	
	
	/**
	 * Gets the end of validity date and time.
	 * 
	 *  -- end of the validity of the bar code, after this date and time the bar code needs to be regenerated 
     *   -- by the provider of the ticket
     *   -- if end of validity is provided year day and time must be provided.
     *   -- year, day, time are in UTC
     *   -- the provider of the bar code should ensure that the endOfValidity given here does not exceed 
     *   --     the validity of the key pair used on level 2.
	 *
	 * @return the end of validity date
	 */
	public Date getEndOfBarcodeValidity();
	
	
	/**
	 * Gets the validity duration of the bar code in seconds.
	 * 
	 *  -- validity duration in seconds of the bar code shown with reference to the time stamp  dynamicContentTimeStamp 
   	 *  --          in the dynamic data included in the level2Data
	 *
	 * @return the validity duration
	 */
	public Long getValidityDuration();

	
	/**
	 * Sets the validity validity duration of the bar code in seconds.
	 * 
	 *  -- validity duration in seconds of the bar code shown with reference to the time stamp  dynamicContentTimeStamp 
   	 *  --          in the dynamic data included in the level2Data
	 *
	 * @param validityDuration the new validity duration
	 */
	public void setValidityDuration(Long validityDuration);
	
	
}
