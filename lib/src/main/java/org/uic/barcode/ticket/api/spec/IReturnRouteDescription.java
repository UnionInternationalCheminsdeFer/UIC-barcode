/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;

/**
 * The Interface IReturnRouteDescription.
 * 
 * IReturnRouteDescription provides the description of a return route for an open 
 * ticket. The data elements for the route description are repeated. 
 */
public interface IReturnRouteDescription {
	
	/**
	 * Gets the from station code.
	 *
	 * @return the from station code
	 */
	public String getFromStation() ;

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
	public void setToStation(String toStation) ;
	
	/**
	 * Gets the from station name.
	 *
	 * @return the from station name
	 */
	public String getFromStationName() ;

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
	 * @return the valid region list
	 */
	public Collection<IRegionalValidity> getValidRegionList() ;

	/**
	 * Adds the valid region list.
	 *
	 * @param validRegion the valid region
	 */
	public void addValidRegionList(IRegionalValidity validRegion);

}
