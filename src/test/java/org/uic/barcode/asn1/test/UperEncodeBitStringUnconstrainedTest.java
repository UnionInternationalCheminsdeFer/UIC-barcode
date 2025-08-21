package org.uic.barcode.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.logging.Level;

import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.Bitstring;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;


public class UperEncodeBitStringUnconstrainedTest {

    /**
     * Example from the Standard on UPER. 
     <pre>
         World-Schema DEFINITIONS AUTOMATIC TAGS ::= 
         BEGIN
         TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
            value BIT STRING (SIZE(3)) OPTIONAL
         }
         END
         
         
         rec1value TestRecord ::= {
             value '001'B
         }
    </pre>
    
    
    
     */
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	@Asn1Optional()
    	@Bitstring()
    	ArrayList<Boolean> booleans = null;

        public TestRecord() {
	        booleans = new ArrayList<Boolean>();
        }

        public TestRecord(Boolean value1,Boolean value2,Boolean value3,
        		          Boolean value4,Boolean value5,Boolean value6,
        		          Boolean value7,Boolean value8,Boolean value9 ) {
        	booleans = new ArrayList<Boolean>();
        	booleans.add(value1);
        	booleans.add(value2);
        	booleans.add(value3);      
        	booleans.add(value4);
        	booleans.add(value5);
        	booleans.add(value6);  
        	booleans.add(value7);
        	booleans.add(value8);
        	booleans.add(value9);          	
        }
    }
    
	@Before public void prepare() {	
		LoggerFactory.setActivateConsoleLog(true);
	}


    @Test public void testEncode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(false,false,true,false,false,true,false,false,true);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("849240",hex); 
    }
    
    @Test public void testEncode2() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(false,false,true,false,false,false,false,false,false);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("8190",hex); 
    }    
    
    @Test public void testEncode3() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(false,false,false,false,false,false,false,false,false);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("8000",hex); 
    }
    

    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(false,false,true,false,false,true,false,false,true);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("849240",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.booleans.get(0),record.booleans.get(0));   
        assertEquals(result.booleans.get(1),record.booleans.get(1));   
        assertEquals(result.booleans.get(2),record.booleans.get(2));
        assertEquals(result.booleans.get(3),record.booleans.get(3));   
        assertEquals(result.booleans.get(4),record.booleans.get(4));   
        assertEquals(result.booleans.get(5),record.booleans.get(5));
        assertEquals(result.booleans.get(6),record.booleans.get(6));   
        assertEquals(result.booleans.get(7),record.booleans.get(7));   
        assertEquals(result.booleans.get(8),record.booleans.get(8));        
        
    }    

}
