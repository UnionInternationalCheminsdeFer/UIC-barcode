package org.uic.barcode.staticFrame.ticketLayoutBarcode;

public class LayoutElement {
	
	private  int column;
	private int line;
	private int height;
	private int width;
	private String text;
	private FormatType format = FormatType.NORMAL;
	
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public FormatType getFormat() {
		return format;
	}
	public void setFormat(FormatType format) {
		this.format = format;
	}
	
	
}
