package org.uic.ticket.api.asn.omv2;

import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfRegisteredLuggageType extends Asn1SequenceOf<RegisteredLuggageType> {
    public SequenceOfRegisteredLuggageType() { super(); }
    public SequenceOfRegisteredLuggageType(Collection<RegisteredLuggageType> coll) { super(coll); }
}
