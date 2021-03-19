package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.IServiceBrand;



public class SimpleServiceBrand implements IServiceBrand {

    /** The service brand. */
	protected int serviceBrand;
	   	
	/** The service brand description. */
	protected String serviceBrandDescription;
	   	
	/** The service brand abbreviation. */
	protected String serviceBrandAbbreviation;

	public int getServiceBrand() {
		return serviceBrand;
	}

	public void setServiceBrand(int serviceBrand) {
		this.serviceBrand = serviceBrand;
	}

	public String getServiceBrandDescription() {
		return serviceBrandDescription;
	}

	public void setServiceBrandDescription(String serviceBrandDescription) {
		this.serviceBrandDescription = serviceBrandDescription;
	}

	public String getServiceBrandAbbreviation() {
		return serviceBrandAbbreviation;
	}

	public void setServiceBrandAbbreviation(String serviceBrandAbbreviation) {
		this.serviceBrandAbbreviation = serviceBrandAbbreviation;
	}


}
