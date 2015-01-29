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
package com.quinsoft.zeidon;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;

import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DynamicAttributeDefConfiguration;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.standardoe.IncrementalEntityFlags;


/**
 * @author DG
 *
 */
public interface EntityInstance
{
    /**
     * Returns the depth of the entity instance.  The root has a depth of 1.
     *
     * @return the depth.
     */
    int getDepth();

    /**
     * Returns true if any of the persistent attributes in the entity instance have
     * been updated.
     *
     * @return true if entity instance has been updated.
     */
    boolean isUpdated();

    /**
     * Returns true if this entity instance has been deleted.  This is intended to be used
     * just by DB handlers.
     *
     * @return true if the entity instance has been created.
     */
    boolean isDeleted();

    /**
     * Returns true of the entity instance has been created.  This will be false for persistent
     * entities loaded from the DB.
     *
     * @return true if the entity instance has been created.
     */
    boolean isCreated();

    /**
     * Returns true if the entity instance has been included.  An included entity is a pre-existing entity
     * that had its relationship with its parent created
     *
     * @return true if the entity instance has been created.
     */
    boolean isIncluded();

    /**
     * Returns true if the entity instance has been excluded.  I.e. the relationship with its parent has
     * been severed but the entity instance has not been deleted.  This is intended to be used by DB
     * handlers.
     *
     * @return true if the entity instance has been excluded.
     */
    boolean isExcluded();

    /**
     * Returns true if the entity instance has been deleted or excluded.  Intended to be used
     * by DB handlers.
     *
     * @return true if the entity instance has been deleted or excluded.
     */
    boolean isHidden();

    /**
     * Returns true if this entity instance is a temporal version.
     *
     * @return true if this entity instance is a temporal version.
     */
    boolean isVersioned();

    /**
     * Returns the entity definition for this entity instance.
     *
     * @return the entity's definition.
     */
    EntityDef      getEntityDef();

    /**
     * Returns the parent entity instance or null if the entity is the root.
     *
     * @return the parent entity instance of null if the entity is the root.
     */
    EntityInstance getParent();

    /**
     * Returns the previous twin of this entity or null if there is none.
     *
     * @return the previous twin of this entity or null if there is none.
     */
    EntityInstance getPrevTwin();

    /**
     * Returns the next twin of this entity or null if there is none.
     *
     * @return the next twin of this entity or null if there is none.
     */
    EntityInstance getNextTwin();

    /**
     * @return true if this EntityInstance has a next, non-hidden, twin.
     */
    boolean hasNextTwin();

    /**
     * @return true if this EntityInstance has a previous, non-hidden, twin.
     */
    boolean hasPrevTwin();

    /**
     * Returns a UUID that has been generated for this entity instance.  Every entity instance
     * has a unique UUID, including linked entity instances.
     *
     * @return unique UUID for the entity instance.
     */
    UUID getEntityUuid();

    /**
     * Returns a UUID that has been generated for the object instance.  All entity instances in
     * the same OI will share the same OI UUID.
     *
     * @return the UUID for the object instance.
     */
    UUID getObjectInstanceUuid();

    /**
     * Returns a value that is unique for all entity instances that have been created by the
     * current Object Engine.  Use UUID if a key that is unique across all Object Engines
     * is needed.
     *
     * @return a key that is unique for this Object Engine.
     */
    long getEntityKey();

    long getObjectInstanceId();

    /**
     * Creates a temporal version of the entity instance and its children (i.e. the subobject).  Changes made
     * to the temporal subobject are not copied to the object instance until acceptSubobject() is called.  The
     * changes made be canceled with canceSubobject().
     *
     * @return the temporal entity instance.
     */
    EntityInstance createTemporalSubobjectVersion();

    /**
     * Accepts the changes made to the temporal subobject and are copied to the object instance.  Accept can
     * only be called on the root of the temporal suboject.
     *
     * @return the root of the accepted subobject.
     */
    EntityInstance acceptSubobject();

    /**
     * Cancels the temporal subobject and throws away the changes.
     *
     * @return the original version of the entity instance.
     */
    EntityInstance cancelSubobject();

    /**
     * Accepts a temporal entity and updates the object instance.
     */
    void acceptTemporalEntity();

    /**
     * Cancels a temporal entity and throws away the changes.
     *
     * @return If this is called via a cursor, it returns the cursor positioning.  Otherwise returns CursorResult.SET.
     */
    CursorResult cancelTemporalEntity();

