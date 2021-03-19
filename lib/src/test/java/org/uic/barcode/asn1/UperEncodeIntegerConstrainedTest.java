package org.uic.barcode.asn1;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;


public class UperEncodeIntegerConstrainedTest {

    /**
     * Example from the Standard on UPER.
     <pre>
		TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
  			number1 INTEGER (1..999),
  			number2 INTEGER (0..999),
  			number3 INTEGER (63..999)
		}
    </pre>
     */
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	@IntRange(minValue=1, maxValue=999)
    	public Long value1;
    	
    	@FieldOrder(order = 1)
    	@IntRange(minValue=0, maxValue=999)
    	public Long value2;    	
    	
    	@FieldOrder(order = 2)
    	@IntRange(minValue=63, maxValue=999)
    	public Long value3;    	
  	    	

        public TestRecord() {
            this(new Long(63L));
        }

        public TestRecord(Long num) {
        	value1 = num;
        	value2 = num;
        	value3 = num;
        }
    }
    


    @Test public void test() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(63L);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("0F83F000",hex);
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value1.longValue(),record.value1.longValue());
        assertEquals(result.value2.longValue(),record.value2.longValue());    
        assertEquals(result.value3.longValue(),record.value3.longValue());    
               
    }
    


}
