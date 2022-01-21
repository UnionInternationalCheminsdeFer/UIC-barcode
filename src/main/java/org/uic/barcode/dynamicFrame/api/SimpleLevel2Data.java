package org.uic.barcode.dynamicFrame.api;

import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.uper.UperEncoder;

/**
 * The Class DataType.
 */
@Sequence
@HasExtensionMarker
public class SimpleLevel2Data implements ILevel2Data {

	@FieldOrder(order = 0)
	ILevel1Data level1Data;
	
	/** The data. */
	@FieldOrder(order = 1)
	@Asn1Optional public byte[] level1Signature;
	
	@FieldOrder(order = 2)
	@Asn1Optional IData level2Data;


	public ILevel1Data getLevel1Data() {
		return level1Data;
	}


	public void setLevel1Data(ILevel1Data level1Data) {
		this.level1Data = level1Data;
	}


	public byte[] getLevel1Signature() {
		return level1Signature;
	}

	public byte[] getLevel1SignatureBytes() {
		return level1Signature;
	}

	
	public void setLevel1Signature(byte[] level1Signature) {
		this.level1Signature = level1Signature;
	}


	public IData getLevel2Data() {
		return level2Data;
	}


	public void setLevel2Data(IData level2Data) {
		this.level2Data = level2Data;
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
