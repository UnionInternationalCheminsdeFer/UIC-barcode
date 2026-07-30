/*
 *
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


/**
 * The Interface ICarCarriageReservation.
 * <p>
 * The data describe a car carriage reservation
 *
 */
public interface ICarCarriageReservation extends IDocumentData {

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
     * <p>
     * A product id to be looked up in a product catalog
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
     *
     * @return the service brand of the train
     */
    public IServiceBrand getServiceBrand();

    /**
     * sets the service brand of the train.
     *
     * @return
     * @return the service brand of the train
     */
    public void setServiceBrand(IServiceBrand serviceBrand);


    /**
     * Gets the station code table.
     * <p>
     * Defines the station code table to be used to retrieve station information.
     * Default in case of reservations is the UIC station codes table for reservations
     *
     * @return the station code table
     */
    public IStationCodeTable getStationCodeTable();

    /**
     * Sets the station code table.
     * <p>
     * Defines the station code table to be used to retrieve station information.
     * Default in case of reservations is the UIC station codes table for reservations
     *
     * @param stationCodeTable the new station code table
     */
    public void setStationCodeTable(IStationCodeTable stationCodeTable);

    /**
     * Gets the from station code
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
     * Sets the from station code
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
     * Gets the to station code
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
     * Sets the to station code
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
     * <p>
     * Station names should be used only in bilateral scenarios where the
     * control devices do not provide a station code table.
     *
     * @return the from station name
     */
    public String getFromStationName();

    /**
     * Sets the from station name.
     * <p>
     * Station names should be used only in bilateral scenarios where the
     * control devices do not provide a station code table.
     *
     * @param fromStationName the new from station name
     */
    public void setFromStationName(String fromStationName);

    /**
     * Gets the to station name.
     * <p>
     * Station names should be used only in bilateral scenarios where the
     * control devices do not provide a station code table.
     *
     * @return the to station name
     */
    public String getToStationName();

    /**
     * Sets the to station name.
     * <p>
     * Station names should be used only in bilateral scenarios where the
     * control devices do not provide a station code table.
     *
     * @param toStationName the new to station name
     */
    public void setToStationName(String toStationName);

    /**
     * Gets the carriers responsible for the transport
     *
     * @return the carriers
     */
    public default Collection<String> getCarriers() {
        return getCarriersInt().stream().map(CharSequence::toString).collect(Collectors.toList());
    }

    public Collection<CharSequence> getCarriersInt();

    /**
     * Adds a carrier responsible for the transport
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
     * Gets the service level.
     * <p>
     * Service level codes are defined in UIC leaflet 918.1
     *
     * @return the service level
     */
    public String getServiceLevel();

    /**
     * Sets the service level.
     * <p>
     * Service level codes are defined in UIC leaflet 918.1
     *
     * @param serviceLevel the new service level
     */
    public void setServiceLevel(String serviceLevel);


    /**
     * Gets the place type details.
     *
     * @return the place type details
     */
    public ICompartmentDetails getCompartmentDetails();

    /**
     * Sets the place type details.
     *
     * @param compartmentDetails the new place type details
     */
    public void setCompartmentDetails(ICompartmentDetails compartmentDetails);

    /**
     * Gets the price type.
     * <p>
     * price type indicates whether the price for the travel is included or not
     *
     * @return the price type
     */
    public IPriceTypeType getPriceType();

    /**
     * Sets the price type.
     * <p>
     * price type indicates whether the price for the travel is included or not
     *
     * @param priceType the new price type
     */
    public void setPriceType(IPriceTypeType priceType);

    /**
     * Gets the info text for the control staff
     *
     * @return the info text for the control staff
     */
    public String getInfoText();

    /**
     * Sets the info text for the control staff
     *
     * @param infoText the new info text for the control staff
     */
    public void setInfoText(String infoText);

    /**
     * Gets the proprietary extension
     *
     * @return the proprietary extension
     */
    public IExtension getExtension();

    /**
     * Sets the proprietary extension.
     *
     * @param extension the new proprietary extension
     */
    public void setExtension(IExtension extension);

    /**
     * Gets the coach.
     *
     * @return the coach
     */
    public String getCoach();

    /**
     * Sets the coach.
     *
     * @param coach the new coach
     */
    public void setCoach(String coach);

    /**
     * Gets the place.
     *
     * @return the place
     */
    public String getPlace();

    /**
     * Sets the place.
     *
     * @param place the new place
     */
    public void setPlace(String place);

    /**
     * Gets the tariff.
     *
     * @return the tariff
     */
    public ITariff getTariff();

    /**
     * Sets the tariff.
     *
     * @param tariff the new tariff
     */
    public void setTariff(ITariff tariff);

