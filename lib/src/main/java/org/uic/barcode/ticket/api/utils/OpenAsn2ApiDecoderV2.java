/*
 * 
 */
package org.uic.barcode.ticket.api.utils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.uic.barcode.ticket.api.asn.omv2.BerthDetailData;
import org.uic.barcode.ticket.api.asn.omv2.CarCarriageReservationData;
import org.uic.barcode.ticket.api.asn.omv2.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv2.CompartmentDetailsType;
import org.uic.barcode.ticket.api.asn.omv2.ControlData;
import org.uic.barcode.ticket.api.asn.omv2.CountermarkData;
import org.uic.barcode.ticket.api.asn.omv2.CustomerCardData;
import org.uic.barcode.ticket.api.asn.omv2.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv2.DelayConfirmation;
import org.uic.barcode.ticket.api.asn.omv2.DeltaCoordinates;
import org.uic.barcode.ticket.api.asn.omv2.DocumentData;
import org.uic.barcode.ticket.api.asn.omv2.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv2.FIPTicketData;
import org.uic.barcode.ticket.api.asn.omv2.GeoCoordinateType;
import org.uic.barcode.ticket.api.asn.omv2.IncludedOpenTicketType;
import org.uic.barcode.ticket.api.asn.omv2.IssuingData;
import org.uic.barcode.ticket.api.asn.omv2.LineType;
import org.uic.barcode.ticket.api.asn.omv2.LuggageRestrictionType;
import org.uic.barcode.ticket.api.asn.omv2.OpenTicketData;
import org.uic.barcode.ticket.api.asn.omv2.ParkingGroundData;
import org.uic.barcode.ticket.api.asn.omv2.PassData;
import org.uic.barcode.ticket.api.asn.omv2.PlacesType;
import org.uic.barcode.ticket.api.asn.omv2.PolygoneType;
import org.uic.barcode.ticket.api.asn.omv2.RegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv2.RegisteredLuggageType;
import org.uic.barcode.ticket.api.asn.omv2.ReservationData;
import org.uic.barcode.ticket.api.asn.omv2.ReturnRouteDescriptionType;
import org.uic.barcode.ticket.api.asn.omv2.RouteSectionType;
import org.uic.barcode.ticket.api.asn.omv2.SeriesDetailType;
import org.uic.barcode.ticket.api.asn.omv2.StationPassageData;
import org.uic.barcode.ticket.api.asn.omv2.TariffType;
import org.uic.barcode.ticket.api.asn.omv2.TicketLinkType;
import org.uic.barcode.ticket.api.asn.omv2.TimeRangeType;
import org.uic.barcode.ticket.api.asn.omv2.TokenType;
import org.uic.barcode.ticket.api.asn.omv2.TrainLinkType;
import org.uic.barcode.ticket.api.asn.omv2.TravelerData;
import org.uic.barcode.ticket.api.asn.omv2.TravelerType;
import org.uic.barcode.ticket.api.asn.omv2.UicRailTicketData;
import org.uic.barcode.ticket.api.asn.omv2.ValidityPeriodDetailType;
import org.uic.barcode.ticket.api.asn.omv2.ValidityPeriodType;
import org.uic.barcode.ticket.api.asn.omv2.VatDetailType;
import org.uic.barcode.ticket.api.asn.omv2.ViaStationType;
import org.uic.barcode.ticket.api.asn.omv2.VoucherData;
import org.uic.barcode.ticket.api.asn.omv2.ZoneType;
import org.uic.barcode.ticket.api.impl.SimpleUicTicketObjectFactory;
import org.uic.barcode.ticket.api.spec.IBerth;
import org.uic.barcode.ticket.api.spec.IBerthTypeType;
import org.uic.barcode.ticket.api.spec.ICarCarriageReservation;
import org.uic.barcode.ticket.api.spec.ICardReference;
import org.uic.barcode.ticket.api.spec.ICompartmentDetails;
import org.uic.barcode.ticket.api.spec.ICompartmentGenderType;
import org.uic.barcode.ticket.api.spec.ICompartmentPositionType;
import org.uic.barcode.ticket.api.spec.IControlDetail;
import org.uic.barcode.ticket.api.spec.ICounterMark;
import org.uic.barcode.ticket.api.spec.ICustomerCard;
import org.uic.barcode.ticket.api.spec.ICustomerStatusDescription;
import org.uic.barcode.ticket.api.spec.IDelayConfirmation;
import org.uic.barcode.ticket.api.spec.IDocumentExtension;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IFipTicket;
import org.uic.barcode.ticket.api.spec.IGenderType;
import org.uic.barcode.ticket.api.spec.IGeoCoordinate;
import org.uic.barcode.ticket.api.spec.IGeoCoordinateSystemType;
import org.uic.barcode.ticket.api.spec.IGeoUnitType;
import org.uic.barcode.ticket.api.spec.IHemisphereLatitudeType;
import org.uic.barcode.ticket.api.spec.IHemisphereLongitudeType;
import org.uic.barcode.ticket.api.spec.IIncludedOpenTicket;
import org.uic.barcode.ticket.api.spec.IIssuingDetail;
import org.uic.barcode.ticket.api.spec.ILine;
import org.uic.barcode.ticket.api.spec.ILinkMode;
import org.uic.barcode.ticket.api.spec.ILoadingDeckType;
import org.uic.barcode.ticket.api.spec.ILuggageRestriction;
import org.uic.barcode.ticket.api.spec.IOpenTicket;
import org.uic.barcode.ticket.api.spec.IParkingGround;
import org.uic.barcode.ticket.api.spec.IPass;
import org.uic.barcode.ticket.api.spec.IPassengerType;
import org.uic.barcode.ticket.api.spec.IPlaces;
import org.uic.barcode.ticket.api.spec.IPolygone;
import org.uic.barcode.ticket.api.spec.IPriceTypeType;
import org.uic.barcode.ticket.api.spec.IRegionalValidity;
import org.uic.barcode.ticket.api.spec.IRegisteredLuggage;
import org.uic.barcode.ticket.api.spec.IReservation;
import org.uic.barcode.ticket.api.spec.IReturnRouteDescription;
import org.uic.barcode.ticket.api.spec.IRoofRackType;
import org.uic.barcode.ticket.api.spec.IRouteSection;
import org.uic.barcode.ticket.api.spec.ISeriesDataDetails;
import org.uic.barcode.ticket.api.spec.IServiceBrand;
import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.IStationPassage;
import org.uic.barcode.ticket.api.spec.ITariff;
import org.uic.barcode.ticket.api.spec.ITicketLink;
import org.uic.barcode.ticket.api.spec.ITicketType;
import org.uic.barcode.ticket.api.spec.ITimeRange;
import org.uic.barcode.ticket.api.spec.IToken;
import org.uic.barcode.ticket.api.spec.ITrainLink;
import org.uic.barcode.ticket.api.spec.ITravelClassType;
import org.uic.barcode.ticket.api.spec.ITraveler;
import org.uic.barcode.ticket.api.spec.ITravelerDetail;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.ticket.api.spec.IUicTicketObjectFactory;
import org.uic.barcode.ticket.api.spec.IValidityDetails;
import org.uic.barcode.ticket.api.spec.IValidityRange;
import org.uic.barcode.ticket.api.spec.IVatDetail;
import org.uic.barcode.ticket.api.spec.IViaStation;
import org.uic.barcode.ticket.api.spec.IVoucher;
import org.uic.barcode.ticket.api.spec.IZone;


/**
 * The Class OpenAsn2ApiDecoder.
 */
public class OpenAsn2ApiDecoderV2 {
	
	IUicTicketObjectFactory factory = SimpleUicTicketObjectFactory.getInstance();
	
