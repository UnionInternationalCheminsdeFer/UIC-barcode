package org.uic.barcode.asn1;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.Choice;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.uper.UperEncoder;


public class UperEncodeChoiceTest {

    /**
     * Example from the Standard on UPER.
     <pre>
     TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
            value EnumType DEFAULT value2,
     }

     EnumType			::= ENUMERATED {	
		value1 (0),
		value2 (1)
		,...
	 }		
     </pre>
     */
    @Choice
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	@RestrictedString(CharacterRestriction.UTF8String)
    	String valueUtf8;
    	
    	@FieldOrder(order = 1)
    	@RestrictedString(CharacterRestriction.IA5String)
    	String valueIA5;

        public TestRecord() {
        }

        public TestRecord(String utf8, String ia5) {
        	this.valueUtf8 = utf8;
        	this.valueIA5  = ia5;
        }
    }
    
    @Test public void testEncode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(null, "Meier");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("82CDCBA72F20",hex); 
    }
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(null, "Meier");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("82CDCBA72F20",hex); 
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(null,record.valueUtf8); 
        assertEquals(result.valueIA5,record.valueIA5);         
    }




}
