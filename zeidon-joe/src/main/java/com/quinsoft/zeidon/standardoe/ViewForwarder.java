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

import java.io.Writer;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.DuplicateOiOptions;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.Level;
import com.quinsoft.zeidon.SelectSet;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * @author DG
 *
 */
public abstract class ViewForwarder extends AbstractTaskQualification implements View
{
    private ViewImpl view;

    /**
     * @param app
     */
    protected ViewForwarder( ViewImpl view )
    {
        super( view.getApplication() );
        this.setView( view );
    }

    protected ViewForwarder( Application application )
    {
        super( application );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.TaskQualification#getSystemTask()
     */
    @Override
    public Task getSystemTask()
    {
        return getView().getSystemTask();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.TaskQualification#getViewByName(java.lang.String)
     */
    @Override
    public View getViewByName( String name )
    {
        return getView().getViewByName( name );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.TaskQualification#getViewByKey(long)
     */
    @Override
    public View getViewByKey( long key )
    {
        return getViewByKey( key );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Lockable#getLock()
     */
    @Override
    public ReentrantReadWriteLock getLock()
    {
        return getView().getLock();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getLodDef()
     */
    @Override
    public LodDef getLodDef()
    {
        return getView().getLodDef();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#isReadOnly()
     */
    @Override
    public boolean isReadOnly()
    {
        return getView().isReadOnly();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#setReadOnly(boolean)
     */
    @Override
    public void setReadOnly( boolean readOnly )
    {
        getView().setReadOnly( readOnly );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getId()
     */
    @Override
    public long getId()
    {
        return getView().getId();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getOiId()
     */
    @Override
    public long getOiId()
    {
        return getView().getOiId();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#cursor(java.lang.String)
     */
    @Override
    public EntityCursor cursor( String entityName )
    {
        return getView().cursor( entityName );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#cursor(com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public EntityCursor cursor( EntityDef entityDef )
    {
        return getView().cursor( entityDef );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getCursor(java.lang.String)
     */
    @Override
    public EntityCursor getCursor( String entityName )
    {
        return getView().getCursor( entityName );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getCursor(com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public EntityCursor getCursor( EntityDef entityDef )
    {
        return getView().getCursor( entityDef );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#createSelectSet()
     */
    @Override
    public SelectSet createSelectSet()
    {
        return getView().createSelectSet();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getSelectSet()
     */
    @Override
    public SelectSet getSelectSet()
    {
        return getView().getSelectSet();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getSelectSet(int)
     */
    @Override
    public SelectSet getSelectSet( Object index )
    {
        return getView().getSelectSet( index );
    }

    @Override
    public Object setCurrentSelectSet( Object key )
    {
        return getView().setCurrentSelectSet( key );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#writeOiToFile(java.lang.String, long)
     */
    @Override
    public void writeOiToFile( String filename, EnumSet<WriteOiFlags> control )
    {
        getView().writeOiToFile( filename, control );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#writeOiToXml(java.lang.String, long)
     */
    @Override
    public void writeOiToXml(String filename, EnumSet<WriteOiFlags> control)
    {
        getView().writeOiToXml( filename, control );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#writeOiToBlob(long)
     */
    @Override
    public Blob writeOiToBlob( long control )
    {
        return getView().writeOiToBlob( control );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getHierEntityList()
     */
    @Override
    public Iterable<EntityInstance> getHierEntityList()
    {
        return getView().getHierEntityList();
    }

    @Override
    public Iterable<EntityInstance> getHierEntityList( boolean includeRoot )
    {
        return getView().getHierEntityList( includeRoot );
    }

    @Override
    public Iterable<EntityInstance> getHierEntityList( boolean includeRoot, String entityName )
    {
        return getView().getHierEntityList( includeRoot, entityName );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getEntityByHierPosition(long)
     */
    @Override
    public EntityInstance getEntityByHierPosition( long position )
    {
        return getView().getEntityByHierPosition( position );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#newView()
     */
    @Override
    public View newView()
    {
        return getView().newView();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#newView( com.quinsoft.zeidon.Task)
     */
    @Override
    public View newView( TaskQualification task )
    {
        return getView().newView( task );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#activateOiFromOi(java.util.Set)
     */
    @Override
    public View activateOiFromOi( Set<ActivateFlags> flags )
    {
        return getView().activateOiFromOi( flags );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#activateOiFromOi(com.quinsoft.zeidon.ActivateFlags)
     */
    @Override
    public View activateOiFromOi( ActivateFlags flag )
    {
        return getView().activateOiFromOi( flag );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#drop()
     */
    @Override
    public void drop()
    {
        getView().drop();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#displayObjectInstance()
     */
    @Override
    public void logObjectInstance()
    {
        getView().logObjectInstance();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#displayObjectInstance(long)
     */
    @Override
    public void logObjectInstance( long flags )
    {
        getView().logObjectInstance( flags );
    }

    /**
     * @deprecated Use logObjectInstance instead.
     */
    @Deprecated
    @Override
    public void displayObjectInstance( )
    {
        logObjectInstance( 0 );
    }

    /**
     * @deprecated Use logObjectInstance instead.
     */
    @Deprecated
    @Override
    public void displayObjectInstance( long flags )
    {
        logObjectInstance( flags );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#commit()
     */
    @Override
    public int commit()
    {
        return getView().commit();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#commit(com.quinsoft.zeidon.dbhandler.DbConfiguration)
     */
    @Override
    public int commit( CommitOptions options )
    {
        return getView().commit( options );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#dropDbLocks()
     */
    @Override
    public void dropDbLocks()
    {
        getView().dropDbLocks();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#setName(java.lang.String)
     */
    @Override
    public void setName( String name )
    {
        getView().setName( name );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#dropNameForView(java.lang.String)
     */
    @Override
    public void dropNameForView( String name )
    {
        getView().dropNameForView( name );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getNameList()
     */
    @Override
    public Collection<String> getNameList()
    {
        return getView().getNameList();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getViewByNameForSubtask(java.lang.String)
     */
    @Override
    public View getViewByNameForSubtask( String name )
    {
        return getView().getViewByNameForSubtask( name );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#setNameForSubtask(java.lang.String, com.quinsoft.zeidon.View)
     */
    @Override
    public void setNameForSubtask( String name, View view )
    {
        view.setNameForSubtask( name, view );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#dropNameForSubtask(java.lang.String, com.quinsoft.zeidon.View)
     */
    @Override
    public void dropNameForSubtask( String name, View view )
    {
        view.dropNameForSubtask( name, view );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getSubtaskNameList()
     */
    @Override
    public Collection<String> getSubtaskNameList()
    {
        return getView().getSubtaskNameList();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getViewByName(java.lang.String, com.quinsoft.zeidon.Level)
     */
    @Override
    public View getViewByName( String name, Level level )
    {
        return getView().getViewByName( name, level );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#setName(java.lang.String, com.quinsoft.zeidon.Level)
     */
    @Override
    public void setName( String name, Level level )
    {
        getView().setName( name, level );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#resetSubobjectTop()
     */
    @Override
    public void resetSubobjectTop()
    {
        getView().resetSubobjectTop();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#resetSubobject()
     */
    @Override
    public void resetSubobject()
    {
        getView().resetSubobject();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#reset()
     */
    @Override
    public void reset()
    {
        getView().reset();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#copyCursors(com.quinsoft.zeidon.View)
     */
    @Override
    public void copyCursors( View src )
    {
        getView().copyCursors( src );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.AbstractTaskQualification#getTask()
     */
    @Override
    public TaskImpl getTask()
    {
        return getView().getTask();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#equals(com.quinsoft.zeidon.View)
     */
    @Override
    public boolean equalsOi(View view)
    {
        return getView().equalsOi( view );
    }

    @Override
    public int relinkOis( View... otherViews )
    {
        return getView().relinkOis( otherViews );
    }

    protected ViewImpl getView()
    {
        return view;
    }

    protected void setView( ViewImpl view )
    {
        this.view = view;
    }

    @Override
    public void setLazyLoad( boolean lazyLoad )
    {
        getView().setLazyLoad( lazyLoad );
    }

    @Override
    public boolean isLazyLoad()
    {
        return getView().isLazyLoad();
    }

    @Override
    public boolean isUpdated()
    {
        return getView().isUpdated();
    }

    @Override
    public boolean isUpdatedFile()
    {
        return getView().isUpdatedFile();
    }

    @Override
    public void writeOi( Writer writer, EnumSet<WriteOiFlags> flags )
    {
        getView().writeOi( writer, flags );
    }

    @Override
    public void writeOi( Writer writer, WriteOiFlags flag )
    {
        getView().writeOi( writer, flag );
    }

    @Override
    public void writeOi( Writer writer, WriteOiFlags... flags )
    {
        getView().writeOi( writer, flags );
    }

    @Override
    public void writeOi( Writer writer )
    {
        getView().writeOi( writer );
    }

    @Override
    public View duplicateOi( )
    {
        return getView().duplicateOi();
    }

    @Override
    public View duplicateOi( DuplicateOiOptions options )
    {
        return getView().duplicateOi( options );
    }

    @Override
    public boolean isEmpty()
    {
        return getView().isEmpty();
    }

    @Override
    public Collection<ZeidonException> validateOi()
    {
        return getView().validateOi();
    }

    @Override
    public SerializeOi serializeOi()
    {
        return getView().serializeOi();
    }

    @Override
    public View newView( boolean readOnly )
    {
        return newView( readOnly );
    }

    @Override
    public View newView( TaskQualification owningTask, boolean readOnly )
    {
        return getView().newView( owningTask, readOnly );
    }

    @Override
    public boolean isLocked()
    {
        return getView().isLocked();
    }

//    @Override
    public void close() throws Exception
    {
        getView().drop();
    }

    @Override
    public int getEntityCount( boolean includeHidden )
    {
        return getView().getEntityCount( includeHidden );
    }
}
