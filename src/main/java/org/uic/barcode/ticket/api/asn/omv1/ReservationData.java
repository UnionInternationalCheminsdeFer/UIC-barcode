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

import java.util.Date;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypes.SizeRange;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringIA5;
import org.uic.barcode.ticket.api.utils.DateTimeUtils;

@Sequence
@HasExtensionMarker
public class ReservationData extends Object {
	public ReservationData() {
	}

	@FieldOrder(order = 0)
	@Asn1Optional public Asn1BigInteger trainNum;

	@FieldOrder(order = 1)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String trainIA5;

	@FieldOrder(order = 2)
	@Asn1Default(value="0")
	@IntRange(minValue=-1,maxValue=370)
	@Asn1Optional public Long departureDate;

	@FieldOrder(order = 3)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String referenceIA5;

	@FieldOrder(order = 4)
	@Asn1Optional public Asn1BigInteger referenceNum;

	@FieldOrder(order = 5)
	@IntRange(minValue=1,maxValue=32000)
	@Asn1Optional public Long productOwnerNum;

	@FieldOrder(order = 6)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String productOwnerIA5;

	@FieldOrder(order = 7)
	@IntRange(minValue=0,maxValue=32000)
	@Asn1Optional public Long productIdNum;

	@FieldOrder(order = 8)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String productIdIA5;

	@FieldOrder(order = 9)
	@IntRange(minValue=0,maxValue=32000)
	@Asn1Optional public Long serviceBrand;

	@FieldOrder(order = 10)
	@RestrictedString(CharacterRestriction.UTF8String)
	@Asn1Optional public String serviceBrandAbrUTF8;

	@FieldOrder(order = 11)
	@RestrictedString(CharacterRestriction.UTF8String)
	@Asn1Optional public String serviceBrandNameUTF8;

	@FieldOrder(order = 12)
	@Asn1Default("seat")
	@Asn1Optional public ServiceType service;

	@FieldOrder(order = 13)
	@Asn1Default("stationUICReservation")
	@Asn1Optional public CodeTableType stationCodeTable;

	@FieldOrder(order = 14)
	@IntRange(minValue=1,maxValue=9999999)
	@Asn1Optional public Long fromStationNum;

	@FieldOrder(order = 15)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String fromStationIA5;

	@FieldOrder(order = 16)
	@IntRange(minValue=1,maxValue=9999999)
	@Asn1Optional public Long toStationNum;

	@FieldOrder(order = 17)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String toStationIA5;

	@FieldOrder(order = 18)
	@RestrictedString(CharacterRestriction.UTF8String)
	@Asn1Optional public String fromStationNameUTF8;

	@FieldOrder(order = 19)
	@RestrictedString(CharacterRestriction.UTF8String)
	@Asn1Optional public String toStationNameUTF8;

	@FieldOrder(order = 20)
	@IntRange(minValue=0,maxValue=1440)
	public Long departureTime;
	
	@FieldOrder(order = 21)
	@IntRange(minValue=-60, maxValue=60)
	@Asn1Optional public Long departureUTCOffset;

	@FieldOrder(order = 22)
	@IntRange(minValue=0,maxValue=20)
	@Asn1Default(value="0")
	@Asn1Optional public Long arrivalDate;

	@FieldOrder(order = 23)
	@IntRange(minValue=0,maxValue=1440)
	@Asn1Optional public Long arrivalTime;
	
	@FieldOrder(order = 24)
	@IntRange(minValue=-60, maxValue=60)
	@Asn1Optional public Long arrivalUTCOffset;

	@FieldOrder(order = 25)
	@Asn1Optional public SequenceOfCarrierNum carrierNum;

	@FieldOrder(order = 26)
	@Asn1Optional public SequenceOfStringIA5 carrierIA5;

	@FieldOrder(order = 27)
	@Asn1Default("second")
	@Asn1Optional public TravelClassType classCode;

	@FieldOrder(order = 28)
	@SizeRange(minValue = 1, maxValue = 2)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String serviceLevel;

	@FieldOrder(order = 29)
	@Asn1Optional public PlacesType places;

