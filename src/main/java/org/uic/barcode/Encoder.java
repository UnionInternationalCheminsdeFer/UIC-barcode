package org.uic.barcode;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;

import org.uic.barcode.dynamicContent.api.IUicDynamicContent;
import org.uic.barcode.dynamicContent.fdc1.UicDynamicContentDataFDC1;
import org.uic.barcode.dynamicFrame.Constants;
import org.uic.barcode.dynamicFrame.api.IData;
import org.uic.barcode.dynamicFrame.api.IDynamicFrame;
import org.uic.barcode.dynamicFrame.api.SimpleData;
import org.uic.barcode.dynamicFrame.api.SimpleDynamicFrame;
import org.uic.barcode.dynamicFrame.api.SimpleLevel1Data;
import org.uic.barcode.dynamicFrame.api.SimpleLevel2Data;
import org.uic.barcode.staticFrame.StaticFrame;
import org.uic.barcode.staticFrame.UFLEXDataRecord;
import org.uic.barcode.staticFrame.UHEADDataRecord;
import org.uic.barcode.staticFrame.UTLAYDataRecord;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.UicRailTicketCoder;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;


/**
 * The Class Encoder.
 * 
 * signing and encoding of UIC bar codes 
 * 
 * 
 */
public class Encoder {
	
	/** The dynamic frame. */
	private IDynamicFrame dynamicFrame = null;

	/** The static frame. */
	private StaticFrame staticFrame = null;
	

	/** The UIC bar code type classic. */
	public static String UIC_BARCODE_TYPE_CLASSIC = "UIC_CLASSIC";
	
	/** The UIC bar code type DOSIPAS. */
	public static String UIC_BARCODE_TYPE_DOSIPAS = "UIC_DOSIPAS";	
	
	/**
	 * Instantiates a new encoder.
	 *
	 * @param ticket the ticket
	 * @param layout the layout
	 * @param barcodeType the bar code type
	 * @param version the version of the bar code 
	 * @param fcbVersion the fcb version
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	public Encoder(IUicRailTicket ticket, TicketLayout layout, String barcodeType, int version, int fcbVersion) throws IOException, EncodingFormatException {
		
		if (barcodeType == UIC_BARCODE_TYPE_CLASSIC) {

			staticFrame = new StaticFrame();
			staticFrame.setVersion(version);
			
			if (layout != null) {
						
				UHEADDataRecord head = new UHEADDataRecord();
				head.setVersionId("01");
				staticFrame.setHeaderRecord(head);				
				
				UTLAYDataRecord tlay = new UTLAYDataRecord();
				tlay.setLayout(layout);
				tlay.setVersionId("01");
				staticFrame.setuTlay(tlay);
			}		
			
			if (ticket != null) {
				
				UFLEXDataRecord flex = new UFLEXDataRecord();
				flex.setTicket(ticket);
				flex.setVersionId(String.format("%02d",fcbVersion));
				staticFrame.setuFlex(flex);
			}
			
			
		} else if (barcodeType == UIC_BARCODE_TYPE_DOSIPAS) {
			
			dynamicFrame = new SimpleDynamicFrame();
			dynamicFrame.setLevel2Data(new SimpleLevel2Data());
			dynamicFrame.getLevel2Data().setLevel1Data(new SimpleLevel1Data());
			
			if (ticket != null) {
				
				if (version == 1) {
					dynamicFrame.setFormat(Constants.DYNAMIC_BARCODE_FORMAT_VERSION_1);
				} else if (version == 2) {
					dynamicFrame.setFormat(Constants.DYNAMIC_BARCODE_FORMAT_VERSION_2);
				} 
				
				IData ticketData = new SimpleData();
				
				UicRailTicketCoder uicTicketCoder = new UicRailTicketCoder();
				if (fcbVersion == 1 || fcbVersion == 13) {
					ticketData.setFormat(Constants.DATA_TYPE_FCB_VERSION_1);
				} else if (fcbVersion == 2) {
					ticketData.setFormat(Constants.DATA_TYPE_FCB_VERSION_2);
				} else if (fcbVersion == 3) {
					ticketData.setFormat(Constants.DATA_TYPE_FCB_VERSION_3);
				}
				ticketData.setData(uicTicketCoder.encode(ticket, fcbVersion));
				dynamicFrame.getLevel2Data().getLevel1Data().addData(ticketData);
				
			}
		}
	}
	
	
	
	/**
	 * Signing level 2 of a dynamic bar code
	 *     
	 * @param key the key
	 * @throws Exception the exception
	 */
	public void signLevel2(PrivateKey key) throws Exception {
		if (dynamicFrame != null) {
			dynamicFrame.signLevel2(key);
		} 
	}
	
