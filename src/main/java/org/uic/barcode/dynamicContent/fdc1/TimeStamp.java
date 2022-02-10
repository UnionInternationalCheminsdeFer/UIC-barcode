package org.uic.barcode.dynamicContent.fdc1;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Date;

import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.Sequence;


/**
 * The Class TimeStamp.
 */
@Sequence
public class TimeStamp {
	

	/*
    -- Moment of generation of the dynamic content, expressed in UTC :
    -- * dynamicContentDay is the number of day in the year
    -- * dynamicContentTime is the number of seconds of the day
    --     (from 0 = 0:00:00 to 86399 = 23:59:59)
    */
	@FieldOrder(order = 0)
	@IntRange(minValue=1, maxValue=366)
	public Long day;
	
    /** The second of day. */
    // dynamicContentTime  INTEGER (0..86399) OPTIONAL,
	@FieldOrder(order = 1)
	@IntRange(minValue=0, maxValue=86399)
	public Long secondOfDay;
	
	
	
	/**
	 * Instantiates a new time stamp and sets the time-stamp to now.
	 */
	public TimeStamp() {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
		day = new Long(now.get(ChronoField.DAY_OF_YEAR));
		secondOfDay = new Long(now.get(ChronoField.SECOND_OF_DAY));
	}
	
	/**
	 * Sets the the time-stamp to now.
	 */
	public void setNow() {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
		day = new Long(now.get(ChronoField.DAY_OF_YEAR));
		secondOfDay = new Long(now.get(ChronoField.SECOND_OF_DAY));		
	}
	
	/**
     * Gets the day.
     *
     * @return the day
     */
    public Long getDay() {
		return day;
	}

	/**
	 * Sets the day.
	 *
	 * @param day the new day
	 */
	public void setDay(Long day) {
		this.day = day;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public Long getTime() {
		return secondOfDay;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(Long time) {
		this.secondOfDay = time;
	}
	
	
	
	
	/**
	 * Gets the current date and time in UTC
	 *
	 * @return the date and time of content creation in UTC
	 */
	public Date getTimeAsDate() {
			
		ZonedDateTime now = Instant.now().atZone(ZoneOffset.UTC);
		int dayOfYear = now.getDayOfYear();
				
		if (dayOfYear - day.intValue() > 250) {
			now = now.plusYears(1);
		}
		if (day.intValue() - dayOfYear > 250) {
			now = now.minusYears(1);
		}

		now = now.withDayOfYear(1);
		now = now.withSecond(0);
		now = now.withHour(0);
		now = now.withMinute(0);
		now = now.withNano(0);
		now = now.withDayOfYear(day.intValue());
		now = now.plusSeconds(secondOfDay);
		
		return Date.from(now.toInstant());
	
	}
	
	/**
	 * Sets the date time.
	 *
	 * @param dateUTC the current date and time in  UTC
	 */
	public void setDateTime(Date dateUTC) {
		
		ZonedDateTime date = dateUTC.toInstant().atZone(ZoneOffset.UTC);
		
		day = (long) date.getDayOfYear();
	
		secondOfDay = (long) date.getSecond();
		secondOfDay = secondOfDay + 60 * (long) date.getMinute();
		secondOfDay = secondOfDay + 60 * 60 * (long) date.getHour();			
		
	}

	
	
}
