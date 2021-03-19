/*
 * 
 */
package org.uic.barcode.ticket.api.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringIA5;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringUTF8;
import org.uic.barcode.ticket.EncodingFormatException;


/**
 * The Class UicEncoderUtils.
 */
public class UicEncoderUtils {
	
	

	/**
	 * Map to int.
	 *
	 * @param o the object to be mapped to an integer
	 * @return the int
	 */
	public static int mapToInt(Object o){

		if (o == null){
			return 0;
		}		

		if (o instanceof Integer) {
			return ((Integer)o).intValue();
		}		
		
		if (o instanceof Long) {
			return ((Long)o).intValue();
		}
		
		if (o instanceof String) {
			
			int i = 0;
			
			try {
				i = Integer.parseInt((String)o);
			} catch (NumberFormatException e) {
				return 0;
			}
			
			
			return i;
		}		
		
		int i = 0;
		
		try {
			i = Integer.parseInt(o.toString());
		} catch (NumberFormatException e) {
			return 0;
		}
		
		
		return i;		
		
	}
	
	/**
	 * Map to string.
	 *
	 * @param number the number
	 * @param text the text
	 * @return the string
	 */
	public static String mapToString(Asn1BigInteger number, String text) {
		if (text != null && text.length() > 0) {
			return text;
		} else {
			if (number != null){
				return number.value().toString();
			} else {
				return null;
			}
		}
	}
	
	/**
	 * Map to string.
	 *
	 * @param number the number
	 * @param text the text
	 * @return the string
	 */
	public static String mapToString(BigInteger number, String text) {
		if (text != null && text.length() > 0) {
			return text;
		} else {
			if (number != null){
				return number.toString();
			} else {
				return null;
			}
		}
	}
	
	/**
	 * Map to string.
	 *
	 * @param number the number
	 * @param text the text
	 * @return the string
	 */
	public static String mapToString(Integer number, String text) {
		
		if (text != null && text.length() > 0) {
			return text;
		} else {
			if (number != null){
				return number.toString();
			} else {
				return null;
			}
		}
	}

	/**
	 * Map to string.
	 *
	 * @param number the number
	 * @param text the text
	 * @return the string
	 */
	public static String mapToString(Long number, String text) {
		
		if (text != null && text.length() > 0) {
			return text;
		} else {
			if (number != null){
				return number.toString();
			} else {
				return null;
			}
		}
	}	
	



	/**
	 * Map to string.
	 *
	 * @param numbers the numbers
	 * @return the collection
	 */
	public static Collection<String> mapToString(Collection<Long> numbers) {
				
		Collection<String> list = new HashSet<String>();		
		
		if (numbers == null || numbers.isEmpty()) {
			return list;	
		}		
		
		for (Long number : numbers){
			list.add(number.toString());
		}

		return list;
	}




	/**
	 * Gets the num.
	 *
	 * @param text the text
	 * @return the num
	 */
	public static Long getNum(String text) {
		
		if (text == null || text.length() == 0) {
			return null;
		}
		
		Long i;
		try {
			i = Long.parseLong(text);
		} catch (NumberFormatException e) {
			return null;
		}
		return i;
	}
	
	/**
	 * Gets the num.
	 *
	 * @param text the text
	 * @return the num
	 */
	public static Asn1BigInteger getLargeNum(String text) {
		
		if (text == null || text.length() == 0) {
			return null;
		}
		
		BigInteger i;
		try {
			i = new BigInteger(text);
		} catch (NumberFormatException e) {
			return null;
		}
		return new Asn1BigInteger(i);
	}
	
	/**
	 * Gets the num list.
	 *
	 * @param list the list
	 * @return the num list
	 * @throws EncodingFormatException the encoding format exception
	 */
	public static List<Long> getNumList(Collection<String> list) throws EncodingFormatException {
		
		if (list== null || list.isEmpty()) {
			return null;
		}
		List<Long> numList = new ArrayList<Long>();
		
		for (String text : list){
			Long num = getNum (text);
			if (num != null) {
				numList.add(num);
			}
		}
		
		
		if (numList.isEmpty()) {
			return null;
		}
		
		return numList;		
	}

	/**
	 * Gets the i a5 non num list.
	 *
	 * @param list the list
	 * @return the i a5 non num list
	 * @throws EncodingFormatException the encoding format exception
	 */
	public static SequenceOfStringIA5 getIA5NonNumList(Collection<String> list) throws EncodingFormatException {
		
		if (list== null || list.isEmpty()) {
			return null;
		}
		SequenceOfStringIA5 ia5List = new SequenceOfStringIA5();
		
		for (String text : list){
			String ia5 = getIA5NonNum (text);
			if (ia5 != null && ia5.length() > 0) {
				ia5List.add(ia5);
			}
		}
		
		
		if (ia5List.isEmpty()) {
			return null;
		}
		
		return ia5List;		
	}
	
	
	
	
	/**
	 * Gets the i a5.
	 *
	 * @param text the text
	 * @return the i a5
	 * @throws EncodingFormatException the encoding format exception
	 */
	public static String getIA5(String text) throws EncodingFormatException {
		
		if (text == null || text.length() == 0) {
			return null;
		}		
		
		for (int i = 0; i < text.length(); i++){
			int index = text.charAt(i);
			if (index < 0 || index > 127) {
				throw new EncodingFormatException("Wrong Characters in IA5 String encoding");
			}
		}

		return text;

	}	

