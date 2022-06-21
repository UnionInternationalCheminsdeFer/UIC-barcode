package org.uic.barcode.asn1.uper;

public class AsnExtractor {
	
	private String path = null;
	
	private boolean extractionStarted = false;
	private boolean extractionCompleted = false;
	
	private BitBuffer bitBuffer = null;
	private int startBit = 0;
	private int endBit = 0;
	
	AsnExtractor(String path, BitBuffer bitBuffer) {
		this.path = path;
		this.bitBuffer = bitBuffer;
	}

	public byte[] getResult() {
		
		if (!extractionCompleted) {
			return null;
		}
		
		if (!(endBit > startBit)) {
			return null;
		}
		
		String bitString = bitBuffer.toBooleanString(startBit, endBit - startBit);
		
		while (bitString.length() % 8 != 0) {
			bitString = bitString + "0";
		}
		
		return AsnUtils.fromBooleanString(bitString);
		
	}
	

	
	public boolean found(String className) {
		
		if (extractionStarted || extractionCompleted) return false;
		
		if (path != null && path.length() > 0 &&
				className != null && className.length() > 0 &&
				className.endsWith(path)) {
			return true;
		}
		
		return false;
	}
	
	public void startExtraction(int position) {
		
		if (path == null || path.length() == 0 || bitBuffer == null) {
			return;
		}
		
		if (!extractionCompleted && !extractionStarted) {
			extractionStarted = true;
			startBit = position;
		}
	}
	
	public void endExtraction(int position) {
		if (extractionStarted) {
			extractionCompleted = true;
			endBit = position;
		}

	}

	public boolean isStarted() {
		return extractionStarted;
	}
	
	
	
}
