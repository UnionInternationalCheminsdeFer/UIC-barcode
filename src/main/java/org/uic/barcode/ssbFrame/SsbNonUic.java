package org.uic.barcode.ssbFrame;

import org.uic.barcode.asn1.uper.AsnUtils;
import org.uic.barcode.asn1.uper.BitBuffer;
import org.uic.barcode.asn1.uper.ByteBitBuffer;

public class SsbNonUic extends SsbTicketPart {
	

	
	
	byte[] openData = null;

	@Override
	protected int decodeContent(byte[] bytes, int offset) {
		
		BitBuffer bits = new ByteBitBuffer(bytes);
			
		StringBuffer sb = new StringBuffer();
		
		
		for (int i = offset; i < offset + openDataLength; i++) {
			if (bits.get(i) == true) {
				sb.append("1");
			} else {
				sb.append("0");
			}
		}
		
		for (int i = openDataLength; i < 440; i++) {
			sb.append("0");
		}	
		
		openData = AsnUtils.fromBooleanString(sb.toString());
		
		return offset + openDataLength ;
		
	}

	@Override
	protected int encodeContent(byte[] bytes, int offset) {
		
		BitBuffer bits = new ByteBitBuffer(bytes);
		
		String bitString = AsnUtils.toBooleanString(openData);
		
	
		for (int i = 0; i< openDataLength ; i++) {
			if (i < bitString.length() && bitString.charAt(i) == '1') {
				bits.put(offset + i, true);
			} else {
				bits.put(offset + i, false);
			}
		}
		
		return offset + openDataLength;
	}

	public byte[] getOpenData() {
		return openData;
	}

	public void setOpenData(byte[] openData) {
		this.openData = openData;
	}
	
	
	
}
