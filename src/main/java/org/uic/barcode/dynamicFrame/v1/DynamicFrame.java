package org.uic.barcode.dynamicFrame.v1;

import java.security.PrivateKey;
import java.security.Provider;
import java.security.Signature;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.CharacterRestriction;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.RestrictedString;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.asn1.datatypesimpl.OctetString;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.dynamicContent.api.DynamicContentCoder;
import org.uic.barcode.dynamicContent.api.IUicDynamicContent;
import org.uic.barcode.dynamicContent.fdc1.UicDynamicContentDataFDC1;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.utils.AlgorithmNameResolver;


/**
 * The DynamicHeader for bar codes 
 * 
 * Implementation of the Draft under discussion, not final.
 */
@Sequence
public class DynamicFrame extends Object{
	
	/**
	 * Instantiates a new dynamic frame.
	 */
	public DynamicFrame() {}

	/** The format. */
	@FieldOrder(order = 0)
	@RestrictedString(CharacterRestriction.IA5String)
	public String format;
	
	/** The level 2 signed data. */
	/*level 2 data*/
	@FieldOrder(order = 1)
	Level2DataType level2SignedData;
	
	
	/**  The signature of level 2 data. */
	@FieldOrder(order = 2)
	@Asn1Optional public OctetString level2Signature;
	
	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the format.
	 *
	 * @param format the new format
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
	/**
	 * Gets the level 2 signed data.
	 *
	 * @return the level 2 signed data
	 */
	public Level2DataType getLevel2SignedData() {
		return level2SignedData;
	}

	/**
	 * Sets the level 2 signed data.
	 *
	 * @param level2SignedData the new level 2 signed data
	 */
	public void setLevel2SignedData(Level2DataType level2SignedData) {
		this.level2SignedData = level2SignedData;
	}

	/**
	 * Gets the level 2 signature.
	 *
	 * @return the level 2 signature
	 */
	public OctetString getLevel2Signature() {
		return level2Signature;
	}

	/**
	 * Sets the level 2 signature.
	 *
	 * @param level2Signature the new level 2 signature
	 */
	public void setLevel2Signature(OctetString level2Signature) {
		this.level2Signature = level2Signature;
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
	 * Decode.
	 *
	 * Decode the header from an ASN.1 PER UNALIGNED encoded byte array
	 *
	 * @param bytes the bytes
	 * @return the dynamic header
	 */
	public static DynamicFrame decode(byte[] bytes) {
		return UperEncoder.decode(bytes, DynamicFrame.class);	
	}
	
	/**
	 * Sign level 2 data without a specific security provider.
	 *
	 * @param key the key
	 * @throws Exception the exception
	 */
	public void signLevel2(PrivateKey key) throws Exception {
		
		//find the algorithm name for the signature OID
		String algo = AlgorithmNameResolver.getSignatureAlgorithmName(this.getLevel2SignedData().getLevel1Data().level2SigningAlg);
		Signature sig = Signature.getInstance(algo);
		sig.initSign(key);
		byte[] data = level2SignedData.encode();
		sig.update(data);
		byte[] signature = sig.sign();
		this.level2Signature = new OctetString(signature);
		
	}

	/**
	 * Sign level 2 data.
	 *
	 * @param key the key
	 * @param prov the security Provider
	 * @throws Exception the exception
	 */
	public void signLevel2(PrivateKey key, Provider prov) throws Exception {
		
		//find the algorithm name for the signature OID
		String algo = AlgorithmNameResolver.getSignatureAlgorithmName(this.getLevel2SignedData().getLevel1Data().level2SigningAlg);
		Signature sig = Signature.getInstance(algo,prov);
		sig.initSign(key);
		byte[] data = level2SignedData.encode();
		sig.update(data);
		this.level2Signature = new OctetString(sig.sign());
		
	}

	
	/**
	 * Adds the dynamic content and encodes it. (API level)
	 *
	 * @param content the dynamic content
	 * @throws EncodingFormatException the encoding format exception
	 */
	public void addDynamicContent(IUicDynamicContent content) throws EncodingFormatException {
		
		
		level2SignedData.setLevel2Data(new DataType());
		
		level2SignedData.getLevel2Data().setFormat(DynamicContentCoder.dynamicContentDataFDC1);
			
		level2SignedData.getLevel2Data().setByteData(DynamicContentCoder.encode(content, DynamicContentCoder.dynamicContentDataFDC1));
		
	}
	
	/**
	 * Adds the level 2 dynamic data. (ASN level)
	 *
	 * @param dynamicData the dynamic data
	 */
	public void addLevel2DynamicData(UicDynamicContentDataFDC1 dynamicData) {	
		DataType dt = new DataType();
		dt.setByteData(dynamicData.getDataType().getByteData());
		dt.setFormat(dynamicData.getDataType().getFormat());	
		level2SignedData.setLevel2Data(dt);	
	}
	
	/**
	 * Gets the dynamic content.
	 *
	 * @return the dynamic content
	 */
	public IUicDynamicContent getDynamicContent() {
		
		if (level2SignedData == null || 
		    level2SignedData.getLevel2Data() == null){
				return null;
		}
		
		return DynamicContentCoder.decode(this.getLevel2SignedData().getLevel2Data().getByteData());
			
	}
	
	/**
	 * Gets the dynamic data FDC 1.
	 *
	 * @return the dynamic data FDC 1
	 */
	public UicDynamicContentDataFDC1 getDynamicDataFDC1() {
		
		if (level2SignedData == null || 
			level2SignedData.getLevel2Data() == null){
			return null;
		}
		
		if ( UicDynamicContentDataFDC1.getFormat().equals(this.getLevel2SignedData().getLevel2Data().getFormat())) {
			return UperEncoder.decode(this.getLevel2SignedData().getLevel2Data().getByteData(), UicDynamicContentDataFDC1.class);		
		}
		return null;
		
	}
	
	
}
