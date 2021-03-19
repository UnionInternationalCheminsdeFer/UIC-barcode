package org.uic.barcode.asn1.datatypesimpl;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.RestrictedString;

@RestrictedString(CharacterRestriction.UTF8String)
public class SequenceOfStringUTF8 extends Asn1SequenceOf<String> {
    public SequenceOfStringUTF8() { super(); }
    public SequenceOfStringUTF8(Collection<String> coll) { super(coll); }
}	
