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

import java.io.InputStream;
import java.util.EnumSet;

import com.quinsoft.zeidon.objectdefinition.ViewOd;
import com.quinsoft.zeidon.utils.BufferedBinaryStreamReader;

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

    View activateEmptyObjectInstance( String viewOdName ) throws UnknownViewOdException;
    View activateEmptyObjectInstance( String viewOdName, String appName ) throws UnknownViewOdException;
    View activateEmptyObjectInstance( String viewOdName, Application app ) throws UnknownViewOdException;
    View activateEmptyObjectInstance( String viewOdName, TaskQualification taskQual ) throws UnknownViewOdException;
    View activateEmptyObjectInstance( ViewOd viewOd );

    View activateObjectInstance( ActivateOptions options ) throws UnknownViewOdException;
    View activateObjectInstance( String viewOdName, View qual, EnumSet<ActivateFlags> control ) throws UnknownViewOdException;
    View activateObjectInstance( ViewOd viewOd, View qual, EnumSet<ActivateFlags> control );
    View activateObjectInstance( String viewOdName, View qual, ActivateOptions options );
    View activateObjectInstance( ViewOd viewOd, View qual, ActivateOptions options );

    View activateOiFromFile( String viewOdName, String filename ) throws UnknownViewOdException;
    View activateOiFromFile( String viewOdName, String filename, EnumSet<ActivateFlags> control ) throws UnknownViewOdException;
    View activateOiFromFile( String viewOdName, Application app, String filename, EnumSet<ActivateFlags> control ) throws UnknownViewOdException;
    View activateOiFromFile( String viewOdName, TaskQualification task, String filename, EnumSet<ActivateFlags> control ) throws UnknownViewOdException;
    View activateOiFromFile( ViewOd viewOd, String filename, EnumSet<ActivateFlags> control );

    /**
     * Activate an OI from the buffered stream reader.  Note: this doesn't close the stream so it can be used
     * to activate multiple OIs from a single stream.
     *
     * @param viewOdName
     * @param stream
     * @return
     * @throws UnknownViewOdException
     */
    View activateOiFromStream( String viewOdName, BufferedBinaryStreamReader stream ) throws UnknownViewOdException;
    View activateOiFromStream( String viewOdName, String applicationName, BufferedBinaryStreamReader stream ) throws UnknownViewOdException;

    View activateOiFromStream( String viewOdName, InputStream stream ) throws UnknownViewOdException;
    View activateOiFromStream( String viewOdName, InputStream stream, EnumSet<ActivateFlags> control ) throws UnknownViewOdException;
    View activateOiFromStream( String viewOdName, Application app, InputStream stream, EnumSet<ActivateFlags> control ) throws UnknownViewOdException;
    View activateOiFromStream( String viewOdName, TaskQualification task, InputStream stream, EnumSet<ActivateFlags> control ) throws UnknownViewOdException;
    View activateOiFromStream( ViewOd viewOd, InputStream stream, EnumSet<ActivateFlags> control );
    View activateOiFromStream( InputStream stream, EnumSet<ActivateFlags> control );

    View activateOiFromJsonStream( InputStream stream, EnumSet<ActivateFlags> control );

    View activateOiFromBlob( Application application, Blob blob, EnumSet<ActivateFlags> control );

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
