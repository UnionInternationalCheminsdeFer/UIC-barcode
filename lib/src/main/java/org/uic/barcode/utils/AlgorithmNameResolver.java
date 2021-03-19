package org.uic.barcode.utils;

import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlgorithmNameResolver {
	
	
	public static final String TYPE_KEY_GENERATOR_ALG = "KeyPairGenerator";
	public static final String TYPE_SIGNATURE_ALG = "Signature";
	
	
    private static final Pattern KEY_TYPE_PATTERN = Pattern.compile("^(\\w+)[.].*$");
    private static final Pattern KEY_ALIAS_TYPE_PATTERN = Pattern.compile("^Alg[.]Alias[.](\\w+).*$");
    private static final Pattern KEY_OID_PATTERN = Pattern.compile(".*?(\\d+(?:[.]\\d+){3,})$");

    public static String getSignatureAlgorithmName (String oid) throws Exception {
    	
		Provider[] provs = Security.getProviders();
		for (Provider prov : provs) {
		       Service service = prov.getService(AlgorithmNameResolver.TYPE_SIGNATURE_ALG,oid);
		       if (service != null) {
		    	   return service.getAlgorithm();
		       }
		}
		return null;
    	
    }
    	

    public static String getName(String type, String oid) throws Exception {
    	
    	Provider[] provs = Security.getProviders();

        for (Provider prov : provs) {
	        	
	       		SortedSet<String> typeAndOID = getTypeAndOIDStrings(prov);

	       		for (String entry : typeAndOID) {
	       			String[] typeAndOIDArray = entry.split("-");
	       			String ptype = typeAndOIDArray[0];   
	       			if (ptype.equalsIgnoreCase(type)) {
		       			String poid = typeAndOIDArray[1];
		       			Service pservice = prov.getService(ptype, poid);
		       			String palgo = pservice.getAlgorithm();	

	       				if (ptype.equalsIgnoreCase(type) && poid.equals(oid)) {
	       					return palgo;
	       				}
	       			}
	       		}
        }
        
        
		if (oid.startsWith("1.2.840.10045")) {
			return "ECDSA";
		} else if (oid.startsWith("1.2.840.10040")) {
			return "DSA";
		}
		
        return null;
	        	
	}    
    
    public static String getName(String type, String oid, String providerName) throws Exception {
	
    	Provider[] provs = Security.getProviders();

        for (Provider prov : provs) {
	        	
	       	if (providerName == null || prov.getName().equals(providerName)) {

	       		SortedSet<String> typeAndOID = getTypeAndOIDStrings(prov);

	       		for (String entry : typeAndOID) {
	       			String[] typeAndOIDArray = entry.split("-");
	       			String ptype = typeAndOIDArray[0];   
	       			if (ptype.equalsIgnoreCase(type)) {
		       			String poid = typeAndOIDArray[1];
		       			Service pservice = prov.getService(ptype, poid);
		       			String palgo = pservice.getAlgorithm();	

	       				if (ptype.equalsIgnoreCase(type) && poid.equals(oid)) {
	       					return palgo;
	       				}
	       			}
	       		}
	       	}
        }
        
        
		if (oid.startsWith("1.2.840.10045")) {
			return "ECDSA";
		} else if (oid.startsWith("1.2.840.10040")) {
			return "DSA";
		}
		
        return null;
	        	
	}

    private static SortedSet<String> getTypeAndOIDStrings(Provider prov) {
    	
    	SortedSet<String> typeAndOID = new TreeSet<>();

    	Set<Object> keys = prov.keySet();
    	for (Object key : keys) {
    		String keyString = key.toString();
    		Matcher oidMatcher = KEY_OID_PATTERN.matcher(keyString);
    		if (oidMatcher.matches()) {
    			// get OID from matched keyString
    			String oid = oidMatcher.group(1);
    			
    			// determine type
    			String type;
    			Matcher aliasTypeMatcher = KEY_ALIAS_TYPE_PATTERN.matcher(keyString);
    			if (aliasTypeMatcher.matches()) {
                    type = aliasTypeMatcher.group(1);
                } else {
                    Matcher typeMatcher = KEY_TYPE_PATTERN.matcher(keyString);
                    typeMatcher.matches();
                    type = typeMatcher.group(1);
                }
                // algorithm parameters are not algorithms, so skip them
                if (type.equals("AlgorithmParameters")) {
                    continue;
                }

                // auto-removes dupes
                typeAndOID.add(type + "-" + oid);
            }
        }
        return typeAndOID;
	}
    

    public static String getSecurityNames() throws Exception {
    	
        	Provider[] provs = Security.getProviders();
        	
        	StringBuilder sb = new StringBuilder();

            for (Provider prov : provs) {

   	       		SortedSet<String> typeAndOID = getTypeAndOIDStrings(prov);

   	       		for (String entry : typeAndOID) {
   	       			String[] typeAndOIDArray = entry.split("-");
   	       			String ptype = typeAndOIDArray[0];   
   	       			String poid = typeAndOIDArray[1];
   		       		Service pservice = prov.getService(ptype, poid);
   		       	    String palgo = "";
   		       		if ( pservice != null) {
   		       			palgo = pservice.getAlgorithm();	
   		       		}
   		       		sb.append(prov).append(";");
   		       		sb.append(ptype).append(";");
   		       		sb.append(poid).append(";");
   		       		sb.append(palgo).append(";");
   		       		sb.append("\r\n");

    	       	}
            }
            return sb.toString();
    	        	
    	}
}
