package org.uic.barcode.ticket.api.test.testtickets;

import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringUTF8;
import org.uic.barcode.ticket.api.asn.omv3.BoardingOrArrivalType;
import org.uic.barcode.ticket.api.asn.omv3.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.ControlData;
import org.uic.barcode.ticket.api.asn.omv3.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.DocumentData;
import org.uic.barcode.ticket.api.asn.omv3.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.IssuingData;
import org.uic.barcode.ticket.api.asn.omv3.LinkMode;
import org.uic.barcode.ticket.api.asn.omv3.PassData;
import org.uic.barcode.ticket.api.asn.omv3.PassengerType;
import org.uic.barcode.ticket.api.asn.omv3.RouteSectionType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfActivatedDays;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCarrierNum;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCountries;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTicketLinkType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfVatDetail;
import org.uic.barcode.ticket.api.asn.omv3.StationPassageData;
import org.uic.barcode.ticket.api.asn.omv3.TariffType;
import org.uic.barcode.ticket.api.asn.omv3.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv3.TicketLinkType;
import org.uic.barcode.ticket.api.asn.omv3.TicketType;
import org.uic.barcode.ticket.api.asn.omv3.TokenType;
import org.uic.barcode.ticket.api.asn.omv3.TrainValidityType;
import org.uic.barcode.ticket.api.asn.omv3.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv3.TravelerData;
import org.uic.barcode.ticket.api.asn.omv3.TravelerType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.asn.omv3.VatDetailType;


	public class PassComplexTicketV3 {
		
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
	    		         	 token {tokenProviderIA5 "XYZ", token '82DA'H }
	    		        	,ticket pass : {
	    		        	       referenceNum      123456789
	    		        		  ,productOwnerNum   4567
	    		        		  ,passType          2
	    		        		  ,passDescription   "Eurail FlexPass"
	    	        			  ,classCode         first
	    	        			  ,validFromDay 0
	    	        			  ,validFromTime 1000
	    	        			  ,validUntilDay 1
	    	        			  ,validUntilTime 1000
                                  ,trainValidity {
  	    	        			  	,validFromDay 0
	    	        			  	,validFromTime 1000
	    	        			  	,validUntilDay 1
	    	        			  	,validUntilTime 1000
                                    ,includedCarrierNum {1234, 5678}
                                    ,boardingOrArrival boarding
                                  }
                                  ,numberOfDaysOfTravel 10
                                  ,activatedDay {200, 201}
                                  ,countries {10, 20}
                                  ,price 10000
	    	        			  ,vatDetail {
	    	        			     { country    80
	    	        			      ,percentage 70
	    	        			      ,amount     10
	    	        			      ,vatId      "IUDGTE"
	    	        			     }
	    	        			   } 
	    	        		   	  ,infoText          "pass info"
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
				"F770562C99B46B01106E797769DFC81DB5E51DC9BDD5C00940762CDA0282DA1A" + 
				"8EB1700E04075BCD1523AC021E8AEAE4C2D2D8408CD8CAF0A0C2E6E617D0027D" + 
				"05A03E8013E80209A258B4240990C902091302271001C4F11804281A4D5891EA" + 
				"45097061737320696E666F120220103B830B9B9B0B3B28084A0B6B9BA32B9323" + 
				"0B696F017B4C0200805900026364015B85D58B118B268CDAB86CDC113D150908" + 
				"0E84EA409D32F3E850201620505B402C80A0F680";
			
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
	    	addPassData(do1);
	    	ds.add(do1);   	    	
	    	
	    	//StationPassage
	    	DocumentData do2 = new DocumentData();    	
	    	addStationPassage(do2);
	    	ds.add(do2);   	    	
	    	
	    	ticket.setTransportDocument(ds);
	    	
	       	SequenceOfExtensionData ed = new SequenceOfExtensionData();
	    	populateExtensionSequence(ed);
	    	ticket.setExtension(ed);
	    	
		}

	    private static void addStationPassage(DocumentData dd) {
	    	TicketDetailData tdd = new TicketDetailData();
	    	StationPassageData sp = new StationPassageData();  
	    	sp.setProductName("passage");
	    	sp.setValidFromDay(0L);
	    	sp.setNumberOfDaysValid(123L);
	    	SequenceOfStringUTF8 ss = new SequenceOfStringUTF8();
	    	ss.add("Amsterdam");
	    	sp.setStationNameUTF8(ss);   	
	     	tdd.setStationPassage(sp);    	
	    	dd.setTicket(tdd);
		}

	    /*
	     * 
	    		         	 token {tokenProviderIA5 "XYZ", token '82DA'H }
	    		        	,ticket pass : {
	    		        	       referenceNum      123456789
	    		        		  ,productOwnerNum   4567
	    		        		  ,passType          2
	    		        		  ,passDescription   "Eurail FlexPass"
	    	        			  ,classCode         first
	    	        			  ,validFromDay 0
	    	        			  ,validFromTime 1000
	    	        			  ,validUntilDay 1
	    	        			  ,validUntilTime 1000
                                  ,trainValidity {
  	    	        			  	,validFromDay 0
	    	        			  	,validFromTime 1000
	    	        			  	,validUntilDay 1
	    	        			  	,validUntilTime 1000
                                    ,includedCarrierNum {1234, 5678}
                                    ,boardingOrArrival boarding
                                  }
                                  ,numberOfDaysOfTravel 10
                                  ,activatedDay {200, 201}
                                  ,countries {10, 20}
                                  ,price 10000
	    	        			  ,vatDetail {
	    	        			     { country    80
	    	        			      ,percentage 70
	    	        			      ,amount     10
	    	        			      ,vatId      "IUDGTE"
	    	        			     }
	    	        			   } 
	    	        		   	  ,infoText          "pass info"
         *
	     */
		private static void addPassData(DocumentData dd) {
	    	TokenType to = new TokenType();
	    	to.setTokenProviderIA5("XYZ");
	    	byte[] ba = { (byte) 0x82, (byte) 0xDA };
	    	to.setToken(ba);
	    	dd.setToken(to);   		
			
	    	TicketDetailData tdd = new TicketDetailData();
	    	PassData otd = new PassData();  
	    	
	    	otd.setReferenceNum(Asn1BigInteger.toAsn1(123456789L));
	    	otd.setProductOwnerNum(4567L);
	    	otd.setPassDescription("Eurail FlexPass");

	    	otd.setInfoText("pass info");
	    	otd.setClassCode(TravelClassType.first);
	    	
			otd.setValidFromDay(0L); 
			otd.setValidFromTime(1000L);
			otd.setValidUntilDay(1L);
			otd.setValidUntilTime(1000L);
			
			SequenceOfCountries countries = new SequenceOfCountries();
			countries.add(10L);
			countries.add(20L);
			otd.setCountries(countries);
			
			otd.setTrainValidity(getTrainValidity());
			SequenceOfActivatedDays activatedDays = new SequenceOfActivatedDays();
			activatedDays.add(200L);
			activatedDays.add(201L);
			otd.setActivatedDay(activatedDays);

	    	otd.setNumberOfDaysOfTravel(10L);
	    	
	    	otd.setPrice(10000L);
	    	otd.setVatDetails(new SequenceOfVatDetail());
	    	otd.getVatDetails().add(getVatDetail());
	    		    	
	    	tdd.setPass(otd);
	    	dd.setTicket(tdd);
	    	
		}
		
		private static TrainValidityType getTrainValidity() {
			TrainValidityType t = new TrainValidityType();
			t.setValidFromDay(0L); 
			t.setValidFromTime(1000L);
			t.setValidUntilDay(1L);
			t.setValidUntilTime(1000L);
			SequenceOfCarrierNum carriers = new SequenceOfCarrierNum();
			carriers.add(1234L);
			carriers.add(5678L);
			t.setIncludedCarriersNum(carriers);

			t.setBordingOrArrival(BoardingOrArrivalType.boarding);
			
	    	return t;
		}
		
	
		
		private static VatDetailType getVatDetail() {
			VatDetailType v = new VatDetailType();
			v.setAmount(10L);
			v.setCountry(80L);
			v.setPercentage(70L);
			v.setVatId("IUDGTE");
			return v;
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
 * 	    	       issuingDetail {
	    	            issuingYear       2018
	    	            issuingDay        1
	    	       	    specimen          TRUE,
	    	            securePaperTicket FALSE,
	    	            activated         TRUE,
	    	            issuerPNR         "issuerTestPNR",
	    	       	    issuedOnLine      12
	    	       }
 */
	    private static void populateIssuingData(IssuingData issuingDetail) {
	    	issuingDetail.setIssuingYear(2018L);
	    	issuingDetail.setIssuingDay(1L);
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

		private static TariffType getTariff() {
			TariffType t = new TariffType();
			t.setNumberOfPassengers(2L);
			t.setPassengerType(PassengerType.adult);
			t.setRestrictedToRouteSection(getRouteSection());
			t.setRestrictedToCountryOfResidence(false);
			return t;
		}

		private static RouteSectionType getRouteSection() {
			RouteSectionType r = new RouteSectionType();
			r.setFromStationNum(8000001L);
			r.setToStationNum(8010000L);

			return r;
		}

	}
