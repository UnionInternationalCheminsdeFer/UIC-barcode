/*
 * 
 */
package org.uic.barcode.ticket.api.utils;

import java.util.Collection;
import java.util.Date;

import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringIA5;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringUTF8;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfUnrestrictedLong;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.asn.omv1.BerthDetailData;
import org.uic.barcode.ticket.api.asn.omv1.BerthTypeType;
import org.uic.barcode.ticket.api.asn.omv1.CarCarriageReservationData;
import org.uic.barcode.ticket.api.asn.omv1.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv1.CodeTableType;
import org.uic.barcode.ticket.api.asn.omv1.CompartmentDetailsType;
import org.uic.barcode.ticket.api.asn.omv1.CompartmentGenderType;
import org.uic.barcode.ticket.api.asn.omv1.ConfirmationTypeType;
import org.uic.barcode.ticket.api.asn.omv1.ControlData;
import org.uic.barcode.ticket.api.asn.omv1.CountermarkData;
import org.uic.barcode.ticket.api.asn.omv1.CustomerCardData;
import org.uic.barcode.ticket.api.asn.omv1.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv1.DelayConfirmation;
import org.uic.barcode.ticket.api.asn.omv1.DeltaCoordinates;
import org.uic.barcode.ticket.api.asn.omv1.DocumentData;
import org.uic.barcode.ticket.api.asn.omv1.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv1.FIPTicketData;
import org.uic.barcode.ticket.api.asn.omv1.GenderType;
import org.uic.barcode.ticket.api.asn.omv1.GeoCoordinateSystemType;
import org.uic.barcode.ticket.api.asn.omv1.GeoCoordinateType;
import org.uic.barcode.ticket.api.asn.omv1.GeoUnitType;
import org.uic.barcode.ticket.api.asn.omv1.HemisphereLatitudeType;
import org.uic.barcode.ticket.api.asn.omv1.HemisphereLongitudeType;
import org.uic.barcode.ticket.api.asn.omv1.IncludedOpenTicketType;
import org.uic.barcode.ticket.api.asn.omv1.IssuingData;
import org.uic.barcode.ticket.api.asn.omv1.LineType;
import org.uic.barcode.ticket.api.asn.omv1.LinkMode;
import org.uic.barcode.ticket.api.asn.omv1.LoadingDeckType;
import org.uic.barcode.ticket.api.asn.omv1.LuggageRestrictionType;
import org.uic.barcode.ticket.api.asn.omv1.OpenTicketData;
import org.uic.barcode.ticket.api.asn.omv1.ParkingGroundData;
import org.uic.barcode.ticket.api.asn.omv1.PassData;
import org.uic.barcode.ticket.api.asn.omv1.PassengerType;
import org.uic.barcode.ticket.api.asn.omv1.PlacesType;
import org.uic.barcode.ticket.api.asn.omv1.PolygoneType;
import org.uic.barcode.ticket.api.asn.omv1.PriceTypeType;
import org.uic.barcode.ticket.api.asn.omv1.RegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv1.RegisteredLuggageType;
import org.uic.barcode.ticket.api.asn.omv1.ReservationData;
import org.uic.barcode.ticket.api.asn.omv1.ReturnRouteDescriptionType;
import org.uic.barcode.ticket.api.asn.omv1.RoofRackType;
import org.uic.barcode.ticket.api.asn.omv1.RouteSectionType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfBerthDetailData;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfCarrierNum;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfCountries;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfDeltaCoordinates;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfIncludedOpenTicketType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfPlaceNum;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfRegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfRegisteredLuggageType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfServiceBrands;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfTariffType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfTicketLinkType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfTravelerId;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv1.SequenceOfViaStationType;
import org.uic.barcode.ticket.api.asn.omv1.SeriesDetailType;
import org.uic.barcode.ticket.api.asn.omv1.ServiceType;
import org.uic.barcode.ticket.api.asn.omv1.StationPassageData;
import org.uic.barcode.ticket.api.asn.omv1.TariffType;
import org.uic.barcode.ticket.api.asn.omv1.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv1.TicketLinkType;
import org.uic.barcode.ticket.api.asn.omv1.TicketType;
import org.uic.barcode.ticket.api.asn.omv1.TimeRangeType;
import org.uic.barcode.ticket.api.asn.omv1.TokenType;
import org.uic.barcode.ticket.api.asn.omv1.TrainLinkType;
import org.uic.barcode.ticket.api.asn.omv1.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv1.TravelerData;
import org.uic.barcode.ticket.api.asn.omv1.TravelerType;
import org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData;
import org.uic.barcode.ticket.api.asn.omv1.ValidityPeriodDetailType;
import org.uic.barcode.ticket.api.asn.omv1.ValidityPeriodType;
import org.uic.barcode.ticket.api.asn.omv1.VatDetailType;
import org.uic.barcode.ticket.api.asn.omv1.ViaStationType;
import org.uic.barcode.ticket.api.asn.omv1.VoucherData;
import org.uic.barcode.ticket.api.asn.omv1.ZoneType;
import org.uic.barcode.ticket.api.spec.IBerth;
import org.uic.barcode.ticket.api.spec.ICarCarriageReservation;
import org.uic.barcode.ticket.api.spec.ICardReference;
import org.uic.barcode.ticket.api.spec.ICompartmentDetails;
import org.uic.barcode.ticket.api.spec.ICompartmentGenderType;
import org.uic.barcode.ticket.api.spec.IControlDetail;
import org.uic.barcode.ticket.api.spec.ICounterMark;
import org.uic.barcode.ticket.api.spec.ICustomerCard;
import org.uic.barcode.ticket.api.spec.ICustomerStatusDescription;
import org.uic.barcode.ticket.api.spec.IDelayConfirmation;
import org.uic.barcode.ticket.api.spec.IDocumentData;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IFipTicket;
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
import org.uic.barcode.ticket.api.spec.IServiceType;
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
import org.uic.barcode.ticket.api.spec.IValidityDetails;
import org.uic.barcode.ticket.api.spec.IValidityRange;
import org.uic.barcode.ticket.api.spec.IVatDetail;
import org.uic.barcode.ticket.api.spec.IViaStation;
import org.uic.barcode.ticket.api.spec.IVoucher;
import org.uic.barcode.ticket.api.spec.IZone;

/**
 * The Class Api2OpenAsnEncoder.
 */
public class Api2OpenAsnEncoder {
	
	
	/**
	 * encode to asn1 model.
	 *
	 * @param uicTicket the uic ticket
	 * @return the encoded byte array 
	 * @throws EncodingFormatException the encoding format exception
	 */
	public byte[] encode(IUicRailTicket uicTicket) throws EncodingFormatException {

		UicRailTicketData asnUicRailTicketData = populateToAsn1Model(uicTicket);
		
		return asnUicRailTicketData.encode();
		
	}
		
		
	
	/**
	 * Populate asn1 model.
	 *
	 * @param uicTicket the uic ticket
	 * @return the org.uic.ticket.asn.om. uic rail ticket data
	 * @throws EncodingFormatException the encoding format exception
	 */
	public UicRailTicketData populateToAsn1Model(IUicRailTicket uicTicket) throws EncodingFormatException {
		
		UicRailTicketData asnTicket = new UicRailTicketData();

		if (uicTicket.getIssuerDetails() != null) {
			asnTicket.setIssuingDetail(encodeIssuingDetails(uicTicket.getIssuerDetails()));
		}
		
		
		if (uicTicket.getControlDetails() != null) {			
			asnTicket.setControlDetail(encodeControlDetails(uicTicket.getControlDetails(), uicTicket.getIssuerDetails().getIssuingDate()));			
		}
		
		
		if (uicTicket.getExtensions() != null && !uicTicket.getExtensions().isEmpty()){
			
			SequenceOfExtensionData asnList = new SequenceOfExtensionData();
			for (IExtension extension : uicTicket.getExtensions()){
				ExtensionData asnExtension = encodeExtension(extension);
				if (asnExtension!= null) {
					asnList.add(asnExtension);
				}
			}
			if (!asnList.isEmpty()){
				asnTicket.setExtension(asnList);
			}
			
			
		}
		
		
		if (uicTicket.getTravelerDetails() != null) {
			asnTicket.setTravelerDetail(encodeTravelers(uicTicket.getTravelerDetails(),uicTicket.getIssuerDetails().getIssuingDate() ));
		}
		
		if (uicTicket.getDocumentData() != null && !uicTicket.getDocumentData().isEmpty()) {
			
			SequenceOfDocumentData documents = new SequenceOfDocumentData();
			
			for (IDocumentData document : uicTicket.getDocumentData()){
				
				DocumentData asnDocument = null;

				if (document instanceof IReservation) {
					asnDocument = convertReservation((IReservation) document , uicTicket.getIssuerDetails().getIssuingDate());
				}	
				
				if (document instanceof IOpenTicket) {
					asnDocument = convertOpenTicket((IOpenTicket) document , uicTicket.getIssuerDetails().getIssuingDate());
				}		
				
				if (document instanceof ICarCarriageReservation) {
					asnDocument = convertCarCarriageReservation((ICarCarriageReservation) document , uicTicket.getIssuerDetails().getIssuingDate());
				}				
				
				if (document instanceof IFipTicket) {
					asnDocument = convertFipTicket((IFipTicket) document , uicTicket.getIssuerDetails().getIssuingDate());
				}						
				
				if (document instanceof IPass) {
					asnDocument = convertPass((IPass) document , uicTicket.getIssuerDetails().getIssuingDate());
				}		
				
				if (document instanceof IVoucher) {
					asnDocument = convertVoucher((IVoucher) document , uicTicket.getIssuerDetails().getIssuingDate());
				}					
				
				if (document instanceof ICounterMark) {
					asnDocument = convertCounterMark((ICounterMark) document , uicTicket.getIssuerDetails().getIssuingDate());
				}					
				
				if (document instanceof IParkingGround) {
					asnDocument = convertParkingGround((IParkingGround) document , uicTicket.getIssuerDetails().getIssuingDate());
				}					
				
				if (document instanceof IStationPassage) {
					asnDocument = convertStationPassage((IStationPassage) document , uicTicket.getIssuerDetails().getIssuingDate());
				}
				
				if (document instanceof ICustomerCard) {
					asnDocument = encodeCustomerCard((ICustomerCard) document , uicTicket.getIssuerDetails().getIssuingDate());
				}				
				
				
				if (document instanceof IDelayConfirmation) {
					asnDocument = encodeDelayConfirmation((IDelayConfirmation) document , uicTicket.getIssuerDetails().getIssuingDate());
				}				
								
				
				if (asnDocument!= null && document.getToken()!= null) {					
					asnDocument.setToken(encodeToken(document.getToken()));
				}
				
				if (asnDocument != null) {
					documents.add(asnDocument);
				}
			}
			asnTicket.setTransportDocument(documents);
		}		

		
		return  asnTicket;
	}
	

	
	private DocumentData encodeDelayConfirmation(IDelayConfirmation document, Date issuingDate) throws EncodingFormatException {
		
		DocumentData asnDocument = new DocumentData();
		TicketDetailData asnTicket = new TicketDetailData();		
		asnDocument.setTicket(asnTicket);			
		
		DelayConfirmation asnData = new DelayConfirmation();
		asnDocument.getTicket().setDelayConfirmation(asnData);
		
		asnData.setTrainIA5(UicEncoderUtils.getIA5NonNum(document.getTrain()));
		asnData.setTrainNum(Asn1BigInteger.toAsn1(UicEncoderUtils.getNum(document.getTrain())));			
						
		asnData.setReferenceNum(UicEncoderUtils.getNum(document.getReference()));
		asnData.setReferenceIA5(UicEncoderUtils.getIA5NonNum(document.getReference()));	
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}		
		asnData.setStationIA5(UicEncoderUtils.getIA5NonNum(document.getStation()));
		asnData.setStationNum(UicEncoderUtils.getNum(document.getStation()));		
		
		
		asnData.setPlannedArrivalDate(document.getArrivalDate());	
		asnData.setDepartureUTCOffset(document.getArrivalUTCoffset());
		
