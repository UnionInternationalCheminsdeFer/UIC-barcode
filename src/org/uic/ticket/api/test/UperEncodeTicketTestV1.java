package org.uic.ticket.api.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import net.gcdc.asn1.datatypesimpl.SequenceOfStringUTF8;
import net.gcdc.asn1.uper.UperEncoder;

import org.junit.Test;
import org.uic.ticket.api.asn.omv1.CardReferenceType;
import org.uic.ticket.api.asn.omv1.ControlData;
import org.uic.ticket.api.asn.omv1.CustomerStatusType;
import org.uic.ticket.api.asn.omv1.DocumentData;
import org.uic.ticket.api.asn.omv1.ExtensionData;
import org.uic.ticket.api.asn.omv1.IssuingData;
import org.uic.ticket.api.asn.omv1.OpenTicketData;
import org.uic.ticket.api.asn.omv1.SequenceOfCardReferenceType;
import org.uic.ticket.api.asn.omv1.SequenceOfCustomerStatusType;
import org.uic.ticket.api.asn.omv1.SequenceOfDocumentData;
import org.uic.ticket.api.asn.omv1.SequenceOfExtensionData;
import org.uic.ticket.api.asn.omv1.SequenceOfTicketLinkType;
import org.uic.ticket.api.asn.omv1.SequenceOfTravelerType;
import org.uic.ticket.api.asn.omv1.StationPassageData;
import org.uic.ticket.api.asn.omv1.TicketDetailData;
import org.uic.ticket.api.asn.omv1.TicketLinkType;
import org.uic.ticket.api.asn.omv1.TokenType;
import org.uic.ticket.api.asn.omv1.TravelerData;
import org.uic.ticket.api.asn.omv1.TravelerType;
import org.uic.ticket.api.asn.omv1.UicRailTicketData;


public class UperEncodeTicketTestV1 {

    /**
     * Example from the Standard on UPER.
     <pre>
     value UicRailTicketData ::=  {     
       issuingDetail {
            issuingYear       2018
            issuingDay        1
       	    specimen          TRUE,
            securePaperTicket FALSE,
            activated         TRUE,
            issuerPNR         "issuerTestPNR",
       	    issuedOnLine      12
       }
       ,travelerDetail{
            traveler {
               {
                	firstName      "John"
          	     	,secondName    "Dow"
                	,idCard        "12345"
                	,ticketHolder  TRUE
          			,status        {{customerStatusDescr "senior" }}
               }
            }
           ,groupName "myGroup"
       }
       ,transportDocument {
          	{
	         	 token {tokenProviderIA5 "VDV", token '82DA'H }
	        	,ticket openTicket : {
	        			returnIncluded    FALSE
        				infoText          "openTicketInfo"
	        	 }
	       	}
	      	,{
	        	 ticket stationPassage : {
       					 productName       "passage"
       					,stationNameUTF8   { "Amsterdam" }       
       					,validFromDay      0
       					,numberOfDaysValid 123
 	        	}
	       	}		
       } 
       ,controlDetail {
          identificationByCardReference {
            { trailingCardIdNum 100 }
          }
   	      ,identificationByIdCard	    FALSE
    	  ,identificationByPassportId   FALSE
          ,passportValidationRequired  	FALSE
      	  ,onlineValidationRequired    	FALSE
          ,ageCheckRequired            	FALSE   	
    	  ,reductionCardCheckRequired  	FALSE          
          ,infoText                     "cd"
          ,includedTickets {
          	{ productOwnerIA5 "test" }
          }
       } 
       ,extension {
            { extensionId "1", extensionData '82DA'H }
           ,{ extensionId "2", extensionData '83DA'H }
        }			
     }
     
     
     Encoding to the file 'data.uper' using PER UNALIGNED encoding rule...
UicRailTicketData SEQUENCE [root fieldcount (not encoded) = 5]
  issuingDetail IssuingData SEQUENCE [root fieldcount (not encoded) = 7]
    issuingYear INTEGER [length (not encoded) = 1.0]
      2018
    issuingDay INTEGER [length (not encoded) = 1.1]
      1
    specimen BOOLEAN [length (not encoded) = 0.1]
      TRUE
    securePaperTicket BOOLEAN [length (not encoded) = 0.1]
      FALSE
    activated BOOLEAN [length (not encoded) = 0.1]
      TRUE
    issuerPNR IA5String [length = 13.0]
      "issuerTestPNR"
    issuedOnLine INTEGER [length = 1.0]
      12
  travelerDetail TravelerData SEQUENCE [root fieldcount (not encoded) = 2]
    traveler SEQUENCE OF [count = 1]
      TravelerType SEQUENCE [root fieldcount (not encoded) = 5]
        firstName UTF8String [length = 4.0]
          0x4a6f686e
        secondName UTF8String [length = 3.0]
          0x446f77
        idCard IA5String [length = 5.0]
          "12345"
        ticketHolder BOOLEAN [length (not encoded) = 0.1]
          TRUE
        status SEQUENCE OF [count = 1]
          CustomerStatusType SEQUENCE [fieldcount (not encoded) = 1]
            customerStatusDescr IA5String [length = 6.0]
              "senior"
    groupName UTF8String [length = 7.0]
      0x6d7947726f7570
  transportDocument SEQUENCE OF [count = 2]
    DocumentData SEQUENCE [root fieldcount (not encoded) = 2]
      token TokenType SEQUENCE [fieldcount (not encoded) = 2]
        tokenProviderIA5 IA5String [length = 3.0]
          "VDV"
        token OCTET STRING [length = 2.0]
          0x82da
      ticket CHOICE [index = 2]
        openTicket OpenTicketData SEQUENCE [root fieldcount (not encoded) = 2]
          returnIncluded BOOLEAN [length (not encoded) = 0.1]
            FALSE
          infoText UTF8String [length = 14.0]
            0x6f70656e5469636b6574496e666f
    DocumentData SEQUENCE [root fieldcount (not encoded) = 1]
      ticket CHOICE [index = 9]
        stationPassage StationPassageData SEQUENCE [root fieldcount (not encoded) = 4]
          productName UTF8String [length = 7.0]
            0x70617373616765
          stationNameUTF8 SEQUENCE OF [count = 1]
            UTF8String [length = 9.0]
              0x416d7374657264616d
          validFromDay INTEGER [length (not encoded) = 1.2]
            0
          numberOfDaysValid INTEGER [length = 1.0]
            123
  controlDetail ControlData SEQUENCE [root fieldcount (not encoded) = 9]
    identificationByCardReference SEQUENCE OF [count = 1]
      CardReferenceType SEQUENCE [root fieldcount (not encoded) = 1]
        trailingCardIdNum INTEGER [length = 1.0]
          100
    identificationByIdCard BOOLEAN [length (not encoded) = 0.1]
      FALSE
    identificationByPassportId BOOLEAN [length (not encoded) = 0.1]
      FALSE
    passportValidationRequired BOOLEAN [length (not encoded) = 0.1]
      FALSE
    onlineValidationRequired BOOLEAN [length (not encoded) = 0.1]
      FALSE
    ageCheckRequired BOOLEAN [length (not encoded) = 0.1]
      FALSE
    reductionCardCheckRequired BOOLEAN [length (not encoded) = 0.1]
      FALSE
    infoText UTF8String [length = 2.0]
      0x6364
    includedTickets SEQUENCE OF [count = 1]
      TicketLinkType SEQUENCE [root fieldcount (not encoded) = 1]
        productOwnerIA5 IA5String [length = 4.0]
          "test"
  extension SEQUENCE OF [count = 2]
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
Total encoded length = 133.7
Encoded successfully in 134 bytes:
78022020 050DD3CF 9F5CBCAA 65E7D284 EA40218A 02D00082 2537B437 01A237BB
82B164CD A3580883 73CBBB4E FE40EDAF 28EE4DEE AE004A03 AD12B014 16D08000
0000100E 6F70656E 5469636B 6574496E 666F1202 2040EE0C 2E6E6C2C ECA02128
2DAE6E8C AE4C8C2D A0080BDA 60100402 C800131B 20082027 4CBCFA01 00B10282
DA016405 07B4

 
			
    </pre>
     */



