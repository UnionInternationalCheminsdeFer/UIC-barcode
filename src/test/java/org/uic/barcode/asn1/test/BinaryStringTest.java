package org.uic.barcode.asn1.test;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.AsnUtils;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.EncodingFormatException;

public class BinaryStringTest {
	
	@Before public void prepare() {	
		LoggerFactory.setActivateConsoleLog(true);
	}

	
	@Test public void testBinaryString() throws IOException, EncodingFormatException{
		
		String bs1 = "01000000";
		String ms1 = "1000000001000000001000000001000000001000000001000000001000000001";
		String ms2 = "10000000010000000010000000010000";

		
		//String bs1 = "1011111100001000011011100000000000000001000000010000010010000000";
		
		byte[] bytes = AsnUtils.fromBooleanString(bs1);
		
		String bs2 = AsnUtils.toBooleanString(bytes);
		
		
		
		byte[] mask = new byte[] {
	    	     (byte) 0b1000_0000,
	    	            0b0100_0000,
	    	            0b0010_0000,
	    	            0b0001_0000,
	    	            0b0000_1000,
	    	            0b0000_0100,
	    	            0b0000_0010,
	    	            0b0000_0001,
	    };
		String bs3 = AsnUtils.toBooleanString(mask);
		byte[] bytes2 = AsnUtils.fromBooleanString(bs3);
		
		
		byte[] mask2 = new byte[] {
	    	     (byte) 0b1000_0000,
	    	            0b0100_0000,
	    	            0b0010_0000,
	    	            0b0001_0000,
	    };
		String bs4 = AsnUtils.toBooleanString(mask2);
		byte[] bytes3 = AsnUtils.fromBooleanString(bs4);
		
		
		assert(bs4.equals(ms2));

		assert(bs3.equals(ms1));
		
		assert(bs1.equals(bs2));
		
	}


}