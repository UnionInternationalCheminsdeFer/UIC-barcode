/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Date;

/**
 * The Interface IVoucher.
 * 
 * IVoucher provides the description of a voucher.
 * 
 */
public interface IVoucher  extends IDocumentData {
	
	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference() ;

	/**
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference);

	/**
	 * Gets the product owner.
	 *
	 * @return the product owner
	 */
	public String getProductOwner();

	/**
	 * Sets the product owner.
	 *
	 * @param productOwner the new product owner
	 */
	public void setProductOwner(String productOwner);


	/**
	 * Gets the product type.
	 *
	 * @return the product type
	 */
	public String getProductId();
	
	/**
	 * Sets the product type.
	 *
	 * @param id the new product id
	 */
	public void setProductId(String type);	
	

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
	public void setInfoText(String infoText) ;

	
	/**
	 * Gets the amount in the currency and the fraction indicated in the issuer detail data.
	 *
	 * @return the amount in the currency and the fraction indicated in the issuer detail data
	 */
	public Integer getAmount() ;

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
	public Integer getType() ;
	
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
