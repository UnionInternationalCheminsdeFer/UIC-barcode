/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.ICounterMark;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IRegionalValidity;
import org.uic.barcode.ticket.api.spec.IReturnRouteDescription;
import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.ITravelClassType;


/**
 * The Class SimpleCounterMark.
 */
public class SimpleCounterMark extends SimpleDocumentData implements ICounterMark {

	/** The departure date. */
	protected Date   departureDate;
	
	/** The arrival date. */
	protected Date   arrivalDate;		
 											
	/** The reference. */
	protected String reference;
	
	/** The product owner. */
	protected String productOwner;
	
	
	/** The product id. */
	protected String productId;	
	
   	/** The number of countermark. */
	   protected int numberOfCountermark;
   	
	   /** The total of countermarks. */
	   protected int totalOfCountermarks;
   	
	   /** The group name. */
	   protected String groupName;
	
	/** The return included. */
	protected boolean returnIncluded = false;

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
    
    /** The return description. */
    protected IReturnRouteDescription returnDescription;
    
	/** The valid from. */
	protected Date validFrom;
	
	/** The valid until. */
	protected Date  validUntil;
	  
	/** The class code. */
	protected ITravelClassType	classCode = ITravelClassType.second;       
   
	/** The included carriers. */
	protected Collection<String>includedCarriers = new LinkedHashSet<String>();	  
	
	/** The included service brands. */
	protected Collection<Integer>includedServiceBrands = new LinkedHashSet<Integer>();	
	
	/** The excluded service brands. */
	protected Collection<Integer>excludedServiceBrands = new LinkedHashSet<Integer>();	    			                
        