    @Test public void testEncodeTicket() throws IllegalArgumentException, IllegalAccessException {
    	UicRailTicketData ticket = SimpleUicTestTicket.getUicTestTicket();
        byte[] encoded = UperEncoder.encode(ticket);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
       
        String expectedHex = "78022020050DD3CF9F5CBCAA65E7D284EA40218A02D000822537B43701A237BB82B164CDA358088373CBBB4EFE40EDAF28EE4DEEAE004A03AD12B01416D080000000100E6F70656E5469636B6574496E666F12022040EE0C2E6E6C2CECA021282DAE6E8CAE4C8C2DA0080BDA60100402C800131B200820274CBCFA0100B10282DA01640507B4"; 
        UperEncoder.logger.debug(String.format("first difference at index: %d",hex.compareTo(expectedHex)));
        UperEncoder.logger.debug(String.format("data returned: %s",hex)); 
        UperEncoder.logger.debug(String.format("data expected: %s",expectedHex)); 
        assertEquals(hex,expectedHex);
    }
    
	@Test public void testDecodeTicket() throws IllegalArgumentException, IllegalAccessException {
    	UicRailTicketData ticket = SimpleUicTestTicket.getUicTestTicket();
        byte[] encoded = UperEncoder.encode(ticket);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        String expectedHex = "78022020050DD3CF9F5CBCAA65E7D284EA40218A02D000822537B43701A237BB82B164CDA358088373CBBB4EFE40EDAF28EE4DEEAE004A03AD12B01416D080000000100E6F70656E5469636B6574496E666F12022040EE0C2E6E6C2CECA021282DAE6E8CAE4C8C2DA0080BDA60100402C800131B200820274CBCFA0100B10282DA01640507B4"; 
        assertEquals(hex,expectedHex);
        UicRailTicketData decodedTicket  = UperEncoder.decode(encoded, UicRailTicketData.class);
        assert(decodedTicket != null);
        assertEquals(decodedTicket.controlDetail.ageCheckRequired , false );
        assertEquals(decodedTicket.controlDetail.getInfoText() , "cd" );
        assertEquals(decodedTicket.controlDetail.getIncludedTickets().get(0).getProductOwnerIA5() , "test" );
        assert(decodedTicket.controlDetail.getIdentificationByCardReference().get(0).getTrailingCardIdNum() == 100L );
    }    

}
