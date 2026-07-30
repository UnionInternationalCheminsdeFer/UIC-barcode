/*
 * 
 */
package org.uic.barcode.ticket.api.utils;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringUTF8;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfUnrestrictedLong;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.asn.omv3.BerthDetailData;
import org.uic.barcode.ticket.api.asn.omv3.BerthTypeType;
import org.uic.barcode.ticket.api.asn.omv3.BoardingOrArrivalType;
import org.uic.barcode.ticket.api.asn.omv3.CarCarriageReservationData;
import org.uic.barcode.ticket.api.asn.omv3.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.CodeTableType;
import org.uic.barcode.ticket.api.asn.omv3.CompartmentDetailsType;
import org.uic.barcode.ticket.api.asn.omv3.CompartmentGenderType;
import org.uic.barcode.ticket.api.asn.omv3.CompartmentPositionType;
import org.uic.barcode.ticket.api.asn.omv3.ConfirmationTypeType;
import org.uic.barcode.ticket.api.asn.omv3.ControlData;
import org.uic.barcode.ticket.api.asn.omv3.CountermarkData;
import org.uic.barcode.ticket.api.asn.omv3.CustomerCardData;
import org.uic.barcode.ticket.api.asn.omv3.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.DelayConfirmation;
import org.uic.barcode.ticket.api.asn.omv3.DeltaCoordinates;
import org.uic.barcode.ticket.api.asn.omv3.DocumentData;
import org.uic.barcode.ticket.api.asn.omv3.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.FIPTicketData;
import org.uic.barcode.ticket.api.asn.omv3.GenderType;
import org.uic.barcode.ticket.api.asn.omv3.GeoCoordinateSystemType;
import org.uic.barcode.ticket.api.asn.omv3.GeoCoordinateType;
import org.uic.barcode.ticket.api.asn.omv3.GeoUnitType;
import org.uic.barcode.ticket.api.asn.omv3.HemisphereLatitudeType;
import org.uic.barcode.ticket.api.asn.omv3.HemisphereLongitudeType;
import org.uic.barcode.ticket.api.asn.omv3.IncludedOpenTicketType;
import org.uic.barcode.ticket.api.asn.omv3.IssuingData;
import org.uic.barcode.ticket.api.asn.omv3.LineType;
import org.uic.barcode.ticket.api.asn.omv3.LinkMode;
import org.uic.barcode.ticket.api.asn.omv3.LoadingDeckType;
import org.uic.barcode.ticket.api.asn.omv3.LuggageRestrictionType;
import org.uic.barcode.ticket.api.asn.omv3.OpenTicketData;
import org.uic.barcode.ticket.api.asn.omv3.ParkingGroundData;
import org.uic.barcode.ticket.api.asn.omv3.PassData;
import org.uic.barcode.ticket.api.asn.omv3.PassengerType;
import org.uic.barcode.ticket.api.asn.omv3.PlacesType;
import org.uic.barcode.ticket.api.asn.omv3.PolygoneType;
import org.uic.barcode.ticket.api.asn.omv3.PriceTypeType;
import org.uic.barcode.ticket.api.asn.omv3.RegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv3.RegisteredLuggageType;
import org.uic.barcode.ticket.api.asn.omv3.ReservationData;
import org.uic.barcode.ticket.api.asn.omv3.ReturnRouteDescriptionType;
import org.uic.barcode.ticket.api.asn.omv3.RoofRackType;
import org.uic.barcode.ticket.api.asn.omv3.RouteSectionType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfBerthDetailData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCarrierNum;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCountries;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfDeltaCoordinates;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfIncludedOpenTicketType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfPlaceNum;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfRegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfRegisteredLuggageType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfServiceBrands;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTariffType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTicketLinkType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTimeRangeType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTransportTypes;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTravelerId;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfValidityPeriodType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfViaStationType;
import org.uic.barcode.ticket.api.asn.omv3.SeriesDetailType;
import org.uic.barcode.ticket.api.asn.omv3.ServiceType;
import org.uic.barcode.ticket.api.asn.omv3.StationPassageData;
import org.uic.barcode.ticket.api.asn.omv3.TariffType;
import org.uic.barcode.ticket.api.asn.omv3.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv3.TicketLinkType;
import org.uic.barcode.ticket.api.asn.omv3.TicketType;
import org.uic.barcode.ticket.api.asn.omv3.TimeRangeType;
import org.uic.barcode.ticket.api.asn.omv3.TokenType;
import org.uic.barcode.ticket.api.asn.omv3.TrainLinkType;
import org.uic.barcode.ticket.api.asn.omv3.TrainValidityType;
import org.uic.barcode.ticket.api.asn.omv3.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv3.TravelerData;
import org.uic.barcode.ticket.api.asn.omv3.TravelerType;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.asn.omv3.ValidityPeriodDetailType;
import org.uic.barcode.ticket.api.asn.omv3.ValidityPeriodType;
import org.uic.barcode.ticket.api.asn.omv3.VatDetailType;
import org.uic.barcode.ticket.api.asn.omv3.ViaStationType;
import org.uic.barcode.ticket.api.asn.omv3.VoucherData;
import org.uic.barcode.ticket.api.asn.omv3.ZoneType;
import org.uic.barcode.ticket.api.spec.IBerth;
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
import org.uic.barcode.ticket.api.spec.ITrainValidity;
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
public class Api2OpenAsnEncoderV3 implements Api2AsnEncoder {
	
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
		
			
		if (uicTicket.getControlDetailsOrNull() != null) {	
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
		
		
		if (uicTicket.getTravelerDetailsOrNull() != null) {
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

        IDWrapper t = new IDWrapper(document.getTrainInt());
		asnData.setTrainIA5(t.getString());
		asnData.setTrainNum(Asn1BigInteger.toAsn1(t.getNumber()));

        IDWrapper r = new IDWrapper(document.getReferenceInt());
		asnData.setReferenceNum(r.getNumber());
		asnData.setReferenceIA5(r.getString());
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}
        IDWrapper s = new IDWrapper(document.getStationInt());
		asnData.setStationIA5(s.getString());
		asnData.setStationNum(s.getNumber());
		
		
		asnData.setPlannedArrivalDate(document.getArrivalDate());	
		asnData.setDepartureUTCOffset(document.getArrivalUTCoffset());
		
		asnData.setAffectedTickets(encodeTicketLickList(document.getLinkedTickets()));
		
		asnData.setConfirmationType(ConfirmationTypeType.values()[document.getConfirmationType()]);
		
		asnData.setDelay((long) document.getDelay());
		
		asnData.setTrainCancelled(document.isTrainCancelled());
		
		
		asnData.setInfoText(document.getInfoText());
		
		
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
	private DocumentData convertCarCarriageReservation(ICarCarriageReservation document, Date issuingDate) throws EncodingFormatException {
		
		DocumentData asnDocument = new DocumentData();
		TicketDetailData asnTicket = new TicketDetailData();		
		asnDocument.setTicket(asnTicket);			
		
		CarCarriageReservationData asnData = new CarCarriageReservationData();
		asnDocument.getTicket().setCarCarriageReservation(asnData);

        IDWrapper t = new IDWrapper(document.getTrainInt());
        asnData.setTrainIA5(t.getString());
        asnData.setTrainNum(t.getNumber());
				
		IDWrapper wn = new IDWrapper(document.getProductOwnerInt(),1,32000);
		asnData.setProductOwnerNum(wn.getNumber());
		asnData.setProductOwnerIA5(wn.getString());
		wn = new IDWrapper(document.getProductIdInt(),0,65535);
		asnData.setProductIdNum(wn.getNumber());
		asnData.setProductIdIA5(wn.getString());

        IDWrapper r = new IDWrapper(document.getReferenceInt());
        asnData.setReferenceNum(r.getNumber());
        asnData.setReferenceIA5(r.getString());

        if (document.getStationCodeTable() != IStationCodeTable.stationUICReservation && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}		
		
		IDListWrapper w = new IDListWrapper(document.getCarriersInt(),1,32000);
		asnData.setCarrierNum(SequenceOfCarrierNum.getSequence(w.getNumList()));
		asnData.setCarrierIA5(w.getStringList());	
		
		asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		asnData.setCompartmentDetails(encodeCompartmentDetails(document.getCompartmentDetails()));
		
		
		if (document.getPriceType()!= IPriceTypeType.travelPrice && document.getPriceType() != null){
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

        IDWrapper fs = new IDWrapper(document.getFromStationInt());
		asnData.setFromStationIA5(fs.getString());
		asnData.setFromStationNum(fs.getNumber());

        IDWrapper ts = new IDWrapper(document.getToStationInt());
		asnData.setToStationIA5(ts.getString());
		asnData.setToStationNum(ts.getNumber());
		
		asnData.setFromStationNameUTF8(document.getFromStationName());
		asnData.setToStationNameUTF8(document.getToStationName());				

		
		asnData.setAttachedBicycles(UicEncoderUtils.getRestrictedInt(document.getAttachedBicycles(),0,4));
		asnData.setAttachedBoats(UicEncoderUtils.getRestrictedInt(document.getAttachedBoats(),0,2));
		asnData.setAttachedSurfboards(UicEncoderUtils.getRestrictedInt(document.getAttachedSurfboards(),0,5));
		
		if (document.getLoadingDeck() != ILoadingDeckType.upper && document.getLoadingDeck() != null){
			asnData.setLoadingDeck(LoadingDeckType.valueOf(document.getLoadingDeck().name()));
		}
				
		asnData.setLoadingListEntry(UicEncoderUtils.getRestrictedInt(document.getLoadingListEntry(),0,999));	
		asnData.setBoatCategory(UicEncoderUtils.getRestrictedInt(document.getBoatCategory(),0,6));
		asnData.setCarCategory(UicEncoderUtils.getRestrictedInt(document.getCarCategory(),0,9));
		asnData.setRoofRackHeight(UicEncoderUtils.getRestrictedInt(document.getRoofRackHeight(),0,99));

		asnData.setCoach(UicEncoderUtils.getIA5(document.getCoach()));

		
		if (document.getRoofRackType()!= IRoofRackType.norack && document.getRoofRackType() != null) {
			asnData.setRoofRackType(RoofRackType.valueOf(document.getRoofRackType().name()));
		}
		
		if (document.getTariff() != null) {
			asnData.setTariff(encodeTariff(document.getTariff()));
		}
		asnData.setNumberPlate(UicEncoderUtils.getIA5(document.getNumberPlate()));
		asnData.setPlace(UicEncoderUtils.getIA5(document.getPlace()));

        asnData.setTextileRoof(document.isTextileRoof());
		
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

		IDListWrapper w = new IDListWrapper(places.getPlacesInt(),1,254);
		asnData.setPlaceNum(SequenceOfPlaceNum.getSequence(w.getNumList()));
		asnData.setPlaceIA5(w.getStringList());	

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

		asnData.setPosition(convert(compartmentDetails.getPosition()));
		return asnData;
	}	
	
	private CompartmentPositionType convert(ICompartmentPositionType position) {
		if (position == null) {
			return null;
		} else if (position.equals(ICompartmentPositionType.lowerLevel)) {
			return CompartmentPositionType.lowerLevel;
		} else if (position.equals(ICompartmentPositionType.upperLevel)) {
			return CompartmentPositionType.upperLevel;
		} else if (position.equals(ICompartmentPositionType.unspecified)) {
			return CompartmentPositionType.unspecified;
		}
		return null;
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
		
		IDWrapper wn = new IDWrapper(document.getProductOwnerInt(),1,32000);
		asnData.setProductOwnerNum(wn.getNumber());
		asnData.setProductOwnerIA5(wn.getString());
		wn = new IDWrapper(document.getProductIdInt(),0,65535);
		asnData.setProductIdNum(wn.getNumber());
		asnData.setProductIdIA5(wn.getString());

        IDWrapper r = new IDWrapper(document.getReferenceInt());
		asnData.setReferenceNum(r.getNumber());
		asnData.setReferenceIA5(r.getString());
		
		asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		IDListWrapper w = new IDListWrapper(document.getCarriersInt(),1,32000);
		asnData.setCarrierNum(SequenceOfCarrierNum.getSequence(w.getNumList()));
		asnData.setCarrierIA5(w.getStringList());	
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUICReservation && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}

        IDWrapper fs = new IDWrapper(document.getFromStationInt());
        asnData.setFromStationIA5(fs.getString());
        asnData.setFromStationNum(fs.getNumber());

        IDWrapper ts = new IDWrapper(document.getToStationInt());
        asnData.setToStationIA5(ts.getString());
        asnData.setToStationNum(ts.getNumber());
		
		asnData.setFromStationNameUTF8(document.getFromStationName());
		asnData.setToStationNameUTF8(document.getToStationName());

        IDWrapper t = new IDWrapper(document.getTrainInt());
        asnData.setTrainIA5(t.getString());
		asnData.setTrainNum(t.getNumber());
		
		asnData.setTariff(encodeTariffCollection(document.getTariffs()));		
		
		asnData.setDepartureArrivalDates(document.getDepartureDate(),document.getArrivalDate(), issuingDate);
		
		if (document.getDepartureUTCoffset() != null) {
			asnData.setDepartureUTCOffset(document.getDepartureUTCoffset());
			if (document.getArrivalUTCoffset() != null && !Objects.equals(document.getArrivalUTCoffset(), document.getDepartureUTCoffset())){
				asnData.setArrivalUTCOffset(document.getArrivalUTCoffset());
			}
		}
		
		
		
		if (document.getClassCode() != ITravelClassType.second && document.getClassCode() != null){
			asnData.setClassCode(TravelClassType.valueOf(document.getClassCode().name()));
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
		
		if (document.getAdditionalPlaces() != null) {
			asnData.setAdditionalPlaces(encodePlaces(document.getAdditionalPlaces()));
		}
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
	 * @param classCode the class code
	 * @return the list
	 * @throws EncodingFormatException the encoding format exception
	 */
	private SequenceOfIncludedOpenTicketType encodeIncludedAddons(Collection<IIncludedOpenTicket> tickets, Date issuingDate, ITravelClassType classCode) throws EncodingFormatException {
		
		if (tickets == null || tickets.isEmpty()) {
			return null;
		}
		SequenceOfIncludedOpenTicketType asnList = new SequenceOfIncludedOpenTicketType();
		
		for ( IIncludedOpenTicket ticket : tickets){
			
			IncludedOpenTicketType asnTicket = encodeIncludedOpenTicket(ticket,issuingDate,classCode);
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
		
		IDWrapper wn = new IDWrapper(document.getProductOwnerInt(),1,32000);
		asnData.setProductOwnerNum(wn.getNumber());
		asnData.setProductOwnerIA5(wn.getString());
		wn = new IDWrapper(document.getProductIdInt(),0,65535);
		asnData.setProductIdNum(wn.getNumber());
		asnData.setProductIdIA5(wn.getString());
		
		asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}		
				
		IDListWrapper w = new IDListWrapper(document.getIncludedCarriersInt(),1,32000);
		asnData.setIncludedCarriersNum(SequenceOfCarrierNum.getSequence(w.getNumList()));
		asnData.setIncludedCarriersIA5(w.getStringList());	
		
		asnData.setExcludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getExcludedServiceBrands(),1,32000)));		
		asnData.setIncludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getIncludedServiceBrands(),1,32000)));	

		asnData.setExcludedTransportTypes(SequenceOfTransportTypes.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getExcludedTransportTypes(),0,31)));		
		asnData.setIncludedTransportTypes(SequenceOfTransportTypes.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getIncludedTransportTypes(),0,31)));	

		
		asnData.setValidityDates(document.getValidFrom(), document.getValidUntil(), issuingDate);
		
		if (document.getValidFromUTCoffset() != null) {
			asnData.setValidFromUTCOffset(document.getValidFromUTCoffset());
			if (document.getValidUntilUTCoffset() != null && !Objects.equals(document.getValidUntilUTCoffset(), document.getValidFromUTCoffset())){
				asnData.setValidUntilUTCOffset(document.getValidUntilUTCoffset());
			}
		}

	
		if (document.getTariffs() != null && !document.getTariffs().isEmpty()){
			asnData.setTariffs(encodeTariffCollection(document.getTariffs()));
		}
		
		if (document.getClassCode() != classCode && document.getClassCode() != null){
			asnData.setClassCode(TravelClassType.valueOf(document.getClassCode().name()));
		}
		
		if (document.getServiceLevel() != null && !document.getServiceLevel().isEmpty()) {
			asnData.setServiceLevel(document.getServiceLevel());
		}
		
		if (document.getExternalIssuer() > 0) {
			asnData.setExternalIssuerId((long) document.getExternalIssuer());
		}
		
		if (document.getAuthorizationCode() > 0)  {
			asnData.setIssuerAutorizationId((long) document.getAuthorizationCode());
		}

		if (document.getValidRegionList()!= null && !document.getValidRegionList().isEmpty()) {
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

        IDWrapper fs = new IDWrapper(route.getFromStationInt());
		asnData.setFromStationIA5(fs.getString());
		asnData.setFromStationNum(fs.getNumber());

        IDWrapper ts = new IDWrapper(route.getToStationInt());
		asnData.setToStationIA5(ts.getString());
		asnData.setToStationNum(ts.getNumber());
		
		asnData.setFromStationNameUTF8(route.getFromStationName());
		asnData.setToStationNameUTF8(route.getToStationName());		
		
		if (route.getValidRegionList()!= null && !route.getValidRegionList().isEmpty()) {
			asnData.setValidReturnRegion(encodeRegionCollection(route.getValidRegionList(), issuingDate));
		}
		
		if (route.getValidRegionDesc() != null && !route.getValidRegionDesc().isEmpty()){
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
				
		if (luggageRestriction.getRegisteredLuggage() != null && !luggageRestriction.getRegisteredLuggage().isEmpty()) {
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
		
		IDWrapper wn = new IDWrapper(document.getProductOwnerInt(),1,32000);
		asnData.setProductOwnerNum(wn.getNumber());
		asnData.setProductOwnerIA5(wn.getString());
		wn = new IDWrapper(document.getProductIdInt(),0,65535);
		asnData.setProductIdNum(wn.getNumber());
		asnData.setProductIdIA5(wn.getString());

        IDWrapper r = new IDWrapper(document.getReferenceInt());
		asnData.setReferenceNum(r.getNumber());
		asnData.setReferenceIA5(r.getString());
		
		asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}

        IDWrapper fs = new IDWrapper(document.getFromStationInt());
        asnData.setFromStationIA5(fs.getString());
        asnData.setFromStationNum(fs.getNumber());

        IDWrapper ts = new IDWrapper(document.getToStationInt());
        asnData.setToStationIA5(ts.getString());
        asnData.setToStationNum(ts.getNumber());
		
		asnData.setFromStationNameUTF8(document.getFromStationName());
		asnData.setToStationNameUTF8(document.getToStationName());		
		
		IDListWrapper w = new IDListWrapper(document.getIncludedCarriersInt(),1,32000);
		asnData.setCarriersNum(SequenceOfCarrierNum.getSequence(w.getNumList()));
		asnData.setCarriersIA5(w.getStringList());	
		
		asnData.setExcludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getExcludedServiceBrands(),1,32000)));		
		asnData.setIncludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getIncludedServiceBrands(),1,32000)));	
		
		asnData.setExcludedTransportTypes(SequenceOfTransportTypes.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getExcludedTransportTypes(),0,31)));		
		asnData.setIncludedTransportTypes(SequenceOfTransportTypes.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getIncludedTransportTypes(),0,31)));	
		
		
		asnData.setValidityDates(document.getValidFrom(), document.getValidUntil(), issuingDate);
		
		if (document.getValidFromUTCoffset() != null) {
			asnData.setValidFromUTCOffset(document.getValidFromUTCoffset());
			if (document.getValidUntilUTCoffset() != null && !Objects.equals(document.getValidUntilUTCoffset(), document.getValidFromUTCoffset())){
				asnData.setValidUntilUTCOffset(document.getValidUntilUTCoffset());
			}
		}

			
		asnData.setTariffs(encodeTariffCollection(document.getTariffs()));
		
		if (document.getActivatedDays() != null && !document.getActivatedDays().isEmpty()) {
			asnData.addActivatedDays(DateTimeUtils.getActivatedDays(document.getValidFrom(), document.getActivatedDays()));
		}
		if (document.getClassCode() != ITravelClassType.second && document.getClassCode() != null){
			asnData.setClassCode(TravelClassType.valueOf(document.getClassCode().name()));
		}
		
		if (document.getServiceLevel() != null && !document.getServiceLevel().isEmpty()) {
			asnData.setServiceLevel(document.getServiceLevel());
		}
		
		if (document.getExternalIssuer()>0) {
			asnData.setExtIssuerId((long) document.getExternalIssuer());
		}
		
		if (document.getAuthorizationCode()>0)  {
			asnData.setIssuerAutorizationId((long) document.getAuthorizationCode());
		}

		if (document.getValidRegionList() != null && !document.getValidRegionList().isEmpty()) {
			asnData.setValidRegion(encodeRegionCollection(document.getValidRegionList(), issuingDate));
		}		
		asnData.setValidRegionDesc(document.getValidRegionDesc());
		
		if (document.getIncludedAddOns() != null && !document.getIncludedAddOns().isEmpty()) {
			asnData.setIncludedAddOns(encodeIncludedAddons(document.getIncludedAddOns(),issuingDate, document.getClassCode()));
		}
		
		if (document.getLuggageRestriction() != null) {
			asnData.setLuggage(encodeLuggage(document.getLuggageRestriction()));
		}
		
		if (document.getReturnDescription() != null) {
			asnData.setReturnDescription(encodeReturnDescription(document.getReturnDescription(),issuingDate));			
		}

        asnData.setReturnIncluded(document.isReturnIncluded());
		
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
		
		IDWrapper wn = new IDWrapper(document.getProductOwnerInt(),1,32000);
		asnData.setProductOwnerNum(wn.getNumber());
		asnData.setProductOwnerIA5(wn.getString());
		wn = new IDWrapper(document.getProductIdInt(),0,65535);
		asnData.setProductIdNum(wn.getNumber());
		asnData.setProductIdIA5(wn.getString());

        IDWrapper r = new IDWrapper(document.getReferenceInt());
        asnData.setReferenceNum(r.getNumber());
        asnData.setReferenceIA5(r.getString());

        IDWrapper tr = new IDWrapper(document.getTicketReferenceInt());
        asnData.setTicketReferenceNum(tr.getNumber());
        asnData.setTicketReferenceIA5(tr.getString());
		
		asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}

        IDWrapper fs = new IDWrapper(document.getFromStationInt());
        asnData.setFromStationIA5(fs.getString());
        asnData.setFromStationNum(fs.getNumber());

        IDWrapper ts = new IDWrapper(document.getToStationInt());
        asnData.setToStationIA5(ts.getString());
        asnData.setToStationNum(ts.getNumber());
		
		asnData.setFromStationNameUTF8(document.getFromStationName());
		asnData.setToStationNameUTF8(document.getToStationName());		
				
		asnData.setValidityDates(document.getValidFrom(), document.getValidUntil(), issuingDate);
		
		if (document.getValidFromUTCoffset() != null) {
			asnData.setValidFromUTCOffset(document.getValidFromUTCoffset());
			if (document.getValidUntilUTCoffset() != null && !Objects.equals(document.getValidUntilUTCoffset(), document.getValidFromUTCoffset())){
				asnData.setValidUntilUTCOffset(document.getValidUntilUTCoffset());
			}
		}

		asnData.setGroupName(document.getGroupName());
		asnData.setNumberOfCountermark((long) document.getNumberOfCountermark());
		asnData.setTotalOfCountermarks((long) document.getTotalOfCountermarks());
		
		IDListWrapper w = new IDListWrapper(document.getIncludedCarriersInt(),1,32000);
		asnData.setCarriersNum(SequenceOfCarrierNum.getSequence(w.getNumList()));
		asnData.setCarriersIA5(w.getStringList());			
	
		asnData.setExcludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getExcludedServiceBrands(),1,32000)));		
		asnData.setIncludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getIncludedServiceBrands(),1,32000)));			
				
		if (document.getValidRegionList() != null && !document.getValidRegionList().isEmpty()) {
			asnData.setValidRegion(encodeRegionCollection(document.getValidRegionList(), issuingDate));
		}
		asnData.setValidRegionDesc(document.getValidRegionDesc());
		
		if (document.getClassCode() != ITravelClassType.second && document.getClassCode() != null){
			asnData.setClassCode(TravelClassType.valueOf(document.getClassCode().name()));
		}

		if (document.getReturnDescription()!= null) {
			asnData.setReturnDescription(encodeReturnDescription(document.getReturnDescription(),issuingDate));
		}
        asnData.setReturnIncluded(document.isReturnIncluded());
		
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

        IDWrapper s = new IDWrapper(document.getStationInt());
        asnData.setStationIA5(s.getString());
        asnData.setStationNum(s.getNumber());
		
		asnData.setAlternativeRoutes(encodeViaStationCollection(document.getAlternativeRoutes()));

        asnData.setBorder(document.isBorder());

		IDListWrapper w = new IDListWrapper(document.getCarriersInt(),1,32000);
		asnData.setCarriersNum(SequenceOfCarrierNum.getSequence(w.getNumList()));
		asnData.setCarriersIA5(w.getStringList());	
		
		asnData.setExcludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getExcludedServiceBrands(),1,32000)));
		asnData.setIncludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getIncludedServiceBrands(),1,32000)));	
	
		asnData.setRoute(encodeViaStationCollection(document.getRoute()));
		
		if (document.getRouteId() > 0){
			asnData.setRouteId((long) document.getRouteId());
		}
		
		if (document.getSeriesId() > 0) {
			asnData.setSeriesId((long) document.getSeriesId());
		}
		
		return asnData;
	}
	
	/**
	 * Encode zone.
	 *
	 * @param document the data
	 * @return the zone type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private ZoneType encodeZone(IZone document) throws EncodingFormatException {
		if (document == null) return null;
		
		ZoneType asnData =new ZoneType();
		
		if (document.getBinaryZoneId() != null && document.getBinaryZoneId().length > 0) {
			asnData.setBinaryZoneId(document.getBinaryZoneId());
		}
        IDWrapper c = new IDWrapper(document.getCarrierInt());
		asnData.setCarrierNum(c.getNumber());
		asnData.setCarrierIA5(c.getString());
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}
        IDWrapper es = new IDWrapper(document.getEntryStationInt());
		asnData.setEntryStationIA5(es.getString());
		asnData.setEntryStationNum(es.getNumber());

        IDWrapper ts = new IDWrapper(document.getTerminatingStationInt());
		asnData.setTerminatingStationIA5(ts.getString());
		asnData.setTerminatingStationNum(ts.getNumber());
		
		asnData.setZoneId(SequenceOfUnrestrictedLong.getSequence(UicEncoderUtils.encodeIntegerCollection(document.getZoneIds())));
		
		if (document.getCity() > 0) {
			asnData.setCity(Asn1BigInteger.toAsn1(document.getCity()));
		}
		
		if (document.getNUTScode() != null && !document.getNUTScode().isEmpty()) {
			asnData.setNutsCode(document.getNUTScode());			
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

        IDWrapper fs = new IDWrapper(data.getFromStationInt());
		asnData.setFromStationIA5(fs.getString());
		asnData.setFromStationNum(fs.getNumber());

        IDWrapper ts = new IDWrapper(data.getToStationInt());
		asnData.setToStationIA5(ts.getString());
		asnData.setToStationNum(ts.getNumber());
		
		asnData.setFromStationName(data.getFromStationName());
		asnData.setToStationName(data.getToStationName());	

        IDWrapper t = new IDWrapper(data.getTrainInt());
		asnData.setTrainIA5(t.getString());
		asnData.setTrainNum(t.getNumber());
		
		asnData.setDepartureDate(data.getDepartureDateTime(), issuingDate);
		
		return asnData;
	}	
	
	/**
	 * Encode polygone.
	 *
	 * @param data the data
	 * @return the polygone type
     */
	private PolygoneType encodePolygone(IPolygone data) {
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
				delta.setLatitude(Asn1BigInteger.toAsn1(edge.getLatitude() - asnData.getFirstEdge().getLatitude()));
				delta.setLongitude(Asn1BigInteger.toAsn1(edge.getLongitude() - asnData.getFirstEdge().getLongitude()));				
                asnList.add(delta);
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
	 * @param document the data
	 * @return the line type
	 * @throws EncodingFormatException the encoding format exception
	 */
	private LineType encodeLine(ILine document) throws EncodingFormatException {
		if (document == null) return null;
		LineType asnData =new LineType();

        IDWrapper c = new IDWrapper(document.getCarrierInt());
		asnData.setCarrierNum(c.getNumber());
		asnData.setCarrierIA5(c.getString());
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}
        IDWrapper es = new IDWrapper(document.getEntryStationInt());
		asnData.setEntryStationIA5(es.getString());
		asnData.setEntryStationNum(es.getNumber());

        IDWrapper ts = new IDWrapper(document.getTerminatingStationInt());
		asnData.setTerminatingStationIA5(ts.getString());
		asnData.setTerminatingStationNum(ts.getNumber());
		
		if (document.getCity() > 0) {
			asnData.setCity((long) document.getCity());
		}
		
		asnData.setLineId(SequenceOfUnrestrictedLong.getSequence(UicEncoderUtils.encodeIntegerCollection(document.getLineIds())));
		
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
		
		IDWrapper wn = new IDWrapper(document.getProductOwnerInt(),1,32000);
		asnData.setProductOwnerNum(wn.getNumber());
		asnData.setProductOwnerIA5(wn.getString());
		wn = new IDWrapper(document.getProductIdInt(),0,65535);
		asnData.setProductIdNum(wn.getNumber());
		asnData.setProductIdIA5(wn.getString());

        IDWrapper r = new IDWrapper(document.getReferenceInt());
        asnData.setReferenceNum(Asn1BigInteger.toAsn1(r.getNumber()));
        asnData.setReferenceIA5(r.getString());

		asnData.setExtension(encodeExtension(document.getExtension()));
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}

        IDWrapper s = new IDWrapper(document.getStationInt());
        asnData.setStationIA5(s.getString());
        asnData.setStationNum(s.getNumber());
		
		asnData.setAccessCode(UicEncoderUtils.getIA5(document.getAccessCode()));
		asnData.setEntryTrack(document.getEntryTrack());
		
		if (document.getFromParkingDate() != null){
			asnData.setParkingDates(document.getFromParkingDate(),document.getToParkingDate(),issuingDate);
		}
		
		asnData.setLocation(document.getLocation());
		asnData.setNumberPlate(UicEncoderUtils.getIA5(document.getNumberPlate()));
		asnData.setParkingGroundId(document.getParkingGroundId().toString());
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

        IDWrapper fs = new IDWrapper(document.getFromStationInt());
		asnRoute.setFromStationIA5(fs.getString());
		asnRoute.setFromStationNum(fs.getNumber());

        IDWrapper ts = new IDWrapper(document.getToStationInt());
		asnRoute.setToStationIA5(ts.getString());
		asnRoute.setToStationNum(ts.getNumber());
		
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
			
		asnTariff.setAgeAbove(UicEncoderUtils.getRestrictedInt(tariff.getAgeAbove(),1,128));
		asnTariff.setAgeBelow(UicEncoderUtils.getRestrictedInt(tariff.getAgeBelow(),1,64));

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
        IDWrapper ti = new IDWrapper(tariff.getTariffIdInt());
		asnTariff.setTariffIdIA5(ti.getString());
		asnTariff.setTariffIdNum(ti.getNumber());
		
		asnTariff.setTraverlerid(SequenceOfTravelerId.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(tariff.getTravelerIds(), 1, 254)));				

		return asnTariff;
	}	
	
	private SeriesDetailType encodeSeriesDataDetails(	ISeriesDataDetails seriesDataDetails) throws EncodingFormatException {		
		SeriesDetailType details = new SeriesDetailType();
		details.setSeries(UicEncoderUtils.getRestrictedInt(seriesDataDetails.getSeries(), 1, 99999));
		details.setSupplyingCarrier(UicEncoderUtils.getRestrictedInt(seriesDataDetails.getSupplyingCarrier(), 1, 32000));
		details.setOfferIdentification(UicEncoderUtils.getRestrictedInt(seriesDataDetails.getOfferIdentification(), 1, 99));
		return details;
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
		
		IDWrapper wn = new IDWrapper(document.getProductOwnerInt(),1,32000);
		asnData.setProductOwnerNum(wn.getNumber());
		asnData.setProductOwnerIA5(wn.getString());
		wn = new IDWrapper(document.getProductIdInt(),0,65535);
		asnData.setProductIdNum(wn.getNumber());
		asnData.setProductIdIA5(wn.getString());

        IDWrapper r = new IDWrapper(document.getReferenceInt());
        asnData.setReferenceNum(Asn1BigInteger.toAsn1(r.getNumber()));
        asnData.setReferenceIA5(r.getString());

        asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));
		
		asnData.setValidity(document.getValidFrom(), document.getValidUntil());

		if (document.getType() != null) {
			asnData.setType(UicEncoderUtils.getRestrictedInt(document.getType(), 1,32000));
		}
		
		if (document.getAmount() != null) {
			asnData.setValue(UicEncoderUtils.getRestrictedInt(document.getAmount(),1,9999999));
		}
		
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
		
		IDWrapper wn = new IDWrapper(document.getProductOwnerInt(),1,32000);
		asnData.setProductOwnerNum(wn.getNumber());
		asnData.setProductOwnerIA5(wn.getString());
		wn = new IDWrapper(document.getProductIdInt(),0,65535);
		asnData.setProductIdNum(wn.getNumber());
		asnData.setProductIdIA5(wn.getString());

        IDWrapper r = new IDWrapper(document.getReferenceInt());
        asnData.setReferenceNum(Asn1BigInteger.toAsn1(r.getNumber()));
        asnData.setReferenceIA5(r.getString());
		
		asnData.setInfoText(document.getInfoText());
		asnData.setExtension(encodeExtension(document.getExtension()));		
		
		IDListWrapper w = new IDListWrapper(document.getIncludedCarriersInt(),1,32000);
		asnData.setIncludedCarriersNum(SequenceOfCarrierNum.getSequence(w.getNumList()));
		asnData.setIncludedCarriersIA5(w.getStringList());	
		
		w = new IDListWrapper(document.getExcludedCarriersInt(),1,32000);
		asnData.setExcludedCarriersNum(SequenceOfCarrierNum.getSequence(w.getNumList()));
		asnData.setExcludedCarriersIA5(w.getStringList());			

		asnData.setValidityDates(document.getValidFrom(), document.getValidUntil(), issuingDate);
		
		if (document.getValidFromUTCoffset() != null) {
			asnData.setValidFromUTCOffset(document.getValidFromUTCoffset());
			if (document.getValidUntilUTCoffset() != null && !Objects.equals(document.getValidUntilUTCoffset(), document.getValidFromUTCoffset())){
				asnData.setValidUntilUTCOffset(document.getValidUntilUTCoffset());
			}
		}

		if (document.getActivatedDays() != null && !document.getActivatedDays().isEmpty()) {
			asnData.addActivatedDays(DateTimeUtils.getActivatedDays(document.getValidFrom(), document.getActivatedDays()));
		}
		if (document.getClassCode() != null) {
			asnData.setClassCode(TravelClassType.valueOf(document.getClassCode().name()));
		}
		if (document.getCountries() != null && !document.getCountries().isEmpty()){
			asnData.setCountries(SequenceOfCountries.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getCountries(),1,250)));
		}

		asnData.setExcludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getExcludedServiceBrands(),1,32000)));
		asnData.setIncludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getIncludedServiceBrands(),1,32000)));	
		
		asnData.setNumberOfDaysOfTravel(UicEncoderUtils.getRestrictedInt(document.getNumberOfDaysOfTravel(), 1, 250));
		asnData.setNumberOfPossibleTrips(UicEncoderUtils.getRestrictedInt(document.getNumberOfPossibleTrips(), 1, 250));
		asnData.setNumberOfValidityDays(UicEncoderUtils.getRestrictedInt(document.getNumberOfValidityDays(), 1, 500));
		asnData.setPassDescription(document.getPassDescription());
		if (document.getPassType() > 0 ){
			asnData.setPassType((long) document.getPassType());
		}
		
		asnData.setTariffs(encodeTariffCollection(document.getTariffs()));
		

		
		if(document.getValidRegionList()!= null && !document.getValidRegionList().isEmpty()) {
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
		
		if (document.getTrainValidity() != null) {
			asnData.setTrainValidity(convert(document.getTrainValidity(), issuingDate));
		}

		return asnDocument;
	}	
	
	
	private TrainValidityType convert(ITrainValidity document, Date issuingDate) throws EncodingFormatException {
		
		TrainValidityType asnData = new TrainValidityType();
		
		
		if (document.getValidFromUTCoffset() != null) {
			asnData.setValidFromUTCOffset(document.getValidFromUTCoffset());
			if (document.getValidUntilUTCoffset() != null && !Objects.equals(document.getValidUntilUTCoffset(), document.getValidFromUTCoffset())){
				asnData.setValidUntilUTCOffset(document.getValidUntilUTCoffset());
			}
		}
		
		IDListWrapper w = new IDListWrapper(document.getIncludedCarriersInt(),1,32000);
		asnData.setIncludedCarriersNum(SequenceOfCarrierNum.getSequence(w.getNumList()));
		asnData.setIncludedCarriersIA5(w.getStringList());		

		w = new IDListWrapper(document.getExcludedCarriersInt(),1,32000);
		asnData.setExcludedCarriersNum(SequenceOfCarrierNum.getSequence(w.getNumList()));
		asnData.setExcludedCarriersIA5(w.getStringList());		

		asnData.setExcludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getExcludedServiceBrands(),1,32000)));
		asnData.setIncludedServiceBrands(SequenceOfServiceBrands.getSequence(UicEncoderUtils.encodeRestrictedIntegerCollection(document.getIncludedServiceBrands(),1,32000)));	

		asnData.setValidityDates(document.getFromDate(), document.getUntilDate(), issuingDate);
		
		if (document.getBoardingOrArrival() != null) {
			asnData.setBordingOrArrival(BoardingOrArrivalType.valueOf(document.getBoardingOrArrival().name()));
		}
		
		return asnData;
	}


    private ValidityPeriodDetailType encodeValidityDetails(IValidityDetails validityDetails, Date referenceDate) {
		
		if ( (validityDetails.getTimeRanges() == null || validityDetails.getTimeRanges().isEmpty())
				||
			 (validityDetails.getValidityRanges() == null || validityDetails.getValidityRanges().isEmpty()) ) {
					return null;
		}
		
		
		ValidityPeriodDetailType asnData = new ValidityPeriodDetailType();
		
		if (validityDetails.getTimeRanges() != null && !validityDetails.getTimeRanges().isEmpty()) {
			asnData.setExcludedTimeRange(new SequenceOfTimeRangeType());

			for (ITimeRange range : validityDetails.getTimeRanges()) {				
			
				if (range.getFromTime() == range.getUntilTime()){
					break;
				}
				
			    TimeRangeType asnRange = new TimeRangeType();
			    asnRange.setFromTime((long) range.getFromTime());
			    asnRange.setUntilTime((long) range.getUntilTime());
			    		    
				asnData.getExcludedTimeRange().add(asnRange);
			}
		}
		
		if (validityDetails.getValidityRanges() != null && !validityDetails.getValidityRanges().isEmpty()) {
			
			asnData.setValidityPeriod(new SequenceOfValidityPeriodType());
			
			for (IValidityRange range : validityDetails.getValidityRanges()) {
							
				ValidityPeriodType asnRange = new ValidityPeriodType();
				
				asnRange.setValidityDates(range.getFromDate(), range.getUntilDate(), referenceDate);
				
				if (range.getValidFromUTCoffset() != null) {
					asnRange.setValidFromUTCOffset(range.getValidFromUTCoffset());
					if (range.getValidUntilUTCoffset() != null && !Objects.equals(range.getValidUntilUTCoffset(), range.getValidFromUTCoffset())){
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
		
		IDWrapper wn = new IDWrapper(document.getProductOwnerInt(),1,32000);
		asnData.setProductOwnerNum(wn.getNumber());
		asnData.setProductOwnerIA5(wn.getString());
		wn = new IDWrapper(document.getProductIdInt(),0,65535);
		asnData.setProductIdNum(wn.getNumber());
		asnData.setProductIdIA5(wn.getString());

        IDWrapper r = new IDWrapper(document.getReferenceInt());
        asnData.setReferenceNum(Asn1BigInteger.toAsn1(r.getNumber()));
        asnData.setReferenceIA5(r.getString());
		
		IDListWrapper w = new IDListWrapper(document.getCarriersInt(),1,32000);
		asnData.setCarrierNum(SequenceOfCarrierNum.getSequence(w.getNumList()));
		asnData.setCarrierIA5(w.getStringList());	
		
		if (document.getClassCode() != null) {
			asnData.setClassCode(TravelClassType.valueOf(document.getClassCode().name()));
		}
		
		asnData.setIncludesSupplements(document.isIncludesSupplements());
		
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
		
		IDWrapper wn = new IDWrapper(document.getProductOwnerInt(),1,32000);
		asnData.setProductOwnerNum(wn.getNumber());
		asnData.setProductOwnerIA5(wn.getString());
		wn = new IDWrapper(document.getProductIdInt(),0,65535);
		asnData.setProductIdNum(wn.getNumber());
		asnData.setProductIdIA5(wn.getString());

        IDWrapper r = new IDWrapper(document.getReferenceInt());
        asnData.setReferenceNum(Asn1BigInteger.toAsn1(r.getNumber()));
        asnData.setReferenceIA5(r.getString());

		if (document.getNumberOfdaysAllowed() > 0) {
			asnData.setNumberOfDaysValid((long) document.getNumberOfdaysAllowed());
		}
		
		asnData.setProductName(document.getProductName());
		
		if (document.getStationCodeTable() != IStationCodeTable.stationUIC && document.getStationCodeTable() != null){
			asnData.setStationCodeTable(CodeTableType.valueOf(document.getStationCodeTable().name()));
		}
		
		IDListWrapper stations = new IDListWrapper(document.getStationsInt());
        asnData.setStationIA5(stations.getStringList());
        asnData.setStationNum(SequenceOfUnrestrictedLong.getSequence(stations.getNumList()));
		
		if (document.getStationNames()!= null && !document.getStationNames().isEmpty()) {
			SequenceOfStringUTF8 asnList = new SequenceOfStringUTF8();
			for ( String text  :document.getStationNames()){
				if (!text.isEmpty()) {
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
			if (document.getValidUntilUTCoffset() != null && !Objects.equals(document.getValidUntilUTCoffset(), document.getValidFromUTCoffset())){
				asnData.setValidUntilUTCOffset(document.getValidUntilUTCoffset());
			}
		}

				
		asnData.setExtension(encodeExtension(document.getExtension()));			

        IDListWrapper ac = new IDListWrapper(document.getAreaCodesInt());
		asnData.setAreaCodeNum(SequenceOfUnrestrictedLong.getSequence(ac.getNumList()));
		asnData.setAreaCodeIA5(ac.getStringList());

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

        asnData.setAgeCheckRequired(data.isAgeCheckRequired());
        asnData.setIdentificationByIdCard(data.isIdentificationByIdCard());
        asnData.setIdentificationByPassportId(data.isIdentificationByPassportId());
        asnData.setOnlineValidationRequired(data.isOnlineValidationRequired());
        asnData.setPassportValidationRequired(data.isPassportValidationRequired());
        asnData.setReductionCardCheckRequired(data.isReductionCardCheckRequired());
		
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

            IDWrapper i = new IDWrapper(card.getCardIdInt());
            asnCard.setCardIdIA5(i.getString());
            asnCard.setCardIdNum(i.getNumber());

            IDWrapper ci = new IDWrapper(card.getCardIssuerInt());
			asnCard.setCardIssuerNum(ci.getNumber());
			asnCard.setCardIssuerIA5(ci.getString());
			asnCard.setCardName(card.getCardName());
			asnCard.setCardType(UicEncoderUtils.getUnRestrictedInt(card.getCardType()));

            IDWrapper lci = new IDWrapper(card.getLeadingCardIdInt());
			asnCard.setLeadingCardIdNum(lci.getNumber());
			asnCard.setLeadingCardIdIA5(lci.getString());

            IDWrapper tci = new IDWrapper(card.getTrailingCardIdInt());
			asnCard.setTrailingCardIdNum(tci.getNumber());
			asnCard.setTrailingCardIdIA5(tci.getString());
			
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
		
		for (ITicketLink document : linkedTickets){
			
			TicketLinkType asnData = convert(document);
			
			if (asnData != null) {
				asnList.add(asnData);
			}
		}
		
		if (asnList.isEmpty()) return null;
		
		return asnList;
	}	
	
	private TicketLinkType convert(ITicketLink document) throws EncodingFormatException {
		
		if (document == null) return null;
		
		TicketLinkType asnData = new TicketLinkType();

        if (document.getIssuer() != null) {
            asnData.setIssuerName(document.getIssuer().toString());
        }

        asnData.setIssuerPNR(document.getIssuerPNR());
		
		IDWrapper wn = new IDWrapper(document.getProductOwnerInt(),1,65535);
		asnData.setProductOwnerNum(wn.getNumber());
		asnData.setProductOwnerIA5(wn.getString());

        IDWrapper r = new IDWrapper(document.getReferenceInt());
        asnData.setReferenceNum(r.getNumber());
        asnData.setReferenceIA5(r.getString());
		
		if (document.getTicketType() != ITicketType.openTicket && document.getTicketType() != null){
			asnData.setTicketType(TicketType.valueOf(document.getTicketType().name()));
		}
		
		if (document.getLinkMode() != ILinkMode.issuedTogether && document.getLinkMode() != null){
			asnData.setLinkMode(LinkMode.valueOf(document.getLinkMode().name()));
		}
		
		return asnData;
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
			throw new EncodingFormatException("Issuing data missing");
		}
		
		IssuingData asnData = new IssuingData();

        asnData.setActivated(data.isActivated());
        asnData.setSecurePaperTicket(data.isSecurePaperTicket());
		
		asnData.setExtension(encodeExtension(data.getExtension()));
		
		
		asnData.setIssuedOnLine(UicEncoderUtils.getRestrictedInt(data.getIssuedOnLine(), 1, 99999));

        IDWrapper iit = new IDWrapper(data.getIssuedOnTrainInt());
		asnData.setIssuedOnTrainNum(iit.getNumber());
		asnData.setIssuedOnTrainIA5(iit.getString());

		IDWrapper sp = new IDWrapper(data.getSecurityProviderInt());
		asnData.setSecurityProviderNum(sp.getNumber());
		asnData.setSecurityProviderIA5(sp.getString());

		if (data.getIssuer() != null && !data.getIssuer().equals(data.getSecurityProvider())) {
            IDWrapper i = new IDWrapper(data.getIssuerInt());
			asnData.setIssuerNum(i.getNumber());
			asnData.setIssuerIA5(i.getString());
		}
		
		asnData.setIssuerName(data.getIssuerName());
		asnData.setIssuerPNR(UicEncoderUtils.getIA5(data.getIssuerPNR()));

		asnData.setIssuingDate(data.getIssuingDate(),data.getTimeZoneId());

        asnData.setSpecimen(data.isSpecimen());
		
		if (data.getPointOfSale()!= null){
			asnData.setPointOfSale(encodeGeoCoordinate(data.getPointOfSale()));
		}
		
		asnData.setCurrency(data.getCurrency());

		if (data.getCurrencyFraction() != null) {
			asnData.setCurrencyFract(data.getCurrencyFraction().longValue());	
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
		
		if (point.getHemisphereLatitude() != IHemisphereLatitudeType.north && point.getHemisphereLatitude() != null) {
			asnPoint.setHemisphereLatitude(HemisphereLatitudeType.valueOf(point.getHemisphereLatitude().name()));
		}
		
		if (point.getHemisphereLongitude() != IHemisphereLongitudeType.east && point.getHemisphereLongitude() != null) {
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
		
		CustomerCardData asnCustomerCard = new CustomerCardData();		
		asnDocument.getTicket().setCustomerCard(asnCustomerCard);		
		
		
		if (document.getCardId() != null && !document.getCardId().isEmpty()){
		//only longs allowed
		 try {
			 long num = Long.parseLong(document.getCardId());
			 asnCustomerCard.setCardIdNum(num);
		 } catch (Exception e ){
			 asnCustomerCard.setCardIdIA5(document.getCardId());
		 }
		}
		
			
		asnCustomerCard.setCardType(UicEncoderUtils.getRestrictedInt(document.getCardType(),1,1000));
		
		asnCustomerCard.setCardTypeDescr(document.getCardTypeDescr());
		
		if (document.getClassCode() != null) {
			asnCustomerCard.setClassCode(TravelClassType.valueOf(document.getClassCode().name()));
		}
		
		if (document.getCustomer()!=null){
			asnCustomerCard.setCustomer(encodeTraveler(document.getCustomer()));
		}
		
		asnCustomerCard.setCustomerStatus(UicEncoderUtils.getUnRestrictedInt(document.getCustomerStatus()));
		
		asnCustomerCard.setCustomerStatusDescr(document.getCustomerStatusDescr());
		
		asnCustomerCard.setExtension(encodeExtension(document.getExtension()));
		
		asnCustomerCard.setIncludedServices(SequenceOfUnrestrictedLong.getSequence(UicEncoderUtils.getUnRestrictedIntList(document.getIncludedServices())));
		
		asnCustomerCard.setValidity(document.getValidFrom(), document.getValidUntil());
			
		return asnDocument;
	}

	private TokenType encodeToken(IToken token) throws EncodingFormatException {		
		TokenType asnToken = new TokenType();
		asnToken.setToken(token.getToken());
        IDWrapper tp = new IDWrapper(token.getTokenProviderInt());
		asnToken.setTokenProviderNum(tp.getNumber());
		asnToken.setTokenProviderIA5(tp.getString());
		asnToken.setTokenSpecification(token.getTokenSpecification());
		return asnToken;
	}
	
	
	
	
	private VatDetailType encodeVatDetail(IVatDetail vatDetail) {
		
		if (vatDetail == null) return null;
		
		VatDetailType asnVatDetail = new VatDetailType();
		
		asnVatDetail.setAmount(vatDetail.getAmount());
		asnVatDetail.setCountry((long) vatDetail.getCountry());
		asnVatDetail.setPercentage((long) vatDetail.getPercentage());
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
		
        IDWrapper ci = new IDWrapper(traveler.getCustomerIdInt());
		asnTraveler.setCustomerIdNum(ci.getNumber());
		asnTraveler.setCustomerIdIA5(ci.getString());
		
		asnTraveler.setDateOfBirth(traveler.getDateOfBirth());
		
		asnTraveler.setFirstName(traveler.getFirstName());
		asnTraveler.setSecondName(traveler.getSecondName());	
		asnTraveler.setLastName(traveler.getLastName());
		if (traveler.getGender() != null) {
			asnTraveler.setGender(GenderType.valueOf(traveler.getGender().toString()));
		}
		asnTraveler.setIdCard(UicEncoderUtils.getIA5(traveler.getIdCard()));
		asnTraveler.setPassportId(UicEncoderUtils.getIA5(traveler.getPassportId()));
		asnTraveler.setTitle(UicEncoderUtils.getIA5(traveler.getTitle()));	
		
		if (traveler.getPassengerType() != null) {
			asnTraveler.setPassengerType(PassengerType.valueOf(traveler.getPassengerType().name()));
		}
				
		asnTraveler.setPassengerWithReducedMobility(traveler.isPassengerWithReducedMobility());
        asnTraveler.setTicketHolder(traveler.isTicketHolder());
		
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
			asnStatus.setCustomerStatus((long) status.getStatus());
		}
		asnStatus.setCustomerStatusDescr(status.getDescription());
        IDWrapper sp = new IDWrapper(status.getStatusProviderInt());
		asnStatus.setStatusProviderIA5(sp.getString());
		asnStatus.setStatusProviderNum(sp.getNumber());

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

		if (extension.getId() == null || extension.getId().isEmpty()) {
			throw new EncodingFormatException("Extension does not include id");
		}
		
		ExtensionData asnExtension = new ExtensionData();
		
		asnExtension.setExtensionData(extension.getBinarydata());
		asnExtension.setExtensionId(UicEncoderUtils.getIA5(extension.getId()));

		return asnExtension;
	}	
	

}
