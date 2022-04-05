package org.uic.barcode.test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.dynamicContent.fdc1.TimeStamp;
import org.uic.barcode.logger.LoggerFactory;

public class TimeStampTest {
	
	
	@Before public void initialize() {
		LoggerFactory.setActivateConsoleLog(true);
	}
	
	@Test public void testDateConversion() {
		
		
		TimeStamp ts = new TimeStamp();
		
		ts.setDay(10L);
		ts.setTime(6000L);
		
		Date date = ts.getTimeAsDate();
		
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.setTime(date);
		long seconds = cal.get(Calendar.SECOND);
		seconds = seconds + cal.get(Calendar.MINUTE) * 60;
		seconds = seconds + cal.get(Calendar.HOUR_OF_DAY) * 60 * 60;
		
		assert(seconds == 6000L);
		
		
	}

	
}
