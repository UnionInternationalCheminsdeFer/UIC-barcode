package org.uic.barcode.ticket.api.test;

import java.text.ParseException;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData;
import org.uic.barcode.ticket.api.test.testtickets.CountermarkTestComplexTicketV1;


/**
 * The Class FipTimeZoneTestV3.
 * 
 * 
 * 
 */
public class AsnLevelCountermarkComplexTestV1 {
	
    
    
    /** The ticket decoded 1. */
    UicRailTicketData ticket = null;
        
    byte[] encodedInTimeZone1 = null; 

    
    TimeZone defaulttimeZone = null;
    
	/**
	 * Prepare tickets.
	 */
	@Before public void prepare() {
		
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
		String hex = CountermarkTestComplexTicketV1.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
        
    }    
		
	@Test public void encoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		String hex = CountermarkTestComplexTicketV1.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		
		//ticket = OpenTestComplexTicketV2.getUicTestTicket();
		byte[] encoded = UperEncoder.encode(ticket);
		
		
		
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = CountermarkTestComplexTicketV1.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
        
    }   
	

}
