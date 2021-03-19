/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;


/**
 * The Interface IStationPassage.
 * 
 * IStationPassage describes a ticket to enter or pass a station or platform.
 * 
 */
public interface IStationPassage  extends IDocumentData {
	
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
	 * Gets the product type.
	 *
	 * @return the product type
	 */
	public String getProductId();
	
	/**
	 * Sets the product type.
	 *
	 * @param type the new product id
	 */
	public void setProductId(String type);

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
	public Date getValidUntil() ;

	/**
	 * Sets the valid until date and time.
	 *
	 * @param validUntil the new valid until date and time
	 */
	public void setValidUntil(Date validUntil);

	/**
	 * Gets the station code table.
	 *
	 * @return the station code table
	 */
	public IStationCodeTable getStationCodeTable();

	/**
	 * Sets the station code table.
	 *
	 * @param stationCodeTable the new station code table
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable);

	/**
	 * Gets the list of station codes of all stations where the passage is allowed.
	 *
	 * @return the stations list
	 */
	public Collection<String> getStations();

	/**
	 * Adds a station where the passage is allowed.
	 *
	 * @param station the station code
	 */
	public void addStation(String station);

	/**
	 * Adds a station name of a station where the passage is allowed.
	 *
	 * @param name the name
	 */
	public void addStationName(String name);
	
	/**
	 * Gets the station names of all stations where the passage is allowed.
	 *
	 * @return the station names
	 */
	public Collection<String> getStationNames();

	/**
	 * Gets the number of days allowed the passage is allowed in case the number is smaller that the validity range.
	 *
	 * @return the number of passage days allowed
	 */
	public int getNumberOfdaysAllowed();

	/**
	 * Sets the number of days allowed the passage is allowed in case the number is smaller that the validity range.
	 *
	 * @param numberOfdaysAllowed the new number of allowed days
	 */
	public void setNumberOfdaysAllowed(int numberOfdaysAllowed);
	
	
	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getProductName();

	/**
	 * Sets the product name.
	 *
	 * @param name the new product name
	 */
	public void setProductName(String name);
	

	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public IExtension getExtension();

	/**
	 * Sets the extension.
	 *
	 * @param extensionData the new extension
	 */
	public void setExtension(IExtension extensionData);	

	
	/**
	 * Gets the area codes of areas in a station where the access is allowed.
	 *
	 * @return the area codes of areas in a station where the access is allowed
	 */
	public Collection<String> getAreaCodes();
	
	/**
	 * Gets the area names of areas in a station where the access is allowed.
	 *
	 * @return the area names of areas in a station where the access is allowed
	 */
	public Collection<String> getAreaNames();
	
	/**
	 * Adds an area code of an area in a station where the access is allowed.
	 *
	 * @param code the area code of an area in a station where the access is allowed.
	 */
	public void addAreaCode(String code);
	
	/**
	 * Adds an area name of an area in a station where the access is allowed.
	 *
	 * @param name the name of an area in a station where the access is allowed
	 */
	public void addAreaName(String name);	
	
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
