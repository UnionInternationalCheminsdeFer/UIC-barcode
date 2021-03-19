package org.uic.barcode;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.Test;
import org.uic.barcode.staticFrame.StaticFrame;
import org.uic.barcode.utils.TestUtils;
import org.uic.barcode.ticket.EncodingFormatException;

public class SignatureSplitTest {
	
	
	/*
	 * 
	 * 
	 *  	World-Schema DEFINITIONS AUTOMATIC TAGS ::= 
				BEGIN
  					Signature ::= SEQUENCE OF INTEGER                                                   
				END


			value Signature ::= {
  				340282366920938463,
  				134515671861986
			}
				
				
			Encoding using DER encoding rule
			Signature SEQUENCE OF: tag = [UNIVERSAL 16] constructed; length = 18
  				INTEGER: tag = [UNIVERSAL 2] primitive; length = 8
    				340282366920938463
  				INTEGER: tag = [UNIVERSAL 2] primitive; length = 6
    				134515671861986
			Encoded successfully in 20 bytes:  30120208 04B8ED02 83A6D3DF 02067A57 5ED68AE2 
				

	 * 
	 * 
	 */
	
	
	@Test public void testSplitSignature() throws IOException, EncodingFormatException{
		
		BigInteger i1 = BigInteger.valueOf(340282366920938463L);
		BigInteger i2 = BigInteger.valueOf(134515671861986L);
		
		byte[] encoded = StaticFrame.encodeSignatureIntegerSequence(i1,i2);

		
		String hex = TestUtils.hexStringFromBytes(encoded);
		
		assertEquals(hex,"3012020804B8ED0283A6D3DF02067A575ED68AE2");
		
		BigInteger[] ints = null;
		try {
			ints = StaticFrame.decodeSignatureIntegerSequence(encoded);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assert(ints != null);
		
		assert(ints.length == 2);
		
		assert(i1.equals(ints[0]));
		
		assert(i2.equals(ints[1]));
		
	}


}