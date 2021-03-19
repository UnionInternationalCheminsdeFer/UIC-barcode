/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.ICustomerCard;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.ITravelClassType;
import org.uic.barcode.ticket.api.spec.ITraveler;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleCustomerCard.
 */
public class SimpleCustomerCard  extends SimpleDocumentData implements ICustomerCard {
	
	/** The customer type. */
	protected ITraveler customer;
    
	/** The card id. */
	protected String 	cardId;
	
	/** The valid from. */
	protected Date 		validFrom;
	
	/** The valid until. */
	protected Date  	validUntil;
	
	/** The class code. */
	protected ITravelClassType	classCode;     
	
	/** The extension. */
	protected IExtension extension;
	
							
	/** The card type. */
	protected int cardType;
	
	/** The card type descr. */
	protected String cardTypeDescr;												
	
	/** The customer status. */
	protected int customerStatus;
	
	/** The customer status descr. */
	protected String customerStatusDescr;
	
	/** The included services. */
	protected Collection<Integer> includedServices = new LinkedHashSet<Integer>();
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#getCustomer()
	 */
	public ITraveler getCustomer() {
		return customer;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#setCustomer(org.uic.ticket.api.spec.ITraveler)
	 */
	public void setCustomer(ITraveler customer) {
		this.customer = customer;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#getCardId()
	 */
	public String getCardId() {
		return cardId;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#setCardId(java.lang.String)
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#getValidFrom()
	 */
	public Date getValidFrom() {
		return validFrom;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#setValidFrom(java.util.Date)
	 */
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#getValidUntil()
	 */
	public Date getValidUntil() {
		return validUntil;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#setValidUntil(java.util.Date)
	 */
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#getClassCode()
	 */
	public ITravelClassType getClassCode() {
		return classCode;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#setClassCode(org.uic.ticket.api.asn.om.TravelClassType)
	 */
	public void setClassCode(ITravelClassType classCode) {
		this.classCode = classCode;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#getCardType()
	 */
	public int getCardType() {
		return cardType;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#setCardType(int)
	 */
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#getCardTypeDescr()
	 */
	public String getCardTypeDescr() {
		return cardTypeDescr;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#setCardTypeDescr(java.lang.String)
	 */
	public void setCardTypeDescr(String cardTypeDescr) {
		this.cardTypeDescr = cardTypeDescr;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#getCustomerStatus()
	 */
	public int getCustomerStatus() {
		return customerStatus;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#setCustomerStatus(int)
	 */
	public void setCustomerStatus(int customerStatus) {
		this.customerStatus = customerStatus;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#getCustomerStatusDescr()
	 */
	public String getCustomerStatusDescr() {
		return customerStatusDescr;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#setCustomerStatusDescr(java.lang.String)
	 */
	public void setCustomerStatusDescr(String customerStatusDescr) {
		this.customerStatusDescr = customerStatusDescr;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#getIncludedServices()
	 */
	public Collection<Integer> getIncludedServices() {
		return includedServices;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#addIncludedService(java.lang.Integer)
	 */
	public void addIncludedService(Integer includedService) {
		this.includedServices.add(includedService);
	}   
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#getExtension()
	 */
	public IExtension getExtension() {
		return extension;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICustomerCard#setExtension(org.uic.ticket.api.spec.IExtension)
	 */
	public void setExtension(IExtension extension) {
		this.extension = extension;
	}	

}
