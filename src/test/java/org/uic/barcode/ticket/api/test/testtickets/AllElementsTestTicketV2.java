package org.uic.barcode.ticket.api.test.testtickets;

import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringIA5;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringUTF8;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfUnrestrictedLong;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfDeltaCoordinates;
import org.uic.barcode.ticket.api.asn.omv2.TravelerType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfRegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfRegisteredLuggageType;
import org.uic.barcode.ticket.api.asn.omv2.DeltaCoordinates;
import org.uic.barcode.ticket.api.asn.omv2.BerthDetailData;
import org.uic.barcode.ticket.api.asn.omv2.BerthTypeType;
import org.uic.barcode.ticket.api.asn.omv2.CarCarriageReservationData;
import org.uic.barcode.ticket.api.asn.omv2.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv2.CodeTableType;
import org.uic.barcode.ticket.api.asn.omv2.CompartmentDetailsType;
import org.uic.barcode.ticket.api.asn.omv2.CompartmentGenderType;
import org.uic.barcode.ticket.api.asn.omv2.CompartmentPositionType;
import org.uic.barcode.ticket.api.asn.omv2.ConfirmationTypeType;
import org.uic.barcode.ticket.api.asn.omv2.ControlData;
import org.uic.barcode.ticket.api.asn.omv2.CountermarkData;
import org.uic.barcode.ticket.api.asn.omv2.CustomerCardData;
import org.uic.barcode.ticket.api.asn.omv2.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv2.DelayConfirmation;
import org.uic.barcode.ticket.api.asn.omv2.DocumentData;
import org.uic.barcode.ticket.api.asn.omv2.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv2.FIPTicketData;
import org.uic.barcode.ticket.api.asn.omv2.GenderType;
import org.uic.barcode.ticket.api.asn.omv2.GeoCoordinateSystemType;
import org.uic.barcode.ticket.api.asn.omv2.GeoCoordinateType;
import org.uic.barcode.ticket.api.asn.omv2.GeoUnitType;
import org.uic.barcode.ticket.api.asn.omv2.HemisphereLatitudeType;
import org.uic.barcode.ticket.api.asn.omv2.HemisphereLongitudeType;
import org.uic.barcode.ticket.api.asn.omv2.IncludedOpenTicketType;
import org.uic.barcode.ticket.api.asn.omv2.IssuingData;
import org.uic.barcode.ticket.api.asn.omv2.LineType;
import org.uic.barcode.ticket.api.asn.omv2.LinkMode;
import org.uic.barcode.ticket.api.asn.omv2.LoadingDeckType;
import org.uic.barcode.ticket.api.asn.omv2.LuggageRestrictionType;
import org.uic.barcode.ticket.api.asn.omv2.OpenTicketData;
import org.uic.barcode.ticket.api.asn.omv2.ParkingGroundData;
import org.uic.barcode.ticket.api.asn.omv2.PassData;
import org.uic.barcode.ticket.api.asn.omv2.PassengerType;
import org.uic.barcode.ticket.api.asn.omv2.PlacesType;
import org.uic.barcode.ticket.api.asn.omv2.PolygoneType;
import org.uic.barcode.ticket.api.asn.omv2.PriceTypeType;
import org.uic.barcode.ticket.api.asn.omv2.RegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv2.RegisteredLuggageType;
import org.uic.barcode.ticket.api.asn.omv2.ReservationData;
import org.uic.barcode.ticket.api.asn.omv2.ReturnRouteDescriptionType;
import org.uic.barcode.ticket.api.asn.omv2.RoofRackType;
import org.uic.barcode.ticket.api.asn.omv2.RouteSectionType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfActivatedDays;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfBerthDetailData;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfCarrierNum;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfCountries;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfIncludedOpenTicketType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfPlaceNum;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfServiceBrands;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfTariffType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfTicketLinkType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfTimeRangeType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfTravelerId;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfValidityPeriodType;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfVatDetail;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfViaStationType;
import org.uic.barcode.ticket.api.asn.omv2.SeriesDetailType;
import org.uic.barcode.ticket.api.asn.omv2.ServiceType;
import org.uic.barcode.ticket.api.asn.omv2.StationPassageData;
import org.uic.barcode.ticket.api.asn.omv2.TariffType;
import org.uic.barcode.ticket.api.asn.omv2.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv2.TicketLinkType;
import org.uic.barcode.ticket.api.asn.omv2.TicketType;
import org.uic.barcode.ticket.api.asn.omv2.TimeRangeType;
import org.uic.barcode.ticket.api.asn.omv2.TokenType;
import org.uic.barcode.ticket.api.asn.omv2.TrainLinkType;
import org.uic.barcode.ticket.api.asn.omv2.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv2.TravelerData;
import org.uic.barcode.ticket.api.asn.omv2.UicRailTicketData;
import org.uic.barcode.ticket.api.asn.omv2.ValidityPeriodDetailType;
import org.uic.barcode.ticket.api.asn.omv2.ValidityPeriodType;
import org.uic.barcode.ticket.api.asn.omv2.VatDetailType;
import org.uic.barcode.ticket.api.asn.omv2.ViaStationType;
import org.uic.barcode.ticket.api.asn.omv2.VoucherData;
import org.uic.barcode.ticket.api.asn.omv2.ZoneType;

public class AllElementsTestTicketV2 {
		
	

	


		
		public static UicRailTicketData getUicTestTicket() {
			UicRailTicketData ticket = new UicRailTicketData();
	    	populateTicket(ticket);
			return ticket;
		}


		private static void populateTicket(UicRailTicketData ticket) {
			
			ticket.setControlDetail(new ControlData());
			populateControlDetail(ticket.getControlDetail()); 
			
			ticket.setExtension(new SequenceOfExtensionData());
			populateExtension(ticket.getExtension());
			
			ticket.setIssuingDetail(new IssuingData());
			populateIssuingData(ticket.getIssuingDetail());
			
			ticket.setTravelerDetail(new TravelerData());
			populateTravelerData(ticket.getTravelerDetail());
			
			ticket.setTransportDocument(new SequenceOfDocumentData());
			
			DocumentData d1 = new DocumentData();
			populateReservation(d1);
			ticket.getTransportDocument().add(d1);
			
			DocumentData d2 = new DocumentData();
			populateCarCarriage(d2);
			ticket.getTransportDocument().add(d2);
			
			DocumentData d3 = new DocumentData();
			populateOpenTicket(d3);
			ticket.getTransportDocument().add(d3);
			
			DocumentData d4 = new DocumentData();
			populatePass(d4);
			ticket.getTransportDocument().add(d4);
			
			DocumentData d5 = new DocumentData();
			populateVoucher(d5);
			ticket.getTransportDocument().add(d5);
			
			DocumentData d6 = new DocumentData();
			populateCustomerCard(d6);
			ticket.getTransportDocument().add(d6);
			
			DocumentData d7 = new DocumentData();
			populateCounterMark(d7);
			ticket.getTransportDocument().add(d7);
			
			DocumentData d8 = new DocumentData();
			populateParking(d8);
			ticket.getTransportDocument().add(d8);
			
			DocumentData d9 = new DocumentData();
			populateFip(d9);
			ticket.getTransportDocument().add(d9);
			
			DocumentData d10 = new DocumentData();
			populateStationPassage(d10);
			ticket.getTransportDocument().add(d10);
			
			DocumentData d11 = new DocumentData();
			populateExtensionDocument(d11);
			ticket.getTransportDocument().add(d11);
			
			DocumentData d12 = new DocumentData();
			populateDelay(d12);
			ticket.getTransportDocument().add(d12);
		
		}
		
