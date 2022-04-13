package org.uic.barcode.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypes.SizeRange;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;


public class UperEncodeRestrictedStringLengthTest {

    /**
     * Example from the Standard on UPER.
     <pre>
        World-Schema DEFINITIONS AUTOMATIC TAGS ::= 
        BEGIN
        TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
            testString1 IA5String (SIZE(1..2)) 	OPTIONAL
         }                                   
        END
        
        value TestRecord ::= {
  			testString1 "A"
		}

    </pre>
     */
	
	
	
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	@RestrictedString(CharacterRestriction.IA5String)
    	@SizeRange(minValue = 1, maxValue = 2)
    	@Asn1Optional() String value;

        public TestRecord() {
        }

        public TestRecord(String utf8) {
        	this.value = utf8;
        }
    }

	@Before public void prepare() {	
		LoggerFactory.setActivateConsoleLog(true);
	}

    @Test public void testEncode1() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("A");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("A080",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(record.value,result.value);
    }
    
}
