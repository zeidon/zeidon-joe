package com.quinsoft.zeidon.standardoe;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.AbstractOptionsConfiguration;
import com.quinsoft.zeidon.StreamFormat;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
import com.quinsoft.zeidon.objectdefinition.ViewOd;

/**
 * Data and methods common to File DB activate and commit.
 * @author dgc
 *
 */
class FileDbUtils
{
    private final AbstractOptionsConfiguration options;
    private final String                       directoryName;
    private final StreamFormat                 streamFormat;

    /**
     * If true, the the ioServerUrl specifies a filename instead of a directory
     * name.  In that case all activates take place from the same file.
     */
    private final boolean isFile;

    FileDbUtils( AbstractOptionsConfiguration options )
    {
        this.options = options;
        String url = this.options.getOiSourceUrl();
        String workstring;

        if ( url.startsWith( "file:" ) )
            workstring = url.substring( 5 );
        else
        if ( url.startsWith( "resource:" ) )
            workstring = url.substring( 9 );
        else
            throw new ZeidonException("File DB Error: oiSourceUrl doesn't start with 'file:' or 'resource:'.  URL = %s", url );

        if ( workstring.startsWith( "xml:" ) )
        {
            streamFormat = StreamFormat.XML;
            directoryName = workstring.substring( 4 );
        }
        else
        if ( workstring.startsWith( "json:" ) )
        {
            streamFormat = StreamFormat.JSON;
            directoryName = workstring.substring( 5 );
        }
        else
        if ( workstring.toLowerCase().endsWith( ".xml" ) )
        {
            streamFormat = StreamFormat.XML;
            directoryName = workstring;
        }
        else
        if ( workstring.toLowerCase().endsWith( ".json" ) )
        {
            streamFormat = StreamFormat.JSON;
            directoryName = workstring;
        }
        else
        {
            streamFormat = StreamFormat.POR;
            directoryName = workstring;
        }

        if ( url.startsWith( "resource:" ) )
        {
            isFile = true;
        }
        else
        {
            File f = new File( directoryName );
            if ( ! f.exists() )
                throw new ZeidonException( "File DB directory does not exist: %s", directoryName );

            isFile = f.isFile();
        }
    }

    String genFilename( ViewOd viewOd, String qualifier )
    {
        if ( isFile )
            return directoryName;

        return directoryName + File.separator + viewOd.getName() + "_" + qualifier + streamFormat.getExtension();
    }

    /**
     * Generates the appropriate filename using the key value of the root.
     *
     * @param view
     * @return
     */
    String genKeyFilename( View view )
    {
        ViewOd viewOd = view.getViewOd();
        ViewEntity root = viewOd.getRoot();
        List<ViewAttribute> keys = root.getKeys();
        if ( keys.size() > 1 )
            throw new ZeidonException( "File DB only supports root entities with a single key." );

        ViewAttribute key = keys.get( 0 );
        String value = view.cursor( root ).getAttribute( key ).getString();
        String qualifier = genKeyQualifier( key, value );
        return genFilename( viewOd, qualifier );
    }

    String genKeyQualifier( ViewAttribute key, String value )
    {
        if ( StringUtils.isBlank( value ) )
            throw new ZeidonException( "Key value may not be null.  Key = %s.%s",
                                       key.getViewEntity().getName(), key.getName() );

        return key.getName() + "_" + value;
    }

    StreamFormat getStreamFormat()
    {
        return streamFormat;
    }

    String getDirectoryName()
    {
        return directoryName;
    }

    /**
     * Returns true if the oiSourceUrl specifies a filename instead of a directory.
     *
     * @return
     */
    boolean urlIsFile()
    {
        return isFile;
    }
}
