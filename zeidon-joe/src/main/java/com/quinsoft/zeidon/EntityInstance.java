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
package com.quinsoft.zeidon;

import java.util.Collection;
import java.util.EnumSet;
import java.util.UUID;

import org.joda.time.DateTime;

import com.quinsoft.zeidon.objectdefinition.DynamicViewAttributeConfiguration;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
import com.quinsoft.zeidon.standardoe.IncrementalEntityFlags;


/**
 * @author DG
 *
 */
public interface EntityInstance
{
    int getLevel();
    boolean isUpdated();
    boolean isDeleted();
    boolean isCreated();
    boolean isIncluded();
    boolean isExcluded();
    boolean isHidden();
    boolean isVersioned();

    ViewEntity     getViewEntity();
    EntityInstance getParent();
    EntityInstance getPrevTwin();
    EntityInstance getNextTwin();

    UUID getEntityUuid();
    UUID getObjectInstanceUuid();

    long getEntityKey();
    long getObjectInstanceId();

    EntityInstance createTemporalSubobjectVersion();
    EntityInstance acceptSubobject();
    void acceptTemporalEntity();
    EntityInstance cancelSubobject();
    CursorResult cancelTemporalEntity();

    /**
     * Performs validation on this entity and all child subobjects:
     *  1) Checks for required attributes.
     *  2) Validates min/max cardinality.
     *
     *  @returns a list of exceptions found.  If no exceptions found,
     *  returns null.
     */
    Collection<ZeidonException> validateSubobject();

    CursorResult  deleteEntity();
    CursorResult  dropEntity();
    CursorResult  excludeEntity();

    //
    // Attribute methods.
    //
    AttributeInstance getAttribute( String attributeName );
    AttributeInstance getAttribute( ViewAttribute viewAttribute );
    /**
     * Creates a work attribute for this entity type.
     *
     * @param config
     * @return
     */
    AttributeInstance createDynamicViewAttribute( DynamicViewAttributeConfiguration config );
    boolean isAttributeNull( String attributeName );
    boolean isAttributeNull( ViewAttribute viewAttribute );
    boolean isAttributeUpdated( ViewAttribute viewAttribute );
    EntityInstance setAttribute( String attributeName, Object value ) throws InvalidAttributeValueException;
    EntityInstance setAttribute( String attributeName, Object value, String contextName ) throws InvalidAttributeValueException;
    EntityInstance setAttribute( ViewAttribute viewAttribute, Object value ) throws InvalidAttributeValueException;
    EntityInstance setAttribute( ViewAttribute viewAttribute, Object value, String contextName ) throws InvalidAttributeValueException;
    EntityInstance setAttributeFromAttribute( String tgtAttributeName, View srcView, String srcEntityName, String srcAttributeName ) throws InvalidAttributeValueException;

    /**
     * Sets the value of the attribute without attempting to convert it first.  This still validates that the
     * value is well-formed.  Does *NOT* set the update flag.
     *
     * @param viewAttribute
     * @param value
     * @param setIncremental TODO
     * @return
     * @throws InvalidAttributeValueException
     */
    EntityInstance setInternalAttributeValue( ViewAttribute viewAttribute, Object value, boolean setIncremental ) throws InvalidAttributeValueException;

    /**
     * Sets the incremental update flags for this entity.  Does not set OI flags.
     *
     * @param flags
     * @return
     */
    EntityInstance setIncrementalFlags( EnumSet<IncrementalEntityFlags> flags );
    EntityInstance setIncrementalFlags( IncrementalEntityFlags flag );

    Object getInternalAttributeValue( String attributeName );
    Object getInternalAttributeValue( ViewAttribute viewAttribute );

    String getStringFromAttribute( ViewAttribute viewAttribute );
    String getStringFromAttribute( String attributeName );
    String getStringFromAttribute( ViewAttribute viewAttribute, String contextName );
    String getStringFromAttribute( String attributeName, String contextName );

    Integer getIntegerFromAttribute( ViewAttribute viewAttribute );
    Integer getIntegerFromAttribute( String attributeName );
    Integer getIntegerFromAttribute( ViewAttribute viewAttribute, String contextName );
    Integer getIntegerFromAttribute( String attributeName, String contextName );

    Double getDoubleFromAttribute( ViewAttribute viewAttribute );
    Double getDoubleFromAttribute( String attributeName );
    Double getDoubleFromAttribute( ViewAttribute viewAttribute, String contextName );
    Double getDoubleFromAttribute( String attributeName, String contextName );

    DateTime getDateTimeFromAttribute( ViewAttribute viewAttribute );
    DateTime getDateTimeFromAttribute( String attributeName );
    DateTime getDateTimeFromAttribute( ViewAttribute viewAttribute, String contextName );
    DateTime getDateTimeFromAttribute( String attributeName, String contextName );

    Blob getBlobFromAttribute( String attributeName );
    Blob getBlobFromAttribute( ViewAttribute viewAttribute );

    /**
     * Returns a string representation of the key values of this entity.  If all keys are
     * null then returns null.
     *
     * @return
     */
    String getKeyString();

    Object addToAttribute( String attributeName, Object value );
    Object addToAttribute( ViewAttribute viewAttribute, Object value );
    Object multiplyAttribute( String attributeName, Object value );
    Object multiplyAttribute( ViewAttribute viewAttribute, Object value );

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
    int compareAttribute( ViewAttribute viewAttribute, Object value );
    int compareAttribute( String attributeName, EntityInstance entityInstance, String attributeName2 );
    int compareAttribute( ViewAttribute viewAttribute, EntityInstance entityInstance, ViewAttribute viewAttribute2 );

    /**
     * Iterates through all the child entities that match childViewEntity, including
     * hidden entities.
     *
     * @param childViewEntity
     * @return
     */
    EntityIterator<? extends EntityInstance> getChildren( ViewEntity childViewEntity );
    EntityIterator<? extends EntityInstance> getChildren( String childEntityName );
    EntityIterator<? extends EntityInstance> getChildren( ViewEntity childViewEntity, boolean allowHidden );

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
     * Returns an iterable list of entities linked with 'this'.  If there
     * are no linked entities it will return an empty list.
     *
     * @return
     */
    Collection<? extends EntityInstance> getLinkedInstances();

    /**
     * Copies attributes from source to 'this' matching by name.
     *
     *   zSET_NULL    - Set, if the current value of the target attribute is null  (except KEYs)
     *   zSET_KEYS    - Set target attributes that represent KEY's.
     *   zSET_NOTNULL - Set, even if current value of the target attribute is not null.  (except KEYs)
     *   zSET_ALL     - Set all target attributes.
     *   zSET_SRCNOTNULL - Only set attribute if the src value is not null.
     *
     */

    int setMatchingAttributesByName( View source, String sourceViewEntity, EnumSet<SetMatchingFlags> control );
    int setMatchingAttributesByName( View source, ViewEntity viewEntity, EnumSet<SetMatchingFlags> control );
    int setMatchingAttributesByName( EntityInstance sourceInstance, EnumSet<SetMatchingFlags> control );
    int setMatchingAttributesByName( EntityInstance sourceInstance );

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
