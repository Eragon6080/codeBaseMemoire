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
import org.example.edges.UseCaseAssociationEdge;
import org.example.edges.UseCaseDependencyEdge;
import org.example.edges.UseCaseGeneralizationEdge;
import org.example.nodes.ActorNode;
import org.example.nodes.UseCaseNode;
import org.example.edges.UseCaseAssociationEdgeRenderer;
import org.example.edges.UseCaseDependencyEdgeRenderer;
import org.example.edges.UseCaseGeneralizationEdgeRenderer;
import org.example.nodes.ActorNodeRenderer;
import org.example.nodes.UseCaseNodeRenderer;

/**
 * The renderer for use case diagrams.
 */
public final class UseCaseDiagramRenderer extends AbstractDiagramRenderer
{
	/**
	 * @param pDiagram The diagram being rendered.
	 */
	public UseCaseDiagramRenderer(Diagram pDiagram)
	{
		super(pDiagram);
		addElementRenderer(ActorNode.class, new ActorNodeRenderer(this));
		addElementRenderer(UseCaseNode.class, new UseCaseNodeRenderer(this));
		addElementRenderer(UseCaseAssociationEdge.class, new UseCaseAssociationEdgeRenderer(this));
		addElementRenderer(UseCaseGeneralizationEdge.class, new UseCaseGeneralizationEdgeRenderer(this));
		addElementRenderer(UseCaseDependencyEdge.class, new UseCaseDependencyEdgeRenderer(this));
	}
}
