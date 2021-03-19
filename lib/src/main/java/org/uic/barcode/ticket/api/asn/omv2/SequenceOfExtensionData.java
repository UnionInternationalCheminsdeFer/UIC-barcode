package org.uic.barcode.ticket.api.asn.omv2;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfExtensionData extends Asn1SequenceOf<ExtensionData> {
    public SequenceOfExtensionData() { super(); }
    public SequenceOfExtensionData(Collection<ExtensionData> coll) { super(coll); }
}	