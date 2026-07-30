/*
 *
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Date;

/**
 * The Interface IVoucher.
 * <p>
 * IVoucher provides the description of a voucher.
 *
 */
public interface IVoucher extends IDocumentData {

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
     * Gets the product type.
     *
     * @return the product type
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
     * Sets the product type.
     *
     * @param id the new product id
     */
    public void setProductId(String id);

    /**
     * Sets the product type, without conversion to an integer type in encoding.
     *
     * @param id the new product id
     */
    public void setProductIdMustString(String id);


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
     * Gets the amount in the currency and the fraction indicated in the issuer detail data.
     *
     * @return the amount in the currency and the fraction indicated in the issuer detail data
     */
    public Integer getAmount();

    /**
     * Sets the amount in the currency and the fraction indicated in the issuer detail data.
     *
     * @param amount the new amount in the currency and the fraction indicated in the issuer detail data
     */
    public void setAmount(Integer amount);

    /**
     * Gets the type of the voucher (code list defined by the product owner).
     *
     * @return the type of the voucher
     */
    public Integer getType();

    /**
     * Sets the type of the voucher (code list defined by the product owner).
     *
     * @param type the new type
     */
    public void setType(Integer type);

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

}
