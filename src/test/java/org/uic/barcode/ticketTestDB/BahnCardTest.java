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
import org.uic.barcode.ticket.api.spec.ICustomerCard;
import org.uic.barcode.ticket.api.spec.ITravelClassType;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public class BahnCardTest {
	
    TimeZone defaulttimeZone = null;
    
    String ticketHex = "2355543031313038303030303031302D02150098E762AFB6D0BB7A7F629DCBFAB0BD04B4F0C53B02146111A3F5D92B5FDF83A0FAFD209CD3A56C37CE2A00000030353036789C65514D88D34018ED414B8DACB02CA8C7591069D596994926A9BDA549D696C6B2B46971B15027EED80E265349DA8A375111653DF8830B2B5E54943D08222C9E3C282A08E2A1E0C59B7B70F1EEC1AB93A2BBA0EF32336FDEF7F3BEAFD5AD38A60D11844445B008511E43A7E962D34B6D43FE220CB10A13D88EEDB4BA9E6B2E495A2DE286E561491B30D1A8EA71792F6A65B352B7CC860DFE81B7B4082A001BCAC94BA04CFBC2A2D1322010647101D4021AC72C972D5B04E6142ECE07A3988F1968985577D16D356587B2004A0AE85A7D1481DEBBD7C190F780CF38680FA27C4704B4C700E382C58086C3809FED33D111AE3C873E0F96E928BEC878CCE28E806A628A400D428D9CF893683C08012E16A05E48DC82BFB4CFE3296D4C69A8EBBA8C25C92CA4633931A819C943458AA254459FFA2CCA72912B35994FE321A7E218A889C18573484B6A4EA788F5B295AF8FC29045C08045A42184548231D13422BB4A642891C11A8D864CE4AB36D8594CABBBE03AA76422440CFF76EAFAE6CCDB37A9F51B7626FB3D25B7765F6F1F3E602CACEEFFE6BDDAFA95EE67DA1B5FFE67AF8EF71DDCF03FACE8CFD7487FB25EFAFCD8AABE389471F3331F83827B2DFD6CD7EEB4F37E4F78AB2082A77273F393C05B293D5CFBB9FAF2CCE6E54F5FE74EEFBDF7E468549B9F9DCCDEE477AE1C7940EF2A8FE67EFC068346B0C6";
	
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


        byte[] content = UperEncoder.bytesFromHexString(ticketHex);

          // try to decode
        Decoder decoder = new Decoder(content);
        TicketLayout layout = decoder.getLayout();
        IUicRailTicket ticket = decoder.getUicTicket();
        
        Assert.assertNotNull(layout);        
        Assert.assertNotNull(ticket);        
        
        assert(ticket.getDocumentData().size() == 1);
        ICustomerCard card = (ICustomerCard) ticket.getDocumentData().iterator().next();
        Assert.assertNotNull(card.getCardId().equals("7081411135225445"));
        assert(card.getCardTypeDescr().equals("My BahnCard 50 (2. Klasse)"));
        assert(card.getClassCode().equals(ITravelClassType.second));
        assert(card.getExtension() != null);
        assert(card.getIncludedServices().contains(new Integer(1)));
        
        Assert.assertNotNull(card.getCustomer() != null);
        
        
     }
    

}