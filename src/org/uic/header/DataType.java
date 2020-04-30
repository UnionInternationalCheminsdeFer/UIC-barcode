package org.uic.header;

import net.gcdc.asn1.datatypes.Asn1Default;
import net.gcdc.asn1.datatypes.Sequence;
import net.gcdc.asn1.datatypesimpl.OctetString;

/**
 * The Class DataType.
 */
@Sequence
public class DataType {

	/*
	 *   	-- format:
	    	-- FCB1  FCB version 1
	    	-- RICS  company code + addon
	 */
	
	/** The data format.
	 * 
	 *  -- FCB1  FCB version 1
	 *  -- FCB2  FCB version 2
	 *  -- RICS  company code + ...
	 **/
	@Asn1Default("FCB1")
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

}
