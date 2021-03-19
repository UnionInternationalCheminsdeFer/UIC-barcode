package org.uic.barcode.dynamicFrame;

import org.uic.barcode.asn1.datatypes.Asn1Default;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.asn1.uper.UperEncoder;

/**
 * The Class DataType.
 */
@Sequence
public class DataType {


	/** The data format.
	 * 
	 *  -- FCB1  FCB version 1
	 *  -- FCB2  FCB version 2
	 *  -- RICS  company code + ...
	 **/
	@Asn1Default("FCB1")
	@RestrictedString(CharacterRestriction.IA5String)
	public String format;
	
	/** The data. */
	public OctetString data;

	/**
	 * Gets the data format.
	 *
	 * @return the data format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the data format.
	 *
	 * @param dataFormat the new data format
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public OctetString getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(OctetString data) {
		this.data = data;
	}
	
	/**
	 * Gets the data as byte array.
	 *
	 * @return the data
	 */
	public byte[] getByteData() {
		return data.toByteArray();
	}

	/**
	 * Sets the data from a byte array.
	 *
	 * @param data the new data
	 */
	public void setByteData(byte[] data) {
		this.data = new OctetString(data);
	}
	
	/**
	 * Encode.
	 * 
	 * Encode the header as ASN.1 PER UNALIGNED byte array
	 *
	 * @return the byte[]
	 */
	public byte[] encode() {
		return UperEncoder.encode(this);
	}

}
