package net.gcdc.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import net.gcdc.asn1.datatypes.Asn1Optional;
import net.gcdc.asn1.datatypes.Sequence;

import net.gcdc.asn1.uper.UperEncoder;

import org.junit.Test;


public class UperEncodeBooleanTest {

    /**
     * Example from the Standard on UPER.
     <pre>
         TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
            value BOOLEAN OPTIONAL,
}
    </pre>
     */
    @Sequence
    public static class TestRecord {
    	
    	
    	@Asn1Optional() Boolean value;

        public TestRecord() {
            this(false);
        }

        public TestRecord(Boolean value) {
        	this.value = value;
        }
    }


    @Test public void testTrue() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(new Boolean(true));
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C0",hex); 
    }
    
    @Test public void testFalse() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(new Boolean(false));
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("80",hex); 
    }    
    
    @Test public void testDecodeTrue() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(new Boolean(true));
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C0",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value,record.value);
        
    }
    
    @Test public void testDecodeFalse() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(new Boolean(false));
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("80",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value,record.value);        
    } 
    
    
    
    
    

}
