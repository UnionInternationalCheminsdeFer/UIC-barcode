package net.gcdc.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import net.gcdc.asn1.datatypes.CharacterRestriction;
import net.gcdc.asn1.datatypes.RestrictedString;
import net.gcdc.asn1.datatypes.Sequence;
import net.gcdc.asn1.datatypesimpl.SequenceOfStringIA5;
import net.gcdc.asn1.uper.UperEncoder;

import org.junit.Test;


public class UperEncodeSequenceOfStringTest {

    /**
     * Example from the Standard on UPER.
     <pre>
            TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
                strings SEQUENCE OF IA5String,
         }
    </pre>
     */
    @Sequence
    public static class TestRecord {
    	
    	
    	SequenceOfStringIA5 strings = new SequenceOfStringIA5();

        public TestRecord() {
        }

		public SequenceOfStringIA5 getStrings() {
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
        assertEquals("0305E9979F4620BD32F3E8C817A65E7D1980",hex);       
    }
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {

    	TestRecord record = new TestRecord();
    	record.getStrings().add("test1");
    	record.getStrings().add("test2");
    	record.getStrings().add("test3");
    	
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", UperEncoder.hexStringFromBytes(encoded)));
        assertEquals("0305E9979F4620BD32F3E8C817A65E7D1980",hex);
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assert(result.getStrings().contains("test1"));
        assert(result.getStrings().contains("test2"));        
        assert(result.getStrings().contains("test3"));
        
        
    }

}