	@FieldOrder(order = 30)
	@Asn1Optional public PlacesType additionalPlaces;

	@FieldOrder(order = 31)
	@Asn1Optional public PlacesType bicyclePlaces;

	@FieldOrder(order = 32)
	@Asn1Optional public CompartmentDetailsType compartmentDetails;

	@FieldOrder(order = 33)
	@IntRange(minValue=0,maxValue=200)
	@Asn1Default(value="0")
	@Asn1Optional public Long numberOfOverbooked;

	@FieldOrder(order = 34)
	@Asn1Optional public SequenceOfBerthDetailData berth;

	@FieldOrder(order = 35)
	@Asn1Optional public SequenceOfTariffType tariff;

	@FieldOrder(order = 36)
	@Asn1Default("travelPrice")
	@Asn1Optional public PriceTypeType priceType;
	
	@FieldOrder(order = 37)
	@Asn1Optional Asn1BigInteger price;
	
	@FieldOrder(order = 38)
	@Asn1Optional SequenceOfVatDetail vatDetails;

	@FieldOrder(order = 39)
	@IntRange(minValue=0,maxValue=9)
	@Asn1Default("0")
	@Asn1Optional public Long typeOfSupplement;

	@FieldOrder(order = 40)
	@IntRange(minValue=0,maxValue=200)
	@Asn1Default("0")
	@Asn1Optional public Long numberOfSupplements;

	@FieldOrder(order = 41)
	@Asn1Optional public LuggageRestrictionType luggage;
	
	@FieldOrder(order = 42)
	@RestrictedString(CharacterRestriction.UTF8String)
	@Asn1Optional public String infoText;

	@FieldOrder(order = 43)
	@Asn1Optional public ExtensionData extension;
	
	public Long getTrainNum() {

		return Asn1BigInteger.toLong(this.trainNum);
	}

	public String getTrainIA5() {
		return this.trainIA5;
	}

	public Long getDepartureDate() {
		return this.departureDate;
	}

	public String getReferenceIA5() {
		return this.referenceIA5;
	}
	
	public Long getReferenceNum() {
		return Asn1BigInteger.toLong(this.referenceNum);
	}

	public Long getProductOwnerNum() {
		return this.productOwnerNum;
	}

	public String getProductOwnerIA5() {
		return this.productOwnerIA5;
	}

	public Long getProductIdNum() {
		return this.productIdNum;
	}

	public String getProductIdIA5() {
		return this.productIdIA5;
	}

	public Long getServiceBrand() {
		return this.serviceBrand;
	}

	public String getServiceBrandAbrUTF8() {
		return this.serviceBrandAbrUTF8;
	}

	public String getServiceBrandNameUTF8() {
		return this.serviceBrandNameUTF8;
	}

	public ServiceType getService() {

		if (service == null){
			return ServiceType.seat;
		}
		
		return this.service;
	}

	public CodeTableType getStationCodeTable() {

		if (stationCodeTable == null) {
			return CodeTableType.stationUICReservation;
		}
		
		return this.stationCodeTable;
	}

	public Long getFromStationNum() {

		return this.fromStationNum;
	}

	public String getFromStationIA5() {

		return this.fromStationIA5;
	}

	public Long getToStationNum() {

		return this.toStationNum;
	}

	public String getToStationIA5() {

		return this.toStationIA5;
	}

	public String getFromStationNameUTF8() {

		return this.fromStationNameUTF8;
	}

	public String getToStationNameUTF8() {

		return this.toStationNameUTF8;
	}

	public Long getDepartureTime() {

		return this.departureTime;
	}

	public Long getArrivalDate() {

		if (arrivalDate == null) {
			return Long.valueOf(0);
		}
		
		return this.arrivalDate;
	}

	public Long getArrivalTime() {

		return this.arrivalTime;
	}

	public List<Long> getCarrierNum() {

		return this.carrierNum;
	}

	public List<String> getCarrierIA5() {

		return this.carrierIA5;
	}

	public TravelClassType getClassCode() {

		
		if (classCode == null) {
			return TravelClassType.second;
		}
		
		return this.classCode;
	}

