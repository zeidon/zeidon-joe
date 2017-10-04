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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.tree.DefaultExpressionEngine;
import org.apache.commons.configuration2.tree.DefaultExpressionEngineSymbols;
import org.apache.commons.configuration2.tree.NodeNameMatchers;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOG = LoggerFactory.getLogger( ZeidonIniPreferences.class );

    private final String        iniFileName;
    private       String        iniFileDesc;
    private       INIConfiguration iniConfObj;
    
    /**
     * This maps the lower-case section name to the section names in the INI file.
     * Used to ensure case-insensitivity.
     */
    private Map<String,String> sectionNameMap;

    private static final StrSubstitutor strSub = new StrSubstitutor( combinePropertiesAndEnvironment(), "${env.", "}" );

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

    @Override
    public String get( String groupName, String key, String defaultValue )
    {
        String str = iniConfObj.getSection( sectionNameMap.get( groupName.toLowerCase() ) ).getString( key, defaultValue );
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
            DefaultExpressionEngine engine = new DefaultExpressionEngine( DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS,
                                                                          NodeNameMatchers.EQUALS_IGNORE_CASE );

            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<INIConfiguration> builder = 
                    new FileBasedConfigurationBuilder<INIConfiguration>( INIConfiguration.class )
                    .configure( params.hierarchical().setFileName( "/tmp/zeidon.ini" ).setExpressionEngine( engine ) );
            iniConfObj = builder.getConfiguration();

            iniConfObj.read( reader );
            reader.close();
            
            sectionNameMap = new HashMap<>();
            for ( String sectionName : iniConfObj.getSections() )
                sectionNameMap.put( sectionName.toLowerCase(), sectionName );
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
    
    private static Map<String,String> combinePropertiesAndEnvironment()
    {
        Map<String,String> map = new HashMap<>( System.getenv() );
        for (final String name: System.getProperties().stringPropertyNames())
            map.put(name, System.getProperties().getProperty(name));
        
        return map;
    }
}
