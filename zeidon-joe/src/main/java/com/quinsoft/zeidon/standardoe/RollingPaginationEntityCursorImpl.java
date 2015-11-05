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
package com.quinsoft.zeidon.standardoe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.ActivateOptions.ActivateOrder;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.EntityIterator;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.utils.QualificationBuilder;

/**
 * This cursor is used for rolling pagination.
 *
 */
public class RollingPaginationEntityCursorImpl extends EntityCursorImpl
{
    private ActivateOptions activateOptions;
    
    /**
     * Keep track of what we think is the last root EI.  We will use this when we
     * try to load the next page.
     */
    private EntityInstanceImpl lastEi;

    /**
     * @param viewCursor
     * @param entityDef
     * @param options
     */
    public RollingPaginationEntityCursorImpl( ViewCursor viewCursor, EntityDef entityDef, ActivateOptions options )
    {
        super( viewCursor, entityDef );
        assert entityDef.getParent() == null : "Rolling pagination only allowed on the root";
        this.activateOptions = options;
    }

    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setCursor(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public CursorResult setCursor( EntityInstance targetInstance )
    {
        if ( targetInstance == null )
            throw new ZeidonException("Cannot set a cursor to null.");

        // Convert the targetInstance to an EntityInstanceImpl
        EntityInstanceImpl newInstance = (EntityInstanceImpl) targetInstance.getEntityInstance();

        if ( newInstance.getObjectInstance() != getObjectInstance() )
        {
            // We're changing the cursor to a new OI.
            getViewCursor().replaceObjectInstance( newInstance.getObjectInstance() );
        }
            
        return super.setCursor( newInstance );
    }


    /**
     * This gets called when the cursor has reached the end of the current page 
     * and we want to load the next page.
     * 
     */
    private EntityInstance loadNextPage()
    {
        lastEi = lastEi.getLastTwin();
        
        // We are going to load the next page.  To do this we need to add some qualification
        // to the original qual.  Clone the original so we can make the changes.
        View originalQual = activateOptions.getQualificationObject();
        View qual = originalQual.activateOiFromOi( ActivateFlags.MULTIPLE );
        
        // Create a qual builder to make it easier to manipulate.
        QualificationBuilder builder = new QualificationBuilder( getView(), qual );
        builder.setLodDef( getLodDef() );
        builder.forEntity( getEntityDef().getName() );
        
        // If qual already has attributes then we need to add AND.
        if ( builder.hasQualAttrib() )
            builder.addAttribQual( "AND" );

        assert activateOptions.getRootGetActivateOrdering() != null : "Root ordering was not set";

        // We need to add qualification to load the next page of entities that
        // occur after lateEi.  To do this we'll add "AND column > lastEi.column"
        // for each attribute that was used in the ORDER BY.  Handling multiple
        // order by clauses is tricky; we have to add 
        // "AND (column1 > k1 OR (column1 = k1 and column2 > k2 ) )".
        builder.addAttribQual( "(" );
        boolean firstAttr = true;
        List<AttributeDef> prevAttrs = new ArrayList<>(); // Keep track of attrs we've added.
        for ( ActivateOrder order : activateOptions.getRootGetActivateOrdering().values() )
        {
            AttributeDef attributeDef = order.attributeDef;
            EntityDef entityDef = attributeDef.getEntityDef();
            
            if ( ! firstAttr )
            {
                builder.addAttribQual( "OR" );
                builder.addAttribQual( "(" );
            }
            
            for ( AttributeDef prevAttr : prevAttrs )
            {
                String attribValue = lastEi.getAttribute( prevAttr ).getString();
                builder.addAttribQual( entityDef.getName(), prevAttr.getName(), "=", attribValue );
                builder.addAttribQual( "AND" );
            }

            if ( lastEi.getAttribute( attributeDef ).isNull() )
                throw new ZeidonException("Rolling pagination: 'order by' by attributes with null values is not supported." )
                                 .prependAttributeDef( attributeDef );
            
            String attribValue = lastEi.getAttribute( attributeDef ).getString();
            builder.addAttribQual( entityDef.getName(), attributeDef.getName(), order.descending ? "<" : ">", attribValue );

            if ( ! firstAttr )
            {
                builder.addAttribQual( ")" );
            }
            
            prevAttrs.add( order.attributeDef );
            firstAttr = false;
        }
        
        builder.addAttribQual( ")" );

        activateOptions.setQualificationObject( qual );
        View newView = getView().activateObjectInstance( activateOptions );
        
        // Reset the qual back to the original qual so we can recreate the new attributes
        // from the original qual.
        activateOptions.setQualificationObject( originalQual );
        
        EntityCursor rootCursor = newView.cursor( getEntityDef() );
        if ( rootCursor.getEntityCount() == 0 )
            return null;
        
        EntityInstance ei = newView.cursor( getEntityDef() ).getEntityInstance();
        return ei;
    }

    @Override
    public CursorResult setNext()
    {
        CursorResult result = super.setNext();
        if ( result.isSet() )
        {
            lastEi = getEntityInstance();
            return result;
        }

        EntityInstance ei = loadNextPage();
        if ( ei == null )
            return CursorResult.UNCHANGED;

        setCursor( ei );
        return CursorResult.SET;
    }

    @Override
    public EntityIterator<EntityInstanceImpl> eachEntity()
    {
        return new RollingIterator();
    }

    @Override
    public EntityIterator<? extends EntityInstance> eachEntity( String scopingEntityName )
    {
        throw new ZeidonException( "Scoping entity not allowed on the root" );
    }

    @Override
    public EntityIterator<? extends EntityInstance> eachEntity( EntityDef scopingEntity )
    {
        throw new ZeidonException( "Scoping entity not allowed on the root" );
    }

    @Override
    public EntityIterator<? extends EntityInstance> allEntities()
    {
        throw new ZeidonException( "Method allEntities() is not supported on root of rolling pagination" );
    }

    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#getPrevHier()
     */
    @Override
    EntityCursorImpl getPrevHier()
    {
        // TODO Auto-generated method stub
        return super.getPrevHier();
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#getNextHierCursor()
     */
    @Override
    EntityCursorImpl getNextHierCursor()
    {
        // TODO Auto-generated method stub
        return super.getNextHierCursor();
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#deleteAll()
     */
    @Override
    public CursorResult deleteAll()
    {
        // TODO Auto-generated method stub
        return super.deleteAll();
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setFirst()
     */
    @Override
    public CursorResult setFirst()
    {
        // TODO Auto-generated method stub
        return super.setFirst();
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setFirst(java.lang.String)
     */
    @Override
    public CursorResult setFirst( String scopingEntityName )
    {
        // TODO Auto-generated method stub
        return super.setFirst( scopingEntityName );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setFirst(com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public CursorResult setFirst( EntityDef scopingEntity )
    {
        // TODO Auto-generated method stub
        return super.setFirst( scopingEntity );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setFirst(java.lang.String, java.lang.Object)
     */
    @Override
    public CursorResult setFirst( String attributeName, Object value )
    {
        // TODO Auto-generated method stub
        return super.setFirst( attributeName, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setFirst(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public CursorResult setFirst( AttributeDef attribute, Object value )
    {
        // TODO Auto-generated method stub
        return super.setFirst( attribute, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setFirst(java.lang.String, java.lang.Object, java.lang.String)
     */
    @Override
    public CursorResult setFirst( String attributeName, Object value, String scopingEntityName )
    {
        // TODO Auto-generated method stub
        return super.setFirst( attributeName, value, scopingEntityName );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setFirst(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object, com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public CursorResult setFirst( AttributeDef attribute, Object value, EntityDef scopingEntity )
    {
        // TODO Auto-generated method stub
        return super.setFirst( attribute, value, scopingEntity );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setNext(java.lang.String, java.lang.Object)
     */
    @Override
    public CursorResult setNext( String attributeName, Object value )
    {
        // TODO Auto-generated method stub
        return super.setNext( attributeName, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setNext(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public CursorResult setNext( AttributeDef attribute, Object value )
    {
        // TODO Auto-generated method stub
        return super.setNext( attribute, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setNext(java.lang.String, java.lang.Object, java.lang.String)
     */
    @Override
    public CursorResult setNext( String attributeName, Object value, String scopingEntityName )
    {
        // TODO Auto-generated method stub
        return super.setNext( attributeName, value, scopingEntityName );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setNext(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object, com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public CursorResult setNext( AttributeDef attribute, Object value, EntityDef scopingEntity )
    {
        // TODO Auto-generated method stub
        return super.setNext( attribute, value, scopingEntity );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setLast()
     */
    @Override
    public CursorResult setLast()
    {
        // TODO Auto-generated method stub
        return super.setLast();
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setPrevContinue()
     */
    @Override
    public CursorResult setPrevContinue()
    {
        // TODO Auto-generated method stub
        return super.setPrevContinue();
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setByEntityKey(long)
     */
    @Override
    public CursorResult setByEntityKey( long entityKey )
    {
        // TODO Auto-generated method stub
        return super.setByEntityKey( entityKey );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setPosition(int)
     */
    @Override
    public CursorResult setPosition( int position )
    {
        // TODO Auto-generated method stub
        return super.setPosition( position );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setPosition(int, java.lang.String)
     */
    @Override
    public CursorResult setPosition( int position, String scopingEntityName )
    {
        // TODO Auto-generated method stub
        return super.setPosition( position, scopingEntityName );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#orderEntities(java.lang.String)
     */
    @Override
    public void orderEntities( String orderKeys )
    {
        // TODO Auto-generated method stub
        super.orderEntities( orderKeys );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#orderEntities(java.util.Comparator)
     */
    @Override
    public void orderEntities( Comparator<? extends EntityInstance> comparator )
    {
        // TODO Auto-generated method stub
        super.orderEntities( comparator );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setLast(java.lang.String)
     */
    @Override
    public CursorResult setLast( String scopingEntityName )
    {
        // TODO Auto-generated method stub
        return super.setLast( scopingEntityName );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setLast(com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public CursorResult setLast( EntityDef scopingEntity )
    {
        // TODO Auto-generated method stub
        return super.setLast( scopingEntity );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setLast(java.lang.String, java.lang.Object)
     */
    @Override
    public CursorResult setLast( String attributeName, Object value )
    {
        // TODO Auto-generated method stub
        return super.setLast( attributeName, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setLast(java.lang.String, java.lang.Object, java.lang.String)
     */
    @Override
    public CursorResult setLast( String attributeName, Object value, String scopingEntityName )
    {
        // TODO Auto-generated method stub
        return super.setLast( attributeName, value, scopingEntityName );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setLast(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public CursorResult setLast( AttributeDef attributeDef, Object value )
    {
        // TODO Auto-generated method stub
        return super.setLast( attributeDef, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setLast(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object, com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public CursorResult setLast( AttributeDef attribute, Object value, EntityDef scopingEntity )
    {
        // TODO Auto-generated method stub
        return super.setLast( attribute, value, scopingEntity );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setPrev()
     */
    @Override
    public CursorResult setPrev()
    {
        // TODO Auto-generated method stub
        return super.setPrev();
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setPrev(java.lang.String)
     */
    @Override
    public CursorResult setPrev( String scopingEntityName )
    {
        // TODO Auto-generated method stub
        return super.setPrev( scopingEntityName );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setPrev(com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public CursorResult setPrev( EntityDef scopingEntity )
    {
        // TODO Auto-generated method stub
        return super.setPrev( scopingEntity );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setPrev(java.lang.String, java.lang.Object, java.lang.String)
     */
    @Override
    public CursorResult setPrev( String attributeName, Object value, String scopingEntityName )
    {
        // TODO Auto-generated method stub
        return super.setPrev( attributeName, value, scopingEntityName );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setPrev(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object, com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public CursorResult setPrev( AttributeDef attribute, Object value, EntityDef scopingEntity )
    {
        // TODO Auto-generated method stub
        return super.setPrev( attribute, value, scopingEntity );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setPrev(java.lang.String, java.lang.Object)
     */
    @Override
    public CursorResult setPrev( String attributeName, Object value )
    {
        // TODO Auto-generated method stub
        return super.setPrev( attributeName, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setPrev(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public CursorResult setPrev( AttributeDef attribute, Object value )
    {
        // TODO Auto-generated method stub
        return super.setPrev( attribute, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setFirstWithinOi()
     */
    @Override
    public CursorResult setFirstWithinOi()
    {
        // TODO Auto-generated method stub
        return super.setFirstWithinOi();
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#hasPrev()
     */
    @Override
    public boolean hasPrev()
    {
        // TODO Auto-generated method stub
        return super.hasPrev();
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setFirstWithinOi(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public CursorResult setFirstWithinOi( AttributeDef attributeDef, Object value )
    {
        // TODO Auto-generated method stub
        return super.setFirstWithinOi( attributeDef, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setLastWithinOi()
     */
    @Override
    public CursorResult setLastWithinOi()
    {
        // TODO Auto-generated method stub
        return super.setLastWithinOi();
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setLastWithinOi(java.lang.String, java.lang.Object)
     */
    @Override
    public CursorResult setLastWithinOi( String attributeName, Object value )
    {
        // TODO Auto-generated method stub
        return super.setLastWithinOi( attributeName, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setPrevWithinOi()
     */
    @Override
    public CursorResult setPrevWithinOi()
    {
        // TODO Auto-generated method stub
        return super.setPrevWithinOi();
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setPrevWithinOi(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public CursorResult setPrevWithinOi( AttributeDef attributeDef, Object value )
    {
        // TODO Auto-generated method stub
        return super.setPrevWithinOi( attributeDef, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setFirstWithinOi(java.lang.String, java.lang.Object)
     */
    @Override
    public CursorResult setFirstWithinOi( String attributeName, Object value )
    {
        // TODO Auto-generated method stub
        return super.setFirstWithinOi( attributeName, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#setLastWithinOi(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public CursorResult setLastWithinOi( AttributeDef attributeDef, Object value )
    {
        // TODO Auto-generated method stub
        return super.setLastWithinOi( attributeDef, value );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#getPrevTwin()
     */
    @Override
    public EntityInstance getPrevTwin()
    {
        // TODO Auto-generated method stub
        return super.getPrevTwin();
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.EntityCursorImpl#hasPrevTwin()
     */
    @Override
    public boolean hasPrevTwin()
    {
        // TODO Auto-generated method stub
        return super.hasPrevTwin();
    }


    private class RollingIterator implements EntityIterator<EntityInstanceImpl>
    {
        /**
         * We could use getEntityInstance() to get the current EI but that involves
         * extra processing so we'll just keep it here.
         */
        private EntityInstanceImpl ei;

        private RollingIterator()
        {
            ei = getView().getObjectInstance().getRootEntityInstance();
            while ( ei != null && ei.isHidden() )
                ei = ei.getNextTwin();
        }

        private void findNext()
        {
            while ( ei != null && ei.isHidden() )
                ei = ei.getNextTwin();

            if ( ei == null )
                ei = (EntityInstanceImpl) loadNextPage();
            else
                lastEi = ei;
        }

        @Override
        public boolean hasNext()
        {
            return ei != null;
        }

        @Override
        public EntityInstanceImpl next()
        {
            setCursor( ei );
            EntityInstanceImpl returnEi = ei;
            ei = ei.getNextTwin();
            findNext();
            return returnEi;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException( "remove() not supported for rolling pagination iterators" );
        }

        @Override
        public EntityInstanceImpl prev()
        {
            throw new UnsupportedOperationException( "prev() not supported for rolling pagination iterators" );
        }

        @Override
        public boolean hasPrev()
        {
            throw new UnsupportedOperationException( "hasPrev() not supported for rolling pagination iterators" );
        }

        /* (non-Javadoc)
         * @see java.lang.Iterable#iterator()
         */
        @Override
        public Iterator<EntityInstanceImpl> iterator()
        {
            return this;
        }
    }
}
