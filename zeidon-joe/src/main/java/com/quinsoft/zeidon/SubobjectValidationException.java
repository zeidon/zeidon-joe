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
/**
 * 
 */
package com.quinsoft.zeidon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author DG
 *
 */
public class SubobjectValidationException extends ZeidonException
{
    private static final long serialVersionUID = 1L;

    private final List<ZeidonException> exceptions = new ArrayList<ZeidonException>();
    
    /**
     * @param format
     * @param strings
     */
    public SubobjectValidationException( Collection<ZeidonException> list )
    {
        super( "Subobject Validation exception.  See child exceptions for more." );
        addChildExceptions( list );
    }

    public SubobjectValidationException addChildExceptions( Collection<ZeidonException> list )
    {
        for ( ZeidonException ze : list )
            addChildException( ze );

        return this;
    }
    
    public SubobjectValidationException addChildException( ZeidonException exception )
    {
        exceptions.add( exception );
        appendMessage( "%d) %s", exceptions.size(), exception.toString() );
        return this;
    }
    
    /**
     * @return the exceptions
     */
    public List<ZeidonException> getChildExceptions()
    {
        return exceptions;
    }
}
