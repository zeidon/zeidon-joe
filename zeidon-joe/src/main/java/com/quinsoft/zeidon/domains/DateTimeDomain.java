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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

/**
 * This domain handles any differences needed to deal with dates+time.
 * @author DG
 *
 */
public class DateTimeDomain extends DateDomain
{
    protected DateTimeFormatter defaultDateTimeFormatter = DateTimeFormat.forPattern( ObjectEngine.INTERNAL_DATE_STRING_FORMAT + "HHmmss");

    public DateTimeDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
    }

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
    {
        if ( internalValue == null )
            return super.convertToString( task, attributeDef, internalValue );

        return defaultDateTimeFormatter.print( (DateTime) internalValue );
    }
}
