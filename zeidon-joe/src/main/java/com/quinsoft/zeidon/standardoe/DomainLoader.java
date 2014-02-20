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

    Copyright 2009-2012 QuinSoft
 */

package com.quinsoft.zeidon.standardoe;

import java.lang.reflect.Constructor;
import java.util.Map;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.domains.DomainClassLoader;

/**
 * Loads a domain Java class.
 * 
 * @author DG
 *
 */
public class DomainLoader implements DomainClassLoader
{
    private final Class<?>[] constructorArgTypes = new Class<?>[] { Application.class, Map.class, Task.class };

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.domains.DomainClassLoader#loadDomain(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Domain loadDomain(Application application, Map<String, Object> domainProperties, Task task)
    {
        try
        {
            String className = (String) domainProperties.get( "JavaClass" );
            String domainName = (String) domainProperties.get( "Name" );
            Class<? extends Domain> cl = null;
            ClassLoader classLoader = getClass().getClassLoader();
            cl = (Class<? extends Domain>) classLoader.loadClass( className );
            Constructor<? extends Domain> constructor = cl.getConstructor( constructorArgTypes);
            Domain domain = constructor.newInstance( application, domainProperties, task );
            task.log().debug( "Loaded class %s for domain %s", className, domainName );
            return domain;
        }
        catch ( Throwable t )
        {
            throw ZeidonException.wrapException( t );
        }
    }
}
