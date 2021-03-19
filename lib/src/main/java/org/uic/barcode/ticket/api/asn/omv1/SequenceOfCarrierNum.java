package org.uic.barcode.ticket.api.asn.omv1;

import java.util.Collection;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;
import org.uic.barcode.asn1.datatypes.IntRange;

@IntRange(minValue=1,maxValue=32000)
public class SequenceOfCarrierNum extends Asn1SequenceOf<Long> {
    public SequenceOfCarrierNum() { super(); }
    public SequenceOfCarrierNum(Collection<Long> coll) { super(coll); }
	

	public SequenceOfCarrierNum(List<Long> numbers) {
		super();
		for (Long number: numbers){
			this.add(new Long(number));
		}
	}
	public static SequenceOfCarrierNum getSequence(List<Long> numList) {
		if (numList == null || numList.isEmpty()) return null;
		return new SequenceOfCarrierNum(numList);
	}
	
	
    
}