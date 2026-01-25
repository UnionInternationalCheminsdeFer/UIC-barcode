package org.uic.barcode.ticket.api.test;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.text.ParseException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;
import org.uic.barcode.Decoder;
import org.uic.barcode.Encoder;
import org.uic.barcode.ticket.api.impl.SimpleControlDetail;
import org.uic.barcode.ticket.api.impl.SimpleIssuingDetail;
import org.uic.barcode.ticket.api.impl.SimplePass;
import org.uic.barcode.ticket.api.impl.SimpleUicRailTicket;
import org.uic.barcode.ticket.api.spec.IPass;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;

public class PassProductIdTest {

	private IUicRailTicket getIUicRailTicket() {
		IUicRailTicket uicRailTicket = new SimpleUicRailTicket();
		uicRailTicket.setControlDetails(new SimpleControlDetail());
		uicRailTicket.setIssuerDetails(new SimpleIssuingDetail());
		IPass pass = new SimplePass();
		pass.setReference("423423");
		pass.setProductId("123123");
		uicRailTicket.addPass(pass);
		return uicRailTicket;
	}

	@Test
	public void testEncodingWithFcbVersion1() throws IllegalArgumentException, IllegalAccessException, ParseException {

		encodeAndDecodePass(1);
	}

	@Test
	public void testEncodingWithFcbVersion2() throws IllegalArgumentException, IllegalAccessException, ParseException {

		encodeAndDecodePass(2);
	}

	private void encodeAndDecodePass(int fcbVersion)
			throws IllegalArgumentException, IllegalAccessException, ParseException {
		byte[] content = null;
		try {

			IUicRailTicket ticket = getIUicRailTicket();
			KeyPair keyPair = generateDSAKeys(1024);
			Encoder enc = new Encoder(ticket, null, "UIC_DOSIPAS", 1, fcbVersion);
			enc.signLevel1("9902", keyPair.getPrivate(), "1.2.840.10040.4.3", "00003", new BouncyCastleProvider());
			content = enc.encode();

		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}

		// decode ticket again
		IUicRailTicket ticket = null;
		try {
			Decoder decoder = new Decoder(content);
			ticket = decoder.getUicTicket();
		} catch (Exception e) {
			assert (false);
		}
		assert (ticket != null);
	}

	private KeyPair generateDSAKeys(int keySize)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
		KeyPairGenerator g = KeyPairGenerator.getInstance("DSA", new BouncyCastleProvider());
		g.initialize(keySize, new SecureRandom());
		return g.generateKeyPair();
	}

}
