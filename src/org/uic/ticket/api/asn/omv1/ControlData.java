/*
 *   This file was generated by openASN.1 - an open source ASN.1 toolkit for java
 *
 *   openASN.1 is Copyright (C) 2007 Clayton Hoss, Marc Weyland
 *
 *   openASN.1 is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as
 *   published by the Free Software Foundation, either version 3 of
 *   the License, or (at your option) any later version.
 *
 *   openASN.1 is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with openASN.1. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.uic.ticket.api.asn.omv1;

import net.gcdc.asn1.datatypes.Asn1BigInteger;

import net.gcdc.asn1.datatypes.Asn1Optional;
import net.gcdc.asn1.datatypes.CharacterRestriction;
import net.gcdc.asn1.datatypes.HasExtensionMarker;
import net.gcdc.asn1.datatypes.IntRange;
import net.gcdc.asn1.datatypes.RestrictedString;
import net.gcdc.asn1.datatypes.Sequence;



@Sequence
@HasExtensionMarker
public class ControlData extends Object {
	public ControlData() {
	}

	@Asn1Optional public SequenceOfCardReferenceType identificationByCardReference;

	public Boolean identificationByIdCard = false;

	public Boolean identificationByPassportId = false;

	@Asn1Optional public Asn1BigInteger identificationItem;

	public Boolean passportValidationRequired = false;

	public Boolean onlineValidationRequired = false;

	@IntRange(minValue=0,maxValue=99)
	@Asn1Optional public Long randomDetailedValidationRequired;

	public Boolean ageCheckRequired = false;

	public Boolean reductionCardCheckRequired = false;

	@RestrictedString(CharacterRestriction.UTF8String)
	@Asn1Optional public String infoText;

	@Asn1Optional public SequenceOfTicketLinkType includedTickets;

	@Asn1Optional public ExtensionData extension;
	



	public SequenceOfCardReferenceType getIdentificationByCardReference() {

		return this.identificationByCardReference;
	}

	public Boolean getIdentificationByIdCard() {

		return this.identificationByIdCard;
	}

	public Boolean getIdentificationByPassportId() {

		return this.identificationByPassportId;
	}

	public Long getIdentificationItem() {

		return Asn1BigInteger.toLong(this.identificationItem);
	}

	public Boolean getPassportValidationRequired() {

		return this.passportValidationRequired;
	}

	public Boolean getOnlineValidationRequired() {

		return this.onlineValidationRequired;
	}

	public Long getRandomDetailedValidationRequired() {

		return this.randomDetailedValidationRequired;
	}

	public Boolean getAgeCheckRequired() {

		return this.ageCheckRequired;
	}

	public Boolean getReductionCardCheckRequired() {

		return this.reductionCardCheckRequired;
	}

	public String getInfoText() {

		return this.infoText;
	}

	public SequenceOfTicketLinkType getIncludedTickets() {

		return this.includedTickets;
	}

	public ExtensionData getExtension() {

		return this.extension;
	}

	public void setIdentificationByCardReference(SequenceOfCardReferenceType identificationByCardReference) {

		this.identificationByCardReference = identificationByCardReference;
	}

	public void setIdentificationByIdCard(Boolean identificationByIdCard) {

		this.identificationByIdCard = identificationByIdCard;
	}

	public void setIdentificationByPassportId(Boolean identificationByPassportId) {

		this.identificationByPassportId = identificationByPassportId;
	}

	public void setIdentificationItem(Long identificationItem) {

		this.identificationItem = Asn1BigInteger.toAsn1(identificationItem);
	}

	public void setPassportValidationRequired(Boolean passportValidationRequired) {

		this.passportValidationRequired = passportValidationRequired;
	}

	public void setOnlineValidationRequired(Boolean onlineValidationRequired) {

		this.onlineValidationRequired = onlineValidationRequired;
	}

	public void setRandomDetailedValidationRequired(Long randomDetailedValidationRequired) {
		this.randomDetailedValidationRequired = randomDetailedValidationRequired;
	}

	public void setAgeCheckRequired(Boolean ageCheckRequired) {
		this.ageCheckRequired = ageCheckRequired;
	}

	public void setReductionCardCheckRequired(Boolean reductionCardCheckRequired) {

		this.reductionCardCheckRequired = reductionCardCheckRequired;
	}

	public void setInfoText(String infoText) {

		this.infoText = infoText;
	}

	public void setIncludedTickets(SequenceOfTicketLinkType includedTickets) {

		this.includedTickets = includedTickets;
	}

	public void setExtension(ExtensionData extension) {

		this.extension = extension;
	}



}
