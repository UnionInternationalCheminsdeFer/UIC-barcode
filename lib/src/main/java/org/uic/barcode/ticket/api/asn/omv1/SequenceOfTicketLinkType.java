package org.uic.barcode.ticket.api.asn.omv1;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfTicketLinkType extends Asn1SequenceOf<TicketLinkType> {
    public SequenceOfTicketLinkType() { super(); }
    public SequenceOfTicketLinkType(Collection<TicketLinkType> coll) { super(coll); }
}	