/**
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

import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.CompareEntityOptions;
import com.quinsoft.zeidon.CopyAttributesBuilder;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityConstraintType;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.EntityIterator;
import com.quinsoft.zeidon.HiddenAttributeException;
import com.quinsoft.zeidon.HiddenCursorException;
import com.quinsoft.zeidon.IncludeFlags;
import com.quinsoft.zeidon.NullCursorException;
import com.quinsoft.zeidon.OutOfScopeException;
import com.quinsoft.zeidon.SetMatchingFlags;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DynamicAttributeDefConfiguration;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LazyLoadConfig;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * @author DG
 *
 */
class EntityCursorImpl implements EntityCursor
{
    private final EntityDef        entityDef;
    private final ViewCursor       viewCursor;

    private EntityIterator<EntityInstanceImpl> currentIterator;

    /**
     * Used to keep track of most recent setFirst/setLast.
     */
    private boolean forwardDirection = true;

    /**
     * This points to the cursor's current entity instance.  It's a weak reference so
     * that if the entity is dropped then this cursor will release it.
     */
    private EntityInstanceImpl entityInstance;

    EntityCursorImpl(ViewCursor viewCursor, EntityDef entityDef)
    {
        this.viewCursor = viewCursor;
        this.entityDef = entityDef;
        setEntityInstance( null );
    }

    /**
     * Create a cursor and initialize it to point to the same entity as 'source'.
     *
     * @param viewCursor
     * @param source
     * @param parentCsr
     */
    EntityCursorImpl( ViewCursor viewCursor, EntityCursorImpl source )
    {
        this( viewCursor, source.getEntityDef() );
        setEntityInstance( source.entityInstance );
        if ( source.currentIterator != null)
            currentIterator = IteratorBuilder.build( source.currentIterator, this );
    }

    protected ObjectInstance getObjectInstance()
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
            if ( entityDef.getParent() == null )
                entityInstance = getObjectInstance().getRootEntityInstance();
            else
            {
                // Try to get the entity instance for the parent.
                EntityCursorImpl parentCsr = viewCursor.getEntityCursor( entityDef.getParent() );
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
                     viewCursor.getRecursiveRoot().getDepth() > parentInstance.getDepth() )
                {
                    viewCursor.getView().logObjectInstance();
                    throw new ZeidonException("Internal error: parent level for %s doesn't match " +
                                              "level for suboject root %s", parentInstance,
                                              viewCursor.getRecursiveRoot() );
                }

                // Check to see if we need to load a lazy child.
                if ( allowLazyLoad )
                    parentInstance.lazyLoadChild( getView(), getEntityDef() );

                EntityInstanceImpl searchInstance = null;

                // We need to find the first entityInstance under the parent.  The
                // quickest way is to find a previous sibling and search hier from there.
                EntityDef prevSibling = entityDef.getPrevSibling();
                if ( prevSibling != null)
                    // Use getEntityInstance because its possible for the cursor to be null.
                    searchInstance = viewCursor.getEntityCursor( prevSibling ).getEntityInstance( false );

                // No siblings were found so start the search from the parent.
                if ( searchInstance == null )
                    searchInstance = parentInstance.getNextHier();

                // Find the first entity instance under the parent that:
                //    1) Has the same entityDef for the cursor we're trying to set.
                //    2) Has the same instance level for the entityDef + recursive diff.
                //       Checking for instance level will skip over recursive suboject
                //       instances with the same LodDef.
                int level = entityDef.getDepth() + viewCursor.getRecursiveDiff();
                for ( ; searchInstance != null; searchInstance = searchInstance.getNextHier() )
                {
                    // If the searchInstance level is less than the parent then there is no
                    // entity that matches what we want.
                    if ( searchInstance.getDepth() <= parentInstance.getDepth() )
                    {
                        searchInstance = null;
                        break;
                    }

                    if ( searchInstance.isHidden() )
                        continue;

                    EntityDef searchEntityDef = searchInstance.getEntityDef();
                    if ( searchEntityDef == entityDef && searchInstance.getDepth() == level )
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
        if ( ! viewCursor.isCursorInScope( this ) )
            throw new OutOfScopeException( this );

        EntityInstanceImpl ei = getEntityInstance();  // Potentially sets UNSET_CSR.
        if ( ei == null )
            throw new NullCursorException( this );

       if ( ! allowHidden && ei.isHidden() )
            throw new HiddenCursorException( this );

       if ( ! viewCursor.isCursorInScope( this ) )
           throw new ZeidonException( "Cursor %s is out of scope", getEntityDef() );

       return ei;
    }

    private EntityInstanceImpl getExistingInstance() throws NullCursorException
    {
        return getExistingInstance( getView().isAllowHiddenEntities() );
    }

    /**
     * Sets the EntityInstance for this cursor.
     *
     * Note:  Most code should call setCursor( ei ) instead of this method.
     * Other than simple validity checking this code does not perform any
     * extra processing (like reseting child cursors).  This should only be used by code
     * that expects to explicitly set all the cursors.
     *
     * @param entityInstance
     * @return
     */
    EntityInstanceImpl setEntityInstance( EntityInstanceImpl entityInstance )
    {
        if ( entityInstance == null ||
             entityInstance.getEntityDef() == getEntityDef() ||
             entityInstance.getEntityDef().getRecursiveParent() == getEntityDef() )
        {
            this.entityInstance = entityInstance;
            return entityInstance;
        }

        throw new ZeidonException( "Internal error: Attempting to set a cursor to an invalid entity def" );
    }

    @Override
    public EntityInstanceImpl getParent()
    {
        if ( getParentCursor() == null )
            return null;

        // If this entity is a recursive parent and the current view is in a subobject
        // then the parent is not necessarily determined by the parent cursor.
        if ( getEntityDef().isRecursiveParent() && viewCursor.getRecursiveDiff() > 0 )
        {
            // The current EntityDef is the parent of a recursive relationship and recursiveDiff
            // indicates we have a recursive subobject.  Check to see if there is a recursive root.
            if ( getViewCursor().getRecursiveRoot() == null )
            {
                // The recursive root is null.  This means that when setSubobject was called
                // the subobject child was null and is now the parent.  Return the EI that
                // is the parent of the null EI.
                return getViewCursor().getRecursiveRootParent();
            }
            else
                return getViewCursor().getRecursiveRoot().getParent();
//                assert getViewCursor().getRecursiveRoot().getParent() == getParentCursor().getExistingInstance();
        }

        EntityInstanceImpl parent = getParentCursor().getExistingInstance();
        return parent;
    }

