/*
 * 
 */
package org.uic.barcode.ticket.api.impl;


import org.uic.barcode.ticket.api.spec.IRouteSection;
import org.uic.barcode.ticket.api.spec.IStationCodeTable;


/**
 * The Class SimpleRouteSection.
 */
public class SimpleRouteSection implements IRouteSection {
	
	
	/** The station code table. */
	protected IStationCodeTable stationCodeTable = IStationCodeTable.stationUIC;
	
	/** The from station. */
	protected String fromStation;
	
	/** The to station. */
	protected String toStation;        
				                 
	/** The from station name. */
	protected String fromStationName;
	
	/** The to station name. */
	protected String toStationName;
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRouteSection#getStationCodeTable()
	 */
	public IStationCodeTable getStationCodeTable() {
		return stationCodeTable;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRouteSection#setStationCodeTable(org.uic.ticket.api.asn.om.CodeTableType)
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable) {
		this.stationCodeTable = stationCodeTable;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRouteSection#getFromStation()
	 */
	public String getFromStation() {
		return fromStation;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRouteSection#setFromStation(java.lang.String)
	 */
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRouteSection#getToStation()
	 */
	public String getToStation() {
		return toStation;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRouteSection#setToStation(java.lang.String)
	 */
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRouteSection#getFromStationName()
	 */
	public String getFromStationName() {
		return fromStationName;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRouteSection#setFromStationName(java.lang.String)
	 */
	public void setFromStationName(String fromStationName) {
		this.fromStationName = fromStationName;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRouteSection#getToStationName()
	 */
	public String getToStationName() {
		return toStationName;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRouteSection#setToStationName(java.lang.String)
	 */
	public void setToStationName(String toStationName) {
		this.toStationName = toStationName;
	}       
	
	
	
	
	

}
