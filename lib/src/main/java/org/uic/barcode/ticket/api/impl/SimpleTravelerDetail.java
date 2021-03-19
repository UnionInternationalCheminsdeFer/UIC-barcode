/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.uic.barcode.ticket.api.spec.ITraveler;
import org.uic.barcode.ticket.api.spec.ITravelerDetail;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleTravelerDetail.
 */
public class SimpleTravelerDetail implements ITravelerDetail{
	
	/** The group name. */
	String groupName;

	/** The preferred language. */
	String preferredLanguage;	
	
	/** The travelers. */
	LinkedHashSet<ITraveler> travelers = new LinkedHashSet<ITraveler>();

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.ITravelerDetail#getGroupName()
	 */
	public String getGroupName() {
		return groupName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.ITravelerDetail#setGroupName(java.lang.String)
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.ITravelerDetail#addTraveler(org.uic.ticket.api.ITravelerData)
	 */
	public void addTraveler (ITraveler traveler) {
		travelers.add(traveler);
	}

	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.ITravelerDetail#getTravelers()
	 */
	public Set<ITraveler> getTravelers() {
		return travelers;
	}	

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.ITravelerDetail#setTravelers(java.util.LinkedHashSet)
	 */
	/**
	 * Sets the travelers.
	 *
	 * @param travelers the new travelers
	 */
	public void setTravelers(LinkedHashSet<ITraveler> travelers) {
		this.travelers = travelers;
	}


	@Override
	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	@Override
	public void setPreferredLanguage(String language) {
		this.preferredLanguage = language;		
	}



	
	
}
