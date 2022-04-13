package org.uic.barcode.ticket.api.test;

import java.text.ParseException;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.api.asn.omv3.HemisphereLongitudeType;
import org.uic.barcode.ticket.api.asn.omv3.IncludedOpenTicketType;
import org.uic.barcode.ticket.api.asn.omv3.LineType;
import org.uic.barcode.ticket.api.asn.omv3.BerthTypeType;
import org.uic.barcode.ticket.api.asn.omv3.BoardingOrArrivalType;
import org.uic.barcode.ticket.api.asn.omv3.CarCarriageReservationData;
import org.uic.barcode.ticket.api.asn.omv3.CodeTableType;
import org.uic.barcode.ticket.api.asn.omv3.CompartmentGenderType;
import org.uic.barcode.ticket.api.asn.omv3.CompartmentPositionType;
import org.uic.barcode.ticket.api.asn.omv3.ConfirmationTypeType;
import org.uic.barcode.ticket.api.asn.omv3.CountermarkData;
import org.uic.barcode.ticket.api.asn.omv3.CustomerCardData;
import org.uic.barcode.ticket.api.asn.omv3.DelayConfirmation;
import org.uic.barcode.ticket.api.asn.omv3.DocumentData;
import org.uic.barcode.ticket.api.asn.omv3.FIPTicketData;
import org.uic.barcode.ticket.api.asn.omv3.GenderType;
import org.uic.barcode.ticket.api.asn.omv3.GeoCoordinateSystemType;
import org.uic.barcode.ticket.api.asn.omv3.GeoUnitType;
import org.uic.barcode.ticket.api.asn.omv3.HemisphereLatitudeType;
import org.uic.barcode.ticket.api.asn.omv3.LinkMode;
import org.uic.barcode.ticket.api.asn.omv3.LoadingDeckType;
import org.uic.barcode.ticket.api.asn.omv3.OpenTicketData;
import org.uic.barcode.ticket.api.asn.omv3.ParkingGroundData;
import org.uic.barcode.ticket.api.asn.omv3.PassData;
import org.uic.barcode.ticket.api.asn.omv3.PassengerType;
import org.uic.barcode.ticket.api.asn.omv3.PolygoneType;
import org.uic.barcode.ticket.api.asn.omv3.PriceTypeType;
import org.uic.barcode.ticket.api.asn.omv3.ReservationData;
import org.uic.barcode.ticket.api.asn.omv3.RoofRackType;
import org.uic.barcode.ticket.api.asn.omv3.ServiceType;
import org.uic.barcode.ticket.api.asn.omv3.StationPassageData;
import org.uic.barcode.ticket.api.asn.omv3.TicketType;
import org.uic.barcode.ticket.api.asn.omv3.TrainLinkType;
import org.uic.barcode.ticket.api.asn.omv3.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.asn.omv3.ViaStationType;
import org.uic.barcode.ticket.api.asn.omv3.VoucherData;
import org.uic.barcode.ticket.api.asn.omv3.ZoneType;
import org.uic.barcode.ticket.api.test.testtickets.AllElementsTestTicketV3;


/**
 * 
 * 
 * 
 */
public class AsnLevelAllElementsTestV3 {
	
    
    
    /** The ticket decoded 1. */
    UicRailTicketData ticket = null;
        
    byte[] encodedInTimeZone1 = null; 

    
    TimeZone defaulttimeZone = null;
    
	/**
	 * Prepare tickets.
	 */
	@Before public void prepare() {
		
		LoggerFactory.setActivateConsoleLog(true);
		
		defaulttimeZone = TimeZone.getDefault();

    	//encode in UTC time zone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));


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
	@Test public void decoding()  {
		
		//get ticket
		String expectedHex = AllElementsTestTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(expectedHex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
		
		byte[] encoded = UperEncoder.encode(ticket);
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		
		int i = getIndexOfDifference(expectedHex,encodedHex);
		
		assert (i == 0);
	
		assert(expectedHex.equals(encodedHex));
		        
    }   
	
	@Test public void encoding() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get ticket
		String expectedHex = AllElementsTestTicketV3.getEncodingHex();
		byte[] content = UperEncoder.bytesFromHexString(expectedHex);
		ticket = UperEncoder.decode(content, UicRailTicketData.class);
		
		assert(ticket != null);
		
		byte[] encoded = UperEncoder.encode(ticket);
						
		assert(encoded != null);
		assert(encoded.length > 20);
		
		String encodedHex = UperEncoder.hexStringFromBytes(encoded);
		
		int i = getIndexOfDifference(expectedHex,encodedHex);
		
		assert (i == 0);
		
		assert(expectedHex.equals(encodedHex));
		
		validateTicketContent(ticket);
        
    }  	
	
	@Test public void encodingDecodingValues() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get ticket
		ticket = AllElementsTestTicketV3.getUicTestTicket();
		
		assert(ticket != null);
		
		byte[] encoded = UperEncoder.encode(ticket);
						
		assert(encoded != null);
		assert(encoded.length > 20);
		
		ticket = UperEncoder.decode(encoded, UicRailTicketData.class);
		
		
		validateTicketContent(ticket);
        
    } 
	
	
	
	
	
	
		 
