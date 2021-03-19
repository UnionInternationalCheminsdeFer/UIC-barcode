package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.uic.barcode.ticket.api.spec.IDelayConfirmation;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.ITicketLink;

/**
 * The Class SimpleDelayConfirmation.
 */
public class SimpleDelayConfirmation extends SimpleDocumentData  implements IDelayConfirmation {
	
	/** The train. */
	protected String train;  						
	
	/** The reference. */
	protected String    reference;
	
    /** The station code table. */
    protected IStationCodeTable stationCodeTable = IStationCodeTable.stationUICReservation;
    
    /** The station. */
    protected String station;	
    
    /** The station name. */
    protected String stationName;    
    
    
 	/** The info text. */
	 protected String infoText;
	 	
  	/** The extension. */
	protected IExtension   extension;
	
	
	/** The arrival date. */
	protected Date arrivalDate;
	
	/** The arrival ut coffset. */
	protected Long arrivalUTCoffset;
	
	/** The cancelled train. */
	protected boolean cancelledTrain = false;
	
	/** The delay. */
	protected int delay = 0;
	
	/** The confirmation type. */
	protected int confirmationType = 0;
	  
	/** The linked tickets. */
	protected Collection<ITicketLink> linkedTickets	= new HashSet<ITicketLink>();	
	


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#getReference()
	 */
	@Override
	public String getReference() {
		return reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setReference(java.lang.String)
	 */
	@Override
	public void setReference(String reference) {
		this.reference = reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#getTrain()
	 */
	@Override
	public String getTrain() {
		return train;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setTrain(java.lang.String)
	 */
	@Override
	public void setTrain(String train) {
		this.train = train;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#getStationCodeTable()
	 */
	@Override
	public IStationCodeTable getStationCodeTable() {
		return stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setStationCodeTable(org.uic.ticket.api.asn.omv1.CodeTableType)
	 */
	@Override
	public void setStationCodeTable(IStationCodeTable stationCodeTable) {
		this.stationCodeTable = stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#getStation()
	 */
	@Override
	public String getStation() {
		return station;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setStation(java.lang.String)
	 */
	@Override
	public void setStation(String station) {
		this.station = station;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setStationName(java.lang.String)
	 */
	@Override
	public void setStationName(String name) {
		this.stationName  = name;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#getStationName()
	 */
	@Override
	public String getStationName() {
		return stationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#getArrivalDate()
	 */
	@Override
	public Date getArrivalDate() {
		return arrivalDate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setArrivalDate(java.util.Date)
	 */
	@Override
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#isTrainCancelled()
	 */
	@Override
	public boolean isTrainCancelled() {
		return cancelledTrain;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setTrainCancelled(boolean)
	 */
	@Override
	public void setTrainCancelled(boolean trainIsCancelled) {
		this.cancelledTrain = trainIsCancelled;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#getDelay()
	 */
	@Override
	public int getDelay() {
		return delay;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setDelay(int)
	 */
	@Override
	public void setDelay(int delay) {
		this.delay = delay;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#getConfirmationType()
	 */
	@Override
	public int getConfirmationType() {
		return this.confirmationType;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setConfirmationType(int)
	 */
	@Override
	public void setConfirmationType(int type) {
		this.confirmationType = type;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setTravelerOnBoardDelayed(boolean)
	 */
	@Override
	public void setTravelerOnBoardDelayed(boolean travelerOnBoardDelayed) {
		this.confirmationType = 0;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setTrainDelayed(boolean)
	 */
	@Override
	public void setTrainDelayed(boolean trainDelayed) {
		this.confirmationType = 1;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setTrainDelayedTravelerHasTrainTicket(boolean)
	 */
	@Override
	public void setTrainDelayedTravelerHasTrainTicket(
			boolean trainOfTravelerDelayed) {
		this.confirmationType = 2;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#isTravelerOnBoardDelayed()
	 */
	@Override
	public boolean isTravelerOnBoardDelayed() {
		if (this.confirmationType == 0) {
			return true;
		}
		return false;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#isTrainDelayed()
	 */
	@Override
	public boolean isTrainDelayed() {
		if (this.confirmationType == 1) {
			return true;
		}
		return false;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#isTrainDelayedTravelerHasTrainTicket()
	 */
	@Override
	public boolean isTrainDelayedTravelerHasTrainTicket() {
		if (this.confirmationType == 2) {
			return true;
		}
		return false;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#getLinkedTickets()
	 */
	@Override
	public Collection<ITicketLink> getLinkedTickets() {
		return linkedTickets;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#addLinkedTicket(org.uic.ticket.api.spec.ITicketLink)
	 */
	@Override
	public void addLinkedTicket(ITicketLink linkedTicket) {
		this.linkedTickets.add(linkedTicket);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#getInfoText()
	 */
	@Override
	public String getInfoText() {
		return infoText;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setInfoText(java.lang.String)
	 */
	@Override
	public void setInfoText(String text) {
		this.infoText = text;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#getExtension()
	 */
	@Override
	public IExtension getExtension() {
		return extension;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setExtension(org.uic.ticket.api.spec.IExtension)
	 */
	@Override
	public void setExtension(IExtension extension) {
		this.extension = extension;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#getArrivalUTCoffset()
	 */
	public Long getArrivalUTCoffset() {
		return arrivalUTCoffset;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDelayConfirmation#setArrivalUTCoffset(java.lang.Long)
	 */
	public void setArrivalUTCoffset(Long arrivalUTCoffset) {
		this.arrivalUTCoffset = arrivalUTCoffset;
	}
	
	

}
