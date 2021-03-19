/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;

// TODO: Auto-generated Javadoc
/**
 * The Interface ILuggageRestriction.
 */
public interface ILuggageRestriction {
	
	/**
	 * Gets the max hand luggage pieces.
	 *
	 * @return the max hand luggage pieces
	 */
	public int getMaxHandLuggagePieces();
	
	/**
	 * Sets the max hand luggage pieces.
	 *
	 * @param maxHandLuggagePieces the new max hand luggage pieces
	 */
	public void setMaxHandLuggagePieces(int maxHandLuggagePieces);
	
	/**
	 * Gets the max non hand luggage pieces.
	 *
	 * @return the max non hand luggage pieces
	 */
	public int getMaxNonHandLuggagePieces();
	
	/**
	 * Sets the max non hand luggage pieces.
	 *
	 * @param maxNonHandLuggagePieces the new max non hand luggage pieces
	 */
	public void setMaxNonHandLuggagePieces(int maxNonHandLuggagePieces);
	
	/**
	 * Gets the registered luggage.
	 *
	 * @return the registered luggage
	 */
	public Collection<IRegisteredLuggage> getRegisteredLuggage();
	
	/**
	 * Adds the registered luggage.
	 *
	 * @param registeredLuggage the registered luggage
	 */
	public void addRegisteredLuggage(IRegisteredLuggage registeredLuggage);

}
