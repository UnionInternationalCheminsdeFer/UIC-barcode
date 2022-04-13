package org.uic.barcode.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.Asn1VarSizeBitstring;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;


public class UperEncodeVarBitStringTest {

    /**
     * Example from the Standard on UPER. 
     <pre>
         World-Schema DEFINITIONS AUTOMATIC TAGS ::= 
         BEGIN
         TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
            value BIT STRING OPTIONAL,
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
    	@Asn1Optional() Asn1VarSizeBitstring value;

        public TestRecord() {
            this(false,false,true);
        }

        public TestRecord(Boolean value1,Boolean value2,Boolean value3 ) {
        	List<Boolean> booleans = new ArrayList<Boolean>();
        	booleans.add(value1);
        	booleans.add(value2);
        	booleans.add(value3);
        	this.value = new Asn1VarSizeBitstring(booleans);
        	
        }
    }

	@Before public void prepare() {	
		LoggerFactory.setActivateConsoleLog(true);
	}

    @Test public void testEncode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(false,false,true);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("8190",hex); 
    }
    

    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(false,false,true);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("8190",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value.get(0),record.value.get(0));   
        assertEquals(result.value.get(1),record.value.get(1));   
        assertEquals(result.value.get(2),record.value.get(2));
        
    }    

}
