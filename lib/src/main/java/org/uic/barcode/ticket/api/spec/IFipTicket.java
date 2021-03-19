/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;


/**
 * The Interface IFipTicket.
 * 
 * IFipTicket provides a description of the FIP ticket.
 * 
 */
public interface IFipTicket  extends IDocumentData {
	
	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference();

	/**
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference);

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public String getProductId() ;

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(String productId);

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
	 * Gets the travel class code.
	 *
	 * @return the travel class code
	 */
	public ITravelClassType getClassCode();

	/**
	 * Sets the class code.
	 *
	 * @param classCode the new class code
	 */
	public void setClassCode(ITravelClassType classCode);

	/**
	 * Gets the valid from date.
	 *
	 * @return the valid from date
	 */
	public Date getValidFrom();

	/**
	 * Sets the valid from date.
	 *
	 * @param validFrom the new valid from date
	 */
	public void setValidFrom(Date validFrom);

	/**
	 * Gets the valid until date.
	 *
	 * @return the valid until date
	 */
	public Date getValidUntil();

	/**
	 * Sets the valid until date.
	 *
	 * @param validUntil the new valid until date
	 */
	public void setValidUntil(Date validUntil);
	
	/**
	 * Gets the number of travel days allowed for traveling within the validity range.
	 *
	 * @return the number of travel days allowed for traveling within the validity range.
	 */
	public int getNumberOfTravelDates();

	/**
	 * Sets the number of travel days allowed for traveling within the validity range.
	 *
	 * @param numberOfTravelDates the new number of travel days
	 */
	public void setNumberOfTravelDates(int numberOfTravelDates);

	/**
	 * Checks if supplements are included.
	 *
	 * @return true, if is supplements are included
	 */
	public boolean isIncludesSupplements();

	/**
	 * Sets whether supplements are included.
	 *
	 * @param includesSuplements the new includes supplements flag
	 */
	public void setIncludesSupplements(boolean includesSuplements);
	
	/**
	 * Gets the carriers valid for traveling.
	 *
	 * @return the carriers valid for traveling
	 */
	public Collection<String> getCarriers();

	/**
	 * Adds a carrier.
	 *
	 * @param carrier the carrier
	 */
	public void addCarrier(String carrier);
	
	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public IExtension getExtension();

	/**
	 * Sets the extension.
	 *
	 * @param extensionData the new extension
	 */
	public void setExtension(IExtension extensionData);	
	
	/**
	 * Gets the activated days where the ticket is valid for tarveling. 
	 *
	 * @return the activated days
	 */
	public Collection<Date> getActivatedDays();
	
	/**
	 * Adds the activated day.
	 *
	 * @param day the day
	 */
	public void addActivatedDay(Date day);
	
	

}
