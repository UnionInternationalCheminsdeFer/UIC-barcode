package org.uic.barcode.asn1;

import static org.junit.Assert.assertEquals;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;

class UperEncodeObjectIdentifierTest {
	
	
    /**
     * Example from the Standard on UPER.
     <pre>
		TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
  			value1 OBJECT IDENTIFIER,
  			value2 OBJECT IDENTIFIER,
  			value3 OBJECT IDENTIFIER
		}
		
		value TestRecord ::= {
  			value1 2.16.840.1.101.3.4.3.1,
  			value2 2.16.840.1.101.3.4.3.2,
  			value3 1.2.840.10045.3.1.7			
		}
    </pre>
     */
	
    @Sequence
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	@RestrictedString(CharacterRestriction.ObjectIdentifier)
    	String value1 = "2.16.840.1.101.3.4.3.1";  //DSA SHA224

    	@FieldOrder(order = 1)
    	@RestrictedString(CharacterRestriction.ObjectIdentifier)
    	String value2 = "2.16.840.1.101.3.4.3.2";  //DSA SHA248
    	
    	@FieldOrder(order = 2)
    	@RestrictedString(CharacterRestriction.ObjectIdentifier)
    	String value3 = "1.2.840.10045.3.1.7";     //ECC
     
        public TestRecord() {}

    }
    
    @Test 
    public void testEncode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord();
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("0960864801650304030109608648016503040302082A8648CE3D030107",hex); 
    }
    
    @Test 
    public void testDecode() throws IllegalArgumentException, IllegalAccessException {
        TestRecord record = new TestRecord();
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("0960864801650304030109608648016503040302082A8648CE3D030107",hex); 
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value1,record.value1); 
        assertEquals(result.value2,record.value2);
        assertEquals(result.value3,record.value3);        
    } 


}