    /**
     * Performs validation on this entity and all child subobjects:
     *  1) Checks for required attributes.
     *  2) Validates min/max cardinality.
     *
     *  @returns a list of exceptions found.  If no exceptions found returns null.
     */
    Collection<ZeidonException> validateSubobject();

    CursorResult  deleteEntity();
    CursorResult  dropEntity();
    CursorResult  excludeEntity();

    //
    // Attribute methods.
    //
    AttributeInstance getAttribute( String attributeName );
    AttributeInstance getAttribute( AttributeDef attributeDef );
    List<AttributeInstance> attributeList( boolean includeNullValues );

    /**
     * Creates a work attribute for this entity type.
     *
     * @param config
     * @return
     */
    AttributeInstance createDynamicAttributeDef( DynamicAttributeDefConfiguration config );
    boolean isAttributeNull( String attributeName );
    boolean isAttributeNull( AttributeDef attributeDef );
    boolean isAttributeUpdated( AttributeDef attributeDef );
    EntityInstance setAttribute( String attributeName, Object value ) throws InvalidAttributeValueException;
    EntityInstance setAttribute( String attributeName, Object value, String contextName ) throws InvalidAttributeValueException;
    EntityInstance setAttribute( AttributeDef attributeDef, Object value ) throws InvalidAttributeValueException;
    EntityInstance setAttribute( AttributeDef attributeDef, Object value, String contextName ) throws InvalidAttributeValueException;
    EntityInstance setAttributeFromAttribute( String tgtAttributeName, View srcView, String srcEntityName, String srcAttributeName ) throws InvalidAttributeValueException;

    /**
     * Sets the value of the attribute without attempting to convert it first.  This still validates that the
     * value is well-formed.  Does *NOT* set the update flag.
     *
     * @param attributeDef
     * @param value
     * @param setIncremental TODO
     * @return
     * @throws InvalidAttributeValueException
     */
    EntityInstance setInternalAttributeValue( AttributeDef attributeDef, Object value, boolean setIncremental ) throws InvalidAttributeValueException;

    /**
     * Sets the incremental update flags for this entity.  Does not set OI flags.
     *
     * @param flags
     * @return
     */
    EntityInstance setIncrementalFlags( EnumSet<IncrementalEntityFlags> flags );
    EntityInstance setIncrementalFlags( IncrementalEntityFlags flag );

    Object getInternalAttributeValue( String attributeName );
    Object getInternalAttributeValue( AttributeDef attributeDef );

    String getStringFromAttribute( AttributeDef attributeDef );
    String getStringFromAttribute( String attributeName );
    String getStringFromAttribute( AttributeDef attributeDef, String contextName );
    String getStringFromAttribute( String attributeName, String contextName );

    Integer getIntegerFromAttribute( AttributeDef attributeDef );
    Integer getIntegerFromAttribute( String attributeName );
    Integer getIntegerFromAttribute( AttributeDef attributeDef, String contextName );
    Integer getIntegerFromAttribute( String attributeName, String contextName );

    Double getDoubleFromAttribute( AttributeDef attributeDef );
    Double getDoubleFromAttribute( String attributeName );
    Double getDoubleFromAttribute( AttributeDef attributeDef, String contextName );
    Double getDoubleFromAttribute( String attributeName, String contextName );

    DateTime getDateTimeFromAttribute( AttributeDef attributeDef );
    DateTime getDateTimeFromAttribute( String attributeName );
    DateTime getDateTimeFromAttribute( AttributeDef attributeDef, String contextName );
    DateTime getDateTimeFromAttribute( String attributeName, String contextName );

    Blob getBlobFromAttribute( String attributeName );
    Blob getBlobFromAttribute( AttributeDef attributeDef );

    /**
     * Returns a string representation of the key values of this entity.  If all keys are
     * null then returns null.
     *
     * @return
     */
    String getKeyString();

    Object addToAttribute( String attributeName, Object value );
    Object addToAttribute( AttributeDef attributeDef, Object value );
    Object multiplyAttribute( String attributeName, Object value );
    Object multiplyAttribute( AttributeDef attributeDef, Object value );

    /**
     * Compares the value of the named attribute with 'value'.  This will convert the value of 'value'
     * to the internal representation before doing the comparison.
     *
     * Null values are considered less than all other values except null (if both values are null then
     * the return value is 0).
     *
     * @param attributeName
     * @param value
     * @return -1, 0, or 1 depending on attribute <=> value.
     */
    int compareAttribute( String attributeName, Object value );
    int compareAttribute( AttributeDef attributeDef, Object value );
    int compareAttribute( String attributeName, EntityInstance entityInstance, String attributeName2 );
    int compareAttribute( AttributeDef attributeDef, EntityInstance entityInstance, AttributeDef attributeDef2 );

