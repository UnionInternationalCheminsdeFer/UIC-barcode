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

import java.util.ArrayList;
import java.util.Collection;
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
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringIA5;
import org.uic.barcode.ticket.api.asn.omv2.SequenceOfActivatedDays;
import org.uic.barcode.ticket.api.utils.DateTimeUtils;

@Sequence
@HasExtensionMarker
public class PassData extends Object {
	public PassData() {
	}

	@FieldOrder(order = 0)
	@Asn1Optional public Asn1BigInteger referenceNum;
	
	@FieldOrder(order = 1)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String referenceIA5;

	@FieldOrder(order = 2)
	@IntRange(minValue=1,maxValue=32000)
	@Asn1Optional public Long productOwnerNum;

	@FieldOrder(order = 3)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String productOwnerIA5;

	@FieldOrder(order = 4)
	@IntRange(minValue=0,maxValue=65535)
	@Asn1Optional public Long productIdNum;

	@FieldOrder(order = 5)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String productIdIA5;

	@FieldOrder(order = 6)
	@IntRange(minValue=1,maxValue=250)
	@Asn1Optional public Long passType;

	@FieldOrder(order = 7)
	@RestrictedString(CharacterRestriction.UTF8String)
	@Asn1Optional public String passDescription;

	@FieldOrder(order = 8)
	@Asn1Default (value="second")
	@Asn1Optional public TravelClassType classCode;

	@FieldOrder(order = 9)
	@IntRange(minValue=-1,maxValue=700)
	@Asn1Optional public Long validFromDay;

	@FieldOrder(order = 10)
	@IntRange(minValue=0,maxValue=1439)
	@Asn1Optional public Long validFromTime;
	
	@FieldOrder(order = 11)
	@IntRange(minValue=-60, maxValue=60)
	@Asn1Optional public Long validFromUTCOffset;

	@FieldOrder(order = 12)
	@IntRange(minValue=0,maxValue=370)
	@Asn1Optional public Long validUntilDay;

	@FieldOrder(order = 13)
	@IntRange(minValue=0,maxValue=1439)
	@Asn1Optional public Long validUntilTime;
	
	@FieldOrder(order = 14)
	@IntRange(minValue=-60, maxValue=60)
	@Asn1Optional public Long validUntilUTCOffset;

	@FieldOrder(order = 15)
	@Asn1Optional public ValidityPeriodDetailType validityPeriodDetails;

	@FieldOrder(order = 16)
	@IntRange(minValue=0,maxValue=370)
	@Asn1Optional public Long numberOfValidityDays;

	@FieldOrder(order = 17)
	@IntRange(minValue=1,maxValue=250)
	@Asn1Optional public Long numberOfPossibleTrips;

	@FieldOrder(order = 18)
	@IntRange(minValue=1,maxValue=250)
	@Asn1Optional public Long numberOfDaysOfTravel;

	@FieldOrder(order = 19)
	@Asn1Optional public SequenceOfActivatedDays activatedDay;

	@FieldOrder(order = 20)
	@Asn1Optional public SequenceOfCountries countries;
	
	@FieldOrder(order = 21)
	@Asn1Optional public SequenceOfCarrierNum includedCarriersNum;

	@FieldOrder(order = 22)
	@Asn1Optional public SequenceOfStringIA5 includedCarriersIA5;
	
	@FieldOrder(order = 23)
	@Asn1Optional public SequenceOfCarrierNum excludedCarriersNum;

	@FieldOrder(order = 24)
	@Asn1Optional public SequenceOfStringIA5 excludedCarriersIA5;

	@FieldOrder(order = 25)
	@Asn1Optional public SequenceOfServiceBrands includedServiceBrands;

	@FieldOrder(order = 26)
	@Asn1Optional public SequenceOfServiceBrands excludedServiceBrands;

	@FieldOrder(order = 27)
	@Asn1Optional public SequenceOfRegionalValidityType validRegion;
	
	@FieldOrder(order = 28)
	@Asn1Optional public SequenceOfTariffType tariffs;

	@FieldOrder(order = 29)
	@Asn1Optional Asn1BigInteger price;
	
