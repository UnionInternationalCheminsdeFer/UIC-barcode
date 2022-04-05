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
import org.uic.barcode.ticket.api.asn.omv3.LinkMode;
import org.uic.barcode.ticket.api.asn.omv3.TicketType;
import org.uic.barcode.ticket.api.asn.omv3.DelayConfirmation;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.test.testtickets.DelayTestTicketV3;


/**
 * The Class FipTimeZoneTestV3.
 * 
 * 
 * 
 */
public class AsnLevelDelayConfirmationTestV3 {
	
    
    
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
		ticket = DelayTestTicketV3.getUicTestTicket();
		byte[] encoded = UperEncoder.encode(ticket);
						
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = DelayTestTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
        
    }  
	
	@Test public void encodingDate() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		ticket = DelayTestTicketV3.getUicTestTicket();
		
		TimeZone current = TimeZone.getDefault();
		DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
		Date d = dateFormat.parse("2022.01.12-16:40");
		ticket.getTransportDocument().get(0).getTicket().getDelayConfirmation().setPlannedArrivalDate(d);
		TimeZone.setDefault(current);
		
		
		byte[] encoded = UperEncoder.encode(ticket);
						
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = DelayTestTicketV3.getEncodingHex();
		
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
		String hex = DelayTestTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
		
		byte[] encoded = UperEncoder.encode(ticket);
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = DelayTestTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
		
		assert(ticket.getTransportDocument().get(0).getTicket().getDelayConfirmation() != null);
		
		DelayConfirmation del = ticket.getTransportDocument().get(0).getTicket().getDelayConfirmation();
		
		/*
		 *         
		referenceIA5 "ABDJ12345",
        trainNum 100,
        departureYear 2022,
        departureDay 12,
        departureTime 1000,
        stationCodeTable stationUIC,
        stationNum 8000001,
        delay 31,
        trainCancelled FALSE,
        confirmationType travelerDelayConfirmation,
        affectedTickets {
          {
            referenceNum 801234567890,
            productOwnerNum 1080,
            ticketType openTicket,
            linkMode issuedTogether
          }
        },
        infoText "delay confirmation"
		 */
		
		assert(del.getAffectedTickets().size() == 1);
		assert(del.getDelay() == 31L);
		assert(del.getPlannedArrivalDay() == 12L);
		assert(del.getPlannedArrivalTime() == 1000L);
		assert(del.getPlannedArrivalYear() == 2022L);
		assert(del.getTrainCancelled() == false);
		assert(del.getInfoText().equals("delay confirmation"));
		assert(del.getReferenceIA5().equals("ABDJ12345"));
		assert(del.getTrainNum() == 100L);
		
		assert(del.getAffectedTickets().get(0).getReferenceNum() == 801234567890L);
		assert(del.getAffectedTickets().get(0).getProductOwnerNum() == 1080L);
		assert(del.getAffectedTickets().get(0).getTicketType().equals(TicketType.openTicket));
		assert(del.getAffectedTickets().get(0).getLinkMode().equals(LinkMode.issuedTogether));
		
		TimeZone current = TimeZone.getDefault();
		DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
		String pd = dateFormat.format(del.getPlannedArrivalDate());
		assert(pd.equals("2022.01.12-16:40"));
		TimeZone.setDefault(current);
        
    }    
		 
	

}
