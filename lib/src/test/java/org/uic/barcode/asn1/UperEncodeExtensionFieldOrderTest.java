package org.uic.barcode.asn1;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.IsExtension;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;


public class UperEncodeExtensionFieldOrderTest {

    /**
     * Example from the Standard on UPER.
     <pre>
     TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {
           number1 INTEGER,
           ...,
           number2 INTEGER,
           number3 INTEGER 
    }
    
    value TestRecord ::=  {     
        value1       12345678909999899,
        value2       5555555555,
        value3       32001      
       }
    
Encoding to the file 'data.uper' using PER UNALIGNED encoding rule...
TestRecord SEQUENCE [root fieldcount (not encoded) = 1]
  value1 INTEGER [length = 7.0]
    12345678909999899
  value2 INTEGER [length = 5.0]
    5555555555
  value3 INTEGER [length = 2.0]
    32001
Total encoded length = 20.2
Encoded successfully in 21 bytes:
8395EE2A 2EF8858D 81C18140 52C8C338 C0C09F40 40
    
    
    </pre>
     */
    @Sequence
	@HasExtensionMarker
    public static class TestRecord {
    	
    	@FieldOrder(order = 0)
    	Asn1BigInteger value1;

    	@FieldOrder(order = 1)
    	@IsExtension
    	Asn1BigInteger value2;
    	    
    	@FieldOrder(order = 2)
    	@IsExtension
    	Asn1BigInteger value3;

        public TestRecord() {
            value1 = new Asn1BigInteger(12345678909999899L);
            value2 = new Asn1BigInteger(5555555555L);
            value3 = new Asn1BigInteger(32001L);
        }


    }


    @Test public void test() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord();
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("8395EE2A2EF8858D81C1814052C8C338C0C09F4040",hex);
        
                      
    }
    
    @Test public void testDecode() throws IllegalArgumentException, IllegalAccessException {

        TestRecord record = new TestRecord();
        byte[] encoded = UperEncoder.encode(record);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        assertEquals("8395EE2A2EF8858D81C1814052C8C338C0C09F4040",hex);
        
        TestRecord result = UperEncoder.decode(encoded, TestRecord.class);
        assertEquals(result.value1.longValue(),record.value1.longValue());
        assertEquals(result.value2.longValue(),record.value2.longValue());
        assertEquals(result.value3.longValue(),record.value3.longValue());
        
    }

}
