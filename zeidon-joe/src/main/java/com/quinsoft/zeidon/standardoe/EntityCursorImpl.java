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
package com.quinsoft.zeidon.standardoe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.EntityIterator;
import com.quinsoft.zeidon.HiddenCursorException;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.NullCursorException;
import com.quinsoft.zeidon.SetMatchingFlags;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.LazyLoadConfig;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
import com.quinsoft.zeidon.objectdefinition.ViewOd;

/**
 * @author DG
 *
 */
class EntityCursorImpl implements EntityCursor
{
    private final ViewEntity       viewEntity;
    private final ViewCursor       viewCursor;
    private final EntityCursorImpl parentCursor;

    private EntityCursorImpl   prevHier;
    private EntityCursorImpl   nextHier;
    private EntityIterator<EntityInstanceImpl> currentIterator;

    /**
     * This points to the cursor's current entity instance.  It's a weak reference so
     * that if the entity is dropped then this cursor will release it.
     */
    private EntityInstanceImpl entityInstance;

    EntityCursorImpl(ViewCursor viewCursor, ViewEntity viewEntity, EntityCursorImpl parentCsr)
    {
        this.viewCursor = viewCursor;
        this.viewEntity = viewEntity;
        this.parentCursor = parentCsr;
        assert parentCsr != null || viewEntity.getParent() == null : "Parent Cursor not set correctly";
        setEntityInstance( null );
    }

    /**
     * Create a cursor and initialize it to point to the same entity as 'source'.
     *
     * @param viewCursor
     * @param source
     * @param parentCsr
     */
    EntityCursorImpl( ViewCursor viewCursor, EntityCursorImpl source, EntityCursorImpl parentCsr )
    {
        this( viewCursor, source.getViewEntity(), parentCsr );
        setEntityInstance( source.getEntityInstance( false ) );
        if ( source.currentIterator != null)
            currentIterator = IteratorBuilder.build( source.currentIterator, this );
    }

    private ObjectInstance getObjectInstance()
    {
        return viewCursor.getObjectInstance();
    }
    /**
     * Returns the current entity pointed to by this cursor or null.
     *
     * If the cursor is currently pointing to null then this logic will attempt to
     * re-establish the cursor by looking at parent cursor values.  Will return
     * an entity instance even if it's hidden.
     *
     * If necessary, this will lazy load an entity instance.
     */
    @Override
    public EntityInstanceImpl getEntityInstance()
    {
        return getEntityInstance( true );
    }

    /**
     * Attempts to get an entity instance.
     *
     * @param allowLazyLoad If false, then don't attempt to lazy load an instance.
     *
     * @return
     */
    private EntityInstanceImpl getEntityInstance( boolean allowLazyLoad )
    {
        // There are some edge cases with dropped entities.  Look to see if the current
        // entity is dropped and if it is set the cursor to null.
        // KJS 05/13/10 - Commenting out temporarily per DG request.
        //if ( entityInstance != null && entityInstance.isDropped() )
        //    setEntityInstance( null );

        // If it's null then we'll still try to re-establish the cursor because it's
        // possible that an entity was created using a different view.
        if ( entityInstance == null )
        {
            if ( viewEntity.getParent() == null )
                entityInstance = getObjectInstance().getRootEntityInstance();
            else
            {
                // Try to get the entity instance for the parent.
                EntityCursorImpl parentCsr = viewCursor.getEntityCursor( viewEntity.getParent() );
                EntityInstanceImpl parentInstance = parentCsr.getEntityInstance( allowLazyLoad );

                // If the parent instance is null then this cursor must be null as well.
                if ( parentInstance == null )
                {
                    entityInstance = null;
                    return null;
                }

                // Do some validity checking.  Parent shouldn't be higher in the hier structure
                // than the recursive root.
                if ( viewCursor.getRecursiveRoot() != null &&
                     viewCursor.getRecursiveRoot().getLevel() > parentInstance.getLevel() )
                {
                    throw new ZeidonException("Internal error: parent level for %s doesn't match " +
                                              "level for suboject root %s", parentInstance,
                                              viewCursor.getRecursiveRoot() );
                }

                // Check to see if we need to load a lazy child.
                if ( allowLazyLoad )
                    parentInstance.lazyLoadChild( getView(), getViewEntity() );

                EntityInstanceImpl searchInstance = null;

                // We need to find the first entityInstance under the parent.  The
                // quickest way is to find a previous sibling and search hier from there.
                ViewEntity prevSibling = viewEntity.getPrevSibling();
                if ( prevSibling != null)
                    // Use getEntityInstance because its possible for the cursor to be null.
                    searchInstance = viewCursor.getEntityCursor( prevSibling ).getEntityInstance( false );

                // No siblings were found so start the search from the parent.
                if ( searchInstance == null )
                    searchInstance = parentInstance.getNextHier();

                // Find the first entity instance under the parent that:
                //    1) Has the same viewEntity for the cursor we're trying to set.
                //    2) Has the same instance level for the viewEntity + recursive diff.
                //       Checking for instance level will skip over recursive suboject
                //       instances with the same view entity.
                int level = viewEntity.getLevel() + viewCursor.getRecursiveDiff();
                for ( ; searchInstance != null; searchInstance = searchInstance.getNextHier() )
                {
                    // TODO: Since we're running through all the entities
                    // we might as well set whatever cursors we can.

                    // If the searchInstance level is less than the parent then there is no
                    // entity that matches what we want.
                    if ( searchInstance.getLevel() <= parentInstance.getLevel() )
                    {
                        searchInstance = null;
                        break;
                    }

                    if ( searchInstance.isHidden() )
                        continue;

                    ViewEntity searchViewEntity = searchInstance.getViewEntity();
                    if ( searchViewEntity == viewEntity && searchInstance.getLevel() == level )
                        break;
                }

                entityInstance = searchInstance;
            }
        }

        if ( entityInstance != null )
            entityInstance = entityInstance.getLatestVersion();

        return entityInstance;
    }

    /**
     * Gets the cursor's entity instance.  Throws NullCursorException if the entity is null.
     * @return
     * @throws NullCursorException
     */
    private EntityInstanceImpl getExistingInstance( boolean allowHidden ) throws NullCursorException
    {
        EntityInstanceImpl ei = getEntityInstance();  // Potentially sets UNSET_CSR.
        if ( ei == null )
            throw new NullCursorException( this );

       if ( ! allowHidden && ei.isHidden() )
            throw new HiddenCursorException( this );

       if ( ! viewCursor.isCursorInScope( this ) )
           throw new ZeidonException( "Cursor %s is out of scope", getViewEntity() );

       // Sanity checking--make sure that the existing instance has at least one linked instance,
       // which is itself.
       assert ei.getAllLinkedInstances( allowHidden ).contains( ei ) : "Entity doesn't have itself in linked instances";

       return ei;
    }

    private EntityInstanceImpl getExistingInstance() throws NullCursorException
    {
        return getExistingInstance( false );
    }

    private EntityInstanceImpl setEntityInstance( EntityInstanceImpl entityInstance )
    {
        this.entityInstance = entityInstance;
        return entityInstance;
    }

    @Override
    public EntityInstanceImpl getParent()
    {
        if ( parentCursor == null )
            return null;

        return parentCursor.getExistingInstance();
    }

    @Override
    public EntityInstance copySubobject( EntityInstance source, CursorPosition position )
    {
        if ( ! getViewEntity().isCreate() )
            throw new ZeidonException( "Entity is not flagged for create." )
                            .prependViewEntity( getViewEntity() );

        if ( getViewEntity() != source.getViewEntity() )
            throw new ZeidonException( "Source and target entity definitions must be the same." )
                            .prependViewEntity( getViewEntity() )
                            .prependMessage( "Source Entity = ", source.getViewEntity().getName() );

        EntityInstanceImpl sourceInstance = (EntityInstanceImpl) source.getEntityInstance();
        createEntity( position );
        setMatchingAttributesByName( sourceInstance );

        // Now copy children.  We can't use the usual iterator because we need to skip over
        // children if we include a child entity.
        for ( EntityInstanceImpl child = sourceInstance.getNextHier();
              child != null && child.getLevel() > sourceInstance.getLevel();
              child = child.getNextHier() )
        {
            if ( child.isHidden() )
            {
                // Skip hidden children.
                child = child.getLastChildHier();
                continue;
            }

            ViewEntity childViewEntity = child.getViewEntity();
            EntityCursorImpl childCursor = getView().cursor( childViewEntity );

            if ( childViewEntity.isCreate() )
            {
                childCursor.createEntity();
                childCursor.setMatchingAttributesByName( child );
                continue;
            }

            if ( childViewEntity.isInclude() )
            {
                childCursor.includeSubobject( child );
                child = child.getLastChildHier();
                continue;
            }

            throw new ZeidonException( "Copied child entity is neither creatable or includable." )
                            .prependViewEntity( childViewEntity );
        }

        resetChildCursors( getExistingInstance() );

        assert validateChains() : "Something is wrong with the chain pointers";
        return getExistingInstance();
    }

    @Override
    public EntityInstanceImpl createEntity()
    {
        return createEntity(CursorPosition.NEXT, CreateEntityFlags.DEFAULT );
    }

