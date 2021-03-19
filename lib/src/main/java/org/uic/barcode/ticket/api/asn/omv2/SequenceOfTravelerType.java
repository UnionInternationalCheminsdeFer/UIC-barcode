package org.uic.barcode.ticket.api.asn.omv2;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfTravelerType extends Asn1SequenceOf<TravelerType> {
    public SequenceOfTravelerType() { super(); }
    public SequenceOfTravelerType(Collection<TravelerType> coll) { super(coll); }
}	