		private static void populateDelay(DocumentData d) {
			
			d.setTicket(new TicketDetailData());
			DelayConfirmation dc = new DelayConfirmation();
			d.getTicket().setDelayConfirmation(dc);
		    
		    dc.setTrainNum(Asn1BigInteger.toAsn1(100L)); 						
		    dc.setTrainIA5("100");     						
		    dc.setPlannedArrivalYear(2022L); 
		    dc.setPlannedArrivalDay(12L); 
		    dc.setPlannedArrivalTime(1000L); 
		    dc.setDepartureUTCOffset(30L);
		    dc.setReferenceIA5("ABDJ12345");	
		    dc.setReferenceNum(12345L);			
		    
		    dc.setStationCodeTable(CodeTableType.stationUIC);
		    dc.setStationNum(8000001L);
		    dc.setStationIA5("DJE");
		    dc.setDelay(31L);
		    dc.setTrainCancelled(false);
		    dc.setConfirmationType(ConfirmationTypeType.travelerDelayConfirmation);
		    
		    dc.setAffectedTickets(new SequenceOfTicketLinkType());
		    TicketLinkType tl = new TicketLinkType();
		    dc.getAffectedTickets().add(tl);
		    
		    tl.setReferenceIA5("KDJET");
		    tl.setReferenceNum(801234567890L);
		    tl.setIssuerName("XYZ");
		    tl.setIssuerPNR("LDWDUR45");
		    tl.setProductOwnerNum(1080L);
		    tl.setProductOwnerIA5("IEFHU");
		    tl.setTicketType(TicketType.openTicket);
		    tl.setLinkMode(LinkMode.issuedTogether);
	        
	        dc.setInfoText("delay confirmation");
			dc.setExtension(new ExtensionData());
			dc.getExtension().setExtensionId("1");
			dc.getExtension().setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			
		}


		private static void populateExtensionDocument(DocumentData d) {
			
			d.setTicket(new TicketDetailData());
			d.getTicket().setExtension(new ExtensionData());
			d.getTicket().getExtension().setExtensionId("1");
			d.getTicket().getExtension().setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			
		}


		private static void populateStationPassage(DocumentData d) {
			
			d.setTicket(new TicketDetailData());		
			StationPassageData s = new StationPassageData();
			d.getTicket().setStationPassage(s);

			s.setExtension(new ExtensionData());
			s.getExtension().setExtensionId("1");
			s.getExtension().setExtensionData(UperEncoder.bytesFromHexString("82DA"));
					
		    s.setReferenceIA5("810123456789");	
		    s.setReferenceNum(Asn1BigInteger.toAsn1(810123456789L));			     		        															
		    s.setProductOwnerNum(23456L);    
		    s.setProductOwnerIA5("23456");    
		    s.setProductIdNum(15535L);    
		    s.setProductIdIA5("23456"); 
				      
		    s.setProductName("passage");
	        s.setStationCodeTable(CodeTableType.stationUIC);
		    s.setStationNum(new SequenceOfUnrestrictedLong());
		    s.getStationNum().add(8200001L);	    	
		    s.setStationIA5(new SequenceOfStringIA5());
		    s.getStationIA5().add("AMS");
		    s.setStationNameUTF8(new SequenceOfStringUTF8());
		    s.getStationNameUTF8().add("Amsterdam");
		    s.setAreaCodeNum(new SequenceOfUnrestrictedLong());
		    s.getAreaCodeNum().add(8200001L);	    	    		
		    s.setAreaCodeIA5(new SequenceOfStringIA5());
			s.getAreaCodeIA5().add("AMS");
		    s.setAreaNameUTF8(new SequenceOfStringUTF8());
		    s.getAreaNameUTF8().add("Amsterdam");
		    s.setValidFromDay(5L);
		    s.setValidFromTime(0L);
		    s.setValidFromUTCOffset(1L);
		    s.setValidUntilDay(5L);
		    s.setValidUntilTime(1000L);
		    s.setValidUntilUTCOffset(1L);
		    s.setNumberOfDaysValid(5L);
		    
		}


		private static void populateFip(DocumentData d) {

			d.setTicket(new TicketDetailData());
			FIPTicketData f = new FIPTicketData();
			d.getTicket().setFipTicket(f);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			f.setExtension(e1);
					
		    f.setReferenceIA5("810123456789");	
		    f.setReferenceNum(Asn1BigInteger.toAsn1(810123456789L));			     		        															
		    f.setProductOwnerNum(23456L);    
		    f.setProductOwnerIA5("23456");    
		    f.setProductIdNum(15535L);    
		    f.setProductIdIA5("23456"); 
		    
	 				   		
		    f.setValidFromDay( 2L);   		        	 
	        f.setValidUntilDay(5L);							
	        f.setActivatedDay(new SequenceOfActivatedDays());     
	        f.getActivatedDay().add(1L);
	       	f.getActivatedDay().add(13L);
	        f.getActivatedDay().add(14L); 
	       	f.getActivatedDay().add(15L); 
	       	
		    f.setCarrierNum(new SequenceOfCarrierNum());
		    f.getCarrierNum().add(1080L);
		    f.getCarrierNum().add(1181L);
		 
		    f.setCarrierIA5(new SequenceOfStringIA5());
		    f.getCarrierIA5().add("1080");
		    f.getCarrierIA5().add("1181");       	
	       	
	        f.setNumberOfTravelDays(8L);                  
	        f.setIncludesSupplements(true);			
	        f.setClassCode(TravelClassType.first);
			
		}


		private static void populateParking(DocumentData d) {

			d.setTicket(new TicketDetailData());
			ParkingGroundData p = new ParkingGroundData();
			d.getTicket().setParkingGround(p);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			p.setExtension(e1);

	
		    p.setReferenceIA5("810123456789");	
		    p.setReferenceNum(Asn1BigInteger.toAsn1(810123456789L));			     		        															
		    p.setProductOwnerNum(23456L);    
		    p.setProductOwnerIA5("23456");    
		    p.setProductIdNum(15535L);    
		    p.setProductIdIA5("23456"); 
			
		    p.setParkingGroundId("IA5");
		    p.setFromParkingDate(370L);        
		    p.setToParkingDate(370L);
	        
		    p.setAccessCode("4ga");
		    p.setLocation("Parking Frankfurt Main West");
		    p.setStationCodeTable(CodeTableType.stationUIC);
		    p.setStationNum(8000001L);
		    p.setStationIA5("8000001");
		    p.setSpecialInformation("outdoor parking");
		    p.setEntryTrack("left");
		    p.setNumberPlate("AA-DE-12345");
		    p.setPrice(500L);	
		    p.setVatDetails(new SequenceOfVatDetail());
		    VatDetailType v = new VatDetailType();
		    p.getVatDetails().add(v);
		    v.setAmount(10L);
		    v.setCountry(80L);
		    v.setPercentage(70L);
		    v.setVatId("IUDGTE");  
			  
		}


