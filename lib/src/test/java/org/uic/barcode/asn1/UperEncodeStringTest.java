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
    	
    	@FieldOrder(order = 0)
    	@RestrictedString(CharacterRestriction.UTF8String)
    	@Asn1Optional() String valueUtf8;
    	
    	@FieldOrder(order = 1)
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
    	
    	//Teststring: AêñüC
    	String original = new String("A" + "\u00ea" + "\u00f1" + "\u00fc" + "C");
    	
        TestRecord record = new TestRecord(original, "Meier");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C21070EAB0EC70EF10C166E5D39790",hex); 
        
    }
    
    @Test public void testEncodeUtf8() throws IllegalArgumentException, IllegalAccessException {
    	
    	 //"你好�?�" 
        String original = new String("\u00e4" + "\u00bd" + "\u00a0" + "\u00e5" + "\u00a5" + "\u00bd" + "\u00e5" + "\u0090" + "\u0097");
        TestRecord record = new TestRecord(original, "Meier");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C4B0E930AF70A830E970A970AF70E970A430A5C166E5D39790",hex); 
    }
    

    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {

    	//Teststring: AêñüC
    	String original = new String("A" + "\u00ea" + "\u00f1" + "\u00fc" + "C");

    	TestRecord record = new TestRecord(original, "Meier");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C21070EAB0EC70EF10C166E5D39790",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.valueUtf8,record.valueUtf8); 
        assertEquals(result.valueIA5,record.valueIA5); 
    } 
    
    
    @Test public void testDecodeUtf8() throws IllegalArgumentException, IllegalAccessException {
   	    //"你好�?�" 
        String original = new String("\u00e4" + "\u00bd" + "\u00a0" + "\u00e5" + "\u00a5" + "\u00bd" + "\u00e5" + "\u0090" + "\u0097");
        TestRecord record = new TestRecord(original, "Meier");    	
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C4B0E930AF70A830E970A970AF70E970A430A5C166E5D39790",hex);  
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.valueUtf8,record.valueUtf8); 
        assertEquals(result.valueIA5,record.valueIA5); 
    }
    
    
    
    

}
