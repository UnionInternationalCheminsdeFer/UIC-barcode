/*
 *
 */
package org.uic.barcode.ticket.api.spec;


/**
 * The Interface ITicketLink.
 * <p>
 * ITicketLink provides data to reference an external separate ticket
 */
public interface ITicketLink {

    /**
     * Gets the reference.
     *
     * @return the reference
     */
    public default String getReference() {
        CharSequence v = getReferenceInt();
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    public CharSequence getReferenceInt();

    /**
     * Sets the reference.
     *
     * @param reference the new reference
     */
    public void setReference(String reference);

    /**
     * Sets the reference, without conversion to an integer type in encoding.
     *
     * @param reference the new reference
     */
    public void setReferenceMustString(String reference);

    /**
     * Gets the issuer.
     *
     * @return the issuer
     */
    public default String getIssuer() {
        CharSequence v = getIssuerInt();
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    public CharSequence getIssuerInt();

    /**
     * Sets the issuer.
     *
     * @param issuer the new issuer
     */
    public void setIssuer(String issuer);

    /**
     * Sets the issuer, without conversion to an integer type in encoding.
     *
     * @param issuer the new issuer
     */
    public void setIssuerMustString(String issuer);

    /**
     * Gets the product owner.
     *
     * @return the product owner
     */
    public default String getProductOwner() {
        CharSequence v = getProductOwnerInt();
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    public CharSequence getProductOwnerInt();

    /**
     * Sets the product owner.
     *
     * @param productOwner the new product owner
     */
    public void setProductOwner(String productOwner);

    /**
     * Sets the product owner, without conversion to an integer type in encoding.
     *
     * @param productOwner the new product owner
     */
    public void setProductOwnerMustString(String productOwner);

    /**
     * Gets the ticket type.
     *
     * @return the ticket type
     */
    public ITicketType getTicketType();

    /**
     * Sets the ticket type.
     *
     * @param ticketType the new ticket type
     */
    public void setTicketType(ITicketType ticketType);

    /**
     * Gets the link mode.
     *
     * @return the link mode
     */
    public ILinkMode getLinkMode();

    /**
     * Sets the link mode.
     *
     * @param linkMode the new link mode
     */
    public void setLinkMode(ILinkMode linkMode);

    /**
     * Gets the issuer PNR.
     *
     * @return the issuer PNR
     */
    public String getIssuerPNR();

    /**
     * Sets the issuer PNR.
     *
     * @param train the new train
     */
    public void setIssuerPNR(String issuerPNR);
}
