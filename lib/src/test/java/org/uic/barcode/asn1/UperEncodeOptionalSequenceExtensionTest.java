package org.uic.barcode.asn1;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.IsExtension;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;


public class UperEncodeOptionalSequenceExtensionTest {

    /**   Example for extended sequence including extension
          World-Schema DEFINITIONS AUTOMATIC TAGS ::= 
          BEGIN
          TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
              value1 IA5String,
              ...,
              value2 IA5String OPTIONAL
          }                                                    
          END
     */
    @Sequence
	@HasExtensionMarker
    public static class TestRecordExtended {
    	
    	@FieldOrder(order = 0)
    	@RestrictedString(CharacterRestriction.IA5String)
    	String value1;
    	
    	@FieldOrder(order = 1)
    	@IsExtension
    	@RestrictedString(CharacterRestriction.IA5String)
    	@Asn1Optional() String value2;

        public TestRecordExtended() {    }

		public void setValue1(String value1) {
			this.value1 = value1;
		}

		public void setValue2(String value2) {
			this.value2 = value2;
		}
        
        
        
    }
    
    /**   Example for extended sequence
    TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
        value1 IA5String,
        ,...
    }
     */
    @Sequence
    @HasExtensionMarker
    public static class TestRecord {
	
    	@RestrictedString(CharacterRestriction.IA5String)
		String value1 = "regular";
			public TestRecord() {    }
    }


    @Test public void testEncode() throws IllegalArgumentException, IllegalAccessException {
        TestRecordExtended record = new TestRecordExtended();
        record.setValue1("regular");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("03F2CB9FAECC3C80",hex); 
    }
    
    @Test public void testEncodeWithoutOptionalElement() throws IllegalArgumentException, IllegalAccessException {
        TestRecordExtended record = new TestRecordExtended();
        record.setValue1("regular");
        record.setValue2("extension");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("83F2CB9FAECC3C80424272F8E997773D3BF700",hex); 
    }
       
    @Test public void testDecodeExtended() throws IllegalArgumentException, IllegalAccessException {
        TestRecordExtended record = new TestRecordExtended();
        record.setValue1("regular");
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("03F2CB9FAECC3C80",hex); 
        TestRecordExtended result = UperEncoder.decode(encoded, TestRecordExtended.class);
        assertEquals(result.value1,record.value1); 
    } 
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {
        TestRecordExtended record = new TestRecordExtended();
        record.setValue1("regular");
        record.setValue2("extension");        
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("83F2CB9FAECC3C80424272F8E997773D3BF700",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value1,record.value1); 
    } 

    
    

}
