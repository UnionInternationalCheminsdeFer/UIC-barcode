/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

/**
 * The Interface ICustomerStatusDescription.
 * 
 * 
 * ICustomerStatusDescription provides a description of the customer status.
 * 
 */
public interface ICustomerStatusDescription {
	
	/**
	 * Gets the status provider.
	 *
	 * RICS codes should be used in case the status is provided by a railway.
	 *
	 * @return the status provider
	 */
	public String getStatusProvider();
	
	/**
	 * Sets the status provider.
	 *
	 * RICS codes should be used in case the status is provided by a railway.
	 * 	 
	 * @param statusProvider the new status provider
	 */
	public void setStatusProvider(String statusProvider);
	
	/**
	 * Gets the human readable status description.
	 *
	 * @return the human readable status description.
	 */
	public String getDescription();
	
	/**
	 * Sets the human readable status description.
	 *
	 * @param description the new the human readable status description.
	 */
	public void setDescription(String description) ;
	
	/**
	 * Gets the status code.
	 * 
	 *  -- customer status code 
	 *  -- 	1 = basic 
	 *  -- 	2 = premium
	 *  -- 	3 = silver
	 *  -- 	4 = gold
	 *  -- 	5 = platinum
	 *  -- 	6 = senator
	 *  -- 	> 50 - code table of the status provider
	 *
	 * @return the status code
	 */
	public int getStatus() ;
	
	/**
	 * Sets the status.
	 *
	 *  -- customer status code 
	 *  -- 	1 = basic 
	 *  -- 	2 = premium
	 *  -- 	3 = silver
	 *  -- 	4 = gold
	 *  -- 	5 = platinum
	 *  -- 	6 = senator
	 *  -- 	> 50 - code table of the status provider	 	 
	 *
	 * @param status the new status
	 */
	public void setStatus(int status) ;

}
