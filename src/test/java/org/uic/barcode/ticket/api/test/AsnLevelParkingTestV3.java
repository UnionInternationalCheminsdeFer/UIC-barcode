package org.uic.barcode.ticket.api.test;

import java.text.ParseException;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.api.asn.omv3.ParkingGroundData;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.test.testtickets.ParkingTestTicketV3;


/**
 * The Class FipTimeZoneTestV3.
 * 
 * 
 * 
 */
public class AsnLevelParkingTestV3 {
	
    
    
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
	
	@Test public void encoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		ticket = ParkingTestTicketV3.getUicTestTicket();
		byte[] encoded = UperEncoder.encode(ticket);
						
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = ParkingTestTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
        
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
		String hex = ParkingTestTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
		
		byte[] encoded = UperEncoder.encode(ticket);
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = ParkingTestTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
		
		assert(ticket.getTransportDocument().get(0).getTicket().getParkingGround() != null);
		
		ParkingGroundData p = ticket.getTransportDocument().get(0).getTicket().getParkingGround();
		
		assert(p.getReferenceIA5().equals("ACHE12345"));
		assert(p.getParkingGroundId().equals("P47623"));
		assert(p.getFromParkingDate() == 1L);
		assert(p.getToParkingDate() == 1L);
		assert(p.getLocation().equals("Parking Frankfurt Main West"));
		assert(p.getStationNum() == 8000001L);
		assert(p.getSpecialInformation().equals("outdoor parking"));
		assert(p.getPrice() == 500L);
		assert(p.getNumberPlate().equals("AA-DE-12345"));
	
 
    }    
		 
	

}
