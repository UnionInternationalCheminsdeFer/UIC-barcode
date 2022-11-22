package org.uic.barcode.ssbFrame;

import org.uic.barcode.ticket.EncodingFormatException;

public abstract class SsbTicketPart {
	
	public void decode(byte[] bytes) throws EncodingFormatException {
		if (bytes.length != 114) {
			throw new EncodingFormatException("Data size does not fit to SSB");
		}
		decodeContent(bytes);
	};
	
	protected abstract void decodeContent(byte[] bytes);

	public void encode(byte[] bytes) throws EncodingFormatException {
		if (bytes.length != 114) {
			throw new EncodingFormatException("Data size does not fit to SSB");
		}
		encodeContent(bytes);
	}

	protected abstract void encodeContent(byte[] bytes);
	
	
	
	

}
