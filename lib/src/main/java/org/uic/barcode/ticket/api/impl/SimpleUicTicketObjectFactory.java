package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.IBerth;
import org.uic.barcode.ticket.api.spec.ICarCarriageReservation;
import org.uic.barcode.ticket.api.spec.ICardReference;
import org.uic.barcode.ticket.api.spec.ICompartmentDetails;
import org.uic.barcode.ticket.api.spec.IControlDetail;
import org.uic.barcode.ticket.api.spec.ICounterMark;
import org.uic.barcode.ticket.api.spec.ICustomerCard;
import org.uic.barcode.ticket.api.spec.ICustomerStatusDescription;
import org.uic.barcode.ticket.api.spec.IDelayConfirmation;
import org.uic.barcode.ticket.api.spec.IDocumentData;
import org.uic.barcode.ticket.api.spec.IDocumentExtension;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IFipTicket;
import org.uic.barcode.ticket.api.spec.IGeoCoordinate;
import org.uic.barcode.ticket.api.spec.IIncludedOpenTicket;
import org.uic.barcode.ticket.api.spec.IIssuingDetail;
import org.uic.barcode.ticket.api.spec.ILine;
import org.uic.barcode.ticket.api.spec.ILuggageRestriction;
import org.uic.barcode.ticket.api.spec.IOpenTicket;
import org.uic.barcode.ticket.api.spec.IParkingGround;
import org.uic.barcode.ticket.api.spec.IPass;
import org.uic.barcode.ticket.api.spec.IPlaces;
import org.uic.barcode.ticket.api.spec.IPolygone;
import org.uic.barcode.ticket.api.spec.IRegisteredLuggage;
import org.uic.barcode.ticket.api.spec.IReservation;
import org.uic.barcode.ticket.api.spec.IReturnRouteDescription;
import org.uic.barcode.ticket.api.spec.IRouteSection;
import org.uic.barcode.ticket.api.spec.ISeriesDataDetails;
import org.uic.barcode.ticket.api.spec.IServiceBrand;
import org.uic.barcode.ticket.api.spec.IStationPassage;
import org.uic.barcode.ticket.api.spec.ITariff;
import org.uic.barcode.ticket.api.spec.ITicketLink;
import org.uic.barcode.ticket.api.spec.ITimeRange;
import org.uic.barcode.ticket.api.spec.IToken;
import org.uic.barcode.ticket.api.spec.ITrainLink;
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




public class SimpleUicTicketObjectFactory implements IUicTicketObjectFactory {
	
	public static IUicTicketObjectFactory getInstance(){
		return new  SimpleUicTicketObjectFactory();
	}


	public IBerth createBerth() {
		return new SimpleBerth();
	}

	public ICarCarriageReservation createCarCarriageReservation() {
		return new SimpleCarCarriageReservation();
	}

	public ICardReference createCardReference() {
		return new SimpleCardReference();
	}

	public ICompartmentDetails createCompartmentDetails() {
		return new SimpleCompartmentDetails();
	}

	public IControlDetail createControlDetail() {
		return new SimpleControlDetail();
	}

	public ICounterMark createCounterMark() {
		return new SimpleCounterMark();
	}

	public ICustomerCard createCustomerCard() {
		return new SimpleCustomerCard();
	}

	public ICustomerStatusDescription createCustomerStatusDescription() {
		return new SimpleCustomerStatusDescription();
	}

	public IDelayConfirmation createDelayConfirmation() {
		return new SimpleDelayConfirmation();
	}

	public IDocumentData createDocumentData() {
		return new SimpleDocumentData();
	}

	public IExtension createExtension() {
		return new SimpleExtension();
	}

	public IFipTicket createFipTicket() {
		return new SimpleFipTicket();
	}

	public IGeoCoordinate createGeoCoordinate() {
		return new SimpleGeoCoordinate();
	}

	public IIncludedOpenTicket createIncludedOpenTicket() {
		return new SimpleIncludedOpenTicket();
	}

	public IIssuingDetail createIssuingDetail() {
		return new SimpleIssuingDetail();
	}

	public ILine createLine() {
		return new SimpleLine();
	}

	public ILuggageRestriction createLuggageRestriction() {
		return new SimpleLuggageRestriction();
	}

	public IOpenTicket createOpenTicket() {
		return new SimpleOpenTicket();
	}

	public IParkingGround createParkingGround() {
		return new SimpleParkingGround();
	}

	public IPass createPass() {
		return new SimplePass();
	}

	public IPlaces createPlaces() {
		return new SimplePlaces();
	}

	public IPolygone createPolygone() {
		return new SimplePolygone();
	}

	public IRegisteredLuggage createRegisteredLuggage() {
		return new SimpleRegisteredLuggage();
	}

	public IReservation createReservation() {
		return new SimpleReservation();
	}

	public IReturnRouteDescription createReturnRouteDescription() {
		return new SimpleReturnRouteDescription();
	}

	public IRouteSection createRouteSection() {
		return new SimpleRouteSection();
	}

	public ISeriesDataDetails createSeriesDataDetails() {
		return new SimpleSeriesDataDetails();
	}

	public IStationPassage createStationPassage() {
		return new SimpleStationPassage();
	}

	public ITariff createTariff() {
		return new SimpleTariff();
	}

	public ITicketLink createTicketLink() {
		return new SimpleTicketLink();
	}

	public ITimeRange createTimeRange() {
		return new SimpleTimeRange();
	}

	public IToken createToken() {
		return new SimpleToken();
	}

	public ITrainLink createTrainLink() {
		return new SimpleTrainLink();
	}

	public ITraveler createTraveler() {
		return new SimpleTraveler();
	}

	public ITravelerDetail createTravelerDetail() {
		return new SimpleTravelerDetail();
	}

	public IUicRailTicket createUicRailTicket() {
		return new SimpleUicRailTicket();
	}

	public IValidityDetails createValidityDetails() {
		return new SimpleValidityDetails();
	}

	public IValidityRange createValidityRange() {
		return new SimpleValidityRange();
	}

	public IViaStation createViaStation() {
		return new SimpleViaStation();
	}

	public IVoucher createVoucher() {
		return new SimpleVoucher();
	}

	public IZone createZone() {
		return new SimpleZone();
	}

	public IDocumentExtension createDocumentExtension() {
		return new SimpleDocumentExtension();
	}


	@Override
	public IServiceBrand createServiceBrand() {
		return new SimpleServiceBrand();
	}


	@Override
	public IVatDetail createVatDetail() {
		return new SimpleVatDetail();
	}

	
	
	
	
}
