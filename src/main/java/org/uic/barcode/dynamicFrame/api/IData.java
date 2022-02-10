package org.uic.barcode.dynamicFrame.api;

/**
 * The Class DataType.
 */
public interface IData {




	/**
	 * Gets the data format.
	 *
	 * @return the data format
	 */
	public String getFormat();

	/**
	 * Sets the data format.
	 *
	 * @param dataFormat the new data format
	 */
	public void setFormat(String format);
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public byte[] getData();

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(byte[] data);


}
