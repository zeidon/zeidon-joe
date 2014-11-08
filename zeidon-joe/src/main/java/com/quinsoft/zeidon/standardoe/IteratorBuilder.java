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

package com.quinsoft.zeidon.standardoe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.EntityIterator;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.AttributeHashKeyType;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LazyLoadConfig;
import com.quinsoft.zeidon.standardoe.EntityComparator.AlwaysTrueComparator;
import com.quinsoft.zeidon.standardoe.EntityComparator.InSetComparator;
import com.quinsoft.zeidon.standardoe.EntityComparator.NonHiddenComparator;
import com.quinsoft.zeidon.standardoe.EntityTraverser.DirectChildTraverser;
import com.quinsoft.zeidon.standardoe.EntityTraverser.HierTraverser;
import com.quinsoft.zeidon.standardoe.EntityTraverser.NextTwinTraverser;

/**
 * This object builds entity iterators with different options.  Expected use case is:
 *      iterator = new IteratorBuilder()
 *                       .setCursor( cursor )
 *                       .forTwinsOf( getEntityInstance() )
 *                       .forEntityDef( getEntityDef() )
 *                       .build();
 * @author DG
 *
 */
class IteratorBuilder
{
    private final static EntityComparator  s_nonHiddenComparator = new NonHiddenComparator();
    private final static EntityComparator  s_hiddenComparator    = new AlwaysTrueComparator();
    private final static EmptyIterator     s_emptyIterator       = new EmptyIterator();
    private final static NextTwinTraverser s_nextTwinTraverser   = new NextTwinTraverser();
    private final static HierTraverser     s_hierTraverser       = new HierTraverser();

    private final ObjectInstance objectInstance;
    private EntityInstanceImpl   initialInstance;
    private boolean              allowHidden       = false;
    private boolean              setNext           = false;
    private boolean              scopeOi           = false;
    private boolean              setLast           = false;
    private boolean              directChildren    = false;
    private boolean              includeHierParent = false;
    private EntityCursorImpl     cursor;
    private EntityDef           targetEntityDef;
    private EntityInstanceImpl   currentInstance;
    private EntityComparator     comparator;
    private EntityInstanceImpl   scopingInstance;
    private List<AttributeValue> attributeValueList;

    /**
     * A flag that allows the caller of the iterator builder to enforce lazy load
     * on or off.
     */
    private Boolean              loadLazyEntities  = null;

    /**
     * If true, then user attempted to set a cursor that uses a hashmap Attribute but
     * no match was found.
     */
    private boolean noMatchForHashMapAttribute = false;
    private boolean usingAttributeHashKey = false;

    IteratorBuilder(ObjectInstance oi)
    {
        objectInstance = oi;
    }

