package org.uic.barcode.ticket.api.spec;

import java.util.Collection;

/**
 * The Interface IValidityDetails.
 * 
 * IValidityDetails provides a more detailed validity description:
 * 
 * 	a list of date/time ranges
 *  a list of excluded time ranges
 * 
 */
public interface IValidityDetails {
	
	
	/**
	 * Gets the validity ranges.
	 *
	 * @return the validity ranges
	 */
	public Collection<IValidityRange> getValidityRanges();
	
	/**
	 * Gets the time ranges.
	 *
	 * @return the time ranges
	 */
	public Collection<ITimeRange> getTimeRanges();	
	
	/**
	 * Adds the validity ranges.
	 *
	 * @param range the range
	 */
	public void addValidityRanges(IValidityRange range);
	
	/**
	 * Adds the time ranges.
	 *
	 * @param range the range
	 */
	public void addTimeRanges(ITimeRange range);		

}
