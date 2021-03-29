package org.uic.barcode.asn1.uper;



public class ByteBitBuffer implements BitBuffer {

    byte[] bytes;
    byte[] mask = new byte[] {
     (byte) 0b1000_0000,
            0b0100_0000,
            0b0010_0000,
            0b0001_0000,
            0b0000_1000,
            0b0000_0100,
            0b0000_0010,
            0b0000_0001,
    };

    boolean isFinite;

    int mark;
    int position;
    int limit;

    
    @Override public boolean get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is less than 0");
        } else if (index >= limit) {
            throw new IndexOutOfBoundsException("Index " + index + " violates the limit " + limit);
        }
        boolean result = (bytes[index / 8] & mask[index % 8]) != 0;
        return result;
    }

    @Override public boolean get() {
        boolean result = get(position);
        position++;
        return result;
    }

    private void grow() {
        byte[] newbytes = new byte[2 * bytes.length];
        System.arraycopy(bytes, 0, newbytes, 0, bytes.length);
        bytes = newbytes;
    }

    @Override public BitBuffer put(int index, boolean element) {
        if (bytes.length <= index / 8) {
            if (isFinite) { throw new IndexOutOfBoundsException(); }
            else { grow(); }
        }
        if (element) {
            bytes[index / 8] |= mask[index % 8];
        } else {
            bytes[index / 8] &= ~mask[index % 8];
        }
        return this;
    }

    @Override public BitBuffer put(boolean element) {
        put(position, element);
        position++;
        limit = limit < position ? position : limit;  // TODO: should it be here?
        return this;
    }

    @Override public BitBuffer putByte(byte element) {
        for (int i = 0; i < 8; i++) {
            put((element & mask[i]) != 0);
        }
        return this;
    }
    
    @Override public BitBuffer putByteArray(int index, byte[] data) {
    	
    	for (int l = 0; l < data.length;l++) {
    		for (int i = 0; i < 8; i++) {
    			put((data[l] & mask[i]) != 0);
    		}
    	}	
    	return this;
    }
    

    @Override public byte getByte() {
        byte result = 0;
        for (int i = 0; i < 8; i++) {
            result |= (get() ? 1 : 0) << (7 - i);
        }
        return result;
    }

    @Override public int limit() {
        return limit;
    }

    @Override public String toBooleanString(int startIndex, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = startIndex; i < startIndex + length; i++) {
            sb.append(get(i) ? "1" : "0");
        }
        return sb.toString();
    }

    @Override public int capacity() {
        return isFinite ? bytes.length * 8 : Integer.MAX_VALUE;
    }

    @Override public int position() {
        return position;
    }

    @Override public int remaining() {
        return limit - position;
    }

    public ByteBitBuffer(byte[] backingArray) {
        this.bytes = backingArray;
        this.isFinite = true;
    }

    private ByteBitBuffer(int initialCapacity) {
        this.bytes = new byte[initialCapacity];
        this.isFinite = false;
    }

    public static ByteBitBuffer allocate(int lengthInBits) {
        return new ByteBitBuffer(new byte[(lengthInBits + 7) / 8]);
    }

    public static ByteBitBuffer createInfinite() {
        return new ByteBitBuffer(64);
    }

    @Override public BitBuffer flip() {
        limit = position;
        position = 0;
        return this;
    }

    @Override public String toBooleanStringFromPosition(int startIndex) {
        return toBooleanString(startIndex, position-startIndex);
    }

    @Override public byte[] array() {
        return bytes;
    }

	@Override
	public void putInteger(int position, int length,int number) {
		String s = Integer.toBinaryString(number);
		if (s.length() > length) {
			//value is to large
			return;
		}
		
		for (int i = 0;i < length;i++){
			int index = position + i;
     	    this.put(index,false);
		}
		
		
		int startIndex = position + length - s.length();
		for (int i = 0;i < s.length();i++){
			/*
			 * i = max --> index = position + length - 1
			 * i = 0 --> index = position + 
			 */
			int index = startIndex + i;
			if (s.charAt(i) == '1') {
			  this.put(index, true );
			} else {
			  this.put(index, false);		
			}
		}
		
	}
	
	@Override
	public void putChar5String(int position, int length, String s) {

		String upperCaseString  = s.toUpperCase();
		int offset = 0;
		for (int i = 0; i < s.length() ; i++) {
			char character = upperCaseString.charAt(i);
			int intValue = (int) character - 32;
			if (intValue > -1 && intValue < 64) {
				this.putInteger(position + offset,5, intValue); 
				offset = offset + 5;
			} else {
				this.putInteger(position + offset,5,0); 
				position = position + 5;
			}
		}
	}

	@Override
	public void putChar6String(int position, int length, String s) {

		String upperCaseString  = s.toUpperCase();
		int offset = 0;
		for (int i = 0; i < s.length() ; i++) {
			char character = upperCaseString.charAt(i);
			int intValue = (int) character - 32;
			if (intValue > -1 && intValue < 64) {
				this.putInteger(position + offset,6, intValue); 
				offset = offset + 6;
			} else {
				this.putInteger(position + offset,6,0); 
				position = position + 6;
			}
		}
	}

	@Override
	public int getInteger(int position, int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0;i < length;i++){
			if (this.get(position + i)) {
				sb.append("1");
			} else {
				sb.append("0");
			}
		}
	    return Integer.parseInt(sb.toString(), 2);
	}

	@Override
	public String getChar6String(int position, int length) {
		
		StringBuilder stringBuilder = new StringBuilder();

		int chars = length / 6;

		for (int i = 0; i < chars; i++) {
			int newPosition =  position + i * 6;

			int x =  this.getInteger(newPosition, 6);
			x = x + 32;
		
			char c = (char) x;
			stringBuilder.append(c);

		}

		return stringBuilder.toString().trim();
	}
	
	@Override
	public String getChar5String(int position, int length) {
		
		StringBuilder stringBuilder = new StringBuilder();

		int chars = length / 5;

		for (int i = 0; i < chars; i++) {
			int newPosition =  position + i * 5;

			int x =  getInteger(newPosition, 5);
			x = x + 42;
			
			char c = (char) x;
			stringBuilder.append(c);

		}

		return stringBuilder.toString().trim();
	}

}
