package org.uic.barcode.ticket.api.test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.spec.IDelayConfirmation;
import org.uic.barcode.ticket.api.spec.IDocumentData;
import org.uic.barcode.ticket.api.spec.ILinkMode;
import org.uic.barcode.ticket.api.spec.ITicketLink;
import org.uic.barcode.ticket.api.spec.ITicketType;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.ticket.api.test.testtickets.DelayTestTicketV3;
import org.uic.barcode.ticket.api.utils.Api2AsnEncoder;
import org.uic.barcode.ticket.api.utils.Api2OpenAsnEncoderV3;
import org.uic.barcode.ticket.api.utils.Asn2ApiDecoder;
import org.uic.barcode.ticket.api.utils.OpenAsn2ApiDecoderV3;


/**
 * The Class VoucherTestV1.
 * 
 * 
 * 
 */
public class DelayConfirmationTestV3 {
	
	   
    /** The decoder. */
    Asn2ApiDecoder decoder = new OpenAsn2ApiDecoderV3();
    
    /** The encoder. */
    Api2AsnEncoder encoder = new Api2OpenAsnEncoderV3();
    
    /** The API ticket low level encoded for case 1. */
    IUicRailTicket iTicketDecodedFromAsn1Case1 = null;
      
    
    /** The ticket decoded 1. */
    IUicRailTicket iTicketDecodedCase1 = null;
        
    byte[] encodedInTimeZone1 = null;
   
       
    TimeZone defaulttimeZone = null;
    
	/**
	 * Prepare tickets.
	 */
	@Before public void prepare() {
			
		LoggerFactory.setActivateConsoleLog(true);
		
		defaulttimeZone = TimeZone.getDefault();  	

		
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
	@Test public void testDelayConfirmation() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		
		defaulttimeZone = TimeZone.getDefault();
    	
    	//encode in UTC time zone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		IUicRailTicket ticketDecoded = null;
		try {
			ticketDecoded = decoder.decodeFromAsn(DelayTestTicketV3.getEncodingBytes());
		} catch (IOException e) {
			assert(false);
		}
		
		IDocumentData document = ticketDecoded.getDocumentData().iterator().next();
		assert(document != null);
		assert(document instanceof IDelayConfirmation);
		
		IDelayConfirmation del = (IDelayConfirmation) document;
		assert(del.getDelay() == 31);
				
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
		
		assert(del.getLinkedTickets().size() == 1);
		assert(del.getDelay() == 31L);
		assert(del.isTrainCancelled() == false);
		assert(del.getInfoText().equals("delay confirmation"));
		assert(del.getReference().equals("ABDJ12345"));
		assert(del.getTrain().equals("100"));
		
		
		ITicketLink tl = del.getLinkedTickets().iterator().next();
		assert(tl.getReference().equals("801234567890"));
		assert(tl.getProductOwner().equals("1080"));
		assert(tl.getTicketType().equals(ITicketType.openTicket));
		assert(tl.getLinkMode().equals(ILinkMode.issuedTogether));
		
		TimeZone current = TimeZone.getDefault();
		DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
		String pd = dateFormat.format(del.getArrivalDate());
		assert(pd.equals("2022.01.12-16:40"));
		TimeZone.setDefault(current);
		
    	//encode in UTC time zone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    	
        byte[] encoded = null;
		try {
			encoded = encoder.encode(ticketDecoded);
		} catch (EncodingFormatException e) {
			assert(false);
		}
		
		
		IUicRailTicket ticketDecoded2 = null;
		try {
			ticketDecoded2 = decoder.decodeFromAsn(encoded);
		} catch (IOException e) {
			assert(false);
		}
				
		assert (ticketDecoded2 != null);
		
		String hex1 = UperEncoder.hexStringFromBytes(encoded);
		String hex2 = DelayTestTicketV3.getEncodingHex();
	    assert(hex1.equals(hex2));
		
		
		
              
	    TimeZone.setDefault(defaulttimeZone);
    }    
		

	
}