    /**
     * Gets the number plate.
     *
     * @return the number plate
     */
    public String getNumberPlate();

    /**
     * Sets the number plate.
     *
     * @param numberPlate the new number plate
     */
    public void setNumberPlate(String numberPlate);

    /**
     * Gets the trailer plate.
     *
     * @return the trailer plate
     */
    public String getTrailerPlate();

    /**
     * Sets the trailer plate.
     *
     * @param trailerPlate the new trailer plate
     */
    public void setTrailerPlate(String trailerPlate);

    /**
     * Gets the car category.
     * <p>
     * Car categories are defined in UIC leaflet 918.1
     *
     * @return the car category
     */
    public int getCarCategory();

    /**
     * Sets the car category.
     * <p>
     * Car categories are defined in UIC leaflet 918.1
     *
     * @param carCategory the new car category
     */
    public void setCarCategory(int carCategory);

    /**
     * Gets the boat category.
     * <p>
     * Boat categories are defined in UIC leaflet 918.1
     *
     * @return the boat category
     */
    public int getBoatCategory();

    /**
     * Sets the boat category.
     * <p>
     * Boat categories are defined in UIC leaflet 918.1
     *
     * @param boatCategory the new boat category
     */
    public void setBoatCategory(int boatCategory);

    /**
     * Checks if the car has a textile roof.
     *
     * @return true, if the car has a textile roof
     */
    public boolean isTextileRoof();

    /**
     * Sets that the car bas a textile roof.
     *
     * @param textileRoof the textile roof of a car
     */
    public void setTextileRoof(boolean textileRoof);

    /**
     * Gets the roof rack type.
     * <p>
     * Roof rack types are defined in UIC leaflet 918.1
     *
     * @return the roof rack type
     */
    public IRoofRackType getRoofRackType();

    /**
     * Sets the roof rack type.
     * <p>
     * Roof rack types are defined in UIC leaflet 918.1
     *
     * @param roofRackType the new roof rack type
     */
    public void setRoofRackType(IRoofRackType roofRackType);

    /**
     * Gets the roof rack height in cm
     *
     * @return the roof rack height in cm
     */
    public int getRoofRackHeight();

    /**
     * Sets the roof rack height in cm
     *
     * @param roofRackHeight the new roof rack height in cm
     */
    public void setRoofRackHeight(int roofRackHeight);

    /**
     * Gets the number of attached boats.
     *
     * @return the number of attached boats
     */
    public int getAttachedBoats();

    /**
     * Sets the number of attached boats.
     *
     * @param attachedBoats the number of attached boats
     */
    public void setAttachedBoats(int attachedBoats);

    /**
     * Gets the number of attached bicycles.
     *
     * @return the number of attached bicycles
     */
    public int getAttachedBicycles();

    /**
     * Sets the number of attached bicycles.
     *
     * @param attachedBicycles the number of attached bicycles
     */
    public void setAttachedBicycles(int attachedBicycles);

    /**
     * Gets the number of attached surfboards.
     *
     * @return the number of attached surfboards
     */
    public int getAttachedSurfboards();

    /**
     * Sets the number of attached surfboards.
     *
     * @param attachedSurfboards the number of attached surfboards
     */
    public void setAttachedSurfboards(int attachedSurfboards);

    /**
     * Gets the begin of the loading on the train
     *
     * @return the begin loading date and time
     */
    public Date getBeginLoading();

    /**
     * Sets the begin of the loading on the train
     *
     * @param beginLoading the begin loading date and time
     */
    public void setBeginLoading(Date beginLoading);

    /**
     * Gets the end of the loading on the train
     *
     * @return the end of the loading on the train
     */
    public Date getEndLoading();

    /**
     * Sets the end of the loading on the train
     *
     * @param endLoading the end of the loading on the train
     */
    public void setEndLoading(Date endLoading);

    /**
     * Gets the loading list entry.
     *
     * @return the loading list entry
     */
    public int getLoadingListEntry();

    /**
     * Sets the loading list entry.
     *
     * @param loadingListEntry the new loading list entry
     */
    public void setLoadingListEntry(int loadingListEntry);

    /**
     * Gets the loading deck.
     *
     * @return the loading deck
     */
    public ILoadingDeckType getLoadingDeck();

    /**
     * Sets the loading deck.
     *
     * @param loadingDeck the new loading deck
     */
    public void setLoadingDeck(ILoadingDeckType loadingDeck);


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
     * @return the loading time UTC offset
     */
    public Long getLoadingTimeUTCoffset();

    /**
     * Sets the departure date time.
     *
     * @param the loading time UTC offset
     */
    public void setLoadingTimeUTCoffset(Long UTCoffset);


}
