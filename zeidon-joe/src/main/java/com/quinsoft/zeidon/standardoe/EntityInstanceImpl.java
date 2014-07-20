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

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;

import com.google.common.collect.MapMaker;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.EntityIterator;
import com.quinsoft.zeidon.EventNotification;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.MaxCardinalityException;
import com.quinsoft.zeidon.RequiredAttributeException;
import com.quinsoft.zeidon.RequiredEntityMissingException;
import com.quinsoft.zeidon.SetMatchingFlags;
import com.quinsoft.zeidon.SubobjectValidationException;
import com.quinsoft.zeidon.TemporalEntityException;
import com.quinsoft.zeidon.UnknownViewAttributeException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeHashKeyType;
import com.quinsoft.zeidon.objectdefinition.InternalType;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
import com.quinsoft.zeidon.objectdefinition.ViewOd;
import com.quinsoft.zeidon.utils.JoeUtils;

/**
 * @author DG
 *
 */
class EntityInstanceImpl implements EntityInstance
{
    // These are for compatibility with the old C OE.
    private static final int  FLAG_HANGING_ENTITY = 0x00000001;
    private static final int  FLAG_CREATED        = 0x00000002;
    private static final int  FLAG_UPDATED        = 0x00000004;
    private static final int  FLAG_DELETED        = 0x00000008;
    private static final int  FLAG_EXCLUDED       = 0x00000010;
    private static final int  FLAG_INCLUDED       = 0x00000020;
    private static final int  FLAG_HIDDEN         = 0x00000400;

    private final ObjectInstance  objectInstance;
    private final ViewEntity      viewEntity;
    private final int             level;
    private final long            entityKey;

    private UUID                  uuid;
    private EntityInstanceImpl    parentInstance;
    private EntityInstanceImpl    prevHierInstance;
    private EntityInstanceImpl    nextHierInstance;
    private EntityInstanceImpl    prevTwinInstance;
    private EntityInstanceImpl    nextTwinInstance;
    private AttributeListInstance attributeList;
    private boolean updated  = false;
    private boolean deleted  = false;
    private boolean created  = false;
    private boolean included = false;
    private boolean hidden   = false;
    private boolean excluded = false;
    private boolean dropped  = false;

    /**
     * This keeps track of attribute hash keys that are under this EI.  Intended for use
     * by cursor.setFirst() processing.
     */
    private final AttributeHashKeyMap attributeHashkeyMap;

    /**
     * Temporal subobject information.
     */
    private enum VersionStatus
    {
        /**
         * Entity is not versioned (may be result of accepted entity)
         */
        NONE,

        /**
         * Entity is part of a versioned subobject and has not been accepted/canceled yet.
         */
        UNACCEPTED,

        /**
         * Entity is root of versioned subobject and has not been accepted/canceled yet.
         */
        UNACCEPTED_ROOT,

        /**
         * Entity created by createTemporalEntity().
         */
        UNACCEPTED_ENTITY,

        /**
         * This was a temporal entity but it was canceled.
         */
        CANCELED,

        /**
         * This entity has been superseded by a newer temporal entity that was accepted.
         */
        SUPERSEDED;
    }

    private EntityInstanceImpl prevVersion;
    private EntityInstanceImpl nextVersion;
    private VersionStatus      versionStatus;
    private long               versionNumber;

    //
    // "Meta" information.
    private String tag;  // Used for merging OIs across servers.

    // Information used by commit methods.
    private long    hierIndex;
    private boolean isWritten;
    private boolean isRecordOwner;

    // Following flags used during commit to DB.
    boolean dbhCreated;
    boolean dbhDeleted;
    boolean dbhExcluded;
    boolean dbhUpdated;
    boolean dbhIncluded;
    boolean dbhNeedsInclude; // True if linked entity was created.
    boolean dbhSeqUpdated;
    boolean dbhGenKeyNeeded;
    boolean dbhNoGenKey;
    boolean dbhForeignKey;

    /**
     * True if this entity has something that needs to be committed.
     */
    boolean dbhNeedsCommit;

    /**
     * AttributeInstances cannot be be shared by multiple entities because the AttributeInstance
     * must be able to refer to its entity instance.
     */
    private Map<ViewAttribute, AttributeInstanceImpl> attributeInstanceMap;

    /**
     * This keeps track of child entity instances that have been loaded lazily.
     */
    private Set<ViewEntity> entitiesLoadedLazily;

    static EntityInstanceImpl createEntity( ObjectInstance     oi,
                                            EntityInstanceImpl parent,
                                            EntityInstanceImpl relativeEntity,
                                            ViewEntity         viewEntity,
                                            CursorPosition     position )
    {
        // Create a new instance and initialize the attributes.
        EntityInstanceImpl newInstance =
            new EntityInstanceImpl( oi, viewEntity, parent, relativeEntity, position );
        newInstance.setCreated( true );
        newInstance.attributeList = AttributeListInstance.newAttributeList( newInstance );
        return newInstance;
    }

    /**
     * Creates an empty entity instance.  This gives us a way to create uninitialized
     * entity instances used for temporary traversal.
     */
    EntityInstanceImpl( ViewEntity viewEntity )
    {
        super();
        this.viewEntity = viewEntity;
        objectInstance = null;
        entityKey = NumberUtils.LONG_ZERO;
        level = -1;
        this.attributeHashkeyMap = null;
    }

    /**
     * Creates an entity instance and adds it to the OI.
     *
     * @param objectInstance
     * @param viewEntity
     * @param parentInstance
     * @param relativeInstance
     * @param position
     */
    EntityInstanceImpl(ObjectInstance        objectInstance,
                       ViewEntity            viewEntity,
                       EntityInstanceImpl    parentInstance,
                       EntityInstanceImpl    relativeInstance,
                       CursorPosition        position )
    {
        this( objectInstance, viewEntity, parentInstance );
        insertInstance( objectInstance, parentInstance, relativeInstance, position, null );
    }

    /**
     * Creates an entity instance without inserting it into the chain.
     *
     * @param objectInstance
     * @param viewEntity
     * @param parentInstance
     * @param initAttributes
     */
    EntityInstanceImpl(ObjectInstance        objectInstance,
                       ViewEntity            viewEntity,
                       EntityInstanceImpl    parentInstance )
    {
        this.objectInstance = objectInstance;
        this.viewEntity = viewEntity;
        this.attributeHashkeyMap = new AttributeHashKeyMap( objectInstance );

        // Set a unique identifier for this entity.  We use a number that's unique across
        // all tasks in case the entity is included into another task.
        this.entityKey = getTask().getObjectEngine().getNextObjectKey();

        // Copy some values from parentInstance if it isn't null.
        if ( parentInstance != null )
        {
            this.setParent( parentInstance );
            this.level = parentInstance.getLevel() + 1;
            if ( parentInstance.isVersioned() )
                setVersionStatus( VersionStatus.UNACCEPTED );
            else
            {
                // If we get here then parent instance isn't versioned so status should always be none.
                assert parentInstance.versionStatus == VersionStatus.NONE;
                setVersionStatus( VersionStatus.NONE );
            }
            this.versionNumber = parentInstance.versionNumber;
        }
        else
        {
            setVersionStatus( VersionStatus.NONE );
            this.versionNumber = 0;
            this.level = 1;
        }
    }

    void initializeDefaultAttributes()
    {
        if ( getViewEntity().hasInitializedAttributes() )
        {
            for ( ViewAttribute viewAttribute : getViewEntity().getAttributes() )
            {
                if ( StringUtils.isBlank( viewAttribute.getInitialValue() ) )
                    continue;

                setAttribute( viewAttribute, viewAttribute.getInitialValue() );
            }
        }
    }

    public ObjectInstance getObjectInstance()
    {
        return objectInstance;
    }

    @Override
    public ViewEntity getViewEntity()
    {
        return viewEntity;
    }

    /**
     * If this ViewEntity is recursive then this returns the recursive parent,
     * otherwise returns getViewEntity().
     *
     * @return
     */

    ViewEntity getBaseViewEntity()
    {
        return viewEntity.getBaseViewEntity();
    }

    EntityInstanceImpl getLatestVersion()
    {
        return getLatestVersion( this );
    }


    private EntityInstanceImpl getLatestVersion( EntityInstanceImpl ei )
    {
        if ( ei == null )
            return null;

        // Skip canceled entities UNLESS there is no prevVersion.  When prevVersion is null
        // then this entity was created by createTemporalEntity and we will consider this
        // the latest version.  This is necessary for repositioning after canceling the
        // temporal create.
        while ( ei.versionStatus == VersionStatus.CANCELED && ei.prevVersion != null )
            ei = ei.prevVersion;

        while ( ei.nextVersion != null )
        {
            assert ei.versionNumber < ei.nextVersion.versionNumber;
            ei = ei.nextVersion;
        }

        return ei;
    }

    @Override
    public EntityInstanceImpl getParent()
    {
        return getLatestVersion( parentInstance );
    }

    EntityInstanceImpl getPrevVersion()
    {
        return prevVersion;
    }

    // ****************************************
    // Assertion methods for asserting correctness of list pointers.
    // ****************************************

    private boolean assertNextTwin()
    {
        for ( EntityInstanceImpl ei = getNextTwin(); ei != null; ei = ei.getNextTwin() )
        {
            if ( ei == this )
                return false;
        }

        return true;
    }

    private boolean assertPrevTwin()
    {
        for ( EntityInstanceImpl ei = getPrevTwin(); ei != null; ei = ei.getPrevTwin() )
        {
            if ( ei == this )
                return false;
        }

        return true;
    }

    /**
     * Verifies that:
     *  - 'this' is not repeated in the hier chain.
     *
     * @return
     */
    private boolean assertNextHier()
    {
        for ( EntityInstanceImpl ei = getNextHier(); ei != null; ei = ei.getNextHier() )
        {
            if ( ei == this )
                return false;
        }

        return true;
    }

    private boolean assertPrevHier()
    {
        for ( EntityInstanceImpl ei = getPrevHier(); ei != null; ei = ei.getPrevHier() )
        {
            if ( ei == this )
                return false;
        }

        return true;
    }

    private boolean assertParent()
    {
        for ( EntityInstanceImpl ei = getParent(); ei != null; ei = ei.getParent() )
        {
            if ( ei == this )
                return false;
        }

        return true;
    }

    /**
     * For ease of use this can take a null argument.
     *
     * @param parent
     * @return False if 'child' is not a child of 'this'.
     */
    private boolean validateParentage( EntityInstanceImpl child )
    {
        if ( child == null )
            return true;

        for ( EntityInstanceImpl scan = child; scan != null; scan = scan.getParent() )
        {
            if ( scan == this )
                return true;
        }

        return false;
    }

    void setParent(EntityInstanceImpl parent)
    {
        this.parentInstance = getLatestVersion( parent );
        assert assertParent();
        // We match by ER entity token to handle recursive cases.
        assert getViewEntity().getParent().getErEntityToken() == parent.getViewEntity().getErEntityToken() :
            "Setting parent to mismatching VE.  Parent = " + parent.getViewEntity() + ", child = " + getViewEntity();
    }

    EntityInstanceImpl getPrevHier()
    {
        return getLatestVersion( prevHierInstance );
    }

    void setPrevHier(EntityInstanceImpl prevHier)
    {
        this.prevHierInstance = getLatestVersion( prevHier );
        assert assertPrevHier();
    }

    EntityInstanceImpl getNextHier()
    {
        return getLatestVersion( nextHierInstance );
    }

    void setNextHier(EntityInstanceImpl nextHier)
    {
        this.nextHierInstance = getLatestVersion( nextHier );
        assert assertNextHier();
    }

    @Override
    public EntityInstanceImpl getPrevTwin()
    {
        return getLatestVersion( prevTwinInstance );
    }

    void setPrevTwin(EntityInstanceImpl prevTwin)
    {
        this.prevTwinInstance = getLatestVersion( prevTwin );
        assert assertPrevTwin();
        assert prevTwin == null || prevTwin.getViewEntity() == getViewEntity();
    }

    @Override
    public EntityInstanceImpl getNextTwin()
    {
        return getLatestVersion( nextTwinInstance );
    }

    void setNextTwin(EntityInstanceImpl nextTwin)
    {
        this.nextTwinInstance = getLatestVersion( nextTwin );
        assert assertNextTwin();
        assert nextTwin == null || nextTwin.getViewEntity() == getViewEntity();
    }

    void setNextVersion( EntityInstanceImpl nextVersion )
    {
        this.nextVersion = nextVersion;
        assert nextVersion == null || nextVersion.getViewEntity() == getViewEntity();
    }

