package org.uic.barcode.extensions;


import org.uic.barcode.ticket.api.spec.IExtension;

public interface IExtensionCoder {

   byte[] encode(IExtension obj) throws Exception;
    
   IExtension decode(byte[] bytes, String extensionId) throws Exception;
   
   IExtensionCoder clone();

}
