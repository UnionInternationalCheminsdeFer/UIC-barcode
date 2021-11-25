package org.uic.barcode.ticket.api.test.testtickets;

import org.uic.barcode.asn1.datatypesimpl.SequenceOfUnrestrictedLong;
import org.uic.barcode.ticket.api.asn.omv1.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv1.ControlData;
import org.uic.barcode.ticket.api.asn.omv1.CountermarkData;
import org.uic.barcode.ticket.api.asn.omv1.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv1.DocumentData;
import org.uic.barcode.ticket.api.asn.omv1.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv1.IssuingData;
import org.uic.barcode.ticket.api.asn.omv1.LinkMode;
import org.uic.barcode.ticket.api.asn.omv1.RegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfRegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfTicketLinkType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfViaStationType;
import org.uic.barcode.ticket.api.asn.omv1.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv1.TicketLinkType;
import org.uic.barcode.ticket.api.asn.omv1.TicketType;
import org.uic.barcode.ticket.api.asn.omv1.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv1.TravelerData;
import org.uic.barcode.ticket.api.asn.omv1.TravelerType;
import org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData;
import org.uic.barcode.ticket.api.asn.omv1.ViaStationType;
import org.uic.barcode.ticket.api.asn.omv1.ZoneType;


	public class CountermarkTestComplexTicketV1 {
		
		public static UicRailTicketData getUicTestTicket() {
			UicRailTicketData ticket = new UicRailTicketData();
	    	populateTicket(ticket);
			return ticket;
		}
		
		/*
value UicRailTicketData ::=  {     
       issuingDetail {
           issuingYear       2018
           issuingDay        1
           issuingTime       600
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
          ticket counterMark : {
             numberOfCountermark 12
            ,totalOfCountermarks 24
            ,groupName "groupName"
            ,validRegion 
  	      { viaStations { 
  	         route {
   		   { stationNum 123455, border FALSE }
   		  ,{ stationNum 123456, border FALSE }
                  ,{ alternativeRoutes {
                      { route {  {stationNum 23455, border FALSE},{stationNum 23456, border FALSE }}, border FALSE  }
                     ,{ route {  {stationNum 3455, border FALSE },{stationNum 3456, border FALSE }},  border FALSE  }
                     }
                     ,border FALSE 
                   }
	    	  ,{ stationNum 123457, border FALSE }
	         }
	         ,border FALSE
	    	 ,seriesId 999
	       }
	      ,zones { zoneId {100,200}}
	      }
             ,returnIncluded    FALSE
       	     ,classCode         first
       	     ,infoText          "counterMark"
       	    }
	  }	
	} 
       ,controlDetail {
          identificationByCardReference {{ trailingCardIdNum 100 }  }
 	 ,identificationByIdCard	FALSE
  	 ,identificationByPassportId    FALSE
         ,passportValidationRequired  	FALSE
    	 ,onlineValidationRequired    	FALSE
         ,ageCheckRequired            	FALSE   	
  	 ,reductionCardCheckRequired  	FALSE          
         ,infoText                     "cd"
         ,includedTickets {
           {  referenceIA5 "UED12435867"
             ,issuerName "OEBB" 
             ,issuerPNR "PNR" 
             ,productOwnerIA5 "test" 
	     ,ticketType pass
	     ,linkMode onlyValidInCombination           
           }
        }
     } 
     ,extension {
          { extensionId "1", extensionData '82DA'H }
         ,{ extensionId "2", extensionData '83DA'H }
      }			
   }	    	     
	    	     
Encoding to the file 'data.uper' using PER UNALIGNED encoding rule...
UicRailTicketData SEQUENCE [root fieldcount (not encoded) = 5]
  issuingDetail IssuingData SEQUENCE [root fieldcount (not encoded) = 8]
    issuingYear INTEGER [length (not encoded) = 1.0]
      2018
    issuingDay INTEGER [length (not encoded) = 1.1]
      1
    issuingTime INTEGER [length (not encoded) = 1.3]
      600
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
  transportDocument SEQUENCE OF [count = 1]
    DocumentData SEQUENCE [root fieldcount (not encoded) = 1]
      ticket CHOICE [index = 6]
        counterMark CountermarkData SEQUENCE [root fieldcount (not encoded) = 7]
          numberOfCountermark INTEGER [length (not encoded) = 1.0]
            12
          totalOfCountermarks INTEGER [length (not encoded) = 1.0]
            24
          groupName UTF8String [length = 9.0]
            0x67726f75704e616d65
          validRegion SEQUENCE OF [count = 2]
            RegionalValidityType CHOICE [index = 1]
              viaStations ViaStationType SEQUENCE [root fieldcount (not encoded) = 3]
                route SEQUENCE OF [count = 4]
                  ViaStationType SEQUENCE [root fieldcount (not encoded) = 2]
                    stationNum INTEGER [length (not encoded) = 3.0]
                      123455
                    border BOOLEAN [length (not encoded) = 0.1]
                      FALSE
                  ViaStationType SEQUENCE [root fieldcount (not encoded) = 2]
                    stationNum INTEGER [length (not encoded) = 3.0]
                      123456
                    border BOOLEAN [length (not encoded) = 0.1]
                      FALSE
                  ViaStationType SEQUENCE [root fieldcount (not encoded) = 2]
                    alternativeRoutes SEQUENCE OF [count = 2]
                      ViaStationType SEQUENCE [root fieldcount (not encoded) = 2]
                        route SEQUENCE OF [count = 2]
                          ViaStationType SEQUENCE [root fieldcount (not encoded) = 2]
                            stationNum INTEGER [length (not encoded) = 3.0]
                              23455
                            border BOOLEAN [length (not encoded) = 0.1]
                              FALSE
                          ViaStationType SEQUENCE [root fieldcount (not encoded) = 2]
                            stationNum INTEGER [length (not encoded) = 3.0]
                              23456
                            border BOOLEAN [length (not encoded) = 0.1]
                              FALSE
                        border BOOLEAN [length (not encoded) = 0.1]
                          FALSE
                      ViaStationType SEQUENCE [root fieldcount (not encoded) = 2]
                        route SEQUENCE OF [count = 2]
                          ViaStationType SEQUENCE [root fieldcount (not encoded) = 2]
                            stationNum INTEGER [length (not encoded) = 3.0]
                              3455
                            border BOOLEAN [length (not encoded) = 0.1]
                              FALSE
                          ViaStationType SEQUENCE [root fieldcount (not encoded) = 2]
                            stationNum INTEGER [length (not encoded) = 3.0]
                              3456
                            border BOOLEAN [length (not encoded) = 0.1]
                              FALSE
                        border BOOLEAN [length (not encoded) = 0.1]
                          FALSE
                    border BOOLEAN [length (not encoded) = 0.1]
                      FALSE
                  ViaStationType SEQUENCE [root fieldcount (not encoded) = 2]
                    stationNum INTEGER [length (not encoded) = 3.0]
                      123457
                    border BOOLEAN [length (not encoded) = 0.1]
                      FALSE
                border BOOLEAN [length (not encoded) = 0.1]
                  FALSE
                seriesId INTEGER [length = 2.0]
                  999
            RegionalValidityType CHOICE [index = 2]
              zones ZoneType SEQUENCE [root fieldcount (not encoded) = 1]
                zoneId SEQUENCE OF [count = 2]
                  INTEGER [length = 1.0]
                    100
                  INTEGER [length = 2.0]
                    200
          returnIncluded BOOLEAN [length (not encoded) = 0.1]
            FALSE
          classCode TravelClassType ENUMERATED [length (not encoded) = 0.3]
            1
          infoText UTF8String [length = 11.0]
            0x636f756e7465724d61726b
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
      TicketLinkType SEQUENCE [root fieldcount (not encoded) = 6]
        referenceIA5 IA5String [length = 11.0]
          "UED12435867"
        issuerName UTF8String [length = 4.0]
          0x4f454242
        issuerPNR IA5String [length = 3.0]
          "PNR"
        productOwnerIA5 IA5String [length = 4.0]
          "test"
        ticketType TicketType ENUMERATED [length (not encoded) = 0.2]
          1
        linkMode LinkMode ENUMERATED [length (not encoded) = 0.1]
          1
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
Total encoded length = 184.1
Encoded successfully in 185 bytes:
78222020 0258A1BA 79F3EB97 954CBCFA 509D4804 31405A00 1044A6F6 86E03446
F770562C 99B46B01 106E7977 69DFC81D B5E51DC9 BDD5C004 30000202 1058B84B
3B937BAB 82730B6B 28108240 84000F11 F08001E2 3F040040 80110000 B73C2000
16E7C040 0880000D 7E100001 AFE08001 E2400080 F9C80100 80590080 320216C6
DEEADCE8 CAE49AC2 E4D69804 0100B200 04C6C802 B70BAB16 23164D19 B570D9B8
227A2A12 101D09D4 813A65E7 D0A0402C 40A0B680 590141ED 00
	    	     
		*/
		

		
		public static String getEncodingHex() {
			return  	
			"782220200258A1BA79F3EB97954CBCFA509D480431405A001044A6F686E03446" + 
			"F770562C99B46B01106E797769DFC81DB5E51DC9BDD5C004300002021058B84B" + 
			"3B937BAB82730B6B2810824084000F11F08001E23F04004080110000B73C2000" + 
			"16E7C0400880000D7E100001AFE08001E2400080F9C8010080590080320216C6" + 
			"DEEADCE8CAE49AC2E4D698040100B20004C6C802B70BAB1623164D19B570D9B8" + 
			"227A2A12101D09D4813A65E7D0A0402C40A0B680590141ED00";
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

	    	
	    	//OpenTicket
	    	DocumentData do1 = new DocumentData();
	    	addCounterMark(do1);
	    	ds.add(do1);   	    		    
	    	ticket.setTransportDocument(ds);
	    	
	       	SequenceOfExtensionData ed = new SequenceOfExtensionData();
	    	populateExtensionSequence(ed);
	    	ticket.setExtension(ed);
	    	
		}


		private static void addCounterMark(DocumentData dd) {
		
			TicketDetailData tdd = new TicketDetailData();
	    	CountermarkData otd = new CountermarkData();  
	    	otd.setInfoText("counterMark");
	    	otd.setClassCode(TravelClassType.first);
	    	otd.setReturnIncluded(false);
	    	
	    	otd.setNumberOfCountermark(12L);
	    	otd.setTotalOfCountermarks(24L);;
	    	otd.setGroupName("groupName");
	    	
	    	otd.setValidRegion(getValidRegion());
	    		    		    	
	    	tdd.setCounterMark(otd);
	    	dd.setTicket(tdd);
	    	
		}
		    
	    private static SequenceOfRegionalValidityType getValidRegion() {
	    	SequenceOfRegionalValidityType sr = new SequenceOfRegionalValidityType();
			sr.add(getRegionalValidity());
			sr.add(getZone());
			return sr;
		}

		private static RegionalValidityType getRegionalValidity() {
	    	RegionalValidityType r = new RegionalValidityType();
	    	r.setViaStations(getRoute());
			return r;
		}

		private static ViaStationType getRoute() {
			ViaStationType m = new ViaStationType();
			m.setRoute(getMainRoute());
			m.setSeriesId(999L);
			return m;
		}
		
		

		private static ViaStationType getAlternativeRoute() {
			ViaStationType s = new ViaStationType();
			s.setAlternativeRoutes(new SequenceOfViaStationType());
			s.getAlternativeRoutes().add(getAltRoute1());
			s.getAlternativeRoutes().add(getAltRoute2());
			return s;
		}

		/*
		 *       { route {  {stationNum 23455, border FALSE},{stationNum 23456, border FALSE }}, border FALSE  }
		 */
		private static ViaStationType getAltRoute1() {
			ViaStationType m = new ViaStationType();
			m.setRoute(new SequenceOfViaStationType());
			m.getRoute().add(getViaStation(23455L));
			m.getRoute().add(getViaStation(23456L));
			return m;
		}

		/*  
		 *  { route {  {stationNum 3455, border FALSE },{stationNum 3456, border FALSE }},  border FALSE  }
		 */


		private static ViaStationType getAltRoute2() {
			ViaStationType m = new ViaStationType();
			m.setRoute(new SequenceOfViaStationType());
			m.getRoute().add(getViaStation(3455L));
			m.getRoute().add(getViaStation(3456L));
			return m;
		}


		private static ViaStationType getViaStation(long i) {
			ViaStationType s = new ViaStationType();
			s.setStationNum(i);
			return s;
		}

		private static SequenceOfViaStationType getMainRoute() {
			SequenceOfViaStationType s = new SequenceOfViaStationType();
			s.add(getViaStation(123455L));
			s.add(getViaStation(123456L));
			s.add(getAlternativeRoute());
			s.add(getViaStation(123457L));
			return s;
		}

		private static RegionalValidityType getZone() {
	    	RegionalValidityType r = new RegionalValidityType();
	    	ZoneType z = new ZoneType();
	    	z.setZoneId(new SequenceOfUnrestrictedLong());
	    	z.getZoneId().add(100L);
	    	z.getZoneId().add(200L);
	    	r.setZones(z);
			return r;
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
			tlt.setTicketType(TicketType.pass);
			tlt.setIssuerPNR("PNR");
			tlt.setReferenceIA5("UED12435867");
			tlt.setLinkMode(LinkMode.onlyValidInCombination);
			tlt.setIssuerName("OEBB");
			sequenceOfTicketLinkType.add(tlt);
		}

		private static CardReferenceType populateCardRefrence() {
			CardReferenceType cr = new CardReferenceType();
			cr.setTrailingCardIdNum(100L);
			return cr;
		}

	}
