/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Date;

import org.uic.barcode.ticket.api.spec.ITrainLink;
import org.uic.barcode.ticket.api.utils.MustString;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleTrainLink.
 */
public class SimpleTrainLink implements ITrainLink{
	
		
	/** The train. */
	protected CharSequence train;
	
	/** The departure date time. */
	protected Date departureDateTime;
	
	/**  offset to UTC time in units of 15 minutes. */
	protected Long departureUTCoffset;
	
	/** The from station. */
	protected CharSequence fromStation;
	
	/** The to station. */
	protected CharSequence toStation;
				                 
	/** The from station name. */
	protected String fromStationName;
	
	/** The to station name. */
	protected String toStationName;
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#getTrain()
	 */
	public CharSequence getTrainInt() {
		return train;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setTrain(java.lang.String)
	 */
	public void setTrain(String train) {
		this.train = train.trim();
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setTrainMustString(java.lang.String)
	 */
	public void setTrainMustString(String train) {
		this.train = new MustString(train.trim());
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
	public CharSequence getFromStationInt() {
		return fromStation;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setFromStation(java.lang.String)
	 */
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setFromStationMustString(java.lang.String)
	 */
	public void setFromStationMustString(String fromStation) {
		this.fromStation = new MustString(fromStation);
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#getToStation()
	 */
	public CharSequence getToStationInt() {
		return toStation;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setToStation(java.lang.String)
	 */
	public void setToStation(String toStation) {
		if (toStation != null) {
			this.toStation = toStation.trim();
		} else {
			this.toStation = null;
		}
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITrainLink#setToStation(java.lang.String)
	 */
	public void setToStationMustString(String toStation) {
		this.toStation = new MustString(toStation.trim());
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
		if (fromStationName != null) {
			this.fromStationName = fromStationName.trim();
		} else {
			this.fromStationName = null;
		}
		
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
