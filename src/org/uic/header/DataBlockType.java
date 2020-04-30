package org.uic.header;

import net.gcdc.asn1.datatypes.Asn1Optional;
import net.gcdc.asn1.datatypes.CharacterRestriction;
import net.gcdc.asn1.datatypes.IntRange;
import net.gcdc.asn1.datatypes.RestrictedString;
import net.gcdc.asn1.datatypes.Sequence;

/**
 * The Class DataBlockType.
 */
@Sequence
public class DataBlockType {
	
	
		/** The data. */
		public SequenceOfDataType data;
		
		/** 
		 * The signing algorithm
		 * Object Identifier of the Algorithm
		 * Number notation:
		 * 
		 *  e.g.:
		 *        	--    DSA SHA224  2.16.840.1.101.3.4.3.1
      	 *          --    DSA SHA248  2.16.840.1.101.3.4.3.2
      	 *          --    ECC         1.2.840.10045.3.1.7
		 *  
		 *  
		 */
		@RestrictedString(CharacterRestriction.ObjectIdentifier)
		@Asn1Optional public String signingAlg;
		
		/** The key id. */
		@IntRange(minValue=1,maxValue=1024)
		@Asn1Optional public Long	keyId;

		/**
		 * Gets the data.
		 *
		 * @return the data
		 */
		public SequenceOfDataType getData() {
			return data;
		}

		/**
		 * Sets the data.
		 *
		 * @param data the new data
		 */
		public void setData(SequenceOfDataType data) {
			this.data = data;
		}

		/**
		 * Gets the signing algorithm
		 * 
		 * Object Identifier of algorithm Algorithm
		 * Number notation:
		 * 
		 *  e.g.:
		 *        	--    DSA SHA224  2.16.840.1.101.3.4.3.1
      	 *          --    DSA SHA248  2.16.840.1.101.3.4.3.2
      	 *          --    ECC         1.2.840.10045.3.1.7
		 *
		 * @return the signing alg
		 */
		public String getSigningAlg() {
			return signingAlg;
		}

		/**
		 * Sets the signing algorithm
		 * 
		 * Object Identifier of the Algorithm
		 * Number notation:
		 * 
		 *  e.g.:
		 *        	--    DSA SHA224  2.16.840.1.101.3.4.3.1
      	 *          --    DSA SHA248  2.16.840.1.101.3.4.3.2
      	 *          --    ECC         1.2.840.10045.3.1.7 
		 *
		 * @param signingAlg the new signing algorithm
		 */
		public void setSigningAlg(String signingAlgorithm) {
			this.signingAlg = signingAlgorithm;
		}

		/**
		 * Gets the key id.
		 *
		 * @return the key id
		 */
		public Long getKeyId() {
			return keyId;
		}

		/**
		 * Sets the key id.
		 *
		 * @param keyId the new key id
		 */
		public void setKeyId(Long keyId) {
			this.keyId = keyId;
		}
		
		
		  
}
