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


public class UperEncodeStringLengthTest {

    /**
     * Example from the Standard on UPER.
     <pre>
        World-Schema DEFINITIONS AUTOMATIC TAGS ::= 
        BEGIN
        TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
            testString1 UTF8String OPTIONAL
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
    	@RestrictedString(CharacterRestriction.UTF8String)
    	@Asn1Optional() String valueUtf8;

        public TestRecord() {
        }

        public TestRecord(String utf8) {
        	this.valueUtf8 = utf8;
        }
    }


    @Test public void testEncode1() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("A");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("80A080",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(record.valueUtf8,result.valueUtf8);
    }
    

    @Test public void testEncode63() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("123456789012345678901234567890123456789012345678901234567890123");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("9F9899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C9818991980",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(record.valueUtf8,result.valueUtf8);
    }
    
    @Test public void testEncode64() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("1234567890123456789012345678901234567890123456789012345678901234");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("A01899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A00",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(record.valueUtf8,result.valueUtf8);
    }
    
    @Test public void testEncode65() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("12345678901234567890123456789012345678901234567890123456789012345");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("A09899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A80",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(record.valueUtf8,result.valueUtf8);
    }
    
    @Test public void testEncode126() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("BF1899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C9818" + 
        		"99199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C98189919" + 
        		"9A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A" + 
        		"9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B00",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(record.valueUtf8,result.valueUtf8);
    }    
    
    
    @Test public void testEncode127() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("BF9899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C9818" + 
        		"99199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C98189919" + 
        		"9A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A" + 
        		"9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B" + 
        		"80",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(record.valueUtf8,result.valueUtf8);
    }    
    
    @Test public void testEncode128() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678");

        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C0401899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C98" + 
        		"1899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899" + 
        		"199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A" + 
        		"1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B" + 
        		"1B9C00",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(record.valueUtf8,result.valueUtf8);
    }
    
    @Test public void testEncode129() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C0409899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C98" + 
        		"1899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899" + 
        		"199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A" + 
        		"1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B1B9C1C981899199A1A9B" + 
        		"1B9C1C80",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(record.valueUtf8,result.valueUtf8);
    }

}
