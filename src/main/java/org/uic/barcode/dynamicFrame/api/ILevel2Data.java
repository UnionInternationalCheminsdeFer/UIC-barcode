package org.uic.barcode.dynamicFrame.api;

/**
 * The Level 2 data.
 */

public interface ILevel2Data {



	public ILevel1Data getLevel1Data();

	public void setLevel1Data(ILevel1Data level1Data);

	public byte[] getLevel1Signature();

	public byte[] getLevel1SignatureBytes();
	
	public void setLevel1Signature(byte[] level1Signature);

	public IData getLevel2Data();

	public void setLevel2Data(IData level2Data);

}
