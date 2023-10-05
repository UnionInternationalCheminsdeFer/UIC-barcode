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

public class EurailPassTest {
	
    TimeZone defaulttimeZone = null;
    
    String ticketBase64 = "I1VUMDE5OTAxVFQwMDEwLAIUNSMMrRGIB8fL3UcWDUxGq2yI5YICFEtBKOXG+1"
    		+ "iZAAHvKzvpQNsmkR4CAAAAADAzNzd4nFVSTWvDMAz9KzoHmkl2HKdHL3FpIHWK63T0VAYrbDB2"
    		+ "2MZ+/yT3MyYJSJbee3rKdFx71yEhGr1cIsH9OGWXK7sgpQlJoUL+VuQDwHRMgztwU91gbBPf6I"
    		+ "ZvuZ1fPrWfousHTnMKK0lVwW28kFxrmljC7qMoipw4J6lpx8128BsfkosH2LrdDimDNNJj23EK"
    		+ "KTLzDYconH7fT9+fr19vPygqCbXU6rbfc1RzbLIAmQ6VgPEwMougw+Kp7xYhosqI+cIUD+dPaV"
    		+ "RUcXktKMZ105CYQV1xzd4NfccZBlbCrLSmEqkULbCAe4D6Tq47lzyMK3juY1qjzuwCx+qpLAp+"
    		+ "xEf2jvCcL7AmnEVXUUqf7YYxeLg4dLauNuaxo6ZZZG8RoZ0x2RmTnaHYGYoVFVhnkG0cu6lNsm"
    		+ "jSxD3ayh7sGIYDZJfgpU9rlrj3EVzossjtGBPvgCpjL+bIChQ++nExkMythiqeeQS4rol/iOYiBJe3H/cfqY6d6Q==";

	
    
    String ticketHex = "2355543031353231373030303031302D021500A7958EBB21072C93018BEF922A"
    		+ "53F597AAE3B5E90214625157E0A477C8955BFC2F288B450F5AB8501592000000303233367"
    		+ "8DA554F5D4BC33014FD2B795470E5DCDC246D1F675BF5414146ABEC6954565D5997413705"
    		+ "FF9B6FFE316F52E8F042B83987F391349B876A598200CB56535ABEAFBF5F0AF56F9022D5D"
    		+ "04C6083B252AAD9D48FCBB59858EB55516B32220265C2E490C9EFDADDB86FC77327B11A44"
    		+ "81243CB5DEABD77E187AA1444C26D0A6AC1795DFEE3FFDB65BDC16A0E0C064916DEDEC7FE"
    		+ "EC6D3D15F75FE1A1C25C10F139E06E7DCECD298EE3620B127F20127676690C872B1E7A2C9"
    		+ "84C92E0C27314972A4405AF8FEF76738F71FEAEB7850313309C5EAAD3F45984D70F271CCE"
    		+ "690C455B312ECE66493DF007F3C8154C0";
    
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