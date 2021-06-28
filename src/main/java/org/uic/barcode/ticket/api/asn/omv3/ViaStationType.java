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

import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringIA5;

@Sequence
@HasExtensionMarker
public class ViaStationType extends Object {
	public ViaStationType() {
	}

	@FieldOrder(order = 0)
	@Asn1Default("stationUIC")
	@Asn1Optional public CodeTableType stationCodeTable;

	@FieldOrder(order = 1)
	@IntRange(minValue=1,maxValue=9999999)
	@Asn1Optional public Long stationNum;

	@FieldOrder(order = 2)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String stationIA5;

	@FieldOrder(order = 3)
	@Asn1Optional public SequenceOfViaStationType alternativeRoutes;

	@FieldOrder(order = 4)
	@Asn1Optional public SequenceOfViaStationType route;

	@FieldOrder(order = 5)
	@Asn1Optional public Boolean border = false;

	@FieldOrder(order = 6)
	@Asn1Optional public SequenceOfCarrierNum carriersNum;

	@FieldOrder(order = 7)
	@Asn1Optional public SequenceOfStringIA5 carriersIA5;

	@FieldOrder(order = 8)
	@Asn1Optional public Asn1BigInteger seriesId;

	@FieldOrder(order = 9)
	@Asn1Optional public Asn1BigInteger routeId;
	
	@FieldOrder(order = 10)
	@Asn1Optional public SequenceOfServiceBrands includedServiceBrands;

	@FieldOrder(order = 11)
	@Asn1Optional public SequenceOfServiceBrands excludedServiceBrands;

	
	

	public CodeTableType getStationCodeTable() {

		if (stationCodeTable == null)  {
			return CodeTableType.stationUIC;
		}
		
		return this.stationCodeTable;
	}

	public Long getStationNum() {

		return this.stationNum;
	}

	public String getStationIA5() {

		return this.stationIA5;
	}

	public SequenceOfViaStationType getAlternativeRoutes() {

		return this.alternativeRoutes;
	}

	public SequenceOfViaStationType getRoute() {

		return this.route;
	}

	public Boolean getBorder() {

		return this.border;
	}

	public SequenceOfCarrierNum getCarriersNum() {

		return this.carriersNum;
	}

	public SequenceOfStringIA5 getCarriersIA5() {

		return this.carriersIA5;
	}

	public Long getSeriesId() {

		return Asn1BigInteger.toLong(this.seriesId);
	}

	public Long getRouteId() {

		return Asn1BigInteger.toLong(this.routeId);
	}

	public void setStationCodeTable(CodeTableType stationCodeTable) {

		this.stationCodeTable = stationCodeTable;
	}

	public void setStationNum(Long stationNum) {

		this.stationNum = stationNum;
	}

	public void setStationIA5(String stationIA5) {

		this.stationIA5 = stationIA5;
	}

	public void setAlternativeRoutes(SequenceOfViaStationType alternativeRoutes) {

		this.alternativeRoutes = alternativeRoutes;
	}

	public void setRoute(SequenceOfViaStationType route) {

		this.route = route;
	}

	public void setBorder(Boolean border) {

		this.border = border;
	}

	public void setCarriersNum(SequenceOfCarrierNum carriersNum) {

		this.carriersNum = carriersNum;
	}

	public void setCarriersIA5(SequenceOfStringIA5 carriersIA5) {

		this.carriersIA5 = carriersIA5;
	}

	public void setSeriesId(Long seriesId) {

		this.seriesId = Asn1BigInteger.toAsn1(seriesId);
	}

	public void setRouteId(Long routeId) {

		this.routeId = Asn1BigInteger.toAsn1(routeId);
	}

	public SequenceOfServiceBrands getIncludedServiceBrands() {

		return this.includedServiceBrands;
	}

	public SequenceOfServiceBrands getExcludedServiceBrands() {

		return this.excludedServiceBrands;
	}

	public void setIncludedServiceBrands(SequenceOfServiceBrands includedServiceBrands) {
		this.includedServiceBrands = includedServiceBrands;
	}

	public void setExcludedServiceBrands(SequenceOfServiceBrands excludedServiceBrands) {
		this.excludedServiceBrands = excludedServiceBrands;
	}
	
	

}
