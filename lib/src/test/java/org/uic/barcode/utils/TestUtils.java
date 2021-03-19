package org.uic.barcode.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {
	
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
    public static String hexStringFromBytes(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    
    public static Date parseDate (String source){
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	
    	try {
			return formatter.parse(source);
		} catch (ParseException e) {
			try {
				return formatter.parse("2001-01-01");
			} catch (ParseException e1) {
				return null;
			}
		}
    	
    }
    
}
