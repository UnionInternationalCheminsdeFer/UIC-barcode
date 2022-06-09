package org.uic.barcode.utils;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class ElipticCurves.
 */
public class EllipticCurveNames {
	
	
	/** The oit to name. */
	public HashMap<String, String> oitToName = new HashMap<String,String>();
	
	/** The me. */
	private static EllipticCurveNames me = null;
	
	
	/**
	 * Gets the single instance of ElipticCurves.
	 *
	 * @return single instance of ElipticCurves
	 */
	public static EllipticCurveNames getInstance() {
		
		if (me == null) {
			me = new EllipticCurveNames();
			me.oitToName.put("1.2.840.10045.3.0.4", "c2pnb176w1");
			me.oitToName.put("1.2.840.10045.3.0.1", "c2pnb163v1");
		    me.oitToName.put("1.2.840.10045.3.0.2", "c2pnb163v2");
		    me.oitToName.put("1.2.840.10045.3.0.3", "c2pnb163v3");
		    me.oitToName.put("1.2.840.10045.3.0.10","c2pnb208w1");
		    me.oitToName.put("1.2.840.10045.3.0.7", "c2tnb191v3");
		    me.oitToName.put("1.2.840.10045.3.0.6", "c2tnb191v2");
		    me.oitToName.put("1.2.840.10045.3.0.5", "c2tnb191v1");
		    me.oitToName.put("1.2.840.10045.3.0.13","c2tnb239v3");
		    me.oitToName.put("1.2.840.10045.3.0.12","c2tnb239v2");
		    me.oitToName.put("1.2.840.10045.3.0.11","c2tnb239v1");
		    me.oitToName.put("1.2.840.10045.3.0.16","c2pnb272w1");
		    me.oitToName.put("1.2.840.10045.3.0.17","c2pnb304w1");
		    me.oitToName.put("1.2.840.10045.3.0.19","c2pnb368w1");
		    me.oitToName.put("1.2.840.10045.3.0.18","c2tnb359v1");
		    me.oitToName.put("1.2.840.10045.3.0.20","c2tnb431r1");
		    me.oitToName.put("1.2.840.10045.3.0.8", "c2onb191v4");
		    me.oitToName.put("1.2.840.10045.3.0.9", "c2onb191v5");
		    me.oitToName.put("1.2.840.10045.3.0.14","c2onb239v4");
		    me.oitToName.put("1.2.840.10045.3.0.15","c2onb239v5");
		    me.oitToName.put("1.2.840.10045.3.1.1", "secp192r1");  
			//me.oitToName.put("1.2.840.10045.3.1.1", "prime192v1");
		    me.oitToName.put("1.2.840.10045.3.1.2", "prime192v2"); 
		    me.oitToName.put("1.2.840.10045.3.1.3", "prime192v3"); 
		    me.oitToName.put("1.2.840.10045.3.1.4", "prime239v1"); 
		    me.oitToName.put("1.2.840.10045.3.1.5", "prime239v2"); 
		    me.oitToName.put("1.2.840.10045.3.1.6", "prime239v3"); 
			me.oitToName.put("1.2.840.10045.3.1.7", "secp256r1");
			//me.oitToName.put("1.2.840.10045.3.1.7", "prime256v1");	
			
		    me.oitToName.put("1.3.132.0.1",  "sect163k1");  
		    me.oitToName.put("1.3.132.0.2",  "sect163r1"); 
		    me.oitToName.put("1.3.132.0.3",  "sect239k1");  
		    me.oitToName.put("1.3.132.0.4",  "sect113r1");  
		    me.oitToName.put("1.3.132.0.5",  "sect113r2");  
		    me.oitToName.put("1.3.132.0.6",  "secp112r1");  	   
		    me.oitToName.put("1.3.132.0.7",  "secp112r2");  
		    me.oitToName.put("1.3.132.0.8",  "secp160r1");  
		    me.oitToName.put("1.3.132.0.9",  "secp160k1");  
			me.oitToName.put("1.3.132.0.10", "secp256k1");			    
			me.oitToName.put("1.3.132.0.15", "sect163r2");
			me.oitToName.put("1.3.132.0.16", "sect283k1");
			me.oitToName.put("1.3.132.0.17", "sect283r1");
		    me.oitToName.put("1.3.132.0.22", "sect131r1");  
		    me.oitToName.put("1.3.132.0.23", "sect131r2");  
		    me.oitToName.put("1.3.132.0.24", "sect193r1");  
		    me.oitToName.put("1.3.132.0.25", "sect193r2");  
		    me.oitToName.put("1.3.132.0.26", "sect233k1");  
		    me.oitToName.put("1.3.132.0.27", "sect233r1"); 		    
		    me.oitToName.put("1.3.132.0.28", "secp128r1");  
		    me.oitToName.put("1.3.132.0.29", "secp128r2");  
		    me.oitToName.put("1.3.132.0.30", "secp160r2");  
		    me.oitToName.put("1.3.132.0.31", "secp192k1");  
		    me.oitToName.put("1.3.132.0.32", "secp224k1");  
		    me.oitToName.put("1.3.132.0.33", "secp224r1");  			
			me.oitToName.put("1.3.132.0.34", "secp384r1");
			me.oitToName.put("1.3.132.0.35", "secp521r1");
			me.oitToName.put("1.3.132.0.36", "sect409k1");
			me.oitToName.put("1.3.132.0.37", "sect409r1");
			me.oitToName.put("1.3.132.0.38", "sect571k1");
			me.oitToName.put("1.3.132.0.39", "sect571r1");
 
		}
		return me;
	}
	
	
	/**
	 * Adds the oid to name mapping.
	 *
	 * @param oid the oid
	 * @param name the name
	 */
	public void addOidToNameMapping(String oid, String name) {
		oitToName.put(oid, name);
	}
	
	/**
	 * Gets the name.
	 *
	 * @param oid the oid
	 * @return the name
	 */
	public String getName(String oid) {
		return oitToName.get(oid);
	}
}
