/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;

/**
 * The Interface IControlDetail.
 * 
 * 
 * IControlDetails provides data supporting the control process:
 *   --  - list of items which the traveler can use to identify himself or the unique
 *   --            usage of the ticket 
 *   --           (card ids, parts or identity card numbers, credit card numbers,..)
 *   --  - hints on the validation to be made on board
 */
public interface IControlDetail {
	
	/**
	 * Gets the card references the traveler can use to identify himself.
	 *
	 * @return the card references
	 */
	public Collection<ICardReference> getIdentificationByCardReference();
	


	/**
	 * Adds a card references the traveler can use to identify himself.
	 *
	 * @param cardReference the card reference
	 */
	public void addIdentificationByCardReference(ICardReference cardReference);
		

	/**
	 * Checks if the traveler can be identified by id card.
	 *
	 * @return true, if identification by id card is possible
	 */
	public boolean isIdentificationByIdCard();
	

	/**
	 * Sets whether the traveler can be identified by id card.
	 *
	 * @param identificationByIdCard the new identification by id card flag
	 */
	public void setIdentificationByIdCard(boolean identificationByIdCard);
	

	/**
	 * Checks whether the traveler can be identified by passport id.
	 *
	 * @return true, if the traveler can be identified by passport id
	 */
	public boolean isIdentificationByPassportId();
	

	/**
	 * Sets whether the traveler can be identified by passport id.
	 *
	 * @param identificationByPassportId the new identification by passport id flag
	 */
	public void setIdentificationByPassportId(boolean identificationByPassportId);
	

	/**
	 * Gets the identification item code.
	 * 
	 * For future use, no code list defined yet
	 *
	 * @return the identification item
	 */
	public int getIdentificationItem();
	

	/**
	 * Sets the identification item.
	 *
	 * For future use, no code list defined yet
	 *
	 * @param identificationItem the new identification item
	 */
	public void setIdentificationItem(int identificationItem);
	

	/**
	 * Checks if passport validation is required.
	 *
	 * @return true, if is passport validation required
	 */
	public boolean isPassportValidationRequired();
	

	/**
	 * Sets the passport validation required.
	 *
	 * @param passportValidationRequired the new passport validation required flag
	 */
	public void setPassportValidationRequired(boolean passportValidationRequired);
	

	/**
	 * Checks if is online validation required.
	 *
	 * In this case the ticket must be validated with the online server holding the ticket. 
	 * This is usually the server of the product owner of a ticket. 	 
	 *
	 * @return true, if is online validation required
	 */
	public boolean isOnlineValidationRequired();
	

	/**
	 * Sets the online validation required.
	 *
	 * In this case the ticket must be validated with the online server holding the ticket. 
	 * This is usually the server of the product owner of a ticket. 
	 *
	 * @param onlineValidationRequired the new online validation required
	 */
	public void setOnlineValidationRequired(boolean onlineValidationRequired);
	

	/**
	 * Gets the random detailed validation required.
	 *
	 * This indicates to the control device, that this ticket type should be
	 * checked in more detail in the indicated percentage of cases.
	 * 
	 *  The ticket type is identified by:
	 *  	- product owner
	 *  	- type of the ticket (Reservation, Open Ticket, Pass,..)	
	 *
	 *	The detailed inspection might be implemented by storing the entire bar code data for 
	 *  further inspection and in taking a picture of the paper ticket. 
	 *
	 * @return the random detailed validation required
	 */
	public int getRandomDetailedValidationRequired();
	

	/**
	 * Sets the random detailed validation required.
	 *
	 * This indicates to the control device, that this ticket type should be
	 * checked in more detail in the indicated percentage of cases.
	 * 
	 *  The ticket type is identified by:
	 *  	- product owner
	 *  	- type of the ticket (Reservation, Open Ticket, Pass,..)	
	 *
	 *	The detailed inspection might be implemented by storing the entire bar code data for 
	 *  further inspection and in taking a picture of the paper ticket. 
	 *
	 * @param randomOnlineValidationRequired the new random detailed validation required
	 */
	public void setRandomDetailedValidationRequired(int randomOnlineValidationRequired);
	

	/**
	 * Checks if an age check on the passengers is required.
	 *
	 * @return true, if an age check on the passengers is required
	 */
	public boolean isAgeCheckRequired();
	

	/**
	 * Sets whether an age check on the passengers is required.
	 *
	 * @param ageCheckRequired the new age check required flag
	 */
	public void setAgeCheckRequired(boolean ageCheckRequired);
	

	/**
	 * Checks if is reduction card check required.
	 *
	 * @return true, if is reduction card check required
	 */
	public boolean isReductionCardCheckRequired();
	

	/**
	 * Sets the reduction card check required.
	 *
	 * @param reductionCardCheckRequired the new reduction card check required
	 */
	public void setReductionCardCheckRequired(boolean reductionCardCheckRequired);
	

	/**
	 * Gets the info text for the control staff.
	 *
	 * @return the info text for the control staff
	 */
	public String getInfoText();
	

	/**
	 * Sets the info text for the control staff.
	 *
	 * @param infoText the new info text for the control staff
	 */
	public void setInfoText(String infoText);
	

	/**
	 * Gets the linked tickets.
	 *
	 * Linked tickets are separate tickets which should be included in the check. 
	 * E.g. A Rail Pass accompanying a Reservation with a tariff valid for passes only. 
	 *
	 * @return the linked tickets
	 */
	public Collection<ITicketLink> getLinkedTickets() ;
	

	/**
	 * Adds the linked ticket.
	 *
	 * Linked tickets are separate tickets which should be included in the check. 
	 * E.g. A Rail Pass accompanying a Reservation with a tariff valid for passes only. 
	 * 
	 * @param linkedTicket the linked ticket
	 */
	public void addLinkedTicket(ITicketLink linkedTicket) ;
	

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
