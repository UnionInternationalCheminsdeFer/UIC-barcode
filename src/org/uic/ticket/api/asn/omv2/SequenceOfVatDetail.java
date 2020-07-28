package org.uic.ticket.api.asn.omv2;

import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfVatDetail extends Asn1SequenceOf<VatDetailType> {
    public SequenceOfVatDetail() { super(); }
    public SequenceOfVatDetail(Collection<VatDetailType> coll) { super(coll); }
}	
