/*
 * 
 */
package org.uic.barcode.ticket.api.impl;


import org.uic.barcode.ticket.api.spec.ILinkMode;
import org.uic.barcode.ticket.api.spec.ITicketLink;
import org.uic.barcode.ticket.api.spec.ITicketType;


/**
 * The Class SimpleTicketLink.
 */
public class SimpleTicketLink implements ITicketLink {

	/** The reference. */
	protected String reference;
	
	/** The issuer. */
	protected String issuer;
	
	/** The issuer PNR. */
	protected String issuerPNR;
		
	/** The product owner. */
	protected String productOwner;
	
	/** The ticket type. */
	protected ITicketType ticketType = ITicketType.openTicket;
	
	/** The link mode. */
	protected ILinkMode linkMode = ILinkMode.issuedTogether;

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#getReference()
	 */
	public String getReference() {
		return reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#setReference(java.lang.String)
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#getIssuer()
	 */
	public String getIssuer() {
		return issuer;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#setIssuer(java.lang.String)
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#getProductOwner()
	 */
	public String getProductOwner() {
		return productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#setProductOwner(java.lang.String)
	 */
	public void setProductOwner(String productOwner) {
		this.productOwner = productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#getTicketType()
	 */
	public ITicketType getTicketType() {
		return ticketType;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#setTicketType(org.uic.ticket.api.asn.om.TicketType)
	 */
	public void setTicketType(ITicketType ticketType) {
		this.ticketType = ticketType;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#getLinkMode()
	 */
	public ILinkMode getLinkMode() {
		return linkMode;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#setLinkMode(org.uic.ticket.api.asn.om.LinkMode)
	 */
	public void setLinkMode(ILinkMode linkMode) {
		this.linkMode = linkMode;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#getIssuerPNR()
	 */
	public String getIssuerPNR() {
		return issuerPNR;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#setIssuerPNR(java.lang.String)
	 */
	public void setIssuerPNR(String issuerPNR) {
		this.issuerPNR = issuerPNR;
	}
	
	
	
	
	
}
