/*
 * 
 */
package org.uic.ticket.api.spec;

import org.uic.ticket.api.EncodingFormatException;
import org.uic.ticket.api.asn.omv1.BerthTypeType;
import org.uic.ticket.api.asn.omv1.CompartmentGenderType;


/**
 * The Interface IBerth to specify the number and type of reserved berths in a reservation.
 */
public interface IBerth {
	
	/**
	 * Gets the type of the berth (Single, Special, Double,..).
	 *   -- sleeper compartment types corresponding to UIC leaflet 918.1 
	 *
	 * @return the type
	 */
	public BerthTypeType getType() ;
	
	/**
	 * Sets the type of the Berth.
	 *   -- sleeper compartment types corresponding to UIC leaflet 918.1 	 
	 *
	 * @param type the new berth type
	 */
	public void setType(BerthTypeType type);
	
	/**
	 * Gets the number of berths.
	 *
	 * @return the number of berths reserved for the specified type of berth.
	 */
	public int getNumberOfBerths();
	
	/**
	 * Sets the number of berths reserved for the specified type of berth. The largest number allowed is 200.
	 *
	 * @param numberOfBerths the new number of berths
	 * @throws EncodingFormatException 
	 */
	public void setNumberOfBerths(int numberOfBerths);
	
	/**
	 * Gets the gender information of the compartment reserved (family, male, female, mixed,..).
	 *
	 * @return the gender
	 */
	public CompartmentGenderType getGender();
	
	/**
	 * Sets the gender information of the compartment reserved (family, male, female, mixed,..).
	 *
	 * @param gender the new gender
	 */
	public void setGender(CompartmentGenderType gender) ;

}
