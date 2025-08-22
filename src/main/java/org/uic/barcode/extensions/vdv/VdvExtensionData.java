package org.uic.barcode.extensions.vdv;

import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.asn1.datatypes.Asn1Optional;
import org.uic.barcode.asn1.datatypes.FieldOrder;
import org.uic.barcode.asn1.datatypes.HasExtensionMarker;
import org.uic.barcode.asn1.datatypes.IntRange;
import org.uic.barcode.asn1.datatypes.Sequence;
import org.uic.barcode.extensions.ExtensionRegistry;
import org.uic.barcode.extensions.IExtensionCoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.api.spec.IExtension;

@Sequence
@HasExtensionMarker
public class VdvExtensionData implements IExtension {

	    public VdvExtensionData() {}
		
		@FieldOrder(order = 0)
		@IntRange(minValue=1,maxValue=32000)
		@Asn1Optional 
		public Long productOwnerNum;

		@FieldOrder(order = 4)
		@Asn1Optional 
		public Asn1BigInteger externalIssuerId;

		@FieldOrder(order = 5)
		@Asn1Optional 
		public Asn1BigInteger issuerAutorizationId;

		public Long getProductOwnerNum() {
			return productOwnerNum;
		}

		public void setProductOwnerNum(Long productOwnerNum) {
			this.productOwnerNum = productOwnerNum;
		}

		public Asn1BigInteger getExternalIssuerId() {
			return externalIssuerId;
		}

		public void setExternalIssuerId(Asn1BigInteger externalIssuerId) {
			this.externalIssuerId = externalIssuerId;
		}

		public Asn1BigInteger getIssuerAutorizationId() {
			return issuerAutorizationId;
		}

		public void setIssuerAutorizationId(Asn1BigInteger issuerAutorizationId) {
			this.issuerAutorizationId = issuerAutorizationId;
		}

		@Override
		public String getId() {
			return "vdv01";
		}

		@Override
		public void setId(String id) {
			// 
		}

		@Override
		public byte[] getBinarydata() {
			IExtensionCoder coder = ExtensionRegistry.getInstance().getCoder(this.getId()).clone();
			try {
				return coder.encode(this);
			} catch (Exception e) {
				LoggerFactory.getLogger("extensionLogger").debug(e.getMessage());
				return null;
			}
		}

		@Override
		public void setBinarydata(byte[] binarydata) {
			// 			
		}

}
