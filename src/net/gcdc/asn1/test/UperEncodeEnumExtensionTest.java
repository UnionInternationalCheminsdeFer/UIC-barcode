package net.gcdc.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import net.gcdc.asn1.datatypes.Asn1Optional;
import net.gcdc.asn1.datatypes.HasExtensionMarker;
import net.gcdc.asn1.datatypes.IsExtension;
import net.gcdc.asn1.datatypes.Sequence;

import net.gcdc.asn1.uper.UperEncoder;

import org.junit.Test;


public class UperEncodeEnumExtensionTest {

    /*** Example from the Standard on UPER.
    <pre>
    World-Schema DEFINITIONS AUTOMATIC TAGS ::= 
    BEGIN
    TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
            value EnumType DEFAULT value2
    }

    EnumType ::= ENUMERATED {	
		value1 (0),
		value2 (1)
		,...
    }	                                  
    END
    </pre>	
     */
    @Sequence
    public static class TestRecord {
    	
    	@Asn1Optional EnumType value = EnumType.value1;
        public TestRecord() {}
        public void setValue(EnumType value) {
        	this.value = value;
        }
    }
    
    
    /*** Example from the Standard on UPER.
    TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
           value EnumType DEFAULT value2,
    }

    EnumType			::= ENUMERATED {	
		value1 (0),
		value2 (1)
		,...
		value3 (2)
	 }		
    */
   @Sequence
   public static class TestRecordExtended {
   	
   	@Asn1Optional EnumTypeExtended value = EnumTypeExtended.value3;
    
   	public TestRecordExtended() {}
       
    public void setValue(EnumTypeExtended value) {
    	this.value = value;
    }
       
       
   }
   
   @HasExtensionMarker
   public enum EnumType {
		value1("value1"),
		value2("value2");
			
		public String text;

		EnumType(String text) {
			this.text = text;
		}
		
		public String toString(){
			return text;
		}		
   }

    
   @HasExtensionMarker
    public enum EnumTypeExtended {
		value1("value1"),
		value2("value2"),
		
		@IsExtension
		value3("value3");

		public String text;

		EnumTypeExtended(String text) {
			this.text = text;
		}
		
		public String toString(){
			return text;
		}		
    }



    @Test public void testExtension() throws IllegalArgumentException, IllegalAccessException {

        TestRecordExtended record = new TestRecordExtended();
        record.setValue(EnumTypeExtended.value3);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("Enum value3: data hex: %s", hex));
        assertEquals("C000", hex);
    }
    
    @Test public void testExtensionDecoding() throws IllegalArgumentException, IllegalAccessException {

        TestRecordExtended record = new TestRecordExtended();
        record.setValue(EnumTypeExtended.value3);
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("Enum value3: data hex: %s", hex));
        assertEquals("C000", hex);
        
        TestRecordExtended result = UperEncoder.decode(encoded, TestRecordExtended.class);
        assertEquals(result.value,EnumTypeExtended.value3);
    }
 
    @Test public void testUnknownExtensionDecoding() throws IllegalArgumentException, IllegalAccessException {

        TestRecordExtended record = new TestRecordExtended();
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("Enum value3: data hex: %s", hex));
        assertEquals("C000", hex);
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assert(result.value == null);
    }
    
    
}
