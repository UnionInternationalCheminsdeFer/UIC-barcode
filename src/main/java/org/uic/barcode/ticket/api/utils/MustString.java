package org.uic.barcode.ticket.api.utils;

public class MustString implements CharSequence {
    private final String underlyingString;

    public MustString(String underlyingString) {
        this.underlyingString = underlyingString;
    }

    @Override
    public int length() {
        return this.underlyingString.length();
    }

    @Override
    public char charAt(int index) {
        return this.underlyingString.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this.underlyingString.subSequence(start, end);
    }

    @Override
    public String toString() {
        return this.underlyingString;
    }

    public boolean isEmpty() {
        return this.underlyingString.isEmpty();
    }
}
