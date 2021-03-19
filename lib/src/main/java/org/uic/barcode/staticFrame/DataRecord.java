package org.uic.barcode.staticFrame;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.uic.barcode.ticket.EncodingFormatException;

/**
 * The Class DataRecord implements the basic decoding and encoding 
 * of the data record structure, the split into tag, version, length and content.
 * 
 * Implementing classes must provide decoding and encoding of the content
 * 
 */
public abstract class DataRecord {
	
	/** The id tag. */
	protected String idTag; 
	
	/** The version id. */
	protected String versionId;
	
	/** The content. */
	protected byte[] content;

	/**
	 * Instantiates a new data record.
	 *
	 * @param idTag the id tag
	 * @param version the version
	 */
	public DataRecord (String idTag, String version) {
		this.idTag = idTag;
		this.versionId = version;
	}
	
	/**
	 * Instantiates a new data record.
	 *
	 * @param idTag the id tag
	 */
	public DataRecord (String idTag) {
		this.idTag = idTag;
	}
	
	/**
	 * Encode.
	 *
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	public byte[] encode() throws IOException, EncodingFormatException {
			
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		encodeContent();
		
		//size of tag + version + length
		int length = 12;
			
		//size of data
		length = length + content.length;
			
		String lengthElement = String.format("%04d",length);		
		
		while (idTag.length() < 6) {
			idTag = idTag + " ";
		}
		
		while (versionId.length() < 2) {
			versionId = "0" + versionId;
		}

		outputStream.write(idTag.getBytes());
		
		outputStream.write(versionId.getBytes());
		
		outputStream.write(lengthElement.getBytes());	
		
		outputStream.write(content);				
									
		return outputStream.toByteArray();
	}
	
	/**
	 * Decode.
	 *
	 * @param byteData the byte data
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	public int decode(byte[] byteData) throws IOException, EncodingFormatException {
		
	    int offset = 0;
		String tag = new String(Arrays.copyOfRange(byteData, offset,  offset + 6));
		this.setIdTag(tag);
		offset = offset + 6;
		
		String version =  new String(Arrays.copyOfRange(byteData, offset,  offset + 2));
		this.setVersionId(version);
		offset = offset + 2;				
		
		String dataSize =  new String(Arrays.copyOfRange(byteData, offset,  offset + 4));
		offset = offset + 4;			
		
		int length = Integer.parseInt(dataSize) - 12;
		this.setData(Arrays.copyOfRange(byteData, offset,  offset + length));
		
		decodeContent();
		
		return length + 12;
	}


	/**
	 * Gets the id tag.
	 *
	 * @return the id tag
	 */
	public String getIdTag() {
		return idTag;
	}


	/**
	 * Sets the id tag.
	 *
	 * @param idTag the new id tag
	 */
	public void setIdTag(String idTag) {
		this.idTag = idTag;
	}


	/**
	 * Gets the version id.
	 *
	 * @return the version id
	 */
	public String getVersionId() {
		return versionId;
	}


	/**
	 * Sets the version id.
	 *
	 * @param versionId the new version id
	 */
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}


	/**
	 * Gets the data.
	 *
	 * @return the data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	protected byte[] getData() throws IOException, EncodingFormatException {
		return content;
	}


	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	protected void setData(byte[] data) throws IOException, EncodingFormatException  {
		this.content = data;
	}


	
	/**
	 * Decode content.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	protected abstract void decodeContent() throws IOException, EncodingFormatException;
	
	/**
	 * Encode content.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	protected abstract void encodeContent() throws IOException, EncodingFormatException;
	
}
