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
import com.quinsoft.zeidon.objectdefinition.RelRecord.RelationshipType;
import com.quinsoft.zeidon.utils.CacheMapImpl;
import com.quinsoft.zeidon.utils.EventStackTrace;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;

/**
 * @author DG
 *
 */
public class EntityDef implements PortableFileAttributeHandler
{
    private LodDef     lodDef;
    private EntityDef  prevHier;
    private EntityDef  nextHier;
    private EntityDef  parent;
    private EntityDef  prevSibling;
    private EntityDef  nextSibling;
    private String     name;
    private String     erEntityToken;
    private String     erRelToken;
    private boolean    erRelLink;  // RelLink direction.  True = '1' from the XOD file.
    private final int        depth;
    private final int        entityNumber;
    private List<EntityDef> children;
    private List<EntityDef> childrenHier;
    private EventListener eventListener;
    private ArrayList<AttributeDef> activateOrdering;
    private Integer    activateLimit;
    private EntityDefLinkInfo linkInfo;

    /**
     * List of the attributes in the order they are defined in the XOD file.
     */
    private final List<AttributeDef> attributes = Collections.synchronizedList( new ArrayList<AttributeDef>() );

    /**
     * List of the non-hidden attributes in the order they are defined in the XOD file.
     */
    private volatile List<AttributeDef> nonHiddenAttributes = Collections.synchronizedList( new ArrayList<AttributeDef>() );;

    /**
     * Map of attributes by attribute name.  This is a concurrent map because this can be increased
     * with dynamic attributes.
     */
    private final ConcurrentMap<String, AttributeDef> attributeMap = new MapMaker().concurrencyLevel( 2 ).makeMap();

    /**
     * Map of attributes by ER attribute token.
     */
    private final Map<String, AttributeDef> erAttributeMap = new HashMap<>();

    /**
     * This map keeps track of entities that have been checked to see if 'this' entity
     * is an attribute superset.
     * 	Key = LodDef of 'child' entity.
     *  Value = true if 'this' entity is a superset.
     *
     *  This is maintained at run-time which is why we need it to be a concurrent map.
     */
    private final ConcurrentMap<EntityDef, Boolean> attributeSuperset = new MapMaker().concurrencyLevel( 2 ).weakKeys().makeMap();

    private CacheMap cacheMap;

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
    private boolean    autoloadFromParent;
    private int        persistentAttributeCount;
    private int        workAttributeCount;
    private DataRecord dataRecord;
    /**
     * If not null, this is the relationship that is used to version this entity.
     */
    private DataField  versioningDataField;
    private String     versioningAttributeTok;

    private EntityDef  recursiveParent;
    private int        minCardinality;
    private int        maxcardinality;
    private boolean    hasInitializedAttributes = false;

    /**
     * If this entity is a recursive parent then recursiveChild references the child.
     */
    private EntityDef recursiveChild;

    /**
     * This is true if this EntityDef has a parent that is a Recursive parent.
     */
    private boolean recursivePath;

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
    private AttributeDef dbCreatedTimestamp;
    private AttributeDef dbUpdatedTimestamp;

    public EntityDef( LodDef lodDef, int level )
    {
        this.lodDef = lodDef;
        this.entityNumber = lodDef.getEntityCount();
        this.depth = level;
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
                else
                if ( reader.getAttributeName().equals( "AUTOLOADFROMPARENT" ))
                {
                    autoloadFromParent = reader.getAttributeValue().startsWith( "Y" );
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
                    lodDef.setHasDuplicateInstances( true );
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
                    erEntityToken = reader.getAttributeValue().intern();
                }
                else
                if ( reader.getAttributeName().equals( "ERREL_TOK" ))
                {
                    erRelToken = reader.getAttributeValue().intern();
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
                    getLodDef().setHasLazyLoadEntities( true );
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
                            this.recursiveParent = search;

                            for ( EntityDef child = search;
                                  child != null && child.getDepth() > this.getDepth();
                                  child = child.getNextHier() )
                            {
                                child.recursivePath = true;
                            }

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

            case 'V':
                if ( reader.getAttributeName().equals( "UPDATE" ))
                {
                    update = reader.getAttributeValue().startsWith( "Y" );
                }
                break;
        }
    }

