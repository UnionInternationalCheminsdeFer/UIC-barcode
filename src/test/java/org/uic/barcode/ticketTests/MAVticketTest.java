package org.uic.barcode.ticketTests;


import java.util.TimeZone;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.dynamicFrame.api.IDynamicFrame;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;
import org.uic.barcode.test.utils.TestUtils;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public class MAVticketTest {
	
    TimeZone defaulttimeZone = null;
    

	
    String ticketHex = "2355543031313135345454303031"
    		+ "302C02140501D652A1F1F70BB3065D14801ECAC6B1DB7B7D0214918A522B1B1FACC567BB4E3052F7B1116B9D2EC600000000"
    		+ "30343035"
    		+ "789C5551C16E9C3010ED99AFF03141CA32B6B159A26A2503CE424C01D98696BD449B64B5425D51A9"
    		+ "CAAE947FC867E5C36A236DABCEE9F9CDCC7B33E3FEA994A2000CC028C62C0E1D48EE80D02FFF0233"
    		+ "5813C01C03E12C3785EC9F6C2D46D71403D7B92500900201B6302E62AF048009C434756FC61FAB5D"
    		+ "211B258207516A9397B26A82A03BEDDFA6687B3EBD4DC77B046405E9CAFBDC01BD42D7EB44B017C1"
    		+ "3C008C8AD674B21E23A9BFAF803B07BC2481755A9402388DAFCC3AEB0BD149638173EE26639E740D"
    		+ "6B8769B254E0A112E8AB1F7673538A21D36D11E52BAB65761B66BA6943D5DBF166AB6F7D0DDF645A"
    		+ "D8CAD46210E156D4A1D9B5994F063ECB36A10F3F10D0C52A89875C9846A0EBEE480EBAED44F45069"
    		+ "63D1B7AAE9AD44B2779C74B7F87599E6F91D5D4EFB9FD1EE7C7C9EE6D7F37C44B241719260EACE40"
    		+ "96BD489C1FE63D628C20F51274BFA7970322E075FC1EBD75FE1852F508980DA351EDE74755A36E7F"
    		+ "399C54E1F6C72A074C317210A5E91AC53C510A808FC2FC30D016FEE67F3FC255DD0314AD27E9FFE4"
    		+ "1FC2CF86380000000000000000000000000000000000000000000000000000000000000000";

    
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


        //byte[] content = Base64.getDecoder().decode(ticketBase64);
        
        byte[] content = TestUtils.hexStringToByteArray(ticketHex);
        
        // try to decode
        Decoder decoder = new Decoder(content);
        TicketLayout layout = decoder.getLayout();
        IUicRailTicket ticket = decoder.getUicTicket();
        IDynamicFrame frame = decoder.getDynamicFrame();

        
        Assert.assertNotNull(layout);     
        Assert.assertNull(frame);    
        Assert.assertNull(ticket);       
        //Assert.assertNotNull(frame.getLevel2Data());
        //Assert.assertNotNull(frame.getLevel2Data().getLevel1Data());
        
        //Assert.assertNotNull(frame.getLevel2Data().getLevel1Signature());        
        //assert(frame.getFormat().equals(Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2));

        
     }
    

}