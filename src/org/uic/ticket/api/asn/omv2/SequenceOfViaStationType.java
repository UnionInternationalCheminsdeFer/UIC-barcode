package org.uic.ticket.api.asn.omv2;

import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfViaStationType extends Asn1SequenceOf<ViaStationType> {
    public SequenceOfViaStationType() { super(); }
    public SequenceOfViaStationType(Collection<ViaStationType> coll) { super(coll); }
}