	private void validateTicketContent(UicRailTicketData t) {
		assert (t != null);		
	
		assert(t.getControlDetail() != null);
		assert(t.getControlDetail().getAgeCheckRequired() == false);
		assert(t.getControlDetail().getIdentificationByPassportId() == false);
		assert(t.getControlDetail().getIdentificationByIdCard() == false);
		assert(t.getControlDetail().getOnlineValidationRequired() == false);
		assert(t.getControlDetail().getIdentificationItem() == 12);
		assert(t.getControlDetail().getPassportValidationRequired() == false);
		assert(t.getControlDetail().getRandomDetailedValidationRequired() == 50);
		assert(t.getControlDetail().getExtension() != null);
		assert(t.getControlDetail().getReductionCardCheckRequired() == false);
		assert(t.getControlDetail().getInfoText().equals("control"));
		assert(t.getControlDetail().getIdentificationByCardReference() != null);
	
		assert(t.getControlDetail().getIdentificationByCardReference().size() == 1);
		assert(t.getControlDetail().getIdentificationByCardReference().get(0).getCardIdIA5().equals("5678"));
		assert(t.getControlDetail().getIdentificationByCardReference().get(0).getCardIdNum().intValue() == 5678);
		assert(t.getControlDetail().getIdentificationByCardReference().get(0).getCardIssuerIA5().equals("1234"));
		assert(t.getControlDetail().getIdentificationByCardReference().get(0).getCardIssuerNum().intValue() == 1234);
		assert(t.getControlDetail().getIdentificationByCardReference().get(0).getCardName().equals("testcard"));
		assert(t.getControlDetail().getIdentificationByCardReference().get(0).getCardType().intValue() == 123);
		assert(t.getControlDetail().getIdentificationByCardReference().get(0).getLeadingCardIdIA5().equals("3456"));
		assert(t.getControlDetail().getIdentificationByCardReference().get(0).getLeadingCardIdNum().intValue() == 3456);
		assert(t.getControlDetail().getIdentificationByCardReference().get(0).getTrailingCardIdIA5().equals("100"));
		assert(t.getControlDetail().getIdentificationByCardReference().get(0).getTrailingCardIdNum() == 100);
		
		
		assert(t.getControlDetail().getIncludedTickets() != null);

		assert(t.getControlDetail().getIncludedTickets().size() == 1);
		assert(t.getControlDetail().getIncludedTickets().get(0).getIssuerName().equals("XYZ"));
		assert(t.getControlDetail().getIncludedTickets().get(0).getIssuerPNR().equals("LDWDUR45"));
		assert(t.getControlDetail().getIncludedTickets().get(0).getProductOwnerIA5().equals("IEFHU"));
		assert(t.getControlDetail().getIncludedTickets().get(0).getProductOwnerNum() == 1080);
		assert(t.getControlDetail().getIncludedTickets().get(0).getLinkMode().equals(LinkMode.issuedTogether));
		assert(t.getControlDetail().getIncludedTickets().get(0).getReferenceIA5().equals("KDJET"));
		assert(t.getControlDetail().getIncludedTickets().get(0).getReferenceNum().longValue() == 801234567890L);
		assert(t.getControlDetail().getIncludedTickets().get(0).getTicketType().equals(TicketType.openTicket));
							
		assert(t.getExtension() != null);
		assert(t.getExtension().size() == 2);
		assert(t.getExtension().get(0).getExtensionId().equals("1"));
		assert(UperEncoder.hexStringFromBytes(t.getExtension().get(0).getExtensionData()).equals("82DA"));
		assert(t.getExtension().get(1).getExtensionId().equals("2"));
		assert(UperEncoder.hexStringFromBytes(t.getExtension().get(1).getExtensionData()).equals("83DA"));
		
		
		assert(t.getIssuingDetail() != null);
	
		assert(t.getIssuingDetail().getActivated() == true);
		assert(t.getIssuingDetail().getCurrency().equals("SRF"));
		assert(t.getIssuingDetail().getCurrencyFract() == 3);
		assert(t.getIssuingDetail().getExtension() != null);
		assert(t.getIssuingDetail().getIssuedOnLine() == 12);
		assert(t.getIssuingDetail().getIssuedOnTrainIA5().equals("123"));
		assert(t.getIssuingDetail().getIssuedOnTrainNum() == 123);
		assert(t.getIssuingDetail().getIssuerName().equals("name"));
		assert(t.getIssuingDetail().getIssuerIA5().equals("1"));
		assert(t.getIssuingDetail().getIssuerNum() == 32000);
		assert(t.getIssuingDetail().getIssuerPNR().equals("issuerTestPNR"));
		assert(t.getIssuingDetail().getIssuingDay() == 1);
		assert(t.getIssuingDetail().getIssuingTime() == 600);
		assert(t.getIssuingDetail().getIssuingYear() == 2018);
		assert(t.getIssuingDetail().getSecurePaperTicket() == false);
		assert(t.getIssuingDetail().getSecurityProviderIA5().equals("1"));
		assert(t.getIssuingDetail().getSecurityProviderNum() == 1);
		assert(t.getIssuingDetail().getSpecimen() == true);
		assert(t.getIssuingDetail().getPointOfSale() !=  null);
		assert(t.getIssuingDetail().getPointOfSale().getGeoUnit().equals(GeoUnitType.microDegree));
		assert(t.getIssuingDetail().getPointOfSale().getCoordinateSystem().equals(GeoCoordinateSystemType.wgs84));
		assert(t.getIssuingDetail().getPointOfSale().getAccuracy().equals(GeoUnitType.microDegree));
		assert(t.getIssuingDetail().getPointOfSale().getHemisphereLatitude().equals(HemisphereLatitudeType.east));
		assert(t.getIssuingDetail().getPointOfSale().getHemisphereLongitude().equals(HemisphereLongitudeType.north));
		assert(t.getIssuingDetail().getPointOfSale().getLatitude() == 56789);
		assert(t.getIssuingDetail().getPointOfSale().getLongitude() == 12345);
		
		assert(t.getTransportDocument() != null);
		assert(!t.getTransportDocument().isEmpty());

		DocumentData d1 = t.getTransportDocument().get(0);
		assert(d1 != null);
		assert(d1.getTicket().getReservation() != null);
		validateReservation(d1.getTicket().getReservation());
 	
		
		DocumentData d2 = t.getTransportDocument().get(1);
		assert(d2 != null);
		assert(d2.getTicket().getCarCarriageReservation() != null);
		validateCarCarriage(d2.getTicket().getCarCarriageReservation());
		assert(d2.getToken() != null);
		assert(UperEncoder.hexStringFromBytes(d2.getToken().getToken()).equals("82DA"));
		assert(d2.getToken().getTokenProviderIA5().equals("VDV"));
		assert(d2.getToken().tokenProviderNum == 123);
		assert(d2.getToken().getTokenSpecification().equals("TEST"));
		
		DocumentData d3b = t.getTransportDocument().get(2);
		assert(d3b != null);
		assert(d3b.getTicket().getOpenTicket() != null);
		validateOpenTicket(d3b.getTicket().getOpenTicket());
		
		DocumentData d3 = t.getTransportDocument().get(3);
		assert(d3 != null);
		assert(d3.getTicket().getPass() != null);
		validatePass(d3.getTicket().getPass());
		
		DocumentData d4 = t.getTransportDocument().get(4);
		assert(d4 != null);
		assert(d4.getTicket().getVoucher() != null);
		validateVoucher(d4.getTicket().getVoucher());
		
		
		DocumentData d5 = t.getTransportDocument().get(5);
		assert(d5 != null);
		assert(d5.getTicket().getCustomerCard() != null);
		validateCustomerCard(d5.getTicket().getCustomerCard());
	
		DocumentData d7 = t.getTransportDocument().get(6);
		assert(d7 != null);
		assert(d7.getTicket().getCounterMark() != null);
		validateCounterMark(d7.getTicket().getCounterMark());
		
		DocumentData d8 = t.getTransportDocument().get(7);
		assert(d8 != null);
		assert(d8.getTicket().getParkingGround() != null);
		validateParking(d8.getTicket().getParkingGround());
		
		DocumentData d9 = t.getTransportDocument().get(8);
		assert(d9 != null);
		assert(d9.getTicket().getFipTicket() != null);
		validateFip(d9.getTicket().getFipTicket());
		
		DocumentData d10 = t.getTransportDocument().get(9);
		assert(d10 != null);
		assert(d10.getTicket().getStationPassage() != null);
		validateStationPassage(d10.getTicket().getStationPassage());
		
		DocumentData d11 = t.getTransportDocument().get(10);
		assert(d11 != null);
		assert(d11.getTicket().getExtension() != null);
		
		DocumentData d12 = t.getTransportDocument().get(11);
		assert(d12 != null);
		assert(d12.getTicket().getDelayConfirmation() != null);
		validateDelay(d12.getTicket().getDelayConfirmation());
		
		
		assert(t.getTravelerDetail() != null);
		assert(t.getTravelerDetail().getGroupName().equals("myGroup"));
		assert(t.getTravelerDetail().getPreferedLanguage().equals("EN"));
		assert(t.getTravelerDetail().getTraveler() != null);
		assert(t.getTravelerDetail().getTraveler().size() == 1);
 
		assert(t.getTravelerDetail().getTraveler().get(0).getCountryOfIdCard() == 103);
		assert(t.getTravelerDetail().getTraveler().get(0).getCountryOfPassport() == 102);
		assert(t.getTravelerDetail().getTraveler().get(0).getCountryOfResidence() == 101);
		assert(t.getTravelerDetail().getTraveler().get(0).getCustomerIdIA5().equals("DZE5gT"));
		assert(t.getTravelerDetail().getTraveler().get(0).getCustomerIdNum() == 12345);
		assert(t.getTravelerDetail().getTraveler().get(0).getYearOfBirth() == 1901);
		assert(t.getTravelerDetail().getTraveler().get(0).getDayOfBirth()  == 31);
		assert(t.getTravelerDetail().getTraveler().get(0).getFirstName().equals("John"));
		assert(t.getTravelerDetail().getTraveler().get(0).getGender().equals(GenderType.male));
		assert(t.getTravelerDetail().getTraveler().get(0).getIdCard().equals("12345"));
		assert(t.getTravelerDetail().getTraveler().get(0).getLastName().equals("Dow"));
		assert(t.getTravelerDetail().getTraveler().get(0).getMonthOfBirth() == 12);
		assert(t.getTravelerDetail().getTraveler().get(0).getPassengerType().equals(PassengerType.senior));
		assert(t.getTravelerDetail().getTraveler().get(0).getPassengerWithReducedMobility() == false);
		assert(t.getTravelerDetail().getTraveler().get(0).getPassportId().equals("JDTS"));
		assert(t.getTravelerDetail().getTraveler().get(0).getSecondName().equals("Little"));
		assert(t.getTravelerDetail().getTraveler().get(0).getTitle().equals("PhD"));
		assert(t.getTravelerDetail().getTraveler().get(0).getTicketHolder() == true);
		assert(t.getTravelerDetail().getTraveler().get(0).getStatus() != null);
		assert(t.getTravelerDetail().getTraveler().get(0).getStatus().size() == 1);
		assert(t.getTravelerDetail().getTraveler().get(0).getStatus().get(0).getCustomerStatus() == 1);
		assert(t.getTravelerDetail().getTraveler().get(0).getStatus().get(0).getCustomerStatusDescr().equals("senior"));
	}





