package org.uic.barcode;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.zip.DataFormatException;

import org.uic.barcode.dynamicContent.api.IUicDynamicContent;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.dynamicFrame.api.DynamicFrameCoder;
import org.uic.barcode.dynamicFrame.api.IData;
import org.uic.barcode.dynamicFrame.api.IDynamicFrame;
import org.uic.barcode.dynamicFrame.api.ILevel1Data;
import org.uic.barcode.dynamicFrame.api.ILevel2Data;
import org.uic.barcode.staticFrame.StaticFrame;
import org.uic.barcode.staticFrame.UFLEXDataRecord;
import org.uic.barcode.staticFrame.UTLAYDataRecord;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.UicRailTicketCoder;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;


/**
 * The Class Decoder.
 * 
 * signature validation and decoding of UIC bar codes
 * 
 */
public class Decoder {
	
	
	/** The dynamic frame. */
	private IDynamicFrame dynamicFrame = null;
	
	/** The static frame. */
	private StaticFrame staticFrame = null;
	
	/** The uic ticket coder. */
	private UicRailTicketCoder uicTicketCoder = null;
	
	/** The uic ticket. */
	private IUicRailTicket uicTicket = null;
	
	/** The layout. */
	private TicketLayout layout = null;
	
	/** The data. */
	byte[] data = null;
	
	
	/**
	 * Instantiates a new decoder.
	 *
	 * @param data the data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 * @throws DataFormatException the data format exception
	 */
	public Decoder (byte[] data) throws IOException, EncodingFormatException, DataFormatException {
		this.data = data;
		decode(data);
	}
	
	/**
	 * Validate level 1.
	 *
	 * @param key the public key
	 * @param signingAlg the signing algorithm OID
	 * @return the return code indicating errors
	 * @throws InvalidKeyException the invalid key exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws SignatureException the signature exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws UnsupportedOperationException the unsupported operation exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	public int validateLevel1(PublicKey key, String signingAlg) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, IllegalArgumentException, UnsupportedOperationException, IOException, EncodingFormatException {
		if (dynamicFrame != null) {
			return dynamicFrame.validateLevel1(key, data) ;
		} else {
			if (staticFrame.verifyByAlgorithmOid(key,signingAlg)) {
				return Constants.LEVEL1_VALIDATION_OK;
			} else {
				return Constants.LEVEL1_VALIDATION_FRAUD;
			}
		}
	}
	
	/**
	 * Validate level 1.
	 *
	 * @param key the public key
	 * @param signingAlg the signing algorithm OID
	 * @param security provider in case a dedicated provider must be used (otherwise null)
	 * @return the return code indicating errors
	 * @throws InvalidKeyException the invalid key exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws SignatureException the signature exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws UnsupportedOperationException the unsupported operation exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	public int validateLevel1(PublicKey key, String signingAlg, Provider provider) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, IllegalArgumentException, UnsupportedOperationException, IOException, EncodingFormatException {
		if (!isStaticHeader(data)) {
			return dynamicFrame.validateLevel1(key, provider, data) ;
		} else {
			if (staticFrame.verifyByAlgorithmOid(key,signingAlg, provider)) {
				return Constants.LEVEL1_VALIDATION_OK;
			} else {
				return Constants.LEVEL1_VALIDATION_FRAUD;
			}
		}
	}
	
	/**
	 * Validate level 2.
	 *
	 * @return the return code indicating errors
	 * @throws EncodingFormatException 
	 */
	public int validateLevel2() throws EncodingFormatException {
		if (!isStaticHeader(data)) {
			return dynamicFrame.validateLevel2(null, data) ;
		} else {
			return Constants.LEVEL2_VALIDATION_NO_SIGNATURE;
		}
	}

