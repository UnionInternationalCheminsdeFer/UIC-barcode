/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.IExtension;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleExtension.
 */
public class SimpleExtension implements IExtension{
	
	/** The id. */
	protected String id;
	
	/** The binarydata. */
	protected byte[] binarydata;
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IExtension#getId()
	 */
	public String getId() {
		return id;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IExtension#setId(java.lang.String)
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IExtension#getBinarydata()
	 */
	public byte[] getBinarydata() {
		return binarydata;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IExtension#setBinarydata(byte[])
	 */
	public void setBinarydata(byte[] binarydata) {
		this.binarydata = binarydata;
	}

	
	
}
