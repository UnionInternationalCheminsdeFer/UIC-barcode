/*
 * 
 */
package org.uic.ticket.api.impl;

import org.uic.ticket.api.asn.omv1.LinkMode;
import org.uic.ticket.api.asn.omv1.TicketType;
import org.uic.ticket.api.spec.ITicketLink;

// TODO: Auto-generated Javadoc
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
	protected TicketType ticketType = TicketType.openTicket;
	
	/** The link mode. */
	protected LinkMode linkMode = LinkMode.issuedTogether;

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
	public TicketType getTicketType() {
		return ticketType;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#setTicketType(org.uic.ticket.api.asn.om.TicketType)
	 */
	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#getLinkMode()
	 */
	public LinkMode getLinkMode() {
		return linkMode;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITicketLink#setLinkMode(org.uic.ticket.api.asn.om.LinkMode)
	 */
	public void setLinkMode(LinkMode linkMode) {
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
