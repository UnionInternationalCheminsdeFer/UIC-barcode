package org.uic.barcode.dynamicFrame.api;

import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.dynamicFrame.v1.DynamicFrameCoderV1;
import org.uic.barcode.dynamicFrame.v2.DynamicFrameCoderV2;
import org.uic.barcode.ticket.EncodingFormatException;

public class DynamicFrameCoder {
	
	/**
	 * Encode.
	 * 
	 * Encode the header as ASN.1 PER UNALIGNED byte array
	 *
	 * @return the byte[]
	 * @throws EncodingFormatException 
	 */
	public static byte[] encode(IDynamicFrame frame) throws EncodingFormatException {
			
		if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1.equals(frame.getFormat())) {
			
			return DynamicFrameCoderV1.encode(frame);
			
		} else if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2.equals(frame.getFormat())) {
			
			return DynamicFrameCoderV2.encode(frame);
			
		}
		
		throw new EncodingFormatException("Frame version not supported for encoding");
	}
	
	
	/**
	 * Decode.
	 *
	 * Decode the header from an ASN.1 PER UNALIGNED encoded byte array
	 *
	 * @param bytes the bytes
	 * @return the dynamic header
	 * @throws EncodingFormatException 
	 */
	public static IDynamicFrame decode(byte[] bytes) throws EncodingFormatException {
		
		IDynamicFrame frame = new SimpleDynamicFrame();
		
		try {
			DynamicFrameCoderV1.decode(frame,bytes);
			
			if (frame.getFormat() != null && frame.getFormat().equals(Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1)) {
				return frame;
			} 		
		} catch (Throwable e) {
		    frame = null;
			// failed, try next 			
		}
		
		frame = new SimpleDynamicFrame();
		try {
			DynamicFrameCoderV2.decode(frame,bytes);
				
			if (frame.getFormat() != null && frame.getFormat().equals(Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2)) {
				return frame;
			} 
		} catch(Throwable e) {
			throw new EncodingFormatException("Dynamic Header Version not supported");
			// failed
		}
		
		throw new EncodingFormatException("Dynamic Header Version not supported");

	}


	public static byte[] encodeLevel1(IDynamicFrame frame) throws EncodingFormatException {
		
		if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1.equals(frame.getFormat())) {
			
			return DynamicFrameCoderV1.encodeLevel1(frame);
			
		} else if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2.equals(frame.getFormat())) {
			
			return DynamicFrameCoderV2.encodeLevel1(frame);
			
		}
		
		throw new EncodingFormatException("Frame version not supported for encoding");
		
	}


	public static byte[] encodeLevel2Data(IDynamicFrame frame) throws EncodingFormatException {
		
		if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1.equals(frame.getFormat())) {
			
			return DynamicFrameCoderV1.encodeLevel2Data(frame.getLevel2Data());
			
		} else if (Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2.equals(frame.getFormat())) {
			
			return DynamicFrameCoderV2.encodeLevel2Data(frame.getLevel2Data());
			
		}
		
		throw new EncodingFormatException("Dynamic Header Version not supported: " + frame.getFormat());
	}

}
