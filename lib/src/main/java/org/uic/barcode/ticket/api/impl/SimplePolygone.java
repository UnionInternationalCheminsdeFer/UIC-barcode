/*
 * 
 */
package org.uic.barcode.ticket.api.impl;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.uic.barcode.ticket.api.spec.IGeoCoordinate;
import org.uic.barcode.ticket.api.spec.IPolygone;

// TODO: Auto-generated Javadoc
/**
 * The Class SimplePolygone.
 */
public class SimplePolygone implements IPolygone{
	
	/** The edges. */
	protected Collection<IGeoCoordinate> edges = new LinkedHashSet<IGeoCoordinate>();

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IPolygone#getEdges()
	 */
	public Collection<IGeoCoordinate> getEdges() {
		return edges;
	}

	/* (nicht-Javadoc)
	 * @see org.uic.ticket.api.spec.IPolygone#addEdge(org.uic.ticket.api.spec.IGeoCoordinate)
	 */
	public void addEdge(IGeoCoordinate edge) {
		this.edges.add(edge);
	} 
	
	

}
