package org.uic.barcode.dynamicFrame.v2;

import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.asn1.uper.UperEncoder;


/**
 * The DynamicHeader for bar codes 
 * 
 * Implementation of the Draft under discussion, not final.
 */
@Sequence
public class DynamicFrame extends Object{
	
	/**
	 * Instantiates a new dynamic frame.
	 */
	public DynamicFrame() {}

	/** The format. */
	@FieldOrder(order = 0)
	@RestrictedString(CharacterRestriction.IA5String)
	public String format;
	
	/** The level 2 signed data. */
	/*level 2 data*/
	@FieldOrder(order = 1)
	Level2DataType level2SignedData;
	
	
	/**  The signature of level 2 data. */
	@FieldOrder(order = 2)
	@Asn1Optional public OctetString level2Signature;
	
	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the format.
	 *
	 * @param format the new format
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
	/**
	 * Gets the level 2 signed data.
	 *
	 * @return the level 2 signed data
	 */
	public Level2DataType getLevel2SignedData() {
		return level2SignedData;
	}

	/**
	 * Sets the level 2 signed data.
	 *
	 * @param level2SignedData the new level 2 signed data
	 */
	public void setLevel2SignedData(Level2DataType level2SignedData) {
		this.level2SignedData = level2SignedData;
	}

	/**
	 * Gets the level 2 signature.
	 *
	 * @return the level 2 signature
	 */
	public OctetString getLevel2Signature() {
		return level2Signature;
	}

	/**
	 * Sets the level 2 signature.
	 *
	 * @param level2Signature the new level 2 signature
	 */
	public void setLevel2Signature(OctetString level2Signature) {
		this.level2Signature = level2Signature;
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
	
	/**
	 * Decode.
	 *
	 * Decode the header from an ASN.1 PER UNALIGNED encoded byte array
	 *
	 * @param bytes the bytes
	 * @return the dynamic header
	 */
	public static DynamicFrame decode(byte[] bytes) {
		return UperEncoder.decode(bytes, DynamicFrame.class);	
	}
	

}
