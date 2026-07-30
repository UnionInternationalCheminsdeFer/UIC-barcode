/*
 *
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * The Interface ICounterMark.
 * <p>
 * The group ticket counter mark.
 * <p>
 * Members of a group might be provided with a counter mark indicating their
 * participation in a group. The counter mark is not itself a ticket, but referees to a
 * ticket.
 * <p>
 * The content corresponds to the open ticket content.
 *
 */
public interface ICounterMark extends IDocumentData {


    /**
     * Gets the reference.
     *
     * @return the reference
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
     *
     * @param reference the new reference
     */
    public void setReference(String reference);

    /**
     * Sets the reference, without conversion to an integer type in encoding.
     *
     * @param reference the new reference
     */
    public void setReferenceMustString(String reference);

    /**
     * Gets the product owner.
     * <p>
     * The product owner is the railways responsible for assembling this ticket. As there is currently no
     * standard interface for open tickets between an product owner and an issuer this is usually
     * identical to the issuer of a counter mark.
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
     * The product owner is the railways responsible for assembling this ticket. As there is currently no
     * standard interface for open tickets between an product owner and an issuer this is usually
     * identical to the issuer of a counter mark.
     *
     * @param productOwner the new product owner
     */
    public void setProductOwner(String productOwner);

    /**
     * Sets the product owner, without conversion to an integer type in encoding.
     * <p>
     * The product owner is the railways responsible for assembling this ticket. As there is currently no
     * standard interface for open tickets between an product owner and an issuer this is usually
     * identical to the issuer of a counter mark.
     *
     * @param productOwner the new product owner
     */
    public void setProductOwnerMustString(String productOwner);

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
     * Checks if is return included.
     *
     * @return true, if is return included
     */
    public boolean isReturnIncluded();

    /**
     * Sets the return included.
     *
     * @param returnIncluded the new return included
     */
    public void setReturnIncluded(boolean returnIncluded);

    /**
     * Gets the station code table.
     * <p>
     * Defines the station code table to be used to retrieve station information.
     * Default in this case is the UIC station codes table for standard UIC
     * station code from MERITS (UIC country code + 5 digit local code)
     *
     * @return the station code table
     */
    public IStationCodeTable getStationCodeTable();

    /**
     * Sets the station code table.
     * <p>
     * Defines the station code table to be used to retrieve station information.
     * Default in this case is the UIC station codes table for standard UIC
     * station code from MERITS (UIC country code + 5 digit local code)
     *
     * @param stationCodeTable the new station code table
     */
    public void setStationCodeTable(IStationCodeTable stationCodeTable);

    /**
     * Gets the from station.
     *
     * @return the from station
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
     * Sets the from station.
     *
     * @param fromStation the new from station
     */
    public void setFromStation(String fromStation);

    /**
     * Sets the from station, without conversion to an integer type in encoding.
     *
     * @param fromStation the new from station
     */
    public void setFromStationMustString(String fromStation);

    /**
     * Gets the to station.
     *
     * @return the to station
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
     * Sets the to station.
     *
     * @param toStation the new to station
     */
    public void setToStation(String toStation);

    /**
     * Sets the to station, without conversion to an integer type in encoding.
     *
     * @param toStation the new to station
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
     * Gets the valid region desc.
     *
     * @return the valid region desc
     */
    public String getValidRegionDesc();

    /**
     * Sets the valid region desc.
     *
     * @param validRegionDesc the new valid region desc
     */
    public void setValidRegionDesc(String validRegionDesc);

    /**
     * Gets the valid region list.
     *
     * @return the valid region list
     */
    public Collection<IRegionalValidity> getValidRegionList();

    /**
     * Adds the valid region list.
     *
     * @param validRegion the valid region
     */
    public void addValidRegionList(IRegionalValidity validRegion);

    /**
     * Gets the return description.
     *
     * @return the return description
     */
    public IReturnRouteDescription getReturnDescription();

