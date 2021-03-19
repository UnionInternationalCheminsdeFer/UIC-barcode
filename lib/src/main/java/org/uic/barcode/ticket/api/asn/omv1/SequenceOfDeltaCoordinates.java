package org.uic.barcode.ticket.api.asn.omv1;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfDeltaCoordinates extends Asn1SequenceOf<DeltaCoordinates> {
    public SequenceOfDeltaCoordinates() { super(); }
    public SequenceOfDeltaCoordinates(Collection<DeltaCoordinates> coll) { super(coll); }
}	