	/**
	 * Decode from asn.1 unaligned PER encoded data. 
	 *
	 * @param asnUicRailTicketData the asn uic rail ticket data
	 * @return the decoded uic rail ticket
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public IUicRailTicket decodeFromAsn (UicRailTicketData asnUicRailTicketData) throws IOException{		
					
		IUicRailTicket uicRailTicket = factory.createUicRailTicket();
		
		populateFromAsn1Model(uicRailTicket, asnUicRailTicketData);
		
		return uicRailTicket;				

	}	
	
	/**
	 * Decode from asn.1 unaligned PER encoded data. 
	 *
	 * @param data byte array of the asn.1 encoded FCB data
	 * @return the decoded uic rail ticket
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public IUicRailTicket decodeFromAsn (byte[] data) throws IOException{		
		
		UicRailTicketData asnUicRailTicketData = UicRailTicketData.decode(data);
							
		IUicRailTicket uicRailTicket = factory.createUicRailTicket();
		
		populateFromAsn1Model(uicRailTicket, asnUicRailTicketData);
		
		return uicRailTicket;				

	}	

	
	


	
	
	/**
	 * Populate from asn1 model.
	 *
	 * @param uicRailTicket the uic rail ticket
	 * @param asnUicRailTicketData the asn uic rail ticket data
	 */
	protected void populateFromAsn1Model(IUicRailTicket uicRailTicket, UicRailTicketData asnUicRailTicketData) {
		
		if (asnUicRailTicketData.getExtension()!= null && !asnUicRailTicketData.getExtension().isEmpty()) {
			for (ExtensionData asnExtension : asnUicRailTicketData.getExtension()){
				uicRailTicket.addExtension(convertExtension(asnExtension));
			}
		}
		
		if (asnUicRailTicketData.getIssuingDetail() != null) { 				
			populateIssuingDetail(asnUicRailTicketData.getIssuingDetail(), uicRailTicket.getIssuerDetails());			
		}
		
		if (asnUicRailTicketData.getControlDetail() != null) {
			populateControlDetails(asnUicRailTicketData.getControlDetail(),uicRailTicket.getControlDetails());
		}
		
		if (asnUicRailTicketData.getTravelerDetail() != null) {
			populateTravelerDetails(asnUicRailTicketData.getTravelerDetail(),uicRailTicket.getTravelerDetails());
		}		
		
		if (asnUicRailTicketData.getTransportDocument() != null && !asnUicRailTicketData.getTransportDocument().isEmpty()) {
			
			// date is already converted to local time, use UTC for internal calculations
			Date localIssuingDate = uicRailTicket.getIssuerDetails().getIssuingDate();
			Date issuingDate = DateTimeUtils.dateToUTC(localIssuingDate);
			
			populateTravelDocuments(asnUicRailTicketData.getTransportDocument(),uicRailTicket, issuingDate);
		}		
	
		
	}
	
	
	
	

	
	/**
	 * Populate travel documents.
	 *
	 * @param asnTransportDocuments the asn transport documents
	 * @param uicRailTicket the uic rail ticket
	 * @param issuingDate the issuing date
	 */
	protected void populateTravelDocuments(List<DocumentData> asnTransportDocuments,IUicRailTicket uicRailTicket, Date issuingDate) {
		
		for ( DocumentData asnDocument : asnTransportDocuments){
			
			if (asnDocument.getTicket() == null) {
				break;
			}
			
			if (asnDocument.getTicket().getExtension() != null) {				
				IDocumentExtension ticket = convertDocumentExtension(asnDocument.getTicket().getExtension());
				if (asnDocument.getToken() != null) {
					IToken token = convertToken(asnDocument.getToken());										
					ticket.setToken(token);
				}
				uicRailTicket.addDocumentExtension(ticket);
			}
			
			if (asnDocument.getTicket().getCarCarriageReservation() != null) {																
				ICarCarriageReservation ticket  = convertCarCarriage(asnDocument.getTicket().getCarCarriageReservation(),issuingDate);
				if (asnDocument.getToken() != null) {
					IToken token = convertToken(asnDocument.getToken());										
					ticket.setToken(token);
				}
				uicRailTicket.addCarCarriageReservation(ticket);				
			}
			
			if (asnDocument.getTicket().getCounterMark() != null) {
				ICounterMark ticket = convertCountermark(asnDocument.getTicket().getCounterMark(),issuingDate);
				if (asnDocument.getToken() != null) {
					IToken token = convertToken(asnDocument.getToken());										
					ticket.setToken(token);
				}
				uicRailTicket.addCounterMark(ticket);				
			}			
			
			if (asnDocument.getTicket().getCustomerCard()!= null) {
				ICustomerCard ticket = convertCustomerCard(asnDocument.getTicket().getCustomerCard(),issuingDate);
				if (asnDocument.getToken() != null) {
					IToken token = convertToken(asnDocument.getToken());										
					ticket.setToken(token);
				}
				uicRailTicket.addCustomerCard(ticket);				
			}
			
			if (asnDocument.getTicket().getFipTicket()!= null) {
				IFipTicket ticket = convertFipTicket(asnDocument.getTicket().getFipTicket(),issuingDate);
				if (asnDocument.getToken() != null) {
					IToken token = convertToken(asnDocument.getToken());										
					ticket.setToken(token);
				}
				uicRailTicket.addFipTicket(ticket);				
			}			
				
			if (asnDocument.getTicket().getOpenTicket()!= null) {
				IOpenTicket ticket = convertOpenTicket(asnDocument.getTicket().getOpenTicket(),issuingDate);
				if (asnDocument.getToken() != null) {
					IToken token = convertToken(asnDocument.getToken());										
					ticket.setToken(token);
				}
				uicRailTicket.addOpenTicket(ticket);				
			}			
			
			
			if (asnDocument.getTicket().getParkingGround()!= null) {
				IParkingGround ticket = convertParkingGround(asnDocument.getTicket().getParkingGround(),issuingDate);
				if (asnDocument.getToken() != null) {
					IToken token = convertToken(asnDocument.getToken());										
					ticket.setToken(token);
				}
				uicRailTicket.addParkingGround(ticket);				
			}			
			
			if (asnDocument.getTicket().getPass() != null) {
				IPass ticket = convertPass(asnDocument.getTicket().getPass(),issuingDate);
				if (asnDocument.getToken() != null) {
					IToken token = convertToken(asnDocument.getToken());										
					ticket.setToken(token);
				}
				uicRailTicket.addPass(ticket);				
			}			

			if (asnDocument.getTicket().getStationPassage() != null) {
				IStationPassage ticket = convertStationPassage(asnDocument.getTicket().getStationPassage(),issuingDate);
				if (asnDocument.getToken() != null) {
					IToken token = convertToken(asnDocument.getToken());										
					ticket.setToken(token);
				}
				uicRailTicket.addStationPassage(ticket);				
			}	
			
			if (asnDocument.getTicket().getReservation() != null) {
				IReservation ticket = convertReservation(asnDocument.getTicket().getReservation(),issuingDate);
				if (asnDocument.getToken() != null) {
					IToken token = convertToken(asnDocument.getToken());										
					ticket.setToken(token);
				}
				uicRailTicket.addReservation(ticket);				
			}			
			
			if (asnDocument.getTicket().getVoucher()!= null) {
				IVoucher ticket = convertVoucher(asnDocument.getTicket().getVoucher(),issuingDate);
				if (asnDocument.getToken() != null) {
					IToken token = convertToken(asnDocument.getToken());										
					ticket.setToken(token);
				}
				uicRailTicket.addVoucher(ticket);				
			}
			
			if (asnDocument.getTicket().getDelayConfirmation()!= null) {
				IDelayConfirmation ticket = convertDelayConfirmation(asnDocument.getTicket().getDelayConfirmation(),issuingDate);
				if (asnDocument.getToken() != null) {
					IToken token = convertToken(asnDocument.getToken());										
					ticket.setToken(token);
				}
				uicRailTicket.addDelayConfirmation(ticket);				
			}			
		}	
	}










	private IToken convertToken(TokenType asnToken) {
		
		if (asnToken == null) return null;
		
		IToken token = factory.createToken();
		token.setToken(asnToken.getToken());
		token.setTokenProvider(UicEncoderUtils.mapToString(asnToken.getTokenProviderNum(), asnToken.getTokenProviderIA5()));
		token.setTokenSpecification(asnToken.getTokenSpecification());
		return token;
	}


	
	private IDelayConfirmation convertDelayConfirmation(DelayConfirmation asnDocument, Date issuingDate) {
		
		IDelayConfirmation document = factory.createDelayConfirmation();
		document.setReference(UicEncoderUtils.mapToString(asnDocument.getReferenceNum().longValue(),asnDocument.getReferenceIA5()));		
		
	
		document.setExtension(convertExtension(asnDocument.getExtension()));
		document.setInfoText(asnDocument.getInfoText());		
		
 		if (asnDocument.getStationCodeTable()!=null){
			document.setStationCodeTable(IStationCodeTable.valueOf(asnDocument.getStationCodeTable().toString()));
		}	
		document.setStation(UicEncoderUtils.mapToString(asnDocument.getStationNum(),asnDocument.getStationIA5()));
		
		document.setStationName(asnDocument.getStationNameUTF8());
		
		document.setArrivalDate(asnDocument.getPlannedArrivalDate());
		document.setArrivalUTCoffset(asnDocument.getDepartureUTCOffset());

		if (asnDocument.getConfirmationType() != null) {
			document.setConfirmationType(asnDocument.getConfirmationType().ordinal());
		}
		
		if (asnDocument.getDelay() != null) {
			document.setDelay(asnDocument.getDelay().intValue());
		}		
		
		if (asnDocument.getTrainCancelled() != null) {
			document.setTrainCancelled(asnDocument.getTrainCancelled());
		} else {
			document.setTrainCancelled(false);
		}
		
		if (asnDocument.getAffectedTickets() !=null && !asnDocument.getAffectedTickets().isEmpty()) {
			for (TicketLinkType asnTicketLink : asnDocument.getAffectedTickets()) {
				document.addLinkedTicket(convertTicketLink(asnTicketLink));
			}
		}

		return document;
	}






	/**
	 * Convert reservation.
	 *
	 * @param asnDocument the asn document
	 * @param issuingDate the issuing date
	 * @return the i reservation
	 */
	protected IReservation convertReservation(ReservationData asnDocument,	Date issuingDate) {
		
		IReservation document = factory.createReservation();
		document.setReference(UicEncoderUtils.mapToString(asnDocument.getReferenceNum(),asnDocument.getReferenceIA5()));		
		
		document.setProductId(UicEncoderUtils.mapToString(asnDocument.getProductIdNum(),asnDocument.getProductIdIA5()));
		document.setProductOwner(UicEncoderUtils.mapToString(asnDocument.getProductOwnerNum(),asnDocument.getProductOwnerIA5()));		
		
		document.setExtension(convertExtension(asnDocument.getExtension()));
		document.setInfoText(asnDocument.getInfoText());		
		
 		if (asnDocument.getStationCodeTable()!=null){
			document.setStationCodeTable(IStationCodeTable.valueOf(asnDocument.getStationCodeTable().name()));
		}	
		document.setFromStation(UicEncoderUtils.mapToString(asnDocument.getFromStationNum(),asnDocument.getFromStationIA5()));
		document.setToStation(UicEncoderUtils.mapToString(asnDocument.getToStationNum(),asnDocument.getToStationIA5()));
		document.setFromStationName(asnDocument.getFromStationNameUTF8());
		document.setToStationName(asnDocument.getToStationNameUTF8());		
		
		document.setDepartureDate(asnDocument.getDepartureDate(issuingDate));
		document.setArrivalDate(asnDocument.getArrivalDate(issuingDate));
		
		document.setDepartureUTCoffset(asnDocument.getDepartureUTCOffset());
		if (asnDocument.getArrivalUTCOffset() != null) {
			document.setArrivalUTCoffset(asnDocument.getArrivalUTCOffset());
		} else {
			document.setArrivalUTCoffset(asnDocument.getDepartureUTCOffset());
		}
		
		if(asnDocument.getClassCode()!=null){
			document.setClassCode(ITravelClassType.valueOf(asnDocument.getClassCode().name()));
		}
		
		document.setCompartmentDetails(convertCompartmentDetails(asnDocument.getCompartmentDetails()));

		document.setLuggageRestriction(convertLuggageRestriction(asnDocument.getLuggage()));
		
		if (asnDocument.getNumberOfOverbooked() != null) {
			document.setNumberOfOverbooked(asnDocument.getNumberOfOverbooked().intValue());
		}
		
		if (asnDocument.getNumberOfSupplements() != null) {
			document.setNumberOfSupplements(asnDocument.getNumberOfSupplements().intValue());
		}
		
		if (asnDocument.getPlaces()!=null) {
			document.setPlaces(convertPlaces(asnDocument.getPlaces()));
		}

		if (asnDocument.getBicyclePlaces()!=null) {
			document.setBicyclePlaces(convertPlaces(asnDocument.getBicyclePlaces()));
		}
		
		document.setPriceType(IPriceTypeType.valueOf(asnDocument.getPriceType().name()));
		
		IServiceBrand serviceBrand = factory.createServiceBrand();
		
		if (asnDocument.getServiceBrand()!=null){
			serviceBrand.setServiceBrand(asnDocument.getServiceBrand().intValue());
		}
		serviceBrand.setServiceBrandAbbreviation(asnDocument.getServiceBrandAbrUTF8());
		serviceBrand.setServiceBrandDescription(asnDocument.getServiceBrandNameUTF8());
		
		document.setServiceBrand(serviceBrand);
		
		if (asnDocument.getServiceLevel()!=null && asnDocument.getServiceLevel().length() != 0) {
			document.setServiceLevel(asnDocument.getServiceLevel());
		}
		
		document.setTrain(UicEncoderUtils.mapToString(asnDocument.getTrainNum(), asnDocument.getTrainIA5()));		
		
		if (asnDocument.getTypeOfSupplement()!=null) {
			document.setTypeOfSupplement(asnDocument.getTypeOfSupplement().intValue());
		}
		
		if (asnDocument.getBerth()!=null && !asnDocument.getBerth().isEmpty()){
			for (BerthDetailData asnBerth : asnDocument.getBerth()){
				document.addBerth(convertBerth(asnBerth));
			}
		}
		
        if (asnDocument.getCarrierNum()!=null && !asnDocument.getCarrierNum().isEmpty()){
            for(Long carrier :asnDocument.getCarrierNum()){
         	   document.addCarrier(carrier.toString());
            }
        }
        if (asnDocument.getCarrierIA5()!=null && !asnDocument.getCarrierIA5().isEmpty()){
            for(String carrier :asnDocument.getCarrierIA5()){
         	   document.addCarrier(carrier);
            }
         }         
		
		if (asnDocument.getTariff()!=null && !asnDocument.getTariff().isEmpty()){
			for (TariffType asnTariff : asnDocument.getTariff()) {
				document.addTariff(convertTariff(asnTariff));
			}
		}
		
		document.setPrice(asnDocument.getPrice());
		
		if (asnDocument.getVatDetails() != null && !asnDocument.getVatDetails().isEmpty()){
			for (VatDetailType vat : asnDocument.getVatDetails()) {
				document.addVatDetail(decodeVatDetail(vat));
			}
		}
		
		return document;
	}









