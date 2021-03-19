/*
 * 
 */
package org.uic.barcode.ticket;

/**
 * The Class EncodingFormatException.
 */
public class UnsuportedFeatureException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3156877999150353704L;

	/**
	 * 
	 */


	/**
	 * Instantiates a new encoding format exception. The exception indicates a violation of the asn.1 ticket format specification
	 *
	 * @param message the message
	 */
	public UnsuportedFeatureException(String message) {
		super(message);
	}

}
