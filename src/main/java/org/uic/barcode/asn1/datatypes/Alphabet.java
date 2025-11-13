package org.uic.barcode.asn1.datatypes;

/**
 * Alphabet class for Restricted Strings.
 *
 */
public abstract class Alphabet {

    private final String chars;

    protected Alphabet(String chars) {
        this.chars = chars;
    }

    public final String chars() {
        return chars;
    }
    
    public final boolean validate(String content) {
    	       
        for (int i = 0; i < content.length(); i++) {
        	int index = chars.indexOf(content.charAt(i));
        	if (index == 0) {
        		return false;
        	}
        }   	
		return true;
    }
    
    public int getIndex(char c) {
    	return chars.indexOf(c);
    }
    
    public int getSize() {
    	return chars.toCharArray().length;
    }

    public char getChar(int index) {
    	return chars.charAt(index);
    }
}
