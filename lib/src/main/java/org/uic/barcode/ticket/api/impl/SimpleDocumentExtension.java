/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.IDocumentExtension;
import org.uic.barcode.ticket.api.spec.IToken;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleDocumentExtension.
 */
public class SimpleDocumentExtension extends SimpleExtension   implements IDocumentExtension {

	/** The token. */
	protected IToken token;
	
	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDocumentData#getToken()
	 */
	@Override
	public IToken getToken() {
		return token;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IDocumentData#setToken(org.uic.ticket.api.spec.IToken)
	 */
	@Override
	public void setToken(IToken token) {
		this.token = token;
	}
	
	
}
