/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Date;

import org.uic.barcode.ticket.api.spec.ITrainLink;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleTrainLink.
 */
public class SimpleTrainLink implements ITrainLink{
	
		
	/** The train. */
	protected String train;
	
	/** The departure date time. */
	protected Date departureDateTime;
	
	/**  offset to UTC time in units of 15 minutes. */
	protected Long departureUTCoffset;
	
	/** The from station. */
	protected String fromStation;
	
	/** The to station. */
	protected String toStation;        
				                 
	/** The from station name. */
	protected String fromStationName;
	
	/** The to station name. */
	protected String toStationName;
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#getTrain()
	 */
	public String getTrain() {
		return train;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setTrain(java.lang.String)
	 */
	public void setTrain(String train) {
		this.train = train.trim();
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#getDepartureDateTime()
	 */
	public Date getDepartureDateTime() {
		return departureDateTime;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setDepartureDateTime(java.util.Date)
	 */
	public void setDepartureDateTime(Date departureDateTime) {
		this.departureDateTime = departureDateTime;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#getFromStation()
	 */
	public String getFromStation() {
		return fromStation;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setFromStation(java.lang.String)
	 */
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#getToStation()
	 */
	public String getToStation() {
		return toStation;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setToStation(java.lang.String)
	 */
	public void setToStation(String toStation) {
		this.toStation = toStation.trim();
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#getFromStationName()
	 */
	public String getFromStationName() {
		return fromStationName;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setFromStationName(java.lang.String)
	 */
	public void setFromStationName(String fromStationName) {
		this.fromStationName = fromStationName.trim();
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#getToStationName()
	 */
	public String getToStationName() {
		return toStationName;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setToStationName(java.lang.String)
	 */
	public void setToStationName(String toStationName) {
		this.toStationName = toStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#getDepartureUTCoffset()
	 */
	public Long getDepartureUTCoffset() {
		return departureUTCoffset;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setDepartureUTCoffset(java.lang.Long)
	 */
	public void setDepartureUTCoffset(Long departureUTCoffset) {
		this.departureUTCoffset = departureUTCoffset;
	}         
	
	

}
