package org.uic.barcode.ticket.api.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.spec.IPass;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.ticket.api.test.testtickets.PassTimeZoneTestTicketV3;
import org.uic.barcode.ticket.api.utils.Api2AsnEncoder;
import org.uic.barcode.ticket.api.utils.Api2OpenAsnEncoderV3;
import org.uic.barcode.ticket.api.utils.Asn2ApiDecoder;
import org.uic.barcode.ticket.api.utils.OpenAsn2ApiDecoderV3;


/**
 * The Class FipTimeZoneTestV2.
 * 
 * 
 * 
 */
public class PassTimeZoneTestV3 {
	
	/** The low level encoded test ticket test case 1 . */
	private byte[] encoded1 = null;
	
	/** The low level encoded test ticket test case 2 . */
	private byte[] encoded2 = null;
	
	/** The low level encoded test ticket test case 3 . */
	private byte[] encoded3 = null;
    
    /** The decoder. */
    Asn2ApiDecoder decoder = new OpenAsn2ApiDecoderV3();
    
    /** The encoder. */
    Api2AsnEncoder encoder = new Api2OpenAsnEncoderV3();
    
    /** The API ticket low level encoded for case 1. */
    IUicRailTicket iTicketDecodedFromAsn1Case1 = null;
    
    /** The API ticket low level encoded for case 2. */
    IUicRailTicket iTicketDecodedFromAsn1Case2 = null;
    
    /** The API ticket low level encoded for case 3. */
    IUicRailTicket iTicketDecodedFromAsn1Case3 = null;
    
    
    /** The ticket decoded 1. */
    IUicRailTicket iTicketDecodedCase1 = null;
    
    /** The i ticket decoded 2. */
    IUicRailTicket iTicketDecodedCase2 = null;
    
    /** The i ticket decoded 3. */
    IUicRailTicket iTicketDecodedCase3 = null;
    
    byte[] encodedInTimeZone1 = null;
    byte[] encodedInTimeZone2 = null;
    byte[] encodedInTimeZone3 = null;    
    
    String issuingDate1 = null;
    String issuingDate2 = null;
    String issuingDate3 = null;
    
    String validFrom1 = null;
    String validFrom2 = null;
    String validFrom3 = null;
    
    String validUntil1 = null;
    String validUntil2 = null;
    String validUntil3 = null;
    
    String activationDate1 = null;
    String activationDate2 = null;
    String activationDate3 = null;
    
    TimeZone defaulttimeZone = null;
    
	/**
	 * Prepare tickets.
	 */
	@Before public void prepare() {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		defaulttimeZone = TimeZone.getDefault();
		
    	UicRailTicketData ticket1 =  PassTimeZoneTestTicketV3.getUicTestTicket();
    	UicRailTicketData ticket2 =  PassTimeZoneTestTicketV3.getUicTestTicket();
    	UicRailTicketData ticket3 =  PassTimeZoneTestTicketV3.getUicTestTicket();
    	
    	//encode in UTC time zone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    	
        encoded1 = UperEncoder.encode(ticket1);
        encoded2 = UperEncoder.encode(ticket2);
        encoded3 = UperEncoder.encode(ticket3);
		
	}
	
	/**
	 * clean up
	 */
	@After public void resetTimeZone() {
		TimeZone.setDefault(defaulttimeZone);
	}
	
    
	/**
	 * Test encode test tickets in UTC and decode in CET.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws ParseException 
	 */
	@Test public void testCETCETencoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		decodeTestTicketUTC();
		
