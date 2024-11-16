package org.uic.barcode.ssbFrame;

import org.uic.barcode.asn1.uper.BitBuffer;
import org.uic.barcode.asn1.uper.ByteBitBuffer;
import org.uic.barcode.ticket.EncodingFormatException;

public class SsbPass extends SsbCommonTicketPart {
	
	/*
	 * RPT sub ticket type	3 values	2	2 bit	1 = INTERRAIL, 2 = EURAIL EUROPE, 3 = EURAIL OVERSEAS
       First day of validity from the issuing date	Num (<367)	9 bit 000 = open date for regular Eurail pass to be activated
       Maximum duration from the issuing date for OVERSEAS; otherwise, last day of validity	Num (<278)	9 bit 9 months max. validity
       Number of days of travel allowed	Num (<93)	7	bit	
       Country code 1	Num (<100)	7	0.875	100 = all countries
       Country code 2	Num (<99)	7	0.875	If country code 1 is 100, then 00
       Country code 3	Num (<99)	7	0.875	If country code 1 is 100, then 00
       Country code 4	Num (<99)	7	0.875	If country code 1 is 100, then 00
       Country code 5	Num (<99)	7	0.875	If country code 1 is 100, then 00
       Second page	Bit flag	1	0.125	For a two-page pass
       Information messages	Num (<9999)	14	1.75	
       Open text	6-bit ASCII (40 Char)	240	30	
	 */


	private int passSubType = 0;
	private int firstDayOfValidity = 0;
	private int maximumValidityDuration = 0;
	private int numberOfTravels = 0;
	private int country_1 = 0;
	private int country_2 = 0;
	private int country_3 = 0;
	private int country_4 = 0;
	private int country_5 = 0;
	private boolean hasSecondPage = false;
	private int infoCode = 0;
	private String text = null;
	
	@Override
	protected int decodeContent(byte[] bytes, int offset) {
		
		offset = decodeCommonPart(bytes);
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		passSubType = bits.getInteger(offset, 2);
		offset += 2;
		
		firstDayOfValidity = bits.getInteger(offset, 9);
		offset += 9;

		maximumValidityDuration = bits.getInteger(offset, 9);
		offset += 9;

		numberOfTravels = bits.getInteger(offset, 7);
		offset += 7;
		
		country_1 = bits.getInteger(offset, 7);
		offset += 7;

		country_2 = bits.getInteger(offset, 7);
		offset += 7;
		
		country_3 = bits.getInteger(offset, 7);
		offset += 7;
		
		country_4 = bits.getInteger(offset, 7);
		offset += 7;
		
		country_5 = bits.getInteger(offset, 7);
		offset += 7;
		
		hasSecondPage = bits.get(offset);
		offset++;
		
		infoCode = bits.getInteger(offset, 14);
		offset += 14;
		
		text = bits.getChar6String(offset, 240);
		offset += 240;
		
		return offset;
	}

	@Override
	protected int encodeContent(byte[] bytes, int offset) throws EncodingFormatException {
		
		offset = encodeCommonPart(bytes, offset);
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		if (passSubType < 0 || passSubType > 3) {
			throw new EncodingFormatException("SSB pass type too big");
		}
		bits.putInteger(offset, 2,passSubType);
		offset += 2;
		
		if (firstDayOfValidity < 0 || firstDayOfValidity > 511) {
			throw new EncodingFormatException("SSB first day of validity too big");
		}
		bits.putInteger(offset, 9,firstDayOfValidity);
		offset += 9;

		if (maximumValidityDuration < 0 || maximumValidityDuration > 511) {
			throw new EncodingFormatException("SSB validity duration too big");
		}
		bits.putInteger(offset, 9,maximumValidityDuration);
		offset += 9;

		if (numberOfTravels < 0 || numberOfTravels > 94) {
			throw new EncodingFormatException("SSB number of travels too big");
		}
		bits.putInteger(offset, 7, numberOfTravels);
		offset += 7;
		
		if (country_1 < 0 || country_1 > 100) {
			throw new EncodingFormatException("SSB country 1 too big");
		}
		bits.putInteger(offset, 7,country_1);
		offset += 7;

		if (country_2 < 0 || country_2 > 99) {
			throw new EncodingFormatException("SSB country 2 too big");
		}
		bits.putInteger(offset, 7,country_2);
		offset += 7;
		
		if (country_3 < 0 || country_3 > 99) {
			throw new EncodingFormatException("SSB country 3 too big");
		}
		bits.putInteger(offset, 7,country_3);
		offset += 7;
		
		if (country_4 < 0 || country_4 > 99) {
			throw new EncodingFormatException("SSB country 4 too big");
		}
		bits.putInteger(offset, 7,country_4);
		offset += 7;
		
		if (country_5 < 0 || country_5 > 99) {
			throw new EncodingFormatException("SSB country 5 too big");
		}
		bits.putInteger(offset, 7,country_5);
		offset += 7;
		
		bits.put(offset, hasSecondPage);
		offset++;
		
		if (infoCode < 0 || infoCode > 9999) {
			throw new EncodingFormatException("SSB info code too big");
		}
		bits.putInteger(offset, 14, infoCode);
		offset += 14;
		
		if (text.length() > 40) {
			throw new EncodingFormatException("SSB text too big");
		}
		bits.putChar6String(offset, 240,text);
		offset += 240;		
		
		return offset;
	}

	public int getPassSubType() {
		return passSubType;
	}

	public void setPassSubType(int passSubType) {
		this.passSubType = passSubType;
	}

	public int getFirstDayOfValidity() {
		return firstDayOfValidity;
	}

	public void setFirstDayOfValidity(int firstDayOfValidity) {
		this.firstDayOfValidity = firstDayOfValidity;
	}

	public int getMaximumValidityDuration() {
		return maximumValidityDuration;
	}

	public void setMaximumValidityDuration(int maximumValidityDuration) {
		this.maximumValidityDuration = maximumValidityDuration;
	}

	public int getNumberOfTravels() {
		return numberOfTravels;
	}

	public void setNumberOfTravels(int numberOfTravels) {
		this.numberOfTravels = numberOfTravels;
	}

	public int getCountry_1() {
		return country_1;
	}

	public void setCountry_1(int country_1) {
		this.country_1 = country_1;
	}

	public int getCountry_2() {
		return country_2;
	}

	public void setCountry_2(int country_2) {
		this.country_2 = country_2;
	}

	public int getCountry_3() {
		return country_3;
	}

	public void setCountry_3(int country_3) {
		this.country_3 = country_3;
	}

	public int getCountry_4() {
		return country_4;
	}

	public void setCountry_4(int country_4) {
		this.country_4 = country_4;
	}

	public int getCountry_5() {
		return country_5;
	}

	public void setCountry_5(int country_5) {
		this.country_5 = country_5;
	}

	public boolean isHasSecondPage() {
		return hasSecondPage;
	}

	public void setHasSecondPage(boolean hasSecondPage) {
		this.hasSecondPage = hasSecondPage;
	}

	public int getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(int infoCode) {
		this.infoCode = infoCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	


}
