package org.uic.barcode.ticket.api.utils;

import org.uic.barcode.ticket.EncodingFormatException;

public class NumWrapper {

	private String ia5string = null;
	private Long number = null;
	
	public NumWrapper(String string, int min, int max) throws EncodingFormatException {
		
		
		if (string == null || string.isEmpty()) {
			return;
		}
		
		ia5string = UicEncoderUtils.getIA5RestrictedNonNum (string,min,max);
		if (ia5string == null || ia5string.length() == 0) {
			number =  UicEncoderUtils.getRestrictedNum (string,min,max);		
		}
		if (ia5string != null && ia5string.length() == 0) {
			ia5string = null;
		}
		return;	
		
	}

	public String getString() {
		return ia5string;
	}

	public Long getNumber() {
		return number;
	}
	
}