    @Override
    public EntityInstance copySubobject( EntityInstance source, CursorPosition position )
    {
        if ( ! getEntityDef().isCreate() )
            throw new ZeidonException( "Entity is not flagged for create." )
                            .prependEntityDef( getEntityDef() );

        if ( getEntityDef() != source.getEntityDef() )
            throw new ZeidonException( "Source and target entity definitions must be the same." )
                            .prependEntityDef( getEntityDef() )
                            .prependMessage( "Source Entity = ", source.getEntityDef().getName() );

        EntityInstanceImpl sourceInstance = (EntityInstanceImpl) source.getEntityInstance();
        createEntity( position );
        setMatchingAttributesByName( sourceInstance );

        // Now copy children.  We can't use the usual iterator because we need to skip over
        // children if we include a child entity.
        for ( EntityInstanceImpl child = sourceInstance.getNextHier();
              child != null && child.getDepth() > sourceInstance.getDepth();
              child = child.getNextHier() )
        {
            if ( child.isHidden() )
            {
                // Skip hidden children.
                child = child.getLastChildHier();
                continue;
            }

            EntityDef childEntityDef = child.getEntityDef();
            EntityCursorImpl childCursor = getView().cursor( childEntityDef );

            if ( childEntityDef.isCreate() )
            {
                childCursor.createEntity();
                childCursor.setMatchingAttributesByName( child );
                continue;
            }

            if ( childEntityDef.isInclude() )
            {
                childCursor.includeSubobject( child );
                child = child.getLastChildHier();
                continue;
            }

            throw new ZeidonException( "Copied child entity is neither creatable or includable." )
                            .prependEntityDef( childEntityDef );
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
        if ( ! flags.contains( CreateEntityFlags.fIGNORE_PERMISSIONS ) && ! getEntityDef().isCreate() )
            throw new ZeidonException( "Entity is not flagged for create." )
                            .prependEntityDef( getEntityDef() );

        EntityInstanceImpl parent = getParent();  // Throws NullCursor if cursor is null.

        if ( ! flags.contains( CreateEntityFlags.fIGNORE_MAX_CARDINALITY ) )
            validateMaxCardinality();

        // If the entity is a derived entity or a work entity (both marked as derived) then
        // we don't need to check if the entity is read only.
        if ( ! flags.contains( CreateEntityFlags.fIGNORE_PERMISSIONS ) )
           validateOiUpdate();

        EntityInstanceImpl ei = getEntityInstance();

        EntityDef newInstanceEntityDef = getEntityDef();

        // Check for an edge case.  See if the EntityDef of the parent is the same as the
        // one we're about to create.  If it is then we are creating the child of a recursive
        // relationship.  If the EntityDef is the recursive parent then the LodDef of
        // the new instance should be the recursive child.
        if ( parent != null )
        {
            EntityDef parentEntityDef = parent.getEntityDef();

            if ( newInstanceEntityDef == parentEntityDef && // Recursive relationship?
                 newInstanceEntityDef.isRecursiveParent() )        // EntityDef is recursive parent?
            {
                // Change the EntityDef of the instance we're about to create to be the child
                // of the recursive relationship.
                newInstanceEntityDef = newInstanceEntityDef.getRecursiveChild();
            }
            else
            if ( newInstanceEntityDef.getRecursiveChild() == parentEntityDef )
                newInstanceEntityDef = newInstanceEntityDef.getRecursiveChild();
        }

        // Create a new instance and initialize the attributes.
        EntityInstanceImpl newInstance =
                EntityInstanceImpl.createEntity( getObjectInstance(),
                                                 parent,
                                                 ei,
                                                 newInstanceEntityDef,
                                                 position );

        // If recursiveDiff is > 0 then we are in a recursive subobject.  If recursiveRoot is
        // null then we just created the root of the recursive subobject so set it.
        if ( getViewCursor().getRecursiveDiff() > 0 && getViewCursor().getRecursiveRoot() == null )
        {
            getViewCursor().setRecursiveParent( newInstance, newInstanceEntityDef, null );
        }

        newInstance.initializeDefaultAttributes( flags );

        if ( ! flags.contains( CreateEntityFlags.fDONT_UPDATE_OI ) && ! newInstance.isVersioned() )
            getObjectInstance().setUpdated( true );

        if ( ! flags.contains( CreateEntityFlags.fNO_SPAWNING ) )
        {
            EntitySpawner spawner = new EntitySpawner( newInstance );
            spawner.spawnCreate();
        }

        if ( flags.contains( CreateEntityFlags.fDBHANDLER ) )
            newInstance.dbhLoaded = true;

        resetChildCursors( newInstance );

        // Check to see if we need to execute the create constraint.  We'll assume we don't
        // execute it if the initialize flag is set because we don't want to execute the
        // constraint when loading from DB/file.
        if ( getEntityDef().hasCreateConstraint() &&
             ! flags.contains( CreateEntityFlags.fDONT_INITIALIZE_ATTRIBUTES ) )
        {
            entityDef.executeEntityConstraint( getView(), EntityConstraintType.CREATE );
        }

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
        EntityInstanceImpl ei = createEntity( position,
                                              CreateEntityFlags.fNO_SPAWNING,
                                              CreateEntityFlags.fDONT_UPDATE_OI );
        ei.setVersionedEntity();

        assert validateChains() : "Something is wrong with the chain pointers";
        return ei;
    }

    protected LodDef getLodDef()
    {
        return viewCursor.getLodDef();
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
              resetCsr != null && resetCsr.getEntityDef().getDepth() > this.getEntityDef().getDepth();
              resetCsr = resetCsr.getNextHierCursor() )
        {
            resetCsr.setEntityInstance( null );
        }
    }

    /**
     * Validate that ei's EntityDef match that of the cursor.  Throws an exception if not.
     *
     * @return true if ei is a recursive child of this cursor, return false if not recursive.
     */
    private boolean validateEntityForCursor( EntityInstanceImpl ei )
    {
        // If they are the same entity then we're good.
        EntityDef sourceEntityDef = ei.getEntityDef();
        if ( sourceEntityDef == getEntityDef() )
        {
            if ( sourceEntityDef.isRecursive() )
                return true;
            else
                return false;
        }

        // Check to see if ei is a recursive child of this cursor.
        if ( sourceEntityDef.isRecursive() && sourceEntityDef.getRecursiveParent() == getEntityDef() )
            return true;

        // If we get here then Houston we have a problem.
        throw new ZeidonException( "Entity Instance %s is not a valid entity for this cursor.", sourceEntityDef );
    }

    private EntityInstanceImpl findMatchingLinkedInstance( EntityInstanceImpl targetInstance )
    {
        // The targetInstance belongs to a different OI.  Let's see if we can find a linked
        // instance that belongs to the current OI.
        for ( EntityInstanceImpl ei : targetInstance.getLinkedInstances() )
        {
            if ( ei.getObjectInstance() == getObjectInstance() && ei.getEntityDef() == targetInstance.getEntityDef() )
                return ei;
        }

        // If we get here we didn't find one.
        throw new ZeidonException( "Attempting to set a cursor to an Entity Instance that is from a different OI" );
    }

