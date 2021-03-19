/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.IBerth;
import org.uic.barcode.ticket.api.spec.ICompartmentDetails;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.ILuggageRestriction;
import org.uic.barcode.ticket.api.spec.IPlaces;
import org.uic.barcode.ticket.api.spec.IPriceTypeType;
import org.uic.barcode.ticket.api.spec.IReservation;
import org.uic.barcode.ticket.api.spec.IServiceBrand;
import org.uic.barcode.ticket.api.spec.IServiceType;
import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.ITariff;
import org.uic.barcode.ticket.api.spec.ITravelClassType;
import org.uic.barcode.ticket.api.spec.IVatDetail;


/**
 * The Class SimpleReservation.
 */
public class SimpleReservation extends SimpleDocumentData implements IReservation {
		
		/** The train. */
		protected String train;  						
		
		/** The departure date. */
		protected Date   departureDate;
		
		/** The arrival date. */
		protected Date   arrivalDate;		
    	 											
		/** The reference. */
		protected String reference;
    		
		/** The product id. */
		protected String productId;
		
		/** The product owner. */
		protected String productOwner;
      	
	    /** The service brand. */
    	protected IServiceBrand serviceBrand;
	    
		/** The service. */
		protected IServiceType 	service = IServiceType.seat;		
		   	
		/** The service brand description. */
		protected String serviceBrandDescription;
		   	
		/** The service brand abbreviation. */
		protected String serviceBrandAbbreviation;

		    
        /** The station code table. */
        protected IStationCodeTable stationCodeTable = IStationCodeTable.stationUICReservation;
        
        /** The from station. */
        protected String fromStation;
        
        /** The to station. */
        protected String toStation;        
  					                 
        /** The from station name. */
        protected String fromStationName;
        
        /** The to station name. */
        protected String toStationName;         
        
        /** The carriers. */
        protected Collection<String>carriers = new LinkedHashSet<String>();
        
        /** The berths. */
        protected Collection<IBerth> berths  = new LinkedHashSet<IBerth>();
		
        /** The tariffs. */
        protected Collection<ITariff> tariffs = new LinkedHashSet<ITariff>();	        
		    
		/** The class code. */
		protected ITravelClassType	classCode = ITravelClassType.second;

		/** The service level. */
		protected String   serviceLevel;

		/** The additional places of a second coach. */
		protected IPlaces additionalPlaces;		
		
		/** The places. */
		protected IPlaces places;
		
		/** The bicycle places. */
		protected IPlaces bicyclePlaces;

		/** The compartment details. */
		protected ICompartmentDetails compartmentDetails;
		
		/** The number of overbooked. */
		protected int numberOfOverbooked;
		
	
		
        /** The price type. */
        protected IPriceTypeType priceType = IPriceTypeType.travelPrice;
        
        /** The type of supplement. */
        protected int typeOfSupplement;				
        
        /** The number of supplements. */
        protected int numberOfSupplements;  			
        
        /** The VAT details. */
        protected Collection<IVatDetail>vatDetails = new LinkedHashSet<IVatDetail>();
        
        /** The price. */
        protected Long price;        
			                    
	 	/** The info text. */
	 	protected String infoText;
			
		/** The luggage restriction. */
		protected  ILuggageRestriction luggageRestriction;
		 	
	  	/** The extension. */
	  	protected IExtension   extension;
	  	
	  	/** The departure ut coffset. */
	  	protected Long departureUTCoffset;
	  	
