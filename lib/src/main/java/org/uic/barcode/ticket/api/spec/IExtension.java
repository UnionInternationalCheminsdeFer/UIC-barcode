/*
 * 
 */
package org.uic.barcode.ticket.api.spec;


/**
 * The Interface IExtension.
 * 
 *	 IExtension defines an extension of the data defined in the UIC standard
 *
 *   If not agreed bilaterally otherwise these extensions must be ignored.
 *
 *  --    the generic non - standard extension contains:
 *  --            - an extension id to distinguish different extensions
 *  --            - the extension data as binary data
 *  -- proprietary extensions are by definition proprietary. This standard provides
 *  --           the means to identify these extensions 
 *  --           within the data and to skip these data.
 *  --           the evaluation of these data and the unique identification of the extensions 
 *  --           via the extension id is in the 
 *  --           responsibility of the railways which use these extensions.
 * 
 */
public interface IExtension {
	
	/**
	 * Gets the id of the extension.
	 * The id identifies the type of the extension.
	 *
	 * @return the id
	 */
	public String getId() ;
	
	/**
	 * Sets the id.
	 * The id identifies the type of the extension. 
	 *
	 * @param id the new id
	 */
	public void setId(String id);
	
	/**
	 * Gets the binary data content.
	 *
	 * @return the binary data content 
	 */
	public byte[] getBinarydata();
	
	/**
	 * Sets the binary data content.
	 *
	 * @param binarydata the new binary data content
	 */
	public void setBinarydata(byte[] binarydata);
	

}
