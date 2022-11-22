package org.uic.barcode.ssbFrame;

import org.uic.barcode.asn1.uper.AsnUtils;
import org.uic.barcode.asn1.uper.BitBuffer;
import org.uic.barcode.asn1.uper.ByteBitBuffer;

public class SsbNonUic extends SsbTicketPart {
	
	byte[] openData = null;

	@Override
	protected void decodeContent(byte[] bytes) {
		
		String bitString = AsnUtils.toBooleanString(bytes);
				
		openData = AsnUtils.fromBooleanString(bitString);
		
	}

	@Override
	protected void encodeContent(byte[] bytes) {
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		String bitString = AsnUtils.toBooleanString(openData);
		
		for (int i = 0;i< 58 *8 ;i++) {
			if (bitString.charAt(i) == '0') {
				bits.put(27 + i, true);
			} else {
				bits.put(27 + i, false);
			}
		}
	}

	public byte[] getOpenData() {
		return openData;
	}

	public void setOpenData(byte[] openData) {
		this.openData = openData;
	}
	
	
	
}