    /**
     * Set 'this' cursor to match targetCursor.  This will change parent and child
     * cursors if necessary.
     *
     * @param targetCursor
     * @return
     */
    private CursorResult setCursorFromCursor( EntityCursorImpl targetCursor )
    {
        assert targetCursor.getEntityDef() == getEntityDef();
        assert targetCursor.getObjectInstance() == getObjectInstance();

        CursorResult cursorResult = CursorResult.SET;

        // Find the top-most parent cursor that needs to be changed.
        EntityCursorImpl parentTargetCursor = targetCursor;
        EntityCursorImpl parentSourceCursor = this;
        while ( parentTargetCursor.getParentCursor() != null &&
                parentTargetCursor.getParentCursor().entityInstance !=
                        parentSourceCursor.getParentCursor().entityInstance )
        {
            cursorResult = CursorResult.SET_NEWPARENT;
            parentTargetCursor = parentTargetCursor.getParentCursor();
            parentSourceCursor = parentSourceCursor.getParentCursor();
        }

        // Set the top-most parent and then set all children to UNSET.
        parentSourceCursor.resetChildCursors( parentTargetCursor.entityInstance );

        // Now search through the parents again.  This time we'll set the cursors.
        if ( parentTargetCursor != targetCursor )
        {
            parentTargetCursor = targetCursor;
            parentSourceCursor = this;
            while ( parentTargetCursor.getParentCursor() != null &&
                    parentTargetCursor.getParentCursor().entityInstance !=
                            parentSourceCursor.getParentCursor().entityInstance )
            {
                // Set don't use setEntityInstance because that will attempt to set parents
                // and other logic.  We'll just set it directly.
                parentSourceCursor.entityInstance = parentSourceCursor.entityInstance;

                parentTargetCursor = parentTargetCursor.getParentCursor();
                parentSourceCursor = parentSourceCursor.getParentCursor();
            }

            // If we are changing parents then it's also possible we're changing
            // recursive entities.  Just copy the values from the target.
            viewCursor.copyRecursiveSettings( targetCursor.getViewCursor() );
        }

        return cursorResult;
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
        if ( targetInstance == null )
            throw new ZeidonException("Cannot set a cursor to null.");

        // Optimization: if the target instance is a cursor then we can just
        // copy the entity instances from the target.
        // TODO: Following doesn't appear to be faster.  Why not?
//        if ( targetInstance instanceof EntityCursorImpl &&
//             targetInstance.getEntityDef() == getEntityDef() )
//        {
//            EntityCursorImpl targetCursor = (EntityCursorImpl) targetInstance;
//            if ( targetCursor.getObjectInstance() == getObjectInstance() )
//                return setCursorFromCursor( (EntityCursorImpl) targetInstance );
//        }

        // Convert the targetInstance to an EntityInstanceImpl
        EntityInstanceImpl newInstance = (EntityInstanceImpl) targetInstance.getEntityInstance();

        if ( newInstance.getObjectInstance() != getObjectInstance() )
            newInstance = findMatchingLinkedInstance( newInstance );

        boolean recursive = validateEntityForCursor( newInstance );

        // Default return code to setting the cursor.
        CursorResult cursorResult = CursorResult.SET;

        if ( recursive || getEntityDef().isRecursivePath() )
        {
            // If we get here then we're setting a subobject cursor.  We have a couple of situations to handle.
            // To illustrate, assume the following recursive subobject where A is the recursive parent of A'.
            //     A
            //     |
            //     B
            //    / \
            //   A'  C
            //   |
            //   D

            EntityDef targetEntityDef = newInstance.getEntityDef();
            EntityDef recursiveParent;

            if ( ! recursive )
            {
                // If we get here then we're not setting A or A' but one of the other
                // cursors (B, C or D above).

                // Find the recursive parent (A).
                recursiveParent = getEntityDef();
                while ( recursiveParent.getRecursiveChild() == null )
                    recursiveParent = recursiveParent.getParent();
            }
            else
                recursiveParent = targetEntityDef.getRecursiveParent();

            // We're setting the parent cursor (A) to a subobject child (A').
            if ( getEntityDef() == recursiveParent )
            {
                // Set the recursive structure.  We'll set the cursors later on.
                viewCursor.setRecursiveParent( newInstance, targetEntityDef, null );
            }
            else
            {
                assert getEntityDef() == targetEntityDef;

                EntityInstanceImpl parentInstance = newInstance.findMatchingParent( recursiveParent );
                if ( parentInstance.getEntityDef() == recursiveParent )
                {
                    // If we get here then the parent of targetInstance is the root of the
                    // subobject so we're just resetting it.
                    viewCursor.resetSubobjectTop();
                }
                else
                {
                    viewCursor.setRecursiveParent( parentInstance, recursiveParent, null );
                }
            }
        }
        else
            viewCursor.resetRecursiveParent();

        // Check to see if we need to set the parent cursors. Find the highest root cursor that
        // needs to be reset.
        EntityCursorImpl searchCursor = this;
        EntityInstanceImpl topEi = newInstance;
        for ( topEi = newInstance; topEi.getParent() != null; topEi = topEi.getParent() )
        {
            EntityCursorImpl searchParentCursor = searchCursor.getParentCursor();
            if ( searchParentCursor == null )
            {
                while ( topEi.getEntityDef().getErEntityToken() != searchCursor.getEntityDef().getErEntityToken() )
                    topEi = topEi.getParent();

                break;
            }

            // If the cursor isn't in scope then we won't bother setting it.
            if ( ! viewCursor.isCursorInScope( searchParentCursor ) )
                break;

            // Don't use getEntityInstance() because it will potentially try to set
            // the parent cursors if entityInstance is null.
            if ( searchParentCursor.entityInstance == topEi.getParent() )
                break;

            searchCursor = searchCursor.getParentCursor();
        }

        searchCursor.resetChildCursors( topEi );

        // If topEi is different from newInstance that means we've just reset a parent
        // cursor.  Now loop through again and set all cursors between searchCursor and 'this'.
        if ( topEi != newInstance )
        {
            cursorResult = CursorResult.SET_NEWPARENT;
            EntityCursorImpl tc = this;
            EntityInstanceImpl ei = newInstance;
            while ( tc != searchCursor )
            {
                if ( tc.getEntityDef() != ei.getEntityDef() && tc.getEntityDef() != ei.getEntityDef().getRecursiveParent() )
                    ei = ei.findMatchingParent( tc.getEntityDef() );

                tc.setEntityInstance( ei );
                tc = tc.getParentCursor();
                ei = ei.getParent();
            }
        }

        return cursorResult;
    }

    private EntityCursorImpl getOtherCursor( EntityDef entityDef )
    {
        if ( entityDef == null )
            return null;

        return getViewCursor().getEntityCursor( entityDef );
    }

    EntityCursorImpl getPrevHier()
    {
        return getOtherCursor( getEntityDef().getPrevHier() );
    }

    EntityCursorImpl getNextHierCursor()
    {
        return getOtherCursor( getEntityDef().getNextHier() );
    }

