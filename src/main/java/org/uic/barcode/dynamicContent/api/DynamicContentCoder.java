package org.uic.barcode.dynamicContent.api;

import java.util.Date;
import java.util.List;

import org.uic.barcode.dynamicContent.fdc1.GeoCoordinateType;
import org.uic.barcode.dynamicContent.fdc1.SequenceOfExtension;
import org.uic.barcode.dynamicContent.fdc1.TimeStamp;
import org.uic.barcode.dynamicContent.fdc1.UicDynamicContentDataFDC1;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicContent.fdc1.ExtensionData;
import org.uic.barcode.dynamicContent.fdc1.GeoCoordinateSystemType;
import org.uic.barcode.dynamicContent.fdc1.GeoUnitType;
import org.uic.barcode.dynamicContent.fdc1.HemisphereLatitudeType;
import org.uic.barcode.dynamicContent.fdc1.HemisphereLongitudeType;
import org.uic.barcode.ticket.api.impl.SimpleExtension;
import org.uic.barcode.ticket.api.impl.SimpleGeoCoordinate;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IGeoCoordinate;
import org.uic.barcode.ticket.api.spec.IGeoCoordinateSystemType;
import org.uic.barcode.ticket.api.spec.IGeoUnitType;
import org.uic.barcode.ticket.api.spec.IHemisphereLatitudeType;
import org.uic.barcode.ticket.api.spec.IHemisphereLongitudeType;
import org.uic.barcode.ticket.api.utils.UicEncoderUtils;

public class DynamicContentCoder {
	
	public static byte[] encode(IUicDynamicContent content, String format) throws EncodingFormatException {
		
		if (format != null && !format.equals("FDC1")) {
			throw new EncodingFormatException("Format of dynamic content not supported!");
		}
				
		UicDynamicContentDataFDC1 asn = new UicDynamicContentDataFDC1();
		
		asn.setAppId(content.getAppId());
		
		if (content.getChallengeString() != null && content.getChallengeString().length() > 0) {
			asn.setChallengeString(content.getChallengeString());
		}

		asn.setDynamicContentExtension(getAsnExtension(content.getExtension()));
		
		asn.setGeoCoordinate(getAsnGeoCoordinate(content.getGeoCoordinate()));
		
		asn.setTimeStamp(getAsnTimeStamp(content.getTimeStamp()));
		
		asn.setExtensions(getAsnContentExtensions(asn, content.getDynamicContentResponseList()));
		
		return UperEncoder.encode(asn);
		
	}
	
	

	private static SequenceOfExtension getAsnContentExtensions(UicDynamicContentDataFDC1 asn, List<IExtension> dynamicContentResponseList) throws EncodingFormatException {
		if (dynamicContentResponseList != null && !dynamicContentResponseList.isEmpty()){
			
			SequenceOfExtension asnList = asn.getExtensions();
			if (asnList == null) asnList = new SequenceOfExtension();
			for (IExtension extension : dynamicContentResponseList){
				ExtensionData asnExtension = getAsnExtension(extension);
				if (asnExtension!= null) {
					asnList.add(asnExtension);
				}
			}
			if (!asnList.isEmpty()){
				return asnList;
			}
		}
	
		return null;
	}

	private static TimeStamp getAsnTimeStamp(Date date) {
		
		if (date == null) return null;
		
		TimeStamp asnTimeStamp = new TimeStamp();
		asnTimeStamp.setDateTime(date);
		
		return asnTimeStamp;
		
	}

