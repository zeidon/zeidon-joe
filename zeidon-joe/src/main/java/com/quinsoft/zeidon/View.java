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
 * A View is the main interface for manipulating Zeidon data organized in Object Instances
 * (OIs).  A view is a set of EntityCursors, one for each entity specified in the LOD.
 * The EntityCursor is used to scan through existing Entity Instances in an OI.
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

    /**
     * Returns the LodDef for this View.
     *
     * @return the LodDef for this view.
     */
    LodDef getLodDef();

    /**
     * Returns 'true' if this View is read-only.  A read-only view can not be used
     * to update the underlying OI.  It is possible for a different view to update
     * the same OI.
     *
     * @return true if this View is read-only.
     */
    boolean isReadOnly();

    /**
     * Used to set the read-only flag for this view.
     *
     * @param readOnly if 'true' then this View will be read-only.
     */
    void setReadOnly( boolean readOnly );

    /**
     * If true then allow cursors to refer to hidden entities without throwing
     * an exception.  Intended to be used  by DBHandlers.
     *
     * @return true if the view can reference hidden (e.g. deleted) entities.
     */
    boolean isAllowHiddenEntities();

    /**
     * If set to true then this view can reference hidden entities without throwing
     * an exception.  Intended to be used by DBHandlers.
     *
     * @param allowHiddenEntities
     *
     * @return the previous value of allowHiddenEntities.
     */
    boolean setAllowHiddenEntities( boolean allowHiddenEntities );

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

    /**
     * Returns the EntityCursor for the entity specified by name.
     *
     * @param entityName name of the entity.
     *
     * @return the EntityCursor.
     */
    EntityCursor cursor( String entityName );

    /**
     * Returns the EntityCursor for the entity specified by EntityDef.
     *
     * @param entityDef definition of the entity.
     *
     * @return the EntityCursor.
     */
    EntityCursor cursor( EntityDef entityDef );

    /**
     * Synonym for cursor( String entityName );
     *
     * @param entityName name of the entity.
     *
     * @return the EntityCursor.
     */
    EntityCursor getCursor( String entityName );

    /**
     * Synonym for cursor( EntityDef entityDef ).
     *
     * @param entityDef definition of the entity.
     * @return the EntityCursor.
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

    /**
     * Writes the OI to a file using POR formatting.
     *
     * @deprecated use view.serializeOi() methods instead.
     *
     */
    @Deprecated
    void writeOiToFile( String filename, EnumSet<WriteOiFlags> control );

    /**
     * Writes the OI to a writer using POR formatting.
     *
     * @deprecated use view.serializeOi() methods instead.
     *
     */
    @Deprecated
    void writeOi( Writer writer, EnumSet<WriteOiFlags> flags );

    /**
     * Writes the OI to a writer using POR formatting.
     *
     * @deprecated use view.serializeOi() methods instead.
     *
     */
    @Deprecated
    void writeOi( Writer writer );

    /**
     * Writes the OI to a writer using POR formatting.
     *
     * @deprecated use view.serializeOi() methods instead.
     *
     */
    @Deprecated
    void writeOi( Writer writer, WriteOiFlags flag );

    /**
     * Writes the OI to a writer using POR formatting.
     *
     * @deprecated use view.serializeOi() methods instead.
     *
     */
    @Deprecated
    void writeOi( Writer writer, WriteOiFlags... flags );

    /**
     * Writes the OI to a writer using POR formatting.
     *
     * @deprecated use view.serializeOi() methods instead.
     *
     */
    @Deprecated
    void writeOiToXml( String filename, EnumSet<WriteOiFlags> control );

    /**
     * Writes the OI to a blob using POR formatting.
     *
     * @deprecated use view.serializeOi() methods instead.
     *
     */
    @Deprecated
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
     *
     * This method is thread-safe, meaning that multiple threads can create a new
     * view from the same one.
     *
     * @return new view.
     */
    View newView();

    /**
     * Create a new view but set its owning task to a different task.
     *
     * This method is thread-safe, meaning that multiple threads can create a new
     * view from the same one.
     *
     * @param owningTask the view will be assigned to this task.
     *
     * @return
     */
    View newView( TaskQualification owningTask );

    /**
     * Create a new view.  If readOnly is true then the new view will be read-only.
     *
     * This method is thread-safe, meaning that multiple threads can create a new
     * view from the same one.
     *
     * @param readOnly if true then the new view will be read-only
     *
     * @return new view.
     */
    View newView( boolean readOnly );

    /**
     * Create a new view.  If readOnly is true then the new view will be read-only.
     * The owning task is specified by owningTask.
     *
     * This method is thread-safe, meaning that multiple threads can create a new
     * view from the same one.
     *
     * @param owningTask the view will be assigned to this task.
     * @param readOnly if true then the new view will be read-only
     *
     * @return the new view.
     */
    View newView( TaskQualification owningTask, boolean readOnly );

    /**
     * Creates a new Object Instance by copying or including the entity instances from this view.
     * Whether an entity instance is created or included form the source OI is dependent on the
     * permissions as specified in the LOD.
     *
     * @return duplicated OI.
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
     * Drops all view names for this view and flags this view as invalid.
     *
     * This will only attempt to remove the view from the default application view list.
     * If this view was named for a different application then it must be removed explicitly.
     *
     * Note: this only drops the view but does not drop the OI unless there are no
     * other Views that reference the OI.  Dropping a view will have no effect on
     * other views.
     */
    void drop();

    /**
     * Writes the OI to the Zeidon logs.
     */
    void logObjectInstance();

    /**
     * @deprecated Use logObjectInstance instead.
     */
    @Deprecated
    void displayObjectInstance();

    /**
     * Commit the changes to this OI to the backing DB.
     *
     * @return
     */
    int commit();

    /**
     * Commits the changes to this OI to the backing DB with options specified.
     *
     * @param options
     *
     * @return
     */
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

    /**
     * Sets the name for this view inside its task.  This view can be retrieved by
     * its name given its task.  For example:
     *
     * <pre><code>
     *      View view = task.activate....();
     *      view.setName( "MyName" );
     *      ...
     *      View anotherView = task.getViewByName( "MyName" ); // anotherView == view
     * </code></pre>
     *
     * @param name Name of the view.
     */
    void setName(String name);

    /**
     * Drops a name for the view.  This does not drop the view.
     *
     * @param name name to be dropped.
     */
    void dropNameForView(String name);

    /**
     * Gets the list of all names for this view.
     *
     * @return list of all names for this view.
     */
    Collection<String> getNameList();

    /**
     * Retrieves a view by name that was assigned under a subtask view.  See
     * setNameForSubtaskView for more info.
     *
     * @param name Name of view to retrieve.
     *
     * @return named view if it exists.
     */
    View getViewByNameForSubtask(String name);

    /**
     * Names a view within another view (which is known as a subtask).  This allows
     * views to be grouped together under a single view.  View names do not have to be
     * unique across all views.  For example:
     *
     * <code>
     *  View subtask1 = task.activate...();
     *  View subtask2 = task.activate...();
     *  View v1 = task.activate...();
     *  view v2 = task.activate...();
     *
     *  subtask1.setNameForSubtask( "MyName", v1 );
     *  subtask2.setNameForSubtask( "MyName", v2 );
     *
     *  View temp = subtask1.getViewByNameForSubtask( "MyName" ); // temp == v1
     * </code>
     *
     * @param name Name to give to subtask view.
     * @param view View that will be named under the subtask view.
     */
    void setNameForSubtask(String name, View view);

    /**
     * Drops the name for a subtask view.
     *
     * @param name Name to drop
     * @param view view to drop.  TODO: Is this necessary?
     */
    void dropNameForSubtask(String name, View view);

    /**
     * Returns the view names that have been added under this subtask.
     *
     * @return list of subtask view names.
     */
    Collection<String> getSubtaskNameList();

    /**
     * @deprecated use the appropriate getViewByName.
     */
    @Deprecated
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

    /**
     * Returns true if the lazy-load is enabled for this View.  See setLazyLoad for more.
     *
     * @return true if Lazy Load is enabled for this view.
     */
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