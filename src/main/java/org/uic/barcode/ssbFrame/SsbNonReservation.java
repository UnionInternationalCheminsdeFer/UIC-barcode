package org.uic.barcode.ssbFrame;

import org.uic.barcode.asn1.uper.BitBuffer;
import org.uic.barcode.asn1.uper.ByteBitBuffer;
import org.uic.barcode.ticket.EncodingFormatException;

public class SsbNonReservation extends SsbCommonTicketPart {
	
	protected int firstDayOfValidity = 0;
	protected int lastDayOfValidity = 0;
	protected boolean isReturnJourney = false;
	private int infoCode = 0;
	private String text = null;
	private SsbStations stations = new SsbStations();
	

	@Override
	protected int decodeContent(byte[] bytes, int offset) {
		
		offset = decodeCommonPart(bytes);
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		isReturnJourney = bits.get(offset);
		offset++;
		
		firstDayOfValidity = bits.getInteger(offset, 9);
		offset += 9;
		
		lastDayOfValidity = bits.getInteger(offset, 9);
		offset += 9;
		
		offset = stations.decode(offset, bytes);
		
		infoCode = bits.getInteger(offset, 14);
		offset += 14;
		
		text = bits.getChar6String(offset, 222);
		offset += 222;
		
		return offset;
		
	}

	@Override
	protected int encodeContent(byte[] bytes, int offset) throws EncodingFormatException {
		
		offset = encodeCommonPart(bytes, offset);
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		bits.put(offset, isReturnJourney);
		offset++;
		
		if (firstDayOfValidity < 0 || firstDayOfValidity > 511) {
			throw new EncodingFormatException("SSB first day of validity too big");
		}
		bits.putInteger(offset, 9, firstDayOfValidity);
		offset += 9;
		
		if (lastDayOfValidity < 0 || lastDayOfValidity > 511) {
			throw new EncodingFormatException("SSB last day of validity too big");
		}
		bits.putInteger(offset, 9, lastDayOfValidity);
		offset += 9;
		
		offset = stations.encode(offset, bytes);
		
		if (infoCode < 0 || infoCode > 9999) {
			throw new EncodingFormatException("SSB info code too big");
		}
		bits.putInteger(offset, 14, infoCode);
		offset += 14;
		
		if (text.length() > 37) {
			throw new EncodingFormatException("SSB text too big");
		}
		bits.putChar6String(offset, 222, text);
		offset += 222;
		
		return offset;
		
	}

	public int getFirstDayOfValidity() {
		return firstDayOfValidity;
	}

	public void setFirstDayOfValidity(int firstDayOfValidity) {
		this.firstDayOfValidity = firstDayOfValidity;
	}

	public int getLastDayOfValidity() {
		return lastDayOfValidity;
	}

	public void setLastDayOfValidity(int lastDayOfValidity) {
		this.lastDayOfValidity = lastDayOfValidity;
	}

	public boolean isReturnJourney() {
		return isReturnJourney;
	}

	public void setReturnJourney(boolean isReturnJourney) {
		this.isReturnJourney = isReturnJourney;
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

	public SsbStations getStations() {
		return stations;
	}
	
	

}
