package org.uic.barcode.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypes.SequenceSizeRange;
import org.uic.barcode.asn1.datatypes.SizeRange;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfString;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;


public class UperEncodeSequenceOfSizeRangeTest {

    /**
     * Example from the Standard on UPER.
     <pre>
            TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
                strings SEQUENCE SIZE(2..9) OF IA5String,
         }
    </pre>
     */
	
	
	
	
	
	
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
        @SequenceSizeRange(minValue = 2, maxValue = 9)
    	@RestrictedString(CharacterRestriction.IA5String)
        @SizeRange(minValue = 2, maxValue = 6)    	
    	SequenceOfString strings = new SequenceOfString();

        public TestRecord() {
        }

		public SequenceOfString getStrings() {
			return strings;
		}


    }

	@Before public void prepare() {	
		LoggerFactory.setActivateConsoleLog(true);
	}
	
    @Test public void test() throws IllegalArgumentException, IllegalAccessException {

    	TestRecord record = new TestRecord();
    	record.getStrings().add("test1");
    	record.getStrings().add("test2");
    	record.getStrings().add("test3");
    	
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", UperEncoder.hexStringFromBytes(encoded)));
        assertEquals("2FA65E7D18BE9979F464FA65E7D198",hex);        
    }
    
    @Test public void testEncodeOutOfBoundaries() throws IllegalArgumentException, IllegalAccessException {

    	TestRecord record = new TestRecord();
    	record.getStrings().add("test1");
    	record.getStrings().add("test2");
    	record.getStrings().add("test3");
    	record.getStrings().add("test4");
    	record.getStrings().add("test5");
    	record.getStrings().add("test6");
    	record.getStrings().add("test7");
    	record.getStrings().add("test8");
    	record.getStrings().add("test9");
    	record.getStrings().add("test10");    	
    	
    	byte[] encoded = null;
    	try {
          encoded = UperEncoder.encode(record);
    	} catch (Throwable t) {
    	  //out of boundary exception expected
    	  assert(t != null);
    	  assert(encoded == null);
    	}

    }
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {

    	TestRecord record = new TestRecord();
    	record.getStrings().add("test1");
    	record.getStrings().add("test2");
    	record.getStrings().add("test3");
    	
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", UperEncoder.hexStringFromBytes(encoded)));
        assertEquals("2FA65E7D18BE9979F464FA65E7D198",hex);
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assert(result.getStrings().contains("test1"));
        assert(result.getStrings().contains("test2"));        
        assert(result.getStrings().contains("test3"));
        
    }

    
    
}
