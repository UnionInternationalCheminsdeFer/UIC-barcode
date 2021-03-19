/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.uic.barcode.ticket.api.spec.ICustomerStatusDescription;
import org.uic.barcode.ticket.api.spec.IGenderType;
import org.uic.barcode.ticket.api.spec.IPassengerType;
import org.uic.barcode.ticket.api.spec.ITraveler;


/**
 * The Class SimpleTraveler.
 */
public class SimpleTraveler implements ITraveler {
	
	
	/** The first name. */
	protected String  	firstName;
	
	/** The second name. */
	protected String 	secondName;
	
	/** The last name. */
	protected String 	lastName;    										     
	
	/** The id card. */
	protected String 	idCard;
	
	/** The passport id. */
	protected String 	passportId;		
	
	/** The title. */
	protected String 	title;
	
	/** The gender. */
	protected IGenderType 	gender;									
	
	/** The customer id. */
	protected String 	customerId;		
	
	/** The date of birth. */
	protected Date 		dateOfBirth;
	
	/** The ticket holder. */
	protected boolean	ticketHolder = true;								
	
	/** The passenger type. */
	protected IPassengerType passengerType;
	
	/** The passenger with reduced mobility. */
	protected boolean 	passengerWithReducedMobility;
	
	/** The country of residence. */
	protected int 		countryOfResidence  = 0;

	
	/** The country of passport. */
	protected int 		countryOfPassport  = 0;	
	
	/** The country of id card. */
	protected int 		countryOfIDCard  = 0;
	
	/** The status collection. */
	protected Collection<ICustomerStatusDescription> statusCollection = new HashSet<ICustomerStatusDescription>();
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#getFirstName()
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#getSecondName()
	 */
	public String getSecondName() {
		return secondName;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setSecondName(java.lang.String)
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#getLastName()
	 */
	public String getLastName() {
		return lastName;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#getIdCard()
	 */
	public String getIdCard() {
		return idCard;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setIdCard(java.lang.String)
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#getPassportId()
	 */
	public String getPassportId() {
		return passportId;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setPassportId(java.lang.String)
	 */
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#getTitle()
	 */
	public String getTitle() {
		return title;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#getGender()
	 */
	public IGenderType getGender() {
		return gender;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setGender(org.uic.ticket.api.asn.om.GenderType)
	 */
	public void setGender(IGenderType gender) {
		this.gender = gender;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#getCustomerId()
	 */
	public String getCustomerId() {
		return customerId;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setCustomerId(java.lang.String)
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#getDateOfBirth()
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setDateOfBirth(java.util.Date)
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#isTicketHolder()
	 */
	public boolean isTicketHolder() {
		return ticketHolder;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setTicketHolder(boolean)
	 */
	public void setTicketHolder(boolean ticketHolder) {
		this.ticketHolder = ticketHolder;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#getPassengerType()
	 */
	public IPassengerType getPassengerType() {
		return passengerType;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setPassengerType(org.uic.ticket.api.asn.om.PassengerType)
	 */
	public void setPassengerType(IPassengerType passengerType) {
		this.passengerType = passengerType;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#isPassengerWithReducedMobility()
	 */
	public Boolean isPassengerWithReducedMobility() {
		return passengerWithReducedMobility;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setPassengerWithReducedMobility(boolean)
	 */
	public void setPassengerWithReducedMobility(Boolean passengerWithReducedMobility) {
		this.passengerWithReducedMobility = passengerWithReducedMobility;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#getCountryOfResidence()
	 */
	public int getCountryOfResidence() {
		return countryOfResidence;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#setCountryOfResidence(int)
	 */
	public void setCountryOfResidence(int countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#getStatusCollection()
	 */
	public Collection<ICustomerStatusDescription> getStatusCollection() {
		return statusCollection;
	}
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.ITraveler#addStatusDescription(org.uic.ticket.api.spec.ICustomerStatusDescription)
	 */
	public void addStatusDescription(ICustomerStatusDescription statusDescription) {
		this.statusCollection.add(statusDescription);
	}

	@Override
	public int getPassportCountry() {
		return countryOfPassport;
	}

	@Override
	public void setPassportCountry(int country) {
		this.countryOfPassport = country;
	}

	@Override
	public int getIDCardCountry() {
		return countryOfIDCard;
	}

	@Override
	public void setIDCardCountry(int country) {
		this.countryOfIDCard = country;
	} 

	
	
}
