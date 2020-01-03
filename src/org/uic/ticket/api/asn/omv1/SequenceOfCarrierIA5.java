package org.uic.ticket.api.asn.omv1;

import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;
import net.gcdc.asn1.datatypes.CharacterRestriction;
import net.gcdc.asn1.datatypes.RestrictedString;

@RestrictedString(CharacterRestriction.IA5String)
public class SequenceOfCarrierIA5 extends Asn1SequenceOf<String> {
    public SequenceOfCarrierIA5() { super(); }
    public SequenceOfCarrierIA5(Collection<String> coll) { super(coll); }
}	
