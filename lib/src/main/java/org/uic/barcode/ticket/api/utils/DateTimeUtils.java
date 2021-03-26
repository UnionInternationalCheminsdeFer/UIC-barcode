package org.uic.barcode.ticket.api.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {
	
	public static Long getDateDifference(Date issuingDate, Date localDate) {
		
		if (issuingDate == null || localDate == null) return null;
		
		Calendar startCal = Calendar.getInstance();
		startCal.clear();
		startCal.setTime(issuingDate);
		startCal.setTimeZone(TimeZone.getTimeZone("UTC"));
		startCal.set(Calendar.HOUR_OF_DAY, 0);
		startCal.set(Calendar.MINUTE, 0);
		startCal.set(Calendar.SECOND, 0);
		startCal.set(Calendar.MILLISECOND, 0);
		Date start = startCal.getTime();
			
		Calendar endCal = Calendar.getInstance();
		endCal.clear();
		endCal.setTime(localDate);
		endCal.setTimeZone(TimeZone.getTimeZone("UTC"));
		endCal.set(Calendar.HOUR_OF_DAY, 0);
		endCal.set(Calendar.MINUTE, 0);
		endCal.set(Calendar.SECOND, 0);
		endCal.set(Calendar.MILLISECOND, 0);
		Date end = endCal.getTime();
		
		long diff = TimeUnit.DAYS.convert(end.getTime() - start.getTime(), TimeUnit.MILLISECONDS );
		//long diff = localDate.getTime() - issuingDate.getTime();
	    //long dayDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);		
	    
	    return new Long(diff);
	    	
	}
	
	public static Long getDateDifferenceLocal(Date referenceDate, Date localDate) {
		
		if (referenceDate == null || localDate == null) return null;
		
		Calendar issuingCal = Calendar.getInstance();
		issuingCal.clear();
		issuingCal.setTime(referenceDate);
		issuingCal.set(Calendar.HOUR_OF_DAY, 0);
		issuingCal.set(Calendar.MINUTE, 0);
		issuingCal.set(Calendar.SECOND, 0);
		issuingCal.set(Calendar.MILLISECOND, 0);
			
		Calendar fromCal = Calendar.getInstance();
		fromCal.clear();
		fromCal.setTime(localDate);
		fromCal.set(Calendar.HOUR_OF_DAY, 0);
		fromCal.set(Calendar.MINUTE, 0);
		fromCal.set(Calendar.SECOND, 0);
		fromCal.set(Calendar.MILLISECOND, 0);
		
		long diff = localDate.getTime() - referenceDate.getTime();
	    long dayDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);		
	    
	    return new Long(dayDiff);
	    	
	}
	
	public static Date getLocalDateFromDifference(Date issuingDate, int diff , Long time ) {
		
		if (issuingDate == null) return null;
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(issuingDate);
		cal.add(Calendar.DAY_OF_YEAR, diff);
		
		if (time != null) {
			int hours = time.intValue() / 60;
			int minutes = time.intValue() - hours * 60;
			cal.set(Calendar.HOUR_OF_DAY, hours);
			cal.set(Calendar.MINUTE,minutes);
		} 
		
	    return cal.getTime();
	    	
	}
	
	
	public static void setTime(Calendar cal, Long time){
		
		if (time != null) {
			int hours = time.intValue() / 60;
			int minutes = time.intValue() - hours * 60;
			cal.set(Calendar.HOUR_OF_DAY, hours);
			cal.set(Calendar.MINUTE,minutes);
		}	
		
		
	}
	
	public static Long getTime (Calendar cal) {
		
		if (cal == null || 
		    !cal.isSet(Calendar.HOUR_OF_DAY) ||
		    !cal.isSet(Calendar.MINUTE)	) {
			return null;
		}
		
		int time =  cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
		return new Long (time );
	}
	
	public static Long getTime (Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(date);
	
		int time =  cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
		return new Long (time );
	}
	
	public static Date getDate(Date issuingDate, Long date, Long time){
		
		if (issuingDate == null) return null;
		
		if (date == null) {
			date = 0L;
		}
		
		Calendar issuingCal = Calendar.getInstance();
		issuingCal.clear();
		issuingCal.setTime(issuingDate);
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR,issuingCal.get(Calendar.YEAR) );
		cal.set(Calendar.DAY_OF_YEAR,issuingCal.get(Calendar.DAY_OF_YEAR) );
		
		cal.add(Calendar.DAY_OF_YEAR, date.intValue());
		
		if (time == null) {
			DateTimeUtils.setTime(cal,0L);
		} else {
			DateTimeUtils.setTime(cal,time);
		}
		return cal.getTime();

	}
	

	/**
	 * Gets the UTC offset.
	 *
	 * @param date and time of the issuing date
	 * @param date and time of the departure
	 * @return the UTC offset in multiples of 15 minutes
	 *         the offset needs to be added to local time to get the UTC time  (UTC = local + offset)
	 */
	public static Long getUTCoffset(Date local) {
		

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(local);
		
		/*
		 * Returns the amount of time in milliseconds to add to UTC to get standard time in this
		 * time zone. Because this value is not affected by daylight saving time, it is called raw offset. 
		 * If an underlying TimeZone implementation subclass supports historical GMT offset changes, the method 
		 * returns the raw offset value of the current date. In Honolulu, for example, its raw offset 
		 * changed from GMT-10:30 to GMT-10:00 in 1947, and this method 
		 * always returns -36000000 milliseconds (i.e., -10 hours).
		 */
		int minuteOffset = - cal.getTimeZone().getRawOffset()/ ( 1000 * 60 * 15 );
		
		return new Long (minuteOffset);
	
	}

	public static Date getUTCDate(Date issuingDate, Long date, Long time, Long UTCOffset) {
		
		if (issuingDate == null) return null;
		
		if (UTCOffset == null) return null;
		
		if (time == null) return null;
		
		if (date == null) {
			date = 0L;
		}
		
		Calendar issuingCal = Calendar.getInstance();
		issuingCal.clear();
		issuingCal.setTime(issuingDate);
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR,issuingCal.get(Calendar.YEAR) );
		cal.set(Calendar.DAY_OF_YEAR,issuingCal.get(Calendar.DAY_OF_YEAR) );
		cal.add(Calendar.DAY_OF_YEAR, date.intValue());
		
		int hours = time.intValue() / 60;
		int minutes = time.intValue() - hours * 60;
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE,minutes);
		
		cal.add(Calendar.MINUTE, (int) (UTCOffset * 15) );
		
		cal.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		return cal.getTime();		

	}
	
	
	public static Collection<Long> getActivatedDays(Date referenceDate, Collection<Date> days) {
			
		ArrayList<Long> lDays = new ArrayList<Long>();
		
		if (referenceDate == null) return lDays;
				
		for (Date day : days) {
			long dateDiff2 = DateTimeUtils.getDateDifference(referenceDate,day);
			lDays.add(dateDiff2);
		}
		
		return lDays;
		
	}

	public static Date dateToUTC(Date date){
	    return new Date(date.getTime() - Calendar.getInstance().getTimeZone().getOffset(date.getTime()));
	}

}
