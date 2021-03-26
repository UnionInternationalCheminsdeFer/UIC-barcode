package org.uic.barcode.ticket.api;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;

import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData;
import org.uic.barcode.ticket.api.testtickets.SimpleUicTestTicket;


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
     
     
78022020 050DD3CF 9F5CBCAA 65E7D284 EA40218A 02D00082 2537B437 01A237BB
82B164CD A3580883 73CBBB4E FE40EDAF 28EE4DEE AE004A03 AD12B014 16D08000
00000403 9BDC195B 951A58DA D95D125B 999BC480 88040EE0 C2E6E6C2 CECA0212
82DAE6E8 CAE4C8C2 DA0080BD A6010040 2C800131 B2008101 3A65E7D0 08058814
16D00B20 283DA0

    </pre>
     */



    @Test public void testEncodeTicket() throws IllegalArgumentException, IllegalAccessException {
    	UicRailTicketData ticket = SimpleUicTestTicket.getUicTestTicket();
        byte[] encoded = UperEncoder.encode(ticket);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
       
        String expectedHex = "78022020050DD3CF9F5CBCAA65E7D284EA40218A02D000822537B43701A237BB82B164CDA358088373CBBB4EFE40EDAF28EE4DEEAE004A03AD12B01416D08000000004039BDC195B951A58DAD95D125B999BC48088040EE0C2E6E6C2CECA021282DAE6E8CAE4C8C2DA0080BDA60100402C800131B20081013A65E7D00805881416D00B20283DA0"; 
        UperEncoder.logger.debug(String.format("first difference at index: %d",hex.compareTo(expectedHex)));
        UperEncoder.logger.debug(String.format("data returned: %s",hex)); 
        UperEncoder.logger.debug(String.format("data expected: %s",expectedHex)); 
        assert(hex.equals(expectedHex));
    }
    
	@Test public void testDecodeTicket() throws IllegalArgumentException, IllegalAccessException {
    	UicRailTicketData ticket = SimpleUicTestTicket.getUicTestTicket();
        byte[] encoded = UperEncoder.encode(ticket);
        String hex = UperEncoder.hexStringFromBytes(encoded);
        UperEncoder.logger.log(Level.FINEST,String.format("data hex: %s", hex));
        String expectedHex = "78022020050DD3CF9F5CBCAA65E7D284EA40218A02D000822537B43701A237BB82B164CDA358088373CBBB4EFE40EDAF28EE4DEEAE004A03AD12B01416D08000000004039BDC195B951A58DAD95D125B999BC48088040EE0C2E6E6C2CECA021282DAE6E8CAE4C8C2DA0080BDA60100402C800131B20081013A65E7D00805881416D00B20283DA0"; 
        assertEquals(hex,expectedHex);
        UicRailTicketData decodedTicket  = UperEncoder.decode(encoded, UicRailTicketData.class);
        assert(decodedTicket != null);
        assertEquals(decodedTicket.controlDetail.ageCheckRequired , false );
        assertEquals(decodedTicket.controlDetail.getInfoText() , "cd" );
        assertEquals(decodedTicket.controlDetail.getIncludedTickets().get(0).getProductOwnerIA5() , "test" );
        assert(decodedTicket.controlDetail.getIdentificationByCardReference().get(0).getTrailingCardIdNum() == 100L );
    }    

}