	/*
	 * Validate level 2.
	 * @param prov - provider of the java security implementation in case a dedicated provider must be used
	 * @return the return code indicating errors
	 */
	public int validateLevel2(Provider prov) throws EncodingFormatException {
		if (!isStaticHeader(data)) {
			return dynamicFrame.validateLevel2(prov,data) ;
		} else {
			return Constants.LEVEL2_VALIDATION_NO_SIGNATURE;
		}
	}

	
	/**
	 * Decode.
	 *
	 * @param data the byte array raw data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 * @throws DataFormatException the data format exception
	 */
	public void decode(byte[] data) throws IOException, EncodingFormatException, DataFormatException {
		
		if (!isStaticHeader(data)) {
			
			dynamicFrame = DynamicFrameCoder.decode(data);
			
			ILevel2Data level2 = dynamicFrame.getLevel2Data();
			
			ILevel1Data level1 = level2.getLevel1Data();
			
			
			for (IData level1Content : level1.getData()) {
				
				uicTicketCoder = new UicRailTicketCoder();
				if (level1Content.getFormat().equals("FCB1")) {
					uicTicket = uicTicketCoder.decodeFromAsn(level1Content.getData(), 1);
				} else if (level1Content.getFormat().equals("FCB2")) {
					uicTicket = uicTicketCoder.decodeFromAsn(level1Content.getData(), 2);
				} else if (level1Content.getFormat().equals("FCB3")) {
					uicTicket = uicTicketCoder.decodeFromAsn(level1Content.getData(), 3);
				}
			}
			
		} else if (isStaticHeader(data)){
			
			staticFrame = new StaticFrame();
			
			staticFrame.decode(data);
			
			UFLEXDataRecord flex = staticFrame.getuFlex();
			
			if (flex != null) {
				uicTicket = flex.getTicket();
			}

			UTLAYDataRecord tlay = staticFrame.getuTlay();
			
			if (tlay != null) {
				layout = tlay.getLayout();
			}
		}
	}


	/**
	 * Checks if is static header.
	 *
	 * @param data the data
	 * @return true, if is static header
	 */
	private boolean isStaticHeader(byte[] data) {
		byte[] start = "#UT".getBytes();
		if (start[0] != data[0] || start[1]!= start[1] || start[2] != data[2]) return false;
		return true;
	}

	/**
	 * Gets the uic ticket.
	 *
	 * @return the uic ticket
	 */
	public IUicRailTicket getUicTicket() {
		return uicTicket;
	}

	/**
	 * Gets the layout.
	 *
	 * @return the layout
	 */
	public TicketLayout getLayout() {
		return layout;
	}
	
	/**
	 * Gets the dynamic header.
	 *
	 * @return the dynamic header
	 */
	public IDynamicFrame getDynamicFrame() {
		return dynamicFrame;
	}

	/**
	 * Gets the dynamic content.
	 *
	 * @return the dynamic header
	 */
	public IUicDynamicContent getDynamicContent() {
		if (dynamicFrame == null) return null;
		
		return dynamicFrame.getDynamicContent();
	}
	
	/**
	 * Sets the dynamic header.
	 *
	 * @param dynamicHeader the new dynamic header
	 */
	public void setDynamicHeader(IDynamicFrame dynamicHeader) {
		this.dynamicFrame = dynamicHeader;
	}

	/**
	 * Gets the static frame.
	 *
	 * @return the static frame
	 */
	public StaticFrame getStaticFrame() {
		return staticFrame;
	}

	/**
	 * Sets the static frame.
	 *
	 * @param staticFrame the new static frame
	 */
	public void setStaticFrame(StaticFrame staticFrame) {
		this.staticFrame = staticFrame;
	}

	public IData getLevel2Data() {
		if (!isStaticHeader(data) && dynamicFrame.getLevel2Data() != null) {
			return dynamicFrame.getLevel2Data().getLevel2Data();
		}
		return null;
	}
	
	public byte[] getEncodedLevel1Data() throws IOException, EncodingFormatException {
		if (!isStaticHeader(data)) {
			return dynamicFrame.getLevel1DataBin();
		} else if (staticFrame != null) {
			return staticFrame.getDataForSignature();
		} else {
			throw new EncodingFormatException("Unknown Header");
		}		
	}
	
	public byte[] getLevel1Signature() throws IOException, EncodingFormatException {
		
		if (!isStaticHeader(data)) {
			return dynamicFrame.getLevel2Data().getLevel1Signature();
		} else if (staticFrame != null) {
			return staticFrame.getDataForSignature();
		} else {
			throw new EncodingFormatException("Unknown Header");
		}
	}
	
	public String getLevel1KeyId() throws EncodingFormatException {
		
		if (dynamicFrame != null 
			&& dynamicFrame.getLevel2Data() != null 
			&& dynamicFrame.getLevel2Data().getLevel1Data() != null) {
			return dynamicFrame.getLevel2Data().getLevel1Data().getKeyId().toString();
		} else if (staticFrame != null) {
			return staticFrame.getSignatureKey();
		} else {
			throw new EncodingFormatException("Unknown Header");
		}
		
	}
	
}
