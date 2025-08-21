package org.uic.barcode.asn1.datatypesimpl;

/*
 * Sequence of Asn1Integer for restricted integers
 * 
 */
public class Asn1NullObject {
	
	private String attributeName = null;
	
	
	public Asn1NullObject(String name){
		attributeName = name;
	}


	public String getAttributeName() {
		return attributeName;
	}


	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
}	
