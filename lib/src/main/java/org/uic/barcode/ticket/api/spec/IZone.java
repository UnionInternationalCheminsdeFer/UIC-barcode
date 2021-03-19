/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;

/**
 * The Interface IZone.
 * 
 * 
 * IZone describes a regional validity in a zone.
 * 
 */
public interface IZone extends IRegionalValidity{

	/**
	 * Gets the carrier.
	 * 
	 * @return the carrier
	 */
	public String getCarrier();
	
	/**
	 * Sets the carrier.
	 *
	 * @param carrier the new carrier
	 */
	public void setCarrier(String carrier);
	
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
	 * Gets the entry station in case the journey in the zone  has to start at a specific station in the zone 
	 * E.g. city traffic at the end of a train journey starting at the final train station.
	 *
	 * @return the entry station
	 */
	public String getEntryStation() ;

	/**
	 * Sets the entry station in case the journey in the zone has to start at a specific station in the zone 
	 * E.g. city traffic at the end of a train journey starting at the final train station.
	 * 
	 * @param entryStation the new entry station
	 */
	public void setEntryStation(String entryStation);

	/**
	 * Gets the terminating station in case the journey in the zone has to end at a specific station in the zone 
	 * E.g. city traffic at the begin of a train journey starting at the first train station.
	 *
	 * @return the terminating station
	 */
	public String getTerminatingStation();

	/**
	 * Sets the terminating station in case the journey in the zone has to end at a specific station in the zone 
	 * E.g. city traffic at the begin of a train journey starting at the first train station.
	 *
	 * @param terminatingStation the new terminating station
	 */
	public void setTerminatingStation(String terminatingStation);
	
	/**
	 * Gets the city code of the local city in case the zone is part of regional 
	 *   city transport: code list of the local carrier.
	 *
	 * @return the city
	 */
	public int getCity() ;

	/**
	 * Sets the city code of the local city in case the zone is part of regional 
	 *   city transport: code list of the local carrier
	 *   
	 * @param city the new city
	 */
	public void setCity(int city);

	/**
	 * Gets the binary zone id.
	 *   binary encoding of zones, encoding specification provided by 
	 *   the local service provider
	 *
	 * @return the binary zone id
	 */
	public byte[] getBinaryZoneId() ;

	/**
	 * Sets the binary zone id.
	 *   binary encoding of zones, encoding specification provided by 
	 *   the local service provider
	 *   
	 * @param binatyZoneId the new binaty zone id
	 */
	public void setBinaryZoneId(byte[] binatyZoneId);
	
	/**
	 * Gets the zone ids.
	 *  ids of the valid zones known by the local carriers in that zone
	 *
	 * @return the zone ids
	 */
	public Collection<Integer> getZoneIds() ;

	/**
	 * Adds a zone id.
	 *  id of the valid zones known by the local carriers in that zone
	 *
	 * @param zoneId the zone id
	 */
	public void addZoneId(int zoneId);
	
	/**
	 * Gets the European NUTS code in case this code is used to encode the zone.
	 *
	 * @return the NUTS code
	 */
	public String  getNUTScode();
	
	/**
	 * Sets the European NUTS code in case this code is used to encode the zone.
	 *
	 * @param code the new NUTS code
	 */
	public void  setNUTScode(String code);
	
}
