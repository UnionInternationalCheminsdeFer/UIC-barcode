package org.uic.barcode.ticketTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.impl.SimpleIssuingDetail;
import org.uic.barcode.ticket.api.impl.SimpleControlDetail;
import org.uic.barcode.ticket.api.impl.SimpleUicRailTicket;
import org.uic.barcode.ticket.api.spec.IIssuingDetail;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.ticket.api.spec.IControlDetail;
import org.uic.barcode.ticket.api.utils.Api2AsnEncoder;
import org.uic.barcode.ticket.api.utils.Api2OpenAsnEncoderV3;
import org.uic.barcode.ticket.api.utils.Asn2ApiDecoder;
import org.uic.barcode.ticket.api.utils.OpenAsn2ApiDecoderV3;

import java.io.IOException;
import java.util.Date;

public class EmptyTravelerDetailTest {
	
		@Before
		public void setup() {
			LoggerFactory.setActivateConsoleLog(true);
		}

		@Test
		public void shouldNotEncodeControlDetailsAndTravelerDetails() throws EncodingFormatException, IOException {
			// Given
			IUicRailTicket ticket = new SimpleUicRailTicket();
			IIssuingDetail issuingDetail = new SimpleIssuingDetail();
			issuingDetail.setIssuingDate(new Date());
			issuingDetail.setSpecimen(false);
			issuingDetail.setSecurePaperTicket(true);
			issuingDetail.setActivated(true);
			ticket.setIssuerDetails(issuingDetail);
			Api2AsnEncoder uicEncoder = new Api2OpenAsnEncoderV3();
			Asn2ApiDecoder uicDecoder = new OpenAsn2ApiDecoderV3();
			// When
			byte[] encoded = uicEncoder.encode(ticket);
			System.out.println(UperEncoder.hexStringFromBytes(encoded));
			System.out.println(UperEncoder.binaryStringFromBytes(encoded)); // 01010 00000000000000 0000101000001010101011001001011 0000000000000 000000000
			IUicRailTicket decoded = uicDecoder.decodeFromAsn(encoded);
			// Then
			Assert.assertNotNull("Decoded ticket should not be null.", decoded);
			Assert.assertNotNull("Issuer details should not be null.", decoded.getIssuerDetails());
			Assert.assertNull("Control details should be null as it is optional and was not given.", decoded.getControlDetailsOrNull());
			Assert.assertNull("Traveler details should be null as it is optional and was not given.", decoded.getTravelerDetailsOrNull());

		}
		
		@Test
		public void shouldEncodeControlDetailsWithFalseValues() throws EncodingFormatException, IOException {
		    // Given
		    IUicRailTicket ticket = new SimpleUicRailTicket();
		    IIssuingDetail issuingDetail = new SimpleIssuingDetail();
		    issuingDetail.setIssuingDate(new Date());
		    issuingDetail.setSpecimen(false);
		    issuingDetail.setSecurePaperTicket(true);
		    issuingDetail.setActivated(true);
		    ticket.setIssuerDetails(issuingDetail);
		    IControlDetail controlDetail = new SimpleControlDetail();
		    controlDetail.setAgeCheckRequired(false);
		    ticket.setControlDetails(controlDetail);
		    Assert.assertSame("Control details should be set.", controlDetail, ticket.getControlDetails());
		    Api2AsnEncoder uicEncoder = new Api2OpenAsnEncoderV3();
		    Asn2ApiDecoder uicDecoder = new OpenAsn2ApiDecoderV3();
		    // When
		    byte[] encoded = uicEncoder.encode(ticket);
		    IUicRailTicket decoded = uicDecoder.decodeFromAsn(encoded);
		    // Then
		    Assert.assertTrue("Encoded preamble bits should all be set to false except controlDetail flag", UperEncoder.binaryStringFromBytes(encoded).startsWith("00010"));
		    Assert.assertNotNull("Decoded ticket should not be null.", decoded);
		    Assert.assertNotNull("Control details should not be null.", decoded.getControlDetailsOrNull());
		    Assert.assertFalse("Age check required should be false.", decoded.getControlDetails().isAgeCheckRequired());
		}


}