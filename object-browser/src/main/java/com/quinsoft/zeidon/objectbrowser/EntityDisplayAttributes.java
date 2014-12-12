/**
 * 
 */
package com.quinsoft.zeidon.objectbrowser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * This keeps track of what attributes we should display in EntitySquares
 * in the browser.
 *
 */
public class EntityDisplayAttributes
{
    /**
     * Map of the attributes to display.
     *      key = name of entity as App.LOD.Entity
     *      value = list of attributes.
     */
    private Properties properties;
    private final String filename;
    
    public EntityDisplayAttributes( String browserDir )
    {
        filename = browserDir + "/EntityDisplayAttributes.xml";
        restore();
    }

    /**
     * Loads the properties from the .xml file.
     */
    private void restore()
    {
        properties = new Properties();
        InputStream is = null;
        
        File file = new File( filename );
        if ( ! file.exists() )
            return;
        
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
    }
    
    /**
     * Save the properties to an XML file.
     */
    private void save()
    {
        OutputStream os = null;
        try
        {
            os = new FileOutputStream( filename );
            properties.storeToXML( os, "EntityDisplayAttributes" );
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
    
    private String getProperty( EntityDef entityDef )
    {
        String attributes = properties.getProperty( entityDef.toString() );
        if ( attributes == null )
            return "";
        
        return attributes;
        
    }

    private String getProperty( AttributeDef attributeDef )
    {
        return getProperty( attributeDef.getEntityDef() );
    }

    private void putProperty( AttributeDef attributeDef, String value )
    {
        EntityDef entityDef = attributeDef.getEntityDef();
        properties.setProperty( entityDef.toString(), value );
        
    }
    
    void addAttribute( AttributeDef attributeDef )
    {
        String attributes = getProperty( attributeDef );
        if ( ! StringUtils.isBlank( attributes ) && ! attributes.endsWith( ";" ))
            attributes = attributes + ";";
        
        attributes += attributeDef.getName();
        putProperty( attributeDef, attributes );
        save();
    }
    
    void removeAttribute( AttributeDef attributeDef )
    {
        String attributes = getProperty( attributeDef );
        if ( StringUtils.isBlank( attributes ) )
            return;
        
        String[] array = split( attributes );
        for ( int i = 0; i < array.length; i++ )
        {
            if ( array[i].equals( attributeDef.getName() ) )
            {
                array[i] = null;
                break;
            }
        }
        
        String newString = Joiner.on( ";" ).skipNulls().join( array );
        putProperty( attributeDef, newString );
        save();
    }
    
    private String[] split( String property )
    {
        return property.split( ";" );
    }
    
    private boolean contains( String property, String attributeName )
    {
        for ( String s : split( property ) )
        {
            if ( s.equals( attributeName ) )
                return true;
        }
        
        return false;
    }
    
    boolean containsAttribute( AttributeDef attributeDef )
    {
        String attributes = getProperty( attributeDef );
        if ( StringUtils.isBlank( attributes ) )
            return false;
        
        return contains( attributes, attributeDef.getName() );
    }
    
    List<AttributeDef> getAttributeList( EntityDef entityDef )
    {
        String prop = getProperty( entityDef );
        if ( StringUtils.isBlank( prop ) )
            return entityDef.getKeys();
        
        String[] attrs = split( prop );
        List<AttributeDef> list = new ArrayList<>();
        for ( String attr : attrs )
        {
            if ( StringUtils.isBlank( attr ) )
                continue;
            
            AttributeDef a = entityDef.getAttribute( attr, false );
            if ( a != null )
                list.add( a );
        }
        
        if ( list.size() == 0 )
            return entityDef.getKeys();
        
        return list;
    }
    
    void printAttributes( AttributeDef attributeDef )
    {
        String attributes = getProperty( attributeDef );
        System.out.println( "Attributes = " + attributes );
    }
}