    @Override
    public EntityDef getEntityDef()
    {
        return entityDef;
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
                    CursorResult rc = setNext();
                    if ( rc == CursorResult.SET )
                        return rc;
                    else
                        return setLast();

                case LAST:
                    return setLast();

                case PREV:
                    CursorResult rc2 = setPrev();
                    if ( rc2 == CursorResult.SET )
                        return rc2;
                    else
                        return setFirst();

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
        getExistingInstance().excludeEntity( getView() );
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

        for ( EntityInstanceImpl ei = getEntityInstance().getFirstTwin();
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
        if ( getEntityDef().isDerived() )
            return;

        if ( ! getEntityDef().isPersistent() )
            return;

        // If the entity is a derived entity or a work entity (both marked as derived) then
        // we don't need to check if the entity is read only.
        if ( getEntityDef().isDerived() || getEntityDef().isDerivedPath() )
        	return;

        if ( getObjectInstance().isReadOnly() )
            throw new ZeidonException( "Object Instance is read-only" )
                                .prependEntityDef( getEntityDef() );
    }

    @Override
    public void includeSubobject(EntityInstance sourceEi) throws NullCursorException
    {
        includeSubobject( sourceEi, CursorPosition.NEXT );
    }

    @Override
    public void includeSubobject(EntityInstance sourceEi, CursorPosition position) throws NullCursorException
    {
        includeSubobject( sourceEi, position, IncludeFlags.EMPTY );
    }

    @Override
    public void includeSubobject(EntityInstance sourceEi, CursorPosition position, EnumSet<IncludeFlags> options ) throws NullCursorException
    {
        if ( ! options.contains( IncludeFlags.FROM_ACTIVATE ) )
        {
            // Include constraints take some work.  Since nobody appears to use them let's not
            // worry about implementing them for now.
            if ( entityDef.hasIncludeConstraint() )
                throw new UnsupportedOperationException( "Include constraints not supported yet." );

            validateMaxCardinality();
            validateOiUpdate();
        }

        EntityInstanceImpl source = (EntityInstanceImpl) sourceEi.getEntityInstance();
        EntityInstanceImpl parent = getParent();
        ObjectInstance oi = getObjectInstance();

        // Create a new instance and initialize the attributes.
        EntityInstanceImpl rootInstance =
                EntityInstanceIncluder.includeSubobject( getEntityInstance(), getEntityDef(), parent,
                                                         oi, source, position, true, options );

        if ( getEntityDef().getHashKeyAttributes() != null )
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
    public int getDepth() throws NullCursorException
    {
        return getExistingInstance().getDepth();
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
    public boolean isChildUpdated() throws NullCursorException
    {
        return getExistingInstance().isChildUpdated();
    }

    @Override
    public boolean isVersioned()
    {
        return getExistingInstance().isVersioned();
    }

    Iterable<AttributeDef> getNonNullAttributeList() throws NullCursorException
    {
        return getExistingInstance().getNonNullAttributeList();
    }

     /**
     * Gets the entity instance for the current cursor value matching scopingEntity.
     *
     * @param scopingEntity
     * @return
     */
    private EntityInstanceImpl getScopingEntityInstance( EntityDef scopingEntity )
    {
        // If the entity for this cursor has no parent then scoping isn't required.
        if ( getEntityDef().getParent() == null )
            return null;

        if ( scopingEntity == null )
            scopingEntity = getEntityDef().getParent();

        // If the scoping entity isn't the parent then we need to reset ancestor
        // cursors for everything under the scoping entity child.
        EntityDef searchEntity = getEntityDef().getParent();
        while ( searchEntity != null && searchEntity != scopingEntity )
            searchEntity = searchEntity.getParent();

        if ( searchEntity == null )
            throw new ZeidonException( "Invalid scoping entity: Entity %s is not an ancestor of %s",
                                        scopingEntity.getName(), this.getEntityDef().getName() );

        // Find the root EI for the scoping entity.
        EntityCursorImpl cursor = viewCursor.getEntityCursor( searchEntity );
        EntityInstanceImpl rootEi = cursor.getEntityInstance();
        return rootEi;
    }

    @Override
    public CursorResult setFirst()
    {
        // For performance reasons we won't create an iterator;
        // we'll just manipulate the cursor directly.
        currentIterator = null;
        forwardDirection = true;

        EntityInstanceImpl ei = getEntityInstance();
        if ( ei != null && ei.isDropped() )
        {
            this.resetChildCursors( null );
            ei = getEntityInstance();
        }

        if ( ei == null )
            return CursorResult.NULL;

        // Find the first twin.
        ei = ei.getFirstTwin();
        while ( ei != null && ei.isHidden() )
            ei = ei.getNextTwin();

        if ( ei == null || ei.isHidden() )
            return CursorResult.NULL;

        setCursor( ei );
        return CursorResult.SET;
    }

    @Override
    public CursorResult setFirst(String scopingEntityName)
    {
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setFirst();

        return setFirst( getLodDef().getEntityDef( scopingEntityName ) );
    }

    @Override
    public CursorResult setFirst(EntityDef scopingEntity)
    {
        if ( scopingEntity == null )
            return setFirst();

        if ( scopingEntity == getEntityDef().getParent() )
            return setFirst();

        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .withScoping( getScopingEntityInstance( scopingEntity ) )
                                    .forEntityDef( getEntityDef() )
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
        return setFirst( getEntityDef().getAttribute( attributeName ), value );
    }

    @Override
    public CursorResult setFirst(AttributeDef attribute, Object value)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .forEntityDef( getEntityDef() )
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

        EntityDef scopingEntity = getLodDef().getEntityDef( scopingEntityName );
        return setFirst( getEntityDef().getAttribute( attributeName ), value, scopingEntity );
    }

    @Override
    public CursorResult setFirst(AttributeDef attribute, Object value, EntityDef scopingEntity)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .withScoping( getScopingEntityInstance( scopingEntity ) )
                                    .forEntityDef( getEntityDef() )
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
    	{
    	    if ( forwardDirection)
    	        return setNext();
    	    else
    	        return setPrev();
    	}

        if ( ! currentIterator.hasNext() )
            return CursorResult.UNCHANGED;

        currentIterator.next();
        return CursorResult.SET;
    }

    @Override
    public CursorResult setNext()
    {
        // For performance reasons we won't create an iterator;
        // we'll just manipulate the cursor directly.
        currentIterator = null;

        EntityInstanceImpl ei = getEntityInstance();
        if ( ei == null )
            return CursorResult.NULL;

        ei = ei.getNextTwin();
        while ( ei != null && ei.isHidden() )
            ei = ei.getNextTwin();

        if ( ei == null || ei.isHidden() )
            return CursorResult.UNCHANGED;

        setCursor( ei );
        return CursorResult.SET;
    }

    @Override
    public CursorResult setNext(String scopingEntityName)
    {
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setNext();

        return setNext( getLodDef().getEntityDef( scopingEntityName ) );
    }

    @Override
    public CursorResult setNext(EntityDef scopingEntity)
    {
        if ( scopingEntity == null )
            return setNext();

        if ( scopingEntity == getEntityDef().getParent() )
            return setNext();

        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .withScoping( getScopingEntityInstance( scopingEntity ) )
                                    .forEntityDef( getEntityDef() )
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
        return setNext( getEntityDef().getAttribute( attributeName ), value );
    }

    @Override
    public CursorResult setNext(AttributeDef attribute, Object value)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
        						.forEntityDef( getEntityDef() )
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
            return setNext(getEntityDef().getAttribute( attributeName ), value);

        return setNext( getEntityDef().getAttribute( attributeName ), value, getLodDef().getEntityDef( scopingEntityName ) );
    }

    @Override
    public CursorResult setNext( AttributeDef attribute, Object value, EntityDef scopingEntity)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
					        .withScoping( getScopingEntityInstance( scopingEntity ) )
					        .forEntityDef( getEntityDef() )
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
        // For performance reasons we won't create an iterator;
        // we'll just manipulate the cursor directly.
        currentIterator = null;
        forwardDirection = false;

