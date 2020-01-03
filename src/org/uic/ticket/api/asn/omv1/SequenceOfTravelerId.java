package org.uic.ticket.api.asn.omv1;

import java.util.Collection;
import java.util.List;


import net.gcdc.asn1.datatypes.Asn1SequenceOf;
import net.gcdc.asn1.datatypes.IntRange;

@IntRange(minValue=0,maxValue=254)
public class SequenceOfTravelerId extends Asn1SequenceOf<Long> {
    public SequenceOfTravelerId() { super(); }
    public SequenceOfTravelerId(Collection<Long> coll) { super(coll); }

	public SequenceOfTravelerId(List<Long> numbers) {
		super();
		for (Long number: numbers){
			this.add(new Long(number));
		}
	}
	
	public static SequenceOfTravelerId getSequence(List<Long> numList) {
		if (numList == null || numList.isEmpty()) return null;
		return new SequenceOfTravelerId(numList);
	}

}