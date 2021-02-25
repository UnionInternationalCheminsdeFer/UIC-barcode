package org.uic.barcode.asn1.datatypes;

import java.math.BigInteger;

//outdated: use BigInteger 
public class Asn1BigInteger {

    private final BigInteger value;

    public Asn1BigInteger(final BigInteger value) {
        this.value = value;
    }

    @Override public String toString() {
        return "" + value;
    }

    public BigInteger value() { return value; }
    
    public Long longValue() {
    	return value.longValue();
    }
    
    public Integer intValue() {
    	return value.intValue();
    }    
    
	public Asn1BigInteger(Long num) {
		this.value = BigInteger.valueOf(num);
	}
	
	public Asn1BigInteger(long num) {
		this.value = BigInteger.valueOf(num);
	}	
	
	public Asn1BigInteger(Integer num) {
		this.value = BigInteger.valueOf(num);
	}
	
	public Asn1BigInteger(int num) {
		this.value = BigInteger.valueOf(num);
	}		
	
	public static Long toLong(Asn1BigInteger object) {
		if (object == null) return null;
		return object.longValue();
	}
	
	public static Asn1BigInteger toAsn1(Long object) {
		if (object == null) return null;
		return new Asn1BigInteger(object);
	}

	public static Asn1BigInteger toAsn1(Integer object) {
		if (object == null) return null;
		return new Asn1BigInteger(object);
	}
	
	public Long toLong(){
		if (this.value != null) {
			return this.value.longValue();
		} 
		return null;
	}
	
	public BigInteger toBigInteger(){
		return value;
	}
	
	
	
}