    /**
     * Copy all the next/prev pointers.
     *
     * @param src
     */
    private void copyAllPointers( EntityInstanceImpl src )
    {
        assert getViewEntity() == src.getViewEntity();

        // Don't use the set...() methods (eg. setNextHier) because it uses getLatestVersion() and
        // we want to set it explicitly.
        parentInstance = src.getParent();
        nextHierInstance = src.getNextHier();
        prevHierInstance = src.getPrevHier();
        nextTwinInstance = src.getNextTwin();
        prevTwinInstance = src.getPrevTwin();

        assert assertNextTwin();
        assert assertPrevTwin();
        assert assertNextHier();
        assert assertPrevHier();
        assert assertParent();
    }

    ViewOd getViewOd()
    {
        return objectInstance.getViewOd();
    }

    long getInstanceFlags()
    {
        long flags = 0;

        if ( isUpdated() )
            flags |= FLAG_UPDATED;

        if ( isDeleted() )
            flags |= FLAG_DELETED;

        if ( isCreated() )
            flags |= FLAG_CREATED;

        if ( isIncluded() )
            flags |= FLAG_INCLUDED;

        if ( isExcluded() )
            flags |= FLAG_EXCLUDED;

        if ( isHidden() )
            flags |= FLAG_HIDDEN;

        return flags;
    }

    Iterable<ViewAttribute> getNonNullAttributeList()
    {
        return attributeList.getNonNullAttributeList(  getTask() );
    }

    /**
     * Be careful using this.  It does not call the derived operation so derived
     * attribute values may be stale.
     *
     * @param viewAttrib
     * @return
     */
    AttributeValue getInternalAttribute(ViewAttribute viewAttrib)
    {
        return attributeList.getAttribute( viewAttrib );
    }

    private void executeDerivedOper( View view, ViewAttribute viewAttribute )
    {
        if ( ! viewAttribute.isDerived() )
            return;

        if ( view == null )
        {
            view = new ViewImpl( getObjectInstance() );
            view.cursor( viewAttribute.getViewEntity() ).setCursor( this );
        }

        viewAttribute.executeDerivedAttributeForGet( view );
    }

    @Override
    public int getLevel()
    {
        return level;
    }

    /**
     * Returns the last child under the current entity instance.  If there is no child
     * under 'this', then returns 'this'.
     * @return
     */
    @Override
    public EntityInstanceImpl getLastChildHier()
    {
        if ( getNextHier() == null )
            return this;

        if ( getNextTwin() != null )
        {
            // If nextTwin.prevHier is null then we haven't set the prevHier pointer
            // for nextTwin yet.  This is possible in the middle of a create entity.
            if ( getNextTwin().getPrevHier() != null )
                return getNextTwin().getPrevHier();
        }

        // If 'this' doesn't have a next hier or if the next hier has a level that is
        // not below 'this' then return 'this' because it has no descendants.
        if ( getNextHier() == null || getNextHier().getLevel() <= level )
            return this;

        EntityInstanceImpl prev = null;
        EntityInstanceImpl search;
        for ( search = this.getNextHier();
              search != null && search.getLevel() > level;
              prev = search, search = search.getNextHier() )
        {
            // We can short-circuit descendants by going to the last twin.
            search = search.getLastTwin();
        }

        assert prev != null; // The logic should guarantee we get non-null 'prev'.

        return prev;
    }

    EntityInstanceImpl getLastTwin()
    {
        EntityInstanceImpl scanInst = this;
        while ( scanInst.getNextTwin() != null )
            scanInst = scanInst.getNextTwin();
        return scanInst;
    }

    EntityInstanceImpl getFirstTwin()
    {
        EntityInstanceImpl scanInst = this;
        while ( scanInst.getPrevTwin() != null )
            scanInst = scanInst.getPrevTwin();
        return scanInst;
    }

    /**
     * Finds the first child/descendant entity that matches viewEntity.
     * @param viewEntity
     * @return May return null.
     */
    EntityInstanceImpl getFirstChildMatchingViewEntity( ViewEntity searchViewEntity )
    {
        for ( EntityInstanceImpl next = this.getNextHier(); next != null; next = next.getNextHier() )
        {
            if ( next.getViewEntity() == searchViewEntity )
                return next; // We found what we're looking for.

            if ( next.getLevel() <= this.getLevel() )
                // We've gone past the last hier so we're done.
                return null;

            // If we're looking at a descendant of 'e' then we can short-circuit
            // some of the entities by skipping to the last twin.
            if ( next.getLevel() > searchViewEntity.getLevel() )
                next = next.getLastTwin().getLastChildHier();
        }

        return null;
    }

    boolean hasChildBeenLazyLoaded( ViewEntity childViewEntity )
    {
        return getEntitiesLoadedLazily().contains( childViewEntity );
    }

    /**
     * This checks to see if the childViewEntity needs to be lazy loaded and, if so, loads it.
     *
     * @param view
     * @param childViewEntity
     */
    void lazyLoadChild( ViewImpl view, ViewEntity childViewEntity )
    {
        // Is lazyLoad enabled for this view?
        if ( ! view.isLazyLoad() )
            return;

        // Has this entity been created?  If so then nothing has been written to
        // the DB that can be lazy loaded.
        if ( isCreated() )
            return;

        // Is the child entity instance loaded lazily?
        if ( ! childViewEntity.getLazyLoadConfig().isLazyLoad() )
            return; // Nope.

        // Have we already loaded this child?
        if ( hasChildBeenLazyLoaded( childViewEntity ) )
            return;  // Already loaded.  Don't do it again.

        // Add the entity to the map now.  This will prevent an infite loop when we try
        // to activate the EI from the DB.
        getEntitiesLoadedLazily().add( childViewEntity );

        view.dblog().debug( "Lazy-loading %s for parent %s", childViewEntity.getName(), this.toString() );
        ActivateObjectInstance activator = new ActivateObjectInstance( view );
        activator.activate( view, childViewEntity );
    }

    private boolean assertNoTwin( EntityInstanceImpl parent,
                                  EntityInstanceImpl prevInstance )
    {
        if ( parent != null && prevInstance == null )
        {
            // The calling code has indicated that there are no twins of the newly created
            // instance but we will verify because if there is then the chains will be thrown off.
            for ( EntityInstanceImpl search : parent.getDirectChildren() )
            {
                if ( search.getViewEntity() == getViewEntity() )
                    return false; // We found a twin.
            }
        }

        return true;
    }

    private MaxCardinalityException checkMaxCardinality( boolean forAdd )
    {
        if ( getParent() != null )
        {
            int twinCount = getTwinCount();

            // If forAdd is true then we're making sure we can add a twin, so add
            // one to the count to account for the new one.
            if ( forAdd )
                twinCount++;

            if ( twinCount > viewEntity.getMaxCardinality() )
                return new MaxCardinalityException( getViewEntity() );
        }

        return null;
    }

    /**
     * Validates that we can add one more twin to this entity instance.
     */
    void validateMaxCardinality()
    {
        MaxCardinalityException e = checkMaxCardinality( true );
        if ( e != null )
            throw e;
    }

    /**
     * Sets the prev/next/etc pointers of an instance to insert it into the OI chain.
     *
     * @param objectInstance - OI for this instance.
     * @param parent - Parent of the new instance.
     * @param prevInstance - New instance will be inserted before/after prevInstance,
     *                       depending on the value of position.
     * @param position - Indicates where the new instance will be inserted relative to
     *                       prevInstance.
     * @param lastHierChild Points to last child under 'this'.  May be null in which case
     *                       this method will determine it.
     */
    void insertInstance( ObjectInstance     objectInstance,
                         EntityInstanceImpl parent,
                         EntityInstanceImpl prevInstance,
                         CursorPosition     position,
                         EntityInstanceImpl lastChildHier )
    {
        assert assertNoTwin( parent, prevInstance ) : "Internal error: calling code hasn't found a twin";
        assert this.validateParentage( lastChildHier ) : "Supplied lastChildHier is not a child of this";

        if ( objectInstance.getRootEntityInstance() == null )
        {
            assert this.getParent() == null : "Oops, first EI has a parent";

            // This must be the first instance in the OI.
            objectInstance.setRootEntityInstance( this );
            return;
        }

        // prevInstance may be flagged as dropped, which means that we're inserting
        // 'this' using a cursor that hasn't been repositioned.  We need to find an
        // instance that hasn't been dropped.
        if ( prevInstance != null && prevInstance.isDropped() )
        {
            // Try finding a prev/next twin that hasn't been dropped.  We have to pay
            // attention to the found instance to make sure we don't insert in the wrong
            // position.  We don't care too much about FIRST or LAST because the logic later
            // on will take care of it.
            EntityInstanceImpl search;
            if ( position == CursorPosition.PREV )
            {
                search = prevInstance.getNextTwin();
                while ( search != null && search.isDropped() )
                    search = search.getNextTwin();

                if ( search == null ) // Did we find one?
                {
                    // No, so try finding one before prevInstance.
                    search = prevInstance.getPrevTwin();
                    while ( search != null && search.isDropped() )
                        search = search.getPrevTwin();

                    // We found an instance *before* prevInstance.  We need to change position
                    // to be NEXT because we're going to change prevInstance to be an
                    // instance before the current value.
                    if ( search != null )
                        position = CursorPosition.NEXT;
                }
            }
            else
            { // FIRST, LAST, or NEXT
                search = prevInstance.getPrevTwin();
                while ( search != null && search.isDropped() )
                    search = search.getPrevTwin();

                if ( search == null ) // Did we find one?
                {
                    // No, so try finding one after prevInstance.
                    search = prevInstance.getNextTwin();
                    while ( search != null && search.isDropped() )
                        search = search.getNextTwin();

                    // We found an instance *after* prevInstance.  If position is NEXT
                    // then we need to change it to be PREV because we're going to change
                    // prevInstance to be an instance after the current value.
                    // We don't care if position is FIRST/LAST because that will be handled
                    // later.
                    if ( search != null && position == CursorPosition.NEXT )
                        position = CursorPosition.PREV;
                }
            }

            // Regardless of whether we found one we'll set prevInstance.  This may
            // mean prevInstance gets set to null but that's OK; it just means that
            // the new instance has no twins.
            prevInstance = search;
        }

        // Before we set any twin pointers let's find the last child because once we set the
        // twin pointers we can no longer use getLastChildHier().
        if ( lastChildHier == null )
            lastChildHier = this.getLastChildHier();

        // Set twin pointers.
        if ( prevInstance != null )
        {
            switch ( position )
            {
                case LAST:
                    prevInstance = prevInstance.getLastTwin();
                    // Fall through...

                case NEXT:
                    this.setPrevTwin( prevInstance );
                    if ( prevInstance.getNextTwin() != null )
                    {
                        this.setNextTwin( prevInstance.getNextTwin() );
                        prevInstance.getNextTwin().setPrevTwin( this );
                    }
                    prevInstance.setNextTwin( this );
                    break;

                case FIRST:
                    prevInstance = prevInstance.getFirstTwin();
                    // Fall through...

                case PREV:
                    this.setNextTwin( prevInstance );
                    if ( prevInstance.getPrevTwin() != null )
                    {
                        this.setPrevTwin( prevInstance.getPrevTwin() );
                        prevInstance.getPrevTwin().setNextTwin( this );
                    }
                    else
                        this.setPrevTwin( null );
                    prevInstance.setPrevTwin( this );
                    break;

                default:
                    throw new ZeidonException("Internal error: we shouldn't have CursorPosition.NONE here" );
            }
        }

        EntityInstanceImpl nextTwin = this.getNextTwin();
        EntityInstanceImpl prevTwin = this.getPrevTwin();
        if ( nextTwin == null && prevTwin == null && parent != null )
        {
            // We didn't find a twin.  Find the last child under parent with a ViewEntity
            // index < 'this' -- that will be the prev sibling.  Find the first child
            // with index > 'this' to be the next sibling.
            for ( EntityInstanceImpl child : parent.getDirectChildren() )
            {
                ViewEntity ve = child.getViewEntity();
                if ( ve.getHierIndex() < this.getViewEntity().getHierIndex() )
                {
                    prevTwin = child;
                }
                else
                if ( ve.getHierIndex() > this.getViewEntity().getHierIndex() )
                {
                    nextTwin = child;
                    break;
                }
            }
        }

        // Now insert 'this' into the hier chain.   There are three different ways we might have to do
        // this:
        //    1) We are inserting the new instance before an existing twin (nextTwin != null)
        //    2) We are inserting after an existing twin (prevTwin != null)
        //    3) There are no twins (both prevTwin and nextTwin are null).
        if ( nextTwin != null )
        {
            // Simplest case is if new instance has a next twin.

            lastChildHier.setNextHier( nextTwin );

            // KJS - 02/04/2011 For some reason when I execute the following line from lTrnscpt_Object:
            // OrderEntityForView( lTrnscpt, "TranscriptGroup", "wCollegeYear A Semester A wLegacyTerm A wSortOrder A" );
            // after executing the following line, if I try to go to the object browser everything is hung.  I could have
            // sworn earlier that I got this issue when executing "this.getPrevHier().setNextHier( this );" so I changed it just to
            // see what would happen and now I get it on "this.setPrevHier( nextSibling.getPrevHier() );".  I am confused, it doesn't
            // seem incorrect and I am wondering could it have anything to do with the fact that for TranscriptGroup the IDs are all NULL???
            this.setPrevHier( nextTwin.getPrevHier() );
            if ( this.getPrevHier() != null )
            {
               EntityInstanceImpl tempPrev = this.getPrevHier();
               tempPrev.setNextHier( this );
            }

            nextTwin.setPrevHier( lastChildHier );
            if ( nextTwin == getObjectInstance().getRootEntityInstance() )
                getObjectInstance().setRootEntityInstance( this );
        }
        else
        if ( prevTwin != null )
        {
            // Since we know that nextTwin == null then we must be adding the new instance to the end
            // of the twins.
            EntityInstanceImpl lastHier = prevTwin.getLastChildHier();
            EntityInstanceImpl nextHier = lastHier.getNextHier();

            this.setPrevHier( lastHier );
            lastChildHier.setNextHier( nextHier );
            lastHier.setNextHier( this );
            if ( nextHier != null )
                nextHier.setPrevHier( lastChildHier );
        }
        else
        {
            assert parent != null : "Internal error inserting instance: parent is null";

            // There are no twins so we need to insert between the parent
            this.setPrevHier( parent );
            lastChildHier.setNextHier( parent.getNextHier() );
            parent.setNextHier( this );
            if ( lastChildHier.getNextHier() != null )
                lastChildHier.getNextHier().setPrevHier( lastChildHier );
        }

        // Check to see if we need to set the root entity instance to this.
        if ( this.getPrevHier() == null )
        {
            getObjectInstance().setRootEntityInstance( this );
        }
    }

