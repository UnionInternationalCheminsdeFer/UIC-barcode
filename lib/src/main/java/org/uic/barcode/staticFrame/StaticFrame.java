package org.uic.barcode.staticFrame;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.uic.barcode.ticket.EncodingFormatException;


/**
 * The Class StaticHeader implements the static bar code header frame defined in UIC IRS 90918-9. 
 * It allows to decode and encode the bar code content and to add sub-records as defined in the IRS 90918-9 for:
 *   - additional header data
 *   - Ticket Layout content
 *   - Flexible content 
 *   - bilateral data records 
 */
public class StaticFrame {
	
	/** The additional header record. */
	private UHEADDataRecord headerRecord;

	/** The bar code version. */
	private int version;
	
	/** The u_flex. */
	private UFLEXDataRecord uFlex;
	
	/** The u_tlay. */
	private UTLAYDataRecord uTlay;
	
	/** The security provider. */
	private String securityProvider; 
	
	/** The signature key. */
	private String signatureKey; 
	
	/** The signature. */
	private byte[] signature;	
	
	/** The data records. */
	private ArrayList<DataRecord> dataRecords = new ArrayList<DataRecord>();
	
	
	private byte[] signedData = null;
	
	/**
	 * Instantiates a new static header frame.
	 */
	public StaticFrame (){	}
	
	
	
	/**
	 * Instantiates a new static header and decodes the provided data.
	 *
	 * @param bytes the bar code data
	 * @throws EncodingFormatException the encoding format exception
	 * @throws DataFormatException the data format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public StaticFrame (byte[] bytes) throws EncodingFormatException, DataFormatException, IOException{
		decode(bytes);
	}
	

	/**
	 * Encode the barcode data.
	 *
	 * @param version the barcode version
	 * @return byte[] the encoded data as 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws Exception the exception
	 */
	/*
	 * creates a UIC bar code of version 1
	 * 
	 * limits:
	 *   - version 1 allows for signatures up to 50 byte length
	 *   - max data length 2048 Byte 
	 * input:
	 *    data to be included
	 *    provider of the signature
	 * processing:  
	 *    1. create header informations 
	 *    2. compression of the data content
	 *    3. adding a signature
	 * output:
	 *    raw data to be included in an aztec bar code
	 * 
	 */
	public byte[] encode() throws IOException, Exception {
		
		if (headerRecord == null && uFlex == null && uTlay == null 
				&& (dataRecords == null || dataRecords.isEmpty())) return null;
		
		if (signedData == null) {
			signedData = encodeData();
		}
		
		if (version != 1 && version != 2) {
			throw (new Exception(String.format("UIC Barcode Version %d not supported", version)));
		}
		
		if (signedData.length < 1) {
			throw new IOException("data missing!");
		}		
		if (signedData.length > 2048) {
			throw new IOException("too many data!"); //2048 should be enough  
		}				
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();  
		
		//UIC bar code version 1
		String header = "#UT01";
		if (version == 2) {
			header = "#UT02";
		}
		outputStream.write(header.getBytes());
		
		outputStream.write(securityProvider.getBytes());
		

		while (signatureKey.length() < 5) {
			signatureKey = "0" + signatureKey;
		}
		outputStream.write(signatureKey.getBytes());
		
		if (signature.length < 1) {
			// signature too small for bar code version 1
			throw new IOException("signature size too small!");
		}		
		
		if (version == 1) {
			if (signature.length > 50) {
				// signature too large for bar code version 1
				throw new IOException("signature size too large!");
			}
			outputStream.write(Arrays.copyOfRange(signature, 0, 50));
		} else if (version == 2) {
			BigInteger[] bInts = null;
			byte zeroByte = 0;

			bInts = decodeSignatureIntegerSequence(signature);
			byte[] r = toUnsignedBytes(bInts[0]);
			
			byte[] s = toUnsignedBytes(bInts[1]);
			
			if (r.length > 32 || s.length > 32) {
			   throw (new EncodingFormatException(String.format("DSA signature too big")));
			}
			for (int i = 0; i < 32 - r.length; i++) {
				outputStream.write(zeroByte);
			}
			outputStream.write(r);
			for (int i = 0; i < 32 - s.length; i++) {
				outputStream.write(zeroByte);
			}				
			outputStream.write(s);
			//outputStream.write(Arrays.copyOfRange(signature, 0, 64));
		}

		String length = String.format("%04d", signedData.length); 
		outputStream.write(length.getBytes());		
		
		outputStream.write(signedData);

		outputStream.close();			
 
		return outputStream.toByteArray();
	}
	

