/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;



/**
 * The Interface ICustomerCard.
 */
public interface ICustomerCard  extends IDocumentData {

	
	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public ITraveler getCustomer() ;
	
	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(ITraveler customer) ;
	
	/**
	 * Gets the card id.
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
	 * Gets the valid from.
	 *
	 * @return the valid from
	 */
	public Date getValidFrom();
	
	/**
	 * Sets the valid from.
	 *
	 * @param validFrom the new valid from
	 */
	public void setValidFrom(Date validFrom);
	
	/**
	 * Gets the valid until.
	 *
	 * @return the valid until
	 */
	public Date getValidUntil();
	
	/**
	 * Sets the valid until.
	 *
	 * @param validUntil the new valid until
	 */
	public void setValidUntil(Date validUntil) ;
	
	/**
	 * Gets the class code.
	 *
	 * @return the class code
	 */
	public ITravelClassType getClassCode();
	
	/**
	 * Sets the class code.
	 *
	 * @param classCode the new class code
	 */
	public void setClassCode(ITravelClassType classCode);
	
	/**
	 * Gets the card type.
	 *
	 * @return the card type
	 */
	public int getCardType();
	
	/**
	 * Sets the card type.
	 *
	 * @param cardType the new card type
	 */
	public void setCardType(int cardType);
	
	/**
	 * Gets the card type descr.
	 *
	 * @return the card type descr
	 */
	public String getCardTypeDescr();
	
	/**
	 * Sets the card type descr.
	 *
	 * @param cardTypeDescr the new card type descr
	 */
	public void setCardTypeDescr(String cardTypeDescr);
	
	/**
	 * Gets the customer status.
	 *
	 * @return the customer status
	 */
	public int getCustomerStatus();
	
	/**
	 * Sets the customer status.
	 *
	 * @param customerStatus the new customer status
	 */
	public void setCustomerStatus(int customerStatus);
	
	/**
	 * Gets the customer status descr.
	 *
	 * @return the customer status descr
	 */
	public String getCustomerStatusDescr();
	
	/**
	 * Sets the customer status descr.
	 *
	 * @param customerStatusDescr the new customer status descr
	 */
	public void setCustomerStatusDescr(String customerStatusDescr);
	
	/**
	 * Gets the included services.
	 *
	 * @return the included services
	 */
	public Collection<Integer> getIncludedServices();
	
	/**
	 * Adds the included service.
	 *
	 * @param includedService the included service
	 */
	public void addIncludedService(Integer includedService);
	
	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public IExtension getExtension();

	/**
	 * Sets the extension.
	 *
	 * @param extension the new extension
	 */
	public void setExtension(IExtension extension);	
	
}
