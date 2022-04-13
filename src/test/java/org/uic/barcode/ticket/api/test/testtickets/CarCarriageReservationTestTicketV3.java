package org.uic.barcode.ticket.api.test.testtickets;

import org.uic.barcode.ticket.api.asn.omv3.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.ControlData;
import org.uic.barcode.ticket.api.asn.omv3.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.DocumentData;
import org.uic.barcode.ticket.api.asn.omv3.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.IssuingData;
import org.uic.barcode.ticket.api.asn.omv3.LoadingDeckType;
import org.uic.barcode.ticket.api.asn.omv3.PriceTypeType;
import org.uic.barcode.ticket.api.asn.omv3.RoofRackType;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.api.asn.omv3.CarCarriageReservationData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCarrierNum;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfVatDetail;
import org.uic.barcode.ticket.api.asn.omv3.TariffType;
import org.uic.barcode.ticket.api.asn.omv3.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv3.TravelerData;
import org.uic.barcode.ticket.api.asn.omv3.TravelerType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.asn.omv3.VatDetailType;


	public class CarCarriageReservationTestTicketV3 {
		
		/*
		 * rec1value UicRailTicketData ::= {
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
      ticket carCarriageReservation : {
        trainNum 123,
        beginLoadingDate 10,
        beginLoadingTime 0,
        endLoadingTime 500,
        referenceNum 810123456789,
        serviceBrand 100,
        serviceBrandAbrUTF8 "AZ",
        serviceBrandNameUTF8 "special train",
        stationCodeTable stationUICReservation,
        fromStationNum 8100001,
        toStationNum 800001,
        coach "21",
        place "41",
        numberPlate "AD-DE-123",
        trailerPlate "DX-AB-123",
        carCategory 3,
        textileRoof FALSE,
        roofRackType bicycleRack,
        roofRackHeight 20,
        attachedBicycles 1,
        attachedSurfboards 2,
        loadingListEntry 421,
        loadingDeck upper,
        carrierNum {
          1080,
          1181
        },
        tariff {
          numberOfPassengers 1,
          restrictedToCountryOfResidence FALSE,
          tariffIdNum 72,
          tariffDesc "Large Car Full Fare"
        },
        priceType travelPrice,
        price 12345,
        vatDetail {
          {
            country 80,
            percentage 70,
            amount 10,
            vatId "IUDGTE"
          }
        },
        infoText "car carriage"
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
    infoText "cd"
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
		
			return "7804404004B14374F3E7D72F2A9979F4A13A90086280B4001"
					+ "044A6F686E03446F770562C99B46B01106E797769DFC81"
					+ "DB5E51DC9BDD5C0040AE43A8D6E9C02F60B0007D01802F"
					+ "27C7BC4540318120AD06B9B832B1B4B0B6103A3930B4B7"
					+ "3DCC50061A8001326204D1884C188B62455AC593309896"
					+ "16C184B58B266639429A502086E127002802902698C2E4"
					+ "CECA4086C2E4408CEAD8D8408CC2E4CA0460720389E230"
					+ "0850349AB123D48A18C6C2E440C6C2E4E4D2C2CECA9004"
					+ "0100B20004C6C80402C40A0B680590141ED00";
			
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
	    	addReservation(do1);
	    	ds.add(do1);   	    	
	    	   	
	    	
	    	ticket.setTransportDocument(ds);
	    	
	       	SequenceOfExtensionData ed = new SequenceOfExtensionData();
	    	populateExtensionSequence(ed);
	    	ticket.setExtension(ed);
	    	
		}
	    
	    
	    
		private static void addReservation(DocumentData dd) {
		
	    	TicketDetailData tdd = new TicketDetailData();
	    	CarCarriageReservationData ticket = new CarCarriageReservationData();  
	    	ticket.setTrainNum(123L);
	    	ticket.setReferenceNum(810123456789L);
	    	ticket.setServiceBrandAbrUTF8("XYZ");
	    	ticket.setServiceBrandNameUTF8("special train");
	    	SequenceOfCarrierNum carriers = new SequenceOfCarrierNum();
	    	carriers.add(1080L);
	    	carriers.add(1181L);
	    	ticket.setCarrierNum(carriers);
	    	ticket.setFromStationNum(8100001L);
	    	ticket.setToStationNum(800001L);
	    	ticket.setServiceBrand(100L);
	    	ticket.setPrice(12345L);
	    	ticket.setPriceType(PriceTypeType.travelPrice);
	    	ticket.setAttachedBicycles(1L);
	    	ticket.setCarCategory(3L);
	    	ticket.setInfoText("car carriage");
	    	ticket.setLoadingDeck(LoadingDeckType.upper);
	    	ticket.setNumberPlate("AD-DE-123");
	    	ticket.setRoofRackHeight(20L);
	    	ticket.setRoofRackType(RoofRackType.bicycleRack);
	    	ticket.setServiceBrandAbrUTF8("AZ");
	    	ticket.setServiceBrand(100L);
	    	ticket.setTextileRoof(false);
	    	ticket.setTrailerPlate("DX-AB-123");
	    	ticket.setAttachedSurfboards(2L);
	    	ticket.setLoadingListEntry(421L);
	    	
	    	ticket.setVatDetails(new SequenceOfVatDetail());
	    	ticket.getVatDetails().add(getVatDetail());
	    		    	
	    	ticket.setTariff(getTariff());
	    	
	    	ticket.setPlace("41");
	    	ticket.setCoach("21");
	    	ticket.setBeginLoadingDate(10L);
	    	ticket.setBeginLoadingTime(0L);
	    	ticket.setEndLoadingTime(500L);
	    	tdd.setCarCarriageReservation(ticket);
	    	dd.setTicket(tdd);
		}


    
	    private static TariffType getTariff() {
	        	
	    	TariffType tariff = new TariffType();
	    	tariff.setNumberOfPassengers(1L);
	    	tariff.setRestrictedToCountryOfResidence(false);
	    	tariff.setTariffIdNum(72L);
	    	tariff.setTariffDesc("Large Car Full Fare");
	      	
			return tariff;
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

		public static byte[] getEncodingBytes() {
			return UperEncoder.bytesFromHexString(getEncodingHex());
		}



	}
