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
import org.uic.barcode.ticket.api.asn.omv3.CarCarriageReservationData;
import org.uic.barcode.ticket.api.asn.omv3.LoadingDeckType;
import org.uic.barcode.ticket.api.asn.omv3.PriceTypeType;
import org.uic.barcode.ticket.api.asn.omv3.RoofRackType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.test.testtickets.CarCarriageReservationTestTicketV3;


/**
 * The Class FipTimeZoneTestV3.
 * 
 * 
 * 
 */
public class AsnLevelCarCarriageTestV3 {
	
    
    
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
	
	@Test public void encoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		ticket = CarCarriageReservationTestTicketV3.getUicTestTicket();
		byte[] encoded = UperEncoder.encode(ticket);
						
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = CarCarriageReservationTestTicketV3.getEncodingHex();
		
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
		String hex = CarCarriageReservationTestTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
		
		byte[] encoded = UperEncoder.encode(ticket);
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = CarCarriageReservationTestTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
		
		assert(ticket.getTransportDocument().get(0).getTicket().getCarCarriageReservation() != null);
		
		CarCarriageReservationData c = ticket.getTransportDocument().get(0).getTicket().getCarCarriageReservation();
		
		assert(c.getAttachedBicycles() == 1L);
		assert(c.getAttachedSurfboards() == 2L);
		assert(c.getBeginLoadingDate() == 10L);
		assert(c.getBeginLoadingTime() == 0L);
		assert(c.getCarCategory() == 3L);
		assert(c.getCoach().equals("21"));
		assert(c.getPlace().equals("41"));
		assert(c.getEndLoadingTime() == 500L);
		assert(c.getFromStationNum() == 8100001L);
		assert(c.getToStationNum() == 800001L);
		assert(c.getInfoText().equals("car carriage"));
		assert(c.getLoadingDeck().equals(LoadingDeckType.upper));
		assert(c.getLoadingListEntry() == 421L);
		assert(c.getTrainNum() == 123L);
		assert(c.getNumberPlate().equals("AD-DE-123"));
		assert(c.getTrailerPlate().equals("DX-AB-123"));
		assert(c.getTextileRoof() == false);
		assert(c.getServiceBrandAbrUTF8().equals("AZ"));
		assert(c.getServiceBrandNameUTF8().equals("special train"));
		assert(c.getServiceBrand() == 100L);
		assert(c.getRoofRackType().equals(RoofRackType.bicycleRack));
		assert(c.getTariff() != null);
		assert(c.getRoofRackHeight() == 20L);
		assert(c.getPriceType().equals(PriceTypeType.travelPrice));
		assert(c.getReferenceNum() == 810123456789L);
		assert(c.getVatDetails() != null);
		assert(c.getCarrierNum().contains(1080L));
		assert(c.getCarrierNum().contains(1181L));
		assert(c.getPrice() == 12345L);
		
		
		TimeZone current = TimeZone.getDefault();
		DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
		Date df = c.getBeginLoadingDate(ticket.getIssuingDetail().getIssuingDate());
		String vf = dateFormat.format(df);
		assert(vf.equals("2018.01.11-00:00"));
		
		Date du = c.getEndLoadingDate(ticket.getIssuingDetail().getIssuingDate());
		String vu = dateFormat.format(du);
		assert(vu.equals("2018.01.11-08:20"));
		
		String id = dateFormat.format(ticket.getIssuingDetail().getIssuingDate());
		assert(id.equals("2018.01.01-10:00"));	
				
		TimeZone.setDefault(current);
	
 
    }    
		 
	@Test public void encodingDate() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		ticket = CarCarriageReservationTestTicketV3.getUicTestTicket();
				
		CarCarriageReservationData c = ticket.getTransportDocument().get(0).getTicket().getCarCarriageReservation();
		
		TimeZone current = TimeZone.getDefault();
		DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
		Date d1 = dateFormat.parse("2018.01.11-00:00");
		Date d2 = dateFormat.parse("2018.01.11-08:20");
		c.setLoadingDates(d1, d2, ticket.getIssuingDetail().getIssuingDate());
		
		TimeZone.setDefault(current);	
						
		byte[] encoded = UperEncoder.encode(ticket);
		
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = CarCarriageReservationTestTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
        
    }  
	

}