    private LazyLoadInfo determineIfLazyLoadNeeded()
    {
        if ( loadLazyEntities != null && ! loadLazyEntities )
            return null;

        if ( objectInstance.isIgnoreLazyLoadEntities() )
            return null;

        // Determine if we are going to lazy-load entities.
        if ( targetEntityDef != null )
        {
            LazyLoadConfig targetLazyLoadConfig = targetEntityDef.getLazyLoadConfig();

            // We're looking for a particular LodDef.  Are any of the entities between
            // the target and the scoping entity flagged as lazy load?
            if ( ! targetLazyLoadConfig.hasLazyLoadParent() )
                return null;  // No, so lazy load isn't needed.

            // Determine the scoping LodDef.  We'll initialize it to be the root
            // LodDef.
            EntityDef scopingEntityDef = objectInstance.getRootEntityInstance().getEntityDef();
            if ( scopingInstance != null ) // Has a scoping entity been specified?
                scopingEntityDef = scopingInstance.getEntityDef();

            LazyLoadInfo lazyLoadInfo = new LazyLoadInfo( objectInstance );

            // Find the LodDef between target and scoping that is lazy-loaded.
            EntityDef lazyLoadEntityDef;

            for ( lazyLoadEntityDef = targetEntityDef;
                  lazyLoadEntityDef != scopingEntityDef;
                  lazyLoadEntityDef = lazyLoadEntityDef.getParent() )
            {
                if ( lazyLoadEntityDef.getLazyLoadConfig().isLazyLoad() )
                {
                    // lazyLoadEntityDef is flagged for lazy load.  This means that when we
                    // access the parent we need to lazy load lazyLoadEntityDef.  Store this
                    // in the lazyLoadInfo.
                    lazyLoadInfo.parentLazyLoad.put( lazyLoadEntityDef.getParent(), lazyLoadEntityDef );
                }
                else
                {
                    // If the lazyLoadLodDef is the scoping instance then this path has already
                    // been lazy-loaded so we don't need to attempt lazy loading.
                    if ( lazyLoadEntityDef == scopingEntityDef )
                        return null;
                }
            }

            // Did we find any entities that need to be lazy loaded?
            if ( lazyLoadInfo.parentLazyLoad.size() == 0 )
                return null;

            // LazyLoadInfo has a new view to be used later.  Set the cursors to point to
            // the scoping or initial instance.
            if ( scopingInstance != null )
            {
                lazyLoadInfo.view.cursor( scopingInstance.getEntityDef() ).setCursor( scopingInstance );

                // Make sure we lazy-load any child instances of the scopingInstance.
                for ( EntityDef childLazyLoad : lazyLoadInfo.parentLazyLoad.get( scopingEntityDef ) )
                    scopingInstance.lazyLoadChild( lazyLoadInfo.view, childLazyLoad );
            }
            else
            if ( initialInstance != null )
            {
                // Find the parent of initialInstance that matches the LazyLoad parent of targetEntityDef.
                // We can't just use initialInstance because when we're setting parent cursors they may
                // be currently set to some other ancestor path, which will itself trigger a lazy load down
                // the other path.
                for ( EntityInstanceImpl ei = initialInstance; ei != null; ei = ei.getParent() )
                {
                    if ( ei.getEntityDef() == targetLazyLoadConfig.getLazyLoadParent() )
                    {
                        lazyLoadInfo.view.cursor( ei.getEntityDef() ).setCursor( ei );
                    }
                }
            }

            // If we get here then all conditions required for lazy load are met.
            return lazyLoadInfo;
        }

        // If we get here and loadLazyEntities is null then the iterator wasn't
        // explicitly set to allow lazyLoading, so throw an error.  At some point
        // this check can be removed.
        if ( loadLazyEntities == null )
            throw new ZeidonException( "Implement logic with null target LodDef" );

        // If we get here then we must be looping through all the entities in the
        // OI.  This means we need to load all lazyload entities.
        LazyLoadInfo lazyLoadInfo = new LazyLoadInfo( objectInstance );
        lazyLoadInfo.allEntities = true;
        return lazyLoadInfo;
    }

    /**
     * Attempt to find a non-dropped instance that is a twin of droppedInstance.
     *
     * @param droppedInstance
     * @return Either a valid (i.e. non-dropped) EI or droppInstance if one isn't found.
     */
    private EntityInstanceImpl findNonDroppedInstance( EntityInstanceImpl droppedInstance )
    {
        assert droppedInstance.isDropped() : "Expecing dropped instance";

        EntityInstanceImpl search = droppedInstance.getNextTwin();
        while ( search != null && search.isDropped() )
            search = search.getNextTwin();

        if ( search != null )
            return search;

        // We didn't find one after droppedInstance.  Try the previous ones.
        search = droppedInstance.getPrevTwin();
        while ( search != null && search.isDropped() )
            search = search.getPrevTwin();

        if ( search != null )
            return search;

        // If search is null then there are no non-dropped twins of droppedInstance.  Try
        // searching starting from the parent.
        EntityInstanceImpl parent = droppedInstance.getParent();
        if ( parent == null )
        {
            // If we get here then droppedInstance is a root.  Since we are
            // looking for a valid non-dropped EI then get the first EI.
            return objectInstance.getRootEntityInstance();
        }

        if ( parent.isDropped() )  // If parent is also dropped then forget it.
            return droppedInstance;

        // Loop through the children of the ei to find a matching EI.
        for ( EntityInstanceImpl ei : parent.getDirectChildren( true, false ) )
        {
            if ( ei.getEntityDef() == droppedInstance.getEntityDef() )
            {
                // We found a match!  This is a valid EI that is the same entity
                // type as droppedInstance and shouldn't be flagged as dropped.
                assert ! ei.isDropped();
                return ei;
            }
        }

        // If we get here we didn't find a valid twin so return the original EI.
        return droppedInstance;
    }