	public String getServiceLevel() {

		return this.serviceLevel;
	}

	public PlacesType getPlaces() {

		return this.places;
	}

	public PlacesType getAdditionalPlaces() {

		return this.additionalPlaces;
	}

	public PlacesType getBicyclePlaces() {

		return this.bicyclePlaces;
	}

	public CompartmentDetailsType getCompartmentDetails() {

		return this.compartmentDetails;
	}

	public Long getNumberOfOverbooked() {

		return this.numberOfOverbooked;
	}

	public List<BerthDetailData> getBerth() {

		return this.berth;
	}

	public List<TariffType> getTariff() {

		return this.tariff;
	}

	public PriceTypeType getPriceType() {

		if (priceType == null) {
			return PriceTypeType.travelPrice;
		}
		
		return this.priceType;
	}

	public Long getTypeOfSupplement() {

		if (typeOfSupplement == null){
			return Long.valueOf(0);
		}
		
		
		return this.typeOfSupplement;
	}

	public Long getNumberOfSupplements() {

		if (numberOfSupplements == null) {
			return Long.valueOf(0);
		}
		
		return this.numberOfSupplements;
	}

	public LuggageRestrictionType getLuggage() {

		return this.luggage;
	}

	public String getInfoText() {

		return this.infoText;
	}

	public ExtensionData getExtension() {

		return this.extension;
	}

	public void setTrainNum(Long trainNum) {

		this.trainNum = Asn1BigInteger.toAsn1(trainNum);
	}

	public void setTrainIA5(String trainIA5) {

		this.trainIA5 = trainIA5;
	}

	public void setDepartureDate(Long departureDate) {

		this.departureDate = departureDate;
	}

	public void setReferenceIA5(String referenceIA5) {

		this.referenceIA5 = referenceIA5;
	}

	public void setReferenceNum(Long referenceNum) {
		this.referenceNum = Asn1BigInteger.toAsn1(referenceNum);
	}

	public void setProductOwnerNum(Long productOwnerNum) {

		this.productOwnerNum = productOwnerNum;
	}

	public void setProductOwnerIA5(String productOwnerIA5) {

		this.productOwnerIA5 = productOwnerIA5;
	}

	public void setProductIdNum(Long productIdNum) {

		this.productIdNum = productIdNum;
	}

	public void setProductIdIA5(String productIdIA5) {

		this.productIdIA5 = productIdIA5;
	}

	public void setServiceBrand(Long serviceBrand) {

		this.serviceBrand = serviceBrand;
	}

	public void setServiceBrandAbrUTF8(String serviceBrandAbrUTF8) {

		this.serviceBrandAbrUTF8 = serviceBrandAbrUTF8;
	}

	public void setServiceBrandNameUTF8(String serviceBrandNameUTF8) {

		this.serviceBrandNameUTF8 = serviceBrandNameUTF8;
	}

	public void setService(ServiceType service) {

		this.service = service;
	}

	public void setStationCodeTable(CodeTableType stationCodeTable) {

		this.stationCodeTable = stationCodeTable;
	}

	public void setFromStationNum(Long fromStationNum) {

		this.fromStationNum = fromStationNum;
	}

	public void setFromStationIA5(String fromStationIA5) {

		this.fromStationIA5 = fromStationIA5;
	}

	public void setToStationNum(Long toStationNum) {

		this.toStationNum = toStationNum;
	}

	public void setToStationIA5(String toStationIA5) {

		this.toStationIA5 = toStationIA5;
	}

	public void setFromStationNameUTF8(String fromStationNameUTF8) {

		this.fromStationNameUTF8 = fromStationNameUTF8;
	}

	public void setToStationNameUTF8(String toStationNameUTF8) {

		this.toStationNameUTF8 = toStationNameUTF8;
	}

	public void setDepartureTime(Long departureTime) {

		this.departureTime = departureTime;
	}

	public void setArrivalDate(Long arrivalDate) {

		this.arrivalDate = arrivalDate;
	}

	public void setArrivalTime(Long arrivalTime) {

		this.arrivalTime = arrivalTime;
	}

