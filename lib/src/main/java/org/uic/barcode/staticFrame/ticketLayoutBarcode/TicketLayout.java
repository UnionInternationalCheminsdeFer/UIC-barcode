package org.uic.barcode.staticFrame.ticketLayoutBarcode;

import java.util.ArrayList;
import java.util.List;

public class TicketLayout {
	
	private String layoutStandard = "RCT2";
	
	/** The layout elements. */
	private List<LayoutElement> elements = new ArrayList<LayoutElement>();


	/**
	 * Gets the layout standard.
	 *
	 * @return the layout standard
	 */
	public String getLayoutStandard() {
        if (layoutStandard == null || layoutStandard.length() != 4) {
        	layoutStandard = "RCT2";
        }
		return layoutStandard;
	}

	/**
	 * Sets the layout standard.
	 *
	 * @param layoutStandard the new layout standard
	 */
	public void setLayoutStandard(String layoutStandard) {
		this.layoutStandard = layoutStandard;
	}

	/**
	 * Adds the layout element.
	 *
	 * @param element the element
	 */
	public void addLayoutElement(LayoutElement element){
		elements.add(element);
	}
	
	/**
	 * Removes the layout elements.
	 */
	public void removeLayoutElements(){
		elements.clear();
	}
	
	/**
	 * Gets the elements.
	 *
	 * @return the elements
	 */
	public List<LayoutElement> getElements(){
		return elements;
	}
	

}