    /**
     * Returns the next entity in heir order that is not a descendant of 'this'.
     * May return null.
     *
     * @return
     */
    EntityInstanceImpl getNextNonDescendant()
    {
        // Quick check: a twin is always the next non descendant.
        if ( this.getNextTwin() != null )
            return this.getNextTwin();

        // Find the last child under 'this' and return its next hier.
        return this.getLastChildHier().getNextHier();
    }

    @Override
    public CursorResult deleteEntity()
    {
        return deleteEntity(true, null);
    }

    /**
     * A simple wrapper around deleteEntity( boolean, view ).  We use this to hide the use
     * of spawnRootDelete from users outside this class.
     *
     * @param view
     * @return
     */
    CursorResult deleteEntity( View view )
    {
        return deleteEntity( true, view );
    }

    /**
     * Delete the entity.  'view' is passed to EventData listener.
     *
     * @param view
     * @return
     */
    CursorResult deleteEntity( boolean spawnRootDelete, View view )
    {
        if ( ! getViewEntity().isDelete() )
            throw new ZeidonException( "Entity is not flagged for delete." )
                            .prependViewEntity( getViewEntity() );

        if ( getViewEntity().getEventListener() != null )
        {
            EventDataImpl data = new EventDataImpl( getTask() ).setEntityInstance( this ).setView( view );
            getViewEntity().getEventListener().event( EventNotification.EntityDeleted, data );
        }

        int startLevel = getLevel();

        // If checkRestrictedDelete is set, then make sure none of the child entities
        // have their parent-restrict delete flag set.
        if ( getViewEntity().isCheckRestrictedDelete() )
        {
            for ( EntityInstanceImpl child : this.getDirectChildren() )
            {
                if ( child.getViewEntity().isRestrictParentDelete( ) )
                    throw new ZeidonException( "Can't delete %s because of restrict constraint on child entity %s", this, child );
            }
        }

        EntitySpawner spawner = new EntitySpawner( this, view );

        // Run through the entity and all it's children and set the delete flag.
        // We start with 'this' because the logic below spawns the delete and
        // we want to spawn the deletes for all entities.
        EntityInstanceImpl scan = this;
        while ( scan != null && ( scan.getLevel() > startLevel || scan == this ) )
        {
            // If the instance in question is already hidden, skip it
            // and all of its descendants since it may have been excluded or
            // moved BEFORE the Delete was issued.
            if ( scan.isHidden() )
            {
                scan = scan.getNextNonDescendant();
                continue;
            }

            if ( ! scan.getViewEntity().isParentDelete()  && scan != this )
            {
                scan.excludeEntity( true ); // This will also exclude linked instances.
                scan = scan.getNextNonDescendant();  // exclude() handled child entities so skip them.
                continue;
            }

            scan.setDeleted( true );

            // Spawn the delete for linked instances.  We don't always want to spawn the
            // delete for instances linked to the root of the root ('this') because we
            // may be in a recursive call to delete.
            if ( scan != this || spawnRootDelete )
                spawner.spawnDelete( scan );

            scan = scan.getNextHier();
        }

        dropIfDead();

        return CursorResult.UNCHANGED;
    }

    private boolean isDead()
    {
        if ( ! isHidden() )
            return false;  // Non-hidden instances are never dead.

        // Versioned instances are never dead because if we drop them it could screw
        // up previous versions if we ever need to cancel/accept.
        // DGC 2011-10-12 We allow versioned instances to be dropped.
        // DGC 2012-02-16 It seems we can't drop versioned instances because then if we later
        //                do an accept then we don't know what's been deleted.  The above comment
        //                indicates that there was a need to drop versioned instances but I don't
        //                remember why.
        if ( isVersioned() )
            return false;

        if ( ! getViewEntity().isDerived() && ! getViewEntity().isDerivedPath() )
        {
            // Entities that aren't flagged as created or included can't be dead.
            if ( ! isCreated() && ! isIncluded() && ( isDeleted() || isExcluded() ) )
                return false;
        }

        return true;
    }

    /**
     * Checks to see if an entity is "dead".  A dead entity instance is an
     * instance that's been deleted but doesn't need to be saved to the database.
     * If a an entity is dead then it is removed from the OI entity instance chains.
     * @return true if instance is cleared.
     */
    private boolean dropIfDead()
    {
        if ( ! isDead() )
            return false;

        // Entity instance is dead.  Drop it from the chain.
        getTask().log().trace( "Cleaning up dead entity %s for %s", this, viewEntity );
        dropEntity();
        return true;
    }

    boolean temporalVersionMatch( EntityInstanceImpl ei )
    {
        // If neither are versioned then they match.
        if ( ! this.isVersioned() && ! ei.isVersioned() )
            return true;

        // At least one is versioned.  They only match if they their version
        // numbers are the same.
        return this.versionNumber == ei.versionNumber;
    }

    void excludeEntity( boolean spawnExclude )
    {
        setExcluded( true );

        if ( spawnExclude )
        {
            EntitySpawner spawner = new EntitySpawner( this );
            spawner.spawnExclude();
        }

        hideChildren();

        // Drop the instance if this is a dead entity.
        dropIfDead();
    }

    @Override
    public CursorResult excludeEntity()
    {
        // If there is no parent then excluding the entity is the same as dropping it.
    	if ( getParent() == null )
    	{
    	    // For consistency's sake we won't allow excluding a root entity that is also versioned.
    	    if ( isVersioned() )
    	        throw new ZeidonException( "Invalid operation: cannot excluded a root entity that is versioned");

    	    // Excluding a root entity is the same as dropping it.
    	    dropEntity();
            return CursorResult.UNCHANGED;
    	}

    	// Validate the exclude.
    	if ( ! getViewEntity().isExclude() )
    	    throw new ZeidonException( "Entity is not flagged for exclude." )
    	                    .prependViewEntity( getViewEntity() );

    	excludeEntity( true );
        return CursorResult.UNCHANGED;
    }

    private void hideChildren()
    {
        for ( EntityInstanceImpl child : getChildrenHier() )
            child.setHidden( true );
    }

    void copyFlags( EntityInstanceImpl source )
    {
        setHidden( source.hidden );
        setDeleted( source.deleted );
        setCreated( source.created );
        setIncluded( source.included );
        setExcluded( source.excluded );
        setUpdated( source.updated );
    }

    @Override
    public boolean linkInstances( EntityInstance source )
    {
        assert source != null;

        if ( source == this )
            return false;

        if ( viewEntity.getErEntityToken() != source.getViewEntity().getErEntityToken() )
            throw new ZeidonException("Unmatched ER tokens").appendMessage( "Target = %s\nSource = %s", viewEntity, source.getViewEntity() );

        EntityInstanceImpl s = (EntityInstanceImpl) source.getEntityInstance();

        // If key1 is null then we are probably in the middle of creating the entity instance
        // and we already know we can link them, so skip the key check.
        // This happens, for example, when loading entities from the DB.
        String key1 = this.getKeyString();
        if ( key1 != null )
        {
            String key2 = s.getKeyString();
            if ( ! StringUtils.equals( key2, key1 ) )
                throw new ZeidonException( "Attempting to relink instances with different keys.")
                                .appendMessage( "Entity1 = %s.%s", getViewOd().getName(), getViewEntity().getName() )
                                .appendMessage( "Key1 = %s", key2 )
                                .appendMessage( "Entity2 = %s.%s", s.getViewOd().getName(), s.getViewEntity().getName() )
                                .appendMessage( "Key2 = %s", key1 );
        }

        Collection<EntityInstanceImpl> linked = this.getLinkedInstances();
        if ( linked.contains( s ) )
            return false;

        linkInternalInstances( s );
        return true;
    }

    /**
     * Checks to see if all the attributes in targetEi as sourceEi.
     *
     * @return null if all attributes exist otherwise ViewAttribute of first missing attribute found.
     */
    static private ViewAttribute checkForAllPersistentAttributes( ViewEntity source, ViewEntity target )
    {
        for ( ViewAttribute va : target.getAttributes() )
        {
            if ( ! va.isPersistent() )
                continue;

            if ( source.getAttribute( va.getName(), false ) == null )
                return va;
        }

        return null;
    }

    enum LinkValidation
    {
        SOURCE_OK;
    }

    /**
     * Validate that the source and target entities are OK to link.  The error we're checking
     * for is that the source entity doesn't define all the attributes that the target has.  If
     * that happens the attributes in target will be lost when it is linked to source.
     *
     * If the entities have the same ER date then we'll assume all is good because they were
     * built with the same ER and both entities should have all the required attributes.
     *
     * Some day we may handle checking the reverse (linking source to target).
     *
     * @param target
     * @param source
     * @return
     */
    static LinkValidation validateLinking( ViewEntity target, ViewEntity source )
    {
        if ( target == source )
            return LinkValidation.SOURCE_OK;

        ViewAttribute missingViewAttribute = null;

        // If they have the same ER date we'll assume all is good.
        if ( source.getViewOd().getErDate().equals( target.getViewOd().getErDate() ) )
            return LinkValidation.SOURCE_OK;

        // Check to see if it's ok for target to be linked to source.
        ViewEntityLinkInfo sourceInfo = getViewEntityLinkInfo( source );
        Boolean sourceOk = sourceInfo.mayBeLinked.get( target );
        if ( sourceOk == Boolean.TRUE )
            return LinkValidation.SOURCE_OK;

        /*  This leads to a NPE.  Some day we may try to fix it.
        // Check to see if source can be linked to 'this'.
        ViewEntityLinkInfo targetInfo = getViewEntityLinkInfo( viewEntity );
        Boolean targetOk = targetInfo.mayBeLinked.get( viewEntity );
        if ( targetOk == Boolean.TRUE )
        {
            source.attributeList = AttributeListInstance.newSharedAttributeList( source, source.attributeList,
                                                                                 this, this.attributeList );
            return;
        }
        */

        if ( sourceOk == null ) // If it's null we've never checked this one.
        {
            missingViewAttribute = checkForAllPersistentAttributes( target, source );
            sourceOk = missingViewAttribute == null;
            sourceInfo.mayBeLinked.putIfAbsent( target, sourceOk );
            if ( sourceOk == Boolean.TRUE )
                return LinkValidation.SOURCE_OK;
        }

        /*  This leads to a NPE.  Some day we may try to fix it.
        if ( targetOk == null ) // If it's null we've never checked this one.
        {
            missingViewAttribute = checkForAllPersistentAttributes( source, this );
            targetOk = missingViewAttribute == null;
            targetInfo.mayBeLinked.putIfAbsent( sourceViewEntity, targetOk );
            if ( targetOk == Boolean.TRUE )
            {
                source.attributeList = AttributeListInstance.newSharedAttributeList( source, source.attributeList,
                                                                                     this, this.attributeList );
                return;
            }
        }
        */

        ZeidonException ex = new ZeidonException( "Attempting to link instances that don't have matching attributes" );
        ex.appendMessage( "Source instance = %s", source );
        ex.appendMessage( "Target instance type = %s", target );
        ex.appendMessage( "Missing attribute = %s.%s.%s",
                          missingViewAttribute.getViewEntity().getViewOd().getName(),
                          missingViewAttribute.getViewEntity().getName(),
                          missingViewAttribute.getName() );

        throw ex;
    }

