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
public class RelField implements PortableFileAttributeHandler
{
    private String    fieldName;
    private int       srcToken;
    private int       relToken;
    private DataField srcDataField;
    private DataField relDataField;
    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler#setAttribute(com.quinsoft.zeidon.utils.PortableFileReader)
     */
    @Override
    public void setAttribute(PortableFileReader reader)
    {
        String attributeName = reader.getAttributeName();
        if ( attributeName.equals( "SRCXVAATT_TOK" ) )
            srcToken = Integer.parseInt( reader.getAttributeValue() );
        else
        if ( attributeName.equals( "RELXVAATT_TOK" ) )
            relToken = Integer.parseInt( reader.getAttributeValue() );
        else
        if ( attributeName.equals( "FLDNAME" ) )
            fieldName = reader.getAttributeValue();
    }

    public String getFieldName()
    {
        return fieldName;
    }

    int getSrcToken()
    {
        return srcToken;
    }

    int getRelToken()
    {
        return relToken;
    }

    public DataField getSrcDataField()
    {
        return srcDataField;
    }

    public DataField getRelDataField()
    {
        return relDataField;
    }

    void setSrcDataField(DataField srcDataField)
    {
        this.srcDataField = srcDataField;
    }

    void setRelDataField(DataField relDataField)
    {
        this.relDataField = relDataField;
    }
    
    @Override
    public String toString()
    {
        return relDataField + " = " + srcDataField;
    }
}
