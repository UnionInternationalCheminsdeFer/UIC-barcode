package org.uic.barcode.ticketTestDB;

import org.junit.Assert;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;
import org.uic.barcode.ticket.api.spec.IOpenTicket;
import org.uic.barcode.ticket.api.spec.ITrainLink;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public class DecodeSparpreisTicketDBTest {
	
    @Test
    public void testDecoder() throws Exception {


        byte[] content = UperEncoder.bytesFromHexString(getEncodingV2Hex());

          // try to decode
        Decoder decoder = new Decoder(content);
        TicketLayout layout = decoder.getLayout();
        IUicRailTicket ticket = decoder.getUicTicket();
        
        
        Assert.assertNotNull(ticket);        
        
        Assert.assertNotNull(ticket.getDocumentData());
        Assert.assertNotNull(ticket.getIssuerDetails());        
        Assert.assertNotNull(ticket.getTravelerDetails());
        assert(ticket.getTravelerDetails().getTravelers().size() == 1); 
        assert(ticket.getDocumentData().size() == 1);
        IOpenTicket openTicket = (IOpenTicket) ticket.getDocumentData().iterator().next();
        Assert.assertNotNull(openTicket.getValidRegionList());
        ITrainLink tl = (ITrainLink) openTicket.getValidRegionList().iterator().next();
        assert(tl.getTrain().contentEquals("ICE973"));
        Assert.assertNotNull(decoder);
    }
    
	public static String getEncodingV2Hex() {
		
		 return "2355543032313038303030303032782e" + 
				"2fe184a1d85e89e9338b298ec61aeba2" + 
				"48ce722056ca940a967c8a1d39126e2c" + 
				"628c4fcea91ba35216a0a350f894de5e" + 
				"bd7b8909920fde947feede0e20c43031" + 
			    "3939789c01bc0043ff555f464c455831" + 
				"333031383862b20086e10dc125ea2815" + 
				"110881051c844464d985668e23a00a80" + 
				"000e96c2e4e6e8cadc08aed2d8d90104" + 
				"44d7be0100221ce610ea559b64364c38" + 
				"a82361d1cb5e1e5d32a3d0979bd099c8" + 
				"426b0b7373432b4b6852932baba3634b" + 
				"733b2b715ab34b09d101e18981c181f1" + 
				"424221521291521292a17a3a920a1152" + 
				"5a095282314952b20a49529952826278" + 
				"083001a4c38ae5bb303ace7003800700" + 
				"14b00240400f53757065722053706172" + 
				"7072656973c41e4a03";
		 }
}