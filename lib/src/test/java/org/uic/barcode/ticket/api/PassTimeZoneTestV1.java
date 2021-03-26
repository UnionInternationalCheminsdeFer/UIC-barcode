package org.uic.barcode.ticket.api;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData;
import org.uic.barcode.ticket.api.spec.IPass;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.ticket.api.testtickets.PassTimeZoneTestTicketCase1V1;
import org.uic.barcode.ticket.api.testtickets.PassTimeZoneTestTicketCase2V1;
import org.uic.barcode.ticket.api.testtickets.PassTimeZoneTestTicketCase3V1;
import org.uic.barcode.ticket.api.utils.Api2OpenAsnEncoder;
import org.uic.barcode.ticket.api.utils.OpenAsn2ApiDecoder;


/**
 * The Class PassTimeZoneTestV1.
 * 
 * 
 * test validity dates and activated days in different time zones
 * 
   Case 1:
				issue date: 04-03-2021 12:30 UTC
 				issuingYear: 2021
  				issuingDay: 63
  				issuingTime: 750
  				validFromDay: 10
  				activatedDays: [0]
  				utcOffset: 0

				expected:   activated for 04-03-2021 + 10 + 0 00:00 UTC --> 14-03-2021

   Case 2:
				issue date: 04-03-2021 00:30 UTC
  				issuingYear: 2021
  				issuingDay: 63
  				issuingTime: 30
  				validFromDay: 10
  				activatedDays: [0]
  				utcOffset: 0
  				
  				expected:   activated for 04-03-2021 + 10 + 0 00:00 UTC --> 14-03-2021
  				 
   Case 3:
				issue date: 03-03-2021 23:30 UTC
  				issuingYear: 2021
  				issuingDay: 62
  				issuingTime: 1410
  				validFromDay: 11
  				activatedDays: [0]
  				
  				expected: activated for 03-03-2021 + 11 + 0 00:00 UTC --> 14-03-2021
 * 
 * - Test tickets defined on the level of the asn.1 object model
 * - set the local time zone
 * - decoded to get an API ticket object
 * - encode again to  
 * 
 * 
 * 
 * 
 */
public class PassTimeZoneTestV1 {
	
	/** The low level encoded test ticket test case 1 . */
	private byte[] encoded1 = null;
	
	/** The low level encoded test ticket test case 2 . */
	private byte[] encoded2 = null;
	
	/** The low level encoded test ticket test case 3 . */
	private byte[] encoded3 = null;
    
    /** The decoder. */
    OpenAsn2ApiDecoder decoder = new OpenAsn2ApiDecoder();
    
    /** The encoder. */
    Api2OpenAsnEncoder encoder = new Api2OpenAsnEncoder();
    
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
		
		defaulttimeZone = TimeZone.getDefault();
		
    	UicRailTicketData ticket1 =  PassTimeZoneTestTicketCase1V1.getUicTestTicket();
    	UicRailTicketData ticket2 =  PassTimeZoneTestTicketCase2V1.getUicTestTicket();
    	UicRailTicketData ticket3 =  PassTimeZoneTestTicketCase3V1.getUicTestTicket();
    	
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
	 * Test decode test tickets in CET.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test public void testCETdecodeOnly() throws IllegalArgumentException, IllegalAccessException {
		
		encodedInTimeZone1 = encoded1;
		encodedInTimeZone2 = encoded2;
		encodedInTimeZone3 = encoded3;

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
	 * Test  decode test tickets in GMT.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test public void testGMTdecodeOnly() throws IllegalArgumentException, IllegalAccessException {
		
		encodedInTimeZone1 = encoded1;
		encodedInTimeZone2 = encoded2;
		encodedInTimeZone3 = encoded3;

        //test decoded ticket
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
		} catch (IOException e) {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
