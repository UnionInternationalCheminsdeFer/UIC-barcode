package org.uic.barcode.ticket.api.spec;

import java.util.Date;

/**
 * The Interface IValidityRange.
 * 
 * IValidityRange describes a validity range by from / until date and time
 */
public interface IValidityRange {
	
	
	/**
	 * Gets the from date and time.
	 *
	 * @return the from date and time
	 */
	public Date getFromDate();
	
	/**
	 * Gets the until date and time.
	 *
	 * @return the until date and time
	 */
	public Date getUntilDate();

	
	/**
	 * Sets the from date and time.
	 *
	 * @param date the new from date and time
	 */
	public void setFromDate(Date date);
	
	/**
	 * Sets the until date and time.
	 *
	 * @param date the new until date and time
	 */
	public void setUntilDate(Date date);	
	
	
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
