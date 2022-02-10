package org.uic.barcode.test.utils;

import java.security.KeyPair;
import java.security.Signature;

import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.dynamicFrame.v1.DataType;
import org.uic.barcode.dynamicFrame.v1.DynamicFrame;
import org.uic.barcode.dynamicFrame.v1.Level1DataType;
import org.uic.barcode.dynamicFrame.v1.Level2DataType;
import org.uic.barcode.dynamicFrame.v1.SequenceOfDataType;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData;
import org.uic.barcode.ticket.api.test.testtickets.SimpleUicTestTicket;
import org.uic.barcode.utils.AlgorithmNameResolver;


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
				String algo = AlgorithmNameResolver.getSignatureAlgorithmName(level1Data.getLevel1SigningAlg());
				Signature sig = Signature.getInstance(algo);
				sig.initSign(keyPair.getPrivate());
				byte[] data2 = UperEncoder.encode(level1Data);
				sig.update(data2);
				level2Data.setLevel1Signature(sig.sign());
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
