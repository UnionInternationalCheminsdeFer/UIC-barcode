package net.gcdc.asn1.datatypesimpl;

import java.util.Collection;
import java.util.List;

import net.gcdc.asn1.datatypes.Asn1BigInteger;
import net.gcdc.asn1.datatypes.Asn1SequenceOf;

/*
 * Sequence of Asn1Integer for restricted integers
 * 
 */
public class SequenceOfUnrestrictedLong extends Asn1SequenceOf<Asn1BigInteger> {
    public SequenceOfUnrestrictedLong() { super(); }
    public SequenceOfUnrestrictedLong(Collection<Asn1BigInteger> coll) { super(coll); }
    
	public void add(Long num) {
		add (new Asn1BigInteger(num));
	}
	

	public SequenceOfUnrestrictedLong(List<Long> numbers) {
		super();
		for (Long number: numbers){
			this.add(new Asn1BigInteger(number));
		}
	}
	
	
	public static SequenceOfUnrestrictedLong getSequence(List<Long> numList) {
		if (numList == null || numList.isEmpty()) return null;
		return new SequenceOfUnrestrictedLong(numList);
	}
	
}	
