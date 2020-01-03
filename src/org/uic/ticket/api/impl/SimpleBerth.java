/*
 * 
 */
package org.uic.ticket.api.impl;

import org.uic.ticket.api.asn.omv1.BerthTypeType;
import org.uic.ticket.api.asn.omv1.CompartmentGenderType;
import org.uic.ticket.api.spec.IBerth;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleBerth.
 */
public class SimpleBerth implements IBerth {
	
	
	/** The type. */
	protected BerthTypeType type;
	
	/** The number of berths. */
	protected int numberOfBerths;
	
	/** The gender. */
	protected CompartmentGenderType	gender = CompartmentGenderType.family;
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IBerth#getType()
	 */
	public BerthTypeType getType() {
		return type;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IBerth#setType(org.uic.ticket.api.asn.om.BerthTypeType)
	 */
	public void setType(BerthTypeType type) {
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
	public CompartmentGenderType getGender() {
		return gender;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IBerth#setGender(org.uic.ticket.api.asn.om.CompartmentGenderType)
	 */
	public void setGender(CompartmentGenderType gender) {
		this.gender = gender;
	}
	
	

}
