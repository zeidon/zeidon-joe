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

import java.util.ArrayList;
import java.util.Collection;

/**
 * Thrown by activates if the DB can't acquire a pessimistic lock for an OI.
 * 
 * @author DG
 *
 */
public class PessimisticLockingException extends ZeidonException
{
    private static final long serialVersionUID = 1L;
    
    private final Collection<View> views;

    /**
     * @param format
     * @param strings
     */
    public PessimisticLockingException(Collection<View> views, String format, Object... strings)
    {
        super( format, strings );
        this.views = views;
    }

    public PessimisticLockingException(View view, String format, Object... strings)
    {
        this( new ArrayList<View>(), format, strings );
        views.add( view );
    }

    public Collection<View> getViews()
    {
        return views;
    }
}
