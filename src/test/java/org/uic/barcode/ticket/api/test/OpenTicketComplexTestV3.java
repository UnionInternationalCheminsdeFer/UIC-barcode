package org.uic.barcode.ticket.api.test;


import java.text.ParseException;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.api.asn.omv3.IncludedOpenTicketType;
import org.uic.barcode.ticket.api.asn.omv3.OpenTicketData;
import org.uic.barcode.ticket.api.asn.omv3.PassengerType;
import org.uic.barcode.ticket.api.asn.omv3.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.test.testtickets.OpenTestComplexTicketV3;


/**
 * The Class FipTimeZoneTestV3.
 * 
 * 
 * 
 */
public class OpenTicketComplexTestV3 {
	
    
    
    /** The ticket decoded 1. */
    UicRailTicketData ticket = null;
        
    byte[] encodedInTimeZone1 = null; 

    
    TimeZone defaulttimeZone = null;
    
	/**
	 * Prepare tickets.
	 */
	@Before public void prepare() {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		defaulttimeZone = TimeZone.getDefault();

    	//encode in UTC time zone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));


	}
	
	/**
	 * clean up
	 */
	@After public void resetTimeZone() {
		TimeZone.setDefault(defaulttimeZone);
	}
	
    
	/**
	 * Test encode test tickets in UTC and decode in CET.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws ParseException 
	 */
	@Test public void decoding()  {
		
		//get tickets
		String hex = OpenTestComplexTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
		
		assert(ticket.getTransportDocument().get(0).getTicket().getOpenTicket() != null);
		
		OpenTicketData ot = ticket.getTransportDocument().get(0).getTicket().getOpenTicket();
		
		assert(ot.getReturnIncluded() == false);
		assert(ot.getClassCode().equals(TravelClassType.first));
		assert(ot.getInfoText().equals("openTicketInfo"));
		assert(ot.getVatDetails().size() == 1);
		assert(ot.getVatDetails().get(0).getCountry() == 80L);
		assert(ot.getVatDetails().get(0).getPercentage() == 70L);
		assert(ot.getVatDetails().get(0).getAmount() == 10L);
		assert(ot.getVatDetails().get(0).getVatId().equals("IUDGTE"));
				
		assert(ot.getIncludedAddOns().size() == 1);
		IncludedOpenTicketType inc = ot.getIncludedAddOns().get(0);
		assert(inc.getProductOwnerNum() == 1080L);
		assert(inc.getClassCode().equals(TravelClassType.second));
		assert(inc.getInfoText().equals("included ticket"));
		assert(inc.getValidRegion().get(0).getZones() != null);
		assert(inc.getValidRegion().get(0).getZones().getZoneId().get(0) == 100L);
		assert(inc.getTariffs().size() == 1);
		assert(inc.getTariffs().get(0).getNumberOfPassengers() == 2L);
		assert(inc.getTariffs().get(0).getPassengerType().equals(PassengerType.adult));
		assert(inc.getTariffs().get(0).getRestrictedToCountryOfResidence() == false);
		assert(inc.getTariffs().get(0).getRestrictedToRouteSection().getFromStationNum() == 8000001L);
		assert(inc.getTariffs().get(0).getRestrictedToRouteSection().getToStationNum() == 8010000L);	
        
    }    
		
	@Test public void encoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		String hex = OpenTestComplexTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		
		//ticket = OpenTestComplexTicketV2.getUicTestTicket();
		byte[] encoded = UperEncoder.encode(ticket);
		
		
		
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = OpenTestComplexTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
        
    }   
	

}
