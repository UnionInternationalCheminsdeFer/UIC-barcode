/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

/**
 * The Interface ICardReference.
 * 
 * ICardReference defines the data for a reference to a customer card
 */
public interface ICardReference {
	
	
	/**
	 * Gets the card issuer.
	 *
	 * For rail cards the RICS code has to be used.
	 *
	 * @return the card issuer
	 */
	public String getCardIssuer();
	
	/**
	 * Sets the card issuer.
	 *
	 * For rail cards the RICS code has to be used.
	 *
	 * @param cardIssuer the new card issuer
	 */
	public void setCardIssuer(String cardIssuer);
	
	/**
	 * Gets the card id.
	 *
	 * The id will be trimmed. The maximum size is 20 positions
	 *
	 * @return the card id
	 */
	public String getCardId();
	
	/**
	 * Sets the card id.
	 *
	 * @param cardId the new card id
	 */
	public void setCardId(String cardId);
	
	/**
	 * Gets the card name.
	 *
	 * @return the card name
	 */
	public String getCardName();
	
	/**
	 * Sets the card name.
	 *
	 *
	 * @param cardName the new card name
	 */
	public void setCardName(String cardName);
	
	/**
	 * Gets the card type.
	 *
	 * @return the card type
	 */
	public int getCardType();
	
	/**
	 * Sets the card type.
	 *
	 * Code list defined and published by the issuer of the card
	 *
	 * @param cardType the new card type
	 */
	public void setCardType(int cardType);
	
	/**
	 * Gets the leading card id in case the entire cardId must not be provided.
	 *
	 * @return the leading card id
	 */
	public String getLeadingCardId();
	
	/**
	 * Sets the leading card id in case the entire cardId must not be provided.
	 *
	 * @param leadingCardId the new leading card id
	 */
	public void setLeadingCardId(String leadingCardId);
	
	/**
	 * Gets the trailing card id in case the entire cardId must not be provided.
	 *
	 * @return the trailing card id
	 */
	public String getTrailingCardId();
	
	/**
	 * Sets the trailing card id in case the entire cardId must not be provided.
	 *
	 * @param trailingCardId the new trailing card id
	 */
	public void setTrailingCardId(String trailingCardId);

}
