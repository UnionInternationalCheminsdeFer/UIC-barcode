/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IPass;
import org.uic.barcode.ticket.api.spec.IRegionalValidity;
import org.uic.barcode.ticket.api.spec.ITariff;
import org.uic.barcode.ticket.api.spec.ITravelClassType;
import org.uic.barcode.ticket.api.spec.IValidityDetails;
import org.uic.barcode.ticket.api.spec.IVatDetail;


/**
 * The Class SimplePass.
 */
public class SimplePass extends SimpleDocumentData implements IPass {
	
	/** The reference. */
	protected String reference;
	
	/** The product id. */
	protected String productId;

	/** The product owner. */
	protected String productOwner;
	
	/** The class code. */
	protected ITravelClassType	classCode = ITravelClassType.second;      
	
	/** The valid from. */
	protected Date validFrom;
	
	/** The valid until. */
	protected Date  validUntil;	
	
 	/** The info text. */
	 protected String infoText;
	 	
  	/** The extension. */
	  protected IExtension   extension;	
		
	
		/** The pass type. */
		protected int passType;
    
		/** The pass description. */
		protected String passDescription;
    
                                                                                            
		/** The number of validity days. */
		protected int numberOfValidityDays;  

		/** The number of possible trips. */
		protected int numberOfPossibleTrips; 

		/** The number of days of travel. */
		protected int numberOfDaysOfTravel;  
		
    	
        /** The activated days. */
        protected Collection<Date> activatedDays = new LinkedHashSet<Date>();  	
        
    	/** The countries. */
	    protected Collection<Integer>countries = new LinkedHashSet<Integer>();	        

    	/** The included carriers. */
	    protected Collection<String>includedCarriers = new LinkedHashSet<String>();	  

    	/** The excluded carriers. */
	    protected Collection<String>excludedCarriers = new LinkedHashSet<String>();	 	    
	    
	    /** The included service brands. */
	    protected Collection<Integer>includedServiceBrands = new LinkedHashSet<Integer>();	
    	
	    /** The excluded service brands. */
	    protected Collection<Integer>excludedServiceBrands = new LinkedHashSet<Integer>();	 
    	
    	/** The valid region list. */
	    protected Collection<IRegionalValidity> validRegionList = new LinkedHashSet<IRegionalValidity>();    	
    	
        /** The tariffs. */
        protected Collection<ITariff> tariffs = new LinkedHashSet<ITariff>();	   
	
        
        
        /** The validity details. */
        protected IValidityDetails validityDetails;
        
        /** The VAT details. */
        protected Collection<IVatDetail>vatDetails = new LinkedHashSet<IVatDetail>();
        
