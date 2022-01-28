package org.uic.barcode.dynamicFrame.api;

import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.FieldOrder;

/**
 * The Class DataType.
 */
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
		
	
}
