package org.uic.barcode.utils;

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
