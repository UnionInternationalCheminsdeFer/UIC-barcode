package org.uic.barcode.ticket.api.test;

import java.text.ParseException;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.test.testtickets.AllElementsTestTicketV3;


/**
 * 
 * 
 * 
 */
public class AsnLevelAllElementsTestV3 {
	
    
    
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
		
		//get ticket
		String expectedHex = AllElementsTestTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(expectedHex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
		
		byte[] encoded = UperEncoder.encode(ticket);
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		
		int i = getIndexOfDifference(expectedHex,encodedHex);
		
		assert (i == 0);
	
		assert(expectedHex.equals(encodedHex));
		        
    }   
	
	@Test public void encoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get ticket
		String expectedHex = AllElementsTestTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(expectedHex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
		
		byte[] encoded = UperEncoder.encode(ticket);
						
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		
		int i = getIndexOfDifference(expectedHex,encodedHex);
		
		assert (i == 0);
		
		assert(expectedHex.equals(encodedHex));
        
    }  	
	
		 
	public static int getIndexOfDifference(String s1, String s2) {
	
		if (s1 == null || s2 == null) return -1;
		
		char[] ca1 = null;
		char[] ca2 = null;
	
		if (s1.length() > s2.length()) {
			ca1 = s1.toCharArray();
			ca2 = s2.toCharArray();
		} else {
			ca1 = s2.toCharArray();
			ca2 = s1.toCharArray();			
		}
		
		int i = 0;
		for (char c : ca2) {
			if (c != ca1[i]) return i;
			i++;
		}
		return 0;
	}
	

}
