package org.uic.barcode.ticket.api;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.api.asn.omv1.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfExtensionData;


public class UperEncodeUICTicketExtensionTest {

    /**
     * Example from the Standard on UPER.
     <pre>
 		World-Schema DEFINITIONS AUTOMATIC TAGS ::= 
		BEGIN
		TestRecord ::= [APPLICATION 0] IMPLICIT SEQUENCE {     
          extensionList			SEQUENCE OF ExtensionData 	OPTIONAL   
        }
        ExtensionData	::= SEQUENCE 	{	
	      extensionId   IA5String, 
	      extensionData OCTET STRING
        }        
        END
     
     
		value TestRecord ::= { extensionList {
       		{ extensionId "1", extensionData '82DA'H }
      	   ,{ extensionId "2", extensionData '83DA'H }
   		 }			
		}
     
Encoding to the file 'data.uper' using PER UNALIGNED encoding rule...
TestRecord SEQUENCE [fieldcount (not encoded) = 1]
  extensionList SEQUENCE OF [count = 2]
    ExtensionData SEQUENCE [fieldcount (not encoded) = 2]
      extensionId IA5String [length = 1.0]
        "1"
      extensionData OCTET STRING [length = 2.0]
        0x82da
    ExtensionData SEQUENCE [fieldcount (not encoded) = 2]
      extensionId IA5String [length = 1.0]
        "2"
      extensionData OCTET STRING [length = 2.0]
        0x83da
Total encoded length = 10.7
Encoded successfully in 11 bytes:
8100B102 82DA0164 0507B4
    </pre>
     */

	
    @Sequence
    public static class TestRecord {
    	
    	@Asn1Optional public SequenceOfExtensionData extension;
    	
        public TestRecord() {}
        public void setExtensionList(SequenceOfExtensionData sd){
        	this.extension = sd;
        }
    }


    @Test public void testEncodeTicket() throws IllegalArgumentException, IllegalAccessException {
    	TestRecord ticket = new TestRecord();
    	populateTicket(ticket);
        byte[] encoded = UperEncoder.encode(ticket);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        String expectedHex = "8100B10282DA01640507B4"; 
        UperEncoder.logger.debug(String.format("first diference at index: %d",hex.compareTo(expectedHex)));
        assertEquals(hex,expectedHex);
    }
    
	@Test public void testDecodeTicket() throws IllegalArgumentException, IllegalAccessException {
		TestRecord ticket = new TestRecord();
    	populateTicket(ticket);
        byte[] encoded = UperEncoder.encode(ticket);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        String expectedHex = "8100B10282DA01640507B4"; 
        TestRecord decodedTicket  = UperEncoder.decode(encoded, TestRecord.class);
        assert(decodedTicket != null);
        assertEquals(hex,expectedHex);
    }
    
    private void populateTicket(TestRecord ticket) {
    	
     	SequenceOfExtensionData ed = new SequenceOfExtensionData();
    	populateExtensionSequence(ed);
    	ticket.setExtensionList(ed);
    	
	}
    
  

	/*
       ,extension {
            { extensionId "1", extensionData '82DA'H }
           ,{ extensionId "2", extensionData '83DA'H }
        }
     */
    private void populateExtensionSequence(SequenceOfExtensionData ed) {
       	ExtensionData ed1 = new ExtensionData();
    	ed1.setExtensionId("1");
    	byte[] ba1 = { (byte) 0x82, (byte) 0xDA };
    	ed1.setExtensionData(ba1); 	
    	ExtensionData ed2 = new ExtensionData();    	
    	ed2.setExtensionId("2");
    	byte[] ba2 = { (byte) 0x83, (byte) 0xDA };
    	ed2.setExtensionData(ba2);     	
    	ed.add(ed1);
    	ed.add(ed2);
	}

    
    
    

}
