package org.uic.barcode.ssbFrame;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.Provider.Service;
import java.util.Arrays;


import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.utils.AlgorithmNameResolver;
import org.uic.barcode.utils.SecurityUtils;

public class SsbFrame {
	
	private SsbHeader header = null;
	
	private byte[] signaturePart1 = null;
	
	private byte[] signaturePart2 = null;
	
	private SsbNonUic nonUicData = null;
	
	private SsbNonReservation nonReservationData = null;
	
	private SsbReservation reservationData = null;
	
	private SsbGroup groupData = null;
	
	private SsbPass passData = null;
	
	public void decode(byte[] bytes) throws EncodingFormatException {
		
		if (bytes.length != 114) {
			throw new EncodingFormatException("Data size does not fit to SSB");
		}
		
		header = new SsbHeader();
		header.decode(bytes);
		
		if (header.getTicketType().equals(SsbTicketType.UIC_1_IRT_RES_BOA)) {
			
			reservationData = new SsbReservation();
			reservationData.decode(bytes);	
			
		} else if (header.getTicketType().equals(SsbTicketType.UIC_2_NRT)) {
			
			nonReservationData = new SsbNonReservation();
			nonReservationData.decode(bytes);
			
		} else if (header.getTicketType().equals(SsbTicketType.UIC_3_GRP)) {
			
			groupData = new SsbGroup();
			groupData.decode(bytes);

		} else if (header.getTicketType().equals(SsbTicketType.UIC_4_RPT)) {

			passData = new SsbPass();
			passData.decode(bytes);
			
		} else {
			 
			nonUicData = new SsbNonUic();
			nonUicData.decode(bytes);
			
		}
				
		signaturePart1 = new byte[28];
		signaturePart2 = new byte[28];		
		
		for (int i = 0 ; i < 28;i++) {
			signaturePart1[i] = bytes[59 + i];
			signaturePart2[i] = bytes[59 + 28 + i];
		}
		
	}
	
	public byte[] encode() throws EncodingFormatException {
		
		byte[] bytes = new byte[114];
		
		header.encode(bytes);
		
		if (nonUicData != null) {
			nonUicData.encode(bytes);
		} else if (nonReservationData != null) {
			nonReservationData.encode(bytes);
		} else if (reservationData != null) {
			reservationData.encode(bytes);
		} else if (groupData != null) {
			groupData.encode(bytes);
		} else if (passData != null) {
			passData.encode(bytes);
		} else {
			throw new EncodingFormatException("Data Content for SSB missing");
		};
		
		for (int i = 0 ; i < 28;i++) {
			bytes[59 + i] = signaturePart1[i];
			bytes[59 + 28 + i] = signaturePart2[i];
		}
		
		return bytes;
		
	}
	
	public byte[] getDataForSignature() throws EncodingFormatException {
		
		byte[] bytes = new byte[58];
		
		header.encode(bytes);
		
		if (nonUicData != null) {
			nonUicData.encode(bytes);
		} else if (nonReservationData != null) {
			nonReservationData.encode(bytes);
		} else if (reservationData != null) {
			reservationData.encode(bytes);
		} else if (groupData != null) {
			groupData.encode(bytes);
		} else if (passData != null) {
			passData.encode(bytes);
		} else {
			throw new EncodingFormatException("Data Content for SSB missing");
		};
				
		return bytes;
		
	}

	public SsbHeader getHeader() {
		return header;
	}

	public void setHeader(SsbHeader header) {
		this.header = header;
	}

	public byte[] getSignaturePart1() {
		return signaturePart1;
	}

	public void setSignaturePart1(byte[] signaturePart1) {
		this.signaturePart1 = signaturePart1;
	}

	public byte[] getSignaturePart2() {
		return signaturePart2;
	}

	public void setSignaturePart2(byte[] signaturePart2) {
		this.signaturePart2 = signaturePart2;
	}

	public SsbNonUic getNonUicData() {
		return nonUicData;
	}

	public void setNonUicData(SsbNonUic nonUicData) {
		this.nonUicData = nonUicData;
	}

	public SsbNonReservation getNonReservationData() {
		return nonReservationData;
	}

	public void setNonReservationData(SsbNonReservation nonReservationData) {
		this.nonReservationData = nonReservationData;
	}

	public SsbReservation getReservationData() {
		return reservationData;
	}

	public void setReservationData(SsbReservation reservationData) {
		this.reservationData = reservationData;
	}

	public SsbGroup getGroupData() {
		return groupData;
	}

	public void setGroupData(SsbGroup groupData) {
		this.groupData = groupData;
	}

	public SsbPass getPassData() {
		return passData;
	}

	public void setPassData(SsbPass passData) {
		this.passData = passData;
	}

	public void signLevel1(PrivateKey key, Provider prov, String algorithmOid) throws Exception {
		
		
		byte[] data = getDataForSignature();
		
		if (prov == null) {
			//check for a provider supporting the key
			prov = SecurityUtils.findPrivateKeyProvider(key);
		}
		
		//find the algorithm name for the signature OID
		String algo = AlgorithmNameResolver.getSignatureAlgorithmName(algorithmOid, prov);
		Signature sig = null;

		if (prov != null) {
			sig = Signature.getInstance(algo, prov);
		} else {
			sig = Signature.getInstance(algo);
		}
		sig.initSign(key);
		
		sig.update(data);
		
		byte[] signature = sig.sign();
		
		BigInteger[] bInts = SecurityUtils.decodeSignatureIntegerSequence(signature);
		
		signaturePart1 = toUnsignedBytes(bInts[0]);
		
		signaturePart2 = toUnsignedBytes(bInts[1]);
		
		
	}
	
	private static byte[] toUnsignedBytes(BigInteger i) {
		byte[] b = i.abs().toByteArray();
		//remove top sign bit  
		if (b[0] == 0) {
			b = Arrays.copyOfRange(b, 1, b.length);
		}
		return b;
	}

	/**
	 * Verify the signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @param singningAlg the Object ID of the signing algorithm
	 * @param a dedicated security provider to validate the signature
	 * @return true, if successful
	 * @throws InvalidKeyException the invalid key exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws SignatureException the signature exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws UnsupportedOperationException the unsupported operating exception
	 * @throws EncodingFormatException 
	 * @throws IOException 
	 */
	public boolean verifyByAlgorithmOid(PublicKey key, String signingAlg, Provider prov) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, IllegalArgumentException, UnsupportedOperationException, IOException, EncodingFormatException {
		//find the algorithm name for the signature OID
		String algo = null;
        Service service = prov.getService("Signature",signingAlg);
	    if (service != null) {
	    	algo = service.getAlgorithm();
	    }
		if (algo == null) {
			throw new NoSuchAlgorithmException("No service for algorithm found: " + signingAlg);
		}
		Signature sig = Signature.getInstance(algo);
		sig.initVerify(key);
		sig.update(getDataForSignature());
		
		BigInteger r = new BigInteger(1,signaturePart1);
		BigInteger s = new BigInteger(1,signaturePart2);
		
		byte[] signature = SecurityUtils.encodeSignatureIntegerSequence(r,s);
		
		return sig.verify(signature);
	}
}