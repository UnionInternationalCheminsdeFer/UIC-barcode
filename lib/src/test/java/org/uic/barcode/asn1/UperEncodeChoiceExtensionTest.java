package org.uic.barcode.asn1;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.Choice;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.IsExtension;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.uper.UperEncoder;


public class UperEncodeChoiceExtensionTest {

    /**   Example for extended sequence
  	TestRecord ::= [APPLICATION 0] CHOICE {
      value1 IA5String
      ,...
      ,value2 IA5String
  	}
  	
  	value TestRecord ::= value2: "extension"
     */
    @Choice
	@HasExtensionMarker
	public static class TestRecordExtended {
  	
    	@FieldOrder(order = 0)
    	@RestrictedString(CharacterRestriction.IA5String)
    	String value1 = null;
  	
    	@FieldOrder(order = 1)
    	@IsExtension
    	@RestrictedString(CharacterRestriction.IA5String)
    	String value2 = "extension";

    	public TestRecordExtended() {    }
  }
  
    /**   Example for extended sequence
  	TestRecord ::= [APPLICATION 0] CHOICE {
      value1 IA5String,
      ,...
  	}
     */
    @Choice
    @HasExtensionMarker
    public static class TestRecord {
	
    	@RestrictedString(CharacterRestriction.IA5String)
		@Asn1Optional() String value1 = "regular";
	
		public TestRecord() {    }
    }


  @Test public void testEncode() throws IllegalArgumentException, IllegalAccessException {
      TestRecordExtended record = new TestRecordExtended();
      byte[] encoded = UperEncoder.encode(record);
      String hex = UperEncoder.hexStringFromBytes(encoded);
      UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
      assertEquals("800909CBE3A65DDCF4EFDC",hex); 
  }
     
  @Test public void testDecodeExtended() throws IllegalArgumentException, IllegalAccessException {
      TestRecordExtended record = new TestRecordExtended();
      byte[] encoded = UperEncoder.encode(record);
      String hex = UperEncoder.hexStringFromBytes(encoded);
      UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
      assertEquals("800909CBE3A65DDCF4EFDC",hex); 
      TestRecordExtended result = UperEncoder.decode(encoded, TestRecordExtended.class);
      assertEquals(result.value2,record.value2); 
  } 
  
  @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {
      TestRecordExtended record = new TestRecordExtended();
      byte[] encoded = UperEncoder.encode(record);
      String hex = UperEncoder.hexStringFromBytes(encoded);
      UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
      assertEquals("800909CBE3A65DDCF4EFDC",hex); 
      TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
      assert(result == null); 
  } 



}
