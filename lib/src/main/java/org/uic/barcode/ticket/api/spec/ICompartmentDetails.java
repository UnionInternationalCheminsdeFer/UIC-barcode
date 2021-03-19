/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

/**
 * The Interface ICompartmentDetails.
 * 
 * ICompartmentDetails defines details on reserved place types 
 * 
 * The codes corresponds to UIC leaflet 918.1 element 51
 * 
 */
public interface ICompartmentDetails {

	/**
	 * Gets the coach type.
	 * 
	 * The codes corresponds to UIC leaflet 918.1 element 51 position 1 and 2
	 * 
	 * @return the coach type
	 */
	public int getCoachType();
	
	/**
	 * Sets the coach type.
	 * 
	 * The codes corresponds to UIC leaflet 918.1 element 51 position 1 and 2 
	 *
	 * @param coachType the new coach type
	 */
	public void setCoachType(int coachType);
	
	/**
	 * Gets the compartment type.
	 * 
	 * The codes corresponds to UIC leaflet 918.1 element 51 position 3 and 4	 
	 *
	 * @return the compartment type
	 */
	public int getCompartmentType() ;
	
	/**
	 * Sets the compartment type.
	 * 
	 * The codes corresponds to UIC leaflet 918.1 element 51 position 3 and 4	
	 *
	 * @param compartmentType the new compartment type
	 */
	public void setCompartmentType(int compartmentType);
	
	/**
	 * Gets the special allocation.
	 * 
	 * The codes corresponds to UIC leaflet 918.1 element 51 position 5 and 6	 
	 *
	 * @return the special allocation
	 */
	public int getSpecialAllocation() ;
	
	/**
	 * Sets the special allocation.
	 * 
	 * The codes corresponds to UIC leaflet 918.1 element 51 position 5 and 6	 	 
	 *
	 * @param specialAllocation the new special allocation
	 */
	public void setSpecialAllocation(int specialAllocation);
	
	/**
	 * Gets the coach type description.
	 *
	 * @return the coach type description
	 */
	public String getCoachTypeDescr();
	
	/**
	 * Sets the coach type description.
	 *
	 * @param coachTypeDescr the new coach type description
	 */
	public void setCoachTypeDescr(String coachTypeDescr);
	
	/**
	 * Gets the compartment type description.
	 *
	 * @return the compartment type description
	 */
	public String getCompartmentTypeDescr();
	
	/**
	 * Sets the compartment type description
	 *
	 * @param comparttmentTypeDescr the new compartment type description
	 */
	public void setCompartmentTypeDescr(String comparttmentTypeDescr);
	
	/**
	 * Gets the special allocation description.
	 *
	 * @return the special allocation description
	 */
	public String getSpecialAllocationDescr();
	
	/**
	 * Sets the special allocation description.
	 *
	 * @param specialAllocationDescr the new special allocation description
	 */
	public void setSpecialAllocationDescr(String specialAllocationDescr);
	
	/**
	 * Gets the position in trains with different decks.
	 *
	 * @return the position
	 */
	public ICompartmentPositionType getPosition();
	
	/**
	 * Sets the position of the compartment in trains with different decks
	 *
	 * @param position the new position
	 */
	public void setPosition(ICompartmentPositionType position);
	
}
