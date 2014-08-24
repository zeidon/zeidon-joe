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

    /**
     *
     */
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
