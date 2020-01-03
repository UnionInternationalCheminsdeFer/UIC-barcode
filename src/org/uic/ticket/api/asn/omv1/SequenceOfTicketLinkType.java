package org.uic.ticket.api.asn.omv1;

import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfTicketLinkType extends Asn1SequenceOf<TicketLinkType> {
    public SequenceOfTicketLinkType() { super(); }
    public SequenceOfTicketLinkType(Collection<TicketLinkType> coll) { super(coll); }
}	