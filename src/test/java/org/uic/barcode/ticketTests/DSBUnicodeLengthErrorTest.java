package org.uic.barcode.ticketTests;


import java.util.Base64;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;


public class DSBUnicodeLengthErrorTest {
	
    TimeZone defaulttimeZone = null;
    
    String ticketBase64 = "I1VUMDExMTg2MDAwMDEwLAIUQ/owLvBw503VxO38HljgZC77oe4CFAELL"
    		+ "FSx+ASz93rCD7/hqq2Pc1wYAAAAADAzMzd42lVRy2qDUBD9FdehTc94n24KpkoSDDGoCW"
    		+ "QVLAoNiC1J6KJf1n1/rHMVfFx8zJkzjzNzj5dNHEYgQAkiq5WKj6uTibzJYZJ8+IqUIlm"
    		+ "VdXu8FLvwzEkayN4KHxD8MiYI/kNE+YqhBRE5P2VxHmensNime/hQHCddnHQdQcpnhysA"
    		+ "Z3AX/ioH1aEpqzt3ocFDcsm24T7O54ovmDcDD7NUhnEw5Un0WgGdVnV7r2FoVkCMCp5fY"
    		+ "eZQCzkMkvz9vtftR/ndehuOk9Mi3RgzlWrO21FlsHSqNaa8tgPymbMTzk52W6RrxuPE7oI"
    		+ "kLJlhq6d0vYf19ZDCG4AVo7zDLoxyWGmHCCU8BF1Ef6hXFjg72CZJnJ3XccYufvomyB/Xp"
    		+ "ql/Ptvae/HyR9lW5a3yJt6vuq2a+kaiW4x1WfZwu969KElI6K5Y12rhDuEJ+AfiQJFI";

    byte[] content = null;
    
    String ticketHex = "";
    
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
    	
        content = Base64.getDecoder().decode(ticketBase64);
        
        //byte[] content = TestUtils.hexStringToByteArray(ticketHex);

        // try to decode
        Decoder decoder = new Decoder(content);
        TicketLayout layout = decoder.getLayout();
        //IUicRailTicket ticket = decoder.getUicTicket();
        
        Assert.assertNotNull(layout);        
        //Assert.assertNotNull(ticket);        
        
        Assert.assertNotNull(layout.getElements());
        Assert.assertEquals(layout.getElements().size(), 32);
        String kobenhavn = "København H";
        String text = layout.getElements().get(13).getText();
        assert(kobenhavn.equals(text));
        
     }
    
 
}