	/**
	 * Convert car carriage.
	 *
	 * @param asnDocument the asn document
	 * @param issuingDate the issuing date
	 * @return the i car carriage reservation
	 */
	protected ICarCarriageReservation convertCarCarriage(CarCarriageReservationData asnDocument , Date issuingDate) {
		
		ICarCarriageReservation document = factory.createCarCarriageReservation();
		document.setReference(UicEncoderUtils.mapToString(asnDocument.getReferenceNum(),asnDocument.getReferenceIA5()));	
		
		document.setProductId(UicEncoderUtils.mapToString(asnDocument.getProductIdNum(),asnDocument.getProductIdIA5()));
		document.setProductOwner(UicEncoderUtils.mapToString(asnDocument.getProductOwnerNum(),asnDocument.getProductOwnerIA5()));		
		
		document.setExtension(convertExtension(asnDocument.getExtension()));
		document.setInfoText(asnDocument.getInfoText());		
		
 		if (asnDocument.getStationCodeTable()!=null){
			document.setStationCodeTable(IStationCodeTable.valueOf(asnDocument.getStationCodeTable().name()));
		}	
		document.setFromStation(UicEncoderUtils.mapToString(asnDocument.getFromStationNum(),asnDocument.getFromStationIA5()));
		document.setToStation(UicEncoderUtils.mapToString(asnDocument.getToStationNum(),asnDocument.getToStationIA5()));
		document.setFromStationName(asnDocument.getFromStationNameUTF8());
		document.setToStationName(asnDocument.getToStationNameUTF8());			
		
		document.setBeginLoading(asnDocument.getBeginLoadingDate(issuingDate));
		document.setEndLoading(asnDocument.getEndLoadingDate(issuingDate));
		
		document.setLoadingTimeUTCoffset(asnDocument.getLoadingUTCOffset());

			
		if(asnDocument.getAttachedBicycles()!=null){
			document.setAttachedBicycles(asnDocument.getAttachedBicycles().intValue());
		}
		
		if (asnDocument.getAttachedBoats()!=null) {
			document.setAttachedBoats(asnDocument.getAttachedBoats().intValue());
		}
		
		if(asnDocument.getAttachedSurfboards()!=null) {
			document.setAttachedSurfboards(asnDocument.getAttachedSurfboards().intValue());
		}
		
		if (asnDocument.getBoatCategory()!=null){
			document.setBoatCategory(asnDocument.getBoatCategory().intValue());
		}
		
		if(asnDocument.getCarCategory()!=null){
			document.setCarCategory(asnDocument.getCarCategory().intValue());
		}
		
		if (asnDocument.getLoadingDeck()!=null){
			document.setLoadingDeck(ILoadingDeckType.valueOf(asnDocument.getLoadingDeck().name()));
		}
		
		if(asnDocument.getLoadingListEntry()!=null){
			document.setLoadingListEntry(asnDocument.getLoadingListEntry().intValue());
		}
		
		if (asnDocument.getRoofRackHeight()!=null){
			document.setRoofRackHeight(asnDocument.getRoofRackHeight().intValue());
		}
		
		document.setNumberPlate(asnDocument.getNumberPlate());
		document.setTrailerPlate(asnDocument.getTrailerPlate());
		
		if(asnDocument.getRoofRackType()!=null){
			document.setRoofRackType(IRoofRackType.valueOf(asnDocument.getRoofRackType().name()));
		}
		
		if(asnDocument.getTextileRoof()) {
			document.setTextileRoof(asnDocument.getTextileRoof());
		}

		
		document.setCompartmentDetails(convertCompartmentDetails(asnDocument.getCompartmentDetails()));		

        if (asnDocument.getCarrierNum()!=null && !asnDocument.getCarrierNum().isEmpty()){
            for(Long carrier :asnDocument.getCarrierNum()){
         	   document.addCarrier(carrier.toString());
            }
        }
        if (asnDocument.getCarrierIA5()!=null && !asnDocument.getCarrierIA5().isEmpty()){
            for(String carrier :asnDocument.getCarrierIA5()){
         	   document.addCarrier(carrier);
            }
         }   

		if (asnDocument.getCoach()!=null) {
			document.setCoach(asnDocument.getCoach());
		}
		if (asnDocument.getPlace()!=null) {
			document.setPlace(asnDocument.getPlace());
		}

		
		document.setPriceType(IPriceTypeType.valueOf(asnDocument.getPriceType().name()));
		
		IServiceBrand serviceBrand = factory.createServiceBrand();
		
		if (asnDocument.getServiceBrand()!=null){
			serviceBrand.setServiceBrand(asnDocument.getServiceBrand().intValue());
		}
		serviceBrand.setServiceBrandAbbreviation(asnDocument.getServiceBrandAbrUTF8());
		serviceBrand.setServiceBrandDescription(asnDocument.getServiceBrandNameUTF8());
		
		document.setServiceBrand(serviceBrand);

		document.setTrain(UicEncoderUtils.mapToString(asnDocument.getTrainNum(), asnDocument.getTrainIA5()));		
				

		if (asnDocument.getTariff()!=null){
			document.setTariff(convertTariff(asnDocument.getTariff()));
		}
		
		document.setPrice(asnDocument.getPrice());
		
		if (asnDocument.getVatDetails() != null && !asnDocument.getVatDetails().isEmpty()){
			for (VatDetailType vat : asnDocument.getVatDetails()) {
				document.addVatDetail(decodeVatDetail(vat));
			}
		}
		
		return null;
	}
	

	private IVatDetail decodeVatDetail(VatDetailType asnVat) {
		
		IVatDetail vat = factory.createVatDetail();
		
		vat.setAmount(asnVat.getAmount());
		if (asnVat.getPercentage() != null){
			vat.setPercentage(asnVat.getPercentage().intValue());
		}
		if (asnVat.getCountry() != null) {
			vat.setCountry(asnVat.getCountry().intValue());
		}
		vat.setVatId(asnVat.getVatId());
		return vat;
	}







	/**
	 * Convert compartment details.
	 *
	 * @param asnDetails the asn details
	 * @return the i compartment details
	 */
	protected ICompartmentDetails convertCompartmentDetails(CompartmentDetailsType asnDetails) {
		
		if (asnDetails == null) return null;
		
		ICompartmentDetails details = factory.createCompartmentDetails();

		if (asnDetails.getCompartmentType()!=null) {
			details.setCompartmentType(asnDetails.getCompartmentType().intValue());
		}
		
		if (asnDetails.getCoachType()!=null){
			details.setCoachType(asnDetails.getCoachType().intValue());
		}
		
		if (asnDetails.getSpecialAllocation()!=null) {
			details.setSpecialAllocation(asnDetails.getSpecialAllocation().intValue());
		}
		
		details.setCoachTypeDescr(asnDetails.getCoachTypeDescr());
		details.setCompartmentTypeDescr(asnDetails.getCompartmentTypeDescr());
		details.setSpecialAllocationDescr(asnDetails.getSpecialAllocationDescr());

		details.setPosition(ICompartmentPositionType.valueOf(asnDetails.getPosition().name()));
		
		return details;
	}

	/**
	 * Convert places.
	 *
	 * @param asnPlaces the asn places
	 * @return the i places
	 */
	protected IPlaces convertPlaces(PlacesType asnPlaces) {
		
		if (asnPlaces == null) return null;
		
		IPlaces places = factory.createPlaces();
		
		places.setCoach(asnPlaces.getCoach());
		places.setPlaceDescription(asnPlaces.getPlaceDescription());
		
		if (asnPlaces.getPlaceNum()!=null && !asnPlaces.getPlaceNum().isEmpty()){
			for (Long place: asnPlaces.getPlaceNum()){
				places.addPlace(place.toString());
			}
		} 
		
		if (asnPlaces.getPlaceIA5()!=null && !asnPlaces.getPlaceIA5().isEmpty()){
			for (String place: asnPlaces.getPlaceIA5()){
				places.addPlace(place);
			}
		} 	
		
		places.setPlaceString(asnPlaces.getPlaceString());

		return places;
	}

	/**
	 * Convert berth.
	 *
	 * @param asnBerth the asn berth
	 * @return the i berth
	 */
	protected IBerth convertBerth(BerthDetailData asnBerth) {
		
		if (asnBerth == null) return null;
		
		IBerth berth = factory.createBerth();
		if (asnBerth.getGender()!= null) {
			berth.setGender(ICompartmentGenderType.valueOf(asnBerth.getGender().name()));
		}
		if (asnBerth.getNumberOfBerths()!=null) {
			berth.setNumberOfBerths(asnBerth.getNumberOfBerths().intValue());
		}
		if (asnBerth.getBerthType()!=null){
			berth.setType(IBerthTypeType.valueOf(asnBerth.getBerthType().name()));
		}

		return berth;
	}	
	
	/**
	 * Convert countermark.
	 *
	 * @param asnDocument the asn document
	 * @param issuingDate the issuing date
	 * @return the i counter mark
	 */
	protected ICounterMark convertCountermark(CountermarkData asnDocument , Date issuingDate) {
		
		ICounterMark document = factory.createCounterMark();
		
		document.setProductId(UicEncoderUtils.mapToString(asnDocument.getProductIdNum(),asnDocument.getProductIdIA5()));
		document.setProductOwner(UicEncoderUtils.mapToString(asnDocument.getProductOwnerNum(),asnDocument.getProductOwnerIA5()));		
		
		if(asnDocument.getClassCode()!=null){
			document.setClassCode(ITravelClassType.valueOf(asnDocument.getClassCode().name()));
		}
		
		document.setValidFrom(asnDocument.getValidFromDate(issuingDate));
		document.setValidUntil(asnDocument.getValidUntilDate(issuingDate));
		
		document.setValidFromUTCoffset(asnDocument.getValidFromUTCOffset());
		if (asnDocument.getValidUntilUTCOffset() != null) {
			document.setValidUntilUTCoffset(asnDocument.getValidUntilUTCOffset());
		} else {
			document.setValidUntilUTCoffset(asnDocument.getValidFromUTCOffset());
		}
		

		document.setExtension(convertExtension(asnDocument.getExtension()));
		document.setInfoText(asnDocument.getInfoText());
		
		document.setReference(UicEncoderUtils.mapToString(asnDocument.getReferenceNum(),asnDocument.getReferenceIA5()));		
		document.setTicketReference(UicEncoderUtils.mapToString(asnDocument.getTicketReferenceNum(),asnDocument.getTicketReferenceIA5()));	
		
		document.setFromStation(UicEncoderUtils.mapToString(asnDocument.getFromStationNum(),asnDocument.getFromStationIA5()));
		document.setToStation(UicEncoderUtils.mapToString(asnDocument.getToStationNum(),asnDocument.getToStationIA5()));
		
		document.setFromStationName(asnDocument.getFromStationNameUTF8());
		document.setToStationName(asnDocument.getToStationNameUTF8());	
		document.setValidRegionDesc(asnDocument.getValidRegionDesc());
		
		if (asnDocument.getValidRegion()!= null && !asnDocument.getValidRegion().isEmpty()) {
			for (RegionalValidityType validRegion  :asnDocument.getValidRegion()){
				document.addValidRegionList(convertValidRegion(validRegion, issuingDate));
			}
		}		
		
		document.setReturnDescription(convertReturnDescription(asnDocument.getReturnDescription(), issuingDate));

		
		if(asnDocument.getReturnIncluded()!=null){
			document.setReturnIncluded(asnDocument.getReturnIncluded());
		} else {
			document.setReturnIncluded(false);
		}
		
				
		
		document.setGroupName(asnDocument.getGroupName());
		
		if (asnDocument.getNumberOfCountermark() != null) {
			document.setNumberOfCountermark(asnDocument.getNumberOfCountermark().intValue());
		}
		
		if (asnDocument.getTotalOfCountermarks() != null) {
			document.setTotalOfCountermarks(asnDocument.getTotalOfCountermarks().intValue());
		}
		
		return document;		

	}

