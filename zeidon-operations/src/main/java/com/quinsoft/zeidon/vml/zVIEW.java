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

package com.quinsoft.zeidon.vml;

import java.io.Writer;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.DuplicateOiOptions;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.Level;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.SelectSet;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.UnknownLodDefException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonLogger;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * A wrapper around a View to allow VML generation to closely resemble generated .c.
 * This is a delegate wrapper around a view().
 *
 * @author DG
 *
 */
public class zVIEW extends VmlOperation implements View
{
    private View view;

    public zVIEW( View view )
    {
        super( view );
        this.view = view;
    }

    public zVIEW()
    {
        this( null );
    }

    public void setView( View view )
    {
        this.view = view;
        if ( view != null )
            setTaskQual( view );
    }

    public View getView()
    {
        return view;
    }

    /**
     * Returns the view.  If the view is null then an exception is thrown.
     *
     * @return
     */
    private View view()
    {
        if ( view == null )
            throw new ZeidonException( "VIEW does not reference a valid OI." );

        return view;
    }

    @Override
    public View activateOiFromOi(Set<ActivateFlags> flags)
    {
        return view().activateOiFromOi( flags );
    }

    @Override
    public View activateOiFromOi(ActivateFlags flag)
    {
        return view().activateOiFromOi( flag );
    }

    @Override
    public int commit()
    {
        return view().commit();
    }

    @Override
    public EntityCursor cursor(String entityName)
    {
        return view().cursor( entityName );
    }

    @Override
    public EntityCursor cursor(EntityDef entityDef)
    {
        return view().cursor( entityDef );
    }

    @Override
    public void logObjectInstance()
    {
        view().logObjectInstance();
    }

