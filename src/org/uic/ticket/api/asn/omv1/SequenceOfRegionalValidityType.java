package org.uic.ticket.api.asn.omv1;

import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfRegionalValidityType extends Asn1SequenceOf<RegionalValidityType> {
    public SequenceOfRegionalValidityType() { super(); }
    public SequenceOfRegionalValidityType(Collection<RegionalValidityType> coll) { super(coll); }
}