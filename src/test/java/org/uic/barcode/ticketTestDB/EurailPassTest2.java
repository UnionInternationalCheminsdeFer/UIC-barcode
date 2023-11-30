package org.uic.barcode.ticketTestDB;


import java.util.Base64;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;
import org.uic.barcode.test.utils.TestUtils;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public class EurailPassTest2 {
	
    TimeZone defaulttimeZone = null;
    
    String ticketBase64 = "I1VUMDE5OTAxVFQwMDEwLAIUNSMMrRGIB8fL3UcWDUxGq2yI5YICFEtBKOXG+1"
    		+ "iZAAHvKzvpQNsmkR4CAAAAADAzNzd4nFVSTWvDMAz9KzoHmkl2HKdHL3FpIHWK63T0VAYrbDB2"
    		+ "2MZ+/yT3MyYJSJbee3rKdFx71yEhGr1cIsH9OGWXK7sgpQlJoUL+VuQDwHRMgztwU91gbBPf6I"
    		+ "ZvuZ1fPrWfousHTnMKK0lVwW28kFxrmljC7qMoipw4J6lpx8128BsfkosH2LrdDimDNNJj23EK"
    		+ "KTLzDYconH7fT9+fr19vPygqCbXU6rbfc1RzbLIAmQ6VgPEwMougw+Kp7xYhosqI+cIUD+dPaV"
    		+ "RUcXktKMZ105CYQV1xzd4NfccZBlbCrLSmEqkULbCAe4D6Tq47lzyMK3juY1qjzuwCx+qpLAp+"
    		+ "xEf2jvCcL7AmnEVXUUqf7YYxeLg4dLauNuaxo6ZZZG8RoZ0x2RmTnaHYGYoVFVhnkG0cu6lNsm"
    		+ "jSxD3ayh7sGIYDZJfgpU9rlrj3EVzossjtGBPvgCpjL+bIChQ++nExkMythiqeeQS4rol/iOYiBJe3H/cfqY6d6Q==";

	
    
    String ticketHex = "23555430323131383130303330314713bb51986a0c947ce7b06aeb53ef44b96c7cbab606cf"
              + "ee61d19d56c352ffc1ace883e82b4edaada9fa715eb7d501fec6df3bc8bd8fc570f3cc37"
              + "887f75482630323139789c0b8d77f3718d3030363032322e52625467bced17c0cb69aad3"
              + "b0d1b05aa92435b72027b124352cb5a838333f4fc94ac9c8d8c852cf5ccf50494729bfb4"
              + "a4a0b424a4b22015281ee0e2a654eb202adc2030e7d2b57b370ebd7ac6b7eed08d17174e"
              + "3d99d17b412043f800038b4cc6fd33394fde78acd5ddb8746bebc5a5bd6ba75e68e68b68"
              + "d4bc3a77eace090d0a5b1a6fee3db9996fe2d5994be74e3dd3180892bfc4662278f38398"
              + "3a7f8a4f8e9e4f57ce3acd736b97b8bbb8b9b8b680043a755a801c35360848810876f9ac"
              + "3b0951a5e2bf800100bac05beb";
    
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
        
        Assert.assertNotNull(layout);        
        //Assert.assertNotNull(ticket);        
        
     }
    

}