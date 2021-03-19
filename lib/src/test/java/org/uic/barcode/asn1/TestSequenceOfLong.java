package org.uic.barcode.asn1;

import java.util.Collection;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

public class TestSequenceOfLong extends Asn1SequenceOf<Long> {
	    public TestSequenceOfLong() { super(); }
	    public TestSequenceOfLong(Collection<Long> coll) { super(coll); }
	    

		public TestSequenceOfLong(List<Long> numbers) {
			super();
			for (Long number: numbers){
				this.add(new Long(number));
			}
		}
		
		public static TestSequenceOfLong getSequence(List<Long> numList) {
			if (numList == null || numList.isEmpty()) return null;
			return new TestSequenceOfLong(numList);
		}
}
