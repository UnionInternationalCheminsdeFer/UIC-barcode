package org.uic.barcode.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.Choice;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypesimpl.Asn1NullObject;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;


public class UperEncodeChoiceWithNullTest {

    /**
     * Example from the Standard on UPER.
     <pre>
     TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
            value ChoiceType,
     }

     ChoiceType			::= CHOICE {	
        utf8            Utf8String,
		ia5             IA5String,
		choice3         NULL,
		choice4         NULL
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
    	
    	@FieldOrder(order = 2)
    	Asn1NullObject choice3 = null;
    	
    	@FieldOrder(order = 4)
    	Asn1NullObject choice4 = null;
    	
        public TestRecord() { }

        public TestRecord(String utf8, String ia5, String choice) {
        	this.valueUtf8 = utf8;
        	this.valueIA5  = ia5;
        	if ("choice3".equals(choice)) {
        	  this.choice3 = new Asn1NullObject(choice);
        	}
        	if ("choice4".equals(choice)) {
        	  this.choice4 = new Asn1NullObject(choice);
        	}
        }
    }
    
	@Before public void prepare() {	
		LoggerFactory.setActivateConsoleLog(true);
	}
    
    @Test public void testEncode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(null, "Meier", null);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("4166E5D39790",hex); 
    }
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(null, "Meier", null);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("4166E5D39790",hex); 
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(null,result.valueUtf8); 
        assertEquals(result.valueIA5,record.valueIA5); 
        assertEquals(result.choice3,null);  
        assertEquals(result.choice4,null);          
    }

    @Test public void testEncodeNull() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(null, null, "choice3");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("80",hex); 
    }
    
    @Test public void testDecodeNull() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord(null,null,"choice3");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("80",hex); 
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(null,result.valueUtf8); 
        assertEquals(null,result.valueIA5);       
        assert(result.choice3 != null);  
        assertEquals(result.choice4,null);         
    }


}
