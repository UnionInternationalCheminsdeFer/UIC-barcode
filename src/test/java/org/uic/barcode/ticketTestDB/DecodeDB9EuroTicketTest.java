package org.uic.barcode.ticketTestDB;


import java.util.TimeZone;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;
import org.uic.barcode.ticket.api.spec.IOpenTicket;
import org.uic.barcode.ticket.api.spec.IPassengerType;
import org.uic.barcode.ticket.api.spec.ITariff;
import org.uic.barcode.ticket.api.spec.ITrainLink;
import org.uic.barcode.ticket.api.spec.ITraveler;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public class DecodeDB9EuroTicketTest {
	
    TimeZone defaulttimeZone = null;
	
	/**
	 * Prepare tickets.
	 */
	@Before public void prepare() {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		defaulttimeZone = TimeZone.getDefault();
    	//decode in local CET time zone
		TimeZone.setDefault(TimeZone.getTimeZone("CET"));
	}
	
	
	/**
	 * clean up
	 */
	@After public void resetTimeZone() {
		TimeZone.setDefault(defaulttimeZone);
	}
	
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

        assert(ticket.getIssuerDetails().getIssuer().equals("1080"));
        assert(ticket.getIssuerDetails().getIssuerPNR().equals("D260V48G"));
        String issuingDate = ticket.getIssuerDetails().getIssuingDate().toString();
        assert(issuingDate.equals("Fri Oct 30 11:50:00 CET 2020"));
        assert(ticket.getIssuerDetails().getSecurityProvider().equals("1080"));
        assert(ticket.getIssuerDetails().isSecurePaperTicket() == false);
        assert(ticket.getIssuerDetails().isActivated() == true);
        assert(ticket.getIssuerDetails().isSpecimen() == false);
        
        assert(ticket.getTravelerDetails().getTravelers().size() == 1); 
        ITraveler traveler = ticket.getTravelerDetails().getTravelers().iterator().next();
        assert(traveler.getFirstName().equals("Karsten"));
        assert(traveler.getLastName().equals("Will"));
        assert(traveler.isTicketHolder() == true);
        
        assert(ticket.getDocumentData().size() == 1);
        IOpenTicket openTicket = (IOpenTicket) ticket.getDocumentData().iterator().next();
        Assert.assertNotNull(openTicket.getValidRegionList());
        assert(openTicket.getReference().equals("CN0CTUMY"));
        String fromDate = openTicket.getValidFrom().toString();
        assert(fromDate.equals("Thu Nov 05 00:00:00 CET 2020"));
        assert(openTicket.getValidFromUTCoffset() == -4L);
        String toDate = openTicket.getValidUntil().toString();
        assert(toDate.equals("Fri Nov 06 10:00:00 CET 2020"));
        assert(openTicket.getValidUntilUTCoffset() == -4L);
        
        Assert.assertNotNull(openTicket.getTariffs());
        assert(openTicket.getTariffs().size() == 1);
        ITariff tariff = openTicket.getTariffs().iterator().next();
        assert(tariff.getNumberOfPassengers() == 1);
        assert(tariff.getPassengerType().equals(IPassengerType.adult));
        assert(tariff.getTariffDescription().equals("Super Sparpreis"));
        
        
        ITrainLink tl = (ITrainLink) openTicket.getValidRegionList().iterator().next();
        Assert.assertNotNull(tl);
        assert(tl.getTrain().equals("ICE973"));
        String departureDate = tl.getDepartureDateTime().toString();
        assert(departureDate.equals("Fri Nov 06 11:58:00 CET 2020"));
        Assert.assertNull(layout);
        Assert.assertNotNull(decoder);
    }
    
	public static String getEncodingV2Hex() {
		
		 return "2355543032323038303030303033000000005C57C2"+
				 "8521C3A1C3BCC29D5DC3960E2CC2897C18C390534"+
				 "EC2AFC387C388703AC2B367C39FC3BD55C2B70000"+
				 "0000422E1BC29F1CC3B9265E43C2B7493B2B51C39"+
				 "2C291007CC2B5C2870D3A2EC3B3C38249C38FC2B3"+
				 "3032343778C29C0BC28DC3B770757431303430303"+
				 "53534343656400379C2A539394049132303232343"+
				 "430353C283C3943CC2905068C2BCC29BC28F6B045"+
				 "0C29BC291C28141C28101430AC3974C76C3BFC39C"+
				 "C2BCC38CC292C38C420D2626090E064715C3AEC29"+
				 "6060FC3A66E29C28D74C2BFC288C29277C391C2BB"+
				 "C397C39DC289C2AC642E4A2D3978C39AC3ACC38D5"+
				 "CC383C2A4C2AB0BC3B27CC396C38DC2B04CC2BB3B"+
				 "79C2994EC3B4265EC3A7C28CC3845C1D05C2A7C38"+
				 "4C28CC2BCC28CC3BC340108C38F2D27C3BFC3B0C3"+
				 "BCC28CC384C2B4C394C2BC603D15C296C38606763"+
				 "1C293C380C292C2B069C38FC297C2851D4B136B57"+
				 "776209572F13336B370B7B16C2AEC2A032C3A508C"+
				 "283C282C388C3846561C2B9C2B337255DC38D33C2"+
				 "8BC29459C29B395B78C3AAC28EC399C39BC28E6D5"+
				 "D17C2937BC38732C3A73CC2B73A6FC38BC29D1D45"+
				 "4FC29BC2AEC3B1C38CC2B1C2883AC39B6CC38E7B6"+
				 "D7B16030046115A6F";
		 }

}