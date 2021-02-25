package org.uic.barcode.asn1.uper;

/**
 * An interface for convenient storage of bits, similar to Java's ByteBuffer.
 *
 * This interface and its implementation are very useful for UPER, since UPER operates on bits
 * regardless of byte boundaries.
 *
 */
public interface BitBuffer {
    boolean get();
    boolean get(int index);
    BitBuffer put(boolean element);
    BitBuffer put(int index, boolean element);
    int limit();
    int capacity();
    int position();
    int remaining();
    BitBuffer flip();
    String toBooleanString(int startIndex, int length);
    String toBooleanStringFromPosition(int startIndex);
    byte[] array();
    BitBuffer putByte(byte element);
    byte getByte();
	void putInteger(int index, int length,int number);
	void putChar6String(int index, int length, String s);
	int getInteger(int index, int length);
	String getChar6String(int position, int length);
	void putChar5String(int index, int length, String s);
	String getChar5String(int inxed, int length);
	BitBuffer putByteArray(int index, byte[] data);
}
