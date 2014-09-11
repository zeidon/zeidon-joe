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
    private final Map<ViewAttribute, DataField> attributeMap = new HashMap<ViewAttribute, DataField>();
    private boolean joinable;

    DataRecord( EntityDef entityDef )
    {
        this.entityDef = entityDef;
    }

    @Override
    public void setAttribute(PortableFileReader reader)
    {
        String attributeName = reader.getAttributeName();
        if ( attributeName.equals( "RECNAME" ) )
            recordName = reader.getAttributeValue();
        else
        if ( attributeName.equals( "TYPE" ) )
            type = reader.getAttributeValue();
        else
        if ( attributeName.equals( "JOIN" ) )
            joinable = reader.getAttributeValue().toUpperCase().startsWith( "Y" );
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

    public DataField getDataField( ViewAttribute viewAttribute )
    {
        // We allow viewAttribute to be null.  Just return null.
        if ( viewAttribute == null )
            return null;

        DataField dataField = attributeMap.get( viewAttribute );
        if ( dataField == null )
            throw new ZeidonException( "ViewAttrib %s does not belong to record %s", viewAttribute, this );

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
            attributeMap.put( dataField.getViewAttribute(), dataField );

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
                        if ( relField.getSrcDataField().getViewAttribute().getEntityDef() != getEntityDef() )
                            relRecord.setParentRelField( relField );
                    }
                }
                else
                if ( relField.getRelDataField() != null )
                {
                    {
                        if ( relField.getRelDataField().getViewAttribute().getEntityDef() == getEntityDef() )
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
}
