/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;


/**
 * The Interface IUicRailTicket.
 * 
 * This class provides the top level of the data for a document to be encoded 
 * according to the: 
 * 		UIC standard for ticket control data in asn.1 / PER unaligned encoding.
 * 		version 1.0
 * 
 * The data contains:
 *   --            -issuer informations
 *   --            -the details of the transport document
 *   --            -informations required for the control process
 *   --            -informations on the travelers independent from the transport document
 *   --            -proprietary extensions
 * 
 */
public interface IUicRailTicket {
		
	/**
	 * Gets the issuer details.
	 *
	 * @return the issuer details
	 */
	public IIssuingDetail getIssuerDetails();  

	/**
	 * Gets the traveler details.
	 *
	 * @return the traveler details
	 */
	public ITravelerDetail getTravelerDetails();  
	
	/**
	 * Gets the traveler details.
	 *
	 * @return the traveler details
	 */
	public IControlDetail getControlDetails();  	
	
	/**
	 * Gets the extensions.
	 *
	 * @return the extensions
	 */
	public Collection<IExtension> getExtensions();

	/**
	 * Adds an extension
	 *
	 * @param extension the extension
	 */
	public void addExtension(IExtension extension);

	/**
	 * Sets the issuer details.
	 *
	 * @param issuerDetails the new issuer details
	 */
	public void setIssuerDetails(IIssuingDetail issuerDetails);

	/**
	 * Sets the traveler details.
	 *
	 * @param travelerDetails the new traveler details
	 */
	public void setTravelerDetails(ITravelerDetail travelerDetails);

	/**
	 * Sets the control details
	 *
	 * @param controlDetails the new control details
	 */
	public void setControlDetails(IControlDetail controlDetails);
	
		
	/**
	 * Gets the travel document data.
	 *
	 * @return the travel document data
	 */
	public Collection<IDocumentData> getDocumentData();
	
	/**
	 * Adds the reservation.
	 *         -- more than one document to be used on bilateral agreement only 
	 *
	 * @param document the reservation
	 */
	public void addReservation(IReservation document);
	
	/**
	 * Adds the open ticket.
	 *         -- more than one document to be used on bilateral agreement only 	 
	 *
	 * @param document the open ticket
	 */
	public void addOpenTicket(IOpenTicket document);

	/**
	 * Adds the car carriage reservation.
	 *
	 * @param document the car carriage reservation
	 */
	public void addCarCarriageReservation(ICarCarriageReservation document);
	
	/**
	 * Adds the rail pass.
	 *         -- more than one document to be used on bilateral agreement only  
	 *
	 * @param document the rail pass
	 */
	public void addPass(IPass document);
	
	/**
	 * Adds the voucher.
	 *
	 * @param document the voucher
	 */
	public void addVoucher(IVoucher document);
	
	/**
	 * Adds the customer card.
	 *         -- more than one document to be used on bilateral agreement only  
	 *
	 * @param document the customer card
	 */
	public void addCustomerCard(ICustomerCard document);
	
	/**
	 * Adds the group ticket counter mark.
	 *         -- more than one document to be used on bilateral agreement only  
	 *
	 * @param document the group ticket counter mark
	 */
	public void addCounterMark(ICounterMark document);
	
	/**
	 * Adds the parking ground reservation
	 *         -- more than one document to be used on bilateral agreement only  
	 *
	 * @param document the parking ground reservation
	 */
	public void addParkingGround(IParkingGround document);
	
	/**
	 * Adds the FIP ticket.
	 *
	 * @param document the FIP ticket
	 */
	public void addFipTicket (IFipTicket document);
	
	/**
	 * Adds the station passage allowance.
	 * 	       -- more than one document to be used on bilateral agreement only 
	 *
	 * @param document the station passage allowance
	 */
	public void addStationPassage(IStationPassage document);
	
	/**
	 * Adds the delay confirmation
	 * 	       -- more than one document to be used on bilateral agreement only 
	 *
	 * @param document the delay confirmation
	 */
	public void addDelayConfirmation(IDelayConfirmation document);	
	
	/**
	 * Adds a proprietary document.
	 *   if not bilaterally agreed otherwise proprietary extensions must be ignored
	 *
	 * @param document the proprietary document 
	 */
	public void addDocumentExtension(IDocumentExtension document);
	
}