    @Override
    public EntityInstanceImpl createEntity( CursorPosition position,
                                            EnumSet<CreateEntityFlags> flags )
    {
        if ( ! flags.contains( CreateEntityFlags.fIGNORE_PERMISSIONS ) && ! getViewEntity().isCreate() )
            throw new ZeidonException( "Entity is not flagged for create." )
                            .prependViewEntity( getViewEntity() );

        EntityInstanceImpl parent = getParent();  // Throws NullCursor if cursor is null.

        if ( ! flags.contains( CreateEntityFlags.fIGNORE_MAX_CARDINALITY ) )
            validateMaxCardinality();

        // If the entity is a derived entity or a work entity (both marked as derived) then
        // we don't need to check if the entity is read only.
        if ( !getViewEntity().isDerived() )
           validateOiUpdate();

        EntityInstanceImpl ei = getEntityInstance();

        // Create a new instance and initialize the attributes.
        EntityInstanceImpl newInstance =
                EntityInstanceImpl.createEntity( getObjectInstance(),
                                                 parent,
                                                 ei,
                                                 getViewEntity(),
                                                 position );

        if ( ! flags.contains( CreateEntityFlags.fDONT_INITIALIZE_ATTRIBUTES ) )
            newInstance.initializeDefaultAttributes();

        if ( ! flags.contains( CreateEntityFlags.fDONT_UPDATE_OI ) && ! newInstance.isVersioned() )
            getObjectInstance().setUpdated( true );

        if ( ! flags.contains( CreateEntityFlags.fNO_SPAWNING ) )
        {
            EntitySpawner spawner = new EntitySpawner( newInstance );
            spawner.spawnCreate();
        }

        resetChildCursors( newInstance );
        assert validateChains() : "Something is wrong with the chain pointers";
        return newInstance;
    }

    @Override
    public EntityInstanceImpl createEntity( CursorPosition position )
    {
        return createEntity( position, CreateEntityFlags.DEFAULT );
    }

    @Override
    public EntityInstanceImpl createEntity( EnumSet<CreateEntityFlags> flags )
    {
        return createEntity( CursorPosition.NEXT, flags );
    }

    @Override
    public EntityInstanceImpl createEntity( CreateEntityFlags... flags )
    {
        EnumSet<CreateEntityFlags> set = EnumSet.copyOf( Arrays.asList( flags ) );
        return createEntity( CursorPosition.NEXT, set );
    }

    EntityInstanceImpl createEntity( CursorPosition position, CreateEntityFlags... flags )
    {
        EnumSet<CreateEntityFlags> set = EnumSet.copyOf( Arrays.asList( flags ) );
        return createEntity( position, set );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#createTemporalEntity()
     */
    @Override
    public EntityInstance createTemporalEntity()
    {
        return createTemporalEntity( CursorPosition.NEXT );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#createTemporalEntity(com.quinsoft.zeidon.CursorPosition)
     */
    @Override
    public EntityInstance createTemporalEntity(CursorPosition position)
    {
        validateMaxCardinality();
        validateOiUpdate();
        EntityInstanceImpl ei = createEntity( position,
                                              CreateEntityFlags.fNO_SPAWNING,
                                              CreateEntityFlags.fDONT_UPDATE_OI );
        ei.setVersionedEntity();
        assert validateChains() : "Something is wrong with the chain pointers";
        return ei;
    }

    protected ViewOd getViewOD()
    {
        return viewCursor.getViewOD();
    }

    /**
     * Reset the currently cursor to point to resetInstance and set all child cursors
     * to be unset.
     *
     * @param resetInstance
     */
    void resetChildCursors(EntityInstanceImpl resetInstance)
    {
        EntityInstanceImpl ei = resetInstance;
        if ( ei != null )
            ei = resetInstance.getLatestVersion();

        setEntityInstance( ei );
        for ( EntityCursorImpl resetCsr = this.getNextHierCursor();
              resetCsr != null && resetCsr.getViewEntity().getLevel() > this.getViewEntity().getLevel();
              resetCsr = resetCsr.getNextHierCursor() )
        {
            resetCsr.setEntityInstance( null );
        }
    }

    /**
     * Validate that ei's ViewEntity match that of the cursor.  Throws an exception if not.
     *
     * @return true if ei is a recursive child of this cursor, return false if not recursive.
     */
    private boolean validateEntityForCursor( EntityInstanceImpl ei )
    {
        // If they are the same entity then we're good.
        ViewEntity sourceViewEntity = ei.getViewEntity();
        if ( sourceViewEntity == getViewEntity() )
            return false;

        // Check to see if ei is a recursive child of this cursor.
        if ( sourceViewEntity.isRecursive() && sourceViewEntity.getRecursiveParentViewEntity() == getViewEntity() )
            return true;

        // If we get here then Houston we have a problem.
        throw new ZeidonException( "Entity Instance %s is not a valid entity for this cursor.", sourceViewEntity );
    }


    /**
     * Set the cursor to point to targetInstance and set all child cursors to point to
     * UNSET_CSR.  This will also check to see if the parent cursors need to be
     * set.
     *
     * @param newInstance
     * @return
     */
    @Override
    public CursorResult setCursor( EntityInstance targetInstance )
    {
        /*
         * We used to have an assert to validate that newInstance can't be null.  Asserts aren't
         * run in production so I've changed it to a normal check.  The code a few lines beneath
         * this check would execute some logic if newInstance was null so we had some contradictory
         * code.  This may need to be reverted.
         */
        if ( targetInstance == null )
            throw new ZeidonException("Cannot set a cursor to null.");

        EntityInstanceImpl newInstance = (EntityInstanceImpl) targetInstance.getEntityInstance();

        boolean recursive = validateEntityForCursor( newInstance );

        // Default return code to setting the cursor.
        CursorResult cursorResult = CursorResult.SET;

        // If this is a recursive child then we don't need to reset any parent
        // cursor so reset 'this' and return.
        if ( recursive )
        {
            resetChildCursors( newInstance );
            return cursorResult;
        }

        // Check to see if we need to set the parent cursors. Find the highest root cursor that
        // needs to be reset.
        EntityCursorImpl searchCursor = this;
        EntityInstanceImpl searchEi = newInstance;
        for ( searchEi = newInstance; searchEi.getParent() != null; searchEi = searchEi.getParent() )
        {
            EntityCursorImpl searchParentCursor = searchCursor.getParentCursor();
            if ( searchParentCursor == null )
                break;

            if ( searchParentCursor.getEntityInstance() == searchEi.getParent() )
                break;

            searchCursor = searchCursor.getParentCursor();
        }

        searchCursor.resetChildCursors( searchEi );

        // If searchEi is different from newInstance that means we've just reset a parent
        // cursor.  Now loop through again and set all cursors between searchCursor and 'this'.
        if ( searchEi != newInstance )
        {
            cursorResult = CursorResult.SET_NEWPARENT;
            searchCursor = this;
            EntityInstanceImpl ei;
            for ( ei = newInstance; ei != searchEi; ei = ei.getParent() )
            {
                searchCursor.setEntityInstance( ei );
                searchCursor = searchCursor.getParentCursor();
            }
        }

        return cursorResult;
    }

    EntityCursorImpl getPrevHier()
    {
        return prevHier;
    }

    /**
     * Sets the prev *cursor*.  Used when building a new ViewCursor.
     *
     * @param prevHier
     */
    void setPrevHier(EntityCursorImpl prevHier)
    {
        this.prevHier = prevHier;
    }

    EntityCursorImpl getNextHierCursor()
    {
        return nextHier;
    }

    /**
     * Sets the next *cursor*.  Used when building a new ViewCursor.
     *
     * @param prevHier
     */
    void setNextHierCursor(EntityCursorImpl nextHier)
    {
        this.nextHier = nextHier;
    }

    @Override
    public ViewEntity getViewEntity()
    {
        return viewEntity;
    }

    @Override
    public CursorResult deleteEntity() throws NullCursorException
    {
        return deleteEntity(CursorPosition.NONE);
    }

    @Override
    public CursorResult excludeEntity()
    {
        return excludeEntity( CursorPosition.NONE );
    }

    /**
     * Re-positions the cursor after a delete or exclude.
     *
     * @param cursorPosition
     */
    private CursorResult repositionCursor( CursorPosition cursorPosition )
    {
        if ( cursorPosition == CursorPosition.NONE )
            return CursorResult.UNCHANGED;

        // Save the current iterator.
        EntityIterator<EntityInstanceImpl> iterator = currentIterator;

        try
        {
            switch ( cursorPosition )
            {
                case FIRST:
                    return setFirst();

                case NEXT:
                    return setNext();

                case NEXT_OR_LAST:
                    CursorResult rc = setNext();
                    if ( rc == CursorResult.SET )
                        return rc;
                    else
                        return setLast();


                case LAST:
                    return setLast();

                case PREV:
                    return setPrev();

                default:
                    throw new RuntimeException( "Uknown CursorPosition " + cursorPosition );
            }
        }
        finally
        {
            currentIterator = iterator;
        }
    }

    @Override
    public CursorResult excludeEntity(CursorPosition position)
    {
        getExistingInstance().excludeEntity();
        assert validateChains() : "Something is wrong with the chain pointers";
        return repositionCursor( position );
    }

    @Override
    public CursorResult deleteEntity(CursorPosition position) throws NullCursorException
    {
        getExistingInstance().deleteEntity( getView() );
        assert validateChains() : "Something is wrong with the chain pointers";
        return repositionCursor( position );
    }

    @Override
    public CursorResult deleteAll()
    {
        CursorResult result = CursorResult.UNCHANGED;

        if ( getEntityInstance() == null )
            return result;

        for ( EntityInstanceImpl ei = getExistingInstance().getFirstTwin();
              ei != null;
              ei = ei.getNextTwin() )
        {
            if ( ei.isHidden() )
                continue;

            ei.deleteEntity();
            result = CursorResult.SET;
        }

        return result;
    }

    private void validateMaxCardinality()
    {
        EntityInstanceImpl ei = getEntityInstance();
        if ( ei != null )
            ei.validateMaxCardinality();
    }

    /**
     * Validates that this OI may be updated.
     */
    private void validateOiUpdate()
    {
        // If the entity is a derived entity or a work entity (both marked as derived) then
        // we don't need to check if the entity is read only.
        if ( getViewEntity().isDerived() || getViewEntity().isDerivedPath() )
        	return;

        if ( getObjectInstance().isReadOnly() )
            throw new ZeidonException( "Object Instance is read-only" )
                                .prependViewEntity( getViewEntity() );
    }

    @Override
    public void includeSubobject(EntityInstance sourceEi) throws NullCursorException
    {
        includeSubobject( sourceEi, CursorPosition.NEXT );
    }

    @Override
    public void includeSubobject(EntityInstance sourceEi, CursorPosition position) throws NullCursorException
    {
        validateMaxCardinality();
        validateOiUpdate();
        EntityInstanceImpl source = (EntityInstanceImpl) sourceEi.getEntityInstance();
        EntityInstanceImpl parent = getParent();
        ObjectInstance oi = getObjectInstance();

        // Create a new instance and initialize the attributes.
        EntityInstanceImpl rootInstance =
                EntityInstanceIncluder.includeSubobject( getEntityInstance(), getViewEntity(), parent,
                                                         oi, source, position, true );

        if ( getViewEntity().getHashKeyAttributes() != null )
            rootInstance.addAllHashKeyAttributes();

        resetChildCursors( rootInstance );
        assert validateChains() : "Something is wrong with the chain pointers";
        // TODO: Add INCLUDE_WITH_COPY flag?
    }

    @Override
    public EntityInstance createTemporalSubobjectVersion() throws NullCursorException
    {
        validateOiUpdate();
        EntityInstanceImpl ei = getExistingInstance().createTemporalSubobjectVersion();
        assert validateChains() : "Something is wrong with the chain pointers";
        return ei;
    }

    @Override
    public int getLevel() throws NullCursorException
    {
        return getExistingInstance().getLevel();
    }

    /**
     * Returns the last child under the current entity instance.  If there is no child
     * under 'this', then returns 'this'.
     * @return
     */
    @Override
    public EntityInstanceImpl getLastChildHier()
    {
        return getExistingInstance().getLastChildHier();
    }

    @Override
    public boolean isCreated() throws NullCursorException
    {
        return getExistingInstance().isCreated();
    }

    @Override
    public boolean isDeleted() throws NullCursorException
    {
        // Use getEntityInstance instead of getExistingInstance because getExisting
        // will throw an exception for hidden instances.
        return getEntityInstance().isDeleted();
    }

    @Override
    public boolean isExcluded() throws NullCursorException
    {
        // Use getEntityInstance instead of getExistingInstance because getExisting
        // will throw an exception for hidden instances.
        return getEntityInstance().isExcluded();
    }

    @Override
    public boolean isHidden() throws NullCursorException
    {
        // Use getEntityInstance instead of getExistingInstance because getExisting
        // will throw an exception for hidden instances.
        return getEntityInstance().isHidden();
    }

    @Override
    public boolean isIncluded()  throws NullCursorException
    {
        return getExistingInstance().isIncluded();
    }

    @Override
    public boolean isUpdated() throws NullCursorException
    {
        return getExistingInstance().isUpdated();
    }

    @Override
    public boolean isVersioned()
    {
        return getExistingInstance().isVersioned();
    }

    Iterable<ViewAttribute> getNonNullAttributeList() throws NullCursorException
    {
        return getExistingInstance().getNonNullAttributeList();
    }

     /**
     * Gets the entity instance for the current cursor value matching scopingEntity.
     *
     * @param scopingEntity
     * @return
     */
    private EntityInstanceImpl getScopingEntityInstance( ViewEntity scopingEntity )
    {
        if ( scopingEntity == null )
            throw new ZeidonException( "ScopingEntity may not be null" );

        // If the entity for this cursor has no parent then scoping isn't required.
        if ( getViewEntity().getParent() == null )
            return null;

// DGC 2013-02-21 Following is commented out in case the check-for-null at the beginning
// of this method is incorrect.  You can remove the following code once it has been
// verified that it is not needed.
//        if ( scopingEntity == null )
//            scopingEntity = getViewEntity().getParent();

        // If the scoping entity isn't the parent then we need to reset ancestor
        // cursors for everything under the scoping entity child.
        ViewEntity searchEntity = getViewEntity().getParent();
        while ( searchEntity != null && searchEntity != scopingEntity )
            searchEntity = searchEntity.getParent();

        if ( searchEntity == null )
            throw new ZeidonException( "Invalid scoping entity: Entity %s is not an ancestor of %s",
                                        scopingEntity.getName(), this.getViewEntity().getName() );

        // Find the root EI for the scoping entity.
        EntityCursorImpl cursor = viewCursor.getEntityCursor( searchEntity );
        EntityInstanceImpl rootEi = cursor.getEntityInstance();
        return rootEi;
    }

    @Override
    public CursorResult setFirst()
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .forTwinsOf( getEntityInstance() )
                                    .forViewEntity( getViewEntity() )
                                    .setCursor( this )
                                    .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.NULL;

        currentIterator.next();
        return CursorResult.SET;
    }

    @Override
    public CursorResult setFirst(String scopingEntityName)
    {
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setFirst();

        return setFirst( getViewOD().getViewEntity( scopingEntityName ) );
    }

    @Override
    public CursorResult setFirst(ViewEntity scopingEntity)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .withScoping( getScopingEntityInstance( scopingEntity ) )
                                    .forViewEntity( getViewEntity() )
                                    .setCursor( this )
                                    .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.NULL;

        currentIterator.next();
        return CursorResult.SET;
    }

