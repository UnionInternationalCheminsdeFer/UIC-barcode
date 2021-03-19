/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.IViaStation;


/**
 * The Class SimpleViaStation.
 */
public class SimpleViaStation implements IViaStation {
	
	/** The station code table. */
	protected IStationCodeTable stationCodeTable = IStationCodeTable.stationUIC;
	
	/** The station. */
	protected String station;
	
	/** The alternative routes. */
	protected Collection<IViaStation>alternativeRoutes = new LinkedHashSet<IViaStation>();	
	
	/** The route. */
	protected Collection<IViaStation>route = new LinkedHashSet<IViaStation>();	
	
	/** The border. */
	protected boolean border = false;
	
	/** The carriers. */
	protected Collection<String>carriers = new LinkedHashSet<String>();	
	
	/** The route id. */
	protected int routeId;

	/** The series id. */
	protected int seriesId;	
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#getStationCodeTable()
	 */
	public IStationCodeTable getStationCodeTable() {
		return stationCodeTable;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#setStationCodeTable(org.uic.ticket.api.asn.om.CodeTableType)
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable) {
		this.stationCodeTable = stationCodeTable;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#getStation()
	 */
	public String getStation() {
		return station;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#setStation(java.lang.String)
	 */
	public void setStation(String station) {
		this.station = station;
	}
	

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#getAlternativeRoutes()
	 */
	public Collection<IViaStation> getAlternativeRoutes() {
		return alternativeRoutes;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#addAlternativeRoute(org.uic.ticket.api.spec.IViaStation)
	 */
	public void addAlternativeRoute(IViaStation route) {
		this.alternativeRoutes.add(route);
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#getRoute()
	 */
	public Collection<IViaStation> getRoute() {
		return route;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#addRouteStation(org.uic.ticket.api.spec.IViaStation)
	 */
	public void addRouteStation(IViaStation viaStation) {
		this.route.add(viaStation);
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#isBorder()
	 */
	public boolean isBorder() {
		return border;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#setBorder(boolean)
	 */
	public void setBorder(boolean border) {
		this.border = border;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#getCarriers()
	 */
	public Collection<String> getCarriers() {
		return carriers;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#addCarrier(int)
	 */
	public void addCarrier(String carrier) {
		this.carriers.add(carrier);
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#getRouteId()
	 */
	public int getRouteId() {
		return routeId;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#setRouteId(int)
	 */
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#getSeriesId()
	 */
	public int getSeriesId() {
		return seriesId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IViaStation#setSeriesId(int)
	 */
	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}
	
	

}
