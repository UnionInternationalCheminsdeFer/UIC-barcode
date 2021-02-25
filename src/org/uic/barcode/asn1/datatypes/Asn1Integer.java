package org.uic.barcode.asn1.datatypes;



//outdated: use BigInteger 
public class Asn1Integer {

    public long value;

    public Asn1Integer() {}
    public Asn1Integer(long value) {
        this.value = value;
    }

    public Long value() { return value; }

    @Override public String toString() {
        return "" + value;
    }
    
    public Long longObject () {
    	return new Long(value()); 
    }

	public Asn1Integer(Long num) {
		this.value = num;
	}
	
	
	public Asn1Integer(Integer num) {
		this.value = num;
	}
	
	public Asn1Integer(int num) {
		this.value = num;
	}
	
	public static Long toLong(Asn1Integer object) {
		if (object == null) return null;
		return object.value();
	}
		
	
	public static Asn1Integer toAsn1(Long object) {
		if (object == null) return null;
		return new Asn1Integer(object);
	}
	







}
