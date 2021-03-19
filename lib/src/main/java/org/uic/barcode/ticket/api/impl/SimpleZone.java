/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.HashSet;

import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.IZone;


/**
 * The Class SimpleZone.
 */
public class SimpleZone implements IZone {
	
	/** The carrier. */
	protected String carrier;
	
	
	/** The station code table. */
	protected IStationCodeTable stationCodeTable = IStationCodeTable.stationUIC;
	
	/** The entry station. */
	protected String entryStation;
	
	/** The terminating station. */
	protected String terminatingStation;        

	/** The city. */
	protected int city;
	
	
	/** The zone ids. */
	protected Collection<Integer>zoneIds = new HashSet<Integer>(); 
	
	/** The binaty zone id. */
	protected byte[] binaryZoneId;
	
	
	protected String nutsCode;

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#getCarrier()
	 */
	public String getCarrier() {
		return carrier;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#setCarrier(java.lang.String)
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#getStationCodeTable()
	 */
	public IStationCodeTable getStationCodeTable() {
		return stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#setStationCodeTable(org.uic.ticket.api.asn.om.CodeTableType)
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable) {
		this.stationCodeTable = stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#getEntryStation()
	 */
	public String getEntryStation() {
		return entryStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#setEntryStation(java.lang.String)
	 */
	public void setEntryStation(String entryStation) {
		this.entryStation = entryStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#getTerminatingStation()
	 */
	public String getTerminatingStation() {
		return terminatingStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#setTerminatingStation(java.lang.String)
	 */
	public void setTerminatingStation(String terminatingStation) {
		this.terminatingStation = terminatingStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#getCity()
	 */
	public int getCity() {
		return city;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#setCity(int)
	 */
	public void setCity(int city) {
		this.city = city;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#getBinatyZoneId()
	 */
	public byte[] getBinaryZoneId() {
		return binaryZoneId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#setBinatyZoneId(byte[])
	 */
	public void setBinaryZoneId(byte[] binaryZoneId) {
		this.binaryZoneId = binaryZoneId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#getZoneIds()
	 */
	public Collection<Integer> getZoneIds() {
		return zoneIds;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#addZoneId(int)
	 */
	public void addZoneId(int zoneId) {
		this.zoneIds.add(new Integer (zoneId));
	}

	@Override
	public String getNUTScode() {
		return nutsCode;
	}

	@Override
	public void setNUTScode(String code) {
		nutsCode = code;
	}
	
	
	

}
