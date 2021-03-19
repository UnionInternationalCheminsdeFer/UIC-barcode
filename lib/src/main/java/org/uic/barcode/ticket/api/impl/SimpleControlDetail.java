/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.ICardReference;
import org.uic.barcode.ticket.api.spec.IControlDetail;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.ITicketLink;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleControlDetail.
 */
public class SimpleControlDetail implements IControlDetail {
	
	
		/** The identification by card reference. */
		protected Collection <ICardReference> identificationByCardReference = new LinkedHashSet<ICardReference>();

		/** The identification by id card. */
		protected boolean identificationByIdCard = false;   				
		
		/** The identification by passport id. */
		protected boolean identificationByPassportId  = false;    									
		
		/** The identification item. */
		protected int identificationItem  = 0;
		
		/** The passport validation required. */
		protected boolean passportValidationRequired  = false;
		
		/** The online validation required. */
		protected boolean onlineValidationRequired = false; 
		
		/** The random online validation required. */
		protected int randomDetailedValidationRequired = 0;
		
		/** The age check required. */
		protected boolean ageCheckRequired         = false;
		
		/** The reduction card check required. */
		protected boolean reductionCardCheckRequired  = false;
		
		/** The info text. */
		protected String infoText	;
		
		/** The linked tickets. */
		protected Collection<ITicketLink> linkedTickets	= new HashSet<ITicketLink>();
 
		/** The extension. */
		protected IExtension extension ;

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#getIdentificationByCardReference()
		 */
		public Collection<ICardReference> getIdentificationByCardReference() {
			return identificationByCardReference;
		}

	
		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#addIdentificationByCardReference(org.uic.ticket.api.spec.ICardReference)
		 */
		public void addIdentificationByCardReference(ICardReference cardReference) {
			this.identificationByCardReference.add(cardReference);
		}		

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#isIdentificationByIdCard()
		 */
		public boolean isIdentificationByIdCard() {
			return identificationByIdCard;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#setIdentificationByIdCard(boolean)
		 */
		public void setIdentificationByIdCard(boolean identificationByIdCard) {
			this.identificationByIdCard = identificationByIdCard;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#isIdentificationByPassportId()
		 */
		public boolean isIdentificationByPassportId() {
			return identificationByPassportId;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#setIdentificationByPassportId(boolean)
		 */
		public void setIdentificationByPassportId(boolean identificationByPassportId) {
			this.identificationByPassportId = identificationByPassportId;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#getIdentificationItem()
		 */
		public int getIdentificationItem() {
			return identificationItem;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#setIdentificationItem(int)
		 */
		public void setIdentificationItem(int identificationItem) {
			this.identificationItem = identificationItem;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#isPassportValidationRequired()
		 */
		public boolean isPassportValidationRequired() {
			return passportValidationRequired;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#setPassportValidationRequired(boolean)
		 */
		public void setPassportValidationRequired(boolean passportValidationRequired) {
			this.passportValidationRequired = passportValidationRequired;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#isOnlineValidationRequired()
		 */
		public boolean isOnlineValidationRequired() {
			return onlineValidationRequired;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#setOnlineValidationRequired(boolean)
		 */
		public void setOnlineValidationRequired(boolean onlineValidationRequired) {
			this.onlineValidationRequired = onlineValidationRequired;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#getRandomOnlineValidationRequired()
		 */
		public int getRandomDetailedValidationRequired() {
			return randomDetailedValidationRequired;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#setRandomOnlineValidationRequired(int)
		 */
		public void setRandomDetailedValidationRequired(int randomDetailedValidationRequired) {
			this.randomDetailedValidationRequired = randomDetailedValidationRequired;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#isAgeCheckRequired()
		 */
		public boolean isAgeCheckRequired() {
			return ageCheckRequired;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#setAgeCheckRequired(boolean)
		 */
		public void setAgeCheckRequired(boolean ageCheckRequired) {
			this.ageCheckRequired = ageCheckRequired;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#isReductionCardCheckRequired()
		 */
		public boolean isReductionCardCheckRequired() {
			return reductionCardCheckRequired;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#setReductionCardCheckRequired(boolean)
		 */
		public void setReductionCardCheckRequired(boolean reductionCardCheckRequired) {
			this.reductionCardCheckRequired = reductionCardCheckRequired;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#getInfoText()
		 */
		public String getInfoText() {
			return infoText;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#setInfoText(java.lang.String)
		 */
		public void setInfoText(String infoText) {
			this.infoText = infoText;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#getLinkedTickets()
		 */
		public Collection<ITicketLink> getLinkedTickets() {
			return linkedTickets;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#addLinkedTicket(org.uic.ticket.api.spec.ITicketLink)
		 */
		public void addLinkedTicket(ITicketLink linkedTicket) {
			this.linkedTickets.add(linkedTicket);
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#getExtension()
		 */
		public IExtension getExtension() {
			return extension;
		}

		/* (nicht-Javadoc)
		 * @see org.uic.ticket.api.spec.IControlDetail#setExtension(org.uic.ticket.api.spec.IExtension)
		 */
		public void setExtension(IExtension extension) {
			this.extension = extension;
		}                       

		

}
