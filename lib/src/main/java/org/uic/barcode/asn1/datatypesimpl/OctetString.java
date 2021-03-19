package org.uic.barcode.asn1.datatypesimpl;

import java.util.Collection;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

/*
 * Sequence of Asn1Integer for restricted integers
 * 
 * 
 */
public class OctetString extends Asn1SequenceOf<Byte> {
    public OctetString() { super(); }
    public OctetString(Collection<Byte> coll) { super(coll); }

	public OctetString(List<Byte> numbers) {
		super();
		this.addAll(numbers);
	}
	
	public static OctetString getSequence(List<Byte> numList) {
		if (numList == null || numList.isEmpty()) return null;
		return new OctetString(numList);
	}
	
	
	public byte[] toByteArray () {
		
		byte[] bytes= new byte[this.size()];
		
		for (int i = 0; i < this.size(); i++){
			bytes[i] = this.get(i);
		}
		
		return bytes;
	}
	
	public OctetString(byte[] bytes){
		super();
		for (int i= 0;i < bytes.length; i++){
		  this.add(bytes[i]);
		}
	}
	
}	