	/**
	 * Signing level 2 of a dynamic bar code
	 *     
	 * @param key the key
	 * @param provider - provider of the java security implementation to be used
	 * @throws Exception the exception
	 */
	public void signLevel2(PrivateKey key, Provider prov) throws Exception {
		if (dynamicFrame != null) {
			dynamicFrame.signLevel2(key, prov);
		} 
	}


	/**
	 * Sets the level 1 algorithm Is.
	 *
	 * @param level1SigningAlg the level 1 signing algorithm (OID)
	 * @param level1KeyAlg the level 1 key algorithm (OID)
	 */
	public void setLevel1Algs(String level1SigningAlg, String level1KeyAlg) {
		if (dynamicFrame != null) {
			dynamicFrame.getLevel2Data().getLevel1Data().setLevel1SigningAlg(level1SigningAlg);
			dynamicFrame.getLevel2Data().getLevel1Data().setLevel1KeyAlg(level1KeyAlg);	
		}
	}
	
	/**
	 * Sets the level 2 algorithm Is.
	 *
	 * @param level2SigningAlg the level 2 signing algorithm (OID)
	 * @param level2KeyAlg the level 2 key algorithm (OID)
	 * @param publicKey the public key of the level 2 signature
	 */
	public void setLevel2Algs(String level2SigningAlg, String level2KeyAlg, PublicKey publicKey) {
		if (dynamicFrame != null) {
			if (dynamicFrame.getLevel2Data() == null) {
				dynamicFrame.setLevel2Data(new SimpleLevel2Data());
			}
			if (dynamicFrame.getLevel2Data().getLevel1Data() == null) {
				dynamicFrame.getLevel2Data().setLevel1Data(new SimpleLevel1Data());	
			}
			dynamicFrame.getLevel2Data().getLevel1Data().setLevel2SigningAlg(level2SigningAlg);
			dynamicFrame.getLevel2Data().getLevel1Data().setLevel2KeyAlg(level2KeyAlg);
			if (publicKey != null) {
				dynamicFrame.getLevel2Data().getLevel1Data().setLevel2publicKey(publicKey.getEncoded());
			}
		}
	}
	
	public void setDynamicData(IUicDynamicContent content) throws EncodingFormatException {
		if (dynamicFrame != null) {
			if (dynamicFrame.getLevel2Data() == null) {
				dynamicFrame.setLevel2Data(new SimpleLevel2Data());
			}		
			dynamicFrame.addDynamicContent(content);
		}		
	}
	
	public void setLevel2Data(IData level2data) {
		if (dynamicFrame != null) {
			if (dynamicFrame.getLevel2Data() == null) {
				dynamicFrame.setLevel2Data(new SimpleLevel2Data());
			}		
			dynamicFrame.getLevel2Data().setLevel2Data(level2data);
		}
	}
	
	public void setDynamicContentDataUIC1(UicDynamicContentDataFDC1 dcd) {
		if (dynamicFrame != null) {
			if (dynamicFrame.getLevel2Data() == null) {
				dynamicFrame.setLevel2Data(new SimpleLevel2Data());
			}		
			dynamicFrame.getLevel2Data().setLevel2Data(dcd.getApiDataType());
		}
	}
		
