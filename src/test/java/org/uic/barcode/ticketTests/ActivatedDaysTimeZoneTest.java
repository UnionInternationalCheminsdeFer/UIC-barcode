package org.uic.barcode.ticketTests;

import org.junit.Test;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.uic.barcode.Decoder;
import org.uic.barcode.Encoder;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.impl.SimpleOpenTicket;
import org.uic.barcode.ticket.api.impl.SimpleIssuingDetail;
import org.uic.barcode.ticket.api.impl.SimpleUicRailTicket;
import org.uic.barcode.ticket.api.spec.IOpenTicket;
import org.uic.barcode.ticket.api.spec.IIssuingDetail;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;


public class ActivatedDaysTimeZoneTest {
 
	//issuing date is in UTC as defined in the barcode spec
    final String issuingDateValue = "2017-07-08 10:15";
    //valid from is in the local time zone where the ticket starts
    final String validFromDateTimeValue = "2017-12-01 00:05";
    //activated day is in any local time zone where the ticket is used    
    final String activatedDayValue1 = "2017-12-01";
    //activated day is in any local time zone where the ticket is used      
    final String activatedDayValue2 = "2018-01-01";
    
    //activated day with time to test that no time is transferred
    final String activatedDayValueWithTime1 = "2017-12-01 00:00";
    //activated day with time to test that no time is transferred    
    final String activatedDayValueWithTime2 = "2018-01-01 00:00";    
    
