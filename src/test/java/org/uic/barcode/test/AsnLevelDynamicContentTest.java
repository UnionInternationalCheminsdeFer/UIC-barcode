package org.uic.barcode.test;

import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicContent.fdc1.ExtensionData;
import org.uic.barcode.dynamicContent.fdc1.TimeStamp;
import org.uic.barcode.dynamicContent.fdc1.UicDynamicContentDataFDC1;
import org.uic.barcode.dynamicContent.fdc1.GeoCoordinateType;

public class AsnLevelDynamicContentTest {
	
	UicDynamicContentDataFDC1 asn = null;
	
	@Before public void initialize() {
		
		
	asn = new UicDynamicContentDataFDC1();
	
	asn.setAppId("appID");
	
	asn.setChallengeString("challenge string");
	
	ExtensionData asnE1 = new ExtensionData();
	asnE1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
	asnE1.setExtensionId("challenge_extension_id1");
	asn.setDynamicContentExtension(asnE1);
	
	
	asn.getExtensions().add(asnE1);
	
	ExtensionData asnE2 = new ExtensionData();
	asnE2.setExtensionData(UperEncoder.bytesFromHexString("83DA"));
	asnE2.setExtensionId("phone");
	asn.getExtensions().add(asnE2);
	
	ExtensionData asnE3 = new ExtensionData();
	asnE3.setExtensionData(UperEncoder.bytesFromHexString("84DA"));
	asnE3.setExtensionId("pass");
	asn.getExtensions().add(asnE3);
	

	
	asn.setGeoCoordinate(new GeoCoordinateType());
	asn.getGeoCoordinate().setLongitude( 12345L);
    asn.getGeoCoordinate().setLatitude(  56789L);			
	
	TimeStamp t = new TimeStamp();
	t.setTime(100L);
	t.setDay(20L);
	asn.setTimeStamp(t);
	
		
		
	}

	@Test public void testDynamicContentEncoding() {
		
		byte[] encodedBytes = UperEncoder.encode(asn);
		
		String encoding = UperEncoder.hexStringFromBytes(encodedBytes);
		
		String expectedEncoding = "7C170F0E126204C00C800230390300DDD504017A20C6D0C2D8D8CADCCECA40E6E8E4D2DCCE2F8F461D9B32EECF96FE5F1D32EEE7A77EEBFA72310282DA05E1A37EECA0507B409C30F3E60509B42F8F461D9B32EECF96FE5F1D32EEE7A77EEBFA72310282DA";
		
		assert(expectedEncoding.equals(encoding));
		
	}
	
	@Test public void testDynamicContentDecoding() {
		
		String encoding = "7C170F0E126204C00C800230390300DDD504017A20C6D0C2D8D8CADCCECA40E6E8E4D2DCCE2F8F461D9B32EECF96FE5F1D32EEE7A77EEBFA72310282DA05E1A37EECA0507B409C30F3E60509B42F8F461D9B32EECF96FE5F1D32EEE7A77EEBFA72310282DA";
		
		UicDynamicContentDataFDC1 asn = UperEncoder.decode(UperEncoder.bytesFromHexString(encoding), UicDynamicContentDataFDC1.class);
		
		
		assert("appID".equals(asn.getAppId()));
		
		assert("challenge string".equals(asn.getChallengeString()));
		
		ExtensionData asnE1 = asn.getDynamicContentExtension();
		assert(UperEncoder.hexStringFromBytes(asnE1.getExtensionData()).equals("82DA"));
		assert(asnE1.getExtensionId().equals("challenge_extension_id1"));
		
		
		assert(asn.getExtensions().size() == 4);
		
		assert(UperEncoder.hexStringFromBytes(asn.getExtensions().get(0).getExtensionData()).equals("6368616C6C656E676520737472696E67"));
		assert(asn.getExtensions().get(0).getExtensionId().equals("="));
		
		assert(UperEncoder.hexStringFromBytes(asn.getExtensions().get(1).getExtensionData()).equals("82DA"));
		assert(asn.getExtensions().get(1).getExtensionId().equals("challenge_extension_id1"));
		
		assert(UperEncoder.hexStringFromBytes(asn.getExtensions().get(2).getExtensionData()).equals("83DA"));
		assert(asn.getExtensions().get(2).getExtensionId().equals("phone"));
		
		assert(UperEncoder.hexStringFromBytes(asn.getExtensions().get(3).getExtensionData()).equals("84DA"));
		assert(asn.getExtensions().get(3).getExtensionId().equals("pass"));
		
	
		assert(asn.getGeoCoordinate() != null);
		assert(asn.getGeoCoordinate().getLongitude() == 12345L);
		assert(asn.getGeoCoordinate().getLatitude() == 56789L);			
		
		assert(asn.getTimeStamp() != null);
		assert(asn.getTimeStamp().getDay() == 20L);
		assert(asn.getTimeStamp().getTime() == 100L);
		
	}
	
	
	
}
