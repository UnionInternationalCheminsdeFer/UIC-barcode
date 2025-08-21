package org.uic.barcode.asn1.datatypesimpl;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class SequenceOfString extends Asn1SequenceOf<String> {
    public SequenceOfString() { super(); }
    public SequenceOfString(Collection<String> coll) { super(coll); }
}	
