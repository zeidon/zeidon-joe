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
package com.quinsoft.zeidon.objectbrowser;

import java.awt.Component;
import java.awt.Container;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * This class can be used to save/restore Swing windows and components.
 *
 * @author dgc
 *
 */
public class WindowBoundsRestorer
{
    private final String filename;
    private Properties properties;

    public WindowBoundsRestorer( String filename )
    {
        this.filename = filename;
    }

    private void setBounds( String key, Component c )
    {
        key = key + c.getName();

        String position = properties.getProperty( key );
        if ( c.getName() != null && ! StringUtils.isBlank( position ) )
        {
            String[] nums = position.split( "," );
            c.setBounds( Integer.parseInt( nums[0] ), Integer.parseInt( nums[1] ),
                         Integer.parseInt( nums[2] ), Integer.parseInt( nums[3] ) );
        }

        if ( c instanceof Container )
        {
            key = key + "/";
            Container container = (Container) c;
            for ( Component child : container.getComponents() )
               setBounds( key, child );
        }
    }

    /**
     * Loads the properties from the .xml file and sets all named windows with a matching
     * name.
     *
     * @param component Any component in the Swing app.  The top-most container will be
     * determined from this component.
     */
    public void restore( Component component )
    {
        properties = new Properties();
        InputStream is = null;
        try
        {
            is = new FileInputStream( filename );
            properties.loadFromXML( is );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            return;
        }
        finally
        {
            IOUtils.closeQuietly( is );
        }

        Component top = component;
        while ( top.getParent() != null )
            top = top.getParent();

        setBounds( "", top );
    }

    private void getBounds( String key, Component c )
    {
        key = key + c.getName();
        String position = String.format( "%d,%d,%d,%d", c.getX(), c.getY(), c.getWidth(), c.getHeight() );
        properties.setProperty( key, position );
        if ( c instanceof Container )
        {
            key = key + "/";
            Container container = (Container) c;
            for ( Component child : container.getComponents() )
                getBounds( key, child );
        }
    }

    public void save( Component component )
    {
        Component top = component;
        while ( top.getParent() != null )
            top = top.getParent();

        properties = new Properties();
        getBounds( "", top );

        OutputStream os = null;
        try
        {
            os = new FileOutputStream( filename );
            properties.storeToXML( os, "Browser" );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly( os );
        }
    }
}
