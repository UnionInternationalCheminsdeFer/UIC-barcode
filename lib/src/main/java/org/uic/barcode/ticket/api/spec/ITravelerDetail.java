/*
 * 
 */
package org.uic.barcode.ticket.api.spec;


import java.util.Set;

/**
 * The Interface ITravelerDetail.
 * 
 * ITravelerDetail provides a list of traveler data.
 */
public interface ITravelerDetail {
	
	/**
	 * Gets the group name.
	 *
	 * @return the group name
	 */
	public String getGroupName() ;
	
	/**
	 * Sets the group name.
	 *
	 * @param groupName the new group name
	 */
	public void setGroupName(String groupName) ;
	
	/**
	 * Adds the traveler.
	 *
	 * @param traveler the traveler
	 */
	public void addTraveler (ITraveler traveler) ;
	
	/**
	 * Gets the travelers.
	 *
	 * @return the travelers
	 */
	public Set<ITraveler> getTravelers() ;
	
	/**
	 * Gets the preferred language.
	 * ISO 639-1 coding of the language preferred for the traveler / ticket holder
	 *
	 * @return the preferred language
	 */
	public String getPreferredLanguage();
	
	/**
	 * Sets the preferred language.
	 * ISO 639-1 coding of the language preferred for the traveler / ticket holder
	 *
	 * @param language the new preferred language
	 */
	public void setPreferredLanguage(String language);	
	

}
