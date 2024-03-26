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

import static org.example.geom.GeomUtils.max;

import org.example.diagram.DiagramElement;
import org.example.diagram.DiagramType;
import org.example.geom.Dimension;
import org.example.geom.Rectangle;
import org.example.rendering.DiagramRenderer;
import org.example.rendering.StringRenderer;
import org.example.rendering.StringRenderer.Alignment;
import org.example.rendering.StringRenderer.TextDecoration;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * An object to render a package in a class diagram.
 */
public final class PackageDescriptionNodeRenderer extends AbstractPackageNodeRenderer
{
	private static final StringRenderer CONTENTS_VIEWER = StringRenderer.get(Alignment.CENTER_CENTER, TextDecoration.PADDED);
	
	/**
	 * @param pParent Renderer of the parent diagram.
	 */
	public PackageDescriptionNodeRenderer(DiagramRenderer pParent)
	{
		super(pParent);
	}
	
	@Override
	public void draw(DiagramElement pElement, GraphicsContext pGraphics)
	{
		super.draw(pElement, pGraphics);
		Rectangle bottomBounds = getBottomBounds((AbstractPackageNode)pElement);
		CONTENTS_VIEWER.draw(((PackageDescriptionNode)pElement).getContents(), pGraphics, new Rectangle(bottomBounds.getX() + NAME_GAP, 
				bottomBounds.getY(), bottomBounds.getWidth(), bottomBounds.getHeight()));
	}
	
	@Override
	protected Rectangle getBottomBounds(AbstractPackageNode pNode)
	{
		Dimension contentsBounds = CONTENTS_VIEWER.getDimension(((PackageDescriptionNode)pNode).getContents());
		int width = max(contentsBounds.width() + 2 * PADDING, DEFAULT_WIDTH);
		int height = max(contentsBounds.height() + 2 * PADDING, DEFAULT_BOTTOM_HEIGHT);
		
		Dimension topDimension = getTopDimension(pNode);
		width = max( width, topDimension.width()+ (DEFAULT_WIDTH - DEFAULT_TOP_WIDTH));
		
		return new Rectangle(pNode.position().getX(), pNode.position().getY() + topDimension.height(), 
				width, height);
	}
	
	/*
	 * Custom version to distinguish package descriptions from package nodes.
	 */
	@Override
	public Canvas createIcon(DiagramType pDiagramType, DiagramElement pElement)
	{
		assert pElement instanceof AbstractPackageNode;
		Canvas icon = super.createIcon(pDiagramType, pElement);
		CONTENTS_VIEWER.draw("description", icon.getGraphicsContext2D(), getBottomBounds((AbstractPackageNode)pElement));
		return icon;
	}
}
