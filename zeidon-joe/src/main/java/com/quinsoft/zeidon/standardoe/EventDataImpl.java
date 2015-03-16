/**
    This file is part of the Zeidon Java Object Engine (Zeidon JOE).

    Zeidon JOE is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zeidon JOE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2009-2015 QuinSoft
 */

package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.EventData;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * Implementation of EventData.
 *
 */
class EventDataImpl implements EventData
{
    private final Task task;

    private View           view;
    private EntityDef     entityDef;
    private AttributeDef  attributeDef;
    private EntityInstance entityInstance;

    /**
     * @param task
     */
    public EventDataImpl(Task task)
    {
        this.task = task;
    }

    public EventDataImpl(View view)
    {
        this.task = view.getTask();
        this.view = view;
    }

    public EventDataImpl setEntityInstance( EntityInstance ei )
    {
        entityInstance = ei;
        return this;
    }

    public EventDataImpl setView( View v )
    {
        view = v;
        return this;
    }

    public EventDataImpl setEntityDef( EntityDef ve )
    {
        entityDef = ve;
        return this;
    }

    public EventDataImpl setAttributeDef( AttributeDef va )
    {
        attributeDef = va;
        return this;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EventData#getTask()
     */
    @Override
    public Task getTask()
    {
        return task;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EventData#getView()
     */
    @Override
    public View getView()
    {
        return view;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EventData#getEntityDef()
     */
    @Override
    public EntityDef getEntityDef()
    {
        return entityDef;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EventData#getAttributeDef()
     */
    @Override
    public AttributeDef getAttributeDef()
    {
        return attributeDef;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EventData#getEntityInstance()
     */
    @Override
    public EntityInstance getEntityInstance()
    {
        return entityInstance;
    }
}
