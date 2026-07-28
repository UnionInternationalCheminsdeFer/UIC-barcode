/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.ICustomerStatusDescription;
import org.uic.barcode.ticket.api.utils.MustString;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleCustomerStatusDescription.
 */
public class SimpleCustomerStatusDescription implements ICustomerStatusDescription {
	
	
	/** The status provider. */
	protected CharSequence statusProvider;
	
	/** The description. */
	protected String description;
	
	/** The status. */
	protected int status;

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerStatusDescription#getStatusProvider()
	 */
	public CharSequence getStatusProvider() {
		return statusProvider;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerStatusDescription#setStatusProvider(java.lang.String)
	 */
	public void setStatusProvider(String statusProvider) {
		this.statusProvider = statusProvider;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerStatusDescription#setStatusProviderMustString(java.lang.String)
	 */
	public void setStatusProviderMustString(String statusProvider) {
		this.statusProvider = new MustString(statusProvider);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerStatusDescription#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerStatusDescription#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerStatusDescription#getStatus()
	 */
	public int getStatus() {
		return status;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerStatusDescription#setStatus(org.uic.ticket.api.asn.om.CustomerStatusType)
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	
	
	
}