	/**
	 * Convert return description.
	 *
	 * @param asnReturnRoute the asn return route
	 * @param issuingDate the issuing date
	 * @return the i return route description
	 */
	protected IReturnRouteDescription convertReturnDescription(ReturnRouteDescriptionType asnReturnRoute, Date issuingDate) {
		
		if (asnReturnRoute == null) return null;
		
		IReturnRouteDescription route = factory.createReturnRouteDescription();
		
		route.setFromStation(UicEncoderUtils.mapToString(asnReturnRoute.getFromStationNum(),asnReturnRoute.getFromStationIA5()));
		route.setToStation(UicEncoderUtils.mapToString(asnReturnRoute.getToStationNum(),asnReturnRoute.getToStationIA5()));
		
		route.setFromStationName(asnReturnRoute.getFromStationNameUTF8());
		route.setToStationName(asnReturnRoute.getToStationNameUTF8());	
		route.setValidRegionDesc(asnReturnRoute.getValidReturnRegionDesc());
		
		if (asnReturnRoute.getValidReturnRegion()!= null && !asnReturnRoute.getValidReturnRegion().isEmpty()) {
			for (RegionalValidityType validRegion  :asnReturnRoute.getValidReturnRegion()){
				route.addValidRegionList(convertValidRegion(validRegion, issuingDate));
			}
		}
		
		return route;
	}	

	/**
	 * Convert luggage restriction.
	 *
	 * @param asnLuggage the asn luggage
	 * @return the i luggage restriction
	 */
	protected ILuggageRestriction convertLuggageRestriction(LuggageRestrictionType asnLuggage) {
		
		if (asnLuggage == null) return null;
		
		ILuggageRestriction luggage = factory.createLuggageRestriction();
		if (asnLuggage.getMaxHandLuggagePieces()!=null){
			luggage.setMaxHandLuggagePieces(asnLuggage.getMaxHandLuggagePieces().intValue());
		}
		if (asnLuggage.getMaxNonHandLuggagePieces()!=null){
			luggage.setMaxNonHandLuggagePieces(asnLuggage.getMaxNonHandLuggagePieces().intValue());
		}
		
		
		if (asnLuggage.getRegisteredLuggage()!= null &&!asnLuggage.getRegisteredLuggage().isEmpty()){
			for (RegisteredLuggageType rl: asnLuggage.getRegisteredLuggage()){
				luggage.addRegisteredLuggage(convertRegisteredLuggage(rl));
			}
		}
		return luggage;
	}

	/**
	 * Convert registered luggage.
	 *
	 * @param rl the rl
	 * @return the i registered luggage
	 */
	protected IRegisteredLuggage convertRegisteredLuggage(RegisteredLuggageType rl) {
		
		if (rl == null) return null;
		
		IRegisteredLuggage luggage= factory.createRegisteredLuggage();
		if (rl.getMaxSize()!=null){
			if (rl.getMaxSize()!=null) {
				luggage.setMaxSize(rl.getMaxSize().intValue());
			}
		}
		if (rl.getMaxWeight()!=null){
			if(rl.getMaxWeight()!=null){
				luggage.setMaxWeight(rl.getMaxWeight().intValue());
			}
		}
		luggage.setRegistrationId(rl.getRegistrationId());
		return luggage;
	}
	
	
	/**
	 * Convert open ticket.
	 *
	 * @param asnDocument the asn document
	 * @param issuingDate the issuing date
	 * @return the i open ticket
	 */
	protected IOpenTicket convertOpenTicket(OpenTicketData asnDocument , Date issuingDate) {
		
		IOpenTicket document = factory.createOpenTicket();
		
		if(asnDocument.getClassCode()!=null){
			document.setClassCode(ITravelClassType.valueOf(asnDocument.getClassCode().name()));
		}
		
		document.setValidFrom(asnDocument.getValidFromDate(issuingDate));
		document.setValidUntil(asnDocument.getValidUntilDate(issuingDate));
		
		document.setValidFromUTCoffset(asnDocument.getValidFromUTCOffset());
		if (asnDocument.getValidUntilUTCOffset() != null) {
			document.setValidUntilUTCoffset(asnDocument.getValidUntilUTCOffset());
		} else {
			document.setValidUntilUTCoffset(asnDocument.getValidFromUTCOffset());
		}
		
	
		document.setExtension(convertExtension(asnDocument.getExtension()));
		document.setInfoText(asnDocument.getInfoText());
		document.setProductId(UicEncoderUtils.mapToString(asnDocument.getProductIdNum(),asnDocument.getProductIdIA5()));
		document.setProductOwner(UicEncoderUtils.mapToString(asnDocument.getProductOwnerNum(),asnDocument.getProductOwnerIA5()));
		document.setReference(UicEncoderUtils.mapToString(asnDocument.getReferenceNum(),asnDocument.getReferenceIA5()));		

         if (asnDocument.getExcludedServiceBrands()!=null && !asnDocument.getExcludedServiceBrands().isEmpty()){
             for(Long number :asnDocument.getExcludedServiceBrands()){
          	   document.addExcludedServiceBrand(number.intValue());
             }
          }

         if (asnDocument.getCarriersNum()!=null && !asnDocument.getCarriersNum().isEmpty()){
             for(Long carrier :asnDocument.getCarriersNum()){
          	   document.addIncludedCarrier(carrier.toString());
             }
         }
         if (asnDocument.getCarriersIA5()!=null && !asnDocument.getCarriersIA5().isEmpty()){
             for(String carrier :asnDocument.getCarriersIA5()){
          	   document.addIncludedCarrier(carrier);
             }
         }   
         
         
        if (asnDocument.getIncludedServiceBrands()!=null && !asnDocument.getIncludedServiceBrands().isEmpty()){
             for(Long number :asnDocument.getIncludedServiceBrands()){
          	   document.addIncludedServiceBrand(number.intValue());
             }
        }
        
       if (asnDocument.getIncludedTransportTypes()!=null && !asnDocument.getIncludedTransportTypes().isEmpty()){
    	   for(Long number :asnDocument.getIncludedTransportTypes()){
         	   document.addInludedTransportType(number.intValue());
           }
       }  
       
       if (asnDocument.getExcludedTransportTypes()!=null && !asnDocument.getExcludedTransportTypes().isEmpty()){
    	   for(Long number :asnDocument.getExcludedTransportTypes()){
         	   document.addExcludedTransportType(number.intValue());
           }
       }         
        
        
         
        if (asnDocument.getTariffs()!=null && !asnDocument.getTariffs().isEmpty()){
             for(TariffType asnTariff :asnDocument.getTariffs()){
          	   document.addTariff(convertTariff(asnTariff));
             }
        }		
		
 		if (asnDocument.getStationCodeTable()!=null){
			document.setStationCodeTable(IStationCodeTable.valueOf(asnDocument.getStationCodeTable().name()));
		}	
		document.setFromStation(UicEncoderUtils.mapToString(asnDocument.getFromStationNum(),asnDocument.getFromStationIA5()));
		document.setToStation(UicEncoderUtils.mapToString(asnDocument.getToStationNum(),asnDocument.getToStationIA5()));
		document.setFromStationName(asnDocument.getFromStationNameUTF8());
		document.setToStationName(asnDocument.getToStationNameUTF8());	
		document.setValidRegionDesc(asnDocument.getValidRegionDesc());
		if (asnDocument.getValidRegion()!= null && !asnDocument.getValidRegion().isEmpty()) {
			for (RegionalValidityType validRegion  :asnDocument.getValidRegion()){
				document.addValidRegionList(convertValidRegion(validRegion, issuingDate));
			}
		}
		
		if (asnDocument.getIssuerAutorizationId() != null) {
			document.setAuthorizationCode(asnDocument.getIssuerAutorizationId().intValue());
		}
		
		if (asnDocument.getExtIssuerId() != null) {
			document.setExternalIssuer(asnDocument.getExtIssuerId().intValue());
		}
		
		document.setLuggageRestriction(convertLuggageRestriction(asnDocument.getLuggage()));
		
		document.setReturnDescription(convertReturnDescription(asnDocument.getReturnDescription(), issuingDate));
		if(asnDocument.getReturnIncluded()!=null){
			document.setReturnIncluded(asnDocument.getReturnIncluded());
		}
		
        if (asnDocument.getActivatedDay()!=null && !asnDocument.getActivatedDay().isEmpty()) {
        	document.getActivatedDays().addAll(asnDocument.getActivatedDays(issuingDate));     	                	
        }
        
        if (asnDocument.getIncludedAddOns()!= null && !asnDocument.getIncludedAddOns().isEmpty()) {
        	
        	for (IncludedOpenTicketType asnAddOn :asnDocument.getIncludedAddOns()) {
        		
        		IIncludedOpenTicket addOn = convertIncludedOpenTicket(asnAddOn, issuingDate, document.getClassCode());        		
        		if (addOn != null) {
        			document.addIncludedAddOn(addOn);
        		}
        	}
        	
        	
        	
        }
        
		document.setPrice(asnDocument.getPrice());
		
		if (asnDocument.getVatDetails() != null && !asnDocument.getVatDetails().isEmpty()){
			for (VatDetailType vat : asnDocument.getVatDetails()) {
				document.addVatDetail(decodeVatDetail(vat));
			}
		}
        
		return document;		

	}

	

