package org.uic.barcode.ssbFrame;

import org.uic.barcode.asn1.uper.BitBuffer;
import org.uic.barcode.asn1.uper.ByteBitBuffer;

public class SsbReservation extends SsbCommonTicketPart {
	
	private SsbStations stations = new SsbStations();
	
	private int ticketSubType = 0;
	
	private int departureDate = 0;
	
	private int departureTime = 0;
	
	private String train = null;
	
	private int coach = 0;
	
	private String place = null;
	
	private boolean overbooking = false;
	
	private int infoCode = 0;
	
	private String text = null;
	
	
	
	

	@Override
	protected void decodeContent(byte[] bytes) {
		
		int offset = decodeCommonPart(bytes);
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		ticketSubType = bits.getInteger(offset, 2);
		offset = offset + 4;
		
		stations = new SsbStations();
		stations.decode(offset, bytes);
		
		/*
		 * 	Departure date : First day of validity from the issuing date	Num (<367)	9,000
			Departure Time	Num (<1440)	11,000
			Train number	AlphaNum + 5 Car	30,000
			Coach number	Num (< 999)	10,000
			Seat/berth number	3 AlphaNum	18,000
			Overbooking indicator	Bit Flag	1,000
			Information Messages	Num (< 9999)	14,000
			Open Tekst	6 Bit ASCII (27 Car)	162,000
		 */
		
		departureDate = bits.getInteger(offset, 9);
		offset = offset + 9;
		
		departureTime = bits.getInteger(offset, 11);
		offset = offset + 11;
		
		train = bits.getChar6String(offset, 30);
		offset = offset +30;
		
		coach = bits.getInteger(offset, 10);
		offset = offset + 10;
		
		place = bits.getChar6String(offset, 18);
		offset = offset + 18;
		
		overbooking = bits.get(offset);
		offset++;
		
		infoCode = bits.getInteger(offset, 14);
		offset = offset + 14;
		
		text = bits.getChar6String(offset, 162);
		offset = offset + 162;
	}

	@Override
	protected void encodeContent(byte[] bytes) {
		
		int offset = encodeCommonPart(bytes);
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		bits.putInteger(offset, 2,ticketSubType);
		offset = offset + 4;

		offset = stations.encode(offset, bytes);
		
		/*
		 * 	Departure date : First day of validity from the issuing date	Num (<367)	9,000
			Departure Time	Num (<1440)	11,000
			Train number	AlphaNum + 5 Car	30,000
			Coach number	Num (< 999)	10,000
			Seat/berth number	3 AlphaNum	18,000
			Overbooking indicator	Bit Flag	1,000
			Information Messages	Num (< 9999)	14,000
			Open Tekst	6 Bit ASCII (27 Car)	162,000
		 */
		
		bits.putInteger(offset, 9, departureDate);
		offset = offset + 9;
		
		bits.putInteger(offset, 11,departureTime);
		offset = offset + 11;
		
		bits.putChar6String(offset, 30,train);
		offset = offset +30;
		
		bits.putInteger(offset, 10,coach);
		offset = offset + 10;
		
		bits.putChar6String(offset, 18,place);
		offset = offset + 18;
		
		bits.put(offset, overbooking);
		offset++;
		
		bits.putInteger(offset, 14, infoCode);
		offset = offset + 14;
		
		bits.putChar6String(offset, 162, text);
		offset = offset + 162;
		
	}

	public SsbStations getStations() {
		return stations;
	}

	public int getTicketSubType() {
		return ticketSubType;
	}

	public void setTicketSubType(int ticketSubType) {
		this.ticketSubType = ticketSubType;
	}

	public int getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(int departureDate) {
		this.departureDate = departureDate;
	}

	public int getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(int departureTime) {
		this.departureTime = departureTime;
	}

	public String getTrain() {
		return train;
	}

	public void setTrain(String train) {
		this.train = train;
	}

	public int getCoach() {
		return coach;
	}

	public void setCoach(int coach) {
		this.coach = coach;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public boolean isOverbooking() {
		return overbooking;
	}

	public void setOverbooking(boolean overbooking) {
		this.overbooking = overbooking;
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
