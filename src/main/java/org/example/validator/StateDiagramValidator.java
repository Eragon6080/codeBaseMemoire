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
package org.example.validator;

import java.util.Set;

import org.example.diagram.Diagram;
import org.example.diagram.DiagramType;
import org.example.diagram.Edge;
import org.example.diagram.Node;
import org.example.edges.StateTransitionEdge;
import org.example.nodes.FinalStateNode;
import org.example.nodes.InitialStateNode;
import org.example.nodes.StateNode;
import org.example.constraints.ConstraintMaxNumberOfEdgesOfGivenTypeBetweenNodes;
import org.example.constraints.ConstraintValidTransitionEdgeEndNode;
import org.example.constraints.ConstraintValidTransitionEdgeStartNode;

/**
 * Validator for state diagrams.
 */
public class StateDiagramValidator extends AbstractDiagramValidator
{
	private static final Set<EdgeConstraint> CONSTRAINTS = Set.of(
			new ConstraintMaxNumberOfEdgesOfGivenTypeBetweenNodes(2),
			new ConstraintValidTransitionEdgeStartNode(),
			new ConstraintValidTransitionEdgeEndNode());

	private static final Set<Class<? extends Node>> VALID_NODE_TYPES = Set.of(
			StateNode.class,
			InitialStateNode.class, 
			FinalStateNode.class);

	private static final Set<Class<? extends Edge>> VALID_EDGE_TYPES = Set.of(
			StateTransitionEdge.class);

	/**
	 * Creates a new validator for one state diagram.
	 *
	 * @param pDiagram The diagram to do semantic validity check on.
	 * @pre pDiagram != null && pDiagram.getType() == DiagramType.STATE
	 */
	public StateDiagramValidator(Diagram pDiagram)
	{
		super(pDiagram, VALID_NODE_TYPES, VALID_EDGE_TYPES, CONSTRAINTS);
		assert pDiagram.getType() == DiagramType.STATE;
	}
}
