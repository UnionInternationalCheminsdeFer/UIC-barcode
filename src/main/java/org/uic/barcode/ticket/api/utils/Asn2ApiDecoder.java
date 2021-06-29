package org.uic.barcode.ticket.api.utils;

import java.io.IOException;

import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public interface Asn2ApiDecoder {


	public IUicRailTicket decodeFromAsn (byte[] data) throws IOException;

	
}