	private void validateCounterMark(CountermarkData t) {
		
		assert(t != null);
		
		assert(t.getReferenceNum().longValue() ==        810123456789L);
		assert(t.getReferenceIA5().equals("810123456789"));
    	assert(t.getProductOwnerNum() == 	  23456);  
    	assert(t.getProductOwnerIA5().equals(	  "23456"));    
		assert(t.getProductIdNum() == 		  65535);   
		assert(t.getProductIdIA5().equals(		  "123456"));    			
		assert(t.getTicketReferenceIA5().equals(	 "810123456789"));		     
		assert(t.getTicketReferenceNum() ==  810123456789L);
		assert(t.getNumberOfCountermark() ==  12L);
        assert(t.getTotalOfCountermarks() ==  24L);
        assert(t.getGroupName().equals(	           "groupName"));
		assert(t.getReturnIncluded() == false);	      
			  
		assert(t.getStationCodeTable().equals(CodeTableType.stationERA));
		assert(t.getFromStationNum() == 8100001);
		assert(t.getFromStationIA5().equals("8100001"));
		assert(t.getToStationNum()   ==      8000002);
		assert(t.getToStationIA5().equals(  "8100002"));
		assert(t.getFromStationNameUTF8().equals(  "A-STATION")); 
		assert(t.getToStationNameUTF8().equals(    "B-STATION"));				  

		assert(t.getValidRegionDesc().equals("From A to B via C"));		   
			  
		assert(t.getValidRegion() != null);
		assert(t.getValidRegion().size() == 1);
		assert(t.getValidRegion().get(0).getViaStations() != null);
	
	    assert(t.getReturnDescription() != null);
	    assert(t.getReturnDescription().getFromStationNum() ==  8100001);
	    assert(t.getReturnDescription().getFromStationIA5().equals("8100001"));
		assert(t.getReturnDescription().getToStationNum() ==         8000002);
	    assert(t.getReturnDescription().getToStationIA5().equals(         "8100002"));
	    assert(t.getReturnDescription().getFromStationNameUTF8().equals(  "A-STATION"));
		assert(t.getReturnDescription().getToStationNameUTF8().equals(    "B-STATION")); 	
		assert(t.getReturnDescription().getValidReturnRegionDesc().equals( "return"));
		assert(t.getReturnDescription().getValidReturnRegion() != null);
		assert(t.getReturnDescription().getValidReturnRegion().size() == 1);
        
	    assert(t.getValidFromDay() ==  700);
	    assert(t.getValidFromTime() ==  0);
	    assert(t.getValidFromUTCOffset() ==     60); 
	    assert(t.getValidUntilDay() ==  370);
	    assert(t.getValidUntilTime() ==  1439);
	    assert(t.getValidUntilUTCOffset() ==    10);  
		 		  
		assert(t.getClassCode().equals(TravelClassType.first));		
	
    	assert(t.getCarriersNum() != null);
    	assert(t.getCarriersNum().size() == 2);
    	assert(t.getCarriersNum().get(0) == 1080);
    	assert(t.getCarriersNum().get(1) == 1181);		
    
    	assert(t.getCarriersIA5() != null);
    	assert(t.getCarriersIA5().size() == 2);
    	assert(t.getCarriersIA5().get(0).equals("1080"));
    	assert(t.getCarriersIA5().get(1).equals("1181"));		
	
        assert(t.getIncludedServiceBrands() != null);
        assert(t.getIncludedServiceBrands().size() == 2);
        assert(t.getIncludedServiceBrands().get(0) == 108);
        assert(t.getIncludedServiceBrands().get(1) == 118);
     
        assert(t.getExcludedServiceBrands() != null);
        assert(t.getExcludedServiceBrands().size() == 2);
        assert(t.getExcludedServiceBrands().get(0) == 108);
        assert(t.getExcludedServiceBrands().get(1) == 118);		
	
   
        assert (t.getInfoText().equals("counterMark"));
		
		assert(t.getExtension() != null);

		
	}

	private void validateDelay(DelayConfirmation t) {
	    assert(t != null);
	    
	    assert(t.getTrainNum() == 100); 						
	    assert(t.getTrainIA5().equals("100"));     						
	    assert(t.getPlannedArrivalYear() ==  2022); 
	    assert(t.getPlannedArrivalDay() ==  12); 
	    assert(t.getPlannedArrivalTime() ==  1000); 
	    assert(t.getDepartureUTCOffset() == 30);
	    assert(t.getReferenceIA5().equals("ABDJ12345"));	
	    assert(t.getReferenceNum().intValue() == 12345);			
	    
	    assert(t.getStationNum() ==  8000001);
	    assert(t.getStationIA5().equals("DJE"));
	    assert(t.getDelay() ==      31);
	    assert(t.getTrainCancelled() == false);
	    assert(t.getConfirmationType().equals(ConfirmationTypeType.travelerDelayConfirmation));
	    assert(t.getAffectedTickets() != null);
	    assert(t.getAffectedTickets().size() == 1);
	    assert(t.getAffectedTickets().get(0).getReferenceIA5().equals("KDJET"));
	    assert(t.getAffectedTickets().get(0).getReferenceNum() ==    801234567890L);
        assert(t.getAffectedTickets().get(0).getIssuerName().equals(      "XYZ"));
        assert(t.getAffectedTickets().get(0).getIssuerPNR().equals(       "LDWDUR45"));
        assert(t.getAffectedTickets().get(0).getProductOwnerNum() == 1080);
        assert(t.getAffectedTickets().get(0).getProductOwnerIA5().equals( "IEFHU"));
        assert(t.getAffectedTickets().get(0).getTicketType().equals(TicketType.openTicket));
        assert(t.getAffectedTickets().get(0).getLinkMode().equals( LinkMode.issuedTogether));
        
        assert(t.getInfoText().equals( "delay confirmation"));
        assert(t.getExtension() != null);
        
	}

	private void validateCustomerCard(CustomerCardData c) {
		assert(c != null);
		assert (c.getExtension() != null);	
		assert (c.getCustomer() != null);
		assert (c.getCustomer() != null);

		assert (c.getCardIdIA5().equals("2345")); 
		assert (c.getCardIdNum().intValue() == 		123456);
		assert (c.getValidFromYear() == 	2269);
		assert (c.getValidFromDay() ==		2);
	    assert (c.getValidUntilYear() == 	1);    
	    assert (c.getValidUntilDay() == 	5);    
	    assert (c.getClassCode().equals(TravelClassType.second));
	    assert (c.getCardType() ==			15);
	    assert (c.getCardTypeDescr().equals(	"RAILPLUS"));	
	    assert (c.getCustomerStatus() ==   1);  
	    assert (c.getCustomerStatusDescr().equals( "gold"));      
	    assert (c.getIncludedServices() != null);
	    assert (c.getIncludedServices().size() == 2);
	    assert (c.getIncludedServices().get(0) == 1);
	    assert (c.getIncludedServices().get(1) == 2);
		
	}

