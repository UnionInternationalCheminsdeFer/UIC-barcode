/*
 *
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


/**
 * The Interface IReservation.
 * <p>
 * IReservation describes a reservation, either a reservation only or an integrated reservation ticket
 */
public interface IReservation extends IDocumentData {

    /**
     * Gets the train.
     *
     * @return the train
     */
    public default String getTrain() {
        CharSequence v = getTrainInt();
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    public CharSequence getTrainInt();

    /**
     * Sets the train.
     *
     * @param train the new train
     */
    public void setTrain(String train);

    /**
     * Sets the train, without conversion to an integer type in encoding.
     *
     * @param train the new train
     */
    public void setTrainMustString(String train);

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
     * <p>
     * The booking reference must contain the booking reference exchanged via
     * booking interface according to UIC leaflet 918.1 in case this interface was used.
     *
     * @return the booking reference
     */
    public default String getReference() {
        CharSequence v = getReferenceInt();
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    public CharSequence getReferenceInt();

    /**
     * Sets the reference.
     * <p>
     * The booking reference must contain the booking reference exchanged via
     * booking interface according to UIC leaflet 918.1 in case this interface was used.
     *
     * @param reference the new reference
     */
    public void setReference(String reference);

    /**
     * Sets the reference, without conversion to an integer type in encoding.
     * <p>
     * The booking reference must contain the booking reference exchanged via
     * booking interface according to UIC leaflet 918.1 in case this interface was used.
     *
     * @param reference the new reference
     */
    public void setReferenceMustString(String reference);

    /**
     * Gets the product id.
     *
     * @return the product id
     */
    public default String getProductId() {
        CharSequence v = getProductIdInt();
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    public CharSequence getProductIdInt();

    /**
     * Sets the product id.
     *
     * @param productId the new product id
     */
    public void setProductId(String productId);

    /**
     * Sets the product id, without conversion to an integer type in encoding.
     *
     * @param productId the new product id
     */
    public void setProductIdMustString(String productId);

    /**
     * Gets the product owner.
     * <p>
     * The company that defined the product. This is the allocating railway in case of car carriage reservations.
     *
     * @return the product owner
     */
    public default String getProductOwner() {
        CharSequence v = getProductOwnerInt();
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    public CharSequence getProductOwnerInt();

    /**
     * Sets the product owner.
     * <p>
     * The company that defined the product. This is the allocating railway in case of car carriage reservations.
     *
     * @param productOwner the new product owner
     */
    public void setProductOwner(String productOwner);

    /**
     * Sets the product owner, without conversion to an integer type in encoding.
     * <p>
     * The company that defined the product. This is the allocating railway in case of car carriage reservations.
     *
     * @param productOwner the new product owner
     */
    public void setProductOwnerMustString(String productOwner);

    /**
     * Gets the service brand of the train.
     * <p>
     * Service brand code list provided by UIC
     *
     * @return the service brand of the train
     */
    public IServiceBrand getServiceBrand();

    /**
     * Sets the service brand of the train
     * <p>
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
     * <p>
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
    public IStationCodeTable getStationCodeTable();

    /**
     * Gets the station code table.
     * <p>
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
    public default String getFromStation() {
        CharSequence v = getFromStationInt();
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    public CharSequence getFromStationInt();

    /**
     * Sets the from station code.
     *
     * @param fromStation the new from station code
     */
    public void setFromStation(String fromStation);

    /**
     * Sets the from station code, without conversion to an integer type in encoding.
     *
     * @param fromStation the new from station code
     */
    public void setFromStationMustString(String fromStation);

    /**
     * Gets the to station code.
     *
     * @return the to station code
     */
    public default String getToStation() {
        CharSequence v = getToStationInt();
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    public CharSequence getToStationInt();

    /**
     * Sets the to station code.
     *
     * @param toStation the new to station code
     */
    public void setToStation(String toStation);

    /**
     * Sets the to station code, without conversion to an integer type in encoding.
     *
     * @param toStation the new to station code
     */
    public void setToStationMustString(String toStation);

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
    public default Collection<String> getCarriers() {
        return getCarriersInt().stream().map(CharSequence::toString).collect(Collectors.toList());
    }

    public Collection<CharSequence> getCarriersInt();

    /**
     * Adds a carrier responsible for the transport.
     *
     * @param carrier the carrier
     */
    public void addCarrier(String carrier);

    /**
     * Adds a carrier responsible for the transport, without conversion to an integer type in encoding.
     *
     * @param carrier the carrier
     */
    public void addCarrierMustString(String carrier);

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
     * <p>
     * Service level codes as defined in UIC leaflet 918.1
     *
     * @return the service level
     */
    public String getServiceLevel();

    /**
     * Sets the service level.
     * <p>
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
    public void setPriceType(IPriceTypeType priceType);

    /**
     * Gets the type of supplement.
     * <p>
     * Codes according to UIC leaflet 918.1
     *
     * @return the type of supplement
     */
    public int getTypeOfSupplement();

    /**
     * Sets the type of supplement.
     * <p>
     * Codes according to UIC leaflet 918.1
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
    public void setInfoText(String infoText);

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
     * @param price
     */
    public void setPrice(Long price);


    /**
     * Gets the price.
     * <p>
     * The price
     *
     * @return the price
     */
    public Long getPrice();


    /**
     * Gets the included add on tickets.
     * <p>
     * E.g. an included local transport ticket at the beginning or end of the route.
     *
     * @return the included add ons
     */
    public Collection<IVatDetail> getVatDetails();

    /**
     * Adds an included add on ticket.
     * <p>
     * E.g. an included local transport ticket at the beginning or end of the route.
     *
     * @param includedAddOn the included add on
     */
    public void addVatDetail(IVatDetail vatDetail);

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
    public void setDepartureUTCoffset(Long departureUTCoffset);

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
    public void setArrivalUTCoffset(Long arrivalUTCoffset);
}
