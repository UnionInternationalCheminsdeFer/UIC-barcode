package org.uic.barcode.asn1.uper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.uic.barcode.asn1.datatypes.FixedSize;
import org.uic.barcode.asn1.datatypes.SizeRange;
import org.uic.barcode.asn1.uper.SimpleTypeResolver.Unknown;


class SeqOfCoder implements Decoder, Encoder {

    @Override public <T> boolean canEncode(T obj, Annotation[] extraAnnotations) {
        return obj instanceof List<?>;
    }

    @Override public <T> void encode(BitBuffer bitbuffer, T obj, Annotation[] extraAnnotations) throws Asn1EncodingException {
        Class<?> type = obj.getClass();
        UperEncoder.logger.debug(String.format("SEQUENCE OF %s",obj.getClass().getName()));
        AnnotationStore annotations = new AnnotationStore(type.getAnnotations(), extraAnnotations);
        List<?> list = (List<?>) obj;

        final FixedSize fixedSize = annotations.getAnnotation(FixedSize.class);

        //CG pass annotations too each field encoding
        Annotation[] annotationArray = new Annotation[] {};
        if (annotations != null & annotations.getAnnotations() != null && !annotations.getAnnotations().isEmpty()) {
        	ArrayList<Annotation> fieldAnnotations = new ArrayList<Annotation>();
          	fieldAnnotations.addAll(annotations.getAnnotations());
            annotationArray = new Annotation[fieldAnnotations.size()];
            for (int i = 0; i< fieldAnnotations.size();i++){
            	annotationArray[i] = fieldAnnotations.get(i);
            }  
        } 

        SizeRange sizeRange = annotations.getAnnotation(SizeRange.class);
        if (fixedSize != null)
            sizeRange = new SizeRange() {
                @Override public Class<? extends Annotation> annotationType() { return SizeRange.class; }
                @Override public int minValue() { return fixedSize.value(); }
                @Override public int maxValue() { return fixedSize.value(); }
                @Override public boolean hasExtensionMarker() { return false; }
            };
        if (sizeRange == null) {
            int position1 = bitbuffer.position();
            try {
                UperEncoder.encodeLengthDeterminant(bitbuffer, list.size());
            } catch (Asn1EncodingException e) {
                throw new Asn1EncodingException(" number of elements ", e);
            }
            UperEncoder.logger.debug(String.format("unbound size %d, encoded as %s", list.size(),
                    bitbuffer.toBooleanStringFromPosition(position1)));
            UperEncoder.logger.debug(String.format("  all elems of Seq Of: %s", list ));
            for (Object elem : list) {
                try {
               		UperEncoder.encode2(bitbuffer, elem, annotationArray);
                } catch (Asn1EncodingException e) {
                    throw new Asn1EncodingException(" element " + elem.toString(), e);
                }
            }
            return;
        }
        boolean outsideOfRange = list.size() < sizeRange.minValue()
                || sizeRange.maxValue() < list.size();
        if (outsideOfRange && !sizeRange.hasExtensionMarker()) { throw new IllegalArgumentException(
                "Out-of-range size for " + obj.getClass() + ", expected " +
                        sizeRange.minValue() + ".." + sizeRange.maxValue() + ", got "
                        + list.size()); }
        if (sizeRange.hasExtensionMarker()) {
            bitbuffer.put(outsideOfRange);
            UperEncoder.logger.debug(String.format("With Extension Marker, %s of range (%d <= %d <= %d)",
                    (outsideOfRange ? "outside" : "inside"), sizeRange.minValue(), list.size(),
                    sizeRange.maxValue()));
            if (outsideOfRange) { throw new UnsupportedOperationException(
                    "Sequence-of size range extensions are not implemented yet, range " +
                            sizeRange.minValue() + ".." + sizeRange.maxValue()
                            + ", requested size " + list.size()); }
        }
        UperEncoder.logger.debug(String.format("seq-of of constrained size %d, encoding size...", list.size()));
        UperEncoder.encodeConstrainedInt(bitbuffer, list.size(), sizeRange.minValue(), sizeRange.maxValue());
        UperEncoder.logger.debug(String.format("  all elems of Seq Of: %s", list));
        for (Object elem : list) {
            UperEncoder.encode2(bitbuffer, elem, new Annotation[] {});
        }
    }

    @Override public <T> boolean canDecode(Class<T> classOfT, Annotation[] extraAnnotations) {
        return List.class.isAssignableFrom(classOfT);
    }

    @SuppressWarnings("unchecked")
	@Override public <T> T decode(BitBuffer bitbuffer,
            Class<T> classOfT,Field field,
            Annotation[] extraAnnotations)  {
        AnnotationStore annotations = new AnnotationStore(classOfT.getAnnotations(),
                extraAnnotations);
        UperEncoder.logger.debug(String.format("SEQUENCE OF for %s", classOfT));
        FixedSize fixedSize = annotations.getAnnotation(FixedSize.class);
        SizeRange sizeRange = annotations.getAnnotation(SizeRange.class);

        //CG pass annotations from the sequence to each element encoding
        Annotation[] annotationArray = new Annotation[] {};
        
        if (annotations != null && annotations.getAnnotations() != null && !annotations.getAnnotations().isEmpty()){
        	annotationArray = new Annotation[annotations.getAnnotations().size()];
        	Iterator<Annotation> it = annotations.getAnnotations().iterator();
        	int i = 0;
        	while (it.hasNext()) {
        		annotationArray[i] = it.next();
        		i++;
        	}
        }    	
       	
        
        long size =
          (fixedSize != null) ? fixedSize.value() :
              (sizeRange != null) ? UperEncoder.decodeConstrainedInt(bitbuffer, UperEncoder.intRangeFromSizeRange(sizeRange)) :
                  UperEncoder.decodeLengthDeterminant(bitbuffer);
        Collection<Object> coll = new ArrayList<Object>((int) size);
        
        Class<?> classOfElements;
        Class<?>[] typeArgs = SimpleTypeResolver.resolveRawArguments(List.class, classOfT);
        classOfElements = typeArgs[0];
        if (classOfElements == null || classOfElements == Unknown.class) {
        	try {
        		ParameterizedType elementType = (ParameterizedType) field.getGenericType();
        		classOfElements = (Class<?>) elementType.getActualTypeArguments()[0];
        	} catch (SecurityException e) {
        		throw new IllegalArgumentException("Can't resolve type of elements for " + classOfT.getName()); 
        	}
        }        
        for (int i = 0; i < size; i++) {
            coll.add(UperEncoder.decodeAny(bitbuffer, classOfElements,field,  annotationArray));
        }
        
        T result = null;
        try {
        	result = UperEncoder.instantiate(classOfT, coll);
        } catch (Exception e) {
        	result = (T) coll;
        }
        return result;

    }

    
	@Override
	public <T> T getDefault(Class<T> classOfT, Annotation[] extraAnnotations) {
		throw new IllegalArgumentException("Default Sequence not yet implemented");
	}
}