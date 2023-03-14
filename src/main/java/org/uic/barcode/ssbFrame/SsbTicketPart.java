package org.uic.barcode.ssbFrame;

import org.uic.barcode.ticket.EncodingFormatException;

public abstract class SsbTicketPart {
	
	public static int openDataLength = 437;
	
	public void decode(byte[] bytes) throws EncodingFormatException {
		if (bytes.length != 114) {
			throw new EncodingFormatException("Data size does not fit to SSB");
		}
		decodeContent(bytes, 0);
	};
	
	protected abstract int decodeContent(byte[] bytes , int offset);

	public void encode(byte[] bytes) throws EncodingFormatException {
		if (bytes.length != 114) {
			throw new EncodingFormatException("Data size does not fit to SSB");
		}
		encodeContent(bytes, 0);
	}

	protected abstract int encodeContent(byte[] bytes, int offset) throws EncodingFormatException;
	
	
	
	

}
