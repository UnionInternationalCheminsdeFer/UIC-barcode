package org.uic.barcode.asn1.uper;

import java.util.Arrays;
import java.util.Collection;

import org.uic.barcode.asn1.datatypes.Asn1SequenceOf;
import org.uic.barcode.asn1.datatypes.FixedSize;


public class SeqOfFixedSize {
        @FixedSize(3)
        public static class Bar extends Asn1SequenceOf<Byte> {
            public Bar(Byte... coll)          { this(Arrays.asList(coll)); }
            public Bar(Collection<Byte> coll) { super(coll); }
        }


}
