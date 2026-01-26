package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.IControlDetail;
import org.uic.barcode.ticket.api.spec.ITravelerDetail;

public class SimpleTicketUtils {
	
	/*
	 * 
	 * Check whether travelerDetails has content
	 * 
	 * This is a workaround as simple ticket implicitly creates a default
	 * travelerDetails and this function is used to avoid adding these to the barcode encoding   
	 * 
	 */
	public static boolean travelerDetailsHasContent(ITravelerDetail travelerDetails) {
		if (travelerDetails.getTravelers() != null
				&& !travelerDetails.getTravelers().isEmpty()) return true;
		if (travelerDetails.getGroupName() != null) return true;
		if (travelerDetails.getPreferredLanguage() != null) return true;
		return false;
	}

	/*
	 * 
	 * Check whether controlDetails has content
	 * 
	 * This is a workaround as simple ticket implicitly creates default
	 * controlsDetails and this function is used to avoid adding these to the barcode encoding   
	 * 
	 */
	public static boolean controlDetailsHasContent(IControlDetail controlDetails) {
		if (controlDetails.isAgeCheckRequired() == true) return true;
		if (controlDetails.isIdentificationByIdCard() == true) return true;
		if (controlDetails.isIdentificationByPassportId() == true) return true;		
		if (controlDetails.isOnlineValidationRequired() == true) return true;
		if (controlDetails.isPassportValidationRequired() == true) return true;
		if (controlDetails.isReductionCardCheckRequired() == true) return true;
		if (controlDetails.getIdentificationByCardReference() != null 
			&& !controlDetails.getIdentificationByCardReference().isEmpty() ) return true;
		if (controlDetails.getIdentificationItem() != 0) return true;
		if (controlDetails.getLinkedTickets() != null
			&& !controlDetails.getLinkedTickets().isEmpty()) return true;
		if (controlDetails.getRandomDetailedValidationRequired() != 0) return true;
		if (controlDetails.getExtension() != null) return true;
		return false;
	}

}
