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

}
