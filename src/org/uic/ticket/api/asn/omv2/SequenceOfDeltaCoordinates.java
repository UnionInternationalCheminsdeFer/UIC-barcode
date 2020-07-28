package org.uic.ticket.api.asn.omv2;

import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfDeltaCoordinates extends Asn1SequenceOf<DeltaCoordinates> {
    public SequenceOfDeltaCoordinates() { super(); }
    public SequenceOfDeltaCoordinates(Collection<DeltaCoordinates> coll) { super(coll); }
}	