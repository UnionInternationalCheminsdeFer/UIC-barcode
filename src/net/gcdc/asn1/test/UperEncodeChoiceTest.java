package net.gcdc.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import net.gcdc.asn1.datatypes.CharacterRestriction;
import net.gcdc.asn1.datatypes.Choice;
import net.gcdc.asn1.datatypes.RestrictedString;
import net.gcdc.asn1.uper.UperEncoder;

import org.junit.Test;


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
    	
    	@RestrictedString(CharacterRestriction.UTF8String)
    	String valueUtf8;
    	
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
