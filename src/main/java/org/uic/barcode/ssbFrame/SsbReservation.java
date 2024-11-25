package org.uic.barcode.ssbFrame;

import org.uic.barcode.asn1.uper.BitBuffer;
import org.uic.barcode.asn1.uper.ByteBitBuffer;
import org.uic.barcode.ticket.EncodingFormatException;

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
	protected int decodeContent(byte[] bytes, int offset) {
		
		offset = decodeCommonPart(bytes);
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		ticketSubType = bits.getInteger(offset, 2);
		offset += 2;
		
		stations = new SsbStations();
		offset = stations.decode(offset, bytes);
		
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
		offset += 9;
		
		departureTime = bits.getInteger(offset, 11);
		offset += 11;
		
		train = bits.getChar6String(offset, 30);
		offset += 30;
		
		coach = bits.getInteger(offset, 10);
		offset += 10;
		
		place = bits.getChar6String(offset, 18);
		offset += 18;
		
		overbooking = bits.get(offset);
		offset++;
		
		infoCode = bits.getInteger(offset, 14);
		offset += 14;
		
		text = bits.getChar6String(offset, 162);
		offset += 162;
		
		return offset;
	}

	@Override
	protected int encodeContent(byte[] bytes, int offset) throws EncodingFormatException {
		
		offset = encodeCommonPart(bytes, offset);
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		if (ticketSubType < 0 || ticketSubType > 3) {
			throw new EncodingFormatException("SSB pass type too big");
		}
		bits.putInteger(offset, 2,ticketSubType);
		offset += 2;

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
		
		if (departureDate < 0 || departureDate > 512) {
			throw new EncodingFormatException("SSB departure date too big");
		}
		bits.putInteger(offset, 9, departureDate);
		offset += 9;
		
		if (departureTime < 0 || departureTime > 1440) {
			throw new EncodingFormatException("SSB departure time too big");
		}
		bits.putInteger(offset, 11,departureTime);
		offset += 11;
		
		if (train.length() > 5) {
			throw new EncodingFormatException("SSB train too big");
		}
		bits.putChar6String(offset, 30,train);
		offset += 30;
		
		if (coach < 0 || coach > 999) {
			throw new EncodingFormatException("SSB coach too big");
		}
		bits.putInteger(offset, 10,coach);
		offset += 10;
		
		if (place.length() > 3) {
			throw new EncodingFormatException("SSB coach too big");
		}
		bits.putChar6String(offset, 18,place);
		offset += 18;
		
		bits.put(offset, overbooking);
		offset++; 
		
		if (infoCode < 0 || infoCode > 9999) {
			throw new EncodingFormatException("SSB info code too big");
		}
		bits.putInteger(offset, 14, infoCode);
		offset += 14;
		
		if (text.length() > 27) {
			throw new EncodingFormatException("SSB text too big");
		}
		bits.putChar6String(offset, 162, text);
		offset += 162;
		
		return offset;
		
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
