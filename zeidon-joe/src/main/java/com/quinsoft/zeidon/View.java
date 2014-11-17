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

import java.io.Writer;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * @author DG
 *
 */
public interface View extends TaskQualification, CacheMap
{
    final static long DISPLAY_HIDDEN        = 0x00000001;
    final static long DISPLAY_EMPTY_ATTRIBS = 0x00000002;

    /**
     * Returns the application for this View.  The logic is as follows:
     *
     *  1) Gets the application of the LodDef.  If this application is not ZeidonSystem,
     *     then it returns the application.
     *  2) If the LodDef belongs to ZeidonSystem, then the default application of the
     *     parent task is returned.
     */
    @Override
    Application getApplication();

    LodDef getLodDef();
    boolean isReadOnly();
    void setReadOnly( boolean readOnly );

    /**
     * Returns an ID for the view that is unique for the JVM.
     *
     * @return unique key.
     */
    long getId();

    /**
     * Return an ID that is unique for this OI.  Different views that reference the
     * same OI will return the same OI ID.
     *
     * @return
     */
    long getOiId();

    EntityCursor cursor( String entityName );
    EntityCursor cursor( EntityDef entityDef );

    /**
     * Synonym for cursor( String entityName );
     *
     * @param entityName
     * @return
     */
    EntityCursor getCursor( String entityName );

    /**
     * Synonym for cursor( EntityDef entityDef ).
     *
     * @param entityDef
     * @return
     */
    EntityCursor getCursor( EntityDef entityDef );

    /**
     * @return The default select set.  If one doesn't exist then it will be created
     * and stored in an internal hash set.
     */
    SelectSet getSelectSet();

    /**
     * Returns the select set by index.  If one doesn't exist then it will be created
     * and stored in an internal hash set.
     *
     * @param index.
     * @return
     */
    SelectSet getSelectSet( Object index );

    /**
     * Creates a new SelectSet.  This is *not* stored in the internal SelectSet hash and
     * can not be retrieved through getSelectSet.
     *
     * @return new SelectSet.
     */
    SelectSet createSelectSet();

    /**
     * Set the default select set, i.e. the select set that will be returned
     * by getSelectSet().
     *
     * @param index
     * @return Previous key
     */
    Object setCurrentSelectSet( Object key );

    void writeOiToFile( String filename, EnumSet<WriteOiFlags> control );

    /**
     * Writes the OI to a Java Writer.
     *
     * @param writer
     */
    void writeOi( Writer writer, EnumSet<WriteOiFlags> flags );
    void writeOi( Writer writer );
    void writeOi( Writer writer, WriteOiFlags flag );
    void writeOi( Writer writer, WriteOiFlags... flags );
    void writeOiToXml( String filename, EnumSet<WriteOiFlags> control );

    Blob writeOiToBlob( long control );

    /**
     * Loops through all the entities in the OI in hierarchical order.
     * Sets the cursors.
     *
     * @return Iterable list of entity instances.
     */
    Iterable<EntityInstance> getHierEntityList( );

    /**
     * Loops through all the entities in the OI in hierarchical order.
     * Sets the cursors.
     *
     * @param includeRoot If false, then skip the root.
     *
     * @return Iterable list of entity instances.
     */
    Iterable<EntityInstance> getHierEntityList( boolean includeRoot );

    /**
     * Loops through all the entities in the OI that match entityName in hierarchical order.
     * Sets the cursors.
     *
     * @param includeRoot If false, then skip the root.
     * @param entityName If non-blank, then limit entities to this.
     *
     * @return Iterable list of entity instances.
     *
     */
    Iterable<EntityInstance> getHierEntityList( boolean includeRoot, String entityName );

    /**
     * Returns the entity instance by hierarchical positioning. Excludes hidden
     * entities, root = 0.
     */
    EntityInstance getEntityByHierPosition( long position );

    /**
     * Create a new view from the existing view and set the cursors to be the same.
     * @return new view.
     */
    View newView();

    /**
     * Create a new view but set its owning task to a different task.
     *
     * @param owningTask
     * @return
     */
    View newView( TaskQualification owningTask );

    /**
     * Create a new view that is readonly if readonly is true.
     *
     * @return
     */
    View newView( boolean readOnly );

