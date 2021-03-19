/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Date;

import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IVoucher;




// TODO: Auto-generated Javadoc
/**
 * The Class SimpleVoucher.
 */
public class SimpleVoucher extends SimpleDocumentData implements IVoucher {
	
	/** The reference. */
	protected String reference;
	
	/** The product id. */
	protected String productId;

	/** The product owner. */
	protected String productOwner;
	
	/** The valid from. */
	protected Date validFrom;
	
	/** The valid until. */
	protected Date  validUntil;	
	
 	/** The info text. */
	 protected String infoText;

    /** The amount. */
    protected Integer amount;      
    
    /** The type. */
    protected Integer type;

	/** The extension data. */
	protected IExtension   	extensionData;	    
    
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#getAmount()
	 */
	public Integer getAmount() {
		return amount;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#setAmount(java.lang.Integer)
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#getType()
	 */
	public Integer getType() {
		return type;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#setType(java.lang.Integer)
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#getReference()
	 */
	public String getReference() {
		return reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#setReference(java.lang.String)
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#getProductId()
	 */
	public String getProductId() {
		return productId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#setProductId(java.lang.String)
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#getProductOwner()
	 */
	public String getProductOwner() {
		return productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#setProductOwner(java.lang.String)
	 */
	public void setProductOwner(String productOwner) {
		this.productOwner = productOwner;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#getValidFrom()
	 */
	public Date getValidFrom() {
		return validFrom;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#setValidFrom(java.util.Date)
	 */
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#getValidUntil()
	 */
	public Date getValidUntil() {
		return validUntil;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#setValidUntil(java.util.Date)
	 */
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#getInfoText()
	 */
	public String getInfoText() {
		return infoText;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#setInfoText(java.lang.String)
	 */
	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#getExtension()
	 */
	@Override
	public IExtension getExtension() {
		return extensionData;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IVoucher#setExtension(org.uic.ticket.api.spec.IExtension)
	 */
	@Override
	public void setExtension(IExtension extensionData) {
		this.extensionData = extensionData;		
	}


    
    


}
