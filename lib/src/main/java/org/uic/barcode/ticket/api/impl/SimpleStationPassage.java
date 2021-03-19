/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.IStationPassage;


/**
 * The Class SimpleStationPassage.
 */
public class SimpleStationPassage extends SimpleDocumentData implements IStationPassage {
	
	/** The reference. */
	protected String reference;
	
	/** The product name. */
	protected String productName;
	
	/** The product type. */
	protected String productId;
	
	/** The product owner. */
	protected String productOwner;
	
	/** The valid from. */
	protected Date validFrom;
	
	/** The valid until. */
	protected Date  validUntil;	
	
	/** The station code table. */
	protected IStationCodeTable stationCodeTable = IStationCodeTable.stationUIC;
       
	/** The station name. */
	protected String stationName;
	
	/** The number ofdays allowed. */
	protected int numberOfdaysAllowed;
	
	/** The stations. */
	protected Collection<String> stations = new LinkedHashSet<String>();

	/** The station names. */
	protected Collection<String> stationNames = new LinkedHashSet<String>();	
	
	
	/** The extension data. */
	protected IExtension   	extensionData;	
	
	
	/** The area codes. */
	protected Collection<String> areaCodes = new LinkedHashSet<String>();	
	
	/** The area names. */
	protected Collection<String> areaNames = new LinkedHashSet<String>();	

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getReference()
	 */
	public String getReference() {
		return reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#setReference(java.lang.String)
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getProductName()
	 */
	public String getProductName() {
		return productName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#setProductName(java.lang.String)
	 */
	public void setProductName(String name) {
		this.productName = name;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getProductType()
	 */
	public String getProductId() {
		return productId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#setProductType(int)
	 */
	public void setProductId(String id) {
		this.productId = id;
	}	

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getProductOwner()
	 */
	public String getProductOwner() {
		return productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#setProductOwner(java.lang.String)
	 */
	public void setProductOwner(String productOwner) {
		this.productOwner = productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getValidFrom()
	 */
	public Date getValidFrom() {
		return validFrom;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#setValidFrom(java.util.Date)
	 */
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getValidUntil()
	 */
	public Date getValidUntil() {
		return validUntil;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#setValidUntil(java.util.Date)
	 */
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getStationCodeTable()
	 */
	public IStationCodeTable getStationCodeTable() {
		return stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#setStationCodeTable(org.uic.ticket.api.asn.om.CodeTableType)
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable) {
		this.stationCodeTable = stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getStations()
	 */
	public Collection<String> getStations() {
		return stations;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#addStation(java.lang.String)
	 */
	public void addStation(String station) {
		this.stations.add(station);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#addStationName(java.lang.String)
	 */
	public void addStationName(String name) {
		this.stationNames.add(name);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getStationNames()
	 */
	public Collection<String> getStationNames() {
		return stationNames;		
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getNumberOfdaysAllowed()
	 */
	public int getNumberOfdaysAllowed() {
		return numberOfdaysAllowed;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#setNumberOfdaysAllowed(int)
	 */
	public void setNumberOfdaysAllowed(int numberOfdaysAllowed) {
		this.numberOfdaysAllowed = numberOfdaysAllowed;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getExtension()
	 */
	@Override
	public IExtension getExtension() {
		return extensionData;	
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#setExtension(org.uic.ticket.api.spec.IExtension)
	 */
	@Override
	public void setExtension(IExtension extensionData) {
		this.extensionData = extensionData;		
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getAreaCodes()
	 */
	@Override
	public Collection<String> getAreaCodes() {
		return areaCodes;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getAreaNames()
	 */
	@Override
	public Collection<String> getAreaNames() {
		return areaNames;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#addAreaCode(java.lang.String)
	 */
	@Override
	public void addAreaCode(String code) {
		areaCodes.add(code);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#addAreaName(java.lang.String)
	 */
	@Override
	public void addAreaName(String name) {
		areaNames.add(name);
	}


	/** The valid from utc coffset. */
	protected Long validFromUTCoffset;
	
	/** The valid until utc coffset. */
	protected Long validUntilUTCoffset;
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getValidFromUTCoffset()
	 */
	public Long getValidFromUTCoffset() {
		return validFromUTCoffset;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#setValidFromUTCoffset(java.lang.Long)
	 */
	public void setValidFromUTCoffset(Long validFromUTCoffset) {
		this.validFromUTCoffset = validFromUTCoffset;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#getValidUntilUTCoffset()
	 */
	public Long getValidUntilUTCoffset() {
		return validUntilUTCoffset;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IStationPassage#setValidUntilUTCoffset(java.lang.Long)
	 */
	public void setValidUntilUTCoffset(Long validUntilUTCoffset) {
		this.validUntilUTCoffset = validUntilUTCoffset;
	}

	@Override
	public void setUntilDate(Date date) {
		// TODO Automatisch generierter Methodenstub
		this.validUntil = date;
	}



}
