/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;


/**
 * The Interface IReservation.
 * 
 * IReservation describes a reservation, either a reservation only or an integrated reservation ticket
 */
public interface IReservation extends IDocumentData {
	
	/**
	 * Gets the train.
	 *
	 * @return the train
	 */
	public String getTrain();
	
	/**
	 * Sets the train.
	 *
	 * @param train the new train
	 */
	public void setTrain(String train);

	/**
	 * Gets the departure date and time.
	 *
	 * @return the departure date and time
	 */
	public Date getDepartureDate();
	
	/**
	 * Sets the departure date and time.
	 *
	 * @param departureDate the new departure date and time
	 */
	public void setDepartureDate(Date departureDate);

	/**
	 * Gets the arrival date and time.
	 *
	 * @return the arrival date and time
	 */
	public Date getArrivalDate();
	
	/**
	 * Sets the arrival date and time.
	 *
	 * @param arrivalDate the new arrival date and time
	 */
	public void setArrivalDate(Date arrivalDate);

	/**
	 * Gets the booking reference.
	 * 
	 * The booking reference must contain the booking reference exchanged via 
	 * booking interface according to UIC leaflet 918.1 in case this interface was used.
	 *
	 * @return the booking reference
	 */
	public String getReference();
	
	/**
	 * Sets the reference.
	 * 
	 * The booking reference must contain the booking reference exchanged via 
	 * booking interface according to UIC leaflet 918.1 in case this interface was used.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference);

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public String getProductId();
	
	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(String productId);

	/**
	 * Gets the product owner.
	 * 
	 * The company that defined the product. This is the allocating railway in case of car carriage reservations.
	 *
	 * @return the product owner
	 */
	public String getProductOwner() ;
	
	/**
	 * Sets the product owner.
	 *
	 * The company that defined the product. This is the allocating railway in case of car carriage reservations.
	 * 
	 * @param productOwner the new product owner
	 */
	public void setProductOwner(String productOwner);		
	
	/**
	 * Gets the service brand of the train.
	 * 
	 * Service brand code list provided by UIC
	 *
	 * @return the service brand of the train
	 */
	public IServiceBrand getServiceBrand();

	/**
	 * Sets the service brand of the train
	 *
	 * Service brand code list provided by UIC	 
	 *
	 * @param serviceBrand the new service brand of the train
	 */
	public void setServiceBrand(IServiceBrand serviceBrand);

	/**
	 * Gets the service.
	 * 
	 * @return the service
	 */
	public IServiceType getService();
	
	/**
	 * Sets the service.
	 *
	 * Services according to UIC leaflet 918.1
	 * 
	 * @param service the new service
	 */
	public void setService(IServiceType service);


	/**
	 * Gets the station code table.
	 *
	 * @return the station code table
	 */
	public IStationCodeTable  getStationCodeTable();

	/**
	 * Gets the station code table.
	 * 
	 * Defines the station code table to be used to retrieve station information. 
	 * Default in case of reservations is the UIC station codes table for reservations 
	 *
	 * @return the station code table
	 */
	public void setStationCodeTable(IStationCodeTable stationCodeTable);

	/**
	 * Gets the from station code.
	 *
	 * @return the from station code
	 */
	public String getFromStation();

	/**
	 * Sets the from station code.
	 *
	 * @param fromStation the new from station code
	 */
	public void setFromStation(String fromStation);

	/**
	 * Gets the to station code.
	 *
	 * @return the to station code
	 */
	public String getToStation();

	/**
	 * Sets the to station code.
	 *
	 * @param toStation the new to station code
	 */
	public void setToStation(String toStation);

	/**
	 * Gets the from station name.
	 *
	 * @return the from station name
	 */
	public String getFromStationName();

	/**
	 * Sets the from station name.
	 *
	 * @param fromStationName the new from station name
	 */
	public void setFromStationName(String fromStationName);

	/**
	 * Gets the to station name.
	 *
	 * @return the to station name
	 */
	public String getToStationName();

	/**
	 * Sets the to station name.
	 *
	 * @param toStationName the new to station name
	 */
	public void setToStationName(String toStationName);

	/**
	 * Gets the carriers responsible for the transport.
	 *
	 * @return the carriers
	 */
	public Collection<String> getCarriers();

	/**
	 * Adds a carrier responsible for the transport.
	 *
	 * @param carrier the carrier
	 */
	public void addCarrier(String carrier);

	/**
	 * Gets the travel class code.
	 *
	 * @return the travel class code
	 */
	public ITravelClassType getClassCode();

	/**
	 * Sets the travel class code.
	 *
	 * @param classCode the new travel class code
	 */
	public void setClassCode(ITravelClassType classCode);

	/**
	 * Gets the service level.
	 *
	 * Service level codes as defined in UIC leaflet 918.1
	 *
	 * @return the service level
	 */
	public String getServiceLevel();

	/**
	 * Sets the service level.
	 *
	 * Service level codes as defined in UIC leaflet 918.1	 
	 *
	 * @param serviceLevel the new service level
	 */
	public void setServiceLevel(String serviceLevel);

	/**
	 * Gets the places.
	 *
	 * @return the places
	 */
	public IPlaces getPlaces();