	  	/** The arrival ut coffset. */
	  	protected Long arrivalUTCoffset;

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getTrain()
		 */
		public String getTrain() {
			return train;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setTrain(java.lang.String)
		 */
		public void setTrain(String train) {
			this.train = train.trim();
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getDepartureDate()
		 */
		public Date getDepartureDate() {
			return departureDate;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setDepartureDate(java.util.Date)
		 */
		public void setDepartureDate(Date departureDate) {
			this.departureDate = departureDate;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getArrivalDate()
		 */
		public Date getArrivalDate() {
			return arrivalDate;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setArrivalDate(java.util.Date)
		 */
		public void setArrivalDate(Date arrivalDate) {
			this.arrivalDate = arrivalDate;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getReference()
		 */
		public String getReference() {
			return reference;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setReference(java.lang.String)
		 */
		public void setReference(String reference) {
			this.reference = reference;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getProductId()
		 */
		public String getProductId() {
			return productId;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setProductId(java.lang.String)
		 */
		public void setProductId(String productId) {
			this.productId = productId;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getServiceBrand()
		 */
		public IServiceBrand getServiceBrand() {
			return serviceBrand;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setServiceBrand(int)
		 */
		public void setServiceBrand(IServiceBrand serviceBrand) {
			this.serviceBrand = serviceBrand;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getService()
		 */
		public IServiceType getService() {
			return service;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setService(org.uic.ticket.api.asn.om.ServiceType)
		 */
		public void setService(IServiceType service) {
			this.service = service;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getStationCodeTable()
		 */
		public IStationCodeTable getStationCodeTable() {
			return stationCodeTable;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setStationCodeTable(org.uic.ticket.api.asn.om.CodeTableType)
		 */
		public void setStationCodeTable(IStationCodeTable stationCodeTable) {
			this.stationCodeTable = stationCodeTable;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getFromStation()
		 */
		public String getFromStation() {
			return fromStation;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setFromStation(java.lang.String)
		 */
		public void setFromStation(String fromStation) {
			this.fromStation = fromStation;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getToStation()
		 */
		public String getToStation() {
			return toStation;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setToStation(java.lang.String)
		 */
		public void setToStation(String toStation) {
			this.toStation = toStation;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getFromStationName()
		 */
		public String getFromStationName() {
			return fromStationName;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setFromStationName(java.lang.String)
		 */
		public void setFromStationName(String fromStationName) {
			this.fromStationName = fromStationName;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getToStationName()
		 */
		public String getToStationName() {
			return toStationName;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setToStationName(java.lang.String)
		 */
		public void setToStationName(String toStationName) {
			this.toStationName = toStationName;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getCarriers()
		 */
		public Collection<String> getCarriers() {
			return carriers;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#addCarrier(java.lang.Integer)
		 */
		public void addCarrier(String carrier) {
			this.carriers.add(carrier);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getClassCode()
		 */
		public ITravelClassType getClassCode() {
			return classCode;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setClassCode(org.uic.ticket.api.asn.om.TravelClassType)
		 */
		public void setClassCode(ITravelClassType classCode) {
			this.classCode = classCode;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getServiceLevel()
		 */
		public String getServiceLevel() {
			return serviceLevel;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setServiceLevel(java.lang.String)
		 */
		public void setServiceLevel(String serviceLevel) {
			this.serviceLevel = serviceLevel;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getPlaces()
		 */
		public IPlaces getPlaces() {
			return places;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setPlaces(org.uic.ticket.api.spec.IPlaces)
		 */
		public void setPlaces(IPlaces places) {
			this.places = places;
		}

		
		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getAdditionalPlaces()
		 */
		public IPlaces getAdditionalPlaces() {
			return additionalPlaces;
		}


		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setAdditionalPlaces(org.uic.ticket.api.spec.IPlaces)
		 */
		public void setAdditionalPlaces(IPlaces places) {
			this.additionalPlaces = places;
		}		
		
		
		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getBicyclePlaces()
		 */
		public IPlaces getBicyclePlaces() {
			return bicyclePlaces;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setBicyclePlaces(org.uic.ticket.api.spec.IPlaces)
		 */
		public void setBicyclePlaces(IPlaces bicyclePlaces) {
			this.bicyclePlaces = bicyclePlaces;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getCompartmentDetails()
		 */
		public ICompartmentDetails getCompartmentDetails() {
			return compartmentDetails;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setCompartmentDetails(org.uic.ticket.api.spec.ICompartmentDetails)
		 */
		public void setCompartmentDetails(ICompartmentDetails compartmentDetails) {
			this.compartmentDetails = compartmentDetails;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getNumberOfOverbooked()
		 */
		public int getNumberOfOverbooked() {
			return numberOfOverbooked;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setNumberOfOverbooked(int)
		 */
		public void setNumberOfOverbooked(int numberOfOverbooked) {
			this.numberOfOverbooked = numberOfOverbooked;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getBerths()
		 */
		public Collection<IBerth> getBerths() {
			return berths;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#addBerth(org.uic.ticket.api.spec.IBerth)
		 */
		public void addBerth(IBerth berth) {
			this.berths.add(berth);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getTariffs()
		 */
		public Collection<ITariff> getTariffs() {
			return tariffs;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#addTariff(org.uic.ticket.api.spec.ITariff)
		 */
		public void addTariff(ITariff tariff) {
			this.tariffs.add(tariff);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getPriceType()
		 */
		public IPriceTypeType getPriceType() {
			return priceType;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setPriceType(org.uic.ticket.api.asn.om.PriceTypeType)
		 */
		public void setPriceType(IPriceTypeType priceType) {
			this.priceType = priceType;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getTypeOfSupplement()
		 */
		public int getTypeOfSupplement() {
			return typeOfSupplement;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setTypeOfSupplement(int)
		 */
		public void setTypeOfSupplement(int typeOfSupplement) {
			this.typeOfSupplement = typeOfSupplement;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getNumberOfSupplements()
		 */
		public int getNumberOfSupplements() {
			return numberOfSupplements;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setNumberOfSupplements(int)
		 */
		public void setNumberOfSupplements(int numberOfSupplements) {
			this.numberOfSupplements = numberOfSupplements;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getInfoText()
		 */
		public String getInfoText() {
			return infoText;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setInfoText(java.lang.String)
		 */
		public void setInfoText(String infoText) {
			this.infoText = infoText;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getLuggageRestriction()
		 */
		public ILuggageRestriction getLuggageRestriction() {
			return luggageRestriction;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setLuggageRestriction(org.uic.ticket.api.spec.ILuggageRestriction)
		 */
		public void setLuggageRestriction(ILuggageRestriction luggageRestriction) {
			this.luggageRestriction = luggageRestriction;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getExtension()
		 */
		public IExtension getExtension() {
			return extension;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setExtension(org.uic.ticket.api.spec.IExtension)
		 */
		public void setExtension(IExtension extension) {
			this.extension = extension;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getProductOwner()
		 */
		public String getProductOwner() {
			return productOwner;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setProductOwner(java.lang.String)
		 */
		public void setProductOwner(String productOwner) {
			this.productOwner = productOwner;
		}

		
		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setPrice(java.lang.Long)
		 */
		@Override
		public void setPrice(Long price) {
			this.price = price;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getPrice()
		 */
		@Override
		public Long getPrice() {
			return price;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getVatDetails()
		 */
		@Override
		public Collection<IVatDetail> getVatDetails() {
			return vatDetails;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#addVatDetail(org.uic.ticket.api.spec.IVatDetail)
		 */
		@Override
		public void addVatDetail(IVatDetail vatDetail) {
			this.vatDetails.add(vatDetail);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getDepartureUTCoffset()
		 */
		public Long getDepartureUTCoffset() {
			return departureUTCoffset;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setDepartureUTCoffset(java.lang.Long)
		 */
		public void setDepartureUTCoffset(Long departureUTCoffset) {
			this.departureUTCoffset = departureUTCoffset;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#getArrivalUTCoffset()
		 */
		public Long getArrivalUTCoffset() {
			return arrivalUTCoffset;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IReservation#setArrivalUTCoffset(java.lang.Long)
		 */
		public void setArrivalUTCoffset(Long arrivalUTCoffset) {
			this.arrivalUTCoffset = arrivalUTCoffset;
		}
		
		
		
	  	
}
