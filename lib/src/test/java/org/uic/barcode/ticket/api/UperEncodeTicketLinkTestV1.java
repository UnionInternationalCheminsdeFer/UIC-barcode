package org.uic.barcode.ticket.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData;
import org.uic.barcode.ticket.api.spec.ILinkMode;
import org.uic.barcode.ticket.api.spec.ITicketLink;
import org.uic.barcode.ticket.api.spec.ITicketType;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.ticket.api.testtickets.SimpleTicketLinkTestTicket;
import org.uic.barcode.ticket.api.utils.OpenAsn2ApiDecoder;


public class UperEncodeTicketLinkTestV1 {

 



    @Test public void testEncodeTicket() throws IllegalArgumentException, IllegalAccessException {
    	UicRailTicketData ticket = SimpleTicketLinkTestTicket.getUicTestTicket();
        byte[] encoded = UperEncoder.encode(ticket);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
       
        String expectedHex = SimpleTicketLinkTestTicket.getEncodingV1Hex();
         UperEncoder.logger.debug(String.format("first difference at index: %d",hex.compareTo(expectedHex)));
        UperEncoder.logger.debug(String.format("data returned: %s",hex)); 
        UperEncoder.logger.debug(String.format("data expected: %s",expectedHex)); 
        assert(hex.equals(expectedHex));
    }
    
	@Test public void testDecodeTicket() throws IllegalArgumentException, IllegalAccessException {
    	UicRailTicketData ticket = SimpleTicketLinkTestTicket.getUicTestTicket();
        byte[] encoded = UperEncoder.encode(ticket);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        String expectedHex = SimpleTicketLinkTestTicket.getEncodingV1Hex();
        assertEquals(hex,expectedHex);
        UicRailTicketData decodedTicket  = UperEncoder.decode(encoded, UicRailTicketData.class);
        assert(decodedTicket != null);
        assertEquals(decodedTicket.controlDetail.ageCheckRequired , false );
        assertEquals(decodedTicket.controlDetail.getInfoText() , "cd" );
        assertEquals(decodedTicket.controlDetail.getIncludedTickets().get(0).getProductOwnerIA5() , "test" );
        assert(decodedTicket.controlDetail.getIdentificationByCardReference().get(0).getTrailingCardIdNum() == 100L );
        assert(decodedTicket.controlDetail.getIncludedTickets().size() == 1);
  	}    

	@Test public void testDecodeTicket2Api() throws IllegalArgumentException, IllegalAccessException {
    	UicRailTicketData ticket = SimpleTicketLinkTestTicket.getUicTestTicket();
        byte[] encoded = UperEncoder.encode(ticket);
        UicRailTicketData decodedTicket  = UperEncoder.decode(encoded, UicRailTicketData.class);       
        
        OpenAsn2ApiDecoder decoder = new OpenAsn2ApiDecoder();
        IUicRailTicket uicTicket = null;
		try {
			uicTicket = decoder.decodeFromAsn(decodedTicket);
		} catch (IOException e) {
			assert (false);
		}
        
        assert(uicTicket.getControlDetails().getLinkedTickets().size() == 1);
        for (ITicketLink link: uicTicket.getControlDetails().getLinkedTickets() ) {   	
              	assert(link.getLinkMode() == ILinkMode.onlyValidInCombination);
              	assert(link.getTicketType() == ITicketType.pass);
        }
	}    

	
}
