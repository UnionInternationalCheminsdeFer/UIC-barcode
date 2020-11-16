package org.uic.barcode.test.utils;

import org.uic.barcode.staticFrame.ticketLayoutBarcode.LayoutElement;
import org.uic.barcode.staticFrame.ticketLayoutBarcode.TicketLayout;

public class SimpleTestTicketLayout {
	
	
	public static TicketLayout getSimpleTestTicketLayout() {
		
		TicketLayout layout = new TicketLayout();
		
		layout.setLayoutStandard("RCT2");
				
		LayoutElement element = new LayoutElement();
		element.setColumn(1);
		element.setLine(1);
		element.setHeight(1);
		element.setWidth(20);
		element.setText("Müller");
		layout.addLayoutElement(element);

		return layout;
		
	}

	public static void compare(TicketLayout layout1, TicketLayout layout2) {

		assert(layout1.getLayoutStandard().equals(layout2.getLayoutStandard()));
		
	}

}
