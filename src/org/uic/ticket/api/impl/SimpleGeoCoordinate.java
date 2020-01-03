/*
 * 
 */
package org.uic.ticket.api.impl;

import org.uic.ticket.api.asn.omv1.GeoCoordinateSystemType;
import org.uic.ticket.api.asn.omv1.GeoUnitType;
import org.uic.ticket.api.asn.omv1.HemisphereLatitudeType;
import org.uic.ticket.api.asn.omv1.HemisphereLongitudeType;
import org.uic.ticket.api.spec.IGeoCoordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleGeoCoordinate.
 */
public class SimpleGeoCoordinate implements IGeoCoordinate {
	
	/** The unit. */
	protected GeoUnitType unit = GeoUnitType.milliDegree;

	/** The accuracy. */
	protected GeoUnitType accuracy;
	
	/** The system. */
	protected GeoCoordinateSystemType system = GeoCoordinateSystemType.wgs84;
	
	/** The hemispher longiture. */
	protected HemisphereLongitudeType hemispherLongiture = HemisphereLongitudeType.north;
	
	/** The hemisphere latitude. */
	protected HemisphereLatitudeType hemisphereLatitude = HemisphereLatitudeType.east;
	
	/** The longitude. */
	protected Long longitude;
	
	/** The latitude. */
	protected Long latitude;

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#getUnit()
	 */
	public GeoUnitType getUnit() {
		return unit;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#setUnit(org.uic.ticket.api.asn.om.GeoUnitType)
	 */
	public void setUnit(GeoUnitType unit) {
		this.unit = unit;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#getAccuracy()
	 */
	public GeoUnitType getAccuracy() {
		return accuracy;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#setAccuracy(org.uic.ticket.api.asn.om.GeoUnitType)
	 */
	public void setAccuracy(GeoUnitType accuracy) {
		this.accuracy = accuracy;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#getSystem()
	 */
	public GeoCoordinateSystemType getSystem() {
		return system;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#setSystem(org.uic.ticket.api.asn.om.GeoCoordinateSystemType)
	 */
	public void setSystem(GeoCoordinateSystemType system) {
		this.system = system;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#getHemispherLongitude()
	 */
	public HemisphereLongitudeType getHemisphereLongitude() {
		return hemispherLongiture;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#setHemispherLongitude(org.uic.ticket.api.asn.om.HemisphereLongitudeType)
	 */
	public void setHemisphereLongitude(HemisphereLongitudeType hemispherLongiture) {
		this.hemispherLongiture = hemispherLongiture;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#getHemisphereLatitude()
	 */
	public HemisphereLatitudeType getHemisphereLatitude() {
		return hemisphereLatitude;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#setHemisphereLatitude(org.uic.ticket.api.asn.om.HemisphereLatitudeType)
	 */
	public void setHemisphereLatitude(HemisphereLatitudeType hemisphereLatitude) {
		this.hemisphereLatitude = hemisphereLatitude;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#getLongitude()
	 */
	public Long getLongitude() {
		return 	limitValues(longitude);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#setLongitude(long)
	 */
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#getLatitude()
	 */
	public Long getLatitude() {
		
		return limitValues(latitude);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#setLatitude(long)
	 */
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}	
	
	public long limitValues(long value){
				
		while (value < 0) {
			value = value + getFullCircle();
		}
		
		return value % getFullCircle();
		
	}
	
	public long getFullCircle(){
		if (unit == GeoUnitType.centiDegree) {
			return 100 * 360;
		} else if (unit == GeoUnitType.deciDegree) {
			return 10 * 360;
		} else if (unit == GeoUnitType.milliDegree) {
			return 1000 * 360;
		} else if (unit == GeoUnitType.tenthmilliDegree) {
			return 10000 * 360;
		} else if (unit == GeoUnitType.milliDegree) {
			return 100000 * 360;
		} else {
			return 360;
		}
		
	}
	
	
	/* (nicht-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public SimpleGeoCoordinate clone(){
		
		SimpleGeoCoordinate clone = new SimpleGeoCoordinate();
		
		clone.setSystem(this.getSystem());
		clone.setAccuracy(this.getAccuracy());
		clone.setHemisphereLatitude(this.getHemisphereLatitude());
		clone.setHemisphereLongitude(this.getHemisphereLongitude());
		clone.setLatitude(this.getLatitude());
		clone.setLongitude(this.getLongitude());
		clone.setUnit(this.getUnit());
		
		return clone;
		
		
	}

	/**
	 * Adds the longitude.
	 *
	 * @param l the longitude
	 */
	public void addLongitude(Long l) {
		long value = this.longitude + longitude;
		this.longitude = limitValues(value);
	}

	/**
	 * Adds the latitude.
	 *
	 * @param latitude the latitude
	 */
	public void addLatitude(Long latitude) {
		long value = this.latitude + latitude;
		this.latitude = limitValues(value);
	}

}
