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
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * A traverser is like a static iterator.  The getNext() method requires that the
 * current entity be passed in.
 * 
 * @author DG
 *
 */
interface EntityTraverser
{
    EntityInstanceImpl getNext( EntityInstanceImpl e );
    EntityInstanceImpl getPrev( EntityInstanceImpl e );
    EntityInstanceImpl getHeader();

    /**
     * getNext() returns the next twin.  This can be either with or without scoping.
     * 
     * @author DG
     *
     */
    class NextTwinTraverser implements EntityTraverser
    {
        private final EntityInstanceImpl header;
        private final EntityInstanceImpl scopingEntity;
        
        NextTwinTraverser(EntityInstanceImpl twin, boolean useFirstTwin )
        {
            if ( useFirstTwin && twin != null )
                twin = twin.getFirstTwin();

            this.header = new HeaderInstance( twin );
            this.scopingEntity = null;
        }

        NextTwinTraverser(EntityDef entityDef, EntityInstanceImpl scopingEntity )
        {
            this.header = new HeaderInstance( scopingEntity.getFirstChildMatchingEntityDef( entityDef ) );
            this.scopingEntity = scopingEntity;
        }

        /**
         * Creates a generic traverser that can be used by entity cursors.
         */
        NextTwinTraverser()
        {
            this.header = null;
            this.scopingEntity = null;
        }

        @Override
        public EntityInstanceImpl getNext(EntityInstanceImpl e)
        {
            EntityInstanceImpl next = e.getNextTwin();
            if ( next == null && scopingEntity != null )
            {
                EntityDef entityDef = e.getEntityDef();
                int level = e.getLevel();
                for ( next = e.getNextHier(); next != null; next = next.getNextHier() )
                {
                    if ( next.getEntityDef() == entityDef && next.getLevel() == level )
                        break; // We found what we're looking for.
                    
                    if ( next.getLevel() <= scopingEntity.getLevel() )
                    {
                        // We've gone past the scoping entity so we've come to the end.
                        next = null;
                        break;
                    }
                    
                    // If we're looking at a descendant of 'e' then we can short-circuit
                    // some of the entities by skipping to the last twin.
                    if ( next.getLevel() > level )
                        next = next.getLastTwin().getLastChildHier();
                }
            }
            
            return next;
        }

        @Override
        public EntityInstanceImpl getPrev(EntityInstanceImpl e)
        {
            EntityInstanceImpl prev = e.getPrevTwin();
            if ( prev == null && scopingEntity != null )
            {
                EntityDef entityDef = e.getEntityDef();
                int level = e.getLevel();
                for ( prev = e.getPrevHier(); prev != null; prev = prev.getPrevHier() )
                {
                    if ( prev.getEntityDef() == entityDef && prev.getLevel() == level )
                        break; // We found what we're looking for.
                    
                    if ( prev.getLevel() <= scopingEntity.getLevel() )
                    {
                        prev = null;
                        break;
                    }
                }
            }
            return prev;
        }

        @Override
        public EntityInstanceImpl getHeader()
        {
            return header;
        }
    }

    /**
     * getNext() returns the previous twin.  Used to cycle backwards.
     * 
     * @author DG
     *
     */
    class PrevTwinTraverser implements EntityTraverser
    {
        // We'll use a NextTwin traverser and just return the opposite next/prev.
        private final NextTwinTraverser traverser;
        
        PrevTwinTraverser(EntityInstanceImpl twin)
        {
            traverser = new NextTwinTraverser( twin, true );
        }

        /**
         * Creates a traverser that can be used by entity cursors.
         */
        PrevTwinTraverser()
        {
            traverser = new NextTwinTraverser();
        }

        @Override
        public EntityInstanceImpl getNext(EntityInstanceImpl e)
        {
            return traverser.getPrev( e );
        }

        @Override
        public EntityInstanceImpl getPrev(EntityInstanceImpl e)
        {
            return traverser.getNext( e );
        }

        @Override
        public EntityInstanceImpl getHeader()
        {
            return traverser.getHeader();
        }
    }

    /**
     * A traverser that runs through all the immediate child entities of parent.
     * @author DG
     *
     */
    class DirectChildTraverser implements EntityTraverser
    {
        private EntityInstanceImpl parent;
        private EntityInstanceImpl child;
        private final boolean      ignoreHidden;
        
        DirectChildTraverser(EntityInstanceImpl parent)
        {
            this( parent, false );
        }

        DirectChildTraverser(EntityInstanceImpl parent, boolean ignoreHidden )
        {
            super();
            this.parent = parent;
            this.ignoreHidden = ignoreHidden;
            
            child = parent.getNextHier();
            if ( child != null && child.getLevel() <= parent.getLevel() )
                child = null;
        }

        /**
         * Get the next direct child.  Note that 'e' isn't used.
         * Maybe we should do something a little cleaner.
         */
        @Override
        public EntityInstanceImpl getNext(EntityInstanceImpl e)
        {
            while ( child != null )
            {
                if ( child.getNextTwin() != null )
                    child = child.getNextTwin();
                else
                {
                    child = child.getNextHier();
                    while ( child != null && child.getLevel() > parent.getLevel() + 1 )
                    {
                        if ( child.getNextTwin() != null )
                            child = child.getNextTwin();
                        else
                            child = child.getNextHier();
                    }
                }
    
                if ( child != null && child.getLevel() <= parent.getLevel() )
                    child = null;
                
                // If we're not ignoring hidden entities then return the one we have.
                if ( child == null || ! ignoreHidden || ! child.isHidden() )
                    return child;
            }

            return child;
        }

