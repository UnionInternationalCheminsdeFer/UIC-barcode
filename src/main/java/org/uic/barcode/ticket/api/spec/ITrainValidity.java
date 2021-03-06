package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;

/**
 * The Interface IValidityRange.
 * 
 * IValidityRange describes a validity range by from / until date and time
 */
public interface ITrainValidity {
	
	
	/**
	 * Gets the from date and time.
	 *
	 * @return the from date and time
	 */
	public Date getFromDate();
	
	/**
	 * Gets the until date and time.
	 *
	 * @return the until date and time
	 */
	public Date getUntilDate();

	
	/**
	 * Sets the from date and time.
	 *
	 * @param date the new from date and time
	 */
	public void setFromDate(Date date);
	
	/**
	 * Sets the until date and time.
	 *
	 * @param date the new until date and time
	 */
	public void setUntilDate(Date date);	
	
	
	/**
	 * Gets the validFrom date time offset to UTC in units of 15 minutes.
	 *
	 * @return the validFrom date time UTC offset
	 */
	public Long getValidFromUTCoffset();
	
	/**
	 * Sets the validFrom date time.
	 *
	 * @param validFromDateTime the new validFrom date time
	 */
	public void setValidFromUTCoffset(Long validFromUTCoffset) ;
	
	/**
	 * Gets the validUntil date time offset to UTC in units of 15 minutes.
	 *
	 * @return the validUntil date time UTC offset
	 */
	public Long getValidUntilUTCoffset();
	
	/**
	 * Sets the validUntil date time.
	 *
	 * @param validUntilDateTime the new validUntil date time
	 */
	public void setValidUntilUTCoffset(Long validUntilUTCoffset) ;
	
	
	/**
	 * Gets the included carriers.
	 *
	 * @return the included carriers
	 */
	public Collection<String> getIncludedCarriers();

	/**
	 * Adds the included carrier.
	 *
	 * @param carrier the carrier
	 */
	public void addIncludedCarrier(String carrier);

	/**
	 * Gets the excluded carriers.
	 *
	 * @return the excluded carriers
	 */
	public Collection<String> getExcludedCarriers();

	/**
	 * Adds the excluded carrier.
	 *
	 * @param carrier the carrier
	 */
	public void addExcludedCarrier(String carrier);	
	
	/**
	 * Gets the included service brands.
	 *
	 * @return the included service brands
	 */
	public Collection<Integer> getIncludedServiceBrands();

	/**
	 * Adds the included service brand.
	 *
	 * @param includedServiceBrand the included service brand
	 */
	public void addIncludedServiceBrand(Integer includedServiceBrand);

	/**
	 * Gets the excluded service brands.
	 *
	 * @return the excluded service brands
	 */
	public Collection<Integer> getExcludedServiceBrands();

	/**
	 * Adds the excluded service brand.
	 *
	 * @param excludedServiceBrand the excluded service brand
	 */
	public void addExcludedServiceBrand(Integer excludedServiceBrand);
	
	
	/**
	 * Gets the class code.
	 *
	 * @return the class code
	 */
	public IBoardingOrArrivalType getBoardingOrArrival();

	/**
	 * Sets the travel class code.
	 *
	 * @param classCode the new travel class code
	 */
	public void setBoardingOrArrival(IBoardingOrArrivalType boardingOrArrival);


	
}
