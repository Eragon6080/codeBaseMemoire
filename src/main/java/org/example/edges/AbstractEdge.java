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
package org.example.edges;

import org.example.diagram.AbstractDiagramElement;
import org.example.diagram.Edge;
import org.example.diagram.Node;

/**
 * Groups the functionality common to all edges.
 */
public abstract class AbstractEdge extends AbstractDiagramElement implements Edge
{
	private Node aStart;
	private Node aEnd;
	
	@Override
	public final void connect(Node pStart, Node pEnd)
	{
		assert pStart != null && pEnd != null;
		aStart = pStart;
		aEnd = pEnd;
	}

	@Override
	public Node start()
	{
		return aStart;
	}

	@Override
	public Node end()
	{
		return aEnd;
	}

	@Override
	public AbstractEdge clone()
	{
		AbstractEdge clone = (AbstractEdge) super.clone();
		return clone;
	}
	
	@Override
	public String toString()
	{
		String result = getClass().getSimpleName();
		if( start() != null && end() != null )
		{
			result += " " + start() + " -> " + end();
		}
		return result;
	}
}
