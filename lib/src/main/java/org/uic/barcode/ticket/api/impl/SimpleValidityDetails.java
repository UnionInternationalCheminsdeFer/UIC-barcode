package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.ITimeRange;
import org.uic.barcode.ticket.api.spec.IValidityDetails;
import org.uic.barcode.ticket.api.spec.IValidityRange;

public class SimpleValidityDetails implements IValidityDetails {
	
	
	private Collection<IValidityRange> validityRanges = new LinkedHashSet<IValidityRange>();
	
	private Collection<ITimeRange> timeRanges = new LinkedHashSet<ITimeRange>();	

	@Override
	public Collection<IValidityRange> getValidityRanges() {
		return validityRanges;
	}

	@Override
	public Collection<ITimeRange> getTimeRanges() {
		return timeRanges;
	}

	@Override
	public void addValidityRanges(IValidityRange range) {
		this.validityRanges.add(range);
	}

	@Override
	public void addTimeRanges(ITimeRange range) {	
		this.timeRanges.add(range);
	}

}
