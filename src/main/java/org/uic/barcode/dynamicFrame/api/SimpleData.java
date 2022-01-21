package org.uic.barcode.dynamicFrame.api;

import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.asn1.uper.UperEncoder;

/**
 * The Class DataType.
 */
public class SimpleData implements IData{


	/** The data format.
	 * 
	 *  -- FCB1  FCB version 1
	 *  -- FCB2  FCB version 2
	 *  -- RICS  company code + ...
	 **/
	public String format;
	
	/** The data. */
	public byte[] data;

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
	public byte[] getData() {
		return data;
	}

	@Override
	public void setData(byte[] data) {
		this.data = data;
	}

}
