package org.uic.barcode;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.uic.barcode.staticFrame.UTLAYDataRecord;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.LayoutElement;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;
import org.uic.barcode.utils.SimpleTestTicketLayout;
import org.uic.barcode.utils.TestUtils;
import org.uic.barcode.ticket.EncodingFormatException;


public class TicketLayoutTest {
	
	
	@Test public void testTicketLayout() throws IOException, EncodingFormatException{
		
		UTLAYDataRecord tl1 = new UTLAYDataRecord();
		
		TicketLayout layout = SimpleTestTicketLayout.getSimpleTestTicketLayout();
		tl1.setLayout(layout);
		
		byte[] encoded = null;
		try {
			encoded = tl1.encode();
		} catch (IOException e) {
			throw (e);
		}
		
		String hex = TestUtils.hexStringFromBytes(encoded);
		
		assertEquals(hex,"555F544C41593031303034305243543230303031303130313031323030303030374DC3BC6C6C6572");
		
		UTLAYDataRecord tl2 = new UTLAYDataRecord();
		
		tl2.decode(tl1.encode());
		
		
		assertEquals(tl1.toString(),tl2.toString()); 
		
		compare(layout, tl2.getLayout());

	}

	public static void compare(TicketLayout layout1, TicketLayout layout2) {

		assert(layout1.getLayoutStandard().equals(layout2.getLayoutStandard()));

		for (LayoutElement e1: layout1.getElements() ) {

			for (LayoutElement e2 :layout2.getElements()) {

				boolean found = false;
				if (e1.getLine() == e2.getLine() && e1.getColumn() == e2.getColumn()) {
					found = true;
					assert(e1.getText().equals(e2.getText()));
				}
				assert(found == true);

			}

		}

	}




}
