/*
 *
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


/**
 * The Interface IFipTicket.
 * <p>
 * IFipTicket provides a description of the FIP ticket.
 *
 */
public interface IFipTicket extends IDocumentData {

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
     *
     * @param productOwner the new product owner
     */
    public void setProductOwner(String productOwner);

    /**
     * Sets the product owner, without conversion to an integer type in encoding.
     *
     * @param productOwner the new product owner
     */
    public void setProductOwnerMustString(String productOwner);


    /**
     * Gets the travel class code.
     *
     * @return the travel class code
     */
    public ITravelClassType getClassCode();

    /**
     * Sets the class code.
     *
     * @param classCode the new class code
     */
    public void setClassCode(ITravelClassType classCode);

    /**
     * Gets the valid from date.
     *
     * @return the valid from date
     */
    public Date getValidFrom();

    /**
     * Sets the valid from date.
     *
     * @param validFrom the new valid from date
     */
    public void setValidFrom(Date validFrom);

    /**
     * Gets the valid until date.
     *
     * @return the valid until date
     */
    public Date getValidUntil();

    /**
     * Sets the valid until date.
     *
     * @param validUntil the new valid until date
     */
    public void setValidUntil(Date validUntil);

    /**
     * Gets the number of travel days allowed for traveling within the validity range.
     *
     * @return the number of travel days allowed for traveling within the validity range.
     */
    public int getNumberOfTravelDates();

    /**
     * Sets the number of travel days allowed for traveling within the validity range.
     *
     * @param numberOfTravelDates the new number of travel days
     */
    public void setNumberOfTravelDates(int numberOfTravelDates);

    /**
     * Checks if supplements are included.
     *
     * @return true, if is supplements are included
     */
    public boolean isIncludesSupplements();

    /**
     * Sets whether supplements are included.
     *
     * @param includesSuplements the new includes supplements flag
     */
    public void setIncludesSupplements(boolean includesSuplements);

    /**
     * Gets the carriers valid for traveling.
     *
     * @return the carriers valid for traveling
     */
    public default Collection<String> getCarriers() {
        return getCarriersInt().stream().map(CharSequence::toString).collect(Collectors.toList());
    }

    public Collection<CharSequence> getCarriersInt();

    /**
     * Adds a carrier.
     *
     * @param carrier the carrier
     */
    public void addCarrier(String carrier);

    /**
     * Adds a carrier, without conversion to an integer type in encoding.
     *
     * @param carrier the carrier
     */
    public void addCarrierMustString(String carrier);

    /**
     * Gets the extension.
     *
     * @return the extension
     */
    public IExtension getExtension();

    /**
     * Sets the extension.
     *
     * @param extensionData the new extension
     */
    public void setExtension(IExtension extensionData);

    /**
     * Gets the activated days where the ticket is valid for tarveling.
     *
     * @return the activated days
     */
    public Collection<Date> getActivatedDays();

    /**
     * Adds the activated day.
     *
     * @param day the day
     */
    public void addActivatedDay(Date day);


}