	/**
	 * Adds a proprietary data record.
	 *
	 * @param record the record
	 */
	public void addDataRecord(DataRecord record) {
		dataRecords.add(record);
	}

	/**
	 * Gets the version of the header frame.
	 *
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the version of the header frame.
	 * supported values are 1 and 2
	 *
	 * @param version the new version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Gets the security provider.
	 *
	 * @return the security provider
	 */
	public String getSecurityProvider() {
		return securityProvider;
	}

	/**
	 * Sets the security provider.
	 *
	 * @param securityProvider the new security provider
	 */
	public void setSecurityProvider(String securityProvider) {
		this.securityProvider = securityProvider;
	}

	/**
	 * Gets the signature key identifier.
	 *
	 * @return the signature key 
	 */
	public String getSignatureKey() {
		return signatureKey;
	}

	/**
	 * Sets the signature key identifier.
	 *
	 * @param signatureKey the new signature key
	 */
	public void setSignatureKey(String signatureKey) {
		this.signatureKey = signatureKey;
	}

	/**
	 * Gets the signature.
	 *
	 * @return the signature
	 */
	public byte[] getSignature() {
		return signature;
	}

	/**
	 * Sets the signature.
	 *
	 * @param signature the new signature
	 */
	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	/**
	 * Gets the additional header record.
	 *
	 * @return the header record
	 */
	public UHEADDataRecord getHeaderRecord() {
		return headerRecord;
	}

	/**
	 * Gets the list of bilateral data records.
	 *
	 * @return the data records
	 */
	public ArrayList<DataRecord> getDataRecords() {
		return dataRecords;
	}
	
	/**
	 * Gets the data for signing.
	 *
	 * @return the data to be signed
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	public byte[] getDataForSignature() throws IOException, EncodingFormatException {
		// data compression
		if (signedData != null) return signedData;
		
		Deflater deflater = new Deflater();  
		byte[] data = encodeData();
		deflater.setInput(data);  
	    ByteArrayOutputStream compressStream = new ByteArrayOutputStream(data.length);   
	    byte[] buffer = new byte[2048]; 
	    deflater.finish();
		while (!deflater.finished()) {  
			int count = deflater.deflate(buffer); // returns the number of result bytes
			compressStream.write(buffer, 0, count);   
		}  
		compressStream.close();

		return compressStream.toByteArray();
	}
    
	/**
	 * Get the encoded data for the bar code.
	 *
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws EncodingFormatException the encoding format exception
	 */
	private byte[] encodeData() throws IOException, EncodingFormatException {
		
		if (this.uFlex == null && this.uTlay == null && this.headerRecord == null && 
				(dataRecords == null || dataRecords.isEmpty())) return null;
		
    	ByteArrayOutputStream totalStream = new ByteArrayOutputStream();		
		 	    	    	
   		//encode header for layout
   		if (headerRecord != null) {
   			byte[] header = headerRecord.encode();
    			
   			if (header != null && header.length > 0) {
   				totalStream.write(header);
   			}    		
   		}
    	
   		//encode layout
   		if (uTlay != null) {
   			byte[] layout = uTlay.encode();
   			if (layout != null && layout.length > 0) {
   				totalStream.write(layout);
   			}    	    		
   		}    		
    		
   		if (uFlex != null) {
   			byte[] content = uFlex.encode();
   			if (content != null && content.length > 0){
   				totalStream.write(content);
   			}   			      		
   		}      	
    	
    	//third party content
   		for (DataRecord dataRecord : dataRecords){
    			
   			byte[] content = dataRecord.encode();
   			if (content != null && content.length > 0){
   				totalStream.write(content);
   			}  			
    	}
		return totalStream.toByteArray(); 
	}
	