        EntityInstanceImpl ei = getEntityInstance();
        if ( ei != null && ei.isDropped() )
        {
            this.resetChildCursors( null );
            ei = getEntityInstance();
            // KJS 10/07/15 - When I delete the last entity with cursorposition.NEXT, we get here
            // and ei is null. We will return then, otherwise getLastTwin gives null exception.
            if ( ei == null )
            	return CursorResult.NULL;
        }

        if ( ei == null )
            return CursorResult.NULL;

        ei = ei.getLastTwin();
        while ( ei != null && ei.isHidden() )
            ei = ei.getPrevTwin();

        if ( ei == null || ei.isHidden() )
            return CursorResult.NULL;

        setCursor( ei );
        return CursorResult.SET;
    }

    @Override
    public CursorResult setPrevContinue()
    {
        if ( currentIterator == null )
        {
            if ( forwardDirection)
                return setNext();
            else
                return setPrev();
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
                                                .forEntityDef( getEntityDef() )
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
        if ( position < 0 )
            return CursorResult.NULL;

        EntityInstanceImpl ei = getEntityInstance();
        if ( ei == null )
            return CursorResult.NULL;

        ei = ei.getFirstTwin();
        while ( true )
        {
            while ( ei != null && ei.isHidden() )
                ei = ei.getNextTwin();

            if ( ei == null )
                return CursorResult.NULL;

            if ( position == 0 )
            {
                setCursor( ei );
                return CursorResult.SET;
            }

            ei = ei.getNextTwin();
            position--;
        }
    }

    @Override
    public CursorResult setPosition(int position, String scopingEntityName )
    {
        if ( position < 0 )
            return CursorResult.NULL;

        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setPosition( position );

        EntityDef scopingEntity = getLodDef().getEntityDef( scopingEntityName );
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                                            .withScoping( getScopingEntityInstance( scopingEntity ) )
                                            .forEntityDef( getEntityDef() )
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
            EntityInstanceImpl ei = getExistingInstance().acceptSubobject( getView() );
            setEntityInstance( ei );

            // The child cursors still point to the old version.  Reset them all to point
            // to the new version so that the GC doesn't hold onto the old ones.
            for ( EntityCursorImpl child : getChildCursors() )
            {
                // If the child cursor is pointing to something, reset it.
                if ( child.entityInstance != null )
                    child.getEntityInstance();
            }

            assert validateChains() : "Something is wrong with the chain pointers";
            return ei;
        }
        catch ( Throwable e )
        {
            throw ZeidonException.wrapException( e ).prependEntityDef( getEntityDef() );
        }
    }

    @Override
    public EntityInstance cancelSubobject()
    {
        EntityInstanceImpl ei = getExistingInstance().cancelSubobject( getView() );
        setEntityInstance( ei );

        // The child cursors may still point to the new version.  Reset them all to point
        // to the old version so that the GC doesn't hold onto the new ones.
        for ( EntityCursorImpl child : getChildCursors() )
        {
            // If the child cursor is pointing to something, reset it.zr\]
        	//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        	// child is TestTRCode... we need to set it's .nextHier().setPrevHier() = ei.setPrevHier() // which is US_CollegeTerm or we 
        	// need to set it to ei (because now we are on the previous subobject version???
            if ( child.entityInstance != null )
                child.getEntityInstance();
        }

        assert validateChains() : "Something is wrong with the chain pointers";
        return ei;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#acceptTemporalEntity()
     */
    @Override
    public void acceptTemporalEntity()
    {
        getExistingInstance().acceptTemporalEntity( getView() );
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
        if ( ! viewCursor.isCursorInScope( this ) )
            throw new OutOfScopeException( this );

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
                private final int startLevel = cursor.getEntityDef().getDepth();

                @Override
                public boolean hasNext()
                {
                    EntityCursorImpl next = current.getNextHierCursor();
                    if ( next == null )
                        return false;

                    if ( next.getEntityDef().getDepth() <= startLevel )
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
        AttributeDef attributeDef;
        boolean       ascending;
        String        context;

        SortKey(AttributeDef AttributeDef, boolean ascending, String context)
        {
            this.attributeDef = AttributeDef;
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

        private EntityInstanceImpl findMatchingChild( EntityInstanceImpl parent, EntityDef sortEntity )
        {
            // We need to find the child entity that matches sortEntity.
            for ( EntityInstanceImpl child : parent.getChildrenHier() )
            {
                if ( child.getEntityDef() == sortEntity )
                    return child;
            }

            return null;
        }

        @Override
        public int compare(EntityInstanceImpl ei1, EntityInstanceImpl ei2 )
        {
            assert ei1.getEntityDef() == ei2.getEntityDef();

            if ( ei1.isHidden() )
            {
                if ( ei2.isHidden() )
                    return 0;  // Two hidden EIs are considered equal.
                else
                    return 1;  // A hidden EI is considered greater than non-hidden to put it at the end.
            }
            else
            if ( ei2.isHidden() )
                return -1;

            for ( SortKey key : sortAttribs )
            {
                // Since we might be performing the compare on a child entity instance,
                // create "compare" EIs.
                EntityInstanceImpl cei1 = ei1;
                EntityInstanceImpl cei2 = ei2;

                EntityDef sortEntity = key.attributeDef.getEntityDef();
                if ( cei1.getEntityDef() != sortEntity )
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
                	// Use context for comparing.
                	if ( cei1.getAttribute(key.attributeDef ).isNull() || cei2.getAttribute(key.attributeDef ).isNull() )
                	{
                		if ( !cei1.getAttribute(key.attributeDef ).isNull() )
                			return 1;
                		if ( !cei2.getAttribute(key.attributeDef ).isNull() )
                			return -1;
                		return 0; // Both are null.
                	}
                	String value1 = cei1.getAttribute(key.attributeDef ).getString( key.context );
                	String value2 = cei2.getAttribute(key.attributeDef ).getString( key.context );

                	cmp = value1.compareTo(value2);
                }
                else
                {
                	cmp = cei1.getAttribute( key.attributeDef ).compare( cei2.getAttribute( key.attributeDef ).getValue() );
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
            EntityDef sortEntity = getEntityDef();
            if ( name.contains( "." ) )
            {
                String[] s = name.split( "\\." );
                if ( s.length != 2 )
                    throw new ZeidonException( "Ill-formed order keys.  Entity.attrib name expected.  Got '%s'", name );

                sortEntity = getLodDef().getEntityDef( s[0] );
                name = s[1];
            }

            AttributeDef sortAttrib = sortEntity.getAttribute( name );

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
        List<SortKey> sortAttribs = parseOrderKeys( orderKeys );
        EntitySorter comparator = new EntitySorter( sortAttribs );
        orderEntities( comparator );
    }

    @SuppressWarnings("unchecked") // For Collections.sort(...)
    @Override
    public void orderEntities( Comparator<? extends EntityInstance> comparator )
    {
         // If there is an autoseq attribute then ordering for this entity matters, so
         // validate that it can be updated.
         boolean isAutoseq = getEntityDef().getAutoSeq() != null;
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
         Collections.sort( entities, (Comparator<? super EntityInstance>) comparator );

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
        if ( ! viewCursor.isCursorInScope( this ) )
            return CursorResult.UNDEFINED;

        // Check to see if the entity is null.
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

        return CursorResult.UNDEFINED;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        // Don't use getEntityInstance() or getNull() because that will attempt to
        // establish the cursor.  We don't want toString() changing anything.
        if ( entityInstance == null )
            builder.append( getEntityDef() ).append( ": NULL" );
        else
            builder.append( entityInstance.toString() );

        return builder.toString();
    }

    @Override
    public void setToSubobject()
    {
        if ( entityDef.getParent() == null )
            throw new ZeidonException("Entity %s is the root of the LodDef", getEntityDef() );

        if ( ! entityDef.isRecursive() )
            throw new ZeidonException("Entity %s is not recursive", entityDef );

        EntityInstanceImpl ei = getEntityInstance();
        EntityInstanceImpl parentOfSubobject = getParentCursor().getExistingInstance();

        EntityDef recursiveParentEntityDef = getEntityDef().getRecursiveParent();
        viewCursor.setRecursiveParent( ei, getEntityDef(), parentOfSubobject );
        viewCursor.getEntityCursor( recursiveParentEntityDef ).resetChildCursors( ei );
    }

    @Override
    public boolean resetSubobjectToParent()
    {
        return viewCursor.resetSubobjectToParent();
    }

    EntityCursorImpl getParentCursor()
    {
        if ( getEntityDef().getParent() == null )
            return null;

        return viewCursor.getEntityCursor( getEntityDef().getParent() );
    }

    @Override
    public int getEntityCount() throws NullCursorException
    {
        EntityInstanceImpl ei = getEntityInstance(); // Establishes the cursor if necessary.
        if ( ei == null )
            return 0;

        return ei.getTwinCount(false);
    }

    @Override
    public int getEntityCount( boolean includeHidden ) throws NullCursorException
    {
        EntityInstanceImpl ei = getEntityInstance(); // Establishes the cursor if necessary.
        if ( ei == null )
            return 0;

        return ei.getTwinCount( includeHidden );
    }

    @Override
    public EntityIterator<? extends EntityInstance> getChildren( EntityDef childEntityDef)
    {
        return getChildren( childEntityDef, false );
    }


    @Override
    public EntityIterator<? extends EntityInstance> getChildren( EntityDef childEntityDef,
                                                                 boolean allowHidden )
    {
        return new IteratorBuilder(getObjectInstance())
                        .allowHidden( allowHidden )
                        .withScoping( getExistingInstance() )
                        .forEntityDef( childEntityDef )
                        .build();
    }

    @Override
    public EntityIterator<? extends EntityInstance> getChildren(String childEntityName)
    {
        return getChildren( getLodDef().getEntityDef( childEntityName ) );
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
        return getChildrenHier( includeParent, true, true );
    }

    @Override
    public EntityIterator<EntityInstanceImpl> getChildrenHier( boolean includeParent,
                                                               boolean excludeHidden,
                                                               boolean forceLazyLoad )
    {
        // We don't call getExistingEntity().getChildrenHier(...) because we want to set the
        // view so that the iterator changes the cursor.
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                                                            .withScoping( getEntityInstance() )
                                                            .allowHidden( ! excludeHidden )
                                                            .setLazyLoad( forceLazyLoad )
                                                            .setView( getView() )
                                                            .includeHierParent( includeParent )
                                                            .build();
        if ( ! includeParent && iter.hasNext() )
            iter.next();  // Skip past the parent.

        return iter;
    }

    @Override
    public CopyAttributesBuilder copyAttributes()
    {
        return getExistingInstance().copyAttributes();
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
    public boolean isDbhUpdateForeignKeysOnly()
    {
        return getExistingInstance().isDbhUpdateForeignKeysOnly();
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
    public ViewImpl getView()
    {
        return viewCursor.getView();
    }

    @Override
    public EntityIterator<EntityInstanceImpl> eachEntity()
    {
        return new IteratorBuilder(getObjectInstance())
                        .setCursor(EntityCursorImpl.this)
                        .forTwinsOf( getEntityInstance() )
                        .forEntityDef( getEntityDef() )
                        .build();
    }

    @Override
    public EntityIterator<? extends EntityInstance> eachEntity( String scopingEntityName )
    {
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return eachEntity();

        return eachEntity( getLodDef().getEntityDef( scopingEntityName ) );
    }

    @Override
    public EntityIterator<? extends EntityInstance> eachEntity( EntityDef scopingEntity )
    {
        EntityInstanceImpl scopingEi = getScopingEntityInstance( scopingEntity );
        return new IteratorBuilder(getObjectInstance())
                        .withScoping( scopingEi )
                        .forEntityDef( getEntityDef() )
                        .setCursor(EntityCursorImpl.this)
                        .build();
    }

	@Override
	public CursorResult setLast(String scopingEntityName)
	{
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setLast();

        return setLast( getLodDef().getEntityDef( scopingEntityName ) );
	}

	@Override
	public CursorResult setLast(EntityDef scopingEntity)
	{
        if ( scopingEntity == null )
            return setLast();

        if ( scopingEntity == getEntityDef().getParent() )
            return setLast();

        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .withScoping( getScopingEntityInstance( scopingEntity ) )
                                    .forEntityDef( getEntityDef() )
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
	    return setLast( getEntityDef().getAttribute( attributeName ), value );
	}

	@Override
	public CursorResult setLast(String attributeName, Object value, String scopingEntityName)
	{
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setLast( attributeName, value );

        return setLast( getEntityDef().getAttribute( attributeName ), value, getLodDef().getEntityDef( scopingEntityName ) );
	}

	@Override
	public CursorResult setLast(AttributeDef attributeDef, Object value)
	{
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .forTwinsOf( getEntityInstance() )
                                    .forEntityDef( getEntityDef() )
                                    .setCursor( this )
                                    .withAttributeValue( attributeDef, value )
                                    .setLast()
                                    .build();
        if ( ! currentIterator.hasPrev() ) // Is there a last entity?
            return CursorResult.NULL;

        currentIterator.prev();
        return CursorResult.SET;
	}

	@Override
    public CursorResult setLast( AttributeDef attribute, Object value, EntityDef scopingEntity )
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException( "Not written yet" );
    }

	@Override
    public CursorResult setPrev()
    {
        // For performance reasons we won't create an iterator;
        // we'll just manipulate the cursor directly.
        currentIterator = null;

        EntityInstanceImpl ei = getEntityInstance();
        if ( ei == null )
            return CursorResult.NULL;

        ei = ei.getPrevTwin();
        while ( ei != null && ei.isHidden() )
            ei = ei.getPrevTwin();

        if ( ei == null || ei.isHidden() )
            return CursorResult.UNCHANGED;

        setCursor( ei );
        return CursorResult.SET;
    }


    @Override
    public CursorResult setPrev(String scopingEntityName)
    {
        // We'll assume that a blank/null scoping entity means no scoping.
        if ( StringUtils.isBlank( scopingEntityName ) )
            return setPrev();

        return setPrev( getLodDef().getEntityDef( scopingEntityName ) );
    }

    @Override
    public CursorResult setPrev(EntityDef scopingEntity)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .withScoping( getScopingEntityInstance( scopingEntity ) )
                                    .forEntityDef( getEntityDef() )
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
            return setPrev(getEntityDef().getAttribute( attributeName ), value);

        return setPrev( getEntityDef().getAttribute( attributeName ), value, getLodDef().getEntityDef( scopingEntityName ) );
    }

    @Override
    public CursorResult setPrev( AttributeDef attribute, Object value, EntityDef scopingEntity)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
					        .withScoping( getScopingEntityInstance( scopingEntity ) )
					        .forEntityDef( getEntityDef() )
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
        return setPrev( getEntityDef().getAttribute( attributeName ), value );
    }

    @Override
    public CursorResult setPrev( AttributeDef attribute, Object value )
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                .forEntityDef( getEntityDef() )
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

    private CursorResult moveTwin(EntityInstanceImpl target, CursorPosition position, EntityInstanceImpl source)
    {
        validateOiUpdate();
        EntityInstanceImpl lastHierChild = source.removeEntityFromChains();
        source.insertInstance( getObjectInstance(), target.getParent(), target, position, lastHierChild );

        // Set OI incremental flags if the entities are sequenced.
        if ( getEntityDef().getAutoSeq() != null )
        {
            target.setUpdated( true, false, true );
            source.setUpdated( true, false, true );
        }

        assert validateChains() : "Something is wrong with the chain pointers";
        return CursorResult.SET;
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
        return moveSubobject(position, (EntityInstance) source, sourceReposition );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#moveSubobject(int, com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public CursorResult moveSubobject(CursorPosition position, EntityInstance source)
    {
        return moveSubobject(position, source, CursorPosition.NEXT );
    }

    private void verifySubobjectMove( EntityInstance source )
    {
        // Make sure source is not a parent of target.  If it is we're attempting to
        // move a parent under it's child.
        EntityInstanceImpl target = getEntityInstance();
        if ( target == null )
            target = getParentCursor().getEntityInstance();

        if ( source.getEntityInstance() == null )
            throw new ZeidonException( "Source EntityCursor is null" );

        if ( target.isChildOf( source ) )
            throw new ZeidonException( "Attempting to move an entity instance under one of its children" )
                            .appendMessage( "Target Entity = %s", getEntityDef().getName() )
                            .appendMessage( "Child entity = %s", source.getEntityDef().getName() );

        EntityDef tgtEntityDef = getEntityDef();
        EntityDef srcEntityDef = source.getEntityDef();

        if ( ! tgtEntityDef.isInclude() )
            throw new ZeidonException( "Target of moveSubobject be be includable" )
                            .appendMessage( "Target = %s", tgtEntityDef.getName() );

        if ( ! srcEntityDef.isExclude() )
            throw new ZeidonException( "Source of moveSubobject be be excludable" )
                            .appendMessage( "Source = %s", srcEntityDef.getName() );

        // Verify that the EntityDefs are the same for source and target -or- one is a recursive
        // child of the other.
        // The COE requires them to be the same EntityDef but is that really necessary?
        if ( tgtEntityDef == srcEntityDef )
            return;

        if ( srcEntityDef.getLodDef() != tgtEntityDef.getLodDef() )
            throw new ZeidonException( "When moving subobjects, source and target OIs must be using the same LOD" );

        if ( tgtEntityDef.isRecursiveParent() && srcEntityDef.isRecursive() &&
             tgtEntityDef.isAncestorOf( srcEntityDef ) )
        {
            return;
        }

        if ( srcEntityDef.isRecursiveParent() && tgtEntityDef.isRecursive() &&
             srcEntityDef.isAncestorOf( tgtEntityDef ) )
        {
            return;
        }

        // If we get here then user has specified incorrect EntityDefs.
        throw new ZeidonException( "When moving subobjects, source and target must be the same entity type " +
                                   "or one must be a recursive parent of the other" );
    }

    private CursorResult moveSubobject(CursorPosition position, EntityInstance source, CursorPosition sourceReposition)
    {
        validateOiUpdate();

        EntityInstanceImpl target = getEntityInstance();

        // If source and target are the same then there's nothing to do.  Call getEntityInstance() on
        // source because it may be a EntityCursor.
        if ( target == source.getEntityInstance() )
            return CursorResult.UNCHANGED;

        // If they have the same parent then we're moving twins.  Call a different method.
        // If this changes then we need to verify max cardinality.
        if ( target != null && target.getParent() == source.getParent() )
            return moveTwin( target, position, (EntityInstanceImpl) source.getEntityInstance() );

        // Verify that the subobject can be moved between different parents.
        verifySubobjectMove( source );

        // If we get here then everything should be good.
        includeSubobject( source, position );
        if ( source instanceof EntityCursor )
            return ((EntityCursor) source).excludeEntity( sourceReposition );

        source.excludeEntity();
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
                                    .forEntityDef( getEntityDef() )
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
                                    .forEntityDef( getEntityDef() )
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
        if ( ! viewCursor.isCursorInScope( this ) )
            throw new OutOfScopeException( this );

        EntityInstanceImpl ei = getEntityInstance();
        if ( ei == null )
            return false;

        // See if there is a next, nonhidden twin.
        for ( EntityInstanceImpl search = ei.getNextTwin(); search != null; search = search.getNextTwin() )
        {
            if ( !search.isHidden() )
                return true;
        }

        // If we get here then we didn't find any non-hidden EIs.
        return false;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasNext()
     */
    @Override
    public boolean hasPrev()
    {
        if ( ! viewCursor.isCursorInScope( this ) )
            throw new OutOfScopeException( this );

        EntityInstanceImpl ei = getEntityInstance();
        if ( ei == null )
            return false;

        // Look for a prev non-hidden twin.
        for ( EntityInstanceImpl search = ei.getPrevTwin(); search != null; search = search.getPrevTwin() )
        {
            if ( ! search.isHidden() )
                return true;
        }

        // If we get here then we didn't find any non-hidden EIs.
        return false;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAny()
     */
    @Override
    public boolean hasAny()
    {
        if ( ! viewCursor.isCursorInScope( this ) )
            throw new OutOfScopeException( this );

        EntityInstanceImpl ei = getEntityInstance();
        if ( ei == null )
            return false;

        if ( ! ei.isHidden() )
            return true;

        // If we get here then ei is hidden. See if there are any non-hidden EIs
        // either before or after this ei.
        for ( EntityInstanceImpl search = ei.getPrevTwin(); search != null; search = search.getPrevTwin() )
        {
            if ( ! search.isHidden() )
                return true;
        }

        for ( EntityInstanceImpl search = ei.getNextTwin(); search != null; search = search.getNextTwin() )
        {
            if ( !search.isHidden() )
                return true;
        }

        // If we get here then we didn't find any non-hidden EIs.
        return false;
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

        return hasAny( getLodDef().getEntityDef( scopingEntityName ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAny(com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public boolean hasAny(EntityDef scopingEntityDef)
    {
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                                            .withScoping( getScopingEntityInstance( scopingEntityDef ) )
                                            .forEntityDef( getEntityDef() )
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
                                            .forEntityDef( getEntityDef() )
                                            .build();
        return iter.hasNext();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setFirstWithinOi(java.lang.String, java.lang.Object)
     */
    @Override
    public CursorResult setFirstWithinOi( AttributeDef attributeDef, Object value)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .setCursor( this )
                                    .withOiScoping( getObjectInstance() )
                                    .forEntityDef( getEntityDef() )
                                    .withAttributeValue( attributeDef, value )
                                    .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.NULL;

        currentIterator.next();
        return CursorResult.SET;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setNextWithinOi(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public CursorResult setNextWithinOi(AttributeDef attributeDef, Object value)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .setCursor( this )
                                    .withOiScoping( getObjectInstance() )
                                    .currentInstance( getEntityInstance() )
                                    .forEntityDef( getEntityDef() )
                                    .withAttributeValue( attributeDef, value )
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
        return hasAny( getEntityDef().getAttribute( attributeName ), value );
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

        return hasAny( getEntityDef().getAttribute( attributeName ), value, getLodDef().getEntityDef( scopingEntityName ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAnyWithinOi(java.lang.String, java.lang.Object)
     */
    @Override
    public boolean hasAnyWithinOi(String attributeName, Object value)
    {
        return hasAnyWithinOi( getEntityDef().getAttribute( attributeName ), value );
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
                                    .forEntityDef( getEntityDef() )
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
        return setLastWithinOi( getEntityDef().getAttribute( attributeName ), value );
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
                                    .forEntityDef( getEntityDef() )
                                    .setLast()
                                    .build();

        if ( ! currentIterator.hasPrev() )
            return CursorResult.UNCHANGED;

        currentIterator.prev();
        return CursorResult.SET;
    }

    private boolean assertParentCursors()
    {
        if ( getParentCursor() == null )
            return true;

        // Break this out with a 'if' statement to make it easier to set a breakpoint.
        if ( getParentCursor().getEntityInstance() == getEntityInstance().getParent() )
            return true;
        else
            return false;
    }
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setPrevWithinOi(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public CursorResult setPrevWithinOi(AttributeDef attributeDef, Object value)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                    .setCursor( this )
                                    .withOiScoping( getObjectInstance() )
                                    .currentInstance( getEntityInstance() )
                                    .forEntityDef( getEntityDef() )
                                    .withAttributeValue( attributeDef, value )
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
        return setFirstWithinOi( getEntityDef().getAttribute( attributeName ), value );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setNextWithinOi(java.lang.String, java.lang.Object)
     */
    @Override
    public CursorResult setNextWithinOi(String attributeName, Object value)
    {
        return setNextWithinOi( getEntityDef().getAttribute( attributeName ), value );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#setLastWithinOi(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public CursorResult setLastWithinOi(AttributeDef attributeDef, Object value)
    {
        currentIterator = new IteratorBuilder(getObjectInstance())
                                .setCursor( this )
                                .withOiScoping( getObjectInstance() )
                                .forEntityDef( getEntityDef() )
                                .withAttributeValue( attributeDef, value )
                                .setLast()
                                .build();
        if ( ! currentIterator.hasNext() )
            return CursorResult.NULL;

        currentIterator.next();
        return CursorResult.SET;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAny(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public boolean hasAny(AttributeDef attributeDef, Object value)
    {
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                            .forEntityDef( getEntityDef() )
                            .withAttributeValue( attributeDef, value )
                            .build();
        return iter.hasNext();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAny(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object, com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public boolean hasAny(AttributeDef attributeDef, Object value, EntityDef scopingEntityDef)
    {
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                            .forEntityDef( getEntityDef() )
                            .withScoping( getScopingEntityInstance( scopingEntityDef ) )
                            .withAttributeValue( attributeDef, value )
                            .build();
        return iter.hasNext();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityCursor#hasAnyWithinOi(com.quinsoft.zeidon.objectdefinition.AttributeDef, java.lang.Object)
     */
    @Override
    public boolean hasAnyWithinOi(AttributeDef attributeDef, Object value)
    {
        EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                            .withOiScoping( getObjectInstance() )
                            .forEntityDef( getEntityDef() )
                            .withAttributeValue( attributeDef, value )
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
            LazyLoadConfig config = getEntityDef().getLazyLoadConfig();
            if ( config.isLazyLoad() )
            {
                assert getParentCursor() != null : "Root cannot be lazy load candidate";

                EntityInstanceImpl parent = getParentCursor().getEntityInstance( false );
                if ( parent == null )
                    // TODO: What if there are multiple levels of LazyLoad?  It's possible that
                    // the parent entity wasn't loaded because it is lazyload.
                    return CursorStatus.NULL;

                if ( parent.hasChildBeenLazyLoaded( getEntityDef() ) )
                    return CursorStatus.NULL;

                return CursorStatus.NOT_LOADED;
            }
            else
            if ( config.hasLazyLoadParent() )
            {
                assert getParentCursor() != null : "Root cannot be lazy load candidate";

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

    protected ViewCursor getViewCursor()
    {
        return viewCursor;
    }

    @Override
    public AttributeInstance getAttribute( String attributeName )
    {
        AttributeDef attributeDef = getEntityDef().getAttribute( attributeName );
        if ( attributeDef.isHidden() )
            throw new HiddenAttributeException( attributeDef );

        return getAttribute( attributeDef );
    }

    @Override
    public AttributeInstance getAttribute( AttributeDef attributeDef )
    {
        return getExistingInstance().getAttribute( getView(), attributeDef );
    }

    @Override
    public AttributeInstance createDynamicAttributeDef( DynamicAttributeDefConfiguration config )
    {
        return getExistingInstance().createDynamicAttributeDef( config );
    }

    @Override
    public EntityIterator<? extends EntityInstance> getDirectChildren()
    {
        return getExistingInstance().getDirectChildren();
    }

    @Override
    public EntityIterator<? extends EntityInstance> getDirectChildren( boolean allowHidden )
    {
        return getExistingInstance().getDirectChildren( allowHidden );
    }

    @Override
    public EntityIterator<? extends EntityInstance> getDirectChildren( boolean allowHidden, boolean allowLazyLoad )
    {
        return getExistingInstance().getDirectChildren( allowHidden, allowLazyLoad );
    }

    @Override
    public boolean hasNextTwin()
    {
        return getExistingInstance().hasNextTwin();
    }

    @Override
    public boolean hasPrevTwin()
    {
        return getExistingInstance().hasPrevTwin();
    }

    @Override
    public EntityIterator<? extends EntityInstance> allEntities()
    {
        return new IteratorBuilder( getObjectInstance() )
                                    .setCursor( this )
                                    .withOiScoping( getObjectInstance() )
                                    .forEntityDef( getEntityDef() )
                                    .build();
    }

    @Override
    public List<AttributeInstance> getAttributes()
    {
        return getExistingInstance( true ).getAttributes();
    }

    @Override
    public List<AttributeInstance> getAttributes( boolean includeNullValues )
    {
        return getExistingInstance( true ).getAttributes( includeNullValues );
    }

    @Override
    public void copyAttributes( CopyAttributesBuilder flags )
    {
        getExistingInstance( true ).copyAttributes( flags );
    }

    @Override
    public boolean isIncomplete()
    {
        return getExistingInstance().isIncomplete();
    }

    @Override
    public boolean compareEntity( EntityInstance sourceInstance )
    {
        return compareEntity( sourceInstance, CompareEntityOptions.DEFAULT_OPTIONS );
    }

    @Override
    public boolean compareEntity( EntityInstance sourceInstance, CompareEntityOptions options )
    {
        return getExistingInstance().compareEntity( sourceInstance, options );
    }
}
