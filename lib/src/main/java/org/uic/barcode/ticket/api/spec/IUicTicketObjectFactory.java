package org.uic.barcode.ticket.api.spec;

public interface IUicTicketObjectFactory {
	
    public abstract IBerth 			           createBerth();
    public abstract ICarCarriageReservation    createCarCarriageReservation();
    public abstract ICardReference 		       createCardReference();
    public abstract ICompartmentDetails 	   createCompartmentDetails();
    public abstract IControlDetail 		       createControlDetail();
    public abstract ICounterMark 		       createCounterMark();
    public abstract ICustomerCard 		       createCustomerCard();
    public abstract ICustomerStatusDescription createCustomerStatusDescription();
    public abstract IDelayConfirmation 	       createDelayConfirmation();
    public abstract IDocumentData 		       createDocumentData();
    public abstract IExtension 		           createExtension();
    public abstract IFipTicket 		      	   createFipTicket();
    public abstract IGeoCoordinate 		       createGeoCoordinate();
    public abstract IIncludedOpenTicket 	   createIncludedOpenTicket();
    public abstract IIssuingDetail 		       createIssuingDetail();
    public abstract ILine 			           createLine();
    public abstract ILuggageRestriction 	   createLuggageRestriction();
    public abstract IOpenTicket 		       createOpenTicket();
    public abstract IParkingGround 		       createParkingGround();
    public abstract IPass 			           createPass();
    public abstract IPlaces 			       createPlaces();
    public abstract IPolygone 			       createPolygone();
    public abstract IRegisteredLuggage 	       createRegisteredLuggage();
    public abstract IReservation 		       createReservation();
    public abstract IReturnRouteDescription    createReturnRouteDescription();
    public abstract IRouteSection 		       createRouteSection();
    public abstract ISeriesDataDetails 	       createSeriesDataDetails();
    public abstract IStationPassage 		   createStationPassage();
    public abstract ITariff 			       createTariff();
    public abstract ITicketLink 		       createTicketLink();
    public abstract ITimeRange 		           createTimeRange();
    public abstract IToken 			           createToken();
    public abstract ITrainLink 		           createTrainLink();
    public abstract ITraveler 			       createTraveler();
    public abstract ITravelerDetail 		   createTravelerDetail();
    public abstract IUicRailTicket 		       createUicRailTicket();
    public abstract IValidityDetails 		   createValidityDetails();
    public abstract IValidityRange 		       createValidityRange();
    public abstract IViaStation 		       createViaStation();
    public abstract IVoucher 			       createVoucher();
    public abstract IZone 			           createZone();
	public abstract IDocumentExtension         createDocumentExtension();
	public abstract IServiceBrand              createServiceBrand();
	public abstract IVatDetail                 createVatDetail();

	
}