	/**
	 * Encode signature integer sequence.
	 *  
	 *  Support function to format two parameters as DER encoded integer list 
	 *  to get a valid formated DSA signature from the signature parameter
	 *
	 * @param i1 the i 1
	 * @param i2 the i 2
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] encodeSignatureIntegerSequence(BigInteger i1, BigInteger i2) throws IOException {
		
		//SEQUENCE OF --> tag 16
		int sequenceTag = 16 + 32;  // (bits 6 = 1 constructed)
		//INTEGER     --> tag 2
		int integerTag = 2;
			
		byte[] b1 = i1.toByteArray();
        int lb1 = b1.length;
		byte[] b2 = i2.toByteArray();
        int lb2 = b2.length;		
        
        int sequenceLength = lb1 + lb2 + 4;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		out.write((byte) sequenceTag);
		out.write((byte) sequenceLength);   
		out.write((byte) integerTag);  
		out.write((byte) lb1);  
		out.write(b1);   
		out.write((byte) integerTag);  
		out.write((byte) lb2);  
		out.write(b2);   

		return out.toByteArray();
	}
	
	/**
	 * Decode signature integer sequence.
	 * 
	 * Support function to decode a DSA signature
	 * Provides the two DSA signature parameter encoded in a DSA signature
	 *
	 * @param bytes the bytes
	 * @return the big integer[]
	 * @throws Exception the exception
	 */
	public static BigInteger[] decodeSignatureIntegerSequence(byte[] bytes) throws Exception {

		int sequenceTag = (int) bytes[0];
		
		if (sequenceTag != 48) throw new Exception("signature is not a sequence");
		
		int sequenceLength = (int) bytes[1];
		
		if (sequenceLength < 6) throw new Exception("signature sequence too short");
		
		BigInteger[] result = new BigInteger[2];
		
		int offset = 2;
		int i = 0;
		while (offset < bytes.length && i < 2) {
			int integerTag = (int) bytes[offset];
			if (integerTag != 2) throw new Exception("signature is not an integer sequence");
			int integerLength = (int) bytes[offset + 1];
			byte[] value = Arrays.copyOfRange(bytes, offset + 2, offset + 2 + integerLength);		
			result[i] = new BigInteger(+1, value);
			offset = offset + integerLength + 2;
			i++;
		}

		return result;
	}
	
