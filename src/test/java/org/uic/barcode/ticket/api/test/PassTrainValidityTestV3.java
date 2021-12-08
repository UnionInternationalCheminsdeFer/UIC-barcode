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
import org.uic.barcode.ticket.api.asn.omv3.BoardingOrArrivalType;
import org.uic.barcode.ticket.api.asn.omv3.PassData;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.test.testtickets.AllElementsTestTicketV3;


/**
 * The Class Test asn.1 encoding of a pass.
 * 
 * 
 * 
 */
public class PassTrainValidityTestV3 {
	
    
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
		String hex = AllElementsTestTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
		
		assert(ticket.getTransportDocument().get(3).getTicket().getPass() != null);
		
		PassData p = ticket.getTransportDocument().get(3).getTicket().getPass();
		
		TimeZone current = TimeZone.getDefault();
		DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
		Date df = p.getValidFromDate(ticket.getIssuingDetail().getIssuingDate());
		String vf = dateFormat.format(df);
		assert(vf.equals("2018.01.01-16:40"));
		
		Date du = p.getValidUntilDate(ticket.getIssuingDetail().getIssuingDate());
		String vu = dateFormat.format(du);
		assert(vu.equals("2018.01.02-16:40"));
		
		String id = dateFormat.format(ticket.getIssuingDetail().getIssuingDate());
		assert(id.equals("2018.01.01-10:00"));		
		
		//ticketvalidity
		String tvf = dateFormat.format(p.getTrainValidity().getValidFromDate(ticket.getIssuingDetail().getIssuingDate()));
		assert(tvf.equals("2018.01.01-16:40"));
		
		String tvu = dateFormat.format(p.getTrainValidity().getValidUntilDate(ticket.getIssuingDetail().getIssuingDate()));
		assert(tvu.equals("2018.01.02-16:40"));
		
		//validityDetails
		String vdf = dateFormat.format(p.getValidityPeriodDetails().getValidityPeriod().get(0).getValidFromDate(ticket.getIssuingDetail().getIssuingDate()));
		assert(vdf.equals("2018.01.01-16:40"));
		
		String vdu = dateFormat.format(p.getValidityPeriodDetails().getValidityPeriod().get(0).getValidUntilDate(ticket.getIssuingDetail().getIssuingDate()));
		assert(vdu.equals("2018.01.02-16:40"));
		
		TimeZone.setDefault(current);
        
    }    
		
	@Test public void encoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		String hex = AllElementsTestTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		
		byte[] encoded = UperEncoder.encode(ticket);	
		
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = AllElementsTestTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
		
		assert(ticket.getTransportDocument().get(3).getTicket().getPass() != null);
		
		PassData p = ticket.getTransportDocument().get(3).getTicket().getPass();
		
		
		assert(p.getTrainValidity().getBordingOrArrival().equals(BoardingOrArrivalType.boarding));
		assert(p.getTrainValidity().getIncludedCarriersNum().contains(1234L));
		assert(p.getTrainValidity().getIncludedCarriersNum().contains(5678L));
		assert(p.getTrainValidity().getValidFromDay() == 0L);
		assert(p.getTrainValidity().getValidFromTime() == 1000L);
		assert(p.getTrainValidity().getValidUntilDay() == 1L);
		assert(p.getTrainValidity().getValidUntilTime() == 1000L);
		        
    }   
	
	@Test public void encodingDate() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		String hex = AllElementsTestTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		PassData p = ticket.getTransportDocument().get(3).getTicket().getPass();
		
		TimeZone current = TimeZone.getDefault();
		DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
		Date d1 = dateFormat.parse("2018.01.01-16:40");
		Date d2 = dateFormat.parse("2018.01.02-16:40");
		
		p.setValidityDates(d1, d2, ticket.getIssuingDetail().getIssuingDate());
		p.getTrainValidity().setValidityDates(d1, d2, ticket.getIssuingDetail().getIssuingDate());
		p.getValidityPeriodDetails().getValidityPeriod().get(0).setValidityDates(d1, d2, ticket.getIssuingDetail().getIssuingDate());
		
		TimeZone.setDefault(current);		
		
		
		byte[] encoded = UperEncoder.encode(ticket);
		
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = AllElementsTestTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
		
		
		        
    } 
}
