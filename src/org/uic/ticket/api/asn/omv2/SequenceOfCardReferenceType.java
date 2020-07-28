package org.uic.ticket.api.asn.omv2;

import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfCardReferenceType extends Asn1SequenceOf<CardReferenceType> {
    public SequenceOfCardReferenceType() { super(); }
    public SequenceOfCardReferenceType(Collection<CardReferenceType> coll) { super(coll); }
}	