    /**
     * This is called after the entity def has been completely loaded.  We'll validate that
     * the entity def is configured correctly.
     *
     * @param reader used to pass the task.
     */
    void validateEntityDef( PortableFileReader reader )
    {
        DataRecord childRecord = getDataRecord();
        if ( childRecord != null )
        {
            // Make sure SINGLE SELECT is configured properly.
            if ( childRecord.isActivateWithSingleSelect() )
            {
                if ( childRecord.isJoinable() )
                    throw new ZeidonException( "EntityDef shouldn't be JOIN and ACTIVATONE" );

                RelRecord relRecord = childRecord.getRelRecord();
                RelationshipType relType = relRecord.getRelationshipType();

                // We don't support m-to-1 relationships because it is too hard
                // to figure out where the children go.  In most cases this doesn't
                // matter because m-to-1 children should be joined with the parent.
                if ( relType.isManyToOne() )
                    throw new ZeidonException( "m-to-1 relationships not supported in a single select." );

                // We can only handle it if there is a single key between child
                // and parent.
                if ( relType.isOneToMany() && relRecord.getRelFields().size() > 1 )
                    throw new ZeidonException( "Only single keys supported in a single select." );

                if ( relType.isManyToMany() && relRecord.getRelFields().size() > 2 )
                    throw new ZeidonException( "Only single keys supported in a single select." );

                // Can't load children of recursive entities.
                for ( EntityDef ve = this; ve != null; ve = ve.getParent() )
                {
                    if ( ve.isRecursive() )
                        throw new ZeidonException( "Recursive entities not supported in a single select." );
                }
            }
        }

        // Everything looks good.  Lets check to see if there's a versioning relationship.
        if ( versioningAttributeTok != null )
            setVersioningRel();
    }

    private void setVersioningRel()
    {
        if ( ! isUpdate() )
            return;

        if ( isDerived() )
            return;

        AttributeDef versioningAttribute = getAttributeByErToken( versioningAttributeTok );

        // The versioning attribute should be a foreign key, which means it should be hidden.
        assert versioningAttribute.isHidden() : "Versioning attribute is not hidden.";

        versioningDataField = getDataRecord().getDataField( versioningAttribute );
    }

    /**
     * Name of the entity  This string has been intern() so it is safe to use ==
     * instead of equals().
     *
     * @return internal string.
     */
    public String getName()
    {
        return name;
    }

    /**
     * A string ID that uniquely defines this entity from the ER.  This string has
     * been intern() so it is safe to use == instead of equals().
     *
     * @return internal string.
     */
    public String getErEntityToken()
    {
        return erEntityToken;
    }

    public LodDef getLodDef()
    {
        return lodDef;
    }

    void setLodDef(LodDef lodDef)
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

        if ( parent.recursivePath || parent.isRecursive() || parent.isRecursiveParent() )
            recursivePath = true;

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

    /**
     * Returns the depth of this EntityDef.  The root has a depth of 1.
     *
     * @return entity depth.
     */
    public int getDepth()
    {
        return depth;
    }

    void addAttributeDef( AttributeDef attributeDef )
    {
        attributes.add( attributeDef );
        if ( ! attributeDef.isHidden() )
            nonHiddenAttributes.add( attributeDef );
        attributeMap.put( attributeDef.getName(), attributeDef );
        attributeMap.put( attributeDef.getName().toLowerCase(), attributeDef );

        if ( ! attributeDef.isDynamicAttribute() )
            erAttributeMap.put( attributeDef.getErAttributeToken(), attributeDef );
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
        return attributes.size();
    }

    public AttributeDef getAttribute( String attribName )
    {
        return getAttribute( attribName, true, false );
    }

    public AttributeDef getAttribute( String attribName, boolean required )
    {
        return getAttribute( attribName, required, false );
    }

