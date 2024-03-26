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
import org.example.edges.NoteEdge;
import org.example.nodes.NoteNode;
import org.example.nodes.PointNode;
import org.example.validator.EdgeConstraint;

/**
 * Validates that a note edge is semantically correct. A note edge can come in
 * two flavors:
 * 1. From a note node to a point node
 * 2. From any node except a note node or a point node to a note node
 */
public final class ConstraintValidNoteEdge implements EdgeConstraint
{
    @Override
    public boolean satisfied(Edge pEdge, Diagram pDiagram)
    {
        if( pEdge.getClass() != NoteEdge.class )
        {
            return true;
        }
        if( pEdge.end().getClass() == PointNode.class )
        {
            return pEdge.start().getClass() == NoteNode.class;
        }
        return pEdge.start().getClass() != PointNode.class && pEdge.start().getClass() != NoteNode.class &&
                pEdge.end().getClass() == NoteNode.class;
    }
}