        @Override
        public EntityInstanceImpl getHeader()
        {
            return child;
        }

        /* (non-Javadoc)
         * @see com.quinsoft.zeidon.standardoe.EntityTraverser#getPrev(com.quinsoft.zeidon.standardoe.EntityInstanceImpl)
         */
        @Override
        public EntityInstanceImpl getPrev(EntityInstanceImpl e)
        {
            throw new UnsupportedOperationException( "This has not been implemented yet" );
        }
    }
    
    class HierTraverser implements EntityTraverser
    {
        private final EntityInstanceImpl entityInstance;
        private final int                rootLevel;
        private final boolean            includeParent;
        
        /**
         * Traverses through all entity instances in hier order.
         * @param oi
         */
        HierTraverser( ObjectInstance oi )
        {
            entityInstance = oi.getRootEntityInstance();
            rootLevel = -1;
            includeParent = false;
        }
        
        HierTraverser( )
        {
            entityInstance = null;
            rootLevel = -1;
            includeParent = false;
        }
        
        /**
         * Traverse through the child entities of 'parent'.
         * 
         * @param parent
         * @param includeParent - if true then the parent will be included as part of the
         *                        traversal.
         */
        HierTraverser( EntityInstanceImpl parent, boolean includeParent )
        {
            this( parent, includeParent, parent.getLevel() );
        }

        /**
         * Traverse through entities in hier order starting with ei and continuing until
         * the end of the OI or we find an entity with a root level < rootLevel.
         * 
         * @param ei
         * @param includeParent
         * @param rootLevel if -1 then continue until the end no matter what.
         */
        HierTraverser( EntityInstanceImpl ei, boolean includeParent, int rootLevel )
        {
            if ( includeParent )
                entityInstance = new HeaderInstance( ei );
            else
                entityInstance = ei;
            this.rootLevel = rootLevel;
            this.includeParent = includeParent;
        }
        
        @Override
        public EntityInstanceImpl getHeader()
        {
            return entityInstance;
        }

        @Override
        public EntityInstanceImpl getNext( EntityInstanceImpl ei )
        {
            ei = ei.getNextHier();
            if ( ei == null ) 
                return null;

            if ( includeParent && entityInstance.getNextHier() == ei )
                return ei;
            
            // If the entity has a level <= rootLevel then it is not a child of the original
            // parent so we're done.
            if ( ei.getLevel() <= rootLevel )
                return null;
            
            return ei;
        }

        /* (non-Javadoc)
         * @see com.quinsoft.zeidon.standardoe.EntityTraverser#getPrev(com.quinsoft.zeidon.standardoe.EntityInstanceImpl)
         */
        @Override
        public EntityInstanceImpl getPrev(EntityInstanceImpl e)
        {
            throw new UnsupportedOperationException( "This has not been implemented yet" );
        }
    }

    class NextTwinWithinOiTraverser implements EntityTraverser
    {
        private final EntityDef         entityDef;
        private       EntityInstanceImpl current;
        
        /**
         * 
         */
        public NextTwinWithinOiTraverser( ObjectInstance oi, EntityDef entityDef )
        {
            this.entityDef = entityDef;
            current = new HeaderInstance( oi.getRootEntityInstance() );
        }
        
        /* (non-Javadoc)
         * @see com.quinsoft.zeidon.standardoe.EntityTraverser#getNext(com.quinsoft.zeidon.standardoe.EntityInstanceImpl)
         */
        @Override
        public EntityInstanceImpl getNext(EntityInstanceImpl e)
        {
            if ( current.getEntityDef() == entityDef && current.getNextTwin() != null )
            {
                current = current.getNextTwin();
                return current;
            }
            
            while ( true )
            {
                current = current.getNextHier();
                if ( current == null )
                    return null;
                
                if ( current.getEntityDef() == entityDef )
                    return current;
            }
        }

        /* (non-Javadoc)
         * @see com.quinsoft.zeidon.standardoe.EntityTraverser#getPrev(com.quinsoft.zeidon.standardoe.EntityInstanceImpl)
         */
        @Override
        public EntityInstanceImpl getPrev(EntityInstanceImpl e)
        {
            if ( current.getEntityDef() == entityDef && current.getPrevTwin() != null )
            {
                current = current.getPrevTwin();
                return current;
            }
            
            while ( true )
            {
                current = current.getPrevHier();
                if ( current == null )
                    return null;
                
                if ( current.getEntityDef() == entityDef )
                    return current;
            }
        }

        /* (non-Javadoc)
         * @see com.quinsoft.zeidon.standardoe.EntityTraverser#getHeader()
         */
        @Override
        public EntityInstanceImpl getHeader()
        {
            return current;
        }
        
    }
    /**
     * This class is used to create temporary headers to make navigating entity
     * lists easier.
     * 
     * @author DG
     *
     */
    static class HeaderInstance extends EntityInstanceImpl
    {
        private HeaderInstance( EntityInstanceImpl entityInstance )
        {
            super( entityInstance.getEntityDef() );
            setPrevTwin( entityInstance );
            setNextTwin( entityInstance );
            setNextHier( entityInstance );
            setPrevHier( entityInstance );
            if ( entityInstance != null )
                setParent( entityInstance.getParent() );
        }
    }
}
