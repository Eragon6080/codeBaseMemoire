/*******************************************************************************
 * example - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2023 by McGill University.
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
package org.example.constraints;

import org.example.diagram.Diagram;
import org.example.diagram.Edge;
import org.example.edges.ReturnEdge;
import org.example.nodes.CallNode;
import org.example.validator.EdgeConstraint;

/**
 * A return can only be between call nodes.
 */
public final class ConstraintReturnEdgeBetweenCallNodes implements EdgeConstraint
{
    @Override
    public boolean satisfied(Edge pEdge, Diagram pDiagram)
    {
        return !(pEdge instanceof ReturnEdge && (pEdge.start().getClass() != CallNode.class ||
                pEdge.end().getClass() != CallNode.class));
    }
}
