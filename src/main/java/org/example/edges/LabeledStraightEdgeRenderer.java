/*******************************************************************************
 * example - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2020, 2021 by McGill University.
 *     
 * See: https://github.com/prmr/example
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 *******************************************************************************/
package org.example.edges;

import java.util.function.Function;

import org.example.diagram.DiagramElement;
import org.example.diagram.Edge;
import org.example.geom.Dimension;
import org.example.geom.Point;
import org.example.geom.Rectangle;
import org.example.rendering.ArrowHead;
import org.example.rendering.DiagramRenderer;
import org.example.rendering.LineStyle;
import org.example.rendering.StringRenderer;
import org.example.rendering.StringRenderer.Alignment;
import org.example.rendering.StringRenderer.TextDecoration;

import javafx.scene.canvas.GraphicsContext;

/**
 * Can draw a straight edge with a label than can be obtained dynamically. 
 */
public class LabeledStraightEdgeRenderer extends StraightEdgeRenderer
{	
	private static final StringRenderer STRING_VIEWER = StringRenderer.get(Alignment.CENTER_CENTER, TextDecoration.PADDED);
	
	private final Function<Edge, String> aLabelExtractor;
	
	/**
	 * Creates a new view with the required LineStyle and ArrowHead and label provider.
	 * 
	 * @param pLineStyle The line style for the edge.
	 * @param pArrowHead The arrow head for the end of the arrow. The start is always NONE.
	 * @param pLabelExtractor A function to extract for the edge's label.
	 */
	public LabeledStraightEdgeRenderer(DiagramRenderer pParent, LineStyle pLineStyle, ArrowHead pArrowHead,
			Function<Edge, String> pLabelExtractor)
	{
		super(pParent, pLineStyle, pArrowHead);
		aLabelExtractor = pLabelExtractor;
	}
	
	@Override
	public void draw(DiagramElement pElement, GraphicsContext pGraphics)
	{
		super.draw(pElement, pGraphics);
		Edge edge = (Edge) pElement;
		String label = wrapLabel(edge);
		int labelHeight = STRING_VIEWER.getDimension(label).height();
		if( label.length() > 0 )
		{
			STRING_VIEWER.draw(label, pGraphics, getConnectionPoints(edge).spanning().translated(0, -labelHeight/2));
		}
	}
	
	private String wrapLabel(Edge pEdge) 
	{
		int distanceInX = Math.abs(parent().getBounds(pEdge.start()).getCenter().getX() -
				parent().getBounds(pEdge.end()).getCenter().getX());
		int distanceInY = Math.abs(parent().getBounds(pEdge.start()).getCenter().getY() -
				parent().getBounds(pEdge.end()).getCenter().getY());
		return super.wrapLabel(aLabelExtractor.apply(pEdge), distanceInX, distanceInY);
	}

	private Rectangle getStringBounds(Edge pEdge)
	{
		String label = wrapLabel(pEdge);
		assert label != null && label.length() > 0;
		Dimension dimensions = STRING_VIEWER.getDimension(label);
		Point center = getConnectionPoints(pEdge).spanning().getCenter();
		return new Rectangle(center.getX()-dimensions.width()/2, center.getY() - dimensions.height()/2, dimensions.width(), 
				dimensions.height());
	}
	
	@Override
	public Rectangle getBounds(DiagramElement pElement)
	{
		Rectangle bounds = super.getBounds(pElement);
		Edge edge = (Edge) pElement;
		String label = aLabelExtractor.apply(edge);
		if( label.length() > 0 )
		{
			bounds = bounds.add(getStringBounds(edge));
		}
		return bounds;
	}
}