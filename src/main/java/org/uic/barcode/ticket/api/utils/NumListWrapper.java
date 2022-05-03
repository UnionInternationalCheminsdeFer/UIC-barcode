package org.uic.barcode.ticket.api.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringIA5;
import org.uic.barcode.ticket.EncodingFormatException;

public class NumListWrapper {
	
	private SequenceOfStringIA5 stringList = null;
	private List<Long> numList = null;
	
	public NumListWrapper(Collection<String> list, int min, int max) throws EncodingFormatException {
		
		
		if (list== null || list.isEmpty()) {
			return;
		}
		
		for (String text : list){
			String ia5 = UicEncoderUtils.getIA5RestrictedNonNum (text,min,max);
			if (ia5 != null && ia5.length() > 0) {
				if (stringList == null) {
					stringList = new SequenceOfStringIA5();
				}
				stringList.add(ia5);
			} else {
				Long l =  UicEncoderUtils.getRestrictedNum (text,min,max);
				if (l != null) {
					if (numList == null) {
						numList = new ArrayList<Long>();
					}
					numList.add(l);
				}
			}
			
		}
		return;	
		
	}

	public SequenceOfStringIA5 getStringList() {
		return stringList;
	}

	public List<Long> getNumList() {
		return numList;
	}
	
	
	

}
