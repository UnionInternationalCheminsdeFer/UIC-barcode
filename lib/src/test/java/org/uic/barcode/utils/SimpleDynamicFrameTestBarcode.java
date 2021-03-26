package org.uic.barcode.utils;

import java.security.KeyPair;

import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.dynamicFrame.DataType;
import org.uic.barcode.dynamicFrame.DynamicFrame;
import org.uic.barcode.dynamicFrame.Level1DataType;
import org.uic.barcode.dynamicFrame.Level2DataType;
import org.uic.barcode.dynamicFrame.SequenceOfDataType;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData;
import org.uic.barcode.ticket.api.testtickets.SimpleUicTestTicket;


public class SimpleDynamicFrameTestBarcode {
	
	public static DynamicFrame getSimpleDynamicHeaderBarcode(String algorithm, KeyPair keyPair) {
		
		
		
		DynamicFrame barcode = null;
		
		
		
		try {
			barcode = new DynamicFrame();
			barcode.setFormat(Constants.DYNAMIC_BARCODE_FORMAT_DEFAULT);
			Level2DataType level2Data = new Level2DataType();
			barcode.setLevel2SignedData(level2Data); 
			
			Level1DataType level1Data = new Level1DataType();
			level2Data.setLevel1Data(level1Data); 
			
			level1Data.setSecurityProvider("1080");
			level1Data.setKeyId(1L);
			
			level1Data.setLevel1SigningAlg(Constants.ECDSA_SHA256);
			
			DataType data = new DataType();
			UicRailTicketData ticket = SimpleUicTestTicket.getUicTestTicket();
			byte[] ticketData = ticket.encode();
			data.setByteData(ticketData);
			data.setFormat(Constants.DATA_TYPE_FCB_VERSION_1);
			SequenceOfDataType dataSequence = new SequenceOfDataType();
			level1Data.setData(dataSequence);
			level1Data.getData().add(data);

			try {
				level2Data.signLevel1(keyPair.getPrivate());
			} catch (Exception e) {
				assert(false);
			}
		
			
		} catch (EncodingFormatException e) {
			e.printStackTrace();
			return null;
		}

		return barcode;
	}

	
	public static void compareFrame(DynamicFrame frame1, DynamicFrame frame2) {
		
		assert(frame1.getLevel2SignedData().getLevel1Data().getKeyId() == frame2.getLevel2SignedData().getLevel1Data().getKeyId());
		
		assert(frame1.getLevel2SignedData().getLevel1Data().getLevel1SigningAlg().equals(frame2.getLevel2SignedData().getLevel1Data().level1SigningAlg));
	
		assert(frame1.getLevel2SignedData().getLevel1Data().getSecurityProvider().equals(frame2.getLevel2SignedData().getLevel1Data().getSecurityProvider()));

		DataType data1 = frame1.getLevel2SignedData().getLevel1Data().getData().get(0);
		DataType data2 = frame2.getLevel2SignedData().getLevel1Data().getData().get(0);		
		
		assert(data1.getFormat().equals(data2.getFormat()));		
		
	}

}
