package org.uic.barcode.ticket.api.test;

import java.text.ParseException;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.api.asn.omv3.VoucherData;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.test.testtickets.VoucherTestTicketV3;


/**
 * The Class FipTimeZoneTestV3.
 * 
 * 
 * 
 */
public class AsnLevelVoucherTestV3 {
	
    
    
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
		ticket = VoucherTestTicketV3.getUicTestTicket();
		byte[] encoded = UperEncoder.encode(ticket);
						
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = VoucherTestTicketV3.getEncodingHex();
		
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
		String hex = VoucherTestTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(hex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
		
		byte[] encoded = UperEncoder.encode(ticket);
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		String expectedHex = VoucherTestTicketV3.getEncodingHex();
		
		assert(expectedHex.equals(encodedHex));
		
		assert(ticket.getTransportDocument().get(0).getTicket().getVoucher() != null);
		
		VoucherData vo = ticket.getTransportDocument().get(0).getTicket().getVoucher();
		

        assert(vo.getReferenceIA5().equals("ACHE12345"));
        assert(vo.getProductOwnerIA5().equals("COFFEEMACHINE"));
        assert(vo.getValidFromYear() == 2022L);
        assert(vo.getValidFromDay() == 1L);
        assert(vo.getValidUntilYear() == 2022L);
        assert(vo.getValidUntilDay() == 1);
        assert(vo.value == 500L);
        assert(vo.getInfoText().equals("coffee voucher"));
    }    
		 
	

}
