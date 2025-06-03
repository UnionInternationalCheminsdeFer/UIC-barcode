package org.uic.barcode.ticket.api.test;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.asn.omv2.ConfirmationTypeType;
import org.uic.barcode.ticket.api.asn.omv2.UicRailTicketData;
import org.uic.barcode.ticket.api.spec.IBerth;
import org.uic.barcode.ticket.api.spec.IBerthTypeType;
import org.uic.barcode.ticket.api.spec.ICarCarriageReservation;
import org.uic.barcode.ticket.api.spec.ICardReference;
import org.uic.barcode.ticket.api.spec.ICompartmentGenderType;
import org.uic.barcode.ticket.api.spec.ICompartmentPositionType;
import org.uic.barcode.ticket.api.spec.ICounterMark;
import org.uic.barcode.ticket.api.spec.ICustomerCard;
import org.uic.barcode.ticket.api.spec.ICustomerStatusDescription;
import org.uic.barcode.ticket.api.spec.IDelayConfirmation;
import org.uic.barcode.ticket.api.spec.IDocumentData;
import org.uic.barcode.ticket.api.spec.IDocumentExtension;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IFipTicket;
import org.uic.barcode.ticket.api.spec.IGenderType;
import org.uic.barcode.ticket.api.spec.IGeoCoordinateSystemType;
import org.uic.barcode.ticket.api.spec.IGeoUnitType;
import org.uic.barcode.ticket.api.spec.IHemisphereLatitudeType;
import org.uic.barcode.ticket.api.spec.IHemisphereLongitudeType;
import org.uic.barcode.ticket.api.spec.IIncludedOpenTicket;
import org.uic.barcode.ticket.api.spec.ILine;
import org.uic.barcode.ticket.api.spec.ILinkMode;
import org.uic.barcode.ticket.api.spec.ILoadingDeckType;
import org.uic.barcode.ticket.api.spec.IOpenTicket;
import org.uic.barcode.ticket.api.spec.IParkingGround;
import org.uic.barcode.ticket.api.spec.IPass;
import org.uic.barcode.ticket.api.spec.IPassengerType;
import org.uic.barcode.ticket.api.spec.IPolygone;
import org.uic.barcode.ticket.api.spec.IPriceTypeType;
import org.uic.barcode.ticket.api.spec.IRegionalValidity;
import org.uic.barcode.ticket.api.spec.IRegisteredLuggage;
import org.uic.barcode.ticket.api.spec.IReservation;
import org.uic.barcode.ticket.api.spec.IRoofRackType;
import org.uic.barcode.ticket.api.spec.IServiceType;
import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.IStationPassage;
import org.uic.barcode.ticket.api.spec.ITariff;
import org.uic.barcode.ticket.api.spec.ITicketLink;
import org.uic.barcode.ticket.api.spec.ITicketType;
import org.uic.barcode.ticket.api.spec.ITimeRange;
import org.uic.barcode.ticket.api.spec.ITrainLink;
import org.uic.barcode.ticket.api.spec.ITravelClassType;
import org.uic.barcode.ticket.api.spec.ITraveler;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.ticket.api.spec.IValidityRange;
import org.uic.barcode.ticket.api.spec.IVatDetail;
import org.uic.barcode.ticket.api.spec.IViaStation;
import org.uic.barcode.ticket.api.spec.IVoucher;
import org.uic.barcode.ticket.api.spec.IZone;
import org.uic.barcode.ticket.api.test.testtickets.AllElementsTestTicketV2;
import org.uic.barcode.ticket.api.utils.Api2AsnEncoder;
import org.uic.barcode.ticket.api.utils.Api2OpenAsnEncoderV2;
import org.uic.barcode.ticket.api.utils.Asn2ApiDecoder;
import org.uic.barcode.ticket.api.utils.OpenAsn2ApiDecoderV2;


/**
 * The Class CarCarriageTestV1.
 * 
 * 
 * 
 */
public class AllElementsTestV2 {
	
	   
    /** The decoder. */
    Asn2ApiDecoder decoder = new OpenAsn2ApiDecoderV2();
    
    /** The encoder. */
    Api2AsnEncoder encoder = new Api2OpenAsnEncoderV2();
       
       
    TimeZone defaulttimeZone = null;
    
	/**
	 * Prepare tickets.
	 */
	@Before public void prepare() {
			
		LoggerFactory.setActivateConsoleLog(true);
		
		defaulttimeZone = TimeZone.getDefault();  	

		
	}
	
	/**
	 * clean up
	 */
	@After public void resetTimeZone() {
		TimeZone.setDefault(defaulttimeZone);
	}
	
    
	/**
	 * Test encode test tickets in UTC and decode in CET.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws ParseException 
	 */
	@Test public void testDelayConfirmation() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
			
		defaulttimeZone = TimeZone.getDefault();
    	//encode in UTC time zone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		IUicRailTicket ticketDecoded = null;
		try {
			
			UicRailTicketData rtd = AllElementsTestTicketV2.getUicTestTicket();
			byte[] content = UperEncoder.encode(rtd);
			ticketDecoded = decoder.decodeFromAsn(content);
		} catch (Exception e) {
			assert(false);
		}
		

		
    	
        byte[] encoded = null;
		try {
			encoded = encoder.encode(ticketDecoded);
		} catch (EncodingFormatException e) {
			assert(false);
		}
		
		//decode ticket again
		IUicRailTicket ticketDecoded2 = null;
		try {
			ticketDecoded2 = decoder.decodeFromAsn(encoded);
		} catch (Exception e) {
			assert(false);
		}
				
		assert (ticketDecoded2 != null);
		
