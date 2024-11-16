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
	protected boolean alphaNumeric = true;
	
	public int encode(int offset, byte[] bytes) throws EncodingFormatException {
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		if (!alphaNumeric) try {
			Integer.parseInt(arrivalStationCode);
			Integer.parseInt(departureStationCode);
			alphaNumeric  = false;
		} catch(NumberFormatException e) {
			alphaNumeric  = true;
		}
		bits.put(offset, alphaNumeric);
		offset++;
		
		if (alphaNumeric) {
			if (departureStationCode.length() > 6) {
				throw new EncodingFormatException("SSB departure station too long");
			}	
			bits.putChar6String(offset,30, departureStationCode);
			offset += 30;
			
			if (arrivalStationCode.length() > 6) {
				throw new EncodingFormatException("SSB arrival station too long");
			}	
			bits.putChar6String(offset,30, arrivalStationCode);
			offset += 30;
		} else {
			bits.putInteger(offset, 4, codeTable.ordinal());
			offset += 4;
			
			int stationCode = Integer.parseInt(departureStationCode);
			if (stationCode < 0 || stationCode > 9999999) {
				throw new EncodingFormatException("SSB departure station code too long");
			}	
			bits.putInteger(offset, 28, stationCode);
			offset += 28;
			
			stationCode = Integer.parseInt(arrivalStationCode);
			if (stationCode < 0 || stationCode > 9999999) {
				throw new EncodingFormatException("SSB arrival station code too long");
			}	
			bits.putInteger(offset, 28, stationCode);
			offset += 28;
		}
		
		return offset;
		
	}
	
	public int decode(int offset, byte[] bytes) {
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		alphaNumeric = bits.get(offset);
		offset++;
		
		if (alphaNumeric) {
			departureStationCode = bits.getChar6String(offset,30);
			offset += 30;
			arrivalStationCode = bits.getChar6String(offset,30);
			offset += 30;
		} else {
			codeTable = SsbStationCodeTable.values()[bits.getInteger(offset, 4)];
			offset += 4;
			departureStationCode = Integer.toString(bits.getInteger(offset, 28));
			offset += 28;
			arrivalStationCode = Integer.toString(bits.getInteger(offset, 28));
			offset += 28;
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
	
	public boolean isAlphaNumeric() {
		return alphaNumeric;
	}

	public void setAlphaNumeric(boolean alphaNumeric) {
		this.alphaNumeric = alphaNumeric;
	}

}
