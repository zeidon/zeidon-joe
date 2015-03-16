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
/**
 * 
 */
package com.quinsoft.zeidon.objectdefinition;

import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;

/**
 * @author DG
 *
 */
public class DataField implements PortableFileAttributeHandler
{
    private int           length;
    private String        name;
    private String        type;
    private int           token;
    private AttributeDef attributeDef;
    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler#setAttribute(com.quinsoft.zeidon.utils.PortableFileReader)
     */
    @Override
    public void setAttribute(PortableFileReader reader)
    {
        String attributeName = reader.getAttributeName();
        if ( attributeName.equals( "FLDNAME" ) )
            name = reader.getAttributeValue();
        else
        if ( attributeName.equals( "FLDLTH" ) )
            length = Integer.parseInt( reader.getAttributeValue() );
        else
        if ( attributeName.equals( "FLDTYPE" ) )
            type = reader.getAttributeValue();
        else
        if ( attributeName.equals( "XVAATT_TOK" ) )
            token = Integer.parseInt( reader.getAttributeValue() );
    }

    public int getLength()
    {
        return length;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public int getToken()
    {
        return token;
    }

    public AttributeDef getAttributeDef()
    {
        return attributeDef;
    }

    void setAttributeDef(AttributeDef attributeDef)
    {
        this.attributeDef = attributeDef;
    }
    
    @Override
    public String toString()
    {
        return attributeDef.toString() + "/" + name;
    }
}