    /**
     * Builds an entity iterator using the options previously set.
     *
     * @return
     */
    EntityIterator<EntityInstanceImpl> build()
    {
        // If noMatch is true, then user attempted to find a match using a hashkey attribute but no
        // match was found.  Return an empty iterator to indicate this.
        // TODO: Is this the best way to handle no match?
        if ( noMatchForHashMapAttribute )
            return s_emptyIterator;

        EntityTraverser traverser;

        if ( directChildren )
        {
            if ( currentInstance == null )
            {
                currentInstance = scopingInstance.getNextHier();
                if ( currentInstance == null || currentInstance.getLevel() <= scopingInstance.getLevel() )
                    return s_emptyIterator;
            }

            traverser = new DirectChildTraverser( scopingInstance, false );
        }
        else
        if ( scopeOi )
        {
            traverser = s_hierTraverser;

            if ( currentInstance == null )
            {
                currentInstance = initialInstance;
                if ( setLast && currentInstance != null )
                    currentInstance = currentInstance.getLastChildHier();
            }
        }
        else
        if ( targetEntityDef != null &&     // If targetEntityDef == null then we must be looping hierarchically.
             ( scopingInstance == null  || targetEntityDef.getParent() == null ||
               scopingInstance.getEntityDef() == targetEntityDef.getParent() ) )
        {
            traverser = s_nextTwinTraverser;

            // If currentInstance is then we should be setting cursor to First/Last.
            if ( currentInstance == null )
            {
                if ( initialInstance == null && targetEntityDef.getParent() == null )
                    initialInstance = objectInstance.getRootEntityInstance();

                if ( initialInstance != null && initialInstance.isDropped() )
                {
                    // If we get here then the initial instance has been dropped and thus
                    // removed from the chains.  Scan through the twins to see if we can
                    // find a non-dropped instance.
                    initialInstance = findNonDroppedInstance( initialInstance );
                }

                if ( initialInstance != null && targetEntityDef != initialInstance.getEntityDef()
                                             && targetEntityDef != initialInstance.getBaseEntityDef() )
                {
                    // If we get here then the caller indicated they want scoping but it is scoping using the parent EI,
                    // which really amounts to no scoping.  However, the initialInstance references the parent
                    // scoping EI.  Find the first EI matching targetEntityDef under the current initialInstance.
                    assert targetEntityDef.getParent() == initialInstance.getEntityDef();
                    int level = initialInstance.getLevel();
                    initialInstance = initialInstance.getNextHier();
                    while ( initialInstance != null && initialInstance.getEntityDef() != targetEntityDef && initialInstance.getLevel() > level )
                    {
                        initialInstance = initialInstance.getLastTwin().getLastChildHier().getNextHier();
                    }

                    if ( initialInstance != null && initialInstance.getLevel() <= level )
                        initialInstance = null;

                    assert initialInstance == null || initialInstance.getEntityDef() == targetEntityDef;
                }

                currentInstance = initialInstance;
                if ( currentInstance != null )
                {
                    if ( setLast )
                        currentInstance = currentInstance.getLastTwin();
                    else
                        currentInstance = currentInstance.getFirstTwin();
                }
            }
        }
        else
        {
            traverser = new HierTraverser( scopingInstance, includeHierParent );
            if ( currentInstance == null )
            {
                if ( setLast )
                    currentInstance = scopingInstance.getLastChildHier();
                else
                    currentInstance = scopingInstance;
            }
        }

        if ( currentInstance == null )
            return s_emptyIterator;

        if ( comparator == null )
        {
            if ( allowHidden )
                comparator = s_hiddenComparator;
            else
                comparator = s_nonHiddenComparator;
        }

        // Returns null if no lazy loaded is needed.
        LazyLoadInfo lazyLoadInfo = determineIfLazyLoadNeeded();

        InternalEntityIteratorImpl iterator =
                new InternalEntityIteratorImpl( currentInstance,
                                                targetEntityDef,
                                                traverser,
                                                comparator,
                                                attributeValueList,
                                                setLast,
                                                usingAttributeHashKey,
                                                lazyLoadInfo );
        if ( setNext )
        {
            // The starting point was set by the builder so we just need to move the iterator to the next/prev instance.
            if ( setLast )
                iterator.prev();
            else
                iterator.next();
        }
        else
        if ( ! iterator.matchesCriteria() )
        {
            if ( setLast )
                iterator.prev();
            else
                iterator.next();

            // Indicate that there is no prior instance
            iterator.resetPriorInstance();
        }
        else
        if ( setLast )
        {
            // If we get here then the current instance matches the criteria so we don't need to call .prev() but
            // we need to indicate that we're set up to go backwards.
            iterator.setPrev();
        }

        iterator.setCursor( cursor );

        return iterator;
    }

