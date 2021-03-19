/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;


/**
 * The Interface IIncludedOpenTicket.
 * 
 * IIncludedOpenTicket provides the description of an add on ticket to be added to an open ticket. 
 * E.g. a local transport ticket at the end or begin of the route.
 * 
 */
public interface IIncludedOpenTicket {
	
	/**
	 * Gets the departure date.
	 *
	 * @return the departure date
	 */
	public Date getDepartureDate();

	/**
	 * Sets the departure date.
	 *
	 * @param departureDate the new departure date
	 */
	public void setDepartureDate(Date departureDate);

	/**
	 * Gets the arrival date.
	 *
	 * @return the arrival date
	 */
	public Date getArrivalDate() ;

	/**
	 * Sets the arrival date.
	 *
	 * @param arrivalDate the new arrival date
	 */
	public void setArrivalDate(Date arrivalDate);


	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public String getProductId() ;

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(String productId) ;

	/**
	 * Gets the product owner.
	 *
	 * @return the product owner
	 */
	public String getProductOwner();

	/**
	 * Sets the product owner.
	 *
	 * @param productOwner the new product owner
	 */
	public void setProductOwner(String productOwner);

	/**
	 * Gets the external issuer.
	 *
	 * @return the external issuer
	 */
	public int getExternalIssuer();
	
	/**
	 * Sets the external issuer.
	 *
	 * Identification of the issuer provided by an external carrier
	 * 
	 * @param externalIssuer the new external issuer
	 */
	public void setExternalIssuer(int externalIssuer);
	
	/**
	 * Gets the authorization code.
	 *
	 * Authorization code given by a carrier to the product owner to allow the sale
	 *
	 * @return the authorization code
	 */
	public int getAuthorizationCode();
	
	/**
	 * Sets the authorization code.
	 *
	 * An authorization code given by a carrier to the product owner to allow the sale	 
	 *
	 * @param authorizationCode the new authorization code
	 */
	public void setAuthorizationCode(int authorizationCode);

	/**
	 * Gets the station code table.
	 *
	 * Defines the station code table to be used to retrieve station information. 
	 * Default in this case is the UIC station codes table for standard UIC 
	 * station code from MERITS (UIC country code + 5 digit local code) 
	 * 
	 * @return the station code table
	 */
	public IStationCodeTable getStationCodeTable();

	/**
	 * Sets the station code table.
	 *
	 * Defines the station code table to be used to retrieve station information. 
	 * Default in this case is the UIC station codes table for standard UIC 
	 * station code from MERITS (UIC country code + 5 digit local code) 
	 * 	
	 * @param stationCodeTable the new station code table
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable);

	/**
	 * Gets the from station code.
	 *
	 * @return the from station code
	 */
	public String getFromStation();

	/**
	 * Sets the from station code.
	 *
	 * @param fromStation the new from station code
	 */
	public void setFromStation(String fromStation);

	/**
	 * Gets the to station code.
	 *
	 * @return the to station code
	 */
	public String getToStation();

	/**
	 * Sets the to station code.
	 *
	 * @param toStation the new to station code
	 */
	public void setToStation(String toStation);

	/**
	 * Gets the from station name.
	 *
	 * @return the from station name
	 */
	public String getFromStationName();

	/**
	 * Sets the from station name.
	 *
	 * @param fromStationName the new from station name
	 */
	public void setFromStationName(String fromStationName);

	/**
	 * Gets the to station name.
	 *
	 * @return the to station name
	 */
	public String getToStationName();

	/**
	 * Sets the to station name.
	 *
	 * @param toStationName the new to station name
	 */
	public void setToStationName(String toStationName);

	/**
	 * Gets the valid region description.
	 *
	 * @return the valid region desc
	 */
	public String getValidRegionDesc();

	/**
	 * Sets the valid region description.
	 *
	 * @param validRegionDesc the new valid region description
	 */
	public void setValidRegionDesc(String validRegionDesc);

