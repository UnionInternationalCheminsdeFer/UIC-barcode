package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.IBoardingOrArrivalType;
import org.uic.barcode.ticket.api.spec.ITrainValidity;

/**
 * The Class SimpleTrainValidity.
 */
public class SimpleTrainValidity implements ITrainValidity {
	
	
	/** The valid from. */
	protected Date validFrom;
	
	/** The valid until. */
	protected Date  validUntil;	  

   	/** The included carriers. */
    protected Collection<String>includedCarriers = new LinkedHashSet<String>();	  

   	/** The excluded carriers. */
    protected Collection<String>excludedCarriers = new LinkedHashSet<String>();	 	    
	    
    /** The included service brands. */
    protected Collection<Integer>includedServiceBrands = new LinkedHashSet<Integer>();	
    	
    /** The excluded service brands. */
    protected Collection<Integer>excludedServiceBrands = new LinkedHashSet<Integer>();	 
    
	/** The class code. */
	protected IBoardingOrArrivalType boardingOrArrival = IBoardingOrArrivalType.boarding;      
	
	/** The valid from utc coffset. */
	protected Long validFromUTCoffset;

	/** The valid until utc coffset. */
	protected Long validUntilUTCoffset;

	
	/**
	 * Gets the included carriers.
	 *
	 * @return the included carriers
	 */
	public Collection<String> getIncludedCarriers() {
		return includedCarriers;
	}


	/**
	 * Adds the included carrier.
	 *
	 * @param carrier the carrier
	 */
	public void addIncludedCarrier(String carrier) {
		this.includedCarriers.add(carrier);
	}

	/**
	 * Gets the excluded carriers.
	 *
	 * @return the excluded carriers
	 */
	public Collection<String> getExcludedCarriers() {
		return excludedCarriers;
	}

	/**
	 * Adds the excluded carrier.
	 *
	 * @param carrier the carrier
	 */
	public void addExcludedCarrier(String carrier) {
		this.excludedCarriers.add(carrier);
	}		
	
	/**
	 * Gets the included service brands.
	 *
	 * @return the included service brands
	 */
	public Collection<Integer> getIncludedServiceBrands() {
		return includedServiceBrands;
	}

	/**
	 * Adds the included service brand.
	 *
	 * @param includedServiceBrand the included service brand
	 */
	public void addIncludedServiceBrand(Integer includedServiceBrand) {
		this.includedServiceBrands.add(includedServiceBrand);
	}

	/**
	 * Gets the excluded service brands.
	 *
	 * @return the excluded service brands
	 */
	public Collection<Integer> getExcludedServiceBrands() {
		return excludedServiceBrands;
	}

	/**
	 * Adds the excluded service brand.
	 *
	 * @param excludedServiceBrand the excluded service brand
	 */
	public void addExcludedServiceBrand(Integer excludedServiceBrand) {
		this.excludedServiceBrands.add(excludedServiceBrand);
	}
	
	public Date getFromDate() {
		return validFrom;
	}

	public void setFromDate(Date validFrom) {
		this.validFrom = validFrom;
	}

	

	
	
	/**
	 * Gets the valid from UT coffset.
	 *
	 * @return the valid from UT coffset
	 */
	public Long getValidFromUTCoffset() {
		return validFromUTCoffset;
	}

	/**
	 * Sets the valid from UT coffset.
	 *
	 * @param validFromUTCoffset the new valid from UT coffset
	 */
	public void setValidFromUTCoffset(Long validFromUTCoffset) {
		this.validFromUTCoffset = validFromUTCoffset;
	}

	/**
	 * Gets the valid until UT coffset.
	 *
	 * @return the valid until UT coffset
	 */
	public Long getValidUntilUTCoffset() {
		return validUntilUTCoffset;
	}

	/**
	 * Sets the valid until UT coffset.
	 *
	 * @param validUntilUTCoffset the new valid until UT coffset
	 */
	public void setValidUntilUTCoffset(Long validUntilUTCoffset) {
		this.validUntilUTCoffset = validUntilUTCoffset;
	}

	/**
	 * Sets the until date.
	 *
	 * @param date the new until date
	 */
	@Override
	public void setUntilDate(Date validUntil) {
		this.validUntil = validUntil;		
	}


	public Date getUntilDate() {
		return validUntil;
	}




	@Override
	public IBoardingOrArrivalType getBoardingOrArrival() {
		return this.boardingOrArrival;
	}


	@Override
	public void setBoardingOrArrival(IBoardingOrArrivalType boardingOrArrival) {
		this.boardingOrArrival = boardingOrArrival;		
	}
		
	
	

}
