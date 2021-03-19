/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.ICarCarriageReservation;
import org.uic.barcode.ticket.api.spec.ICompartmentDetails;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.ILoadingDeckType;
import org.uic.barcode.ticket.api.spec.IPriceTypeType;
import org.uic.barcode.ticket.api.spec.IRoofRackType;
import org.uic.barcode.ticket.api.spec.IServiceBrand;
import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.ITariff;
import org.uic.barcode.ticket.api.spec.IVatDetail;

/**
 * The Class SimpleCarCarriageReservation.
 */
public class SimpleCarCarriageReservation extends SimpleDocumentData implements ICarCarriageReservation {
	
	/** The train. */
	protected String train;  						
	

	/** The reference. */
	protected String    reference;
		
	/** The product id. */
	protected String productId;
	
	/** The product owner. */
	protected String productOwner;
  	
    /** The service brand. */
    protected IServiceBrand serviceBrand;

	    
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
    
    /** The coach. */
    protected String coach;     
    
    /** The place. */
    protected String place;     
    
    /** The carriers. */
    protected Collection<String>carriers = new LinkedHashSet<String>();
    
	
    /** The tariff. */
    protected ITariff tariff;	        
	    
	/** The service level. */
	protected String   serviceLevel;
	
	/** The compartment details. */
	protected ICompartmentDetails compartmentDetails;

    /** The number plate. */
    protected String numberPlate;
    
    /** The trailer plate. */
    protected String trailerPlate;
    
	/** The car category. */
	protected int carCategory;
	
	/** The boat category. */
	protected int boatCategory;
	
	/** The textile roof. */
	protected boolean textileRoof	= false;
	
	/** The roof rack type. */
	protected IRoofRackType roofRackType	= IRoofRackType.norack;
										 		  
	/** The roof rack height. */
	protected int roofRackHeight;
	
	/** The attached boats. */
	protected int attachedBoats;
	
	/** The attached bicycles. */
	protected int attachedBicycles;
	
	/** The attached surfboards. */
	protected int attachedSurfboards;

  									
	/** The begin loading. */
	protected Date beginLoading;
	
	/** The end loading. */
	protected Date endLoading;

	/** The loading list entry. */
	protected int loadingListEntry;
	
	/** The loading deck. */
	protected ILoadingDeckType loadingDeck = ILoadingDeckType.upper; 	

    /** The price type. */
    protected IPriceTypeType priceType = IPriceTypeType.travelPrice;
    
    /** The VAT details. */
    protected Collection<IVatDetail>vatDetails = new LinkedHashSet<IVatDetail>();
    
    /** The price. */
    protected Long price;
         					                
		                    
 	/** The info text. */
	 protected String infoText;
	 	
  	/** The extension. */
	  protected IExtension   extension;