	/**
	 * Decode.
	 *
	 * @param inputData the input data
	 * @throws EncodingFormatException the encoding format exception
	 * @throws DataFormatException the data format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void decode(byte[] inputData) throws EncodingFormatException, DataFormatException, IOException {
		
		
		int offset = 0;
		String  headerTag = new String( Arrays.copyOfRange(inputData,offset,offset + 3));
		offset = offset + 3;
		if (!headerTag.equals("#UT")) {
			throw (new EncodingFormatException("not a UIC barcode"));
		}

		
		String versionValue = new String(Arrays.copyOfRange(inputData,offset,offset + 2));
		offset = offset + 2;
		int barcodeVersion = 0;
		try {
			barcodeVersion = Integer.parseInt(versionValue);
			this.setVersion(barcodeVersion);
		} catch (NumberFormatException e2) {
			throw (new EncodingFormatException(String.format("UIC Barcode Version %s not supported", versionValue)));
		}

		String providerValue   = new String( Arrays.copyOfRange(inputData,offset,offset + 4));
		this.setSecurityProvider(providerValue);		
		offset = offset + 4;
		
		String signatureKeyIdValue   = new String( Arrays.copyOfRange(inputData,offset,offset + 5));
		this.setSignatureKey(signatureKeyIdValue);
		offset = offset + 5;	
		
		byte[] sealdata = null;
		
		if (barcodeVersion == 1) {
			sealdata =  Arrays.copyOfRange(inputData, offset, offset + 50);
			signature = trimDsaSignature(sealdata);
			offset = offset + 50;	
		} else if (barcodeVersion == 2) {
			sealdata =  Arrays.copyOfRange(inputData, offset, offset + 64);
			signature = recombineDsaSignature(sealdata);
			offset = offset + 64;				
		} else {
			throw (new EncodingFormatException(String.format("UIC Barcode Version %s not supported", versionValue)));
		}


		String lengthValue   = new String( Arrays.copyOfRange(inputData,offset,offset + 4));
		offset = offset + 4;		
		
		int dataLength = 0;
		dataLength = Integer.parseInt(lengthValue);
		
		signedData = Arrays.copyOfRange(inputData, offset,  offset + dataLength);

		ByteBuffer containedDataBuffer = ByteBuffer.allocate(dataLength);
		containedDataBuffer.put(signedData);
		
		byte[] inflatedDataBuffer = new byte[2000];	
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();  
		Inflater inflater = new Inflater();
		byte[] inflaterInput = containedDataBuffer.array();
		inflater.setInput(inflaterInput);
		while (!inflater.finished()) {  
			int count = inflater.inflate(inflatedDataBuffer,0,2000);
			if (inflater.needsDictionary()) {
				break;
			}  
		    outputStream.write(inflatedDataBuffer, 0, count);  
	    } 

		outputStream.close();

		byte[] byteData  = outputStream.toByteArray();
		
		offset = 0;
		int remainingBytes = byteData.length;

		while (remainingBytes > 0) {
		
			String tag = new String(Arrays.copyOfRange(byteData, offset, offset + 6));
			int length = 0;
			
			if (tag.startsWith("U_TLAY")) {
				UTLAYDataRecord record = new UTLAYDataRecord();
				length = record.decode(Arrays.copyOfRange(byteData, offset, byteData.length));
				this.uTlay = record;			
			} else if (tag.startsWith("U_FLEX")) {
				UFLEXDataRecord record = new UFLEXDataRecord();
				length = record.decode(Arrays.copyOfRange(byteData, offset, byteData.length));
				this.uFlex = record;			
			} else if (tag.startsWith("U_HEAD")) {
				UHEADDataRecord record = new UHEADDataRecord();
				length = record.decode(Arrays.copyOfRange(byteData, offset, byteData.length));
				this.headerRecord = record;
			} else {
				DataRecord record = new GENERICDataRecord(tag);
				length = record.decode(Arrays.copyOfRange(byteData, offset, byteData.length));
				addDataRecord(record);
			}
			offset = offset + length;
			remainingBytes = remainingBytes - length;						
		}	
	}

	
	private byte[] recombineDsaSignature(byte[] sealdata) throws IOException {
		
		//check whether the encoding is wrong and the sealdata contain a signature
		//remove trailing zeroes from the signature
		BigInteger[] bInts = null;
		try {
			bInts = decodeSignatureIntegerSequence(sealdata);
			byte[] sig = encodeSignatureIntegerSequence(bInts[0],bInts[1]);
			//decoding the entire signature was ok, so there was no split
			return sig;
		} catch (Exception e) {
			//the signature is correctly implemented, continue with recombination
		}

		// split the data into two blocks
		int length = sealdata.length / 2;
		byte[] rBytes = Arrays.copyOfRange(sealdata,      0, length);
		byte[] sBytes = Arrays.copyOfRange(sealdata, length, length + length);	
		
		//convert to BigInteger to get rid of leading zeroes
		BigInteger r = new BigInteger(1,rBytes);
		BigInteger s = new BigInteger(1,sBytes);	
		
		//encode as DSA signature structure
		//SEQUENCE OF --> tag 16
		int sequenceTag = 16 + 32;  // (bits 6 = 1 constructed)
		//INTEGER     --> tag 2
		int integerTag = 2;
		
		byte[] b1 = r.toByteArray();
        int lb1 = b1.length;     

        byte[] b2 = s.toByteArray();	
        int lb2 = b2.length;		
        int sequenceLength = lb1 + lb2 + 4;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte) sequenceTag);
		out.write((byte) sequenceLength);   
		out.write((byte) integerTag);  
		out.write((byte) lb1);  
		out.write(b1);   
		out.write((byte) integerTag);  
		out.write((byte) lb2);  
		out.write(b2);   
		return out.toByteArray();		
		

	}

	private static byte[] toUnsignedBytes(BigInteger i) {
		byte[] b = i.abs().toByteArray();
		//remove top sign bit  
		if (b[0] == 0) {
			b = Arrays.copyOfRange(b, 1, b.length);
		}
		return b;
	}


	private byte[] trimDsaSignature(byte[] sealdata) throws EncodingFormatException {
		//remove trailing zeroes from the signature
		BigInteger[] bInts = null;
		try {
			bInts = decodeSignatureIntegerSequence(sealdata);
			return encodeSignatureIntegerSequence(bInts[0],bInts[1]);
		} catch (Exception e) {
			throw (new EncodingFormatException(String.format("Invalid DSA signature")));
		}
	}



	/**
	 * Verify the signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @param algo the algorithm name
	 * @return true, if successful
	 * @throws InvalidKeyException the invalid key exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws SignatureException the signature exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws UnsupportedOperationException the unsupported operation exception
	 * @throws EncodingFormatException 
	 * @throws IOException 
	 */
	public boolean ByAlgorithmName(PublicKey key, String algo) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, IllegalArgumentException, UnsupportedOperationException, IOException, EncodingFormatException {
		Signature sig = Signature.getInstance(algo);
		sig.initVerify(key);
		sig.update(this.getDataForSignature());
		return sig.verify(this.getSignature());
	}
	
