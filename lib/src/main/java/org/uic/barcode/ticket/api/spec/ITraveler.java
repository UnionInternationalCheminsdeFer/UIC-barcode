/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;


/**
 * The Interface ITravelerData.
 * 
 * ITravelerData specifies the details of a traveler.
 * 
 */
public interface ITraveler {
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName();
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName);
	
	/**
	 * Gets the second name.
	 *
	 * @return the second name
	 */
	public String getSecondName() ;
	
	/**
	 * Sets the second name.
	 *
	 * @param secondName the new second name
	 */
	public void setSecondName(String secondName);
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName();
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName);
	
	/**
	 * Gets the id card.
	 *
	 * @return the id card
	 */
	public String getIdCard();
	
	/**
	 * Sets the id card.
	 *
	 * @param idCard the new id card
	 */
	public void setIdCard(String idCard) ;
	
	/**
	 * Gets the passport id.
	 *
	 * @return the passport id
	 */
	public String getPassportId() ;
	
	/**
	 * Sets the passport id.
	 *
	 * @param passportId the new passport id
	 */
	public void setPassportId(String passportId);
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle();
	
	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title);
	
	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public IGenderType getGender();

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(IGenderType gender);
	
	/**
	 * Gets the customer id.
	 *
	 * @return the customer id
	 */
	public String getCustomerId();
	
	/**
	 * Sets the customer id.
	 *
	 * @param customerId the new customer id
	 */
	public void setCustomerId(String customerId);
	
	/**
	 * Gets the date of birth.
	 *
	 * @return the date of birth
	 */
	public Date getDateOfBirth();
	
	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the new date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth);

	/**
	 * Checks if is ticket holder.
	 *
	 * @return true, if is ticket holder
	 */
	public boolean isTicketHolder();

	/**
	 * Sets the ticket holder.
	 *
	 * @param ticketHolder the new ticket holder
	 */
	public void setTicketHolder(boolean ticketHolder);

	/**
	 * Gets the passenger type.
	 *
	 * @return the passenger type
	 */
	public IPassengerType getPassengerType();

	/**
	 * Sets the passenger type.
	 *
	 * @param passengerType the new passenger type
	 */
	public void setPassengerType(IPassengerType passengerType);

	/**
	 * Checks if is passenger with reduced mobility.
	 *
	 * @return true, if is passenger with reduced mobility
	 */
	public Boolean isPassengerWithReducedMobility();

	/**
	 * Sets the passenger with reduced mobility.
	 *
	 * @param passengerWithReducedMobility the new passenger with reduced mobility
	 */
	public void setPassengerWithReducedMobility(Boolean passengerWithReducedMobility);

	/**
	 * Gets the country of residence (numeric ISO country code) .
	 *
	 * @return the country of residence (numeric ISO country code) 
	 */
	public int getCountryOfResidence();

	/**
	 * Sets the country of residence (numeric ISO country code) .
	 *
	 * @param countryOfResidence the new country of residence (numeric ISO country code) 
	 */
	public void setCountryOfResidence(int countryOfResidence);

	

	/**
	 * Gets the passport country (numeric ISO country code) .
	 *
	 * @return the passport country (numeric ISO country code) 
	 */
	public int getPassportCountry();


	/**
	 * Sets the passport country (numeric ISO country code) . 
	 *
	 * @param passportCountry the new passport country (numeric ISO country code) 
	 */
	public void setPassportCountry(int passportCountry);	

	
	/**
	 * Gets the ID card country (numeric ISO country code) .
	 *
	 * @return the ID card country (numeric ISO country code) 
	 */
	public int getIDCardCountry();


	/**
	 * Sets the ID card country (numeric ISO country code) .
	 *
	 * @param idcardCountry the new ID card country (numeric ISO country code) 
	 */
	public void setIDCardCountry(int idcardCountry);	
	
	/**
	 * Gets the status collection.
	 *
	 * @return the status collection
	 */
	public Collection<ICustomerStatusDescription> getStatusCollection();

	/**
	 * Adds the status description.
	 *
	 * @param statusDescription the status description
	 */
	public void addStatusDescription(ICustomerStatusDescription statusDescription) ;
	


	
}
