package org.uic.barcode.dynamicFrame.api;

import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import org.uic.barcode.dynamicContent.api.IUicDynamicContent;
import org.uic.barcode.dynamicContent.fdc1.UicDynamicContentDataFDC1;
import org.uic.barcode.ticket.EncodingFormatException;



/**
 * The DynamicHeader for bar codes .
 */
public interface IDynamicFrame{
	

	
	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public String getFormat();
	

	/**
	 * Sets the format.
	 *
	 * @param format the new format
	 */
	public void setFormat(String format);

	/**
	 * Gets the level 2 signed data.
	 *
	 * @return the level 2 signed data
	 */
	public ILevel2Data getLevel2Data();

	/**
	 * Sets the level 2 signed data.
	 *
	 * @param level2Data the new level 2 data
	 */
	public void setLevel2Data(ILevel2Data level2Data);


	/**
	 * Gets the level 2 signature.
	 *
	 * @return the level 2 signature
	 */
	public byte[] getLevel2Signature();
	

	/**
	 * Sets the level 2 signature.
	 *
	 * @param level2Signature the new level 2 signature
	 */
	public void setLevel2Signature(byte[] level2Signature);
	
	
	/**
	 * Verify the level 2 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 * @return the return error code
	 * @throws EncodingFormatException the encoding format exception
	 */	
	public int validateLevel2() throws EncodingFormatException;
	
	/**
	 * Verify the level 2 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param prov the registered security provider
	 * @return the return error code
	 * @throws EncodingFormatException the encoding format exception
	 */	
	public int validateLevel2(Provider prov) throws EncodingFormatException;

	/**
	 * Verify the level 1 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @param data the data content
	 * @return the return error code
	 * @throws EncodingFormatException the encoding format exception
	 */
	public int validateLevel1(PublicKey key) throws EncodingFormatException;
	
	/**
	 * Verify the level 1 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @param signatureAlgorithmOid the signature algorithmOid to be used in case it is not contained in the barcode
	 * @return the return error code
	 * @throws EncodingFormatException the encoding format exception
	 */
	public int validateLevel1(PublicKey key,String signatureAlgorithmOid) throws EncodingFormatException;

	
	/**
	 * Verify the level 1 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @param prov the registered security provider
	 * @return the return error code
	 * @throws EncodingFormatException the encoding format exception
	 */
	public int validateLevel1(PublicKey key, Provider prov) throws EncodingFormatException;
 
	/**
	 * Verify the level 1 signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @param prov the registered security provider
	 * @return the return error code
	 * @throws EncodingFormatException the encoding format exception
	 */
	public int validateLevel1(PublicKey key, Provider prov, String signatureAlgorithmOid) throws EncodingFormatException;
	
	/**
	 * Sign level 2 data without a specific security provider.
	 *
	 * @param key the key
	 * @throws Exception the exception
	 */
	public void signLevel2(PrivateKey key) throws Exception;


	/**
	 * Sign level 2 data.
	 *
	 * @param key the key
	 * @param prov the security Provider
	 * @throws Exception the exception
	 */
	public void signLevel2(PrivateKey key, Provider prov) throws Exception;
	
	
	/**
	 * Adds the dynamic content and encodes it. (API level)
	 *
	 * @param content the dynamic content
	 * @throws EncodingFormatException the encoding format exception
	 */
	public void addDynamicContent(IUicDynamicContent content) throws EncodingFormatException;
	
	
	/**
	 * Adds the level 2 dynamic data. (ASN level)
	 *
	 * @param dynamicData the dynamic data
	 */
	public void addLevel2DynamicData(UicDynamicContentDataFDC1 dynamicData);
	
	/**
	 * Gets the dynamic content.
	 *
	 * @return the dynamic content
	 */
	public IUicDynamicContent getDynamicContent();
	
	
	/**
	 * Sign the contained data block.
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the private key
	 * @return the signature
	 * @throws Exception the exception
	 */
	public void signLevel1(PrivateKey key) throws Exception;
	
	/**
	 * Sign the contained data block.
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the private key
	 * @param prov the security provider providing the signature implementation
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	public void signLevel1(PrivateKey key, Provider prov) throws Exception;


	/**
	 * Gets the signature of the level 1 data.
	 *
	 * @return the level 1 signature
	 */
	public byte[] getLevel1Signature();

	/**
	 * Gets the level 1 data in binary as they are signed by the level 1 signature.
	 *
	 * @return the level 1 data binary
	 * @throws EncodingFormatException the encoding format exception
	 */
	public byte[] getLevel1DataBin() throws EncodingFormatException;
	
	/**
	 * Gets the level 2 data in binary as they are signed by the level 1 signature.
	 *
	 * @return the level 2 data binary
	 * @throws EncodingFormatException the encoding format exception
	 */
	public byte[] getLevel2DataBin() throws EncodingFormatException;
	
	
}
