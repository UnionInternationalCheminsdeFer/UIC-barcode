/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.HashSet;

import org.uic.barcode.ticket.api.spec.ILine;
import org.uic.barcode.ticket.api.spec.IStationCodeTable;

/**
 * The Class SimpleLine.
 */
public class SimpleLine implements ILine {
	
	/** The carrier. */
	protected String carrier;
	
	/** The line ids. */
	protected HashSet<Integer> lineIds = new HashSet<Integer>();
	
	/** The station code table. */
	protected IStationCodeTable stationCodeTable = IStationCodeTable.stationUIC;
	
	/** The entry station. */
	protected String entryStation;
	
	/** The terminating station. */
	protected String terminatingStation;        
	
	/** The city. */
	protected int city;
	
	/** The binary zone id. */
	protected byte[] binaryZoneId;

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#getCarrier()
	 */
	public String getCarrier() {
		return carrier;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#setCarrier(java.lang.String)
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#getLineIds()
	 */
	public HashSet<Integer> getLineIds() {
		return lineIds;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#addLineId(java.lang.Integer)
	 */
	public void addLineId(Integer lineId) {
		this.lineIds.add(lineId);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#getStationCodeTable()
	 */
	public IStationCodeTable getStationCodeTable() {
		return stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#setStationCodeTable(org.uic.ticket.api.asn.om.CodeTableType)
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable) {
		this.stationCodeTable = stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#getEntryStation()
	 */
	public String getEntryStation() {
		return entryStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#setEntryStation(java.lang.String)
	 */
	public void setEntryStation(String entryStation) {
		this.entryStation = entryStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#getTerminatingStation()
	 */
	public String getTerminatingStation() {
		return terminatingStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#setTerminatingStation(java.lang.String)
	 */
	public void setTerminatingStation(String terminatingStation) {
		this.terminatingStation = terminatingStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#getCity()
	 */
	public int getCity() {
		return city;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#setCity(int)
	 */
	public void setCity(int city) {
		this.city = city;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#getBinaryZoneId()
	 */
	public byte[] getBinaryZoneId() {
		return binaryZoneId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILine#setBinaryZoneId(byte[])
	 */
	public void setBinaryZoneId(byte[] binatyZoneId) {
		this.binaryZoneId = binatyZoneId;
	}
	
	
	

}