 	/** The info text. */
	 protected String infoText;

	 	
  	/** The extension. */
	  protected IExtension   extension;
	  
	  
	  /** The ticket reference. */
  	protected String ticketReference;


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getReference()
	 */
	public String getReference() {
		return reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setReference(java.lang.String)
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#isReturnIncluded()
	 */
	public boolean isReturnIncluded() {
		return returnIncluded;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setReturnIncluded(boolean)
	 */
	public void setReturnIncluded(boolean returnIncluded) {
		this.returnIncluded = returnIncluded;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getStationCodeTable()
	 */
	public IStationCodeTable getStationCodeTable() {
		return stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setStationCodeTable(org.uic.ticket.api.asn.om.CodeTableType)
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable) {
		this.stationCodeTable = stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getFromStation()
	 */
	public String getFromStation() {
		return fromStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setFromStation(java.lang.String)
	 */
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getToStation()
	 */
	public String getToStation() {
		return toStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setToStation(java.lang.String)
	 */
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getFromStationName()
	 */
	public String getFromStationName() {
		return fromStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setFromStationName(java.lang.String)
	 */
	public void setFromStationName(String fromStationName) {
		this.fromStationName = fromStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getToStationName()
	 */
	public String getToStationName() {
		return toStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setToStationName(java.lang.String)
	 */
	public void setToStationName(String toStationName) {
		this.toStationName = toStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getValidRegionDesc()
	 */
	public String getValidRegionDesc() {
		return validRegionDesc;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setValidRegionDesc(java.lang.String)
	 */
	public void setValidRegionDesc(String validRegionDesc) {
		this.validRegionDesc = validRegionDesc;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getValidRegionList()
	 */
	public Collection<IRegionalValidity> getValidRegionList() {
		return validRegionList;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#addValidRegionList(org.uic.ticket.api.spec.IRegionalValidity)
	 */
	public void addValidRegionList(IRegionalValidity validRegion) {
		this.validRegionList.add(validRegion);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getReturnDescription()
	 */
	public IReturnRouteDescription getReturnDescription() {
		return returnDescription;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setReturnDescription(org.uic.ticket.api.spec.IReturnRouteDescription)
	 */
	public void setReturnDescription(IReturnRouteDescription returnDescription) {
		this.returnDescription = returnDescription;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getValidFrom()
	 */
	public Date getValidFrom() {
		return validFrom;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setValidFrom(java.util.Date)
	 */
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getValidUntil()
	 */
	public Date getValidUntil() {
		return validUntil;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setValidUntil(java.util.Date)
	 */
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getClassCode()
	 */
	public ITravelClassType getClassCode() {
		return classCode;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setClassCode(org.uic.ticket.api.asn.om.TravelClassType)
	 */
	public void setClassCode(ITravelClassType classCode) {
		this.classCode = classCode;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getIncludedCarriers()
	 */
	public Collection<String> getIncludedCarriers() {
		return includedCarriers;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#addIncludedCarrier(java.lang.Integer)
	 */
	public void addIncludedCarrier(String carrier) {
		this.includedCarriers.add(carrier);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getIncludedServiceBrands()
	 */
	public Collection<Integer> getIncludedServiceBrands() {
		return includedServiceBrands;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#addIncludedServiceBrand(java.lang.Integer)
	 */
	public void addIncludedServiceBrand(Integer includedServiceBrand) {
		this.includedServiceBrands.add(includedServiceBrand);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getExcludedServiceBrands()
	 */
	public Collection<Integer> getExcludedServiceBrands() {
		return excludedServiceBrands;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#addExcludedServiceBrand(java.lang.Integer)
	 */
	public void addExcludedServiceBrand(Integer excludedServiceBrand) {
		this.excludedServiceBrands.add(excludedServiceBrand);
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getInfoText()
	 */
	public String getInfoText() {
		return infoText;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setInfoText(java.lang.String)
	 */
	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}



	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getExtension()
	 */
	public IExtension getExtension() {
		return extension;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setExtension(org.uic.ticket.api.spec.IExtension)
	 */
	public void setExtension(IExtension extension) {
		this.extension = extension;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getTotalOfCountermarks()
	 */
	public int getTotalOfCountermarks() {
		return totalOfCountermarks;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setTotalOfCountermarks(int)
	 */
	public void setTotalOfCountermarks(int totalOfCountermarks) {
		this.totalOfCountermarks = totalOfCountermarks;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getGroupName()
	 */
	public String getGroupName() {
		return groupName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setGroupName(java.lang.String)
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getNumberOfCountermark()
	 */
	public int getNumberOfCountermark() {
		return numberOfCountermark;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setNumberOfCountermark(int)
	 */
	public void setNumberOfCountermark(int numberOfCountermark) {
		this.numberOfCountermark = numberOfCountermark;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getProductOwner()
	 */
	public String getProductOwner() {
		return productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setProductOwner(java.lang.String)
	 */
	public void setProductOwner(String productOwner) {
		this.productOwner = productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getTicketReference()
	 */
	public String getTicketReference() {
		return ticketReference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setTicketReference(java.lang.String)
	 */
	public void setTicketReference(String ticketReference) {
		this.ticketReference = ticketReference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getProductId()
	 */
	public String getProductId() {
		return productId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setProductId(java.lang.String)
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/** The valid from utc coffset. */
	protected Long validFromUTCoffset;
	
	/** The valid until utc coffset. */
	protected Long validUntilUTCoffset;
	

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getValidFromUTCoffset()
	 */
	public Long getValidFromUTCoffset() {
		return validFromUTCoffset;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setValidFromUTCoffset(java.lang.Long)
	 */
	public void setValidFromUTCoffset(Long validFromUTCoffset) {
		this.validFromUTCoffset = validFromUTCoffset;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#getValidUntilUTCoffset()
	 */
	public Long getValidUntilUTCoffset() {
		return validUntilUTCoffset;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICounterMark#setValidUntilUTCoffset(java.lang.Long)
	 */
	public void setValidUntilUTCoffset(Long validUntilUTCoffset) {
		this.validUntilUTCoffset = validUntilUTCoffset;
	}
	
}
