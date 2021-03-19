package org.uic.barcode.ticket.api.asn.omv2;

import java.util.Collection;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;
import org.uic.barcode.asn1.datatypes.IntRange;

@IntRange(minValue=1,maxValue=254)
public class SequenceOfPlaceNum extends Asn1SequenceOf<Long> {
    public SequenceOfPlaceNum() { super(); }
    public SequenceOfPlaceNum(Collection<Long> coll) { super(coll); }


   
	public SequenceOfPlaceNum(List<Long> numbers) {
		super();
		for (Long number: numbers){
			this.add(new Long(number));
		}
	}
	
	public static SequenceOfPlaceNum getSequence(List<Long> numList) {
		if (numList == null || numList.isEmpty()) return null;
		return new SequenceOfPlaceNum(numList);
	}

}