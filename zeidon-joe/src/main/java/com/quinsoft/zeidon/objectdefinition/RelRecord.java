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

import java.util.ArrayList;
import java.util.List;

import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;

/**
 * @author DG
 *
 */
public class RelRecord implements PortableFileAttributeHandler
{
    public enum RelationshipType
    {
        // 'O' originally meant 'owner', 'M' originally meant member.
        MANY_TO_MANY( '2' ), MANY_TO_ONE( 'M' ), ONE_TO_MANY( 'O' );

        private final char code;

        private RelationshipType( char code )
        {
            this.code = code;
        }

        public boolean isManyToMany()
        {
            return code == '2';
        }

        public boolean isManyToOne()
        {
            return code == 'M';
        }

        public boolean isOneToMany()
        {
            return code == 'O';
        }

        public boolean parentIsSource()
        {
            return isOneToMany();
        }

        public boolean childIsSource()
        {
            return isManyToOne();
        }
    }

    public final static RelationshipType MANY_TO_MANY     = RelationshipType.MANY_TO_MANY;
    public final static RelationshipType MANY_TO_ONE      = RelationshipType.MANY_TO_ONE;
    public final static RelationshipType ONE_TO_MANY      = RelationshipType.ONE_TO_MANY;
    public final static RelationshipType PARENT_IS_SOURCE = RelationshipType.ONE_TO_MANY;
    public final static RelationshipType CHILD_IS_SOURCE  = RelationshipType.MANY_TO_ONE;

    private RelationshipType   relationshipType;
    private String recordName;
    private final List<RelField> relFields = new ArrayList<RelField>();
    private final DataRecord dataRecord;

    /**
     * If the relationship is many-to-many, this is the relfield that references the parent
     * entity of the relationship.
     */
    private RelField parentRelField;

    RelRecord(DataRecord dataRecord)
    {
        this.dataRecord = dataRecord;
    }

    @Override
    public void setAttribute(PortableFileReader reader )
    {
        String attributeName = reader.getAttributeName();
        if ( attributeName.equals( "OWNER_MEMB" ) )
        {
            switch ( reader.getAttributeValue().charAt( 0 ) )
            {
                case 'O': relationshipType = RelationshipType.ONE_TO_MANY; break;
                case '2': relationshipType = RelationshipType.MANY_TO_MANY; break;
                case 'M': relationshipType = RelationshipType.MANY_TO_ONE; break;
                default: throw new ZeidonException( "Unknown relationship type %s", reader.getAttributeValue() );
            }
        }
        else
        if ( attributeName.equals( "RECNAME" ) )
            recordName = reader.getAttributeValue();
    }

    public RelationshipType getRelationshipType()
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

    public RelField getParentRelField()
    {
        assert relationshipType.isManyToMany() : "Illegal attempt to get parent rel field";
        return parentRelField;
    }

    public void setParentRelField( RelField parent )
    {
        parentRelField = parent;
    }
}
