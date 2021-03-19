/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.ICardReference;
import org.uic.barcode.ticket.api.spec.IPassengerType;
import org.uic.barcode.ticket.api.spec.IRouteSection;
import org.uic.barcode.ticket.api.spec.ISeriesDataDetails;
import org.uic.barcode.ticket.api.spec.ITariff;


/**
 * The Class SimpleTariff.
 */
public class SimpleTariff implements ITariff {

    /** The number of passengers. */
    protected int numberOfPassengers = 1;
    
	/** The passenger type. */
	protected IPassengerType  passengerType ;
    

	/** The age below. */
	protected int ageBelow;  
	
	/** The age above. */
	protected int ageAbove;         
    

    /** The traveler ids. */
    protected Collection<Integer>travelerIds = new LinkedHashSet<Integer>();


    /** The restricted to country of residence. */
    protected boolean restrictedToCountryOfResidence = false;
    
    
    protected ISeriesDataDetails seriesDataDetails;

	
    /** The restricted to route section. */
    protected IRouteSection restrictedToRouteSection;
                    
                            
    /** The tariff id. */
    protected String tariffId;
    
    /** The tariff description. */
    protected String tariffDescription; 
    

    /** The reduction cards. */
    protected Collection<ICardReference> reductionCards	= new LinkedHashSet<ICardReference>();


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#getNumberOfPassengers()
	 */
	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#setNumberOfPassengers(int)
	 */
	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#getPassengerType()
	 */
	public IPassengerType getPassengerType() {
		return passengerType;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#setPassengerType(org.uic.ticket.api.asn.om.PassengerType)
	 */
	public void setPassengerType(IPassengerType passengerType) {
		this.passengerType = passengerType;
	}



	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#getAgeBelow()
	 */
	public int getAgeBelow() {
		return ageBelow;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#setAgeBelow(int)
	 */
	public void setAgeBelow(int ageBelow) {
		this.ageBelow = ageBelow;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#getAgeAbove()
	 */
	public int getAgeAbove() {
		return ageAbove;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#setAgeAbove(int)
	 */
	public void setAgeAbove(int ageAbove) {
		this.ageAbove = ageAbove;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#getTravelerIds()
	 */
	public Collection<Integer> getTravelerIds() {
		return travelerIds;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#addTravelerId(java.lang.Integer)
	 */
	public void addTravelerId(Integer travelerId) {
		this.travelerIds.add(travelerId);
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#isRestrictedToCountryOfResidence()
	 */
	public boolean isRestrictedToCountryOfResidence() {
		return restrictedToCountryOfResidence;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#setRestrictedToCountryOfResidence(boolean)
	 */
	public void setRestrictedToCountryOfResidence(
			boolean restrictedToCountryOfResidence) {
		this.restrictedToCountryOfResidence = restrictedToCountryOfResidence;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#getRestrictedToRouteSection()
	 */
	public IRouteSection getRestrictedToRouteSection() {
		return restrictedToRouteSection;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#setRestrictedToRouteSection(org.uic.ticket.api.spec.IRouteSection)
	 */
	public void setRestrictedToRouteSection(IRouteSection restrictedToRouteSection) {
		this.restrictedToRouteSection = restrictedToRouteSection;
	}




	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#getTariffId()
	 */
	public String getTariffId() {
		return tariffId;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#setTariffId(java.lang.String)
	 */
	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#getTariffDescription()
	 */
	public String getTariffDescription() {
		return tariffDescription;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#setTariffDescription(java.lang.String)
	 */
	public void setTariffDescription(String tariffDescription) {
		this.tariffDescription = tariffDescription;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#getReductionCards()
	 */
	public Collection<ICardReference> getReductionCards() {
		return reductionCards;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#addReductionCard(org.uic.ticket.api.spec.ICardReference)
	 */
	public void addReductionCard(ICardReference reductionCard) {
		this.reductionCards.add(reductionCard);
	}


	@Override
	public void setSeriesDataDetails(ISeriesDataDetails seriesDataDetails) {		
		this.seriesDataDetails = seriesDataDetails;
	}


	@Override
	public ISeriesDataDetails getSeriesDataDetails() {
		return seriesDataDetails;
	}      
	
    
    
}
