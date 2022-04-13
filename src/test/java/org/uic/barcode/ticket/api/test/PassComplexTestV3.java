package org.uic.barcode.ticket.api.test;

import java.text.ParseException;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.api.asn.omv3.BoardingOrArrivalType;
import org.uic.barcode.ticket.api.asn.omv3.PassData;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.test.testtickets.PassComplexTicketV3;


/**
 * The Class Test asn.1 encoding of a pass.
 * 
 * 
 * 
 */
public class PassComplexTestV3 {
	
    
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
		String hex = PassComplexTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
        
    }    
		
	@Test public void encoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		String hex = PassComplexTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		
		byte[] encoded = UperEncoder.encode(ticket);
		
		
		
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = PassComplexTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
		
		assert(ticket.getTransportDocument().get(0).getTicket().getPass() != null);
		
		PassData p = ticket.getTransportDocument().get(0).getTicket().getPass();
		
		
		assert(p.getTrainValidity().getBordingOrArrival().equals(BoardingOrArrivalType.boarding));
		assert(p.getTrainValidity().getIncludedCarriersNum().contains(1234L));
		assert(p.getTrainValidity().getIncludedCarriersNum().contains(5678L));
		assert(p.getTrainValidity().getValidFromDay() == 0L);
		assert(p.getTrainValidity().getValidFromTime() == 1000L);
		assert(p.getTrainValidity().getValidUntilDay() == 1L);
		assert(p.getTrainValidity().getValidUntilTime() == 1000L);
		
 
        
    }   
	
}