	/**
	 * Sets the places.
	 *
	 * @param places the new places
	 */
	public void setPlaces(IPlaces places);
	
	
	/**
	 * Gets the additional places in a second coach.
	 *
	 * @return the additional places in a second coach.
	 */
	public IPlaces getAdditionalPlaces();
	
	/**
	 * Sets the additional places.
	 *
	 * @param places the new places
	 */
	public void setAdditionalPlaces(IPlaces places);	

	/**
	 * Gets the bicycle places.
	 *
	 * @return the bicycle places
	 */
	public IPlaces getBicyclePlaces();

	/**
	 * Sets the bicycle places.
	 *
	 * @param bicyclePlaces the new bicycle places
	 */
	public void setBicyclePlaces(IPlaces bicyclePlaces);
	
	/**
	 * Gets the compartment details.
	 *
	 * @return the compartment details
	 */
	public ICompartmentDetails getCompartmentDetails();

	/**
	 * Sets the compartment details.
	 *
	 * @param compartmentDetails the new compartment details
	 */
	public void setCompartmentDetails(ICompartmentDetails compartmentDetails);

	/**
	 * Gets the number of overbooked travelers.
	 *
	 * @return the number of overbooked travelers
	 */
	public int getNumberOfOverbooked();

	/**
	 * Sets the number of overbooked travelers.
	 *
	 * @param numberOfOverbooked the new number of overbooked travelers
	 */
	public void setNumberOfOverbooked(int numberOfOverbooked);

	/**
	 * Gets the berths.
	 *
	 * @return the berths
	 */
	public Collection<IBerth> getBerths();

	/**
	 * Adds the berth.
	 *
	 * @param berth the berth
	 */
	public void addBerth(IBerth berth);

	/**
	 * Gets the tariffs.
	 *
	 * @return the tariffs
	 */
	public Collection<ITariff> getTariffs();

	/**
	 * Adds the tariff.
	 *
	 * @param tariff the tariff
	 */
	public void addTariff(ITariff tariff);
	
	/**
	 * Gets the price type.
	 *
	 * @return the price type
	 */
	public IPriceTypeType getPriceType();

	/**
	 * Sets the price type.
	 *
	 * @param priceType the new price type
	 */
	public void setPriceType(IPriceTypeType priceType) ;

	/**
	 * Gets the type of supplement.
	 *
	 *	Codes according to UIC leaflet 918.1
	 *
	 * @return the type of supplement
	 */
	public int getTypeOfSupplement() ;

	/**
	 * Sets the type of supplement.
	 *
	 *	Codes according to UIC leaflet 918.1
	 *	 
	 * @param typeOfSupplement the new type of supplement
	 */
	public void setTypeOfSupplement(int typeOfSupplement);

	/**
	 * Gets the number of supplements.
	 *
	 * @return the number of supplements
	 */
	public int getNumberOfSupplements();

	/**
	 * Sets the number of supplements.
	 *
	 * @param numberOfSupplements the new number of supplements
	 */
	public void setNumberOfSupplements(int numberOfSupplements);


	/**
	 * Gets the info text.
	 *
	 * @return the info text
	 */
	public String getInfoText();

	/**
	 * Sets the info text.
	 *
	 * @param infoText the new info text
	 */
	public void setInfoText(String infoText) ;

	/**
	 * Gets the luggage restriction.
	 *
	 * @return the luggage restriction
	 */
	public ILuggageRestriction getLuggageRestriction();
	
	/**
	 * Sets the luggage restriction.
	 *
	 * @param luggageRestriction the new luggage restriction
	 */
	public void setLuggageRestriction(ILuggageRestriction luggageRestriction);
	
	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public IExtension getExtension();

	/**
	 * Sets the extension.
	 *
	 * @param extension the new extension
	 */
	public void setExtension(IExtension extension);
	
	/**
	 * Sets the price. 
	 *
	 *
	 * @param price
	 */
	public void setPrice(Long price);
	
	
	/**
	 * Gets the price.
	 *
	 * The price
	 *
	 * @return the price
	 */
	public Long getPrice();
	
	
	/**
	 * Gets the included add on tickets.
	 *	 
	 * E.g. an included local transport ticket at the beginning or end of the route.
	 * 	 
	 * @return the included add ons
	 */
	public Collection<IVatDetail> getVatDetails();

	/**
	 * Adds an included add on ticket.
	 * 
	 * E.g. an included local transport ticket at the beginning or end of the route.
	 *
	 * @param includedAddOn the included add on
	 */
	public void addVatDetail(IVatDetail vatDetail) ;

	/**
	 * Gets the departure date time offset to UTC in units of 15 minutes.
	 *
	 * @return the departure date time UTC offset
	 */
	public Long getDepartureUTCoffset();
	
	/**
	 * Sets the departure date time.
	 *
	 * @param departureDateTime the new departure date time
	 */
	public void setDepartureUTCoffset(Long departureUTCoffset) ;
	
	/**
	 * Gets the arrival date time offset to UTC in units of 15 minutes.
	 *
	 * @return the arrival date time UTC offset
	 */
	public Long getArrivalUTCoffset();
	
	/**
	 * Sets the arrival date time.
	 *
	 * @param arrivalDateTime the new arrival date time
	 */
	public void setArrivalUTCoffset(Long arrivalUTCoffset) ;
}