	/**
	 * Convert included open ticket.
	 *
	 * @param asnDocument the asn document
	 * @param issuingDate the issuing date
	 * @param classCode the class code
	 * @return the simple included open ticket
	 */
	private IIncludedOpenTicket convertIncludedOpenTicket(	IncludedOpenTicketType asnDocument, Date issuingDate,	ITravelClassType classCode) {
		
		IIncludedOpenTicket document = factory.createIncludedOpenTicket();
		
		if(asnDocument.getClassCode()!=null){
			document.setClassCode(ITravelClassType.valueOf(asnDocument.getClassCode().name()));
		} else {
			document.setClassCode(classCode);
		}
		
		document.setValidFrom(asnDocument.getValidFromDate(issuingDate));
		document.setValidUntil(asnDocument.getValidUntilDate(issuingDate));
		
		document.setValidFromUTCoffset(asnDocument.getValidFromUTCOffset());
		if (asnDocument.getValidUntilUTCOffset() != null) {
			document.setValidUntilUTCoffset(asnDocument.getValidUntilUTCOffset());
		} else {
			document.setValidUntilUTCoffset(asnDocument.getValidFromUTCOffset());
		}
		

		document.setExtension(convertExtension(asnDocument.getExtension()));
		document.setInfoText(asnDocument.getInfoText());
		document.setProductId(UicEncoderUtils.mapToString(asnDocument.getProductIdNum(),asnDocument.getProductIdIA5()));
		document.setProductOwner(UicEncoderUtils.mapToString(asnDocument.getProductOwnerNum(),asnDocument.getProductOwnerIA5()));

         if (asnDocument.getExcludedServiceBrands()!=null && !asnDocument.getExcludedServiceBrands().isEmpty()){
             for(Long number :asnDocument.getExcludedServiceBrands()){
          	   document.addExcludedServiceBrand(number.intValue());
             }
          }
         if (asnDocument.getIncludedCarriersNum()!=null && !asnDocument.getIncludedCarriersNum().isEmpty()){
             for(Long carrier :asnDocument.getIncludedCarriersNum()){
          	   document.addIncludedCarrier(carrier.toString());
             }
         }
         if (asnDocument.getIncludedCarriersIA5()!=null && !asnDocument.getIncludedCarriersIA5().isEmpty()){
             for(String carrier :asnDocument.getIncludedCarriersIA5()){
          	   document.addIncludedCarrier(carrier);
             }
          }     
         
         if (asnDocument.getIncludedServiceBrands()!=null && !asnDocument.getIncludedServiceBrands().isEmpty()){
             for(Long number :asnDocument.getIncludedServiceBrands()){
          	   document.addIncludedServiceBrand(number.intValue());
             }
          }        
         
         if (asnDocument.getIncludedTransportTypes()!=null && !asnDocument.getIncludedTransportTypes().isEmpty()){
      	   for(Long number :asnDocument.getIncludedTransportTypes()){
           	   document.addInludedTransportType(number.intValue());
             }
         }  
         
         if (asnDocument.getExcludedTransportTypes()!=null && !asnDocument.getExcludedTransportTypes().isEmpty()){
      	   for(Long number :asnDocument.getExcludedTransportTypes()){
           	   document.addExcludedTransportType(number.intValue());
             }
         }           
         
         if (asnDocument.getTariffs()!=null && !asnDocument.getTariffs().isEmpty()){
             for(TariffType asnTariff :asnDocument.getTariffs()){
          	   document.addTariff(convertTariff(asnTariff));
             }
        }		
		
 		if (asnDocument.getStationCodeTable()!=null){
			document.setStationCodeTable(IStationCodeTable.valueOf(asnDocument.getStationCodeTable().name()));
		}	

		if (asnDocument.getValidRegion()!= null && !asnDocument.getValidRegion().isEmpty()) {
			for (RegionalValidityType validRegion  :asnDocument.getValidRegion()){
				document.addValidRegionList(convertValidRegion(validRegion, issuingDate));
			}
		}
		
		if (asnDocument.getIssuerAutorizationId()!=null){
			document.setAuthorizationCode(asnDocument.getIssuerAutorizationId().intValue());
		}
		if (asnDocument.getExternalIssuerId()!=null){
			document.setExternalIssuer(asnDocument.getExternalIssuerId().intValue());
		}
		return document;
	}







	/**
	 * Convert station passage.
	 *
	 * @param asnDocument the asn document
	 * @param issuingDate the issuing date
	 * @return the i station passage
	 */
	protected IStationPassage convertStationPassage(StationPassageData asnDocument , Date issuingDate) {
		
		IStationPassage document = factory.createStationPassage();
		
		document.setValidFrom(asnDocument.getValidFromDate(issuingDate));
		document.setValidUntil(asnDocument.getValidUntilDate(issuingDate));
		
		document.setValidFromUTCoffset(asnDocument.getValidFromUTCOffset());
		if (asnDocument.getValidUntilUTCOffset() != null) {
			document.setValidUntilUTCoffset(asnDocument.getValidUntilUTCOffset());
		} else {
			document.setValidUntilUTCoffset(asnDocument.getValidFromUTCOffset());
		}

		document.setReference(UicEncoderUtils.mapToString(asnDocument.getReferenceNum(),asnDocument.getReferenceIA5()));		

		if (asnDocument.getNumberOfDaysValid()!=null)  {
			document.setNumberOfdaysAllowed(asnDocument.getNumberOfDaysValid().intValue());						
		}
		
		if (asnDocument.getStationNum()!=null && !asnDocument.getStationNum().isEmpty()){
			for (Long station : asnDocument.getStationNum()) {
				if (station != null) {
					document.addStation(station.toString());
				}
			}			
		}
		if (asnDocument.getStationIA5()!=null && !asnDocument.getStationIA5().isEmpty()){
			for (String station : asnDocument.getStationIA5()) {
				document.addStation(station);
			}			
		}		
		
		document.setProductId(UicEncoderUtils.mapToString(asnDocument.getProductIdNum(),asnDocument.getProductIdIA5()));
		document.setProductOwner(UicEncoderUtils.mapToString(asnDocument.getProductOwnerNum(),asnDocument.getProductOwnerIA5()));
		
		document.setProductName(asnDocument.getProductName());		
		document.setProductOwner(UicEncoderUtils.mapToString(asnDocument.getProductOwnerNum(),asnDocument.getProductOwnerIA5()));
		
		if (asnDocument.getStationNameUTF8()!= null && asnDocument.getStationNameUTF8().isEmpty() ) {
			for (String name : asnDocument.getStationNameUTF8()) {
				document.addStation(name);
			}
		}
		
		if (asnDocument.getStationCodeTable()!=null){
			document.setStationCodeTable(IStationCodeTable.valueOf(asnDocument.getStationCodeTable().name()));
		}
		
		document.setExtension(convertExtension(asnDocument.getExtension()));
		
		if (asnDocument.getAreaCodeIA5() != null && !asnDocument.getAreaCodeIA5().isEmpty())  {
			for (String code : asnDocument.getAreaCodeIA5()) {
				document.addAreaCode(code);
			}			
		}
		if (asnDocument.getAreaCodeNum() != null && !asnDocument.getAreaCodeNum().isEmpty())  {
			for (Long code : asnDocument.getAreaCodeNum()) {
				if (code != null) {
					document.addAreaCode(code.toString());
				}
			}			
		}				
		
		if (asnDocument.getAreaNameUTF8() != null && !asnDocument.getAreaNameUTF8().isEmpty())  {
			for (String code : asnDocument.getAreaNameUTF8()) {
				document.addAreaName(code);
			}			
		}		
		
		
		
		return document;
	}
	
	
	/**
	 * Convert valid region.
	 *
	 * @param asnRegion the asn region
	 * @param issuingDate the issuing date
	 * @return the i regional validity
	 */
	protected IRegionalValidity convertValidRegion(RegionalValidityType asnRegion, Date issuingDate) {
		
		if (asnRegion == null) return null;
		
		if (asnRegion.getLines()!=null){
			return convertLine(asnRegion.getLines());
		}
		if (asnRegion.getPolygone()!=null){
			return convertPolygone(asnRegion.getPolygone());
		}
		if (asnRegion.getTrainLink()!=null){
			return convertTrainLink(asnRegion.getTrainLink(),issuingDate);
		}
		if (asnRegion.getViaStations()!=null){
			return convertViaStation(asnRegion.getViaStations());
		}
		if (asnRegion.getZones()!=null){
			return convertZone(asnRegion.getZones());
		}
		return null;
	}


	/**
	 * Convert parking ground.
	 *
	 * @param asnDocument the asn document
	 * @param issuingDate the issuing date
	 * @return the i parking ground
	 */
	protected IParkingGround convertParkingGround(ParkingGroundData asnDocument , Date issuingDate) {
		
		IParkingGround document = factory.createParkingGround();
		
		document.setReference(UicEncoderUtils.mapToString(asnDocument.getReferenceNum(),asnDocument.getReferenceIA5()));		
	
		document.setProductId(UicEncoderUtils.mapToString(asnDocument.getProductIdNum(),asnDocument.getProductIdIA5()));
		document.setProductOwner(UicEncoderUtils.mapToString(asnDocument.getProductOwnerNum(),asnDocument.getProductOwnerIA5()));	
		
		
		document.setAccessCode(asnDocument.getAccessCode());
		document.setLocation(asnDocument.getLocation());
		
		document.setExtension(convertExtension(asnDocument.getExtension()));
		
		document.setNumberPlate(asnDocument.getNumberPlate());
		document.setEntryTrack(asnDocument.getEntryTrack());
		
		
		document.setFromParkingDate(asnDocument.getFromParkingDate(issuingDate));
		document.setToParkingDate(asnDocument.getToParkingDate(issuingDate));		
		
		document.setParkingGroundId(asnDocument.getParkingGroundId());
		
		document.setSpecialInformation(asnDocument.getSpecialInformation());
				
 		if (asnDocument.getStationCodeTable()!=null){
			document.setStationCodeTable(IStationCodeTable.valueOf(asnDocument.getStationCodeTable().name()));
		}	
		
		document.setStation(UicEncoderUtils.mapToString(asnDocument.getStationNum(),asnDocument.getStationIA5()));		
		
		document.setPrice(asnDocument.getPrice());
		
		if (asnDocument.getVatDetails() != null && !asnDocument.getVatDetails().isEmpty()){
			for (VatDetailType vat : asnDocument.getVatDetails()) {
				document.addVatDetail(decodeVatDetail(vat));
			}
		}
		
		return document;
	}	
	
	/**
	 * Convert via station.
	 *
	 * @param asnDocument the asn via
	 * @return the i via station
	 */
	protected IViaStation convertViaStation(ViaStationType asnDocument) {
		
		if (asnDocument == null) return null;
		
		IViaStation via = factory.createViaStation();
		
		if (asnDocument.getBorder()!=null) {
			via.setBorder(asnDocument.getBorder());
		}
		
		if (asnDocument.getRouteId() != null) {
			via.setRouteId(asnDocument.getRouteId().intValue());
		}

 		if (asnDocument.getStationCodeTable()!=null){
			via.setStationCodeTable(IStationCodeTable.valueOf(asnDocument.getStationCodeTable().name()));
		}	
 		
 		if (asnDocument.getStationNum() != null) {
 			via.setStation(asnDocument.getStationNum().toString());	
 		} else if (asnDocument.getStationIA5() != null) {
 			via.setStation(asnDocument.getStationIA5());	
 		} 				 

        if (asnDocument.getCarriersNum()!=null && !asnDocument.getCarriersNum().isEmpty()){
            for(Long carrier :asnDocument.getCarriersNum()){
         	   via.addCarrier(carrier.toString());
            }
        }
        if (asnDocument.getCarriersIA5()!=null && !asnDocument.getCarriersIA5().isEmpty()){
            for(String carrier :asnDocument.getCarriersIA5()){
         	   via.addCarrier(carrier);
            }
         }   		

		if (asnDocument.getRoute()!= null && !asnDocument.getRoute().isEmpty()) {
			for ( ViaStationType routeVia: asnDocument.getRoute()) {
				via.addRouteStation(convertViaStation(routeVia));
			}
		}
		if (asnDocument.getAlternativeRoutes()!= null && !asnDocument.getAlternativeRoutes().isEmpty()) {
			for ( ViaStationType routeVia: asnDocument.getAlternativeRoutes()) {
				via.addRouteStation(convertViaStation(routeVia));
			}
		}


		return via;
	}	
	
