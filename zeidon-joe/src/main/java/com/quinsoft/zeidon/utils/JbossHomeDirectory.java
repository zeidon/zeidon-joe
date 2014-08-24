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
package com.quinsoft.zeidon.utils;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.config.HomeDirectoryFromString;

/**
 * If jboss conf directory is specified in properties, then use it.  Otherwise return
 * the string declared in the constructor.
 * 
 * @author dg
 *
 */
public class JbossHomeDirectory extends HomeDirectoryFromString
{
    public JbossHomeDirectory(String homeDirectory)
    {
        super( homeDirectory );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.config.HomeDirectory#getHomeDirectory()
     */
    @Override
    public String getHomeDirectory()
    {
        // See if jboss conf directory is specified.
        String path = System.getProperty("jboss.server.config.url");
        if ( StringUtils.isBlank( path ) )
            return super.getHomeDirectory();
        
        if ( path.startsWith( "file:" ) )
            path = path.substring( 5 );
        
        return path;
    }
}