        /** The price. */
        protected Long price;        


		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getPassType()
		 */
		public int getPassType() {
			return passType;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setPassType(int)
		 */
		public void setPassType(int passType) {
			this.passType = passType;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getPassDescription()
		 */
		public String getPassDescription() {
			return passDescription;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setPassDescription(java.lang.String)
		 */
		public void setPassDescription(String passDescription) {
			this.passDescription = passDescription;
		}


		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getNumberOfValidityDays()
		 */
		public int getNumberOfValidityDays() {
			return numberOfValidityDays;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setNumberOfValidityDays(int)
		 */
		public void setNumberOfValidityDays(int numberOfValidityDays) {
			this.numberOfValidityDays = numberOfValidityDays;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getNumberOfPossibleTrips()
		 */
		public int getNumberOfPossibleTrips() {
			return numberOfPossibleTrips;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setNumberOfPossibleTrips(int)
		 */
		public void setNumberOfPossibleTrips(int numberOfPossibleTrips) {
			this.numberOfPossibleTrips = numberOfPossibleTrips;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getNumberOfDaysOfTravel()
		 */
		public int getNumberOfDaysOfTravel() {
			return numberOfDaysOfTravel;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setNumberOfDaysOfTravel(int)
		 */
		public void setNumberOfDaysOfTravel(int numberOfDaysOfTravel) {
			this.numberOfDaysOfTravel = numberOfDaysOfTravel;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getActivatedDays()
		 */
		public Collection<Date> getActivatedDays() {
			return activatedDays;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#addActivatedDay(java.util.Date)
		 */
		public void addActivatedDay(Date activatedDay) {
			this.activatedDays.add(activatedDay);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getCountries()
		 */
		public Collection<Integer> getCountries() {
			return countries;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#addCountry(java.lang.Integer)
		 */
		public void addCountry(Integer country) {
			this.countries.add(country);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getIncludedCarriers()
		 */
		public Collection<String> getIncludedCarriers() {
			return includedCarriers;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#addIncludedCarrier(java.lang.Integer)
		 */
		public void addIncludedCarrier(String carrier) {
			this.includedCarriers.add(carrier);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getExcludedCarriers()
		 */
		public Collection<String> getExcludedCarriers() {
			return excludedCarriers;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#addIncludedCarrier(java.lang.Integer)
		 */
		public void addExcludedCarrier(String carrier) {
			this.excludedCarriers.add(carrier);
		}		
		
		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getIncludedServiceBrands()
		 */
		public Collection<Integer> getIncludedServiceBrands() {
			return includedServiceBrands;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#addIncludedServiceBrand(java.lang.Integer)
		 */
		public void addIncludedServiceBrand(Integer includedServiceBrand) {
			this.includedServiceBrands.add(includedServiceBrand);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getExcludedServiceBrands()
		 */
		public Collection<Integer> getExcludedServiceBrands() {
			return excludedServiceBrands;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#addExcludedServiceBrand(java.lang.Integer)
		 */
		public void addExcludedServiceBrand(Integer excludedServiceBrand) {
			this.excludedServiceBrands.add(excludedServiceBrand);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getValidRegionList()
		 */
		public Collection<IRegionalValidity> getValidRegionList() {
			return validRegionList;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#addValidRegion(org.uic.ticket.api.spec.IRegionalValidity)
		 */
		public void addValidRegion(IRegionalValidity validRegion) {
			this.validRegionList.add(validRegion);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getTariffs()
		 */
		public Collection<ITariff> getTariffs() {
			return tariffs;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#addTariff(org.uic.ticket.api.spec.ITariff)
		 */
		public void addTariff(ITariff tariff) {
			this.tariffs.add(tariff);
		}


		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getReference()
		 */
		public String getReference() {
			return reference;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setReference(java.lang.String)
		 */
		public void setReference(String reference) {
			this.reference = reference;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getProductId()
		 */
		public String getProductId() {
			return productId;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setProductId(java.lang.String)
		 */
		public void setProductId(String productId) {
			this.productId = productId;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getProductOwner()
		 */
		public String getProductOwner() {
			return productOwner;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setProductOwner(java.lang.String)
		 */
		public void setProductOwner(String productOwner) {
			this.productOwner = productOwner;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getClassCode()
		 */
		public ITravelClassType getClassCode() {
			return classCode;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setClassCode(org.uic.ticket.api.asn.om.TravelClassType)
		 */
		public void setClassCode(ITravelClassType classCode) {
			this.classCode = classCode;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getValidFrom()
		 */
		public Date getValidFrom() {
			return validFrom;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setValidFrom(java.util.Date)
		 */
		public void setValidFrom(Date validFrom) {
			this.validFrom = validFrom;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getValidUntil()
		 */
		public Date getValidUntil() {
			return validUntil;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setValidUntil(java.util.Date)
		 */
		public void setValidUntil(Date validUntil) {
			this.validUntil = validUntil;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getInfoText()
		 */
		public String getInfoText() {
			return infoText;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setInfoText(java.lang.String)
		 */
		public void setInfoText(String infoText) {
			this.infoText = infoText;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getExtension()
		 */
		public IExtension getExtension() {
			return extension;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setExtension(org.uic.ticket.api.spec.IExtension)
		 */
		public void setExtension(IExtension extension) {
			this.extension = extension;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setValidityDetails(org.uic.ticket.api.spec.IValidityDetails)
		 */
		@Override
		public void setValidityDetails(IValidityDetails validityDetails) {
			this.validityDetails = validityDetails;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getValidityDetails()
		 */
		@Override
		public IValidityDetails getValidityDetails() {
			return validityDetails;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setPrice(java.lang.Long)
		 */
		@Override
		public void setPrice(Long price) {
			this.price = price;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getPrice()
		 */
		@Override
		public Long getPrice() {
			return price;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getVatDetails()
		 */
		@Override
		public Collection<IVatDetail> getVatDetails() {
			return vatDetails;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#addVatDetail(org.uic.ticket.api.spec.IVatDetail)
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
		 * @see org.uic.ticket.api.spec.IPass#getValidFromUTCoffset()
		 */
		public Long getValidFromUTCoffset() {
			return validFromUTCoffset;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setValidFromUTCoffset(java.lang.Long)
		 */
		public void setValidFromUTCoffset(Long validFromUTCoffset) {
			this.validFromUTCoffset = validFromUTCoffset;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#getValidUntilUTCoffset()
		 */
		public Long getValidUntilUTCoffset() {
			return validUntilUTCoffset;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IPass#setValidUntilUTCoffset(java.lang.Long)
		 */
		public void setValidUntilUTCoffset(Long validUntilUTCoffset) {
			this.validUntilUTCoffset = validUntilUTCoffset;
		}

		@Override
		public void setUntilDate(Date date) {
			this.setValidUntil(date);			
		}

}
