package org.uic.barcode.ticket.api.asn.omv2;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfValidityPeriodType extends Asn1SequenceOf<ValidityPeriodType> {
    public SequenceOfValidityPeriodType() { super(); }
    public SequenceOfValidityPeriodType(Collection<ValidityPeriodType> coll) { super(coll); }
}	