	public IData getLevel2Data() {
		if (dynamicFrame != null && dynamicFrame.getLevel2Data() != null) {
			return dynamicFrame.getLevel2Data().getLevel2Data();
		}
		return null;
	}
	
	
	public IUicDynamicContent getDynamicContent() {
		if (dynamicFrame != null && dynamicFrame.getLevel2Data() != null) {
			return dynamicFrame.getDynamicContent();
		}
		return null;
	}
	

	
	/**
	 * Sign level 1 of a dynamic bar code or a static bar code.
	 *
	 * @param securityProvider the security provider (RICS code of the company responsible for the security)
	 * @param key the key
	 * @param signingAlg the signing algorithm (OID)
	 * @param keyId the key id
	 * @throws Exception the exception
	 */
	public void signLevel1(String securityProvider,PrivateKey key,String signingAlg, String keyId) throws Exception {
		if (dynamicFrame != null) {
			dynamicFrame.getLevel2Data().getLevel1Data().setSecurityProvider(securityProvider);
			dynamicFrame.getLevel2Data().getLevel1Data().setLevel1SigningAlg(signingAlg);
			dynamicFrame.getLevel2Data().getLevel1Data().setKeyId(Long.parseLong(keyId));
			dynamicFrame.signLevel1(key);
		} else if (staticFrame != null) {
			staticFrame.setSignatureKey(keyId);
			staticFrame.setSecurityProvider(securityProvider);
			if (staticFrame.getHeaderRecord()!= null && staticFrame.getHeaderRecord().getIssuer() == null) {
				staticFrame.getHeaderRecord().setIssuer(securityProvider);
			}
			staticFrame.signByAlgorithmOID(key,signingAlg);
		}
	}
	
	/**
	 * Sign level 1 of a dynamic bar code or a static bar code.
	 *
	 * @param securityProvider the security provider (RICS code of the company responsible for the security)
	 * @param key the key
	 * @param signingAlg the signing algorithm (OID)
	 * @param keyId the key id
	 * @param provider - the provider of the java security implementation
	 * @throws Exception the exception
	 */
	public void signLevel1(String securityProvider,PrivateKey key,String signingAlg, String keyId, Provider prov) throws Exception {
		if (dynamicFrame != null) {
			dynamicFrame.getLevel2Data().getLevel1Data().setSecurityProvider(securityProvider);
			dynamicFrame.getLevel2Data().getLevel1Data().setLevel1SigningAlg(signingAlg);
			dynamicFrame.getLevel2Data().getLevel1Data().setKeyId(Long.parseLong(keyId));
			dynamicFrame.signLevel1(key,prov);
			//dynamicFrame.getLevel2Data().signLevel1(key, prov);
		} else if (staticFrame != null) {
			staticFrame.setSignatureKey(keyId);
			staticFrame.setSecurityProvider(securityProvider);
			if (staticFrame.getHeaderRecord()!= null && staticFrame.getHeaderRecord().getIssuer() == null) {
				staticFrame.getHeaderRecord().setIssuer(securityProvider);
			}
			staticFrame.signByAlgorithmOID(key,signingAlg,prov);
		}
	}
	
	
	/**
	 * Sets the static header parameter.
	 *
	 * @param ticketId the ticket id
	 * @param language the language
	 */
	public void setStaticHeaderParams(String ticketId,String language) {
	  if (staticFrame != null && staticFrame.getHeaderRecord()!= null) {
		staticFrame.getHeaderRecord().setIdentifier(ticketId);
		staticFrame.getHeaderRecord().setLanguage(language);
	  }
	}

	/**
	 * Gets the dynamic frame.
	 *
	 * @return the dynamic frame
	 */
	public IDynamicFrame getDynamicFrame() {
		return dynamicFrame;
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
	 * Encodes the signed bar code data 
	 *
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws Exception the exception
	 */
	public byte[] encode() throws IOException, Exception {
		if (dynamicFrame != null) {
			return dynamicFrame.encode();
		} else if (staticFrame != null) {
			return staticFrame.encode();
		}
		return null;
	}




	
	
	

}
