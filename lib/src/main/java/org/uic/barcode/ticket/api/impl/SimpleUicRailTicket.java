/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.ICarCarriageReservation;
import org.uic.barcode.ticket.api.spec.IControlDetail;
import org.uic.barcode.ticket.api.spec.ICounterMark;
import org.uic.barcode.ticket.api.spec.ICustomerCard;
import org.uic.barcode.ticket.api.spec.IDelayConfirmation;
import org.uic.barcode.ticket.api.spec.IDocumentData;
import org.uic.barcode.ticket.api.spec.IDocumentExtension;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IFipTicket;
import org.uic.barcode.ticket.api.spec.IIssuingDetail;
import org.uic.barcode.ticket.api.spec.IOpenTicket;
import org.uic.barcode.ticket.api.spec.IParkingGround;
import org.uic.barcode.ticket.api.spec.IPass;
import org.uic.barcode.ticket.api.spec.IReservation;
import org.uic.barcode.ticket.api.spec.IStationPassage;
import org.uic.barcode.ticket.api.spec.ITravelerDetail;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.ticket.api.spec.IVoucher;


/**
 * The Class SimpleUicRailTicket.
 */
public class SimpleUicRailTicket implements IUicRailTicket {
	
	/** The issuer details. */
	private IIssuingDetail issuerDetails = new SimpleIssuingDetail();
	
	/** The traveler details. */
	private ITravelerDetail travelerDetails;	
	
	/** The control details. */
	private IControlDetail controlDetails;	
		
	/** The extensions. */
	private Collection<IExtension> extensions = new LinkedHashSet<IExtension>();
	
	/** The documents. */
	private Collection<IDocumentData> documents = new LinkedHashSet<IDocumentData>();

	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.IUicRailTicket#getIssuerDetails()
	 */
	@Override
	public IIssuingDetail getIssuerDetails() {
		
		if (issuerDetails == null) {
			issuerDetails = new SimpleIssuingDetail();
		}
		return issuerDetails;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.IUicRailTicket#getTravelerDetails()
	 */
	public ITravelerDetail getTravelerDetails() {
		
		if (travelerDetails == null) {
			travelerDetails = new SimpleTravelerDetail();
		}		
		return travelerDetails;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#getControlDetails()
	 */
	public IControlDetail getControlDetails() {
		
		if (controlDetails == null) {
			controlDetails = new SimpleControlDetail();
		}			
		return controlDetails;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#getExtensions()
	 */
	public Collection<IExtension> getExtensions() {
		return extensions;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#addExtension(org.uic.ticket.api.spec.IExtension)
	 */
	public void addExtension(IExtension extension) {
		this.extensions.add(extension);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#setIssuerDetails(org.uic.ticket.api.spec.IIssuingDetail)
	 */
	public void setIssuerDetails(IIssuingDetail issuerDetails) {
		this.issuerDetails = issuerDetails;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#setTravelerDetails(org.uic.ticket.api.spec.ITravelerDetail)
	 */
	public void setTravelerDetails(ITravelerDetail travelerDetails) {
		this.travelerDetails = travelerDetails;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#setControlDetails(org.uic.ticket.api.spec.IControlDetail)
	 */
	public void setControlDetails(IControlDetail controlDetails) {
		this.controlDetails = controlDetails;
	}


	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#getDocumentData()
	 */
	@Override
	public Collection<IDocumentData> getDocumentData() {
		return this.documents;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#addReservation(org.uic.ticket.api.spec.IReservation)
	 */
	@Override
	public void addReservation(IReservation document) {
		this.documents.add(document);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#addOpenTicket(org.uic.ticket.api.spec.IOpenTicket)
	 */
	@Override
	public void addOpenTicket(IOpenTicket document) {
		this.documents.add(document);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#addCarCarriageReservation(org.uic.ticket.api.spec.ICarCarriageReservation)
	 */
	@Override
	public void addCarCarriageReservation(ICarCarriageReservation document) {
		this.documents.add(document);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#addPass(org.uic.ticket.api.spec.IPass)
	 */
	@Override
	public void addPass(IPass document) {
		this.documents.add(document);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#addVoucher(org.uic.ticket.api.spec.IVoucher)
	 */
	@Override
	public void addVoucher(IVoucher document) {
		this.documents.add(document);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#addCustomerCard(org.uic.ticket.api.spec.ICustomerCard)
	 */
	@Override
	public void addCustomerCard(ICustomerCard document) {
		this.documents.add(document);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#addCounterMark(org.uic.ticket.api.spec.ICounterMark)
	 */
	@Override
	public void addCounterMark(ICounterMark document) {
		this.documents.add(document);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#addParkingGround(org.uic.ticket.api.spec.IParkingGround)
	 */
	@Override
	public void addParkingGround(IParkingGround document) {
		this.documents.add(document);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#addFipTicket(org.uic.ticket.api.spec.IFipTicket)
	 */
	@Override
	public void addFipTicket(IFipTicket document) {
		this.documents.add(document);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#addStationPassage(org.uic.ticket.api.spec.IStationPassage)
	 */
	@Override
	public void addStationPassage(IStationPassage document) {
		this.documents.add(document);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IUicRailTicket#addDocumentExtension(org.uic.ticket.api.spec.IDocumentExtension)
	 */
	@Override
	public void addDocumentExtension(IDocumentExtension document) {
		this.documents.add(document);
	}

	@Override
	public void addDelayConfirmation(IDelayConfirmation document) {
		this.documents.add(document);
	}


}
