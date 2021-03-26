package org.uic.barcode.ticket.api.testtickets;

import org.uic.barcode.ticket.api.asn.omv2.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv2.ControlData;
import org.uic.barcode.ticket.api.asn.omv2.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv2.DocumentData;
import org.uic.barcode.ticket.api.asn.omv2.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv2.IssuingData;
import org.uic.barcode.ticket.api.asn.omv2.PassengerType;
import org.uic.barcode.ticket.api.asn.omv2.PlacesType;
import org.uic.barcode.ticket.api.asn.omv2.PriceTypeType;
import org.uic.barcode.ticket.api.asn.omv2.ReservationData;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfCarrierNum;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfTariffType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv2.ServiceType;
import org.uic.barcode.ticket.api.asn.omv2.TariffType;
import org.uic.barcode.ticket.api.asn.omv2.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv2.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv2.TravelerData;
import org.uic.barcode.ticket.api.asn.omv2.TravelerType;
import org.uic.barcode.ticket.api.asn.omv2.UicRailTicketData;


	public class ReservationTestTicketV2 {
		
		public static UicRailTicketData getUicTestTicket() {
			UicRailTicketData ticket = new UicRailTicketData();
	    	populateTicket(ticket);
			return ticket;
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
	    	ReservationData ticket = new ReservationData();  
	    	ticket.setTrainNum(123L);
	    	ticket.setService(ServiceType.seat);
	    	ticket.setReferenceNum(810123456789L);
	    	ticket.setServiceBrandAbrUTF8("XYZ");
	    	ticket.setServiceBrandNameUTF8("special train");
	    	SequenceOfCarrierNum carriers = new SequenceOfCarrierNum();
	    	carriers.add(1080L);
	    	carriers.add(1181L);
	    	ticket.setCarrierNum(carriers);
	    	ticket.setFromStationNum(8100001L);
	    	ticket.setToStationNum(800001L);
	    	ticket.setNumberOfSupplements(1L);
	    	ticket.setServiceBrand(100L);
	    	ticket.setPrice(12345L);
	    	ticket.setPriceType(PriceTypeType.supplement);
	    	
	    	ticket.setPlaces(getPlaces());
	    	ticket.setTariff(getTariffs());

	    	ticket.setInfoText("reservation");
	    	ticket.setClassCode(TravelClassType.first);
	    	ticket.setDepartureDate(10L);
	    	ticket.setDepartureTime(0L);
	    	ticket.setArrivalDate(10L);
	    	ticket.setArrivalTime(1439L);
	    	tdd.setReservation(ticket);
	    	dd.setTicket(tdd);
		}


    
	    private static SequenceOfTariffType getTariffs() {
	    	SequenceOfTariffType tariffs = new SequenceOfTariffType();
	    	
	    	TariffType tariff = new TariffType();
	    	tariff.setNumberOfPassengers(1L);
	    	tariff.setRestrictedToCountryOfResidence(false);
	    	tariff.setPassengerType(PassengerType.adult);
	    	tariff.setTariffIdNum(72L);
	    	tariff.setTariffDesc("Full Fare Adult");
	    	tariffs.add(tariff);
	    	TariffType tariff2 = new TariffType();
	    	tariff2.setRestrictedToCountryOfResidence(false);
	    	tariff2.setNumberOfPassengers(2L);
	    	tariff2.setPassengerType(PassengerType.child);
	    	tariff2.setTariffIdNum(73L);
	    	tariff2.setTariffDesc("Full Fare Child");
	    	tariffs.add(tariff2);	    	
			return tariffs;
		}


		private static PlacesType getPlaces() {
			PlacesType places = new PlacesType();
			places.setPlaceDescription("11-13");
			places.setCoach("12");
			return places;
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



	}
