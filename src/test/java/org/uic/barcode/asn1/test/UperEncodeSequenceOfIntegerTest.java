package org.uic.barcode.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfUnrestrictedLong;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;


public class UperEncodeSequenceOfIntegerTest {

    /**
     * Example from the Standard on UPER.
     <pre>
TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
  number INTEGER,
}
    </pre>
     */
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	SequenceOfUnrestrictedLong numbers;

        public TestRecord() {
        }

        public TestRecord(List<Long> nums) {
        	numbers = new SequenceOfUnrestrictedLong(nums);
        }
    }

	@Before public void prepare() {	
		LoggerFactory.setActivateConsoleLog(true);
	}

    @Test public void test() throws IllegalArgumentException, IllegalAccessException {

    	ArrayList<Long> nums = new ArrayList<Long>();
    	nums.add(Long.valueOf(12345678909999899L));
    	nums.add(Long.valueOf(12345678909999899L));        	
        TestRecord record = new TestRecord(nums);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", UperEncoder.hexStringFromBytes(encoded)));
        assertEquals("02072BDC545DF10B1B072BDC545DF10B1B",hex);
        
    }
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {

    	ArrayList<Long> nums = new ArrayList<Long>();
    	nums.add(Long.valueOf(12345678909999899L));
    	nums.add(Long.valueOf(12345678909999899L));        	
        TestRecord record = new TestRecord(nums);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", UperEncoder.hexStringFromBytes(encoded)));
        assertEquals("02072BDC545DF10B1B072BDC545DF10B1B",hex);
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.numbers.get(0).longValue(),record.numbers.get(0).longValue());        
        assertEquals(result.numbers.get(1).longValue(),record.numbers.get(1).longValue());
        
        
    }

}
