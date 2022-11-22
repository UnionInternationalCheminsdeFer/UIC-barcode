package org.uic.barcode.ssbFrame;

import org.uic.barcode.asn1.uper.BitBuffer;
import org.uic.barcode.asn1.uper.ByteBitBuffer;

public class SsbGroup extends SsbCommonTicketPart {

	protected int firstDayOfValidity = 0;
	protected int lastDayOfValidity = 0;
	protected boolean isReturnJourney = false;
	private int infoCode = 0;
	private String text = null;
	private SsbStations stations = new SsbStations();
	
	private String groupName = null;
	
	private int counterMarkNumber = 0;
	

	@Override
	protected void decodeContent(byte[] bytes) {
		
		int offset = decodeCommonPart(bytes);
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		isReturnJourney = bits.get(offset);
		offset = offset++;
		
		firstDayOfValidity = bits.getInteger(offset, 9);
		offset = offset + 9;
		
		lastDayOfValidity = bits.getInteger(offset, 9);
		offset = offset + 9;
		
		offset = stations.decode(offset, bytes);
		
		groupName = bits.getChar6String(offset, 72);
		offset = offset + 72;
		
		counterMarkNumber = bits.getInteger(offset, 9);
		offset = offset + 9;
		
		infoCode = bits.getInteger(offset, 14);
		offset = offset + 14;
		
		text = bits.getChar6String(offset, 222);
		offset = offset + 222;
		
	}

	@Override
	protected void encodeContent(byte[] bytes) {
		
		int offset = encodeCommonPart(bytes);
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		bits.put(offset, isReturnJourney);
		offset = offset++;
		
		bits.putInteger(offset, 9, firstDayOfValidity);
		offset = offset + 9;
		
		bits.putInteger(offset, 9, lastDayOfValidity);
		offset = offset + 9;
		
		offset = stations.decode(offset, bytes);
		
		bits.putChar6String(offset, 72,groupName);
		offset = offset + 72;
		
		bits.putInteger(offset, 9,counterMarkNumber);
		offset = offset + 9;
		
		bits.putInteger(offset, 14, infoCode);
		offset = offset + 14;
		
		bits.putChar6String(offset, 144, text);
		offset = offset + 222;
		
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getCounterMarkNumber() {
		return counterMarkNumber;
	}

	public void setCounterMarkNumber(int counterMarkNumber) {
		this.counterMarkNumber = counterMarkNumber;
	}
	
	

}
