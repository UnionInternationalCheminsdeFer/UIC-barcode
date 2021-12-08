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
import org.uic.barcode.ticket.api.asn.omv3.CodeTableType;
import org.uic.barcode.ticket.api.asn.omv3.IncludedOpenTicketType;
import org.uic.barcode.ticket.api.asn.omv3.OpenTicketData;
import org.uic.barcode.ticket.api.asn.omv3.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.test.testtickets.AllElementsTestTicketV3;


/**
 * 
 * 
 * 
 * 
 */
public class OpenTicketINcludedTicketDateTestV3 {
	
    
    
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
		
		assert(ticket.getTransportDocument().get(2).getTicket().getOpenTicket() != null);
		
		OpenTicketData ot = ticket.getTransportDocument().get(2).getTicket().getOpenTicket();
				
		assert(ot.getIncludedAddOns().size() == 1);
		IncludedOpenTicketType inc = ot.getIncludedAddOns().get(0);
		
		validate(inc);

		
		TimeZone current = TimeZone.getDefault();
		DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
		Date df = inc.getValidFromDate(ticket.getIssuingDetail().getIssuingDate());
		String vf = dateFormat.format(df);
		assert(vf.equals("2018.01.01-16:40"));
		
		Date du = inc.getValidUntilDate(ticket.getIssuingDetail().getIssuingDate());
		String vu = dateFormat.format(du);
		assert(vu.equals("2018.01.02-16:40"));
		
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
		
		assert(ot.getIncludedAddOns().size() == 1);
		IncludedOpenTicketType inc = ot.getIncludedAddOns().get(0);
		
		TimeZone current = TimeZone.getDefault();
		DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
		Date d1 = dateFormat.parse("2018.01.01-16:40");
		Date d2 = dateFormat.parse("2018.01.02-16:40");
		
		inc.setValidityDates(d1, d2, ticket.getIssuingDetail().getIssuingDate());
		
		TimeZone.setDefault(current);	
		
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = AllElementsTestTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
        
    } 
	private void validate(IncludedOpenTicketType t) {

		assert(t.getProductOwnerNum() == 23456);    
		assert(t.getProductOwnerIA5().equals("23456"));    
		assert(t.getProductIdNum() == 		 65535);    
		assert(t.getProductIdIA5().equals(		 "123456"));
		assert(t.getExternalIssuerId() ==     12);
		assert(t.getIssuerAutorizationId() == 13);		
		assert(t.getStationCodeTable().equals(CodeTableType.stationERA));    	         
		assert(t.getValidRegion() != null);
		assert(t.getValidRegion().size() == 1);
		assert(t.getValidFromDay() ==  0);
		assert(t.getValidFromTime() ==  1000);
		assert(t.getValidUntilDay() ==  1);
		assert(t.getValidUntilTime() ==  1000);
		assert(t.getClassCode().equals(TravelClassType.second));
		assert(t.getServiceLevel().equals("A"));
				
	    assert(t.getIncludedCarriersNum() != null);
	    assert(t.getIncludedCarriersNum().size() == 2);
	    assert(t.getIncludedCarriersNum().get(0) == 1080);
	    assert(t.getIncludedCarriersNum().get(1) == 1181);		
	    
        assert(t.getIncludedCarriersIA5() != null);
        assert(t.getIncludedCarriersIA5().size() == 2);
        assert(t.getIncludedCarriersIA5().get(0).equals("1080"));
        assert(t.getIncludedCarriersIA5().get(1).equals("1181"));
	    
   
        
        assert(t.getIncludedServiceBrands() != null);
        assert(t.getIncludedServiceBrands().size() == 2);
        assert(t.getIncludedServiceBrands().get(0) == 108);
        assert(t.getIncludedServiceBrands().get(1) == 118);
     
        assert(t.getExcludedServiceBrands() != null);
        assert(t.getExcludedServiceBrands().size() == 2);
        assert(t.getExcludedServiceBrands().get(0) == 108);
        assert(t.getExcludedServiceBrands().get(1) == 118);
               		
        assert(t.getTariffs() != null);
        assert(t.getTariffs().size() == 1);
                            
		assert(t.getInfoText().equals("included ticket"));
				
		assert(t.getIncludedTransportTypes() != null);
		assert(t.getIncludedTransportTypes().size() == 2);
		assert(t.getIncludedTransportTypes().get(0) == 10);
		assert(t.getIncludedTransportTypes().get(1) == 11);
				
		assert(t.getExcludedTransportTypes() != null);
		assert(t.getExcludedTransportTypes().size() == 2);
		assert(t.getExcludedTransportTypes().get(0) == 10);
		assert(t.getExcludedTransportTypes().get(1) == 18);				
		
        assert(t.getExtension() != null);
        		    	        
		 
		
	}
	

}
