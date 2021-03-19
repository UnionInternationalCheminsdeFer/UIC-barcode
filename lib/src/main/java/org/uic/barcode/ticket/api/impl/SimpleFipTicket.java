/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;

import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IFipTicket;
import org.uic.barcode.ticket.api.spec.ITravelClassType;


// TODO: Auto-generated Javadoc
/**
 * The Class SimpleFipTicket.
 */
public class SimpleFipTicket extends SimpleDocumentData implements IFipTicket {
	
                
	/** The reference. */
	protected String reference;
	
	/** The product id. */
	protected String productId;

	/** The product owner. */
	protected String productOwner;
	
	/** The class code. */
	protected ITravelClassType	classCode = ITravelClassType.second;      
	
	/** The valid from. */
	protected Date validFrom;
	
	/** The valid until. */
	protected Date  validUntil;	
		
	
	/** The number of travel dates. */
	protected int numberOfTravelDates;
	
	/** The includes suplements. */
	protected boolean includesSupplements = true;
	
	/** The carriers. */
	protected Collection<String>carriers = new LinkedHashSet<String>();
	
	/** The extension data. */
	protected IExtension   	extensionData;	
	
	protected Collection<Date> activatedDays = new LinkedHashSet<Date>();

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#getNumberOfTravelDates()
	 */
	public int getNumberOfTravelDates() {
		return numberOfTravelDates;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#setNumberOfTravelDates(int)
	 */
	public void setNumberOfTravelDates(int numberOfTravelDates) {
		this.numberOfTravelDates = numberOfTravelDates;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#isIncludesSuplements()
	 */
	public boolean isIncludesSupplements() {
		return includesSupplements;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#setIncludesSuplements(boolean)
	 */
	public void setIncludesSupplements(boolean includesSuplements) {
		this.includesSupplements = includesSuplements;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#getCarriers()
	 */
	public Collection<String> getCarriers() {
		return carriers;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#addCarrier(java.lang.Integer)
	 */
	public void addCarrier(String carrier) {
		this.carriers.add(carrier);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#getReference()
	 */
	public String getReference() {
		return reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#setReference(java.lang.String)
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#getProductId()
	 */
	public String getProductId() {
		return productId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#setProductId(java.lang.String)
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#getProductOwner()
	 */
	public String getProductOwner() {
		return productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#setProductOwner(java.lang.String)
	 */
	public void setProductOwner(String productOwner) {
		this.productOwner = productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#getClassCode()
	 */
	public ITravelClassType getClassCode() {
		return classCode;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#setClassCode(org.uic.ticket.api.asn.om.TravelClassType)
	 */
	public void setClassCode(ITravelClassType classCode) {
		this.classCode = classCode;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#getValidFrom()
	 */
	public Date getValidFrom() {
		return validFrom;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#setValidFrom(java.util.Date)
	 */
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#getValidUntil()
	 */
	public Date getValidUntil() {
		return validUntil;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#setValidUntil(java.util.Date)
	 */
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#getExtension()
	 */
	@Override
	public IExtension getExtension() {
		return extensionData;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IFipTicket#setExtension(org.uic.ticket.api.spec.IExtension)
	 */
	@Override
	public void setExtension(IExtension extensionData) {
		this.extensionData = extensionData;		
	}

	@Override
	public Collection<Date> getActivatedDays() {
		return activatedDays;
	}

	@Override
	public void addActivatedDay(Date day) {
		this.activatedDays.add(day);
	}


 					                
        




	
	

}
