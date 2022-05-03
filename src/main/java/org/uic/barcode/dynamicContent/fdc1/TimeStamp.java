package org.uic.barcode.dynamicContent.fdc1;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
		setNow();
	}
	
	/**
	 * Sets the the time-stamp to now.
	 */
	public void setNow() {
	    Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	    day = (long) c.get(Calendar.DAY_OF_YEAR);
	    long now = c.getTimeInMillis();
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	    long passed = now - c.getTimeInMillis();
	    secondOfDay = passed / 1000;
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
		
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		int dayOfYear = now.get(Calendar.DAY_OF_YEAR);

		if (dayOfYear - day.intValue() > 250) {
			now.add(Calendar.YEAR, 1);
		}
		if (day.intValue() - dayOfYear > 250) {
			now.add(Calendar.YEAR, -1);
		}

		now.set(Calendar.DAY_OF_YEAR, 1);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.MILLISECOND, 0);
		now.set(Calendar.DAY_OF_YEAR, day.intValue());
		now.add(Calendar.SECOND, secondOfDay.intValue());

		return now.getTime();
	
	}
	
	/**
	 * Sets the date time.
	 *
	 * @param dateUTC the current date and time in  UTC
	 */
	public void setDateTime(Date dateUTC) {
		
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		c.setTime(dateUTC);

		day = (long) c.get(Calendar.DAY_OF_YEAR);

		secondOfDay = (long) c.get(Calendar.SECOND);
		secondOfDay += 60L * c.get(Calendar.MINUTE);
		secondOfDay += 3600L * c.get(Calendar.HOUR_OF_DAY);
			
		
	}

	
	
}