		private static void populateCounterMark(DocumentData d) {
			
			d.setTicket(new TicketDetailData());
			CountermarkData t = new CountermarkData();
			d.getTicket().setCounterMark(t);
			
			t.setTicketReferenceIA5("810123456789");		     
			t.setTicketReferenceNum(810123456789L);
			t.setNumberOfCountermark(12L);
	        t.setTotalOfCountermarks(24L);
	        t.setGroupName("groupName");
	        
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			t.setExtension(e1);	
			
		    t.setReferenceIA5("810123456789");	
		    t.setReferenceNum(810123456789L);			     		        															
		    t.setProductOwnerNum( 23456L);    
		    t.setProductOwnerIA5("23456");    
		    t.setProductIdNum( 15535L);    
		    t.setProductIdIA5("23456"); 
		
			t.setReturnIncluded( false);	      
				  
			t.setStationCodeTable(CodeTableType.stationERA);
			t.setFromStationNum( 8100001L);
			t.setFromStationIA5("8100001");
			t.setToStationNum(   8000002L);
			t.setToStationIA5(  "8100002");
			t.setFromStationNameUTF8(  "A-STATION"); 
			t.setToStationNameUTF8(    "B-STATION");				  
 
			t.setValidRegionDesc("From A to B via C");		   
				  
			t.setValidRegion(new SequenceOfRegionalValidityType());
			t.getValidRegion().add(new RegionalValidityType());
			t.getValidRegion().get(0).setViaStations(new ViaStationType());
			populateCounterMarkRoute(t.getValidRegion().get(0).getViaStations());
					
		    t.setReturnDescription(new ReturnRouteDescriptionType());
		    t.getReturnDescription().setFromStationNum(  8100001L);
		    t.getReturnDescription().setFromStationIA5("8100001");
			t.getReturnDescription().setToStationNum(         8000002L);
		    t.getReturnDescription().setToStationIA5(         "8100002");
		    t.getReturnDescription().setFromStationNameUTF8(  "A-STATION");
			t.getReturnDescription().setToStationNameUTF8(    "B-STATION"); 	
			t.getReturnDescription().setValidReturnRegionDesc( "return");
			t.getReturnDescription().setValidReturnRegion(new SequenceOfRegionalValidityType());
			t.getReturnDescription().getValidReturnRegion().add(new RegionalValidityType());
			
			t.getReturnDescription().getValidReturnRegion().get(0).setZones(new ZoneType());
			t.getReturnDescription().getValidReturnRegion().get(0).getZones().setZoneId(new SequenceOfUnrestrictedLong());
			t.getReturnDescription().getValidReturnRegion().get(0).getZones().getZoneId().add(100L);
			t.getReturnDescription().getValidReturnRegion().get(0).getZones().getZoneId().add(200L);
			t.getReturnDescription().getValidReturnRegion().get(0).getZones().setCarrierNum(1080L);
			t.getReturnDescription().getValidReturnRegion().get(0).getZones().setCarrierIA5("1181");
			t.getReturnDescription().getValidReturnRegion().get(0).getZones().setStationCodeTable(CodeTableType.stationERA);
	       	                  
		    t.setValidFromDay(  700L);
		    t.setValidFromTime(  0L);
		    t.setValidFromUTCOffset(     60L); 
		    t.setValidUntilDay(  370L);
		    t.setValidUntilTime(  1439L);
		    t.setValidUntilUTCOffset(    10L);  
					       		   
			t.setClassCode(TravelClassType.first);
						
	        t.setCarriersNum(new SequenceOfCarrierNum());
	        t.getCarriersNum().add(1080L);
	        t.getCarriersNum().add(1181L);
	   
	        t.setCarriersIA5(new SequenceOfStringIA5());
	        t.getCarriersIA5().add("1080");
	        t.getCarriersIA5().add("1181");
	      
	        t.setIncludedServiceBrands(new SequenceOfServiceBrands());
	        t.getIncludedServiceBrands().add(108L);
	        t.getIncludedServiceBrands().add(118L);
	       
	        t.setExcludedServiceBrands(new SequenceOfServiceBrands());
	        t.getExcludedServiceBrands().add(108L);
	        t.getExcludedServiceBrands().add(118L);
			
    
	        t.setInfoText("counterMark");
			
		}


		private static void populateCustomerCard(DocumentData d) {
			
			d.setTicket(new TicketDetailData());
			CustomerCardData c = new CustomerCardData();
			d.getTicket().setCustomerCard(c);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			c.setExtension(e1);
			
			TravelerType t = new TravelerType();
			c.setCustomer(t);
			t.setCustomerIdIA5("1234");
            t.setTicketHolder(false);
            t.setPassengerType(PassengerType.senior);
	
			c.setCardIdIA5("2345"); 
			c.setCardIdNum(123456L);
			c.setValidFromYear(2269L);
			c.setValidFromDay(2L);
		    c.setValidUntilYear(1L);    
		    c.setValidUntilDay(5L);    
		    c.setClassCode(TravelClassType.second);
		    c.setCardType(15L);
		    c.setCardTypeDescr("RAILPLUS");	
		    c.setCustomerStatus(1L);  
		    
		    c.setCustomerStatusDescr("gold");      
		    c.setIncludedServices(new SequenceOfUnrestrictedLong());
		    c.getIncludedServices().add(1L);
		    c.getIncludedServices().add(2L);
			
		}


		private static void populateVoucher(DocumentData d) {
			
			d.setTicket(new TicketDetailData());
			VoucherData v = new VoucherData();
			d.getTicket().setVoucher(v);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			v.setExtension(e1);			
			
		    v.setReferenceIA5("810123456789");	
		    v.setReferenceNum(Asn1BigInteger.toAsn1(810123456789L));			     		        															
		    v.setProductOwnerNum(23456L);    
		    v.setProductOwnerIA5("COFFEEMACHINE");    
		    v.setProductIdNum(15535L);    
		    v.setProductIdIA5("23456"); 

		    v.setValidFromYear(2022L);
		    v.setValidFromDay(1L);
		    v.setValidUntilYear(2022L);
	        v.setValidUntilDay(1L);
	        v.setValue(500L);
	        v.setType(123L);
	        v.setInfoText("coffee voucher");
		}


