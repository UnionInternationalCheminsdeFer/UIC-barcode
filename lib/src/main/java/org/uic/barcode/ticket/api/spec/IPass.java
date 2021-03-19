/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;

/**
 * The Interface IPass.
 * 
 * IPass provides the data for a rail pass ticket.
 * 
 */
public interface IPass  extends IDocumentData {
	
	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference();

	/**
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference);

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public String getProductId();

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(String productId);

	/**
	 * Gets the product owner.
	 *
	 * Company that provides the product. 
	 * E.g. The RICS code of Eurail G.E.I. for Eurail passes.
	 *
	 * @return the product owner
	 */
	public String getProductOwner();

	/**
	 * Sets the product owner.
	 *
	 * Company that provides the product. 
	 * E.g. The RICS code of Eurail G.E.I. for Eurail passes.
	 * 	 
	 * @param productOwner the new product owner
	 */
	public void setProductOwner(String productOwner);

	/**
	 * Gets the pass type.
	 *
	 * @return the pass type
	 */
	public int getPassType();

	/**
	 * Sets the pass type.
	 *
	 *  -- type of the pass, code list provided by the product owner
	 *  	 
	 * @param passType the new pass type
	 */
	public void setPassType(int passType);

	/**
	 * Gets the human readable pass description.
	 *
	 * @return the human readable pass description.
	 */
	public String getPassDescription();

	/**
	 * Sets the human readable pass description.
	 *
	 * @param passDescription the new human readable pass description.
	 */
	public void setPassDescription(String passDescription);

	/**
	 * Gets the class code.
	 *
	 * @return the class code
	 */
	public ITravelClassType getClassCode();

	/**
	 * Sets the travel class code.
	 *
	 * @param classCode the new travel class code
	 */
	public void setClassCode(ITravelClassType classCode);

	/**
	 * Gets the valid from date and time.
	 *
	 * @return the valid from date and time
	 */
	public Date getValidFrom();

	/**
	 * Sets the valid from date and time.
	 *
	 * @param validFrom the new valid from date and time
	 */
	public void setValidFrom(Date validFrom);

	/**
	 * Gets the valid until date and time.
	 *
	 * @return the valid until date and time
	 */
	public Date getValidUntil();

	/**
	 * Sets the valid until date and time.
	 *
	 * @param validUntil the new valid until date and time
	 */
	public void setValidUntil(Date validUntil);

	/**
	 * Gets the number of validity days in case this is less than the entire validity range and the valid from day is open.
	 *
	 * @return the number of validity days in case this is less than the entire validity range.
	 */
	public int getNumberOfValidityDays();

	/**
	 * Sets the number of validity days in case this is less than the entire validity range and the valid from day is open.
	 *
	 * @param numberOfValidityDays the new number of validity days in case this is less than the entire validity range.
	 */
	public void setNumberOfValidityDays(int numberOfValidityDays);

	/**
	 * Gets the number of possible trips.
	 *
	 * @return the number of possible trips
	 */
	public int getNumberOfPossibleTrips();

	/**
	 * Sets the number of possible trips.
	 *
	 * @param numberOfPossibleTrips the new number of possible trips
	 */
	public void setNumberOfPossibleTrips(int numberOfPossibleTrips);

	/**
	 * Gets the number of days of travel in case this is less than the entire validity range.
	 *
	 * @return the number of days of travel in case this is less than the entire validity range
	 */
	public int getNumberOfDaysOfTravel();

	/**
	 * Sets the number of days of travel in case this is less than the entire validity range.
	 *
	 * @param numberOfDaysOfTravel the new number of days of travel in case this is less than the entire validity range
	 */
	public void setNumberOfDaysOfTravel(int numberOfDaysOfTravel) ;

	/**
	 * Gets the activated days valid for traveling.
	 *
	 * @return the activated days valid for traveling
	 */
	public Collection<Date> getActivatedDays() ;

	/**
	 * Adds an activated day valid for traveling.
	 *
	 * @param activatedDay the activated day valid for traveling
	 */
	public void addActivatedDay(Date activatedDay);

	/**
	 * Gets the countries where the pass is valid.
	 * 
	 * Code list according to the codes in UIC leaflet 918.2
	 *
	 * @return the countries
	 */
	public Collection<Integer> getCountries();

	/**
	 * Adds a country.
	 * 	 
	 * Code list according to the codes in UIC leaflet 918.2	 
	 *
	 * @param country the country
	 */
	public void addCountry(Integer country);

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
	 * Gets the list of valid regions.
	 *
	 * @return the valid region list
	 */
	public Collection<IRegionalValidity> getValidRegionList();

	/**
	 * Adds a valid region.
	 *
	 * @param validRegion the valid region to be added
	 */
	public void addValidRegion(IRegionalValidity validRegion);

	/**
	 * Gets the tariffs.
	 *
	 * @return the tariffs
	 */
	public Collection<ITariff> getTariffs();

	/**
	 * Adds the tariff.
	 *
	 * @param tariff the tariff
	 */
	public void addTariff(ITariff tariff);

	/**
	 * Gets the info text.
	 *
	 * @return the info text
	 */
	public String getInfoText();

	/**
	 * Sets the info text.
	 *
	 * @param infoText the new info text
	 */
	public void setInfoText(String infoText);

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
	 * Sets the validity details containing additional validity ranges and excluded time ranges.
	 *
	 * @param validityDetails the new validity details
	 */
	public void setValidityDetails(IValidityDetails validityDetails);
	

	/**
	 * Gets the validity details containing additional validity ranges and excluded time ranges.
	 *
	 * @return the validity details
	 */
	public IValidityDetails getValidityDetails();	
	
	/**
	 * Sets the price. 
	 *
	 *
	 * @param price
	 */
	public void setPrice(Long price);
	
	
	/**
	 * Gets the price.
	 *
	 * The price
	 *
	 * @return the price
	 */
	public Long getPrice();
	
	
	/**
	 * Gets the included add on tickets.
	 *	 
	 * E.g. an included local transport ticket at the beginning or end of the route.
	 * 	 
	 * @return the included add ons
	 */
	public Collection<IVatDetail> getVatDetails();

	/**
	 * Adds an included add on ticket.
	 * 
	 * E.g. an included local transport ticket at the beginning or end of the route.
	 *
	 * @param includedAddOn the included add on
	 */
	public void addVatDetail(IVatDetail vatDetail) ;
	
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
	
}
