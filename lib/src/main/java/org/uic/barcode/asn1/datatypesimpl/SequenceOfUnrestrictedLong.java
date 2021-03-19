package org.uic.barcode.asn1.datatypesimpl;

import java.util.Collection;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

/*
 * Sequence of Asn1Integer for restricted integers
 * 
 */
public class SequenceOfUnrestrictedLong extends Asn1SequenceOf<Long> {
    public SequenceOfUnrestrictedLong() { super(); }
    public SequenceOfUnrestrictedLong(Collection<Long> coll) { super(coll); }
	

	public SequenceOfUnrestrictedLong(List<Long> numbers) {
		super();
		for (Long number: numbers){
			this.add(new Long(number));
		}
	}
	
	
	public static SequenceOfUnrestrictedLong getSequence(List<Long> numList) {
		if (numList == null || numList.isEmpty()) return null;
		return new SequenceOfUnrestrictedLong(numList);
	}
	
}	
