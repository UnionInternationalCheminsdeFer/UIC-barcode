/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.IBerth;
import org.uic.barcode.ticket.api.spec.IBerthTypeType;
import org.uic.barcode.ticket.api.spec.ICompartmentGenderType;

/**
 * The Class SimpleBerth.
 */
public class SimpleBerth implements IBerth {
	
	
	/** The type. */
	protected IBerthTypeType type;
	
	/** The number of berths. */
	protected int numberOfBerths;
	
	/** The gender. */
	protected ICompartmentGenderType	gender = ICompartmentGenderType.family;
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IBerth#getType()
	 */
	public IBerthTypeType getType() {
		return type;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IBerth#setType(org.uic.ticket.api.asn.om.BerthTypeType)
	 */
	public void setType(IBerthTypeType type) {
		this.type = type;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IBerth#getNumberOfBerths()
	 */
	public int getNumberOfBerths() {
		return numberOfBerths;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IBerth#setNumberOfBerths(int)
	 */
	public void setNumberOfBerths(int numberOfBerths) {	
		this.numberOfBerths = numberOfBerths;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IBerth#getGender()
	 */
	public ICompartmentGenderType getGender() {
		return gender;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IBerth#setGender(org.uic.ticket.api.asn.om.CompartmentGenderType)
	 */
	public void setGender(ICompartmentGenderType gender) {
		this.gender = gender;
	}
	
	

}
