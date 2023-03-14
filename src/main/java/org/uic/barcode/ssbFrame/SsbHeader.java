package org.uic.barcode.ssbFrame;

import org.uic.barcode.asn1.uper.BitBuffer;
import org.uic.barcode.asn1.uper.ByteBitBuffer;
import org.uic.barcode.ticket.EncodingFormatException;

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

	public int decodeContent(byte[] headerData, int offset) {
		
		BitBuffer bits = new ByteBitBuffer(headerData);

		version = bits.getInteger(0, 4);
		issuer = bits.getInteger(4, 14);
		keyId = bits.getInteger(18, 4);
		ticketType = SsbTicketType.values()[bits.getInteger(22, 5)];
		
		return 4 + 14 + 4 + 5;

	}
	
	public int encodeContent(byte[] bytes, int offset) throws EncodingFormatException {
			
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		if (version < 0 || version > 15) {
			throw new EncodingFormatException("SSB Version too big");
		}
		
		bits.putInteger(0, 4, version);
		
		if (issuer < 0 || issuer > 9999) {
			throw new EncodingFormatException("SSB Issuer code too big");
		}
		
		bits.putInteger(4, 14, issuer);
		
		if (keyId < 0 || keyId > 15) {
			throw new EncodingFormatException("SSB Key Id too big");
		}
		
		bits.putInteger(18, 4, keyId);
		
		bits.putInteger(22, 5, ticketType.ordinal());
		
		return 4 + 14 + 4 + 5;
		
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