	@FieldOrder(order = 30)
	@Asn1Optional SequenceOfVatDetail vatDetails;
	
	@FieldOrder(order = 31)
	@RestrictedString(CharacterRestriction.UTF8String)
	@Asn1Optional public String infoText;

	@FieldOrder(order = 32)
	@Asn1Optional public ExtensionData extension;
	
	

	

	public Asn1BigInteger getReferenceNum() {

		return this.referenceNum;
	}

	public String getReferenceIA5() {

		return this.referenceIA5;
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

	public Long getPassType() {

		return this.passType;
	}

	public String getPassDescription() {

		return this.passDescription;
	}

	public TravelClassType getClassCode() {

		if (classCode == null){
			return TravelClassType.second;
		}
		
		return this.classCode;
	}

	public Long getValidFromDay() {

		return this.validFromDay;
	}

	public Long getValidFromTime() {

		return this.validFromTime;
	}

	public Long getValidUntilDay() {

		return this.validUntilDay;
	}

	public Long getValidUntilTime() {

		return this.validUntilTime;
	}

	public ValidityPeriodDetailType getValidityPeriodDetails() {

		return this.validityPeriodDetails;
	}

	public Long getNumberOfValidityDays() {

		return this.numberOfValidityDays;
	}

	public Long getNumberOfPossibleTrips() {

		return this.numberOfPossibleTrips;
	}

	public Long getNumberOfDaysOfTravel() {

		return this.numberOfDaysOfTravel;
	}

	public List<Long> getActivatedDay() {

		return this.activatedDay;
	}

	public List<Long> getCountries() {

		return this.countries;
	}

	public List<Long> getIncludedCarriersNum() {

		return this.includedCarriersNum;
	}

	public List<String> getIncludedCarriersIA5() {

		return this.includedCarriersIA5;
	}

	public List<Long> getExcludedCarriersNum() {

		return this.excludedCarriersNum;
	}

	public SequenceOfStringIA5 getExcludedCarriersIA5() {

		return this.excludedCarriersIA5;
	}

	public SequenceOfServiceBrands getIncludedServiceBrands() {

		return this.includedServiceBrands;
	}

	public SequenceOfServiceBrands getExcludedServiceBrands() {

		return this.excludedServiceBrands;
	}

	public List<RegionalValidityType> getValidRegion() {

		return this.validRegion;
	}

	public List<TariffType> getTariffs() {

		return this.tariffs;
	}

	public String getInfoText() {

		return this.infoText;
	}

	public ExtensionData getExtension() {

		return this.extension;
	}

	public void setReferenceNum(Asn1BigInteger referenceNum) {

		this.referenceNum = referenceNum;
	}

	public void setReferenceIA5(String referenceIA5) {

		this.referenceIA5 = referenceIA5;
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

	public void setPassType(Long passType) {

		this.passType = passType;
	}

	public void setPassDescription(String passDescription) {

		this.passDescription = passDescription;
	}

	public void setClassCode(TravelClassType classCode) {

		this.classCode = classCode;
	}

	public void setValidFromDay(Long validFromDay) {

		this.validFromDay = validFromDay;
	}

	public void setValidFromTime(Long validFromTime) {

		this.validFromTime = validFromTime;
	}

	public void setValidUntilDay(Long validUntilDay) {

		this.validUntilDay = validUntilDay;
	}

	public void setValidUntilTime(Long validUntilTime) {

		this.validUntilTime = validUntilTime;
	}

	public void setValidityPeriodDetails(ValidityPeriodDetailType validityPeriodDetails) {

		this.validityPeriodDetails = validityPeriodDetails;
	}

	public void setNumberOfValidityDays(Long numberOfValidityDays) {

		this.numberOfValidityDays = numberOfValidityDays;
	}

	public void setNumberOfPossibleTrips(Long numberOfPossibleTrips) {

		this.numberOfPossibleTrips = numberOfPossibleTrips;
	}

	public void setNumberOfDaysOfTravel(Long numberOfDaysOfTravel) {

		this.numberOfDaysOfTravel = numberOfDaysOfTravel;
	}

	public void setActivatedDay(SequenceOfActivatedDays activatedDay) {

		this.activatedDay = activatedDay;
	}

	public void setCountries(SequenceOfCountries countries) {

		this.countries = countries;
	}

	public void setIncludedCarriersNum(SequenceOfCarrierNum includedCarriersNum) {

		this.includedCarriersNum = includedCarriersNum;
	}

	public void setIncludedCarriersIA5(SequenceOfStringIA5 includedCarriersIA5) {

		this.includedCarriersIA5 = includedCarriersIA5;
	}

	public void setExcludedCarriersNum(SequenceOfCarrierNum excludedCarriersNum) {

		this.excludedCarriersNum = excludedCarriersNum;
	}

	public void setExcludedCarriersIA5(SequenceOfStringIA5 excludedCarriersIA5) {

		this.excludedCarriersIA5 = excludedCarriersIA5;
	}

	public void setIncludedServiceBrands(SequenceOfServiceBrands includedServiceBrands) {

		this.includedServiceBrands = includedServiceBrands;
	}

	public void setExcludedServiceBrands(SequenceOfServiceBrands excludedServiceBrands) {

		this.excludedServiceBrands = excludedServiceBrands;
	}

	public void setValidRegion(SequenceOfRegionalValidityType validRegion) {

		this.validRegion = validRegion;
	}

	public void setTariffs(SequenceOfTariffType tariffs) {

		this.tariffs = tariffs;
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
	
	public void setValidityDates (Date fromDate, Date untilDate, Date issuingDate){
		
		if (issuingDate == null || fromDate == null) return;
				
		this.validFromDay = DateTimeUtils.getDateDifference(issuingDate,fromDate);
		this.validFromTime =  DateTimeUtils.getTime(fromDate);

		if (untilDate != null){
			this.validUntilDay = DateTimeUtils.getDateDifference(fromDate, untilDate);
			this.validUntilTime = DateTimeUtils.getTime(untilDate);
		}

	}
	
	public Date getValidFromDate(Date issuingDate){
		
		return DateTimeUtils.getDate(issuingDate, this.validFromDay, this.validFromTime);
		
	}
	
	public Date getValidUntilDate(Date issuingDate){
		
		if (issuingDate == null) return null;
		
		if (this.validFromDay == null) {
			this.validFromDay = 0L;
		}
		
		if (this.validUntilDay == null) {
			return null;
		}		
		
		
		return DateTimeUtils.getDate(issuingDate, this.validFromDay + this.validUntilDay, this.validUntilTime);
		
	}
	
	public void addActivatedDays(Collection<Long> days) {
		
		if (days == null  || days.isEmpty()) return;
		
		if (this.activatedDay == null) {
			this.activatedDay = new SequenceOfActivatedDays();
		}
		
		for (Long l : days) {
			this.activatedDay.add(l);
		}
		
	}
	
	public void addActivatedDay(Date issuingDate, Date day){
		
		Long dayDiff = DateTimeUtils.getDateDifference(issuingDate, day);
		
		if (this.activatedDay == null) {
			this.activatedDay = new SequenceOfActivatedDays();
		}
		
		if (dayDiff != null) {
			this.activatedDay.add(dayDiff);
		}
		
	}
	
	/**
	 * Gets the activated days.
	 *
	 * @param issuingDate the issuing date
	 * @return the activated days
	 */
	public Collection<Date> getActivatedDays(Date issuingDate) {
		
		if (this.activatedDay == null) return null;
		
		ArrayList<Date> dates = new ArrayList<Date>();
		
		for (Long diff: this.getActivatedDay()) {
			
			Date day = DateTimeUtils.getDate(this.getValidFromDate(issuingDate), diff, null);
			
			if (day != null) {
				dates.add(day);
			}
			
		}
				
		return dates;
		
	}	

	public Long getValidFromUTCOffset() {
		return validFromUTCOffset;
	}

	public void setValidFromUTCOffset(Long validFromUTCOffset) {
		this.validFromUTCOffset = validFromUTCOffset;
	}

	public Long getValidUntilUTCOffset() {
		return validUntilUTCOffset;
	}

	public void setValidUntilUTCOffset(Long validUntilUTCOffset) {
		this.validUntilUTCOffset = validUntilUTCOffset;
	}
	
	
	

}
