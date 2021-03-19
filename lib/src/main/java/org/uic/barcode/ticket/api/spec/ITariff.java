/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;

/**
 * The Interface ITariff.
 * 
 * ITariff describes the tariff of an open ticket, reservation or  or IRT
 * 
 * 
 */
public interface ITariff {

	/**
	 * Gets the number of passengers associated with that tariff.
	 *
	 * @return the number of passengers associated with that tariff
	 */
	public int getNumberOfPassengers();


	/**
	 * Sets the number of passengers associated with that tariff.
	 *
	 * @param numberOfPassengers the new number of passengers associated with that tariff
	 */
	public void setNumberOfPassengers(int numberOfPassengers);


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
	 * Gets the upper limit of the age of the passenger to qualify for that tariff.
	 *
	 * @return the upper age limit
	 */
	public int getAgeBelow();

	/**
	 * Sets the upper limit of the age of the passenger to qualify for that tariff.
	 *
	 * @param ageBelow the new upper limit of the age of the passenger to qualify for that tariff
	 */
	public void setAgeBelow(int ageBelow);


	/**
	 * Gets the lower limit of the age of the passenger to qualify for that tariff.
	 *
	 * @return  the lower limit of the age of the passenger to qualify for that tariff
	 */
	public int getAgeAbove();


	/**
	 * Sets the lower limit of the age of the passenger to qualify for that tariff.
	 *
	 * @param ageAbove  the new lower limit of the age of the passenger to qualify for that tariff
	 */
	public void setAgeAbove(int ageAbove);


	/**
	 * Gets the traveler ids.
	 *
	 *  This provides a link to the travelers listed in the data in case the travelers have to be named (e.g. Eurail passes)
	 *  -- 	 the number indicates the position in the traveler list starting from 1 		 
	 *
	 * @return the traveler ids
	 */
	public Collection<Integer> getTravelerIds() ;


	/**
	 * Adds the traveler id.
	 *
	 *  This provides a link to the travelers listed in the data in case the travelers have to be named (e.g. Eurail passes)
	 *  -- 	 the number indicates the position in the traveler list starting from 1 	
	 *  	 
	 * @param travelerId the traveler id
	 */
	public void addTravelerId(Integer travelerId);

	/**
	 * Checks if this tariff is restricted to the country of residence given in the traveler data.
	 *
	 * @return true, if is restricted to country of residence
	 */
	public boolean isRestrictedToCountryOfResidence();



	/**
	 * Sets if this tariff is restricted to the country of residence given in the traveler data.
	 *
	 * @param restrictedToCountryOfResidence the new restricted to country of residence
	 */
	public void setRestrictedToCountryOfResidence(boolean restrictedToCountryOfResidence);


	/**
	 * Gets the restricted to route section.
	 * 
	 * This limits the tariff to a route section.
	 * This could be used in case the age restrictions vary along the route.
	 * 
	 * @return the restricted to route section
	 */
	public IRouteSection getRestrictedToRouteSection();


	/**
	 * Sets the restricted to route section.
	 * 
	 * This limits the tariff to a route section.
	 * This could be used in case the age restrictions vary along the route.	 
	 *
	 * @param restrictedToRouteSection the new restricted to route section
	 */
	public void setRestrictedToRouteSection(IRouteSection restrictedToRouteSection);


    /**
     * Sets the series data details.
     * 
     * Add series details in case the tariff is based on data from UIC leaflet 108.1.     
     *
     * @param seriesDataDetails the new series data details
     */
    public void setSeriesDataDetails(ISeriesDataDetails seriesDataDetails);
	

    /**
     * Gets the series data details.
     *
     * Add series details in case the tariff is based on data from UIC leaflet 108.1.   
     *      
     * @return the series data details
     */
    public ISeriesDataDetails getSeriesDataDetails();

	/**
	 * Gets the tariff code.
	 *
	 * @return the tariff code
	 */
	public String getTariffId() ;


	/**
	 * Sets the tariff code.
	 *
	 * @param tariffId the new tariff code
	 */
	public void setTariffId(String tariffId);


	/**
	 * Gets the human readable tariff description.
	 *
	 * @return the human readable tariff description
	 */
	public String getTariffDescription();


	/**
	 * Sets the human readable tariff description.
	 *
	 * @param tariffDescription the new human readable tariff description
	 */
	public void setTariffDescription(String tariffDescription);

	/**
	 * Gets the list of reduction cards required to qualify for that tariff.
	 * 
	 *
	 * @return the reduction cards required to qualify for that tariff
	 */
	public Collection<ICardReference> getReductionCards();


	/**
	 * Adds a reduction card required to qualify for that tariff.
	 *
	 * @param reductionCard the reduction card
	 */
	public void addReductionCard(ICardReference reductionCard);
}
