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
package org.example.rendering.nodes;

import org.example.diagram.nodes.TypeNode;
import org.example.rendering.DiagramRenderer;

/**
 * An object to render an interface in a class diagram.
 */
public final class InterfaceNodeRenderer extends TypeNodeRenderer
{
	/**
	 * @param pParent The renderer for the parent diagram.
	 */
	public InterfaceNodeRenderer(DiagramRenderer pParent)
	{
		super(pParent);
	}
	
	/**
	 * Adds the interface Stereotype to the name.
	 * 
	 * @param pNode The Node.
	 * @return The text to show as the name of the node.
	 * @pre pNode != null
	 */
	@Override
	protected String getNameText(TypeNode pNode)
	{
		return "\u00ABinterface\u00BB\n" + pNode.getName();
	}
}
