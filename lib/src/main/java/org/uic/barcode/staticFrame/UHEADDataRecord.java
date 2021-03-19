package org.uic.barcode.staticFrame;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.uic.barcode.ticket.EncodingFormatException;

/**
 * The Class UHEADDataRecord implements the additional header record of a statis UIC bar code
 */
public class UHEADDataRecord extends DataRecord{
	
	
		/** The issuing date. */
		private Date issuingDate = null;
		
		/** The flags. */
		private int flags;
		
		/** The issuer. */
		private String issuer;
		
		/** The identifier. */
		private String identifier;
		
		/** The language. */
		private String language;
		
		/** The additional language. */
		private String additionalLanguage;
	
		/**
		 * Instantiates a new UHEAD data record.
		 */
		public UHEADDataRecord() {
			super("U_HEAD");
		}
		
		
		/**
		 * Gets the issuing date.
		 *
		 * @return the issuing date
		 */
		public Date getIssuingDate() {
			return issuingDate;
		}
		
		/**
		 * Sets the issuing date.
		 *
		 * @param issuingDate the new issuing date
		 */
		public void setIssuingDate(Date issuingDate) {
			this.issuingDate = issuingDate;
		}
		
		/**
		 * Gets the flags.
		 *
		 * @return the flags
		 */
		public int getFlags() {
			return flags;
		}
		
		/**
		 * Sets the flags.
		 *
		 * @param flags the new flags
		 */
		public void setFlags(int flags) {
			this.flags = flags;
		}
		
		/**
		 * Gets the issuer.
		 *
		 * @return the issuer
		 */
		public String getIssuer() {
			return issuer;
		}
		
		/**
		 * Sets the issuer.
		 *
		 * @param issuer the new issuer
		 */
		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}
		
		/**
		 * Gets the identifier.
		 *
		 * @return the identifier
		 */
		public String getIdentifier() {
			return identifier;
		}
		
		/**
		 * Sets the identifier.
		 *
		 * @param identifier the new identifier
		 */
		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}
		
		/**
		 * Gets the language.
		 *
		 * @return the language
		 */
		public String getLanguage() {
			return language;
		}
		
		/**
		 * Sets the language.
		 *
		 * @param language the new language
		 */
		public void setLanguage(String language) {
			this.language = language;
		}
		
		/**
		 * Gets the additional language.
		 *
		 * @return the additional language
		 */
		public String getAdditionalLanguage() {
			return additionalLanguage;
		}
		
		/**
		 * Sets the additional language.
		 *
		 * @param additionalLanguage the new additional language
		 */
		public void setAdditionalLanguage(String additionalLanguage) {
			this.additionalLanguage = additionalLanguage;
		}
			

		/**
		 * Decode content.
		 *
		 * @throws IOException Signals that an I/O exception has occurred.
		 * @throws EncodingFormatException the encoding format exception
		 */
		protected void decodeContent() throws IOException, EncodingFormatException{
						
			if (content == null || content.length == 0 ) return;
			
			issuer = decodeString(content, 0 , 4);	

			identifier = decodeString(content, 4 , 20);		
			
			String issuingDateString = decodeString(content, 24 , 12);		

			String flagsString  = decodeString(content,36 , 1);
			
			language = decodeString(content, 37 , 2);

			additionalLanguage = decodeString(content,39 , 2);
			

			try {
				flags = Integer.parseInt(flagsString);
			} catch (Exception e) {
				flags = 9;
			}		

			// date format "DDMMYYYYHHMM"
			SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyhhmm");
			try {
				issuingDate = formatter.parse(issuingDateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		

		/**
		 * Decode string.
		 *
		 * @param byteData the byte data
		 * @param offset the offset
		 * @param length the length
		 * @return the string
		 */
		private String decodeString(byte[] byteData, int offset, int length) {
			
			char[] chars = new char[length];
			
			for (int i = 0; i < length  && i < byteData.length;i++) {
				byte byteValue = byteData[offset + i];
				if (byteValue == '\n') {
					byteValue = ' ';
				}
				chars[i] = (char) byteValue;
			}
			
			return String.copyValueOf(chars);
		}

		/**
		 * Encode content.
		 *
		 * @throws IOException Signals that an I/O exception has occurred.
		 * @throws EncodingFormatException the encoding format exception
		 */
		protected void encodeContent() throws IOException, EncodingFormatException {
			
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    	
  	     	String issuerElement = String.format("%4s", this.issuer);

			String idElement = String.format("%20s", this.identifier);
			
			//DDMMYYYYHHMM"		
			Calendar now = Calendar.getInstance();
			
			// issuing date can be in the ticket or in the header
			if (this.issuingDate != null) {
				now.setTime(this.issuingDate);
			}
			
			String timeElement = String.format("%02d%02d%04d%02d%02d", 
					                           now.get(Calendar.DAY_OF_MONTH),
					                           now.get(Calendar.MONTH),
					                           now.get(Calendar.YEAR),
					                           now.get(Calendar.HOUR),
					                           now.get(Calendar.MINUTE));
			
				
			String flagsElement = String.format("%01d",this.flags);
			
			
			String languageElement =  String.format("%2s%2s" ,this.language, this.additionalLanguage);
			
			try {
				
				outputStream.write(issuerElement.getBytes());				
				outputStream.write(idElement.getBytes());	
				outputStream.write(timeElement.getBytes());		
				
				outputStream.write(flagsElement.getBytes());	
				outputStream.write(languageElement.getBytes());			
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			super.setData(outputStream.toByteArray());

			
		}

}
