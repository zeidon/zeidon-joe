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

import java.util.EnumSet;
import java.util.List;

import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * @author DG
 *
 */
public interface TaskQualification extends Lockable
{
    Application getApplication();
    Application getApplication( String appName );
    Task getTask();
    Task getSystemTask();
    ZeidonLogger log();
    ZeidonLogger dblog();

    View activateEmptyObjectInstance( String lodDefName ) throws UnknownLodDefException;
    View activateEmptyObjectInstance( String lodDefName, String appName ) throws UnknownLodDefException;
    View activateEmptyObjectInstance( String lodDefName, Application app ) throws UnknownLodDefException;
    View activateEmptyObjectInstance( LodDef lodDef );

    View activateObjectInstance( ActivateOptions options ) throws UnknownLodDefException;
    View activateObjectInstance( String lodDefName, View qual, EnumSet<ActivateFlags> control ) throws UnknownLodDefException;
    View activateObjectInstance( LodDef lodDef, View qual, EnumSet<ActivateFlags> control );
    View activateObjectInstance( String lodDefName, View qual, ActivateOptions options );

    View activateOiFromFile( String lodDefName, String filename ) throws UnknownLodDefException;
    View activateOiFromFile( String lodDefName, String filename, EnumSet<ActivateFlags> control ) throws UnknownLodDefException;
    View activateOiFromFile( LodDef lodDef, String filename, EnumSet<ActivateFlags> control );

    List<View> activateOisFromStream( DeserializeOi options ) throws UnknownLodDefException;
    SerializeOi serializeOi();
    DeserializeOi deserializeOi();

    View getViewByName( String name );
    View getViewByKey( long key );

    String readZeidonConfig( String group, String key );
    String readZeidonConfig( String group, String key, String defaultValue );
    String readZeidonConfig( String applicationName, String group, String key, String defaultValue );
    String readZeidonConfig( Application application, String group, String key, String defaultValue );
    ObjectEngine getObjectEngine();

    /**
     * Gets the temp directory for this task.  For now this uses java.io.tmpdir.
     */
    String getTempDirectory();
}
