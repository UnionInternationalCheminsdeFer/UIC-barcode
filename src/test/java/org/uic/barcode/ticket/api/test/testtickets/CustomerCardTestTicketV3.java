package org.uic.barcode.ticket.api.test.testtickets;

import org.uic.barcode.ticket.api.asn.omv3.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.ControlData;
import org.uic.barcode.ticket.api.asn.omv3.CustomerCardData;
import org.uic.barcode.ticket.api.asn.omv3.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.DocumentData;
import org.uic.barcode.ticket.api.asn.omv3.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.GenderType;
import org.uic.barcode.ticket.api.asn.omv3.IssuingData;
import org.uic.barcode.ticket.api.asn.omv3.LinkMode;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTicketLinkType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv3.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv3.TicketLinkType;
import org.uic.barcode.ticket.api.asn.omv3.TicketType;
import org.uic.barcode.ticket.api.asn.omv3.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv3.TravelerData;
import org.uic.barcode.ticket.api.asn.omv3.TravelerType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;

	public class CustomerCardTestTicketV3 {
		
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
	    	addCustomerCard(do1);    	
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
	    
		private static void addCustomerCard(DocumentData dd) {		
			
	    	TicketDetailData tdd = new TicketDetailData();
	    	CustomerCardData card = new CustomerCardData();  
	    	card.setCustomer(getTraveler());
	    	card.setClassCode(TravelClassType.first);
	    	card.setValidFromYear(2021L);
	    	card.setValidFromDay(10L);
	    	card.setValidUntilYear(1L);
	    	card.setValidUntilDay(10L);
	    	card.setCardTypeDescr("RAILPLUS");
	    	tdd.setCustomerCard(card);
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
	    
	    private static TravelerType getTraveler() {
	    	TravelerType tr = new TravelerType();
	    	tr.setIdCard("12345");
	    	tr.setFirstName("John");
	    	tr.setSecondName("Dow");
	    	tr.setYearOfBirth(1990L);
	    	tr.setMonthOfBirth(1L);
	    	tr.setDayOfBirth(21L);
	    	tr.setGender(GenderType.male);
	    	tr.setIdCard("IEDZ235FT");
	    	return tr;
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
