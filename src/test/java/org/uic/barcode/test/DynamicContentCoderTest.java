package org.uic.barcode.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

public class DynamicContentCoderTest {
	
	IUicDynamicContent content = null; 
	
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

		try {
			content.setTimeStamp(new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).parse( "2021.03.04-12:30" ));
		} catch (ParseException e2) {
			// 
		}			
		
	}

	@Test public void testDynamicContentEncoding() {
		
		
		byte[] encodedBytes = null;
		try {
			encodedBytes = DynamicContentCoder.encode(content, null);
		} catch (EncodingFormatException e) {
			assert(false);
		}
		
		String encoding = UperEncoder.hexStringFromBytes(encodedBytes);
		
		String expectedEncoding = "7C170F0E12620F9437000230390300DDD504017A20C6D0C2D8D8CADCCECA40E6E8E4D2DCCE2F8F461D9B32EECF96FE5F1D32EEE7A77EEBFA72310282DA05E1A37EECA0507B409C30F3E60509B42F8F461D9B32EECF96FE5F1D32EEE7A77EEBFA72310282DA";
		
		assert(expectedEncoding.equals(encoding));
		
	}
	
	@Test public void testDynamicContentDecoding() {
		
		String encoding = "7C170F0E12620F9437000230390300DDD504017A20C6D0C2D8D8CADCCECA40E6E8E4D2DCCE2F8F461D9B32EECF96FE5F1D32EEE7A77EEBFA72310282DA05E1A37EECA0507B409C30F3E60509B42F8F461D9B32EECF96FE5F1D32EEE7A77EEBFA72310282DA";
		
		IUicDynamicContent content = DynamicContentCoder.decode(UperEncoder.bytesFromHexString(encoding));
		
		try {
			content.setTimeStamp(new SimpleDateFormat( "yyyy.MM.dd-HH:mm" ).parse( "2021.03.04-12:30" ));
		} catch (ParseException e2) {
			// 
		}			
		
		
		
		assert("appID".equals(content.getAppId()));
		
		assert("challenge string".equals(content.getChallengeString()));
		
		IExtension e1 = content.getExtension();
		assert(UperEncoder.hexStringFromBytes(e1.getBinarydata()).equals("82DA"));
		assert(e1.getId().equals("challenge_extension_id1"));
		
		
		assert(content.getChallengeString().equals("challenge string"));
				
		byte[] ce = null;
		for (IExtension e : content.getDynamicContentResponseList()) {
			if (e.getId().equals("challenge_extension_id1")) {
				ce = e.getBinarydata();
			}
		}
		assert(UperEncoder.hexStringFromBytes(ce).equals("82DA"));
		
		assert(UperEncoder.hexStringFromBytes(content.getPhoneIdHash()).equals("83DA"));

		assert(UperEncoder.hexStringFromBytes(content.getPassIdHash()).equals("84DA"));
	
		assert(content.getGeoCoordinate() != null);
		assert(content.getGeoCoordinate().getLongitude() == 12345L);
		assert(content.getGeoCoordinate().getLatitude() == 56789L);			
		
		assert(content.getTimeStamp() != null);
		
		assert(content.getTimeStamp().toString().contains("04 12:30:00 CET"));
		
		
	}
	
	
	
}
