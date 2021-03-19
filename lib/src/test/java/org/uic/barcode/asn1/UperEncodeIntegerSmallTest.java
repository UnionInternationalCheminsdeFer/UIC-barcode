package org.uic.barcode.asn1;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;


public class UperEncodeIntegerSmallTest {

    /**
     * Example from the Standard on UPER.
     <pre>
		TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
  			number1 INTEGER,
  			number2 INTEGER
		}
    </pre>
     */
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	public Long value1;
    	
    	@FieldOrder(order = 1)
    	public Integer value2;    	

        public TestRecord() {
            this(new Long(12345678909999899L));
        }

        public TestRecord(Long num) {
       		value1 = num;
       		value2 = Integer.valueOf(num.intValue());
        }
    }
    


    @Test public void test1() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(1L);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("01010101",hex);

        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value1.longValue(),record.value1.longValue());
        assertEquals(result.value2.longValue(),record.value2.longValue());       
        
    }
    
    @Test public void test16() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(16L);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("01100110",hex);

        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value1.longValue(),record.value1.longValue());
        assertEquals(result.value2.longValue(),record.value2.longValue());       
        
    }
    
    
    @Test public void test63() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(63L);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("013F013F",hex);

        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value1.longValue(),record.value1.longValue());
        assertEquals(result.value2.longValue(),record.value2.longValue());       
        
    }    
    
    @Test public void test64() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(64L);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("01400140",hex);

        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value1.longValue(),record.value1.longValue());
        assertEquals(result.value2.longValue(),record.value2.longValue());       
        
    }    
    
    @Test public void test127() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(127L);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("017F017F",hex);

        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value1.longValue(),record.value1.longValue());
        assertEquals(result.value2.longValue(),record.value2.longValue());       
        
    }    
    
    @Test public void test128() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(128L);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("020080020080",hex);

        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value1.longValue(),record.value1.longValue());
        assertEquals(result.value2.longValue(),record.value2.longValue());       
        
    }    

}
