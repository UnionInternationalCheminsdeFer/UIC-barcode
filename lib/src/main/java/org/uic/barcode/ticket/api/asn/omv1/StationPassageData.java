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
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringIA5;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringUTF8;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfUnrestrictedLong;
import org.uic.barcode.ticket.api.utils.DateTimeUtils;

@Sequence
@HasExtensionMarker
public class StationPassageData extends Object {
	public StationPassageData() {
	}

	@FieldOrder(order = 0)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String referenceIA5;

	@FieldOrder(order = 1)
	@Asn1Optional public Asn1BigInteger referenceNum;

	@FieldOrder(order = 2)
	@IntRange(minValue=1,maxValue=32000)
	@Asn1Optional public Long productOwnerNum;

	@FieldOrder(order = 3)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String productOwnerIA5;

	@FieldOrder(order = 4)
	@IntRange(minValue=1,maxValue=32000)
	@Asn1Optional public Long productIdNum;

	@FieldOrder(order = 5)
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String productIdIA5;

	@FieldOrder(order = 6)
	@RestrictedString(CharacterRestriction.UTF8String)
	@Asn1Optional public String productName;

	@FieldOrder(order = 7)
	@Asn1Default("stationUIC")
	@Asn1Optional public CodeTableType stationCodeTable;

	@FieldOrder(order = 8)
	@Asn1Optional public SequenceOfUnrestrictedLong stationNum;

	@FieldOrder(order = 9)
	@Asn1Optional public SequenceOfStringIA5 stationIA5;

	@FieldOrder(order = 10)
	@Asn1Optional public SequenceOfStringUTF8 stationNameUTF8;
	
	@FieldOrder(order = 11)
	@Asn1Optional public SequenceOfUnrestrictedLong areaCodeNum;

	@FieldOrder(order = 12)
	@Asn1Optional public SequenceOfStringIA5 areaCodeIA5;

	@FieldOrder(order = 13)
	@Asn1Optional public SequenceOfStringUTF8 areaNameUTF8;	

	@FieldOrder(order = 14)
	@IntRange(minValue=-1,maxValue=700)
	public Long validFromDay;

	@FieldOrder(order = 15)
	@IntRange(minValue=0,maxValue=1440)
	@Asn1Optional public Long validFromTime;
	
	@FieldOrder(order = 16)
	@IntRange(minValue=-60, maxValue=60)
	@Asn1Optional public Long validFromUTCOffset;

	@FieldOrder(order = 17)
	@Asn1Default(value="0")
	@IntRange(minValue=0,maxValue=370)
	@Asn1Optional public Long validUntilDay;

	@FieldOrder(order = 18)
	@IntRange(minValue=0,maxValue=1440)
	@Asn1Optional public Long validUntilTime;
	
	@FieldOrder(order = 19)
	@IntRange(minValue=-60, maxValue=60)
	@Asn1Optional public Long validUntilUTCOffset;

	@FieldOrder(order = 20)
	@Asn1Optional public Asn1BigInteger numberOfDaysValid;

	@FieldOrder(order = 21)
	@Asn1Optional public ExtensionData extension;
	
	
	public String getReferenceIA5() {

		return this.referenceIA5;
	}

	public Asn1BigInteger getReferenceNum() {

		return this.referenceNum;
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

	public String getProductName() {

		return this.productName;
	}

	public CodeTableType getStationCodeTable() {

		if (stationCodeTable == null)  {
			return CodeTableType.stationUIC;
		}
		
		return this.stationCodeTable;
	}

	public SequenceOfUnrestrictedLong getStationNum() {

		return this.stationNum;
	}

	public List<String> getStationIA5() {

		return this.stationIA5;
	}

	public SequenceOfStringUTF8 getStationNameUTF8() {

		return this.stationNameUTF8;
	}

	public SequenceOfUnrestrictedLong getAreaCodeNum() {

		return this.areaCodeNum;
	}

	public List<String> getAreaCodeIA5() {

		return this.areaCodeIA5;
	}

	public List<String> getAreaNameUTF8() {

		return this.areaNameUTF8;
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

	public Long getNumberOfDaysValid() {

		return Asn1BigInteger.toLong(numberOfDaysValid);
	}

	public ExtensionData getExtension() {

		return this.extension;
	}

	public void setReferenceIA5(String referenceIA5) {

		this.referenceIA5 = referenceIA5;
	}

	public void setReferenceNum(Asn1BigInteger referenceNum) {

		this.referenceNum = referenceNum;
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

	public void setProductName(String productName) {

		this.productName = productName;
	}

	public void setStationCodeTable(CodeTableType stationCodeTable) {

		this.stationCodeTable = stationCodeTable;
	}

	public void setStationNum(SequenceOfUnrestrictedLong stationNum) {

		this.stationNum = stationNum;
	}

	public void setStationIA5(SequenceOfStringIA5 stationIA5) {

		this.stationIA5 = stationIA5;
	}

	public void setStationNameUTF8(SequenceOfStringUTF8 stationNameUTF8) {

		this.stationNameUTF8 = stationNameUTF8;
	}

	public void setAreaCodeNum(SequenceOfUnrestrictedLong sequenceOfUnrestrictedLong) {

		this.areaCodeNum = sequenceOfUnrestrictedLong;
	}

	public void setAreaCodeIA5(SequenceOfStringIA5 areaCodeIA5) {

		this.areaCodeIA5 = areaCodeIA5;
	}

	public void setAreaNameUTF8(SequenceOfStringUTF8 areaNameUTF8) {

		this.areaNameUTF8 = areaNameUTF8;
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

	public void setNumberOfDaysValid(Long numberOfDaysValid) {

		this.numberOfDaysValid = Asn1BigInteger.toAsn1(numberOfDaysValid);
	}

	public void setExtension(ExtensionData extension) {

		this.extension = extension;
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
