package org.uic.barcode.ticket.api.asn.omv1;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfIncludedOpenTicketType extends Asn1SequenceOf<IncludedOpenTicketType> {
    public SequenceOfIncludedOpenTicketType() { super(); }
    public SequenceOfIncludedOpenTicketType(Collection<IncludedOpenTicketType> coll) { super(coll); }
}	
