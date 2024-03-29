package org.uic.barcode.ssbFrame;

import org.uic.barcode.asn1.uper.BitBuffer;
import org.uic.barcode.asn1.uper.ByteBitBuffer;
import org.uic.barcode.ticket.EncodingFormatException;

public class SsbStations {
	
	/*
	 * Station code       1	bit	0 = Num; or 1=Bilateral AlphaNum 6Char

       Numeric:
       Station code List             4 bit 1= NRT; 2=Reservation
	   Departure station Location 	28 bit 
	   Arrival Station              28 bit

	   AlphaNum:
	   Departure: 30 bit
	   Arrival =  30 bit

	 */
	
	protected String arrivalStationCode = "      ";
	protected String departureStationCode = "      ";
	protected SsbStationCodeTable codeTable = SsbStationCodeTable.NRT;
	
	public int encode(int offset, byte[] bytes) throws EncodingFormatException {
		
		boolean isAlphaNumeric = false;
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		try {
			Integer.parseInt(arrivalStationCode);
			Integer.parseInt(departureStationCode);
			isAlphaNumeric  = false;
		} catch(NumberFormatException e) {
			isAlphaNumeric  = true;
		}
		bits.put(offset, isAlphaNumeric);
		offset++;
		
		if (isAlphaNumeric) {
			if (departureStationCode.length() > 6) {
				throw new EncodingFormatException("SSB departure station too long");
			}	
			bits.putChar6String(offset,30, departureStationCode);
			offset = offset + 30;
			
			if (arrivalStationCode.length() > 6) {
				throw new EncodingFormatException("SSB arrival station too long");
			}	
			bits.putChar6String(offset,30, arrivalStationCode);
			offset = offset + 30;
		} else {
			bits.putInteger(offset, 4, codeTable.ordinal());
			offset = offset + 4;
			
			int stationCode = Integer.parseInt(departureStationCode);
			if (stationCode < 0 || stationCode > 9999999) {
				throw new EncodingFormatException("SSB departure station code too long");
			}	
			bits.putInteger(offset, 28, stationCode);
			offset = offset + 28;
			
			stationCode = Integer.parseInt(arrivalStationCode);
			if (stationCode < 0 || stationCode > 9999999) {
				throw new EncodingFormatException("SSB arrival station code too long");
			}	
			bits.putInteger(offset, 28, stationCode);
			offset = offset + 28;
		}
		
		return offset;
		
	}
	
	public int decode(int offset, byte[] bytes) {
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		boolean isAlphaNumeric = bits.get(offset);
		offset++;
		
		if (isAlphaNumeric) {
			departureStationCode = bits.getChar6String(offset,30);
			offset = offset + 30;
			arrivalStationCode = bits.getChar6String(offset,30);
			offset = offset + 30;
		} else {
			codeTable = SsbStationCodeTable.values()[bits.getInteger(offset, 4)];
			offset = offset + 4;
			departureStationCode = Integer.toString(bits.getInteger(offset, 28));
			offset = offset + 28;
			arrivalStationCode = Integer.toString(bits.getInteger(offset, 28));
			offset = offset + 28;
		}

		
		
		return offset;
		
	}

	public String getArrivalStationCode() {
		return arrivalStationCode;
	}

	public void setArrivalStationCode(String arrivalStationCode) {
		this.arrivalStationCode = arrivalStationCode;
	}

	public String getDepartureStationCode() {
		return departureStationCode;
	}

	public void setDepartureStationCode(String departureStationCode) {
		this.departureStationCode = departureStationCode;
	}

	public SsbStationCodeTable getCodeTable() {
		return codeTable;
	}

	public void setCodeTable(SsbStationCodeTable codeTable) {
		this.codeTable = codeTable;
	}
	
	
	

}
