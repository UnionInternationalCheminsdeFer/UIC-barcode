package org.uic.barcode.dynamicFrame;

import java.security.PrivateKey;
import java.security.Signature;

import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.utils.AlgorithmNameResolver;

/**
 * The Class DataType.
 */
@Sequence
public class Level2DataType {

	@FieldOrder(order = 0)
	Level1DataType level1Data;
	
	/** The data. */
	@FieldOrder(order = 1)
	@Asn1Optional public OctetString level1Signature;
	
	@FieldOrder(order = 2)
	@Asn1Optional DataType level2Data;


	public Level1DataType getLevel1Data() {
		return level1Data;
	}


	public void setLevel1Data(Level1DataType level1Data) {
		this.level1Data = level1Data;
	}


	public OctetString getLevel1Signature() {
		return level1Signature;
	}

	public byte[] getLevel1SignatureBytes() {
		return level1Signature.toByteArray();
	}

	public void setLevel1Signature(OctetString level1Signature) {
		this.level1Signature = level1Signature;
	}
	
	public void setLevel1Signature(byte[] level1Signature) {
		this.level1Signature = new OctetString(level1Signature);
	}


	public DataType getLevel2Data() {
		return level2Data;
	}


	public void setLevel2Data(DataType level2Data) {
		this.level2Data = level2Data;
	}

	
	/**
	 * Encode.
	 * 
	 * Encode the header as ASN.1 PER UNALIGNED byte array
	 *
	 * @return the byte[]
	 */
	public byte[] encode() {
		return UperEncoder.encode(this);
	}
	
	/**
	 * Sign the contained data block.
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @return 
	 * @return the byte[]
	 * @throws Exception 
	 */
	public void signLevel1(PrivateKey key) throws Exception {
		//find the algorithm name for the signature OID
		String algo = AlgorithmNameResolver.getSignatureAlgorithmName(getLevel1Data().level1SigningAlg);
		Signature sig = Signature.getInstance(algo);
		sig.initSign(key);
		byte[] data = level1Data.encode();
		sig.update(data);
		this.level1Signature = new OctetString(sig.sign());
	}

	
	
}
