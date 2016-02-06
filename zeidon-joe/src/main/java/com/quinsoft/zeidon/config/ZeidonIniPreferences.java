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
package com.quinsoft.zeidon.config;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.log4j.Logger;

import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.ZeidonInputStream;

/**
 * Reads preferences from zeidon.ini.
 *
 * @author dgc
 *
 */
public class ZeidonIniPreferences implements ZeidonPreferences
{
    private static final Logger LOG = Logger.getLogger( ZeidonIniPreferences.class );

    private final String        iniFileName;
    private       String        iniFileDesc;
    private       HierarchicalINIConfiguration iniConfObj;

    private static final StrSubstitutor strSub = new StrSubstitutor( System.getenv(), "${env.", "}" );

    public ZeidonIniPreferences( HomeDirectory homeDirectory, String jmxAppName )
    {
        iniFileName = FilenameUtils.concat( homeDirectory.getHomeDirectory(), "zeidon.ini" );
        loadZeidonIni();
        new JmxZeidonPreferences( this, "com.quinsoft.zeidon:type=ZeidonIniPreferences", jmxAppName, iniFileName );
    }

    /**
     * Explicitly set the file name to be loaded.
     * @param fileName
     * @param jmxAppName
     */
    public ZeidonIniPreferences( String fileName, String jmxAppName )
    {
        iniFileName = fileName;
        loadZeidonIni();
        new JmxZeidonPreferences( this, "com.quinsoft.zeidon:type=ZeidonIniPreferences", jmxAppName, iniFileName );
    }

    public ZeidonIniPreferences( InputStream iniFile )
    {
        iniFileName = "input stream";
        loadZeidonIni( iniFile );
    }

    @Override
    public String get( String groupName, String key, String defaultValue )
    {
        String str = iniConfObj.getSection( groupName ).getString( key, defaultValue );
        return strSub.replace( str );
    }

    private void loadZeidonIni()
    {
        LOG.info( "Opening Preferences: " + iniFileName );
        ZeidonInputStream iniFile = JoeUtils.getInputStream( null, iniFileName, getClass().getClassLoader() );
        try
        {
            loadZeidonIni( iniFile );
            iniFileDesc = iniFile.getDescription();
        }
        finally
        {
            IOUtils.closeQuietly( iniFile );
        }
    }

    private void loadZeidonIni( InputStream iniFile )
    {
        if ( iniFile == null )
            throw new ZeidonException( "Could not find zeidon.ini" );

        InputStreamReader reader = new InputStreamReader( iniFile );

        try
        {

            iniConfObj = new HierarchicalINIConfiguration();
            iniConfObj.load( reader );
            reader.close();
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( iniFileName );
        }
        finally
        {
            IOUtils.closeQuietly( reader );
        }
    }

    @Override
    public void reload()
    {
        loadZeidonIni();
    }

    @Override
    public String getAllProperties()
    {
        try
        {
            StringBuilder builder = new StringBuilder();

            for ( String sectionName : iniConfObj.getSections() )
            {
                builder.append( "[" ).append( sectionName ).append( "]\n" );
                for ( Iterator<String> iter = iniConfObj.getSection(sectionName).getKeys(); iter.hasNext(); )
                {
                    String key = iter.next();
                    builder.append( key ).append( "=" ).append( get( sectionName, key, null ) ).append( "\n" );
                }
                builder.append( "\n" );
            }

            return builder.toString();
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( iniFileName );
        }
    }

    @Override
    public String getSourceDescription()
    {
        return iniFileDesc;
    }

    @Override
    public ZeidonPreferences set( String groupName, String key, String value )
    {
        iniConfObj.setProperty( groupName + "." + key, value );
        return this;
    }
}
