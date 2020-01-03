/*
 * 
 */
package org.uic.ticket.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.uic.ticket.api.asn.omv1.UicRailTicketData;
import org.uic.ticket.api.spec.IUicRailTicket;
import org.uic.ticket.api.utils.Api2OpenAsnEncoder;
import org.uic.ticket.api.utils.OpenAsn2ApiDecoder;


/**
 * The Class UicRailTicketCoder.
 */
public class UicRailTicketCoder {


	/**
	 * encode an UicRailTicket to asn.1 format using PER unaligned encoding 
	 *
	 * @param outputStream the output stream for the encoded data
	 * @param uicRailTicket the uic rail ticket to be encoded
	 * @param version version of the asn1 specification
	 * @throws IOException signals that an I/O exception has occurred.
	 * @throws EncodingFormatException signals that a format rule of the asn.1 specification was violated. 
	 */
	public byte[] encodeTag(IUicRailTicket uicRailTicket, int version) throws IOException, EncodingFormatException {
		
				
	    byte[] content = encode(uicRailTicket, version);
			
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		String idTag = "U_FLEX"; 
		String versionId = "01";
		int length = 8;
			
		//size of data
		length = length + content.length;
		
		//size of length element
		length = length + 4;  		
		String lengthElement = String.format("%04d",length);		
		
		outputStream.write(idTag.getBytes());
		outputStream.write(versionId.getBytes());
		outputStream.write(lengthElement.getBytes());	
			
		outputStream.write(content);					
					
		return outputStream.toByteArray();
	
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
	public byte[] encode (IUicRailTicket uicRailTicket, int version) throws IOException, EncodingFormatException{
			
		if (version != 1) {
			throw new EncodingFormatException(String.format("Encoding version %d not supported", version));
		}
		
		Api2OpenAsnEncoder uicEncoder = new Api2OpenAsnEncoder(); 		
		
		UicRailTicketData asnUicRailTicketData = uicEncoder.populateToAsn1Model(uicRailTicket);
		
		return asnUicRailTicketData.encode();

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
			
		if (version != 1) {
			throw new EncodingFormatException(String.format("Encoding version %d not supported", version));
		}
		
		Api2OpenAsnEncoder uicEncoder = new Api2OpenAsnEncoder(); 		
		
		UicRailTicketData asnUicRailTicketData = uicEncoder.populateToAsn1Model(uicRailTicket);
		
		outputStream.write(asnUicRailTicketData.encode());
		
		return;

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
		
		if (version != 1) {
			throw new EncodingFormatException(String.format("Encoding version %d not supported", version));
		}
		
		UicRailTicketData asnUicRailTicketData = UicRailTicketData.decode(byteData);
			
		OpenAsn2ApiDecoder uicDecoder = new OpenAsn2ApiDecoder();
			
		IUicRailTicket uicRailTicket = uicDecoder.decodeFromAsn(asnUicRailTicketData);
		
		return uicRailTicket;				

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
		
		if (version != 1) {
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
