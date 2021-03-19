/*
 * 
 */
package org.uic.barcode.ticket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.ticket.api.utils.Api2OpenAsnEncoder;
import org.uic.barcode.ticket.api.utils.Api2OpenAsnEncoderV2;
import org.uic.barcode.ticket.api.utils.OpenAsn2ApiDecoder;
import org.uic.barcode.ticket.api.utils.OpenAsn2ApiDecoderV2;


/**
 * The Class UicRailTicketCoder.
 */
public class UicRailTicketCoder {
	
	/**
	 * encode an UicRailTicket to asn.1 format using PER unaligned encoding 
	 *
	 * @param outputStream the output stream for the encoded data
	 * @param uicRailTicket the uic rail ticket to be encoded
	 * @param version of the asn1 specification
	 * @throws IOException signals that an I/O exception has occurred.
	 * @throws EncodingFormatException signals that a format rule of the asn.1 specification was violated. 
	 */
	public byte[] encode (IUicRailTicket uicRailTicket, int version) throws IOException, EncodingFormatException{

		
		if (version == 13) {
			
			Api2OpenAsnEncoder uicEncoder = new Api2OpenAsnEncoder(); 		
			
			return uicEncoder.encode(uicRailTicket);

			
		} else if (version == 2) {
			
			Api2OpenAsnEncoderV2 uicEncoder = new Api2OpenAsnEncoderV2(); 		
			
			return uicEncoder.encode(uicRailTicket);
			
		}
		
		throw new EncodingFormatException(String.format("Encoding version %d not supported", version));


	}	
	

	/**
	 * encode an UicRailTicket to asn.1 format using PER unaligned encoding 
	 *
	 * @param outputStream the output stream for the encoded data
	 * @param uicRailTicket the uic rail ticket to be encoded
	 * @param version of the asn1 specification
	 * @throws IOException signals that an I/O exception has occurred.
	 * @throws EncodingFormatException signals that a format rule of the asn.1 specification was violated. 
	 */
	public void encode ( ByteArrayOutputStream outputStream, IUicRailTicket uicRailTicket, int version) throws IOException, EncodingFormatException{
			

		
		if (version == 13) {
		
			Api2OpenAsnEncoder uicEncoder = new Api2OpenAsnEncoder(); 		
		
			org.uic.barcode.ticket.api.asn.omv1.UicRailTicketData asnUicRailTicketData = uicEncoder.populateToAsn1Model(uicRailTicket);
		
			outputStream.write(asnUicRailTicketData.encode());
		
			return;

			
		} else if (version == 2) {
			
			Api2OpenAsnEncoderV2 uicEncoder = new Api2OpenAsnEncoderV2(); 		
			
			org.uic.barcode.ticket.api.asn.omv2.UicRailTicketData asnUicRailTicketData = uicEncoder.populateToAsn1Model(uicRailTicket);
		
			outputStream.write(asnUicRailTicketData.encode());
		
			return;
			
		}
		
		throw new EncodingFormatException(String.format("Encoding version %d not supported", version));

	}	
	
	/**
	 * Decode a rail ticket from asn.1 unaligned PER encoded data. 
	 *
	 * @param byteData the asn.1 PER encoded byte data 
	 * @return the decoded uic rail ticket
	 * @throws IOException signals that an I/O exception has occurred.
	 * @throws EncodingFormatException 
	 */
	public IUicRailTicket decodeFromAsn (byte[] byteData, int version) throws IOException, EncodingFormatException{
		
		if (version == 1 || version == 13) {
			
			OpenAsn2ApiDecoder uicDecoder = new OpenAsn2ApiDecoder();
				
			IUicRailTicket uicRailTicket = uicDecoder.decodeFromAsn(byteData);
			
			return uicRailTicket;	
			
		} else if (version == 2) {
			
			OpenAsn2ApiDecoderV2 uicDecoder = new OpenAsn2ApiDecoderV2();
				
			IUicRailTicket uicRailTicket = uicDecoder.decodeFromAsn(byteData);
			
			return uicRailTicket;	
			
		}
		
		throw new EncodingFormatException(String.format("Encoding version %d not supported", version));

	}	
	
	
	/**
	 * Decode a rail ticket from asn.1 unaligned PER encoded data. 
	 * @param version of the asn1 specification
	 * @param input stream of the asn.1 PER encoded data 
	 * @return the decoded uic rail ticket
	 * @throws IOException signals that an I/O exception has occurred.
	 * @throws EncodingFormatException 
	 */
	public IUicRailTicket decodeFromAsn (InputStream input, int version) throws IOException, EncodingFormatException{
		
		if (version != 1 && version != 2 && version != 13) {
			throw new EncodingFormatException(String.format("Encoding version %d not supported", version));
		}
			
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];

		while ((nRead = input.read(data, 0, data.length)) != -1) {
		  buffer.write(data, 0, nRead);
		}

		buffer.flush();

		byte[] byteData = buffer.toByteArray();
		
		return decodeFromAsn (byteData, version);
			

	}		

	


}
