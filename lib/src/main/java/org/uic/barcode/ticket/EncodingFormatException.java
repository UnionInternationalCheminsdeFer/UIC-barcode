/*
 * 
 */
package org.uic.barcode.ticket;


/**
 * The Class EncodingFormatException.
 */
public class EncodingFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6949233719793346110L;

	/**
	 * Instantiates a new encoding format exception. The exception indicates a violation of the asn.1 ticket format specification
	 *
	 * @param message the message
	 */
	public EncodingFormatException(String message) {
		super(message);
	}

}
