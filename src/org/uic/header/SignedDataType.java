package org.uic.header;

import net.gcdc.asn1.datatypes.Asn1Optional;
import net.gcdc.asn1.datatypes.Sequence;
import net.gcdc.asn1.datatypesimpl.OctetString;

/**
 * The Class SignedDataType.
 */
@Sequence
public class SignedDataType {
	
	/** The data. */
	public DataBlockType dataBlock;
	
	/** The signature. */
	@Asn1Optional public OctetString signature;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public DataBlockType getDataBlock() {
		return dataBlock;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setDataBlock(DataBlockType dataBlock) {
		this.dataBlock = dataBlock;
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
	 * @param signature the signature
	 */
	public void setSignature(OctetString signature) {
		this.signature = signature;
	}
	
	
	/**
	 * Sets the signature.
	 *
	 * @param signature the signature
	 */
	public void setSignature(byte[] signature) {
		this.signature = new OctetString(signature);
	}

	
	
}
