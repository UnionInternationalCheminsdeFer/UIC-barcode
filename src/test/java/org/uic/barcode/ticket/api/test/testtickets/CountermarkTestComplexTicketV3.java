package org.uic.barcode.ticket.api.test.testtickets;

import org.uic.barcode.asn1.datatypesimpl.SequenceOfUnrestrictedLong;
import org.uic.barcode.ticket.api.asn.omv3.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.ControlData;
import org.uic.barcode.ticket.api.asn.omv3.CountermarkData;
import org.uic.barcode.ticket.api.asn.omv3.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.DocumentData;
import org.uic.barcode.ticket.api.asn.omv3.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.IssuingData;
import org.uic.barcode.ticket.api.asn.omv3.LinkMode;
import org.uic.barcode.ticket.api.asn.omv3.RegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfRegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTicketLinkType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfViaStationType;
import org.uic.barcode.ticket.api.asn.omv3.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv3.TicketLinkType;
import org.uic.barcode.ticket.api.asn.omv3.TicketType;
import org.uic.barcode.ticket.api.asn.omv3.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv3.TravelerData;
import org.uic.barcode.ticket.api.asn.omv3.TravelerType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.asn.omv3.ViaStationType;
import org.uic.barcode.ticket.api.asn.omv3.ZoneType;


	public class CountermarkTestComplexTicketV3 {
		
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
	    	     
		*/
		
	
		public static String getEncodingHex() {
			return  	
			"7804404004B14374F3E7D72F2A9979F4A13A90086280B4001044A6F686E03446" + 
			"F770562C99B46B01106E797769DFC81DB5E51DC9BDD5C004300002021058B84B" + 
			"3B937BAB82730B6B28108240210000F11F08000788FC100040800440000B73C2" + 
			"00005B9F0100088000035F8400001AFE08000789000203E720040201640200C8" + 
			"042D8DBDD5B9D195C93585C9AD300802016400098D90056E17562C462C9A336A" + 
			"E1B37044F45424203A13A90274CBCFA140805881416D00B20283DA";
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
	    	otd.setTotalOfCountermarks(24L);
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

		/*
		 * issuingDetail {
           issuingYear       2018
           issuingDay        1
           issuingTime       600
           specimen          TRUE,
           securePaperTicket FALSE,
           activated         TRUE,
           issuerPNR         "issuerTestPNR",
           issuedOnLine      12
		 */
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

	    /*
	     *extension {
          { extensionId "1", extensionData '82DA'H }
         ,{ extensionId "2", extensionData '83DA'H }
      }	
	     */
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
	     *controlDetail {
           identificationByCardReference {{ trailingCardIdNum 100 }  }
 	      ,identificationByIdCard	    FALSE
  	      ,identificationByPassportId   FALSE
          ,passportValidationRequired  	FALSE
    	  ,onlineValidationRequired    	FALSE
          ,ageCheckRequired            	FALSE   	
  	      ,reductionCardCheckRequired  	FALSE          
          ,infoText                     "cd"
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
		 * includedTickets {
           {  referenceIA5 "UED12435867"
             ,issuerName "OEBB" 
             ,issuerPNR "PNR" 
             ,productOwnerIA5 "test" 
	         ,ticketType pass
	         ,linkMode onlyValidInCombination           
           }
		 */
		private static void populateLinkedTickets(SequenceOfTicketLinkType sequenceOfTicketLinkType) {
			TicketLinkType tlt = new TicketLinkType();
			tlt.setProductOwnerIA5("test");
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
