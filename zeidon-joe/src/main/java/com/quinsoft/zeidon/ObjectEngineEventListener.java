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

package com.quinsoft.zeidon;

import java.util.Collection;

/**
 * @author dg
 *
 */
public interface ObjectEngineEventListener
{
    void setObjectEngine( ObjectEngine oe );
    
    /**
     * An object instance was activated.
     * 
     * @param view
     * @param qualification TODO
     * @param millis
     * @param exception TODO
     */
    void objectInstanceActivated( View view, View qualification, long millis, Exception exception );
    
    /**
     * An object instance was committed.
     * @param viewList
     * @param millis
     * @param e TODO
     */
    void objectInstanceCommitted( Collection<View> viewList, long millis, Exception e );
}
