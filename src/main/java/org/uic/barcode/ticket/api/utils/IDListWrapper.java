package org.uic.barcode.ticket.api.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringIA5;
import org.uic.barcode.ticket.EncodingFormatException;

public class IDListWrapper {
	private SequenceOfStringIA5 stringList = null;
	private List<Long> numList = null;
	
	public IDListWrapper(Collection<CharSequence> list, int min, int max) throws EncodingFormatException {
        if (list != null && !list.isEmpty()) {
            for (CharSequence v : list){
                if (v instanceof String) {
                    String string = (String) v;
                    if (string.isEmpty()) {
                        return;
                    }

                    String ia5 = UicEncoderUtils.getIA5RestrictedNonNum(string,min,max);
                    if (ia5 != null && !ia5.isEmpty()) {
                        if (stringList == null) {
                            stringList = new SequenceOfStringIA5();
                        }
                        stringList.add(ia5);
                    } else {
                        Long l =  UicEncoderUtils.getRestrictedNum(string,min,max);
                        if (l != null) {
                            if (numList == null) {
                                numList = new ArrayList<Long>();
                            }
                            numList.add(l);
                        }
                    }
                } else if (v instanceof MustString) {
                    MustString string = (MustString) v;
                    if (string.isEmpty()) {
                        return;
                    }
                    String ia5 = UicEncoderUtils.getIA5(string.toString());
                    if (ia5 != null && !ia5.isEmpty()) {
                        if (stringList == null) {
                            stringList = new SequenceOfStringIA5();
                        }
                        stringList.add(ia5);
                    }
                } else {
                    throw new EncodingFormatException("Invalid ID inner type");
                }
            }
        }
	}

	public IDListWrapper(Collection<CharSequence> list) throws EncodingFormatException {
        if (list != null && !list.isEmpty()) {
            for (CharSequence v : list){
                if (v instanceof String) {
                    String string = (String) v;
                    if (string.isEmpty()) {
                        return;
                    }

                    String ia5 = UicEncoderUtils.getIA5NonNum(string);
                    if (ia5 != null && !ia5.isEmpty()) {
                        if (stringList == null) {
                            stringList = new SequenceOfStringIA5();
                        }
                        stringList.add(ia5);
                    } else {
                        Long l =  UicEncoderUtils.getNum(string);
                        if (l != null) {
                            if (numList == null) {
                                numList = new ArrayList<>();
                            }
                            numList.add(l);
                        }
                    }
                } else if (v instanceof MustString) {
                    MustString string = (MustString) v;
                    if (string.isEmpty()) {
                        return;
                    }
                    String ia5 = UicEncoderUtils.getIA5(string.toString());
                    if (ia5 != null && !ia5.isEmpty()) {
                        if (stringList == null) {
                            stringList = new SequenceOfStringIA5();
                        }
                        stringList.add(ia5);
                    }
                } else {
                    throw new EncodingFormatException("Invalid ID inner type");
                }
            }
        }
	}

	public SequenceOfStringIA5 getStringList() {
		return stringList;
	}

	public List<Long> getNumList() {
		return numList;
	}
}