		private static void populatePass(DocumentData d) {
			
			
			d.setTicket(new TicketDetailData());
			PassData p = new PassData();
			d.getTicket().setPass(p);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			p.setExtension(e1);			
			
		    p.setReferenceIA5("810123456789");	
		    p.setReferenceNum(Asn1BigInteger.toAsn1(810123456789L));			     		        															
		    p.setProductOwnerNum(23456L);    
		    p.setProductOwnerIA5("23456");    
		    p.setProductIdNum(15535L);    
		    p.setProductIdIA5("23456"); 
				  
			p.setPassType(2L);
			p.setPassDescription("Eurail FlexPass");
			p.setClassCode(TravelClassType.first);
		    p.setValidFromDay(       0L);
		    p.setValidFromTime(      1000L);
		    p.setValidFromUTCOffset( 1L);
		    p.setValidUntilDay(      1L);
		    p.setValidUntilTime(     1000L);
		    p.setValidUntilUTCOffset(1L);
		    
		    ValidityPeriodDetailType v = new ValidityPeriodDetailType();
		    p.setValidityPeriodDetails(v);
		    v.setValidityPeriod(new SequenceOfValidityPeriodType());
		    v.setExcludedTimeRange(new SequenceOfTimeRangeType());
	    	  
	        ValidityPeriodType vp = new ValidityPeriodType();
	        p.getValidityPeriodDetails().getValidityPeriod().add(vp);
	        vp.setValidFromDay(0L);
	        vp.setValidFromTime( 1000L);
	        vp.setValidFromUTCOffset( 1L);
	        vp.setValidUntilDay( 1L);
	        vp.setValidUntilTime( 1000L);
	        vp.setValidUntilUTCOffset(1L);

	        TimeRangeType tr = new TimeRangeType();
	        p.getValidityPeriodDetails().getExcludedTimeRange().add(tr);
		   
		    tr.setFromTime(6L);
		    tr.setUntilTime(9L);

		    p.setNumberOfValidityDays(5L);
		    
      
	        p.setNumberOfPossibleTrips( 3L);
	        p.setNumberOfDaysOfTravel(     10L);
	          
		    p.setActivatedDay(new SequenceOfActivatedDays());     
		    p.getActivatedDay().add(200L);
		    p.getActivatedDay().add(201L);
		       	
      
	        p.setCountries(new SequenceOfCountries());
	        p.getCountries().add(10L);
	        p.getCountries().add(20L);
	          
	        p.setIncludedCarriersNum(new SequenceOfCarrierNum());
	        p.getIncludedCarriersNum().add(1080L);
	        p.getIncludedCarriersNum().add(1181L);
	          
	        p.setIncludedCarriersIA5(new SequenceOfStringIA5());
	        p.getIncludedCarriersIA5().add("1080");
	        p.getIncludedCarriersIA5().add("1181");
	    
	        p.setExcludedCarriersNum(new SequenceOfCarrierNum());
	        p.getExcludedCarriersNum().add(1080L);
	        p.getExcludedCarriersNum().add(1181L);
	   
	        p.setExcludedCarriersIA5(new SequenceOfStringIA5());
	        p.getExcludedCarriersIA5().add("1080");
	        p.getExcludedCarriersIA5().add("1181");
	      
	        p.setIncludedServiceBrands(new SequenceOfServiceBrands());
	        p.getIncludedServiceBrands().add(108L);
	        p.getIncludedServiceBrands().add(118L);
	       
	        p.setExcludedServiceBrands(new SequenceOfServiceBrands());
	        p.getExcludedServiceBrands().add(108L);
	        p.getExcludedServiceBrands().add(118L);
	       
	        p.setValidRegion(new SequenceOfRegionalValidityType());
	        
	        RegionalValidityType rv = new RegionalValidityType();
	        p.getValidRegion().add(rv);
	        rv.setZones(new ZoneType());
	        rv.getZones().setZoneId(new SequenceOfUnrestrictedLong());
	        rv.getZones().getZoneId().add(100L);
	       	                  
	        p.setTariffs(new SequenceOfTariffType());
	        TariffType ta = new TariffType();
	        p.getTariffs().add(ta);
	        ta.setNumberOfPassengers(1L);
	        ta.setRestrictedToCountryOfResidence(false);
	        ta.setTariffIdNum(72L);
	        ta.setTariffDesc("Large Car Full Fare"); 
	        
		    p.setVatDetails(new SequenceOfVatDetail());
		    VatDetailType v1 = new VatDetailType();
		    p.getVatDetails().add(v1);
		    v1.setAmount(10L);
		    v1.setCountry(80L);
		    v1.setPercentage(70L);
		    v1.setVatId("IUDGTE");  

		    p.setPrice(10000L);
		    p.setInfoText("pass info");
			
		}


		private static void populateOpenTicket(DocumentData d) {
			
			d.setTicket(new TicketDetailData());
			OpenTicketData o = new OpenTicketData();
			d.getTicket().setOpenTicket(o);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			o.setExtension(e1);	
			
		    o.setReferenceIA5("810123456789");	
		    o.setReferenceNum(810123456789L);			     		        															
		    o.setProductOwnerNum( 23456L);    
		    o.setProductOwnerIA5("23456");    
		    o.setProductIdNum( 15535L);    
		    o.setProductIdIA5("23456"); 
			

			o.setExtIssuerId(         12L);
			o.setIssuerAutorizationId( 13L);	
			o.setReturnIncluded( false);	      
				  
			o.setStationCodeTable(CodeTableType.stationERA);
			o.setFromStationNum( 8100001L);
			o.setFromStationIA5("8100001");
			o.setToStationNum(   8000002L);
			o.setToStationIA5(  "8100002");
			o.setFromStationNameUTF8(  "A-STATION"); 
			o.setToStationNameUTF8(    "B-STATION");				  
 
			o.setValidRegionDesc("From A to B via C");		   
				  
			o.setValidRegion(new SequenceOfRegionalValidityType());
			o.getValidRegion().add(new RegionalValidityType());
			o.getValidRegion().get(0).setViaStations(new ViaStationType());
			o.getValidRegion().add(new RegionalValidityType());
			o.getValidRegion().get(1).setZones(new ZoneType());
			o.getValidRegion().add(new RegionalValidityType());
			o.getValidRegion().get(2).setLines(new LineType());
			o.getValidRegion().add(new RegionalValidityType());
			o.getValidRegion().get(3).setTrainLink(new TrainLinkType());
			o.getValidRegion().add(new RegionalValidityType());
			o.getValidRegion().get(4).setPolygone(new PolygoneType());
			populateRoute(o.getValidRegion().get(0).getViaStations());
			populateZone(o.getValidRegion().get(1).getZones());
			populateLine(o.getValidRegion().get(2).getLines());
			populateTrainLink(o.getValidRegion().get(3).getTrainLink());
			populatePolygon(o.getValidRegion().get(4).getPolygone());
		
		    o.setReturnDescription(new ReturnRouteDescriptionType());
		    o.getReturnDescription().setFromStationNum(  8100001L);
		    o.getReturnDescription().setFromStationIA5("8100001");
			o.getReturnDescription().setToStationNum(         8000002L);
		    o.getReturnDescription().setToStationIA5(         "8100002");
		    o.getReturnDescription().setFromStationNameUTF8(  "A-STATION");
			o.getReturnDescription().setToStationNameUTF8(    "B-STATION"); 	
			o.getReturnDescription().setValidReturnRegionDesc( "return");
			o.getReturnDescription().setValidReturnRegion(new SequenceOfRegionalValidityType());
			o.getReturnDescription().getValidReturnRegion().add(new RegionalValidityType());
			
			o.getReturnDescription().getValidReturnRegion().get(0).setZones(new ZoneType());
			o.getReturnDescription().getValidReturnRegion().get(0).getZones().setZoneId(new SequenceOfUnrestrictedLong());
			o.getReturnDescription().getValidReturnRegion().get(0).getZones().getZoneId().add(100L);
			o.getReturnDescription().getValidReturnRegion().get(0).getZones().getZoneId().add(200L);
			o.getReturnDescription().getValidReturnRegion().get(0).getZones().setCarrierIA5("1080");
			o.getReturnDescription().getValidReturnRegion().get(0).getZones().setCarrierNum(1080L);
			o.getReturnDescription().getValidReturnRegion().get(0).getZones().setStationCodeTable(CodeTableType.stationERA);	       	                  
			
		    o.setValidFromDay(  700L);
		    o.setValidFromTime(  0L);
		    o.setValidFromUTCOffset(     60L); 
		    o.setValidUntilDay(  370L);
		    o.setValidUntilTime(  1439L);
		    o.setValidUntilUTCOffset(    10L);  
			 
		    o.setActivatedDay(new SequenceOfActivatedDays());     
		    o.getActivatedDay().add(1L);
		    o.getActivatedDay().add(2L);
		    		  
			   
			o.setClassCode(TravelClassType.first);
			o.setServiceLevel("A");		
			
	        o.setCarriersNum(new SequenceOfCarrierNum());
	        o.getCarriersNum().add(1080L);
	        o.getCarriersNum().add(1181L);
	   
	        o.setCarriersIA5(new SequenceOfStringIA5());
	        o.getCarriersIA5().add("1080");
	        o.getCarriersIA5().add("1181");
	      
	        o.setIncludedServiceBrands(new SequenceOfServiceBrands());
	        o.getIncludedServiceBrands().add(108L);
	        o.getIncludedServiceBrands().add(118L);
	       
	        o.setExcludedServiceBrands(new SequenceOfServiceBrands());
	        o.getExcludedServiceBrands().add(108L);
	        o.getExcludedServiceBrands().add(118L);
			
	        o.setTariffs(new SequenceOfTariffType());
	        TariffType ta = new TariffType();
	        o.getTariffs().add(ta);
	        ta.setNumberOfPassengers(1L);
	        ta.setRestrictedToCountryOfResidence(false);
	        ta.setTariffIdNum(72L);
	        ta.setTariffDesc("Large Car Full Fare"); 
	        
		    o.setVatDetails(new SequenceOfVatDetail());
		    VatDetailType v1 = new VatDetailType();
		    o.getVatDetails().add(v1);
		    v1.setCountry(80L);
		    v1.setPercentage(70L);	
    				    
		    o.setPrice(  12345L);

    
	        o.setInfoText("openTicketInfo");
		
			o.setIncludedAddOns(new SequenceOfIncludedOpenTicketType());
			o.getIncludedAddOns().add(new IncludedOpenTicketType());
			populateIncludedTicket(o.getIncludedAddOns().get(0));
			
			o.setLuggage(new LuggageRestrictionType());
			o.getLuggage().setMaxHandLuggagePieces(2L);
			o.getLuggage().setMaxNonHandLuggagePieces(1L);
			
			
						
		}
		



