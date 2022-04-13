package org.uic.barcode.utils;

import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import java.util.HashMap;

/**
 * The Class AlgorithmNameResolver.
 */
public class AlgorithmNameResolver {
	
	
	/** The Constant TYPE_KEY_GENERATOR_ALG. */
	public static final String TYPE_KEY_GENERATOR_ALG = "KeyPairGenerator";
	
	/** The Constant TYPE_SIGNATURE_ALG. */
	public static final String TYPE_SIGNATURE_ALG = "Signature";
	
	/** The map. */
	private static HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();
	
	
	/**
	 * Adds an entry for a mapping of algorithm type and oid to an algorithm name
	 *
	 * @param type the algorithm type
	 * @param oid the algorithm OID
	 * @param name the algorithm name
	 */
	public static void addMap (String type, String oid, String name) {
		
		if (map.get(type) == null) {
			HashMap<String,String> list = new HashMap<String , String>(); 
			list.put(oid,  name);
			map.put(type, list);
		} else {
			map.get(type).put(oid, name);
		}
	}
	
    /**
     * Gets the signature algorithm name.
     *
     * @param oid the oid
     * @return the signature algorithm name
     * @throws Exception the exception
     */
    public static String getSignatureAlgorithmName (String oid) throws Exception {
    	
    	return getSignatureAlgorithmName (oid, null);
    	
    }
    
    /**
     * Gets the signature algorithm name.
     *
     * @param oid the oid
     * @param provider the provider
     * @return the signature algorithm name
     * @throws Exception the exception
     */
    public static String getSignatureAlgorithmName (String oid, Provider provider) throws Exception {
    	
    	return getAlgorithmName(AlgorithmNameResolver.TYPE_SIGNATURE_ALG,oid,provider);
     	
    }
    
    /**
     * Gets the name.
     *
     * @param type the type
     * @param oid the oid
     * @return the name
     * @throws Exception the exception
     */
    public static String getName (String type, String oid) throws Exception {
    	
    	return getAlgorithmName(type,oid,null);
    	
    }
    	
    
    /**
     * Gets the name.
     *
     * @param type the type
     * @param oid the oid
     * @param provider the provider
     * @return the name
     * @throws Exception the exception
     */
    public static String getName(String type, String oid, Provider provider) throws Exception {
	   
	   return getAlgorithmName(type,oid,provider);
	
	}    
    
    
    
    /**
     * Gets the algorithm name.
     *
     * @param type the type
     * @param oid the oid
     * @param provider the provider
     * @return the algorithm name
     * @throws Exception the exception
     */
    public static String getAlgorithmName (String type, String oid, Provider provider) throws Exception {
    	
    	String name = null;
    	
    	if (provider != null) {
    		 Service service = provider.getService(type,oid);
    		 if (service != null) {
 		    	 name = service.getAlgorithm();
 		     }
    		 
    		 if (name != null && name.length() > 0) {
    			 return name;
    		 }
    	}
    	
    	
 		Provider[] provs = Security.getProviders();
 		for (Provider prov : provs) {
 		       Service service = prov.getService(type,oid);
 		       if (service != null) {
 		    	 name = service.getAlgorithm();
 		       }		    	    		    		 
 		       if (name != null && name.length() > 0) {
 		    	 return name;
 		       }
 		}
 		 		
 		if (map.get(type) != null) {
 			if (map.get(type).get(oid) != null) {
 				return map.get(type).get(oid);
 			}
 		}
 		
 		
		//fallback if the provider did not implement OIDs
		if (oid.startsWith("1.2.840.10045.4")) {
			return "ECDSA";
		} else if (oid.startsWith("1.2.840.10045.3")) {
			return "EC";
		} else if (oid.startsWith("1.2.840.10045")) {
			return "ECDSA";
		} else if (oid.startsWith("1.2.840.10040")) {
			return "DSA";
		}
 		
 		return null;
     	
     }
    
    
 
  


}