	/**
	 * Convert train link.
	 *
	 * @param asnTrainLink the asn train link
	 * @param issuingDate the issuing date
	 * @return the i regional validity
	 */
	protected IRegionalValidity convertTrainLink(TrainLinkType asnTrainLink, Date issuingDate) {
		
		ITrainLink trainLink = factory.createTrainLink();
		
		trainLink.setTrain(UicEncoderUtils.mapToString(asnTrainLink.getTrainNum(), asnTrainLink.getTrainIA5()));
		
		trainLink.setFromStation(UicEncoderUtils.mapToString(asnTrainLink.getFromStationNum(),asnTrainLink.getFromStationIA5()));
		trainLink.setToStation(UicEncoderUtils.mapToString(asnTrainLink.getToStationNum(),asnTrainLink.getToStationIA5()));
		trainLink.setFromStationName(asnTrainLink.getFromStationName());
		trainLink.setToStationName(asnTrainLink.getToStationName());		
		
		trainLink.setDepartureDateTime(asnTrainLink.getDepartureDate(issuingDate));

		return trainLink;
	}	
	
	/**
	 * Convert zone.
	 *
	 * @param asnDocument the asn zone
	 * @return the i regional validity
	 */
	protected IRegionalValidity convertZone(ZoneType asnDocument) {
		
		if (asnDocument == null) return null;
		
		IZone zone = factory.createZone();

		zone.setBinaryZoneId(asnDocument.getBinaryZoneId());
		zone.setCarrier(UicEncoderUtils.mapToString(asnDocument.getCarrierNum(),asnDocument.getCarrierIA5()));
		
		if (asnDocument.getCity() != null) {
			zone.setCity(asnDocument.getCity().intValue());
		}
 		if (asnDocument.getStationCodeTable()!=null){
			zone.setStationCodeTable(IStationCodeTable.valueOf(asnDocument.getStationCodeTable().name()));
		}	
		zone.setEntryStation(UicEncoderUtils.mapToString(asnDocument.getEntryStationNum(),asnDocument.getEntryStationIA5()));
		zone.setTerminatingStation(UicEncoderUtils.mapToString(asnDocument.getTerminatingStationNum(),asnDocument.getTerminatingStationIA5()));
		
		zone.setNUTScode(asnDocument.getNutsCode());
		
		return zone;
	}	
	
	/**
	 * Convert polygone.
	 *
	 * @param asnPolygone the asn polygone
	 * @return the i regional validity
	 */
	protected IRegionalValidity convertPolygone(PolygoneType asnPolygone) {
		
		if (asnPolygone == null) return null;
		
		IPolygone polygone = factory.createPolygone();
		
		IGeoCoordinate firstEdge = convertGeoCoordinate(asnPolygone.getFirstEdge());
		
		if (firstEdge == null)	return null;
				
		if (asnPolygone.getEdges()!=null && !asnPolygone.getEdges().isEmpty()) {
			for (DeltaCoordinates asnEdge :asnPolygone.getEdges()){
				
				IGeoCoordinate edge = firstEdge.clone();
				try {
					edge.addLongitude(asnEdge.getLongitude().longValue());
					edge.addLatitude(asnEdge.getLatitude().longValue());
				} catch (Exception e) {
					
				}
				polygone.addEdge(edge);
			}
		}
		return polygone;
	}

	/**
	 * Convert line.
	 *
	 * @param asnDocument the asn line
	 * @return the i regional validity
	 */
	protected IRegionalValidity convertLine(LineType asnDocument) {
		
		if (asnDocument == null) return null;
		
		ILine line = factory.createLine();

		line.setBinaryZoneId(asnDocument.getBinaryZoneId());
		line.setCarrier(UicEncoderUtils.mapToString(asnDocument.getCarrierNum(),asnDocument.getCarrierIA5()));
		if (asnDocument.getCity()!=null) {
			line.setCity(asnDocument.getCity().intValue());
		}
 		if (asnDocument.getStationCodeTable()!=null){
			line.setStationCodeTable(IStationCodeTable.valueOf(asnDocument.getStationCodeTable().name()));
		}	
		line.setEntryStation(UicEncoderUtils.mapToString(asnDocument.getEntryStationNum(),asnDocument.getEntryStationIA5()));
		line.setTerminatingStation(UicEncoderUtils.mapToString(asnDocument.getTerminatingStationNum(),asnDocument.getTerminatingStationIA5()));
		if (asnDocument.getLineId()!=null && !asnDocument.getLineId().isEmpty()){
			for (Long lineId : asnDocument.getLineId()) {
				line.addLineId(lineId.intValue());
			}
		}
		return line;
	}

	/**
	 * Convert tariff.
	 *
	 * @param asnTariff the asn tariff
	 * @return the i tariff
	 */
	protected ITariff convertTariff(TariffType asnTariff) {
		
		if (asnTariff== null) return null;
		
		ITariff tariff = factory.createTariff();
		
		if (asnTariff.getAgeAbove() != null) {
			tariff.setAgeAbove(asnTariff.getAgeAbove().intValue());
		}
		
		if (asnTariff.getAgeBelow() != null) {
			tariff.setAgeBelow(asnTariff.getAgeBelow().intValue());
		}
		
		if(asnTariff.getNumberOfPassengers()!=null){
			tariff.setNumberOfPassengers(asnTariff.getNumberOfPassengers().intValue());
		}
		
		if (asnTariff.getSeriesDataDetails() != null) {			
			tariff.setSeriesDataDetails(convertSeriesDataDetails(asnTariff.getSeriesDataDetails()));			
		}
		

		if (asnTariff.getPassengerType()!=null) {
			tariff.setPassengerType(IPassengerType.valueOf(asnTariff.getPassengerType().name()));
		}
		

		if(asnTariff.getRestrictedToCountryOfResidence()!=null){
			tariff.setRestrictedToCountryOfResidence(asnTariff.getRestrictedToCountryOfResidence());
		}
		if (asnTariff.getRestrictedToRouteSection()!=null){
			tariff.setRestrictedToRouteSection(convertRouteSection(asnTariff.getRestrictedToRouteSection()));
		}
		

		tariff.setTariffDescription(asnTariff.getTariffDesc());
		tariff.setTariffId(UicEncoderUtils.mapToString(asnTariff.getTariffIdNum(),asnTariff.getTariffIdIA5()));
		
		if (asnTariff.getTraverlerid()!= null && !asnTariff.getTraverlerid().isEmpty() ) {
			for (Long number : asnTariff.getTraverlerid()){
				tariff.addTravelerId(number.intValue());
			}
		}
		
		if (asnTariff.getReductionCard()!=null && !asnTariff.getReductionCard().isEmpty()){
			for(CardReferenceType card : asnTariff.getReductionCard()){
				tariff.addReductionCard(convertCardReference(card));
			}
		}

		return tariff;
	}

	private ISeriesDataDetails convertSeriesDataDetails(SeriesDetailType asnDetails) {
		
		if (asnDetails == null) return null;
		
		ISeriesDataDetails details = factory.createSeriesDataDetails();
		
		if (asnDetails.getSeries() != null) {
			details.setSeries(asnDetails.getSeries().intValue());
		}
		
		if (asnDetails.getSupplyingCarrier() != null) {
			details.setSupplyingCarrier(asnDetails.getSupplyingCarrier().intValue());
		}
		
		if (asnDetails.getOfferIdentification() != null) {
			details.setOfferIdentification(asnDetails.getOfferIdentification().intValue());
		}

		return details;
	}







	/**
	 * Convert route section.
	 *
	 * @param asnDocument the asn document
	 * @return the i route section
	 */
	protected IRouteSection convertRouteSection(RouteSectionType asnDocument) {
		
		if (asnDocument == null) return null;
		
		IRouteSection document = factory.createRouteSection();
		if (asnDocument.getStationCodeTable()!=null){
			document.setStationCodeTable(IStationCodeTable.valueOf(asnDocument.getStationCodeTable().name()));
		}	
		document.setFromStation(UicEncoderUtils.mapToString(asnDocument.getFromStationNum(),asnDocument.getFromStationIA5()));
		document.setToStation(UicEncoderUtils.mapToString(asnDocument.getToStationNum(),asnDocument.getToStationIA5()));
		document.setFromStationName(asnDocument.getFromStationNameUTF8());
		document.setToStationName(asnDocument.getToStationNameUTF8());		
		return document;
	}	
	
	/**
	 * Convert fip ticket.
	 *
	 * @param asnDocument the asn document
	 * @param issuingDate the issuing date
	 * @return the i fip ticket
	 */
	protected IFipTicket convertFipTicket(FIPTicketData asnDocument , Date issuingDate) {
		
		if (asnDocument == null) return null;
		
		IFipTicket document = factory.createFipTicket();
		if(asnDocument.getClassCode()!=null){
			document.setClassCode(ITravelClassType.valueOf(asnDocument.getClassCode().name()));
		}
		
		document.setValidFrom(asnDocument.getValidFromDate(issuingDate));
		document.setValidUntil(asnDocument.getValidUntilDate(issuingDate));
		
		
	
		document.setProductId(UicEncoderUtils.mapToString(asnDocument.getProductIdNum(),asnDocument.getProductIdIA5()));
		document.setProductOwner(UicEncoderUtils.mapToString(asnDocument.getProductOwnerNum(),asnDocument.getProductOwnerIA5()));
		document.setReference(UicEncoderUtils.mapToString(asnDocument.getReferenceNum(),asnDocument.getReferenceIA5()));	
		
		if (asnDocument.getActivatedDay() != null && !asnDocument.getActivatedDay().isEmpty()) {
			document.getActivatedDays().addAll(asnDocument.getActivatedDays(issuingDate));
		}
		
		if(asnDocument.getIncludesSupplements()!=null) {
			document.setIncludesSupplements(asnDocument.getIncludesSupplements());
		}
		if(asnDocument.getNumberOfTravelDays()!=null){
			document.setNumberOfTravelDates(asnDocument.getNumberOfTravelDays().intValue());
		}
		
		document.setExtension(convertExtension(asnDocument.getExtension()));		
		
		return document;		
	}
	