	/**
	 * Gets the i a5 non num.
	 *
	 * @param text the text
	 * @return the i a5 non num
	 * @throws EncodingFormatException the encoding format exception
	 */
	public static String getIA5NonNum(String text) throws EncodingFormatException {
		
		if (text == null || text.length() == 0) {
			return null;
		}		
		
		for (int i = 0; i < text.length(); i++){
			int index = text.charAt(i);
			if (index < 0 || index > 127) {
				throw new EncodingFormatException("Wrong Characters in IA5 String encoding");
			}
		}

		try {
			Long.parseLong(text);
			return null;
		} catch (NumberFormatException e) {
			return text;
		}		

	}

	/**
	 * Gets the restricted int.
	 *
	 * @param value the value
	 * @param min the min
	 * @param max the max
	 * @return the restricted int
	 * @throws EncodingFormatException the encoding format exception
	 */
	public static Long getRestrictedInt(int value, int min, int max) throws EncodingFormatException {
		if (value == 0) return null;
		
		if (value < min || value > max) {
			throw new EncodingFormatException("Integer value exceeds boundaries");
		}
		return new Long(value);
	}	
	
	/**
	 * Gets the restricted int with default.
	 *
	 * @param value the value
	 * @param min the min
	 * @param max the max
	 * @param defaultValue the default value
	 * @return the restricted int with default
	 * @throws EncodingFormatException the encoding format exception
	 */
	public static Long getRestrictedIntWithDefault(int value, int min, int max, int defaultValue) throws EncodingFormatException {
		if (value == defaultValue || value == 0) return null;
		
		if (value < min || value > max) {
			throw new EncodingFormatException("Integer value exceeds boundaries");
		}
		return new Long(value);
	}

	
	/**
	 * Gets the un restricted int.
	 *
	 * @param value the value
	 * @return the un restricted int
	 */
	public static Long getUnRestrictedInt(int value) {
		if (value == 0) return null;
		return new Long(value);
	}	

	/**
	 * Gets the un restricted int list.
	 *
	 * @param intList the int list
	 * @return the un restricted int list
	 */
	public static List<Long> getUnRestrictedIntList(	Collection<Integer> intList) {
		if (intList == null || intList.isEmpty()) return null;
		
		List<Long> list = new ArrayList<Long>();
		
		for (Integer i : intList){
			if (i != 0){
				list.add(i.longValue());
			}
		}
		
		if (list.isEmpty()) return null;
		return list;
	}
	
	/**
	 * Gets the restricted int list.
	 *
	 * @param intList the int list
	 * @param min the min
	 * @param max the max
	 * @return the restricted int list
	 * @throws EncodingFormatException the encoding format exception
	 */
	public static List<Integer> getRestrictedIntList(	Collection<Integer> intList, int min, int max) throws EncodingFormatException {
		if (intList == null || intList.isEmpty()) return null;
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (Integer i : intList){
			if (i != 0){
				
				if (i < min || i > max){
					throw new EncodingFormatException("Integer value exceeds boundaries");
				}
				
				list.add(i);
			}
		}
		
		if (list.isEmpty()) return null;
		return list;
	}
	
	/**
	 * Encode restricted integer collection.
	 *
	 * @param collection the collection
	 * @param min the min
	 * @param max the max
	 * @return the list
	 * @throws EncodingFormatException the encoding format exception
	 */
	public static List<Long> encodeRestrictedIntegerCollection(Collection<Integer> collection, int min, int max) throws EncodingFormatException {
		
		if ( collection == null ||  collection.isEmpty()) {
			return null;
		}
			
		List<Long> list = new ArrayList<Long>();
		for (Integer item : collection){
			Long listItem = getRestrictedInt(item, min, max);
			if (listItem != null){
				list.add(listItem);
			}
		}
		if (list.isEmpty()){
			return null;
		}
		return list;

	}	
	
	/**
	 * Encode integer collection.
	 *
	 * @param collection the collection
	 * @return the list
	 * @throws EncodingFormatException the encoding format exception
	 */
	public static SequenceOfStringUTF8 encodeStringCollection(Collection<String> collection) throws EncodingFormatException {
		
		if ( collection == null ||  collection.isEmpty()) {
			return null;
		}
			
		SequenceOfStringUTF8 list = new SequenceOfStringUTF8();
		for (String item : collection){
			if (item.length() > 0){
				list.add(item);
			}
		}
		if (list.isEmpty()){
			return null;
		}
		return list;

	}	
	
	/**
	 * Encode integer collection.
	 *
	 * @param collection the collection
	 * @return the list
	 * @throws EncodingFormatException the encoding format exception
	 */
	public static List<Long> encodeIntegerCollection(Collection<Integer> collection) throws EncodingFormatException {
		
		if ( collection == null ||  collection.isEmpty()) {
			return null;
		}
			
		List<Long> list = new ArrayList<Long>();
		for (Integer item : collection){
			if (item != null){
				list.add(item.longValue());
			}
		}
		if (list.isEmpty()){
			return null;
		}
		return list;

	}


}
