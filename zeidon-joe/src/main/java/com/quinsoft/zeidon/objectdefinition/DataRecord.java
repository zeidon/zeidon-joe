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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;

/**
 * @author DG
 *
 */
public class DataRecord implements PortableFileAttributeHandler
{
    private String recordName;
    private String type;
    private final List<DataField> dataFields = new ArrayList<DataField>();
    private RelRecord relRecord;
    private final EntityDef entityDef;
    private final Map<AttributeDef, DataField> attributeMap = new HashMap<AttributeDef, DataField>();
    private boolean joinable;
    private boolean activateWithSingleSelect;

    DataRecord( EntityDef entityDef )
    {
        this.entityDef = entityDef;
    }

    @Override
    public void setAttribute(PortableFileReader reader)
    {
        String attributeName = reader.getAttributeName();
        switch ( attributeName )
        {
            case "RECNAME":     recordName = reader.getAttributeValue(); break;
            case "TYPE":        type = reader.getAttributeValue(); break;
            case "JOIN":        joinable = reader.valueStartsWith( "Y" ); break;
            case "ACTIVATEONE":
                activateWithSingleSelect = reader.valueStartsWith( "Y" );
                if ( activateWithSingleSelect )
                {
                    // Make sure all parents have activateWithSingleSelect turned on.
                    for ( EntityDef def = entityDef; def.getParent() != null; def = def.getParent() )
                    {
                        DataRecord dataRecord = def.getDataRecord();
                        if ( ! dataRecord.isActivateWithSingleSelect() && ! dataRecord.isJoinable() )
                        {
                            reader.getLogger().error( "Child DataRecord = %s\nParent DataRecord = %s",
                                                      this.toString(), dataRecord.toString() );
                            throw new ZeidonException( "Internal error.  Parent isn't set to ACTIVATESINGLE.  See logs for more" );
                        }
                    }
                }
                break;
        }
    }

    public EntityDef getEntityDef()
    {
        return entityDef;
    }

    public String getRecordName()
    {
        return recordName;
    }

    public String getType()
    {
        return type;
    }

    void addDataField( DataField dataField )
    {
        dataFields.add( dataField );
    }

    public DataField getDataField( AttributeDef attributeDef )
    {
        // We allow attributeDef to be null.  Just return null.
        if ( attributeDef == null )
            return null;

        DataField dataField = attributeMap.get( attributeDef );
        if ( dataField == null )
            throw new ZeidonException( "AttributeDef %s does not belong to record %s", attributeDef, this );

        return dataField;
    }

    public List<DataField> dataFields()
    {
        return dataFields;
    }

    public RelRecord getRelRecord()
    {
        return relRecord;
    }

    void setRelRecord(RelRecord relRecord)
    {
        this.relRecord = relRecord;
    }

    // Loops through all the rel fields and sets the src and tgt datafields.
    void setFields(EntityDef currentEntityDef)
    {
        for ( DataField dataField : dataFields )
            attributeMap.put( dataField.getAttributeDef(), dataField );

        if ( relRecord == null )
            return;

        Map<Integer, DataField> map = new HashMap<Integer, DataField>();
        for ( EntityDef ve = currentEntityDef; ve != null; ve = ve.getParent() )
        {
            DataRecord dataRecord = ve.getDataRecord();
            if ( dataRecord != null )
            {
                for ( DataField dataField : dataRecord.dataFields )
                {
                    map.put( dataField.getToken(), dataField );
                }
            }
        }

        for ( RelField relField : relRecord.getRelFields() )
        {
            if ( relField.getRelToken() != 0 )
                relField.setRelDataField( map.get( relField.getRelToken() ) );

            if ( relField.getSrcToken() != 0 )
                relField.setSrcDataField( map.get( relField.getSrcToken() ) );

            if ( relRecord.getRelationshipType().isManyToMany() )
            {
                if ( relField.getSrcDataField() != null )
                {
                    {
                        if ( relField.getSrcDataField().getAttributeDef().getEntityDef() != getEntityDef() )
                            relRecord.setParentRelField( relField );
                    }
                }
                else
                if ( relField.getRelDataField() != null )
                {
                    {
                        if ( relField.getRelDataField().getAttributeDef().getEntityDef() == getEntityDef() )
                            relRecord.setChildRelField( relField );
                    }
                }
            }

        }
    }

    public boolean isJoinable()
    {
        return joinable;
    }

    @Override
    public String toString()
    {
        return entityDef.toString() + "/" + recordName;
    }

    public boolean isActivateWithSingleSelect()
    {
        return activateWithSingleSelect;
    }
}
