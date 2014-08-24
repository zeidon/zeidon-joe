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

    Copyright 2009-2014 QuinSoft
 */

package com.quinsoft.zeidon.standardoe;

import java.util.Collection;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.ObjectEngineEventListener;
import com.quinsoft.zeidon.View;

/**
 * An oe listener that doesn't do anything.
 * 
 * @author dg
 *
 */
public class NullObjectEngineListener implements ObjectEngineEventListener
{

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ObjectEngineEventListener#setObjectEngine(com.quinsoft.zeidon.ObjectEngine)
     */
    @Override
    public void setObjectEngine( ObjectEngine oe )
    {
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ObjectEngineEventListener#objectInstanceActivated(com.quinsoft.zeidon.View, long)
     */
    @Override
    public void objectInstanceActivated( View view, View qualification, long millis, Exception exception )
    {
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ObjectEngineEventListener#objectInstanceCommitted(com.quinsoft.zeidon.View, long)
     */
    @Override
    public void objectInstanceCommitted( Collection<View> viewList, long millis, Exception e )
    {
    }
}
