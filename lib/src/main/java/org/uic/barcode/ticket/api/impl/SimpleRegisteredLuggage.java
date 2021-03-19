/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.IRegisteredLuggage;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleRegisteredLuggage.
 */
public class SimpleRegisteredLuggage implements IRegisteredLuggage{
	
	/** The registration id. */
	protected String registrationId;
	
	/** The max weight. */
	protected int maxWeight;
	
	/** The max size. */
	protected int maxSize;
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRegisteredLuggage#getRegistrationId()
	 */
	public String getRegistrationId() {
		return registrationId;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRegisteredLuggage#setRegistrationId(java.lang.String)
	 */
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRegisteredLuggage#getMaxWeight()
	 */
	public int getMaxWeight() {
		return maxWeight;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRegisteredLuggage#setMaxWeight(int)
	 */
	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRegisteredLuggage#getMaxSize()
	 */
	public int getMaxSize() {
		return maxSize;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IRegisteredLuggage#setMaxSize(int)
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
	

}
