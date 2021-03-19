package org.uic.barcode.dynamicFrame;


import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;

/**
 * The Class SequenceOfDataType.
 */
public class SequenceOfDataType extends Asn1SequenceOf<DataType>{

	    /**
    	 * Instantiates a new sequence of data type.
    	 */
    	public SequenceOfDataType() { super(); }
	    
    	/**
    	 * Instantiates a new sequence of data type.
    	 *
    	 * @param coll the coll
    	 */
    	public SequenceOfDataType(Collection<DataType> coll) { super(coll); }

}
