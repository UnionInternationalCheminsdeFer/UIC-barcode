/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IIncludedOpenTicket;
import org.uic.barcode.ticket.api.spec.IRegionalValidity;
import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.ITariff;
import org.uic.barcode.ticket.api.spec.ITravelClassType;

/**
 * The Class SimpleIncludedOpenTicket.
 */
public class SimpleIncludedOpenTicket implements IIncludedOpenTicket {
	
	/** The departure date. */
	protected Date   departureDate;
	
	/** The arrival date. */
	protected Date   arrivalDate;		
	
	/** The product id. */
	protected String productId;

	/** The product owner. */
	protected String productOwner;
	
	/** The external issuer. */
	protected int externalIssuer;
	
	/** The authorization code. */
	protected int authorizationCode;	

	/** The station code table. */
	protected IStationCodeTable stationCodeTable = IStationCodeTable.stationUIC;
	
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
       
	/** The valid from. */
	protected Date validFrom;
	
	/** The valid until. */
	protected Date validUntil;    
    
	/** The class code. */
	protected ITravelClassType	classCode = ITravelClassType.second;       
   
	/** The included carriers. */
	protected Collection<String>includedCarriers = new LinkedHashSet<String>();	  
	
	/** The included service brands. */
	protected Collection<Integer>includedServiceBrands = new LinkedHashSet<Integer>();	
	
	/** The excluded service brands. */
	protected Collection<Integer>excludedServiceBrands = new LinkedHashSet<Integer>();	    	

    /** The tariffs. */
    protected Collection<ITariff> tariffs = new LinkedHashSet<ITariff>();	   
    
       
 	/** The info text. */
	 protected String infoText;
	 	
  	/** The extension. */
	  protected IExtension   extension;

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getDepartureDate()
	 */
	public Date getDepartureDate() {
		return departureDate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setDepartureDate(java.util.Date)
	 */
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getArrivalDate()
	 */
	public Date getArrivalDate() {
		return arrivalDate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setArrivalDate(java.util.Date)
	 */
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getProductId()
	 */
	public String getProductId() {
		return productId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setProductId(java.lang.String)
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getProductOwner()
	 */
	public String getProductOwner() {
		return productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setProductOwner(java.lang.String)
	 */
	public void setProductOwner(String productOwner) {
		this.productOwner = productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getExternalIssuer()
	 */
	public int getExternalIssuer() {
		return externalIssuer;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setExternalIssuer(int)
	 */
	public void setExternalIssuer(int externalIssuer) {
		this.externalIssuer = externalIssuer;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getAuthorizationCode()
	 */
	public int getAuthorizationCode() {
		return authorizationCode;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setAuthorizationCode(int)
	 */
	public void setAuthorizationCode(int authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getStationCodeTable()
	 */
	public IStationCodeTable getStationCodeTable() {
		return stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setStationCodeTable(org.uic.ticket.api.asn.om.CodeTableType)
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable) {
		this.stationCodeTable = stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getFromStation()
	 */
	public String getFromStation() {
		return fromStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setFromStation(java.lang.String)
	 */
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getToStation()
	 */
	public String getToStation() {
		return toStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setToStation(java.lang.String)
	 */
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getFromStationName()
	 */
	public String getFromStationName() {
		return fromStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setFromStationName(java.lang.String)
	 */
	public void setFromStationName(String fromStationName) {
		this.fromStationName = fromStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getToStationName()
	 */
	public String getToStationName() {
		return toStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setToStationName(java.lang.String)
	 */
	public void setToStationName(String toStationName) {
		this.toStationName = toStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getValidRegionDesc()
	 */
	public String getValidRegionDesc() {
		return validRegionDesc;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setValidRegionDesc(java.lang.String)
	 */
	public void setValidRegionDesc(String validRegionDesc) {
		this.validRegionDesc = validRegionDesc;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getValidRegionList()
	 */
	public Collection<IRegionalValidity> getValidRegionList() {
		return validRegionList;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#addValidRegionList(org.uic.ticket.api.spec.IRegionalValidity)
	 */
	public void addValidRegionList(IRegionalValidity validRegion) {
		this.validRegionList.add(validRegion);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getValidFrom()
	 */
	public Date getValidFrom() {
		return validFrom;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setValidFrom(java.util.Date)
	 */
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getValidUntil()
	 */
	public Date getValidUntil() {
		return validUntil;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setValidUntil(java.util.Date)
	 */
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getClassCode()
	 */
	public ITravelClassType getClassCode() {
		return classCode;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setClassCode(org.uic.ticket.api.asn.om.TravelClassType)
	 */
	public void setClassCode(ITravelClassType classCode) {
		this.classCode = classCode;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getIncludedCarriers()
	 */
	public Collection<String> getIncludedCarriers() {
		return includedCarriers;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#addIncludedCarrier(java.lang.Integer)
	 */
	public void addIncludedCarrier(String carrier) {
		this.includedCarriers.add(carrier);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getIncludedServiceBrands()
	 */
	public Collection<Integer> getIncludedServiceBrands() {
		return includedServiceBrands;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#addIncludedServiceBrand(java.lang.Integer)
	 */
	public void addIncludedServiceBrand(Integer includedServiceBrand) {
		this.includedServiceBrands.add(includedServiceBrand);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getExcludedServiceBrands()
	 */
	public Collection<Integer> getExcludedServiceBrands() {
		return excludedServiceBrands;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#addExcludedServiceBrand(java.lang.Integer)
	 */
	public void addExcludedServiceBrand(Integer excludedServiceBrand) {
		this.excludedServiceBrands.add(excludedServiceBrand);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getTariffs()
	 */
	public Collection<ITariff> getTariffs() {
		return tariffs;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#addTariff(org.uic.ticket.api.spec.ITariff)
	 */
	public void addTariff(ITariff tariff) {
		this.tariffs.add(tariff);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getInfoText()
	 */
	public String getInfoText() {
		return infoText;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setInfoText(java.lang.String)
	 */
	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getExtension()
	 */
	public IExtension getExtension() {
		return extension;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setExtension(org.uic.ticket.api.spec.IExtension)
	 */
	public void setExtension(IExtension extension) {
		this.extension = extension;
	}
  	
	/** The valid from utc coffset. */
	protected Long validFromUTCoffset;
	
	/** The valid until utc coffset. */
	protected Long validUntilUTCoffset;
	

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getValidFromUTCoffset()
	 */
	public Long getValidFromUTCoffset() {
		return validFromUTCoffset;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setValidFromUTCoffset(java.lang.Long)
	 */
	public void setValidFromUTCoffset(Long validFromUTCoffset) {
		this.validFromUTCoffset = validFromUTCoffset;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#getValidUntilUTCoffset()
	 */
	public Long getValidUntilUTCoffset() {
		return validUntilUTCoffset;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIncludedOpenTicket#setValidUntilUTCoffset(java.lang.Long)
	 */
	public void setValidUntilUTCoffset(Long validUntilUTCoffset) {
		this.validUntilUTCoffset = validUntilUTCoffset;
	}

	@Override
	public void setUntilDate(Date date) {
		this.validUntil = date;
	}

	@Override
	public Collection<Integer> getExcludedTransportTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addExcludedTransportType(Integer excludedTransportType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Integer> getIncludedTransportTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addInludedTransportType(Integer includedTransportType) {
		// TODO Auto-generated method stub
		
	}

}
