package org.uic.barcode.ssbFrame;

import org.uic.barcode.asn1.uper.BitBuffer;
import org.uic.barcode.asn1.uper.ByteBitBuffer;

public class SsbHeader extends SsbTicketPart {
	
	private int version = 3;
	private SsbTicketType ticketType = null;
	private int keyId = 0;
	private int issuer = 0;
	
	/*
	        Version	             Num 0-4 Bits
			Issuer code	         Num  14 Bits
			ID					 Num   4 Bits
			Ticket type code	 Num   5 Bits
	 */

	public SsbHeader(int version, SsbTicketType type,  int keyId, int issuer) {
		this.issuer = issuer;
		this.keyId = keyId;
		this.ticketType = type;
		this.version = version;
	}
	
	public SsbHeader() {		
	}

	public void decodeContent(byte[] headerData) {
		
		BitBuffer bits = new ByteBitBuffer(headerData);

		version = bits.getInteger(0, 4);
		issuer = bits.getInteger(4, 14);
		keyId = bits.getInteger(18, 4);
		ticketType = SsbTicketType.values()[bits.getInteger(22, 5)];
		
		return;

	}
	
	public void encodeContent(byte[] bytes) {
			
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		bits.putInteger(0, 4, version);
		bits.putInteger(4, 14, issuer);
		bits.putInteger(18, 4, keyId);
		bits.putInteger(22, 5, ticketType.ordinal());
		
	}



	public int getVersion() {
		return version;
	}



	public void setVersion(int version) {
		this.version = version;
	}



	public SsbTicketType getTicketType() {
		return ticketType;
	}



	public void setTicketType(SsbTicketType ticketType) {
		this.ticketType = ticketType;
	}



	public int getKeyId() {
		return keyId;
	}



	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}



	public int getIssuer() {
		return issuer;
	}



	public void setIssuer(int issuer) {
		this.issuer = issuer;
	}



	
	
	
	
}
