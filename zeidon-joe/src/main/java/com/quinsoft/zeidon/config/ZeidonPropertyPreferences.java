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

import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.text.StrLookup;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.ZeidonInputStream;

/**
 * Reads Zeidon preferences from a .properties file.  Since properties aren't broken out
 * into multiple sections, the group name and key are concatenated with a period to create
 * a single property key.  For example, the call:
 *
 *      get( "MyGroup", "MyKey", "default" )
 *
 * will look for a property named "MyGroup.MyKey".
 *
 * @author dgc
 *
 */
public class ZeidonPropertyPreferences implements ZeidonPreferences
{
    private static final Logger LOG = LoggerFactory.getLogger( ZeidonPropertyPreferences.class );

    private final String     filename;
    private       Properties properties;
    private       String sourceDescription;

    /**
     * Used to translate environment variables in property values.
     */
    private final StrSubstitutor substitutor = new StrSubstitutor( StrLookup.systemPropertiesLookup());

    public ZeidonPropertyPreferences( String filename, String jmxAppName )
    {
        this.filename = substitutor.replace( filename );
        reload();
        new JmxZeidonPreferences( this, "com.quinsoft.zeidon:type=ZeidonPropertyPreferences", jmxAppName, filename );
    }

    @Override
    public String get( String groupName, String key, String defaultValue )
    {
        String fullKey = groupName + "." + key;
        String str = properties.getProperty( fullKey, defaultValue );
        str = substitutor.replace( str );
        return str;
    }

    @Override
    public void reload()
    {
        ZeidonInputStream stream = null;
        try
        {
            properties = new Properties();
            stream = JoeUtils.getInputStream( null, filename, this.getClass().getClassLoader() );
            if ( stream == null )
                throw new ZeidonException( "Couldn't find property file" );

            LOG.info( "Opening properties from: " + stream.getDescription() );
            properties.load( stream );
            sourceDescription = stream.getDescription();
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( filename );
        }
        finally
        {
            IOUtils.closeQuietly( stream );
        }
    }

    @Override
    public String getAllProperties()
    {
        StringBuilder builder = new StringBuilder();
        for ( Object key : properties.keySet() )
            builder.append( key ).append( "=" ).append( properties.getProperty( key.toString() ) ).append( "\n" );

        return builder.toString();
    }

    @Override
    public String getSourceDescription()
    {
        return sourceDescription;
    }

    @Override
    public ZeidonPreferences set( String groupName, String key, String value )
    {
        String fullKey = groupName + "." + key;
        properties.setProperty( fullKey, value );
        return this;
    }
}
