/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Date;


/**
 * The Interface IIssuerDetails.
 */
public interface IIssuingDetail {
	
	/**
	 * Gets the issuing date.
	 *
	 * @return the issuing date
	 */
	public Date getIssuingDate();
	
	/**
	 * Sets the issuing date.
	 *
	 * @param date the new issuing date
	 */
	public void setIssuingDate(Date date);
	
	/**
	 * Gets the issuer.
	 *
	 * @return the issuer
	 */
	public String getIssuer();
	
	/**
	 * Sets the issuer.
	 *
	 * @param issuer the new issuer
	 */
	public void setIssuer(String issuer);
	
	/**
	 * Gets the security provider.
	 *
	 * @return the security provider
	 */
	public String getSecurityProvider();
	

	/**
	 * Sets the security provider.
	 *
	 * @param securityProvider the new security provider
	 */
	public void setSecurityProvider(String securityProvider);
	

	/**
	 * Gets the issuer name.
	 *
	 * @return the issuer name
	 */
	public String getIssuerName();
	

	/**
	 * Sets the issuer name.
	 *
	 * @param issuerName the new issuer name
	 */
	public void setIssuerName(String issuerName);
	

	/**
	 * Checks if is specimen.
	 *
	 * @return true, if is specimen
	 */
	public boolean isSpecimen();
	

	/**
	 * Sets the specimen.
	 *
	 * @param specimen the new specimen
	 */
	public void setSpecimen(boolean specimen);
	

	/**
	 * Checks if is activated.
	 *
	 * @return true, if is activated
	 */
	public boolean isActivated();
	

	/**
	 * Sets the activated.
	 *
	 * @param activated the new activated
	 */
	public void setActivated(boolean activated);
	

	/**
	 * Gets the issuer pnr.
	 *
	 * @return the issuer pnr
	 */
	public String getIssuerPNR();
	

	/**
	 * Sets the issuer pnr.
	 *
	 * @param issuerPNR the new issuer pnr
	 */
	public void setIssuerPNR(String issuerPNR);
	

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
	 * Gets the issued on train.
	 *
	 * @return the issued on train
	 */
	public String getIssuedOnTrain();
	

	/**
	 * Sets the issued on train.
	 *
	 * @param issuedOnTrain the new issued on train
	 */
	public void setIssuedOnTrain(String issuedOnTrain);
	

	/**
	 * Gets the issued on line.
	 *
	 * @return the issued on line
	 */
	public Integer getIssuedOnLine();
	

	/**
	 * Sets the line number or id in case the ticket was issued on a local transport line.
	 *
	 * @param issuedOnLine the new issued on line
	 */
	public void setIssuedOnLine(Integer issuedOnLine);
	

	/**
	 * Gets the point of sale.
	 *
	 * @return the point of sale
	 */
	public IGeoCoordinate getPointOfSale();
	

	/**
	 * Sets the point of sale.
	 *
	 * @param pointOfSale the new point of sale
	 */
	public void setPointOfSale(IGeoCoordinate pointOfSale);
	
		
	public boolean isSecurePaperTicket();
	public void setSecurePaperTicket(boolean securePaperTicket); 

}
