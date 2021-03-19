package org.uic.barcode.staticFrame.ticketLayoutBarcode;


public enum FormatType {
	NORMAL("NORMAL"),
	BOLD("BOLD"),
	ITALIC("ITALIC"),
	BOLDITALIC("BOLDITALIC"),
	SMALL("SMALL"),
	SMALLBOLD("SMALLBOLD"),
	SMALLITALIC("SMALLITALIC"),
	SMALLBOLDITALIC("SMALLBOLDITALIC");
	

	
	public String text;

	FormatType(String text) {
		this.text = text;
	}
	
	public static FormatType getFormatType(int i) {
		try {
			return FormatType.values()[i];
		} catch (Exception e) {
			return null;
		}
	}	
	
	public String toString(){
		return text;
	}
	
}