	/**
	 * Convert pass.
	 *
	 * @param asnDocument the asn document
	 * @param issuingDate the issuing date
	 * @return the i pass
	 */
	protected IPass convertPass(PassData asnDocument, Date issuingDate) {
		
		if (asnDocument == null) return null;
				
		IPass document = factory.createPass();
		
		if(asnDocument.getClassCode()!=null){
			document.setClassCode(ITravelClassType.valueOf(asnDocument.getClassCode().name()));
		}
		
		document.setValidFrom(asnDocument.getValidFromDate(issuingDate));
		document.setValidFromUTCoffset(asnDocument.getValidFromUTCOffset());
		
		document.setValidUntil(asnDocument.getValidUntilDate(issuingDate));
		document.setValidUntilUTCoffset(asnDocument.getValidUntilUTCOffset());	
				
		if (asnDocument.getActivatedDay() != null && !asnDocument.getActivatedDay().isEmpty()) {
			document.getActivatedDays().addAll(asnDocument.getActivatedDays(issuingDate));
		}
		
		document.setExtension(convertExtension(asnDocument.getExtension()));
		document.setInfoText(asnDocument.getInfoText());
		document.setProductId(UicEncoderUtils.mapToString(asnDocument.getProductIdNum(),asnDocument.getProductIdIA5()));
		document.setProductOwner(UicEncoderUtils.mapToString(asnDocument.getProductOwnerNum(),asnDocument.getProductOwnerIA5()));
		document.setReference(UicEncoderUtils.mapToString(asnDocument.getReferenceNum(),asnDocument.getReferenceIA5()));		

		if (asnDocument.getNumberOfDaysOfTravel() != null) {
			document.setNumberOfDaysOfTravel(asnDocument.getNumberOfDaysOfTravel().intValue());
		}
		if (asnDocument.getNumberOfPossibleTrips() != null) {
			document.setNumberOfPossibleTrips(asnDocument.getNumberOfPossibleTrips().intValue());
		}
		
        document.setPassDescription(asnDocument.getPassDescription());
        if(asnDocument.getPassType()!=null){
        	document.setPassType(asnDocument.getPassType().intValue());
        }
        
        

        if (asnDocument.getCountries()!=null && !asnDocument.getCountries().isEmpty()){
           for(Long number :asnDocument.getCountries()){
        	   document.addCountry(number.intValue());
           }
        }
        if (asnDocument.getExcludedServiceBrands()!=null && !asnDocument.getExcludedServiceBrands().isEmpty()){
            for(Long number :asnDocument.getExcludedServiceBrands()){
         	   document.addExcludedServiceBrand(number.intValue());
            }
         }

        if (asnDocument.getIncludedCarriersNum()!=null && !asnDocument.getIncludedCarriersNum().isEmpty()){
            for(Long carrier :asnDocument.getIncludedCarriersNum()){
         	   document.addIncludedCarrier(carrier.toString());
            }
        }
        if (asnDocument.getIncludedCarriersIA5()!=null && !asnDocument.getIncludedCarriersIA5().isEmpty()){
            for(String carrier :asnDocument.getIncludedCarriersIA5()){
         	   document.addIncludedCarrier(carrier);
            }
         }   

        if (asnDocument.getExcludedCarriersNum()!=null && !asnDocument.getExcludedCarriersNum().isEmpty()){
            for(Long carrier :asnDocument.getExcludedCarriersNum()){
         	   document.addExcludedCarrier(carrier.toString());
            }
        }
        if (asnDocument.getExcludedCarriersIA5()!=null && !asnDocument.getExcludedCarriersIA5().isEmpty()){
            for(String carrier :asnDocument.getExcludedCarriersIA5()){
         	   document.addExcludedCarrier(carrier);
            }
         }         
        
        
        if (asnDocument.getIncludedServiceBrands()!=null && !asnDocument.getIncludedServiceBrands().isEmpty()){
            for(Long number :asnDocument.getIncludedServiceBrands()){
         	   document.addIncludedServiceBrand(number.intValue());
            }
         }        
        
        if (asnDocument.getTariffs()!=null && !asnDocument.getTariffs().isEmpty()){
            for(TariffType asnTariff :asnDocument.getTariffs()){
         	   document.addTariff(convertTariff(asnTariff));
            }
         }        

        if (asnDocument.getValidRegion()!=null && !asnDocument.getValidRegion().isEmpty()){
            for(RegionalValidityType asnRegion :asnDocument.getValidRegion()){
         	   document.addValidRegion(convertValidRegion(asnRegion, issuingDate));
            }
         }    
        
        
        if (asnDocument.getValidityPeriodDetails() != null) {
        	document.setValidityDetails(convertValidityDetails(asnDocument.getValidityPeriodDetails(), issuingDate));
        }
        
		document.setPrice(asnDocument.getPrice());
		
		if (asnDocument.getVatDetails() != null && !asnDocument.getVatDetails().isEmpty()){
			for (VatDetailType vat : asnDocument.getVatDetails()) {
				document.addVatDetail(decodeVatDetail(vat));
			}
		}
        

		return document;
	}	
	
	private IValidityDetails convertValidityDetails(ValidityPeriodDetailType asnDetails, Date issuingDate) {
		
		if (asnDetails == null) return null;
		
		IValidityDetails details = factory.createValidityDetails();
		
		if (asnDetails.getExcludedTimeRange()!= null && !asnDetails.getExcludedTimeRange().isEmpty()) {

			ITimeRange range = factory.createTimeRange();
			
			for (TimeRangeType asnRange : asnDetails.getExcludedTimeRange() ) {
				
				if (asnRange.getFromTime() != null) { 
					range.setFromTime(asnRange.getFromTime().intValue());
				}
				if (asnRange.getUntilTime() != null) {
					range.setUntilTime(asnRange.getUntilTime().intValue());
				}
		
				details.addTimeRanges(range);
			}
			
		
			
		}
		
		if (asnDetails.getValidityPeriod()!= null && !asnDetails.getValidityPeriod().isEmpty()) {

			IValidityRange range = factory.createValidityRange();
			
			for (ValidityPeriodType asnRange : asnDetails.getValidityPeriod() ) {
				
				range.setFromDate(asnRange.getValidFromDate(issuingDate));
				range.setUntilDate(asnRange.getValidUntilDate(issuingDate));
				
				range.setValidFromUTCoffset(asnRange.getValidFromUTCOffset());
				if (asnRange.getValidUntilUTCOffset() != null) {
					range.setValidUntilUTCoffset(asnRange.getValidUntilUTCOffset());
				} else {
					range.setValidUntilUTCoffset(asnRange.getValidFromUTCOffset());
				}
								
				details.addValidityRanges(range);
			}
			
		}
		
		return details;
	}







	/**
	 * Convert voucher.
	 *
	 * @param asnDocument the asn document
	 * @param issuingDate the issuing date
	 * @return the i voucher
	 */
	protected IVoucher convertVoucher(VoucherData asnDocument , Date issuingDate) {
		
		if (asnDocument == null) return null;
		
		IVoucher document = factory.createVoucher();
		
		document.setValidFrom(asnDocument.getValidFrom());
		
		document.setValidUntil(asnDocument.getValidUntil());
		
		document.setInfoText(asnDocument.getInfoText());
		document.setProductId(UicEncoderUtils.mapToString(asnDocument.getProductIdNum(),asnDocument.getProductIdIA5()));
		document.setProductOwner(UicEncoderUtils.mapToString(asnDocument.getProductOwnerNum(),asnDocument.getProductOwnerIA5()));
		document.setReference(UicEncoderUtils.mapToString(asnDocument.getReferenceNum(),asnDocument.getReferenceIA5()));		
		
		if (asnDocument.getValue()!=null) {
			document.setAmount(asnDocument.getValue().intValue());
		}
		
		if (asnDocument.getType()!= null) {
			document.setType(asnDocument.getType().intValue());
		}
		
		document.setExtension(convertExtension(asnDocument.getExtension()));		
		
		return document;
	}	
	
	/**
	 * Convert customer card.
	 *
	 * @param asnDocument the asn card
	 * @param issuingDate the issuing date
	 * @return the i customer card
	 */
	protected ICustomerCard convertCustomerCard(CustomerCardData asnDocument, Date issuingDate ) {
		
		if (asnDocument == null) return null;
		
		ICustomerCard card = factory.createCustomerCard();

		card.setCardId(UicEncoderUtils.mapToString(asnDocument.getCardIdNum(), asnDocument.getCardIdIA5()));
		if (asnDocument.getCardType()!= null) {
			card.setCardType(asnDocument.getCardType().intValue());
		}
		card.setCardTypeDescr(asnDocument.getCardTypeDescr());
		if(asnDocument.getClassCode()!=null){
			card.setClassCode(ITravelClassType.valueOf(asnDocument.getClassCode().name()));
		}
		if(asnDocument.getCustomer()!=null) {
			card.setCustomer(convertTraveler(asnDocument.getCustomer()));
		}
		if (asnDocument.getCustomerStatus()!=null) {
			card.setCustomerStatus(asnDocument.getCustomerStatus().intValue());
		}
		
		card.setCustomerStatusDescr(asnDocument.getCustomerStatusDescr());
		
		card.setValidFrom(asnDocument.getValidFromDate());
		
		card.setValidUntil(asnDocument.getValidUntilDate());
		
		
		if (asnDocument.getIncludedServices() != null && !asnDocument.getIncludedServices().isEmpty()){
			for (Long service: asnDocument.getIncludedServices()){
				card.addIncludedService(new Integer(service.intValue()));	
			}
		}
		return card;
	}

	
	/**
	 * Convert document extension.
	 *
	 * @param extension the extension
	 * @return the i document extension
	 */
	protected IDocumentExtension convertDocumentExtension(ExtensionData asnExtension) {
		
		if (asnExtension == null) return null;
		
		IDocumentExtension documentExtension = factory.createDocumentExtension();
		documentExtension.setId(asnExtension.getExtensionId());
		documentExtension.setBinarydata(asnExtension.getExtensionData());
		return documentExtension;
	}


	/**
	 * Populate traveler details.
	 *
	 * @param asnTravelerDetails the asn traveler details
	 * @param travelerDetails the traveler details
	 */
	protected void populateTravelerDetails(TravelerData asnTravelerDetails,ITravelerDetail travelerDetails) {
		
		if (asnTravelerDetails == null) return;
		
		travelerDetails.setGroupName(asnTravelerDetails.getGroupName());
		
		travelerDetails.setPreferredLanguage(asnTravelerDetails.getPreferedLanguage());
		
		if (asnTravelerDetails.getTraveler() != null && !asnTravelerDetails.getTraveler().isEmpty() ) {
			for ( TravelerType asnTraveler  : asnTravelerDetails.getTraveler()  ){
				travelerDetails.addTraveler(convertTraveler(asnTraveler));
			}
		}
	}

	/**
	 * Convert traveler.
	 *
	 * @param asnTraveler the asn traveler
	 * @return the i traveler
	 */
	protected ITraveler convertTraveler(TravelerType asnTraveler) {
		
		if (asnTraveler == null) return null;
		
		ITraveler traveler = factory.createTraveler();
		
		if (asnTraveler.getCountryOfResidence() != null){
			traveler.setCountryOfResidence(asnTraveler.getCountryOfResidence().intValue());
		}
		if (asnTraveler.getCountryOfPassport() != null) {
			traveler.setPassportCountry(asnTraveler.getCountryOfPassport().intValue());
		}
		
		if (asnTraveler.getCountryOfIdCard() != null) {
			traveler.setIDCardCountry(asnTraveler.getCountryOfIdCard().intValue());
		}
				
		
		traveler.setCustomerId(UicEncoderUtils.mapToString(asnTraveler.getCustomerIdNum(),asnTraveler.getCustomerIdIA5()));
		
		traveler.setDateOfBirth(asnTraveler.getDateOfBirth());
		
		traveler.setFirstName(asnTraveler.getFirstName());
		if (asnTraveler.getGender()!= null) {
			traveler.setGender(IGenderType.valueOf(asnTraveler.getGender().name()));
		}
		traveler.setIdCard(asnTraveler.getIdCard());
		traveler.setLastName(asnTraveler.getLastName());
		
		if (asnTraveler.getPassengerType()!= null) {
			traveler.setPassengerType(IPassengerType.valueOf(asnTraveler.getPassengerType().name()));
		}
		if (asnTraveler.getPassengerWithReducedMobility()!= null) {
			traveler.setPassengerWithReducedMobility(asnTraveler.getPassengerWithReducedMobility());
		}
		
		traveler.setPassportId(asnTraveler.getPassportId());
		traveler.setSecondName(asnTraveler.getSecondName());
		
		if (asnTraveler.getTicketHolder() != null) {
			traveler.setTicketHolder(asnTraveler.getTicketHolder());
		}
		
		if (asnTraveler.getStatus()!= null && !asnTraveler.getStatus().isEmpty()) {
			
			for (CustomerStatusType asnStatus : asnTraveler.getStatus()){
				
				traveler.addStatusDescription( mapToStatusDescription(asnStatus) );
				
			}
			
		}
		
		traveler.setTitle(asnTraveler.getTitle());
		
		return traveler;
	}