	/**
	 * Gets the list of valid regions.
	 *
	 * @return the list of valid regions
	 */
	public Collection<IRegionalValidity> getValidRegionList();

	/**
	 * Adds a valid region.
	 *
	 * @param validRegion the valid region
	 */
	public void addValidRegionList(IRegionalValidity validRegion);

	/**
	 * Gets the valid from date and time.
	 *
	 * @return the valid from date and time.
	 */
	public Date getValidFrom();

	/**
	 * Sets the valid from date and time.
	 *
	 * @param validFrom the new valid from date and time.
	 */
	public void setValidFrom(Date validFrom);

	/**
	 * Gets the valid until date and time.
	 *
	 * @return the valid until date and time.
	 */
	public Date getValidUntil();

	/**
	 * Sets the valid until date and time.
	 *
	 * @param validUntil the new valid until date and time.
	 */
	public void setValidUntil(Date validUntil);

	/**
	 * Gets the travel class code.
	 *
	 * @return the travel class code
	 */
	public ITravelClassType getClassCode();

	/**
	 * Sets the travel class code.
	 *
	 * @param classCode the new travel class code
	 */
	public void setClassCode(ITravelClassType classCode);

	/**
	 * Gets the carriers included in the transport contract.
	 *
	 * @return the included carriers
	 */
	public Collection<String> getIncludedCarriers();

	/**
	 * Adds the included carrier.
	 *
	 * @param includedCarrier the included carrier
	 */
	public void addIncludedCarrier(String includedCarrier);

	/**
	 * Gets the included service brands on which the ticket is valid
	 *
	 * The list should be omitted in case all service brands are 
	 * included or a list of excluded service brands is provided.
	 *
	 * @return the included service brands
	 */
	public Collection<Integer> getIncludedServiceBrands();

	/**
	 * Adds an included service brand.
	 *
	 * The list should be omitted in case all service brands are 
	 * included or a list of excluded service brands is provided.
	 * 
	 * @param includedServiceBrand the included service brand
	 */
	public void addIncludedServiceBrand(Integer includedServiceBrand);

	/**
	 * Gets the list of excluded service brands.
	 *
	 * The list should be omitted in case a complete 
	 * list of included service brands is provided.	 
	 *
	 * @return the list of excluded service brands
	 */
	public Collection<Integer> getExcludedServiceBrands();

	/**
	 * Adds an excluded service brand.
	 *
	 * The list should be omitted in case a complete 
	 * list of included service brands is provided.	 
	 * 
	 * @param excludedServiceBrand the excluded service brand
	 */
	public void addExcludedServiceBrand(Integer excludedServiceBrand);
	
	
	/**
	 * Gets the list of excluded Transport Types.
	 *
	 * The list should be omitted in case a complete 
	 * list of included transport types is provided.	 
	 *
	 * @return the list of excluded transport types
	 */
	public Collection<Integer> getExcludedTransportTypes();

	/**
	 * Adds an excluded service brand.
	 *
	 * The list should be omitted in case a complete 
	 * list of included TransportTypes is provided.	 
	 * 
	 * @param excluded TransportType the excluded TransportTypes
	 */
	public void addExcludedTransportType(Integer excludedTransportType);


	/**
	 * Gets the list of excluded Transport Types.
	 *
	 * The list should be omitted in case a complete 
	 * list of included transport types is provided.	 
	 *
	 * @return the list of included transport types
	 */
	public Collection<Integer> getIncludedTransportTypes();

	/**
	 * Adds an excluded service brand.
	 *
	 * The list should be omitted in case a complete 
	 * list of included TransportTypes is provided.	 
	 * 
	 * @param excluded TransportType the excluded TransportTypes
	 */
	public void addInludedTransportType(Integer includedTransportType);

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
	public IExtension getExtension() ;

	/**
	 * Sets the extension.
	 *
	 * @param extension the new extension
	 */
	public void setExtension(IExtension extension);
	
	
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
