package org.uic.ticket.api.asn.omv1;

import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfDocumentData extends Asn1SequenceOf<DocumentData> {
    public SequenceOfDocumentData() { super(); }
    public SequenceOfDocumentData(Collection<DocumentData> coll) { super(coll); }
}	