    View newView( TaskQualification owningTask, boolean readOnly );

    /**
     * Creates a new Object Instance by copying or including the entity instances from this view.
     * Whether an entity instance is created or included form the source OI is dependent on the
     * permissions as specified in the LOD.
     *
     * @return
     */
    View duplicateOi();

    /**
     * Creates a new Object Instance by copying or including the entity instances from this view.
     * Whether an entity instance is created or included form the source OI is dependent on the
     * permissions as specified in the LOD.
     *
     * @param options
     * @return
     */
    View duplicateOi( DuplicateOiOptions options );

    /**
     * Creates an exact duplicate of the OI.
     * @param flags
     * @return
     */
    View activateOiFromOi( Set<ActivateFlags> flags );
    View activateOiFromOi( ActivateFlags flag );

    /**
     * @return The ActivateOptions used to activate the OI.  May return null if
     * there was no qualification.
     */
    ActivateOptions getActivateOptions();

    /**
     * Drops all view names for this view.  The view will not be cleaned up by the GC
     * until all application references are removed.
     *
     * This will only attempt to remove the view from the default application view list.
     * If this view was named for a different application then it must be removed explicitly.
     */
    void drop();

    void logObjectInstance();
    void logObjectInstance(long flags);

    /**
     * @deprecated Use logObjectInstance instead.
     */
    @Deprecated
    void displayObjectInstance();
    /**
     * @deprecated Use logObjectInstance instead.
     */
    @Deprecated
    void displayObjectInstance(long flags);

    int commit();
    int commit( CommitOptions options );

    /**
     * Run some validations on this OI.  Returns a list of validation exceptions.
     *
     * @return list of exceptions or null if there are no errors.
     */
    Collection<ZeidonException> validateOi();

    /**
     * Returns true if the OI of this view was activated with locking.
     */
    boolean isLocked();

    /**
     * Drops any outstanding pessimistic locks in the DB.
     */
    void dropDbLocks();

    /**
     * Relinks all the OIs specified in the view list with this one.
     *
     * @param otherViews other, optional, views that can be relinked all at once.
     *
     * @return total number of entities relinked.
     */
    int relinkOis( View... views );
    //
    // View name methods.
    //
    void setName(String name);
    void dropNameForView(String name);
    Collection<String> getNameList();

    View getViewByNameForSubtask(String name);
    void setNameForSubtask(String name, View view);
    void dropNameForSubtask(String name, View view);
    Collection<String> getSubtaskNameList();

    View getViewByName( String name, Level level );
    void setName(String name, Level level);

    /**
     * Reset the subobject cursor back to the top-level object.
     * (I.e. cancels all subobject cursors).
     */
    void resetSubobjectTop();

    /**
     * Resets the subobject cursor to point to its parent.
     */
    void resetSubobject();

    /**
     * Reset all cursors in the view.
     */
    void reset();

    /**
     * Copy the cursor values from src view.
     *
     * @param src - Source view.
     */
    void copyCursors( View src );

    /**
     * Returns true if the Object Instance referenced by 'view' has the same
     * entity/attributes values as this one.
     *
     * @param view
     * @return
     */
    boolean equalsOi( View view );

    /**
     * Enables/disables lazy loading for this view.  Note that other views
     * may still lazy load entities for this OI.
     *
     * @param lazyLoad
     */
    void setLazyLoad( boolean lazyLoad );

    boolean isLazyLoad();

    /**
     * @return true if the OI is thought to have non-persisted changes.  When true this flag is not
     * 100% accurate: an entity may be changed through this view but committed via a linked
     * OI; in this example isUpdated() will return true even though there are no outstanding
     * changes.  If false then this OI has no changes and this is always correct.
     */
    boolean isUpdated();

    /**
     * @return true if this OI was updated since it was last activated from a file.  If the OI
     * was never activated from a file then it is a synonym for isUpdated().
     */
    boolean isUpdatedFile();

    /**
     * Returns true if the OI does not have any entities.
     *
     * @return
     */
    boolean isEmpty();
    
    /**
     * Return the number of entities in the OI.
     * 
     * @param includeHidden if true, count hidden entities.
     * 
     * @return
     */
    int getEntityCount( boolean includeHidden );
}