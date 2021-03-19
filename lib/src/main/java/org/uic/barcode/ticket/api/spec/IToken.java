package org.uic.barcode.ticket.api.spec;


/**
 * The Interface IToken.
 * 
 * IToken specifies a unique token
 */
public interface IToken {
	
	/**
	 * Gets the token provider.
	 *
	 * @return the token provider
	 */
	public String getTokenProvider();
	
	/**
	 * Gets the token specification.
	 *
	 * @return the token specification
	 */
	public String getTokenSpecification();
	
	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public byte[] getToken();
	
	/**
	 * Sets the token provider.
	 *
	 * @param provider the new token provider
	 */
	public void setTokenProvider(String provider);
	
	/**
	 * Sets the token specification.
	 *
	 * @param specification the new token specification
	 */
	public void setTokenSpecification(String specification);
	
	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public void setToken(byte[] token);
}
