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
package org.example.diagram.validator;

import java.util.Set;

import org.example.diagram.Diagram;
import org.example.diagram.DiagramType;
import org.example.diagram.Edge;
import org.example.diagram.Node;
import org.example.diagram.edges.UseCaseAssociationEdge;
import org.example.diagram.edges.UseCaseDependencyEdge;
import org.example.diagram.edges.UseCaseGeneralizationEdge;
import org.example.diagram.nodes.ActorNode;
import org.example.diagram.nodes.UseCaseNode;
import org.example.diagram.validator.constraints.ConstraintMaxNumberOfEdgesOfGivenTypeBetweenNodes;
import org.example.diagram.validator.constraints.ConstraintNoSelfEdgeForEdgeType;

/**
 * Validator for use case diagrams.
 */
public class UseCaseDiagramValidator extends AbstractDiagramValidator
{
	private static final Set<EdgeConstraint> CONSTRAINTS = Set.of(
			new ConstraintMaxNumberOfEdgesOfGivenTypeBetweenNodes(1),
			new ConstraintNoSelfEdgeForEdgeType(UseCaseAssociationEdge.class),
			new ConstraintNoSelfEdgeForEdgeType(UseCaseGeneralizationEdge.class),
			new ConstraintNoSelfEdgeForEdgeType(UseCaseDependencyEdge.class));

	private static final Set<Class<? extends Node>> VALID_NODE_TYPES = Set.of(
			ActorNode.class, 
			UseCaseNode.class);

	private static final Set<Class<? extends Edge>> VALID_EDGE_TYPES = Set.of(
			UseCaseAssociationEdge.class,
			UseCaseDependencyEdge.class, 
			UseCaseGeneralizationEdge.class);

	/**
	 * Creates a new validator for one use case diagram.
	 *
	 * @param pDiagram The diagram to do semantic validity check on.
	 * @pre pDiagram != null && pDiagram.getType() == DiagramType.USECASE
	 */

	public UseCaseDiagramValidator(Diagram pDiagram)
	{
		super(pDiagram, VALID_NODE_TYPES, VALID_EDGE_TYPES, CONSTRAINTS);
		assert pDiagram.getType() == DiagramType.USECASE;
	}
}
