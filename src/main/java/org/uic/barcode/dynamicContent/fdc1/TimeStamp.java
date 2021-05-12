package org.uic.barcode.dynamicContent.fdc1;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.Sequence;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeStamp.
 */
@Sequence
public class TimeStamp {
	

	/*
    -- Moment of generation of the dynamic content, expressed in UTC :
    -- * dynamicContentDay is the number of days from issuing date
    --     (UicRailTicketData.issuingDetail.issuingYear and issuingDay)
	--     The range 0..1070 allows a validity equal to that of the validFrom (700) plus 
	--       validUntil (370) elements of the different transport documents of UicRailTicketData.
    -- * dynamicContentTime is the number of seconds of the day
    --     (from 0 = 0:00:00 to 86399 = 23:59:59)
    -- These two elements shall be either both present, either both absent
    dynamicContentDay   INTEGER (0..366),
     *    
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
		Instant now = Instant.now();
		day = new Long(now.get(ChronoField.DAY_OF_YEAR));
		secondOfDay = new Long(now.get(ChronoField.SECOND_OF_DAY));
	}
	
	/**
	 * Sets the the time-stamp to now.
	 */
	public void setNow() {
		Instant now = Instant.now();
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
	 * Gets the time.
	 *
	 * @return the date and time of content creation in UTC
	 */
	public Date getTimeAsDate() {
		
		Calendar cal = Calendar.getInstance();
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		
		if (dayOfYear - day.intValue() > 250) {
			cal.add(Calendar.YEAR, 1);
		}
		if (day.intValue() - dayOfYear > 250) {
			cal.add(Calendar.YEAR, -1);
		}
			
		cal.setTimeZone(TimeZone.getTimeZone("UTC"));
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.DAY_OF_YEAR, day.intValue());
		cal.add(Calendar.SECOND, secondOfDay.intValue());
		
		return cal.getTime();
	}
	
	/**
	 * Sets the date time.
	 *
	 * @param dateUTC the current date and time in  UTC
	 */
	public void setDateTime(Date dateUTC) {
			
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateUTC);
		
		day = Long.valueOf(cal.get(Calendar.DAY_OF_YEAR));
	
		secondOfDay = (long) cal.get(Calendar.SECOND);
		secondOfDay = secondOfDay + 60 * (long) cal.get(Calendar.MINUTE);
		secondOfDay = secondOfDay + 60 * 60 * (long) cal.get(Calendar.HOUR_OF_DAY);			
		
	}

}
