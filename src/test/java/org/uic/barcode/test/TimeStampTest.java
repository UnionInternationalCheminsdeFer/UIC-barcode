package org.uic.barcode.test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
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
	
	@Test public void testDateConversion2() {
		
		
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        long day = (long) c.get(Calendar.DAY_OF_YEAR);
        long now = c.getTimeInMillis();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long passed = now - c.getTimeInMillis();
        long secondOfDay = passed / 1000;
		
		TimeStamp ts = new TimeStamp();
		assert (ts.day == day);
		assert (ts.secondOfDay >= secondOfDay - 1);
		assert (ts.secondOfDay <= secondOfDay + 1);
		
		
	}
	
	@Test public void testDateConversion3() {
		
		//implementation not available on older android versions:
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
		long day = new Long(now.get(ChronoField.DAY_OF_YEAR));
		long secondOfDay = new Long(now.get(ChronoField.SECOND_OF_DAY));		
		
		//alternative implementation
	    Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	    long day2 = (long) c.get(Calendar.DAY_OF_YEAR);
	    long now2 = c.getTimeInMillis();
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	    long passed = now2 - c.getTimeInMillis();
	    long secondOfDay2 = passed / 1000;
	    
	    
		assert (day2 == day);
		assert (secondOfDay2 >= secondOfDay - 1);
		assert (secondOfDay2 <= secondOfDay + 1);
	    
	}
	
	@Test public void testSetDateTime() {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
		Date dateNow = Date.from(now.toInstant());

		TimeStamp ts = new TimeStamp();
		ts.setDateTime(dateNow);

		assert (ts.day.intValue() == now.get(ChronoField.DAY_OF_YEAR));
		assert (ts.secondOfDay.intValue() == now.get(ChronoField.SECOND_OF_DAY));
	}

	@Test public void testGetTimeAsDate() {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC")).withNano(0); // we ignore nano seconds
		Date dateNow = Date.from(now.toInstant());

		TimeStamp ts = new TimeStamp();
		ts.setDateTime(dateNow);

		Date resDate = ts.getTimeAsDate();

		assert (resDate.getTime() == dateNow.getTime());
		ZonedDateTime res = ZonedDateTime.ofInstant(resDate.toInstant(), ZoneId.of("UTC")).withNano(0);
		assert (res.compareTo(now) == 0);

	}
	
}
