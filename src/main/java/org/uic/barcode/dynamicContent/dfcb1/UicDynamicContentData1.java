/*
 * 
 */
package org.uic.barcode.dynamicContent.dfcb1;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicFrame.DataType;


/**
 * The Class UicDynamicContentData.
 * 
 * The dynamic content for FDC1
 * 
 */


@Sequence
@HasExtensionMarker
public class UicDynamicContentData1 {
	
    //-- Moment of generation of the dynamic content, expressed in UTC :
    //-- * dynamicContentDay is the number of days from issuing date
    //--     (UicRailTicketData.issuingDetail.issuingYear and issuingDay)
	// --     The range 0..1070 allows a validity equal to that of the validFrom (700) plus 
	// --       validUntil (370) elements of the different transport documents of UicRailTicketData.
    //-- * dynamicContentTime is the number of seconds of the day
    //--     (from 0 = 0:00:00 to 86399 = 23:59:59)
    //-- These two elements shall be either both present, either both absent
    /** The day. */
    //dynamicContentDay   INTEGER (0..1070)  DEFAULT 0,
	@FieldOrder(order = 0)
	@Asn1Default(value="0")
	@IntRange(minValue=0, maxValue=1070)
	public Long day;
	
    /** The second of day. */
    // dynamicContentTime  INTEGER (0..86399) OPTIONAL,
	@FieldOrder(order = 1)
	@IntRange(minValue=0, maxValue=86399)
	public Long secondOfDay;

    //-- Coordinates of the place where the dynamic content has been generated
    // --   (same GeoCoordinateType type as in UicRailTicketData)
    /** The geo coordinate. */
    //dynamicContentGeoCoordinate GeoCoordinateType OPTIONAL,
	@FieldOrder(order = 2)	
	@Asn1Optional public GeoCoordinateType geoCoordinate;
    //-- Response from the mobile to any data received from the terminal.
    //--   The data received from the terminal may be a random number, or any other information.
    //--   The response may be the data itself, a hashing of this data, or any other response.
	// --   This response may be completed with other information: IMEI, mobile phone number...
    //-- The type used is ExtensionData, as it is fully adapted. 
	// -- extensionId shall be set to:
	// -- * "=" if the data included in extensionData is exactly the one that was transmitted by the terminal,
	// -- * any other value (chosen by the issuer) in other cases.
    /** The challenge response. */
    //dynamicContentResponseToChallenge ExtensionData OPTIONAL,
	@FieldOrder(order = 3)	
	@Asn1Optional public ExtensionData challengeResponse;
 
    /** The extension. */
    //-- proprietary data defined bilaterally
	@FieldOrder(order = 4)	
	@Asn1Optional public ExtensionData extension;
    //dynamicContentExtension ExtensionData OPTIONAL,
	
    // challenge string asked by the TCO
	@FieldOrder(order = 5)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String challengeString;
	
	// hashed phone id 
	@FieldOrder(order = 6)
	public OctetString phoneIdHash;
	
	// hashed e-passport-id
	@FieldOrder(order = 7)
	public OctetString passHash;
		
    //...

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
	 * Gets the geo coordinate.
	 *
	 * @return the geo coordinate
	 */
	public GeoCoordinateType getGeoCoordinate() {
		return geoCoordinate;
	}

	/**
	 * Sets the geo coordinate.
	 *
	 * @param geoCoordinate the new geo coordinate
	 */
	public void setGeoCoordinate(GeoCoordinateType geoCoordinate) {
		this.geoCoordinate = geoCoordinate;
	}

	/**
	 * Gets the challenge response.
	 *
	 * @return the challenge response
	 */
	public ExtensionData getChallengeResponse() {
		return challengeResponse;
	}

	/**
	 * Sets the challenge response.
	 *
	 * @param challengeResponse the new challenge response
	 */
	public void setChallengeResponse(ExtensionData challengeResponse) {
		this.challengeResponse = challengeResponse;
	}

	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public ExtensionData getExtension() {
		return extension;
	}

	/**
	 * Sets the extension.
	 *
	 * @param extension the new extension
	 */
	public void setExtension(ExtensionData extension) {
		this.extension = extension;
	}
	
	/**
	 * Gets the time.
	 *
	 * @param issuingDate the issuing date in UTC
	 * @return the date and time of content creation in UTC
	 */
	public Date getTime(Date issuingDate) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(issuingDate);
		cal.setTimeZone(TimeZone.getTimeZone("UTC"));
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.add(Calendar.DATE, day.intValue());
		cal.add(Calendar.SECOND, secondOfDay.intValue());
		
		return cal.getTime();
	}
	
	/**
	 * Sets the date time.
	 *
	 * @param dateUTC the current date and time in  UTC
	 * @param issuingDateUTC the issuing date and time in UTC
	 */
	public void setDateTime(Date dateUTC, Date issuingDateUTC) {
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(issuingDateUTC);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(dateUTC);
		
		int days1 = cal1.get(Calendar.DATE);
		int days2 = cal2.get(Calendar.DATE);
		
		day = new Long (days2 - days1);
		
		secondOfDay = (long) cal2.get(Calendar.SECOND);
		secondOfDay = secondOfDay + 60 * (long) cal2.get(Calendar.MINUTE);
		secondOfDay = secondOfDay + 60 * 60 * (long) cal2.get(Calendar.HOUR_OF_DAY);			
		
	}
	
	public static String getFormat() {
		return "FDC1";
	}
	
	public DataType getDataType() {
		DataType data = new DataType();
		data.setFormat(UicDynamicContentData1.getFormat());
		data.setByteData(UperEncoder.encode(this));
		return data;
	}

	public String getChallengeString() {
		return challengeString;
	}

	public void setChallengeString(String challengeString) {
		this.challengeString = challengeString;
	}

	public OctetString getPhoneIdHash() {
		return phoneIdHash;
	}

	public void setPhoneIdHash(OctetString phoneIdHash) {
		this.phoneIdHash = phoneIdHash;
	}

	public OctetString getPassHash() {
		return passHash;
	}

	public void setPassHash(OctetString passHash) {
		this.passHash = passHash;
	}

	

}
