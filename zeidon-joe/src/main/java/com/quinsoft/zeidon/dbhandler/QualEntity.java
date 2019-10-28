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

package com.quinsoft.zeidon.dbhandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.ActivateOptions.ActivateOrder;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * Keeps track of qualification information for an entity.
 */
public class QualEntity
{
    boolean                usesChildQualification;
    boolean                exclude;
    boolean                hasDoesNotExist;
    Integer                activateLimit;
    final EntityDef        entityDef;
    final List<QualAttrib> qualAttribs;
    LinkedHashMap<AttributeDef, ActivateOrder> ordering;

    /**
     * Keeps track of the EntityDefs that are used as part of this qualification.
     */
    final Set<EntityDef>   usesEntityDefs = new HashSet<>();

    /**
     * This keeps track if all the qualification on this entity are
     * keys and the opers are all '='.
     */
    private boolean keyQualification;

    /**
     * If not null, specifies custom SQL.
     */
    String openSql;
    List<AttributeDef> openSqlAttributeList = new ArrayList<>();
    List<String> openSqlQueryValues = new ArrayList<>();

    QualEntity(EntityInstance qualEntityInstance, EntityDef entityDef)
    {
        super();
        this.entityDef = entityDef;
        qualAttribs = new ArrayList<QualAttrib>();

        // For now we only support entities with a single key.
        keyQualification = entityDef.getKeys().size() == 1;

        // qualEntityInstance may be null if we're creating a qualEntity just for ordering.
        if ( qualEntityInstance != null )
        {
            AttributeInstance limitAttr = qualEntityInstance.getAttribute( "ActivateLimit" );
            if ( ! limitAttr.isNull() )
                activateLimit = limitAttr.getInteger();
        }
    }

    void addQualAttrib( QualAttrib qualAttrib )
    {
        qualAttribs.add( qualAttrib );

        if ( qualAttrib.entityDef != null && qualAttrib.entityDef != entityDef )
        {
            usesEntityDefs.add( qualAttrib.entityDef );
            usesChildQualification = true;

            // We're qualifying on a child entity so that's not a key.
            keyQualification = false;
        }
        else
        if ( qualAttrib.columnAttributeValue != null &&
             qualAttrib.columnAttributeValue.getEntityDef() != entityDef )
        {
            usesChildQualification = true;
            usesEntityDefs.add( qualAttrib.entityDef );
        }

        // Are we qualifying using a key?
        if ( qualAttrib.attributeDef != null && ! qualAttrib.attributeDef.isKey() )
        {
            switch ( qualAttrib.oper )
            {
                // Following operations are key operations.
                case "=":
                case "IN":
                    break;

                // All other operations are not exact key qualifications.
                default:
                    keyQualification = false;
            }
        }
    }

    protected boolean hasOrdering()
    {
        return ordering != null;
    }

    protected LinkedHashMap<AttributeDef, ActivateOrder> getOrdering()
    {
        return ordering;
    }

    protected void addOrderBy( AttributeDef attributeDef, boolean descending )
    {
        if ( ordering == null )
            ordering = new LinkedHashMap<>();

        if ( ordering.containsKey( attributeDef ) )
            throw new ZeidonException( "Ordering attribute %s specified twice", attributeDef );

        ordering.put( attributeDef, new ActivateOrder( attributeDef, descending ) );
    }

    /**
     * Check to see if the key is part of the order by and add it if
     * it is not.  Intended to be used by rolling pagination.
     */
    protected boolean checkForKeysInOrderBy()
    {
        assert entityDef.getKeys().size() > 0 : "Attemping rolling pagination on entity without a key.";

        boolean keyAdded = false;

        if ( ordering == null )
            ordering = new LinkedHashMap<>();

        for ( AttributeDef key : entityDef.getKeys() )
        {
            if ( ! ordering.containsKey( key ) )
            {
                keyAdded = true;
                ordering.put( key, new ActivateOrder( key, false ) );
            }
        }

        return keyAdded;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        if ( entityDef != null )
            sb.append("EntityDef = ").append( entityDef.getName() ).append( " " );

        if ( qualAttribs.size() > 0 )
            sb.append( StringUtils.join( qualAttribs, ", " ) );

        return sb.toString();
    }

    boolean isKeyQualification()
    {
        return keyQualification;
    }

    void setOpenSqlAttributeList( String attributeList )
    {
        if ( StringUtils.isBlank( attributeList ) )
            throw new ZeidonException( "Using OpenSQL in qualification requires OpenSQL_AttributeList" );

        openSqlAttributeList = new ArrayList<>();

        // Parse it out and create a map of the DataFields.
        String[] list = attributeList.split( "," );
        for ( String attrName : list )
        {
            attrName = attrName.trim();
            AttributeDef attributeDef = entityDef.getAttribute( attrName, false );
            if ( attributeDef == null )
                throw new ZeidonException( "Attribute %s specified in OpenSQL_AttributeList does not exist in entity %s",
                                           attrName, entityDef );

            openSqlAttributeList.add( attributeDef );
        }
    }

    void setOpenSqlAttributeList( View qual )
    {
        EntityCursor attributeList = qual.cursor( "CustomQueryAttribute" );
        if ( attributeList.checkExistenceOfEntity().isEmpty() )
            throw new ZeidonException( "Using CustomQuery in qualification requires Attribute list" );

        openSqlAttributeList = new ArrayList<>();

        for ( EntityInstance attributeEi : attributeList.eachEntity() )
        {
            String attrName = attributeEi.getAttribute( "Name" ).getString().trim();
            AttributeDef attributeDef = entityDef.getAttribute( attrName, false );
            if ( attributeDef == null )
                throw new ZeidonException( "Attribute %s specified in CustomQuery Attribute list does not exist in entity %s",
                                           attrName, entityDef );

            openSqlAttributeList.add( attributeDef );
        }

        EntityCursor queryValue = qual.cursor( "CustomQueryValue" );
        if ( queryValue.checkExistenceOfEntity().isSet() )
        {
            openSqlQueryValues = new ArrayList<>();
            for ( EntityInstance value : queryValue.eachEntity() )
                openSqlQueryValues.add( value.getAttribute( "Value" ).getString() );
        }
    }
}