	private void validateVoucher(VoucherData v) {
		assert(v != null);
		assert (v.getExtension() != null);
		
	    assert(v.getReferenceIA5().equals("810123456789"));	
	    assert(v.getReferenceNum().longValue() == 810123456789L);			     		        															
	    assert(v.getProductOwnerNum() == 23456);    
	    assert(v.getProductOwnerIA5().equals("COFFEEMACHINE"));    
	    assert(v.getProductIdNum() == 65535);    
	    assert(v.getProductIdIA5().equals("123456")); 

	    assert(v.getValidFromYear() == 2022);
	    assert(v.getValidFromDay() == 1);
	    assert(v.getValidUntilYear() == 2022);
        assert(v.getValidUntilDay() == 1);
        assert(v.getValue() == 500);
        assert(v.getType() == 123);
        assert(v.getInfoText().equals("coffee voucher"));

	}

	private void validateStationPassage(StationPassageData t) {
		assert(t != null);
		assert(t.getExtension() != null);
		
	    assert(t.getReferenceIA5().equals("810123456789"));	
	    assert(t.getReferenceNum().longValue() == 810123456789L);			     		        															
	    assert(t.getProductOwnerNum() == 23456);    
	    assert(t.getProductOwnerIA5().equals("23456"));    
	    assert(t.getProductIdNum() == 65535);    
	    assert(t.getProductIdIA5().equals("123456")); 
			      
	    assert(t.getProductName().equals("passage"));
        assert(t.getStationCodeTable().equals(CodeTableType.stationUIC));
	    assert(t.getStationNum() != null);
	    assert(t.getStationNum().size() == 1);
   	    assert(t.getStationNum().get(0) == 8200001);	    	
	    assert(t.getStationIA5() != null);
	    assert(t.getStationIA5().size() == 1);
	    assert(t.getStationIA5().get(0).equals("AMS"));
	    assert(t.getStationNameUTF8() != null);
	    assert(t.getStationNameUTF8().size() == 1);
	    assert(t.getStationNameUTF8().get(0).equals("Amsterdam"));
	    assert(t.getAreaCodeNum() != null);
	    assert(t.getAreaCodeNum().size() == 1);
	    assert(t.getAreaCodeNum().get(0) == 8200001);	    	    		
	    assert(t.getAreaCodeIA5() != null);
	    assert(t.getAreaCodeIA5().size() == 1);
		assert(t.getAreaCodeIA5().get(0).equals("AMS"));
	    assert(t.getAreaNameUTF8() != null);
	    assert(t.getAreaNameUTF8().size() == 1);
	    assert(t.getAreaNameUTF8().get(0).equals("Amsterdam"));
	    assert(t.getValidFromDay() ==  5);
	    assert(t.getValidFromTime() ==  0);
	    assert(t.getValidFromUTCOffset() ==  1);
	    assert(t.getValidUntilDay() ==  5);
	    assert(t.getValidUntilTime() ==  1000);
	    assert(t.getValidUntilUTCOffset() ==  1);
	    assert(t.getNumberOfDaysValid() == 5);

	}

	private void validateParking(ParkingGroundData t) {
		assert(t != null);
		assert(t.getExtension() != null);
		
	    assert(t.getReferenceIA5().equals("810123456789"));	
	    assert(t.getReferenceNum().longValue() == 810123456789L);			     		        															
	    assert(t.getProductOwnerNum() == 23456);    
	    assert(t.getProductOwnerIA5().equals("23456"));    
	    assert(t.getProductIdNum() == 65535);    
	    assert(t.getProductIdIA5().equals("123456")); 
		
       
	    assert(t.getParkingGroundId().equals( 	  "IA5"));
	    assert(t.getFromParkingDate() ==     370);        
	    assert(t.getToParkingDate() ==    370);
        
        
	    assert(t.getAccessCode().equals(          "4ga"));
	    assert(t.getLocation().equals(            "Parking Frankfurt Main West"));
	      assert(t.getStationCodeTable().equals(CodeTableType.stationUIC));
	      assert(t.getStationNum() ==          8000001);
	      assert(t.getStationIA5().equals(          "8000001"));
	      assert(t.getSpecialInformation().equals(  "outdoor parking"));
	      assert(t.getEntryTrack().equals(          "left"));
	      assert(t.getNumberPlate().equals(         "AA-DE-12345"));
	      assert(t.getPrice() ==               500);	
	      assert(t.getVatDetails() != null);
	      assert(t.getVatDetails().size() == 1);
		  
		
	}

	private void validateFip(FIPTicketData t) {
		assert(t != null);
		assert(t.getExtension() != null);
		
	    assert(t.getReferenceIA5().equals("810123456789"));	
	    assert(t.getReferenceNum().longValue() == 810123456789L);			     		        															
	    assert(t.getProductOwnerNum() == 23456);    
	    assert(t.getProductOwnerIA5().equals("23456"));    
	    assert(t.getProductIdNum() == 65535);    
	    assert(t.getProductIdIA5().equals("123456")); 
	    
 				   		
	    assert(t.getValidFromDay() ==		  -367);   		        	 
        assert(t.getValidUntilDay() ==	      -1);							
        assert(t.getActivatedDay() != null);     
        assert(t.getActivatedDay().size() == 4);    
        assert(t.getActivatedDay().get(0) == 1);
       	assert(t.getActivatedDay().get(1) ==  13);
        assert(t.getActivatedDay().get(2) ==  14); 
       	assert(t.getActivatedDay().get(3) == 15); 
       	
	    assert(t.getCarrierNum()!= null);
	    assert(t.getCarrierNum().size() == 2);
	    assert(t.getCarrierNum().get(0) == 1080);
	    assert(t.getCarrierNum().get(1) == 1181);
	 
	    assert(t.getCarrierIA5() != null);
	    assert(t.getCarrierIA5().size() == 2);
	    assert(t.getCarrierIA5().contains("1080"));
	    assert(t.getCarrierIA5().contains("1181"));       	
       	
        assert(t.getNumberOfTravelDays() ==  8);                  
        assert(t.getIncludesSupplements() == true);			
        assert(t.getClassCode().equals(TravelClassType.first));

	}

