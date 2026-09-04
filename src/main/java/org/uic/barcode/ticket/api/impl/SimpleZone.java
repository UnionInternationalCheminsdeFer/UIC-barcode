/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.HashSet;

import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.IZone;
import org.uic.barcode.ticket.api.utils.MustString;


/**
 * The Class SimpleZone.
 */
public class SimpleZone implements IZone {
	
	/** The carrier. */
	protected CharSequence carrier;
	
	
	/** The station code table. */
	protected IStationCodeTable stationCodeTable = IStationCodeTable.stationUIC;
	
	/** The entry station. */
	protected CharSequence entryStation;
	
	/** The terminating station. */
	protected CharSequence terminatingStation;

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
	public CharSequence getCarrierInt() {
		return carrier;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#setCarrier(java.lang.String)
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#setCarrierMustString(java.lang.String)
	 */
	public void setCarrierMustString(String carrier) {
		this.carrier = new MustString(carrier);
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
	public CharSequence getEntryStationInt() {
		return entryStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#setEntryStation(java.lang.String)
	 */
	public void setEntryStation(String entryStation) {
		this.entryStation = entryStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#setEntryStationMustString(java.lang.String)
	 */
	public void setEntryStationMustString(String entryStation) {
		this.entryStation = new MustString(entryStation);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#getTerminatingStation()
	 */
	public CharSequence getTerminatingStationInt() {
		return terminatingStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#setTerminatingStation(java.lang.String)
	 */
	public void setTerminatingStation(String terminatingStation) {
		this.terminatingStation = terminatingStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IZone#setTerminatingStationMustString(java.lang.String)
	 */
	public void setTerminatingStationMustString(String terminatingStation) {
		this.terminatingStation = new MustString(terminatingStation);
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
		this.zoneIds.add(zoneId);
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
