package org.uic.ticket.api.asn.omv2;

import java.util.Collection;
import java.util.List;


import net.gcdc.asn1.datatypes.Asn1SequenceOf;
import net.gcdc.asn1.datatypes.IntRange;

@IntRange(minValue=0,maxValue=31)
public class SequenceOfTransportTypes extends Asn1SequenceOf<Long> {
    public SequenceOfTransportTypes() { super(); }
    public SequenceOfTransportTypes(Collection<Long> coll) { super(coll); }
    

	public SequenceOfTransportTypes(List<Long> numbers) {
		super();
		for (Long number: numbers){
			this.add(new Long(number));
		}
	}
	
	public static SequenceOfTransportTypes getSequence(List<Long> numList) {
		if (numList == null || numList.isEmpty()) return null;
		return new SequenceOfTransportTypes(numList);
	}
}	