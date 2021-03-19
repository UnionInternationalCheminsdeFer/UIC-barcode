package org.uic.barcode.ticket.api.asn.omv2;

import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.RestrictedString;

@RestrictedString(CharacterRestriction.IA5String)
public class SequenceOfCarrierIA5 extends Asn1SequenceOf<String> {
    public SequenceOfCarrierIA5() { super(); }
    public SequenceOfCarrierIA5(Collection<String> coll) { super(coll); }
}	