	private void validateOpenTicket(OpenTicketData t) {
		
			assert (t != null);
		
			assert(t.getReferenceNum().longValue() ==        810123456789L);
			assert(t.getReferenceIA5().equals("810123456789"));
        	assert(t.getProductOwnerNum() == 	  23456);  
        	assert(t.getProductOwnerIA5().equals(	  "23456"));    
			assert(t.getProductIdNum() == 		  65535);   
			assert(t.getProductIdIA5().equals(		  "123456"));    			
			assert(t.getExtIssuerId() ==         12);
			assert(t.getIssuerAutorizationId() == 13);	
			assert(t.getReturnIncluded() == false);	      
				  
			assert(t.getStationCodeTable().equals(CodeTableType.stationERA));
			assert(t.getFromStationNum() == 8100001);
			assert(t.getFromStationIA5().equals("8100001"));
			assert(t.getToStationNum()   ==      8000002);
			assert(t.getToStationIA5().equals(  "8100002"));
			assert(t.getFromStationNameUTF8().equals(  "A-STATION")); 
			assert(t.getToStationNameUTF8().equals(    "B-STATION"));				  
 
			assert(t.getValidRegionDesc().equals("From A to B via C"));		   
				  
			assert (t.getValidRegion() != null);
			assert(t.getValidRegion().size() == 5);
			assert(t.getValidRegion().get(0).getViaStations() != null);
			validateRegion(t.getValidRegion().get(0).getViaStations());
			validateRegion(t.getValidRegion().get(1).getZones());
			validateRegion(t.getValidRegion().get(2).getLines());
			validateRegion(t.getValidRegion().get(3).getTrainLink());
			validateRegion(t.getValidRegion().get(4).getPolygone());
		
		    assert(t.getReturnDescription() != null);
		    assert(t.getReturnDescription().getFromStationNum() ==  8100001);
		    assert(t.getReturnDescription().getFromStationIA5().equals("8100001"));
			assert(t.getReturnDescription().getToStationNum() ==         8000002);
		    assert(t.getReturnDescription().getToStationIA5().equals(         "8100002"));
		    assert(t.getReturnDescription().getFromStationNameUTF8().equals(  "A-STATION"));
			assert(t.getReturnDescription().getToStationNameUTF8().equals(    "B-STATION")); 	
			assert(t.getReturnDescription().getValidReturnRegionDesc().equals( "return"));
			assert(t.getReturnDescription().getValidReturnRegion() != null);
			assert(t.getReturnDescription().getValidReturnRegion().size() == 1);
            
		    assert(t.getValidFromDay() ==  700);
		    assert(t.getValidFromTime() ==  0);
		    assert(t.getValidFromUTCOffset() ==     60); 
		    assert(t.getValidUntilDay() ==  370);
		    assert(t.getValidUntilTime() ==  1439);
		    assert(t.getValidUntilUTCOffset() ==    10);  
			 
	        assert(t.getActivatedDay() != null);
	        assert(t.getActivatedDay().size() == 2);
	        assert(t.getActivatedDay().get(0) == 1);
	        assert(t.getActivatedDay().get(1) == 2);			  
			   
			assert(t.getClassCode().equals(TravelClassType.first));
			assert(t.getServiceLevel().equals("A"));		
		
	    	assert(t.getCarriersNum() != null);
	    	assert(t.getCarriersNum().size() == 2);
	    	assert(t.getCarriersNum().get(0) == 1080);
	    	assert(t.getCarriersNum().get(1) == 1181);		
	    
        	assert(t.getCarriersIA5() != null);
        	assert(t.getCarriersIA5().size() == 2);
        	assert(t.getCarriersIA5().get(0).equals("1080"));
        	assert(t.getCarriersIA5().get(1).equals("1181"));		
		
	        assert(t.getIncludedServiceBrands() != null);
	        assert(t.getIncludedServiceBrands().size() == 2);
	        assert(t.getIncludedServiceBrands().get(0) == 108);
	        assert(t.getIncludedServiceBrands().get(1) == 118);
	     
	        assert(t.getExcludedServiceBrands() != null);
	        assert(t.getExcludedServiceBrands().size() == 2);
	        assert(t.getExcludedServiceBrands().get(0) == 108);
	        assert(t.getExcludedServiceBrands().get(1) == 118);		
		
        	assert(t.getTariffs() != null);
        	assert(t.getTariffs().size() == 1);		
				    
		    assert (t.getPrice() ==  12345);
            assert (t.getVatDetails() != null);
            assert (t.getVatDetails().size() == 1);
       
	        assert (t.getInfoText().equals("openTicketInfo"));
		
			assert (t.getIncludedAddOns() != null);
			assert (t.getIncludedAddOns().size() == 1);
			validate(t.getIncludedAddOns().get(0));
			
			assert(t.getLuggage() != null);
			
			assert(t.getIncludedTransportTypes() != null);
			assert(t.getIncludedTransportTypes().size() == 2);
			assert(t.getIncludedTransportTypes().get(0) == 10);
			assert(t.getIncludedTransportTypes().get(1) == 11);
			
			assert(t.getExcludedTransportTypes() != null);
			assert(t.getExcludedTransportTypes().size() == 2);
			assert(t.getExcludedTransportTypes().get(0) == 10);
			assert(t.getExcludedTransportTypes().get(1) == 18);
			
			assert(t.getExtension() != null);
		
	}

	private void validateRegion(TrainLinkType t) {
		
		assert (t != null);
		
	    assert(t.getTrainNum() == 12345); 						
	    assert(t.getTrainIA5().equals("12345"));     						
	    assert(t.getTravelDate() ==  2);
	    assert(t.getDepartureTime() == 		 1439);           
	    assert(t.departureUTCOffset ==   -60); 	    

	    assert(t.getFromStationNum() == 8100001);
	    assert(t.getFromStationIA5().equals("8100001"));
	    assert(t.getToStationNum()   ==      8000002);
	    assert(t.getToStationIA5().equals(  "8100002"));
	    assert(t.getFromStationName().equals(  "A-STATION")); 
	    assert(t.getToStationName().equals(    "B-STATION")); 
	         
	}

	private void validateRegion(PolygoneType p) {
		
		assert(p != null);
		assert(p.getFirstEdge() != null);
		assert(p.getFirstEdge().getLongitude() == 12345);
        assert(p.getFirstEdge().getLatitude() ==  56789);						
		assert(p.getEdges() != null);
		assert(p.getEdges().size() == 2);
		assert(p.getEdges().get(0).getLongitude().intValue() == 12345);
		assert(p.getEdges().get(0).getLatitude().intValue() == 56789);
		assert(p.getEdges().get(1).getLongitude().intValue() == 12345);
		assert(p.getEdges().get(1).getLatitude().intValue() == 56789);

	}

	private void validateRegion(ZoneType z) {
		
			assert(z != null);
		
			assert(z.getCarrierNum() == 1080);
			assert(z.getCarrierIA5().equals("1080"));
			assert(z.getStationCodeTable().equals(CodeTableType.stationERA));
			assert(z.getEntryStationNum() ==         1234);
            assert(z.getEntryStationIA5().equals(        "1234"));
            assert(z.getTerminatingStationNum() ==   2345);
            assert(z.getTerminatingStationIA5().equals(  "2345"));
            assert(z.getCity().intValue() ==                    123456);
            assert(z.getZoneId() != null);
            assert(z.getZoneId().size() == 2);
            assert(z.getZoneId().get(0) == 100);
            assert(z.getZoneId().get(1) == 200);
           
            assert(UperEncoder.hexStringFromBytes(z.getBinaryZoneId()).equals("82DA"));
            assert(z.getNutsCode().equals("DE4711"));
        
	}

	private void validateRegion(LineType z) {
		
		assert(z.getCarrierNum() == 1080);
		assert(z.getCarrierIA5().equals("1080"));
		assert(z.getStationCodeTable().equals(CodeTableType.stationERA));
		assert(z.getEntryStationNum() ==         1234);
        assert(z.getEntryStationIA5().equals(        "1234"));
        assert(z.getTerminatingStationNum() ==   2345);
        assert(z.getTerminatingStationIA5().equals(  "2345"));
        assert(z.getCity().intValue() ==                    123456);
        assert(z.getLineId() != null);
        assert(z.getLineId().size() == 2);
        assert(z.getLineId().get(0) == 100);
        assert(z.getLineId().get(1) == 200);
       	
	}

