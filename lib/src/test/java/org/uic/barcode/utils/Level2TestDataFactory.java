package org.uic.barcode.utils;

import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.dynamicFrame.DataType;

public class Level2TestDataFactory {
	
	public static DataType getLevel2SimpleTestData() {
		
		String testContent = "2020.10.01T12:12.20";
		
		DataType level2Data = new DataType();
		level2Data.setFormat("TEST");
		level2Data.setData(new OctetString(testContent.getBytes()));
		
		return level2Data;
	}

}
