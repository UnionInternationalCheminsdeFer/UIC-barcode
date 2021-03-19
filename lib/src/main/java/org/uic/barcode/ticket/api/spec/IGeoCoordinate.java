/*
 * 
 */
package org.uic.barcode.ticket.api.spec;



/**
 * The Interface IGeoCoordinate.
 * 
 * IGeoCoordinate provides the data for a geo coordinate.
 * 
 */
public interface IGeoCoordinate {
	
	/**
	 * Gets the unit.
	 * 
	 * Default is milliDegree
	 *
	 * @return the unit
	 */
	public IGeoUnitType getUnit();
	
	/**
	 * Sets the unit.
	 *
	 * Default is milliDegree
	 * 	 
	 * @param unit the new unit
	 */
	public void setUnit(IGeoUnitType unit);
	
	/**
	 * Gets the accuracy.
	 *
	 * @return the accuracy
	 */
	public IGeoUnitType getAccuracy();
	
	/**
	 * Sets the accuracy.
	 *
	 * @param accuracy the new accuracy
	 */
	public void setAccuracy(IGeoUnitType accuracy);
	
	/**
	 * Gets the geo coordinate system.
	 * 
	 * Default is wgs84
	 *
	 * @return the geo coordinate 
	 */
	public IGeoCoordinateSystemType getSystem();
	
	/**
	 * Sets the geo coordinate 
	 * 
	 * Default is wgs84 
	 *
	 * @param system the new geo coordinate 
	 */
	public void setSystem(IGeoCoordinateSystemType system);
	
	/**
	 * Gets the longitude hemisphere.
	 *
	 * @return the longitude hemisphere
	 */
	public IHemisphereLongitudeType getHemisphereLongitude() ;
	
	/**
	 * Sets the longitude hemisphere.
	 * 
	 * Default is north
	 *
	 * @param hemispherLongiture the new longitude hemisphere.
	 */
	public void setHemisphereLongitude(IHemisphereLongitudeType hemispherLongiture);
	
	/**
	 * Gets the latitude hemisphere.
	 * 
	 * Default is east
	 *
	 * @return the latitude hemisphere.
	 */
	public IHemisphereLatitudeType getHemisphereLatitude();
	
	/**
	 * Sets the latitude hemisphere.
	 * 
	 * Default is east
	 * 
	 * @param hemisphereLatitude the new latitude hemisphere.
	 */
	public void setHemisphereLatitude(IHemisphereLatitudeType hemisphereLatitude);
	
	/**
	 * Gets the longitude.
	 * 
	 * @return the longitude
	 */
	public Long getLongitude();
	
	/**
	 * Sets the longitude.
	 * 
	 * @param longitude the new longitude
	 */
	public void setLongitude(long longitude) ;
	
	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public Long getLatitude() ;
	
	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(long latitude);

	public IGeoCoordinate clone();

	public void addLongitude(Long longValue);

	public void addLatitude(Long longValue);
}
