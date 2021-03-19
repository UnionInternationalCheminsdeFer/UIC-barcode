/*
 * 
 */
package org.uic.barcode.ticket.api.spec;


/**
 * The Interface IRouteSection.
 * 
 * IRouteSection describes a section of a route by the beginn and end station.
 */
public interface IRouteSection {
	
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
	public void setFromStation(String fromStation) ;
	
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
}
