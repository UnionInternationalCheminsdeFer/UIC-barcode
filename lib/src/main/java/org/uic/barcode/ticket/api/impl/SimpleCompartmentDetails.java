/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.ICompartmentDetails;
import org.uic.barcode.ticket.api.spec.ICompartmentPositionType;

/**
 * The Class SimpleCompartmentDetails.
 */
public class SimpleCompartmentDetails implements ICompartmentDetails {

	
	/** The coach type. */
	protected int coachType	;   
	
	/** The compartment type. */
	protected int compartmentType;
	
	/** The special allocation. */
	protected int specialAllocation;
	
	/** The coach type descr. */
	protected String coachTypeDescr;
	
	/** The compartment type descr. */
	protected String compartmentTypeDescr;
	
	/** The special allocation descr. */
	protected String specialAllocationDescr;
	
	/** The position. */
	protected ICompartmentPositionType position        = ICompartmentPositionType.unspecified;
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#getCoachType()
	 */
	public int getCoachType() {
		return coachType;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#setCoachType(int)
	 */
	public void setCoachType(int coachType) {
		this.coachType = coachType;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#getCompartmentType()
	 */
	public int getCompartmentType() {
		return compartmentType;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#setCompartmentType(int)
	 */
	public void setCompartmentType(int compartmentType) {
		this.compartmentType = compartmentType;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#getSpecialAllocation()
	 */
	public int getSpecialAllocation() {
		return specialAllocation;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#setSpecialAllocation(int)
	 */
	public void setSpecialAllocation(int specialAllocation) {
		this.specialAllocation = specialAllocation;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#getCoachTypeDescr()
	 */
	public String getCoachTypeDescr() {
		return coachTypeDescr;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#setCoachTypeDescr(java.lang.String)
	 */
	public void setCoachTypeDescr(String coachTypeDescr) {
		this.coachTypeDescr = coachTypeDescr;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#getComparttmentTypeDescr()
	 */
	public String getCompartmentTypeDescr() {
		return compartmentTypeDescr;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#setComparttmentTypeDescr(java.lang.String)
	 */
	public void setCompartmentTypeDescr(String comparttmentTypeDescr) {
		this.compartmentTypeDescr = comparttmentTypeDescr;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#getSpecialAllocationDescr()
	 */
	public String getSpecialAllocationDescr() {
		return specialAllocationDescr;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#setSpecialAllocationDescr(java.lang.String)
	 */
	public void setSpecialAllocationDescr(String specialAllocationDescr) {
		this.specialAllocationDescr = specialAllocationDescr;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#getPosition()
	 */
	public ICompartmentPositionType getPosition() {
		return position;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICompartmentDetails#setPosition(org.uic.ticket.api.asn.om.CompartmentPositionType)
	 */
	public void setPosition(ICompartmentPositionType position) {
		this.position = position;
	}
	
	
	
}
