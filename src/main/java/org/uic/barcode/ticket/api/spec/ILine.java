/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.HashSet;



/**
 * The Interface ILine.
 */
public interface ILine extends IRegionalValidity{

	/**
	 * Gets the carrier.
	 *
	 * @return the carrier
	 */
	public CharSequence getCarrier();
	
	/**
	 * Sets the carrier.
	 *
	 * @param carrier the new carrier
	 */
	public void setCarrier(String carrier);

	/**
	 * Sets the carrier, without conversion to an integer type in encoding.
	 *
	 * @param carrier the new carrier
	 */
	public void setCarrierMustString(String carrier);

	/**
	 * Gets the line ids.
	 *
	 * @return the line ids
	 */
	public HashSet<Integer> getLineIds();
	
	/**
	 * Adds the line id.
	 *
	 * @param lineId the line id
	 */
	public void addLineId(Integer lineId);

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
	public void setStationCodeTable( IStationCodeTable stationCodeTable);

	/**
	 * Gets the entry station.
	 *
	 * @return the entry station
	 */
	public CharSequence getEntryStation();
	
	/**
	 * Sets the entry station.
	 *
	 * @param entryStation the new entry station
	 */
	public void setEntryStation(String entryStation);

	/**
	 * Sets the entry station, without conversion to an integer type in encoding.
	 *
	 * @param entryStation the new entry station
	 */
	public void setEntryStationMustString(String entryStation);

	/**
	 * Gets the terminating station.
	 *
	 * @return the terminating station
	 */
	public CharSequence getTerminatingStation();
	
	/**
	 * Sets the terminating station.
	 *
	 * @param terminatingStation the new terminating station
	 */
	public void setTerminatingStation(String terminatingStation);

	/**
	 * Sets the terminating station, without conversion to an integer type in encoding.
	 *
	 * @param terminatingStation the new terminating station
	 */
	public void setTerminatingStationMustString(String terminatingStation);

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public int getCity() ;
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(int city);

	/**
	 * Gets the binary zone id.
	 *
	 * @return the binary zone id
	 */
	public byte[] getBinaryZoneId() ;
	
	/**
	 * Sets the binary zone id.
	 *
	 * @param binatyZoneId the new binary zone id
	 */
	public void setBinaryZoneId(byte[] binatyZoneId);
	
	
}
