package org.uic.barcode.ticket.api.asn.omv2;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfBerthDetailData extends Asn1SequenceOf<BerthDetailData> {
    public SequenceOfBerthDetailData() { super(); }
    public SequenceOfBerthDetailData(Collection<BerthDetailData> coll) { super(coll); }
}