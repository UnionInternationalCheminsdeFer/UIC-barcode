package org.uic.barcode.ssbFrame;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.Provider.Service;
import java.util.Arrays;

import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.utils.AlgorithmNameResolver;
import org.uic.barcode.utils.SecurityUtils;

public class SsbFrame {
	
	private SsbHeader header = new SsbHeader();
	
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
		
		if (header == null) {
			header = new SsbHeader();
		}
		
		int offset = 0;
		
		offset = offset + header.decodeContent(bytes,0);
		
		if (header.getTicketType().equals(SsbTicketType.UIC_1_IRT_RES_BOA)) {
			
			reservationData = new SsbReservation();
			offset = offset + reservationData.decodeContent(bytes,offset);	
			
		} else if (header.getTicketType().equals(SsbTicketType.UIC_2_NRT)) {
			
			nonReservationData = new SsbNonReservation();
			offset = offset + nonReservationData.decodeContent(bytes,offset);
			
		} else if (header.getTicketType().equals(SsbTicketType.UIC_3_GRP)) {
			
			groupData = new SsbGroup();
			offset = offset + groupData.decodeContent(bytes, offset);

		} else if (header.getTicketType().equals(SsbTicketType.UIC_4_RPT)) {

			passData = new SsbPass();
			offset = offset + passData.decodeContent(bytes,offset);
			
		} else {
			 
			nonUicData = new SsbNonUic();
			offset = offset + nonUicData.decodeContent(bytes,offset);
			
		}
		
		byte[] signatureBytes = new byte[56];
				
		try {
			//check for non-standard signature encoding
			BigInteger[] bInts = SecurityUtils.decodeSignatureIntegerSequence(signatureBytes);
			SecurityUtils.encodeSignatureIntegerSequence(bInts[0],bInts[1]);
			signaturePart1 = bInts[0].toByteArray();
			signaturePart2 = bInts[1].toByteArray();	
			//decoding the entire signature was ok, so there was no split
		} catch (Exception e) {
			//the signature is correctly implemented, continue with recombination
			signaturePart1 = new byte[28];
			signaturePart2 = new byte[28];	
			
			for (int i = 0 ; i < 28;i++) {
				signaturePart1[i] = bytes[58 + i];
				signaturePart2[i] = bytes[58 + 28 + i];
			}
		}

		
	}
	


	
	public byte[] encode() throws EncodingFormatException {
		
		byte[] bytes = new byte[114];
		
		int offset = header.encodeContent(bytes,0);
		
		
		
		if (nonUicData != null) {
			offset = nonUicData.encodeContent(bytes, offset);
		} else if (nonReservationData != null) {
			offset = nonReservationData.encodeContent(bytes, offset);
		} else if (reservationData != null) {
			offset = reservationData.encodeContent(bytes, offset);
		} else if (groupData != null) {
			offset = groupData.encodeContent(bytes, offset);
		} else if (passData != null) {
			offset = passData.encodeContent(bytes, offset);
		} else {
			throw new EncodingFormatException("Data Content for SSB missing");
		};
		
		
		if (signaturePart1.length > 28) {
			throw new EncodingFormatException("Signature too large");
		}
		if (signaturePart2.length > 28) {
			throw new EncodingFormatException("Signature too large");
		}
		
		for (int i = 1 ; i < 29; i++) {
			int sigInd = signaturePart1.length - i;
			if (sigInd < signaturePart1.length && sigInd >= 0) {
				bytes[58 + 28 - i] = signaturePart1[sigInd];
			} else {
				bytes[58 + 28 - i] = '\0';
			}
			sigInd = signaturePart2.length - i;
			if (sigInd < signaturePart2.length && sigInd >= 0) {
				bytes[58 + 28 + 28 - i] = signaturePart2[sigInd];
			} else {
				bytes[58 + 28 + 28 - i] = '\0';
			}
		}
		
				
		return bytes;
		
	}
	
	public byte[] getDataForSignature() throws EncodingFormatException {
		
		byte[] bytes = new byte[58];
		
		int offset = header.encodeContent(bytes,0);
		
				
		if (nonUicData != null) {
			offset = nonUicData.encodeContent(bytes, offset);
		} else if (nonReservationData != null) {
			offset = nonReservationData.encodeContent(bytes, offset);
		} else if (reservationData != null) {
			offset = reservationData.encodeContent(bytes, offset);
		} else if (groupData != null) {
			offset = groupData.encodeContent(bytes, offset);
		} else if (passData != null) {
			offset = passData.encodeContent(bytes, offset);
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
		this.nonReservationData = null;
		this.reservationData = null;
		this.groupData = null;
		this.passData = null;		
	}

	public SsbNonReservation getNonReservationData() {
		return nonReservationData;
	}

	public void setNonReservationData(SsbNonReservation nonReservationData) {
		this.nonReservationData = nonReservationData;
		header.setTicketType(SsbTicketType.UIC_2_NRT);
		this.reservationData = null;
		this.nonUicData = null;
		this.groupData = null;
		this.passData = null;		
	}

	public SsbReservation getReservationData() {
		return reservationData;
	}

	public void setReservationData(SsbReservation reservationData) {
		header.setTicketType(SsbTicketType.UIC_1_IRT_RES_BOA);
		this.nonReservationData = null;
		this.nonUicData = null;
		this.groupData = null;
		this.passData = null;
		this.reservationData = reservationData;
	}

	public SsbGroup getGroupData() {
		return groupData;
	}

	public void setGroupData(SsbGroup groupData) {
		this.groupData = groupData;
		header.setTicketType(SsbTicketType.UIC_3_GRP);
		this.nonReservationData = null;
		this.nonUicData = null;
		this.reservationData = null;
		this.passData = null;
		
	}

	public SsbPass getPassData() {
		return passData;
	}

	public void setPassData(SsbPass passData) {
		this.passData = passData;
		header.setTicketType(SsbTicketType.UIC_4_RPT);
		this.nonReservationData = null;
		this.nonUicData = null;
		this.groupData = null;
		this.reservationData = null;		
	}

	public void signLevel1(PrivateKey key, Provider prov, String keyId, String algorithmOid) throws Exception {
		
		
		this.header.setKeyId(Integer.parseInt(keyId));
		
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
		
		
		BigInteger r = new BigInteger(1,signaturePart1);
		BigInteger s = new BigInteger(1,signaturePart2);
		byte[] signature = SecurityUtils.encodeSignatureIntegerSequence(r,s);
		
		String signatureAlgorithmOid = signingAlg;
		
		// guess the signature algorithm based on the signature size
		if ((signingAlg == null || signingAlg.length() < 1) && signature != null) {			
			signatureAlgorithmOid = SecurityUtils.getDsaAlgorithm(signature);
		}	
		
		if (prov != null) {
			Service service = prov.getService("Signature",signatureAlgorithmOid);
			if (service != null) {
				algo = service.getAlgorithm();
			}
		} else {
			Provider[] provs = Security.getProviders();
			for (Provider p : provs) {
			   if (algo == null) {
				   Service service = p.getService("Signature",signatureAlgorithmOid);
				   if (service != null) {
					   algo = service.getAlgorithm();
				   }
			   }
			}
		}
		
		if (algo == null) {
			throw new NoSuchAlgorithmException("No service for algorithm found: " + signingAlg);
		}
		
		Signature sig = null;
		if (prov != null) {
			sig = Signature.getInstance(algo,prov);
		} else {
			sig = Signature.getInstance(algo);
		}
		sig.initVerify(key);
		sig.update(getDataForSignature());
		
		return sig.verify(signature);
	}


}
