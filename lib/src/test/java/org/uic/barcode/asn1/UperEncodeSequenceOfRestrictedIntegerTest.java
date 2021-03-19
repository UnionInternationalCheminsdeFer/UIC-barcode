package org.uic.barcode.asn1;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;


public class UperEncodeSequenceOfRestrictedIntegerTest {

    /**
     * Example from the Standard on UPER.
     <pre>
TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
  numbers SEQUENCE OF INTEGER(0..9999999),
}
    </pre>
     */
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	@IntRange(minValue=9500000,maxValue=99900001)
    	TestSequenceOfLong numbers = null;;

        public TestRecord() {
        }

        public void addNumber(Long longValue){
        	if (numbers == null) {
        		numbers = new TestSequenceOfLong();
        	} 
        	numbers.add(longValue);
        }
    }


    @Test public void test() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord();
        
        record.addNumber(new Long(9500001L));
        record.addNumber(new Long(9699999L));
        
       
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", UperEncoder.hexStringFromBytes(encoded)));
        assertEquals("02000000200C34FC",hex); 
    }
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord();
        
        record.addNumber(new Long(9500001L));
        record.addNumber(new Long(9699999L));
        
       
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", UperEncoder.hexStringFromBytes(encoded)));
        assertEquals("02000000200C34FC",hex); 
        
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.numbers.get(0).longValue(),record.numbers.get(0).longValue());        
        assertEquals(result.numbers.get(1).longValue(),record.numbers.get(1).longValue());
    }


}
