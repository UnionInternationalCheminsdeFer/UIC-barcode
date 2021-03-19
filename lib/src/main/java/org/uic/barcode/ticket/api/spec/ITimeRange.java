/*
 * 
 */
package org.uic.barcode.ticket.api.spec;



/**
 * The Interface ITimeRange.
 * 
 *  ITimeRange defines a range of times. The times are encoded as Minutes of the day (0 = 0:00 , 1440 = 24:00)
 */
public interface ITimeRange {
	
	/**
	 * Gets the from time.
	 *
	 * @return the from time
	 */
	public int getFromTime();
	
	/**
	 * Gets the until time.
	 *
	 * @return the until time
	 */
	public int getUntilTime();	

	/**
	 * Sets the from time.
	 *
	 * @param minutes the new from time
	 */
	public void setFromTime(int minutes);
	
	/**
	 * Sets the until time.
	 *
	 * @param minutes the new until time
	 */
	public void setUntilTime(int minutes);		
	
	
	/**
	 * Gets the validFrom date time offset to UTC in units of 15 minutes.
	 *
	 * @return the validFrom date time UTC offset
	 */
	public Long getValidFromUTCoffset();
	
	/**
	 * Sets the validFrom date time.
	 *
	 * @param validFromDateTime the new validFrom date time
	 */
	public void setValidFromUTCoffset(Long validFromUTCoffset) ;
	
	/**
	 * Gets the validUntil date time offset to UTC in units of 15 minutes.
	 *
	 * @return the validUntil date time UTC offset
	 */
	public Long getValidUntilUTCoffset();
	
	/**
	 * Sets the validUntil date time.
	 *
	 * @param validUntilDateTime the new validUntil date time
	 */
	public void setValidUntilUTCoffset(Long validUntilUTCoffset) ;
	
}
