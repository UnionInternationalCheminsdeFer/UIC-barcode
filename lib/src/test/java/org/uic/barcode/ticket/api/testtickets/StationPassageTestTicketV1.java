package org.uic.barcode.ticket.api.testtickets;

import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringUTF8;
import org.uic.barcode.ticket.api.asn.omv1.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv1.ControlData;
import org.uic.barcode.ticket.api.asn.omv1.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv1.DocumentData;
import org.uic.barcode.ticket.api.asn.omv1.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv1.IssuingData;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfTicketLinkType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv1.StationPassageData;
import org.uic.barcode.ticket.api.asn.omv1.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv1.TicketLinkType;
import org.uic.barcode.ticket.api.asn.omv1.TravelerData;
import org.uic.barcode.ticket.api.asn.omv1.TravelerType;
import org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData;

public class StationPassageTestTicketV1 {
	
	
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
