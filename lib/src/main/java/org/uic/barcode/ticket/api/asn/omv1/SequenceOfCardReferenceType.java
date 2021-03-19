package org.uic.barcode.ticket.api.asn.omv1;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfCardReferenceType extends Asn1SequenceOf<CardReferenceType> {
    public SequenceOfCardReferenceType() { super(); }
    public SequenceOfCardReferenceType(Collection<CardReferenceType> coll) { super(coll); }
}	
