package org.uic.barcode.ticket.api.asn.omv1;

import java.util.Collection;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;
import org.uic.barcode.asn1.datatypes.IntRange;

@IntRange(minValue=1,maxValue=250)
public class SequenceOfCountries extends Asn1SequenceOf<Long> {
    public SequenceOfCountries() { super(); }
    public SequenceOfCountries(Collection<Long> coll) { super(coll); }

    
	public SequenceOfCountries(List<Long> numbers) {
		super();
		for (Long number: numbers){
			this.add(new Long(number));
		}
	}
	
	public static SequenceOfCountries getSequence(List<Long> numList) {
		if (numList == null || numList.isEmpty()) return null;
		return new SequenceOfCountries(numList);
	}

}