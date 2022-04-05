package org.uic.barcode.ticket.api.test;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.api.asn.omv3.OpenTicketData;
import org.uic.barcode.ticket.api.asn.omv3.TrainLinkType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.test.testtickets.AllElementsTestTicketV3;


/**
 * 
 * 
 * 
 * 
 */
public class OpenTicketTrainLinkDateTestV3 {
	
    
    
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
		String hex = AllElementsTestTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
		
		assert(ticket.getTransportDocument().get(2).getTicket().getOpenTicket() != null);
		
		OpenTicketData ot = ticket.getTransportDocument().get(2).getTicket().getOpenTicket();
				
		assert(ot.getValidRegion().size() > 2);
		
		TrainLinkType tl = ot.getValidRegion().get(3).getTrainLink();
		
				
		TimeZone current = TimeZone.getDefault();
		DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
		Date df = tl.getDepartureDate(ticket.getIssuingDetail().getIssuingDate());
		String vf = dateFormat.format(df);
		assert(vf.equals("2018.01.03-23:59"));
				
		String id = dateFormat.format(ticket.getIssuingDetail().getIssuingDate());
		assert(id.equals("2018.01.01-10:00"));	
				
		TimeZone.setDefault(current);
		
    }    
		
	@Test public void encoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		ticket = AllElementsTestTicketV3.getUicTestTicket();
		
		byte[] encoded = UperEncoder.encode(ticket);
		
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = AllElementsTestTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
        
    }   
	
	@Test public void encodingDate() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		ticket = AllElementsTestTicketV3.getUicTestTicket();
		
		byte[] encoded = UperEncoder.encode(ticket);
		
		OpenTicketData ot = ticket.getTransportDocument().get(2).getTicket().getOpenTicket();
		
		assert(ot.getValidRegion().size() > 2);
		
		TrainLinkType tl = ot.getValidRegion().get(3).getTrainLink();
		
		TimeZone current = TimeZone.getDefault();
		DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
		Date d = dateFormat.parse("2018.01.03-23:59");
		
		tl.setDepartureDate(d, ticket.getIssuingDetail().getIssuingDate());
		
		TimeZone.setDefault(current);	
		
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = AllElementsTestTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
        
    } 

	

}
