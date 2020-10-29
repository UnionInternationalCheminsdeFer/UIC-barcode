package net.gcdc.asn1.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import net.gcdc.asn1.datatypes.Asn1Optional;
import net.gcdc.asn1.datatypes.CharacterRestriction;
import net.gcdc.asn1.datatypes.FieldOrder;
import net.gcdc.asn1.datatypes.HasExtensionMarker;
import net.gcdc.asn1.datatypes.IsExtension;
import net.gcdc.asn1.datatypes.RestrictedString;
import net.gcdc.asn1.datatypes.Sequence;

import net.gcdc.asn1.uper.UperEncoder;

import org.junit.Test;


public class UperEncodeSequenceExtensionTest {

    /**   Example for extended sequence including extension
          TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
              value1 IA5String,
              ,...
              value2 IA5String
          }
     */
    @Sequence
	@HasExtensionMarker
    public static class TestRecordExtended {
    	
    	@FieldOrder(order = 0)
    	@RestrictedString(CharacterRestriction.IA5String)
    	@Asn1Optional() String value1 = "regular";
    	
    	@FieldOrder(order = 1)
    	@IsExtension
    	@RestrictedString(CharacterRestriction.IA5String)
    	@Asn1Optional() String value2 = "extension";

    	
        public TestRecordExtended() {    }
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
		@Asn1Optional() String value1 = "regular";
	
		public TestRecord() {    }
    }


    @Test public void testEncode() throws IllegalArgumentException, IllegalAccessException {
        TestRecordExtended record = new TestRecordExtended();
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C1F965CFD7661E402121397C74CBBB9E9DFB80",hex); 
    }
       
    @Test public void testDecodeExtended() throws IllegalArgumentException, IllegalAccessException {
        TestRecordExtended record = new TestRecordExtended();
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C1F965CFD7661E402121397C74CBBB9E9DFB80",hex); 
        TestRecordExtended result = UperEncoder.decode(encoded, TestRecordExtended.class);
        assertEquals(result.value1,record.value1); 
        assertEquals(result.value2,record.value2); 
    } 
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {
        TestRecordExtended record = new TestRecordExtended();
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("C1F965CFD7661E402121397C74CBBB9E9DFB80",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value1,record.value1); 
    } 

    
    

}
