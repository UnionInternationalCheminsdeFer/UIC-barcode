package org.uic.barcode.dynamicContent.fdc1;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfExtension extends Asn1SequenceOf<ExtensionData> {
	    public SequenceOfExtension() { super(); }
	    public SequenceOfExtension(Collection<ExtensionData> coll) { super(coll); }
}
