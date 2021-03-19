package org.uic.barcode.asn1;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;


public class UperEncodeEnumTest {

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
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	@Asn1Default(value="value2")
    	@Asn1Optional EnumType value = EnumType.value2;
    	
    	
        public TestRecord() {}

        public TestRecord(EnumType value) {
        	this.value = value;
        }
    }
    
    public enum EnumType {
		value1("value1"),
		value2("value2"),
		value3("value3"),
		value4("value4"),
		value5("value5"),
		value6("value6"),
		value7("value7"),
		value8("value8"),
		value9("value9"),
		value10("value10"),
		value11("value11"),
		value12("value12"),
		value13("value13"),
		value14("value14"),
		value15("value15"),
		value16("value16"),
		value17("value17"),
		value18("value18"),
		value19("value19"),
		value20("value20"),
		value21("value21"),
		value22("value22");
		
		
		public String text;

		EnumType(String text) {
			this.text = text;
		}
		
		public String toString(){
			return text;
		}		
    }



    @Test public void testNonDefaultValue() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(EnumType.value4);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("Enum value4: data hex: %s", hex));
        assertEquals("8C", hex);
    }
    
    @Test public void testDefaultValue() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(EnumType.value2);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("Enum value2: data hex: %s", hex));
        assertEquals("00", UperEncoder.hexStringFromBytes(encoded));
    }    
    
    @Test public void testDecodeNonDefaultValue() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(EnumType.value4);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("Enum value4: data hex: %s", hex));
        assertEquals("8C", hex);
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value,EnumType.value4);
    }
    
    @Test public void testDecodeDefaultValue() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord(EnumType.value2);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("Enum value2: data hex: %s", hex));
        assertEquals("00", UperEncoder.hexStringFromBytes(encoded));
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value,EnumType.value2);
    }     

}