    static EntityIterator<EntityInstanceImpl> build( EntityIterator<EntityInstanceImpl> source, EntityCursorImpl newCursor )
    {
        if ( source instanceof EmptyIterator )
            return s_emptyIterator;

        return new InternalEntityIteratorImpl( (InternalEntityIteratorImpl) source, newCursor );
    }

    IteratorBuilder forTwinsOf( EntityInstanceImpl initialInstance )
    {
        this.initialInstance = initialInstance;
        return this;
    }

    IteratorBuilder withScoping( EntityInstanceImpl scopingInstance )
    {
        if ( scopeOi )
            throw new ZeidonException("Mixing OI scoping with normal entity scoping" );

        this.scopingInstance = scopingInstance;
        this.initialInstance = scopingInstance;
        return this;
    }

    IteratorBuilder withOiScoping( ObjectInstance oi )
    {
        if ( scopingInstance != null )
            throw new ZeidonException("Mixing OI scoping with normal entity scoping" );

        scopeOi = true;
        initialInstance = oi.getRootEntityInstance();
        return this;
    }

    /**
     * Limit the entities found to those matching this EntityDef.  This is normally set except for
     * hierarchical cursors.
     *
     * The entityDef may be null, which means we'll accept all entities.
     *
     * @param entityDef
     * @return
     */
    IteratorBuilder forEntityDef( EntityDef entityDef )
    {
        targetEntityDef = entityDef;
        return this;
    }

    /**
     * Loops through all the direct children of parent, even if they have different entity names.
     *
     * NOTE: this will *NOT* load lazy-load entities if they haven't already
     * been loaded.
     *
     * @param parent
     * @return
     */
    IteratorBuilder forDirectChildren( EntityInstanceImpl parent )
    {
        directChildren = true;
        scopingInstance = parent;
        return this;
    }

    IteratorBuilder containedInSet( Set<EntityInstance> set )
    {
        if ( comparator != null )
            throw new ZeidonException( "Comparitor already set" );

        comparator = new InSetComparator( set );
        return this;
    }

    IteratorBuilder allowHidden()
    {
        allowHidden = true;
        return this;
    }

    IteratorBuilder allowHidden( boolean allowHidden )
    {
        if ( allowHidden )
            allowHidden();

        return this;
    }

    /**
     * This is used for hier cursors and will tell the logic to include the parent
     * instance in the iterator.
     *
     * @return
     */
    IteratorBuilder includeHierParent( boolean include )
    {
        includeHierParent = include;
        return this;
    }

    /**
     * Initializes the iterator to the last entity and for navigating using getPrev().
     *
     * @return
     */
    IteratorBuilder setLast()
    {
        setLast = true;
        return this;
    }

    /**
     * Set whether to load lazy entities if necessary.
     *
     * @param lazyLoad
     * @return
     */
    IteratorBuilder setLazyLoad( boolean lazyLoad )
    {
        loadLazyEntities = lazyLoad;
        return this;
    }

