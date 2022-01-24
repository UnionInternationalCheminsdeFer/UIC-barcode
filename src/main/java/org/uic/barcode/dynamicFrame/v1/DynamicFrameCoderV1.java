package org.uic.barcode.dynamicFrame.v1;

import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicFrame.v1.DynamicFrame;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.dynamicFrame.api.IData;
import org.uic.barcode.dynamicFrame.api.IDynamicFrame;
import org.uic.barcode.dynamicFrame.api.ILevel1Data;
import org.uic.barcode.dynamicFrame.api.ILevel2Data;
import org.uic.barcode.dynamicFrame.api.SimpleData;
import org.uic.barcode.dynamicFrame.api.SimpleLevel1Data;
import org.uic.barcode.dynamicFrame.api.SimpleLevel2Data;

public class DynamicFrameCoderV1 {
	
	public static void decode(IDynamicFrame frame, byte[] bytes) {
		
		DynamicFrame asnFrame = UperEncoder.decode(bytes,DynamicFrame.class);	
		
		frame.setFormat(asnFrame.getFormat());
		
		if (asnFrame.getLevel2Signature() != null) {
			frame.setLevel2Signature(asnFrame.getLevel2Signature().toByteArray());
		}
		
		if (asnFrame.getLevel2SignedData() != null) { 
			frame.setLevel2Data(new SimpleLevel2Data());
			populateApi(frame.getLevel2Data(), asnFrame.getLevel2SignedData());	
		}
					
	}

	private static void populateApi(ILevel2Data level2, Level2DataType asnLevel2) {
		
		if (asnLevel2 == null) return;
		
		
		level2.setLevel1Signature(asnLevel2.getLevel1SignatureBytes());
		
		if (asnLevel2.getLevel1Data() != null) {
		    level2.setLevel1Data(new SimpleLevel1Data());
			level2.setLevel1Data(populateApi(asnLevel2.getLevel1Data()));
		}
		
		if (asnLevel2.getLevel2Data() != null) {
			level2.setLevel2Data(new SimpleData());
			level2.setLevel2Data(populateApi(asnLevel2.getLevel2Data()));
		}		
	}

	private static IData populateApi(DataType asnData) {
		
		IData data = new SimpleData();
		data.setData(asnData.getByteData());
		data.setFormat(asnData.getFormat());
		return data;
	}

	private static ILevel1Data populateApi(Level1DataType asnLevel1) {
		
		ILevel1Data level1 = new SimpleLevel1Data();
		
		level1.setKeyId(asnLevel1.getKeyId());
		level1.setSecurityProvider(asnLevel1.getSecurityProvider());
		level1.setLevel1KeyAlg(asnLevel1.getLevel1KeyAlg());
		level1.setLevel1SigningAlg(asnLevel1.getLevel1SigningAlg());
		level1.setLevel2KeyAlg(asnLevel1.getLevel2KeyAlg());
		level1.setLevel2SigningAlg(asnLevel1.getLevel2SigningAlg());
		if (asnLevel1.getLevel2publicKey() != null) {
			level1.setLevel2publicKey(asnLevel1.getLevel2publicKey().toByteArray());
		}
		
		if (asnLevel1.getData() != null && !asnLevel1.getData().isEmpty()) {
			
			for (DataType asnData : asnLevel1.getData()) {
				IData data = new SimpleData();
				data.setData(asnData.getByteData());
				data.setFormat(asnData.getFormat());
		   	    level1.addData(data);
			}
		}
		
		return level1;
	}

	public static byte[] encode(IDynamicFrame dynamicFrame) throws EncodingFormatException {
		
		DynamicFrame asnDynamicFrame = populateAsn(dynamicFrame);
		
		return UperEncoder.encode(asnDynamicFrame);	
						
	}


	public static byte[] encode(ILevel1Data level1Data) throws EncodingFormatException {
		
		Level1DataType asn = populateAsn(level1Data);
		
		return UperEncoder.encode(asn);
	}
	
	
	public static byte[] getEncoded(String path, byte[] data) {
		
		if (path.endsWith("Level1Data")){
			return UperEncoder.extract(data, "Level1DataType" ,DynamicFrame.class );
		} else if (path.endsWith("Level2Data")){
			return UperEncoder.extract(data, "Level2DataType" ,DynamicFrame.class );
		}
	
		return null;
	}
	
	
	public static byte[] encode(ILevel2Data level2SignedData) throws EncodingFormatException {
		
       Level2DataType asn = populateAsn(level2SignedData);
		
		return UperEncoder.encode(asn);
	}

	private static DynamicFrame populateAsn(IDynamicFrame frame) throws EncodingFormatException {
		
		DynamicFrame asnFrame = new DynamicFrame();
		
		asnFrame.setFormat(frame.getFormat());
		
		if (frame.getLevel2Signature() != null && frame.getLevel2Signature().length > 0) {
			asnFrame.setLevel2Signature(new OctetString(frame.getLevel2Signature()));
		}
		
		Level2DataType asnLevel2 = populateAsn(frame.getLevel2Data());
		if (asnLevel2 != null) {
			asnFrame.setLevel2SignedData(asnLevel2);
		}

		return asnFrame;
	}


	private static Level2DataType populateAsn(ILevel2Data level2) throws EncodingFormatException {
		
		Level2DataType asnLevel2 = new Level2DataType();
		
		asnLevel2.setLevel1Signature(level2.getLevel1Signature());
		
		Level1DataType asnLevel1 = populateAsn(level2.getLevel1Data());
		
		asnLevel2.setLevel1Data(asnLevel1);
		
		if (level2.getLevel2Data() != null) {
			DataType data2 = new DataType();
			data2.setFormat(level2.getLevel2Data().getFormat());
			data2.setData(new OctetString(level2.getLevel2Data().getData()));
			asnLevel2.setLevel2Data(data2);
		}

		return asnLevel2;
	}

	private static Level1DataType populateAsn(ILevel1Data level1) throws EncodingFormatException {
		
		Level1DataType asnLevel1 = new Level1DataType();
		
		asnLevel1.setSecurityProvider(level1.getSecurityProvider());
		
		asnLevel1.setKeyId(level1.getKeyId());
		
		asnLevel1.setLevel1KeyAlg(level1.getLevel1KeyAlg());
		
		asnLevel1.setLevel1SigningAlg(level1.getLevel1SigningAlg());

		asnLevel1.setLevel2KeyAlg(level1.getLevel2KeyAlg());
		
		if (level1.getLevel2publicKey() != null && level1.getLevel2publicKey().length > 0) {
			asnLevel1.setLevel2publicKey(new OctetString(level1.getLevel2publicKey()));
		}
		
		asnLevel1.setLevel2SigningAlg(level1.getLevel2SigningAlg());
		
		if (level1.getData() != null && !level1.getData().isEmpty()) {
			
			asnLevel1.setData(new SequenceOfDataType());
			
			for (IData data : level1.getData()) {
				
				DataType asnData = new DataType();
				asnData.setByteData(data.getData());
				asnData.setFormat(data.getFormat());
				asnLevel1.getData().add(asnData);
				
			}
			
		}
		
		return asnLevel1;
	}

	
	


}
