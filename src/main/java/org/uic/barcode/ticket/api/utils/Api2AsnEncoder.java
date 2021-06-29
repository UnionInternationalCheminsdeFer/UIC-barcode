package org.uic.barcode.ticket.api.utils;

import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public abstract interface Api2AsnEncoder {

	
	public byte[] encode(IUicRailTicket uicTicket) throws EncodingFormatException;
	

}
