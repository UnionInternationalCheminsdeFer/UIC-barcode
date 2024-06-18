package org.uic.barcode.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;


public class UperEncodeRestrictedIntegerTest {

    /**
     * Example from the Standard on UPER.
     <pre>
TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
  number INTEGER(32000..63000),
}
    </pre>
     */
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	@IntRange(maxValue = 63000, minValue = 33000)
    	Long value;

        public TestRecord() {
            this(Long.valueOf(33005));
        }

        public TestRecord(Long num) {
        	value = num;
        }
    }
    
	@Before public void prepare() {	
		LoggerFactory.setActivateConsoleLog(true);
	}


    @Test public void test() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(33005L);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("000A",hex); 
    }
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(33005L);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("000A",hex); 
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value,record.value);
    }

}
