/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.ICardReference;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleCardReference.
 */
public class SimpleCardReference implements ICardReference {

	
	/** The card issuer. */
	protected String cardIssuer;

	/** The card id. */
	protected String cardId;
	
	/** The card name. */
	protected String cardName;
	
	/** The card type. */
	protected int cardType;
	
	/** The leading card id. */
	protected String leadingCardId;
	
	/** The trailing card id. */
	protected String trailingCardId;

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#getCardIssuer()
	 */
	public String getCardIssuer() {
		return cardIssuer;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setCardIssuer(java.lang.String)
	 */
	public void setCardIssuer(String cardIssuer) {
		this.cardIssuer = cardIssuer;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#getCardId()
	 */
	public String getCardId() {
		return cardId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setCardId(java.lang.String)
	 */
	public void setCardId(String cardId) {
		if (cardId != null && cardId.trim().length() > 0) {
			this.cardId = cardId.trim();
		} else {
			this.cardId = null;
		}
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#getCardName()
	 */
	public String getCardName() {
		return cardName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setCardName(java.lang.String)
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#getCardType()
	 */
	public int getCardType() {
		return cardType;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setCardType(int)
	 */
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#getLeadingCardId()
	 */
	public String getLeadingCardId() {
		return leadingCardId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setLeadingCardId(java.lang.String)
	 */
	public void setLeadingCardId(String leadingCardId) {
		if (leadingCardId != null && leadingCardId.trim().length() > 0) {
			this.leadingCardId = leadingCardId.trim();
		} else {
			this.leadingCardId = null;
		}
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#getTrailingCardId()
	 */
	public String getTrailingCardId() {
		return trailingCardId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setTrailingCardId(java.lang.String)
	 */
	public void setTrailingCardId(String trailingCardId) {
		if (trailingCardId != null && trailingCardId.trim().length() > 0) {
			this.trailingCardId = trailingCardId.trim();
		} else {
			this.trailingCardId = null;
		}
	}


	
	
	
}
