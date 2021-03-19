package org.uic.barcode.asn1;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;


public class UperEncodeFieldOrderTest {

    /**
     * Example from the Standard on UPER.
     <pre>
        World-Schema DEFINITIONS AUTOMATIC TAGS ::= 
        BEGIN
        TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
            testString1 UTF8String OPTIONAL,
            testString2 IA5String OPTIONAL
         }                                   
        END
    </pre>
     */
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 1)
    	@RestrictedString(CharacterRestriction.IA5String)
    	@Asn1Optional() String string2;
    	
    	@FieldOrder(order = 0)
    	@RestrictedString(CharacterRestriction.UTF8String)
    	@Asn1Optional() String string1;
    	

        public TestRecord() {
        }

        public TestRecord(String utf8, String ia5) {
        	this.string1 = utf8;
        	this.string2  = ia5;
        }
    }


    @Test public void test() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord("String1", "String2");
        byte[] encoded = UperEncoder.encode(record);

        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.string1,"String1"); 
        assertEquals(result.string2,"String2");         
    }
    
   
    
    

}