		asnData.setAffectedTickets(encodeTicketLickList(document.getLinkedTickets()));
		
		asnData.setConfirmationType(ConfirmationTypeType.values()[document.getConfirmationType()]);
		
		asnData.setDelay(new Long(document.getDelay()));
		
		asnData.setTrainCancelled(document.isTrainCancelled());
		
		
		asnData.setInfoText(document.getInfoText());
		
		asnData.setStationNameUTF8(document.getStationName());		
		
		asnData.setExtension(encodeExtension(document.getExtension()));		
		
		return asnDocument;
	}

	
	/**
	 * Convert car carriage reservation.
	 *
	 * @param document the document
	 * @param issuingDate the issuing date
	 * @return the document data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private DocumentData convertCarCarriageReservation(	ICarCarriageReservation document, Date issuingDate) throws EncodingFormatException {
		
		DocumentData asnDocument = new DocumentData();
		TicketDetailData asnTicket = new TicketDetailData();		
		asnDocument.setTicket(asnTicket);			
		
		CarCarriageReservationData asnData = new CarCarriageReservationData();
		asnDocument.getTicket().setCarCarriageReservation(asnData);
		
		asnData.setTrainIA5(UicEncoderUtils.getIA5NonNum(document.getTrain()));
		asnData.setTrainNum(UicEncoderUtils.getNum(document.getTrain()));			
				
		asnData.setProductOwnerNum(UicEncoderUtils.getNum(document.getProductOwner()));
		asnData.setProductOwnerIA5(UicEncoderUtils.getIA5NonNum(document.getProductOwner()));
			
		asnData.setReferenceNum(UicEncoderUtils.getNum(document.getReference()));
		asnData.setReferenceIA5(UicEncoderUtils.getIA5NonNum(document.getReference()));	
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUICReservation && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}		
		
		asnData.setCarrierNum(SequenceOfCarrierNum.getSequence(UicEncoderUtils.getNumList(document.getCarriers())));	
		asnData.setCarrierIA5(UicEncoderUtils.getIA5NonNumList(document.getCarriers()));	
		
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		asnData.setCompartmentDetails(encodeCompartmentDetails(document.getCompartmentDetails()));
		
		
		if (document.getPriceType()!= IPriceTypeType.travelPrice && document.getPriceType() != null ){
			asnData.setPriceType(PriceTypeType.valueOf(document.getPriceType().name()));
		}

		
		if (document.getServiceBrand()!= null) {
			asnData.setServiceBrand(UicEncoderUtils.getRestrictedInt(document.getServiceBrand().getServiceBrand(),1,9999));
			asnData.setServiceBrandAbrUTF8(document.getServiceBrand().getServiceBrandAbbreviation());
			asnData.setServiceBrandNameUTF8(document.getServiceBrand().getServiceBrandDescription());
			
		}
		if (document.getStationCodeTable() != IStationCodeTable.stationUICReservation && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}		
		
		asnData.setFromStationIA5(UicEncoderUtils.getIA5NonNum(document.getFromStation()));
		asnData.setFromStationNum(UicEncoderUtils.getNum(document.getFromStation()));
		
		asnData.setToStationIA5(UicEncoderUtils.getIA5NonNum(document.getToStation()));
		asnData.setToStationNum(UicEncoderUtils.getNum(document.getToStation()));		
		
		asnData.setFromStationNameUTF8(document.getFromStationName());
		asnData.setToStationNameUTF8(document.getToStationName());				

		
		asnData.setAttachedBicycles(UicEncoderUtils.getRestrictedInt(document.getAttachedBicycles(),1,5));
		asnData.setAttachedBoats(UicEncoderUtils.getRestrictedInt(document.getAttachedBoats(),1,2));
		asnData.setAttachedSurfboards(UicEncoderUtils.getRestrictedInt(document.getAttachedSurfboards(),1,5));
		
		if (document.getLoadingDeck() != ILoadingDeckType.upper && document.getLoadingDeck() != null){
			asnData.setLoadingDeck(LoadingDeckType.valueOf(document.getLoadingDeck().name()));
		}
				
		asnData.setLoadingListEntry(UicEncoderUtils.getRestrictedInt(document.getAttachedSurfboards(),1,200));
		
		asnData.setBoatCategory(UicEncoderUtils.getRestrictedInt(document.getBoatCategory(),1,6));
		asnData.setCarCategory(UicEncoderUtils.getRestrictedInt(document.getCarCategory(),1,9));
		asnData.setCoach(UicEncoderUtils.getIA5(document.getCoach()));

		asnData.setRoofRackHeight(UicEncoderUtils.getRestrictedInt(document.getRoofRackHeight(),1,99));
		
		if (document.getRoofRackType()!= IRoofRackType.norack && document.getRoofRackType() != null ) {
			asnData.setRoofRackType(RoofRackType.valueOf(document.getRoofRackType().name()));
		}
		
		if (document.getTariff() != null) {
			asnData.setTariff(encodeTariff(document.getTariff()));
		}
		asnData.setNumberPlate(UicEncoderUtils.getIA5(document.getNumberPlate()));
		asnData.setPlace(UicEncoderUtils.getIA5(document.getPlace()));
		
		if (document.isTextileRoof() ) {
			asnData.setTextileRoof(true);
		}	else {
			asnData.setTextileRoof(false);	
		}
		
		asnData.setTrailerPlate(UicEncoderUtils.getIA5(document.getTrailerPlate()));
		
		asnData.setLoadingDates(document.getBeginLoading(),document.getEndLoading(), issuingDate);
		
		asnData.setLoadingUTCOffset(document.getLoadingTimeUTCoffset());
		
		asnData.setPrice(document.getPrice());

		if (document.getVatDetails() != null && !document.getVatDetails().isEmpty()){
			for (IVatDetail vat : document.getVatDetails()) {
				asnData.addVatDetail(encodeVatDetail(vat));
			}
		}
		
		
		return asnDocument;
	}

	
	/**
	 * Encode places.
	 *
	 * @param places the places
	 * @return the places type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private PlacesType encodePlaces(IPlaces places) throws EncodingFormatException {
		
		if (places == null) {
			return null;
		}
		
		PlacesType asnData = new PlacesType();
		
		asnData.setCoach(UicEncoderUtils.getIA5(places.getCoach()));
		asnData.setPlaceDescription(places.getPlaceDescription());
		asnData.setPlaceString(UicEncoderUtils.getIA5(places.getPlaceString()));
		asnData.setPlaceNum(SequenceOfPlaceNum.getSequence(UicEncoderUtils.getNumList(places.getPlaces())));
		asnData.setPlaceIA5(UicEncoderUtils.getIA5NonNumList(places.getPlaces()));

		return asnData;
	}
	
	
	/**
	 * Encode compartment details.
	 *
	 * @param compartmentDetails the compartment details
	 * @return the compartment details type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private CompartmentDetailsType encodeCompartmentDetails(ICompartmentDetails compartmentDetails) throws EncodingFormatException {
		
		if (compartmentDetails == null) {
			return null;
		}
		CompartmentDetailsType asnData = new CompartmentDetailsType();
		
		asnData.setCoachType(UicEncoderUtils.getRestrictedInt(compartmentDetails.getCoachType(), 1,99));
		asnData.setCoachTypeDescr(compartmentDetails.getCoachTypeDescr());
		asnData.setCompartmentType(UicEncoderUtils.getRestrictedInt(compartmentDetails.getCompartmentType(), 1,99));
		asnData.setCompartmentTypeDescr(compartmentDetails.getCompartmentTypeDescr());
		asnData.setSpecialAllocation(UicEncoderUtils.getRestrictedInt(compartmentDetails.getSpecialAllocation(), 1,99));
		asnData.setSpecialAllocationDescr(compartmentDetails.getSpecialAllocationDescr());

		return asnData;
	}	
	
	/**
	 * Encode berths.
	 *
	 * @param berths the berths
	 * @return the list
	 * @throws EncodingFormatException the encoding format exception
	 */
	private SequenceOfBerthDetailData encodeBerths(Collection<IBerth> berths) throws EncodingFormatException {
		
		if (berths == null || berths.isEmpty()) {
			return null;
		}
		SequenceOfBerthDetailData asnList = new SequenceOfBerthDetailData();
		
		for ( IBerth berth : berths){
			BerthDetailData asnBerth = new BerthDetailData();
						
			if (berth.getType() != null) {
				asnBerth.setBerthType(BerthTypeType.valueOf(berth.getType().name()));			
			}
			
			if (berth.getGender()!= ICompartmentGenderType.family && berth.getGender() != null) {
				asnBerth.setGender(CompartmentGenderType.valueOf(berth.getGender().name())); 
			}
			
			asnBerth.setNumberOfBerths(UicEncoderUtils.getRestrictedInt(berth.getNumberOfBerths(), 1 , 999 ));
			
			asnList.add(asnBerth);
		}

		if (asnList.isEmpty()) {
			return null;
		}
		return asnList;
	}	
	
	/**
	 * Convert reservation.
	 *
	 * @param document the document
	 * @param issuingDate the issuing date
	 * @return the document data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private DocumentData convertReservation(IReservation document,	Date issuingDate) throws EncodingFormatException {
		
		DocumentData asnDocument = new DocumentData();
		TicketDetailData asnTicket = new TicketDetailData();		
		asnDocument.setTicket(asnTicket);	
		
		ReservationData asnData = new ReservationData();
		asnDocument.getTicket().setReservation(asnData);
		
		asnData.setProductOwnerNum(UicEncoderUtils.getNum(document.getProductOwner()));
		asnData.setProductOwnerIA5(UicEncoderUtils.getIA5NonNum(document.getProductOwner()));
			
		asnData.setReferenceNum(UicEncoderUtils.getNum(document.getReference()));
		asnData.setReferenceIA5(UicEncoderUtils.getIA5NonNum(document.getReference()));	
		
		asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		asnData.setCarrierNum(SequenceOfCarrierNum.getSequence(UicEncoderUtils.getNumList(document.getCarriers())));	
		asnData.setCarrierIA5(UicEncoderUtils.getIA5NonNumList(document.getCarriers()));	
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUICReservation && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}		
		
		asnData.setFromStationIA5(UicEncoderUtils.getIA5NonNum(document.getFromStation()));
		asnData.setFromStationNum(UicEncoderUtils.getNum(document.getFromStation()));
		
		asnData.setToStationIA5(UicEncoderUtils.getIA5NonNum(document.getToStation()));
		asnData.setToStationNum(UicEncoderUtils.getNum(document.getToStation()));		
		
		asnData.setFromStationNameUTF8(document.getFromStationName());
		asnData.setToStationNameUTF8(document.getToStationName());		
				
		asnData.setTrainIA5(UicEncoderUtils.getIA5NonNum(document.getTrain()));
		asnData.setTrainNum(UicEncoderUtils.getNum(document.getTrain()));			
		
		asnData.setTariff(encodeTariffCollection(document.getTariffs()));		
		
		asnData.setDepartureArrivalDates(document.getDepartureDate(),document.getArrivalDate(), issuingDate);
		
		if (document.getDepartureUTCoffset() != null) {
			asnData.setDepartureUTCOffset(document.getDepartureUTCoffset());
			if (document.getArrivalUTCoffset() != null && document.getArrivalUTCoffset() != document.getDepartureUTCoffset()){
				asnData.setArrivalUTCOffset(document.getArrivalUTCoffset());
			}
		}
		
		
		
		if (document.getClassCode() != ITravelClassType.second){
			asnData.setClassCode(convertTravelClass(document.getClassCode()));
		}		
		
		asnData.setBerth(encodeBerths(document.getBerths()));

		asnData.setCompartmentDetails(encodeCompartmentDetails(document.getCompartmentDetails()));
		
		asnData.setLuggage(encodeLuggage(document.getLuggageRestriction()));
		
		asnData.setNumberOfOverbooked(UicEncoderUtils.getRestrictedInt(document.getNumberOfOverbooked(), 1, 200));
		asnData.setNumberOfSupplements(UicEncoderUtils.getRestrictedInt(document.getNumberOfSupplements(), 1, 200));
		
		if (document.getPriceType()!= IPriceTypeType.travelPrice && document.getPriceType() != null){
			asnData.setPriceType(PriceTypeType.valueOf(document.getPriceType().name()));
		}
		asnData.setTypeOfSupplement(UicEncoderUtils.getRestrictedInt(document.getTypeOfSupplement(), 1, 9));
		
		asnData.setPlaces(encodePlaces(document.getPlaces()));
		asnData.setBicyclePlaces(encodePlaces(document.getBicyclePlaces()));
		
		
		if (document.getService() != IServiceType.seat && document.getService() != null) {
			asnData.setService(ServiceType.valueOf(document.getService().name()));
		}
		
		if (document.getServiceBrand()!= null) {
			asnData.setServiceBrand(UicEncoderUtils.getRestrictedInt(document.getServiceBrand().getServiceBrand(),1,9999));
			asnData.setServiceBrandAbrUTF8(document.getServiceBrand().getServiceBrandAbbreviation());
			asnData.setServiceBrandNameUTF8(document.getServiceBrand().getServiceBrandDescription());
			
		}
		
		if (document.getServiceLevel() != null && document.getServiceLevel().length() > 2) {
			throw new EncodingFormatException("Service level too long");
		}
		asnData.setServiceLevel(UicEncoderUtils.getIA5(document.getServiceLevel()));
		
		asnData.setPrice(document.getPrice());

		if (document.getVatDetails() != null && !document.getVatDetails().isEmpty()){
			for (IVatDetail vat : document.getVatDetails()) {
				asnData.addVatDetail(encodeVatDetail(vat));
			}
		}

		return asnDocument;
	}	
	
	/**
	 * Encode included addons.
	 *
	 * @param tickets the tickets
	 * @param issuingDate the issuing date
	 * @param travelClassType the class code
	 * @return the list
	 * @throws EncodingFormatException the encoding format exception
	 */
	private SequenceOfIncludedOpenTicketType encodeIncludedAddons(Collection<IIncludedOpenTicket> tickets, Date issuingDate, ITravelClassType travelClassType) throws EncodingFormatException {
		
		if (tickets == null || tickets.isEmpty()) {
			return null;
		}
		SequenceOfIncludedOpenTicketType asnList = new SequenceOfIncludedOpenTicketType();
		
		for ( IIncludedOpenTicket ticket : tickets){
			
			IncludedOpenTicketType asnTicket = encodeIncludedOpenTicket(ticket,issuingDate,travelClassType);
			if (asnTicket!=null) {
				asnList.add(asnTicket);
			}			
		}		
		
		if (asnList.isEmpty()) {
			return null;
		}
		return asnList;
	}
	
	
	/**
	 * Encode included open ticket.
	 *
	 * @param document the document
	 * @param issuingDate the issuing date
	 * @param classCode the class code
	 * @return the included open ticket type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private IncludedOpenTicketType encodeIncludedOpenTicket(IIncludedOpenTicket document, Date issuingDate, ITravelClassType classCode) throws EncodingFormatException {
		
		if (document == null) return null;
		
		IncludedOpenTicketType asnData = new IncludedOpenTicketType();
		
		asnData.setProductOwnerNum(UicEncoderUtils.getNum(document.getProductOwner()));
		asnData.setProductOwnerIA5(UicEncoderUtils.getIA5NonNum(document.getProductOwner()));
		
		asnData.setProductIdNum(UicEncoderUtils.getNum(document.getProductId()));
		asnData.setProductIdIA5(UicEncoderUtils.getIA5NonNum(document.getProductId()));				
			
		
		asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}		
				
		asnData.setIncludedCarriersNum(SequenceOfCarrierNum.getSequence(UicEncoderUtils.getNumList(document.getIncludedCarriers())));	
		asnData.setIncludedCarriersIA5(UicEncoderUtils.getIA5NonNumList(document.getIncludedCarriers()));	
		
		asnData.setExcludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getExcludedServiceBrands(),1,9999)));		
		asnData.setIncludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getIncludedServiceBrands(),1,9999)));	
		
		asnData.setValidityDates(document.getValidFrom(), document.getValidUntil(), issuingDate);
		
		if (document.getValidFromUTCoffset() != null) {
			asnData.setValidFromUTCOffset(document.getValidFromUTCoffset());
			if (document.getValidUntilUTCoffset() != null && document.getValidUntilUTCoffset() != document.getValidFromUTCoffset()){
				asnData.setValidUntilUTCOffset(document.getValidUntilUTCoffset());
			}
		}

	
		if (document.getTariffs() != null && document.getTariffs().size() > 0){
			asnData.setTariffs(encodeTariffCollection(document.getTariffs()));
		}
		
		if (document.getClassCode() != classCode){
			asnData.setClassCode(convertTravelClass(document.getClassCode()));
		}
		if (document.getExternalIssuer() > 0) {
			asnData.setExternalIssuerId(new Long(document.getExternalIssuer()));
		}
		
		if (document.getAuthorizationCode() > 0)  {
			asnData.setIssuerAutorizationId(new Long(document.getAuthorizationCode()));
		}

		if (document.getValidRegionList()!= null && document.getValidRegionList().size() > 0) {
			asnData.setValidRegion(encodeRegionCollection(document.getValidRegionList(), issuingDate));
		}

		return asnData;
	}	
	
	/**
	 * Encode return description.
	 *
	 * @param route the route
	 * @param issuingDate the issuing date
	 * @return the return route description type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private ReturnRouteDescriptionType encodeReturnDescription(IReturnRouteDescription route, Date issuingDate) throws EncodingFormatException {
		
		if (route == null) return null;
		
		ReturnRouteDescriptionType asnData = new ReturnRouteDescriptionType();
		
		asnData.setFromStationIA5(UicEncoderUtils.getIA5NonNum(route.getFromStation()));
		asnData.setFromStationNum(UicEncoderUtils.getNum(route.getFromStation()));
		
		asnData.setToStationIA5(UicEncoderUtils.getIA5NonNum(route.getToStation()));
		asnData.setToStationNum(UicEncoderUtils.getNum(route.getToStation()));		
		
		asnData.setFromStationNameUTF8(route.getFromStationName());
		asnData.setToStationNameUTF8(route.getToStationName());		
		
		if (route.getValidRegionList()!= null && route.getValidRegionList().size() > 0) {
			asnData.setValidReturnRegion(encodeRegionCollection(route.getValidRegionList(), issuingDate));
		}
		
		if (route.getValidRegionDesc() != null && route.getValidRegionDesc().length() > 0){
			asnData.setValidReturnRegionDesc(route.getValidRegionDesc());
		}
		
		return asnData;
	}
	
	/**
	 * Encode luggage.
	 *
	 * @param luggageRestriction the luggage restriction
	 * @return the luggage restriction type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private LuggageRestrictionType encodeLuggage(ILuggageRestriction luggageRestriction) throws EncodingFormatException {
		
		if (luggageRestriction== null) return null;
		
		LuggageRestrictionType asnData = new LuggageRestrictionType();

		asnData.setMaxHandLuggagePieces(UicEncoderUtils.getRestrictedIntWithDefault(luggageRestriction.getMaxHandLuggagePieces(),1,99,3));

		asnData.setMaxNonHandLuggagePieces(UicEncoderUtils.getRestrictedIntWithDefault(luggageRestriction.getMaxNonHandLuggagePieces(),1,99,1));
				
		if (luggageRestriction.getRegisteredLuggage() != null && luggageRestriction.getRegisteredLuggage().size() > 0) {
			asnData.setRegisteredLuggage(encodeRegisteredLuggage(luggageRestriction.getRegisteredLuggage()));
		}
		
		return asnData;
	}

	/**
	 * Encode registered luggage.
	 *
	 * @param registeredLuggage the registered luggage
	 * @return the list
	 * @throws EncodingFormatException the encoding format exception
	 */
	private SequenceOfRegisteredLuggageType encodeRegisteredLuggage(Collection<IRegisteredLuggage> registeredLuggage) throws EncodingFormatException {
		if (registeredLuggage== null || registeredLuggage.isEmpty()) {
			return null;
		}
		SequenceOfRegisteredLuggageType asnList = new SequenceOfRegisteredLuggageType(); 
		
		for ( IRegisteredLuggage luggage : registeredLuggage){
			RegisteredLuggageType asnLuggage = new RegisteredLuggageType();
			asnLuggage.setMaxSize(UicEncoderUtils.getRestrictedInt(luggage.getMaxSize(),1,300));
			asnLuggage.setMaxWeight(UicEncoderUtils.getRestrictedInt(luggage.getMaxWeight(),1,99));
			asnLuggage.setRegistrationId(luggage.getRegistrationId());
			
			asnList.add(asnLuggage);
		}

		if (asnList.isEmpty()){
			return null;			
		}		
		return asnList;
	}
	
	/**
	 * Convert open ticket.
	 *
	 * @param document the document
	 * @param issuingDate the issuing date
	 * @return the document data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private DocumentData convertOpenTicket(IOpenTicket document,Date issuingDate) throws EncodingFormatException {
		
		DocumentData asnDocument = new DocumentData();
		TicketDetailData asnTicket = new TicketDetailData();		
		asnDocument.setTicket(asnTicket);	
		
		OpenTicketData asnData = new OpenTicketData();
		asnTicket.setOpenTicket(asnData);

		asnDocument.setTicket(asnTicket);
		asnTicket.setOpenTicket(asnData);
		
		asnData.setProductOwnerNum(UicEncoderUtils.getNum(document.getProductOwner()));
		asnData.setProductOwnerIA5(UicEncoderUtils.getIA5NonNum(document.getProductOwner()));
		
		asnData.setProductIdNum(UicEncoderUtils.getNum(document.getProductId()));
		asnData.setProductIdIA5(UicEncoderUtils.getIA5NonNum(document.getProductId()));				
			
		asnData.setReferenceNum(UicEncoderUtils.getNum(document.getReference()));
		asnData.setReferenceIA5(UicEncoderUtils.getIA5NonNum(document.getReference()));	
		
		asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}		
		
		asnData.setFromStationIA5(UicEncoderUtils.getIA5NonNum(document.getFromStation()));
		asnData.setFromStationNum(UicEncoderUtils.getNum(document.getFromStation()));
		
		asnData.setToStationIA5(UicEncoderUtils.getIA5NonNum(document.getToStation()));
		asnData.setToStationNum(UicEncoderUtils.getNum(document.getToStation()));		
		
		asnData.setFromStationNameUTF8(document.getFromStationName());
		asnData.setToStationNameUTF8(document.getToStationName());		
		
		asnData.setCarriersNum(SequenceOfCarrierNum.getSequence(UicEncoderUtils.getNumList(document.getIncludedCarriers())));	
		asnData.setCarriersIA5(UicEncoderUtils.getIA5NonNumList(document.getIncludedCarriers()));	
		
		asnData.setExcludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getExcludedServiceBrands(),1,32000)));		
		asnData.setIncludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getIncludedServiceBrands(),1,32000)));	
		
		asnData.setValidityDates(document.getValidFrom(), document.getValidUntil(), issuingDate);
		
		if (document.getValidFromUTCoffset() != null) {
			asnData.setValidFromUTCOffset(document.getValidFromUTCoffset());
			if (document.getValidUntilUTCoffset() != null && document.getValidUntilUTCoffset() != document.getValidFromUTCoffset()){
				asnData.setValidUntilUTCOffset(document.getValidUntilUTCoffset());
			}
		}

			
		asnData.setTariffs(encodeTariffCollection(document.getTariffs()));
		
		if (document.getActivatedDays() != null && !document.getActivatedDays().isEmpty()) {
			asnData.addActivatedDays(DateTimeUtils.getActivatedDays(document.getValidFrom(), document.getActivatedDays()));
		}

		if (document.getClassCode() != ITravelClassType.second){
			asnData.setClassCode(convertTravelClass(document.getClassCode()));
		}
		if (document.getExternalIssuer()>0) {
			asnData.setExtIssuerId(new Long(document.getExternalIssuer()));
		}
		
		if (document.getAuthorizationCode()>0)  {
			asnData.setIssuerAutorizationId(new Long (document.getAuthorizationCode()));
		}

		if (document.getValidRegionList() != null && document.getValidRegionList().size() > 0) {
			asnData.setValidRegion(encodeRegionCollection(document.getValidRegionList(), issuingDate));
		}		
		asnData.setValidRegionDesc(document.getValidRegionDesc());
		
		if (document.getIncludedAddOns() != null && document.getIncludedAddOns().size() > 0) {
			asnData.setIncludedAddOns(encodeIncludedAddons(document.getIncludedAddOns(),issuingDate, document.getClassCode()));
		}
		
		if (document.getLuggageRestriction() != null) {
			asnData.setLuggage(encodeLuggage(document.getLuggageRestriction()));
		}
		
		if (document.isReturnIncluded()) {
			asnData.setReturnIncluded(true);
			asnData.setReturnDescription(encodeReturnDescription(document.getReturnDescription(),issuingDate));			
		} else {
			asnData.setReturnIncluded(false);
		}
		
		asnData.setPrice(document.getPrice());

		if (document.getVatDetails() != null && !document.getVatDetails().isEmpty()){
			for (IVatDetail vat : document.getVatDetails()) {
				asnData.addVatDetail(encodeVatDetail(vat));
			}
		}


		return asnDocument;
	}
	
	/**
	 * Convert counter mark.
	 *
	 * @param document the document
	 * @param issuingDate the issuing date
	 * @return the document data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private DocumentData convertCounterMark(ICounterMark document,	Date issuingDate) throws EncodingFormatException {
		
		DocumentData asnDocument = new DocumentData();
		TicketDetailData asnTicket = new TicketDetailData();		
		asnDocument.setTicket(asnTicket);		
		
		CountermarkData asnData = new CountermarkData();
		asnDocument.getTicket().setCounterMark(asnData);
		
		asnData.setProductOwnerNum(UicEncoderUtils.getNum(document.getProductOwner()));  
		asnData.setProductOwnerIA5(UicEncoderUtils.getIA5NonNum(document.getProductOwner())); 

			
		asnData.setTicketReferenceNum(UicEncoderUtils.getNum(document.getTicketReference()));
		asnData.setTicketReferenceIA5(UicEncoderUtils.getIA5NonNum(document.getTicketReference()));	
		
		asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}		
		
		asnData.setFromStationIA5(UicEncoderUtils.getIA5NonNum(document.getFromStation()));
		asnData.setFromStationNum(UicEncoderUtils.getNum(document.getFromStation()));
		
		asnData.setToStationIA5(UicEncoderUtils.getIA5NonNum(document.getToStation()));
		asnData.setToStationNum(UicEncoderUtils.getNum(document.getToStation()));		
		
		asnData.setFromStationNameUTF8(document.getFromStationName());
		asnData.setToStationNameUTF8(document.getToStationName());		
				
		asnData.setValidityDates(document.getValidFrom(), document.getValidUntil(), issuingDate);
		
		if (document.getValidFromUTCoffset() != null) {
			asnData.setValidFromUTCOffset(document.getValidFromUTCoffset());
			if (document.getValidUntilUTCoffset() != null && document.getValidUntilUTCoffset() != document.getValidFromUTCoffset()){
				asnData.setValidUntilUTCOffset(document.getValidUntilUTCoffset());
			}
		}


					
		asnData.setReferenceNum(UicEncoderUtils.getNum(document.getReference()));
		asnData.setReferenceIA5(UicEncoderUtils.getIA5NonNum(document.getReference()));	
		
		asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}		
		
		asnData.setFromStationIA5(UicEncoderUtils.getIA5NonNum(document.getFromStation()));
		asnData.setFromStationNum(UicEncoderUtils.getNum(document.getFromStation()));
		
		asnData.setToStationIA5(UicEncoderUtils.getIA5NonNum(document.getToStation()));
		asnData.setToStationNum(UicEncoderUtils.getNum(document.getToStation()));		
		
		asnData.setFromStationNameUTF8(document.getFromStationName());
		asnData.setToStationNameUTF8(document.getToStationName());		
		
		asnData.setCarriersNum(SequenceOfCarrierNum.getSequence(UicEncoderUtils.getNumList(document.getIncludedCarriers())));	
		asnData.setCarriersIA5(UicEncoderUtils.getIA5NonNumList(document.getIncludedCarriers()));	
	
		asnData.setExcludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getExcludedServiceBrands(),1,32000)));		
		asnData.setIncludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getIncludedServiceBrands(),1,32000)));			
				
		if (document.getValidRegionList() != null && document.getValidRegionList().size() > 0) {
			asnData.setValidRegion(encodeRegionCollection(document.getValidRegionList(), issuingDate));
		}
		asnData.setValidRegionDesc(document.getValidRegionDesc());
		
		if (document.getClassCode() != ITravelClassType.second){
			asnData.setClassCode(convertTravelClass(document.getClassCode()));
		}


		if (document.isReturnIncluded()) {
			asnData.setReturnIncluded(true);
			if (document.getReturnDescription()!= null) {
				asnData.setReturnDescription(encodeReturnDescription(document.getReturnDescription(),issuingDate));
			}
		} else {
			asnData.setReturnIncluded(false);
		}
		
		return asnDocument;
	}

	
	
	/**
	 * Encode via station collection.
	 *
	 * @param stations the stations
	 * @return the list
	 * @throws EncodingFormatException the encoding format exception
	 */
	private SequenceOfViaStationType encodeViaStationCollection(Collection<IViaStation> stations) throws EncodingFormatException {
		if (stations== null || stations.isEmpty()) {
			return null;						
		}
		SequenceOfViaStationType asnList = new SequenceOfViaStationType();
		
		for ( IViaStation station : stations) {
			ViaStationType asnData = encodeViaStation(station);
			if (asnData != null) {
				asnList.add(asnData);
			}
		}
		
		if (asnList.isEmpty()) {
			return null;
		}

		return asnList;
	}
	
	
	/**
	 * Encode via station.
	 *
	 * @param document the data
	 * @return the via station type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private ViaStationType encodeViaStation(IViaStation document) throws EncodingFormatException {
		if (document == null) return null;
		
		ViaStationType asnData =new ViaStationType();
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}	

		asnData.setStationIA5(UicEncoderUtils.getIA5NonNum(document.getStation()));
		asnData.setStationNum(UicEncoderUtils.getNum(document.getStation()));	
		
		asnData.setAlternativeRoutes(encodeViaStationCollection(document.getAlternativeRoutes()));

		if (document.isBorder()) {
			asnData.setBorder(true);
		} else {
			asnData.setBorder(false);
		}

		asnData.setCarriersNum(SequenceOfCarrierNum.getSequence(UicEncoderUtils.getNumList(document.getCarriers())));	
		asnData.setCarriersIA5(UicEncoderUtils.getIA5NonNumList(document.getCarriers()));			

		asnData.setRoute(encodeViaStationCollection(document.getRoute()));
		
		if (document.getRouteId() > 0){
			asnData.setSeriesId(new Long(document.getRouteId()));
		}
		return asnData;
	}
	
	/**
	 * Encode zone.
	 *
	 * @param data the data
	 * @return the zone type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private ZoneType encodeZone(IZone data) throws EncodingFormatException {
		if (data == null) return null;
		
		ZoneType asnData =new ZoneType();
		
		if (data.getBinaryZoneId() != null && data.getBinaryZoneId().length > 0) {
			asnData.setBinaryZoneId(data.getBinaryZoneId());
		}
		asnData.setCarrierNum(UicEncoderUtils.getNum(data.getCarrier()));
		asnData.setCarrierIA5(UicEncoderUtils.getIA5NonNum(data.getCarrier()));
		
		if (data.getStationCodeTable() != IStationCodeTable.stationUIC && data.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(data.getStationCodeTable().name()));
		}	
		asnData.setEntryStationIA5(UicEncoderUtils.getIA5NonNum(data.getEntryStation()));
		asnData.setEntryStationNum(UicEncoderUtils.getNum(data.getEntryStation()));		
		
		asnData.setTerminatingStationIA5(UicEncoderUtils.getIA5NonNum(data.getTerminatingStation()));
		asnData.setTerminatingStationNum(UicEncoderUtils.getNum(data.getTerminatingStation()));	
		
		asnData.setZoneId(SequenceOfUnrestrictedLong.getSequence(UicEncoderUtils.encodeIntegerCollection(data.getZoneIds())));
		
		if (data.getCity() > 0) {
			asnData.setCity(Asn1BigInteger.toAsn1(data.getCity()));
		}
		
		if (data.getNUTScode() != null && data.getNUTScode().length() > 0) {
			asnData.setNutsCode(data.getNUTScode());			
		}
		
		return asnData;
	}
	
	



	/**
	 * Encode train link.
	 *
	 * @param data the data
	 * @param issuingDate the issuing date
	 * @return the train link type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private TrainLinkType encodeTrainLink(ITrainLink data, Date issuingDate) throws EncodingFormatException {
		if (data == null) return null;
		
		TrainLinkType asnData =new TrainLinkType();
		
		asnData.setFromStationIA5(UicEncoderUtils.getIA5NonNum(data.getFromStation()));
		asnData.setFromStationNum(UicEncoderUtils.getNum(data.getFromStation()));
		
		asnData.setToStationIA5(UicEncoderUtils.getIA5NonNum(data.getToStation()));
		asnData.setToStationNum(UicEncoderUtils.getNum(data.getToStation()));		
		
		asnData.setFromStationName(data.getFromStationName());
		asnData.setToStationName(data.getToStationName());	
		
		asnData.setTrainIA5(UicEncoderUtils.getIA5NonNum(data.getTrain()));
		asnData.setTrainNum(UicEncoderUtils.getNum(data.getTrain()));		
		
		asnData.setDepartureDate(data.getDepartureDateTime(), issuingDate);
		
		return asnData;
	}	
	
	/**
	 * Encode polygone.
	 *
	 * @param data the data
	 * @return the polygone type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private PolygoneType encodePolygone(IPolygone data) throws EncodingFormatException {
		if (data == null) return null;	
		if (data.getEdges() == null || data.getEdges().isEmpty()) {
			return null;
		}
		
		PolygoneType asnData = new PolygoneType();
		SequenceOfDeltaCoordinates asnList = new SequenceOfDeltaCoordinates();
		
		boolean firstEdge = true;
		
		for ( IGeoCoordinate edge : data.getEdges()) {
			
			if (firstEdge) {
				asnData.setFirstEdge(encodeGeoCoordinate(edge));
				firstEdge = false;
			} else {
				DeltaCoordinates delta = new DeltaCoordinates();
				delta.setLatitude(Asn1BigInteger.toAsn1(asnData.getFirstEdge().getLatitude() - edge.getLatitude()));
				delta.setLatitude(Asn1BigInteger.toAsn1(asnData.getFirstEdge().getLongitude() - edge.getLongitude()));				
			}			
			
		}		
		if (!asnList.isEmpty()) {
			asnData.setEdges(asnList);
		}
		return asnData;
	}

	/**
	 * Encode line.
	 *
	 * @param data the data
	 * @return the line type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private LineType encodeLine(ILine data) throws EncodingFormatException {
		if (data == null) return null;
		LineType asnData =new LineType();

		asnData.setBinaryZoneId(data.getBinaryZoneId());
		asnData.setCarrierNum(UicEncoderUtils.getNum(data.getCarrier()));
		asnData.setCarrierIA5(UicEncoderUtils.getIA5NonNum(data.getCarrier()));
		if (data.getStationCodeTable() != IStationCodeTable.stationUIC && data.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(data.getStationCodeTable().name()));
		}	
		asnData.setEntryStationIA5(UicEncoderUtils.getIA5NonNum(data.getEntryStation()));
		asnData.setEntryStationNum(UicEncoderUtils.getNum(data.getEntryStation()));		
		
		asnData.setTerminatingStationIA5(UicEncoderUtils.getIA5NonNum(data.getTerminatingStation()));
		asnData.setTerminatingStationNum(UicEncoderUtils.getNum(data.getTerminatingStation()));	
		
		if (data.getCity() > 0) {
			asnData.setCity(new Long(data.getCity()));
		}
		
		asnData.setLineId(SequenceOfUnrestrictedLong.getSequence(UicEncoderUtils.encodeIntegerCollection(data.getLineIds())));
		
		return asnData;
	}
	
	
	
	/**
	 * Encode region collection.
	 *
	 * @param regionList the region list
	 * @param issuingDate the issuing date
	 * @return the list
	 * @throws EncodingFormatException the encoding format exception
	 */
	private SequenceOfRegionalValidityType encodeRegionCollection(Collection<IRegionalValidity> regionList, Date issuingDate) throws EncodingFormatException {
		
		if (regionList == null || regionList.isEmpty()) {
			return null;
		}
		
		SequenceOfRegionalValidityType asnList = new SequenceOfRegionalValidityType();
		
		for ( IRegionalValidity region :regionList) {
			
			RegionalValidityType asnRegion = new RegionalValidityType();
			
			if (region instanceof ILine ) {
				LineType line = encodeLine((ILine)region);
				if (line == null){
					break;
				}
				asnRegion.setLines(line);
				asnList.add(asnRegion);
			} else if (region instanceof IPolygone ) {
				PolygoneType line = encodePolygone((IPolygone)region);
				if (line == null){
					break;
				}
				asnRegion.setPolygone(line);
				asnList.add(asnRegion);
			} else 	if (region instanceof ITrainLink ) {
				TrainLinkType trainLink = encodeTrainLink((ITrainLink)region, issuingDate);
				if (trainLink == null){
					break;
				}
				asnRegion.setTrainLink(trainLink);
				asnList.add(asnRegion);
			} else 	if (region instanceof IViaStation ) {
				ViaStationType viaStation = encodeViaStation((IViaStation)region);
				if (viaStation == null){
					break;
				}
				asnRegion.setViaStations(viaStation);
				asnList.add(asnRegion);
			} else 	if (region instanceof IZone ) {
				ZoneType zone = encodeZone((IZone)region);
				if (zone == null){
					break;
				}
				asnRegion.setZones(zone);
				asnList.add(asnRegion);
			}

		}
		
		if (asnList.isEmpty()) {
			return null;
		}
		
		return asnList;
	}
	
	/**
	 * Convert parking ground.
	 *
	 * @param document the document
	 * @param issuingDate the issuing date
	 * @return the document data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private DocumentData convertParkingGround(IParkingGround document,	Date issuingDate) throws EncodingFormatException {
		
		DocumentData asnDocument = new DocumentData();
		TicketDetailData asnTicket = new TicketDetailData();		
		asnDocument.setTicket(asnTicket);			
		
		ParkingGroundData asnData = new ParkingGroundData();
		asnDocument.getTicket().setParkingGround(asnData);
		
		asnData.setProductOwnerNum(UicEncoderUtils.getNum(document.getProductOwner()));
		asnData.setProductOwnerIA5(UicEncoderUtils.getIA5NonNum(document.getProductOwner())); 
		
		asnData.setReferenceNum(Asn1BigInteger.toAsn1(UicEncoderUtils.getNum(document.getReference()))); 
		asnData.setReferenceIA5(UicEncoderUtils.getIA5NonNum(document.getReference()));	

		asnData.setExtension(encodeExtension(document.getExtension()));
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}		
		
		asnData.setStationIA5(UicEncoderUtils.getIA5NonNum(document.getStation()));
		asnData.setStationNum(UicEncoderUtils.getNum(document.getStation()));		
		
		asnData.setAccessCode(UicEncoderUtils.getIA5(document.getAccessCode()));
		asnData.setEntryTrack(document.getEntryTrack());
		
		if (document.getFromParkingDate() != null){
			asnData.setParkingDates(document.getFromParkingDate(),document.getToParkingDate(),issuingDate);
		}
		
		asnData.setLocation(document.getLocation());
		asnData.setNumberPlate(UicEncoderUtils.getIA5(document.getNumberPlate()));
		asnData.setParkingGroundId(UicEncoderUtils.getIA5(document.getParkingGroundId()));
		asnData.setSpecialInformation(document.getSpecialInformation());
		
		asnData.setPrice(document.getPrice());

		if (document.getVatDetails() != null && !document.getVatDetails().isEmpty()){
			for (IVatDetail vat : document.getVatDetails()) {
				asnData.addVatDetail(encodeVatDetail(vat));
			}
		}

		return asnDocument;
	}
	
	
	/**
	 * Encode route section.
	 *
	 * @param document the route
	 * @return the route section type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private RouteSectionType encodeRouteSection(IRouteSection document) throws EncodingFormatException {
		
		if (document == null) return null;
		
		RouteSectionType asnRoute = new RouteSectionType();
		
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnRoute.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}			
		
		asnRoute.setFromStationIA5(UicEncoderUtils.getIA5NonNum(document.getFromStation()));
		asnRoute.setFromStationNum(UicEncoderUtils.getNum(document.getFromStation()));
		
		asnRoute.setToStationIA5(UicEncoderUtils.getIA5NonNum(document.getToStation()));
		asnRoute.setToStationNum(UicEncoderUtils.getNum(document.getToStation()));		
		
		asnRoute.setFromStationNameUTF8(document.getFromStationName());
		asnRoute.setToStationNameUTF8(document.getToStationName());
	

		return asnRoute;
	}
	
	/**
	 * Encode tariff collection.
	 *
	 * @param tariffs the tariffs
	 * @return the list
	 * @throws EncodingFormatException the encoding format exception
	 */
	private SequenceOfTariffType encodeTariffCollection(Collection<ITariff> tariffs) throws EncodingFormatException {
		
		if (tariffs== null || tariffs.isEmpty()) {
			return null;
		}
		
		SequenceOfTariffType tariffList = new SequenceOfTariffType();
		
		for (ITariff tariff :tariffs){
			
			TariffType asnTariff = encodeTariff(tariff);
			
			if (asnTariff != null) {
				tariffList.add(asnTariff);
			}
		}
		
		if (tariffList.isEmpty()) {
			return null;
		}

		return tariffList;
	}
	
	/**
	 * Encode tariff.
	 *
	 * @param tariff the tariff
	 * @return the tariff type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private TariffType encodeTariff(ITariff tariff) throws EncodingFormatException {
		
		if (tariff == null ) {
			return null;
		}
					
		TariffType asnTariff = new TariffType();
			
		asnTariff.setAgeAbove(UicEncoderUtils.getRestrictedInt(tariff.getAgeAbove(),2,120));
		asnTariff.setAgeBelow(UicEncoderUtils.getRestrictedInt(tariff.getAgeBelow(),1,40));

		asnTariff.setNumberOfPassengers(UicEncoderUtils.getRestrictedIntWithDefault(tariff.getNumberOfPassengers(),1,200,1));

		
		try {
			if (tariff.getPassengerType() != null) {
				asnTariff.setPassengerType(PassengerType.valueOf(tariff.getPassengerType().name()));
			}
		} catch (Exception e){
			//no value found			
		}		
		
		
		asnTariff.setReductionCard(encodeCardReferences(tariff.getReductionCards()));
		asnTariff.setRestrictedToCountryOfResidence(tariff.isRestrictedToCountryOfResidence());					
		asnTariff.setRestrictedToRouteSection(encodeRouteSection(tariff.getRestrictedToRouteSection()));
		
		if (tariff.getSeriesDataDetails()!= null) {
			asnTariff.setSeriesDataDetails(encodeSeriesDataDetails(tariff.getSeriesDataDetails()));
		}
		

		asnTariff.setTariffDesc(tariff.getTariffDescription());
		asnTariff.setTariffIdIA5(UicEncoderUtils.getIA5NonNum(tariff.getTariffId()));
		asnTariff.setTariffIdNum(UicEncoderUtils.getNum(tariff.getTariffId()));
		
		asnTariff.setTraverlerid(SequenceOfTravelerId.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(tariff.getTravelerIds(), 1, 254)));				

		return asnTariff;
	}	
	
	private SeriesDetailType encodeSeriesDataDetails(	ISeriesDataDetails seriesDataDetails) throws EncodingFormatException {		
		SeriesDetailType details = new SeriesDetailType();
		details.setSeries(UicEncoderUtils.getRestrictedInt(seriesDataDetails.getSeries(), 1, 99999));
		details.setSupplyingCarrier(UicEncoderUtils.getRestrictedInt(seriesDataDetails.getSupplyingCarrier(), 1, 9999));
		details.setOfferIdentification(UicEncoderUtils.getRestrictedInt(seriesDataDetails.getOfferIdentification(), 1, 99));
		return null;
	}

	/**
	 * Convert voucher.
	 *
	 * @param document the document
	 * @param issuingDate the issuing date
	 * @return the document data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private DocumentData convertVoucher(IVoucher document, Date issuingDate) throws EncodingFormatException {
		DocumentData asnDocument = new DocumentData();
		TicketDetailData asnTicket = new TicketDetailData();		
		asnDocument.setTicket(asnTicket);			
		
		
		VoucherData asnData = new VoucherData();
		asnDocument.getTicket().setVoucher(asnData);
		
		asnData.setProductOwnerNum(UicEncoderUtils.getNum(document.getProductOwner()));
		asnData.setProductOwnerIA5(UicEncoderUtils.getIA5NonNum(document.getProductOwner()));
		
		asnData.setProductIdNum(UicEncoderUtils.getNum(document.getProductId()));
		asnData.setProductIdIA5(UicEncoderUtils.getIA5NonNum(document.getProductId()));				
		
		asnData.setReferenceNum(Asn1BigInteger.toAsn1(UicEncoderUtils.getNum(document.getReference())));
		asnData.setReferenceIA5(UicEncoderUtils.getIA5NonNum(document.getReference()));	
		
		asnData.setInfoText(document.getInfoText());
		
		asnData.setValidity(document.getValidFrom(), document.getValidUntil());

		asnData.setType(UicEncoderUtils.getRestrictedInt(document.getType(), 1,9999));
		
		asnData.setValue(UicEncoderUtils.getRestrictedInt(document.getAmount(),1,9999999));
		
		return asnDocument;
	}	
	
	/**
	 * Convert pass.
	 *
	 * @param document the document
	 * @param issuingDate the issuing date
	 * @return the document data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private DocumentData convertPass(IPass document, Date issuingDate) throws EncodingFormatException {
		
		DocumentData asnDocument = new DocumentData();
		TicketDetailData asnTicket = new TicketDetailData();		
		asnDocument.setTicket(asnTicket);			
		
		PassData asnData = new PassData();
		asnDocument.getTicket().setPass(asnData);
		
		asnData.setProductOwnerNum(UicEncoderUtils.getNum(document.getProductOwner()));
		asnData.setProductOwnerIA5(UicEncoderUtils.getIA5NonNum(document.getProductOwner()));
				
		asnData.setProductIdNum(UicEncoderUtils.getNum(document.getProductId()));
		asnData.setProductIdIA5(UicEncoderUtils.getIA5NonNum(document.getProductId()));				
		
		asnData.setReferenceNum(Asn1BigInteger.toAsn1(UicEncoderUtils.getNum(document.getReference())));
		asnData.setReferenceIA5(UicEncoderUtils.getIA5NonNum(document.getReference()));	
		
		asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));		
		
		asnData.setIncludedCarriersNum(SequenceOfCarrierNum.getSequence(UicEncoderUtils.getNumList(document.getIncludedCarriers())));
		asnData.setIncludedCarriersIA5(UicEncoderUtils.getIA5NonNumList(document.getIncludedCarriers()));		

		asnData.setExcludedCarriersNum(SequenceOfCarrierNum.getSequence(UicEncoderUtils.getNumList(document.getExcludedCarriers())));
		asnData.setExcludedCarriersIA5(UicEncoderUtils.getIA5NonNumList(document.getExcludedCarriers()));			

		asnData.setValidityDates(document.getValidFrom(), document.getValidUntil(), issuingDate);
		
		if (document.getValidFromUTCoffset() != null) {
			asnData.setValidFromUTCOffset(document.getValidFromUTCoffset());
			if (document.getValidUntilUTCoffset() != null && document.getValidUntilUTCoffset() != document.getValidFromUTCoffset()){
				asnData.setValidUntilUTCOffset(document.getValidUntilUTCoffset());
			}
		}

		if (document.getActivatedDays() != null && !document.getActivatedDays().isEmpty()) {
			asnData.addActivatedDays(DateTimeUtils.getActivatedDays(document.getValidFrom(), document.getActivatedDays()));
		}
		
		asnData.setClassCode(convertTravelClass(document.getClassCode()));
		
		if (document.getCountries() != null && document.getCountries().size() > 0){
			asnData.setCountries(SequenceOfCountries.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getCountries(),1,250)));
		}

		asnData.setExcludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getExcludedServiceBrands(),1,32000)));
		asnData.setIncludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getIncludedServiceBrands(),1,32000)));	
		
		asnData.setNumberOfDaysOfTravel(UicEncoderUtils.getRestrictedInt(document.getNumberOfDaysOfTravel(), 1, 93));
		asnData.setNumberOfPossibleTrips(UicEncoderUtils.getRestrictedInt(document.getNumberOfPossibleTrips(), 1, 30));
		asnData.setNumberOfValidityDays(UicEncoderUtils.getRestrictedInt(document.getNumberOfValidityDays(), 1, 370));
		asnData.setPassDescription(document.getPassDescription());
		if (document.getPassType() > 0 ){
			asnData.setPassType(new Long(document.getPassType()));
		}
		
		asnData.setTariffs(encodeTariffCollection(document.getTariffs()));
		

		
		if(document.getValidRegionList()!= null && document.getValidRegionList().size() > 0) {
			asnData.setValidRegion(encodeRegionCollection(document.getValidRegionList(), issuingDate));
		}		
		
		if (document.getValidityDetails() != null) {
			asnData.setValidityPeriodDetails(encodeValidityDetails(document.getValidityDetails(), issuingDate));
		}
		
		
		
		asnData.setPrice(document.getPrice());

		if (document.getVatDetails() != null && !document.getVatDetails().isEmpty()){
			for (IVatDetail vat : document.getVatDetails()) {
				asnData.addVatDetail(encodeVatDetail(vat));
			}
		}
		

		return asnDocument;
	}	
	
	
	private ValidityPeriodDetailType encodeValidityDetails(	IValidityDetails validityDetails, Date referenceDate) throws EncodingFormatException {
		
		if ( (validityDetails.getTimeRanges() == null || validityDetails.getTimeRanges().isEmpty())
				||
			 (validityDetails.getValidityRanges() == null || validityDetails.getValidityRanges().isEmpty()) ) {
					return null;
		}
		
		
		ValidityPeriodDetailType asnData = new ValidityPeriodDetailType();
		
		if (validityDetails.getTimeRanges() != null) {
			for (ITimeRange range : validityDetails.getTimeRanges()) {				
			
				if (range.getFromTime() == range.getUntilTime()){
					break;
				}
				
			    TimeRangeType asnRange = new TimeRangeType();
			    asnRange.setFromTime(new Long(range.getFromTime()));
			    asnRange.setUntilTime(new Long(range.getUntilTime()));			
			    		    
				asnData.getExcludedTimeRange().add(asnRange);
			}
		}
		
		if (validityDetails.getValidityRanges() != null) {
			for (IValidityRange range : validityDetails.getValidityRanges()) {
							
				ValidityPeriodType asnRange = new ValidityPeriodType();
				
				asnRange.setValidityDates(range.getFromDate(), range.getUntilDate(), referenceDate);
				
				if (range.getValidFromUTCoffset() != null) {
					asnRange.setValidFromUTCOffset(range.getValidFromUTCoffset());
					if (range.getValidUntilUTCoffset() != null && range.getValidUntilUTCoffset() != range.getValidFromUTCoffset()){
						asnRange.setValidUntilUTCOffset(range.getValidUntilUTCoffset());
					}
				}


				asnData.getValidityPeriod().add(asnRange);
			}
		}
		
		return asnData;
	}

	/**
	 * Convert fip ticket.
	 *
	 * @param document the document
	 * @param issuingDate the issuing date
	 * @return the document data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private DocumentData convertFipTicket(IFipTicket document, Date issuingDate) throws EncodingFormatException {

		DocumentData asnDocument = new DocumentData();
		TicketDetailData asnTicket = new TicketDetailData();		
		asnDocument.setTicket(asnTicket);	
		
		
		FIPTicketData asnData = new FIPTicketData();
		asnDocument.getTicket().setFipTicket(asnData);
		
		asnData.setProductOwnerNum(UicEncoderUtils.getNum(document.getProductOwner()));
		asnData.setProductOwnerIA5(UicEncoderUtils.getIA5NonNum(document.getProductOwner()));
		
		asnData.setProductIdNum(UicEncoderUtils.getNum(document.getProductId()));
		asnData.setProductIdIA5(UicEncoderUtils.getIA5NonNum(document.getProductId()));		
		
		asnData.setReferenceNum(Asn1BigInteger.toAsn1(UicEncoderUtils.getNum(document.getReference())));
		asnData.setReferenceIA5(UicEncoderUtils.getIA5NonNum(document.getReference()));	
		
		asnData.setCarrierNum(SequenceOfCarrierNum.getSequence(UicEncoderUtils.getNumList(document.getCarriers())));
		asnData.setCarrierIA5(UicEncoderUtils.getIA5NonNumList(document.getCarriers()));	

		asnData.setClassCode(convertTravelClass(document.getClassCode()));
		
		if (!document.isIncludesSupplements()){
			asnData.setIncludesSupplements(false);
		}
		asnData.setNumberOfTravelDays(UicEncoderUtils.getRestrictedInt(document.getNumberOfTravelDates(), 1, 8));
		
		asnData.setValidityDates(document.getValidFrom(), document.getValidUntil(), issuingDate);
		
		if (document.getActivatedDays() != null && !document.getActivatedDays().isEmpty()) {
			asnData.addActivatedDays(DateTimeUtils.getActivatedDays(document.getValidFrom(), document.getActivatedDays()));
		}
		
		asnData.setExtension(encodeExtension(document.getExtension()));				
		
		return asnDocument;
	}
	
	/**
	 * Convert station passage.
	 *
	 * @param document the document
	 * @param issuingDate the issuing date
	 * @return the document data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private DocumentData convertStationPassage(IStationPassage document,Date issuingDate) throws EncodingFormatException {
		
		DocumentData asnDocument = new DocumentData();
		TicketDetailData asnTicket = new TicketDetailData();		
		asnDocument.setTicket(asnTicket);	
		
		StationPassageData asnData = new StationPassageData();
		asnDocument.getTicket().setStationPassage(asnData);
		
		asnData.setProductOwnerNum(UicEncoderUtils.getNum(document.getProductOwner())); 
		asnData.setProductOwnerIA5(UicEncoderUtils.getIA5NonNum(document.getProductOwner()));

		asnData.setProductIdNum(UicEncoderUtils.getNum(document.getProductId())); 
		asnData.setProductIdIA5(UicEncoderUtils.getIA5NonNum(document.getProductId()));		
		
		asnData.setReferenceNum(Asn1BigInteger.toAsn1(UicEncoderUtils.getNum(document.getReference())));
		asnData.setReferenceIA5(UicEncoderUtils.getIA5NonNum(document.getReference()));	

		if (document.getNumberOfdaysAllowed() > 0) {
			asnData.setNumberOfDaysValid(new Long(document.getNumberOfdaysAllowed()));
		}
		
		asnData.setProductName(document.getProductName());
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}
		
		
		if (document.getStations()!= null && !document.getStations().isEmpty()){
			SequenceOfStringIA5 stationsIA5 = new SequenceOfStringIA5();
			SequenceOfUnrestrictedLong stationsNum = new SequenceOfUnrestrictedLong();
			
			for (String station : document.getStations()) {
				String ia5 = UicEncoderUtils.getIA5NonNum(station);
				Long num = UicEncoderUtils.getNum(station);
				if (ia5 != null && ia5.length() > 0) {
					stationsIA5.add(ia5);
				}
				if (num != null && num > 0){
					stationsNum.add(num);
				}
			}
			if (!stationsIA5.isEmpty()){
				asnData.setStationIA5(stationsIA5);
			}
			if (!stationsNum.isEmpty()){
				asnData.setStationNum(stationsNum);
			}
		}
		
		if (document.getStationNames()!= null && !document.getStationNames().isEmpty()) {
			
			SequenceOfStringUTF8 asnList = new SequenceOfStringUTF8();
			for ( String text  :document.getStationNames()){
				if (text.length() > 0) {
					asnList.add(text);
				}
			}
		
			if (!asnList.isEmpty()) {
				asnData.setStationNameUTF8(asnList);
			}
			
		}
		
		asnData.setValidityDates(document.getValidFrom(), document.getValidUntil(), issuingDate);	
		
		if (document.getValidFromUTCoffset() != null) {
			asnData.setValidFromUTCOffset(document.getValidFromUTCoffset());
			if (document.getValidUntilUTCoffset() != null && document.getValidUntilUTCoffset() != document.getValidFromUTCoffset()){
				asnData.setValidUntilUTCOffset(document.getValidUntilUTCoffset());
			}
		}

				
		asnData.setExtension(encodeExtension(document.getExtension()));			
		
		asnData.setAreaCodeNum(SequenceOfUnrestrictedLong.getSequence(UicEncoderUtils.getNumList(document.getAreaCodes())));
		asnData.setAreaCodeIA5(UicEncoderUtils.getIA5NonNumList(document.getAreaCodes()));

		asnData.setAreaNameUTF8(UicEncoderUtils.encodeStringCollection(document.getAreaNames()));		
		
		return asnDocument;
	}	
	

	/**
	 * Encode control details.
	 *
	 * @param data the data
	 * @param issuingDate the issuing date
	 * @return the control data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private ControlData encodeControlDetails(IControlDetail data,	Date issuingDate) throws EncodingFormatException {
		
		if (data == null) return null;
		
		ControlData asnData = new ControlData();
		
		asnData.setExtension(encodeExtension(data.getExtension()));
		
		if (data.isAgeCheckRequired()) {
			asnData.setAgeCheckRequired(true);
		} else {
			asnData.setAgeCheckRequired(false);
		}
		
		if (data.isIdentificationByIdCard()){
			asnData.setIdentificationByIdCard(true);
		} else {
			asnData.setIdentificationByIdCard(false);
		}
		
		if (data.isIdentificationByPassportId()){
			asnData.setIdentificationByPassportId(true);
		} else {
			asnData.setIdentificationByPassportId(false);
		}		
		
		if (data.isOnlineValidationRequired()){
			asnData.setOnlineValidationRequired(true);
		} else {
			asnData.setOnlineValidationRequired(false);
		}		
		
		if (data.isPassportValidationRequired()){
			asnData.setPassportValidationRequired(true);
		} else {
			asnData.setPassportValidationRequired(false);
		}	
		
		if (data.isReductionCardCheckRequired()){
			asnData.setReductionCardCheckRequired(true);
		} else {
			asnData.setReductionCardCheckRequired(false);
		}	
		
		asnData.setInfoText(data.getInfoText());
		
		asnData.setRandomDetailedValidationRequired(UicEncoderUtils.getRestrictedInt(data.getRandomDetailedValidationRequired(), 0,99));
		
		asnData.setIdentificationItem(UicEncoderUtils.getUnRestrictedInt(data.getIdentificationItem()));

		asnData.setIncludedTickets(encodeTicketLickList(data.getLinkedTickets()));
		
		asnData.setIdentificationByCardReference(encodeCardReferences(data.getIdentificationByCardReference()));
		
		return asnData;
	}



	/**
	 * Encode card references.
	 *
	 * @param cardReferences the card references
	 * @return the list
	 * @throws EncodingFormatException the encoding format exception
	 */
	private SequenceOfCardReferenceType encodeCardReferences(Collection<ICardReference> cardReferences) throws EncodingFormatException {
		
		if (cardReferences == null || cardReferences.isEmpty()) return null;
			
		SequenceOfCardReferenceType asnList = new SequenceOfCardReferenceType();
		
		for (ICardReference card : cardReferences){
			
			CardReferenceType asnCard = new CardReferenceType();
			
			if (card.getCardId() != null && card.getCardId().length() > 0){
				//only longs allowed
				 try {
					 long num = Long.parseLong(card.getCardId());
					 asnCard.setCardIdNum(num);
				 } catch (Exception e ){
					 asnCard.setCardIdIA5(card.getCardId());
				 }
			}
			

			asnCard.setCardIssuerNum(UicEncoderUtils.getNum(card.getCardIssuer()));			
			asnCard.setCardIssuerIA5(UicEncoderUtils.getIA5NonNum(card.getCardIssuer()));
			asnCard.setCardName(card.getCardName());
			asnCard.setCardType(UicEncoderUtils.getUnRestrictedInt(card.getCardType()));
			
			asnCard.setLeadingCardIdNum(UicEncoderUtils.getNum(card.getLeadingCardId()));		
			asnCard.setLeadingCardIdIA5(UicEncoderUtils.getIA5NonNum(card.getLeadingCardId()));		
			
			asnCard.setTrailingCardIdNum(UicEncoderUtils.getNum(card.getTrailingCardId()));		
			asnCard.setTrailingCardIdIA5(UicEncoderUtils.getIA5NonNum(card.getTrailingCardId()));		
			
			asnList.add(asnCard);
			
		}

		
		if (asnList.isEmpty()) return null;
		
		return asnList;
	}

	/**
	 * Encode ticket lick list.
	 *
	 * @param linkedTickets the linked tickets
	 * @return the list
	 * @throws EncodingFormatException the encoding format exception
	 */
	private SequenceOfTicketLinkType encodeTicketLickList(Collection<ITicketLink> linkedTickets) throws EncodingFormatException {
		
		if (linkedTickets == null || linkedTickets.isEmpty()) return null;
		
		SequenceOfTicketLinkType asnList = new SequenceOfTicketLinkType();
		
		for (ITicketLink ticket : linkedTickets){
			
			TicketLinkType asnTicket = new TicketLinkType();
			
			asnTicket.setIssuerName(ticket.getIssuer());
			
			asnTicket.setIssuerPNR(ticket.getIssuerPNR());
			
			asnTicket.setProductOwnerNum(UicEncoderUtils.getNum(ticket.getProductOwner()));
			asnTicket.setProductOwnerIA5(UicEncoderUtils.getIA5NonNum(ticket.getProductOwner()));
			
			asnTicket.setReferenceNum(UicEncoderUtils.getNum(ticket.getReference()));
			asnTicket.setReferenceIA5(UicEncoderUtils.getIA5NonNum(ticket.getReference()));			
			
			if (ticket.getTicketType() != ITicketType.openTicket && ticket.getTicketType() != null){
				asnTicket.setTicketType(TicketType.valueOf(ticket.getTicketType().name()));
			}
			
			if (ticket.getLinkMode() != ILinkMode.issuedTogether && ticket.getLinkMode() != null){
				asnTicket.setLinkMode(LinkMode.valueOf(ticket.getLinkMode().name()));
			}

			asnList.add(asnTicket);
		}
		
		if (asnList.isEmpty()) return null;
		
		return asnList;
	}	
	
	/**
	 * Encode travelers.
	 *
	 * @param data the data
	 * @param issuingDate the issuing date
	 * @return the traveler data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private TravelerData encodeTravelers(ITravelerDetail data,Date issuingDate) throws EncodingFormatException {
		
		if (data == null) return null;
		
		TravelerData asnData = new TravelerData();
		
		asnData.setGroupName(data.getGroupName());
		
		asnData.setPreferedLanguage(data.getPreferredLanguage());
		
		if (data.getTravelers()!=null && !data.getTravelers().isEmpty()) {
			
			SequenceOfTravelerType asnTravelers = new SequenceOfTravelerType();
			
			for (ITraveler traveler : data.getTravelers()){
				
				TravelerType asnTraveler = encodeTraveler(traveler);
				
				if (asnTraveler !=null){
					asnTravelers.add(asnTraveler);
				}
			}
			
			if (!asnTravelers.isEmpty()) {
				asnData.setTraveler(asnTravelers);
			}
			
		}

		return asnData;
	}	
	
	/**
	 * Encode issuing details.
	 *
	 * @param data the data
	 * @return the issuing data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private IssuingData encodeIssuingDetails(IIssuingDetail data) throws EncodingFormatException {
		
		if (data == null){
			throw new EncodingFormatException("Issuind data missing");
		}
		
		IssuingData asnData = new IssuingData();
		
		if (!data.isActivated()){
			asnData.setActivated(false);
		} else {
			asnData.setActivated(true);
		}
		
		if (!data.isSecurePaperTicket()) {
			asnData.setSecurePaperTicket(false);
		} else {
			asnData.setSecurePaperTicket(true);
		}
		
		asnData.setExtension(encodeExtension(data.getExtension()));
		
		
		asnData.setIssuedOnLine(UicEncoderUtils.getRestrictedInt(data.getIssuedOnLine(), 1, 99999));
		
		asnData.setIssuedOnTrainNum(UicEncoderUtils.getNum(data.getIssuedOnTrain()));
		asnData.setIssuedOnTrainIA5(UicEncoderUtils.getIA5NonNum(data.getIssuedOnTrain()));

		if (data.getSecurityProvider() != null) {
			asnData.setSecurityProviderNum(UicEncoderUtils.getNum(data.getSecurityProvider()));
			data.setSecurityProvider(data.getIssuer());
		} else {
			asnData.setSecurityProviderNum(UicEncoderUtils.getNum(data.getIssuer()));
		}
		
		if (data.getIssuer()!= null && !data.getIssuer().equals(data.getSecurityProvider()) ){
			asnData.setIssuerNum(UicEncoderUtils.getNum(data.getIssuer()));
			asnData.setIssuerIA5(UicEncoderUtils.getIA5NonNum(data.getIssuer()));
		}
		
		asnData.setIssuerName(data.getIssuerName());
		asnData.setIssuerPNR(UicEncoderUtils.getIA5(data.getIssuerPNR()));
		
		
		asnData.setIssuingDate(data.getIssuingDate());
			
		if (data.isSpecimen()){
			asnData.setSpecimen(true);
		} else {
			asnData.setSpecimen(false);
		}
		
		if (data.getPointOfSale()!= null){
			asnData.setPointOfSale(encodeGeoCoordinate(data.getPointOfSale()));
		}
		
		return asnData;
	}
	
	/**
	 * Encode geo coordinate.
	 *
	 * @param point the point
	 * @return the geo coordinate type
	 */
	private GeoCoordinateType encodeGeoCoordinate(IGeoCoordinate point) {
		
		if (point == null) return null;
		
		GeoCoordinateType asnPoint = new GeoCoordinateType();
		
		asnPoint.setLatitude(point.getLatitude());  
		asnPoint.setLongitude(point.getLongitude());
		
		if (point.getUnit() != IGeoUnitType.milliDegree && point.getUnit() != null){
			asnPoint.setGeoUnit(GeoUnitType.valueOf(point.getUnit().name()));
		}
		
		if (point.getAccuracy() != null) {
			asnPoint.setAccuracy(GeoUnitType.valueOf(point.getAccuracy().name()));
		}
		
		if (point.getHemisphereLatitude() != IHemisphereLatitudeType.east && point.getHemisphereLatitude() != null) {
			asnPoint.setHemisphereLatitude(HemisphereLatitudeType.valueOf(point.getHemisphereLatitude().name()));
		}
		
		if (point.getHemisphereLongitude() != IHemisphereLongitudeType.north && point.getHemisphereLongitude() != null) {
			asnPoint.setHemisphereLongitude(HemisphereLongitudeType.valueOf(point.getHemisphereLongitude().name()));
		}		
		
		if (point.getSystem() != IGeoCoordinateSystemType.wgs84 && point.getSystem() != null){
			asnPoint.setCoordinateSystem(GeoCoordinateSystemType.valueOf(point.getSystem().name()));
		}

		return asnPoint;
	}
	
	/**
	 * Encode customer card.
	 *
	 * @param document the customer card
	 * @param issuingDate the issuing date
	 * @return the document data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private DocumentData encodeCustomerCard(ICustomerCard document,Date issuingDate) throws EncodingFormatException {
		
		DocumentData asnDocument = new DocumentData();
		TicketDetailData asnTicket = new TicketDetailData();		
		asnDocument.setTicket(asnTicket);	
		
		CustomerCardData asnData = new CustomerCardData();		
		asnDocument.getTicket().setCustomerCard(asnData);		
		
		
		if (document.getCardId() != null && document.getCardId().length() > 0){
		//only longs allowed
		 try {
			 long num = Long.parseLong(document.getCardId());
			 asnData.setCardIdNum(num);
		 } catch (Exception e ){
			 asnData.setCardIdIA5(document.getCardId());
		 }
		}
		
			
		asnData.setCardType(UicEncoderUtils.getRestrictedInt(document.getCardType(),1,1000));
		
		asnData.setCardTypeDescr(document.getCardTypeDescr());
		
		asnData.setClassCode(convertTravelClass(document.getClassCode()));
		  
		if (document.getCustomer()!=null){
			asnData.setCustomer(encodeTraveler(document.getCustomer()));
		}
		
		asnData.setCustomerStatus(UicEncoderUtils.getUnRestrictedInt(document.getCustomerStatus()));
		
		asnData.setCustomerStatusDescr(document.getCustomerStatusDescr());
		
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		asnData.setIncludedServices(SequenceOfUnrestrictedLong.getSequence(UicEncoderUtils.getUnRestrictedIntList(document.getIncludedServices())));
		
		asnData.setValidity(document.getValidFrom(), document.getValidUntil());
			
		return asnDocument;
	}

	private TokenType encodeToken(IToken token) throws EncodingFormatException {		
		TokenType asnToken = new TokenType();
		asnToken.setToken(token.getToken());
		asnToken.setTokenProviderNum(UicEncoderUtils.getNum(token.getTokenProvider()));
		asnToken.setTokenProviderIA5(UicEncoderUtils.getIA5NonNum(token.getTokenProvider()));	
		asnToken.setTokenSpecification(token.getTokenSpecification());
		return asnToken;
	}
	
	
	
	
	private VatDetailType encodeVatDetail(IVatDetail vatDetail) {
		
		if (vatDetail == null) return null;
		
		VatDetailType asnVatDetail = new VatDetailType();
		
		asnVatDetail.setAmount(vatDetail.getAmount());
		asnVatDetail.setCountry(new Long(vatDetail.getCountry()));
		asnVatDetail.setPercentage(new Long(vatDetail.getPercentage()));
		asnVatDetail.setVatId(vatDetail.getVatId());
		
		return asnVatDetail;
		
	}

	/**
	 * Encode traveler.
	 *
	 * @param traveler the traveler
	 * @return the traveler type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private TravelerType encodeTraveler(ITraveler traveler) throws EncodingFormatException {
		
		if (traveler == null) return null;
		
		TravelerType asnTraveler = new TravelerType();
		
		asnTraveler.setCountryOfResidence(UicEncoderUtils.getRestrictedInt(traveler.getCountryOfResidence(), 1, 999));
		asnTraveler.setCountryOfPassport(UicEncoderUtils.getRestrictedInt(traveler.getPassportCountry(), 1, 999));
		asnTraveler.setCountryOfIdCard(UicEncoderUtils.getRestrictedInt(traveler.getIDCardCountry(), 1, 999));		
		
		
		asnTraveler.setCustomerIdNum(UicEncoderUtils.getNum(traveler.getCustomerId()));
		asnTraveler.setCustomerIdIA5(UicEncoderUtils.getIA5NonNum(traveler.getCustomerId()));		
		
		asnTraveler.setDateOfBirth(traveler.getDateOfBirth());
		
		asnTraveler.setFirstName(traveler.getFirstName());
		asnTraveler.setSecondName(traveler.getSecondName());	
		asnTraveler.setLastName(traveler.getLastName());
		if (traveler.getGender() != null) {
			asnTraveler.setGender(GenderType.valueOf(traveler.getGender().name()));
		}
		asnTraveler.setIdCard(UicEncoderUtils.getIA5(traveler.getIdCard()));
		asnTraveler.setPassportId(UicEncoderUtils.getIA5(traveler.getPassportId()));
		asnTraveler.setTitle(UicEncoderUtils.getIA5(traveler.getTitle()));	
		
		if (traveler.getPassengerType() != null) {
			asnTraveler.setPassengerType(PassengerType.valueOf(traveler.getPassengerType().name()));
		}
		
		asnTraveler.setPassengerWithReducedMobility(traveler.isPassengerWithReducedMobility());
		if (traveler.isTicketHolder()){
			asnTraveler.setTicketHolder(true);
		} else {
			asnTraveler.setTicketHolder(false);
		}
		
		if (traveler.getStatusCollection()!= null && !traveler.getStatusCollection().isEmpty()){
			
			SequenceOfCustomerStatusType asnList = new SequenceOfCustomerStatusType();
		
			for (ICustomerStatusDescription status : traveler.getStatusCollection()  ) {
				asnList.add(mapCustomerStatusType(status));
			}
			if (!asnList.isEmpty()) {
				asnTraveler.setStatus(asnList);
			}
		}
		
		
		return asnTraveler;
	}	
	
	/**
	 * Map customer status type.
	 *
	 * @param status the status
	 * @return the customer status type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private CustomerStatusType mapCustomerStatusType(ICustomerStatusDescription status) throws EncodingFormatException {
		
		CustomerStatusType asnStatus = new CustomerStatusType();
		
		if (status.getStatus() > 0) {
			asnStatus.setCustomerStatus(new Long(status.getStatus()));
		}
		asnStatus.setCustomerStatusDescr(status.getDescription());
		asnStatus.setStatusProviderIA5(UicEncoderUtils.getIA5NonNum(status.getStatusProvider()));
		asnStatus.setStatusProviderNum(UicEncoderUtils.getNum(status.getStatusProvider()));

		return asnStatus;
	}

	/**
	 * Encode extension.
	 *
	 * @param extension the extension
	 * @return the extension data
	 * @throws EncodingFormatException the encoding format exception
	 */
	private ExtensionData encodeExtension(IExtension extension) throws EncodingFormatException {
		
		if (extension==null) return null;
		
		if (extension.getBinarydata() == null || extension.getBinarydata().length == 0) {
			throw new EncodingFormatException("Extension does not include data");
		}

		if (extension.getId() == null || extension.getId().length() == 0) {
			throw new EncodingFormatException("Extension does not include id");
		}
		
		ExtensionData asnExtension = new ExtensionData();
		
		asnExtension.setExtensionData(extension.getBinarydata());
		asnExtension.setExtensionId(UicEncoderUtils.getIA5(extension.getId()));

		return asnExtension;
	}	
	
	private TravelClassType convertTravelClass(ITravelClassType apiClass){
		
		if (apiClass == null) return null;

		if (apiClass == ITravelClassType.premiumFirst || apiClass == ITravelClassType.standardFirst){
			return TravelClassType.first;
		}
		
		if (apiClass == ITravelClassType.premiumSecond || apiClass == ITravelClassType.standardSecond){
			return TravelClassType.second;
		}
		
		
		
		
		
		return TravelClassType.valueOf(apiClass.name());
	
		
	}
	

}