    /**
     * Starts iteration with instance instead of the first one.
     *
     * @param instance
     * @return
     */
    IteratorBuilder currentInstance( EntityInstanceImpl instance )
    {
        currentInstance = instance;
        setNext = true;
        return this;
    }

    /**
     * When next() or prev() is called this cursor will be set.
     *
     * @param cursor
     * @return
     */
    IteratorBuilder setCursor( EntityCursorImpl cursor )
    {
        this.cursor = cursor;
        return this;
    }

    private void setHashKeyAttribute( AttributeDef attributeDef, Object value )
    {
        AttributeHashKeyMap map;

        switch ( attributeDef.getHashKeyType() )
        {
            case GLOBAL:
                map = objectInstance.getAttributeHashkeyMap();
                break;

            case UNDER_PARENT:
                EntityInstanceImpl scoping = scopingInstance;

                // If scoping and the initialInstance are null then we should be
                // dealing with a NULL cursor so no match can be found.
                if ( scoping == null && initialInstance == null )
                {
                    noMatchForHashMapAttribute = true;
                    return;
                }

                if ( scoping == null )
                    scoping = initialInstance.getParent();

                if ( ! scoping.getEntityDef().equals( attributeDef.getHashKeyParent() ) )
                    return;  // We can only match if scoping entity matches hashkey parent.

                map = scoping.getAttributeHashkeyMap();
                break;

            default:
                throw new ZeidonException( "Unknown HashKeyType %s", attributeDef.getHashKeyType() );
        }

        EntityInstanceImpl ei = map.getEntityInstanceUsingHashAttribute( attributeDef, value );
        if ( ei == null )
            // There is no entity that matches the attribute value.
            noMatchForHashMapAttribute = true;
        else
        {
            // We found a matching EI, so we'll set the current instance to start with it.
            currentInstance = ei;
        }
    }

    /**
     * Returns only entities that match this attribute value.
     *
     * @param attributeDef
     * @param value
     * @return
     */
    IteratorBuilder withAttributeValue( AttributeDef attributeDef, Object value )
    {
        if ( attributeValueList == null )
            attributeValueList = new ArrayList<IteratorBuilder.AttributeValue>();

        attributeValueList.add( new AttributeValue( attributeDef, value ) );

        if ( attributeDef.getHashKeyType() != AttributeHashKeyType.NONE )
        {
            setHashKeyAttribute( attributeDef, value );
            usingAttributeHashKey  = true;
        }

        return this;
    }

    private static class LazyLoadInfo
    {
        /**
         * This keeps track of entities that have children that need to be lazy-loaded.
         */
        private final Multimap<EntityDef, EntityDef> parentLazyLoad = HashMultimap.create();

        // If true, then all entities should be lazy loaded.
        private boolean allEntities = false;

        // This is the view required for lazy loading.
        private final ViewImpl view;

        private LazyLoadInfo( ObjectInstance oi )
        {
            view = new ViewImpl( oi );
        }
    }

    private static class InternalEntityIteratorImpl implements EntityIterator<EntityInstanceImpl>
    {
        private enum Direction { NEXT, PREV };

        /**
         * The instance that will be returned by next()/prev().  Depends on the value of 'direction'.
         */
        private       EntityInstanceImpl   nextInstance;
        private       EntityInstanceImpl   currentInstance;
        private final EntityDef           targetEntityDef;
        private final EntityTraverser      traverser;
        private final EntityComparator     comparator;
        private final boolean              usingAttributeHashKey;
        private       EntityCursorImpl     cursor;
        private final List<AttributeValue> attributeValueList;

        /**
         * The current "direction" of the cursor.
         */
        private       Direction            direction;

        /**
         * Contains information need to correctly lazy load child entities.  If null then
         * lazy loading is ignored.
         */
        private final LazyLoadInfo         lazyLoadInfo;

