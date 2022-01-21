package org.uic.barcode.test.utils;

import org.uic.barcode.dynamicFrame.api.IData;
import org.uic.barcode.dynamicFrame.api.SimpleData;

public class Level2TestDataFactory {
	
	public static IData getLevel2SimpleTestData() {
		
		String testContent = "2020.10.01T12:12.20";
		
		IData level2Data = new SimpleData();
		level2Data.setFormat("TEST");
		level2Data.setData(testContent.getBytes());
		
		return level2Data;
	}

}
