package org.uic.barcode.ticket.api.test.testtickets;

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
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv3.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv3.TravelerData;
import org.uic.barcode.ticket.api.asn.omv3.TravelerType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.asn.omv3.VoucherData;

	public class VoucherTestTicketV3 {
		
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
	    	
	    	DocumentData do1 = new DocumentData();
	    	addTicket(do1);    	
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
	       	issuingDetail.setIssuingTime(600L);
	    	issuingDetail.setIssuerPNR("issuerTestPNR");
	    	issuingDetail.setSpecimen(true);
	    	issuingDetail.setSecurePaperTicket(false);
	    	issuingDetail.setActivated(true);
	    	issuingDetail.setIssuedOnLine(12L);	
		}
	    
		private static void addTicket(DocumentData dd) {		
			
	    	TicketDetailData tdd = new TicketDetailData();
	    	VoucherData ticket = new VoucherData();  
	    	ticket.setInfoText("coffee voucher");
	    	ticket.setValidFromYear(2022L);	    	
	    	ticket.setValidFromDay(01L);
	    	ticket.setValidUntilYear(2022L);	    	
	    	ticket.setValidUntilDay(01L);
	    	ticket.setReferenceIA5("ACHE12345");
	    	ticket.setProductOwnerIA5("COFFEEMACHINE");
	    	ticket.setValue(500L);
	    	tdd.setVoucher(ticket);
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
