package org.uic.barcode.extensions;

import java.util.HashMap;

import org.uic.barcode.extensions.vdv.VdvExtensionCoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.api.impl.SimpleExtension;
import org.uic.barcode.ticket.api.spec.IExtension;

public class ExtensionRegistry {

	static ExtensionRegistry  me = null;

	private ExtensionRegistry() {
		me = this;
		this.register("vdv01", new VdvExtensionCoder());
	}
	
	private HashMap<String, IExtensionCoder > extensionCoderRegistry = new HashMap<String, IExtensionCoder>();

	
	public static ExtensionRegistry getInstance() {
		
		if (me == null) {
			me = new ExtensionRegistry();
		}
		
		return me;
		
	}
	
	public void register(String extensionId, IExtensionCoder coder){
		extensionCoderRegistry.put(extensionId, coder);
	}
	
	public IExtensionCoder getCoder(String extensionId) {
		return extensionCoderRegistry.get(extensionId);
	}
	
	public IExtension decodeFrom(SimpleExtension simpleExtension) {
		
		IExtensionCoder coder = this.getCoder(simpleExtension.getId());
		
		if (coder == null) return simpleExtension;
		
		try {
			return coder.decode(simpleExtension.getBinarydata(), simpleExtension.getId());
		} catch (Exception e) {
			LoggerFactory.getLogger("extensionLogger").debug(e.getMessage());
			return simpleExtension;
		} 
		
	}
	
	
	public SimpleExtension encodeToSimpleExtension(IExtension extension) {
		SimpleExtension simpleExtension = new SimpleExtension();
		
		simpleExtension.setId(extension.getId());
		simpleExtension.setBinarydata(extension.getBinarydata());
		
		return simpleExtension;
	}
	
	
}