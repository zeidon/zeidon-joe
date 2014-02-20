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
package com.quinsoft.zeidon;

import java.util.Collection;

import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.ViewOd;

/**
 * @author DG
 *
 */
public interface Application
{
    String getName();
    
    /**
     * Return the view OD; load it from the .xod if necessary.
     * 
     * @param taskQual - Used for logging only.
     * @param name - Name of the ViewOD.
     * @return
     */
    ViewOd getViewOd( TaskQualification taskQual, String name );
    
    String getObjectDir();
    String getBinDir();
    String getLocalDir();
    String getSharedDir();
    String getSourceDir();
    String getQlplrDir();
    String getPackage();
    
    Domain getDomain( String name );
    View getViewByName( String name );
    void dropNameForView( String name, View view );
    void dropView( View view );
    Collection<String> getAllViewNames(View view);
    Collection<? extends View> getAllNamedViews();
    void setNameForView( String name, View view );
    Lockable getNamedLock( String name );
    
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