    /**
     * Link this entity instance with 'source'.
     *
     * @param source
     */
    void linkInternalInstances( EntityInstanceImpl source )
    {
        assert source != null;
        assert source != this;
        assert viewEntity.getErEntityToken() == source.getViewEntity().getErEntityToken();

        LinkValidation valid = validateLinking( getViewEntity(), source.getViewEntity() );
        if ( valid == LinkValidation.SOURCE_OK )
        {
            this.attributeList = AttributeListInstance.newSharedAttributeList( this, this.attributeList,
                                                                               source, source.attributeList );
            return;
        }

        // We should never get here because validateLinking() should throw an exception if
        // it doesn't return SOURCE_OK but just in case...
        throw new ZeidonException( "Internal error.  validateLinking returned something invalid." );
    }

    TaskImpl getTask()
    {
        return objectInstance.getTask();
    }

    @Override
    public boolean isUpdated()
    {
        return updated;
    }

    @Override
    public boolean isDeleted()
    {
        return deleted;
    }

    @Override
    public boolean isCreated()
    {
        return created;
    }

    @Override
    public boolean isIncluded()
    {
        return included;
    }

    @Override
    public boolean isHidden()
    {
        return hidden;
    }

    @Override
    public boolean isExcluded()
    {
        return excluded;
    }

    boolean isDropped()
    {
        return dropped;
    }

    void setVersionedEntity()
    {
        setVersionStatus( VersionStatus.UNACCEPTED_ENTITY );
    }

    private void setVersionStatus( VersionStatus newStatus )
    {
        versionStatus = newStatus;
    }

    @Override
    public boolean isVersioned()
    {
        return versionStatus == VersionStatus.UNACCEPTED ||
               versionStatus == VersionStatus.UNACCEPTED_ROOT ||
               versionStatus == VersionStatus.UNACCEPTED_ENTITY;
    }

    /**
     * Returns true if any of the incremental update flags are true.
     */
    private boolean isChanged()
    {
        if ( isUpdated() || isCreated() || isDeleted() || isIncluded() || isExcluded() )
            return true;

        return false;
    }

    /**
     * Creates a temporal subobject with 'this' as the parent.  A copy of every entity instance
     * is made and each new version has its own attributeList.  The new versions point back
     * to the old ones and the old ones point to the new ones.
     *
     * It is expected that all subsequent cursor operations will first find the most recent
     * version before any action is taken.
     */
    @Override
    public EntityInstanceImpl createTemporalSubobjectVersion()
    {
        for ( EntityInstanceImpl ei : getChildrenHier( true ) )
        {
            if ( ei.versionStatus == VersionStatus.UNACCEPTED_ROOT )
                throw new TemporalEntityException( this, "Attempting to create a temporal subobject for an entity that " +
                		                           "has an unaccepted temporal root as a child." )
                          .appendMessage( "Temporal root: %s", ei.toString() );

            // See if any linked instances are already versioned.
            for ( EntityInstanceImpl linked : ei.getAllLinkedInstances() )
            {
                // It's linked is a child of 'this' then it's ok.
                if ( linked.hasAncestorOf( this ) )
                    continue;

                if ( linked.isVersioned() )
                    throw new TemporalEntityException( this, "Attempting to create a temporal subobject for an entity that " +
                                                       "has a child entity linked to another temporal entity.")
                              .appendMessage( "Temporal root: %s", ei.toString() )
                              .appendMessage( "Linked instance: %s", linked.toString() );
            }
        }

        TemporalVersionCreator creator = new TemporalVersionCreator( getTask(), this );
        return creator.createTemporalVersion();
    }

    void setIncrementalFlags( long flags )
    {
        if ( ( flags & FLAG_HANGING_ENTITY ) != 0 )
            throw new ZeidonException("Hanging Entity flags not currently supported");

        setCreated( ( flags & FLAG_CREATED ) != 0 );
        setUpdated( ( flags & FLAG_UPDATED ) != 0 );
        setDeleted( ( flags & FLAG_DELETED ) != 0 );
        setExcluded( ( flags & FLAG_EXCLUDED ) != 0 );
        setIncluded( ( flags & FLAG_INCLUDED ) != 0 );
        setHidden( ( flags & FLAG_HIDDEN ) != 0 );

/* We'll ignore some flags but we'll leave this code in for now to indicate that
 * we're ignoring it on purpose.
        if ( ( flags & FLAG_RECORD_OWNER ) != 0 )
            ; // Ignore record owner

        if ( ( flags & FLAG_RELOWNER ) != 0 )
            ; // Ignore rel owner

        if ( ( flags & FLAG_PREV_VERSION ) != 0 )
            ; // Ignore prev version

        if ( ( flags & FLAG_PREV_VERSIONRT ) != 0 )
            ; // Ignore prev version root
 */

        if ( ( flags & ( FLAG_CREATED | FLAG_UPDATED | FLAG_DELETED | FLAG_EXCLUDED | FLAG_HIDDEN) ) != 0 )
            getObjectInstance().setUpdated( true );
    }

    void setUpdated(boolean isUpdated )
    {
        setUpdated( isUpdated, true, true );
    }

    /**
     * Set the updated flag for this entity instance and the parent OI.
     *
     * @param isUpdated - New value of the EI update flag.
     * @param setLinked - If true, set update flag for all linked instances.
     * @param setPersistent - If true, set EI fl
     */
    void setUpdated(boolean isUpdated, boolean setLinked, boolean setPersistent)
    {
        if ( this.updated == isUpdated )
            return;

        if ( getViewEntity().isDebugIncremental() )
            JoeUtils.sysMessageBox( "Debug Incremental", "Changing update flag for " + toString() );

        // The isUpdated flag is only set for persistent attributes.
        if ( setPersistent )
            this.updated = isUpdated;

        // We don't replicate the updated flag if it's being turned off.
        if ( isUpdated )
        {
            if ( ! isVersioned() )
            {
                if ( setPersistent )
                    getObjectInstance().setUpdated( true ); // Also sets updatedFile
                else
                    getObjectInstance().setUpdatedFile( true );

                if ( setLinked && setPersistent )
                {
                    for ( EntityInstanceImpl linked : getLinkedInstances() )
                        linked.setUpdated( true, false, setPersistent );
                }
            }
        }
    }

    private void setObjectInstanceUpdated()
    {
        ObjectInstance oi = getObjectInstance();
        if ( ! isVersioned() ) // Don't update the OI yet if this entity is versioned.
            oi.setUpdated( true );
    }

    void setDeleted(boolean isDeleted)
    {
        this.deleted = isDeleted;
        if ( isDeleted )
        {
            setObjectInstanceUpdated();
            setHidden( true );
        }
    }

    void setCreated(boolean isCreated)
    {
        this.created = isCreated;
        if ( isCreated )
            setObjectInstanceUpdated();
    }

    void setIncluded(boolean isIncluded)
    {
        this.included = isIncluded;
        if ( isIncluded )
            setObjectInstanceUpdated();
    }

    void setHidden(boolean isHidden)
    {
        this.hidden = isHidden;
        if ( isHidden )
            removeAllHashKeyAttributes();
    }

    void setExcluded(boolean isExcluded)
    {
        this.excluded = isExcluded;
        if ( isExcluded )
        {
            setObjectInstanceUpdated();
            setHidden( true );
        }
    }

    private void acceptSubobjectEntity(final VersionStatus newStatus)
    {
        assert isVersioned() : "Unexpected version status: " + versionStatus;

        setVersionStatus( newStatus );

        // Check for a null pointer because it's possible this entity was
        // created and therefore won't have a prevVersion.
        if ( prevVersion != null )
        {
            Collection<EntityInstanceImpl> linkedInstances = prevVersion.getLinkedInstances();
            if ( linkedInstances.size() > 0 ) // If size = 0 then 'this' is only linked with itself.
            {
                // Update the persistent attributes for each of the linked instances.
                for ( EntityInstanceImpl linked : linkedInstances )
                {
                    // We only care about instances that *don't* have a next version.  If they
                    // have a next version then they better be part of the current temporal
                    // subobject that we're accepting, which means we've handled it already.
                    if ( linked.nextVersion != null )
                    {
                        assert linked.versionNumber == prevVersion.versionNumber : "Version numbers don't match";
                        continue;
                    }

                    // Update the attribute values of the linked instance to point to the new
                    // attribute list.
                    linked.removeAllHashKeyAttributes();  // If linked has attr hashkeys, remove them.
                    AttributeListInstance.updateSharedAttributeList( linked.attributeList, this.attributeList );
                    linked.addAllHashKeyAttributes(); // TODO: We could limit this to only EIs that have been updated.

                    // The spawn logic should have correctly set most of the flags.  The only one we have to
                    // copy is the update flag.
                    if ( this.isUpdated() )
                        linked.setUpdated( true );

                    assert this.isDeleted() == linked.isDeleted()   : "acceptSubobject flag logic is wrong";
                    assert this.isExcluded() == linked.isExcluded() : "acceptSubobject flag logic is wrong";
                    assert this.isUpdated() == linked.isUpdated()   : "acceptSubobject flag logic is wrong";
                    assert this.isIncluded() == linked.isIncluded() : "acceptSubobject flag logic is wrong";

                    // Update the version number so that the linked instance has the same value.
                    linked.versionNumber = this.versionNumber;

                    if ( linked.isChanged() )
                        linked.getObjectInstance().setUpdated( true );
                }

                prevVersion.attributeList.mergeLinkedInstances( prevVersion, this.attributeList );
            }

            // DON'T null out prevVersion.nextVersion.  That will allow any cursors pointing to the
            // superseded version to find the new one.  The GC will eventually clean up.
            // Setting the dropped flag also tells the linked logic to ignore this entity.
            prevVersion.dropped = true;
            prevVersion.setVersionStatus( VersionStatus.SUPERSEDED );

            // If the previous version also has a previous version then we have multiple levels
            // of versioning.
            if ( prevVersion.prevVersion != null )
            {
                EntityInstanceImpl grandfatherVersion = prevVersion.prevVersion;
                grandfatherVersion.setNextVersion( this );
                this.prevVersion = grandfatherVersion;
            }
            else
                prevVersion = null;
        }

        // If this entity has been changed then set the flag for the OI.
        if ( isChanged() )
            getObjectInstance().setUpdated( true );
    }

