package org.uic.barcode.asn1.datatypesimpl;

import java.util.Collection;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

/*
 * Sequence of Asn1Integer for restricted integers
 * 
 * 
 */
public class SequenceOfLong extends Asn1SequenceOf<Long> {
    public SequenceOfLong() { super(); }
    public SequenceOfLong(Collection<Long> coll) { super(coll); }

	public SequenceOfLong(List<Long> numbers) {
		super();
		this.addAll(numbers);
	}
	
	public static SequenceOfLong getSequence(List<Long> numList) {
		if (numList == null || numList.isEmpty()) return null;
		return new SequenceOfLong(numList);
	}
	
}	
