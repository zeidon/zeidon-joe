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
package com.quinsoft.zeidon;

import java.util.Collection;

import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * The class for retrieving information about a Zeidon application.
 *
 */
public interface Application
{
    String getName();

    /**
     * Return the LodDef; load it from the .xod if necessary.  The LodDef
     * will be cached indefinitely.
     *
     * @param taskQual - Used for logging only; specifies the task to perform logging.
     * @param name - Name of the LodDef.
     *
     * @return the LodDef
     */
    LodDef getLodDef( TaskQualification taskQual, String name );

    /**
     * Returns the path of the directory that holds the .XOD files for this application.
     * The OE will use this path when attempting to load LodDefs.
     *
     * It may be a relative path, a absolute path, or a path on the classpath.  In standard
     * use the path will reference a directory in the classpath (e.g. in a .jar file).
     *
     * @return path to .XOD files.
     */
    String getObjectDir();

    /**
     * Returns the default Java package name for this application.
     *
     * @return default package name.
     */
    String getPackage();

    /**
     * Get the domain by name for this application.
     * @param name domain name
     * @return Domain object.
     */
    Domain getDomain( String name );

    /**
     *
     * @param name
     * @return
     */
    View getViewByName( String name );
    void dropNameForView( String name, View view );
    void dropView( View view );
    Collection<String> getAllViewNames(View view);
    Collection<? extends View> getAllNamedViews();
    void setNameForView( String name, View view );
    Lockable getNamedLock( String name );
    boolean isSystemApp();

    /**
     * If true, then this application views null strings equal to empty strings.
     * I.e. null == "".
     *
     * Default is true.
     *
     * @return
     */
    boolean nullStringEqualsEmptyString();
}