	private void validateRegion(ViaStationType t) {
		
			assert( t != null);
		
			assert(t.getRoute() != null);
			
			assert(t.getRoute().size() == 4);
			
			assert(t.getRoute().get(0).getStationNum() == 123455);
			assert(t.getRoute().get(0).getStationIA5().equals("123455"));
			assert(t.getRoute().get(0).getBorder() == false);

			assert(t.getRoute().get(1).getStationNum() == 123456);
			assert(t.getRoute().get(1).getBorder() == false);
			
			assert(t.getRoute().get(2).getAlternativeRoutes() != null);
			assert(t.getRoute().get(2).getAlternativeRoutes().size() == 2);
			assert(t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute() != null);
			assert(t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().size() == 2);
			assert(t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(0).getStationNum() == 23455);
			assert(t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).getStationNum() == 23456);
			assert(t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute() != null);
			assert(t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().size() == 2);
			assert(t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(0).getStationNum() == 3455);
			assert(t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(1).getStationNum() == 3456);
			
			assert(t.getRoute().get(3).getStationNum() == 123457);	          
	  	          
	  	    assert(t.getBorder() == false);
		          
		    assert(t.getSeriesId() == 999);
		    assert(t.getRouteId() == 21);
		    	  
		    assert(t.getIncludedServiceBrands() != null);
		    assert(t.getIncludedServiceBrands().size() == 2);
		    assert(t.getIncludedServiceBrands().get(0) == 108);
		    assert(t.getIncludedServiceBrands().get(1) == 118);
		       
