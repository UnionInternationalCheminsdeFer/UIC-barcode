package org.uic.barcode.ticket.api.utils;

import org.uic.barcode.ticket.EncodingFormatException;

public class IDWrapper {
	private String ia5string = null;
	private Long number = null;

    public IDWrapper(CharSequence v, int min, int max) throws EncodingFormatException {
        if (v != null) {
            if (v instanceof String) {
                String string = (String) v;
                if (string.isEmpty()) {
                    return;
                }
                ia5string = UicEncoderUtils.getIA5RestrictedNonNum(string, min, max);
                if (ia5string == null || ia5string.isEmpty()) {
                    number = UicEncoderUtils.getRestrictedNum(string, min, max);
                }
                if (ia5string != null && ia5string.isEmpty()) {
                    ia5string = null;
                }

            } else if (v instanceof MustString) {
                MustString string = (MustString) v;
                if (string.isEmpty()) {
                    return;
                }
                ia5string = UicEncoderUtils.getIA5(string.toString());
            } else {
                throw new EncodingFormatException("Invalid ID inner type");
            }
        }
    }

    public IDWrapper(CharSequence v) throws EncodingFormatException {
        if (v != null) {
            if (v instanceof String) {
                String string = (String) v;
                if (string.isEmpty()) {
                    return;
                }
                ia5string = UicEncoderUtils.getIA5NonNum(string);
                if (ia5string == null || ia5string.isEmpty()) {
                    number = UicEncoderUtils.getNum(string);
                }
                if (ia5string != null && ia5string.isEmpty()) {
                    ia5string = null;
                }

            } else if (v instanceof MustString) {
                MustString string = (MustString) v;
                if (string.isEmpty()) {
                    return;
                }
                ia5string = UicEncoderUtils.getIA5(string.toString());
            } else {
                throw new EncodingFormatException("Invalid ID inner type");
            }
        }
    }

	public String getString() {
		return ia5string;
	}

	public Long getNumber() {
		return number;
	}
	
}
