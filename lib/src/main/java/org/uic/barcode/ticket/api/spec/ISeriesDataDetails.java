/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

/**
 * The Interface ISeriesDataDetails.
 * 
 * ISeriesDataDetails describes details of a series (according to UIC leaflet 108.1 
 * 
 * Series details should be omitted if not explicitely required for a specific product.
 * 
 * 
 */
public interface ISeriesDataDetails {
	
	
	
	/**
	 * Gets the supplying carrier of the series data.
	 *
	 * @return the supplying carrier of the series data
	 */
	public int getSupplyingCarrier() ;


	/**
	 * Sets the supplying carrier of the series data.
	 *
	 * @param supplyingCarrier the new supplying carrier of the series data
	 */
	public void setSupplyingCarrier(int supplyingCarrier);
	
	
	/**
	 * Gets the offer identification of the series data.
	 *
	 * @return the offer identification of the series data
	 */
	public int getOfferIdentification();


	/**
	 * Sets the offer identification of the series data.
	 *
	 * @param offerIdentification the new offer identification of the series data
	 */
	public void setOfferIdentification(int offerIdentification) ;


	/**
	 * Gets the series id.
	 *
	 * @return the series id
	 */
	public int getSeries();


	/**
	 * Sets the series id.
	 *
	 * @param series the new series id
	 */
	public void setSeries(int series) ;	

}
