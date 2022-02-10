package org.uic.barcode.utils;

import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;

public class AlgorithmNameResolver {
	
	
	public static final String TYPE_KEY_GENERATOR_ALG = "KeyPairGenerator";
	public static final String TYPE_SIGNATURE_ALG = "Signature";
	
	
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
    
    public static String getSignatureAlgorithmName (String oid, Provider provider) throws Exception {
    	
    	if (provider != null) {
    		 Service service = provider.getService(AlgorithmNameResolver.TYPE_SIGNATURE_ALG,oid);
    		 return service.getAlgorithm();
    	}
    	
    	
 		Provider[] provs = Security.getProviders();
 		for (Provider prov : provs) {
 		       Service service = prov.getService(AlgorithmNameResolver.TYPE_SIGNATURE_ALG,oid);
 		       if (service != null) {
	    		   return service.getAlgorithm();
 		       }
 		}
 		return null;
     	
     }
    
    
    public static String getName (String type, String oid) throws Exception {
    	
		Provider[] provs = Security.getProviders();
		for (Provider prov : provs) {
		       Service service = prov.getService(type,oid);
		       if (service != null) {
		    	   return service.getAlgorithm();
		       }
		}
		
		if (oid.startsWith("1.2.840.10045")) {
			return "ECDSA";
		} else if (oid.startsWith("1.2.840.10040")) {
			return "DSA";
		}

		return null;
    	
    }
    	
    
   public static String getName(String type, String oid, Provider provider) throws Exception {
	   
	   Service service = null;
	   if (provider == null) {
		   
			Provider[] provs = Security.getProviders();
			for (Provider prov : provs) {
			       service = prov.getService(type,oid);
			}	   
		   
	   } else {
		   service = provider.getService(type,oid);
	   }
	   
	   
       	
       	if (service != null) {
    	   return service.getAlgorithm();
       	}
    	       
		if (oid.startsWith("1.2.840.10045")) {
			return "ECDSA";
		} else if (oid.startsWith("1.2.840.10040")) {
			return "DSA";
		}
		
        return null;
	        	
	}    
    
  


}
