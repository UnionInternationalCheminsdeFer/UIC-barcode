/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.HashSet;

import org.uic.barcode.ticket.api.spec.ILuggageRestriction;
import org.uic.barcode.ticket.api.spec.IRegisteredLuggage;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleLuggageRestriction.
 */
public class SimpleLuggageRestriction implements ILuggageRestriction {

	/** The max hand luggage pieces. */
	protected int maxHandLuggagePieces = 3;
	
	/** The max non hand luggage pieces. */
	protected int maxNonHandLuggagePieces= 1;
	
	/** The registered luggage. */
	protected Collection<IRegisteredLuggage> registeredLuggage	= new HashSet<IRegisteredLuggage>();
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILuggageRestriction#getMaxHandLuggagePieces()
	 */
	public int getMaxHandLuggagePieces() {
		return maxHandLuggagePieces;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILuggageRestriction#setMaxHandLuggagePieces(int)
	 */
	public void setMaxHandLuggagePieces(int maxHandLuggagePieces) {
		this.maxHandLuggagePieces = maxHandLuggagePieces;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILuggageRestriction#getMaxNonHandLuggagePieces()
	 */
	public int getMaxNonHandLuggagePieces() {
		return maxNonHandLuggagePieces;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILuggageRestriction#setMaxNonHandLuggagePieces(int)
	 */
	public void setMaxNonHandLuggagePieces(int maxNonHandLuggagePieces) {
		this.maxNonHandLuggagePieces = maxNonHandLuggagePieces;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILuggageRestriction#getRegisteredLuggage()
	 */
	public Collection<IRegisteredLuggage> getRegisteredLuggage() {
		return registeredLuggage;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ILuggageRestriction#addRegisteredLuggage(org.uic.ticket.api.spec.IRegisteredLuggage)
	 */
	public void addRegisteredLuggage(IRegisteredLuggage registeredLuggage) {
		this.registeredLuggage.add(registeredLuggage);
	}
	
	
	
}
