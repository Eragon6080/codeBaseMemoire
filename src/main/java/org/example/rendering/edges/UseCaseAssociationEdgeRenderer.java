/*******************************************************************************
 * example - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2020 by McGill University.
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
package org.example.rendering.edges;

import org.example.rendering.ArrowHead;
import org.example.rendering.DiagramRenderer;
import org.example.rendering.LineStyle;

/**
 * A straight solid line.
 */
public final class UseCaseAssociationEdgeRenderer extends StraightEdgeRenderer
{	
	/**
	 * Creates a viewer for UseCaseAssociationEdge instances.
	 */
	public UseCaseAssociationEdgeRenderer(DiagramRenderer pParent)
	{
		super(pParent, LineStyle.SOLID, ArrowHead.NONE);
	}
}