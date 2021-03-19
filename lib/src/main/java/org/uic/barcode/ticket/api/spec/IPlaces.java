/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;

/**
 * The Interface IPlaces.
 * 
 * IPlaces provides data on reserved places
 */
public interface IPlaces {
	
	/**
	 * Gets the coach.
	 *
	 * @return the coach
	 */
	public String getCoach() ;

	/**
	 * Sets the coach.
	 *
	 * @param coach the new coach
	 */
	public void setCoach(String coach) ;
	
	/**
	 * Gets the human readable place string.
	 *  E.g.: "15-18, 21, 22"	
	 *
	 * @return the place string
	 */
	public String getPlaceString() ;
	
	/**
	 * Sets the the human readable place string.
	 *  E.g.: "15-18, 21, 22"	
	 *
	 *  This elements should be avoided if not explicitly required for a special product.
	 *  	 
	 * @param placeString the new place string
	 */
	public void setPlaceString(String placeString);

	/**
	 * Gets the human readable place description.
	 *  E.g. "2 Window, open space"
	 *  
	 *  This elements should be avoided if not explicitly required for a special product.
	 *
	 * @return the human readable place description
	 */
	public String getPlaceDescription();

	/**
	 * Sets the human readable place description.
	 *  E.g. "2 Window, open space"
	 *
	 * @param placeDescription the new place description
	 */
	public void setPlaceDescription(String placeDescription);

	/**
	 * Gets the places.
	 *
	 * @return the places
	 */
	public Collection<String> getPlaces();

	/**
	 * Adds the place.
	 *
	 * @param place the place
	 */
	public void addPlace(String place);
	

}