    final SimpleDateFormat utcDateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);        

  
    @Before
    public void prepare() throws Exception {
        utcDateTimeFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));      
    }

    @Test
    public void testEncodeUTC() throws Exception {
    	   	
    	//keep the time zone to reset it after the test
    	TimeZone originalTimeZone = TimeZone.getDefault();
    	//encoding runs in UTC
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);       
        dateFormatter.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);        
        dateTimeFormatter.setTimeZone(TimeZone.getDefault());
  
        //issuingDateValue is assumed to be already on UTC, so formatter runs on default time zone to avoid additional shifts
        Date issuingDate = dateTimeFormatter.parse(issuingDateValue);        
        Date validFromDate = dateTimeFormatter.parse(validFromDateTimeValue); 
        Date activationDay1 = dateFormatter.parse(activatedDayValue1);
        Date activationDay2 = dateFormatter.parse(activatedDayValue2);       
        
        //check the formatter works
        assert(issuingDateValue.equals(dateTimeFormatter.format(issuingDate)));
        assert(validFromDateTimeValue.equals(dateTimeFormatter.format(validFromDate)));
        assert(activatedDayValue1.equals(dateFormatter.format(activationDay1)));
        assert(activatedDayValue2.equals(dateFormatter.format(activationDay2)));        
        
        	
    	final IIssuingDetail issuingDetail = new SimpleIssuingDetail();
        final IOpenTicket openTicket = new SimpleOpenTicket();
        final IUicRailTicket ticket = new SimpleUicRailTicket();

        issuingDetail.setIssuingDate(issuingDate);
        openTicket.setValidFrom(validFromDate);
        openTicket.addActivatedDay(activationDay1);
        openTicket.addActivatedDay(activationDay2);
        ticket.setIssuerDetails(issuingDetail);
        ticket.addOpenTicket(openTicket);
        
        final byte[] encodedTicket = getEncoded(ticket);      
        
        
        // ==== decode WITH a Europe/Paris timezone (UTC+1 in winter) ====
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/Paris")));

        Decoder decoder = new Decoder(encodedTicket);
        IUicRailTicket decodedTicket = decoder.getStaticFrame().getuFlex().getTicket();
        IOpenTicket decodedOpenTicket = (IOpenTicket) decodedTicket.getDocumentData().iterator().next();

        issuingDate = decodedTicket.getIssuerDetails().getIssuingDate(); 
        validFromDate = decodedOpenTicket.getValidFrom(); 
        activationDay1 = (Date) decodedOpenTicket.getActivatedDays().toArray()[0];
        activationDay2 = (Date) decodedOpenTicket.getActivatedDays().toArray()[1];
        
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);       
        dateFormatter.setTimeZone(TimeZone.getDefault());
        dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);        
        dateTimeFormatter.setTimeZone(TimeZone.getDefault());
        
    	//issuing date is returned in UTC, so formatter needs to be on UTC to compare properly  
        assert(issuingDateValue.equals(utcDateTimeFormatter.format(issuingDate)));
        assert(validFromDateTimeValue.equals(dateTimeFormatter.format(validFromDate)));
        assert(activatedDayValue1.equals(dateFormatter.format(activationDay1)));
        assert(activatedDayValue2.equals(dateFormatter.format(activationDay2)));          
        //as only the date is provided the time should be zero
        assert(activatedDayValueWithTime1.equals(dateTimeFormatter.format(activationDay1)));
        assert(activatedDayValueWithTime2.equals(dateTimeFormatter.format(activationDay2)));
        

        // ==== decoding WITH a UTC timezone ====
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")));

        decoder = new Decoder(encodedTicket);
        decodedTicket = decoder.getStaticFrame().getuFlex().getTicket();
        decodedOpenTicket = (IOpenTicket) decodedTicket.getDocumentData().iterator().next();

        
        issuingDate = decodedTicket.getIssuerDetails().getIssuingDate(); 
        validFromDate = decodedOpenTicket.getValidFrom(); 
        activationDay1 = (Date) decodedOpenTicket.getActivatedDays().toArray()[0];
        activationDay2 = (Date) decodedOpenTicket.getActivatedDays().toArray()[1];
        
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);       
        dateFormatter.setTimeZone(TimeZone.getDefault());
        dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);        
        dateTimeFormatter.setTimeZone(TimeZone.getDefault());
        
    	//issuing date is returned in UTC, so formatter needs to be on UTC to compare properly  
        assert(issuingDateValue.equals(utcDateTimeFormatter.format(issuingDate)));
        assert(validFromDateTimeValue.equals(dateTimeFormatter.format(validFromDate)));
        assert(activatedDayValue1.equals(dateFormatter.format(activationDay1)));
        assert(activatedDayValue2.equals(dateFormatter.format(activationDay2)));          
        
        //as only the date is provided the time should be zero
        assert(activatedDayValueWithTime1.equals(dateTimeFormatter.format(activationDay1)));
        assert(activatedDayValueWithTime2.equals(dateTimeFormatter.format(activationDay2)));

        
        TimeZone.setDefault(originalTimeZone);
    }
    
    @Test
    public void testEncodeParis() throws Exception {
    	
    	//keep the time zone to reset it after the test
    	TimeZone originalTimeZone = TimeZone.getDefault();
    	//encoding runs in CEST
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/Paris")));
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);       
        dateFormatter.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);        
        dateTimeFormatter.setTimeZone(TimeZone.getDefault());
       
        //issuingDateValue is assumed to be already on UTC, so formatter runs on default time zone to avoid additional shifts
        Date issuingDate = dateTimeFormatter.parse(issuingDateValue);        
        Date validFromDate = dateTimeFormatter.parse(validFromDateTimeValue); 
        Date activationDay1 = dateFormatter.parse(activatedDayValue1);
        Date activationDay2 = dateFormatter.parse(activatedDayValue2);       
        
        //issuing date is returned in UTC, so formatter needs to be on UTC to compare properly  
        assert(issuingDateValue.equals(dateTimeFormatter.format(issuingDate)));
        assert(validFromDateTimeValue.equals(dateTimeFormatter.format(validFromDate)));
        assert(activatedDayValue1.equals(dateFormatter.format(activationDay1)));
        assert(activatedDayValue2.equals(dateFormatter.format(activationDay2)));              
        
        	
    	final IIssuingDetail issuingDetail = new SimpleIssuingDetail();
        final IOpenTicket openTicket = new SimpleOpenTicket();
        final IUicRailTicket ticket = new SimpleUicRailTicket();

        issuingDetail.setIssuingDate(issuingDate);
        openTicket.setValidFrom(validFromDate);
        openTicket.addActivatedDay(activationDay1);
        openTicket.addActivatedDay(activationDay2);
        ticket.setIssuerDetails(issuingDetail);
        ticket.addOpenTicket(openTicket);
        
        final byte[] encodedTicket = getEncoded(ticket);      
        
        
        // ==== decode WITH a Europe/Paris timezone (UTC+1 in winter) ====
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/Paris")));

        Decoder decoder = new Decoder(encodedTicket);
        IUicRailTicket decodedTicket = decoder.getStaticFrame().getuFlex().getTicket();
        IOpenTicket decodedOpenTicket = (IOpenTicket) decodedTicket.getDocumentData().iterator().next();

        issuingDate = decodedTicket.getIssuerDetails().getIssuingDate(); 
        validFromDate = decodedOpenTicket.getValidFrom(); 
        activationDay1 = (Date) decodedOpenTicket.getActivatedDays().toArray()[0];
        activationDay2 = (Date) decodedOpenTicket.getActivatedDays().toArray()[1];
        
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);       
        dateFormatter.setTimeZone(TimeZone.getDefault());
        dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);        
        dateTimeFormatter.setTimeZone(TimeZone.getDefault());      
          
    	//issuing date is returned in UTC, so formatter needs to be on UTC to compare properly    
        assert(issuingDateValue.equals(utcDateTimeFormatter.format(issuingDate)));
        assert(validFromDateTimeValue.equals(dateTimeFormatter.format(validFromDate)));
        assert(activatedDayValue1.equals(dateFormatter.format(activationDay1)));
        assert(activatedDayValue2.equals(dateFormatter.format(activationDay2)));          
        //as only the date is provided the time should be zero
        assert(activatedDayValueWithTime1.equals(dateTimeFormatter.format(activationDay1)));
        assert(activatedDayValueWithTime2.equals(dateTimeFormatter.format(activationDay2)));
        

        // ==== decoding WITH a UTC timezone ====
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")));

        decoder = new Decoder(encodedTicket);
        decodedTicket = decoder.getStaticFrame().getuFlex().getTicket();
        decodedOpenTicket = (IOpenTicket) decodedTicket.getDocumentData().iterator().next();

        
        issuingDate = decodedTicket.getIssuerDetails().getIssuingDate(); 
        validFromDate = decodedOpenTicket.getValidFrom(); 
        activationDay1 = (Date) decodedOpenTicket.getActivatedDays().toArray()[0];
        activationDay2 = (Date) decodedOpenTicket.getActivatedDays().toArray()[1];
        
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);       
        dateFormatter.setTimeZone(TimeZone.getDefault());
        dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);        
        dateTimeFormatter.setTimeZone(TimeZone.getDefault());
        
    	//issuing date is returned in UTC, so formatter needs to be on UTC to compare properly        
        assert(issuingDateValue.equals(utcDateTimeFormatter.format(issuingDate)));
        assert(validFromDateTimeValue.equals(dateTimeFormatter.format(validFromDate)));
        assert(activatedDayValue1.equals(dateFormatter.format(activationDay1)));
        assert(activatedDayValue2.equals(dateFormatter.format(activationDay2)));          
        //as only the date is provided the time should be zero
        assert(activatedDayValueWithTime1.equals(dateTimeFormatter.format(activationDay1)));
        assert(activatedDayValueWithTime2.equals(dateTimeFormatter.format(activationDay2)));

        
        
        TimeZone.setDefault(originalTimeZone);

    }    
    
  
	private static byte[] getEncoded(final IUicRailTicket ticket)
            throws IOException, EncodingFormatException, NoSuchAlgorithmException, Exception {
        Encoder encoder = new Encoder(ticket, null, Encoder.UIC_BARCODE_TYPE_CLASSIC, 1, 2);

        final String shaPlusDsaOid = "1.2.840.10040.4.3";
        Security.addProvider(new BouncyCastleProvider());

        Provider provider = Security.getProvider("BC");

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", provider);
        keyGen.initialize(1024, new SecureRandom());

        final KeyPair pair = keyGen.generateKeyPair();
        final PrivateKey privateKey = pair.getPrivate();

        Security.addProvider(new BouncyCastleProvider());

        final String ricsCode = "8888";
        encoder.signLevel1(ricsCode, privateKey, shaPlusDsaOid, "1", provider);
        return encoder.encode();
    }



}