        /**
         * Creates a new iterator for a new cursor that copies the settings from a source iterator.
         *
         * @param source
         * @param newCursor
         */
        private InternalEntityIteratorImpl( InternalEntityIteratorImpl source, EntityCursorImpl newCursor )
        {
            nextInstance = source.nextInstance;
            currentInstance = source.currentInstance;
            targetEntityDef = source.targetEntityDef;
            traverser = source.traverser;
            comparator = source.comparator;
            direction = source.direction;
            attributeValueList = source.attributeValueList;
            usingAttributeHashKey = source.usingAttributeHashKey;
            cursor = newCursor;
            lazyLoadInfo = source.lazyLoadInfo;
        }

        private InternalEntityIteratorImpl( EntityInstanceImpl   currentInstance,
                                            EntityDef           targetEntityDef,
                                            EntityTraverser      traverser,
                                            EntityComparator     comparator,
                                            List<AttributeValue> attributeValueList,
                                            boolean              setLast,
                                            boolean              usingAttributeHashKey,
                                            LazyLoadInfo         lazyLoadInfo )
        {
            super();
            this.nextInstance = currentInstance;
            this.targetEntityDef = targetEntityDef;
            this.traverser = traverser;
            this.comparator = comparator;
            this.attributeValueList = attributeValueList;
            this.usingAttributeHashKey = usingAttributeHashKey;
            this.lazyLoadInfo = lazyLoadInfo;

            direction = setLast ? Direction.PREV : Direction.NEXT;
        }

        /**
         * The default direction of this iterator is NEXT.  Following allows the builder to set the initial
         * direction to PREV.
         */
        private void setPrev()
        {
            direction = Direction.PREV;
        }

        private void resetPriorInstance()
        {
            currentInstance = null;
        }

        private void setCursor( EntityCursorImpl cursor )
        {
            this.cursor = cursor;
        }

        private boolean matchesCriteria()
        {
            if ( nextInstance == null )
                return false;

            if ( ! comparator.isEqual( nextInstance ) )
                return false;

            // If we're looking for a specific instance then targetEntityDef is not null.
            if ( targetEntityDef != null )
            {
                // Same view entities?
                if ( nextInstance.getEntityDef() != targetEntityDef )
                {
                    // No?  Last chance--are they recursive parents?
                    if ( nextInstance.getEntityDef().getRecursiveParentEntityDef() != targetEntityDef )
                        return false;  // No match is found.
                }
            }

            if ( attributeValueList != null )
            {
                for ( AttributeValue attrib : attributeValueList )
                {
                    //TODO: Some day we can support attribute values from parent instances.
                    // We use entity tokens because this could be a recursive structure.
                    assert attrib.getEntityDef().getErEntityToken() == nextInstance.getEntityDef().getErEntityToken();

                    if ( nextInstance.compareAttribute( attrib.getAttributeDef(), attrib.getValue() ) != 0 )
                        return false;
                }
            }

            return true;
        }

        /* (non-Javadoc)
         * @see java.util.Iterator#hasNext()
         */
        @Override
        public boolean hasNext()
        {
            // If current direction is PREV, call findNext() to change it to find
            // the next instance.
            if ( direction == Direction.PREV )
                findNext();

            return nextInstance != null;
        }

