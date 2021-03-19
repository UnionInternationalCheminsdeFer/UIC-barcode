/*
 * 
 */
package org.uic.barcode.ticket.api.impl;


import org.uic.barcode.ticket.api.spec.IGeoCoordinate;
import org.uic.barcode.ticket.api.spec.IGeoCoordinateSystemType;
import org.uic.barcode.ticket.api.spec.IGeoUnitType;
import org.uic.barcode.ticket.api.spec.IHemisphereLatitudeType;
import org.uic.barcode.ticket.api.spec.IHemisphereLongitudeType;


/**
 * The Class SimpleGeoCoordinate.
 */
public class SimpleGeoCoordinate implements IGeoCoordinate {
	
	/** The unit. */
	protected IGeoUnitType unit = IGeoUnitType.milliDegree;

	/** The accuracy. */
	protected IGeoUnitType accuracy;
	
	/** The system. */
	protected IGeoCoordinateSystemType system = IGeoCoordinateSystemType.wgs84;
	
	/** The hemispher longiture. */
	protected IHemisphereLongitudeType hemispherLongiture = IHemisphereLongitudeType.north;
	
	/** The hemisphere latitude. */
	protected IHemisphereLatitudeType hemisphereLatitude = IHemisphereLatitudeType.east;
	
	/** The longitude. */
	protected Long longitude;
	
	/** The latitude. */
	protected Long latitude;

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#getUnit()
	 */
	public IGeoUnitType getUnit() {
		return unit;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#setUnit(org.uic.ticket.api.asn.om.GeoUnitType)
	 */
	public void setUnit(IGeoUnitType unit) {
		this.unit = unit;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#getAccuracy()
	 */
	public IGeoUnitType getAccuracy() {
		return accuracy;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#setAccuracy(org.uic.ticket.api.asn.om.GeoUnitType)
	 */
	public void setAccuracy(IGeoUnitType accuracy) {
		this.accuracy = accuracy;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#getSystem()
	 */
	public IGeoCoordinateSystemType getSystem() {
		return system;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#setSystem(org.uic.ticket.api.asn.om.GeoCoordinateSystemType)
	 */
	public void setSystem(IGeoCoordinateSystemType system) {
		this.system = system;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#getHemispherLongitude()
	 */
	public IHemisphereLongitudeType getHemisphereLongitude() {
		return hemispherLongiture;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#setHemispherLongitude(org.uic.ticket.api.asn.om.HemisphereLongitudeType)
	 */
	public void setHemisphereLongitude(IHemisphereLongitudeType hemispherLongiture) {
		this.hemispherLongiture = hemispherLongiture;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#getHemisphereLatitude()
	 */
	public IHemisphereLatitudeType getHemisphereLatitude() {
		return hemisphereLatitude;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IGeoCoordinate#setHemisphereLatitude(org.uic.ticket.api.asn.om.HemisphereLatitudeType)
	 */
	public void setHemisphereLatitude(IHemisphereLatitudeType hemisphereLatitude) {
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
		if (unit == IGeoUnitType.centiDegree) {
			return 100 * 360;
		} else if (unit == IGeoUnitType.deciDegree) {
			return 10 * 360;
		} else if (unit == IGeoUnitType.milliDegree) {
			return 1000 * 360;
		} else if (unit == IGeoUnitType.tenthmilliDegree) {
			return 10000 * 360;
		} else if (unit == IGeoUnitType.milliDegree) {
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
