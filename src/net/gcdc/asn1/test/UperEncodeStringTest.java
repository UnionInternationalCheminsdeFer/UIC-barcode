package net.gcdc.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import net.gcdc.asn1.datatypes.Asn1Optional;
import net.gcdc.asn1.datatypes.CharacterRestriction;
import net.gcdc.asn1.datatypes.RestrictedString;
import net.gcdc.asn1.datatypes.Sequence;

import net.gcdc.asn1.uper.UperEncoder;

import org.junit.Test;


public class UperEncodeStringTest {

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
    	
    	@RestrictedString(CharacterRestriction.UTF8String)
    	@Asn1Optional() String valueUtf8;
    	
    	@RestrictedString(CharacterRestriction.IA5String)
    	@Asn1Optional() String valueIA5;

        public TestRecord() {
        }

        public TestRecord(String utf8, String ia5) {
        	this.valueUtf8 = utf8;
        	this.valueIA5  = ia5;
        }
    }


    @Test public void testEncode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("Müller", "Meier");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C1D370EF1B1B195C8166E5D39790",hex); 
    }
    
    @Test public void testEncodeUtf8() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("你好吗", "Meier");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C2792F6839696F796425C166E5D39790",hex); 
    }
    

    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("Müller", "Meier");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C1D370EF1B1B195C8166E5D39790",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.valueUtf8,record.valueUtf8); 
        assertEquals(result.valueIA5,record.valueIA5); 
    } 
    
    
    @Test public void testDecodeUtf8() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("你好吗", "Meier");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C2792F6839696F796425C166E5D39790",hex);  
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.valueUtf8,record.valueUtf8); 
        assertEquals(result.valueIA5,record.valueIA5); 
    }
    
    
    
    

}