	private static GeoCoordinateType getAsnGeoCoordinate(IGeoCoordinate point) {
			
			if (point == null) return null;
			
			GeoCoordinateType asnPoint = new GeoCoordinateType();
			
			asnPoint.setLatitude(point.getLatitude());  
			asnPoint.setLongitude(point.getLongitude());
			
			if (point.getUnit() != IGeoUnitType.milliDegree && point.getUnit() != null){
				asnPoint.setGeoUnit(GeoUnitType.valueOf(point.getUnit().name()));
			}
			
			if (point.getAccuracy() != null) {
				asnPoint.setAccuracy(GeoUnitType.valueOf(point.getAccuracy().name()));
			}
			
			if (point.getHemisphereLatitude() != IHemisphereLatitudeType.east && point.getHemisphereLatitude() != null) {
				asnPoint.setHemisphereLatitude(HemisphereLatitudeType.valueOf(point.getHemisphereLatitude().name()));
			}
			
			if (point.getHemisphereLongitude() != IHemisphereLongitudeType.north && point.getHemisphereLongitude() != null) {
				asnPoint.setHemisphereLongitude(HemisphereLongitudeType.valueOf(point.getHemisphereLongitude().name()));
			}		
			
			if (point.getSystem() != IGeoCoordinateSystemType.wgs84 && point.getSystem() != null){
				asnPoint.setCoordinateSystem(GeoCoordinateSystemType.valueOf(point.getSystem().name()));
			}


			return asnPoint;
	}

	private static ExtensionData getAsnExtension(IExtension extension) throws EncodingFormatException {
		if (extension==null) return null;
		
		if (extension.getBinarydata() == null || extension.getBinarydata().length == 0) {
			throw new EncodingFormatException("Extension does not include data");
		}

		if (extension.getId() == null || extension.getId().length() == 0) {
			throw new EncodingFormatException("Extension does not include id");
		}
		
		ExtensionData asnExtension = new ExtensionData();
		
		asnExtension.setExtensionData(extension.getBinarydata());
		asnExtension.setExtensionId(UicEncoderUtils.getIA5(extension.getId()));

		return asnExtension;
	}

	public static IUicDynamicContent decode(byte[] bytes) {	
		
		UicDynamicContentDataFDC1 asn = UperEncoder.decode(bytes, UicDynamicContentDataFDC1.class);
		
		IUicDynamicContent content = new SimpleUicDynamicContent();
		
		content.setAppId(asn.getAppId());
		
		content.setChallengeString(asn.getChallengeString());

		content.setExtension(getExtension(asn.getDynamicContentExtension()));
		
		if (asn.getExtensions() != null && !asn.getExtensions().isEmpty()) {
			for (ExtensionData e : asn.getExtensions())	{	
				content.addDynamicContentResponse(getExtension(e));
			}
		}
		
		content.setGeoCoordinate(getGeoCoordinate(asn.getGeoCoordinate()));
		
		content.setTimeStamp(asn.getTimeStamp().getTimeAsDate());
		

		return content;

	}

	private static IGeoCoordinate getGeoCoordinate(GeoCoordinateType asnCoordinate) {
		
		IGeoCoordinate g = new SimpleGeoCoordinate();
		
		g.setLatitude(asnCoordinate.getLatitude());
		g.setLongitude(asnCoordinate.getLongitude());
		
		if (asnCoordinate.getCoordinateSystem() != null) {
			g.setSystem(IGeoCoordinateSystemType.valueOf(asnCoordinate.getCoordinateSystem().name()));
		}
		
		if (asnCoordinate.getAccuracy() != null) {
			g.setAccuracy(IGeoUnitType.valueOf(asnCoordinate.getAccuracy().name()));
		}		
		
		if (asnCoordinate.getGeoUnit() != null) {
			g.setUnit(IGeoUnitType.valueOf(asnCoordinate.getGeoUnit().name()));
		}
		
		if (asnCoordinate.getHemisphereLatitude() != null) {
			g.setHemisphereLatitude(IHemisphereLatitudeType.valueOf(asnCoordinate.getHemisphereLatitude().name()));
		}

		if (asnCoordinate.getHemisphereLongitude() != null) {
			g.setHemisphereLongitude(IHemisphereLongitudeType.valueOf(asnCoordinate.getHemisphereLongitude().name()));
		}	
		
		
		return g;
	}



	private static IExtension getExtension(ExtensionData asnExtension) {
		
		if (asnExtension == null) return null;
		
		SimpleExtension e = new SimpleExtension();
		e.setBinarydata(asnExtension.getExtensionData());
		e.setId(asnExtension.getExtensionId());
		
		return e;
	}
	
	
}
