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
package com.quinsoft.zeidon.objectdefinition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.MapMaker;
import com.quinsoft.zeidon.CacheMap;
import com.quinsoft.zeidon.EventListener;
import com.quinsoft.zeidon.UnknownViewAttributeException;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.LazyLoadConfig.LazyLoadFlags;
import com.quinsoft.zeidon.utils.CacheMapImpl;
import com.quinsoft.zeidon.utils.EventStackTrace;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;

/**
 * @author DG
 *
 */
public class ViewEntity implements PortableFileAttributeHandler, CacheMap
{
    private ViewOd     viewOd;
    private ViewEntity prevHier;
    private ViewEntity nextHier;
    private ViewEntity parent;
    private ViewEntity prevSibling;
    private ViewEntity nextSibling;
    private String     name;
    private int        erEntityToken;
    private int        erRelToken;
    private boolean    erRelLink;  // RelLink direction.  True = '1' from the XOD file.
    private final int        level;
    private final int        entityNumber;
    private List<ViewEntity> children;
    private List<ViewEntity> childrenHier;
    private EventListener eventListener;
    private ArrayList<ViewAttribute> activateOrdering;
    private Integer    activateLimit;
    /**
     * List of the attributes in the order they are defined in the XOD file.
     */
    private final List<ViewAttribute> attributes = new ArrayList<ViewAttribute>();

    /**
     * Map of attributes by attribute name.
     */
    private final Map<String, ViewAttribute> attributeMap = new HashMap<String, ViewAttribute>();

    /**
     * Map of attributes by ER attribute token.
     */
    private final Map<Long, ViewAttribute> erAttributeMap = new HashMap<Long, ViewAttribute>();

    /**
     * This map keeps track of entities that have been checked to see if 'this' entity
     * is an attribute superset.
     * 	Key = View entity of 'child' entity.
     *  Value = true if 'this' entity is a superset.
     *
     *  This is maintained at run-time which is why we need it to be a concurrent map.
     */
    private final ConcurrentMap<ViewEntity, Boolean> attributeSuperset = new MapMaker().concurrencyLevel( 2 ).weakKeys().makeMap();

    private final CacheMap cacheMap = new CacheMapImpl();

    // Permission flags.
    private boolean    create  = false;
    private boolean    include = false;
    private boolean    exclude = false;
    private boolean    delete  = false;
    private boolean    update  = false;
    private boolean    includeSrc = false;

    private final List<ViewAttribute> keys;
    private ViewAttribute genKey;
    private ViewAttribute autoSeq;
    private Collection<ViewAttribute> hashKeyAttributes;
    private boolean    parentDelete = false;
    private boolean    restrictParentDelete = false;
    private boolean    checkRestrictedDelete = false;
    private boolean    derived = false;
    private boolean    derivedPath = false;
    private boolean    recursive = false;
    private boolean    duplicateEntity = false;
    private int        persistentAttributeCount;
    private int        workAttributeCount;
    private DataRecord dataRecord;
    private ViewEntity recursiveParentViewEntity = null;
    private int        minCardinality;
    private int        maxcardinality;
    private boolean    hasInitializedAttributes = false;

    /**
     * If this entity is a recursive parent then recursiveChild references the child.
     */
    private ViewEntity recursiveChild;

    // Flags to help debug OI.
    private boolean    debugIncrementalFlag; // If true, then pop up a message when we change an incremental flag.

    private final LazyLoadConfig lazyLoadConfig;

    public ViewEntity( ViewOd viewOd, int level )
    {
        this.viewOd = viewOd;
        this.entityNumber = viewOd.getEntityCount();
        this.level = level;
        keys = new ArrayList<ViewAttribute>();
        lazyLoadConfig = new LazyLoadConfig();
    }

