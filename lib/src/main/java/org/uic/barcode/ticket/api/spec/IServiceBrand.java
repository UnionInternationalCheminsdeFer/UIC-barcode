package org.uic.barcode.ticket.api.spec;


public interface IServiceBrand {
	
	/**
	 * Gets the service brand of the train.
	 * 
	 * Service brand code list provided by UIC
	 *
	 * @return the service brand of the train
	 */
	public int getServiceBrand();

	/**
	 * Sets the service brand of the train
	 *
	 * Service brand code list provided by UIC	 
	 *
	 * @param serviceBrand the new service brand of the train
	 */
	public void setServiceBrand(int serviceBrand);
	

	/**
	 * Gets the service brand description.
	 *
	 * Services according to UIC leaflet 918.1	 
	 *
	 * @return the service brand description
	 */
	public String getServiceBrandDescription();
	
	/**
	 * Sets the service brand description.
	 *
	 * @param serviceBrandDescription the new service brand description
	 */
	public void setServiceBrandDescription(String serviceBrandDescription);

	/**
	 * Gets the service brand abbreviation.
	 *
	 * @return the service brand abbreviation
	 */
	public String getServiceBrandAbbreviation();

	/**
	 * Sets the service brand abbreviation.
	 *
	 * @param serviceBrandAbbreviation the new service brand abbreviation
	 */
	public void setServiceBrandAbbreviation(String serviceBrandAbbreviation);

}
