/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Date;

/**
 * The Interface ITrainLink.
 * 
 * ITrainLink specifies the mandatory use of a train within the route of an open ticket or counter mark.
 */
public interface ITrainLink extends IRegionalValidity {
	
	/**
	 * Gets the train.
	 *
	 * @return the train
	 */
	public CharSequence getTrain();
	
	/**
	 * Sets the train.
	 *
	 * @param train the new train
	 */
	public void setTrain(String train);

	/**
	 * Sets the train, without conversion to an integer type in encoding.
	 *
	 * @param train the new train
	 */
	public void setTrainMustString(String train);
	
	/**
	 * Gets the departure date time.
	 *
	 * @return the departure date time
	 */
	public Date getDepartureDateTime();
	
	/**
	 * Sets the departure date time.
	 *
	 * @param departureDateTime the new departure date time
	 */
	public void setDepartureDateTime(Date departureDateTime) ;
	
	
	/**
	 * Gets the departure date time offset to UTC in units of 15 minutes.
	 *
	 * @return the departure date time UTC offset
	 */
	public Long getDepartureUTCoffset();
	
	/**
	 * Sets the departure date time.
	 *
	 * @param departureDateTime the new departure date time
	 */
	public void setDepartureUTCoffset(Long departureUTCoffset) ;
	
	
	
	
	/**
	 * Gets the from station code.
	 *
	 * @return the from station code
	 */
	public CharSequence getFromStation();
	
	/**
	 * Sets the from station code.
	 *
	 * @param fromStation the new from station code
	 */
	public void setFromStation(String fromStation);

	/**
	 * Sets the from station code, without conversion to an integer type in encoding.
	 *
	 * @param fromStation the new from station code
	 */
	public void setFromStationMustString(String fromStation);
	
	/**
	 * Gets the to station code.
	 *
	 * @return the to station code
	 */
	public CharSequence getToStation();
	
	/**
	 * Sets the to station code.
	 *
	 * @param toStation the new to station code
	 */
	public void setToStation(String toStation);

	/**
	 * Sets the to station code, without conversion to an integer type in encoding.
	 *
	 * @param toStation the new to station code
	 */
	public void setToStationMustString(String toStation);
	
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
		

	
	
	
	
}