        /**
         * Sets nextInstance to the next instance.  currentInstance will then point to
         * the current instance of the cursor.
         */
        private void findNext()
        {
            if ( direction == Direction.PREV )
            {
                // direction = PREV, so we need to reset the cursor direction.  findNext()
                // finds the next instance after nextInstance, so we'll reset it it to
                // be currentInstance.
                nextInstance = currentInstance;
                direction = Direction.NEXT;
            }

            // Save the currentInstance so we can return it later.
            currentInstance = nextInstance;

            if ( currentInstance == null )
                return;

            // If we're using an attribute hash key then we already found the matching
            // entity and there should be no other matching entities.
            if ( usingAttributeHashKey )
            {
                nextInstance = null;
                return;
            }

            while ( ( nextInstance = traverser.getNext( nextInstance ) ) != null )
            {
                if ( lazyLoadInfo != null )
                {
                    // nextInstance is determined to be the parent of a child instance that requires
                    // lazy loading.  Load all children that require it.
                    lazyLoadInfo.view.cursor( nextInstance.getEntityDef() ).setCursor( nextInstance );
                    if ( lazyLoadInfo.allEntities )
                    {
                        for ( EntityDef child : nextInstance.getEntityDef().getChildren() )
                        {
                            if ( child.getLazyLoadConfig().isLazyLoad() )
                                nextInstance.lazyLoadChild( lazyLoadInfo.view, child );
                        }
                    }
                    else
                    {
                        for ( EntityDef child : lazyLoadInfo.parentLazyLoad.get( nextInstance.getEntityDef() ) )
                            nextInstance.lazyLoadChild( lazyLoadInfo.view, child );
                    }
                }

                if ( matchesCriteria() )
                    break;

                // If we're looking for a specific entity then we can optimize a bit by
                // skipping past child entities
                // EXCEPT FOR
                // when the target entities are part of a recursive chain.
                // TODO: We really need to look to see if *any* parent of targetEntityDef
                // is recursive.
                if ( targetEntityDef != null &&
                     ( ! targetEntityDef.isRecursive() && ! targetEntityDef.isRecursiveParent() ) )
                {
                    if ( nextInstance.getLevel() > targetEntityDef.getLevel() )
                        nextInstance = nextInstance.getLastTwin().getLastChildHier();
                }
            }
        }

        /* (non-Javadoc)
         * @see java.util.Iterator#next()
         */
        @Override
        public EntityInstanceImpl next()
        {
            // If we were going backwards then reset currentInstance.
            if ( direction == Direction.PREV )
                findNext();

            findNext();

            if ( cursor != null )
                cursor.setCursor( currentInstance );

            return currentInstance;
        }

        /* (non-Javadoc)
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove()
        {
            throw new ZeidonException("remove() currently unsupported.");
        }

        /* (non-Javadoc)
         * @see com.quinsoft.zeidon.InternalEntityIterator#hasPrev()
         */
        @Override
        public boolean hasPrev()
        {
            // If current direction is NEXT, call findPrev() to change it to find
            // the prev instance.
            if ( direction == Direction.NEXT )
                findPrev();

            return nextInstance != null;
        }

        private void findPrev()
        {
            if ( direction == Direction.NEXT )
            {
                // direction = NEXT, so we need to reset the cursor direction.
                nextInstance = currentInstance;
                direction = Direction.PREV;
            }

            // Save the currentInstance so we can return it later.
            currentInstance = nextInstance;

            if ( currentInstance == null )
                return;

            // If we're using an attribute hash key then we already found the matching
            // entity and there should be no other matching entities.
            if ( usingAttributeHashKey )
            {
                nextInstance = null;
                return;
            }

            while ( ( nextInstance = traverser.getPrev( nextInstance ) ) != null )
            {
                if ( matchesCriteria() )
                    break;
            }
        }

        /* (non-Javadoc)
         * @see com.quinsoft.zeidon.InternalEntityIterator#prev()
         */
        @Override
        public EntityInstanceImpl prev()
        {
            // If we were going forwards then reset currentInstance.
            if ( direction == Direction.NEXT )
                findPrev();

            findPrev();

            if ( cursor != null )
                cursor.resetChildCursors( currentInstance );

            return currentInstance;
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

    private static class EmptyIterator implements EntityIterator<EntityInstanceImpl>
    {
        @Override
        public boolean hasNext()
        {
            return false;
        }

        @Override
        public EntityInstanceImpl next()
        {
            return null;
        }

        @Override
        public void remove()
        {
        }

        @Override
        public EntityInstanceImpl prev()
        {
            return null;
        }

        @Override
        public boolean hasPrev()
        {
            return false;
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

    private static class AttributeValue
    {
        private final AttributeDef attributeDef;
        private final Object        value;

        private AttributeValue(AttributeDef attributeDef, Object value)
        {
            super();
            this.attributeDef = attributeDef;
            this.value = value;
        }

        /**
         * @return the attributeDef
         */
        AttributeDef getAttributeDef()
        {
            return attributeDef;
        }

        /**
         * @return the value
         */
        Object getValue()
        {
            return value;
        }

        EntityDef getEntityDef()
        {
            return attributeDef.getEntityDef();
        }
    }
}
