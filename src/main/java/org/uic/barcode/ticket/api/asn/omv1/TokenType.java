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

import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.OctetString;

@Sequence
public class TokenType extends Object {
	public TokenType() {
	}

	@FieldOrder(order = 0)
	@Asn1Optional public Long tokenProviderNum;

	@FieldOrder(order = 1)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String tokenProviderIA5;

	@FieldOrder(order = 2)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String tokenSpecification;

	@FieldOrder(order = 3)
	public OctetString token;

	public Long getTokenProviderNum() {

		return this.tokenProviderNum;
	}

	public String getTokenProviderIA5() {

		return this.tokenProviderIA5;
	}

	public String getTokenSpecification() {

		return this.tokenSpecification;
	}

	public byte[] getToken() {
		
		return token.toByteArray();

	}

	public void setTokenProviderNum(Long tokenProviderNum) {

		this.tokenProviderNum = tokenProviderNum;
	}

	public void setTokenProviderIA5(String tokenProviderIA5) {

		this.tokenProviderIA5 = tokenProviderIA5;
	}

	public void setTokenSpecification(String tokenSpecification) {

		this.tokenSpecification = tokenSpecification;
	}

	public void setToken(byte[] token) {

		this.token = new OctetString(token);

	}



}
