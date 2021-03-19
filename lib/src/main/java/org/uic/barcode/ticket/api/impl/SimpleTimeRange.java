package org.uic.barcode.ticket.api.impl;



import org.uic.barcode.ticket.api.spec.ITimeRange;

public class SimpleTimeRange implements ITimeRange {
	
	int fromTime = 0;
	int untilTime = 1440;

	@Override
	public int getFromTime() {
		return fromTime;
	}

	@Override
	public int getUntilTime() {
		return untilTime;
	}

	@Override
	public void setFromTime(int minutes) {
		this.fromTime = minutes;	
	}

	@Override
	public void setUntilTime(int minutes) {
		this.untilTime = minutes;
	}
	
	/** The valid from utc coffset. */
	protected Long validFromUTCoffset;
	
	/** The valid until utc coffset. */
	protected Long validUntilUTCoffset;
	
	public Long getValidFromUTCoffset() {
		return validFromUTCoffset;
	}

	public void setValidFromUTCoffset(Long validFromUTCoffset) {
		this.validFromUTCoffset = validFromUTCoffset;
	}

	public Long getValidUntilUTCoffset() {
		return validUntilUTCoffset;
	}

	public void setValidUntilUTCoffset(Long validUntilUTCoffset) {
		this.validUntilUTCoffset = validUntilUTCoffset;
	}



}
