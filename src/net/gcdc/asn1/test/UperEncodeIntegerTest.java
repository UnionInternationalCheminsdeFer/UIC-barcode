package net.gcdc.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import net.gcdc.asn1.datatypes.Asn1BigInteger;
import net.gcdc.asn1.datatypes.Sequence;

import net.gcdc.asn1.uper.UperEncoder;

import org.junit.Test;


public class UperEncodeIntegerTest {

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
    	
    	Asn1BigInteger value;

        public TestRecord() {
            this(new Long(12345678909999899L));
        }

        public TestRecord(Long num) {
        	value = new Asn1BigInteger(num);
        }
    }
    


    @Test public void test() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(12345678909999899L);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("072BDC545DF10B1B",hex);
        
    }
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(12345678909999899L);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("072BDC545DF10B1B",hex);
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value.longValue(),record.value.longValue());
        
    }

}
