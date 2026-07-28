package org.uic.barcode.ticket.api.impl;

import org.uic.barcode.ticket.api.spec.IToken;
import org.uic.barcode.ticket.api.utils.MustString;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleToken.
 */
public class SimpleToken implements IToken {
	
	/** The provider. */
	protected CharSequence provider;
	
	/** The specification. */
	protected String specification;
	
	/** The token. */
	protected byte[] token;

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IToken#getTokenProvider()
	 */
	@Override
	public CharSequence getTokenProvider() {
		return provider;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IToken#getTokenSpecification()
	 */
	@Override
	public String getTokenSpecification() {
		return specification;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IToken#getToken()
	 */
	@Override
	public byte[] getToken() {
		return token;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IToken#setTokenProvider(java.lang.String)
	 */
	@Override
	public void setTokenProvider(String provider) {
		this.provider = provider;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IToken#setTokenProviderMustString(java.lang.String)
	 */
	@Override
	public void setTokenProviderMustString(String provider) {
		this.provider = new MustString(provider);
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IToken#setTokenSppecification(java.lang.String)
	 */
	@Override
	public void setTokenSpecification(String specification) {
		this.specification = specification;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IToken#setToken(byte[])
	 */
	@Override
	public void setToken(byte[] token) {
		this.token = token;		
	}

}
