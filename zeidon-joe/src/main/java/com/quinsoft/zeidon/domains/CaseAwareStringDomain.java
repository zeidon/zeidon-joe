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
package com.quinsoft.zeidon.domains;

import java.util.Map;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * A subclass of StringDomain that is aware of the case-sensitive flag
 * in the AttributeDef.
 *
 */
public class CaseAwareStringDomain extends StringDomain
{

    /**
     * @param application
     * @param domainProperties
     * @param task
     */
    public CaseAwareStringDomain( Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }


    /**
     * Override StringDomain compare to be aware of case sensitivity.
     */
    @Override
    public int compare(Task task, AttributeInstance attributeInstance, AttributeDef attributeDef, Object internalValue, Object externalValue)
    {
        Object value = convertExternalValue( task, attributeInstance, attributeDef, null, externalValue );
        String s1 = checkNullString( internalValue );
        String s2 = checkNullString( value );

        Integer rc = compareNull( task, attributeDef, s1, s2);
        if ( rc != null )
            return rc;

        int c;
        if ( attributeDef.isCaseSensitive() )
            c = s1.compareTo( s2 );
        else
            c = s1.compareToIgnoreCase( s2 );

        if ( c < 0 )
            return -1;
        if ( c > 0 )
            return 1;

        return 0;
    }
}
