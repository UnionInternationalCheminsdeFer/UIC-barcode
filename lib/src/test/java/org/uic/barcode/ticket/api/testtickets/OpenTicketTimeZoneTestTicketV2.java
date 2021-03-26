package org.uic.barcode.ticket.api.testtickets;

import org.uic.barcode.ticket.api.asn.omv2.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv2.ControlData;
import org.uic.barcode.ticket.api.asn.omv2.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv2.DocumentData;
import org.uic.barcode.ticket.api.asn.omv2.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv2.IssuingData;
import org.uic.barcode.ticket.api.asn.omv2.OpenTicketData;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfActivatedDays;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv2.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv2.TokenType;
import org.uic.barcode.ticket.api.asn.omv2.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv2.TravelerData;
import org.uic.barcode.ticket.api.asn.omv2.TravelerType;
import org.uic.barcode.ticket.api.asn.omv2.UicRailTicketData;


	public class OpenTicketTimeZoneTestTicketV2 {
		
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
	    	addOpenTicketData(do1);
	    	ds.add(do1);   	    	
	    	   	
	    	
	    	ticket.setTransportDocument(ds);
	    	
	       	SequenceOfExtensionData ed = new SequenceOfExtensionData();
	    	populateExtensionSequence(ed);
	    	ticket.setExtension(ed);
	    	
		}
	    
	    
	    
		private static void addOpenTicketData(DocumentData dd) {
	    	TokenType to = new TokenType();
	    	to.setTokenProviderIA5("VDV");
	    	byte[] ba = { (byte) 0x82, (byte) 0xDA };
	    	to.setToken(ba);
	    	dd.setToken(to);   		
			
	    	TicketDetailData tdd = new TicketDetailData();
	    	OpenTicketData otd = new OpenTicketData();  
	    	otd.setInfoText("openTicketInfo");
	    	otd.setClassCode(TravelClassType.first);
	    	otd.setReturnIncluded(false);
	    	otd.setValidFromDay(10L);
	    	otd.setValidFromTime(0L);
	    	otd.setValidUntilDay(10L);
	    	otd.setValidUntilTime(1439L);
	    	SequenceOfActivatedDays s = new SequenceOfActivatedDays(); 
	    	otd.setActivatedDay(s);
	    	s.add(0L);
	    	tdd.setOpenTicket(otd);
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
