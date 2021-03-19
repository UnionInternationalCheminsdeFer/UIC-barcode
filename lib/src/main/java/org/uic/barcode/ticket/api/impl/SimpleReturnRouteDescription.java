/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.IRegionalValidity;
import org.uic.barcode.ticket.api.spec.IReturnRouteDescription;

/**
 * The Class SimpleReturnRouteDescription.
 */
public class SimpleReturnRouteDescription implements IReturnRouteDescription{
	
	/** The from station. */
	protected String fromStation;
	
	/** The to station. */
	protected String toStation;        
				                 
	/** The from station name. */
	protected String fromStationName;
	
	/** The to station name. */
	protected String toStationName;     
	
    
	/** The valid region desc. */
	protected String validRegionDesc;				                  
	
	/** The valid region list. */
	protected Collection<IRegionalValidity> validRegionList = new LinkedHashSet<IRegionalValidity>();
    
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IReturnRouteDescription#getFromStation()
	 */
	public String getFromStation() {
		return fromStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IReturnRouteDescription#setFromStation(java.lang.String)
	 */
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IReturnRouteDescription#getToStation()
	 */
	public String getToStation() {
		return toStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IReturnRouteDescription#setToStation(java.lang.String)
	 */
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IReturnRouteDescription#getFromStationName()
	 */
	public String getFromStationName() {
		return fromStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IReturnRouteDescription#setFromStationName(java.lang.String)
	 */
	public void setFromStationName(String fromStationName) {
		this.fromStationName = fromStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IReturnRouteDescription#getToStationName()
	 */
	public String getToStationName() {
		return toStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IReturnRouteDescription#setToStationName(java.lang.String)
	 */
	public void setToStationName(String toStationName) {
		this.toStationName = toStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IReturnRouteDescription#getValidRegionDesc()
	 */
	public String getValidRegionDesc() {
		return validRegionDesc;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IReturnRouteDescription#setValidRegionDesc(java.lang.String)
	 */
	public void setValidRegionDesc(String validRegionDesc) {
		this.validRegionDesc = validRegionDesc;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IReturnRouteDescription#getValidRegionList()
	 */
	public Collection<IRegionalValidity> getValidRegionList() {
		return validRegionList;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IReturnRouteDescription#addValidRegionList(org.uic.ticket.api.spec.IRegionalValidity)
	 */
	public void addValidRegionList(IRegionalValidity validRegion) {
		this.validRegionList.add(validRegion);
	}
	
	

}
