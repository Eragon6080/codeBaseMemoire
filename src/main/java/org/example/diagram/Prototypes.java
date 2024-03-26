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
package org.example.diagram;

import static org.example.application.ApplicationResources.RESOURCES;

import java.util.IdentityHashMap;
import java.util.Map;

import org.example.edges.AggregationEdge;
import org.example.edges.AssociationEdge;
import org.example.edges.CallEdge;
import org.example.edges.DependencyEdge;
import org.example.edges.GeneralizationEdge;
import org.example.edges.NoteEdge;
import org.example.edges.ObjectCollaborationEdge;
import org.example.edges.ObjectReferenceEdge;
import org.example.edges.ReturnEdge;
import org.example.edges.StateTransitionEdge;
import org.example.edges.UseCaseAssociationEdge;
import org.example.edges.UseCaseDependencyEdge;
import org.example.edges.UseCaseGeneralizationEdge;
import org.example.nodes.ActorNode;
import org.example.nodes.ClassNode;
import org.example.diagram.nodes.FieldNode;
import org.example.nodes.FinalStateNode;
import org.example.nodes.ImplicitParameterNode;
import org.example.nodes.InitialStateNode;
import org.example.nodes.InterfaceNode;
import org.example.nodes.NoteNode;
import org.example.nodes.ObjectNode;
import org.example.nodes.PackageDescriptionNode;
import org.example.nodes.PackageNode;
import org.example.nodes.StateNode;
import org.example.nodes.UseCaseNode;

/**
 * Prototype objects for creating diagram elements.
 */
public final class Prototypes
{   // CSOFF:
	private static final Prototypes INSTANCE = new Prototypes();
	private final Map<DiagramElement, String> aKeys = new IdentityHashMap<>();
	
	public static final DiagramElement NOTE = create(new NoteNode(), "note");
	public static final DiagramElement NOTE_CONNECTOR = create(new NoteEdge(), "note_connector");
	
	public static final DiagramElement CLASS = create(new ClassNode(), "class");
	public static final DiagramElement INTERFACE = create(new InterfaceNode(), "interface");
	public static final DiagramElement PACKAGE = create(new PackageNode(), "package");
	public static final DiagramElement PACKAGE_DESCRIPTION = 
			create(new PackageDescriptionNode(), "package_description");
	public static final DiagramElement DEPENDENCY = create(new DependencyEdge(), "dependency");
	public static final DiagramElement GENERALIZATION = create(new GeneralizationEdge(), "generalization");
	public static final DiagramElement REALIZATION = 
			create(new GeneralizationEdge(GeneralizationEdge.Type.Implementation), "realization");
	public static final DiagramElement ASSOCIATION = create(new AssociationEdge(), "association");
	public static final DiagramElement AGGREGATION = create(new AggregationEdge(), "aggregation");
	public static final DiagramElement COMPOSITION = 
			create(new AggregationEdge(AggregationEdge.Type.Composition), "composition");
	
	public static final DiagramElement IMPLICIT_PARAMETER = 
			create(new ImplicitParameterNode(), "implicit_parameter");
	public static final DiagramElement METHOD_CALL = create(new CallEdge(), "method_call");
	public static final DiagramElement METHOD_RETURN = create(new ReturnEdge(), "method_return");
	
	public static final DiagramElement STATE = create(new StateNode(), "state");
	public static final DiagramElement START_STATE = create(new InitialStateNode(), "start_state");
	public static final DiagramElement END_STATE = create(new FinalStateNode(), "end_state");
	public static final DiagramElement TRANSITION = create(new StateTransitionEdge(), "transition");
	
	public static final DiagramElement OBJECT = create(new ObjectNode(), "object");
	public static final DiagramElement FIELD = create(new FieldNode(), "field");
	public static final DiagramElement REFERENCE = create(new ObjectReferenceEdge(), "reference");
	public static final DiagramElement COLLABORATION = create(new ObjectCollaborationEdge(), "collaboration");	
	
	public static final DiagramElement ACTOR = create(new ActorNode(), "actor");	
	public static final DiagramElement USE_CASE = create(new UseCaseNode(), "use_case");	
	public static final DiagramElement USE_CASE_ASSOCIATION = 
			create(new UseCaseAssociationEdge(), "use_case_association");	
	public static final DiagramElement USE_CASE_EXTENDS = 
			create(new UseCaseDependencyEdge(UseCaseDependencyEdge.Type.Extend), "use_case_extends");	
	public static final DiagramElement USE_CASE_INCLUDES = 
			create(new UseCaseDependencyEdge(UseCaseDependencyEdge.Type.Include), "use_case_includes");	
	public static final DiagramElement USE_CASE_GENERALIZATION = 
			create(new UseCaseGeneralizationEdge(), "use_case_generalization");	// CSON:
	
	private Prototypes() {}
	
	/**
	 * @return The singleton instance of this class.
	 */
	public static Prototypes instance()
	{
		return INSTANCE;
	}
	
	private static DiagramElement create(DiagramElement pElement, String pKey)
	{
		INSTANCE.aKeys.put(pElement, pKey);
		return pElement;
	}
	
	/**
	 * @param pPrototype The requested prototype
	 * @param pVerbose true if we want the verbose version of this tooltip.
	 * @return The tooltip associated with this prototype.
	 * @pre pPrototype != null
	 */
	public String tooltip(DiagramElement pPrototype, boolean pVerbose)
	{
		if( !aKeys.containsKey(pPrototype))
		{
			return "[tooltip not found]";
		}
		String basicKey = aKeys.get(pPrototype) + ".tooltip";
		String verboseKey = basicKey + ".verbose";
		if( pVerbose && RESOURCES.containsKey(verboseKey))
		{
			return RESOURCES.getString(basicKey) + ": " + RESOURCES.getString(verboseKey);
		}
		else
		{
			return RESOURCES.getString(basicKey);
		}
	}
}
