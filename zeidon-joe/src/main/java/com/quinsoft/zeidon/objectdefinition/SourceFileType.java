/**
 *
 */
package com.quinsoft.zeidon.objectdefinition;

import com.quinsoft.zeidon.ZeidonException;

/**
 * The types of Zeidon operation source files.
 *
 * @author dgc
 *
 */
public enum SourceFileType
{
    VML, JAVA, SCALA, C;

    public static SourceFileType parse( String string )
    {
        switch ( string.toLowerCase().charAt( 0 ) )
        {
            case 'v': return VML;
            case 's': return SCALA;
            case 'j': return JAVA;
            case 'c': return C;
            default:  throw new ZeidonException( "Unknown SourceFileType: %s", string );
        }
    }
}
