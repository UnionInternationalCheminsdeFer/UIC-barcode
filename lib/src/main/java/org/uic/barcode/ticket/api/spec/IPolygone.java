/*
 * 
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;

/**
 * The Interface IPolygone.
 * 
 * 
 * IPolygone provides a description of a region valid for traveling by a polygon of geo-coordinates.
 * 
 */
public interface IPolygone extends IRegionalValidity {
	
	/**
	 * Gets the edges of the polygon.
	 *
	 * @return the edges
	 */
	public Collection<IGeoCoordinate> getEdges();

	/**
	 * Adds an  edge to the polygon.
	 *
	 * @param edge the geo-coordinate of the edge
	 */
	public void addEdge(IGeoCoordinate edge);
	

}
