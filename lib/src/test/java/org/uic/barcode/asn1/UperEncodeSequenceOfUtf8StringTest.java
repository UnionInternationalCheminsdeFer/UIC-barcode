package org.uic.barcode.asn1;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringUTF8;
import org.uic.barcode.asn1.uper.UperEncoder;


public class UperEncodeSequenceOfUtf8StringTest {

    /**
     * Example from the Standard on UPER.
     <pre>
     	World-Schema DEFINITIONS AUTOMATIC TAGS ::= 
		BEGIN
 		TestRecord ::= SEQUENCE {
                strings SEQUENCE OF UTF8String
         }                                                 
		END

         value TestRecord ::= { 
             strings {"test1" , "test2" , "test3" }
         }
         

Encoding to the file 'data.uper' using PER UNALIGNED encoding rule...
TestRecord SEQUENCE [fieldcount (not encoded) = 1]
  strings SEQUENCE OF [count = 3]
    UTF8String [length = 5.0]
      0x7465737431
    UTF8String [length = 5.0]
      0x7465737432
    UTF8String [length = 5.0]
      0x7465737433
Total encoded length = 19.0
Encoded successfully in 19 bytes:
03057465 73743105 74657374 32057465 737433         
         
    </pre>
     */
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	SequenceOfStringUTF8 strings = new SequenceOfStringUTF8();

        public TestRecord() {
        }

		public SequenceOfStringUTF8 getStrings() {
			return strings;
		}


    }


    @Test public void test() throws IllegalArgumentException, IllegalAccessException {

    	TestRecord record = new TestRecord();
    	record.getStrings().add("test1");
    	record.getStrings().add("test2");
    	record.getStrings().add("test3");
    	
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", UperEncoder.hexStringFromBytes(encoded)));
        assertEquals("03057465737431057465737432057465737433",hex);   
    }
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {

    	TestRecord record = new TestRecord();
    	record.getStrings().add("test1");
    	record.getStrings().add("test2");
    	record.getStrings().add("test3");
    	
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", UperEncoder.hexStringFromBytes(encoded)));
        assertEquals("03057465737431057465737432057465737433",hex);   
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assert(result.getStrings().contains("test1"));
        assert(result.getStrings().contains("test2"));        
        assert(result.getStrings().contains("test3"));
        
        
    }

}
