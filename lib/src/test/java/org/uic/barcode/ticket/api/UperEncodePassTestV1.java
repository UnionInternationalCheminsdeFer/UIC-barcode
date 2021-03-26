package org.uic.barcode.ticket.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.ticket.api.testtickets.PassTestTicketV1;
import org.uic.barcode.ticket.api.utils.Api2OpenAsnEncoder;
import org.uic.barcode.ticket.api.utils.OpenAsn2ApiDecoder;


public class UperEncodePassTestV1 {

    
	@Test public void testDecodeTicket() throws IllegalArgumentException, IllegalAccessException {
    	UicRailTicketData ticket =  PassTestTicketV1.getUicTestTicket();
        byte[] encoded = UperEncoder.encode(ticket);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));

        UicRailTicketData decodedTicket  = UperEncoder.decode(encoded, UicRailTicketData.class);
        
        OpenAsn2ApiDecoder decoder = new OpenAsn2ApiDecoder();
        IUicRailTicket iTicket = null;
        try {
        	iTicket = decoder.decodeFromAsn(encoded);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Api2OpenAsnEncoder encoder = new Api2OpenAsnEncoder();
        try {
        	encoded = encoder.encode(iTicket);
		} catch (EncodingFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        assert(decodedTicket != null);
        assertEquals(decodedTicket.controlDetail.ageCheckRequired , false );
        assertEquals(decodedTicket.controlDetail.getInfoText() , "cd" );
        assertEquals(decodedTicket.controlDetail.getIncludedTickets().get(0).getProductOwnerIA5() , "test" );
        assert(decodedTicket.controlDetail.getIdentificationByCardReference().get(0).getTrailingCardIdNum() == 100L );
    }    

}
