/*
 *
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.stream.Collectors;


/**
 * The Interface IViaStation.
 * <p>
 * IViaStation describes a route description or a part of a route description by
 * via station according to UIC leaflet 108.1
 * <p>
 * Note:  as route description with via stations can contain alternative routes and can include
 * other routes the data structure is used recursively!
 * <p>
 * <p>
 * ViaStation could be
 * - a simple station
 * or
 * - a list of alternative routes defined as a list of other IViaStations
 * or
 * - a route defined as a list of other IViaStations
 *
 *
 *
 */
public interface IViaStation extends IRegionalValidity {

    /**
     * Gets the station code table.
     * <p>
     * Defines the station code table to be used to retrieve station information.
     * Default in this case is the UIC station codes table for standard UIC
     * station code from MERITS (UIC country code + 5 digit local code)
     * <p>
     * The station code table should be included only in case it differs from the station
     * code table used in the travel document!
     *
     * @return the station code table
     */
    public IStationCodeTable getStationCodeTable();

    /**
     * Sets the station code table.
     * <p>
     * Defines the station code table to be used to retrieve station information.
     * Default in this case is the UIC station codes table for standard UIC
     * station code from MERITS (UIC country code + 5 digit local code)
     * <p>
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
    public default String getStation() {
        CharSequence v = getStationInt();
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    public CharSequence getStationInt();

    /**
     * Sets the station code.
     *
     * @param station the new station code
     */
    public void setStation(String station);

    /**
     * Sets the station code, without conversion to an integer type in encoding.
     *
     * @param station the new station code
     */
    public void setStationMustString(String station);


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
     * <p>
     * The carriers along the route should preferably be indicated as a list within
     * the ticket, and not in the via stations.
     *
     * @return the carriers
     */
    public default Collection<String> getCarriers() {
        return getCarriersInt().stream().map(CharSequence::toString).collect(Collectors.toList());
    }

    public Collection<CharSequence> getCarriersInt();

    /**
     * Adds the carrier.
     * <p>
     * The carriers along the route should preferably be indicated as a list within
     * the ticket, and not in the via stations.
     * *
     *
     * @param carrier the carrier
     */
    public void addCarrier(String carrier);

    /**
     * Adds the carrier, without conversion to an integer type in encoding.
     * <p>
     * The carriers along the route should preferably be indicated as a list within
     * the ticket, and not in the via stations.
     * *
     *
     * @param carrier the carrier
     */
    public void addCarrierMustString(String carrier);

    /**
     * Gets the route id.
     * <p>
     * A route id indicating the route in a lookup table.
     *
     * @return the route id
     */
    public int getRouteId();

    /**
     * Sets the route id.
     * <p>
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


}
