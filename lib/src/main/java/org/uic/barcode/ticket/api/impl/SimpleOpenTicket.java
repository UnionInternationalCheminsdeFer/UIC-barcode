/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IIncludedOpenTicket;
import org.uic.barcode.ticket.api.spec.ILuggageRestriction;
import org.uic.barcode.ticket.api.spec.IOpenTicket;
import org.uic.barcode.ticket.api.spec.IRegionalValidity;
import org.uic.barcode.ticket.api.spec.IReturnRouteDescription;
import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.ITariff;
import org.uic.barcode.ticket.api.spec.ITravelClassType;
import org.uic.barcode.ticket.api.spec.IVatDetail;


/**
 * The Class SimpleOpenTicket.
 */
public class SimpleOpenTicket  extends SimpleDocumentData implements IOpenTicket {
	
	/** The reference. */
	protected String reference;
	
	/** The product id. */
	protected String productId;

	/** The product owner. */
	protected String productOwner;
	
	
	/** The valid from. */
	protected Date validFrom;
	
	/** The valid until. */
	protected Date  validUntil;	
	
 	/** The info text. */
	 protected String infoText;
	 	
  	/** The extension. */
	  protected IExtension   extension;	
	
	
		/** The departure date. */
		protected Date   departureDate;
		
		/** The arrival date. */
		protected Date   arrivalDate;		
		
		/** The external issuer. */
		protected int externalIssuer;
		
		/** The authorization code. */
		protected int authorizationCode;	
	
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

    	
        /** The activated days. */
        protected Collection<Date> activatedDays = new LinkedHashSet<Date>();      
	    
		/** The class code. */
		protected ITravelClassType	classCode = ITravelClassType.second;       
		
		
		/** The service level. */
		protected String serviceLevel;
       
    	/** The included carriers. */
	    protected Collection<String>includedCarriers = new LinkedHashSet<String>();	  
    	
	    /** The included service brands. */
	    protected Collection<Integer>includedServiceBrands = new LinkedHashSet<Integer>();	
    	
	    /** The excluded service brands. */
	    protected Collection<Integer>excludedServiceBrands = new LinkedHashSet<Integer>();	    	

	    /** The included service brands. */
	    protected Collection<Integer>includedTransportTypes = new LinkedHashSet<Integer>();	
    	
	    /** The excluded service brands. */
	    protected Collection<Integer>excludedTransportTypes = new LinkedHashSet<Integer>();	   
	    
        /** The tariffs. */
        protected Collection<ITariff> tariffs = new LinkedHashSet<ITariff>();	   
        
	    /** The included add ons. */
    	protected Collection<IIncludedOpenTicket>includedAddOns = new LinkedHashSet<IIncludedOpenTicket>();
	
		
		/** The luggage restriction. */
		protected  ILuggageRestriction luggageRestriction;

		/** The price. */
		protected Long price;
		
