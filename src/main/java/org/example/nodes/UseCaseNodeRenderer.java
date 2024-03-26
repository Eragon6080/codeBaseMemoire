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
package org.example.nodes;

import org.example.diagram.DiagramElement;
import org.example.diagram.Node;
import org.example.geom.Dimension;
import org.example.geom.Direction;
import org.example.geom.GeomUtils;
import org.example.geom.Point;
import org.example.geom.Rectangle;
import org.example.rendering.DiagramRenderer;
import org.example.rendering.RenderingUtils;
import org.example.rendering.StringRenderer;
import org.example.rendering.StringRenderer.Alignment;
import org.example.rendering.StringRenderer.TextDecoration;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * An object to render a UseCaseNode.
 */
public final class UseCaseNodeRenderer extends AbstractNodeRenderer
{
	private static final int DEFAULT_WIDTH = 110;
	private static final int DEFAULT_HEIGHT = 40;
	private static final int HORIZONTAL_NAME_PADDING = 30;
	private static final StringRenderer NAME_VIEWER = StringRenderer.get(Alignment.CENTER_CENTER, TextDecoration.PADDED);
	
	/**
	 * @param pParent Renderer for the parent diagram.
	 */
	public UseCaseNodeRenderer(DiagramRenderer pParent)
	{
		super(pParent);
	}
	
	@Override
	public Dimension getDefaultDimension(Node pNode)
	{
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	@Override
	public void draw(DiagramElement pElement, GraphicsContext pGraphics)
	{
		Rectangle bounds = getBounds(pElement);
		RenderingUtils.drawOval(pGraphics, bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), Color.WHITE, true);
		NAME_VIEWER.draw(((UseCaseNode)pElement).getName(), pGraphics, getBounds(pElement));
	}
	
	@Override
	protected Rectangle internalGetBounds(Node pNode)
	{
		return new Rectangle(pNode.position().getX(), pNode.position().getY(), 
				Math.max(DEFAULT_WIDTH,  NAME_VIEWER.getDimension(((UseCaseNode)pNode).getName()).width()+
						HORIZONTAL_NAME_PADDING), 
				Math.max(DEFAULT_HEIGHT, NAME_VIEWER.getDimension(((UseCaseNode)pNode).getName()).height()));
	}
	
	@Override
	public Point getConnectionPoint(Node pNode, Direction pDirection)
	{
		return GeomUtils.intersectEllipse(getBounds(pNode), pDirection);
	}   	
}