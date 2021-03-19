/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

/**
 * The Interface IDocumentData.
 * 
 * IDocumentData is the super class of transport documents and customer cards. It provides a token.
 * 
 */
public interface IDocumentData {
	
	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public IToken getToken();
	
	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public void setToken(IToken token);
	
	
	
	

}
