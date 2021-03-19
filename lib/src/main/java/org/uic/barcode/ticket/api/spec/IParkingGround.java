/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;



/**
 * The Interface IParkingGround.
 * 
 * IParkingGround provides the data for a prking ground reservation
 */
public interface IParkingGround  extends IDocumentData {
	
	/**
	 * Gets the parking ground id.
	 *
	 * @return the parking ground id
	 */
	public String getParkingGroundId();

	/**
	 * Sets the id of the parking ground
	 *
	 * @param parkingGroundId the new parking ground id
	 */
	public void setParkingGroundId(String parkingGroundId);

	/**
	 * Gets the from parking date.
	 *
	 * @return the from parking date
	 */
	public Date getFromParkingDate();

	/**
	 * Sets the from parking date.
	 *
	 * @param parkingDate the new from parking date
	 */
	public void setFromParkingDate(Date parkingDate);

	/**
	 * Gets the to parking date.
	 *
	 * @return the to parking date
	 */
	public Date getToParkingDate();

	/**
	 * Sets the to parking date.
	 *
	 * @param parkingDate the new to parking date
	 */
	public void setToParkingDate(Date parkingDate);	

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
	 * Gets the access code to enter the parking ground.
	 *
	 * @return the access code to enter the parking ground
	 */
	public String getAccessCode();

	/**
	 * Sets the access code to enter the parking ground.
	 *
	 * @param accessCode the new access code to enter the parking ground
	 */
	public void setAccessCode(String accessCode);

	/**
	 * Gets the human readable location name of the parking ground.
	 *
	 * @return the human readable location name of the parking ground.
	 */
	public String getLocation();

	/**
	 * Sets the human readable location name of the parking ground.
	 *
	 * @param location the new human readable location name of the parking ground.
	 */
	public void setLocation(String location);

	/**
	 * Gets the station code in case the parking ground is associated with a station 
	 *
	 * @return the station code in case the parking ground is associated with a station 
	 */
	public String getStation();

	/**
	 * Sets the station code in case the parking ground is associated with a station 
	 *
	 * @param station the new station code in case the parking ground is associated with a station 
	 */
	public void setStation(String station);

	/**
	 * Gets the special information.
	 *
	 * @return the special information
	 */
	public String getSpecialInformation();

	/**
	 * Sets the special information.
	 *
	 * @param specialInformation the new special information
	 */
	public void setSpecialInformation(String specialInformation);

	/**
	 * Gets the entry track to enter the parking ground.
	 *
	 * @return the entry track to enter the parking ground.
	 */
	public String getEntryTrack();

	/**
	 * Sets the entry track to enter the parking ground..
	 *
	 * @param entryTrack the new entry track to enter the parking ground.
	 */
	public void setEntryTrack(String entryTrack);

	/**
	 * Gets the number plate.
	 *
	 * @return the number plate
	 */
	public String getNumberPlate();

	/**
	 * Sets the number plate.
	 *
	 * @param numberPlate the new number plate
	 */
	public void setNumberPlate(String numberPlate);

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
	 * Gets the station code table.
	 *
	 * @return the station code table
	 */
	public  IStationCodeTable getStationCodeTable();

	/**
	 * Sets the station code table.
	 *
	 * @param stationCodeTable the new station code table
	 */
	public void setStationCodeTable( IStationCodeTable stationCodeTable);

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
	 * Gets the product type.
	 *
	 * @return the product type
	 */
	public String getProductId();
	
	/**
	 * Sets the product type.
	 *
	 * @param id the new product id
	 */
	public void setProductId(String type);	
	
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
	
}
