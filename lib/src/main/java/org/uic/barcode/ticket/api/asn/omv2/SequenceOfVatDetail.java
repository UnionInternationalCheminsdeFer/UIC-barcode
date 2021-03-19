package org.uic.barcode.ticket.api.asn.omv2;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfVatDetail extends Asn1SequenceOf<VatDetailType> {
    public SequenceOfVatDetail() { super(); }
    public SequenceOfVatDetail(Collection<VatDetailType> coll) { super(coll); }
}	
