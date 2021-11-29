package org.uic.barcode.ticket.api.test.testtickets;

import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringUTF8;
import org.uic.barcode.ticket.api.asn.omv3.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.ControlData;
import org.uic.barcode.ticket.api.asn.omv3.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.DocumentData;
import org.uic.barcode.ticket.api.asn.omv3.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.IssuingData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTicketLinkType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv3.StationPassageData;
import org.uic.barcode.ticket.api.asn.omv3.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv3.TicketLinkType;
import org.uic.barcode.ticket.api.asn.omv3.TravelerData;
import org.uic.barcode.ticket.api.asn.omv3.TravelerType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;

public class StationPassageTestTicketV3 {
	
	/*
	 * 
value UicRailTicketData ::= {
  issuingDetail {
    issuingYear 2018,
    issuingDay 1,
    issuingTime 600,
    specimen TRUE,
    securePaperTicket FALSE,
    activated TRUE,
    currency "EUR",
    currencyFract 2,
    issuerPNR "issuerTestPNR",
    issuedOnLine 12
  },
  travelerDetail {
    traveler {
      {
        firstName "John",
        secondName "Dow",
        idCard "12345",
        ticketHolder TRUE,
        status {
          {
            customerStatusDescr "senior"
          }
        }
      }
    },
    groupName "myGroup"
  },
  transportDocument {
    {
      ticket stationPassage : {
        productName "passage",
        stationCodeTable stationUIC,
        stationNameUTF8 {
          "Amsterdam"
        },
        validFromDay 5,
        validFromTime 0,
        validUntilDay 5,
        validUntilTime 1000,
        numberOfDaysValid 5
      }
    }
  },
  controlDetail {
    identificationByCardReference {
      {
        trailingCardIdNum 100
      }
    },
    identificationByIdCard FALSE,
    identificationByPassportId FALSE,
    passportValidationRequired FALSE,
    onlineValidationRequired FALSE,
    ageCheckRequired FALSE,
    reductionCardCheckRequired FALSE,
    infoText "cd",
    includedTickets {
      {
        productOwnerIA5 "test",
        ticketType openTicket,
        linkMode issuedTogether
      }
    }
  },
  extension {
    {
      extensionId "1",
      extensionData '82DA'H
    },
    {
      extensionId "2",
      extensionData '83DA'H
    }
  }
}
	 */
	
	
	public static UicRailTicketData getUicTestTicket() {
		UicRailTicketData ticket = new UicRailTicketData();
    	populateTicket(ticket);
		return ticket;
	}
	
	public static String getEncodingHex() {
		return 
		"7804404004B14374F3E7D72F2A9979F4A13A90086280B4001044A6F686E03446"
		+ "F770562C99B46B01106E797769DFC81DB5E51DC9BDD5C00448088B40EE0C2E"
		+ "6E6C2CECA021282DAE6E8CAE4C8C2DA5D000019F40082A60100402C800131B"
		+ "20081013A65E7D00805881416D00B20283DA";
	}	
	
    private static void populateTicket(UicRailTicketData ticket) {
    	
    	ticket.setControlDetail(new ControlData());
    	populate(ticket.getControlDetail());
    	
     	
    	ticket.setIssuingDetail(new IssuingData());
    	populateIssuingData(ticket.getIssuingDetail());
    	
    	TravelerData td = new TravelerData();
    	populateTravelerData(td);
    	ticket.setTravelerDetail(td);    	
    	
    	SequenceOfDocumentData ds = new SequenceOfDocumentData();
  	    	
    	
    	//StationPassage
    	DocumentData do2 = new DocumentData();    	
    	addStationPassage(do2);
    	ds.add(do2);   	    	
    	
    	ticket.setTransportDocument(ds);
    	
       	SequenceOfExtensionData ed = new SequenceOfExtensionData();
    	populateExtensionSequence(ed);
    	ticket.setExtension(ed);
    	
	}
    
    /*
	 ticket stationPassage : {
       	productName "passage"
       	,stationNameUTF8 { "Amsterdam" }       
       	,validFromDay 0
       	,numberOfDaysValid 123
 	 }
     */
    private static void addStationPassage(DocumentData dd) {
    	TicketDetailData tdd = new TicketDetailData();
    	StationPassageData sp = new StationPassageData();  
    	sp.setProductName("passage");
    	sp.setValidFromDay(5L);
    	sp.setValidFromTime(0L);
    	sp.setValidUntilDay(5L);
    	sp.setValidUntilTime(1000L);
    	sp.setNumberOfDaysValid(5L);
    	SequenceOfStringUTF8 ss = new SequenceOfStringUTF8();
    	ss.add("Amsterdam");
    	sp.setStationNameUTF8(ss);   	
     	tdd.setStationPassage(sp);    	
    	dd.setTicket(tdd);
	}
    
    
    private static void populateTravelerData(TravelerData td) {
    	td.setGroupName("myGroup");
    	SequenceOfTravelerType trs = new SequenceOfTravelerType();
    	TravelerType tr = new TravelerType();
    	tr.setIdCard("12345");
    	tr.setFirstName("John");
    	tr.setSecondName("Dow");
    	tr.setTicketHolder(true);
    	SequenceOfCustomerStatusType ts = new SequenceOfCustomerStatusType();  	
    	CustomerStatusType cst = new CustomerStatusType();
    	cst.setCustomerStatusDescr("senior");
    	ts.add(cst);
    	tr.setStatus(ts);
    	trs.add(tr);
    	td.setTraveler(trs);
	}

    private static void populateIssuingData(IssuingData issuingDetail) {
    	issuingDetail.setIssuingYear(2018L);
    	issuingDetail.setIssuingDay(1L);
    	issuingDetail.setIssuingTime(600L);
    	issuingDetail.setIssuerPNR("issuerTestPNR");
    	issuingDetail.setSpecimen(true);
    	issuingDetail.setSecurePaperTicket(false);
    	issuingDetail.setActivated(true);
    	issuingDetail.setIssuedOnLine(12L);	
	}

    private static void populateExtensionSequence(SequenceOfExtensionData ed) {
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

    /*
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
          ,infoText "cd"
          ,includedTickets {
          	{ productOwnerIA5 "test" }
          }
       } 
     */
	private static void populate(ControlData controlDetail) {
		controlDetail.infoText = "cd";
		controlDetail.setAgeCheckRequired(false);
		controlDetail.setIdentificationByIdCard(false);
		controlDetail.setIdentificationByPassportId(false);
		controlDetail.setOnlineValidationRequired(false);
		controlDetail.setPassportValidationRequired(false);
		controlDetail.setReductionCardCheckRequired(false);
		controlDetail.setIdentificationByCardReference(new SequenceOfCardReferenceType());
		controlDetail.getIdentificationByCardReference().add(populateCardRefrence());
		SequenceOfTicketLinkType sit = new SequenceOfTicketLinkType();
		populateLinkedTickets(sit);
		controlDetail.setIncludedTickets(sit);
	}

	
	/*
	 * 
	 */
	private static void populateLinkedTickets(SequenceOfTicketLinkType sequenceOfTicketLinkType) {
		TicketLinkType tlt = new TicketLinkType();
		tlt.productOwnerIA5="test";
		sequenceOfTicketLinkType.add(tlt);
	}

	/*
	 {
	   trailingCardIdNum 100
	 }
	 */
	private static CardReferenceType populateCardRefrence() {
		CardReferenceType cr = new CardReferenceType();
		cr.setTrailingCardIdNum(100L);
		return cr;
	}







}
