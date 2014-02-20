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

    Copyright 2009-2012 QuinSoft
 */
/**
 * 
 */
package com.quinsoft.zeidon.objectdefinition;

import java.util.ArrayList;
import java.util.List;

import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;

/**
 * @author DG
 *
 */
public class RelRecord implements PortableFileAttributeHandler
{
    public final static char MANY_TO_MANY     = '2';
    public final static char MANY_TO_ONE      = 'M';
    public final static char ONE_TO_MANY      = 'O';
    public final static char PARENT_IS_SOURCE = 'O';  // 'O' originally meant 'owner'.
    public final static char CHILD_IS_SOURCE  = 'M';  // 'M' originally meant 'member'.
    
    private char   relationshipType;
    private String recordName;
    private List<RelField> relFields = new ArrayList<RelField>();
    private DataRecord dataRecord;
    
    RelRecord(DataRecord dataRecord)
    {
        this.dataRecord = dataRecord;
    }

    @Override
    public void setAttribute(PortableFileReader reader )
    {
        String attributeName = reader.getAttributeName();
        if ( attributeName.equals( "OWNER_MEMB" ) )
            relationshipType = reader.getAttributeValue().charAt( 0 );
        else
        if ( attributeName.equals( "RECNAME" ) )
            recordName = reader.getAttributeValue();
    }

    public char getRelationshipType()
    {
        return relationshipType;
    }

    public String getRecordName()
    {
        return recordName;
    }

    void addRelField( RelField relField )
    {
        relFields.add( relField );
    }
    
    public List<RelField> getRelFields()
    {
        return relFields;
    }
    
    @Override
    public String toString()
    {
        return dataRecord.toString() + ":" + recordName;
    }
}
