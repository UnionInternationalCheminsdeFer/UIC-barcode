package org.uic.ticket.api.asn.omv2;

import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfBerthDetailData extends Asn1SequenceOf<BerthDetailData> {
    public SequenceOfBerthDetailData() { super(); }
    public SequenceOfBerthDetailData(Collection<BerthDetailData> coll) { super(coll); }
}