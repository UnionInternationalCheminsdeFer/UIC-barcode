package org.uic.barcode.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.Bitstring;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.FixedSize;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;


public class UperEncodeBitStringTest {

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
    	@FixedSize(3)
    	ArrayList<Boolean> booleans = null;

        public TestRecord() {
            this(false,false,true);
        }

        public TestRecord(Boolean value1,Boolean value2,Boolean value3 ) {
        	booleans = new ArrayList<Boolean>();
        	booleans.add(value1);
        	booleans.add(value2);
        	booleans.add(value3);        	
        }
    }


    @Test public void testEncode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(false,false,true);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("90",hex); 
    }
    

    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(false,false,true);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("90",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.booleans.get(0),record.booleans.get(0));   
        assertEquals(result.booleans.get(1),record.booleans.get(1));   
        assertEquals(result.booleans.get(2),record.booleans.get(2));
        
    }    

}
