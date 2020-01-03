package net.gcdc.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import net.gcdc.asn1.datatypes.IntRange;
import net.gcdc.asn1.datatypes.Sequence;

import net.gcdc.asn1.uper.UperEncoder;

import org.junit.Test;


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
    	
    	@IntRange(maxValue = 63000, minValue = 33000)
    	Long value;

        public TestRecord() {
            this(new Long(33005));
        }

        public TestRecord(Long num) {
        	value = num;
        }
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
