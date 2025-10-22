package org.uic.barcode.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicContent.api.DynamicContentCoder;
import org.uic.barcode.dynamicContent.api.IUicDynamicContent;
import org.uic.barcode.dynamicContent.api.SimpleUicDynamicContent;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.impl.SimpleExtension;
import org.uic.barcode.ticket.api.impl.SimpleGeoCoordinate;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IGeoCoordinate;

public class DynamicContentDeCoderTest {
	
	IUicDynamicContent content = null; 
	
	DateFormat dateTimeFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
	
	@Before public void initialize() {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		content = new SimpleUicDynamicContent();		
		
		content.setAppId("appID");
	
		content.setChallengeString("challenge string");
		
		IExtension e = new SimpleExtension();
		e.setId("challenge_extension_id1");
		e.setBinarydata(UperEncoder.bytesFromHexString("82DA"));
		content.setExtension(e);
		
		IExtension e1 = new SimpleExtension();
		e1.setId("challenge_extension_id1");
		e1.setBinarydata(UperEncoder.bytesFromHexString("82DA"));
		content.addDynamicContentResponse(e1);
			
		content.setPhoneIdHash(UperEncoder.bytesFromHexString("83DA"));
		
		content.setPassIdHash(UperEncoder.bytesFromHexString("84DA"));
		
		IGeoCoordinate g = new SimpleGeoCoordinate();
		g.setLongitude( 12345L);
	    g.setLatitude(  56789L);
		content.setGeoCoordinate(g);
	
		//Date date = new Date(1612438200000L);		
		Date date = Calendar.getInstance().getTime();
		content.setTimeStamp(date);	
		
	}

	
	@Test public void testDynamicContentDecoding() {
		
		
		byte[] encodedBytes = null;
		try {
			encodedBytes = DynamicContentCoder.encode(content, null);
		} catch (EncodingFormatException e) {
			assert(false);
		}
		
		String encoding = UperEncoder.hexStringFromBytes(encodedBytes);		

	    //decode
		IUicDynamicContent testContent = DynamicContentCoder.decode(UperEncoder.bytesFromHexString(encoding));
			
		
		assert("appID".equals(testContent.getAppId()));
		
		assert("challenge string".equals(testContent.getChallengeString()));
		
		IExtension e1 = testContent.getExtension();
		assert(UperEncoder.hexStringFromBytes(e1.getBinarydata()).equals("82DA"));
		assert(e1.getId().equals("challenge_extension_id1"));
		
		
		assert(testContent.getChallengeString().equals("challenge string"));
				
		byte[] ce = null;
		for (IExtension e : testContent.getDynamicContentResponseList()) {
			if (e.getId().equals("challenge_extension_id1")) {
				ce = e.getBinarydata();
			}
		}
		assert(UperEncoder.hexStringFromBytes(ce).equals("82DA"));
		
		assert(UperEncoder.hexStringFromBytes(testContent.getPhoneIdHash()).equals("83DA"));

		assert(UperEncoder.hexStringFromBytes(testContent.getPassIdHash()).equals("84DA"));
	
		assert(testContent.getGeoCoordinate() != null);
		assert(testContent.getGeoCoordinate().getLongitude() == 12345L);
		assert(testContent.getGeoCoordinate().getLatitude() == 56789L);			
		
		String nowString = dateTimeFormat.format(content.getTimeStamp());
		String contentTimeStampString = dateTimeFormat.format(testContent.getTimeStamp());		
		
		assert(nowString.equals(contentTimeStampString));
		
	}
	
	
	
}
