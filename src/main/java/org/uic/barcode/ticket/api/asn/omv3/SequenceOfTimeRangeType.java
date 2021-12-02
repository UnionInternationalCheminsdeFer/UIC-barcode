package org.uic.barcode.ticket.api.asn.omv3;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfTimeRangeType extends Asn1SequenceOf<TimeRangeType> {
    public SequenceOfTimeRangeType() { super(); }
    public SequenceOfTimeRangeType(Collection<TimeRangeType> coll) { super(coll); }
}	
