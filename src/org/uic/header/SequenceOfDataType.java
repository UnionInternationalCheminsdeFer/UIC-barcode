package org.uic.header;


import java.util.Collection;

import net.gcdc.asn1.datatypes.Asn1SequenceOf;
// TODO: Auto-generated Javadoc

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
