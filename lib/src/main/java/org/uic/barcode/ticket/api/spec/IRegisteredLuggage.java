/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

/**
 * The Interface IRegisteredLuggage.
 * 
 * IRegisteredLuggage provides a description for a piece of luggage registered for traveling and 
 * thus exceeding the general restrictions for luggage transport.
 * 
 */
public interface IRegisteredLuggage {
	
	/**
	 * Gets the registration id.
	 *
	 * @return the registration id
	 */
	public String getRegistrationId();
	
	/**
	 * Sets the registration id.
	 *
	 * @param registrationId the new registration id
	 */
	public void setRegistrationId(String registrationId);
	
	/**
	 * Gets the max weight of the luggage piece in kg.
	 *
	 * @return the max weight
	 */
	public int getMaxWeight();
	
	/**
	 * Sets the max weight of the luggage piece in kg.
	 *
	 * @param maxWeight the new max weight
	 */
	public void setMaxWeight(int maxWeight);
	
	/**
	 * Gets the max size as sum of length with and height in cm
	 *
	 * @return the max size
	 */
	public int getMaxSize() ;
	
	/**
	 * Sets  the max size as sum of length with and height in cm.
	 *
	 * @param maxSize the new max size
	 */
	public void setMaxSize(int maxSize);
}