		//validate content
		validateTicketContent(ticketDecoded2);
		
		
		
              
	    TimeZone.setDefault(defaulttimeZone);
    }    
	
	
	
	
	
	 
	private void validateTicketContent(IUicRailTicket ticket) {
		assert (ticket != null);		
	
		assert(ticket.getControlDetails() != null);
		assert(ticket.getControlDetails().isAgeCheckRequired() == false);
		assert(ticket.getControlDetails().isIdentificationByPassportId() == false);
		assert(ticket.getControlDetails().isIdentificationByIdCard() == false);
		assert(ticket.getControlDetails().isOnlineValidationRequired() == false);
		assert(ticket.getControlDetails().getIdentificationItem() == 12);
		assert(ticket.getControlDetails().isPassportValidationRequired() == false);
		assert(ticket.getControlDetails().getRandomDetailedValidationRequired() == 50);
		assert(ticket.getControlDetails().getExtension() != null);
		assert(ticket.getControlDetails().isReductionCardCheckRequired() == false);
		assert(ticket.getControlDetails().getInfoText().equals("control"));
		assert(ticket.getControlDetails().getIdentificationByCardReference() != null);
	
		assert(ticket.getControlDetails().getIdentificationByCardReference().size() == 1);
		ICardReference cr = ticket.getControlDetails().getIdentificationByCardReference().iterator().next();
		assert(cr != null);
		assert(cr.getCardId().equals("5678"));
		assert(cr.getCardIssuer().equals("1234"));
		assert(cr.getCardName().equals("testcard"));
		assert(cr.getCardType() == 123);
		assert(cr.getLeadingCardId().equals("3456"));
		assert(cr.getTrailingCardId().equals("100"));
		
		
		assert(ticket.getControlDetails().getLinkedTickets() != null);
		
		ITicketLink tl = ticket.getControlDetails().getLinkedTickets().iterator().next();

		assert(tl.getIssuer().equals("XYZ"));
		assert(tl.getIssuerPNR().equals("LDWDUR45"));
		assert(tl.getProductOwner().equals("IEFHU"));
		assert(tl.getLinkMode().equals(ILinkMode.issuedTogether));
		assert(tl.getReference().equals("KDJET"));
		assert(tl.getTicketType().equals(ITicketType.openTicket));
							
		assert(ticket.getExtensions() != null);
		assert(ticket.getExtensions().size() == 2);
		Iterator<IExtension> it = ticket.getExtensions().iterator();
		IExtension e0 = it.next();
		IExtension e1 = it.next();
		assert(e0.getId().equals("1"));
		assert(UperEncoder.hexStringFromBytes(e0.getBinarydata()).equals("82DA"));
		assert(e1.getId().equals("2"));
		assert(UperEncoder.hexStringFromBytes(e1.getBinarydata()).equals("83DA"));
		
		
		assert(ticket.getIssuerDetails() != null);
	
		assert(ticket.getIssuerDetails().isActivated() == true);
		assert(ticket.getIssuerDetails().getCurrency().equals("SRF"));
		assert(ticket.getIssuerDetails().getCurrencyFraction() == 3L);
		assert(ticket.getIssuerDetails().getExtension() != null);
		assert(ticket.getIssuerDetails().getIssuedOnLine() == 12);
		assert(ticket.getIssuerDetails().getIssuedOnTrain().equals("123"));
		assert(ticket.getIssuerDetails().getIssuerName().equals("name"));
		assert(ticket.getIssuerDetails().getIssuer().equals("1"));
		assert(ticket.getIssuerDetails().getIssuerPNR().equals("issuerTestPNR"));
		assert(ticket.getIssuerDetails().getIssuingDate() != null);
		assert(ticket.getIssuerDetails().isSecurePaperTicket() == false);
		assert(ticket.getIssuerDetails().getSecurityProvider().equals("1"));
		assert(ticket.getIssuerDetails().isSpecimen() == true);
		assert(ticket.getIssuerDetails().getPointOfSale() !=  null);
		assert(ticket.getIssuerDetails().getPointOfSale().getUnit().equals(IGeoUnitType.microDegree));
		assert(ticket.getIssuerDetails().getPointOfSale().getSystem().equals(IGeoCoordinateSystemType.wgs84));
		assert(ticket.getIssuerDetails().getPointOfSale().getAccuracy().equals(IGeoUnitType.microDegree));
		assert(ticket.getIssuerDetails().getPointOfSale().getHemisphereLatitude().equals(IHemisphereLatitudeType.north));
		assert(ticket.getIssuerDetails().getPointOfSale().getHemisphereLongitude().equals(IHemisphereLongitudeType.east));
		assert(ticket.getIssuerDetails().getPointOfSale().getLatitude() == 56789);
		assert(ticket.getIssuerDetails().getPointOfSale().getLongitude() == 12345);
		
		assert(ticket.getDocumentData() != null);
		assert(!ticket.getDocumentData().isEmpty());
		
		Iterator<IDocumentData> i2 = ticket.getDocumentData().iterator();

		int numberOfDocuments = 0;
		while (i2.hasNext()) {
			numberOfDocuments++;
			IDocumentData d1 = i2.next();
			if (d1 instanceof IReservation) {
				validate((IReservation) d1);
			} else if (d1 instanceof ICarCarriageReservation) {
				validate((ICarCarriageReservation) d1);
			} else if (d1 instanceof IPass) {
				validate((IPass) d1);
			} else if (d1 instanceof IOpenTicket) {
				validate((IOpenTicket) d1);
			} else if (d1 instanceof ICounterMark) {
				validate((ICounterMark) d1);
			} else if (d1 instanceof IStationPassage) {
				validate((IStationPassage) d1);
			} else if (d1 instanceof ICustomerCard) {
				validate((ICustomerCard) d1);
			} else if (d1 instanceof IDelayConfirmation) {
				validate((IDelayConfirmation) d1);
			} else if (d1 instanceof IParkingGround) {
				validate((IParkingGround) d1);
			} else if (d1 instanceof IFipTicket) {
				validate((IFipTicket) d1);
			} else if (d1 instanceof IVoucher) {
				validate((IVoucher) d1);
			} else if (d1 instanceof IDocumentExtension) {
				assert(((IDocumentExtension) d1).getId() != null);
			}
		}
		assert(numberOfDocuments == 11);
			
		
		assert(ticket.getTravelerDetails() != null);
		assert(ticket.getTravelerDetails().getGroupName().equals("myGroup"));
		assert(ticket.getTravelerDetails().getPreferredLanguage().equals("EN"));
		assert(ticket.getTravelerDetails().getTravelers() != null);
		assert(ticket.getTravelerDetails().getTravelers().size() == 1);
 
		ITraveler tr = ticket.getTravelerDetails().getTravelers().iterator().next();
		
		assert(tr.getIDCardCountry() == 103);
		assert(tr.getPassportCountry() == 102);
		assert(tr.getCountryOfResidence() == 101);
		assert(tr.getCustomerId().equals("DZE5gT"));
		assert(tr.getDateOfBirth() != null);
		assert(tr.getFirstName().equals("John"));
		assert(tr.getGender().equals(IGenderType.male));
		assert(tr.getIdCard().equals("12345"));
		assert(tr.getLastName().equals("Dow"));
		assert(tr.getPassengerType().equals(IPassengerType.senior));
		assert(tr.isPassengerWithReducedMobility() == false);
		assert(tr.getPassportId().equals("JDTS"));
		assert(tr.getSecondName().equals("Little"));
		assert(tr.getTitle().equals("PhD"));
		assert(tr.isTicketHolder() == true);
		assert(tr.getStatusCollection() != null);
		assert(tr.getStatusCollection().size() == 1);
		ICustomerStatusDescription csd = tr.getStatusCollection().iterator().next();
		assert(csd.getStatus() == 1);
		assert(csd.getDescription().equals("senior"));
		
	}


	private void validate(ICounterMark t) {
		
		assert(t != null);
		
		assert(t.getReference().equals("810123456789"));
    	assert(t.getProductOwner().equals(	  "23456"));    
		assert(t.getProductId().equals(		  "23456"));    			
		assert(t.getTicketReference().equals(	 "810123456789"));		     
		assert(t.getNumberOfCountermark() ==  12L);
        assert(t.getTotalOfCountermarks() ==  24L);
        assert(t.getGroupName().equals(	           "groupName"));
		assert(t.isReturnIncluded() == false);	      
			  
		assert(t.getStationCodeTable().equals(IStationCodeTable.stationERA));
		assert(t.getFromStation().equals("8100001"));
		assert(t.getToStation().equals(  "8100002"));
		assert(t.getFromStationName().equals(  "A-STATION")); 
		assert(t.getToStationName().equals(    "B-STATION"));				  

		assert(t.getValidRegionDesc().equals("From A to B via C"));		   
			  
		assert(t.getValidRegionList() != null);
		assert(t.getValidRegionList().size() == 1);
		
	
	    assert(t.getReturnDescription() != null);
	    assert(t.getReturnDescription().getFromStation().equals("8100001"));
		assert(t.getReturnDescription().getToStation().equals(         "8100002"));
	    assert(t.getReturnDescription().getFromStationName().equals(  "A-STATION"));
		assert(t.getReturnDescription().getToStationName().equals(    "B-STATION")); 	
		assert(t.getReturnDescription().getValidRegionDesc().equals( "return"));
		assert(t.getReturnDescription().getValidRegionList() != null);
		assert(t.getReturnDescription().getValidRegionList().size() == 1);
        
	    assert(t.getValidFrom() != null);
	    assert(t.getValidUntil() != null);
		 		  
		assert(t.getClassCode().equals(ITravelClassType.first));		
	
    	assert(t.getIncludedCarriers() != null);
    	assert(t.getIncludedCarriers().size() == 2);
    	assert(t.getIncludedCarriers().contains("1080"));
    	assert(t.getIncludedCarriers().contains("1181"));		
    
	
        assert(t.getIncludedServiceBrands() != null);
        assert(t.getIncludedServiceBrands().size() == 2);
        Iterator<Integer> i4 = t.getIncludedServiceBrands().iterator();
        assert(i4.next() == 108);
        assert(i4.next() == 118);
     
        assert(t.getExcludedServiceBrands() != null);
        assert(t.getExcludedServiceBrands().size() == 2);
        i4 = t.getExcludedServiceBrands().iterator();
        assert(i4.next() == 108);
        assert(i4.next() == 118);
	
   
        assert (t.getInfoText().equals("counterMark"));
		
		assert(t.getExtension() != null);

		
	}

	private void validate(IDelayConfirmation t) {
	    assert(t != null);
	    					
	    assert(t.getTrain().equals("100"));     						
	    assert(t.getArrivalDate() != null); 
	    assert(t.getReference().equals("ABDJ12345"));	
	    assert(t.getStation().equals("DJE"));
	    assert(t.getDelay() ==      31);
	    assert(t.isTrainCancelled() == false);
	    assert(t.getConfirmationType() ==  ConfirmationTypeType.travelerDelayConfirmation.ordinal());
	    assert(t.getLinkedTickets() != null);
	    assert(t.getLinkedTickets().size() == 1);
	    ITicketLink tl = t.getLinkedTickets().iterator().next();
	    assert(tl.getReference().equals("KDJET"));
        assert(tl.getIssuer().equals(      "XYZ"));
        assert(tl.getIssuerPNR().equals(       "LDWDUR45"));
        assert(tl.getProductOwner().equals( "IEFHU"));
        assert(tl.getTicketType().equals(ITicketType.openTicket));
        assert(tl.getLinkMode().equals( ILinkMode.issuedTogether));
        
        assert(t.getInfoText().equals( "delay confirmation"));
        assert(t.getExtension() != null);
        
	}

	private void validate(ICustomerCard c) {
		assert(c != null);
		assert (c.getExtension() != null);	
		assert (c.getCustomer() != null);
		assert (c.getCustomer() != null);

		assert (c.getCardId().equals("2345")); 
		assert (c.getValidFrom() != null);
		assert (c.getValidUntil() != null);    
	    assert (c.getClassCode().equals(ITravelClassType.second));
	    assert (c.getCardType() ==			15);
	    assert (c.getCardTypeDescr().equals(	"RAILPLUS"));	
	    assert (c.getCustomerStatus() ==   1);  
	    assert (c.getCustomerStatusDescr().equals( "gold"));      
	    assert (c.getIncludedServices() != null);
	    assert (c.getIncludedServices().size() == 2);
	    Iterator<Integer> i3 = c.getIncludedServices().iterator();
    	assert(i3.next() == 1);
    	assert(i3.next() == 2);	
		
	}

	private void validate(IVoucher v) {
		assert(v != null);
		assert (v.getExtension() != null);
		
	    assert(v.getReference().equals("810123456789"));		     		        															
	    assert(v.getProductOwner().equals("COFFEEMACHINE"));    
	    assert(v.getProductId().equals("23456")); 

	    assert(v.getValidFrom() != null);
        assert(v.getValidUntil() != null);
        assert(v.getAmount() == 500);
        assert(v.getType() == 123);
        assert(v.getInfoText().equals("coffee voucher"));

	}

	private void validate(IStationPassage t) {
		assert(t != null);
		assert(t.getExtension() != null);
		
	    assert(t.getReference().equals("810123456789"));		     		        															
	    assert(t.getProductOwner().equals("23456"));    
	    assert(t.getProductId().equals("23456")); 
			      
	    assert(t.getProductName().equals("passage"));
        assert(t.getStationCodeTable().equals(IStationCodeTable.stationUIC));
	    assert(t.getStations() != null);
	    assert(t.getStations().size() == 2);
   	    assert(t.getStations().contains("8200001"));	   
   	    assert(t.getStations().contains("AMS"));
	    assert(t.getStationNames() != null);
	    assert(t.getStationNames().size() == 1);
	    assert(t.getStationNames().iterator().next().equals("Amsterdam"));
	    assert(t.getAreaCodes() != null);
	    assert(t.getAreaCodes().size() == 2);
	    assert(t.getAreaCodes().contains("AMS"));	
	    assert(t.getAreaCodes().contains("8200001"));
	    assert(t.getAreaNames() != null);
	    assert(t.getAreaNames().size() == 1);
	    assert(t.getAreaNames().contains("Amsterdam"));
	    assert(t.getValidFrom() != null);
	    assert(t.getValidUntil() != null);
	    assert(t.getNumberOfdaysAllowed() == 5);

	}

	private void validate(IParkingGround t) {
		assert(t != null);
		assert(t.getExtension() != null);
		
	    assert(t.getReference().equals("810123456789"));	
	    assert(t.getProductOwner().equals("23456"));    
	    assert(t.getProductId().equals("23456")); 
		
       
	    assert(t.getParkingGroundId().equals( 	  "IA5"));
	    assert(t.getFromParkingDate() != null);        
	    assert(t.getToParkingDate() != null);
        
        
	    assert(t.getAccessCode().equals(          "4ga"));
	    assert(t.getLocation().equals(            "Parking Frankfurt Main West"));
	    assert(t.getStationCodeTable().equals(IStationCodeTable.stationUIC));
         assert(t.getStation().equals(          "8000001"));
	      assert(t.getSpecialInformation().equals(  "outdoor parking"));
	      assert(t.getEntryTrack().equals(          "left"));
	      assert(t.getNumberPlate().equals(         "AA-DE-12345"));
	      assert(t.getPrice() ==               500);	
	      assert(t.getVatDetails() != null);
	      assert(t.getVatDetails().size() == 1);
		  
		
	}

	private void validate(IFipTicket t) {
		assert(t != null);
		assert(t.getExtension() != null);
		
	    assert(t.getReference().equals("810123456789"));	
	    assert(t.getProductOwner().equals("23456"));    
	    assert(t.getProductId().equals("23456")); 
	    
 				   		
	    assert(t.getValidFrom() != null);   		        	 
        assert(t.getValidUntil() != null);							
        assert(t.getActivatedDays() != null);     
        assert(t.getActivatedDays().size() == 4);    
        Iterator<Date> i5 = t.getActivatedDays().iterator();
        assert(i5.next() != null);
        assert(i5.next() != null);
        assert(i5.next() != null);
        assert(i5.next() != null);
              	
        
        
	    assert(t.getCarriers()!= null);
	    assert(t.getCarriers().size() == 2);
    	assert(t.getCarriers().contains("1080"));
    	assert(t.getCarriers().contains("1181"));
	    	
       	
        assert(t.getNumberOfTravelDates() ==  8);                  
        assert(t.isIncludesSupplements() == true);			
        assert(t.getClassCode().equals(ITravelClassType.first));

	}

	private void validate(IOpenTicket t) {
		
			assert (t != null);
		
			assert(t.getReference().equals("810123456789"));
        	assert(t.getProductOwner().equals(	  "23456"));    
			assert(t.getProductId().equals(		  "23456"));    			
			assert(t.getExternalIssuer() ==         12);
			assert(t.getAuthorizationCode() == 13);	
			assert(t.isReturnIncluded() == false);	      
				  
			assert(t.getStationCodeTable().equals(IStationCodeTable.stationERA));
			assert(t.getFromStation().equals("8100001"));
			assert(t.getToStation().equals(  "8100002"));
			assert(t.getFromStationName().equals(  "A-STATION")); 
			assert(t.getToStationName().equals(    "B-STATION"));				  
 
			assert(t.getValidRegionDesc().equals("From A to B via C"));		   
				  
			assert (t.getValidRegionList() != null);
			assert(t.getValidRegionList().size() == 5);
			Iterator<IRegionalValidity> irv = t.getValidRegionList().iterator();
			int checks = 0;
			while (irv.hasNext()) {
				IRegionalValidity v = irv.next();
				if (v instanceof IZone) {
					validateRegion((IZone) v);
					checks++;
				} else if (v instanceof IViaStation) {
					validateRegion((IViaStation) v);
					checks++;
				} else if (v instanceof ITrainLink) {
					validateRegion((ITrainLink) v);
					checks++;
				} else if (v instanceof IPolygone) {
					validateRegion((IPolygone) v);
					checks++;
				} else if (v instanceof ILine) {
					validateRegion((ILine) v);
					checks++;
				}  
			}
			assert(checks == 5);
		
		    assert(t.getReturnDescription() != null);
		    
		    assert(t.getReturnDescription().getFromStation().equals("8100001"));
		    assert(t.getReturnDescription().getToStation().equals(         "8100002"));
		    assert(t.getReturnDescription().getFromStationName().equals(  "A-STATION"));
			assert(t.getReturnDescription().getToStationName().equals(    "B-STATION")); 	
			assert(t.getReturnDescription().getValidRegionDesc().equals( "return"));
			assert(t.getReturnDescription().getValidRegionList() != null);
			assert(t.getReturnDescription().getValidRegionList().size() == 1);
            
		    assert(t.getValidFrom() != null);
		    assert(t.getValidUntil() != null);  
			 
	        assert(t.getActivatedDays() != null);
	        assert(t.getActivatedDays().size() == 2);	  
			   
			assert(t.getClassCode().equals(ITravelClassType.first));
			assert(t.getServiceLevel().equals("A"));		
		
	    	assert(t.getIncludedCarriers() != null);
	    	assert(t.getIncludedCarriers().size() == 2);
	    	assert(t.getIncludedCarriers().contains("1080"));
	    	assert(t.getIncludedCarriers().contains("1181"));

		
            assert(t.getIncludedServiceBrands() != null);
            assert(t.getIncludedServiceBrands().size() == 2);
            Iterator<Integer> i4 = t.getIncludedServiceBrands().iterator();
            assert(i4.next() == 108);
            assert(i4.next() == 118);
         
            assert(t.getExcludedServiceBrands() != null);
            assert(t.getExcludedServiceBrands().size() == 2);
            i4 = t.getExcludedServiceBrands().iterator();
            assert(i4.next() == 108);
            assert(i4.next() == 118);
            
		
        	assert(t.getTariffs() != null);
        	assert(t.getTariffs().size() == 1);		
				    
		    assert (t.getPrice() ==  12345);
            assert (t.getVatDetails() != null);
            assert (t.getVatDetails().size() == 1);
       
	        assert (t.getInfoText().equals("openTicketInfo"));
		
			assert (t.getIncludedAddOns() != null);
			assert (t.getIncludedAddOns().size() == 1);
			validate(t.getIncludedAddOns().iterator().next());
			
			assert(t.getLuggageRestriction() != null);
				
			
			assert(t.getExtension() != null);
		
	}

	private void validateRegion(ITrainLink t) {
		
		assert (t != null);
		
	    assert(t.getTrain().equals("12345"));     						
	    assert(t.getDepartureDateTime() != null);  
	    assert(t.getFromStation().equals("8100001"));

	    assert(t.getToStation().equals(  "8100002"));
	    assert(t.getFromStationName().equals(  "A-STATION")); 
	    assert(t.getToStationName().equals(    "B-STATION")); 
	         
	}

	private void validateRegion(IPolygone p) {
						
		assert(p.getEdges() != null);
		assert(p.getEdges().size() == 3);

	}

	private void validateRegion(IZone z) {
		
			assert(z != null);
		
			assert(z.getCarrier().equals("1080"));
			assert(z.getStationCodeTable().equals(IStationCodeTable.stationERA));
			assert(z.getEntryStation().equals(        "1234"));
            assert(z.getTerminatingStation().equals(  "2345"));
            assert(z.getCity() ==                    123456);
            assert(z.getZoneIds() != null);
            assert(z.getZoneIds().size() == 2);
            Iterator<Integer> iz = z.getZoneIds().iterator();
            assert(iz.next() == 100);
            assert(iz.next() == 200);
           
            assert(UperEncoder.hexStringFromBytes(z.getBinaryZoneId()).equals("82DA"));
            assert(z.getNUTScode().equals("DE4711"));
        
	}

	private void validateRegion(ILine z) {
		
		
		assert(z.getCarrier().equals("1080"));
		assert(z.getStationCodeTable().equals(IStationCodeTable.stationERA));
		
        assert(z.getEntryStation().equals(        "1234"));
        
        assert(z.getTerminatingStation().equals(  "2345"));
        assert(z.getCity() ==                    123456);
        assert(z.getLineIds() != null);
        assert(z.getLineIds().size() == 2);
        Iterator<Integer> iz = z.getLineIds().iterator();
        assert(iz.next() == 100);
        assert(iz.next() == 200);
       
       	
	}

	private void validateRegion(IViaStation t) {
		
			assert( t != null);
		
			assert(t.getRoute() != null);
			
			assert(t.getRoute().size() == 4);
			
			Iterator<IViaStation> iv = t.getRoute().iterator();
			IViaStation v1 = iv.next();
			IViaStation v2 = iv.next();
			IViaStation v3 = iv.next();
			IViaStation v4 = iv.next();
			
			assert(v1.getStation().equals("123455"));
			assert(v1.isBorder() == false);

			assert(v2.getStation().equals("123456"));
			assert(v2.isBorder() == false);
			
			assert(v3.getAlternativeRoutes() != null);
			assert(v3.getAlternativeRoutes().size() == 2);
			Iterator<IViaStation> ari = v3.getAlternativeRoutes().iterator();
			IViaStation ar1 = ari.next();
			IViaStation ar2 = ari.next();
			assert(ar1.getRoute().size() == 2);
			assert(ar1.getRoute().iterator().next().getStation().equals("23455"));
			assert(ar2.getRoute() != null);
			assert(ar2.getRoute().size() == 2);
			assert(ar2.getRoute().iterator().next().getStation().equals("3455"));
			
			assert(v4.getStation().equals("123457"));	          
	  	          
	  	    assert(t.isBorder() == false);
		          
		    assert(t.getSeriesId() == 999);
		    assert(t.getRouteId() == 21);
		    	 		    
		
	}

	private void validate(IIncludedOpenTicket d1) {

		assert(d1.getProductOwner().equals("23456"));    
		assert(d1.getProductId().equals("23456"));
		assert(d1.getExternalIssuer() ==     12);
		assert(d1.getAuthorizationCode() == 13);		
		assert(d1.getStationCodeTable().equals(IStationCodeTable.stationERA));    	         
		assert(d1.getValidRegionList() != null);
		assert(d1.getValidRegionList().size() == 1);
		assert(d1.getValidFrom() != null);
		assert(d1.getValidUntil() != null);
		assert(d1.getClassCode().equals(ITravelClassType.second));
		assert(d1.getServiceLevel().equals("A"));
				
	    assert(d1.getIncludedCarriers() != null);
	    assert(d1.getIncludedCarriers().size() == 2);
    	assert(d1.getIncludedCarriers().contains("1080"));
    	assert(d1.getIncludedCarriers().contains("1181"));
	    
          assert(d1.getIncludedServiceBrands() != null);
          assert(d1.getIncludedServiceBrands().size() == 2);
          assert(d1.getIncludedServiceBrands().contains(108));
          assert(d1.getIncludedServiceBrands().contains(118));

       
          assert(d1.getExcludedServiceBrands() != null);
          assert(d1.getExcludedServiceBrands().size() == 2);
          assert(d1.getExcludedServiceBrands().contains(108));
          assert(d1.getExcludedServiceBrands().contains(118));
        
 
        assert(d1.getTariffs() != null);
        assert(d1.getTariffs().size() == 1);
                            
		assert(d1.getInfoText().equals("included ticket"));
		
        assert(d1.getExtension() != null);
        		    	        
		 
		
	}

	private void validate(IPass p) {


		assert(p.getReference().equals("810123456789"));
        assert(p.getProductOwner().equals(	  "23456"));    
		assert(p.getProductId().equals(		  "23456"));    			  
		assert(p.getPassType() ==             2);
		assert(p.getPassDescription().equals(  "Eurail FlexPass"));
		assert(p.getClassCode().equals(ITravelClassType.first));
	    assert(p.getValidFrom() != null);
	    assert(p.getValidUntil() != null);
	    assert(p.getValidityDetails() != null);

        assert(p.getValidityDetails() != null);
        assert(p.getValidityDetails().getValidityRanges().size() == 1);
        IValidityRange vr = p.getValidityDetails().getValidityRanges().iterator().next();
        assert(vr.getFromDate() != null);
        assert(vr.getUntilDate() != null);

	    assert(p.getValidityDetails().getTimeRanges() != null);
	    ITimeRange tr = p.getValidityDetails().getTimeRanges().iterator().next();
	    assert(tr.getFromTime() == 6);
	    assert(tr.getUntilTime() == 9);

	    assert(p.getNumberOfValidityDays() == 5);
      
        assert(p.getNumberOfPossibleTrips() ==        3);
          assert(p.getNumberOfDaysOfTravel() ==         10);
          assert(p.getActivatedDays() != null);
          assert(p.getActivatedDays().size() == 2);

      
          assert(p.getCountries() != null);
          assert(p.getCountries().size() == 2);
          Iterator<Integer> i6 = p.getCountries().iterator();
          assert(i6.next() == 10);
          assert(i6.next() == 20);
          
  	    assert(p.getIncludedCarriers() != null);
  	    assert(p.getIncludedCarriers().size() == 2);
      	assert(p.getIncludedCarriers().contains("1080"));
      	assert(p.getIncludedCarriers().contains("1181"));
  	    
            assert(p.getIncludedServiceBrands() != null);
            assert(p.getIncludedServiceBrands().size() == 2);
            assert(p.getIncludedServiceBrands().contains(108));
            assert(p.getIncludedServiceBrands().contains(118));

         
            assert(p.getExcludedServiceBrands() != null);
            assert(p.getExcludedServiceBrands().size() == 2);
            assert(p.getExcludedServiceBrands().contains(108));
            assert(p.getExcludedServiceBrands().contains(118));
          

    
          assert(p.getExcludedCarriers() != null);
          assert(p.getExcludedCarriers().size() == 2);
       	assert(p.getExcludedCarriers().contains("1080"));
      	assert(p.getExcludedCarriers().contains("1181"));
   
  
  
     
       
        assert(p.getValidRegionList() != null);
        assert(p.getValidRegionList().size() == 1);  
        assert(p.getTariffs() != null);
        assert(p.getTariffs().size() == 1);
        assert(p.getPrice() == 10000);    
        assert(p.getVatDetails() != null);
        assert(p.getVatDetails().size() == 1);
        assert(p.getInfoText().equals("pass info"));
		assert (p.getExtension() != null);	
		
		
	}

	private void validate(IReservation r) {
		
	    assert(r.getTrain().equals("12345"));     						
	    assert(r.getDepartureDate() != null); 
	    assert(r.getReference().equals("810123456789"));	   
	    assert(r.getProductOwner().equals("23456"));    
	    
	    assert(r.getProductId().equals("23456")); 
	    assert(r.getServiceBrand().getServiceBrand() == 12);
	    assert(r.getServiceBrand().getServiceBrandAbbreviation().equals("TGV"));      					   	
	    assert(r.getServiceBrand().getServiceBrandDescription().equals("Lyria"));     					    
	    assert(r.getService().equals(IServiceType.couchette));		
	    
	    assert(r.getStationCodeTable().equals(IStationCodeTable.stationUIC));
	    assert(r.getFromStation().equals("8100001"));
	    assert(r.getToStation().equals(  "8100002"));
	    assert(r.getFromStationName().equals(  "A-STATION")); 
	    assert(r.getToStationName().equals(    "B-STATION")); 
	    assert(r.getDepartureDate() != null);           
	    assert(r.getArrivalDate() != null);  
	    assert(r.getCarriers()!= null);
	    assert(r.getCarriers().size() == 2);
        Iterator<String> i8 = r.getCarriers().iterator();
    	assert(i8.next().equals("1080"));
    	assert(i8.next().equals("1181"));
	 
	    
	    assert(r.getClassCode().equals(ITravelClassType.first));
	    assert(r.getServiceLevel().equals("A"));
	    
	    assert(r.getPlaces() != null);
	    assert(r.getPlaces().getCoach().equals("31A"));
	    assert(r.getPlaces().getPlaceString().equals("31-47"));
	    assert(r.getPlaces().getPlaceDescription().equals("Window"));
	    assert(r.getPlaces().getPlaces() != null);
	    assert(r.getPlaces().getPlaces().size() == 4);
	    Iterator<String> is = r.getPlaces().getPlaces().iterator();
	    assert(is.next().equals("31"));
	    assert(is.next().equals("32"));

	    
	    assert(r.getAdditionalPlaces() != null);
	    assert(r.getBicyclePlaces() != null);
	    
	    
		assert(r.getCompartmentDetails() != null);
		assert(r.getCompartmentDetails().getCoachType() == 1); 
		assert(r.getCompartmentDetails().getCompartmentType() == 			99); 
		assert(r.getCompartmentDetails().getSpecialAllocation() == 		50);		  	
		assert(r.getCompartmentDetails().getCoachTypeDescr().equals("xwz"));    
		assert(r.getCompartmentDetails().getCompartmentTypeDescr().equals(	"xwz"));			
		assert(r.getCompartmentDetails().getSpecialAllocationDescr().equals(	"xwz")); 		
		assert(r.getCompartmentDetails().getPosition().equals(ICompartmentPositionType.upperLevel)); 	
		
	    assert(r.getNumberOfOverbooked() ==  200);
	    assert(r.getBerths() != null);
	    assert(r.getBerths().size() == 1);
	    IBerth b = r.getBerths().iterator().next();
	    
	    assert(b.getType().equals(IBerthTypeType.single));
	    assert(b.getGender().equals(ICompartmentGenderType.female));
	    assert(b.getNumberOfBerths() == 999);
	    
	    
	    assert(r.getTariffs() != null);
	    assert(r.getTariffs().size() == 1);
	    ITariff t = r.getTariffs().iterator().next();
	    assert(t.getNumberOfPassengers() == 1);

 
	    assert(t.getPassengerType().equals(IPassengerType.senior) );
       	assert(t.getAgeBelow() ==           40);
       	assert(t.getAgeAbove() ==           60);
       	assert(t.getTravelerIds() != null);
       	assert(t.getTravelerIds().size() == 1);
       	assert(t.getTravelerIds().iterator().next() == 1);
       	
       	assert(t.isRestrictedToCountryOfResidence() == false);
       	assert(t.getRestrictedToRouteSection() != null);
       	assert(t.getRestrictedToRouteSection().getStationCodeTable().equals(IStationCodeTable.stationERA));              
      	assert(t.getRestrictedToRouteSection().getFromStation().equals(      	"123"));
		assert(t.getRestrictedToRouteSection().getToStation().equals(        	"234")); 				                 
		assert(t.getRestrictedToRouteSection().getFromStationName().equals( 	"A"));    
	    assert(t.getRestrictedToRouteSection().getToStationName().equals(   	"B"));

	    assert(t.getSeriesDataDetails() != null);
	    assert(t.getSeriesDataDetails().getSupplyingCarrier()   == 	2345);		
	    assert(t.getSeriesDataDetails().getOfferIdentification() ==	99);  		
	    assert(t.getSeriesDataDetails().getSeries() ==	23456);                     
              
        
       	assert(t.getTariffId().equals(       "72"));
       	assert(t.getTariffDescription().equals(         "Leasure Fare"));
       	assert(t.getReductionCards() != null);
    	assert(t.getReductionCards().size() == 1);
    	ICardReference rc = t.getReductionCards().iterator().next();
            
        assert(rc.getCardIssuer().equals(   "1234"));
        assert(rc.getCardId().equals(  "5678"));
		assert(rc.getCardName().equals(   "testcard"));
		assert(rc.getCardType() ==  123);                              
		assert(rc.getLeadingCardId().equals("3456"));
		assert(rc.getTrailingCardId().equals("100"));
	       
	    
		assert(r.getPriceType().equals(IPriceTypeType.travelPrice));
        assert(r.getPrice() == 12345);
        assert(r.getVatDetails() != null);
        IVatDetail v = r.getVatDetails().iterator().next();
        assert(r.getVatDetails().size() == 1);
        assert(v.getCountry() == 80);
        assert(v.getPercentage() == 70);
        assert(v.getAmount() == 10);
        assert(v.getVatId().equals("IUDGTE"));
        
        assert(r.getTypeOfSupplement() ==	9);		
        assert(r.getNumberOfSupplements() ==	2);
		assert(r.getLuggageRestriction() != null);
        
		assert(r.getLuggageRestriction().getMaxHandLuggagePieces() ==    2);
		assert(r.getLuggageRestriction().getMaxNonHandLuggagePieces() == 1);
		assert(r.getLuggageRestriction().getRegisteredLuggage() != null);
		assert(r.getLuggageRestriction().getRegisteredLuggage().size() == 2);
		
		
		Iterator<IRegisteredLuggage> il = r.getLuggageRestriction().getRegisteredLuggage().iterator();
		IRegisteredLuggage rl1 = null;
		IRegisteredLuggage rl2 = null;
		while (il.hasNext()) {
			IRegisteredLuggage l = il.next();
			if (l.getRegistrationId().equals("IODHUV")) {
				rl1 = l;
			} else {
				rl2 = l;
			}
		}
		assert(rl1.getRegistrationId().equals("IODHUV"));
		assert(rl1.getMaxWeight() ==       20);
	    assert(rl1.getMaxSize() ==        100);	  
		assert(rl2.getRegistrationId().equals("XXDHUV"));
		assert(rl2.getMaxWeight() ==       21);
	    assert(rl2.getMaxSize() == 101);	
	    assert(r.getInfoText().equals("reservation"));
	    assert(r.getExtension() != null);
	    
	}
	
	
	private void validate(ICarCarriageReservation r) {
		

		assert(r.getToken() != null);
		assert(UperEncoder.hexStringFromBytes(r.getToken().getToken()).equals("82DA"));
		assert(r.getToken().getTokenProvider().equals("VDV"));
		assert(r.getToken().getTokenSpecification().equals("TEST"));
		
					
	    assert(r.getTrain().equals("123"));     						
	    assert(r.getReference().equals("810123456789"));	
	    assert(r.getProductOwner().equals("23456"));    
	    assert(r.getProductId().equals("23456")); 
	    assert(r.getServiceBrand().getServiceBrand() == 100);
	    assert(r.getServiceBrand().getServiceBrandAbbreviation().equals("AZ"));      					   	
	    assert(r.getServiceBrand().getServiceBrandDescription().equals("special train"));     					    
        
        assert(r.getBeginLoading() != null);
        assert(r.getEndLoading() != null);
               
	    assert(r.getStationCodeTable().equals(IStationCodeTable.stationERA));
	    assert(r.getFromStation().equals("8100001"));
	    assert(r.getToStation().equals(  "8100002"));
	    assert(r.getFromStationName().equals(  "A-STATION")); 
	    assert(r.getToStationName().equals(    "B-STATION"));         
         
	    assert(r.getCoach().equals("21"));
	    assert(r.getPlace().equals("41"));
	    assert(r.getCompartmentDetails() != null);
	    	
	    assert(r.getCompartmentDetails().getCoachType()	==	1L);    
	    assert(r.getCompartmentDetails().getCompartmentType() ==	99L);    
	    assert(r.getCompartmentDetails().getSpecialAllocation()	==	50L);   				    	
	    assert(r.getCompartmentDetails().getCoachTypeDescr().equals("xwz"));  
		assert(r.getCompartmentDetails().getCompartmentTypeDescr().equals("xwz"));  			
		assert(r.getCompartmentDetails().getSpecialAllocationDescr().equals("xwz"));  		
		assert(r.getCompartmentDetails().getPosition().equals(ICompartmentPositionType.upperLevel));   	
			        
		assert(r.getNumberPlate().equals(          "AD-DE-123"));
		assert(r.getTrailerPlate().equals(         "DX-AB-123"));
		assert(r.getCarCategory()	==	          3L);
        assert(r.getBoatCategory()	==	         5L);
        assert(r.isTextileRoof()	==	         false);
        assert(r.getRoofRackType().equals(IRoofRackType.bicycleRack));
        assert(r.getRoofRackHeight()	==	       20L);
        assert(r.getAttachedBoats()	==	        2L);
        assert(r.getAttachedBicycles()	==	     1L);
        assert(r.getAttachedSurfboards()	==	   2L);
        assert(r.getLoadingListEntry()	==	     421L);
        assert(r.getLoadingDeck().equals(ILoadingDeckType.upper));
        
	    assert(r.getCarriers()!= null);
	    assert(r.getCarriers().size() == 2);
        Iterator<String> i9 = r.getCarriers().iterator();
    	assert(i9.next().equals("1080"));
    	assert(i9.next().equals("1181"));
        
        assert(r.getTariff() != null);
        assert(r.getPriceType().equals(IPriceTypeType.travelPrice));
        assert(r.getPrice()	==	 12345L);
        
        assert(r.getVatDetails() != null);
        
        assert(r.getInfoText().equals("car carriage"));
        assert(r.getExtension() != null);
        
	}

	public static int getIndexOfDifference(String s1, String s2) {
	
		if (s1 == null || s2 == null) return -1;
		
		char[] ca1 = null;
		char[] ca2 = null;
	
		if (s1.length() > s2.length()) {
			ca1 = s1.toCharArray();
			ca2 = s2.toCharArray();
		} else {
			ca1 = s2.toCharArray();
			ca2 = s1.toCharArray();			
		}
		
		int i = 0;
		for (char c : ca2) {
			if (c != ca1[i]) return i;
			i++;
		}
		return 0;
	}
		

	
}
