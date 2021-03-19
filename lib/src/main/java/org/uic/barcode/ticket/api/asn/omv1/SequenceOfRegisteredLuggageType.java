package org.uic.barcode.ticket.api.asn.omv1;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfRegisteredLuggageType extends Asn1SequenceOf<RegisteredLuggageType> {
    public SequenceOfRegisteredLuggageType() { super(); }
    public SequenceOfRegisteredLuggageType(Collection<RegisteredLuggageType> coll) { super(coll); }
}
