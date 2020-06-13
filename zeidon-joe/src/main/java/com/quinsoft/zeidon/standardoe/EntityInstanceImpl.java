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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.collect.MapMaker;
import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.CompareEntityOptions;
import com.quinsoft.zeidon.CopyAttributesBuilder;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityConstraintType;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.EntityIterator;
import com.quinsoft.zeidon.EventNotification;
import com.quinsoft.zeidon.HiddenAttributeException;
import com.quinsoft.zeidon.MaxCardinalityException;
import com.quinsoft.zeidon.NullCursorException;
import com.quinsoft.zeidon.RequiredAttributeException;
import com.quinsoft.zeidon.RequiredEntityMissingException;
import com.quinsoft.zeidon.SetMatchingFlags;
import com.quinsoft.zeidon.SubobjectValidationException;
import com.quinsoft.zeidon.TemporalEntityException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.AttributeHashKeyType;
import com.quinsoft.zeidon.objectdefinition.DynamicAttributeDefConfiguration;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef.LinkValidation;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.KeyStringBuilder;

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
    private final EntityDef       entityDef;

    /**
     * Depth of this EI from the root.  If this is root then depth = 1.
     */
    private final int             depth;
    private final long            entityKey;

    private UUID                  uuid;
    private EntityInstanceImpl    parentInstance;
    private EntityInstanceImpl    prevHierInstance;
    private EntityInstanceImpl    nextHierInstance;
    private EntityInstanceImpl    prevTwinInstance;
    private EntityInstanceImpl    nextTwinInstance;
    private boolean updated  = false;
    private boolean deleted  = false;
    private boolean created  = false;
    private boolean included = false;
    private boolean hidden   = false;
    private boolean excluded = false;
    private boolean dropped  = false;

    /**
     * Will be true if any child entity has been updated.
     */
    private boolean childUpdated = false;

    /**
     * Map of persistent attributes. Linked entities will reference the same
     * persistentAttributes.
     *      Key = ER Attribute token
     */
    private Map<String, AttributeValue> persistentAttributes;

    /**
     * Map of work attributes. Every entity, even linked ones, will have their
     * own set of workAttributes.
     *      Key = ER Attribute token
     */
    private Map<String, AttributeValue> workAttributes;

    private static final LinkedInstances NOT_LINKED = new LinkedInstances();

    LinkedInstances linkedInstances2 = NOT_LINKED;

    /**
     * This keeps track of attribute hash keys that are under this EI.  Intended for use
     * by cursor.setFirst() processing.
     */
    private AttributeHashKeyMap attributeHashkeyMap;

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

    /**
     * If true then children for this EI were activated with qualification.
     * This means it's possible not all children were loaded and therefore
     * a delete could possibly cause data integrity issues.
     */
    private boolean incomplete;

    // ----------------------------------
    // Following flags used during commit to DB.
    // ----------------------------------
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
    boolean dbhNeedsCommit;
    boolean dbhLoaded;  // True if this EI was just loaded by the DBHandler.

    /**
     * This keeps track of child entity instances that have been loaded lazily.
     */
    private Set<EntityDef> entitiesLoadedLazily;

    static EntityInstanceImpl createEntity( ObjectInstance     oi,
                                            EntityInstanceImpl parent,
                                            EntityInstanceImpl relativeEntity,
                                            EntityDef          entityDef,
                                            CursorPosition     position )
    {
        // Create a new instance and initialize the attributes.
        EntityInstanceImpl newInstance =
            new EntityInstanceImpl( oi, entityDef, parent, relativeEntity, position );
        newInstance.setCreated( true );
        newInstance.workAttributes = new HashMap<>( entityDef.getWorkAttributeCount() );
        newInstance.persistentAttributes = new HashMap<>( entityDef.getPersistentAttributeCount() );

        return newInstance;
    }

    /**
     * Creates an empty entity instance.  This gives us a way to create uninitialized
     * entity instances used for temporary traversal.
     */
    EntityInstanceImpl( EntityDef entityDef )
    {
        super();
        this.entityDef = entityDef;
        objectInstance = null;
        entityKey = NumberUtils.LONG_ZERO;
        depth = -1;
    }

    /**
     * Creates an entity instance and adds it to the OI.
     *
     * @param objectInstance
     * @param entityDef
     * @param parentInstance
     * @param relativeInstance
     * @param position
     */
    EntityInstanceImpl(ObjectInstance        objectInstance,
                       EntityDef             entityDef,
                       EntityInstanceImpl    parentInstance,
                       EntityInstanceImpl    relativeInstance,
                       CursorPosition        position )
    {
        this( objectInstance, entityDef, parentInstance );
        insertInstance( objectInstance, parentInstance, relativeInstance, position, null );
    }

    /**
     * Creates an entity instance without inserting it into the chain.
     *
     * @param objectInstance
     * @param entityDef
     * @param parentInstance
     * @param initAttributes
     */
    EntityInstanceImpl(ObjectInstance        objectInstance,
                       EntityDef             entityDef,
                       EntityInstanceImpl    parentInstance )
    {
        assert objectInstance.getLodDef() == entityDef.getLodDef();
        this.objectInstance = objectInstance;
        this.entityDef = entityDef;

        // Set a unique identifier for this entity.  We use a number that's unique across
        // all tasks in case the entity is included into another task.
        this.entityKey = getTask().getObjectEngine().getNextObjectKey();

        // Copy some values from parentInstance if it isn't null.
        if ( parentInstance != null )
        {
            this.setParent( parentInstance );
            this.depth = parentInstance.getDepth() + 1;
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
            this.depth = 1;
        }

        workAttributes = new HashMap<>( getEntityDef().getWorkAttributeCount() );
    }

    void initializeDefaultAttributes(EnumSet<CreateEntityFlags> flags)
    {
        for ( AttributeDef attributeDef : getEntityDef().getAttributes( true ) )
        {
            // Don't initialize persistent attributes if flag is set.  Used when
            // loading an entity from the DB.  We'll always init work attributes.
            if ( flags.contains( CreateEntityFlags.fDONT_INITIALIZE_ATTRIBUTES ) )
            {
                if ( attributeDef.isPersistent() )
                    continue;
            }

            Domain domain = attributeDef.getDomain();
            if ( ! StringUtils.isBlank( attributeDef.getInitialValue() ) )
            {
                // Use the domain to convert the string to an internal value.  Then
                // set the value using setInternalValue.  This bypasses the restriction
                // on read-only attributes.
                Object internalValue = domain.convertExternalValue( getTask(), null, attributeDef, null,
                                                                    attributeDef.getInitialValue() );
                getAttribute( attributeDef).setInternalValue( internalValue, false );
            }
            else
            if ( ! attributeDef.isHidden() )
            {
                if ( domain.hasInitialValue( getTask(), attributeDef ) )
                    domain.setInitialValue( getAttribute( attributeDef ) );
            }
        }
    }

    public ObjectInstance getObjectInstance()
    {
        return objectInstance;
    }

    @Override
    public EntityDef getEntityDef()
    {
        return entityDef;
    }

    /**
     * If this EntityDef is recursive then this returns the recursive parent,
     * otherwise returns getEntityDef().
     *
     * @return
     */

    EntityDef getBaseEntityDef()
    {
        return entityDef.getBaseEntityDef();
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
            assert ei.versionNumber <= ei.nextVersion.versionNumber;
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
        assert getEntityDef().getParent().getErEntityToken() == parent.getEntityDef().getErEntityToken();

        // We match by ER entity token to handle recursive cases.
        assert parentInstance.getEntityDef().getErEntityToken() == parent.getEntityDef().getErEntityToken() :
            "Setting parent to mismatching EntityDef.  Parent = " + parent.getEntityDef() + ", child = " + getEntityDef();
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
        assert prevTwin == null || prevTwin.getEntityDef() == getEntityDef();
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
        assert nextTwin == null || nextTwin.getEntityDef() == getEntityDef();
    }

    void setNextVersion( EntityInstanceImpl nextVersion )
    {
        this.nextVersion = nextVersion;
        assert nextVersion == null || nextVersion.getEntityDef() == getEntityDef();
    }

    @Override
    public boolean hasNextTwin()
    {
        for ( EntityInstanceImpl ei = getNextTwin();
              ei != null;
              ei = ei.getNextTwin() )
        {
            if ( ! ei.isHidden() )
                return true;
        }

        return false;
    }

    @Override
    public boolean hasPrevTwin()
    {
        for ( EntityInstanceImpl ei = getPrevTwin();
                ei != null;
                ei = ei.getPrevTwin() )
          {
              if ( ! ei.isHidden() )
                  return true;
          }

          return false;
    }

    /**
     * Copy all the next/prev pointers.
     *
     * @param src
     */
    private void copyAllPointers( EntityInstanceImpl src )
    {
        assert getEntityDef() == src.getEntityDef();

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

    LodDef getLodDef()
    {
        return objectInstance.getLodDef();
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

    Iterable<AttributeDef> getNonNullAttributeList()
    {
        return new Iterable<AttributeDef>()
        {
            @Override
            public Iterator<AttributeDef> iterator()
            {
                return new Iterator<AttributeDef>()
                {
                    private int attributeNumber     = -1;
                    private int nextAttributeNumber = -1;

                    @Override
                    public boolean hasNext()
                    {
                        if ( nextAttributeNumber <= attributeNumber )
                        {
                            nextAttributeNumber = attributeNumber + 1;
                            while ( nextAttributeNumber < getEntityDef().getAttributeCount() )
                            {
                                AttributeDef attributeDef = getEntityDef().getAttribute( nextAttributeNumber );
                                AttributeValue attrib = getInternalAttribute( attributeDef );
                                if ( ! attrib.isNull( getTask(), attributeDef) || attrib.isUpdated() )
                                    break;

                                nextAttributeNumber++;
                            }
                        }

                        return nextAttributeNumber < getEntityDef().getAttributeCount();
                    }

                    @Override
                    public AttributeDef next()
                    {
                        attributeNumber = nextAttributeNumber;
                        return getEntityDef().getAttribute( attributeNumber );
                    }

                    @Override
                    public void remove()
                    {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    /**
     * Be careful using this.  It does not call the derived operation so derived
     * attribute values may be stale.
     *
     * @param AttributeDef
     * @return
     */
    AttributeValue getInternalAttribute( AttributeDef attributeDef )
    {
        Map<String, AttributeValue> attributes = getInstanceMap( attributeDef );
        if ( ! attributes.containsKey( attributeDef.getErAttributeToken() ) )
            attributes.put( attributeDef.getErAttributeToken(), new AttributeValue( attributeDef ) );

        return attributes.get( attributeDef.getErAttributeToken() );
    }

    private Map<String, AttributeValue> getInstanceMap( AttributeDef attributeDef )
    {
        if ( attributeDef.isPersistent() )
            return persistentAttributes;

        return workAttributes;
    }

    @Override
    public int getDepth()
    {
        return depth;
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

        // If 'this' doesn't have a next hier or if the next hier has a depth that is
        // not below 'this' then return 'this' because it has no descendants.
        if ( getNextHier() == null || getNextHier().getDepth() <= depth )
            return this;

        EntityInstanceImpl prev = null;
        EntityInstanceImpl search;
        for ( search = this.getNextHier();
              search != null && search.getDepth() > depth;
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
     * Finds the first child/descendant entity that matches entityDef.
     * @param entityDef
     * @return May return null.
     */
    EntityInstanceImpl getFirstChildMatchingEntityDef( EntityDef searchEntityDef )
    {
        for ( EntityInstanceImpl next = this.getNextHier(); next != null; next = next.getNextHier() )
        {
            if ( next.getEntityDef() == searchEntityDef )
                return next; // We found what we're looking for.

            if ( next.getDepth() <= this.getDepth() )
                // We've gone past the last hier so we're done.
                return null;

            // If we're looking at a descendant of 'e' then we can short-circuit
            // some of the entities by skipping to the last twin.
            if ( next.getDepth() > searchEntityDef.getDepth() )
                next = next.getLastTwin().getLastChildHier();
        }

        return null;
    }

    /**
     * Adds all child entities to 'this' EI to indicate they don't need to be lazy
     * loaded.  Intended to be used by commit processing to indicate that an entity
     * that has been created doesn't need to have its children loaded.
     */
    void flagAllChildrenAsLazyLoaded()
    {
        if ( ! getEntityDef().getLazyLoadConfig().hasLazyLoadChild() )
            return;  // EI doesn't have any children who are lazy loaded.

        Set<EntityDef> set = getEntitiesLoadedLazily();
        set.addAll( getEntityDef().getChildren() ); // We'll just add all children regardless of whether they are lazyload.
    }

    boolean hasChildBeenLazyLoaded( EntityDef childEntityDef )
    {
        return getEntitiesLoadedLazily().contains( childEntityDef );
    }

    /**
     * This checks to see if the childEntityDef needs to be lazy loaded and, if so, loads it.
     *
     * @param view
     * @param childEntityDef
     */
    void lazyLoadChild( ViewImpl view, EntityDef childEntityDef )
    {
        // Is lazyLoad enabled for this view?
        if ( ! view.isLazyLoad() )
            return;

        // Is the child entity instance loaded lazily?
        if ( ! childEntityDef.getLazyLoadConfig().isLazyLoad() )
            return; // Nope.

        // Has this entity been created?  If so then nothing has been written to
        // the DB that can be lazy loaded.
        if ( isCreated() )
            return;

        // Was this OI activated with the flag that indicates that entities marked
        // as lazy loaded should be loaded anyway?
        if ( getObjectInstance().getActivateOptions() != null &&
             getObjectInstance().getActivateOptions().getActivateFlags().contains( ActivateFlags.fINCLUDE_LAZYLOAD ) )
        {
            return; // Entities flagged as lazy load have already been loaded.
        }

        // Have we already loaded this child?
        if ( hasChildBeenLazyLoaded( childEntityDef ) )
            return;  // Already loaded.  Don't do it again.

        // Add the entity to the map now.  This will prevent an infite loop when we try
        // to activate the EI from the DB.
        getEntitiesLoadedLazily().add( childEntityDef );

        view.dblog().debug( "Lazy-loading %s for parent %s", childEntityDef.getName(), this.toString() );
        ActivateObjectInstance activator = new ActivateObjectInstance( view );
        activator.activate( view, childEntityDef );
    }

    private boolean assertNoTwin( EntityInstanceImpl parent,
                                  EntityInstanceImpl prevInstance )
    {
        if ( parent != null && prevInstance == null )
        {
            // The calling code has indicated that there are no twins of the newly created
            // instance but we will verify because if there is then the chains will be thrown off.
            for ( EntityInstanceImpl search : parent.getDirectChildren( false, false ) )
            {
                if ( search.getEntityDef() == getEntityDef() )
                    return false; // We found a twin.
            }
        }

        return true;
    }

    private MaxCardinalityException checkMaxCardinality( boolean forAdd )
    {
        if ( getParent() != null )
        {
            int twinCount = getTwinCount(false);

            // If forAdd is true then we're making sure we can add a twin, so add
            // one to the count to account for the new one.
            if ( forAdd )
                twinCount++;

            if ( twinCount > entityDef.getMaxCardinality() )
                return new MaxCardinalityException( getEntityDef() );
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
            // We didn't find a twin.  Find the last child under parent with a EntityDef
            // index < 'this' -- that will be the prev sibling.  Find the first child
            // with index > 'this' to be the next sibling.
            for ( EntityInstanceImpl child : parent.getDirectChildren( true, false ) )
            {
                EntityDef ve = child.getEntityDef();
                if ( ve.getHierIndex() < this.getEntityDef().getHierIndex() )
                {
                    prevTwin = child;
                }
                else
                if ( ve.getHierIndex() > this.getEntityDef().getHierIndex() )
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
        View view = null;

        if ( entityDef.hasDeleteConstraint() )
        {
            view = getObjectInstance().createView( this );
            entityDef.executeEntityConstraint( view, EntityConstraintType.DELETE );
        }

        return deleteEntity( true, view );
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
        if ( entityDef.hasDeleteConstraint() )
            entityDef.executeEntityConstraint( view, EntityConstraintType.DELETE );

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
        if ( ! getEntityDef().isDelete() )
            throw new ZeidonException( "Entity is not flagged for delete." )
                            .prependEntityDef( getEntityDef() );

        if ( isIncomplete() && getEntityDef().isPersistent() )
            throw new ZeidonException( "This entity instance may not be deleted because it is incomplete.  " +
                                       "One or more of its children were activated with qualification that limited results.  " +
                                       "or a child entity was dropped.  It might be possible to get around this error by " +
                                       "dropping %s instead of deleting it", getEntityDef().getName() )
                                    .prependEntityInstance( this );

        // If checkRestrictedDelete is set, then make sure none of the child entities
        // have their parent-restrict delete flag set.
        if ( getEntityDef().isCheckRestrictedDelete() )
        {
            for ( EntityInstanceImpl child : this.getDirectChildren( false, false ) )
            {
                if ( child.getEntityDef().isRestrictParentDelete( ) )
                    throw new ZeidonException( "Can't delete %s because of restrict constraint on child entity %s", this, child );
            }
        }

        if ( getEntityDef().getEventListener() != null )
        {
            EventDataImpl data = new EventDataImpl( getTask() ).setEntityInstance( this ).setView( view );
            getEntityDef().getEventListener().event( EventNotification.EntityDeleted, data );
        }

        // Keep track of child entities that are deleted.  Later all we'll use them
        // to spawn the delete.
        ArrayList<EntityInstanceImpl> deletedEntities = new ArrayList<>();

        // Run through the entity and all it's children and set the delete flag.
        // We start with 'this' because the logic below spawns the delete and
        // we want to spawn the deletes for all entities.
        int startLevel = getDepth();
        EntityInstanceImpl scan = this;
        while ( scan != null && ( scan.getDepth() > startLevel || scan == this ) )
        {
            // If the instance in question is already hidden, skip it
            // and all of its descendants since it may have been excluded or
            // moved BEFORE the Delete was issued.
            if ( scan.isHidden() )
            {
                scan = scan.getNextNonDescendant();
                continue;
            }

            if ( ! scan.getEntityDef().isParentDelete()  && scan != this )
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
                deletedEntities.add( scan );

            scan = scan.getNextHier();
        }

        // Now go through and spwan the delete.  We do it here instead of the main
        // loop because we want to process all the entities in the main OI first.
        EntitySpawner spawner = new EntitySpawner( this, view );
        for ( EntityInstanceImpl ei : deletedEntities )
            spawner.spawnDelete( ei );

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

        if ( ! getEntityDef().isDerived() && ! getEntityDef().isDerivedPath() )
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
        getTask().log().trace( "Cleaning up dead entity %s for %s", this, entityDef );
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
        return excludeEntity( null );
    }

    CursorResult excludeEntity( View view )
    {
        if ( entityDef.hasExcludeConstraint() )
        {
            if ( view == null )
                view = getObjectInstance().createView( this );

            entityDef.executeEntityConstraint( view, EntityConstraintType.EXCLUDE );
        }

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
    	if ( ! getEntityDef().isExclude() )
    	    throw new ZeidonException( "Entity is not flagged for exclude." )
    	                    .prependEntityDef( getEntityDef() );

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

        if ( entityDef.getErEntityToken() != source.getEntityDef().getErEntityToken() )
            throw new ZeidonException("Unmatched ER tokens").appendMessage( "Target = %s\nSource = %s", entityDef, source.getEntityDef() );

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
                                .appendMessage( "Entity1 = %s.%s", getLodDef().getName(), getEntityDef().getName() )
                                .appendMessage( "Key1 = %s", key2 )
                                .appendMessage( "Entity2 = %s.%s", s.getLodDef().getName(), s.getEntityDef().getName() )
                                .appendMessage( "Key2 = %s", key1 );
        }

        if ( isLinked( s ) )
            return false;

        linkInternalInstances( s );
        return true;
    }

    /**
     * Link this entity instance with 'source'.
     *
     * @param source
     */
    void linkInternalInstances( EntityInstanceImpl sourceInstance )
    {
        assert sourceInstance != null;
        assert sourceInstance != this;
        assert entityDef.getErEntityToken() == sourceInstance.getEntityDef().getErEntityToken();

        LinkValidation valid = getEntityDef().validateLinking( sourceInstance.getEntityDef() );
        if ( valid != LinkValidation.SOURCE_OK )
        {
            // We should never get here because validateLinking() should throw an exception if
            // it doesn't return SOURCE_OK but just in case...
            throw new ZeidonException( "Internal error.  validateLinking returned something invalid." );
        }

        assert getEntityDef().getErEntityToken() == sourceInstance.getEntityDef().getErEntityToken() : "Attempting to link view entities that are not the same ER entity";
        sourceInstance.addLinkedInstance( this );

        if ( sourceInstance.versionNumber == this.versionNumber )
            this.persistentAttributes = sourceInstance.persistentAttributes;
        else
        {
            persistentAttributes = new HashMap<>( entityDef.getPersistentAttributeCount() );
            // Copy work and persistent attributes.
            copyAttributes( sourceInstance, true, true );
        }
    }

    /**
     * Link newInstance with 'this'.  Will create this.linkedInstances if necessary.
     * @param newInstance
     */
    private void addLinkedInstance( EntityInstanceImpl newInstance )
    {
        synchronized( getTask() )
        {
            if ( newInstance.linkedInstances2 == NOT_LINKED )
            {
                if ( linkedInstances2 == NOT_LINKED )
                {
                    // This is the first time they have been linked.
                    linkedInstances2 = new LinkedInstances( this );
                }

                linkedInstances2.addLinkedInstance( newInstance );
                newInstance.linkedInstances2 = linkedInstances2;
                return;
            }

            if ( linkedInstances2 == NOT_LINKED )
            {
                newInstance.linkedInstances2.addLinkedInstance( this );
                this.linkedInstances2 = newInstance.linkedInstances2;
                return;
            }

            // If we get here they both have a set of linked instances.
            linkedInstances2.mergeEis( this, newInstance );

            // Check to see if targetInstance is already linked and if it is remove
            // it. This can happen when merging an OI committed on a web server.
//            if ( newInstance.linkedInstances != null )
//                newInstance.linkedInstances.remove( newInstance );

//            assert assertLinkedInstances() : "Error with linked instances";
        }
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
    public boolean isChildUpdated() throws NullCursorException
    {
        return childUpdated;
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
        versionNumber = getTask().getNextVersionNumber();
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
            Optional<EntityInstanceImpl> versioned = linkedInstances2.stream()
                                .filter( linked -> linked.versionNumber != this.versionNumber ) // If it's part of the current version its' ok.
                                .filter(  linked -> linked.isVersioned() )
                                .findFirst();

            if ( versioned.isPresent() )
            {
                throw new TemporalEntityException( this, "Attempting to create a temporal subobject for an entity that " +
                        "has a child entity linked to another temporal entity.")
                    .appendMessage( "Temporal root: %s", ei.toString() )
                    .appendMessage( "Linked instance: %s", versioned.get().toString() );
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
        {
            getObjectInstance().setUpdated( true );
            setChildUpdateForParents();
        }
    }

    void setUpdated(boolean isUpdated )
    {
        setUpdated( isUpdated, true, true );
    }

    private void setChildUpdateForParents()
    {
        if ( getEntityDef().isDerived() )
            return;

        for ( EntityInstanceImpl parent = getParent();
              parent != null && ! parent.isChildUpdated();
              parent = parent.getParent() )
        {
            parent.childUpdated = true;
        }
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

        if ( getEntityDef().isDebugIncremental() )
            JoeUtils.sysMessageBox( "Debug Incremental", "Changing update flag for " + toString() );

        // The isUpdated flag is only set for persistent attributes.
        if ( setPersistent )
            this.updated = isUpdated;

        // We don't replicate the updated flag if it's being turned off.
        if ( isUpdated )
        {
            if ( setPersistent )
                setChildUpdateForParents();

            if ( setLinked )
            {
                linkedInstances2.stream( this ).forEach( linked -> {
                    linked.setUpdated( true, false, setPersistent );
                } );
            }

            if ( ! isVersioned() )
            {
                if ( setPersistent )
                    getObjectInstance().setUpdated( true ); // Also sets updatedFile
                else
                    getObjectInstance().setUpdatedFile( true );
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

    private void acceptSubobjectEntity(final VersionStatus newStatus, final long newVersionNumber, boolean acceptLinked )
    {
        assert isVersioned() : "Unexpected version status: " + versionStatus;

        setVersionStatus( newStatus );

        // Check for a null pointer because it's possible this entity was
        // created and therefore won't have a prevVersion.
        if ( prevVersion != null )
        {
            // DON'T null out prevVersion.nextVersion.  That will allow any cursors pointing to the
            // superseded version to find the new one.  The GC will eventually clean up.
            // Setting the dropped flag also tells the linked logic to ignore this entity.
            prevVersion.dropped = true;
            prevVersion.setVersionStatus( VersionStatus.SUPERSEDED );
            linkedInstances2.removeLinkedInstance( prevVersion );

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

        if ( acceptLinked )
        {
            Stream<EntityInstanceImpl> stream;
            if ( newStatus == VersionStatus.NONE )
                stream = linkedInstances2.stream(); // Update all EIs, including EIs outside the temporal subobject.
            else
                stream = linkedInstances2.stream( this ); // Accept only

            stream.forEach( linked -> {
                if ( linked == this )
                    return;

                assert linked.versionNumber == 0 || linked.versionNumber == this.versionNumber;

                // If there is a version, then we'll eventually accept it so skip it.
                if ( linked.versionNumber != 0 )
                    return;

                // This is a linked instance without a version.  This means we must be accepting
                // the final version so we want to update any linked instances outside the
                // temporal subobject.
                linked.persistentAttributes = this.persistentAttributes;
                linked.updated = this.updated;
                linked.deleted = this.deleted;
                if ( isChanged() )
                {
                    linked.getObjectInstance().setUpdated( true );
                    linked.setChildUpdateForParents();
                }
            } );
        }

        versionNumber = newVersionNumber;

        // If this entity has been changed then set the flag for the OI.
        if ( isChanged() && newStatus == VersionStatus.NONE )
        {
            getObjectInstance().setUpdated( true );
            setChildUpdateForParents();
        }
    }

    void validateSubobject( View view, Collection<ZeidonException> list )
    {
        assert isHidden() == false : "Attempting to validate a hidden instance.";

        if ( getEntityDef().hasAcceptConstraint() )
        {
            try
            {
                getEntityDef().executeEntityConstraint( view, EntityConstraintType.ACCEPT );
            }
            catch ( Exception e )
            {
                list.add( ZeidonException.wrapException( e ) );
                return;
            }
        }

        //
        // Make sure that all the required attributes have non-null values
        // if this entity has been changed in any way.
        //
        if ( isCreated() || isUpdated() || isIncluded() )
        {
            for ( AttributeDef attributeDef : getEntityDef().getAttributes() )
            {
                // Ignore hidden attributes.
                if ( attributeDef.isHidden() )
                    continue;

                // Genkeys will have their value created so ignore it.
                if ( attributeDef.isGenKey() )
                    continue;

                if ( ! attributeDef.isRequired() )
                    continue;

                if ( ! getAttribute( attributeDef) .isNull() )
                    continue;

                list.add( new RequiredAttributeException( attributeDef ) );
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
        for ( EntityDef childEntity : getEntityDef().getChildren() )
        {
            if ( childEntity.getMinCardinality() == 0 )
                continue;  // Child entities aren't required so ignore this one.

            // If the child is being lazy-loaded and 'this' EI hasn't been created
            // then we'll assume the child just hasn't been loaded and we're good.
            if ( childEntity.getLazyLoadConfig().isLazyLoad() )
            {
                if ( ! isCreated() && ! isIncluded() )
                    continue;
            }

            // Make sure there is at least one child instance that matches this.
            int hidden = 0;
            int nonhidden = 0;
            for ( EntityInstance ei : getChildren( childEntity, true ) )
            {
                if ( ei.isHidden() )
                    hidden++;
                else
                    nonhidden++;
            }

            if ( isIncomplete() && hidden == 0 )
            {
                // The parent of childEntity is incomplete which means not all of it's children
                // were loaded.  We only need to validate min cardinality if the user
                // deleted/excluded and of the children.  If we get here then they
                // didn't so we're good.
                continue;
            }

            if ( childEntity.getMinCardinality() > nonhidden )
                list.add( new RequiredEntityMissingException( childEntity ) );
        }

        // Now run this on all direct children.
        for ( EntityInstanceImpl childInstance : getDirectChildren( false, false ) )
            childInstance.validateSubobject( view, list );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#validateSubobject()
     */
    Collection<ZeidonException> validateSubobject( View view )
    {
        Collection<ZeidonException> list = new ArrayList<ZeidonException>();
        validateSubobject( view, list );

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
    void validateSubobjectThrowException( View view )
    {
        Collection<ZeidonException> list = validateSubobject( view );
        if ( list == null || list.size() == 0 )
            return;

        throw new SubobjectValidationException( list );
    }

    @Override
    public EntityInstanceImpl acceptSubobject()
    {
        return acceptSubobject( getObjectInstance().createView( this ) );
    }

    EntityInstanceImpl acceptSubobject( View view )
    {
        // If the entity is a temporal entity created via createTemporal we'll
        // accept it.
        if ( versionStatus == VersionStatus.UNACCEPTED_ENTITY )
        {
            acceptTemporalEntity( view );
            return this;
        }

        // Before we change any version pointers let's validate the entity and attribute values.
        validateSubobjectThrowException( view );

        // If this EI isn't versioned then we're done.
        if ( versionStatus == VersionStatus.NONE )
            return this;

        if ( versionStatus != VersionStatus.UNACCEPTED_ROOT )
            throw new TemporalEntityException(this, "Entity is not a root of a temporal subobject root" );

        assert prevVersion != null : "Unaccepted root has null prevVersion";

        // Make sure none of the child EIs are an unaccepted root.
        for ( final EntityInstanceImpl ei : getChildrenHier( false, false, false ) ) // Loop through all children, including excluded.
        {
            if ( ei.versionStatus == VersionStatus.UNACCEPTED_ROOT )
                throw new TemporalEntityException( this, "Entity has children that are unaccepted version roots" );
        }

        // Get the versionStatus of the previous version.  We'll set the status of all children to be
        // this status.  We need to do this in case there are multiple layers of versioned subobjects.
        final VersionStatus newStatus = prevVersion.versionStatus;
        final long newVersionNumber = prevVersion.versionNumber;
        assert newStatus == VersionStatus.UNACCEPTED || newStatus == VersionStatus.NONE :
            "Internal error: unsupported double versioning " + newStatus;

        // Spawn the changes to linked instances if this is the final version.
        if ( newStatus == VersionStatus.NONE )
        {
            EntitySpawner spawner = new EntitySpawner( this );
            spawner.spawnAccept();
        }

        for ( final EntityInstanceImpl ei : getChildrenHier( true, false ) ) // Loop through all, including excluded.
            ei.acceptSubobjectEntity( newStatus, newVersionNumber, true );

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
        while ( search != null && search.getDepth() > this.getDepth() )
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

        // If we're not going to run accept on linked instances then we might not be done
        // So we can assert.
        assert assertLinkedInstances() : "Error with linked instances";

        getObjectInstance().decrementVersionedCount();

        return this;
    }

    /**
     * Cancels the temporal entity.
     *
     * Note that we don't change this.prevVersion because it is used by cursor logic to
     * find the correct version.
     */
    private void cancelSubobjectEntity( boolean setLinked )
    {
        // It's possible we've already dropped this one because it is linked to another
        // versioned entity that has been canceled.
        if ( isDropped() )
            return;

        assert isVersioned() : "Unexpected version status";

        setVersionStatus( VersionStatus.CANCELED );
        dropped = true;
        linkedInstances2.removeLinkedInstance( this );

        // prevVersion should only be null if the current entity was created/inserted.
        if ( prevVersion != null )
        {
            prevVersion.setNextVersion( null );
            prevVersion.addAllHashKeyAttributes();
        }
        else
            assert isCreated() || isIncluded();

        if ( setLinked )
        {
            linkedInstances2.stream( this ).forEach( linked -> {
                linked.cancelSubobjectEntity( false );
            } );
        }
    }

    @Override
    public EntityInstanceImpl cancelSubobject()
    {
        return cancelSubobject( null );
    }

    /**
     * Cancels the temporal subobject.  The view that is passed in is used to call
     * the entity constraint (if it exists).  If view is null then a temporary view
     * will be created.
     *
     * @return
     */
    EntityInstanceImpl cancelSubobject( ViewImpl view )
    {
        // If the entity is a temporal entity created via createTemporal we'll cancel it.
        if ( versionStatus == VersionStatus.UNACCEPTED_ENTITY )
        {
            cancelTemporalEntity();
            return this.prevVersion;
        }

        if ( versionStatus != VersionStatus.UNACCEPTED_ROOT )
            throw new TemporalEntityException(this, "Entity is not the root of a temporal subobject" );

        if ( entityDef.hasCancelConstraint() )
        {
            if ( view == null )
                view = getObjectInstance().createView( this );

            entityDef.executeEntityConstraint( view, EntityConstraintType.CANCEL );
        }

        // All we need to do is go through all of the entities in the subobject and set their
        // pointers to null.  Everything else will be taken care of by normal processing.
        // The getChildrenHier uses an iterator that needs the nextHier pointers so we'll
        // create a temporary list before nulling them out.
        List<EntityInstanceImpl> list = new ArrayList<EntityInstanceImpl>();
        for ( EntityInstanceImpl ei : this.getChildrenHier( true, false, false ) )
            list.add( ei );

        // Now we can set the pointers to null without a problem.
        for ( EntityInstanceImpl ei : list )
            ei.cancelSubobjectEntity( true );

        getObjectInstance().decrementVersionedCount();
        return this.prevVersion;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EntityInstance#acceptTemporalEntity()
     */
    @Override
    public void acceptTemporalEntity()
    {
        acceptTemporalEntity( getObjectInstance().createView( this ) );
    }

    void acceptTemporalEntity( View view )
    {
        if ( versionStatus != VersionStatus.UNACCEPTED_ENTITY )
            throw new TemporalEntityException(this, "Entity is not the root of a temporal entity");

        // Make sure none of the child EIs are an unaccepted root.
        for ( final EntityInstanceImpl ei : getChildrenHier( false, false, false ) ) // Loop through all children, including excluded.
        {
            if ( ei.versionStatus == VersionStatus.UNACCEPTED_ROOT )
                throw new TemporalEntityException( this, "Entity has children that are unaccepted version roots" );
        }

        // Before we change any version pointers let's validate the entity and attribute values.
        validateSubobjectThrowException( view );

        VersionStatus newVersionStatus = VersionStatus.NONE;
        long newVersionNumber = 0;

        if ( getParent() != null )
        {
            newVersionNumber = getParent().versionNumber;
            newVersionStatus = getParent().versionStatus;
            if ( newVersionStatus == VersionStatus.UNACCEPTED_ROOT )
                newVersionStatus = VersionStatus.UNACCEPTED;
        }

        // Set status/number for all children.  This will allow us to spawn changes.
        // This needs to be down before we spawn so that we'll spawn the changes
        // out to linked entities with versionNumbers that match newVersionNumber.
        for ( EntityInstanceImpl ei : getChildrenHier( true, false, false ) )
        {
            ei.versionNumber = newVersionNumber;
            ei.setVersionStatus( newVersionStatus );

            // For all linked instances, copy the persistentAttributes if they have
            // the same versionNumber.
            ei.linkedInstances2.stream( ei ).forEach( linked -> {
                linked.persistentAttributes = this.persistentAttributes;
            } );
        }

        if ( newVersionStatus == VersionStatus.NONE )
        {
            EntitySpawner spawner = new EntitySpawner( this );
            spawner.spawnCreate();
            getObjectInstance().setUpdated( true );
            setChildUpdateForParents();
        }

        assert assertLinkedInstances();
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

        // If we're dropping this entity without first having deleted/excluded it then
        // this OI is potentially incomplete.  Set the flag.
        if ( getParent() != null && ! isHidden() && ! isCreated() )
            getParent().setIncomplete( getEntityDef() );

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
     * Returns the linked instances.  Note this includes 'this'.
     *
     * @return
     */
    Collection<EntityInstanceImpl> getAllLinkedInstances()
    {
        return getLinkedInstances( false, false );
    }

    /**
     * Returns an iterable list of entities linked with 'this'.  If there
     * are no linked entities it will return an empty list.
     * Does NOT include 'this'.
     *
     * @return
     */
    @Override
    public Collection<EntityInstanceImpl> getLinkedInstances()
    {
        return getLinkedInstances( false, true );
    }

    /**
     * Get the list of linked instances, including those that have been dropped
     * but still unclaimed by the GC.  Includes 'this'.
     *
     * @return
     */
    Collection<EntityInstanceImpl> getAllDroppedLinkedInstances( )
    {
        return linkedInstances2.fullStream().collect( Collectors.toList() );
    }

    /**
     * Validate that all the linked instances have correct data.
     */
    private boolean assertLinkedInstances()
    {
        for ( final EntityInstanceImpl ei : getChildrenHier( true, true ) )
        {
            ei.linkedInstances2.stream( ei ).forEach( linked -> {
                if ( ! linked.isLinked( ei ) )
                    assert false;
            } );
        }

        return true;
    }

    /**
     * Returns the linked instances if there are any. If 'excludeSource' is true
     * then 'source' will be ignored when building the return list. This allows
     * the caller to get a list of all other linked instances.
     *
     * Note that entities that have been dropped but not reclaimed by the GC
     * will still show up in this list. We can skip most of those by ignoring
     * ones flagged as dropped.
     *
     * @param source
     *            Ignore this EI when creating the list.
     *
     * @return List of linked instances. If there are none, an empty list is
     *         returned.
     */
    private Collection<EntityInstanceImpl> getLinkedInstances( boolean includeDropped, boolean excludeSource )
    {
        assert ! includeDropped : "Why are we asking for dropped instances?";

        if ( linkedInstances2 == NOT_LINKED )
        {
            if ( excludeSource )
                return Collections.emptyList();
            else
                return Arrays.asList( this );
        }

        Stream<EntityInstanceImpl> stream = linkedInstances2.stream( this );

        if ( includeDropped )
            throw new ZeidonException("Why are we asking for dropped instances?" );

        if ( excludeSource )
            stream = stream.filter( ei -> ei != this );

        return stream.collect( Collectors.toList() );
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
     * Find the parent entity instance of 'this' that has parentEntityDef as its
     * entity definition.  Must work for recursive subobjects so the logic is a bit
     * more complicated than just doing a simple comparison.
     *
     * Used in commit processing to set foreign keys.
     *
     * @param parentEntityDef
     * @return
     */
    EntityInstanceImpl findMatchingParent( EntityDef parentEntityDef )
    {
        EntityInstanceImpl searchInstance = getParent();
        while ( searchInstance != null && searchInstance.getEntityDef() != parentEntityDef )
        {
            EntityDef ed = searchInstance.getEntityDef();

            // If the parent entity we are looking for is a recursive parent,
            // then it's possible that the entity instance we are looking for
            // has an EntityDef that is the recursive child entity.
            if ( parentEntityDef.isRecursiveParent() && parentEntityDef.getRecursiveChild() == ed )
                break;

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

    private boolean assertAttributesEqual( AttributeValue sourceValue, AttributeValue targetValue )
    {
        // Check to see if sourceValue has been set.  If not then we can't compare it because it wasn't set!
        if ( ! sourceValue.isSet() )
            return true;

        Object source = sourceValue.getInternalValue();
        Object target = targetValue.getInternalValue();

        boolean match;
        if ( source == null )
            match = target == null;
        else
            match = source.equals( target );

        if ( ! match )
        {
            getTask().log().error( "Source = %s", sourceValue );
        }
        return match;
    }

    /**
     * Add all attributes that are in sourceAttributes but not this.
     * @return
     *
     */
    boolean addMissingAttributes( Map<String, AttributeValue> sourceAttributes )
    {
        if ( sourceAttributes == null )
            return false;

        // Keep track of whether any attributes we add are updated.
        boolean attributeUpdated = false;

        for ( String erToken: sourceAttributes.keySet() )
        {
            AttributeValue sourceValue = sourceAttributes.get( erToken );
            AttributeValue targetValue = persistentAttributes.get( erToken );
            if ( targetValue == null || ! targetValue.isSet() )
            {
                persistentAttributes.put( erToken, sourceValue );
                attributeUpdated = attributeUpdated || sourceValue.isUpdated();
            }
            else
            {
                assert assertAttributesEqual( sourceValue, targetValue ) : "The same attribute in two linked instances don't match!";
            }
        }

        return attributeUpdated;
    }

    void copyAttributes(EntityInstanceImpl sourceInstance, boolean copyPersist, boolean copyUpdateFlags)
    {
        for ( AttributeDef attributeDef : sourceInstance.getNonNullAttributeList() )
        {
            if ( attributeDef.isPersistent() && !copyPersist )
                continue;

            AttributeValue source = sourceInstance.getInternalAttribute( attributeDef );
            AttributeValue target = getInternalAttribute( attributeDef );
            Object internalValue = source.getInternalValue();
            target.setInternalValue( getTask(), attributeDef, internalValue, true );
            addHashKeyAttributeToMap( attributeDef );

            if ( copyUpdateFlags )
                target.copyUpdateFlags( source );
            else
                target.setUpdated( true );
        }
    }

    /**
     * Loops through all the direct EI children of 'this'.
     *
     * @param allowHidden
     * @return
     */
    @Override
    public EntityIterator<EntityInstanceImpl> getDirectChildren()
    {
        return getDirectChildren( false, false );
    }

    /**
     * Loops through all the direct EI children of 'this'.
     *
     * @param allowHidden
     * @return
     */
    @Override
    public EntityIterator<EntityInstanceImpl> getDirectChildren( boolean allowHidden )
    {
        return getDirectChildren( allowHidden, true );
    }

    @Override
    public EntityIterator<EntityInstanceImpl> getDirectChildren( boolean allowHidden, boolean allowLazyLoad )
    {
        return new IteratorBuilder(getObjectInstance())
                        .forDirectChildren( this )
                        .allowHidden( allowHidden )
                        .setLazyLoad( allowLazyLoad )
                        .build();
    }

    @Override
    public EntityIterator<? extends EntityInstance> getChildren( EntityDef childEntityDef )
    {
        return new IteratorBuilder(getObjectInstance())
                        .withScoping( this )
                        .forEntityDef( childEntityDef )
                        .build();
    }

    @Override
    public EntityIterator<? extends EntityInstance> getChildren( EntityDef childEntityDef,
                                                                 boolean    allowHidden )
    {
        return new IteratorBuilder(getObjectInstance())
                        .allowHidden( allowHidden )
                        .withScoping( this )
                        .forEntityDef( childEntityDef )
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
        return getChildrenHier( includeParent, excludeHidden, false );
    }

    /**
     * Loop through the children.
     *
     * @param includeParent
     * @param excludeHidden
     * @return
     */
    @Override
    public EntityIterator<EntityInstanceImpl> getChildrenHier( boolean includeParent,
                                                               boolean excludeHidden,
                                                               boolean loadLazyEntities )
    {
        EntityIterator<EntityInstanceImpl> iterable =
                    new IteratorBuilder(getObjectInstance())
                                       .withScoping( this )
                                       .allowHidden( ! excludeHidden )
                                       .setLazyLoad( loadLazyEntities )
                                       .build();

        // If we're not including the parent in this iterator, then skip to the next one.
        if ( ! includeParent )
        {
            // Edge case: if excludeHidden = true and this.hidden = true then the parent will
            // already have been skipped over.
            if ( this.hidden == false )
                iterable.next();
        }

        return iterable;
    }

    /**
     * Iterates through all the children of 'this' in heir order.  If includeParent
     * is true, then the iteration includes 'this' at the beginning.  This will force
     * lazy load.
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

    /**
     * Returns a string representation of the key values of this entity.  If all keys are
     * null then returns null.
     *
     * @return
     */
    @Override
    public String getKeyString()
    {
        KeyStringBuilder builder = new KeyStringBuilder();

        for ( AttributeDef key : getEntityDef().getKeys() )
            builder.appendKey( getAttribute( key ) );

        if ( builder.isNull() )
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

        builder.append( getEntityDef().toString() ).append( " Keys: " );
        for ( AttributeDef key : getEntityDef().getKeys() )
        {
            builder.append( key.getName() ).append( "=" );
            builder.append( getInternalAttribute( key ).toString() ).append( "; " );
        }

        return builder.toString();
    }

    @Override
    public EntityIterator<? extends EntityInstance> getChildren(String childEntityName)
    {
        EntityDef childEntityDef = getLodDef().getEntityDef( childEntityName );
        if ( childEntityDef.getParent() != getEntityDef() )
            throw new ZeidonException( "%s is not a direct child of %s", childEntityName, getEntityDef() );

        return getChildren( childEntityDef );
    }

    @Override
    public CopyAttributesBuilder copyAttributes()
    {
        return new CopyAttributesBuilder().to( this );
    }

    @Override
    public int setMatchingAttributesByName(EntityInstance sourceInstance)
    {
        return setMatchingAttributesByName( sourceInstance, SetMatchingFlags.DEFAULT );
    }

    @Override
    public int setMatchingAttributesByName(EntityInstance sourceInstance, EnumSet<SetMatchingFlags> control)
    {
        EntityDef sourceEntityDef = sourceInstance.getEntityDef();

        // Loop through the target attributes and set their value from the source entity.
        for ( AttributeDef targetAttr : this.getEntityDef().getAttributes() )
        {
            if ( targetAttr.isHidden() )
                continue;

            if ( targetAttr.isDerived() )
                continue;

            if ( ! targetAttr.isUpdate() )
                continue;

            // If target entity was not created (this means that the entity
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

            AttributeDef sourceAttr = sourceEntityDef.getAttribute( targetAttr.getName(), false );
            if ( sourceAttr == null )
                continue;  // No matching attribute by name.

            // Source attr cannot be hidden or derived either.
            if ( sourceAttr.isHidden() || sourceAttr.isDerived() )
                continue;

            // Ignore if both are null.
            if ( getInternalAttribute( targetAttr ).isNull( getTask(), targetAttr ) && sourceInstance.getAttribute( sourceAttr ).isNull() )
                continue;

            if ( control.contains( SetMatchingFlags.fSET_SRCNOTNULL ) )
            {
                // User doesn't want NULL source attributes to be copied.
                if ( sourceInstance.getAttribute( sourceAttr ).isNull() )
                    continue;
            }

            Object sourceValue = sourceInstance.getAttribute( sourceAttr ).getValue();
            getAttribute( targetAttr ).setValue( sourceValue );
        } // for each target AttributeDef...

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
     * Validate the context name.  If the context name is null or "", returns the default context.
     * @param attributeDef
     * @param contextName
     * @return
     */
    private String checkContextName( AttributeDef attributeDef, String contextName )
    {
        Domain domain = attributeDef.getDomain();
        return domain.getContext( getTask(), contextName ).getName();
    }

    AttributeHashKeyMap getAttributeHashkeyMap()
    {
        if ( attributeHashkeyMap == null )
            attributeHashkeyMap = new AttributeHashKeyMap( objectInstance );

        return attributeHashkeyMap;
    }

    /**
     * Get the correct AttributeHaskeyMap depending on the type.
     *
     * @param attributeDef
     * @return
     */
    AttributeHashKeyMap getAttributeHashkeyMap( AttributeDef attributeDef )
    {
        if ( attributeDef.getHashKeyType() == AttributeHashKeyType.GLOBAL )
            return getObjectInstance().getAttributeHashkeyMap();

        if ( attributeDef.getHashKeyType() == AttributeHashKeyType.UNDER_PARENT )
        {
            EntityInstanceImpl parent = findMatchingParent( attributeDef.getHashKeyParent() );
            return parent.getAttributeHashkeyMap();
        }

        throw new ZeidonException("Unsupported AttributeHashKeyType %s", attributeDef.getHashKeyType() );
    }

    /**
     * We've updated an attribute.  Check to see if we need to update the attribute hashkey map.
     *
     * @param attributeDef
     * @param oldValue
     */
    void updateHashKeyAttributeToMap( AttributeDef attributeDef, Object oldValue )
    {
        if ( attributeDef.getHashKeyType() == AttributeHashKeyType.NONE )
            return; // Nothing to do.

        getAttributeHashkeyMap( attributeDef ).updateHashKey( oldValue, attributeDef, this );
    }

    private void addHashKeyAttributeToMap( AttributeDef attributeDef )
    {
        if ( attributeDef.getHashKeyType() == AttributeHashKeyType.NONE )
            return; // Nothing to do.

        getAttributeHashkeyMap( attributeDef ).addHashKey( attributeDef, this );
    }

    private void removeHashKeyAttributeToMap( AttributeDef attributeDef )
    {
        if ( attributeDef.getHashKeyType() == AttributeHashKeyType.NONE )
            return; // Nothing to do.

        getAttributeHashkeyMap( attributeDef ).removeHashKey( attributeDef, this );
    }

    private void removeAllHashKeyAttributes()
    {
        if ( getEntityDef().getHashKeyAttributes() == null )
            return;

        for ( AttributeDef attributeDef : getEntityDef().getHashKeyAttributes() )
            removeHashKeyAttributeToMap( attributeDef );
    }

    void addAllHashKeyAttributes()
    {
        if ( getEntityDef().getHashKeyAttributes() == null )
            return;

        for ( AttributeDef attributeDef : getEntityDef().getHashKeyAttributes() )
            addHashKeyAttributeToMap( attributeDef );
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

            // Create a new version for all children.
            for ( final EntityInstanceImpl ei : root.getChildrenHier( false, true, true ) )
                createTemporalEntity( ei );

            // Now we can use getLatestVersion to set the other pointers.
            for ( EntityInstanceImpl ei : newInstanceList )
            {
                // Each of the 'get' methods (ie. getNextHier) will use getLatestVersion to get the latest version.
                ei.copyAllPointers( ei );

                // If persistentAttributes is set then we've already handled this via a linked EI.
                if ( ei.persistentAttributes != null )
                    continue;

                // We now need to copy attribute values from prevVersion and set up linkedInstances.

                // Copy attributes values from previous version.
                EntityInstanceImpl prevVsn = ei.getPrevVersion();
                ei.newTemporalAttributeList( prevVsn, null );

                ei.linkedInstances2.stream( ei ).forEach( linked -> {
                    if ( linked.persistentAttributes == null )
                        linked.newTemporalAttributeList( linked.getPrevVersion(), ei );
                });
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
                                                                           prevVersionEi.getEntityDef(),
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
            prevVersionEi.setNextVersion( newInstance );
            if ( prevVersionEi.linkedInstances2 != NOT_LINKED )
                prevVersionEi.linkedInstances2.addLinkedInstance( newInstance );

            return newInstance;
        }
    } // private static class TemporalVersionCreator

    /**
     * Creates a new AttributeListInstance for a temporal entity. The work
     * attributes are copied from sourceInstance. Persistent
     * attributes are copied from sourceInstance unless
     * linkedAttributeList is not null.
     *
     * If linkedAttributeList is not null then temporalInstance is linked to
     * another instance so we'll just use the linked instance's persistent
     * attribute.
     *
     * @param sourceInstance - source EI for the temporal instance.
     * @param linkedSourceInstnce - If non-null, then the temporal instance will be linked to this one.
     */
    void newTemporalAttributeList( EntityInstanceImpl sourceInstance,
                                   EntityInstanceImpl linkedSourceInstance )
    {
        workAttributes = new HashMap<>( entityDef.getWorkAttributeCount() );

        if ( linkedSourceInstance == null )
        {
            assert sourceInstance != this;

            sourceInstance.addLinkedInstance( this );
            persistentAttributes = new HashMap<>( entityDef.getPersistentAttributeCount() );

            // Copy work and persistent attributes.
            copyAttributes( sourceInstance, true, true );
        }
        else
        {
            linkedSourceInstance.addLinkedInstance( this );
            persistentAttributes = linkedSourceInstance.persistentAttributes;

            // Copy just work attributes.
            assert sourceInstance.getEntityDef().getName() == this.getEntityDef().getName();
            copyAttributes( sourceInstance, false, true );
            this.addAllHashKeyAttributes();
        }
    }

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
        EntityDef entityDef = getEntityDef();
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

        getTask().log().info( "%s[%s] %s (%x:%d)", indent, entityDef.getName(), flagStr, hashCode(), getEntityKey() );
        for ( AttributeDef attributeDef : getNonNullAttributeList() )
        {
            AttributeInstanceImpl attrib = getAttribute( attributeDef );
            String value = attrib.getString( null );
//            if ( ( flags & View.DISPLAY_EMPTY_ATTRIBS ) == 0 && StringUtils.isBlank( value ) )
//                continue;
//
//            if ( ( flags & View.DISPLAY_HIDDEN ) == 0 && attributeDef.isHidden() )
//                continue;

            getTask().log().info( "%s   (%s)%s: %s", indent, attrib.isUpdated() ? "#" : "~", attributeDef.getName(), value );
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

        if ( getEntityDef().getErEntityToken() != ei.getEntityDef().getErEntityToken() )
            return false;

        EntityInstanceImpl otherInstance = (EntityInstanceImpl) ei.getEntityInstance();
        if ( persistentAttributes == otherInstance.persistentAttributes )
            return true;

        return this.getFirstVersion().persistentAttributes == otherInstance.getFirstVersion().persistentAttributes;
    }

    private EntityInstanceImpl getFirstVersion()
    {
        EntityInstanceImpl temp = this;
        while ( temp.getPrevVersion() != null )
            temp = temp.getPrevVersion();

        return temp;
    }
    /**
     * @param includeHidden If true, count hidden.
     * @return The total number of non-hidden twins for this entity, including this one.
     */
    int getTwinCount( boolean includeHidden )
    {
        int count = 0;
        for ( EntityInstanceImpl search = this; search != null; search = search.getNextTwin() )
        {
            if ( includeHidden || search.isHidden() == false )
                count++;
        }

        for ( EntityInstanceImpl search = this.getPrevTwin(); search != null; search = search.getPrevTwin() )
        {
            if ( includeHidden || search.isHidden() == false )
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
    synchronized Set<EntityDef> getEntitiesLoadedLazily()
    {
        // We should only get here if this entity instance can have children that can be
        // lazy loaded.
        assert getEntityDef().getLazyLoadConfig().hasLazyLoadChild()
                            : "Attempting to get lazy load set for entity without LazyLoad children";

        if ( entitiesLoadedLazily == null )
            entitiesLoadedLazily = new HashSet<EntityDef>();

        return entitiesLoadedLazily;
    }

    /**
     * Returns true if this EI has attempted to load any lazy-load children.
     * @return
     */
    boolean hasLoadedLazyChildren()
    {
        return entitiesLoadedLazily != null && entitiesLoadedLazily.size() > 0;
    }

    @Override
    public AttributeInstance getAttribute( String attributeName )
    {
        AttributeDef attributeDef = getEntityDef().getAttribute( attributeName );
        if ( attributeDef.isHidden() )
            throw new HiddenAttributeException( attributeDef );

        return getAttribute( null, attributeDef );
    }

    @Override
    public AttributeInstanceImpl getAttribute( AttributeDef attributeDef )
    {
        return getAttribute( null, attributeDef );
    }

    AttributeInstanceImpl getAttribute( View view, AttributeDef attributeDef )
    {
        AttributeInstanceImpl attributeInstance =
                new AttributeInstanceImpl( attributeDef,
                                           getInternalAttribute( attributeDef ),
                                           view,
                                           this );

        return attributeInstance;
    }

    @Override
    public AttributeInstance createDynamicAttributeDef( DynamicAttributeDefConfiguration config )
    {
        AttributeDef attributeDef = getEntityDef().createDynamicAttributeDef( config );
        return getAttribute( attributeDef );
    }

    /**
     * @returns true if 'this' entity instance does not have all its children loaded.
     * This can happen if an OI was activated with a RESTRICTING clause or if a child
     * was dropped via dropEntity().  An incomplete EI cannot be deleted.
     */
    @Override
    public boolean isIncomplete()
    {
        return incomplete;
    }

    boolean isParentOf( EntityInstance child )
    {
        child = child.getEntityInstance(); // In case child is an EntityCursor.
        while ( child != null )
        {
            if ( child == this )
                return true;

            child = child.getParent();
        }

        return false;
    }

    boolean isChildOf( EntityInstance parent )
    {
        EntityInstanceImpl p = (EntityInstanceImpl) parent.getEntityInstance();
        return p.isParentOf( this );
    }

    /**
     * This method is called if 'this' entity instance does not have all its children.
     * This can happen if an OI was activated with a RESTRICTING clause or if a child
     * was dropped via dropEntity().
     *
     * In this situation we want to set a flag to indicate that 'this' entity cannot
     * be deleted.
     *
     * @param childEntity
     */
    void setIncomplete( EntityDef childEntity )
    {
        // If we've already set the flag then return.
        if ( incomplete )
            return;

        // If childEntity is null then we are performing a root-only activate
        // so we don't need to check the children.
        if ( childEntity != null )
        {
            // If the child entity isn't deleted when 'this' entity is deleted then
            // we're good.
            if ( ! childEntity.isParentDelete() )
                return;

            if ( childEntity.isDerived() )
                return;

            // If this entity is already deleted then we'll assume the user knows what
            // she's doing.
            if ( isDeleted() )
                return;
        }

        incomplete = true;

        // If there is a parent entity then we'll set its incomplete flag.
        if ( getParent() != null )
            getParent().setIncomplete( getEntityDef() );
    }

    @Override
    public List<AttributeInstance> getAttributes()
    {
        return getAttributes( true );
    }

    @Override
    public List<AttributeInstance> getAttributes( boolean includeNullValues )
    {
        List<AttributeInstance> list = new ArrayList<>();
        if ( includeNullValues )
        {
            for ( AttributeDef attributeDef : getEntityDef().getAttributes() )
                list.add( getAttribute( attributeDef ) );
        }
        else
        {
            for ( AttributeDef attributeDef : getNonNullAttributeList() )
                list.add( getAttribute( attributeDef ) );
        }

        return list;
    }

    @Override
    public void copyAttributes( CopyAttributesBuilder flags )
    {
        EntityInstance sourceInstance = flags.getSourceInstance();
        EntityDef sourceEntityDef = sourceInstance.getEntityDef();

        // Loop through the target attributes and set their value from the source entity.
        for ( AttributeDef targetAttr : this.getEntityDef().getAttributes() )
        {
            if ( targetAttr.isHidden() && ! flags.isCopyHidden() )
                continue;

            if ( targetAttr.isPersistent() && ! flags.isCopyPersistent() )
                continue;

            if ( ! targetAttr.isPersistent() && ! flags.isCopyWork() )
                continue;

            if ( targetAttr.isDerived() )
                continue;

            if ( ! targetAttr.isUpdate() )
                continue;

            // If target entity was not created (this means that the entity
            // has been committed to the database) then the attribute cannot
            // be updated if it is a key.
            if ( ! this.isCreated() && targetAttr.isKey() )
            {
                if ( flags.isCopyKeys() )
                    throw new ZeidonException( "Attempting to copy a key value to an entity instance that already has a key" )
                                   .appendMessage( "Target instance = %s", this.toString() )
                                   .appendMessage( "Source instance = %s", sourceInstance.toString() );

                continue;
            }

            if ( targetAttr.isKey() && ! flags.isCopyKeys() )
                continue;

            // Check to see if user wants to over-write non-null values.
            if ( flags.isKeepNonNull() )
            {
                // The user does NOT want attributes in the target entity that
                // already have values (i.e. they are not null) to be over-written
                // with values from the source entity.  Check the target entity's
                // value.  If it is not null, then continue looping.
                if ( ! getInternalAttribute( targetAttr ).isNull( getTask(), targetAttr ) )
                    continue;
            }

            AttributeDef sourceAttr = sourceEntityDef.getAttribute( targetAttr.getName(), false );
            if ( sourceAttr == null )
                continue;  // No matching attribute by name.

            // Source attr cannot be hidden or derived either.
            if ( ( sourceAttr.isHidden() && ! flags.isCopyHidden() ) || sourceAttr.isDerived() )
                continue;

            // Ignore if both are null.
            if ( getInternalAttribute( targetAttr ).isNull( getTask(), targetAttr ) && sourceInstance.getAttribute( sourceAttr ).isNull() )
                continue;

            if ( flags.isCopyNulls() )
            {
                // User doesn't want NULL source attributes to be copied.
                if ( sourceInstance.getAttribute( sourceAttr ).isNull() )
                    continue;
            }

            Object sourceValue = sourceInstance.getAttribute( sourceAttr ).getValue();
            getAttribute( targetAttr ).setValue( sourceValue );
        } // for each target AttributeDef...
    }

    @Override
    public boolean compareEntity(EntityInstance sourceInstance)
    {
        return compareEntity( sourceInstance, CompareEntityOptions.DEFAULT_OPTIONS );
    }

    @Override
    public boolean compareEntity( EntityInstance sourceInstance, CompareEntityOptions options )
    {
        EntityInstance sourceEi = sourceInstance.getEntityInstance();
        if ( isLinked( sourceEi ) )
            return true;  // If the EIs are linked then they are the same.

        List<AttributeDef> attribs = this.getEntityDef().getAttributes( true );
        EntityDef rightDef = sourceEi.getEntityDef();
        for ( AttributeDef attrib : attribs )
        {
            if ( attrib.isDerived() )  // Skip derived attributes?
            {
                if ( ! options.isCompareDeriviedAttributes() )
                    continue;
            }
            else
            if ( ! attrib.isPersistent() ) // Skip work attributes?
            {
                if ( ! options.isCompareWorkAttributes() )
                    continue;
            }

            AttributeInstance lai = this.getAttribute( attrib );
            if ( lai.isNull() && options.isIgnoreNullAttributes() )
                continue;

            AttributeDef rightAttrib = rightDef.getAttribute( attrib.getName(), false );
            if ( rightAttrib == null || rightAttrib.isHidden() )
            {
                if ( ! options.isCommonAttributesOnly() )
                {
                    getTask().log().trace( "CompareEntity: Attribute missing = %s", attrib.getName() );
                    return false;
                }

                continue;
            }

            AttributeInstance rai = sourceEi.getAttribute( rightAttrib );
            if ( lai.compare( rai ) != 0 )
            {
                getTask().log().trace( "CompareEntity: Attribute is different = %s", attrib.getName() );
                lai.compare( rai );
                return false;
            }
        } // each attrib.

        // If we get here then we didn't find any differences.
        return true;
    }

    /**
     * Keeps track of all EIs linked to each other.
     *
     */
    private static class LinkedInstances
    {
        private final Boolean VALUE = Boolean.TRUE;

        /**
         * List of instances linked with this one. This is a set of weak references;
         * when one of the entity instances is dropped the GC will remove it from
         * this list. The boolean value a dummy value required to make this a map.
         */
        private final Map<EntityInstanceImpl, Boolean> linkedInstances;
        private final String erEntityToken;

        /**
         * We'll allow an empty constructor so we can create a static empty LinkedInstances.
         */
        private LinkedInstances( )
        {
            erEntityToken = "EMPTY_LINKED_INSTANCES";
            linkedInstances = Collections.unmodifiableMap( new HashMap<>() );
        }

        private LinkedInstances( EntityInstanceImpl initialInstance )
        {
            linkedInstances = new MapMaker().concurrencyLevel( 2 ).weakKeys().makeMap();
            linkedInstances.put( initialInstance, VALUE );
            initialInstance.linkedInstances2 = this;
            erEntityToken = initialInstance.getEntityDef().getErEntityToken();
        }

        private Stream<EntityInstanceImpl> fullStream()
        {
            // Check for ei != null because linkedInstances is a weak set and the EI
            // could be garbage collected.
            return linkedInstances.keySet().stream().filter( ei -> ei != null );
        }

        private Stream<EntityInstanceImpl> stream()
        {
            // Check for ei != null because linkedInstances is a weak set and the EI
            // could be garbage collected.
            return fullStream().filter( ei -> ! ei.isDropped() );
        }

        private Stream<EntityInstanceImpl> stream( EntityInstanceImpl ei )
        {
            long versionNumber = ei.versionNumber;
            return stream().filter( linked -> linked.versionNumber == versionNumber );
        }

        private EntityInstanceImpl findMatch( EntityInstanceImpl ei )
        {
            return stream( ei ).findFirst().get();

        }

        void addLinkedInstance( EntityInstanceImpl newInstance )
        {
            assert newInstance.getEntityDef().getErEntityToken() == erEntityToken;

            if ( newInstance.linkedInstances2 == NOT_LINKED )
            {
                // Just add the new instance to linked.
                linkedInstances.putIfAbsent( newInstance, VALUE );
                return;
            }

            assert false; // Do we ever get here?

            // If get here then we have two sets of linkedInstances we need to merge.
            newInstance.linkedInstances2.stream().forEach( linked -> {
                mergeEis( findMatch( linked ), linked );
            } );
        }

        void removeLinkedInstance( EntityInstanceImpl ei )
        {
            if ( linkedInstances.containsKey( ei ) )
                linkedInstances.remove( ei );
        }

        private void mergeEis( EntityInstanceImpl ei1, EntityInstanceImpl ei2 )
        {
            ei1.addMissingAttributes( ei2.persistentAttributes );
            ei1.linkedInstances2.linkedInstances.putAll( ei2.linkedInstances2.linkedInstances );
            ei2.linkedInstances2.stream().forEach( linked -> {
                linked.linkedInstances2 = ei1.linkedInstances2;
                linked.persistentAttributes = ei1.persistentAttributes;
            });
        }

        @Override
        public String toString()
        {
            return linkedInstances.toString();
        }
    }

}