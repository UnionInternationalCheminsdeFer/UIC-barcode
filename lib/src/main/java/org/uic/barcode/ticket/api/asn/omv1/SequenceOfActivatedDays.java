package org.uic.barcode.ticket.api.asn.omv1;

import java.util.Collection;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;
import org.uic.barcode.asn1.datatypes.IntRange;

@IntRange(minValue=0,maxValue=370)
public class SequenceOfActivatedDays extends Asn1SequenceOf<Long> {
    public SequenceOfActivatedDays() { super(); }
    public SequenceOfActivatedDays(Collection<Long> coll) { super(coll); }
    
	public SequenceOfActivatedDays(List<Long> numbers) {
		super();
		for (Long number: numbers){
			this.add(new Long(number));
		}
	}
	
	public static SequenceOfActivatedDays getSequence(List<Long> numList) {
		if (numList == null || numList.isEmpty()) return null;
		return new SequenceOfActivatedDays(numList);
	}
	
}