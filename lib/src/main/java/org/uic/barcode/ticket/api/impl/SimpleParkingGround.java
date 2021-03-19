/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IParkingGround;
import org.uic.barcode.ticket.api.spec.IStationCodeTable;
import org.uic.barcode.ticket.api.spec.IVatDetail;


/**
 * The Class SimpleParkingGround.
 */
public class SimpleParkingGround extends SimpleDocumentData implements IParkingGround {
	
		/** The parking ground id. */
		protected String parkingGroundId;
		
		/** The product owner. */
		protected String productOwner;

		/** The product id. */
		protected String productId;		
		
		/** The from parking date. */
		protected Date	fromParkingDate;
		
		/** The to parking date. */
		protected Date	toParkingDate;		
		
		/** The reference. */
		protected String	reference;
		
		/** The access code. */
		protected String 	accessCode;
			
		/** The location. */
		protected String 	location;     					        		
			
		/** The station. */
		protected String 	station;		

		/** The special information. */
		protected String 	specialInformation;
		
		/** The entry track. */
		protected String 	entryTrack;
		
		/** The number plate. */
		protected String 	numberPlate;
			                				        	
        /** The station code table. */
        protected IStationCodeTable stationCodeTable = IStationCodeTable.stationUIC;		
		
		/** The extension data. */
		protected IExtension   	extensionData;
		
	    /** The VAT details. */
	    protected Collection<IVatDetail>vatDetails = new LinkedHashSet<IVatDetail>();
	    
	    protected Long price;		

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getParkingGroundId()
		 */
		public String getParkingGroundId() {
			return parkingGroundId;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setParkingGroundId(java.lang.String)
		 */
		public void setParkingGroundId(String parkingGroundId) {
			this.parkingGroundId = parkingGroundId;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getFromParkingDate()
		 */
		public Date getFromParkingDate() {
			return fromParkingDate;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setFromParkingDate(java.util.Date)
		 */
		public void setFromParkingDate(Date parkingDate) {
			this.fromParkingDate = parkingDate;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getToParkingDate()
		 */
		public Date getToParkingDate() {
			if (toParkingDate==null) {
				return fromParkingDate;
			}
			
			return toParkingDate;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setToParkingDate(java.util.Date)
		 */
		public void setToParkingDate(Date parkingDate) {
			this.toParkingDate = parkingDate;
		}		
		
		
		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getReference()
		 */
		public String getReference() {
			return reference;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setReference(java.lang.String)
		 */
		public void setReference(String reference) {
			this.reference = reference;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getAccessCode()
		 */
		public String getAccessCode() {
			return accessCode;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setAccessCode(java.lang.String)
		 */
		public void setAccessCode(String accessCode) {
			this.accessCode = accessCode;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getLocation()
		 */
		public String getLocation() {
			return location;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setLocation(java.lang.String)
		 */
		public void setLocation(String location) {
			this.location = location;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getStation()
		 */
		public String getStation() {
			return station;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setStation(java.lang.String)
		 */
		public void setStation(String station) {
			this.station = station;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getSpecialInformation()
		 */
		public String getSpecialInformation() {
			return specialInformation;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setSpecialInformation(java.lang.String)
		 */
		public void setSpecialInformation(String specialInformation) {
			this.specialInformation = specialInformation;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getEntryTrack()
		 */
		public String getEntryTrack() {
			return entryTrack;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setEntryTrack(java.lang.String)
		 */
		public void setEntryTrack(String entryTrack) {
			this.entryTrack = entryTrack;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getNumberPlate()
		 */
		public String getNumberPlate() {
			return numberPlate;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setNumberPlate(java.lang.String)
		 */
		public void setNumberPlate(String numberPlate) {
			this.numberPlate = numberPlate;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getExtension()
		 */
		public IExtension getExtension() {
			return extensionData;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setExtension(org.uic.ticket.api.spec.IExtension)
		 */
		public void setExtension(IExtension extensionData) {
			this.extensionData = extensionData;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getStationCodeTable()
		 */
		public IStationCodeTable getStationCodeTable() {
			return stationCodeTable;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setStationCodeTable(org.uic.ticket.api.asn.om.CodeTableType)
		 */
		public void setStationCodeTable(IStationCodeTable stationCodeTable) {
			this.stationCodeTable = stationCodeTable;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getProductOwner()
		 */
		public String getProductOwner() {
			return productOwner;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setProductOwner(java.lang.String)
		 */
		public void setProductOwner(String productOwner) {
			this.productOwner = productOwner;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#getProductId()
		 */
		@Override
		public String getProductId() {
			return productId;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IParkingGround#setProductId(java.lang.String)
		 */
		@Override
		public void setProductId(String productId) {
			this.productId = productId;			
		}			        		

		@Override
		public void setPrice(Long price) {
			this.price = price;
		}

		@Override
		public Long getPrice() {
			return price;
		}

		@Override
		public Collection<IVatDetail> getVatDetails() {
			return vatDetails;
		}

		@Override
		public void addVatDetail(IVatDetail vatDetail) {
			this.vatDetails.add(vatDetail);
		}

}
