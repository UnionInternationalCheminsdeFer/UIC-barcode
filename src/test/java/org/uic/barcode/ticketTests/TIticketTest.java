package org.uic.barcode.ticketTests;


import java.util.Base64;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.dynamicFrame.api.IDynamicFrame;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public class TIticketTest {
	
    TimeZone defaulttimeZone = null;
    
    String ticketBase64 = "AVVlV4hJ4ABQCCRocJknuREASeB6KmwhTRgwYMGrRg4coAqB4AROT01FB0NP"
    		+ "R05PTUVPAEAEEgFYWAAAFAO5r+0lA5zvA32uCITK5NwUtOrK5NLG0ECQxJ3AidwAAAQVQyRn"
    		+ "HoGAg4SwQyQAsoGCAYCEFUMkZx6CAYEtmCyYCYMDlUMkZx6BAIMEFUMkZx6BgIOBoQACIdM/"
    		+ "3NC/C94syIud9wO7mYNByejQ4l/ik6HEhi7t3XV7vuPZQox/T2r6zccEDw3Ri48MO0LAjOH6"
    		+ "sdzYk9CRfgMaOYIoEQM8jg15cDXzeO2ixAMSKKNvwfo2Fa5brPyMkyd0o0EpmBEIBRkK7smz"
    		+ "ZoF34ztlSrOxWZs5itVsgL3PIlWZ/yhVOgpo";

	

    
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


        byte[] content = Base64.getDecoder().decode(ticketBase64);
        
        // try to decode
        Decoder decoder = new Decoder(content);
        TicketLayout layout = decoder.getLayout();
        IUicRailTicket ticket = decoder.getUicTicket();
        IDynamicFrame frame = decoder.getDynamicFrame();

        Assert.assertNotNull(frame);       
        Assert.assertNotNull(frame.getLevel2Data());
        Assert.assertNotNull(frame.getLevel2Data().getLevel1Data());
        
        Assert.assertNotNull(frame.getLevel2Data().getLevel1Signature());        
        assert(frame.getFormat().equals(Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2));

        
     }
    

}