    public AttributeDef getAttribute( String attribName, boolean required, boolean ignoreCase )
    {
        String searchName = attribName;
        if ( ignoreCase )
            searchName = searchName.toLowerCase();

        AttributeDef attrib = attributeMap.get( searchName );
        if ( attrib == null && required )
            throw new UnknownAttributeDefException( this, attribName );

        return attrib;
    }

    public AttributeDef getAttribute( int index )
    {
        if ( index >= attributes.size() )
            throw new ZeidonException("Attribute index %d out of range for %s.",
                                      index, lodDef.getName() );

        return attributes.get( index );
    }

    public AttributeDef getAttributeByErToken( String erToken )
    {
        return erAttributeMap.get( erToken );
    }

    /**
     * @return all the attributes, including non-hidden ones.
     */
    public List<AttributeDef> getAttributes()
    {
        return Collections.unmodifiableList( attributes );
    }

    public List<AttributeDef> getAttributes( boolean excludeHidden )
    {
        if ( ! excludeHidden )
            return getAttributes();

        return Collections.unmodifiableList( nonHiddenAttributes );
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
              child != null && child.getDepth() > this.getDepth();
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

    /**
     * A string ID that uniquely defines the relationship between this EntityDef
     * and its parent.  This string has been intern() so it is safe to use ==
     * instead of equals().
     *
     * @return internal string.
     */
    public String getErRelToken()
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

    public boolean isRecursivePath()
    {
        return recursivePath;
    }

    /**
     * Returns true if 'this' is an ancestor of entityDef.
     *
     * @param entityDef
     * @return true if 'this' is an ancestor of entityDef.
     */
    public boolean isAncestorOf( EntityDef entityDef )
    {
        for ( EntityDef t = entityDef.getParent(); t != null; t = t.getParent() )
        {
            if ( t == this )
                return true;
        }

        return false;
    }

    /**
     * Returns true if 'this' is a descendant of entityDef.
     *
     * @param entityDef
     * @return true if 'this' is a descendant of entityDef.
     */
    public boolean isDescendantOf( EntityDef entityDef )
    {
        return entityDef.isAncestorOf( this );
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
    public EntityDef getRecursiveParent()
    {
        return recursiveParent;
    }

    /**
     * If this EntityDef is recursive then this returns the recursive parent,
     * otherwise returns 'this'.
     *
     * @return
     */
    public EntityDef getBaseEntityDef()
    {
        if ( getRecursiveParent() != null )
            return getRecursiveParent();

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

    public synchronized CacheMap getCacheMap()
    {
        if ( cacheMap == null )
            cacheMap = new CacheMapImpl();

        return cacheMap;
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
     * @return true if this EntityDef has hashkey attributes.
     */
    public boolean hasAttributeHashKeys()
    {
        return hashKeyAttributes != null;
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
            return view.getTask().getScalaHelper().executeEntityConstraint( view, this, type );
        }
        catch ( Exception e )
        {
            if ( e instanceof InvocationTargetException )
                throw ZeidonException.wrapException( ((InvocationTargetException) e).getTargetException() );
            else
                throw ZeidonException.wrapException( e );
        }
    }

    /**
     * Returns true if this entity can activating by getting the attribute data
     * from the parent instead of going to the DB.   This occurs when the entity
     * has only keys and the foreign keys are in the parent.
     *
     * @return true if this entity can be autoloaded from parent.
     */
    public boolean isAutoloadFromParent()
    {
        return autoloadFromParent;
    }

    /**
     * Checks to see if all the attributes in targetEi as sourceEi.
     *
     * @return null if all attributes exist otherwise AttributeDef of first missing attribute found.
     */
    private AttributeDef checkForAllPersistentAttributes( EntityDef source, EntityDef target )
    {
        for ( AttributeDef attr : target.getAttributes() )
        {
            if ( ! attr.isActivate() )
                continue;

            // It's ok if autoseq is missing because it's maintained by the relationship.
            if ( attr.isAutoSeq() )
                continue;

            // Check to see if the attribute exists in the source.
            if ( source.getAttribute( attr.getName(), false ) == null )
                return attr;
        }

        return null;
    }

    /**
     * Validate that the 'source' EntityDef can be linked with 'this'.
     *
     * @param source
     * @return
     */
    public LinkValidation validateLinking( EntityDef source )
    {
        assert getErEntityToken() == source.getErEntityToken() : "Trying to link mismatching ER tokens";

        if ( this == source )
            return LinkValidation.SOURCE_OK;

        AttributeDef missingAttributeDef = null;

        // If they have the same ER date we'll assume all is good.
        if ( source.getLodDef().getErDate().equals( getLodDef().getErDate() ) )
            return LinkValidation.SOURCE_OK;

        // Check to see if it's ok for target to be linked to source.
        EntityDefLinkInfo sourceInfo = getLinkInfo( source );
        Boolean sourceOk = sourceInfo.mayBeLinked.get( this );
        if ( sourceOk == Boolean.TRUE )
            return LinkValidation.SOURCE_OK;

        /*  This leads to a NPE.  Some day we may try to fix it.
        // Check to see if source can be linked to 'this'.
        EntityDefLinkInfo targetInfo = getEntityDefLinkInfo( entityDef );
        Boolean targetOk = targetInfo.mayBeLinked.get( entityDef );
        if ( targetOk == Boolean.TRUE )
        {
            source.attributeList = AttributeListInstance.newSharedAttributeList( source, source.attributeList,
                                                                                 this, this.attributeList );
            return;
        }
        */

        if ( sourceOk == null ) // If it's null we've never checked this one.
        {
            missingAttributeDef = checkForAllPersistentAttributes( source, this );
            sourceOk = missingAttributeDef == null;
            sourceInfo.mayBeLinked.putIfAbsent( this, sourceOk );
            if ( sourceOk == Boolean.TRUE )
                return LinkValidation.SOURCE_OK;
        }

        /*  This leads to a NPE.  Some day we may try to fix it.
        if ( targetOk == null ) // If it's null we've never checked this one.
        {
            missingAttributeDef = checkForAllPersistentAttributes( source, this );
            targetOk = missingAttributeDef == null;
            targetInfo.mayBeLinked.putIfAbsent( sourceEntityDef, targetOk );
            if ( targetOk == Boolean.TRUE )
            {
                source.attributeList = AttributeListInstance.newSharedAttributeList( source, source.attributeList,
                                                                                     this, this.attributeList );
                return;
            }
        }
        */

        ZeidonException ex = new ZeidonException( "Attempting to link instances that don't have matching attributes.  "
                                                + "You probably need to re-save the target LOD." );
        ex.appendMessage( "Source instance = %s", source );
        ex.appendMessage( "Target instance type = %s", this );
        ex.appendMessage( "Missing attribute = %s.%s.%s",
                          missingAttributeDef.getEntityDef().getLodDef().getName(),
                          missingAttributeDef.getEntityDef().getName(),
                          missingAttributeDef.getName() );

        throw ex;
    }

    private synchronized EntityDefLinkInfo getLinkInfo( EntityDef entityDef )
    {
        if ( linkInfo == null )
            linkInfo = new EntityDefLinkInfo();

        return linkInfo;
    }

    public AttributeDef getDbCreatedTimestamp()
    {
        return dbCreatedTimestamp;
    }

    void setDbCreatedTimestamp( AttributeDef dbCreatedTimestamp )
    {
        this.dbCreatedTimestamp = dbCreatedTimestamp;
    }

    public AttributeDef getDbUpdatedTimestamp()
    {
        return dbUpdatedTimestamp;
    }

    void setDbUpdatedTimestamp( AttributeDef dbUpdatedTimestamp )
    {
        this.dbUpdatedTimestamp = dbUpdatedTimestamp;
    }

    public enum LinkValidation
    {
        SOURCE_OK;
    }

    /**
     * This keeps track of whether two view entities can be validly linked together.
     */
    private class EntityDefLinkInfo
    {
        public final ConcurrentMap<EntityDef, Boolean> mayBeLinked = new MapMaker().concurrencyLevel( 4 ).makeMap();
    }
}
