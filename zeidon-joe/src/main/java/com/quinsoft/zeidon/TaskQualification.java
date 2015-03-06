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
 * Objects that implement this interface can be used to find a Zeidon task.
 * A Zeidon task is the basic container for instantiated data (e.g. Object
 * Instances).
 *
 */
public interface TaskQualification extends Lockable
{
    /**
     * Returns the default application for this task.
     *
     * @return the default application for this task.
     */
    Application getApplication();

    /**
     * Retrieves an application by name.
     *
     * @param appName name of the application to retrieve.
     *
     * @return the application.
     */
    Application getApplication( String appName );

    /**
     * Get the task for this TaskQualificaiton.
     *
     * @return the task
     */
    Task getTask();

    /**
     * Returns the system task.
     * @return
     */
    Task getSystemTask();

    /**
     * Returns the general logger for this task.
     *
     * @return the logger for this task.
     */
    ZeidonLogger log();

    /**
     * Returns the logger used by the DBHandlers for logging statements, usually SQL.
     *
     * @return the DBHandler logger for this task.
     */
    ZeidonLogger dblog();

    /**
     * Activates an empty Object Instance for the specified LOD name.  The LOD must be
     * defined in the default application for this task.
     *
     * @param lodDefName name of the LOD
     *
     * @return an empty OI.
     *
     * @throws UnknownLodDefException
     */
    View activateEmptyObjectInstance( String lodDefName ) throws UnknownLodDefException;

    /**
     * Activates an empty Object Instance for the specified LOD name.  The LOD must be
     * defined in the application that is defined.
     *
     * @param lodDefName name of the LOD
     * @param appName name of the application that defines the LOD.
     *
     * @return an empty OI.
     *
     * @throws UnknownLodDefException
     */
    View activateEmptyObjectInstance( String lodDefName, String appName ) throws UnknownLodDefException;

    /**
     * Activates an empty Object Instance for the specified LOD name.  The LOD must be
     * defined in the application that is defined.
     *
     * @param lodDefName name of the LOD
     * @param app the application that defines the LOD.
     *
     * @return an empty OI.
     *
     * @throws UnknownLodDefException
     */
    View activateEmptyObjectInstance( String lodDefName, Application app ) throws UnknownLodDefException;

    /**
     * Activates an empty Object Instance for the specified LOD definition.  The application
     * does not need to be specified because it can be inferred from the LodDef.
     *
     * @param lodDefName name of the LOD
     *
     * @return an empty OI.
     */
    View activateEmptyObjectInstance( LodDef lodDef );

    /**
     * @deprecated use QualficationBuilder instead.
     */
    @Deprecated
    View activateObjectInstance( ActivateOptions options ) throws UnknownLodDefException;

    /**
     * @deprecated use QualficationBuilder instead.
     */
    @Deprecated
    View activateObjectInstance( String lodDefName, View qual, EnumSet<ActivateFlags> control ) throws UnknownLodDefException;

    /**
     * @deprecated use QualficationBuilder instead.
     */
    @Deprecated
    View activateObjectInstance( LodDef lodDef, View qual, EnumSet<ActivateFlags> control );

    /**
     * @deprecated use QualficationBuilder instead.
     */
    @Deprecated
    View activateObjectInstance( String lodDefName, View qual, ActivateOptions options );

    /**
     * @deprecated use task.deserializeOi instead.
     */
    @Deprecated
    View activateOiFromFile( String lodDefName, String filename ) throws UnknownLodDefException;

    /**
     * @deprecated use task.deserializeOi instead.
     */
    @Deprecated
    View activateOiFromFile( String lodDefName, String filename, EnumSet<ActivateFlags> control ) throws UnknownLodDefException;

    /**
     * @deprecated use task.deserializeOi instead.
     */
    @Deprecated
    View activateOiFromFile( LodDef lodDef, String filename, EnumSet<ActivateFlags> control );

    /**
     * Used internally to deseralize streams.
     */
    List<View> activateOisFromStream( DeserializeOi options ) throws UnknownLodDefException;

    /**
     * Creates a serializer for this task that can be used to serialize multiple OIs to a
     * stream.  See SerializeOi for more.
     *
     * @return a new serializer.
     */
    SerializeOi serializeOi();

    /**
     * Creates a deserializer for deserializing OIs from a stream.
     *
     * @return a new deserializer.
     */
    DeserializeOi deserializeOi();

    /**
     * Retrieves a view by name for this task.  Returns null if a name does not exist.
     *
     * @param name name of the view
     *
     * @return Named view or null
     */
    View getViewByName( String name );

    /**
     * Returns a view by a unique, internal key that is assigned to every view.
     *
     * @param key unique view key
     *
     * @return view or null.
     */
    View getViewByKey( long key );

    /**
     * Retrieves a value from the Zeidon configuration for the default application. The default configuration is
     * a zeidon.ini defined in the JVM classpath.  This can be overridden for each application
     * when the Object Engine is initialized.
     *
     * @param group name of the configuration group
     * @param key name of the configuration key
     *
     * @return configuration value as a string or null if not found.
     */
    String readZeidonConfig( String group, String key );

    /**
     * Retrieves a value from the Zeidon configuration for the default application. The default configuration is
     * a zeidon.ini defined in the JVM classpath.  This can be overridden for each application
     * when the Object Engine is initialized.
     *
     * @param group name of the configuration group
     * @param key name of the configuration key
     *
     * @return configuration value as a string or defaultValue if not found.
     */
    String readZeidonConfig( String group, String key, String defaultValue );

    /**
     * Retrieves a value from the Zeidon configuration for the specified application. The default configuration is
     * a zeidon.ini defined in the JVM classpath.  This can be overridden for each application
     * when the Object Engine is initialized.
     *
     * @param group name of the configuration group
     * @param key name of the configuration key
     *
     * @return configuration value as a string or null if not found.
     */
    String readZeidonConfig( String applicationName, String group, String key, String defaultValue );

    /**
     * Retrieves a value from the Zeidon configuration for the specified application. The default configuration is
     * a zeidon.ini defined in the JVM classpath.  This can be overridden for each application
     * when the Object Engine is initialized.
     *
     * @param group name of the configuration group
     * @param key name of the configuration key
     *
     * @return configuration value as a string or defaultValue if not found.
     */
    String readZeidonConfig( Application application, String group, String key, String defaultValue );

    /**
     * Get the Object Engine that contains this task.
     *
     * @return the object engine.
     */
    ObjectEngine getObjectEngine();

    /**
     * Gets the temp directory for this task.  For now this uses java.io.tmpdir.
     */
    String getTempDirectory();
}