		    assert(t.getExcludedServiceBrands() != null);
		    assert(t.getExcludedServiceBrands().size() == 2);
		    assert(t.getExcludedServiceBrands().get(0) == 108);
		    assert(t.getExcludedServiceBrands().get(1) == 118);
		
	}

	private void validate(IncludedOpenTicketType t) {

		assert(t.getProductOwnerNum() == 23456);    
		assert(t.getProductOwnerIA5().equals("23456"));    
		assert(t.getProductIdNum() == 		 65535);    
		assert(t.getProductIdIA5().equals(		 "123456"));
		assert(t.getExternalIssuerId() ==     12);
		assert(t.getIssuerAutorizationId() == 13);		
		assert(t.getStationCodeTable().equals(CodeTableType.stationERA));    	         
		assert(t.getValidRegion() != null);
		assert(t.getValidRegion().size() == 1);
		assert(t.getValidFromDay() ==  0);
		assert(t.getValidFromTime() ==  1000);
		assert(t.getValidUntilDay() ==  1);
		assert(t.getValidUntilTime() ==  1000);
		assert(t.getClassCode().equals(TravelClassType.second));
		assert(t.getServiceLevel().equals("A"));
				
	    assert(t.getIncludedCarriersNum() != null);
	    assert(t.getIncludedCarriersNum().size() == 2);
	    assert(t.getIncludedCarriersNum().get(0) == 1080);
	    assert(t.getIncludedCarriersNum().get(1) == 1181);		
	    
        assert(t.getIncludedCarriersIA5() != null);
        assert(t.getIncludedCarriersIA5().size() == 2);
        assert(t.getIncludedCarriersIA5().get(0).equals("1080"));
        assert(t.getIncludedCarriersIA5().get(1).equals("1181"));
	    
   
        
        assert(t.getIncludedServiceBrands() != null);
        assert(t.getIncludedServiceBrands().size() == 2);
        assert(t.getIncludedServiceBrands().get(0) == 108);
        assert(t.getIncludedServiceBrands().get(1) == 118);
     
        assert(t.getExcludedServiceBrands() != null);
        assert(t.getExcludedServiceBrands().size() == 2);
        assert(t.getExcludedServiceBrands().get(0) == 108);
        assert(t.getExcludedServiceBrands().get(1) == 118);
               		
        assert(t.getTariffs() != null);
        assert(t.getTariffs().size() == 1);
                            
		assert(t.getInfoText().equals("included ticket"));
				
		assert(t.getIncludedTransportTypes() != null);
		assert(t.getIncludedTransportTypes().size() == 2);
		assert(t.getIncludedTransportTypes().get(0) == 10);
		assert(t.getIncludedTransportTypes().get(1) == 11);
				
		assert(t.getExcludedTransportTypes() != null);
		assert(t.getExcludedTransportTypes().size() == 2);
		assert(t.getExcludedTransportTypes().get(0) == 10);
		assert(t.getExcludedTransportTypes().get(1) == 18);				
		
        assert(t.getExtension() != null);
        		    	        
		 
		
	}

	private void validatePass(PassData p) {

		assert(p.getReferenceNum().longValue() ==        810123456789L);
		assert(p.getReferenceIA5().equals("810123456789"));
        assert(p.getProductOwnerNum() == 	  23456);  
        assert(p.getProductOwnerIA5().equals(	  "23456"));    
		assert(p.getProductIdNum() == 		  65535);   
		assert(p.getProductIdIA5().equals(		  "123456"));    			  
		assert(p.getPassType() ==             2);
		assert(p.getPassDescription().equals(  "Eurail FlexPass"));
		assert(p.getClassCode().equals(TravelClassType.first));
	    assert(p.getValidFromDay() ==         0);
	    assert(p.getValidFromTime() ==        1000);
	    assert(p.getValidFromUTCOffset() ==   1);
	    assert(p.getValidUntilDay() ==        1);
	    assert(p.getValidUntilTime() ==       1000);
	    assert(p.getValidUntilUTCOffset() ==  1);
	    assert(p.getValidityPeriodDetails() != null);
    	  
        assert(p.getValidityPeriodDetails() != null);
        assert(p.getValidityPeriodDetails().getValidityPeriod() != null);
        assert(p.getValidityPeriodDetails().getValidityPeriod().size() == 1);
        assert(p.getValidityPeriodDetails().getValidityPeriod().get(0).getValidFromDay() == 0);
        assert(p.getValidityPeriodDetails().getValidityPeriod().get(0).getValidFromTime() == 1000);
	    assert(p.getValidityPeriodDetails().getValidityPeriod().get(0).getValidFromUTCOffset() == 1);
	    assert(p.getValidityPeriodDetails().getValidityPeriod().get(0).getValidUntilDay() == 1);
	    assert(p.getValidityPeriodDetails().getValidityPeriod().get(0).getValidUntilTime() == 1000);
	    assert(p.getValidityPeriodDetails().getValidityPeriod().get(0).getValidUntilUTCOffset() == 1);

	    assert(p.getValidityPeriodDetails().getExcludedTimeRange() != null);
	    assert(p.getValidityPeriodDetails().getExcludedTimeRange().get(0).getFromTime() == 6);
	    assert(p.getValidityPeriodDetails().getExcludedTimeRange().get(0).getUntilTime() == 9);

	    assert(p.getNumberOfValidityDays() == 5);
        assert(p.getTrainValidity() != null);
        
        assert(p.getTrainValidity().getValidFromDay() == 0);
        assert(p.getTrainValidity().getValidFromTime() ==  1000);
        assert(p.getTrainValidity().getValidUntilDay() == 1);
	    assert(p.getTrainValidity().getValidUntilTime() == 1000);
	    assert(p.getTrainValidity().getIncludedCarriersNum() != null);
	    assert(p.getTrainValidity().getIncludedCarriersNum().size() == 2);
	    assert(p.getTrainValidity().getIncludedCarriersNum().get(0) == 1234);
	    assert(p.getTrainValidity().getIncludedCarriersNum().get(1) == 5678);
	    
        assert(p.getTrainValidity().getBordingOrArrival().equals(BoardingOrArrivalType.boarding));
      
        assert(p.getNumberOfPossibleTrips() ==        3);
          assert(p.getNumberOfDaysOfTravel() ==         10);
          assert(p.getActivatedDay() != null);
          assert(p.getActivatedDay().size() == 2);
          assert(p.getActivatedDay().get(0) == 200);
          assert(p.getActivatedDay().get(1) == 201);
      
          assert(p.getCountries() != null);
          assert(p.getCountries().size() == 2);
          assert(p.getCountries().get(0) == 10);
          assert(p.getCountries().get(1) == 20);
          
          assert(p.getIncludedCarriersNum() != null);
          assert(p.getIncludedCarriersNum().size() == 2);
          assert(p.getIncludedCarriersNum().get(0) == 1080);
          assert(p.getIncludedCarriersNum().get(1) == 1181);
          
          assert(p.getIncludedCarriersIA5() != null);
          assert(p.getIncludedCarriersIA5().size() == 2);
          assert(p.getIncludedCarriersIA5().get(0).equals("1080"));
          assert(p.getIncludedCarriersIA5().get(1).equals("1181"));
    
          assert(p.getExcludedCarriersNum() != null);
          assert(p.getExcludedCarriersNum().size() == 2);
          assert(p.getExcludedCarriersNum().get(0) == 1080);
          assert(p.getExcludedCarriersNum().get(1) == 1181);
   
          assert(p.getExcludedCarriersIA5() != null);
          assert(p.getExcludedCarriersIA5().size() == 2);
          assert(p.getExcludedCarriersIA5().get(0).equals("1080"));
          assert(p.getExcludedCarriersIA5().get(1).equals("1181"));
      
          assert(p.getIncludedServiceBrands() != null);
          assert(p.getIncludedServiceBrands().size() == 2);
          assert(p.getIncludedServiceBrands().get(0) == 108);
          assert(p.getIncludedServiceBrands().get(1) == 118);
       
          assert(p.getExcludedServiceBrands() != null);
          assert(p.getExcludedServiceBrands().size() == 2);
          assert(p.getExcludedServiceBrands().get(0) == 108);
          assert(p.getExcludedServiceBrands().get(1) == 118);
       
        assert(p.getValidRegion() != null);
        assert(p.getValidRegion().size() == 1);  
        assert(p.getTariffs() != null);
        assert(p.getTariffs().size() == 1);
        assert(p.getPrice() == 10000);    
        assert(p.getVatDetails() != null);
        assert(p.getVatDetails().size() == 1);
        assert(p.getInfoText().equals("pass info"));
		assert (p.getExtension() != null);	
		
		
	}

	private void validateReservation(ReservationData r) {
		
	    assert(r.getTrainNum() == 12345); 						
	    assert(r.getTrainIA5().equals("12345"));     						
	    assert(r.getDepartureDate() ==  2); 
	    assert(r.getReferenceIA5().equals("810123456789"));	
	    assert(r.getReferenceNum() == 80123456789L);			     		        															
	    assert(r.getProductOwnerNum() == 23456);    
	    assert(r.getProductOwnerIA5().equals("23456"));    
	    assert(r.getProductIdNum() == 65535);    
	    assert(r.getProductIdIA5().equals("123456")); 
	    assert(r.getServiceBrand() == 12);
	    assert(r.getServiceBrandAbrUTF8().equals("TGV"));      					   	
	    assert(r.getServiceBrandNameUTF8().equals("Lyria"));     					    
	    assert(r.getService().equals(ServiceType.couchette));		
	    
	    assert(r.getStationCodeTable().equals(CodeTableType.stationUIC));
	    assert(r.getFromStationNum() == 8100001);
	    assert(r.getFromStationIA5().equals("8100001"));
	    assert(r.getToStationNum()   ==      8000002);
	    assert(r.getToStationIA5().equals(  "8100002"));
	    assert(r.getFromStationNameUTF8().equals(  "A-STATION")); 
	    assert(r.getToStationNameUTF8().equals(    "B-STATION")); 
	    assert(r.getDepartureTime() == 		 1439);           
	    assert(r.getDepartureUTCOffset() ==   -60); 
	    assert(r.getArrivalDate() ==		     20);  			
	    assert(r.getArrivalTime() ==		     0);
	    assert(r.getArrivalUTCOffset() ==     10);  
	    assert(r.getCarrierNum()!= null);
	    assert(r.getCarrierNum().size() == 2);
	    assert(r.getCarrierNum().get(0) == 1080);
	    assert(r.getCarrierNum().get(1) == 1181);
	 
	    assert(r.getCarrierIA5() != null);
	    assert(r.getCarrierIA5().size() == 2);
	    assert(r.getCarrierIA5().contains("1080"));
	    assert(r.getCarrierIA5().contains("1181"));
	    assert(r.getClassCode().equals(TravelClassType.first));
	    assert(r.getServiceLevel().equals("A"));
	    
	    assert(r.getPlaces() != null);
	    assert(r.getPlaces().getCoach().equals("31A"));
	    assert(r.getPlaces().getPlaceString().equals("31-47"));
	    assert(r.getPlaces().getPlaceDescription().equals("Window"));
	    assert(r.getPlaces().getPlaceIA5() != null);
	    assert(r.getPlaces().getPlaceIA5().size() == 2);
	    assert(r.getPlaces().getPlaceIA5().get(0).equals("31A"));
	    assert(r.getPlaces().getPlaceIA5().get(1).equals("31B"));
	    assert(r.getPlaces().getPlaceNum() != null);
	    assert(r.getPlaces().getPlaceNum().size() == 2);
	    assert(r.getPlaces().getPlaceNum().get(0) == 31);
	    assert(r.getPlaces().getPlaceNum().get(1) == 32);
	    
	    assert(r.getAdditionalPlaces() != null);
	    assert(r.getBicyclePlaces() != null);
	    
	    
		assert(r.getCompartmentDetails() != null);
		assert(r.getCompartmentDetails().getCoachType() == 1); 
		assert(r.getCompartmentDetails().getCompartmentType() == 			99); 
		assert(r.getCompartmentDetails().getSpecialAllocation() == 		50);		  	
		assert(r.getCompartmentDetails().getCoachTypeDescr().equals("xwz"));    
		assert(r.getCompartmentDetails().getCompartmentTypeDescr().equals(	"xwz"));			
		assert(r.getCompartmentDetails().getSpecialAllocationDescr().equals(	"xwz")); 		
		assert(r.getCompartmentDetails().getPosition().equals(CompartmentPositionType.upperLevel)); 	
		
	    assert(r.getNumberOfOverbooked() ==  200);
	    assert(r.getBerth() != null);
	    assert(r.getBerth().size() == 1);
	    assert(r.getBerth().get(0).getBerthType().equals(BerthTypeType.single));
	    assert(r.getBerth().get(0).getGender().equals(CompartmentGenderType.female));
	    assert(r.getBerth().get(0).getNumberOfBerths() == 999);
	    
	    
	    assert(r.getTariff() != null);
	    assert(r.getTariff().size() == 1);
	    assert(r.getTariff().get(0).getNumberOfPassengers() == 1);

 
	    assert(r.getTariff().get(0).getPassengerType().equals(PassengerType.senior) );
       	assert(r.getTariff().get(0).getAgeBelow() ==           64);
       	assert(r.getTariff().get(0).getAgeAbove() ==           60);
       	assert(r.getTariff().get(0).getTraverlerid() != null);
       	assert(r.getTariff().get(0).getTraverlerid().size() == 1);
       	assert(r.getTariff().get(0).getTraverlerid().get(0) == 1);
       	
       	assert(r.getTariff().get(0).getRestrictedToCountryOfResidence() == false);
       	assert(r.getTariff().get(0).getRestrictedToRouteSection() != null);
       	assert(r.getTariff().get(0).getRestrictedToRouteSection().getStationCodeTable().equals(CodeTableType.stationERA));              
      	assert(r.getTariff().get(0).getRestrictedToRouteSection().getFromStationNum() == 123);
      	assert(r.getTariff().get(0).getRestrictedToRouteSection().getFromStationIA5().equals(      	"123"));
		assert(r.getTariff().get(0).getRestrictedToRouteSection().getToStationNum() ==       	234);     					                
		assert(r.getTariff().get(0).getRestrictedToRouteSection().getToStationIA5().equals(        	"234")); 				                 
		assert(r.getTariff().get(0).getRestrictedToRouteSection().getFromStationNameUTF8().equals( 	"A"));    
	    assert(r.getTariff().get(0).getRestrictedToRouteSection().getToStationNameUTF8().equals(   	"B"));

	    assert(r.getTariff().get(0).getSeriesDataDetails() != null);
	    assert(r.getTariff().get(0).getSeriesDataDetails().getSupplyingCarrier()   == 	12345);		
	    assert(r.getTariff().get(0).getSeriesDataDetails().getOfferIdentification() ==	99);  		
	    assert(r.getTariff().get(0).getSeriesDataDetails().getSeries() ==	23456);                     
              
        assert(r.getTariff().get(0).getTariffIdNum() ==       72);
       	assert(r.getTariff().get(0).getTariffIdIA5().equals(       "72"));
       	assert(r.getTariff().get(0).getTariffDesc().equals(         "Leasure Fare"));
       	assert(r.getTariff().get(0).getReductionCard() != null);
    	assert(r.getTariff().get(0).getReductionCard().size() == 1);
            
        assert(r.getTariff().get(0).getReductionCard().get(0).getCardIssuerNum().intValue() ==    1234);
        assert(r.getTariff().get(0).getReductionCard().get(0).getCardIssuerIA5().equals(   "1234"));
        assert(r.getTariff().get(0).getReductionCard().get(0).getCardIdNum().intValue() ==                 5678);
        assert(r.getTariff().get(0).getReductionCard().get(0).getCardIdIA5().equals(  "5678"));
		assert(r.getTariff().get(0).getReductionCard().get(0).getCardName().equals(   "testcard"));
		assert(r.getTariff().get(0).getReductionCard().get(0).getCardType().intValue() ==  123);
		assert(r.getTariff().get(0).getReductionCard().get(0).getLeadingCardIdNum().intValue() == 3456);                                     
		assert(r.getTariff().get(0).getReductionCard().get(0).getLeadingCardIdIA5().equals("3456"));
		assert(r.getTariff().get(0).getReductionCard().get(0).getTrailingCardIdNum().intValue() ==  100);
		assert(r.getTariff().get(0).getReductionCard().get(0).getTrailingCardIdIA5().equals("100"));
	       
	    
		assert(r.getPriceType().equals(PriceTypeType.travelPrice));
        assert(r.getPrice() == 12345);
        assert(r.getVatDetails() != null);
        assert(r.getVatDetails().size() == 1);
        assert(r.getVatDetails().get(0).getCountry() == 80);
        assert(r.getVatDetails().get(0).getPercentage() == 70);
        assert(r.getVatDetails().get(0).getAmount() == 10);
        assert(r.getVatDetails().get(0).getVatId().equals("IUDGTE"));
        
        assert(r.getTypeOfSupplement() ==	9);		
        assert(r.getNumberOfSupplements() ==	2);
		assert(r.getLuggage() != null);
        
		assert(r.getLuggage().getMaxHandLuggagePieces() ==    2);
		assert(r.getLuggage().getMaxNonHandLuggagePieces() == 1);
		assert(r.getLuggage().getRegisteredLuggage() != null);
		assert(r.getLuggage().getRegisteredLuggage().size() == 2);
		assert(r.getLuggage().getRegisteredLuggage().get(0).getRegistrationId().equals("IODHUV"));
		assert(r.getLuggage().getRegisteredLuggage().get(0).getMaxWeight() ==       20);
	    assert(r.getLuggage().getRegisteredLuggage().get(0).getMaxSize() ==        100);	  
		assert(r.getLuggage().getRegisteredLuggage().get(1).getRegistrationId().equals("XXDHUV"));
		assert(r.getLuggage().getRegisteredLuggage().get(1).getMaxWeight() ==       21);
	    assert(r.getLuggage().getRegisteredLuggage().get(1).getMaxSize() == 101);	
	    
	    assert(r.getInfoText().equals("reservation"));
	    assert(r.getExtension() != null);
	    
	}
	
	
	private void validateCarCarriage(CarCarriageReservationData r) {
		
	    assert(r.getTrainNum() == 123); 						
	    assert(r.getTrainIA5().equals("123"));     						
	    assert(r.getReferenceIA5().equals("810123456789"));	
	    assert(r.getReferenceNum() == 810123456789L);			     		        															
	    assert(r.getProductOwnerNum() == 23456);    
	    assert(r.getProductOwnerIA5().equals("23456"));    
	    assert(r.getProductIdNum() == 65535);    
	    assert(r.getProductIdIA5().equals("123456")); 
	    assert(r.getServiceBrand() == 100);
	    assert(r.getServiceBrandAbrUTF8().equals("AZ"));      					   	
	    assert(r.getServiceBrandNameUTF8().equals("special train"));     					    
        
        assert(r.getBeginLoadingDate() ==  10);
        assert(r.getBeginLoadingTime() ==    0);
        assert(r.getEndLoadingTime() ==      500);
        assert(r.getLoadingUTCOffset() ==    30);
        
	    assert(r.getStationCodeTable().equals(CodeTableType.stationERA));
	    assert(r.getFromStationNum() == 8100001);
	    assert(r.getFromStationIA5().equals("8100001"));
	    assert(r.getToStationNum()   ==      8000002);
	    assert(r.getToStationIA5().equals(  "8100002"));
	    assert(r.getFromStationNameUTF8().equals(  "A-STATION")); 
	    assert(r.getToStationNameUTF8().equals(    "B-STATION"));         
         
	    assert(r.getCoach().equals("21"));
	    assert(r.getPlace().equals("41"));
	    assert(r.getCompartmentDetails() != null);
	    	
	    assert(r.getCompartmentDetails().getCoachType()	==	1L);    
	    assert(r.getCompartmentDetails().getCompartmentType() ==	99L);    
	    assert(r.getCompartmentDetails().getSpecialAllocation()	==	50L);   				    	
	    assert(r.getCompartmentDetails().getCoachTypeDescr().equals("xwz"));  
		assert(r.getCompartmentDetails().getCompartmentTypeDescr().equals("xwz"));  			
		assert(r.getCompartmentDetails().getSpecialAllocationDescr().equals("xwz"));  		
		assert(r.getCompartmentDetails().getPosition().equals(CompartmentPositionType.upperLevel));   	
			        
		assert(r.getNumberPlate().equals(          "AD-DE-123"));
		assert(r.getTrailerPlate().equals(         "DX-AB-123"));
		assert(r.getCarCategory()	==	          3L);
        assert(r.getBoatCategory()	==	         5L);
        assert(r.getTextileRoof()	==	         false);
        assert(r.getRoofRackType().equals(RoofRackType.bicycleRack));
        assert(r.getRoofRackHeight()	==	       20L);
        assert(r.getAttachedBoats()	==	        2L);
        assert(r.getAttachedBicycles()	==	     1L);
        assert(r.getAttachedSurfboards()	==	   2L);
        assert(r.getLoadingListEntry()	==	     421L);
        assert(r.getLoadingDeck().equals(LoadingDeckType.upper));
        
	    assert(r.getCarrierNum()!= null);
	    assert(r.getCarrierNum().size() == 2);
	    assert(r.getCarrierNum().get(0) == 1080);
	    assert(r.getCarrierNum().get(1) == 1181);
	 
	    assert(r.getCarrierIA5() != null);
	    assert(r.getCarrierIA5().size() == 2);
	    assert(r.getCarrierIA5().contains("1080"));
	    assert(r.getCarrierIA5().contains("1181"));
        
        assert(r.getTariff() != null);
        assert(r.getPriceType().equals(PriceTypeType.travelPrice));
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
