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
			
			me.oitToName.put("1.3.132.0.15", "sect163r2");
			me.oitToName.put("1.3.132.0.33", "secp224r1");
			me.oitToName.put("1.3.132.0.26", "sect233k1");
			me.oitToName.put("1.3.132.0.27", "sect233r1");
			me.oitToName.put("1.3.132.0.16", "sect283k1");
			me.oitToName.put("1.3.132.0.17", "sect283r1");
			me.oitToName.put("1.3.132.0.34", "secp384r1");
			me.oitToName.put("1.3.132.0.36", "sect409k1");
			me.oitToName.put("1.3.132.0.37", "sect409r1");
			me.oitToName.put("1.3.132.0.35", "secp521r1");
			me.oitToName.put("1.3.132.0.38", "sect571k1");
			me.oitToName.put("1.3.132.0.39", "sect571r1");
			me.oitToName.put("1.3.132.0.10", "secp256k1");

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
