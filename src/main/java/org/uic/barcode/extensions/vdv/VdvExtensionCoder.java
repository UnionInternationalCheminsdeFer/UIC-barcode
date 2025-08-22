package org.uic.barcode.extensions.vdv;

import org.uic.barcode.asn1.uper.Asn1EncodingException;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.extensions.IExtensionCoder;
import org.uic.barcode.ticket.api.spec.IExtension;

public class VdvExtensionCoder implements IExtensionCoder {


	public VdvExtensionData decode(byte[] bytes, String extensionId) throws Asn1EncodingException {
		return (VdvExtensionData) UperEncoder.decode(bytes,VdvExtensionData.class);
	}
	
	public VdvExtensionCoder clone() {
		return new VdvExtensionCoder();
	}

	@Override
	public byte[] encode(IExtension obj) throws Exception {
		return UperEncoder.encode(obj);
	}



}
