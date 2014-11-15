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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import com.quinsoft.zeidon.EntityConstraintType;
import com.quinsoft.zeidon.EventListener;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.UnknownAttributeDefException;
import com.quinsoft.zeidon.View;
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
public class EntityDef implements PortableFileAttributeHandler, CacheMap
{
    private LodDef     lodDef;
    private EntityDef  prevHier;
    private EntityDef  nextHier;
    private EntityDef  parent;
    private EntityDef  prevSibling;
    private EntityDef  nextSibling;
    private String     name;
    private int        erEntityToken;
    private int        erRelToken;
    private boolean    erRelLink;  // RelLink direction.  True = '1' from the XOD file.
    private final int        level;
    private final int        entityNumber;
    private List<EntityDef> children;
    private List<EntityDef> childrenHier;
    private EventListener eventListener;
    private ArrayList<AttributeDef> activateOrdering;
    private Integer    activateLimit;
    /**
     * List of the attributes in the order they are defined in the XOD file.
     */
    private final List<AttributeDef> attributes = Collections.synchronizedList( new ArrayList<AttributeDef>() );

    /**
     * Map of attributes by attribute name.  This is a concurrent map because this can be increased
     * with dynamic attributes.
     */
    private final ConcurrentMap<String, AttributeDef> attributeMap = new MapMaker().concurrencyLevel( 2 ).makeMap();

    /**
     * Map of attributes by ER attribute token.
     */
    private final Map<Long, AttributeDef> erAttributeMap = new HashMap<Long, AttributeDef>();

    /**
     * This map keeps track of entities that have been checked to see if 'this' entity
     * is an attribute superset.
     * 	Key = LodDef of 'child' entity.
     *  Value = true if 'this' entity is a superset.
     *
     *  This is maintained at run-time which is why we need it to be a concurrent map.
     */
    private final ConcurrentMap<EntityDef, Boolean> attributeSuperset = new MapMaker().concurrencyLevel( 2 ).weakKeys().makeMap();

    private final CacheMap cacheMap = new CacheMapImpl();

    // Permission flags.
    private boolean    create  = false;
    private boolean    include = false;
    private boolean    exclude = false;
    private boolean    delete  = false;
    private boolean    update  = false;
    private boolean    includeSrc = false;

    private final List<AttributeDef> keys;
    private AttributeDef genKey;
    private AttributeDef autoSeq;
    private Collection<AttributeDef> hashKeyAttributes;
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
    private EntityDef recursiveParentEntityDef = null;
    private int        minCardinality;
    private int        maxcardinality;
    private boolean    hasInitializedAttributes = false;

    /**
     * If this entity is a recursive parent then recursiveChild references the child.
     */
    private EntityDef recursiveChild;

    // Flags to help debug OI.
    private boolean    debugIncrementalFlag; // If true, then pop up a message when we change an incremental flag.

    private final LazyLoadConfig lazyLoadConfig;

    /**
     * SourceFileType for the entity constraint.
     */
    private SourceFileType sourceFileType = SourceFileType.VML;
    private String         sourceFileName;
    private String         constraintOper;

    private boolean hasCancelConstraint;
    private boolean hasAcceptConstraint;
    private boolean hasExcludeConstraint;
    private boolean hasIncludeConstraint;
    private boolean hasDeleteConstraint;
    private boolean hasCreateConstraint;

