/*
 * 
 */
package org.uic.barcode.staticFrame;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.uic.barcode.staticFrame.ticketLayoutBarcode.FormatType;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.LayoutElement;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;
import org.uic.barcode.ticket.EncodingFormatException;

/**
 * The Class UTLAYDataRecord implements a bar code data record containing the ticket layout.
 */
public class UTLAYDataRecord extends DataRecord {
	
	/** The ticket layout. */
	private TicketLayout layout;
	
	private int length = 0;
	
	/**
	 * Instantiates a new empty UTLAY data record.
	 */
	public UTLAYDataRecord() {
		super("U_TLAY","01");
	}

	/**
	 * Decode utf-8 string.
	 *
	 * @param byteData the byte data
	 * @param offset the offset
	 * @param length the length
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	private static String decodeUtf8String(byte[] byteData, int offset, int length) throws UnsupportedEncodingException {	
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++){
			bytes[i] = byteData[i + offset];
		}
		return Charset.forName("UTF-8").decode(ByteBuffer.wrap(bytes)).toString();
	}

	/**
	 * Decode string.
	 *
	 * @param byteData the byte data
	 * @param offset the offset
	 * @param length the length
	 * @return the string
	 */
	private static String decodeString(byte[] byteData, int offset, int length) {
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++){
			bytes[i] = byteData[i + offset];
		}
		return Charset.forName("ISO-8859-1").decode(ByteBuffer.wrap(bytes)).toString();
	}
	
	/**
	 * Encode utf-8.
	 *
	 * @param value the value
	 * @return the byte[]
	 */
	private static byte[] encodeUtf8(String value) {
		
		try {
			return value.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {			
			throw new RuntimeException("UTF8 String encoding wrong!",e);
		}		
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("TLB: ").append(layout.getLayoutStandard()).append('\n');
				
		for (LayoutElement e : layout.getElements()){
			sb.append("column: ").append(e.getColumn()).append(" - ");
			sb.append("line: ").append(e.getLine()).append(" - ");
			sb.append("width: ").append(e.getWidth()).append(" - ");
			sb.append("heigth: ").append(e.getHeight()).append(" - ");
			sb.append("text: ").append(e.getText()).append(" - ");
			sb.append("format: ").append(e.getFormat().toString()).append('\n');
		}
			
		return sb.toString();
		
	}

	/**
	 * Decode content.
	 * @return 
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	@Override
	protected void decodeContent() throws IOException, EncodingFormatException {
		
		layout = new TicketLayout();
		
		if (content == null || content.length == 0 ) return;
			
		int offset = 0;
		
		String layoutType = decodeString(content, offset , 4);
		layout.setLayoutStandard(layoutType);
		offset = offset + 4;
				
		String numberValue = decodeString(content, offset , 4);
		offset = offset + 4;
		
		int elements = 0;
		try {
			elements = Integer.parseInt(numberValue.trim());			
		} catch(NumberFormatException e){
			//Do Nothing			
		}
		
		int offsetBeforeFields = offset;
		
		try {
		
			decodeFields(elements, offset);

		} catch(Exception e) {
			
			offset = offsetBeforeFields;
						
			if (!decodeUtf8FieldsWithCharacterLengthSucceeded(elements, offset)) {
				throw e;
			}
			
		}
		

	}
	
	
	/*
	 * 
	 * special decoding to fix wrong encoding of DSB and SJ
	 * 
	 */
	private boolean decodeUtf8FieldsWithCharacterLengthSucceeded(int elements, int offset) {

		layout.getElements().clear();
		
		int remainingBytes = content.length - offset; 
		
		//content length - layout standard, number of fields
		int characterLength = this.contentLength - 8;
		
		//Check for unicode with length error
		String utf8String = getUnicode(offset, content, characterLength);
		int charLength = utf8String.length();
		if (charLength == characterLength) {
			//length value was ok
			return false;
		}		

		
		for (int i = 0; i < elements && remainingBytes > 15 ;i++){
					
			String lineValue = decodeString(content, offset , 2);
			offset = offset + 2;			
			int line = 0;
			try {
				line = Integer.parseInt(lineValue.trim());			
			} catch(NumberFormatException e){
				return false;	
			}			
			String columnValue = decodeString(content, offset , 2);
			offset = offset + 2;
			int column = 0;
			try {
				column = Integer.parseInt(columnValue.trim());			
			} catch(NumberFormatException e){
				return false;		
			}			
			String heightValue = decodeString(content, offset , 2);
			offset = offset + 2;
			int height = 0;
			try {
				height = Integer.parseInt(heightValue.trim());			
			} catch(NumberFormatException e){
				return false;			
			}			
			String widthValue = decodeString(content, offset , 2);
			offset = offset + 2;
			int width = 0;
			try {
				width = Integer.parseInt(widthValue.trim());			
			} catch(NumberFormatException e){
				return false;			
			}			
			String formatValue = decodeString(content, offset , 1);
			offset = offset + 1;
			int format = 0;
			try {
				format = Integer.parseInt(formatValue.trim());			
			} catch(NumberFormatException e){
				return false;			
			}			
			String lengthValue = decodeString(content, offset , 4);
			offset = offset + 4;
			int fieldLength = 0;
			try {
				fieldLength = Integer.parseInt(lengthValue.trim());			
			} catch(NumberFormatException e){
				return false;			
			}			

			String text = null;
			if (fieldLength > 0) {
				text = getUnicode(offset, content, fieldLength );
				if (text == null) {
					return false;
				}
				offset = offset + text.getBytes(Charset.forName("UTF-8")).length;
			} 
			
			LayoutElement layoutElement = new LayoutElement();   
			
			layoutElement.setColumn(column);
			layoutElement.setLine(line);
			layoutElement.setHeight(height);
			layoutElement.setWidth(width);
			layoutElement.setText(text);
			
			layoutElement.setFormat(FormatType.values()[format]);
						
			layout.addLayoutElement(layoutElement);
			
			remainingBytes = content.length - offset; 
		
		}	
		
		
		this.length = offset;
		
		return true;
		
	}

	/*
	 * get a unicode string from the byte array with characterLength unicode characters
	 * 
	 * 
	 */
	private String getUnicode(int offset, byte[] content, int characterLength) {
		
		int remainingBytes = content.length - offset;
		
		
		remainingBytes = Math.min(characterLength * 4, remainingBytes);
		
		String utf8String = null;
		
		for (int i = 1 ; i <= remainingBytes ;i++){
			
			try {
				utf8String = decodeUtf8String(content, offset, i);
				if (utf8String.length() == characterLength) {
					return utf8String;
				}
			} catch (UnsupportedEncodingException e) {
				//
			}
		}
		
		return utf8String;
	}

	private void decodeFields(int elements, int offset){
		
		int remainingBytes = content.length - offset; 
		
		for (int i = 0; i < elements && remainingBytes > 15 ;i++){
		
			String lineValue = decodeString(content, offset , 2);
			offset = offset + 2;			
			int line = 0;
			try {
				line = Integer.parseInt(lineValue.trim());			
			} catch(NumberFormatException e){
				//Do Nothing			
			}			
			String columnValue = decodeString(content, offset , 2);
			offset = offset + 2;
			int column = 0;
			try {
				column = Integer.parseInt(columnValue.trim());			
			} catch(NumberFormatException e){
				//Do Nothing			
			}			
			String heightValue = decodeString(content, offset , 2);
			offset = offset + 2;
			int height = 0;
			try {
				height = Integer.parseInt(heightValue.trim());			
			} catch(NumberFormatException e){
				//Do Nothing			
			}			
			String widthValue = decodeString(content, offset , 2);
			offset = offset + 2;
			int width = 0;
			try {
				width = Integer.parseInt(widthValue.trim());			
			} catch(NumberFormatException e){
				//Do Nothing			
			}			
			String formatValue = decodeString(content, offset , 1);
			offset = offset + 1;
			int format = 0;
			try {
				format = Integer.parseInt(formatValue.trim());			
			} catch(NumberFormatException e){
				//Do Nothing			
			}			
			String lengthValue = decodeString(content, offset , 4);
			offset = offset + 4;
			int fieldLength = 0;
			try {
				fieldLength = Integer.parseInt(lengthValue.trim());			
			} catch(NumberFormatException e){
				//Do Nothing			
			}			

			String text = null;
			
			if (fieldLength > 0) {
				try {
					text = decodeUtf8String(content, offset ,fieldLength);
				} catch (UnsupportedEncodingException e) {
					text = "unsupported character set";
				}
				offset = offset + fieldLength;
			}
			
			LayoutElement layoutElement = new LayoutElement();   
			
			layoutElement.setColumn(column);
			layoutElement.setLine(line);
			layoutElement.setHeight(height);
			layoutElement.setWidth(width);
			layoutElement.setText(text);		
			
			layoutElement.setFormat(FormatType.values()[format]);
						
			layout.addLayoutElement(layoutElement);
			
			remainingBytes = content.length - offset; 
		
		}		
		
		this.length = offset;

	}
	

	/**
	 * Encode content.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	@Override
	protected void encodeContent() throws IOException, EncodingFormatException {
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        if (layout == null || layout.getElements() == null || layout.getElements().isEmpty()) {
        	return;
        }

		//number of text elements
		String numberOfFields = String.format("%04d",layout.getElements().size());
			
		outputStream.write(layout.getLayoutStandard().getBytes());
		outputStream.write(numberOfFields.getBytes());
			
		for (LayoutElement e : layout.getElements()){
				
			String line   = String.format("%02d",e.getLine());
			String column = String.format("%02d",e.getColumn());
			String heigth = String.format("%02d",e.getHeight());
			String width  = String.format("%02d",e.getWidth());
			String format = String.format("%01d",e.getFormat().ordinal());
			String size   = String.format("%04d",encodeUtf8(e.getText()).length);

			outputStream.write(line.getBytes());
			outputStream.write(column.getBytes());
			outputStream.write(heigth.getBytes());
			outputStream.write(width.getBytes());
			outputStream.write(format.getBytes());
			outputStream.write(size.getBytes());
			outputStream.write(encodeUtf8(e.getText()));
				
		}			      
        
		content = outputStream.toByteArray();
	}
	
	/**
	 * Sets the layout.
	 *
	 * @param layout the new layout
	 */
	public void setLayout(TicketLayout layout) {
		this.layout = layout;
	}

	
	/**
	 * Gets the layout.
	 *
	 * @return the layout
	 */
	public TicketLayout getLayout() {
		return layout;
	}

	/*
	 * get the length of the data
	 * 
	 * This is needed to fix encoding errors made due to wrong length descriptions on unicode content.
	 * 
	 */
	public int getDecodedLength() {
		return length + 12;
	}

}
