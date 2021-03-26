package org.uic.barcode.ticket.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.ticket.api.testtickets.OpenTestTicketV1;
import org.uic.barcode.ticket.api.utils.Api2OpenAsnEncoder;
import org.uic.barcode.ticket.api.utils.OpenAsn2ApiDecoder;


public class UperEncodeOpenTicketTestV1 {


    
	@Test public void testDecodeTicket() throws IllegalArgumentException, IllegalAccessException {
    	UicRailTicketData ticket = OpenTestTicketV1.getUicTestTicket();
        byte[] encoded = UperEncoder.encode(ticket);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        //String expectedHex = "78022020050DD3CF9F5CBCAA65E7D284EA40218A02D000822537B43701A237BB82B164CDA358088373CBBB4EFE40EDAF28EE4DEEAE004A03AD12B01416D08000000004039BDC195B951A58DAD95D125B999BC48088040EE0C2E6E6C2CECA021282DAE6E8CAE4C8C2DA0080BDA60100402C800131B20081013A65E7D00805881416D00B20283DA0"; 
        //assertEquals(hex,expectedHex);
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