		//set the dates in the time zone CET
		TimeZone.setDefault(TimeZone.getTimeZone("CET"));
		iTicketDecodedFromAsn1Case1.getIssuerDetails().setIssuingDate(new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).parse( "2021.03.04-12:30" ));
		iTicketDecodedFromAsn1Case2.getIssuerDetails().setIssuingDate(new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).parse( "2021.03.04-00:30" ));
		iTicketDecodedFromAsn1Case3.getIssuerDetails().setIssuingDate(new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).parse( "2021.03.03-23:30" ));
			
		setValidFromTo("2021.03.14-00:00");
		setValidUntilTo("2021.03.24-23:59");
		setActivatedDayTo("2021.03.14-00:00");
		
		encode("CET");	
		decode("CET");
        decodedDateToStrings();
        
        assert(issuingDate1.equals("Thu Mar 04 13:30:00 CET 2021" ));
        assert(issuingDate2.equals("Thu Mar 04 01:30:00 CET 2021" ));
        assert(issuingDate3.equals("Thu Mar 04 00:30:00 CET 2021" ));        
       
        assert(validFrom1.equals("Sun Mar 14 00:00:00 CET 2021" ));
        assert(validFrom2.equals("Sun Mar 14 00:00:00 CET 2021" ));
        assert(validFrom3.equals("Sun Mar 14 00:00:00 CET 2021" )); 
        
        assert(validUntil1.equals("Wed Mar 24 23:59:00 CET 2021" ));
        assert(validUntil2.equals("Wed Mar 24 23:59:00 CET 2021" ));
        assert(validUntil3.equals("Wed Mar 24 23:59:00 CET 2021" )); 
        
        assert(activationDate1.equals("Sun Mar 14 00:00:00 CET 2021" ));
        assert(activationDate2.equals("Sun Mar 14 00:00:00 CET 2021" ));
        assert(activationDate3.equals("Sun Mar 14 00:00:00 CET 2021" ));
        
    }    
		

	/**
	 * Test encode test tickets in UTC and decode in CET.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws ParseException 
	 */
	@Test public void testGMTCETencoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		decodeTestTicketUTC();
		
		//set the dates in the time zone GMT
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		iTicketDecodedFromAsn1Case1.getIssuerDetails().setIssuingDate(new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).parse( "2021.03.04-12:30" ));
		iTicketDecodedFromAsn1Case2.getIssuerDetails().setIssuingDate(new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).parse( "2021.03.04-00:30" ));
		iTicketDecodedFromAsn1Case3.getIssuerDetails().setIssuingDate(new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).parse( "2021.03.03-23:30" ));
				
		setValidFromTo("2021.03.14-00:00");
		setValidUntilTo("2021.03.24-23:59");
		setActivatedDayTo("2021.03.14-00:00");
			
		encode("GMT");	
		decode("CET");
        decodedDateToStrings();
        
        assert(issuingDate1.equals("Thu Mar 04 13:30:00 CET 2021" ));
        assert(issuingDate2.equals("Thu Mar 04 01:30:00 CET 2021" ));
        assert(issuingDate3.equals("Thu Mar 04 00:30:00 CET 2021" ));        
       
        assert(validFrom1.equals("Sun Mar 14 00:00:00 CET 2021" ));
        assert(validFrom2.equals("Sun Mar 14 00:00:00 CET 2021" ));
        assert(validFrom3.equals("Sun Mar 14 00:00:00 CET 2021" )); 
        
        assert(validUntil1.equals("Wed Mar 24 23:59:00 CET 2021" ));
        assert(validUntil2.equals("Wed Mar 24 23:59:00 CET 2021" ));
        assert(validUntil3.equals("Wed Mar 24 23:59:00 CET 2021" )); 
        
        assert(activationDate1.equals("Sun Mar 14 00:00:00 CET 2021" ));
        assert(activationDate2.equals("Sun Mar 14 00:00:00 CET 2021" ));
        assert(activationDate3.equals("Sun Mar 14 00:00:00 CET 2021" ));
        
    }    
	
	/**
	 * Test encode test tickets in UTC and decode in CET.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws ParseException 
	 */
	@Test public void testCETGMTencoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		decodeTestTicketUTC();
		
		//set the dates in the time zone GMT
		TimeZone.setDefault(TimeZone.getTimeZone("CET"));
		iTicketDecodedFromAsn1Case1.getIssuerDetails().setIssuingDate(new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).parse( "2021.03.04-12:30" ));
		iTicketDecodedFromAsn1Case2.getIssuerDetails().setIssuingDate(new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).parse( "2021.03.04-00:30" ));
		iTicketDecodedFromAsn1Case3.getIssuerDetails().setIssuingDate(new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).parse( "2021.03.03-23:30" ));

		setValidFromTo("2021.03.14-00:00");
		setValidUntilTo("2021.03.24-23:59");
		setActivatedDayTo("2021.03.14-00:00");
			
		encode("CET");	
		decode("GMT");
        decodedDateToStrings();
        
        assert(issuingDate1.equals("Thu Mar 04 12:30:00 GMT 2021" ));
        assert(issuingDate2.equals("Thu Mar 04 00:30:00 GMT 2021" ));
        assert(issuingDate3.equals("Wed Mar 03 23:30:00 GMT 2021" ));        
       
        assert(validFrom1.equals("Sun Mar 14 00:00:00 GMT 2021" ));
        assert(validFrom2.equals("Sun Mar 14 00:00:00 GMT 2021" ));
        assert(validFrom3.equals("Sun Mar 14 00:00:00 GMT 2021" )); 
        
        assert(validUntil1.equals("Wed Mar 24 23:59:00 GMT 2021" ));
        assert(validUntil2.equals("Wed Mar 24 23:59:00 GMT 2021" ));
        assert(validUntil3.equals("Wed Mar 24 23:59:00 GMT 2021" )); 
        
        assert(activationDate1.equals("Sun Mar 14 00:00:00 GMT 2021" ));
        assert(activationDate2.equals("Sun Mar 14 00:00:00 GMT 2021" ));
        assert(activationDate3.equals("Sun Mar 14 00:00:00 GMT 2021" ));
        
    }    

	private void decodedDateToStrings() {

        issuingDate1 = iTicketDecodedCase1.getIssuerDetails().getIssuingDate().toString();
        issuingDate2 = iTicketDecodedCase2.getIssuerDetails().getIssuingDate().toString();
        issuingDate3 = iTicketDecodedCase3.getIssuerDetails().getIssuingDate().toString();
  
        validFrom1 = ((IPass) iTicketDecodedCase1.getDocumentData().iterator().next()).getValidFrom().toString();
        validFrom2 = ((IPass) iTicketDecodedCase2.getDocumentData().iterator().next()).getValidFrom().toString();
        validFrom3 = ((IPass) iTicketDecodedCase3.getDocumentData().iterator().next()).getValidFrom().toString();

        validUntil1 = ((IPass) iTicketDecodedCase1.getDocumentData().iterator().next()).getValidUntil().toString();
        validUntil2 = ((IPass) iTicketDecodedCase2.getDocumentData().iterator().next()).getValidUntil().toString();
        validUntil3 = ((IPass) iTicketDecodedCase3.getDocumentData().iterator().next()).getValidUntil().toString();

        activationDate1 = ((IPass) iTicketDecodedCase1.getDocumentData().iterator().next()).getActivatedDays().iterator().next().toString();
        activationDate2 = ((IPass) iTicketDecodedCase2.getDocumentData().iterator().next()).getActivatedDays().iterator().next().toString();
        activationDate3 = ((IPass) iTicketDecodedCase3.getDocumentData().iterator().next()).getActivatedDays().iterator().next().toString();

	}

	
	private void setActivatedDayTo(String dateString) throws ParseException {
	    DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
	    		Date date = dateFormat.parse(dateString);
		((IPass) iTicketDecodedFromAsn1Case1.getDocumentData().iterator().next()).getActivatedDays().clear();
		((IPass) iTicketDecodedFromAsn1Case1.getDocumentData().iterator().next()).getActivatedDays().add(date);

		((IPass) iTicketDecodedFromAsn1Case2.getDocumentData().iterator().next()).getActivatedDays().clear();
		((IPass) iTicketDecodedFromAsn1Case2.getDocumentData().iterator().next()).getActivatedDays().add(date);

		((IPass) iTicketDecodedFromAsn1Case3.getDocumentData().iterator().next()).getActivatedDays().clear();
		((IPass) iTicketDecodedFromAsn1Case3.getDocumentData().iterator().next()).getActivatedDays().add(date);
		
	}

	private void setValidFromTo(String dateString) throws ParseException {
	    DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
	    Date date = dateFormat.parse(dateString);
		((IPass) iTicketDecodedFromAsn1Case1.getDocumentData().iterator().next()).setValidFrom(date);
		((IPass) iTicketDecodedFromAsn1Case2.getDocumentData().iterator().next()).setValidFrom(date);
		((IPass) iTicketDecodedFromAsn1Case3.getDocumentData().iterator().next()).setValidFrom(date);			
	}

	private void setValidUntilTo(String dateString) throws ParseException {
	    DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
	    Date date = dateFormat.parse(dateString);
		((IPass) iTicketDecodedFromAsn1Case1.getDocumentData().iterator().next()).setValidUntil(date);
		((IPass) iTicketDecodedFromAsn1Case2.getDocumentData().iterator().next()).setValidUntil(date);
		((IPass) iTicketDecodedFromAsn1Case3.getDocumentData().iterator().next()).setValidUntil(date);
	}


	/**
	 * Encode in Time Zone
	 */
	private void encode(String timeZone) {
		TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        try {
        	encodedInTimeZone1 = encoder.encode(iTicketDecodedFromAsn1Case1);
        	encodedInTimeZone2 = encoder.encode(iTicketDecodedFromAsn1Case2);
        	encodedInTimeZone3 = encoder.encode(iTicketDecodedFromAsn1Case3);
		} catch (EncodingFormatException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Decode in Time Zone
	 */
	private void decode(String timeZone) {	
		TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
	
        try {
        	iTicketDecodedCase1 = decoder.decodeFromAsn(encodedInTimeZone1);
        	iTicketDecodedCase2 = decoder.decodeFromAsn(encodedInTimeZone2);
        	iTicketDecodedCase3 = decoder.decodeFromAsn(encodedInTimeZone3);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * Encode decode.
	 */
	private void decodeTestTicketUTC() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		try {
			iTicketDecodedFromAsn1Case1 = decoder.decodeFromAsn(encoded1);
			iTicketDecodedFromAsn1Case2 = decoder.decodeFromAsn(encoded2);
			iTicketDecodedFromAsn1Case3 = decoder.decodeFromAsn(encoded3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
