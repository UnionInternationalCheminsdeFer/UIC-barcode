package org.uic.barcode.ticket.api;

import java.util.Calendar;
import java.util.TimeZone;
import org.junit.Test;
import org.uic.barcode.ticket.api.utils.DateTimeUtils;


public class DateTimeUtilsTest{



    @Test public void testDateDiff1() throws IllegalArgumentException, IllegalAccessException {
    	
    	Calendar issuing = Calendar.getInstance();
    	issuing.setTimeZone(TimeZone.getTimeZone("UTC"));
    	issuing.set(Calendar.YEAR,2017);
    	issuing.set(Calendar.DAY_OF_MONTH, 31);    	
    	issuing.set(Calendar.MONTH, 12);
    	issuing.set(Calendar.HOUR_OF_DAY, 23);
    	issuing.set(Calendar.MINUTE, 5);
    	
    	Calendar local = Calendar.getInstance();
    	local.set(Calendar.YEAR,2018);
    	local.set(Calendar.DAY_OF_MONTH, 1);    	
    	local.set(Calendar.MONTH, 1);
    	issuing.set(Calendar.HOUR_OF_DAY, 0);
    	issuing.set(Calendar.MINUTE, 5);
    	
    	Long diff = DateTimeUtils.getDateDifference(issuing.getTime(), local.getTime());
    	
    
        assert(diff.intValue() == 1);
    }
    




    
    
    
    

}
