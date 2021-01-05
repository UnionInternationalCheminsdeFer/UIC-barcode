package net.gcdc.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import net.gcdc.asn1.datatypes.Asn1Default;
import net.gcdc.asn1.datatypes.Asn1Optional;
import net.gcdc.asn1.datatypes.CharacterRestriction;
import net.gcdc.asn1.datatypes.FieldOrder;
import net.gcdc.asn1.datatypes.RestrictedString;
import net.gcdc.asn1.datatypes.Sequence;
import net.gcdc.asn1.uper.UperEncoder;

import org.junit.Test;


public class UperEncodeStringDefaultTest {

    /**
     * Example from the Standard on UPER.
     <pre>
         TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
            testString1 UTF8String OPTIONAL,
            testString2 IA5String DEFAULT("TestString")
     }
    </pre>
     */
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	@RestrictedString(CharacterRestriction.UTF8String)
    	@Asn1Optional() String valueUtf8;
    	
    	@FieldOrder(order = 1)
    	@RestrictedString(CharacterRestriction.IA5String)
    	@Asn1Default(value="testString") String valueIA5;

        public TestRecord() {
        }

        public TestRecord(String utf8, String ia5) {
        	this.valueUtf8 = utf8;
        	this.valueIA5  = ia5;
        }
    }


    @Test public void testEncode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("Müller", "testString");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("81D370EF1B1B195C80",hex);
    }
    
    @Test public void testEncodeDefault() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("Müller", "testString");
        byte[] encoded = UperEncoder.encode(record);
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals(result.valueIA5,"testString");
    }
    
    @Test public void testEncodeDefault2() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord("Müller", null);
        byte[] encoded = UperEncoder.encode(record);
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals(result.valueIA5,"testString");
    }
    

}