    void validateSubobject( Collection<ZeidonException> list )
    {
        assert isHidden() == false : "Attempting to validate a hidden instance.";

        //
        // First make sure that all the required attributes have non-null values
        // if this entity has been changed in any way.
        //
        if ( isCreated() || isChanged() || isIncluded() )
        {
            for ( ViewAttribute viewAttribute : getViewEntity().getAttributes() )
            {
                // Ignore hidden attributes.
                if ( viewAttribute.isHidden() )
                    continue;

                // Genkeys will have their value created so ignore it.
                if ( viewAttribute.isGenKey() )
                    continue;

                if ( ! viewAttribute.isRequired() )
                    continue;

                if ( ! isAttributeNull( viewAttribute ) )
                    continue;

                list.add( new RequiredAttributeException( viewAttribute ) );
            }
        }

        //
        // Validate max cardinality with the parent.
        //
        MaxCardinalityException e = checkMaxCardinality( false );
        if ( e != null )
            list.add( e );

        //
        // Make sure there is at least one instance of all required child entities.
        //
        for ( ViewEntity childEntity : getViewEntity().getChildren() )
        {
            if ( childEntity.getMinCardinality() == 0 )
                continue;  // Child entities aren't required so ignore this one.

            // Make sure there is at least one child instance that matches this.
            if ( ! getChildren( childEntity, false ).hasNext() )
                list.add( new RequiredEntityMissingException( childEntity ) );
        }

        // Now run this on all direct children.
        for ( EntityInstanceImpl childInstance : getDirectChildren() )
            childInstance.validateSubobject( list );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#validateSubobject()
     */
    @Override
    public Collection<ZeidonException> validateSubobject()
    {
        Collection<ZeidonException> list = new ArrayList<ZeidonException>();
        validateSubobject( list );

        if ( list.size() == 0 )
            return null;

        return list;
    }

    /**
     * Calls validateSubobject() and throws SubobjectValidationException if any
     * errors were found.
     *
     * @throws SubobjectValidationException
     */
    void validateSubobjectThrowException()
    {
        Collection<ZeidonException> list = validateSubobject();
        if ( list == null || list.size() == 0 )
            return;

        throw new SubobjectValidationException( list );
    }

    @Override
    public EntityInstanceImpl acceptSubobject()
    {
        // If the entity is a temporal entity created via createTemporal we'll
        // accept it.
        if ( versionStatus == VersionStatus.UNACCEPTED_ENTITY )
        {
            acceptTemporalEntity();
            return this;
        }

        // Before we change any version pointers let's validate the entity and attribute values.
        validateSubobjectThrowException();

        if ( versionStatus != VersionStatus.UNACCEPTED_ROOT )
            throw new TemporalEntityException(this, "Entity is not a root of a temporal subobject root" );

        assert prevVersion != null : "Unaccepted root has null prevVersion";

        // Make sure none of the child EIs are an unaccepted root.
        for ( final EntityInstanceImpl ei : getChildrenHier( false, false ) ) // Loop through all children, including excluded.
        {
            if ( ei.versionStatus == VersionStatus.UNACCEPTED_ROOT )
                throw new TemporalEntityException( this, "Entity has children that are unaccepted version roots" );
        }

        // Get the versionStatus of the previous version.  We'll set the status of all children to be
        // this status.  We need to do this in case there are multiple layers of versioned subobjects.
        final VersionStatus newStatus = prevVersion.versionStatus;
        assert newStatus == VersionStatus.UNACCEPTED || newStatus == VersionStatus.NONE :
            "Internal error: unsupported double versioning " + newStatus;

        // Spawn the changes to linked instances.
        EntitySpawner spawner = new EntitySpawner( this );
        spawner.spawnAccept();

        for ( final EntityInstanceImpl ei : getChildrenHier( true, false ) ) // Loop through all, including excluded.
            ei.acceptSubobjectEntity( newStatus );

        // Set next/prev pointers the in non-versioned instances surrounding 'this'.
        if ( getPrevHier() == null )
            getObjectInstance().setRootEntityInstance( this );
        else
            getPrevHier().setNextHier( this );

        if ( getPrevTwin() != null )
            getPrevTwin().setNextTwin( this );

        if ( getNextTwin() != null )
            getNextTwin().setPrevTwin( this );

        // this.getNextHier() still points to the old, superseded entity.

        final EntityInstanceImpl lastHier = this.getLastChildHier();
        if ( lastHier.getNextHier() != null )
            lastHier.getNextHier().setPrevHier( lastHier );

        // Now go through and drop any dead instances.  We do it here after all the
        // chain pointers have been changed.
        EntityInstanceImpl search = getNextHier();
        while ( search != null && search.getLevel() > this.getLevel() )
        {
            if ( search.isDead() )
            {
                // Get the last hier now before we drop it.  This way we can skip over
                // dropping all the child entities.
                EntityInstanceImpl t = search.getLastChildHier().getNextHier();
                search.dropEntity();
                search = t;
            }
            else
                search = search.getNextHier();
        }

        getObjectInstance().decrementVersionedCount();

        return this;
    }

    /**
     * Cancels the temporal entity.
     *
     * Note that we don't change this.prevVersion because it is used by cursor logic to
     * find the correct version.
     */
    private void cancelSubobjectEntity()
    {
        assert isVersioned() : "Unexpected version status";

        setVersionStatus( VersionStatus.CANCELED );

        // prevVersion should only be null if the current entity was created/inserted.
        if ( prevVersion != null )
        {
            prevVersion.setNextVersion( null );
            prevVersion.addAllHashKeyAttributes();
        }
        else
            assert isCreated() || isIncluded();

        dropped = true;
    }

    @Override
    public EntityInstanceImpl cancelSubobject()
    {
        // If the entity is a temporal entity created via createTemporal we'll cancel it.
        if ( versionStatus == VersionStatus.UNACCEPTED_ENTITY )
        {
            cancelTemporalEntity();
            return this.prevVersion;
        }

        if ( versionStatus != VersionStatus.UNACCEPTED_ROOT )
            throw new TemporalEntityException(this, "Entity is not the root of a temporal subobject" );

        // All we need to do is go through all of the entities in the subobject and set their
        // pointers to null.  Everything else will be taken care of by normal processing.
        // The getChildrenHier uses an iterator that needs the nextHier pointers so we'll
        // create a temporary list before nulling them out.
        List<EntityInstanceImpl> list = new ArrayList<EntityInstanceImpl>();
        for ( EntityInstanceImpl ei : this.getChildrenHier( true, false ) )
            list.add( ei );

        // Now we can set the pointers to null without a problem.
        for ( EntityInstanceImpl ei : list )
            ei.cancelSubobjectEntity();

        getObjectInstance().decrementVersionedCount();
        return this.prevVersion;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#acceptTemporalEntity()
     */
    @Override
    public void acceptTemporalEntity()
    {
        if ( versionStatus != VersionStatus.UNACCEPTED_ENTITY )
            throw new TemporalEntityException(this, "Entity is not the root of a temporal entity");

        // Before we change any version pointers let's validate the entity and attribute values.
        validateSubobjectThrowException();

        for ( EntityInstanceImpl ei : getChildrenHier( true, false ) )
            ei.setVersionStatus( VersionStatus.NONE );

        EntitySpawner spawner = new EntitySpawner( this );
        spawner.spawnCreate();

        getObjectInstance().setUpdated( true );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#cancelTemporalEntity()
     */
    @Override
    public CursorResult cancelTemporalEntity()
    {
        if ( versionStatus != VersionStatus.UNACCEPTED_ENTITY )
            throw new TemporalEntityException(this, "Entity is not the root of a temporal entity" );

        setVersionStatus( VersionStatus.CANCELED );

        // The only thing we have to do is drop the entity.
        dropEntity();

        return CursorResult.UNCHANGED;
    }

    /**
     * Removes the entity and its children from the OI chain.
     */
    @Override
    public CursorResult dropEntity()
    {
        // DGC 2011.10.12  - We're allowing versioned instances to be dropped.  Shouldn't be a problem.
        // DGC 2012.02.16  - See comment in dropIfDead().
        if ( isVersioned() )
            throw new ZeidonException( "Invalid operation: can't drop a temporal entity." );

        dropped = true;

        // Following comments may no longer be apropos now that we don't allow dropping
        // versioned objects.  2011-04-12.

        // Do we have to figure out what to do with instances that have other versions still
        // pointing to it?  If we delete them then any cursor still pointing to it won't
        // know what to do.  Should we just throw an error if a cursor is pointing to an
        // old version of a dropped entity?  Is this even a problem?

        // Set the hidden flag for this entity.  That way any cursors that still point
        // to this entity will throw a null entity exception.
        if ( ! isHidden() )
            setHidden( true );

        EntityInstanceImpl lastChild = removeEntityFromChains();

        // We reset the next hier pointer so when we're looping through the EIs in hier
        // order we skip past the ones we just removed.
        setNextHier( lastChild.getNextHier() );

        // We don't have to remove the entity from the linkedInstance map because they
        // are weak references.
        return CursorResult.UNCHANGED;
    }

    /**
     * Sets the next/prev pointers to remove 'this' entity from the OI.
     *
     * Returns the last hier child.
     */
    EntityInstanceImpl removeEntityFromChains()
    {
        EntityInstanceImpl lastChild = getLastChildHier();
        if ( getPrevHier() != null )
            getPrevHier().setNextHier( lastChild.getNextHier() );
        else
            getObjectInstance().setRootEntityInstance( getNextTwin() );

        //if ( getNextHier() != null )
        //    getNextHier().setPrevHier( getPrevHier() );
        if ( lastChild.getNextHier() != null )
        	lastChild.getNextHier().setPrevHier( getPrevHier() );

        if ( getPrevTwin() != null )
            getPrevTwin().setNextTwin( getNextTwin() );

        if ( getNextTwin() != null )
            getNextTwin().setPrevTwin( getPrevTwin() );

        return lastChild;
    }

    /**
     * Sets all the prev/next pointers to null so that this entity doesn't refer to
     * any other EIs.  This is intended to be used in commit processing when an EI is
     * being superseded.  This also sets the 'dropped' flag to indicate that it should
     * not be considered in linked processing.
     *
     * NOTE: This does NOT reset the pointers of twin instances.  Use removeEntityFromChains()
     * for that.
     */
    void setEiPointersToNull()
    {
        parentInstance = null;
        prevHierInstance = null;
        prevTwinInstance = null;
        nextHierInstance = null;
        nextTwinInstance = null;
        dropped          = true;
        // Don't set nextVersion!
    }

    String getTag()
    {
        return tag;
    }

    void setTag(String tag)
    {
        this.tag = tag;
    }

    @Override
    public long getEntityKey()
    {
        return entityKey;
    }

    @Override
    public synchronized UUID getEntityUuid()
    {
        if ( uuid == null )
            uuid = getTask().getObjectEngine().generateUuid();

        return uuid;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#getObjectInstanceId()
     */
    @Override
    public UUID getObjectInstanceUuid()
    {
        return getObjectInstance().getUuid();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#getObjectInstanceId()
     */
    @Override
    public long getObjectInstanceId()
    {
        return getObjectInstance().getId();
    }

    /**
     * Returns the linked instances.  Note this included 'this'.
     *
     * @return
     */
    Collection<EntityInstanceImpl> getAllLinkedInstances()
    {
        return attributeList.getLinkedInstances( false, false, this );
    }

    /**
     * Returns an iterable list of entities linked with 'this'.  If there
     * are no linked entities it will return an empty list.
     *
     * @return
     */
    @Override
    public Collection<EntityInstanceImpl> getLinkedInstances()
    {
        return attributeList.getLinkedInstances( false, true, this );
    }

    /**
     * Get the list of linked instances, including those that have been dropped
     * but still unclaimed by the GC.
     *
     * @param includeDropped
     * @return
     */
    Collection<EntityInstanceImpl> getAllLinkedInstances( boolean includeDropped )
    {
        return attributeList.getLinkedInstances( includeDropped, false, this );
    }

    /**
     * Used by commit/activate processing to store each entity's index
     * from the root.  This is different from getHierPosition() in that it
     * includes hidden entities.
     *
     * @return
     */
    long getHierIndex()
    {
        return hierIndex;
    }

    void setHierIndex(long hierIndex)
    {
        this.hierIndex = hierIndex;
    }

    boolean isWritten()
    {
        return isWritten;
    }

    void setWritten(boolean isWritten)
    {
        this.isWritten = isWritten;
    }

    boolean isRecordOwner()
    {
        return isRecordOwner;
    }

    void setRecordOwner(boolean isRecordOwner)
    {
        this.isRecordOwner = isRecordOwner;
    }

    /**
     * Determines if 'this' has an ancestor of 'ancestor'.  Will return true if
     * 'this' == ancestor
     *
     * @param ancestor
     * @return
     */
    private boolean hasAncestorOf( EntityInstanceImpl ancestor )
    {
        for ( EntityInstanceImpl parent = this; parent != null; parent = parent.getParent() )
        {
            if ( parent == ancestor )
                return true;
        }

        return false;
    }

    /**
     * Find the parent entity instance of 'this' that has parentViewEntity as its
     * entity definition.  Must work for recursive subobjects so the logic is a bit
     * more complicated than just doing a simple comparison.
     *
     * Used in commit processing to set foreign keys.
     *
     * @param parentViewEntity
     * @return
     */
    EntityInstanceImpl findMatchingParent( ViewEntity parentViewEntity )
    {
        EntityInstanceImpl searchInstance = getParent();
        while ( searchInstance != null && searchInstance.getViewEntity() != parentViewEntity )
        {
            ViewEntity ve = searchInstance.getViewEntity();

            // If the parent entity we are looking for is a recursive parent,
            // then it's possible that the entity instance we are looking for
            // has an lpViewEntity that is the recursive child entity.
            if ( parentViewEntity.isRecursiveParent() && ve.isRecursive() &&
                 parentViewEntity.getErEntityToken() == ve.getErEntityToken() )
            {
                break;
            }

            searchInstance = searchInstance.getParent();
        }

        return searchInstance;
    }

    /**
     * This is used to find an entity instance in the OI by searching for the matching
     * hier index.  Assumes:
     * - Hier indexes have been set properly.
     *
     * @param index
     * @return
     */
/*
    EntityInstanceImpl findByHierIndex( long index )
    {
        List<Long> lst = new ArrayList<Long>();
        EntityInstanceImpl search = this;
        while ( search != null )
        {
            while ( search.getNextTwin() != null && search.getNextTwin().getHierIndex() <= index )
                search = search.getNextTwin();

            if ( search.getHierIndex() == index )
                return search;

            if ( search.getHierIndex() > index )
            {
                EntityInstanceImpl returnInst = null;
                search = search.getParent();
                if ( lst.indexOf( search.getHierIndex() ) >= 0 ) {
                   // we have a problem (infinite loop in process) ... get to the top and display the hierarchy
                   while ( search.getParent() != null ) {
                      search = search.getParent();
                   }

                   while ( search != null ) {
                      EntityInstanceImpl parent = search.getParent();
                      getTask().log().info( "Entity Index: %d   Parent Index: %d", search.getHierIndex(), parent == null ? -1 : parent.getHierIndex() );
                      if ( search.getHierIndex() == index ) {
                         returnInst = search;
                      }

                      search = search.getNextHier();
                   }

                   return returnInst;
                }
                else {
                   lst.add( search.getHierIndex() );
                }
                continue;
            }

            search = search.getNextHier();
        }

        return search;
    }
*/

    EntityInstanceImpl findByHierIndex( long index )
    {
        EntityInstanceImpl search = this;
        while ( search != null )
        {
            EntityInstanceImpl next = search.getNextTwin();
            while ( next != null && next.getHierIndex() <= index ) {
                search = next;
                next = search.getNextTwin();
            }

            if ( search.getHierIndex() == index )
                return search;

            if ( next != null && next.getHierIndex() > index )
            {
               while ( (search = search.getNextHier()) != null )
               {
                   if ( search.getHierIndex() == index )
                   {
                       return search;
                   }
                }
            }
            else
            {
               search = search.getNextHier();
            }
        }

        // Try brute force.
        getTask().log().info( "Searching for Entity Index: %d  by brute force", index );
        for ( search = this; search != null; search = search.getNextHier() )
        {
            if ( search.getHierIndex() == index )
                return search;
        }

        return null;
    }

    void copyAttributes(EntityInstanceImpl source, boolean copyPersist, boolean copyUpdateFlags)
    {
        attributeList.copyAttributes( getTask(), source.attributeList, copyPersist, copyUpdateFlags );
    }

    /**
     * Loops through all the direct EI children of 'this'.
     *
     * NOTE: this will *NOT* load lazy-load entities if they haven't already
     * been loaded.
     *
     * @param allowHidden
     * @return
     */
    EntityIterator<EntityInstanceImpl> getDirectChildren()
    {
        return getDirectChildren( false );
    }

    /**
     * Loops through all the direct EI children of 'this'.
     *
     * NOTE: this will *NOT* load lazy-load entities if they haven't already
     * been loaded.
     *
     * @param allowHidden
     * @return
     */
    EntityIterator<EntityInstanceImpl> getDirectChildren( boolean allowHidden )
    {
        return new IteratorBuilder(getObjectInstance())
                        .forDirectChildren( this )
                        .allowHidden( allowHidden )
                        .setLazyLoad( false )
                        .build();
    }

    @Override
    public EntityIterator<? extends EntityInstance> getChildren( ViewEntity childViewEntity )
    {
        return new IteratorBuilder(getObjectInstance())
                        .allowHidden()
                        .withScoping( this )
                        .forViewEntity( childViewEntity )
                        .build();
    }

    @Override
    public EntityIterator<? extends EntityInstance> getChildren( ViewEntity childViewEntity,
                                                                 boolean    allowHidden )
    {
        return new IteratorBuilder(getObjectInstance())
                        .allowHidden( false )
                        .withScoping( this )
                        .forViewEntity( childViewEntity )
                        .build();
    }

    private void addAllLinks( HashSet<EntityInstanceImpl> allEntities )
    {
        allEntities.add( this );
        allEntities.add( parentInstance );
        allEntities.add( nextTwinInstance );
        allEntities.add( nextHierInstance );
        allEntities.add( prevHierInstance );
        allEntities.add( prevTwinInstance );
        allEntities.add( prevVersion );
        allEntities.add( nextVersion );
    }

    void countAllVersions( HashSet<EntityInstanceImpl> allEntities )
    {
        for ( EntityInstanceImpl ei = this; ei != null; ei = ei.prevVersion )
            ei.addAllLinks( allEntities );

        for ( EntityInstanceImpl ei = this.nextVersion; ei != null; ei = ei.nextVersion )
            ei.addAllLinks( allEntities );
    }

    /**
     * Iterates through all the hier children of this entity instance.
     *
     * @return
     */
    EntityIterator<EntityInstanceImpl> getChildrenHier()
    {
        return getChildrenHier( false );
    }

    /**
     * Loop through the children.  Will not load lazy entities.
     *
     * @param includeParent
     * @param excludeHidden
     * @return
     */
    EntityIterator<EntityInstanceImpl> getChildrenHier( boolean includeParent, boolean excludeHidden )
    {
        IteratorBuilder iter = new IteratorBuilder(getObjectInstance()).withScoping( this ).setLazyLoad( false );
        if ( ! excludeHidden )
            iter.allowHidden();

        EntityIterator<EntityInstanceImpl> iterable = iter.build();
        if ( ! includeParent )
            iterable.next();

        return iterable;
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
        return getChildrenHier( includeParent, true );
    }

    /**
     * Returns a string representation of the key values of this entity.  If all keys are
     * null then returns null.
     *
     * @return
     */
    @Override
    public String getKeyString()
    {
        boolean nonnull = false;  // We'll assume all keys are null until we find one that isn't.
        StringBuilder builder = new StringBuilder();

        for ( ViewAttribute key : getViewEntity().getKeys() )
        {
            AttributeValue attr = getInternalAttribute( key );
            if ( ! attr.isNull( getTask(), key ) )
                nonnull = true;
            builder.append( attr.toString() ).append( "|" );
        }

        if ( ! nonnull )
            return null;

        return builder.toString();
    }

    /**
     * Returns the value of all the keys.
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append( getViewEntity().toString() ).append( " Keys: " );
        for ( ViewAttribute key : getViewEntity().getKeys() )
        {
            builder.append( key.getName() ).append( "=" );
            builder.append( getInternalAttribute( key ).toString() ).append( "; " );
        }

        return builder.toString();
    }

    @Override
    public EntityIterator<? extends EntityInstance> getChildren(String childEntityName)
    {
        ViewEntity childViewEntity = getViewOd().getViewEntity( childEntityName );
        if ( childViewEntity.getParent() != getViewEntity() )
            throw new ZeidonException( "%s is not a direct child of %s", childEntityName, getViewEntity() );

        return getChildren( childViewEntity );
    }

    @Override
    public int setMatchingAttributesByName(View source, String sourceViewEntity, EnumSet<SetMatchingFlags> control)
    {
        return setMatchingAttributesByName( source, source.getViewOd().getViewEntity( sourceViewEntity ), control );
    }

    @Override
    public int setMatchingAttributesByName(View source, ViewEntity sourceViewEntity, EnumSet<SetMatchingFlags> control)
    {
        return setMatchingAttributesByName( source.cursor( sourceViewEntity ), control );
    }

    @Override
    public int setMatchingAttributesByName(EntityInstance sourceInstance)
    {
        return setMatchingAttributesByName( sourceInstance, SetMatchingFlags.DEFAULT );
    }

    @Override
    public int setMatchingAttributesByName(EntityInstance sourceInstance, EnumSet<SetMatchingFlags> control)
    {
        ViewEntity sourceViewEntity = sourceInstance.getViewEntity();

        // Loop through the target attributes and set their value from the source entity.
        for ( ViewAttribute targetAttr : this.getViewEntity().getAttributes() )
        {
            if ( targetAttr.isHidden() )
                continue;

            if ( targetAttr.isDerived() )
                continue;

            if ( ! targetAttr.isUpdate() )
                continue;

            // If target entity was not just created (this means that the entity
            // has been committed to the database) then the attribute cannot
            // be updated if it is a key.
            if ( ! this.isCreated() && targetAttr.isKey() )
                continue;

            if ( targetAttr.isKey() && ! control.contains( SetMatchingFlags.fSET_KEYS ) )
                continue;

            // Check to see if user wants to over-write non-null values.
            if ( ! control.contains( SetMatchingFlags.fSET_NOTNULL ) )
            {
                // The user does NOT want attributes in the target entity that
                // already have values (i.e. they are not null) to be over-written
                // with values from the source entity.  Check the target entity's
                // value.  If it is not null, then continue looping.
                // We can pass null for the view because we've already determined that
                // target is not a derived attribute.
                if ( ! getInternalAttribute( targetAttr ).isNull( getTask(), targetAttr ) )
                    continue;
            }

            ViewAttribute sourceAttr = sourceViewEntity.getAttribute( targetAttr.getName(), false );
            if ( sourceAttr == null )
                continue;  // No matching attribute by name.

            // Source attr cannot be hidden or derived either.
            if ( sourceAttr.isHidden() || sourceAttr.isDerived() )
                continue;

            // Ignore if both are null.
            if ( getInternalAttribute( targetAttr ).isNull( getTask(), targetAttr ) && sourceInstance.isAttributeNull( sourceAttr ) )
                continue;

            if ( control.contains( SetMatchingFlags.fSET_SRCNOTNULL ) )
            {
                // User doesn't want NULL source attributes to be copied.
                if ( sourceInstance.isAttributeNull( sourceAttr ) )
                    continue;
            }

            Object sourceValue = sourceInstance.getInternalAttributeValue( sourceAttr );
            setAttribute( targetAttr, sourceValue );
        } // for each target view attribute...

        return 0;
    }

    @Override
    public boolean isDbhCreated()
    {
        return dbhCreated;
    }

    @Override
    public boolean isDbhDeleted()
    {
        return dbhDeleted;
    }

    @Override
    public boolean isDbhExcluded()
    {
        return dbhExcluded;
    }

    @Override
    public boolean isDbhUpdated()
    {
        return dbhUpdated;
    }

    @Override
    public boolean isDbhIncluded()
    {
        return dbhIncluded;
    }

    @Override
    public boolean isDbhNeedsInclude()
    {
        return dbhNeedsInclude;
    }

    @Override
    public boolean isDbhSeqUpdated()
    {
        return dbhSeqUpdated;
    }

    @Override
    public boolean isDbhGenKeyNeeded()
    {
        return dbhGenKeyNeeded;
    }

    @Override
    public boolean isDbhNoGenKey()
    {
        return dbhNoGenKey;
    }

    @Override
    public boolean isDbhForeignKey()
    {
        return dbhForeignKey;
    }

    /**
     * Makes sure the attribute can be updated.
     *
     * @param viewAttribute
     */
    private void validateUpdateAttribute( ViewAttribute viewAttribute )
    {
        // If this EI is versioned they we'll assume that it's ok because the versioning
        // code should have already verified that none of the other instances are versioned.
        if ( isVersioned() )
            return;

        // If the attribute is derived or work, then we do not need to check if the
        // attribute can be updated.
        if ( viewAttribute.isDerived() )
        	return;

        // If the attribute is Persistent, then it is an attribute from the ER.
        // Otherwise it's a work attribute and can be updated even if the
        // object is read only.
        if ( ! viewAttribute.isPersistent() )
        	return;

        if ( ! viewAttribute.isUpdate() )
            throw new ZeidonException( "Attribute is defined as read-only" )
                                .prependViewAttribute( viewAttribute );

        // If the entity is derived or work, then we do not need to check if the
        // attribute can be updated.
        if ( getViewEntity().isDerived() || getViewEntity().isDerivedPath() )
        	return;

        if ( getObjectInstance().isReadOnly() )
            throw new ZeidonException( "Object Instance is read-only" )
                                .prependViewEntity( getViewEntity() );

        for ( EntityInstanceImpl linked : this.getLinkedInstances() )
        {
            if ( ! this.temporalVersionMatch( linked ) )
                throw new TemporalEntityException( this,
                            "Attempting to update an entity that is linked to a versioned instance" );
        }
    }

    /**
     * Validate the context name.  If the context name is null or "", returns the default context.
     * @param viewAttribute
     * @param contextName
     * @return
     */
    private String checkContextName( ViewAttribute viewAttribute, String contextName )
    {
        Domain domain = viewAttribute.getDomain();
        return domain.getContext( getTask(), contextName ).getName();
    }

    @Override
    public EntityInstance setAttribute(String attributeName, Object value, String contextName) throws InvalidAttributeValueException
    {
        return setAttribute( getViewEntity().getAttribute( attributeName ), value, contextName );
    }

    /**
     * Validates that setting this attribute is authorized and then calls attrib.set()
     * to perform the set.
     */
    @Override
    public EntityInstance setAttribute(ViewAttribute viewAttribute, Object value, String contextName) throws InvalidAttributeValueException
    {
        if ( viewAttribute.isHidden() )
            throw new UnknownViewAttributeException( getViewEntity(), viewAttribute.getName() );

        contextName = checkContextName( viewAttribute, contextName );

        validateUpdateAttribute( viewAttribute );

        try
        {
            AttributeValue attrib = getInternalAttribute( viewAttribute );
            Object oldValue = attrib.getInternalValue();
            if ( attrib.set( getTask(), viewAttribute, value, contextName ) )
            {
                if ( ! viewAttribute.isDerived() )
                    setUpdated( true, true, viewAttribute.isPersistent() );

                updateHashKeyAttributeToMap( viewAttribute, oldValue );
            }
        }
        catch( Throwable t )
        {
            throw ZeidonException.wrapException( t ).prependViewAttribute( viewAttribute );
        }

        return this;
    }

    void setAttributeUpdated( ViewAttribute viewAttribute, boolean updated )
    {
        AttributeValue attrib = getInternalAttribute( viewAttribute );
        attrib.setUpdated( updated );
    }

    @Override
    public EntityInstance setAttribute( ViewAttribute viewAttribute, Object value)
    {
        return setAttribute( viewAttribute, value, null );
    }

    @Override
    public EntityInstance setAttribute(String attributeName, Object value)
    {
        return setAttribute( getViewEntity().getAttribute( attributeName ), value );
    }

    @Override
    public EntityInstance setAttributeFromAttribute( String tgtAttributeName,
                                                     View srcView,
                                                     String srcEntityName,
                                                     String srcAttributeName )
    {
        ViewAttribute tgtViewAttribute = getViewEntity().getAttribute( tgtAttributeName );
        Domain tgtDomain = tgtViewAttribute.getDomain();
        Object source;
        // If the target is a string, we'll ask the source to convert its value to a
        // string.  This is necessary for copying Dates to strings. Should we do this with other data types?
        if ( tgtDomain.getDataType() == InternalType.STRING )
            source = srcView.cursor( srcEntityName ).getStringFromAttribute( srcAttributeName );
        else
            source = srcView.cursor( srcEntityName ).getInternalAttributeValue( srcAttributeName );

        return setAttribute( tgtViewAttribute, source );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#setInternalAttributeValue(com.quinsoft.zeidon.objectdefinition.ViewAttribute, java.lang.Object)
     */
    @Override
    public EntityInstance setInternalAttributeValue(ViewAttribute viewAttribute, Object value, boolean setIncremental) throws InvalidAttributeValueException
    {
        try
        {
            AttributeValue attrib = getInternalAttribute( viewAttribute );
            Object oldValue = attrib.getInternalValue();

            // The value is internal value but it may be a different data type.  Ask the domain to convert it and validate it.
            Object newValue = attrib.convertInternalValue( getTask(), viewAttribute, value );
            if ( attrib.setInternalValue( getTask(), viewAttribute, newValue, setIncremental ) )
            {
                if ( setIncremental && ! viewAttribute.isDerived() )
                    setUpdated( true, true, viewAttribute.isPersistent() );

                updateHashKeyAttributeToMap( viewAttribute, oldValue );
            }
        }
        catch( Throwable t )
        {
            throw ZeidonException.wrapException( t ).prependViewAttribute( viewAttribute );
        }

        return this;
    }

    AttributeHashKeyMap getAttributeHashkeyMap()
    {
        return attributeHashkeyMap;
    }

    /**
     * Get the correct AttributeHaskeyMap depending on the type.
     *
     * @param viewAttribute
     * @return
     */
    AttributeHashKeyMap getAttributeHashkeyMap( ViewAttribute viewAttribute )
    {
        if ( viewAttribute.getHashKeyType() == AttributeHashKeyType.GLOBAL )
            return getObjectInstance().getAttributeHashkeyMap();

        if ( viewAttribute.getHashKeyType() == AttributeHashKeyType.UNDER_PARENT )
        {
            EntityInstanceImpl parent = findMatchingParent( viewAttribute.getHashKeyParent() );
            return parent.getAttributeHashkeyMap();
        }

        throw new ZeidonException("Unsupported AttributeHashKeyType %s", viewAttribute.getHashKeyType() );
    }

    /**
     * We've updated an attribute.  Check to see if we need to update the attribute hashkey map.
     *
     * @param viewAttribute
     * @param oldValue
     */
    void updateHashKeyAttributeToMap( ViewAttribute viewAttribute, Object oldValue )
    {
        if ( viewAttribute.getHashKeyType() == AttributeHashKeyType.NONE )
            return; // Nothing to do.

        getAttributeHashkeyMap( viewAttribute ).updateHashKey( oldValue, viewAttribute, this );
    }

    private void addHashKeyAttributeToMap( ViewAttribute viewAttribute )
    {
        if ( viewAttribute.getHashKeyType() == AttributeHashKeyType.NONE )
            return; // Nothing to do.

        getAttributeHashkeyMap( viewAttribute ).addHashKey( viewAttribute, this );
    }

    private void removeHashKeyAttributeToMap( ViewAttribute viewAttribute )
    {
        if ( viewAttribute.getHashKeyType() == AttributeHashKeyType.NONE )
            return; // Nothing to do.

        getAttributeHashkeyMap( viewAttribute ).removeHashKey( viewAttribute, this );
    }

    private void removeAllHashKeyAttributes()
    {
        if ( getViewEntity().getHashKeyAttributes() == null )
            return;

        for ( ViewAttribute viewAttribute : getViewEntity().getHashKeyAttributes() )
            removeHashKeyAttributeToMap( viewAttribute );
    }

    void addAllHashKeyAttributes()
    {
        if ( getViewEntity().getHashKeyAttributes() == null )
            return;

        for ( ViewAttribute viewAttribute : getViewEntity().getHashKeyAttributes() )
            addHashKeyAttributeToMap( viewAttribute );
    }

    String getStringFromAttribute( View view, ViewAttribute viewAttribute )
    {
        return getAttribute( viewAttribute, view ).getString();
    }

    @Override
    public String getStringFromAttribute( ViewAttribute viewAttribute )
    {
        return getAttribute( viewAttribute, null ).getString();
    }

    @Override
    public String getStringFromAttribute(String attributeName)
    {
        return getAttribute( attributeName ).getString();
    }

    @Override
    public String getStringFromAttribute( ViewAttribute viewAttribute, String contextName )
    {
        return getAttribute( viewAttribute ).getString( contextName );
    }

    String getStringFromAttribute( View view, ViewAttribute viewAttribute, String contextName )
    {
        return getAttribute( viewAttribute, view ).getString( contextName );
    }

    @Override
    public String getStringFromAttribute(String attributeName, String contextName)
    {
        return getAttribute( attributeName ).getString( contextName );
    }

    Integer getIntegerFromAttribute( View view, ViewAttribute viewAttribute)
    {
        executeDerivedOper( view, viewAttribute );
        AttributeValue attrib = getInternalAttribute( viewAttribute );
        return attrib.getInteger( getTask(), viewAttribute );
    }

    @Override
    public Integer getIntegerFromAttribute(ViewAttribute viewAttribute)
    {
        return getIntegerFromAttribute( null, viewAttribute );
    }

    @Override
    public Integer getIntegerFromAttribute(String attributeName)
    {
        return getIntegerFromAttribute( getViewEntity().getAttribute( attributeName ) );
    }

    Integer getIntegerFromAttribute( View view, ViewAttribute viewAttribute, String contextName )
    {
        contextName = checkContextName( viewAttribute, contextName );
        executeDerivedOper( view, viewAttribute );
        AttributeValue attrib = getInternalAttribute( viewAttribute );
        return attrib.getInteger( getTask(), viewAttribute, contextName );
    }

    @Override
    public Integer getIntegerFromAttribute(ViewAttribute viewAttribute, String contextName )
    {
        return getIntegerFromAttribute( null, viewAttribute, contextName );
    }

    @Override
    public Integer getIntegerFromAttribute(String attributeName, String contextName )
    {
        return getIntegerFromAttribute( getViewEntity().getAttribute( attributeName ), contextName );
    }

    Double getDoubleFromAttribute( View view, ViewAttribute viewAttribute)
    {
        executeDerivedOper( view, viewAttribute );
        AttributeValue attrib = getInternalAttribute( viewAttribute );
        return attrib.getDouble( getTask(), viewAttribute );
    }

    @Override
    public Double getDoubleFromAttribute(ViewAttribute viewAttribute)
    {
        return getDoubleFromAttribute( null, viewAttribute );
    }

    @Override
    public Double getDoubleFromAttribute(String attributeName)
    {
        return getDoubleFromAttribute( getViewEntity().getAttribute( attributeName ) );
    }

    Double getDoubleFromAttribute( View view, ViewAttribute viewAttribute, String contextName )
    {
        contextName = checkContextName( viewAttribute, contextName );
        executeDerivedOper( view, viewAttribute );
        AttributeValue attrib = getInternalAttribute( viewAttribute );
        return attrib.getDouble( getTask(), viewAttribute, contextName );
    }

    @Override
    public Double getDoubleFromAttribute(ViewAttribute viewAttribute, String contextName )
    {
        return getDoubleFromAttribute( null, viewAttribute, contextName );
    }

    @Override
    public Double getDoubleFromAttribute(String attributeName, String contextName )
    {
        return getDoubleFromAttribute( getViewEntity().getAttribute( attributeName ), contextName );
    }

    DateTime getDateTimeFromAttribute( View view, ViewAttribute viewAttribute )
    {
        executeDerivedOper( view, viewAttribute );
        AttributeValue attrib = getInternalAttribute( viewAttribute );
        return attrib.getDateTime( getTask(), viewAttribute );
    }

    @Override
    public DateTime getDateTimeFromAttribute(ViewAttribute viewAttribute)
    {
        return getDateTimeFromAttribute( null, viewAttribute );
    }

    @Override
    public DateTime getDateTimeFromAttribute(String attributeName)
    {
        return getDateTimeFromAttribute( getViewEntity().getAttribute( attributeName ) );
    }

    DateTime getDateTimeFromAttribute( View view, ViewAttribute viewAttribute, String contextName )
    {
        contextName = checkContextName( viewAttribute, contextName );
        executeDerivedOper( view, viewAttribute );
        AttributeValue attrib = getInternalAttribute( viewAttribute );
        return attrib.getDateTime( getTask(), viewAttribute, contextName );
    }

    @Override
    public DateTime getDateTimeFromAttribute(ViewAttribute viewAttribute, String contextName )
    {
        return getDateTimeFromAttribute( null, viewAttribute, contextName );
    }

    @Override
    public DateTime getDateTimeFromAttribute(String attributeName, String contextName )
    {
        return getDateTimeFromAttribute( getViewEntity().getAttribute( attributeName ), contextName );
    }

    Blob getBlobFromAttribute( View view, ViewAttribute viewAttribute )
    {
        executeDerivedOper( view, viewAttribute );
        AttributeValue attrib = getInternalAttribute( viewAttribute );
        return attrib.getBlob( getTask(), viewAttribute );
    }

    @Override
    public Blob getBlobFromAttribute(String attributeName)
    {
        ViewAttribute viewAttribute = getViewEntity().getAttribute( attributeName );
        return getBlobFromAttribute( viewAttribute );
    }

    @Override
    public Blob getBlobFromAttribute( ViewAttribute viewAttribute )
    {
        return getBlobFromAttribute( null, viewAttribute );
    }

    @Override
    public boolean isAttributeNull(String attributeName)
    {
        return isAttributeNull( getViewEntity().getAttribute( attributeName ) );
    }

    boolean isAttributeNull( View view, ViewAttribute viewAttribute )
    {
        executeDerivedOper( view, viewAttribute );
        AttributeValue attrib = getInternalAttribute( viewAttribute );
        return attrib.isNull( getTask(), viewAttribute  );
    }

    @Override
    public boolean isAttributeNull(ViewAttribute viewAttribute )
    {
        return isAttributeNull( null, viewAttribute );
    }

    @Override
    public boolean isAttributeUpdated(ViewAttribute viewAttribute )
    {
        AttributeValue attrib = getInternalAttribute( viewAttribute );
        return attrib.isUpdated();
    }

    @Override
    public Object getInternalAttributeValue( ViewAttribute viewAttribute )
    {
        return getInternalAttributeValue( null, viewAttribute );
    }

    Object getInternalAttributeValue( ViewImpl view, ViewAttribute viewAttribute )
    {
        executeDerivedOper( view, viewAttribute );
        return getInternalAttribute( viewAttribute ).getInternalValue( );
    }

    @Override
    public Object getInternalAttributeValue(String attributeName)
    {
        return  getInternalAttributeValue( getViewEntity().getAttribute( attributeName ) );
    }

    int compareAttribute( View view, ViewAttribute viewAttribute, Object value)
    {
        executeDerivedOper( view, viewAttribute );
        return getInternalAttribute( viewAttribute ).compare( getTask(), viewAttribute, value );
    }

    @Override
    public int compareAttribute(String attributeName, Object value)
    {
        return compareAttribute( getViewEntity().getAttribute( attributeName ), value );
    }

    @Override
    public int compareAttribute(ViewAttribute viewAttribute, Object value)
    {
        return compareAttribute( null, viewAttribute, value );
    }

    @Override
    public int compareAttribute(String attributeName, EntityInstance entityInstance, String attributeName2)
    {
        return compareAttribute( getViewEntity().getAttribute( attributeName ),
                                 entityInstance, entityInstance.getViewEntity().getAttribute( attributeName2 ) );
    }

    int compareAttribute( View view, ViewAttribute viewAttribute, EntityInstance entityInstance, ViewAttribute viewAttribute2 )
    {
        executeDerivedOper( view, viewAttribute );
        Object value = entityInstance.getInternalAttributeValue( viewAttribute2 );
        return compareAttribute( viewAttribute, value );
    }

    @Override
    public int compareAttribute(ViewAttribute viewAttribute, EntityInstance entityInstance, ViewAttribute viewAttribute2 )
    {
        return compareAttribute( null, viewAttribute, entityInstance, viewAttribute2 );
    }

    Object addToAttribute( View view, ViewAttribute viewAttribute, Object value )
    {
        executeDerivedOper( view, viewAttribute );
        AttributeValue attrib = getInternalAttribute( viewAttribute );
        return attrib.addToAttribute( getTask(), viewAttribute, value );
    }

    @Override
    public Object addToAttribute( String attributeName, Object value )
    {
        return addToAttribute( getViewEntity().getAttribute( attributeName ), value );
    }

    @Override
    public Object addToAttribute( ViewAttribute viewAttribute, Object value )
    {
        return addToAttribute( null, viewAttribute, value );
    }

    Object multiplyAttribute( View view, ViewAttribute viewAttribute, Object value )
    {
        // TODO: I don't think this is updating the entity flags.
        executeDerivedOper( view, viewAttribute );
        AttributeValue attrib = getInternalAttribute( viewAttribute );
        return attrib.multiplyAttribute( getTask(), viewAttribute, value );
    }

    @Override
    public Object multiplyAttribute( String attributeName, Object value )
    {
        return multiplyAttribute( getViewEntity().getAttribute( attributeName ), value );
    }

    @Override
    public Object multiplyAttribute( ViewAttribute viewAttribute, Object value )
    {
        return multiplyAttribute( null, viewAttribute, value );
    }

    /**
     * Temporary object used to create a temporal subobject.
     *
     * @author DGC
     *
     */
    private static class TemporalVersionCreator
    {
        private final EntityInstanceImpl originalRoot;
        private final long version;
        private final TaskImpl task;

        /**
         * Keeps track of all the new entity instances.
         */
        private final List<EntityInstanceImpl> newInstanceList;

        private TemporalVersionCreator(TaskImpl task, EntityInstanceImpl originalRoot)
        {
            this.originalRoot = originalRoot;
            this.task = task;
            version = originalRoot.getTask().getNextVersionNumber();
            newInstanceList = new ArrayList<EntityInstanceImpl>();
        }

        private EntityInstanceImpl createTemporalVersion()
        {
            final EntityInstanceImpl newRoot = createTemporalSubobject( originalRoot );
            originalRoot.getObjectInstance().incrementVersionedCount();
            assert ! originalRoot.getLinkedInstances().contains( newRoot ) :
                "New temporal entity should not be part of prev's linked EIs";
            return newRoot;
        }

        private EntityInstanceImpl createTemporalSubobject( EntityInstanceImpl root )
        {
            // Create temporal entities for the root and all its children.
            final EntityInstanceImpl newRoot = createTemporalEntity( root );
            newRoot.setVersionStatus( VersionStatus.UNACCEPTED_ROOT );
            for ( final EntityInstanceImpl ei : root.getChildrenHier( false, true ) )
                createTemporalEntity( ei );

            // We've created all the new entity versions but their prev/next pointers
            // are pointing to the original instances.  Go through them all and set
            // their pointers to the new ones.

            // Now set the original EI's to have a new version.
            for ( final EntityInstanceImpl ei : newInstanceList )
            {
                EntityInstanceImpl prevVsn = ei.prevVersion;
                prevVsn.setNextVersion( ei );
                assert prevVsn.getViewEntity() == ei.getViewEntity();
            }

            // Now we can use getLatestVersion to set the other pointers.
            for ( EntityInstanceImpl ei : newInstanceList )
            {
                // Each of the 'get' methods (ie. getNextHier) will use getLatestVersion to get the latest version.
                ei.copyAllPointers( ei );
            }

            return newRoot;
        }

        /**
         * Creates a temporal entity for prevVersion and copies attributes and flag information.
         * The next/prev pointers will be the same as prevVersion.
         *
         * This does not set prevVersionEi.nextVersion.  This will be done after all of the new
         * versions are created.
         *
         * @param prevVersionEi
         * @return
         */
        private EntityInstanceImpl createTemporalEntity( final EntityInstanceImpl prevVersionEi )
        {
            // We're going to replace the current version in the attribute hashkey map with
            // a new version.  The hashkey values will be added as we copy attribute values
            // so all we need to do is remove the old ones.
            prevVersionEi.removeAllHashKeyAttributes();

            final EntityInstanceImpl newInstance = new EntityInstanceImpl( prevVersionEi.getObjectInstance(),
                                                                           prevVersionEi.getViewEntity(),
                                                                           prevVersionEi.getParent() );
            newInstance.versionNumber = version;
            newInstance.setVersionStatus( VersionStatus.UNACCEPTED );

            // Keep track of all the new instances.  When we're all done we'll reset all
            // the next/prev pointers to the other new instances.
            newInstanceList.add( newInstance );

            // Copy the next/prev pointers from the previous version.  When we're done creating
            // new instances we'll go back and reset them to the new version.  We don't set
            // prevVersion.nextVersion now because if there's an error we won't have to go
            // back and change them.
            newInstance.copyAllPointers( prevVersionEi );
            newInstance.prevVersion = prevVersionEi;
            newInstance.copyFlags( prevVersionEi );

            // Check to see if this instance is linked to another instance that
            // is versioned.  If one is found we'll copy its persistent attributes to newInstance.
            // We can do this by checking to see if any linked instances have the same version number.
            AttributeListInstance linkedAttributeList = null;
            for ( EntityInstanceImpl linked : prevVersionEi.getLinkedInstances() )
            {
                if ( linked.versionNumber == version )
                {
                    linkedAttributeList = linked.attributeList;
                    break;
                }
            }

            // This adds the newInstance to the linked instances list.
            newInstance.attributeList =
                AttributeListInstance.newTemporalAttributeList( task,
                                                                newInstance,
                                                                prevVersionEi,
                                                                prevVersionEi.attributeList, linkedAttributeList );

            return newInstance;
        }
    } // private static class TemporalVersionCreator

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#logEntity()
     */
    @Override
    public void logEntity()
    {
        logEntity( true, 0 );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#logEntity(boolean)
     */
    @Override
    public void logEntity(boolean logChildren)
    {
        logEntity( logChildren, 0 );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#logChildren(boolean, int)
     */
    @Override
    public void logEntity(boolean logChildren, int indentN)
    {
        ViewEntity viewEntity = getViewEntity();
        String indent = StringUtils.repeat( "    ", indentN );

        StringBuilder flagStr = new StringBuilder();
        if ( isUpdated() )
            flagStr.append( " Updated" );
        if ( isCreated() )
            flagStr.append( " Created" );
        if ( isIncluded() )
            flagStr.append( " Included" );
        if ( isDeleted() )
            flagStr.append( " Deleted" );
        if ( isExcluded() )
            flagStr.append( " Excluded" );

        getTask().log().info( "%s[%s] %s (%x:%d)", indent, viewEntity.getName(), flagStr, hashCode(), getEntityKey() );
        for ( ViewAttribute viewAttribute : getNonNullAttributeList() )
        {
            AttributeValue attrib = getInternalAttribute( viewAttribute );

            String value = attrib.getString( getTask(), viewAttribute );
//            if ( ( flags & View.DISPLAY_EMPTY_ATTRIBS ) == 0 && StringUtils.isBlank( value ) )
//                continue;
//
//            if ( ( flags & View.DISPLAY_HIDDEN ) == 0 && viewAttribute.isHidden() )
//                continue;

            getTask().log().info( "%s   (%s)%s: %s", indent, attrib.isUpdated() ? "#" : "~", viewAttribute.getName(), value );
        }

        if ( logChildren )
        {
            for ( EntityInstance ei : getDirectChildren() )
                ei.logEntity( logChildren, indentN + 1 );
        }
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
     * @see com.quinsoft.zeidon.EntityInstance#getEntityInstance()
     */
    @Override
    public EntityInstance getEntityInstance()
    {
        return this;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#getHierPosition()
     */
    @Override
    public long getHierPosition()
    {
        long position = 0;
        for ( EntityInstanceImpl ei = this.getPrevHier(); ei != null; ei = ei.getPrevHier() )
        {
            if ( ! ei.isHidden() )
                position++;
        }

        return position;
    }

    @Override
    public long getPosition()
    {
        long position = 0;
        for ( EntityInstanceImpl ei = getPrevTwin(); ei != null; ei = ei.getPrevTwin() )
        {
            if ( ! ei.isHidden() )
                position++;
        }

        return position;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#isLinked(com.quinsoft.zeidon.EntityInstance)
     */
    @Override
    public boolean isLinked( EntityInstance ei )
    {
        if ( ei == null )
            return false;

        EntityInstanceImpl temp = (EntityInstanceImpl) ei.getEntityInstance();
        return temp.attributeList.isLinkedWith( this.attributeList );
    }

    /**
     * @return The total number of non-hidden twins for this entity, including this one.
     */
    int getTwinCount()
    {
        int count = 0;
        for ( EntityInstanceImpl search = this; search != null; search = search.getNextTwin() )
        {
            if ( ! search.isHidden() )
                count++;
        }

        for ( EntityInstanceImpl search = this.getPrevTwin(); search != null; search = search.getPrevTwin() )
        {
            if ( ! search.isHidden() )
                count++;
        }

        return count;

    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#setIncrementalFlags(java.util.EnumSet)
     */
    @Override
    public EntityInstanceImpl setIncrementalFlags( EnumSet<IncrementalEntityFlags> flags )
    {
        created  = flags.contains( IncrementalEntityFlags.fCREATED );
        deleted  = flags.contains( IncrementalEntityFlags.fDELETED );
        updated  = flags.contains( IncrementalEntityFlags.fUPDATED );
        included = flags.contains( IncrementalEntityFlags.fINCLUDED );
        excluded = flags.contains( IncrementalEntityFlags.fEXCLUDED);
        hidden = deleted || excluded;

        return this;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#setIncrementalFlags(com.quinsoft.zeidon.standardoe.IncrementalEntityFlags[])
     */
    @Override
    public EntityInstance setIncrementalFlags( IncrementalEntityFlags flag )
    {
        return setIncrementalFlags( EnumSet.of( flag ) );
    }

    /**
     * Return the set of entities that have been lazy loaded.
     *
     * This needs to be synchronized because two different views could be
     * trying to access the same OI at the same time.
     *
     * @return the entitiesLoadedLazily
     */
    synchronized Set<ViewEntity> getEntitiesLoadedLazily()
    {
        // We should only get here if this entity instance can have children that can be
        // lazy loaded.
        assert getViewEntity().getLazyLoadConfig().hasLazyLoadChild()
                            : "Attempting to get lazy load set for entity without LazyLoad children";

        if ( entitiesLoadedLazily == null )
            entitiesLoadedLazily = new HashSet<ViewEntity>();

        return entitiesLoadedLazily;
    }

    @Override
    public AttributeInstance getAttribute( String attributeName )
    {
        ViewAttribute viewAttribute = getViewEntity().getAttribute( attributeName );
        return getAttribute( viewAttribute, null );
    }

    @Override
    public AttributeInstanceImpl getAttribute( ViewAttribute viewAttribute )
    {
        return getAttribute( viewAttribute, null );
    }

    private synchronized AttributeInstanceImpl getAttribute( ViewAttribute viewAttribute, View view )
    {
        if ( attributeInstanceMap == null )
            attributeInstanceMap = new HashMap<ViewAttribute, AttributeInstanceImpl>();

        if ( ! attributeInstanceMap.containsKey( viewAttribute ) )
        {
            AttributeInstanceImpl attributeInstance =
                    new AttributeInstanceImpl( viewAttribute,
                                               getInternalAttribute( viewAttribute ),
                                               this );
            attributeInstanceMap.put( viewAttribute, attributeInstance );
        }

        AttributeInstanceImpl attrInstance = attributeInstanceMap.get( viewAttribute );
        if ( view != null )
            attrInstance.setView( view );

        return attrInstance;
    }

    static private synchronized ViewEntityLinkInfo getViewEntityLinkInfo( ViewEntity viewEntity )
    {
        ViewEntityLinkInfo info = viewEntity.getCacheMap( ViewEntityLinkInfo.class );
        if ( info != null )
            return info;

        info = new ViewEntityLinkInfo();
        viewEntity.putCacheMap( ViewEntityLinkInfo.class, info );
        return info;
    }

    /**
     * This keeps track of whether two view entities can be validly linked together.
     */
    private static class ViewEntityLinkInfo
    {
        private final ConcurrentMap<ViewEntity, Boolean> mayBeLinked = new MapMaker().concurrencyLevel( 4 ).makeMap();
    }
}