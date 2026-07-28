/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.ICardReference;
import org.uic.barcode.ticket.api.utils.MustString;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleCardReference.
 */
public class SimpleCardReference implements ICardReference {
	/** The card issuer. */
	protected CharSequence cardIssuer;

	/** The card id. */
	protected CharSequence cardId;
	
	/** The card name. */
	protected String cardName;
	
	/** The card type. */
	protected int cardType;
	
	/** The leading card id. */
	protected CharSequence leadingCardId;
	
	/** The trailing card id. */
	protected CharSequence trailingCardId;

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#getCardIssuer()
	 */
	public CharSequence getCardIssuer() {
		return cardIssuer;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setCardIssuer(java.lang.String)
	 */
	public void setCardIssuer(String cardIssuer) {
		this.cardIssuer = cardIssuer;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setCardIssuerMustString(java.lang.String)
	 */
	public void setCardIssuerMustString(String cardIssuer) {
		this.cardIssuer = new MustString(cardIssuer);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#getCardId()
	 */
	public CharSequence getCardId() {
		return cardId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setCardId(java.lang.String)
	 */
	public void setCardId(String cardId) {
		if (cardId != null && !cardId.trim().isEmpty()) {
			this.cardId = cardId.trim();
		} else {
			this.cardId = null;
		}
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setCardIdMustString(java.lang.String)
	 */
	public void setCardIdMustString(String cardId) {
		if (cardId != null && !cardId.trim().isEmpty()) {
			this.cardId = new MustString(cardId.trim());
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
	public CharSequence getLeadingCardId() {
		return leadingCardId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setLeadingCardId(java.lang.String)
	 */
	public void setLeadingCardId(String leadingCardId) {
		if (leadingCardId != null && !leadingCardId.trim().isEmpty()) {
			this.leadingCardId = leadingCardId.trim();
		} else {
			this.leadingCardId = null;
		}
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setLeadingCardIdMustString(java.lang.String)
	 */
	public void setLeadingCardIdMustString(String leadingCardId) {
		if (leadingCardId != null && !leadingCardId.trim().isEmpty()) {
			this.leadingCardId = new MustString(leadingCardId.trim());
		} else {
			this.leadingCardId = null;
		}
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#getTrailingCardId()
	 */
	public CharSequence getTrailingCardId() {
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

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ICardReference#setTrailingCardId(java.lang.String)
	 */
	public void setTrailingCardIdMustString(String trailingCardId) {
		if (trailingCardId != null && !trailingCardId.trim().isEmpty()) {
			this.trailingCardId = new MustString(trailingCardId.trim());
		} else {
			this.trailingCardId = null;
		}
	}
}