    @Override
    public void setAttribute(PortableFileReader reader)
    {
        String attributeName = reader.getAttributeName();

        switch ( attributeName.charAt( 0 ) )
        {
            case 'A':
                if ( reader.getAttributeName().equals( "ACT_LIMIT" ))
                {
                    activateLimit = Integer.parseInt( reader.getAttributeValue() );
                }
                break;

            case 'C':
                if ( reader.getAttributeName().equals( "CREATE" ))
                {
                    create = reader.getAttributeValue().startsWith( "Y" );
                }
                else
                if ( reader.getAttributeName().equals( "CARDMAX" ))
                {
                    maxcardinality = Integer.parseInt( reader.getAttributeValue() );
                }
                else
                if ( reader.getAttributeName().equals( "CARDMIN" ))
                {
                    minCardinality = Integer.parseInt( reader.getAttributeValue() );
                }
                break;

            case 'D':
                if ( reader.getAttributeName().equals( "DELETE" ))
                {
                    delete = reader.getAttributeValue().startsWith( "Y" );
                }
                else
                if ( reader.getAttributeName().equals( "DERIVED" ))
                {
                    derived = true;
                    derivedPath = true;
                }
                else
                if ( reader.getAttributeName().equals( "DEBUGCHG" ))
                {
                    // Set up a listener to write stack trace when an entity is changed.
                    if ( StringUtils.startsWithIgnoreCase( reader.getAttributeValue(), "Y" ) )
                        eventListener = new EventStackTrace();
                }
                if ( reader.getAttributeName().equals( "DEBUGINCRE" ))
                {
                    debugIncrementalFlag = StringUtils.startsWithIgnoreCase( reader.getAttributeValue(), "Y" );
                }
                else
                if ( reader.getAttributeName().equals( "DUPENTIN" ))
                {
                    duplicateEntity = true;
                }

                break;

            case 'E':
                if ( reader.getAttributeName().equals( "ERENT_TOK" ))
                {
                    erEntityToken = Integer.parseInt( reader.getAttributeValue() );
                }
                else
                if ( reader.getAttributeName().equals( "ERREL_TOK" ))
                {
                    erRelToken = Integer.parseInt( reader.getAttributeValue() );
                }
                else
                if ( reader.getAttributeName().equals( "ERREL_LINK" ))
                {
                    erRelLink = reader.getAttributeValue().equals( "1" );
                }
                else
                if ( reader.getAttributeName().equals( "EXCLUDE" ))
                {
                    exclude = reader.getAttributeValue().startsWith( "Y" );
                }
                break;

            case 'I':
                if ( reader.getAttributeName().equals( "INCLUDE" ))
                {
                    include = reader.getAttributeValue().startsWith( "Y" );
                }
                else
                if ( reader.getAttributeName().equals( "INCLSRC" ))
                {
                    includeSrc = reader.getAttributeValue().startsWith( "Y" );
                }
                break;

            case 'L':
                if ( reader.getAttributeName().equals( "LAZYLOAD" ))
                {
                    getLazyLoadConfig().setFlag( LazyLoadFlags.IS_LAZYLOAD );
                    if ( getParent() == null )
                        throw new ZeidonException("LAZYLOAD is invalid for root entity" );

                    LazyLoadConfig parentConfig = getParent().getLazyLoadConfig();
                    parentConfig.setFlag( LazyLoadFlags.HAS_LAZYLOAD_CHILD );
                }
                break;

            case 'N':
                if ( reader.getAttributeName().equals( "NAME" ))
                {
                    name = reader.getAttributeValue().intern();
                }
                break;

            case 'P':
                if ( reader.getAttributeName().equals( "PDELETE" ))
                {
                    switch ( reader.getAttributeValue().charAt( 0 ) )
                    {
                        case 'D':
                            parentDelete = true;
                            break;

                        case 'R':
                            restrictParentDelete = true;
                            getParent().setCheckRestrictedDelete( true );
                            break;

                        // It looks like we're supposed to ignore 'E'.
                    }
                }
                break;

            case 'R':
                if ( reader.getAttributeName().equals( "RECURSIVE" ))
                {
                    // Check to see if this entity is recursive.
                    for ( ViewEntity search = parent; search != null; search = search.getParent() )
                    {
                        if ( search.getErEntityToken() == erEntityToken )
                        {
                            search.recursiveChild = this;
                            this.recursive = true;
                            this.recursiveParentViewEntity = search;
                            break;
                        }
                    }

                    if ( ! recursive )
                        throw new ZeidonException( "Internal error: Recursive flag is set but no recursive parent found. %s", this );
                }
                break;

            case 'U':
                if ( reader.getAttributeName().equals( "UPDATE" ))
                {
                    update = reader.getAttributeValue().startsWith( "Y" );
                }
                break;
        }
    }

