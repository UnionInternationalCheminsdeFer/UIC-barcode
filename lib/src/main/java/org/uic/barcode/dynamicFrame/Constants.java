package org.uic.barcode.dynamicFrame;

public class Constants {
	
	/*
	 * Object Identifier for recommended signature algorithms
	 * 
	 */
	public static String KG_EC_256 = "1.2.840.10045.3.1.7";
	public static String KG_EC = "1.2.840.10045.2.1";
	public static String ECDSA_SHA256 = "1.2.840.10045.4.3.2";
	
	public static String DSA_SHA1 = "1.2.840.10040.4.3";
	public static String DSA_SHA224 = "2.16.840.1.101.3.4.3.1";
	public static String DSA_SHA248 = "2.16.840.1.101.3.4.3.2";	
	
	public static String DATA_TYPE_FCB_VERSION_1 = "FCB1";
	public static String DATA_TYPE_FCB_VERSION_2 = "FCB2";	
	
	public static String DYNAMIC_BARCODE_FORMAT_DEFAULT = "U1";

	public static int LEVEL2_VALIDATION_OK = 0;	
	public static int LEVEL2_VALIDATION_NO_KEY = 1;
	public static int LEVEL2_VALIDATION_NO_SIGNATURE = 2;	
	public static int LEVEL2_VALIDATION_FRAUD = 3;
	public static int LEVEL2_VALIDATION_SIG_ALG_NOT_IMPLEMENTED = 4;	
	public static int LEVEL2_VALIDATION_KEY_ALG_NOT_IMPLEMENTED = 5;		
	public static int LEVEL2_VALIDATION_ENCODING_ERROR = 6;		
	
	public static int LEVEL1_VALIDATION_OK = 0;	
	public static int LEVEL1_VALIDATION_NO_KEY = 1;
	public static int LEVEL1_VALIDATION_NO_SIGNATURE = 2;	
	public static int LEVEL1_VALIDATION_FRAUD = 3;
	public static int LEVEL1_VALIDATION_SIG_ALG_NOT_IMPLEMENTED = 4;	
	public static int LEVEL1_VALIDATION_KEY_ALG_NOT_IMPLEMENTED = 5;		
	public static int LEVEL1_VALIDATION_ENCODING_ERROR = 6;	
	
}
