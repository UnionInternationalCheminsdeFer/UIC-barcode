/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.IPlaces;

// TODO: Auto-generated Javadoc
/**
 * The Class SimplePlaces.
 */
public class SimplePlaces implements IPlaces {
	
	/** The coach. */
	protected String coach;
	
	/** The place string. */
	protected String placeString;
	
	/** The place description. */
	protected String placeDescription;
	
	/** The places. */
	protected Collection<String> places = new LinkedHashSet<String>();

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IPlaces#getCoach()
	 */
	public String getCoach() {
		return coach;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IPlaces#setCoach(java.lang.String)
	 */
	public void setCoach(String coach) {
		this.coach = coach;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IPlaces#getPlaceString()
	 */
	public String getPlaceString() {
		return placeString;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IPlaces#setPlaceString(java.lang.String)
	 */
	public void setPlaceString(String placeString) {
		this.placeString = placeString;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IPlaces#getPlaceDescription()
	 */
	public String getPlaceDescription() {
		return placeDescription;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IPlaces#setPlaceDescription(java.lang.String)
	 */
	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IPlaces#getPlaces()
	 */
	public Collection<String> getPlaces() {
		return places;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IPlaces#addPlace(java.lang.String)
	 */
	public void addPlace(String place) {
		this.places.add(place);
	}
	
	
	
	

}
