/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Date;

import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IGeoCoordinate;
import org.uic.barcode.ticket.api.spec.IIssuingDetail;


/**
 * The Class SimpleIssuerDetails.
 */
public class SimpleIssuingDetail implements IIssuingDetail{


	/** The issuer. */
	protected String issuer;
	
	/** The issuing date. */
	protected Date issuingDate;
	
    /** The security provider. */
    protected String		securityProvider;
    
    /** The issuer name. */
    protected String 		issuerName;
    
    /** The specimen. */
    protected boolean		specimen = false;
    
    /** The activated. */
    protected boolean		activated = true;     
    
    /** The issuer pnr. */
    protected String	 	issuerPNR;
    
    /** The extension. */
    protected IExtension 	extension;
	
	/** The issued on train. */
	protected String	 	issuedOnTrain;
    
	/** The line number in case the ticket was issuer on a local transport line. */
	Integer issuedOnLine = 0;
	
	/** The point of sale. */
	protected IGeoCoordinate pointOfSale;
	
	/** The secure paper ticket. */
	protected boolean securePaperTicket;

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.IIssuingDetail#getIssuer()
	 */
	public String getIssuer() {
		if (issuer == null || issuer.length() == 0){
			return this.securityProvider;
		}
		return issuer;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.IIssuingDetail#setIssuer(java.lang.String)
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.IIssuingDetail#getIssuingDate()
	 */
	public Date getIssuingDate() {
		return issuingDate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.IIssuingDetail#setIssuingDate(java.util.Date)
	 */
	public void setIssuingDate(Date issuingDate) {
		this.issuingDate = issuingDate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#getSecurityProvider()
	 */
	public String getSecurityProvider() {
		return securityProvider;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#setSecurityProvider(int)
	 */
	public void setSecurityProvider(String securityProvider) {
		this.securityProvider = securityProvider;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#getIssuerName()
	 */
	public String getIssuerName() {
		return issuerName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#setIssuerName(java.lang.String)
	 */
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#isSpecimen()
	 */
	public boolean isSpecimen() {
		return specimen;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#setSpecimen(boolean)
	 */
	public void setSpecimen(boolean specimen) {
		this.specimen = specimen;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#isActivated()
	 */
	public boolean isActivated() {
		return activated;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#setActivated(boolean)
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#getIssuerPNR()
	 */
	public String getIssuerPNR() {
		return issuerPNR;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#setIssuerPNR(java.lang.String)
	 */
	public void setIssuerPNR(String issuerPNR) {
		this.issuerPNR = issuerPNR;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#getExtension()
	 */
	public IExtension getExtension() {
		return extension;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#setExtension(org.uic.ticket.api.spec.IExtension)
	 */
	public void setExtension(IExtension extension) {
		this.extension = extension;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#getIssuedOnTrain()
	 */
	public String getIssuedOnTrain() {
		return issuedOnTrain;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#setIssuedOnTrain(java.lang.String)
	 */
	public void setIssuedOnTrain(String issuedOnTrain) {
		this.issuedOnTrain = issuedOnTrain;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#getIssuedOnLine()
	 */
	public Integer getIssuedOnLine() {
		return issuedOnLine;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#setIssuedOnLine(int)
	 */
	public void setIssuedOnLine(Integer issuedOnLine) {
		this.issuedOnLine = issuedOnLine;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#getPointOfSale()
	 */
	public IGeoCoordinate getPointOfSale() {
		return pointOfSale;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#setPointOfSale(org.uic.ticket.api.spec.IGeoCoordinate)
	 */
	public void setPointOfSale(IGeoCoordinate pointOfSale) {
		this.pointOfSale = pointOfSale;
	}
	


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#isSecurePaperTicket()
	 */
	public boolean isSecurePaperTicket() {
		return securePaperTicket;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IIssuingDetail#setSecurePaperTicket(boolean)
	 */
	public void setSecurePaperTicket(boolean securePaperTicket) {
		this.securePaperTicket = securePaperTicket;
	}
	
	

}