	/**
	 * Verify the signature
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @param singningAlg the Object ID of the signing algorithm
	 * @return true, if successful
	 * @throws InvalidKeyException the invalid key exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws SignatureException the signature exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws UnsupportedOperationException the unsupported operatign exception
	 * @throws EncodingFormatException 
	 * @throws IOException 
	 */
	public boolean verifyByAlgorithmOid(PublicKey key, String signingAlg) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, IllegalArgumentException, UnsupportedOperationException, IOException, EncodingFormatException {
		//find the algorithm name for the signature OID
		String algo = null;
		Provider[] provs = Security.getProviders();
		for (Provider prov : provs) {
	       Service service = prov.getService("Signature",signingAlg);
	       if (service != null) {
	    	   algo = service.getAlgorithm();
	       }
		}
		Signature sig = Signature.getInstance(algo);
		sig.initVerify(key);
		sig.update(getDataForSignature());
		return sig.verify(this.getSignature());
	}
	
	/**
	 * Sign the contained data block.
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @param singningAlg the Object ID of the signing algorithm
	 * @return 
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws SignatureException the signature exception
	 * @throws EncodingFormatException 
	 * @throws IOException 
	 */
	public void signByAlgorithmOID(PrivateKey key,String signingAlg) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException, EncodingFormatException {
		//find the algorithm name for the signature OID
		String algo = null;
		Provider[] provs = Security.getProviders();
		for (Provider prov : provs) {
	       Service service = prov.getService("Signature",signingAlg);
	       if (service != null) {
	    	   algo = service.getAlgorithm();
	       }
		}
		Signature sig = Signature.getInstance(algo);
		sig.initSign(key);
		signedData = getDataForSignature();
		sig.update(signedData);
		signature = sig.sign();
	}
	
	/**
	 * Sign the contained data block.
	 * 
	 * Note:  an appropriate security provider (e.g. BC) must be registered before 
	 *
	 * @param key the key
	 * @param algo the name of the signing algorithm
	 * @return 
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws SignatureException the signature exception
	 * @throws EncodingFormatException 
	 * @throws IOException 
	 */
	public void signUsingAlgorithmName(PrivateKey key,String algo) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException, EncodingFormatException {
		Signature sig = Signature.getInstance(algo);
		sig.initSign(key);
		sig.update(getDataForSignature());
		signature = sig.sign();
	}



	public UFLEXDataRecord getuFlex() {
		return uFlex;
	}



	public UTLAYDataRecord getuTlay() {
		return uTlay;
	}



	public void setuFlex(UFLEXDataRecord uFlex) {
		this.uFlex = uFlex;
	}



	public void setuTlay(UTLAYDataRecord uTlay) {
		this.uTlay = uTlay;
	}



	public void setHeaderRecord(UHEADDataRecord headerRecord) {
		this.headerRecord = headerRecord;
	}	
	
	
	
	
}
