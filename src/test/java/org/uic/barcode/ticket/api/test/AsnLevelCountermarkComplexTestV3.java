package org.uic.barcode.ticket.api.test;

import java.text.ParseException;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.api.asn.omv3.CountermarkData;
import org.uic.barcode.ticket.api.asn.omv3.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.asn.omv3.ViaStationType;
import org.uic.barcode.ticket.api.asn.omv3.ZoneType;
import org.uic.barcode.ticket.api.test.testtickets.CountermarkTestComplexTicketV3;


/**
 * The Class FipTimeZoneTestV3.
 * 
 * 
 * 
 */
public class AsnLevelCountermarkComplexTestV3 {
	
    
    
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
		String hex = CountermarkTestComplexTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
		
		assert(ticket.getControlDetail() != null);
		
		assert(ticket.getIssuingDetail() != null);
		
		assert(ticket.getTransportDocument() != null);
		
		assert(ticket.getTransportDocument().get(0).getTicket().getCounterMark() != null);
		
		CountermarkData cm = ticket.getTransportDocument().get(0).getTicket().getCounterMark();
		
		assert(cm.getClassCode().equals(TravelClassType.first));
		assert(cm.getGroupName().equals("groupName"));
		assert(cm.getInfoText().equals("counterMark"));
		assert(cm.getNumberOfCountermark().equals(12L));
		assert(cm.getTotalOfCountermarks().equals(24L));
		assert(cm.getReturnIncluded().equals(false));
		assert(cm.getValidRegion() != null);
		assert(cm.getValidRegion().get(0).getViaStations() != null);
		assert(cm.getValidRegion().get(1).getZones() != null);
		
		ZoneType zone = cm.getValidRegion().get(1).getZones();	
		
		assert(zone.getZoneId() != null);
		assert(zone.getZoneId().get(0).equals(100L));
		assert(zone.getZoneId().get(1).equals(200L));

		ViaStationType via = cm.getValidRegion().get(0).getViaStations();
		
		assert(via.getBorder().equals(false));
		assert(via.getSeriesId().equals(999L));
		assert(via.getRoute().size() == 4);
		assert(via.getRoute().get(0).getStationNum() == 123455L);
		assert(via.getRoute().get(1).getStationNum() == 123456L);
		assert(via.getRoute().get(2).getAlternativeRoutes().size() == 2);
		assert(via.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().size() == 2);
		assert(via.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().size() == 2);
		assert(via.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(0).getStationNum() == 23455L);
		assert(via.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).getStationNum() == 23456L);
		assert(via.getRoute().get(3).getStationNum() == 123457L);
		
		assert(ticket.getTravelerDetail() != null);
		
        
    }    
		
	@Test public void encoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		String hex = CountermarkTestComplexTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		
		//ticket = OpenTestComplexTicketV2.getUicTestTicket();
		byte[] encoded = UperEncoder.encode(ticket);
		
		
		
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = CountermarkTestComplexTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
        
    }   
	
	@Test public void encodingDecoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		ticket = CountermarkTestComplexTicketV3.getUicTestTicket();
		byte[] encoded = UperEncoder.encode(ticket);
		
		
		
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = CountermarkTestComplexTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
        
    }  
}