    /**
     * Sets the return description.
     *
     * @param returnDescription the new return description
     */
    public void setReturnDescription(IReturnRouteDescription returnDescription);

    /**
     * Gets the valid from.
     *
     * @return the valid from
     */
    public Date getValidFrom();

    /**
     * Sets the valid from.
     *
     * @param validFrom the new valid from
     */
    public void setValidFrom(Date validFrom);

    /**
     * Gets the valid until.
     *
     * @return the valid until
     */
    public Date getValidUntil();

    /**
     * Sets the valid until.
     *
     * @param validUntil the new valid until
     */
    public void setValidUntil(Date validUntil);

    /**
     * Gets the class code.
     *
     * @return the class code
     */
    public ITravelClassType getClassCode();

    /**
     * Sets the class code.
     *
     * @param classCode the new class code
     */
    public void setClassCode(ITravelClassType classCode);

    /**
     * Gets the included carriers.
     *
     * @return the included carriers
     */
    public default Collection<String> getIncludedCarriers() {
        return getIncludedCarriersInt().stream().map(CharSequence::toString).collect(Collectors.toList());
    }

    public Collection<CharSequence> getIncludedCarriersInt();

    /**
     * Adds the included carrier.
     *
     * @param includedCarrier the included carrier
     */
    public void addIncludedCarrier(String includedCarrier);

    /**
     * Adds the included carrier, without conversion to an integer type in encoding.
     *
     * @param includedCarrier the included carrier
     */
    public void addIncludedCarrierMustString(String includedCarrier);

    /**
     * Gets the included service brands.
     *
     * @return the included service brands
     */
    public Collection<Integer> getIncludedServiceBrands();

    /**
     * Adds the included service brand.
     *
     * @param includedServiceBrand the included service brand
     */
    public void addIncludedServiceBrand(Integer includedServiceBrand);

    /**
     * Gets the excluded service brands.
     *
     * @return the excluded service brands
     */
    public Collection<Integer> getExcludedServiceBrands();

    /**
     * Adds the excluded service brand.
     *
     * @param excludedServiceBrand the excluded service brand
     */
    public void addExcludedServiceBrand(Integer excludedServiceBrand);

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
     * Gets the total of countermarks.
     *
     * @return the total of countermarks
     */
    public int getTotalOfCountermarks();

    /**
     * Sets the total of countermarks.
     *
     * @param totalOfCountermarks the new total of countermarks
     */
    public void setTotalOfCountermarks(int totalOfCountermarks);

    /**
     * Gets the group name.
     *
     * @return the group name
     */
    public String getGroupName();

    /**
     * Sets the group name.
     *
     * @param groupName the new group name
     */
    public void setGroupName(String groupName);

    /**
     * Gets the number of countermark.
     *
     * @return the number of countermark
     */
    public int getNumberOfCountermark();

    /**
     * Sets the number of countermark.
     *
     * @param numberOfCountermark the new number of countermark
     */
    public void setNumberOfCountermark(int numberOfCountermark);


    public void setTicketReference(String ticketReference);

    public void setTicketReferenceMustString(String ticketReference);

    public default String getTicketReference() {
        CharSequence v = getTicketReferenceInt();
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    public CharSequence getTicketReferenceInt();

    /**
     * Gets the validFrom date time offset to UTC in units of 15 minutes.
     *
     * @return the validFrom date time UTC offset
     */
    public Long getValidFromUTCoffset();

    /**
     * Sets the validFrom date time.
     *
     * @param validFromDateTime the new validFrom date time
     */
    public void setValidFromUTCoffset(Long validFromUTCoffset);

    /**
     * Gets the validUntil date time offset to UTC in units of 15 minutes.
     *
     * @return the validUntil date time UTC offset
     */
    public Long getValidUntilUTCoffset();

    /**
     * Sets the validUntil date time.
     *
     * @param validUntilDateTime the new validUntil date time
     */
    public void setValidUntilUTCoffset(Long validUntilUTCoffset);


}
