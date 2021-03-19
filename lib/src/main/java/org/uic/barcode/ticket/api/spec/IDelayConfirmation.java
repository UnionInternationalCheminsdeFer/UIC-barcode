/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;


/**
 * The Interface IDelayConfirmation implements 
 * a confirmation of delay given to a traveler to prove to another carrier he want tu use to continue the journey 
 * that he was delayed.
 */
public interface IDelayConfirmation  extends IDocumentData {
	

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference();

	/**
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference);

	/**
	 * Gets the train.
	 *
	 * @return the train
	 */
	public String getTrain();
	
	/**
	 * Sets the train.
	 *
	 * @param train the new train
	 */
	public void setTrain(String train);

	/**
	 * Gets the station code table.
	 *
	 * Defines the station code table to be used to retrieve station information. 
	 * Default in this case is the UIC station codes table for standard UIC 
	 * station code from MERITS (UIC country code + 5 digit local code) 
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
	 * @param stationCodeTable the new station code table
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable);

	/**
	 * Gets the from station code where the delay was final (usually the station where the traveler left the train).
	 *
	 * @return the from station code
	 */
	public String getStation();

	/**
	 * Sets the from station code where the delay was final (usually the station where the traveler left the train).
	 *
	 * @param station the new station
	 */
	public void setStation(String station);


	/**
	 * Sets the from station name where the delay was final (usually the station where the traveler left the train).
	 *
	 * @param fromStationName the new from station name
	 */
	public void setStationName(String fromStationName);

	/**
	 * Gets the to station name where the delay was final (usually the station where the traveler left the train).
	 *
	 * @return the to station name
	 */
	public String getStationName();

	/**
	 * Gets the planned arrival date and time where the traveler left the train.
	 *
	 * @return the planned arrival date and time
	 */
	public Date getArrivalDate();
	
	/**
	 * Sets the planned arrival date and time where the traveler left the train.
	 *
	 * @param arrivalDate the new planned arrival date and time
	 */
	public void setArrivalDate(Date arrivalDate);
	
	/**
	 * Checks if is train was cancelled.
	 *
	 * @return true, if is train cancelled
	 */
	public boolean isTrainCancelled();
	
	/**
	 * Sets the train cancelled.
	 *
	 * @param trainIsCancelled the new train cancelled
	 */
	public void setTrainCancelled(boolean trainIsCancelled);
	
	
	/**
	 * Gets the delay in minutes.
	 *
	 * @return the delay in minutes
	 */
	public int getDelay();
	
	/**
	 * Sets the delay in minutes.
	 *
	 * @param delay the delay in minutes
	 */
	public void setDelay(int delay);
	
	/**
	 * Gets the confirmation type.
	 * 
	 * See code list of the specification for the meaning of the values.
	 *
	 * @return the confirmation type
	 */
	public int getConfirmationType();
	
	/**
	 * Sets the confirmation type.
	 * 
	 * See code list of the specification for the meaning of the values.
	 *
	 * @param type the new confirmation type
	 */
	public void setConfirmationType(int type);
	
	/**
	 * Sets the confirmation that the traveler was on board of the delayed train.
	 *
	 * @param travelerOnBoardDelayed the confirmation that the traveler was on the delayed train
	 */
	public void setTravelerOnBoardDelayed(boolean travelerOnBoardDelayed);
	
	/**
	 * Sets the confirmation that the train was delayed.
	 *
	 * @param trainDelayed the confirmation that the train was delayed
	 */
	public void setTrainDelayed(boolean trainDelayed);
	
	/**
	 * Sets the confirmation that the train was delayed and the traveler had a ticket designated to the delayed train.
	 *
	 * @param trainOfTravelerDelayed the confirmation that the train was delayed and the traveler had a ticket designated to the delayed train.
	 */
	public void setTrainDelayedTravelerHasTrainTicket(boolean trainOfTravelerDelayed);
	
	/**
	 * Checks if this document confirms that the traveler was on board of the delayed train.
	 *
	 * @return true, if this document confirms that the traveler was on board of the delayed train
	 */
	public boolean isTravelerOnBoardDelayed();
	
	/**
	 * Checks if this document confirms that the train is delayed.
	 *
	 * @return true, if is train delay is confirmed
	 */
	public boolean isTrainDelayed();
	
	/**
	 * Checks if this document confirms that the train where the traveler had a designated ticket for is delayed.
	 *
	 * @return true, if this document confirms that the traveler has a ticket designated to the delayed train.
	 */
	public boolean isTrainDelayedTravelerHasTrainTicket();	
	
	
	/**
	 * Gets the linked tickets.
	 *
	 * Link to the ticket valid on the delayed train. 
	 *
	 * @return the linked tickets
	 */
	public Collection<ITicketLink> getLinkedTickets() ;
	

	/**
	 * Adds the linked ticket valid on the delayed train.
	 *
	 * 
	 * @param linkedTicket the linked ticket
	 */
	public void addLinkedTicket(ITicketLink linkedTicket) ;	
	



	/**
	 * Gets the info text.
	 *
	 * @return the info text
	 */
	public String getInfoText();

	/**
	 * Sets the info text.
	 *
	 * @param infoText the new info text
	 */
	public void setInfoText(String infoText);

	
	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public IExtension getExtension();

	/**
	 * Sets the extension.
	 *
	 * @param extension the new extension
	 */
	public void setExtension(IExtension extension);

	/**
	 * Gets the arrival ut coffset.
	 *
	 * @return the arrival ut coffset
	 */
	public Long getArrivalUTCoffset();

	/**
	 * Sets the arrival ut coffset.
	 *
	 * @param arrivalUTCoffset the new arrival ut coffset
	 */
	public void setArrivalUTCoffset(Long arrivalUTCoffset);
	
	
}