	/**
	 * Map to status description.
	 *
	 * @param asnStatus the asn status
	 * @return the i customer status description
	 */
	private ICustomerStatusDescription mapToStatusDescription(	CustomerStatusType asnStatus) {
		
		if (asnStatus == null) return null;
		
		ICustomerStatusDescription status = factory.createCustomerStatusDescription();
		
		status.setDescription(asnStatus.getCustomerStatusDescr());
		
		if (asnStatus.getCustomerStatus()!=null) {
			status.setStatus(asnStatus.getCustomerStatus().intValue());
		}
		
		status.setStatusProvider(UicEncoderUtils.mapToString(asnStatus.getStatusProviderNum(), asnStatus.getStatusProviderIA5()));
	
		return status;
	}







	/**
	 * Populate control details.
	 *
	 * @param asnControlDetails the asn control details
	 * @param controlDetails the control details
	 */
	protected void populateControlDetails(ControlData asnControlDetails,	IControlDetail controlDetails) {
		
		if (asnControlDetails == null || controlDetails==null ) return;
		
		if (asnControlDetails.getAgeCheckRequired()!= null) {
			controlDetails.setAgeCheckRequired(asnControlDetails.getAgeCheckRequired());
		}
		
		controlDetails.setExtension(convertExtension(asnControlDetails.getExtension()));
		
		if (asnControlDetails.getIdentificationByIdCard()!=null){
			controlDetails.setIdentificationByIdCard(asnControlDetails.getIdentificationByIdCard());
		}
		
		if (asnControlDetails.getIdentificationByPassportId()!= null){
			controlDetails.setIdentificationByPassportId(asnControlDetails.getIdentificationByPassportId());
		}
		
		if(asnControlDetails.getIdentificationItem()!=null){
			controlDetails.setIdentificationItem(asnControlDetails.getIdentificationItem().intValue());
		}
		
		controlDetails.setInfoText(asnControlDetails.getInfoText());
		
		if (asnControlDetails.getOnlineValidationRequired()!=null){
			controlDetails.setOnlineValidationRequired(asnControlDetails.getOnlineValidationRequired());
		}

		if (asnControlDetails.getRandomDetailedValidationRequired()!= null){
			controlDetails.setRandomDetailedValidationRequired(asnControlDetails.getRandomDetailedValidationRequired().intValue());
		}
		
		if (asnControlDetails.getReductionCardCheckRequired() != null){
			controlDetails.setReductionCardCheckRequired(asnControlDetails.getReductionCardCheckRequired());
		}	
		
		if(asnControlDetails.getIdentificationByCardReference()!=null && !asnControlDetails.getIdentificationByCardReference().isEmpty()) {
			for (CardReferenceType asnCardReference : asnControlDetails.getIdentificationByCardReference()){
				controlDetails.addIdentificationByCardReference(convertCardReference(asnCardReference));
			}
		}
		
		if (asnControlDetails.getIncludedTickets()!=null && !asnControlDetails.getIncludedTickets().isEmpty()) {
			for (TicketLinkType asnTicketLink : asnControlDetails.getIncludedTickets()) {
				controlDetails.addLinkedTicket(convertTicketLink(asnTicketLink));
			}
		}

		
	}





	/**
	 * Populate issuing detail.
	 *
	 * @param asnIssuingDetail the asn issuing detail
	 * @param issuingDetail the issuing detail
	 */
	protected void populateIssuingDetail(IssuingData asnIssuingDetail,IIssuingDetail issuingDetail){
		
		if (asnIssuingDetail== null || issuingDetail == null) return;
		
		if (asnIssuingDetail.getActivated() != null) {
			issuingDetail.setActivated(asnIssuingDetail.getActivated());
		}
		
		if (asnIssuingDetail.getSpecimen() != null) {
			issuingDetail.setSpecimen(asnIssuingDetail.getSpecimen());
		} else {
			issuingDetail.setSpecimen(false);
		}

		
		if (asnIssuingDetail.getSecurePaperTicket() != null) {
			issuingDetail.setSecurePaperTicket(asnIssuingDetail.getSecurePaperTicket());
		} else {
			issuingDetail.setSecurePaperTicket(false);
		}
		
		issuingDetail.setExtension(convertExtension(asnIssuingDetail.getExtension()));
		
		if (asnIssuingDetail.getIssuedOnLine()!= null) {
			issuingDetail.setIssuedOnLine(asnIssuingDetail.getIssuedOnLine().intValue());
		}
		
		if (asnIssuingDetail.getIssuedOnTrainIA5()!= null) {
			issuingDetail.setIssuedOnTrain(asnIssuingDetail.getIssuedOnTrainIA5());
		}			
		if (asnIssuingDetail.getIssuedOnTrainNum()!= null) {
			issuingDetail.setIssuedOnTrain(asnIssuingDetail.getIssuedOnTrainNum().toString());
		}		
		
		if (asnIssuingDetail.getIssuerNum() != null || asnIssuingDetail.getIssuerIA5() != null) {
			issuingDetail.setIssuer(UicEncoderUtils.mapToString(asnIssuingDetail.getIssuerNum(), asnIssuingDetail.getIssuerIA5()));
		}
		
		issuingDetail.setIssuerName(asnIssuingDetail.getIssuerName());
		
		issuingDetail.setIssuerPNR(asnIssuingDetail.getIssuerPNR());
		
		issuingDetail.setSecurityProvider(UicEncoderUtils.mapToString(asnIssuingDetail.getSecurityProviderNum(),asnIssuingDetail.getSecurityProviderIA5()));
		
		
		issuingDetail.setIssuingDate(asnIssuingDetail.getIssuingDate());
		
	
		if (asnIssuingDetail.getPointOfSale() != null) {
			issuingDetail.setPointOfSale(convertGeoCoordinate(asnIssuingDetail.getPointOfSale()));
		}
		
	}
	
	
	
	/**
	 * Convert geo coordinate.
	 *
	 * @param asnCoordinate the asn coordinate
	 * @return the i geo coordinate
	 */
	protected IGeoCoordinate convertGeoCoordinate(GeoCoordinateType asnCoordinate) {
		
		if (asnCoordinate == null) return null;
		
		IGeoCoordinate coordinate = factory.createGeoCoordinate();
		
		if (asnCoordinate.getCoordinateSystem() != null) {
			coordinate.setSystem(IGeoCoordinateSystemType.valueOf(asnCoordinate.getCoordinateSystem().name()));
		}
		
		if (asnCoordinate.getAccuracy() != null) {
			coordinate.setAccuracy(IGeoUnitType.valueOf(asnCoordinate.getAccuracy().name()));
		}		
		
		if (asnCoordinate.getGeoUnit() != null) {
			coordinate.setUnit(IGeoUnitType.valueOf(asnCoordinate.getGeoUnit().name()));
		}
		
		if (asnCoordinate.getHemisphereLatitude() != null) {
			coordinate.setHemisphereLatitude(IHemisphereLatitudeType.valueOf(asnCoordinate.getHemisphereLatitude().name()));
		}

		if (asnCoordinate.getHemisphereLongitude() != null) {
			coordinate.setHemisphereLongitude(IHemisphereLongitudeType.valueOf(asnCoordinate.getHemisphereLongitude().name()));
		}		
		
		coordinate.setLongitude (asnCoordinate.getLongitude());
		coordinate.setLatitude (asnCoordinate.getLatitude());
		
		
		return coordinate;
	}


	/**
	 * Convert extension.
	 *
	 * @param asnExtension the asn extension
	 * @return the i extension
	 */
	protected IExtension convertExtension(ExtensionData asnExtension) {					
		if (asnExtension == null) return null;
		IExtension extension = factory.createExtension();
		extension.setId(asnExtension.getExtensionId());
		extension.setBinarydata(asnExtension.getExtensionData());
		return extension;
	}
	
	
	/**
	 * Convert ticket link.
	 *
	 * @param asnTicketLink the asn ticket link
	 * @return the i ticket link
	 */
	protected ITicketLink convertTicketLink(TicketLinkType asnTicketLink) {
		
		if (asnTicketLink == null) return null;
		
		ITicketLink ticketLink = factory.createTicketLink();
		ticketLink.setIssuer(asnTicketLink.getIssuerName());
		ticketLink.setIssuerPNR(asnTicketLink.getIssuerPNR());		
		ticketLink.setProductOwner(UicEncoderUtils.mapToString(asnTicketLink.getProductOwnerNum(),asnTicketLink.getProductOwnerIA5()));
		ticketLink.setReference(UicEncoderUtils.mapToString(asnTicketLink.getReferenceNum(),asnTicketLink.getReferenceIA5()));
		if(asnTicketLink.getTicketType()!=null) {
			ticketLink.setTicketType(ITicketType.valueOf(asnTicketLink.getTicketType().name()));
		}
		if(asnTicketLink.getLinkMode()!=null) {
			ticketLink.setLinkMode(ILinkMode.valueOf(asnTicketLink.getLinkMode().name()));
		}	
		return ticketLink;
	}
	
	/**
	 * Convert card reference.
	 *
	 * @param asnCardReference the asn card reference
	 * @return the card reference
	 */
	protected ICardReference convertCardReference(CardReferenceType asnCardReference) {
		
		if (asnCardReference == null) return null;
		
		ICardReference cardReference = factory.createCardReference();
		cardReference.setCardId(UicEncoderUtils.mapToString(asnCardReference.getCardIdNum(),asnCardReference.getCardIdIA5()));
		cardReference.setCardIssuer(UicEncoderUtils.mapToString(asnCardReference.getCardIssuerNum(),asnCardReference.getCardIssuerIA5()));
		cardReference.setCardName(asnCardReference.getCardName());
		
		if(asnCardReference.getCardType()!=null) {
			cardReference.setCardType(asnCardReference.getCardType().intValue());
		}
		
		
		cardReference.setLeadingCardId(UicEncoderUtils.mapToString(asnCardReference.getLeadingCardIdNum(),asnCardReference.getLeadingCardIdIA5()));
		cardReference.setTrailingCardId(UicEncoderUtils.mapToString(asnCardReference.getTrailingCardIdNum(),asnCardReference.getTrailingCardIdIA5()));
		
		return cardReference;
	}	

}