    public String getName()
    {
        return name;
    }

    public int getErEntityToken()
    {
        return erEntityToken;
    }

    public ViewOd getViewOd()
    {
        return viewOd;
    }

    public void setViewOD(ViewOd viewOd)
    {
        this.viewOd = viewOd;
    }

    public ViewEntity getPrevHier()
    {
        return prevHier;
    }

    public void setPrevHier(ViewEntity prevHier)
    {
        this.prevHier = prevHier;
    }

    public ViewEntity getNextHier()
    {
        return nextHier;
    }

    public void setNextHier(ViewEntity nextHier)
    {
        this.nextHier = nextHier;
    }

    public ViewEntity getParent()
    {
        return parent;
    }

    void setParent(ViewEntity parent)
    {
        this.parent = parent;
        if ( parent.derivedPath )
            derivedPath = true;

        if ( parent.children == null )
            parent.children = new ArrayList<ViewEntity>();

        LazyLoadConfig parentConfig = parent.getLazyLoadConfig();
        if ( parentConfig.isLazyLoad() )
        {
            getLazyLoadConfig().setFlag( LazyLoadFlags.HAS_LAZYLOAD_PARENT );
            getLazyLoadConfig().setLazyLoadParent( getParent() );
        }
        else
        if ( parentConfig.hasLazyLoadParent() )
        {
            getLazyLoadConfig().setFlag( LazyLoadFlags.HAS_LAZYLOAD_PARENT );
            getLazyLoadConfig().setLazyLoadParent( parentConfig.getLazyLoadParent() );
        }

        parent.children.add( this );
    }

    public ViewEntity getPrevSibling()
    {
        return prevSibling;
    }

    public void setPrevSibling(ViewEntity prevSibling)
    {
        this.prevSibling = prevSibling;
    }

    public ViewEntity getNextSibling()
    {
        return nextSibling;
    }

    public void setNextSibling(ViewEntity nextSibling)
    {
        this.nextSibling = nextSibling;
    }

    public int getLevel()
    {
        return level;
    }

    void addViewAttribute( ViewAttribute viewAttribute )
    {
        attributes.add( viewAttribute );
        attributeMap.put( viewAttribute.getName(), viewAttribute );
        erAttributeMap.put( viewAttribute.getErAttributeToken(), viewAttribute );
    }

    public int getAttributeCount()
    {
        return attributes.size();
    }

    public ViewAttribute getAttribute( String attribName )
    {
        return getAttribute( attribName, true );
    }

    public ViewAttribute getAttribute( String attribName, boolean required )
    {
        ViewAttribute attrib = attributeMap.get( attribName );
        if ( attrib == null && required )
            throw new UnknownViewAttributeException( this, attribName );

        return attrib;
    }

    public ViewAttribute getAttribute( int attributeNumber )
    {
        if ( attributeNumber >= attributes.size() )
            throw new ZeidonException("Attribute index %d out of range for %s.",
                                      attributeNumber, viewOd.getName() );

        return attributes.get( attributeNumber );
    }

    public ViewAttribute getAttributeByErToken( long erToken )
    {
        return erAttributeMap.get( erToken );
    }

    public List<ViewAttribute> getAttributes()
    {
        return Collections.unmodifiableList( attributes );
    }

    public int getHierIndex()
    {
        return entityNumber;
    }

    @Override
    public String toString()
    {
        return viewOd.toString() + "." + name;
    }

    public boolean isParentDelete()
    {
        return parentDelete ;
    }

    /**
     * If true, then the parent of this entity cannot be deleted when this entity
     * instance exists.
     *
     * @return
     */
    public boolean isRestrictParentDelete()
    {
        return restrictParentDelete ;
    }

    /**
     * If this flag is true, make sure none of the child entities have
     * the parent delete restrict flag set when the entity is being deleted.
     *
     * @return
     */
    public boolean isCheckRestrictedDelete()
    {
        return checkRestrictedDelete;
    }

    private void setCheckRestrictedDelete( boolean b )
    {
        checkRestrictedDelete = b;
    }

    public int getChildCount()
    {
        return children == null ? 0 : children.size();
    }