	    /** The included add ons. */
    	protected Collection<IVatDetail> vatDetails = new LinkedHashSet<IVatDetail>();
		 	


		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getExternalIssuer()
		 */
		public int getExternalIssuer() {
			return externalIssuer;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setExternalIssuer(int)
		 */
		public void setExternalIssuer(int externalIssuer) {
			this.externalIssuer = externalIssuer;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getAuthorizationCode()
		 */
		public int getAuthorizationCode() {
			return authorizationCode;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setAuthorizationCode(int)
		 */
		public void setAuthorizationCode(int authorizationCode) {
			this.authorizationCode = authorizationCode;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#isReturnIncluded()
		 */
		public boolean isReturnIncluded() {
			return returnIncluded;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setReturnIncluded(boolean)
		 */
		public void setReturnIncluded(boolean returnIncluded) {
			this.returnIncluded = returnIncluded;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getStationCodeTable()
		 */
		public IStationCodeTable getStationCodeTable() {
			return stationCodeTable;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setStationCodeTable(org.uic.ticket.api.asn.om.CodeTableType)
		 */
		public void setStationCodeTable(IStationCodeTable stationCodeTable) {
			this.stationCodeTable = stationCodeTable;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getFromStation()
		 */
		public String getFromStation() {
			return fromStation;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setFromStation(java.lang.String)
		 */
		public void setFromStation(String fromStation) {
			this.fromStation = fromStation;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getToStation()
		 */
		public String getToStation() {
			return toStation;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setToStation(java.lang.String)
		 */
		public void setToStation(String toStation) {
			this.toStation = toStation;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getFromStationName()
		 */
		public String getFromStationName() {
			return fromStationName;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setFromStationName(java.lang.String)
		 */
		public void setFromStationName(String fromStationName) {
			this.fromStationName = fromStationName;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getToStationName()
		 */
		public String getToStationName() {
			return toStationName;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setToStationName(java.lang.String)
		 */
		public void setToStationName(String toStationName) {
			this.toStationName = toStationName;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getValidRegionDesc()
		 */
		public String getValidRegionDesc() {
			return validRegionDesc;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setValidRegionDesc(java.lang.String)
		 */
		public void setValidRegionDesc(String validRegionDesc) {
			this.validRegionDesc = validRegionDesc;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getValidRegionList()
		 */
		public Collection<IRegionalValidity> getValidRegionList() {
			return validRegionList;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#addValidRegionList(org.uic.ticket.api.spec.IRegionalValidity)
		 */
		public void addValidRegionList(IRegionalValidity validRegion) {
			this.validRegionList.add(validRegion);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getReturnDescription()
		 */
		public IReturnRouteDescription getReturnDescription() {
			return returnDescription;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setReturnDescription(org.uic.ticket.api.spec.IReturnRouteDescription)
		 */
		public void setReturnDescription(IReturnRouteDescription returnDescription) {
			this.returnDescription = returnDescription;
		}


		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getActivatedDays()
		 */
		public Collection<Date> getActivatedDays() {
			return activatedDays;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#addActivatedDay(java.util.Date)
		 */
		public void addActivatedDay(Date activatedDay) {
			this.activatedDays.add(activatedDay);
		}


		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getIncludedCarriers()
		 */
		public Collection<String> getIncludedCarriers() {
			return includedCarriers;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#addIncludedCarrier(java.lang.Integer)
		 */
		public void addIncludedCarrier(String includedCarrier) {
			this.includedCarriers.add(includedCarrier);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getIncludedServiceBrands()
		 */
		public Collection<Integer> getIncludedServiceBrands() {
			return includedServiceBrands;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#addIncludedServiceBrand(java.lang.Integer)
		 */
		public void addIncludedServiceBrand(Integer includedServiceBrand) {
			this.includedServiceBrands.add(includedServiceBrand);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getExcludedServiceBrands()
		 */
		public Collection<Integer> getExcludedServiceBrands() {
			return excludedServiceBrands;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#addExcludedServiceBrand(java.lang.Integer)
		 */
		public void addExcludedServiceBrand(Integer excludedServiceBrand) {
			this.excludedServiceBrands.add(excludedServiceBrand);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getTariffs()
		 */
		public Collection<ITariff> getTariffs() {
			return tariffs;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#addTariff(org.uic.ticket.api.spec.ITariff)
		 */
		public void addTariff(ITariff tariff) {
			this.tariffs.add(tariff);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getIncludedAddOns()
		 */
		public Collection<IIncludedOpenTicket> getIncludedAddOns() {
			return includedAddOns;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#addIncludedAddOn(org.uic.ticket.api.spec.IIncludedOpenTicket)
		 */
		public void addIncludedAddOn(IIncludedOpenTicket includedAddOn) {
			this.includedAddOns.add(includedAddOn);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getLuggageRestriction()
		 */
		public ILuggageRestriction getLuggageRestriction() {
			return luggageRestriction;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setLuggageRestriction(org.uic.ticket.api.spec.ILuggageRestriction)
		 */
		public void setLuggageRestriction(ILuggageRestriction luggageRestriction) {
			this.luggageRestriction = luggageRestriction;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getReference()
		 */
		public String getReference() {
			return reference;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setReference(java.lang.String)
		 */
		public void setReference(String reference) {
			this.reference = reference;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getProductId()
		 */
		public String getProductId() {
			return productId;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setProductId(java.lang.String)
		 */
		public void setProductId(String productId) {
			this.productId = productId;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getProductOwner()
		 */
		public String getProductOwner() {
			return productOwner;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setProductOwner(java.lang.String)
		 */
		public void setProductOwner(String productOwner) {
			this.productOwner = productOwner;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getValidFrom()
		 */
		public Date getValidFrom() {
			return validFrom;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setValidFrom(java.util.Date)
		 */
		public void setValidFrom(Date validFrom) {
			this.validFrom = validFrom;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getValidUntil()
		 */
		public Date getValidUntil() {
			return validUntil;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setValidUntil(java.util.Date)
		 */
		public void setValidUntil(Date validUntil) {
			this.validUntil = validUntil;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getInfoText()
		 */
		public String getInfoText() {
			return infoText;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setInfoText(java.lang.String)
		 */
		public void setInfoText(String infoText) {
			this.infoText = infoText;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getExtension()
		 */
		public IExtension getExtension() {
			return extension;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setExtension(org.uic.ticket.api.spec.IExtension)
		 */
		public void setExtension(IExtension extension) {
			this.extension = extension;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getClassCode()
		 */
		public ITravelClassType getClassCode() {
			return classCode;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setClassCode(org.uic.ticket.api.asn.om.TravelClassType)
		 */
		public void setClassCode(ITravelClassType classCode) {
			this.classCode = classCode;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setServiceLevel(java.lang.String)
		 */
		@Override
		public void setServiceLevel(String serviceLevel) {
			this.serviceLevel = serviceLevel;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getServiceLevel()
		 */
		@Override
		public String getServiceLevel() {
			return serviceLevel;
		}


		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setPrice(java.lang.Long)
		 */
		@Override
		public void setPrice(Long price) {
			this.price = price;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getPrice()
		 */
		@Override
		public Long getPrice() {
			return this.price;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getVatDetails()
		 */
		@Override
		public Collection<IVatDetail> getVatDetails() {
			return this.vatDetails;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#addVatDetail(org.uic.ticket.api.spec.IVatDetail)
		 */
		@Override
		public void addVatDetail(IVatDetail vatDetail) {
			this.vatDetails.add(vatDetail);
		}
	  	
		
		/** The valid from utc coffset. */
		protected Long validFromUTCoffset;
		
		/** The valid until utc coffset. */
		protected Long validUntilUTCoffset;
		
		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getValidFromUTCoffset()
		 */
		public Long getValidFromUTCoffset() {
			return validFromUTCoffset;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setValidFromUTCoffset(java.lang.Long)
		 */
		public void setValidFromUTCoffset(Long validFromUTCoffset) {
			this.validFromUTCoffset = validFromUTCoffset;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#getValidUntilUTCoffset()
		 */
		public Long getValidUntilUTCoffset() {
			return validUntilUTCoffset;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IOpenTicket#setValidUntilUTCoffset(java.lang.Long)
		 */
		public void setValidUntilUTCoffset(Long validUntilUTCoffset) {
			this.validUntilUTCoffset = validUntilUTCoffset;
		}

		@Override
		public Collection<Integer> getExcludedTransportTypes() {
			return excludedTransportTypes;
		}

		@Override
		public void addExcludedTransportType(Integer excludedTransportType) {
			excludedTransportTypes.add(excludedTransportType);
		}

		@Override
		public Collection<Integer> getIncludedTransportTypes() {
			return includedTransportTypes;
		}

		@Override
		public void addInludedTransportType(Integer includedTransportType) {
			includedTransportTypes.add(includedTransportType);		
		}

}
