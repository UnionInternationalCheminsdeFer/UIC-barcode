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
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;


public class UperEncodeStringCustomAlphabetTest {

    /**
     * Example from the Standard on UPER.
     <pre>
        World-Schema DEFINITIONS AUTOMATIC TAGS ::= 
        BEGIN
        TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
            value1 PrintableString ( FROM ("ACGT") ) OPTIONAL,
            value2 PrintableString ( FROM ("ACGT") ) OPTIONAL
         }                                   
        END
        
        rec1value TestRecord ::= {
          value1 "ACGT",
          value2 "ACTGCATCGA"
        }
    </pre>
     */
    @Sequence
    public static class TestRecord {
    	   	
    	@FieldOrder(order = 0)
    	@RestrictedString(value = CharacterRestriction.VisibleString, alphabet = GenAlphabet.class)
    	@Asn1Optional() String value1;
    	
    	@FieldOrder(order = 1)
    	@RestrictedString(value = CharacterRestriction.VisibleString, alphabet = GenAlphabet.class)
    	@Asn1Optional() String value2;

        public TestRecord() {
        }

        public TestRecord(String v1, String v2) {
        	this.value1 = v1;
        	this.value2 = v2;
        }
    }

	@Before public void prepare() {	
		LoggerFactory.setActivateConsoleLog(true);
	}

    @Test public void testEncode() throws IllegalArgumentException, IllegalAccessException {
    	
  	
        TestRecord record = new TestRecord("ACGT", "ACTGCATCGA");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C106C2879360",hex); 
        
    }
    
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {
    	
        TestRecord record = new TestRecord("ACGT", "ACTGCATCGA");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C106C2879360",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value1,record.value1); 
        assertEquals(result.value2,record.value2); 
    } 
    
    

}
