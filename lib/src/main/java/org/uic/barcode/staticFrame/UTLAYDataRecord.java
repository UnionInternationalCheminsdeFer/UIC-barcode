/*
 * 
 */
package org.uic.barcode.staticFrame;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

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
		return StandardCharsets.UTF_8.decode(ByteBuffer.wrap(bytes)).toString();
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
		return StandardCharsets.ISO_8859_1.decode(ByteBuffer.wrap(bytes)).toString();
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
			elements = Integer.parseInt(numberValue);			
		} catch(NumberFormatException e){
			//Do Nothing			
		}
		
		int remainingBytes = content.length - offset; 
		
		for (int i = 0; i < elements && remainingBytes > 0 ;i++){
			
			String lineValue = decodeString(content, offset , 2);
			offset = offset + 2;			
			int line = 0;
			try {
				line = Integer.parseInt(lineValue);			
			} catch(NumberFormatException e){
				//Do Nothing			
			}			
			String columnValue = decodeString(content, offset , 2);
			offset = offset + 2;
			int column = 0;
			try {
				column = Integer.parseInt(columnValue);			
			} catch(NumberFormatException e){
				//Do Nothing			
			}			
			String heightValue = decodeString(content, offset , 2);
			offset = offset + 2;
			int height = 0;
			try {
				height = Integer.parseInt(heightValue);			
			} catch(NumberFormatException e){
				//Do Nothing			
			}			
			String widthValue = decodeString(content, offset , 2);
			offset = offset + 2;
			int width = 0;
			try {
				width = Integer.parseInt(widthValue);			
			} catch(NumberFormatException e){
				//Do Nothing			
			}			
			String formatValue = decodeString(content, offset , 1);
			offset = offset + 1;
			int format = 0;
			try {
				format = Integer.parseInt(formatValue);			
			} catch(NumberFormatException e){
				//Do Nothing			
			}			
			String lengthValue = decodeString(content, offset , 4);
			offset = offset + 4;
			int length = 0;
			try {
				length = Integer.parseInt(lengthValue);			
			} catch(NumberFormatException e){
				//Do Nothing			
			}			

			String text;
			try {
				text = decodeUtf8String(content, offset ,length);
			} catch (UnsupportedEncodingException e) {
				text = "unsupported character set";
			}
			offset = offset + length;
			
			LayoutElement layoutElement = new LayoutElement();   
			
			layoutElement.setColumn(column);
			layoutElement.setLine(line);
			layoutElement.setHeight(height);
			layoutElement.setWidth(width);
			layoutElement.setText(text);
			
			layoutElement.setFormat(FormatType.values()[format]);
						
			layout.addLayoutElement(layoutElement);
			
		}

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

}
