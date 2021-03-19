package org.uic.barcode.staticFrame;

import java.io.IOException;

import org.uic.barcode.ticket.EncodingFormatException;


/**
 * The Class GENERICDataRecord implements a generic bilateral data record included in a static bar code frame.
 */
public class GENERICDataRecord extends DataRecord {

	/**
	 * Instantiates a new GENERIC data record.
	 *
	 * @param idTag the id tag
	 */
	public GENERICDataRecord(String idTag) {
		super(idTag);
	}

	/**
	 * Decode content.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	@Override
	protected void decodeContent() throws IOException, EncodingFormatException {
		// Do Nothing, needs to be implemented by subclasses
	}

	/**
	 * Encode content.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	@Override
	protected void encodeContent() throws IOException, EncodingFormatException {
		// Do Nothing, needs to be implemented by subclasses
	}
	
	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}
	
	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

}
