package org.uic.header;


import net.gcdc.asn1.datatypes.CharacterRestriction;
import net.gcdc.asn1.datatypes.RestrictedString;
import net.gcdc.asn1.datatypes.Sequence;
import net.gcdc.asn1.datatypesimpl.OctetString;


// TODO: Auto-generated Javadoc
/**
 * The Class Extension.
 */
@Sequence
public class Extension extends Object {
	
	/**
	 * Instantiates a new extension.
	 */
	public Extension() {}

	/** The extension id. */
	@RestrictedString(CharacterRestriction.IA5String)
	public String extensionId;

	/** The extension data. */
	public OctetString extensionData;

	/**
	 * Gets the extension id.
	 *
	 * @return the extension id
	 */
	public String getExtensionId() {

		return this.extensionId;
	}

	/**
	 * Gets the extension data.
	 *
	 * @return the extension data
	 */
	public byte[] getExtensionData() {
		
		return extensionData.toByteArray();
	}

	/**
	 * Sets the extension id.
	 *
	 * @param extensionId the new extension id
	 */
	public void setExtensionId(String extensionId) {

		this.extensionId = extensionId;
	}

	/**
	 * Sets the extension data.
	 *
	 * @param extensionData the new extension data
	 */
	public void setExtensionData(byte[] extensionData) {
		
		this.extensionData = new OctetString(extensionData);

	}



}