    @Override
    public CursorResult setFirst(String attributeName, Object value)
    {
        return setFirst( getViewEntity().getAttribute( attributeName ), value );
    }

    @Override
    public CursorResult setFirst(ViewAttribute attribute, Object value)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .forViewEntity( getViewEntity() )
                                    .forTwinsOf( getEntityInstance() )
                                    .setCursor( this )
                                    .withAttributeValue( attribute, value )
                                    .build();

        if ( ! currentIterator.hasNext() )
            return CursorResult.UNCHANGED;

        //TODO: It would be nice to get rid of this next call.  Reason: for situations
        // where we are only calling setFirst with no following setNext, this call
        // loops through to find the next EI that matches.  If the list of sibling
        // entities is long we're doing a lot of unnecessary work.
        currentIterator.next();
        return CursorResult.SET;
    }

    @Override
    public CursorResult setFirst(String attributeName, Object value, String scopingEntityName)
    {
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setFirst( attributeName, value );

        ViewEntity scopingEntity = getViewOD().getViewEntity( scopingEntityName );
        return setFirst( getViewEntity().getAttribute( attributeName ), value, scopingEntity );
    }

    @Override
    public CursorResult setFirst(ViewAttribute attribute, Object value, ViewEntity scopingEntity)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .withScoping( getScopingEntityInstance( scopingEntity ) )
                                    .forViewEntity( getViewEntity() )
                                    .setCursor( this )
                                    .withAttributeValue( attribute, value )
                                    .build();

        if ( ! currentIterator.hasNext() )
            return CursorResult.UNCHANGED;

        //TODO: It would be nice to get rid of this next call.  Reason: for situations
        // where we are only calling setFirst with no following setNext, this call
        // loops through to find the next EI that matches.  If the list of sibling
        // entities is long we're doing a lot of unnecessary work.
        currentIterator.next();
        return CursorResult.SET;
    }

    @Override
    public CursorResult setNextContinue()
    {
    	if ( currentIterator == null )
    		setFirst();

        if ( ! currentIterator.hasNext() )
            return CursorResult.UNCHANGED;

        currentIterator.next();
        return CursorResult.SET;
    }

    @Override
    public CursorResult setNext()
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .forViewEntity( getViewEntity() )
                                    .setCursor( this )
                                    .currentInstance( getEntityInstance() )
                                    .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.UNCHANGED;

        currentIterator.next();
        return CursorResult.SET;
    }

    @Override
    public CursorResult setNext(String scopingEntityName)
    {
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setNext();

        return setNext( getViewOD().getViewEntity( scopingEntityName ) );
    }

    @Override
    public CursorResult setNext(ViewEntity scopingEntity)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .withScoping( getScopingEntityInstance( scopingEntity ) )
                                    .forViewEntity( getViewEntity() )
                                    .setCursor( this )
                                    .currentInstance( getEntityInstance() )
                                    .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.NULL;

        currentIterator.next();
        return CursorResult.SET;
    }

    @Override
    public CursorResult setNext(String attributeName, Object value)
    {
        return setNext( getViewEntity().getAttribute( attributeName ), value );
    }

    @Override
    public CursorResult setNext(ViewAttribute attribute, Object value)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
        						.forViewEntity( getViewEntity() )
        						.setCursor( this )
        						.currentInstance( getEntityInstance() )
                                .withAttributeValue( attribute, value )
                                .build();

        if ( ! currentIterator.hasNext() )
            return CursorResult.UNCHANGED;

        currentIterator.next();
        return CursorResult.SET;
    }

    @Override
    public CursorResult setNext(String attributeName, Object value, String scopingEntityName)
    {
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setNext(getViewEntity().getAttribute( attributeName ), value);

        return setNext( getViewEntity().getAttribute( attributeName ), value, getViewOD().getViewEntity( scopingEntityName ) );
    }

    @Override
    public CursorResult setNext( ViewAttribute attribute, Object value, ViewEntity scopingEntity)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
					        .withScoping( getScopingEntityInstance( scopingEntity ) )
					        .forViewEntity( getViewEntity() )
					        .setCursor( this )
					        .currentInstance( getEntityInstance() )
                            .withAttributeValue( attribute, value )
					        .build();

		if ( ! currentIterator.hasNext() )
		return CursorResult.NULL;

		currentIterator.next();
		return CursorResult.SET;

    }

    @Override
    public CursorResult setLast()
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .forTwinsOf( getEntityInstance() )
                                    .setLast()
                                    .forViewEntity( getViewEntity() )
                                    .setCursor( this )
                                    .build();

        if ( ! currentIterator.hasPrev() )  // Is there a last entity?
            return CursorResult.UNCHANGED;

        currentIterator.prev();
        return CursorResult.SET;
    }

    @Override
    public CursorResult setPrevContinue()
    {
    	if ( currentIterator == null )
    	{
    	    setFirst();
            return CursorResult.UNCHANGED;
    	}

        if ( ! currentIterator.hasPrev() )
            return CursorResult.UNCHANGED;

        currentIterator.prev();
        return CursorResult.SET;
    }

    @Override
    public CursorResult setByEntityKey(long entityKey)
    {
        // TODO: This should be cleaned up.  We probably don't need to build
        // an iterator and we probably shouldn't be setting currentIterator because
        // there is no valid 'next' value.
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                                                .forViewEntity( getViewEntity() )
                                                .forTwinsOf( getEntityInstance() )
                                                .build();

        for ( EntityInstance ei : iter )
        {
            if ( ei.getEntityKey() == entityKey )
            {
                currentIterator = iter;
                return setCursor( ei );
            }
        }

        return CursorResult.NULL;
    }

    @Override
    public CursorResult setPosition(int position)
    {
        // TODO: This should be cleaned up.  We probably don't need to build
        // an iterator.
        if ( position < 0 )
            return CursorResult.NULL;

        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                                            .forTwinsOf( getEntityInstance() )
                                            .forViewEntity( getViewEntity() )
                                            .build();

        EntityInstanceImpl ei = null;
        for ( int i = 0; i <= position; i++ )
        {
            if ( ! iter.hasNext() )
                return CursorResult.NULL;

            ei = iter.next();
        }

        setCursor( ei );
        return CursorResult.SET;
    }

    @Override
    public CursorResult setPosition(int position, String scopingEntityName )
    {
        if ( position < 0 )
            return CursorResult.NULL;

        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setPosition( position );

        ViewEntity scopingEntity = getViewOD().getViewEntity( scopingEntityName );
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                                            .withScoping( getScopingEntityInstance( scopingEntity ) )
                                            .forViewEntity( getViewEntity() )
                                            .build();

        EntityInstanceImpl ei = null;
        for ( int i = 0; i <= position; i++ )
        {
            if ( ! iter.hasNext() )
                return CursorResult.NULL;

            ei = iter.next();
        }

        setCursor( ei );
        return CursorResult.SET;
    }

    Iterable<EntityCursorImpl> getChildCursors()
    {
        return new ChildIterable( this );
    }

    @Override
    public EntityInstance acceptSubobject()
    {
        try
        {
            EntityInstanceImpl ei = getExistingInstance().acceptSubobject();
            setEntityInstance( ei );

            // The child cursors still point to the old version.  Reset them all to point
            // to the new version so that the GC doesn't hold onto the old ones.
            for ( EntityCursorImpl child : getChildCursors() )
                child.getEntityInstance();

            assert validateChains() : "Something is wrong with the chain pointers";
            return ei;
        }
        catch ( Throwable e )
        {
            throw ZeidonException.wrapException( e ).prependViewEntity( getViewEntity() );
        }
    }

    @Override
    public EntityInstance cancelSubobject()
    {
        EntityInstanceImpl ei = getExistingInstance().cancelSubobject();
        setEntityInstance( ei );

        // The child cursors may still point to the new version.  Reset them all to point
        // to the old version so that the GC doesn't hold onto the new ones.
        for ( EntityCursorImpl child : getChildCursors() )
            child.getEntityInstance();

        assert validateChains() : "Something is wrong with the chain pointers";
        return ei;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#acceptTemporalEntity()
     */
    @Override
    public void acceptTemporalEntity()
    {
        getExistingInstance().acceptTemporalEntity();
        assert validateChains() : "Something is wrong with the chain pointers";
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#cancelTemporalEntity(com.quinsoft.zeidon.CursorPosition)
     */
    @Override
    public CursorResult cancelTemporalEntity(CursorPosition position)
    {
        getExistingInstance().cancelTemporalEntity();
        assert validateChains() : "Something is wrong with the chain pointers";
        return repositionCursor( position );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#cancelTemporalEntity()
     */
    @Override
    public CursorResult cancelTemporalEntity()
    {
        return cancelTemporalEntity( CursorPosition.NONE );
    }

    @Override
    public CursorResult dropEntity()
    {
        return dropEntity( CursorPosition.NONE );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#dropEntity(com.quinsoft.zeidon.CursorPosition)
     */
    @Override
    public CursorResult dropEntity(CursorPosition position)
    {
        getExistingInstance().dropEntity();
        assert validateChains() : "Something is wrong with the chain pointers";
        return repositionCursor( position );
    }

    void setCreated(boolean isCreated)
    {
        getExistingInstance().setCreated( isCreated );
    }

    void setUpdated(boolean isUpdated)
    {
        getExistingInstance().setUpdated( isUpdated );
    }

    @Override
    public boolean isNull()
    {
        EntityInstanceImpl ei = getEntityInstance();
        return ei == null || ei.isHidden();
    }

    private static final class ChildIterable implements Iterable<EntityCursorImpl>
    {
        private final EntityCursorImpl cursor;

        /**
         * @param start
         */
        private ChildIterable(EntityCursorImpl start)
        {
            this.cursor = start;
        }

        @Override
        public Iterator<EntityCursorImpl> iterator()
        {
            return new Iterator<EntityCursorImpl>() {
                private EntityCursorImpl current = cursor;
                private final int startLevel = cursor.getViewEntity().getLevel();

                @Override
                public boolean hasNext()
                {
                    EntityCursorImpl next = current.getNextHierCursor();
                    if ( next == null )
                        return false;

                    if ( next.getViewEntity().getLevel() <= startLevel )
                        return false;

                    return true;
                }

                @Override
                public EntityCursorImpl next()
                {
                    current = current.getNextHierCursor();
                    return current;
                }

                @Override
                public void remove()
                {
                    throw new UnsupportedOperationException( "remove() not supported");
                }
            };
        }
    }

    private static class SortKey
    {
        ViewAttribute viewAttrib;
        boolean       ascending;
        @SuppressWarnings("unused")
        String        context;

        SortKey(ViewAttribute viewAttrib, boolean ascending, String context)
        {
            this.viewAttrib = viewAttrib;
            this.ascending = ascending;
            this.context = context;
        }
    }

    /**
     * Class used as a comparator when ordering entities.
     *
     * @author DG
     *
     */
    private static class EntitySorter implements Comparator<EntityInstanceImpl>, Serializable
    {
        private static final long serialVersionUID = 1L;

        private final List<SortKey> sortAttribs;

        EntitySorter(List<SortKey> sortAttribs)
        {
            super();
            this.sortAttribs = sortAttribs;
        }

        private EntityInstanceImpl findMatchingChild( EntityInstanceImpl parent, ViewEntity sortEntity )
        {
            // We need to find the child entity that matches sortEntity.
            for ( EntityInstanceImpl child : parent.getChildrenHier() )
            {
                if ( child.getViewEntity() == sortEntity )
                    return child;
            }

            return null;
        }

        @Override
        public int compare(EntityInstanceImpl ei1, EntityInstanceImpl ei2 )
        {
            assert ei1.getViewEntity() == ei2.getViewEntity();

            for ( SortKey key : sortAttribs )
            {
                // Since we might be performing the compare on a child entity instance,
                // create "compare" EIs.
                EntityInstanceImpl cei1 = ei1;
                EntityInstanceImpl cei2 = ei2;

                ViewEntity sortEntity = key.viewAttrib.getViewEntity();
                if ( cei1.getViewEntity() != sortEntity )
                {
                    cei1 = findMatchingChild( cei1, sortEntity );
                    cei2 = findMatchingChild( cei2, sortEntity );

                    // Check for null entities.  If one is null then we can compare without
                    // checking the attributes.
                    if ( cei1 == null || cei2 == null )
                    {
                        if ( cei1 != null )
                            return 1;  // cei2 is null, cei1 is not.

                        if ( cei2 != null )
                            return -1; // cei1 is null, cei2 is not.

                        return 0;  // Both are null.
                    }
                }

                int cmp = 0;
                if (key.context != null)
                {
                	// Use context for sort order.
                	String value1 = cei1.getStringFromAttribute(key.viewAttrib,  key.context);
                	String value2 = cei2.getStringFromAttribute(key.viewAttrib,  key.context);

                	cmp = value1.compareTo(value2);
                }
                else
                {
                	cmp = cei1.compareAttribute( key.viewAttrib, cei2, key.viewAttrib );
                }
                if ( ! key.ascending )
                    cmp = -cmp;

                if ( cmp != 0 )
                    return cmp;

            }

            return 0;
        }
    }

    private List<SortKey> parseOrderKeys( String orderKeys )
    {
        // The old OE allowed the user to request using a bubble sort.  The bubble sort
        // kept already-sorted members in order.  The Java merge sort does the same
        // thing so there's no longer any need to support the bubble sort but we need
        // to ignore it if it's in the string.
        if ( orderKeys.startsWith( ".bubblesort " ) )
            orderKeys = orderKeys.substring( 12 ); // Ignore the bubble sort text.

        // Replace all separating commas with emptystring.  Otherwise our ascending/descending values might be
        // "A," or "D,".
        orderKeys = orderKeys.replaceAll(",", "").replaceAll( "  ", " " );

        List<SortKey> sortAttribs = new ArrayList<SortKey>();
        String[] strings = orderKeys.split( " "  );
        for ( int i = 0; i < strings.length; i++ )
        {
            String name = strings[i];
            ViewEntity sortEntity = getViewEntity();
            if ( name.contains( "." ) )
            {
                String[] s = name.split( "\\." );
                if ( s.length != 2 )
                    throw new ZeidonException( "Ill-formed order keys.  Entity.attrib name expected.  Got '%s'", name );

                sortEntity = getViewOD().getViewEntity( s[0] );
                name = s[1];
            }

            ViewAttribute sortAttrib = sortEntity.getAttribute( name );

            // Look for A or D.
            boolean ascending = true;
            if ( i + 1 < strings.length )
            {
                if ( strings[ i + 1 ].equals( "A" ) )
                {
                    ascending = true;
                    i++; // Skip over the 'A'
                }
                else
                if ( strings[ i + 1 ].equals( "D" ) )
                {
                    ascending = false;
                    i++; // Skip over the 'D'
                }
            }

            // Look for the context name.
            String context = null;
            if ( i + 1 < strings.length )
            {
            	// KJS 05/07/14 - Add context.
                if ( strings[ i + 1 ].startsWith( "[" ) )
                {
                	context = strings[ i + 1 ].substring(1, strings[ i + 1 ].lastIndexOf("]"));
                	i++;
                }

            }

            sortAttribs.add( new SortKey( sortAttrib, ascending, context ) );
        }

        return sortAttribs;
    }

    /**
     * Sorts the entities according to the value of orderKeys.
     *      orderKeys = String of paired 'words', consisting of "AttributeName x",
     *      where x is A for ascending, or D for descending. i.e.,
     *          "LastName A FirstName A".
     *
     *      A context may be specified for the sorting attribute by putting the
     *      context name in square brackets ("[" and "]" after the sort order:
     *          "LastName A State A [Abbrev]"
     *
     * @param orderKeys
     */
     @Override
    public void orderEntities(String orderKeys)
    {
         // If there is an autoseq attribute then ordering for this entity matters, so
         // validate that it can be updated.
         boolean isAutoseq = getViewEntity().getAutoSeq() != null;
         if ( isAutoseq )
             validateOiUpdate();

         // Get the first twin.  We do this first so that if the cursor is null
         // this throws the exception right away.
    	 if ( getEntityInstance() == null )
    		 return;

    	 // Set cursor to first instance.  This will reposition the cursor to the first
    	 // non-hidden entity.
    	 setFirst();

         EntityInstanceImpl firstInstance = getExistingInstance().getFirstTwin();
         EntityInstanceImpl lastInstance = firstInstance.getLastTwin();
         EntityInstanceImpl prevHier = firstInstance.getPrevHier();
         if ( firstInstance == lastInstance )
             return; // There's only one instance so there's no need to re-order.

         EntityInstanceImpl lastHier = lastInstance.getLastChildHier().getNextHier();
         List<SortKey> sortAttribs = parseOrderKeys( orderKeys );

         // Copy the entities into a list.  We null out the next/prev twin pointers so
         // we can re-insert them later.
         List<EntityInstanceImpl> entities = new ArrayList<EntityInstanceImpl>();
         EntityInstanceImpl nextInstance = null;
         for ( EntityInstanceImpl ei = firstInstance; ei != null; ei = nextInstance )
         {
             entities.add( ei );
             nextInstance = ei.getNextTwin();

             ei.setNextTwin( null );
             ei.setPrevTwin( null );
             ei.setPrevHier( null );
             ei.getLastChildHier().setNextHier( null );
         }

         // Temporarily remove the instances from the chain.
         EntityInstanceImpl parent = firstInstance.getParent();
         if ( prevHier != null )
         {
             prevHier.setNextHier( lastHier );
             if ( lastHier != null )
                 lastHier.setPrevHier( prevHier );
         }
         else
             getObjectInstance().setRootEntityInstance( null );

         // Sort the entities.
         Collections.sort( entities, new EntitySorter( sortAttribs ) );

         // Re-insert them into the chain.  We re-insert them starting with the last
         // twin because it's a bit faster.
         ObjectInstance oi = getObjectInstance();
         EntityInstanceImpl previouslyInserted = null;
         for ( int i = entities.size() - 1; i >= 0; i-- )
         {
             EntityInstanceImpl ei = entities.get( i );
             ei.insertInstance( oi, parent, previouslyInserted, CursorPosition.PREV, null );
             if ( isAutoseq )
                 ei.setUpdated( true );  // This will set the OI updated flag as well.
             previouslyInserted = ei;
         }

         assert validateChains() : "Something is wrong with the chain pointers";
    }

    @Override
    public CursorResult checkExistenceOfEntity()
    {
        // First check to see if the entity is null.
        if ( ! isNull() )
            return CursorResult.SET;

        // If we get here then the entity is null or it points to a hidden instance.
        // If it's a hidden instance then try to find a non-hidden instance.
        EntityInstanceImpl ei = getEntityInstance();
        if ( ei == null )
            return CursorResult.NULL;  // There are no twins so return NULL.

        // Try to find a non-hidden twin.
        EntityInstanceImpl search = ei.getNextTwin();
        while ( search != null && search.isHidden() )
            search = search.getNextTwin();

        // If search is still null then we'll search for entities before ei.
        if ( search == null )
        {
            search = ei.getPrevTwin();
            while ( search != null && search.isHidden() )
                search = search.getPrevTwin();
        }

        // If search is *still* null then there are no non-hidden twins.
        // Return null.
        if ( search == null )
            return CursorResult.NULL;

        // Search points to a non-hidden entity.  Reset the cursor to the new entity
        // and return SET.
//        setEntityInstance( search );
        return CursorResult.UNDEFINED;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        // Don't use getEntityInstance() or getNull() because that will attempt to
        // establish the cursor.  We don't want toString() changing anything.
        if ( entityInstance == null )
            builder.append( getViewEntity() ).append( ": NULL" );
        else
            builder.append( entityInstance.toString() );

        return builder.toString();
    }

    @Override
    public boolean setToSubobject()
    {
        if ( viewEntity.getParent() == null )
            throw new ZeidonException("Entity %s is the root of the View OD", getViewEntity() );

        if ( ! viewEntity.isRecursive() )
            throw new ZeidonException("Entity %s is not recursive");

        // TODO: Check to see if subobject entity is already the parent?

        // Throws NullCursor if current cursor isn't set to a valid instance.
        EntityInstanceImpl ei = getExistingInstance();

        ViewEntity recursiveParent = getViewEntity().getRecursiveParentViewEntity();
        viewCursor.setRecursiveParent( ei );
        viewCursor.getEntityCursor( recursiveParent ).resetChildCursors( ei );
        return true;
    }

    @Override
    public boolean resetSubobjectToParent()
    {
        viewCursor.resetSubobjectToParent();
        return true;
    }

    EntityCursorImpl getParentCursor()
    {
        return parentCursor;
    }

    @Override
    public int getEntityCount() throws NullCursorException
    {
        EntityInstanceImpl ei = getEntityInstance(); // Establishes the cursor if necessary.
        if ( ei == null )
            return 0;

        return ei.getTwinCount();
    }

    @Override
    public EntityIterator<? extends EntityInstance> getChildren( ViewEntity childViewEntity)
    {
        return getChildren( childViewEntity, false );
    }


    @Override
    public EntityIterator<? extends EntityInstance> getChildren( ViewEntity childViewEntity,
                                                                 boolean allowHidden )
    {
        return new IteratorBuilder(getObjectInstance())
                        .allowHidden( allowHidden )
                        .withScoping( getExistingInstance() )
                        .forViewEntity( childViewEntity )
                        .build();
    }

    @Override
    public EntityIterator<? extends EntityInstance> getChildren(String childEntityName)
    {
        return getChildren( getViewOD().getViewEntity( childEntityName ) );
    }

    /**
     * Iterates through all the children of 'this' in heir order.  If includeParent
     * is true, then the iteration includes 'this' at the beginning.
     *
     * @param includeParent If true, include 'this'.
     *
     * @return
     */
    @Override
    public EntityIterator<EntityInstanceImpl> getChildrenHier( boolean includeParent )
    {
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                                                            .withScoping( getEntityInstance() )
                                                            .setLazyLoad( true )
                                                            .includeHierParent( includeParent )
                                                            .build();
        if ( ! includeParent && iter.hasNext() )
            iter.next();  // Skip past the parent.

        return iter;
    }


    @Override
    public int setMatchingAttributesByName(View source, String sourceViewEntity, EnumSet<SetMatchingFlags> control)
    {
        validateOiUpdate();
        return getExistingInstance().setMatchingAttributesByName( source, sourceViewEntity, control );
    }

    @Override
    public int setMatchingAttributesByName(View source, ViewEntity viewEntity, EnumSet<SetMatchingFlags> control)
    {
        validateOiUpdate();
        return getExistingInstance().setMatchingAttributesByName( source, viewEntity, control );
    }

    @Override
    public boolean isDbhCreated()
    {
        return getExistingInstance().isDbhCreated();
    }

    @Override
    public boolean isDbhDeleted()
    {
        return getExistingInstance().isDbhDeleted();
    }

    @Override
    public boolean isDbhExcluded()
    {
        return getExistingInstance().isDbhExcluded();
    }

    @Override
    public boolean isDbhUpdated()
    {
        return getExistingInstance().isDbhUpdated();
    }

    @Override
    public boolean isDbhIncluded()
    {
        return getExistingInstance().isDbhIncluded();
    }

    @Override
    public boolean isDbhNeedsInclude()
    {
        return getExistingInstance().isDbhNeedsInclude();
    }

    @Override
    public boolean isDbhSeqUpdated()
    {
        return getExistingInstance().isDbhSeqUpdated();
    }

    @Override
    public boolean isDbhGenKeyNeeded()
    {
        return getExistingInstance().isDbhGenKeyNeeded();
    }

    @Override
    public boolean isDbhNoGenKey()
    {
        return getExistingInstance().isDbhNoGenKey();
    }

    @Override
    public boolean isDbhForeignKey()
    {
        return getExistingInstance().isDbhForeignKey();
    }

    @Override
    public long getEntityKey()
    {
        return getExistingInstance().getEntityKey();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#getObjectInstanceId()
     */
    @Override
    public long getObjectInstanceId()
    {
        return getExistingInstance().getObjectInstanceId();
    }

    @Override
    public UUID getEntityUuid()
    {
        return getExistingInstance().getEntityUuid();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#getObjectInstanceId()
     */
    @Override
    public UUID getObjectInstanceUuid()
    {
        return getExistingInstance().getObjectInstanceUuid();
    }

    @Override
    public EntityInstance setAttribute(ViewAttribute viewAttribute, Object value)
    {
        return getExistingInstance().setAttribute( viewAttribute, value );
    }

    @Override
    public EntityInstance setAttribute(String attributeName, Object value)
    {
        return getExistingInstance().setAttribute( attributeName, value );
    }

    @Override
    public String getStringFromAttribute(String attributeName)
    {
        return getExistingInstance().getStringFromAttribute( getView(), getViewEntity().getAttribute( attributeName ) );
    }

    @Override
    public String getStringFromAttribute(String attributeName, String contextName )
    {
        return getExistingInstance().getStringFromAttribute( getView(), getViewEntity().getAttribute( attributeName ), contextName );
    }

    @Override
    public EntityInstance setAttribute(String attributeName, Object value, String contextName) throws InvalidAttributeValueException
    {
        return getExistingInstance().setAttribute( attributeName, value, contextName );
    }

    @Override
    public EntityInstance setAttribute(ViewAttribute viewAttribute, Object value, String contextName) throws InvalidAttributeValueException
    {
        return getExistingInstance().setAttribute( viewAttribute, value, contextName );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#setInternalAttributeValue(com.quinsoft.zeidon.objectdefinition.ViewAttribute, java.lang.Object)
     */
    @Override
    public EntityInstance setInternalAttributeValue(ViewAttribute viewAttribute, Object value, boolean setIncremental) throws InvalidAttributeValueException
    {
        return getExistingInstance().setInternalAttributeValue( viewAttribute, value, setIncremental );
    }

    @Override
    public ViewImpl getView()
    {
        return viewCursor.getView();
    }

    @Override
    public Object getInternalAttributeValue(String attributeName)
    {
        return getExistingInstance().getInternalAttributeValue( getView(), getViewEntity().getAttribute( attributeName ) );
    }

    @Override
    public Object getInternalAttributeValue( ViewAttribute viewAttribute )
    {
        return getExistingInstance( true ).getInternalAttributeValue( getView(), viewAttribute );
    }

    @Override
    public String getStringFromAttribute(ViewAttribute viewAttribute)
    {
        return getExistingInstance().getStringFromAttribute( getView(), viewAttribute );
    }

    @Override
    public String getStringFromAttribute(ViewAttribute viewAttribute, String contextName )
    {
        return getExistingInstance().getStringFromAttribute( getView(), viewAttribute, contextName );
    }

    @Override
    public boolean isAttributeNull(String attributeName)
    {
        return getExistingInstance().isAttributeNull( getView(), getViewEntity().getAttribute( attributeName ) );
    }

    @Override
    public boolean isAttributeNull(ViewAttribute viewAttribute)
    {
        return getExistingInstance().isAttributeNull( getView(), viewAttribute );
    }

    @Override
    public boolean isAttributeUpdated(ViewAttribute viewAttribute)
    {
        return getExistingInstance().isAttributeUpdated( viewAttribute );
    }

    @Override
    public EntityInstance setAttributeFromAttribute(String tgtAttributeName,
                                                    View srcView, String srcEntityName, String srcAttributeName)
    {
        return getExistingInstance().setAttributeFromAttribute( tgtAttributeName,
                                                                srcView,
                                                                srcEntityName,
                                                                srcAttributeName );
    }

    @Override
    public int compareAttribute(String attributeName, Object value)
    {
        return getExistingInstance().compareAttribute( getView(), getViewEntity().getAttribute( attributeName ), value );
    }

    @Override
    public int compareAttribute(ViewAttribute viewAttribute, Object value)
    {
        return getExistingInstance().compareAttribute( getView(), viewAttribute, value );
    }

    @Override
    public Integer getIntegerFromAttribute(String attributeName)
    {
        return getExistingInstance().getIntegerFromAttribute( getView(), getViewEntity().getAttribute( attributeName ) );
    }

    @Override
    public Integer getIntegerFromAttribute( ViewAttribute viewAttribute )
    {
        return getExistingInstance().getIntegerFromAttribute( getView(), viewAttribute );
    }

    @Override
    public Integer getIntegerFromAttribute(String attributeName, String contextName )
    {
        return getExistingInstance().getIntegerFromAttribute( getView(), getViewEntity().getAttribute( attributeName ), contextName );
    }

    @Override
    public Integer getIntegerFromAttribute( ViewAttribute viewAttribute, String contextName )
    {
        return getExistingInstance().getIntegerFromAttribute( getView(), viewAttribute, contextName );
    }

    @Override
    public Double getDoubleFromAttribute(String attributeName)
    {
        return getExistingInstance().getDoubleFromAttribute( getView(), getViewEntity().getAttribute( attributeName ) );
    }

    @Override
    public Double getDoubleFromAttribute( ViewAttribute viewAttribute )
    {
        return getExistingInstance().getDoubleFromAttribute( getView(), viewAttribute );
    }

    @Override
    public Double getDoubleFromAttribute(String attributeName, String contextName )
    {
        return getExistingInstance().getDoubleFromAttribute( getView(), getViewEntity().getAttribute( attributeName ), contextName );
    }

    @Override
    public Double getDoubleFromAttribute( ViewAttribute viewAttribute, String contextName )
    {
        return getExistingInstance().getDoubleFromAttribute( getView(), viewAttribute, contextName );
    }

    @Override
    public DateTime getDateTimeFromAttribute(String attributeName)
    {
        return getExistingInstance().getDateTimeFromAttribute( getView(), getViewEntity().getAttribute( attributeName ) );
    }

    @Override
    public DateTime getDateTimeFromAttribute( ViewAttribute viewAttribute )
    {
        return getExistingInstance().getDateTimeFromAttribute( getView(), viewAttribute );
    }

    @Override
    public DateTime getDateTimeFromAttribute(String attributeName, String contextName )
    {
        return getExistingInstance().getDateTimeFromAttribute( getView(), getViewEntity().getAttribute( attributeName ), contextName );
    }

    @Override
    public DateTime getDateTimeFromAttribute( ViewAttribute viewAttribute, String contextName )
    {
        return getExistingInstance().getDateTimeFromAttribute( getView(), viewAttribute, contextName );
    }

    @Override
    public Blob getBlobFromAttribute(String attributeName)
    {
        return getExistingInstance().getBlobFromAttribute( getView(), getViewEntity().getAttribute( attributeName ) );
    }

    @Override
    public Blob getBlobFromAttribute(ViewAttribute viewAttribute)
    {
        return getExistingInstance().getBlobFromAttribute( getView(), viewAttribute );
    }

    @Override
    public int compareAttribute(String attributeName, EntityInstance entityInstance, String attributeName2)
    {
        ViewAttribute viewAttribute = getViewEntity().getAttribute( attributeName );
        ViewAttribute viewAttribute2 = entityInstance.getViewEntity().getAttribute( attributeName2 );
        return getExistingInstance().compareAttribute( getView(), viewAttribute, entityInstance, viewAttribute2 );
    }

    @Override
    public int compareAttribute(ViewAttribute viewAttribute, EntityInstance entityInstance, ViewAttribute viewAttribute2)
    {
        return getExistingInstance().compareAttribute( getView(), viewAttribute, entityInstance, viewAttribute2 );
    }

    @Override
    public EntityIterator<EntityInstanceImpl> eachEntity()
    {
        return new IteratorBuilder(getObjectInstance())
                        .setCursor(EntityCursorImpl.this)
                        .forTwinsOf( getEntityInstance() )
                        .forViewEntity( getViewEntity() )
                        .build();
    }

    @Override
    public EntityIterator<? extends EntityInstance> eachEntity( String scopingEntityName )
    {
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return eachEntity();

        return eachEntity( getViewOD().getViewEntity( scopingEntityName ) );
    }

    @Override
    public EntityIterator<? extends EntityInstance> eachEntity( ViewEntity scopingEntity )
    {
        EntityInstanceImpl scopingEi = getScopingEntityInstance( scopingEntity );
        return new IteratorBuilder(getObjectInstance())
                        .withScoping( scopingEi )
                        .forViewEntity( getViewEntity() )
                        .setCursor(EntityCursorImpl.this)
                        .build();
    }

	@Override
	public CursorResult setLast(String scopingEntityName)
	{
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setLast();

        return setLast( getViewOD().getViewEntity( scopingEntityName ) );
	}

	@Override
	public CursorResult setLast(ViewEntity scopingEntity)
	{
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .withScoping( getScopingEntityInstance( scopingEntity ) )
                                    .forViewEntity( getViewEntity() )
                                    .setCursor( this )
                                    .setLast()
                                    .build();
        if ( ! currentIterator.hasPrev() ) // Is there a last entity?
            return CursorResult.NULL;

        currentIterator.prev();
        return CursorResult.SET;
	}

	@Override
	public CursorResult setLast(String attributeName, Object value)
	{
	    return setLast( getViewEntity().getAttribute( attributeName ), value );
	}

	@Override
	public CursorResult setLast(String attributeName, Object value, String scopingEntityName)
	{
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setLast( attributeName, value );

        return setLast( getViewEntity().getAttribute( attributeName ), value, getViewOD().getViewEntity( scopingEntityName ) );
	}

	@Override
	public CursorResult setLast(ViewAttribute viewAttribute, Object value)
	{
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .forTwinsOf( getEntityInstance() )
                                    .forViewEntity( getViewEntity() )
                                    .setCursor( this )
                                    .withAttributeValue( viewAttribute, value )
                                    .setLast()
                                    .build();
        if ( ! currentIterator.hasPrev() ) // Is there a last entity?
            return CursorResult.NULL;

        currentIterator.prev();
        return CursorResult.SET;
	}

	@Override
    public CursorResult setLast( ViewAttribute attribute, Object value, ViewEntity scopingEntity )
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException( "Not written yet" );
    }

    @Override
    public CursorResult setPrev()
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                .forViewEntity( getViewEntity() )
                                .setCursor( this )
                                .currentInstance( getEntityInstance() )
                                .setLast()
                                .build();

        if ( ! currentIterator.hasPrev() )
            return CursorResult.UNCHANGED;

        currentIterator.prev();
        return CursorResult.SET;
    }


    @Override
    public CursorResult setPrev(String scopingEntityName)
    {
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setPrev();

        return setPrev( getViewOD().getViewEntity( scopingEntityName ) );
    }

    @Override
    public CursorResult setPrev(ViewEntity scopingEntity)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .withScoping( getScopingEntityInstance( scopingEntity ) )
                                    .forViewEntity( getViewEntity() )
                                    .setCursor( this )
                                    .currentInstance( getEntityInstance() )
                                    .build();
        if ( ! currentIterator.hasPrev() )
            return CursorResult.NULL;

        currentIterator.prev();
        return CursorResult.SET;
    }

    @Override
    public CursorResult setPrev(String attributeName, Object value, String scopingEntityName)
    {
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setPrev(getViewEntity().getAttribute( attributeName ), value);

        return setPrev( getViewEntity().getAttribute( attributeName ), value, getViewOD().getViewEntity( scopingEntityName ) );
    }

    @Override
    public CursorResult setPrev( ViewAttribute attribute, Object value, ViewEntity scopingEntity)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
					        .withScoping( getScopingEntityInstance( scopingEntity ) )
					        .forViewEntity( getViewEntity() )
					        .setCursor( this )
					        .currentInstance( getEntityInstance() )
                            .withAttributeValue( attribute, value )
					        .build();

		if ( ! currentIterator.hasPrev() )
		return CursorResult.NULL;

		currentIterator.prev();
		return CursorResult.SET;

    }

    @Override
    public CursorResult setPrev( String attributeName, Object value )
    {
        return setPrev( getViewEntity().getAttribute( attributeName ), value );
    }

    @Override
    public CursorResult setPrev( ViewAttribute attribute, Object value )
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                .forViewEntity( getViewEntity() )
                                .setCursor( this )
                                .currentInstance( getEntityInstance() )
                                .withAttributeValue( attribute, value )
                                .setLast()
                                .build();

        if ( ! currentIterator.hasPrev() )
            return CursorResult.UNCHANGED;

        currentIterator.prev();
        return CursorResult.SET;
    }

    @Override
    public Object addToAttribute( String attributeName, Object value )
    {
        return getExistingInstance().addToAttribute( getView(), getViewEntity().getAttribute( attributeName ), value );
    }

    @Override
    public Object addToAttribute( ViewAttribute viewAttribute, Object value )
    {
        return getExistingInstance().addToAttribute( getView(), viewAttribute, value );
    }

    @Override
    public Object multiplyAttribute( String attributeName, Object value )
    {
        return getExistingInstance().multiplyAttribute( getView(), getViewEntity().getAttribute( attributeName ), value );
    }

    @Override
    public Object multiplyAttribute( ViewAttribute viewAttribute, Object value )
    {
        return getExistingInstance().multiplyAttribute( getView(), viewAttribute, value );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#display()
     */
    @Override
    public void logEntity()
    {
        logEntity( true, 0 );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#display(boolean)
     */
    @Override
    public void logEntity(boolean displayChildren)
    {
        logEntity( displayChildren, 0 );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#display(boolean, int)
     */
    @Override
    public void logEntity(boolean displayChildren, int indent)
    {
        getExistingInstance().logEntity( displayChildren, indent );
    }

    private void moveTwin(EntityInstanceImpl target, CursorPosition position, EntityInstanceImpl source)
    {
        validateOiUpdate();
        EntityInstanceImpl lastHierChild = source.removeEntityFromChains();
        source.insertInstance( getObjectInstance(), target.getParent(), target, position, lastHierChild );

        // Set OI incremental flags if the entities are sequenced.
        if ( getViewEntity().getAutoSeq() != null )
        {
            target.setUpdated( true, false, true );
            source.setUpdated( true, false, true );
        }

        assert validateChains() : "Something is wrong with the chain pointers";
    }

    /**
     * @deprecated Use logEntity instead.
     */
    @Deprecated
    @Override
    public void displayEntity()
    {
        logEntity( true, 0 );
    }

    /**
     * @deprecated Use logEntity instead.
     */
    @Deprecated
    @Override
    public void displayEntity(boolean logChildren)
    {
        logEntity( logChildren, 0 );
    }

    /**
     * @deprecated Use logEntity instead.
     */
    @Deprecated
    @Override
    public void displayEntity(boolean logChildren, int indentN)
    {
        logEntity( logChildren, indentN );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#moveSubobject(int, com.quinsoft.zeidon.EntityCursor, int)
     */
    @Override
    public CursorResult moveSubobject(CursorPosition position, EntityCursor source, CursorPosition sourceReposition)
    {
        EntityInstance sourceInstance = source.getEntityInstance();
        ((EntityCursorImpl) source).repositionCursor( sourceReposition );
        return moveSubobject( position, sourceInstance );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#moveSubobject(int, com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public CursorResult moveSubobject(CursorPosition position, EntityInstance source)
    {
        validateOiUpdate();
        EntityInstanceImpl target = getExistingInstance();

        // If source and target are the same then there's nothing to do.
        if ( target == source )
            return CursorResult.UNCHANGED;

        // Currently we only support moving entities with the same parent.
        // If this changes then we need to verify max cardinality.
        if ( target.getParent() != source.getParent() )
            throw new ZeidonException( "moveSubobject only supported for twin entities" );

        moveTwin( target, position, (EntityInstanceImpl) source );
        return CursorResult.SET;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#getHierPosition()
     */
    @Override
    public long getHierPosition()
    {
        return getExistingInstance().getHierPosition();
    }

    @Override
    public long getPosition()
    {
        return getExistingInstance().getPosition();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setFirstWithinOi()
     */
    @Override
    public CursorResult setFirstWithinOi()
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .setCursor( this )
                                    .withOiScoping( getObjectInstance() )
                                    .forViewEntity( getViewEntity() )
                                    .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.NULL;

        currentIterator.next();
        return CursorResult.SET;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setNextWithinOi()
     */
    @Override
    public CursorResult setNextWithinOi()
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .setCursor( this )
                                    .withOiScoping( getObjectInstance() )
                                    .currentInstance( getEntityInstance() )
                                    .forViewEntity( getViewEntity() )
                                    .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.UNCHANGED;

        currentIterator.next();
        return CursorResult.SET;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasNext()
     */
    @Override
    public boolean hasNext()
    {
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                                            .currentInstance( getEntityInstance() )
                                            .forViewEntity( getViewEntity() )
                                            .build();
        return iter.hasNext();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasNext()
     */
    @Override
    public boolean hasPrev()
    {
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                                                .forViewEntity( getViewEntity() )
                                                .currentInstance( getEntityInstance() )
                                                .setLast()
                                                .build();

        return iter.hasPrev();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAny()
     */
    @Override
    public boolean hasAny()
    {
        EntityIterator<EntityInstanceImpl> iter =
                new IteratorBuilder( getObjectInstance() )
                        .forViewEntity( getViewEntity() )
                        .forTwinsOf( getEntityInstance() )
                        .build();

        return iter.hasNext();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAny(java.lang.String)
     */
    @Override
    public boolean hasAny(String scopingEntityName)
    {
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return hasAny();

        return hasAny( getViewOD().getViewEntity( scopingEntityName ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAny(com.quinsoft.zeidon.objectdefinition.ViewEntity)
     */
    @Override
    public boolean hasAny(ViewEntity scopingViewEntity)
    {
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                                            .withScoping( getScopingEntityInstance( scopingViewEntity ) )
                                            .forViewEntity( getViewEntity() )
                                            .build();
        return iter.hasNext();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAnyWithinOi()
     */
    @Override
    public boolean hasAnyWithinOi()
    {
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                                            .withOiScoping( getObjectInstance() )
                                            .forViewEntity( getViewEntity() )
                                            .build();
        return iter.hasNext();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setFirstWithinOi(java.lang.String, java.lang.Object)
     */
    @Override
    public CursorResult setFirstWithinOi( ViewAttribute viewAttribute, Object value)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .setCursor( this )
                                    .withOiScoping( getObjectInstance() )
                                    .forViewEntity( getViewEntity() )
                                    .withAttributeValue( viewAttribute, value )
                                    .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.NULL;

        currentIterator.next();
        return CursorResult.SET;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setNextWithinOi(com.quinsoft.zeidon.objectdefinition.ViewAttribute, java.lang.Object)
     */
    @Override
    public CursorResult setNextWithinOi(ViewAttribute viewAttribute, Object value)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .setCursor( this )
                                    .withOiScoping( getObjectInstance() )
                                    .currentInstance( getEntityInstance() )
                                    .forViewEntity( getViewEntity() )
                                    .withAttributeValue( viewAttribute, value )
                                    .build();

        if ( ! currentIterator.hasNext() )
            return CursorResult.UNCHANGED;

        currentIterator.next();
        return CursorResult.SET;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAny(java.lang.String, java.lang.Object)
     */
    @Override
    public boolean hasAny(String attributeName, Object value)
    {
        return hasAny( getViewEntity().getAttribute( attributeName ), value );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAny(java.lang.String, java.lang.Object, java.lang.String)
     */
    @Override
    public boolean hasAny(String attributeName, Object value, String scopingEntityName)
    {
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return hasAny( attributeName, value );

        return hasAny( getViewEntity().getAttribute( attributeName ), value, getViewOD().getViewEntity( scopingEntityName ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAnyWithinOi(java.lang.String, java.lang.Object)
     */
    @Override
    public boolean hasAnyWithinOi(String attributeName, Object value)
    {
        return hasAnyWithinOi( getViewEntity().getAttribute( attributeName ), value );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setLastWithinOi()
     */
    @Override
    public CursorResult setLastWithinOi()
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .setCursor( this )
                                    .withOiScoping( getObjectInstance() )
                                    .forViewEntity( getViewEntity() )
                                    .setLast()
                                    .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.NULL;

        currentIterator.next();
        return CursorResult.SET;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setLastWithinOi(java.lang.String, java.lang.Object)
     */
    @Override
    public CursorResult setLastWithinOi(String attributeName, Object value)
    {
        return setLastWithinOi( getViewEntity().getAttribute( attributeName ), value );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setPrevWithinOi()
     */
    @Override
    public CursorResult setPrevWithinOi()
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .setCursor( this )
                                    .withOiScoping( getObjectInstance() )
                                    .currentInstance( getEntityInstance() )
                                    .forViewEntity( getViewEntity() )
                                    .setLast()
                                    .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.UNCHANGED;

        currentIterator.next();
        return CursorResult.SET;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setPrevWithinOi(com.quinsoft.zeidon.objectdefinition.ViewAttribute, java.lang.Object)
     */
    @Override
    public CursorResult setPrevWithinOi(ViewAttribute viewAttribute, Object value)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .setCursor( this )
                                    .withOiScoping( getObjectInstance() )
                                    .currentInstance( getEntityInstance() )
                                    .forViewEntity( getViewEntity() )
                                    .withAttributeValue( viewAttribute, value )
                                    .setLast()
                                    .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.UNCHANGED;

        currentIterator.next();
        return CursorResult.SET;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setFirstWithinOi(java.lang.String, java.lang.Object)
     */
    @Override
    public CursorResult setFirstWithinOi(String attributeName, Object value)
    {
        return setFirstWithinOi( getViewEntity().getAttribute( attributeName ), value );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setNextWithinOi(java.lang.String, java.lang.Object)
     */
    @Override
    public CursorResult setNextWithinOi(String attributeName, Object value)
    {
        return setNextWithinOi( getViewEntity().getAttribute( attributeName ), value );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setLastWithinOi(com.quinsoft.zeidon.objectdefinition.ViewAttribute, java.lang.Object)
     */
    @Override
    public CursorResult setLastWithinOi(ViewAttribute viewAttribute, Object value)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                .setCursor( this )
                                .withOiScoping( getObjectInstance() )
                                .forViewEntity( getViewEntity() )
                                .withAttributeValue( viewAttribute, value )
                                .setLast()
                                .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.NULL;

        currentIterator.next();
        return CursorResult.SET;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAny(com.quinsoft.zeidon.objectdefinition.ViewAttribute, java.lang.Object)
     */
    @Override
    public boolean hasAny(ViewAttribute viewAttribute, Object value)
    {
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                            .forViewEntity( getViewEntity() )
                            .withAttributeValue( viewAttribute, value )
                            .build();
        return iter.hasNext();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAny(com.quinsoft.zeidon.objectdefinition.ViewAttribute, java.lang.Object, com.quinsoft.zeidon.objectdefinition.ViewEntity)
     */
    @Override
    public boolean hasAny(ViewAttribute viewAttribute, Object value, ViewEntity scopingViewEntity)
    {
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                            .forViewEntity( getViewEntity() )
                            .withScoping( getScopingEntityInstance( scopingViewEntity ) )
                            .withAttributeValue( viewAttribute, value )
                            .build();
        return iter.hasNext();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAnyWithinOi(com.quinsoft.zeidon.objectdefinition.ViewAttribute, java.lang.Object)
     */
    @Override
    public boolean hasAnyWithinOi(ViewAttribute viewAttribute, Object value)
    {
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                            .withOiScoping( getObjectInstance() )
                            .forViewEntity( getViewEntity() )
                            .withAttributeValue( viewAttribute, value )
                            .build();
        return iter.hasNext();
   }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#isLinked(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public boolean isLinked( EntityInstance ei )
    {
        return getExistingInstance().isLinked( ei );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#getPrevTwin()
     */
    @Override
    public EntityInstance getPrevTwin()
    {
        return getExistingInstance().getPrevTwin();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#getNextTwin()
     */
    @Override
    public EntityInstance getNextTwin()
    {
        return getExistingInstance().getNextTwin();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#linkInstances(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public boolean linkInstances( EntityInstance ei )
    {
        return getExistingInstance().linkInstances( ei );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#getLinkedInstances()
     */
    @Override
    public Collection<? extends EntityInstance> getLinkedInstances()
    {
        return getExistingInstance().getLinkedInstances();
    }

    boolean validateChains()
    {
        return getObjectInstance().validateChains();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#setMatchingAttributesByName(com.quinsoft.zeidon.EntityInstance, long)
     */
    @Override
    public int setMatchingAttributesByName( EntityInstance sourceInstance, EnumSet<SetMatchingFlags> control )
    {
        return getExistingInstance().setMatchingAttributesByName( sourceInstance, control );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#setMatchingAttributesByName(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public int setMatchingAttributesByName( EntityInstance sourceInstance )
    {
        return getExistingInstance().setMatchingAttributesByName( sourceInstance );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#getStatus()
     */
    @Override
    public CursorStatus getStatus()
    {
        if ( ! viewCursor.isCursorInScope( this ) )
            return CursorStatus.OUT_OF_SCOPE;

        EntityInstanceImpl ei = getEntityInstance( false );
        if ( ei == null )
        {
            // The cursor is null.  It's possible that we haven't loaded a lazy load
            // entity.  Check for that by looking at each of the conditions required
            // for lazy load.  If none of them are true then return NULL.

            // Do we allow lazy loading for this entity?  If not, must be null.
            LazyLoadConfig config = getViewEntity().getLazyLoadConfig();
            if ( config.isLazyLoad() )
            {
                assert parentCursor != null : "Root cannot be lazy load candidate";

                EntityInstanceImpl parent = parentCursor.getEntityInstance( false );
                if ( parent == null )
                    // TODO: What if there are multiple levels of LazyLoad?  It's possible that
                    // the parent entity wasn't loaded because it is lazyload.
                    return CursorStatus.NULL;

                if ( parent.hasChildBeenLazyLoaded( getViewEntity() ) )
                    return CursorStatus.NULL;

                return CursorStatus.NOT_LOADED;
            }
            else
            if ( config.hasLazyLoadParent() )
            {
                assert parentCursor != null : "Root cannot be lazy load candidate";

                // Get the status of the parent.  If the status isn't normal (i.e. set to an EI)
                // then the child must have the same status.
                EntityCursorImpl pcursor = getViewCursor().getEntityCursor( config.getLazyLoadParent() );
                CursorStatus parentStatus = pcursor.getStatus();
                if ( parentStatus != CursorStatus.SET )
                    return parentStatus;
            }

            // The lazy-load parent has been loaded.  That means we must be null.
            return CursorStatus.NULL;
        }

        if ( ei.isHidden() )
            return CursorStatus.HIDDEN;

        return CursorStatus.SET;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#getKeyString()
     */
    @Override
    public String getKeyString()
    {
        return getExistingInstance().getKeyString();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#setIncrementalFlags(java.util.EnumSet)
     */
    @Override
    public EntityInstanceImpl setIncrementalFlags( EnumSet<IncrementalEntityFlags> flags )
    {
        return getExistingInstance( true ).setIncrementalFlags( flags );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#setIncrementalFlags(com.quinsoft.zeidon.standardoe.IncrementalEntityFlags)
     */
    @Override
    public EntityInstance setIncrementalFlags( IncrementalEntityFlags flag )
    {
        return getExistingInstance( true ).setIncrementalFlags( flag );
    }

    @Override
    public Collection<ZeidonException> validateSubobject()
    {
        return getExistingInstance().validateSubobject();
    }

    private ViewCursor getViewCursor()
    {
        return viewCursor;
    }

    @Override
    public AttributeInstance getAttribute( String attributeName )
    {
        ViewAttribute viewAttribute = getViewEntity().getAttribute( attributeName );
        return getAttribute( viewAttribute );
    }

    @Override
    public AttributeInstance getAttribute( ViewAttribute viewAttribute )
    {
        AttributeInstanceImpl attributeInstance = getExistingInstance().getAttribute( viewAttribute );
        attributeInstance.setView( getView() );
        return attributeInstance;
    }
}