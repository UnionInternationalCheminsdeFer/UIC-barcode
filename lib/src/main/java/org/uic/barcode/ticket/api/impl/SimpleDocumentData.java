package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.IDocumentData;
import org.uic.barcode.ticket.api.spec.IToken;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleDocumentData.
 */
public class SimpleDocumentData implements IDocumentData {

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
