package org.uic.barcode.ticket.api.asn.omv2;

import java.util.Collection;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;
import org.uic.barcode.asn1.datatypes.IntRange;

@IntRange(minValue=0,maxValue=32000)
public class SequenceOfServiceBrands extends Asn1SequenceOf<Long> {
    public SequenceOfServiceBrands() { super(); }
    public SequenceOfServiceBrands(Collection<Long> coll) { super(coll); }
    

	public SequenceOfServiceBrands(List<Long> numbers) {
		super();
		for (Long number: numbers){
			this.add(new Long(number));
		}
	}
	
	public static SequenceOfServiceBrands getSequence(List<Long> numList) {
		if (numList == null || numList.isEmpty()) return null;
		return new SequenceOfServiceBrands(numList);
	}
}	