    public EntityDef( LodDef lodDef, int level )
    {
        this.lodDef = lodDef;
        this.entityNumber = lodDef.getEntityCount();
        this.level = level;
        keys = new ArrayList<AttributeDef>();
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
                if ( reader.getAttributeName().equals( "ECESRCFILE" ))
                {
                    sourceFileName = reader.getAttributeValue();
                    if ( ! sourceFileName.contains( "." ) )
                        sourceFileName = getLodDef().getApplication().getPackage() + "." + sourceFileName;
                }
                else
                if ( reader.getAttributeName().equals( "ECESRCTYPE" ))
                {
                    sourceFileType = SourceFileType.parse( reader.getAttributeValue() );
                }
                else
                if ( reader.getAttributeName().equals( "ECEOPER" ))
                {
                    constraintOper = reader.getAttributeValue().intern();
                }
                else
                if ( reader.getAttributeName().equals( "ECCR" ))
                {
                    hasCreateConstraint = StringUtils.startsWithIgnoreCase( reader.getAttributeValue(), "Y" );
                }
                else
                if ( reader.getAttributeName().equals( "ECDEL" ))
                {
                    hasDeleteConstraint = StringUtils.startsWithIgnoreCase( reader.getAttributeValue(), "Y" );
                }
                else
                if ( reader.getAttributeName().equals( "ECINC" ))
                {
                    hasIncludeConstraint = StringUtils.startsWithIgnoreCase( reader.getAttributeValue(), "Y" );

                    // Include constraints take some work.  Since nobody appears to use them let's not
                    // worry about implementing them for now.
                    throw new UnsupportedOperationException( "Include constraints not supported yet." );
                }
                else
                if ( reader.getAttributeName().equals( "ECEXC" ))
                {
                    hasExcludeConstraint = StringUtils.startsWithIgnoreCase( reader.getAttributeValue(), "Y" );
                }
                else
                if ( reader.getAttributeName().equals( "ECACC" ))
                {
                    hasAcceptConstraint = StringUtils.startsWithIgnoreCase( reader.getAttributeValue(), "Y" );
                }
                else
                if ( reader.getAttributeName().equals( "ECCAN" ))
                {
                    hasCancelConstraint = StringUtils.startsWithIgnoreCase( reader.getAttributeValue(), "Y" );
                }
                else
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
                    for ( EntityDef search = parent; search != null; search = search.getParent() )
                    {
                        if ( search.getErEntityToken() == erEntityToken )
                        {
                            search.recursiveChild = this;
                            this.recursive = true;
                            this.recursiveParentEntityDef = search;
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

    public LodDef getLodDef()
    {
        return lodDef;
    }

    public void setLodDef(LodDef lodDef)
    {
        this.lodDef = lodDef;
    }

    public EntityDef getPrevHier()
    {
        return prevHier;
    }

    public void setPrevHier(EntityDef prevHier)
    {
        this.prevHier = prevHier;
    }

    public EntityDef getNextHier()
    {
        return nextHier;
    }

    public void setNextHier(EntityDef nextHier)
    {
        this.nextHier = nextHier;
    }

    public EntityDef getParent()
    {
        return parent;
    }

    void setParent(EntityDef parent)
    {
        this.parent = parent;
        if ( parent.derivedPath )
            derivedPath = true;

        if ( parent.children == null )
            parent.children = new ArrayList<EntityDef>();

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

    public EntityDef getPrevSibling()
    {
        return prevSibling;
    }

    public void setPrevSibling(EntityDef prevSibling)
    {
        this.prevSibling = prevSibling;
    }

    public EntityDef getNextSibling()
    {
        return nextSibling;
    }

    public void setNextSibling(EntityDef nextSibling)
    {
        this.nextSibling = nextSibling;
    }

    public int getLevel()
    {
        return level;
    }

    void addAttributeDef( AttributeDef attributeDef )
    {
        attributes.add( attributeDef );
        attributeMap.put( attributeDef.getName(), attributeDef );

        if ( attributeDef.isDynamicAttribute() )
            assert attributeDef.getErAttributeToken() < 0;
        else
        {
            assert attributeDef.getErAttributeToken() != null;
            erAttributeMap.put( attributeDef.getErAttributeToken(), attributeDef );
        }
    }

    public AttributeDef createDynamicAttributeDef( DynamicAttributeDefConfiguration config )
    {
        if ( attributeMap.containsKey( config.getAttributeName() ) )
        {
            if ( config.canExist() )
                return attributeMap.get( config.getAttributeName() );

            throw new ZeidonException( "Attribute already exists with name: %s", config.getAttributeName() );
        }

        AttributeDef dynamicAttrib = new AttributeDef( this, config );
        addAttributeDef( dynamicAttrib );
        return dynamicAttrib;
    }

    public int getAttributeCount()
    {
        return attributeMap.size();
    }

    public AttributeDef getAttribute( String attribName )
    {
        return getAttribute( attribName, true );
    }

    public AttributeDef getAttribute( String attribName, boolean required )
    {
        AttributeDef attrib = attributeMap.get( attribName );
        if ( attrib == null && required )
            throw new UnknownAttributeDefException( this, attribName );

        return attrib;
    }

    public AttributeDef getAttribute( int attributeNumber )
    {
        if ( attributeNumber >= attributes.size() )
            throw new ZeidonException("Attribute index %d out of range for %s.",
                                      attributeNumber, lodDef.getName() );

        return attributes.get( attributeNumber );
    }

    public AttributeDef getAttributeByErToken( long erToken )
    {
        return erAttributeMap.get( erToken );
    }

    public List<AttributeDef> getAttributes()
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
        return lodDef.toString() + "." + name;
    }

    /**
     * If true then this entity will be deleted if its parent is deleted, otherwise
     * this entity will be excluded if it's parent is excluded.
     *
     * @return
     */
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
    public List<EntityDef> getChildren()
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
    public synchronized List<EntityDef> getChildrenHier()
    {
        if ( children == null )
            return Collections.emptyList();

        if ( childrenHier != null )
            return childrenHier;

        List<EntityDef> list = new ArrayList<EntityDef>();
        for ( EntityDef child = this.getNextHier();
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
    public EntityDef getLastChildHier()
    {
        if ( children == null )
            return this;

        List<EntityDef> list = getChildrenHier();
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
        getLodDef().setHasPhysicalMappings( true );
        this.dataRecord = dataRecord;
    }

    public AttributeDef getGenKey()
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

    void setGenKey(AttributeDef attributeDef)
    {
        genKey = attributeDef;
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
     * If this EntityDef is a recursive parent then this returns the recursive child.
     *
     * @return
     */
    public EntityDef getRecursiveChild()
    {
        return recursiveChild;
    }

    public boolean isDebugIncremental()
    {
        return debugIncrementalFlag;
    }

    void setAutoSeq(AttributeDef autoSeq)
    {
        this.autoSeq = autoSeq;
    }

    public AttributeDef getAutoSeq()
    {
        return autoSeq;
    }

    public List<AttributeDef> getKeys()
    {
        return keys;
    }

    void addKey( AttributeDef key )
    {
        keys.add( key );
    }

    /**
     * If this entity is recursive, this returns the recursive parent, null otherwise.
     *
     * @return
     */
    public EntityDef getRecursiveParentEntityDef()
    {
        return recursiveParentEntityDef;
    }

    /**
     * If this EntityDef is recursive then this returns the recursive parent,
     * otherwise returns 'this'.
     *
     * @return
     */
    public EntityDef getBaseEntityDef()
    {
        if ( getRecursiveParentEntityDef() != null )
            return getRecursiveParentEntityDef();

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
     * If true, then there are other entities in this LodDef that have duplicate relationships
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
     * Returns true if 'this' EntityDef has all the persistent attributes of
     * 'otherEntity'.  Intended to be used by includeProcessing.
     *
     * @param otherEntity
     * @return
     */
    public boolean isAttributeSuperset( EntityDef otherEntity )
    {
    	if ( getErEntityToken() != otherEntity.getErEntityToken() )
    		throw new ZeidonException( "Entities do not have matching ER Entity Tokens." )
    						.prependEntityDef(this)
    						.prependMessage("Other entity = %s", otherEntity );

    	// Have we already determined the superset status for this entity?
    	if ( attributeSuperset.containsKey( otherEntity ) )
    		return attributeSuperset.get( otherEntity );  // Yes, so return it.

    	// Determine if 'this' entity is a superset.
    	Boolean isSuperset = Boolean.TRUE;
    	for ( AttributeDef attributeDef : otherEntity.getAttributes() )
    	{
    		if ( attributeDef.isPersistent() )
    		{
    			if ( getAttribute( attributeDef.getName(), false ) == null )
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
    public Collection<AttributeDef> getHashKeyAttributes()
    {
        return hashKeyAttributes;
    }

    /**
     * @param hashKeyAttribute the hashKeyAttribute to set
     */
    void addHashKeyAttribute( AttributeDef hashKeyAttribute )
    {
        if ( hashKeyAttributes == null )
            hashKeyAttributes = new ArrayList<AttributeDef>();

        hashKeyAttributes.add( hashKeyAttribute );
    }

    /**
     * @return the loadIncrementally
     */
    public LazyLoadConfig getLazyLoadConfig()
    {
        return lazyLoadConfig;
    }

    public List<AttributeDef> getSequencingAttributes()
    {
        return activateOrdering;
    }

    /**
     * Add the AttributeDef to the list of ordering attributes in the position
     * 'position'.  Note, position is 1-based.
     *
     * @param attributeDef
     * @param position
     */
    void addSequencingAttribute( AttributeDef attributeDef, int position )
    {
        if ( activateOrdering == null )
            activateOrdering = new ArrayList<AttributeDef>();

        while ( activateOrdering.size() < position )
            activateOrdering.add( null );

        activateOrdering.set( position - 1, attributeDef );
    }

    /**
     * @return the activateLimit
     */
    public Integer getActivateLimit()
    {
        return activateLimit;
    }

    public SourceFileType getSourceFileType()
    {
        return sourceFileType;
    }

    public String getSourceFileName()
    {
        return sourceFileName;
    }

    public String getConstraintOper()
    {
        return constraintOper;
    }

    public boolean hasCancelConstraint()
    {
        return hasCancelConstraint;
    }

    public void setHasCancelConstraint( boolean hasCancelConstraint )
    {
        this.hasCancelConstraint = hasCancelConstraint;
    }

    public boolean hasAcceptConstraint()
    {
        return hasAcceptConstraint;
    }

    public void setHasAcceptConstraint( boolean hasAcceptConstraint )
    {
        this.hasAcceptConstraint = hasAcceptConstraint;
    }

    public boolean hasExcludeConstraint()
    {
        return hasExcludeConstraint;
    }

    public void setHasExcludeConstraint( boolean hasExcludeConstraint )
    {
        this.hasExcludeConstraint = hasExcludeConstraint;
    }

    public boolean hasIncludeConstraint()
    {
        return hasIncludeConstraint;
    }

    public void setHasIncludeConstraint( boolean hasIncludeConstraint )
    {
        this.hasIncludeConstraint = hasIncludeConstraint;
    }

    public boolean hasDeleteConstraint()
    {
        return hasDeleteConstraint;
    }

    public void setHasDeleteConstraint( boolean hasDeleteConstraint )
    {
        this.hasDeleteConstraint = hasDeleteConstraint;
    }

    public boolean hasCreateConstraint()
    {
        return hasCreateConstraint;
    }

    public void setHasCreateConstraint( boolean hasCreateConstraint )
    {
        this.hasCreateConstraint = hasCreateConstraint;
    }

    /**
     * Execute the entity constraint for the view.
     *
     * @param view
     * @param type
     * @return
     */
    public int executeEntityConstraint( View view, EntityConstraintType type )
    {
        switch ( sourceFileType )
        {
            case VML:
                return executeVmlConstraint( view, type );

            case SCALA:
                return executeScalaConstraint( view, type );

            case JAVA:
            default:
                throw new ZeidonException( "Unsupported Entity Constraint SourceFileType: %s", type );
        }
    }

    static private final Class<?>[] VML_CONSTRUCTOR_ARG_TYPES  = new Class<?>[] { View.class };
    static private final Class<?>[] VML_ARGUMENT_TYPES = new Class<?>[] { View.class, String.class, Integer.class, Integer.class };

    private int executeVmlConstraint( View view, EntityConstraintType type )
    {
        ObjectEngine oe = view.getObjectEngine();
        String className = getSourceFileName();
        try
        {
            ClassLoader classLoader = oe.getClassLoader( className );
            Class<?> operationsClass;
            operationsClass = classLoader.loadClass( className );
            Constructor<?> constructor = operationsClass.getConstructor( VML_CONSTRUCTOR_ARG_TYPES );
            Object object = constructor.newInstance( view );
            Method method = object.getClass().getMethod( getConstraintOper(), VML_ARGUMENT_TYPES );
            return (Integer) method.invoke( object, view, this.getName(), type.toInt(), 0 );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e )
                                 .prependEntityDef( this )
                                 .appendMessage( "EntityConstraint class = %s", className )
                                 .appendMessage( "Constraint oper = %s", getConstraintOper() )
                                 .appendMessage( "See inner exception for more info." );
        }
    }

    private int executeScalaConstraint( View view, EntityConstraintType type )
    {
        try
        {
            return getLodDef().getScalaHelper( view ).executeEntityConstraint( view, this, type );
        }
        catch ( Exception e )
        {
            if ( e instanceof InvocationTargetException )
                throw ZeidonException.wrapException( ((InvocationTargetException) e).getTargetException() );
            else
                throw ZeidonException.wrapException( e );
        }
    }
}
