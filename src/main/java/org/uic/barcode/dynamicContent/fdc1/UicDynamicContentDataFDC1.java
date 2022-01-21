/*
 * 
 */
package org.uic.barcode.dynamicContent.fdc1;

import java.io.UnsupportedEncodingException;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicFrame.api.IData;
import org.uic.barcode.dynamicFrame.api.SimpleData;
import org.uic.barcode.dynamicFrame.v1.DataType;


/**
 * The Class UicDynamicContentData.
 * 
 * The dynamic content for FDC1
 * 
 */




@Sequence
@HasExtensionMarker
public class UicDynamicContentDataFDC1  {
	
	

    /*
    -- The possible values are defined by the security provider
    --   (the security provider being UicBarcodeHeader.level2SignedData.level1Data.securityProviderNum/IA5)
    dynamicContentMobileAppId IA5String OPTIONAL,
    */
	//timestamp when the bar code was created
	@FieldOrder(order = 0)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String appId;	

	//timestamp when the bar code was created
	@FieldOrder(order = 1)	
	@Asn1Optional public TimeStamp timeStamp;
	

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
	@FieldOrder(order = 3)
	@Asn1Optional public SequenceOfExtension dynamicContentResponseToChallenge;


	@FieldOrder(order = 4)
	@Asn1Optional public ExtensionData dynamicContentExtension;
	
    //...



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
	
	public static String getFormat() {
		return "FDC1";
	}
	
	public IData getApiDataType() {
		IData data = new SimpleData();
		data.setFormat(getFormat());
		data.setData(UperEncoder.encode(this));
		return data;
	}
	
	
	public DataType getDataType() {
		DataType data = new DataType();
		data.setFormat(getFormat());
		data.setByteData(UperEncoder.encode(this));
		return data;
	}

	public String getChallengeString() {
		if (this.dynamicContentResponseToChallenge != null) {
			for (ExtensionData ed  : dynamicContentResponseToChallenge) {
				if (ed.getExtensionId().equals("=")) {
					byte[] c = ed.getExtensionData();
					try {
						return new String(c,"UTF-8");
					} catch (UnsupportedEncodingException e) {
						return null;
					}
				}
			}
		}
		return null;
	}

	public void setChallengeString(String challengeString) {
		if (dynamicContentResponseToChallenge == null) {
			dynamicContentResponseToChallenge = new SequenceOfExtension();
		};
		ExtensionData ed = new ExtensionData();
		ed.setExtensionId("=");
		try {
			ed.setExtensionData(challengeString.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return;
		}
		dynamicContentResponseToChallenge.add(ed);
	}

	public byte[] getPhoneIdHash() {
		if (this.dynamicContentResponseToChallenge != null) {
			for (ExtensionData ed  : dynamicContentResponseToChallenge) {
				if (ed.getExtensionId().equals("phone")) {
					return ed.getExtensionData();
				}
			}
		}
		return null;
	}

	public void setPhoneIdHash(byte[] phoneIdHash) {
		if (dynamicContentResponseToChallenge == null) {
			dynamicContentResponseToChallenge = new SequenceOfExtension();
		};
		ExtensionData ed = new ExtensionData();
		ed.setExtensionId("phone");
		ed.setExtensionData(phoneIdHash);
		dynamicContentResponseToChallenge.add(ed);
	}

	public byte[] getPassIdHash() {
		if (this.dynamicContentResponseToChallenge != null) {
			for (ExtensionData ed  : dynamicContentResponseToChallenge) {
				if (ed.getExtensionId().equals("pass")) {
					return ed.getExtensionData();
				}
			}
		}
		return null;
	}

	public void setPassIdHash(byte[] passIdHash) {
		if (dynamicContentResponseToChallenge == null) {
			dynamicContentResponseToChallenge = new SequenceOfExtension();
		};
		ExtensionData ed = new ExtensionData();
		ed.setExtensionId("pass");
		ed.setExtensionData(passIdHash);
		dynamicContentResponseToChallenge.add(ed);
	}

	public TimeStamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(TimeStamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	
	
	public SequenceOfExtension getExtensions() {
		return dynamicContentResponseToChallenge;
	}

	public void setExtensions(SequenceOfExtension extensions) {
		this.dynamicContentResponseToChallenge = extensions;
	}
	
	public byte[] encode() {
		return UperEncoder.encode(this);
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public ExtensionData getDynamicContentExtension() {
		return dynamicContentExtension;
	}

	public void setDynamicContentExtension(ExtensionData dynamicContentExtension) {
		this.dynamicContentExtension = dynamicContentExtension;
	}

	
	
}