	/** The loading time ut coffset. */
	protected Long loadingTimeUTCoffset;  
	  
	  
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getTrain()
	 */
	public String getTrain() {
		return train;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setTrain(java.lang.String)
	 */
	public void setTrain(String train) {
		this.train = train.trim();
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getReference()
	 */
	public String getReference() {
		return reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setReference(java.lang.String)
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getProductId()
	 */
	public String getProductId() {
		return productId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setProductId(java.lang.String)
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getServiceBrand()
	 */
	public IServiceBrand getServiceBrand() {
		return serviceBrand;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setServiceBrand(int)
	 */
	public void setServiceBrand(IServiceBrand serviceBrand) {
		this.serviceBrand = serviceBrand;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getStationCodeTable()
	 */
	public IStationCodeTable getStationCodeTable() {
		return stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setStationCodeTable(org.uic.ticket.api.asn.om.CodeTableType)
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable) {
		this.stationCodeTable = stationCodeTable;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getFromStation()
	 */
	public String getFromStation() {
		return fromStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setFromStation(java.lang.String)
	 */
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getToStation()
	 */
	public String getToStation() {
		return toStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setToStation(java.lang.String)
	 */
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getFromStationName()
	 */
	public String getFromStationName() {
		return fromStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setFromStationName(java.lang.String)
	 */
	public void setFromStationName(String fromStationName) {
		this.fromStationName = fromStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getToStationName()
	 */
	public String getToStationName() {
		return toStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setToStationName(java.lang.String)
	 */
	public void setToStationName(String toStationName) {
		this.toStationName = toStationName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getCarriers()
	 */
	public Collection<String> getCarriers() {
		return carriers;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#addCarrier(java.lang.Integer)
	 */
	public void addCarrier(String carrier) {
		this.carriers.add(carrier);
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getServiceLevel()
	 */
	public String getServiceLevel() {
		return serviceLevel;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setServiceLevel(java.lang.String)
	 */
	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getCompartmentDetails()
	 */
	public ICompartmentDetails getCompartmentDetails() {
		return compartmentDetails;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setCompartmentDetails(org.uic.ticket.api.spec.ICompartmentDetails)
	 */
	public void setCompartmentDetails(ICompartmentDetails compartmentDetails) {
		this.compartmentDetails = compartmentDetails;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getPriceType()
	 */
	public IPriceTypeType getPriceType() {
		return priceType;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setPriceType(org.uic.ticket.api.asn.om.PriceTypeType)
	 */
	public void setPriceType(IPriceTypeType priceType) {
		this.priceType = priceType;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getInfoText()
	 */
	public String getInfoText() {
		return infoText;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setInfoText(java.lang.String)
	 */
	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getExtension()
	 */
	public IExtension getExtension() {
		return extension;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setExtension(org.uic.ticket.api.spec.IExtension)
	 */
	public void setExtension(IExtension extension) {
		this.extension = extension;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getCoach()
	 */
	public String getCoach() {
		return coach;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setCoach(java.lang.String)
	 */
	public void setCoach(String coach) {
		this.coach = coach;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getPlace()
	 */
	public String getPlace() {
		return place;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setPlace(java.lang.String)
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getTariff()
	 */
	public ITariff getTariff() {
		return tariff;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setTariff(org.uic.ticket.api.spec.ITariff)
	 */
	public void setTariff(ITariff tariff) {
		this.tariff = tariff;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getNumberPlate()
	 */
	public String getNumberPlate() {
		return numberPlate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setNumberPlate(java.lang.String)
	 */
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getTrailerPlate()
	 */
	public String getTrailerPlate() {
		return trailerPlate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setTrailerPlate(java.lang.String)
	 */
	public void setTrailerPlate(String trailerPlate) {
		this.trailerPlate = trailerPlate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getCarCategory()
	 */
	public int getCarCategory() {
		return carCategory;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setCarCategory(int)
	 */
	public void setCarCategory(int carCategory) {
		this.carCategory = carCategory;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getBoatCategory()
	 */
	public int getBoatCategory() {
		return boatCategory;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setBoatCategory(int)
	 */
	public void setBoatCategory(int boatCategory) {
		this.boatCategory = boatCategory;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#isTextileRoof()
	 */
	public boolean isTextileRoof() {
		return textileRoof;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setTextileRoof(boolean)
	 */
	public void setTextileRoof(boolean textileRoof) {
		this.textileRoof = textileRoof;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getRoofRackType()
	 */
	public IRoofRackType getRoofRackType() {
		return roofRackType;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setRoofRackType(org.uic.ticket.api.asn.om.RoofRackType)
	 */
	public void setRoofRackType(IRoofRackType roofRackType) {
		this.roofRackType = roofRackType;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getRoofRackHeight()
	 */
	public int getRoofRackHeight() {
		return roofRackHeight;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setRoofRackHeight(int)
	 */
	public void setRoofRackHeight(int roofRackHeight) {
		this.roofRackHeight = roofRackHeight;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getAttachedBoats()
	 */
	public int getAttachedBoats() {
		return attachedBoats;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setAttachedBoats(int)
	 */
	public void setAttachedBoats(int attachedBoats) {
		this.attachedBoats = attachedBoats;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getAttachedBicycles()
	 */
	public int getAttachedBicycles() {
		return attachedBicycles;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setAttachedBicycles(int)
	 */
	public void setAttachedBicycles(int attachedBicycles) {
		this.attachedBicycles = attachedBicycles;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getAttachedSurfboards()
	 */
	public int getAttachedSurfboards() {
		return attachedSurfboards;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setAttachedSurfboards(int)
	 */
	public void setAttachedSurfboards(int attachedSurfboards) {
		this.attachedSurfboards = attachedSurfboards;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getBeginLoading()
	 */
	public Date getBeginLoading() {
		return beginLoading;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setBeginLoading(java.util.Date)
	 */
	public void setBeginLoading(Date beginLoading) {
		this.beginLoading = beginLoading;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getEndLoading()
	 */
	public Date getEndLoading() {
		return endLoading;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setEndLoading(java.util.Date)
	 */
	public void setEndLoading(Date endLoading) {
		this.endLoading = endLoading;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getLoadingListEntry()
	 */
	public int getLoadingListEntry() {
		return loadingListEntry;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setLoadingListEntry(int)
	 */
	public void setLoadingListEntry(int loadingListEntry) {
		this.loadingListEntry = loadingListEntry;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getLoadingDeck()
	 */
	public ILoadingDeckType getLoadingDeck() {
		return loadingDeck;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setLoadingDeck(org.uic.ticket.api.asn.om.LoadingDeckType)
	 */
	public void setLoadingDeck(ILoadingDeckType loadingDeck) {
		this.loadingDeck = loadingDeck;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getProductOwner()
	 */
	public String getProductOwner() {
		return productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setProductOwner(java.lang.String)
	 */
	public void setProductOwner(String productOwner) {
		this.productOwner = productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setPrice(java.lang.Long)
	 */
	@Override
	public void setPrice(Long price) {
		this.price = price;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getPrice()
	 */
	@Override
	public Long getPrice() {
		return price;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getVatDetails()
	 */
	@Override
	public Collection<IVatDetail> getVatDetails() {
		return vatDetails;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#addVatDetail(org.uic.ticket.api.spec.IVatDetail)
	 */
	@Override
	public void addVatDetail(IVatDetail vatDetail) {
		this.vatDetails.add(vatDetail);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#getLoadingTimeUTCoffset()
	 */
	public Long getLoadingTimeUTCoffset() {
		return loadingTimeUTCoffset;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICarCarriageReservation#setLoadingTimeUTCoffset(java.lang.Long)
	 */
	public void setLoadingTimeUTCoffset(Long loadingTimeUTCoffset) {
		this.loadingTimeUTCoffset = loadingTimeUTCoffset;
	}

	

}