		private static void populateTrainLink(TrainLinkType t) {
			
			
		    t.setTrainNum( 12345L); 						
		    t.setTrainIA5("12345");     						
		    t.setTravelDate(  2L);
		    t.setDepartureTime( 		 1439L);           
    

		    t.setFromStationNum( 8100001L);
		    t.setFromStationIA5("8100001");
		    t.setToStationNum(   8000002L);
		    t.setToStationIA5(  "8100002");
		    t.setFromStationName(  "A-STATION"); 
		    t.setToStationName(    "B-STATION"); 
		         
		}

		private static void populatePolygon(PolygoneType p) {
			
			p.setFirstEdge(new GeoCoordinateType());
			p.getFirstEdge().setLongitude( 12345L);
	        p.getFirstEdge().setLatitude(  56789L);						
			p.setEdges(new SequenceOfDeltaCoordinates());
			p.getEdges().add(new DeltaCoordinates());
			p.getEdges().add(new DeltaCoordinates());
			p.getEdges().get(0).setLongitude( Asn1BigInteger.toAsn1(12345L));
			p.getEdges().get(0).setLatitude(Asn1BigInteger.toAsn1(56789L));
			p.getEdges().get(1).setLongitude(Asn1BigInteger.toAsn1(12345L));
			p.getEdges().get(1).setLatitude(Asn1BigInteger.toAsn1(56789L));

		}

		private static void populateZone(ZoneType z) {
			
			z.setCarrierNum( 1080L);
			z.setCarrierIA5("1080");
			z.setStationCodeTable(CodeTableType.stationERA);
			z.setEntryStationNum(         1234L);
	        z.setEntryStationIA5(        "1234");
	        z.setTerminatingStationNum(   2345L);
	        z.setTerminatingStationIA5(  "2345");
	        z.setCity(Asn1BigInteger.toAsn1(123456L));
	        z.setZoneId(new SequenceOfUnrestrictedLong());
	        z.getZoneId().add(100L);
	        z.getZoneId().add(200L);
			z.setNutsCode("DE4711");
			z.setBinaryZoneId(UperEncoder.bytesFromHexString("82DA"));
	        
		}

		private static void populateLine(LineType z) {
			
			z.setCarrierNum( 1080L);
			z.setCarrierIA5("1080");
			z.setStationCodeTable(CodeTableType.stationERA);
			z.setEntryStationNum(         1234L);
	        z.setEntryStationIA5(        "1234");
	        z.setTerminatingStationNum(   2345L);
	        z.setTerminatingStationIA5(  "2345");
	        z.setCity(123456L);
	        z.setLineId(new SequenceOfUnrestrictedLong());
	        z.getLineId().add(100L);
	        z.getLineId().add(200L);
	       	
		}