    @Override
    public void logObjectInstance(long flags)
    {
        view().logObjectInstance( flags );
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

    @Override
    public void dropDbLocks()
    {
        view().dropDbLocks();
    }

    @Override
    public void dropNameForSubtask(String name, View view)
    {
        view().dropNameForSubtask( name, view );
    }

    @Override
    public void dropNameForView(String name)
    {
        view().dropNameForView( name );
    }

    @Override
    public void drop()
    {
        view().drop();
    }

    @Override
    public EntityCursor getCursor(String entityName)
    {
        return view().getCursor( entityName );
    }

    @Override
    public EntityCursor getCursor(EntityDef entityDef)
    {
        return view().getCursor( entityDef );
    }

    @Override
    public Iterable<EntityInstance> getHierEntityList()
    {
        return view().getHierEntityList();
    }

    @Override
    public Iterable<EntityInstance> getHierEntityList( boolean includeRoot )
    {
        return view().getHierEntityList( includeRoot );
    }

    @Override
    public Collection<String> getNameList()
    {
        return view().getNameList();
    }

    @Override
    public Collection<String> getSubtaskNameList()
    {
        return view().getSubtaskNameList();
    }

    @Override
    public View getViewByName(String name, Level level)
    {
        return view().getViewByName( name, level );
    }

    @Override
    public View getViewByNameForSubtask(String name)
    {
        return view().getViewByNameForSubtask( name );
    }

    @Override
    public LodDef getLodDef()
    {
        return view().getLodDef();
    }

    @Override
    public boolean isReadOnly()
    {
        return view().isReadOnly();
    }

    @Override
    public View newView()
    {
        return view().newView();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#newView(com.quinsoft.zeidon.Task)
     */
    @Override
    public View newView( TaskQualification task )
    {
        return view().newView( task );
    }

    @Override
    public void resetSubobjectTop()
    {
        view().resetSubobjectTop();
    }

    @Override
    public void resetSubobject()
    {
        view().resetSubobject();
    }

    @Override
    public void setNameForSubtask(String name, View viewToBeNamed)
    {
        view().setNameForSubtask( name, viewToBeNamed );
    }

    @Override
    public void setName(String name)
    {
        view().setName( name );
    }

    @Override
    public void setName(String name, Level level)
    {
        view().setName( name, level );
    }

    @Override
    public void setReadOnly(boolean readOnly)
    {
        view().setReadOnly( readOnly );
    }

    @Override
    public void writeOiToFile(String filename, EnumSet<WriteOiFlags> control)
    {
        view().writeOiToFile( filename, control );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#writeOiToXml(java.lang.String, long)
     */
    @Override
    public void writeOiToXml(String filename, EnumSet<WriteOiFlags> control)
    {
        view().writeOiToXml( filename, control );
    }

    @Override
    public View activateEmptyObjectInstance(String LodDefName)
    {
        return view().activateEmptyObjectInstance( LodDefName );
    }

    @Override
    public View activateEmptyObjectInstance(String LodDefName, String appName)
    {
        return view().activateEmptyObjectInstance( LodDefName, appName );
    }

    @Override
    public View activateEmptyObjectInstance(String LodDefName, Application app)
    {
        return view().activateEmptyObjectInstance( LodDefName, app );
    }

    @Override
    public View activateEmptyObjectInstance(LodDef lodDef)
    {
        return view().activateEmptyObjectInstance( lodDef );
    }

    @Override
    public View activateObjectInstance(String LodDefName, View qual, EnumSet<ActivateFlags> control)
    {
        return view().activateObjectInstance( LodDefName, qual, control );
    }

    @Override
    public View activateObjectInstance(LodDef lodDef, View qual, EnumSet<ActivateFlags> control)
    {
        return view().activateObjectInstance( lodDef, qual, control );
    }

    @Override
    public View activateOiFromFile(String lodDefName, String filename)
    {
        return view().activateOiFromFile( lodDefName, filename, null );
    }

    @Override
    public View activateOiFromFile(String lodDefName, String filename, EnumSet<ActivateFlags> control)
    {
        return view().activateOiFromFile( lodDefName, filename, control );
    }

    @Override
    public View activateOiFromFile(LodDef lodDef, String filename, EnumSet<ActivateFlags> control)
    {
        return view().activateOiFromFile( lodDef, filename, control );
    }

    @Override
    public Application getApplication()
    {
        return view().getApplication();
    }

    @Override
    public Application getApplication(String appName)
    {
        return view().getApplication( appName );
    }

    @Override
    public ObjectEngine getObjectEngine()
    {
        return view().getObjectEngine();
    }

    @Override
    public Task getSystemTask()
    {
        return view().getSystemTask();
    }

    @Override
    public Task getTask()
    {
        return view().getTask();
    }

    @Override
    public View getViewByName(String name)
    {
        return view().getViewByName( name );
    }

    @Override
    public ZeidonLogger log()
    {
        return view().log();
    }

    @Override
    public ReentrantReadWriteLock getLock()
    {
        return view().getLock();
    }

    @Override
    public Blob writeOiToBlob(long control)
    {
        return view().writeOiToBlob( control );
    }

    @Override
    public String getTempDirectory()
    {
        return view().getTempDirectory();
    }

    @Override
    public long getId()
    {
        return view().getId();
    }

    @Override
    public long getOiId()
    {
        return view().getOiId();
    }

    @Override
    public View getViewByKey(long key)
    {
        return view().getViewByKey( key );
    }

    @Override
    public ZeidonLogger dblog()
    {
        return view().dblog();
    }

    @Override
    public void reset()
    {
        view().reset();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#copyCursors(com.quinsoft.zeidon.View)
     */
    @Override
    public void copyCursors(View src)
    {
        view().copyCursors( src );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#commit(com.quinsoft.zeidon.dbhandler.DbConfiguration)
     */
    @Override
    public int commit( CommitOptions options )
    {
        return view().commit( options );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#createSelectSet()
     */
    @Override
    public SelectSet createSelectSet()
    {
        return view().createSelectSet();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getSelectSet()
     */
    @Override
    public SelectSet getSelectSet()
    {
        return view().getSelectSet();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getSelectSet(int)
     */
    @Override
    public SelectSet getSelectSet(Object index)
    {
        return view().getSelectSet( index );
    }

    @Override
    public Object setCurrentSelectSet( Object key )
    {
        return view().setCurrentSelectSet( key );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getEntityByHierPosition(long)
     */
    @Override
    public EntityInstance getEntityByHierPosition(long position)
    {
        return view().getEntityByHierPosition( position );
    }

    @Override
    public String readZeidonConfig(String group, String key)
    {
        return view().readZeidonConfig( group, key );
    }

    @Override
    public String readZeidonConfig(String group, String key, String defaultValue)
    {
        return view().readZeidonConfig( group, key, defaultValue );
    }

    @Override
    public String toString()
    {
        if ( view == null )
            return "<null>";

        return view().toString();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#equals(com.quinsoft.zeidon.View)
     */
    @Override
    public boolean equalsOi(View view)
    {
        return this.view().equalsOi( view );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#relinkOis(com.quinsoft.zeidon.View, com.quinsoft.zeidon.View[])
     */
    @Override
    public int relinkOis( View... otherViews )
    {
        return this.view().relinkOis( otherViews );
    }

    @Override
    public Collection<ZeidonException> validateOi()
    {
        return view().validateOi();
    }

    @Override
    public View activateObjectInstance( ActivateOptions options ) throws UnknownLodDefException
    {
        return view().activateObjectInstance( options );
    }

    @Override
    public View activateObjectInstance( String lodDefName, View qual, ActivateOptions options )
    {
        return view().activateObjectInstance( lodDefName, qual, options );
    }

    @Override
    public Iterable<EntityInstance> getHierEntityList( boolean includeRoot, String entityName )
    {
        return view().getHierEntityList( includeRoot, entityName );
    }

    @Override
    public void setLazyLoad( boolean lazyLoad )
    {
        view().setLazyLoad( lazyLoad );
    }

    @Override
    public boolean isLazyLoad()
    {
        return view().isLazyLoad();
    }

    @Override
    public boolean isUpdated()
    {
        return view().isUpdated();
    }

    @Override
    public boolean isUpdatedFile()
    {
        return view().isUpdatedFile();
    }

    @Override
    public void writeOi( Writer writer, EnumSet<WriteOiFlags> flags )
    {
        view().writeOi( writer, flags );
    }

    @Override
    public void writeOi( Writer writer, WriteOiFlags flag )
    {
        view().writeOi( writer, flag );
    }

    @Override
    public void writeOi( Writer writer, WriteOiFlags... flags )
    {
        view().writeOi( writer, flags );
    }

    @Override
    public void writeOi( Writer writer )
    {
        view().writeOi( writer );
    }

    @Override
    public <T> T putCacheMap( Class<T> key, T value )
    {
        return view().putCacheMap( key, value );
    }

    @Override
    public <T> T getCacheMap( Class<T> key )
    {
        return view().getCacheMap( key );
    }

    @Override
    public String readZeidonConfig( String applicationName, String group, String key, String defaultValue )
    {
        return view().readZeidonConfig( applicationName, group, key, defaultValue );
    }

    @Override
    public String readZeidonConfig( Application application, String group, String key, String defaultValue )
    {
        return view().readZeidonConfig( application, group, key, defaultValue );
    }

    @Override
    public View duplicateOi( )
    {
        return view().duplicateOi();
    }

    @Override
    public View duplicateOi( DuplicateOiOptions options )
    {
        return view().duplicateOi( options );
    }

    @Override
    public ActivateOptions getActivateOptions()
    {
        return view().getActivateOptions();
    }

    @Override
    public boolean isEmpty()
    {
        return view().isEmpty();
    }

    @Override
    public List<View> activateOisFromStream( DeserializeOi options ) throws UnknownLodDefException
    {
        return view().activateOisFromStream( options );
    }

    @Override
    public SerializeOi serializeOi()
    {
        return view().serializeOi();
    }

    @Override
    public DeserializeOi deserializeOi()
    {
        return view().deserializeOi();
    }
    @Override
    public View newView( boolean readOnly )
    {
        return newView( readOnly );
    }

    @Override
    public View newView( TaskQualification owningTask, boolean readOnly )
    {
        return view().newView( owningTask, readOnly );
    }

    @Override
    public boolean isLocked()
    {
        return view().isLocked();
    }

//    @Override
    public void close() throws Exception
    {
        drop();
    }

    @Override
    public int getEntityCount( boolean includeHidden )
    {
        return view().getEntityCount( includeHidden );
    }
}