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
package org.uic.barcode.ticket.api.asn.omv2;

import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.Sequence;


@Sequence
@HasExtensionMarker
public class BerthDetailData extends Object {
	public BerthDetailData() {
	}

	@FieldOrder(order = 0)
	public BerthTypeType berthType;

	@FieldOrder(order = 1)
	@IntRange(minValue=1, maxValue=999)
	public Long numberOfBerths;

	@FieldOrder(order = 2)
	@Asn1Default(value="family")
	@Asn1Optional public CompartmentGenderType gender;
	
	
	public BerthTypeType getBerthType() {
		return this.berthType;
	}

	public Long getNumberOfBerths() {
		return this.numberOfBerths;
	}

	public CompartmentGenderType getGender() {
		
		if (gender == null){
			return CompartmentGenderType.family;
		} else {
			return this.gender;
		}
	}

	public void setBerthType(BerthTypeType berthType) {

		this.berthType = berthType;
	}

	public void setNumberOfBerths(Long numberOfBerths) {
		this.numberOfBerths = numberOfBerths;
	}

	public void setGender(CompartmentGenderType gender) {
		this.gender = gender;
	}


}