    /**
     * Iterates through all the child entities that match childEntityDef, including
     * hidden entities.
     *
     * @param childEntityDef
     * @return
     */
    EntityIterator<? extends EntityInstance> getChildren( EntityDef childEntityDef );
    EntityIterator<? extends EntityInstance> getChildren( String childEntityName );
    EntityIterator<? extends EntityInstance> getChildren( EntityDef childEntityDef, boolean allowHidden );

    /**
     * Iterates through all the children of 'this' in heir order.  If includeParent
     * is true, then the iteration includes 'this' at the beginning.
     *
     * @param includeParent If true, include 'this'.
     *
     * @return
     */
    EntityIterator<? extends EntityInstance> getChildrenHier( boolean includeParent );

    /**
     * Loops through all the direct EI children of 'this'.
     *
     * @param allowHidden
     * @return
     */
    EntityIterator<? extends EntityInstance> getDirectChildren();

    /**
     * Loops through all the direct EI children of 'this'.
     *
     * @param allowHidden
     * @return
     */
    EntityIterator<? extends EntityInstance> getDirectChildren( boolean allowHidden );

    /**
     * Loops through all the direct EI children of 'this'.
     *
     * @param allowHidden
     * @return
     */
    EntityIterator<? extends EntityInstance> getDirectChildren( boolean allowHidden, boolean allowLazyLoad );

    /**
     * Returns an iterable list of entities linked with 'this'.  If there
     * are no linked entities it will return an empty list.
     *
     * @return
     */
    Collection<? extends EntityInstance> getLinkedInstances();

    /**
     * Copies attributes from source to 'this' matching by name.
     *
     *   zSET_KEYS    - Set target attributes that represent KEY's.
     *   zSET_NOTNULL - Set, even if current value of the target attribute is not null.  (except KEYs)
     *   zSET_ALL     - Set all target attributes.
     *   zSET_SRCNOTNULL - Only set attribute if the src value is not null.
     *
     */
    int setMatchingAttributesByName( EntityInstance sourceInstance, EnumSet<SetMatchingFlags> control );
    int setMatchingAttributesByName( EntityInstance sourceInstance );
    SetMatchingFlagsBuilder setMatchingAttributesByName();

    void logEntity();
    void logEntity( boolean displayChildren );
    void logEntity( boolean displayChildren, int indent );

    /**
     * @deprecated Use logEntity instead.
     */
    @Deprecated
    void displayEntity();

    /**
     * @deprecated Use logEntity instead.
     */
    @Deprecated
    void displayEntity( boolean displayChildren );

    /**
     * @deprecated Use logEntity instead.
     */
    @Deprecated
    void displayEntity( boolean displayChildren, int indent );

    // Following are used by DBHandler processing and aren't normally needed by application code.
    boolean isDbhCreated();
    boolean isDbhDeleted();
    boolean isDbhExcluded();
    boolean isDbhUpdated();
    boolean isDbhIncluded();
    boolean isDbhNeedsInclude();
    boolean isDbhSeqUpdated();
    boolean isDbhGenKeyNeeded();
    boolean isDbhNoGenKey();
    boolean isDbhForeignKey();

    /**
     * Returns the position of the entity in the OI using heiarchical order.  Excludes
     * hidden entities.  Root = 0.
     *
     * @return
     */
    long getHierPosition();

    /**
     * Returns the position of this entity amongst its twins.  First twin = 0;
     *
     * @return
     */
    long getPosition();

    /**
     * Returns the last child under the current entity instance.  If there is no child
     * under 'this', then returns 'this'.
     * @return
     */
    EntityInstance getLastChildHier();

    /**
     * Returns the underlying instance for this EntityInstance.  This is normally used
     * by util functions that can accept either an EntityInstance or a EntityCursor to
     * convert the EntityCursor to an EntityInstance.
     *
     * @return  EntityInstance
     */
    EntityInstance getEntityInstance();

    /**
     * Returns true if current instance is linked to ei.
     *
     * @param ei
     * @return
     */
    boolean isLinked( EntityInstance ei );

    /**
     * Links the two instances.
     *
     * @param ei
     * @return false if they are already linked.
     */
    boolean linkInstances( EntityInstance ei );
}