    protected void setSiblingsForChildren()
    {
        if ( children == null )
            return;

        for ( int i = 0; i < children.size(); i++ )
        {
            if ( i > 0 )
                children.get( i ).setPrevSibling( children.get( i - 1 ) );

            if ( i < children.size() - 1 )
                children.get( i ).setNextSibling( children.get( i + 1 ) );

            children.get( i ).setSiblingsForChildren();
        }
    }

    public boolean isDerived()
    {
        return derived;
    }

    public boolean isPersistent()
    {
        return ( ! isDerived() ) && ( ! isDerivedPath() );
    }

    /**
     * Returns a list of the direct children of this entity.
     * @return
     */
    public List<ViewEntity> getChildren()
    {
        if ( children == null )
            return Collections.emptyList();

        return children;
    }

    /**
     * Returns a list of all the children under the current entity
     * in hier order.
     * @return
     */
    public synchronized List<ViewEntity> getChildrenHier()
    {
        if ( children == null )
            return Collections.emptyList();

        if ( childrenHier != null )
            return childrenHier;

        List<ViewEntity> list = new ArrayList<ViewEntity>();
        for ( ViewEntity child = this.getNextHier();
              child != null && child.getLevel() > this.getLevel();
              child = child.getNextHier() )
        {
            list.add( child );
        }

        childrenHier = list;

        return list;
    }

    /**
     * Returns the last child hierarchically under this entity.  If this entity has
     * no children, then returns itself.
     *
     * @return
     */
    public ViewEntity getLastChildHier()
    {
        if ( children == null )
            return this;

        List<ViewEntity> list = getChildrenHier();
        return list.get( list.size() - 1 );
    }

    public int getErRelToken()
    {
        return erRelToken;
    }

    public boolean isErRelLink()
    {
        return erRelLink;
    }

    public int getPersistentAttributeCount()
    {
        return persistentAttributeCount;
    }

    void setPersistentAttributeCount(int persistentAttributeCount)
    {
        this.persistentAttributeCount = persistentAttributeCount;
    }

    public int getWorkAttributeCount()
    {
        return workAttributeCount;
    }

    void setWorkAttributeCount(int workAttributeCount)
    {
        this.workAttributeCount = workAttributeCount;
    }

    public boolean isCreate()
    {
        return create;
    }

    public DataRecord getDataRecord()
    {
        return dataRecord;
    }

    void setDataRecord(DataRecord dataRecord)
    {
        getViewOd().setHasPhysicalMappings( true );
        this.dataRecord = dataRecord;
    }

    public ViewAttribute getGenKey()
    {
        return genKey;
    }

    public boolean isDerivedPath()
    {
        return derivedPath;
    }

    public boolean isInclude()
    {
        return include;
    }

    public boolean isIncludeSource()
    {
        return includeSrc;
    }

    public boolean isExclude()
    {
        return exclude;
    }

    public boolean isDelete()
    {
        return delete;
    }

    public boolean isUpdate()
    {
        return update;
    }

    void setGenKey(ViewAttribute viewAttribute)
    {
        genKey = viewAttribute;
    }

    public boolean isRecursive()
    {
        return recursive;
    }

    public boolean isRecursiveParent()
    {
        return recursiveChild != null;
    }

    /**
     * If this ViewEntity is a recursive parent then this returns the recursive child.
     *
     * @return
     */
    public ViewEntity getRecursiveChild()
    {
        return recursiveChild;
    }

    public boolean isDebugIncremental()
    {
        return debugIncrementalFlag;
    }

    void setAutoSeq(ViewAttribute autoSeq)
    {
        this.autoSeq = autoSeq;
    }

    public ViewAttribute getAutoSeq()
    {
        return autoSeq;
    }

    public List<ViewAttribute> getKeys()
    {
        return keys;
    }

    void addKey( ViewAttribute key )
    {
        keys.add( key );
    }

    /**
     * If this entity is recursive, this returns the recursive parent, null otherwise.
     *
     * @return
     */
    public ViewEntity getRecursiveParentViewEntity()
    {
        return recursiveParentViewEntity;
    }

    /**
     * If this ViewEntity is recursive then this returns the recursive parent,
     * otherwise returns 'this'.
     *
     * @return
     */
    public ViewEntity getBaseViewEntity()
    {
        if ( getRecursiveParentViewEntity() != null )
            return getRecursiveParentViewEntity();

        return this;
    }

