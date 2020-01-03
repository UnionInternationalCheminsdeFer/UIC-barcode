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

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import net.gcdc.asn1.datatypes.Asn1BigInteger;
import net.gcdc.asn1.datatypes.Asn1Default;
import net.gcdc.asn1.datatypes.Asn1Optional;
import net.gcdc.asn1.datatypes.CharacterRestriction;
import net.gcdc.asn1.datatypes.FixedSize;
import net.gcdc.asn1.datatypes.HasExtensionMarker;
import net.gcdc.asn1.datatypes.IntRange;
import net.gcdc.asn1.datatypes.RestrictedString;
import net.gcdc.asn1.datatypes.Sequence;

@Sequence
@HasExtensionMarker
public class IssuingData extends Object {
	public IssuingData() {
	}

	@IntRange(minValue=1,maxValue=32000)
	@Asn1Optional public Long securityProviderNum;

	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String securityProviderIA5;

	@IntRange(minValue=1,maxValue=32000)
	@Asn1Optional public Long issuerNum;

	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String issuerIA5;

	@IntRange(minValue=2016,maxValue=2269)
	public Long issuingYear;

	@IntRange(minValue=1,maxValue=366)
	public Long issuingDay;

	@IntRange(minValue=0,maxValue=1440)
	@Asn1Optional public Long issuingTime;

	@RestrictedString(CharacterRestriction.UTF8String)
	@Asn1Optional public String issuerName;

	public Boolean specimen = false;

	public Boolean securePaperTicket = false;

	public Boolean activated = true;

	@FixedSize(3)
	@Asn1Default(value="EUR")
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String currency;

	@IntRange(minValue=1,maxValue=3)
	@Asn1Default(value="2")
	@Asn1Optional public Long currencyFract;
	
	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String issuerPNR;

	@Asn1Optional public ExtensionData extension;

	@Asn1Optional public Asn1BigInteger issuedOnTrainNum;

	@RestrictedString(CharacterRestriction.IA5String)
	@Asn1Optional public String issuedOnTrainIA5;

	@Asn1Optional public Asn1BigInteger issuedOnLine;

	@Asn1Optional public GeoCoordinateType pointOfSale;
	
	
	
	
	

	public Long getSecurityProviderNum() {

		return this.securityProviderNum;
	}

	public String getSecurityProviderIA5() {

		return this.securityProviderIA5;
	}

	public Long getIssuerNum() {

		return this.issuerNum;
	}

	public String getIssuerIA5() {

		return this.issuerIA5;
	}

	public Long getIssuingYear() {

		return this.issuingYear;
	}

	public Long getIssuingDay() {

		return this.issuingDay;
	}

	public Long getIssuingTime() {

		return this.issuingTime;
	}

	public String getIssuerName() {

		return this.issuerName;
	}

	public Boolean getSpecimen() {

		return this.specimen;
	}

	public Boolean getSecurePaperTicket() {

		return this.securePaperTicket;
	}

	public Boolean getActivated() {

		return this.activated;
	}

	public String getCurrency() {

		if (currency == null || currency.length() == 0){
			return "EUR";
		}
		
		return this.currency;
	}

	public Long getCurrencyFract() {

		if (currencyFract == null) {
			return new Long(2);
		}
		return this.currencyFract;
	}

	public String getIssuerPNR() {

		return this.issuerPNR;
	}

	public ExtensionData getExtension() {

		return this.extension;
	}

	public Long getIssuedOnTrainNum() {

		return Asn1BigInteger.toLong(issuedOnTrainNum);
	}

	public String getIssuedOnTrainIA5() {

		return this.issuedOnTrainIA5;
	}

	public Long getIssuedOnLine() {

		return Asn1BigInteger.toLong(issuedOnLine);
	}

	public GeoCoordinateType getPointOfSale() {

		return this.pointOfSale;
	}

	public void setSecurityProviderNum(Long securityProviderNum) {

		this.securityProviderNum = securityProviderNum;
	}

	public void setSecurityProviderIA5(String securityProviderIA5) {

		this.securityProviderIA5 = securityProviderIA5;
	}

	public void setIssuerNum(Long issuerNum) {

		this.issuerNum = issuerNum;
	}

	public void setIssuerIA5(String issuerIA5) {

		this.issuerIA5 = issuerIA5;
	}

	public void setIssuingYear(Long issuingYear) {

		this.issuingYear = issuingYear;
	}

	public void setIssuingDay(Long issuingDay) {

		this.issuingDay = issuingDay;
	}

	public void setIssuingTime(Long issuingTime) {

		this.issuingTime = issuingTime;
	}

	public void setIssuerName(String issuerName) {

		this.issuerName = issuerName;
	}

	public void setSpecimen(Boolean specimen) {

		this.specimen = specimen;
	}

	public void setSecurePaperTicket(Boolean securePaperTicket) {

		this.securePaperTicket = securePaperTicket;
	}

	public void setActivated(Boolean activated) {

		this.activated = activated;
	}

	public void setCurrency(String currency) {

		this.currency = currency;
	}

	public void setCurrencyFract(Long currencyFract) {

		this.currencyFract = currencyFract;
	}

	public void setIssuerPNR(String issuerPNR) {

		this.issuerPNR = issuerPNR;
	}

	public void setExtension(ExtensionData extension) {

		this.extension = extension;
	}

	public void setIssuedOnTrainNum(Long issuedOnTrainNum) {

		this.issuedOnTrainNum = Asn1BigInteger.toAsn1(issuedOnTrainNum);
	}

	public void setIssuedOnTrainIA5(String issuedOnTrainIA5) {

		this.issuedOnTrainIA5 = issuedOnTrainIA5;
	}

	public void setIssuedOnLine(Long issuedOnLine) {

		this.issuedOnLine = Asn1BigInteger.toAsn1(issuedOnLine);
	}

	public void setPointOfSale(GeoCoordinateType pointOfSale) {

		this.pointOfSale = pointOfSale;
	}

	public void setIssuingDate(Date date){
		
		if (date == null) {
			date = Calendar.getInstance().getTime();
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		this.issuingYear = new Long( cal.get(Calendar.YEAR));
		this.issuingDay = new Long (cal.get(Calendar.DAY_OF_YEAR));
		int time =  cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
		if (time > 0) {
			this.issuingTime = new Long (time );
		}
		
	}
	
	public Date getIssuingDate() {
		
		if (this.issuingYear == null || this.issuingDay == null) return null;
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTimeZone(TimeZone.getTimeZone("UTC"));
		cal.set(Calendar.YEAR, this.issuingYear.intValue());
		cal.set(Calendar.DAY_OF_YEAR, this.issuingDay.intValue());
		
		if (this.issuingTime != null) {
		
			int hours = this.issuingTime.intValue() / 60;
			int minutes = this.issuingTime.intValue() % 60;
			cal.set(Calendar.HOUR_OF_DAY, hours);
			cal.set(Calendar.MINUTE,minutes);

		}
		
		return cal.getTime();
	}
	
	
	

}
