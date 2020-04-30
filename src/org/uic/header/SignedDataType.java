package org.uic.header;

import net.gcdc.asn1.datatypes.Asn1Optional;
import net.gcdc.asn1.datatypes.Sequence;
import net.gcdc.asn1.datatypesimpl.OctetString;

// TODO: Auto-generated Javadoc
/**
 * The Class SignedDataType.
 */
@Sequence
public class SignedDataType {
	
	/** The data. */
	public DataBlockType data;
	
	/** The signature. */
	@Asn1Optional public OctetString signature;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public DataBlockType getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(DataBlockType data) {
		this.data = data;
	}

	/**
	 * Gets the signature.
	 *
	 * @return the signature
	 */
	public OctetString getSignature() {
		return signature;
	}

	/**
	 * Sets the signature.
	 *
	 * @param signature the new signature
	 */
	public void setSignature(OctetString signature) {
		this.signature = signature;
	}

	
	
}
