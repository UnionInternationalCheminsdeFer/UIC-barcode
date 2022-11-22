package org.uic.barcode.ssbFrame;

import org.uic.barcode.asn1.uper.BitBuffer;
import org.uic.barcode.asn1.uper.ByteBitBuffer;

public abstract class SsbCommonTicketPart extends SsbTicketPart {
	
	/*
	Number of adult passengers	Num (<100)	7,000
	Number of child passengers	Num (<100)	7,000
	"specimen" code	Bit Flag	1,000
	Class of travel	Lookup of 64 options	6,000
	TCN: Issuing unique Ticket number	14 AlphaNum	84,000
	Year of issue	Num (0..9)	4,000
	Issuing day, from first of January	Num (<512)	9,000
	 */
	
	protected int numberOfAdults = 0;
	protected int numberOfChildren = 0;
	protected boolean specimen = true;
	protected SsbClass classCode = null;
	protected String ticketNumber = null;
	protected int year = 0;
	protected int day = 0;
			
	protected int decodeCommonPart(byte[] bytes) {
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		int offset = 27; // header offset
		numberOfAdults = bits.getInteger(offset, 7); 
		offset = offset + 7;
		numberOfChildren = bits.getInteger(offset, 7); 
		offset = offset + 7;
		specimen = bits.get(offset);
		offset++;
		int classIndex = bits.getInteger(offset, 6);
		classCode = SsbClass.values()[classIndex];
		offset = offset + 6;
		ticketNumber = bits.getChar6String(offset, 84);
		offset = offset + 84;
		year = bits.getInteger(offset, 4);
		offset = offset + 4;
		day = bits.getInteger(offset, 9);
		offset = offset + 9;
		return offset;
	}
	
	protected int encodeCommonPart(byte[] bytes) {
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		int offset = 27; // header offset
		bits.putInteger(offset,7, numberOfAdults);
		offset = offset + 7;
		bits.putInteger(numberOfChildren,offset, 7); 
		offset = offset + 7;
		bits.put(offset,specimen);
		offset++;
		bits.putInteger(offset, 6,classCode.ordinal());
		offset = offset + 6;
		bits.putChar6String(offset, 84, ticketNumber);
		offset = offset + 84;
		bits.putInteger(offset, 4, year);
		offset = offset + 4;
		bits.putInteger(offset, 9, day);
		offset = offset + 9;
		
		return offset;
		
		
	}

	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public boolean isSpecimen() {
		return specimen;
	}

	public void setSpecimen(boolean specimen) {
		this.specimen = specimen;
	}

	public SsbClass getClassCode() {
		return classCode;
	}

	public void setClassCode(SsbClass classCode) {
		this.classCode = classCode;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	
	
}
