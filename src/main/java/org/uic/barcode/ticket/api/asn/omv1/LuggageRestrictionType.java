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
package org.uic.barcode.ticket.api.asn.omv1;

import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.Sequence;

@Sequence
@HasExtensionMarker
public class LuggageRestrictionType extends Object {
	public LuggageRestrictionType() {
	}

	@FieldOrder(order = 0)
	@IntRange(minValue=0,maxValue=99)
	@Asn1Default(value="3")
	@Asn1Optional public Long maxHandLuggagePieces;

	@FieldOrder(order = 1)
	@IntRange(minValue=0,maxValue=99)
	@Asn1Default(value="1")
	@Asn1Optional public Long maxNonHandLuggagePieces;

	@FieldOrder(order = 2)
	@Asn1Optional public SequenceOfRegisteredLuggageType registeredLuggage;
	

	public void setRegisteredLuggage(
			SequenceOfRegisteredLuggageType registeredLuggage) {
		this.registeredLuggage = registeredLuggage;
	}

	public Long getMaxHandLuggagePieces() {

		if (maxHandLuggagePieces == null) {
			return Long.valueOf(3);
		}
		
		return this.maxHandLuggagePieces;
	}

	public Long getMaxNonHandLuggagePieces() {

		if (maxNonHandLuggagePieces == null) {
			return Long.valueOf(1);
		}
		
		return this.maxNonHandLuggagePieces;
	}

	public SequenceOfRegisteredLuggageType getRegisteredLuggage() {

		return this.registeredLuggage;
	}

	public void setMaxHandLuggagePieces(Long maxHandLuggagePieces) {

		this.maxHandLuggagePieces = maxHandLuggagePieces;
	}

	public void setMaxNonHandLuggagePieces(Long maxNonHandLuggagePieces) {

		this.maxNonHandLuggagePieces = maxNonHandLuggagePieces;
	}


}
