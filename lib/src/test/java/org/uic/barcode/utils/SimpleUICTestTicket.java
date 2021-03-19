package org.uic.barcode.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.uic.barcode.ticket.api.impl.SimpleCardReference;
import org.uic.barcode.ticket.api.impl.SimpleControlDetail;
import org.uic.barcode.ticket.api.impl.SimpleCustomerStatusDescription;
import org.uic.barcode.ticket.api.impl.SimpleExtension;
import org.uic.barcode.ticket.api.impl.SimpleIssuingDetail;
import org.uic.barcode.ticket.api.impl.SimpleOpenTicket;
import org.uic.barcode.ticket.api.impl.SimpleStationPassage;
import org.uic.barcode.ticket.api.impl.SimpleTicketLink;
import org.uic.barcode.ticket.api.impl.SimpleToken;
import org.uic.barcode.ticket.api.impl.SimpleTraveler;
import org.uic.barcode.ticket.api.impl.SimpleTravelerDetail;
import org.uic.barcode.ticket.api.impl.SimpleUicRailTicket;
import org.uic.barcode.ticket.api.spec.ICardReference;
import org.uic.barcode.ticket.api.spec.IControlDetail;
import org.uic.barcode.ticket.api.spec.ICustomerStatusDescription;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IIssuingDetail;
import org.uic.barcode.ticket.api.spec.ILinkMode;
import org.uic.barcode.ticket.api.spec.IOpenTicket;
import org.uic.barcode.ticket.api.spec.IStationPassage;
import org.uic.barcode.ticket.api.spec.ITicketLink;
import org.uic.barcode.ticket.api.spec.IToken;
import org.uic.barcode.ticket.api.spec.ITraveler;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public class SimpleUICTestTicket {
	
	
	public static IUicRailTicket getUicTestTicket() {
		IUicRailTicket ticket = new SimpleUicRailTicket();
    	populateTicket(ticket);
		return ticket;
	}

	
    private static void populateTicket(IUicRailTicket ticket) {
    	
    	ticket.setControlDetails(new SimpleControlDetail());
    	populate(ticket.getControlDetails());
    	
     	
    	ticket.setIssuerDetails(new SimpleIssuingDetail());
    	populateIssuingData(ticket.getIssuerDetails());
    	
    	SimpleTravelerDetail td = new SimpleTravelerDetail();
    	populateTravelerData(td);
    	ticket.setTravelerDetails(td);    	
    	
     	
    	//OpenTicket
    	IOpenTicket do1 = new SimpleOpenTicket();
    	populate(do1);
    	ticket.addOpenTicket(do1);
    	
    	//StationPassage
    	IStationPassage do2 = new SimpleStationPassage();   
    	populateStationPassage(do2);
    	ticket.addStationPassage(do2);
    	
    	//token
    	IToken to = new SimpleToken();
    	to.setTokenProvider("VDV");
    	byte[] ba = { (byte) 0x82, (byte) 0xDA };
    	to.setToken(ba);

    	
    	ticket.addExtension(populateExtension());
    	
	}
    
    /*
	 ticket stationPassage : {
       	productName "passage"
       	,station {8312345}
       	,stationNameUTF8 { "Amsterdam" }       
       	,validFromDay 0
       	,validUntilDay 4
 	 }
     */
    private static void populateStationPassage(IStationPassage sp) {
    	sp.setProductName("passage");
    	
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2018");
			sp.setValidFrom(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse("04/01/2018");
			sp.setValidUntil(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}    	
    	
    	sp.addStation("8312345");
    	sp.addStationName("Amsterdam");
	
	}
    
    /*
     	{
	        token {tokenProviderIA5 "VDV", token '82DA'H }
	        ,ticket openTicket : {
	        	returnIncluded FALSE
        		infoText "openTicketInfo"
	        }
	    }
     */
    
	private static void populate(IOpenTicket otd) {
    	otd.setInfoText("openTicketInfo");
    	otd.setReturnIncluded(false);
	}


	/*
       ,travelerDetail{
            traveler {
               {
                	firstName "John"
          	     	,secondName "Dow"
                	,idCard "12345"
                	,ticketHolder TRUE
          			,status {{customerStatusDescr "senior"  }}
               }
            }
           ,groupName "myGroup"
       }
     */
    
    private static void populateTravelerData(SimpleTravelerDetail td) {
    	td.setGroupName("myGroup");
    	ITraveler tr = new SimpleTraveler();
    	tr.setIdCard("12345");
    	tr.setFirstName("John");
    	tr.setSecondName("Dow");
    	tr.setTicketHolder(true);	
    	ICustomerStatusDescription cst = new SimpleCustomerStatusDescription();
    	tr.addStatusDescription(cst);
    	cst.setDescription("senior");
    	td.addTraveler(tr);
	}

	/*
       ,issuingDetail {
            issuerNum 1080
            issuingYear       2018
            issuingDay        1
       	    specimen  TRUE,
            securePaperTicket FALSE,
            activated TRUE,
            issuerPNR  "issuerTestPNR",
       	    issuedOnLine  12
       }
     */
    private static void populateIssuingData(IIssuingDetail iIssuingDetail) {   	

		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2018");
			iIssuingDetail.setIssuer("1080");
	    	iIssuingDetail.setIssuingDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

    	
    	iIssuingDetail.setIssuerPNR("issuerTestPNR");
    	iIssuingDetail.setSpecimen(true);
    	iIssuingDetail.setSecurePaperTicket(false);
    	iIssuingDetail.setActivated(true);
    	iIssuingDetail.setIssuedOnLine(12);	
	}

	/*
       ,extension {
            { extensionId "1", extensionData '82DA'H }
           ,{ extensionId "2", extensionData '83DA'H }
        }
     */
    private static IExtension populateExtension() {
    	IExtension ed1 = new SimpleExtension();
    	ed1.setId("1");
    	byte[] ba1 = { (byte) 0x82, (byte) 0xDA };
    	ed1.setBinarydata(ba1); 
    	return ed1;
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
	private static void populate(IControlDetail iControlDetail) {
		iControlDetail.setInfoText("cd");
		iControlDetail.setAgeCheckRequired(false);
		iControlDetail.setIdentificationByIdCard(false);
		iControlDetail.setIdentificationByPassportId(false);
		iControlDetail.setOnlineValidationRequired(false);
		iControlDetail.setPassportValidationRequired(false);
		iControlDetail.setReductionCardCheckRequired(false);
		iControlDetail.getIdentificationByCardReference().add(populateCardRefrence());	
		iControlDetail.addLinkedTicket(populateLinkedTicket());
	}

	
	/*
	 * 
	 */
	private static ITicketLink populateLinkedTicket() {
		ITicketLink it = new SimpleTicketLink();
		it.setProductOwner("test");
		it.setLinkMode(ILinkMode.issuedTogether);
		return it;
	}

	/*
	 {
	   trailingCardIdNum 100
	 }
	 */
	private static ICardReference populateCardRefrence() {
		ICardReference cr = new SimpleCardReference();
		cr.setTrailingCardId("100");
		return cr;
	}

	
	public static void compare(IUicRailTicket ticket1, IUicRailTicket ticket2) {
		
		assert(ticket1.getIssuerDetails().getIssuer().equals(ticket2.getIssuerDetails().getIssuer()));
		
	}


}