	public void setCarrierNum(SequenceOfCarrierNum carrierNum) {

		this.carrierNum = carrierNum;
	}
	
	public void setCarriersNum(List<Long> longs) {
		if (longs == null) return;

		this.carrierNum = new SequenceOfCarrierNum();
		this.carrierNum.addAll(longs);
	}	

	public void setCarrierIA5(SequenceOfStringIA5 carrierIA5) {

		this.carrierIA5 = carrierIA5;
	}

	public void setClassCode(TravelClassType classCode) {

		this.classCode = classCode;
	}

	public void setServiceLevel(String serviceLevel) {

		this.serviceLevel = serviceLevel;
	}

	public void setPlaces(PlacesType places) {

		this.places = places;
	}

	public void setAdditionalPlaces(PlacesType additionalPlaces) {

		this.additionalPlaces = additionalPlaces;
	}

	public void setBicyclePlaces(PlacesType bicyclePlaces) {

		this.bicyclePlaces = bicyclePlaces;
	}

	public void setCompartmentDetails(CompartmentDetailsType compartmentDetails) {

		this.compartmentDetails = compartmentDetails;
	}

	public void setNumberOfOverbooked(Long numberOfOverbooked) {

		this.numberOfOverbooked = numberOfOverbooked;
	}

	public void setBerth(SequenceOfBerthDetailData berth) {

		this.berth = berth;
	}

	public void setTariff(SequenceOfTariffType tariff) {

		this.tariff = tariff;
	}

	public void setPriceType(PriceTypeType priceType) {

		this.priceType = priceType;
	}

	public void setTypeOfSupplement(Long typeOfSupplement) {

		this.typeOfSupplement = typeOfSupplement;
	}

	public void setNumberOfSupplements(Long numberOfSupplements) {

		this.numberOfSupplements = numberOfSupplements;
	}

	public void setLuggage(LuggageRestrictionType luggage) {

		this.luggage = luggage;
	}

	public void setInfoText(String infoText) {

		this.infoText = infoText;
	}

	public void setExtension(ExtensionData extension) {

		this.extension = extension;
	}

	public Long getPrice() {
		return Asn1BigInteger.toLong(price);
	}

	public void setPrice(Long price) {
		this.price = Asn1BigInteger.toAsn1(price);
	}

	public SequenceOfVatDetail getVatDetails() {
		return vatDetails;
	}

	public void setVatDetails(SequenceOfVatDetail vatDetails) {
		this.vatDetails = vatDetails;
	}

	public void addVatDetail(VatDetailType vatDetail) {
		if (this.vatDetails == null) {
			this.vatDetails = new SequenceOfVatDetail();
		}
		this.vatDetails.add(vatDetail);
	}

	public void setDepartureArrivalDates (Date departure, Date arrival, Date issuingDate){
		
		if (issuingDate == null || departure == null) return;
					
		this.departureDate = DateTimeUtils.getDateDifference(issuingDate,departure);
		this.departureTime =  DateTimeUtils.getTime(departure);

		if (arrival != null){
			this.arrivalDate = DateTimeUtils.getDateDifference(departure, arrival);
			this.arrivalTime = DateTimeUtils.getTime(arrival);
		}

	}
	
	public Date getDepartureDate(Date issuingDate){
		
		return DateTimeUtils.getDate(issuingDate, this.departureDate, this.departureTime);
		
	}
	
	public Date getArrivalDate(Date issuingDate){
		
		if (this.departureDate == null) {
			this.departureDate = 0L;
		}
		
		if (this.arrivalDate == null) {
			return null;
		}
		
		return DateTimeUtils.getDate(issuingDate, this.departureDate + this.arrivalDate, this.arrivalTime);
	}

	public Long getDepartureUTCOffset() {
		return departureUTCOffset;
	}

	public void setDepartureUTCOffset(Long departureUTCOffset) {
		this.departureUTCOffset = departureUTCOffset;
	}

	public Long getArrivalUTCOffset() {
		return arrivalUTCOffset;
	}

	public void setArrivalUTCOffset(Long arrivalUTCOffset) {
		this.arrivalUTCOffset = arrivalUTCOffset;
	}
	
	


}
