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
package org.uic.barcode.ticket.api.asn.omv3;

import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;


@Sequence
@HasExtensionMarker
public class RegisteredLuggageType extends Object {
	public RegisteredLuggageType() {
	}

	@FieldOrder(order = 0)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String registrationId;

	@FieldOrder(order = 1)
	@IntRange(minValue=1,maxValue=99)
	@Asn1Optional public Long maxWeight;

	@FieldOrder(order = 2)
	@IntRange(minValue=1,maxValue=300)
	@Asn1Optional public Long maxSize;

	public String getRegistrationId() {

		return this.registrationId;
	}

	public Long getMaxWeight() {

		return this.maxWeight;
	}

	public Long getMaxSize() {

		return this.maxSize;
	}

	public void setRegistrationId(String registrationId) {

		this.registrationId = registrationId;
	}

	public void setMaxWeight(Long maxWeight) {

		this.maxWeight = maxWeight;
	}

	public void setMaxSize(Long maxSize) {

		this.maxSize = maxSize;
	}


}