		private static void populateRoute(ViaStationType t) {
			
				
				t.setRoute(new SequenceOfViaStationType());
				t.getRoute().add(new ViaStationType());
				t.getRoute().add(new ViaStationType());
				t.getRoute().add(new ViaStationType());
				t.getRoute().add(new ViaStationType());
				
				t.getRoute().get(0).setStationNum( 123455L);
				t.getRoute().get(0).setStationIA5("123455");
				t.getRoute().get(0).setBorder( false);

				t.getRoute().get(1).setStationNum( 123456L);
				t.getRoute().get(1).setBorder( false);
				
				
				t.getRoute().get(2).setAlternativeRoutes(new SequenceOfViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().add(new ViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().add(new ViaStationType());
				t.getRoute().get(2).setBorder( false);
				
				t.getRoute().get(2).getAlternativeRoutes().get(0).setRoute(new SequenceOfViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().get(0).setBorder(false);
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().add(new ViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().add(new ViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(0).setStationNum( 23455L);
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(0).setBorder(false);
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).setStationNum( 23456L);
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).setBorder(false);
				t.getRoute().get(2).getAlternativeRoutes().get(1).setRoute(new SequenceOfViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().get(1).setBorder(false);
				t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().add(new ViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().add(new ViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(0).setStationNum( 3455L);
				t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(0).setBorder(false);
				t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(1).setStationNum( 3456L);
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).setBorder(false);
				
				t.getRoute().get(3).setStationNum( 123457L);	
				t.getRoute().get(3).setBorder(false);
		  	          
		  	    t.setBorder( false);
			          
			    t.setSeriesId( 999L);
			    t.setRouteId( 21L);
			    	  			
		}
		
		private static void populateCounterMarkRoute(ViaStationType t) {
			
			
			t.setRoute(new SequenceOfViaStationType());
			t.getRoute().add(new ViaStationType());
			t.getRoute().add(new ViaStationType());
			t.getRoute().add(new ViaStationType());
			t.getRoute().add(new ViaStationType());
			
			t.getRoute().get(0).setStationNum( 123455L);
			t.getRoute().get(0).setStationIA5("123455");
			t.getRoute().get(0).setBorder( false);

			t.getRoute().get(1).setStationNum( 123456L);
			t.getRoute().get(1).setBorder( false);
			
			
			t.getRoute().get(2).setAlternativeRoutes(new SequenceOfViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().add(new ViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().add(new ViaStationType());
			t.getRoute().get(2).setBorder( false);
			
			t.getRoute().get(2).getAlternativeRoutes().get(0).setRoute(new SequenceOfViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().get(0).setBorder(false);
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().add(new ViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().add(new ViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(0).setStationNum( 23455L);
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(0).setBorder(false);
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).setStationNum( 23456L);
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).setBorder(false);
			t.getRoute().get(2).getAlternativeRoutes().get(1).setRoute(new SequenceOfViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().get(1).setBorder(false);
			t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().add(new ViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().add(new ViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(0).setStationNum( 3455L);
			t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(0).setBorder(false);
			t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(1).setStationNum( 3456L);
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).setBorder(false);
			
			t.getRoute().get(3).setStationNum( 123457L);	
			t.getRoute().get(3).setBorder(false);
			
			t.setCarriersIA5(new SequenceOfStringIA5());
			t.getCarriersIA5().add("1080");
			t.getCarriersIA5().add("1181");
			
			t.setCarriersNum(new SequenceOfCarrierNum());
			t.getCarriersNum().add(1080L);
			t.getCarriersNum().add(1181L);
			
			
	  	    t.setBorder( false);
		          
		    t.setSeriesId( 999L);
		    t.setRouteId( 21L);
		
	}

		private static void populateIncludedTicket(IncludedOpenTicketType t) {
			
			
		    t.setProductOwnerNum( 23456L);    
		    t.setProductOwnerIA5("23456");    
		    t.setProductIdNum( 15535L);    
		    t.setProductIdIA5("23456"); 

			
			t.setExternalIssuerId(12L);
			t.setIssuerAutorizationId(13L);
			t.setStationCodeTable(CodeTableType.stationERA);    
			
			t.setValidRegion(new SequenceOfRegionalValidityType());
	        RegionalValidityType rv = new RegionalValidityType();
	        t.getValidRegion().add(rv);
	        rv.setZones(new ZoneType());
	        rv.getZones().setZoneId(new SequenceOfUnrestrictedLong());
	        rv.getZones().getZoneId().add(100L);
	        
			t.setValidFromDay( 0L);
			t.setValidFromTime(1000L);
			t.setValidUntilDay(1L);
			t.setValidUntilTime(1000L);
			t.setClassCode(TravelClassType.second);
			t.setServiceLevel("A");
			
	        t.setIncludedCarriersNum(new SequenceOfCarrierNum());
	        t.getIncludedCarriersNum().add(1080L);
	        t.getIncludedCarriersNum().add(1181L);
	          
	        t.setIncludedCarriersIA5(new SequenceOfStringIA5());
	        t.getIncludedCarriersIA5().add("1080");
	        t.getIncludedCarriersIA5().add("1181");
	    
	              
		    t.setIncludedServiceBrands(new SequenceOfServiceBrands());
		    t.getIncludedServiceBrands().add(108L);
		    t.getIncludedServiceBrands().add(118L);
		       
		    t.setExcludedServiceBrands(new SequenceOfServiceBrands());
		    t.getExcludedServiceBrands().add(108L);
		    t.getExcludedServiceBrands().add(118L);
	               	
	        t.setTariffs(new SequenceOfTariffType());
	        TariffType ta = new TariffType();
	        t.getTariffs().add(ta);
	        ta.setNumberOfPassengers(1L);
	        ta.setRestrictedToCountryOfResidence(false);
	        ta.setTariffIdNum(72L);
	        ta.setTariffDesc("Large Car Full Fare"); 
		   	                            
			t.setInfoText("included ticket");
			
	        		    	        
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			t.setExtension(e1);		 
			
		}


		private static void populateCarCarriage(DocumentData d2) {
			
			d2.setTicket(new TicketDetailData());
			d2.setToken(new TokenType());
			
			
			assert(d2.getToken() != null);
			d2.getToken().setToken(UperEncoder.bytesFromHexString("82DA"));
			d2.getToken().setTokenProviderIA5("VDV");
			d2.getToken().setTokenProviderNum(123L);
			d2.getToken().setTokenSpecification("TEST");
			
			CarCarriageReservationData r = new CarCarriageReservationData();
			d2.getTicket().setCarCarriageReservation(r);
			
		    r.setTrainNum( 123L); 						
		    r.setTrainIA5("123");     						
		    r.setReferenceIA5("810123456789");	
		    r.setReferenceNum( 810123456789L);			     		        															
		    r.setProductOwnerNum( 23456L);    
		    r.setProductOwnerIA5("23456");    
		    r.setProductIdNum( 15535L);
		    r.setProductIdIA5("23456"); 
		    r.setServiceBrand( 100L);
		    r.setServiceBrandAbrUTF8("AZ");      					   	
		    r.setServiceBrandNameUTF8("special train");     					    
	        
	        r.setBeginLoadingDate(  10L);
	        r.setBeginLoadingTime(    0L);
	        r.setEndLoadingTime(      500L);
	        r.setLoadingUTCOffset(    30L);
	        
		    r.setStationCodeTable(CodeTableType.stationERA);
		    r.setFromStationNum( 8100001L);
		    r.setFromStationIA5("8100001");
		    r.setToStationNum(   8000002L);
		    r.setToStationIA5(  "8100002");
		    r.setFromStationNameUTF8(  "A-STATION"); 
		    r.setToStationNameUTF8(    "B-STATION");         
	         
		    r.setCoach("21");
		    r.setPlace("41");
		    r.setCompartmentDetails(new CompartmentDetailsType());
		    	
		    r.getCompartmentDetails().setCoachType(	1L);    
		    r.getCompartmentDetails().setCompartmentType(	99L);    
		    r.getCompartmentDetails().setSpecialAllocation(	50L);   				    	
		    r.getCompartmentDetails().setCoachTypeDescr("xwz");  
			r.getCompartmentDetails().setCompartmentTypeDescr("xwz");  			
			r.getCompartmentDetails().setSpecialAllocationDescr("xwz");  		
			r.getCompartmentDetails().setPosition(CompartmentPositionType.upperLevel);   	
				        
			r.setNumberPlate(          "AD-DE-123");
			r.setTrailerPlate(         "DX-AB-123");
			r.setCarCategory(          3L);
	        r.setBoatCategory(         5L);
	        r.setTextileRoof(         false);
	        r.setRoofRackType(RoofRackType.bicycleRack);
	        r.setRoofRackHeight(      20L);
	        r.setAttachedBoats(       2L);
	        r.setAttachedBicycles(    1L);
	        r.setAttachedSurfboards( 2L);
	        r.setLoadingListEntry(   421L);
	        r.setLoadingDeck(LoadingDeckType.upper);
	        
		    r.setCarrierNum(new SequenceOfCarrierNum());
		    r.getCarrierNum().add(1080L);
		    r.getCarrierNum().add(1181L);
		 
		    r.setCarrierIA5(new SequenceOfStringIA5());
		    r.getCarrierIA5().add("1080");
		    r.getCarrierIA5().add("1181");
	        

	        r.setPriceType(PriceTypeType.travelPrice);
	        r.setPrice(12345L);
	        
	        r.setInfoText("car carriage");
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			r.setExtension(e1);		
			
	        TariffType ta = new TariffType();
	        r.setTariff(ta);
	        ta.setNumberOfPassengers(1L);
	        ta.setRestrictedToCountryOfResidence(false);
	        ta.setTariffIdNum(72L);
	        ta.setTariffDesc("Large Car Full Fare"); 
			
		    r.setVatDetails(new SequenceOfVatDetail());
		    VatDetailType v1 = new VatDetailType();
		    r.getVatDetails().add(v1);
		    v1.setAmount(10L);
		    v1.setCountry(80L);
		    v1.setPercentage(70L);
		    v1.setVatId("IUDGTE");  
			
		}


		private static void populateReservation(DocumentData d) {
						
			d.setTicket(new TicketDetailData());
			ReservationData r = new ReservationData();
			d.getTicket().setReservation(r);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			r.setExtension(e1);		
			
		    r.setTrainNum( 12345L); 						
		    r.setTrainIA5("12345");     						
		    r.setDepartureDate(  2L); 
		    r.setReferenceIA5("810123456789");	
		    r.setReferenceNum(80123456789L);			     		        															
		    r.setProductOwnerNum( 23456L);    
		    r.setProductOwnerIA5("23456");    
		    r.setProductIdNum( 15535L);    
		    r.setProductIdIA5("23456"); 
		    r.setServiceBrand( 12L);
		    r.setServiceBrandAbrUTF8("TGV");      					   	
		    r.setServiceBrandNameUTF8("Lyria");     					    
		    r.setService(ServiceType.couchette);		
		    
		    r.setStationCodeTable(CodeTableType.stationUIC);
		    r.setFromStationNum( 8100001L);
		    r.setFromStationIA5("8100001");
		    r.setToStationNum( 8000002L);
		    r.setToStationIA5(  "8100002");
		    r.setFromStationNameUTF8(  "A-STATION"); 
		    r.setToStationNameUTF8(    "B-STATION"); 
		    r.setDepartureTime( 		 1439L);           
		    r.setDepartureUTCOffset(   -60L); 
		    r.setArrivalDate(		     20L);  			
		    r.setArrivalTime(		     0L);
		    r.setArrivalUTCOffset(     10L);  
		    r.setCarrierNum(new SequenceOfCarrierNum());
		    r.getCarrierNum().add(1080L);
		    r.getCarrierNum().add(1181L);
		 
		    r.setCarrierIA5(new SequenceOfStringIA5());
		    r.getCarrierIA5().add("1080");
		    r.getCarrierIA5().add("1181");
		    r.setClassCode(TravelClassType.first);
		    r.setServiceLevel("A");
		    
		    r.setPlaces(new PlacesType());
		    r.getPlaces().setCoach("31A");
		    r.getPlaces().setPlaceString("31-47");
		    r.getPlaces().setPlaceDescription("Window");
		    r.getPlaces().setPlaceIA5(new SequenceOfStringIA5());
		    r.getPlaces().getPlaceIA5().add("31A");
		    r.getPlaces().getPlaceIA5().add("31B");
		    r.getPlaces().setPlaceNum(new SequenceOfPlaceNum());
		    r.getPlaces().getPlaceNum().add(31L);
		    r.getPlaces().getPlaceNum().add(32L);
		    
		    r.setAdditionalPlaces(new PlacesType());
		    r.getAdditionalPlaces().setCoach("31A");
		    r.getAdditionalPlaces().setPlaceString("31-47");
		    r.getAdditionalPlaces().setPlaceDescription("Window");
		    r.getAdditionalPlaces().setPlaceIA5(new SequenceOfStringIA5());
		    r.getAdditionalPlaces().getPlaceIA5().add("31A");
		    r.getAdditionalPlaces().getPlaceIA5().add("31B");
		    r.getAdditionalPlaces().setPlaceNum(new SequenceOfPlaceNum());
		    r.getAdditionalPlaces().getPlaceNum().add(31L);
		    r.getAdditionalPlaces().getPlaceNum().add(32L);
		    
		    r.setBicyclePlaces(new PlacesType());
		    r.getBicyclePlaces().setCoach("31A");
		    r.getBicyclePlaces().setPlaceString("31-47");
		    r.getBicyclePlaces().setPlaceDescription("Window");
		    r.getBicyclePlaces().setPlaceIA5(new SequenceOfStringIA5());
		    r.getBicyclePlaces().getPlaceIA5().add("31A");
		    r.getBicyclePlaces().getPlaceIA5().add("31B");
		    r.getBicyclePlaces().setPlaceNum(new SequenceOfPlaceNum());
		    r.getBicyclePlaces().getPlaceNum().add(31L);
		    r.getBicyclePlaces().getPlaceNum().add(32L);
		    
		    
			r.setCompartmentDetails(new CompartmentDetailsType());
			r.getCompartmentDetails().setCoachType( 1L); 
			r.getCompartmentDetails().setCompartmentType( 			99L); 
			r.getCompartmentDetails().setSpecialAllocation( 		50L);		  	
			r.getCompartmentDetails().setCoachTypeDescr("xwz");    
			r.getCompartmentDetails().setCompartmentTypeDescr(	"xwz");			
			r.getCompartmentDetails().setSpecialAllocationDescr(	"xwz"); 		
			r.getCompartmentDetails().setPosition(CompartmentPositionType.upperLevel); 	
			
		    r.setNumberOfOverbooked(  200L);
		    r.setBerth(new SequenceOfBerthDetailData());
		    BerthDetailData b = new BerthDetailData();
		    r.getBerth().add(b);
		    b.setBerthType(BerthTypeType.single);
		    b.setGender(CompartmentGenderType.female);
		    b.setNumberOfBerths( 999L);
		    
		    
	        r.setTariff(new SequenceOfTariffType());
	        TariffType ta = new TariffType();
	        r.getTariff().add(ta);
	        ta.setNumberOfPassengers(1L);
 
		    ta.setPassengerType(PassengerType.senior);
	       	ta.setAgeBelow(           40L);
	       	ta.setAgeAbove(           60L);
	       	ta.setTraverlerid(new SequenceOfTravelerId());
	       	ta.getTraverlerid().add(1L);
	       	
	       	ta.setRestrictedToCountryOfResidence( false);
	       	ta.setRestrictedToRouteSection(new RouteSectionType());
	       	ta.getRestrictedToRouteSection().setStationCodeTable(CodeTableType.stationERA);              
	      	ta.getRestrictedToRouteSection().setFromStationNum( 123L);
	      	ta.getRestrictedToRouteSection().setFromStationIA5(      	"123");
			ta.getRestrictedToRouteSection().setToStationNum(       	234L);     					                
			ta.getRestrictedToRouteSection().setToStationIA5(        	"234"); 				                 
			ta.getRestrictedToRouteSection().setFromStationNameUTF8( 	"A");    
		    ta.getRestrictedToRouteSection().setToStationNameUTF8(   	"B");

		    ta.setSeriesDataDetails(new SeriesDetailType());
		    ta.getSeriesDataDetails().setSupplyingCarrier(2345L);		
		    ta.getSeriesDataDetails().setOfferIdentification(	99L);  		
		    ta.getSeriesDataDetails().setSeries(	23456L);                     
	              
	        ta.setTariffIdNum(       72L);
	       	ta.setTariffIdIA5(       "72");
	       	ta.setTariffDesc(         "Leasure Fare");
	       	ta.setReductionCard(new SequenceOfCardReferenceType());
	       	ta.getReductionCard().add(new CardReferenceType());
	            
	        ta.getReductionCard().get(0).setCardIssuerNum(1234L);
	        ta.getReductionCard().get(0).setCardIssuerIA5(   "1234");
	        ta.getReductionCard().get(0).setCardIdNum(5678L);
	        ta.getReductionCard().get(0).setCardIdIA5(  "5678");
			ta.getReductionCard().get(0).setCardName(   "testcard");
			ta.getReductionCard().get(0).setCardType(123L);
			ta.getReductionCard().get(0).setLeadingCardIdNum(3456L);                                     
			ta.getReductionCard().get(0).setLeadingCardIdIA5("3456");
			ta.getReductionCard().get(0).setTrailingCardIdNum(100L);
			ta.getReductionCard().get(0).setTrailingCardIdIA5("100");
		       
		    
			r.setPriceType(PriceTypeType.travelPrice);
	        r.setPrice( 12345L);
	        
		    r.setVatDetails(new SequenceOfVatDetail());
		    VatDetailType v1 = new VatDetailType();
		    r.getVatDetails().add(v1);
		    v1.setAmount(10L);
		    v1.setCountry(80L);
		    v1.setPercentage(70L);
		    v1.setVatId("IUDGTE");  
	        	        
	        r.setTypeOfSupplement(	9L);		
	        r.setNumberOfSupplements(	2L);
	        
			r.setLuggage(new LuggageRestrictionType());
	        
			r.getLuggage().setMaxHandLuggagePieces(    2L);
			r.getLuggage().setMaxNonHandLuggagePieces( 1L);
			r.getLuggage().setRegisteredLuggage(new SequenceOfRegisteredLuggageType());
			r.getLuggage().getRegisteredLuggage().add(new RegisteredLuggageType());
			r.getLuggage().getRegisteredLuggage().add(new RegisteredLuggageType());
			r.getLuggage().getRegisteredLuggage().get(0).setRegistrationId("IODHUV");
			r.getLuggage().getRegisteredLuggage().get(0).setMaxWeight(       20L);
		    r.getLuggage().getRegisteredLuggage().get(0).setMaxSize(        100L);	  
			r.getLuggage().getRegisteredLuggage().get(1).setRegistrationId("XXDHUV");
			r.getLuggage().getRegisteredLuggage().get(1).setMaxWeight(       21L);
		    r.getLuggage().getRegisteredLuggage().get(1).setMaxSize( 101L);	
		    
		    r.setInfoText("reservation");
		    
			
		}


		private static void populateTravelerData(TravelerData td) {
			
			td.setGroupName("myGroup");
			td.setPreferedLanguage("EN");
			td.setTraveler(new SequenceOfTravelerType());
			TravelerType t = new TravelerType();
			td.getTraveler().add(t);
				 
			t.setCountryOfIdCard(103L);
			t.setCountryOfPassport(102L);
			t.setCountryOfResidence(101L);
			t.setCustomerIdIA5("DZE5gT");
			t.setCustomerIdNum(12345L);
			t.setYearOfBirth(1901L);
			t.setMonthOfBirth(11L);
			t.setDayOfBirth(31L);
			t.setFirstName("John");
			t.setGender(GenderType.male);
			t.setIdCard("12345");
			t.setLastName("Dow");
			t.setPassengerType(PassengerType.senior);
			t.setPassengerWithReducedMobility(false);
			t.setPassportId("JDTS");
			t.setSecondName("Little");
			t.setTitle("PhD");
			t.setTicketHolder(true);
			t.setStatus(new SequenceOfCustomerStatusType());
			CustomerStatusType cs = new CustomerStatusType();
			t.getStatus().add(cs);
			cs.setCustomerStatus(1L);
			cs.setCustomerStatusDescr("senior");
		}


		private static void populateIssuingData(IssuingData id) {	
			
			id.setActivated(true);
			id.setCurrency("SRF");
			id.setCurrencyFract(3L);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			id.setExtension(e1);
			
			id.setIssuedOnLine(12L);
			id.setIssuedOnTrainIA5("123");
			id.setIssuedOnTrainNum(123L);
			id.setIssuerName("name");
			id.setIssuerIA5("1");
			id.setIssuerNum(15000L);
			id.setIssuerPNR("issuerTestPNR");
			id.setIssuingDay(1L);
			id.setIssuingTime(600L);
			id.setIssuingYear(2018L);
			id.setSecurePaperTicket(false);
			id.setSecurityProviderIA5("1");
			id.setSecurityProviderNum(1L);
			id.setSpecimen(true);
			id.setPointOfSale(new GeoCoordinateType());
			id.getPointOfSale().setGeoUnit(GeoUnitType.microDegree);
			id.getPointOfSale().setCoordinateSystem(GeoCoordinateSystemType.wgs84);
			id.getPointOfSale().setAccuracy(GeoUnitType.microDegree);
			id.getPointOfSale().setHemisphereLatitude(HemisphereLatitudeType.east);
			id.getPointOfSale().setHemisphereLongitude(HemisphereLongitudeType.north);
			id.getPointOfSale().setLatitude(56789L);
			id.getPointOfSale().setLongitude(12345L);
			
		}


		private static void populateExtension(List<ExtensionData> extensions) {
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			extensions.add(e1);
			
			ExtensionData e2 = new ExtensionData();
			e2.setExtensionId("2");
			e2.setExtensionData(UperEncoder.bytesFromHexString("83DA"));
			extensions.add(e2);
			
		}


		private static void populateControlDetail(ControlData controlDetail) {

			controlDetail.setAgeCheckRequired(false);
			controlDetail.setIdentificationByPassportId(false);
			controlDetail.setIdentificationByIdCard(false);
			controlDetail.setOnlineValidationRequired(false);
			controlDetail.setIdentificationItem(12L);
			controlDetail.setPassportValidationRequired(false);
			controlDetail.setRandomDetailedValidationRequired(50L);
			controlDetail.setExtension(new ExtensionData());
			controlDetail.getExtension().setExtensionId("1");
			controlDetail.getExtension().setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			
			controlDetail.setReductionCardCheckRequired(false);
			controlDetail.setInfoText("control");
			controlDetail.setIdentificationByCardReference(new SequenceOfCardReferenceType());
		
			CardReferenceType cr = new CardReferenceType();
			controlDetail.getIdentificationByCardReference().add(cr);
			cr.setCardIdIA5("5678");
			cr.setCardIdNum(5678L);
			cr.setCardIssuerIA5("1234");
			cr.setCardIssuerNum(1234L);
			cr.setCardName("testcard");
			cr.setCardType(123L);
			cr.setLeadingCardIdIA5("3456");
			cr.setLeadingCardIdNum(3456L);
			cr.setTrailingCardIdIA5("100");
			cr.setTrailingCardIdNum(100L);
			
			controlDetail.setIncludedTickets(new SequenceOfTicketLinkType());
			TicketLinkType tl = new TicketLinkType();		
			controlDetail.getIncludedTickets().add(tl);
			tl.setIssuerName("XYZ");
			tl.setIssuerPNR("LDWDUR45");
			tl.setProductOwnerIA5("IEFHU");
			tl.setProductOwnerNum(1080L);
			tl.setLinkMode(LinkMode.issuedTogether);
			tl.setReferenceIA5("KDJET");
			tl.setReferenceNum(801234567890L);
			tl.setTicketType(TicketType.openTicket);
			
		}

	}
