package org.uic.barcode.asn1.uper;

public class Asn1EncodingException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8719453936776248228L;

    public Asn1EncodingException(String message) {
        super(message);
    }

    public Asn1EncodingException(String extraMessage, Asn1EncodingException cause) {
        super(extraMessage + cause.getMessage(), cause);
    }

}
