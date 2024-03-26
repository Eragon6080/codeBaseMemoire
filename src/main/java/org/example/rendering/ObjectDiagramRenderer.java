/*******************************************************************************
 * example - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2022 by McGill University.
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
 ******************************************************************************/
package org.example.rendering;

import org.example.diagram.Diagram;
import org.example.edges.ObjectCollaborationEdge;
import org.example.edges.ObjectReferenceEdge;
import org.example.diagram.nodes.FieldNode;
import org.example.nodes.ObjectNode;
import org.example.edges.ObjectCollaborationEdgeRenderer;
import org.example.edges.ObjectReferenceEdgeRenderer;
import org.example.nodes.FieldNodeRenderer;
import org.example.nodes.ObjectNodeRenderer;

/**
 * The renderer for object diagrams.
 */
public final class ObjectDiagramRenderer extends AbstractDiagramRenderer
{
	/**
	 * @param pDiagram Diagram being rendered.
	 */
	public ObjectDiagramRenderer(Diagram pDiagram)
	{
		super(pDiagram);
		addElementRenderer(FieldNode.class, new FieldNodeRenderer(this));
		addElementRenderer(ObjectNode.class, new ObjectNodeRenderer(this));
		addElementRenderer(ObjectReferenceEdge.class, new ObjectReferenceEdgeRenderer(this));
		addElementRenderer(ObjectCollaborationEdge.class, new ObjectCollaborationEdgeRenderer(this));
	}
}