    public int getMinCardinality()
    {
        return minCardinality;
    }

    public int getMaxCardinality()
    {
        return maxcardinality;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.CacheMap#putCacheMap(java.lang.Class, java.lang.Object)
     */
    @Override
    public <T> T putCacheMap(Class<T> key, T value)
    {
        return cacheMap.putCacheMap( key, value );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.CacheMap#getCacheMap(java.lang.Class)
     */
    @Override
    public <T> T getCacheMap(Class<T> key)
    {
        return cacheMap.getCacheMap( key );
    }

    /**
     * If true, then there are other entities in this View OD that have duplicate relationships
     * that may need to be relinked after activation.
     */
    public boolean isDuplicateEntity()
    {
        return duplicateEntity;
    }

    /**
     * @return the eventListener
     */
    public EventListener getEventListener()
    {
        return eventListener;
    }

    /**
     * @param eventListener the eventListener to set
     */
    public void setEventListener( EventListener eventListener )
    {
        this.eventListener = eventListener;
    }

    /**
     * @return the hasInitializedAttributes
     */
    public boolean hasInitializedAttributes()
    {
        return hasInitializedAttributes;
    }

    /**
     * @param hasInitializedAttributes the hasInitializedAttributes to set
     */
    public void setHasInitializedAttributes( boolean hasInitializedAttributes )
    {
        this.hasInitializedAttributes = hasInitializedAttributes;
    }

    /**
     * Returns true if 'this' ViewEntity has all the persistent attributes of
     * 'otherEntity'.  Intended to be used by includeProcessing.
     *
     * @param otherEntity
     * @return
     */
    public boolean isAttributeSuperset( ViewEntity otherEntity )
    {
    	if ( getErEntityToken() != otherEntity.getErEntityToken() )
    		throw new ZeidonException( "Entities do not have matching ER Entity Tokens." )
    						.prependViewEntity(this)
    						.prependMessage("Other entity = %s", otherEntity );

    	// Have we already determined the superset status for this entity?
    	if ( attributeSuperset.containsKey( otherEntity ) )
    		return attributeSuperset.get( otherEntity );  // Yes, so return it.

    	// Determine if 'this' entity is a superset.
    	Boolean isSuperset = Boolean.TRUE;
    	for ( ViewAttribute viewAttribute : otherEntity.getAttributes() )
    	{
    		if ( viewAttribute.isPersistent() )
    		{
    			if ( getAttribute( viewAttribute.getName(), false ) == null )
    			{
    				isSuperset = Boolean.FALSE;  // Use the constant value to preclude concurrency issues.
    				break;
    			}
    		}
    	}

    	// Store the answer in a concurrent map.  We don't care if there's thread
    	// collision because the answer will be the same in either case.
    	attributeSuperset.put( otherEntity, isSuperset );

    	return isSuperset;
    }

    /**
     * @return the hashKeyAttribute
     */
    public Collection<ViewAttribute> getHashKeyAttributes()
    {
        return hashKeyAttributes;
    }

    /**
     * @param hashKeyAttribute the hashKeyAttribute to set
     */
    void addHashKeyAttribute( ViewAttribute hashKeyAttribute )
    {
        if ( hashKeyAttributes == null )
            hashKeyAttributes = new ArrayList<ViewAttribute>();

        hashKeyAttributes.add( hashKeyAttribute );
    }

    /**
     * @return the loadIncrementally
     */
    public LazyLoadConfig getLazyLoadConfig()
    {
        return lazyLoadConfig;
    }

    public List<ViewAttribute> getSequencingAttributes()
    {
        return activateOrdering;
    }

    /**
     * Add the view attribute to the list of ordering attributes in the position
     * 'position'.  Note, position is 1-based.
     *
     * @param viewAttribute
     * @param position
     */
    void addSequencingAttribute( ViewAttribute viewAttribute, int position )
    {
        if ( activateOrdering == null )
            activateOrdering = new ArrayList<ViewAttribute>();

        while ( activateOrdering.size() < position )
            activateOrdering.add( null );

        activateOrdering.set( position - 1, viewAttribute );
    }

    /**
     * @return the activateLimit
     */
    public Integer getActivateLimit()
    {
        return activateLimit;
    }
}
