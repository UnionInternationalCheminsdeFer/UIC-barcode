package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.ISeriesDataDetails;

public class SimpleSeriesDataDetails implements ISeriesDataDetails {
	

	protected int offerIdentification;

    /** The supplying carrier. */
    protected int supplyingCarrier;
    
    /** The series. */
    protected int series;      	


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#getSupplyingCarrier()
	 */
	public int getSupplyingCarrier() {
		return supplyingCarrier;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#setSupplyingCarrier(int)
	 */
	public void setSupplyingCarrier(int supplyingCarrier) {
		this.supplyingCarrier = supplyingCarrier;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#getOfferIdentification()
	 */
	public int getOfferIdentification() {
		return offerIdentification;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#setOfferIdentification(int)
	 */
	public void setOfferIdentification(int offerIdentification) {
		this.offerIdentification = offerIdentification;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#getSeries()
	 */
	public int getSeries() {
		return series;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITariff#setSeries(int)
	 */
	public void setSeries(int series) {
		this.series = series;
	}

}
