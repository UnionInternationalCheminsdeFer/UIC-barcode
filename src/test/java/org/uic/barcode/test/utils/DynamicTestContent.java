package org.uic.barcode.test.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.uic.barcode.dynamicContent.api.IUicDynamicContent;
import org.uic.barcode.dynamicContent.api.SimpleUicDynamicContent;
import org.uic.barcode.ticket.api.impl.SimpleGeoCoordinate;
import org.uic.barcode.ticket.api.spec.IGeoCoordinate;

public class DynamicTestContent {
	
	public static byte[] passIdHash = "PassId".getBytes();
	public static byte[] phoneIdHash = "myPhone".getBytes();
	

	public static IUicDynamicContent createDynamicTestContent() {
	
		IUicDynamicContent dc = new SimpleUicDynamicContent();
		dc.setChallengeString("CHALLENGE");
		dc.setAppId("MyApp");
		dc.setPhoneIdHash(phoneIdHash);
		dc.setPassIdHash(passIdHash);
		
		ZonedDateTime timeStamp = ZonedDateTime.now(ZoneId.of("UTC"));
		
		dc.setTimeStamp(Date.from(timeStamp.toInstant()));
	
		IGeoCoordinate geo = new SimpleGeoCoordinate();
		geo.setLatitude(123456L);
		geo.setLongitude(23456L);
		dc.setGeoCoordinate(geo);
		
		return dc;
	}

}
