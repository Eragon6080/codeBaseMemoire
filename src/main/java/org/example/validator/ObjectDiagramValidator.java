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
import org.example.edges.ObjectCollaborationEdge;
import org.example.edges.ObjectReferenceEdge;
import org.example.diagram.nodes.FieldNode;
import org.example.nodes.ObjectNode;
import org.example.constraints.ConstraintMaxNumberOfEdgesOfGivenTypeBetweenNodes;
import org.example.constraints.ConstraintNoSelfEdgeForEdgeType;
import org.example.constraints.ConstraintValidCollaborationEdge;
import org.example.constraints.ConstraintValidReferenceEdge;
import org.example.constraints.ConstraintNoDirectCyclesForEdgeType;

/**
 * Validator for object diagrams.
 */
public class ObjectDiagramValidator extends AbstractDiagramValidator
{
	private static final Set<EdgeConstraint> CONSTRAINTS = Set.of(
			new ConstraintValidReferenceEdge(),
			new ConstraintValidCollaborationEdge(),
			new ConstraintNoSelfEdgeForEdgeType(ObjectCollaborationEdge.class),
			new ConstraintMaxNumberOfEdgesOfGivenTypeBetweenNodes(1),
			new ConstraintNoDirectCyclesForEdgeType(ObjectCollaborationEdge.class));
	
	private static final Set<Class<? extends Node>> VALID_NODE_TYPES = Set.of(
			ObjectNode.class, 
			FieldNode.class);
	
	private static final Set<Class<? extends Edge>> VALID_EDGE_TYPES = Set.of(
			ObjectReferenceEdge.class,
			ObjectCollaborationEdge.class);

	/**
	 * Creates a new validator for one object diagram.
	 *
	 * @param pDiagram The diagram to do semantic validity check on.
	 * @pre pDiagram != null && pDiagram.getType() == DiagramType.OBJECT
	 */
	public ObjectDiagramValidator(Diagram pDiagram)
	{
		super(pDiagram, VALID_NODE_TYPES, VALID_EDGE_TYPES, CONSTRAINTS);
		assert pDiagram.getType() == DiagramType.OBJECT;
	}

	/**
	 * All root nodes must not be FieldNode.
	 */
	@Override
	protected boolean hasValidDiagramNodes()
	{
		return diagram().rootNodes().stream().noneMatch(node -> node instanceof FieldNode);
	}
}
