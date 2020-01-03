package org.uic.ticket.api.asn.omv1;

import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfCustomerStatusType extends Asn1SequenceOf<CustomerStatusType> {
    public SequenceOfCustomerStatusType() { super(); }
    public SequenceOfCustomerStatusType(Collection<CustomerStatusType> coll) { super(coll); }
}	

