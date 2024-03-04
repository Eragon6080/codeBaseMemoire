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

import org.example.diagram.Edge;
import org.example.geom.Line;
import org.example.rendering.DiagramElementRenderer;

/**
 * Abstract strategy that describes objects that can draw and
 * compute various geometric properties of edges.
 */
public interface EdgeRenderer extends DiagramElementRenderer
{
   	/**
     * Gets the points at which pEdge is connected to
     * its nodes.
     * @param pEdge The target edge
     * @return a line joining the two connection points
     * @pre pEdge != null
     * 
     */
   	Line getConnectionPoints(Edge pEdge);
}
