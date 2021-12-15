package org.uic.barcode.dynamicContent.api;

import java.util.Date;
import java.util.List;

import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IGeoCoordinate;

// TODO: Auto-generated Javadoc
/**
 * The Interface IUicDynamicContent.
 */
public interface IUicDynamicContent {
	
	/**
	 * Gets the app id.
	 *
	 * @return the app id
	 */
	public String getAppId();
	
	/**
	 * Sets the app id.
	 * @param string 
	 *
	 * @return the string
	 */
	public void setAppId(String string);
	
	/**
	 * Gets the time stamp.
	 *
	 * @return the time stamp
	 */
	public Date getTimeStamp();
		
	/**
	 * Sets the time stamp.
	 *
	 * @param date the new time stamp
	 */
	public void setTimeStamp(Date date);
	
	/**
	 * Gets the geo coordinate.
	 *
	 * @return the geo coordinate
	 */
	public IGeoCoordinate getGeoCoordinate(); 
	
	/**
	 * Sets the geo coordinate.
	 *
	 * @param geoCoordinate the new geo coordinate
	 */
	public void setGeoCoordinate(IGeoCoordinate geoCoordinate); 
	
	
	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public IExtension getExtension();
	
	/**
	 * Sets the extension.
	 *
	 * @param extension the new extension
	 */
	public void setExtension(IExtension extension);
	
	/**
	 * Gets the challenge string.
	 *
	 * @return the challenge string
	 */
	public String getChallengeString();
	
	/**
	 * Sets the challenge string.
	 *
	 * @param challenge the new challenge string
	 */
	public void setChallengeString(String challenge);
	
	/**
	 * Gets the phone id hash.
	 *
	 * @return the phone id hash
	 */
	public byte[] getPhoneIdHash();
	
	/**
	 * Sets the phone id hash.
	 *
	 * @param phoneIdHash the new phone id hash
	 */
	public void setPhoneIdHash(byte[] phoneIdHash);

	/**
	 * Gets the pass id hash.
	 *
	 * @return the pass id hash
	 */
	public byte[] getPassIdHash();

	/**
	 * Sets the pass id hash.
	 *
	 * @param passIdHash the new pass id hash
	 */
	public void setPassIdHash(byte[] passIdHash);
	
	/**
	 * Gets the dynamic content response list.
	 *
	 * @return the dynamic content response list
	 */
	public List<IExtension> getDynamicContentResponseList();
		
	/**
	 * Adds the dynamic content response.
	 *
	 * @param challenge the challenge
	 */
	public void addDynamicContentResponse(IExtension challenge);
	
	
}
