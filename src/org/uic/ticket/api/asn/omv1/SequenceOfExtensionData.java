package org.uic.ticket.api.asn.omv1;

import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfExtensionData extends Asn1SequenceOf<ExtensionData> {
    public SequenceOfExtensionData() { super(); }
    public SequenceOfExtensionData(Collection<ExtensionData> coll) { super(coll); }
}	