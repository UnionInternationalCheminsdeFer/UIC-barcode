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

import java.util.Date;

import net.gcdc.asn1.datatypes.Asn1BigInteger;
import net.gcdc.asn1.datatypes.Asn1Default;
import net.gcdc.asn1.datatypes.Asn1Optional;
import net.gcdc.asn1.datatypes.CharacterRestriction;
import net.gcdc.asn1.datatypes.HasExtensionMarker;
import net.gcdc.asn1.datatypes.IntRange;
import net.gcdc.asn1.datatypes.RestrictedString;
import net.gcdc.asn1.datatypes.Sequence;

@Sequence
@HasExtensionMarker
public class ParkingGroundData extends Object {
	public ParkingGroundData() {
	}

	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String referenceIA5;

	@Asn1Optional public Asn1BigInteger referenceNum;

	@RestrictedString(CharacterRestriction.IA5String)
	public String parkingGroundId;

	@IntRange(minValue=0,maxValue=370)
	@Asn1Optional public Long fromParkingDate;

	@IntRange(minValue=0,maxValue=370)
	@Asn1Optional public Long toParkingDate;

	@IntRange(minValue=1,maxValue=32000)
	@Asn1Optional public Long productOwnerNum;

	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String productOwnerIA5;

	@IntRange(minValue=0,maxValue=32000)
	@Asn1Optional public Long productIdNum;

	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String productIdIA5;

	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String accessCode;

	@RestrictedString(CharacterRestriction.UTF8String)
	public String location;

	@Asn1Default("stationUIC")
	@Asn1Optional public CodeTableType stationCodeTable;

	@IntRange(minValue=1,maxValue=9999999)
	@Asn1Optional public Long stationNum;

	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String stationIA5;

	@RestrictedString(CharacterRestriction.UTF8String)
	@Asn1Optional public String specialInformation;

	@RestrictedString(CharacterRestriction.UTF8String)
	@Asn1Optional public String entryTrack;

	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String numberPlate;
	
	@Asn1Optional Asn1BigInteger price;
	
	@Asn1Optional SequenceOfVatDetail vatDetails;

	@Asn1Optional public ExtensionData extension;
	

	

	public String getReferenceIA5() {

		return this.referenceIA5;
	}

	public Asn1BigInteger getReferenceNum() {

		return this.referenceNum;
	}

	public String getParkingGroundId() {

		return this.parkingGroundId;
	}

	public Long getFromParkingDate() {

		return this.fromParkingDate;
	}

	public Long getToParkingDate() {

		return this.toParkingDate;
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

	public String getAccessCode() {

		return this.accessCode;
	}

	public String getLocation() {

		return this.location;
	}

	public CodeTableType getStationCodeTable() {

		if (stationCodeTable == null) {
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

	public String getSpecialInformation() {

		return this.specialInformation;
	}

	public String getEntryTrack() {

		return this.entryTrack;
	}

	public String getNumberPlate() {

		return this.numberPlate;
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

	public void setParkingGroundId(String parkingGroundId) {

		this.parkingGroundId = parkingGroundId;
	}

	public void setFromParkingDate(Long fromParkingDate) {

		this.fromParkingDate = fromParkingDate;
	}

	public void setToParkingDate(Long toParkingDate) {

		this.toParkingDate = toParkingDate;
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

	public void setAccessCode(String accessCode) {

		this.accessCode = accessCode;
	}

	public void setLocation(String location) {

		this.location = location;
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

	public void setSpecialInformation(String specialInformation) {

		this.specialInformation = specialInformation;
	}

	public void setEntryTrack(String entryTrack) {

		this.entryTrack = entryTrack;
	}

	public void setNumberPlate(String numberPlate) {

		this.numberPlate = numberPlate;
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

	
	public void setParkingDate (Date fromDate, Date issuingDate){
		
		if (issuingDate == null || fromDate == null) return;
				
		this.fromParkingDate = DateTimeUtils.getDateDifference(issuingDate,fromDate);

	}
	
	public Date getFromParkingDate(Date issuingDate){
		
		return DateTimeUtils.getDate(issuingDate, this.fromParkingDate, null);
		
	}

	public Date getToParkingDate(Date issuingDate) {
		
		if ( this.toParkingDate == null) return null;
		
		return DateTimeUtils.getDate(issuingDate, this.fromParkingDate + this.toParkingDate, null);
		
	}

	public void setParkingDates(Date fromDate, Date toDate,	Date issuingDate) {
		
		if (issuingDate == null || fromDate == null) return;
		
		this.fromParkingDate = DateTimeUtils.getDateDifference(issuingDate,fromDate);

		
		if (toDate != null) {
			this.toParkingDate = DateTimeUtils.getDateDifference(fromDate,toDate);

		}
		
	}
	
}
