/*
 * 
 */
package org.uic.ticket.api.spec;

import org.uic.ticket.api.asn.omv1.LinkMode;
import org.uic.ticket.api.asn.omv1.TicketType;

/**
 * The Interface ITicketLink.
 * 
 * ITicketLink provides data to reference an external separate ticket
 */
public interface ITicketLink {
	
	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference() ;

	/**
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference);

	/**
	 * Gets the issuer.
	 *
	 * @return the issuer
	 */
	public String getIssuer();

	/**
	 * Sets the issuer.
	 *
	 * @param issuer the new issuer
	 */
	public void setIssuer(String issuer) ;
	
	/**
	 * Gets the product owner.
	 *
	 * @return the product owner
	 */
	public String getProductOwner();

	/**
	 * Sets the product owner.
	 *
	 * @param productOwner the new product owner
	 */
	public void setProductOwner(String productOwner);
	
	/**
	 * Gets the ticket type.
	 *
	 * @return the ticket type
	 */
	public TicketType getTicketType();

	/**
	 * Sets the ticket type.
	 *
	 * @param ticketType the new ticket type
	 */
	public void setTicketType(TicketType ticketType);
	
	/**
	 * Gets the link mode.
	 *
	 * @return the link mode
	 */
	public LinkMode getLinkMode();

	/**
	 * Sets the link mode.
	 *
	 * @param linkMode the new link mode
	 */
	public void setLinkMode(LinkMode linkMode);
	
	/**
	 * Gets the issuer PNR.
	 *
	 * @return the issuer PNR
	 */
	public String getIssuerPNR() ;
	
	/**
	 * Sets the issuer PNR.
	 *
	 * @param train the new train
	 */
	public void setIssuerPNR(String issuerPNR);
}
