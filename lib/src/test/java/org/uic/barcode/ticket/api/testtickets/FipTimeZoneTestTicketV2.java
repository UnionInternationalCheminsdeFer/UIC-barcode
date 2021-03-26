package org.uic.barcode.ticket.api.testtickets;

import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.ticket.api.asn.omv2.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv2.ControlData;
import org.uic.barcode.ticket.api.asn.omv2.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv2.DocumentData;
import org.uic.barcode.ticket.api.asn.omv2.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv2.FIPTicketData;
import org.uic.barcode.ticket.api.asn.omv2.IssuingData;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfActivatedDays;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfCarrierNum;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv2.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv2.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv2.TravelerData;
import org.uic.barcode.ticket.api.asn.omv2.TravelerType;
import org.uic.barcode.ticket.api.asn.omv2.UicRailTicketData;

	public class FipTimeZoneTestTicketV2 {
		
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

	    	
	    	//FipTicket
	    	DocumentData do1 = new DocumentData();
	    	addFip(do1);    	
	    	ds.add(do1);   	    	
	    	 	    	
	    	
	    	ticket.setTransportDocument(ds);
	    	
	       	SequenceOfExtensionData ed = new SequenceOfExtensionData();
	    	populateExtensionSequence(ed);
	    	ticket.setExtension(ed);
	    	
		}
	    

	    //issue date: 04-03-2021 12:30 UTC
	    private static void populateIssuingData(IssuingData issuingDetail) {
	    	issuingDetail.setIssuingYear(2021L);
	    	issuingDetail.setIssuingTime(750L);
	    	issuingDetail.setIssuingDay(63L);
	    	issuingDetail.setIssuerPNR("issuerTestPNR");
	    	issuingDetail.setSpecimen(true);
	    	issuingDetail.setSecurePaperTicket(false);
	    	issuingDetail.setActivated(true);
	    	issuingDetail.setIssuedOnLine(12L);	
		}
	    
		private static void addFip(DocumentData dd) {		
			
	    	TicketDetailData tdd = new TicketDetailData();
	    	FIPTicketData otd = new FIPTicketData();  
	    	otd.setClassCode(TravelClassType.first);
	    	otd.setNumberOfTravelDays(8L);
	    	otd.setValidFromDay(10L);
	    	otd.setValidUntilDay(10L);
	    	SequenceOfCarrierNum carriers = new SequenceOfCarrierNum();
	    	carriers.add(1080L);
	    	carriers.add(1181L);
	    	otd.setCarrierNum(carriers);
	    	otd.setIncludesSupplements(true);
	    	otd.setReferenceNum(Asn1BigInteger.toAsn1(123445));
	    	SequenceOfActivatedDays s = new SequenceOfActivatedDays(); 
	    	s.add(0L);
	    	otd.setActivatedDay(s);

	    	tdd.setFipTicket(otd);
	    	dd.setTicket(tdd);
		}


	    
	    private static void populateTravelerData(TravelerData td) {
	    	SequenceOfTravelerType trs = new SequenceOfTravelerType();
	    	TravelerType tr = new TravelerType();
	    	tr.setIdCard("12345");
	    	tr.setFirstName("John");
	    	tr.setSecondName("Dow");
	    	tr.setTicketHolder(true);
	    	SequenceOfCustomerStatusType ts = new SequenceOfCustomerStatusType();  	
	    	CustomerStatusType cst = new CustomerStatusType();
	    	cst.setCustomerStatusDescr("employee");
	    	ts.add(cst);
	    	tr.setStatus(ts);
	    	trs.add(tr);
	    	td.setTraveler(trs);
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


		private static CardReferenceType populateCardRefrence() {
			CardReferenceType cr = new CardReferenceType();
			cr.setTrailingCardIdNum(100L);
			return cr;
		}



	}
