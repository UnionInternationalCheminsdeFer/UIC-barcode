/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;


/**
 * The Interface IViaStation.
 * 
 * IViaStation describes a route description or a part of a route description by
 *  via station according to UIC leaflet 108.1
 *  
 *  Note:  as route description with via stations can contain alternative routes and can include 
 *  other routes the data structure is used recursively! 
 *  
 *  
 *  ViaStation could be
 *  	- a simple station
 *    or
 *  	- a list of alternative routes defined as a list of other IViaStations
 *    or 
 *      - a route defined as a list of other IViaStations
 *  
 *  
 *  
 */
public interface IViaStation extends IRegionalValidity {
	
	/**
	 * Gets the station code table.
	 *
	 * Defines the station code table to be used to retrieve station information. 
	 * Default in this case is the UIC station codes table for standard UIC 
	 * station code from MERITS (UIC country code + 5 digit local code) 
	 * 
	 * The station code table should be included only in case it differs from the station 
	 * code table used in the travel document!
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
	 * The station code table should be included only in case it differs from the station 
	 * code table used in the travel document!
	 * 	
	 * @param stationCodeTable the new station code table
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable);
	
	/**
	 * Gets the station code.
	 *
	 * @return the station code
	 */
	public String getStation();
	
	/**
	 * Sets the station code.
	 *
	 * @param station the new station code
	 */
	public void setStation(String station);
	
	
	/**
	 * Gets the alternative routes.
	 *
	 * @return the alternative routes
	 */
	public Collection<IViaStation> getAlternativeRoutes();
	
	/**
	 * Adds an alternative route.
	 *
	 * @param route the alternative route
	 */
	public void addAlternativeRoute(IViaStation route);
	
	/**
	 * Gets the route.
	 *
	 * @return the route
	 */
	public Collection<IViaStation> getRoute();
	
	/**
	 * Adds the route station.
	 *
	 * @param viaStation the via station
	 */
	public void addRouteStation(IViaStation viaStation);
	
	/**
	 * Checks if the via station is a border point.
	 *
	 * @return true, if is border point
	 */
	public boolean isBorder();
	
	/**
	 * Sets if the via station is a border point.
	 *
	 * @param border the new border point flag
	 */
	public void setBorder(boolean border);
	
	/**
	 * Gets the carriers.
	 * 
	 * The carriers along the route should preferably be indicated as a list within 
	 * the ticket, and not in the via stations.
	 *
	 * @return the carriers
	 */
	public Collection<String> getCarriers();
	
	/**
	 * Adds the carrier.
	 *	 
	 * The carriers along the route should preferably be indicated as a list within 
	 * the ticket, and not in the via stations.
	 * 	 *
	 * @param carrier the carrier
	 */
	public void addCarrier(String carrier);
	
	/**
	 * Gets the route id.
	 * 
	 * A route id indicating the route in a lookup table.
	 *
	 * @return the route id
	 */
	public int getRouteId();
	
	/**
	 * Sets the route id.
	 *
	 * A route id indicating the route in a lookup table.
	 * 	 
	 * @param routeId the new route id
	 */
	public void setRouteId(int routeId);


	/**
	 * Gets the series id as defined in the price data according to UIC leaflet 108.1.
	 *
	 * @return the series id as defined in the price data according to UIC leaflet 108.1.
	 */
	public int getSeriesId();
	
	/**
	 * Sets the series id as defined in the price data according to UIC leaflet 108.1..
	 *
	 * @param seriesId the new series id as defined in the price data according to UIC leaflet 108.1.
	 */
	public void setSeriesId(int seriesId);
	

}
