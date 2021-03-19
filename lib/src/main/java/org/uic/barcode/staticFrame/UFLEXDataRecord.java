package org.uic.barcode.staticFrame;

import java.io.IOException;

import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.UicRailTicketCoder;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

/**
 * The Class UFLEXDataRecord implements the dara record to hold the data of an ASN.1 PER encoded UIC ticket.
 */
public class UFLEXDataRecord extends DataRecord {
	

	/** The ticket. */
	private IUicRailTicket ticket;

	/**
	 * Instantiates a new UFLEX data record.
	 */
	public UFLEXDataRecord() {
		super("U_FLEX");
	}
	
	/**
	 * Instantiates a new UFLEX data record.
	 *
	 * @param version the version
	 */
	public UFLEXDataRecord(String version) {
		super("U_FLEX", version);
	}


	/**
	 * Gets the ticket.
	 *
	 * @return the ticket
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	public IUicRailTicket getTicket() throws IOException, EncodingFormatException {
		if (ticket != null) {
			return ticket;
		}
		return null;
	}

	/**
	 * Sets the ticket.
	 *
	 * @param ticket the new ticket
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	public void setTicket(IUicRailTicket ticket) throws IOException, EncodingFormatException {
		this.ticket = ticket;
		super.setData(null);
	}


	/**
	 * Decode content.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	@Override
	protected void decodeContent() throws IOException, EncodingFormatException {
		UicRailTicketCoder coder = new UicRailTicketCoder();
		int version = Integer.parseInt(super.getVersionId());
		this.ticket = coder.decodeFromAsn(content,version);
	}

	/**
	 * Encode content.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	@Override
	protected void encodeContent() throws IOException, EncodingFormatException {
		UicRailTicketCoder coder = new UicRailTicketCoder();
		int version = Integer.parseInt(super.getVersionId());
		content = coder.encode(ticket, version);
	}


	
}
