package org.uic.barcode.ticket.api.impl;

import java.util.Date;

import org.uic.barcode.ticket.api.spec.IValidityRange;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleValidityRange.
 */
public class SimpleValidityRange implements IValidityRange {
	
	/** The from date. */
	private Date fromDate;
	
	/** The until date. */
	private Date untilDate;
	


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IValidityRange#getFromDate()
	 */
	@Override
	public Date getFromDate() {
		return fromDate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IValidityRange#getUntilDate()
	 */
	@Override
	public Date getUntilDate() {
		return untilDate;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IValidityRange#setFromDate(java.util.Date)
	 */
	@Override
	public void setFromDate(Date date) {
		fromDate = date;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IValidityRange#setUntilDate(java.util.Date)
	 */
	@Override
	public void setUntilDate(Date date) {
		untilDate = date;
	}
	
	/** The valid from utc coffset. */
	protected Long validFromUTCoffset;
	
	/** The valid until utc coffset. */
	protected Long validUntilUTCoffset;
	

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IValidityRange#getValidFromUTCoffset()
	 */
	public Long getValidFromUTCoffset() {
		return validFromUTCoffset;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IValidityRange#setValidFromUTCoffset(java.lang.Long)
	 */
	public void setValidFromUTCoffset(Long validFromUTCoffset) {
		this.validFromUTCoffset = validFromUTCoffset;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IValidityRange#getValidUntilUTCoffset()
	 */
	public Long getValidUntilUTCoffset() {
		return validUntilUTCoffset;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IValidityRange#setValidUntilUTCoffset(java.lang.Long)
	 */
	public void setValidUntilUTCoffset(Long validUntilUTCoffset) {
		this.validUntilUTCoffset = validUntilUTCoffset;
	}
	
	

}
