package org.uic.barcode.ssbFrame;

import org.uic.barcode.asn1.uper.BitBuffer;
import org.uic.barcode.asn1.uper.ByteBitBuffer;

public class SsbNonReservation extends SsbCommonTicketPart {
	
	protected int firstDayOfValidity = 0;
	protected int lastDayOfValidity = 0;
	protected boolean isReturnJourney = false;
	private int infoCode = 0;
	private String text = null;
	private SsbStations stations = new SsbStations();
	

	@Override
	protected int decodeContent(byte[] bytes, int offset) {
		
		offset = offset + decodeCommonPart(bytes);
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		isReturnJourney = bits.get(offset);
		offset = offset++;
		
		firstDayOfValidity = bits.getInteger(offset, 9);
		offset = offset + 9;
		
		lastDayOfValidity = bits.getInteger(offset, 9);
		offset = offset + 9;
		
		offset = stations.decode(offset, bytes);
		
		infoCode = bits.getInteger(offset, 14);
		offset = offset + 14;
		
		text = bits.getChar6String(offset, 222);
		offset = offset + 222;
		
		return offset;
		
	}

	@Override
	protected int encodeContent(byte[] bytes, int offset) {
		
		offset = offset + encodeCommonPart(bytes, offset);
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		bits.put(offset, isReturnJourney);
		offset = offset++;
		
		bits.putInteger(offset, 9, firstDayOfValidity);
		offset = offset + 9;
		
		bits.putInteger(offset, 9, lastDayOfValidity);
		offset = offset + 9;
		
		offset = stations.encode(offset, bytes);
		
		bits.putInteger(offset, 14, infoCode);
		offset = offset + 14;
		
		bits.putChar6String(offset, 222, text);
		offset = offset + 222;
		
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
