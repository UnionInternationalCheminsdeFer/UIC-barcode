package org.uic.barcode.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;


public class UperEncodeOctetStringTest {

    /**
     * Example from the Standard on UPER.
     <pre>
		World-Schema DEFINITIONS AUTOMATIC TAGS ::= 
		BEGIN
		TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
            value OCTET STRING
         }                                                    
		END
         
		value TestRecord ::= { value '83DA'H }
		         
    </pre>
     */
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	OctetString value;

        public TestRecord() {
        	value = new OctetString();
        	value.add(hexToByte("83"));
        	value.add(hexToByte("DA"));
        }
    }

	@Before public void prepare() {	
		LoggerFactory.setActivateConsoleLog(true);
	}

    @Test public void test() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord();
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("0283DA",hex);
        
    }
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord();
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("0283DA",hex);
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value,record.value);
        
    }
      
    public static byte hexToByte(String s){
    	return (byte) ((Character.digit(s.charAt(0), 16) << 4)
                + Character.digit(s.charAt(1), 16));
    }

}
