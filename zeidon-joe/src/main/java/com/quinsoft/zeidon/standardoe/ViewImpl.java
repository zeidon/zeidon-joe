/**
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

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.DuplicateOiOptions;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.EntityIterator;
import com.quinsoft.zeidon.Level;
import com.quinsoft.zeidon.SelectSet;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler;
import com.quinsoft.zeidon.dbhandler.PessimisticLockingViaDb;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 *
 * @author DGC
 *
 */
class ViewImpl extends AbstractTaskQualification implements InternalView, Comparable<ViewImpl>
{
    private static final DuplicateOiOptions DEFAULT_COPY_OI_OPTIONS = new DuplicateOiOptions();

    private static final EnumSet<CreateEntityFlags> CREATE_OI_FLAGS = EnumSet.of( CreateEntityFlags.fNO_SPAWNING,
                                                                      CreateEntityFlags.fIGNORE_MAX_CARDINALITY,
                                                                      CreateEntityFlags.fIGNORE_PERMISSIONS);

    private final LodDef       lodDef;
    private final long         id;

    /**
     * Points to the owning task of this view.  This should only be non-null if the view
     * belongs to one task but the OI belongs to another task.  This can happen when an
     * OI is named at the system task but a temp view is created for another task.
     */
    private final TaskImpl     task;

    private ViewCursor         viewCursor;
    private ViewNameList       subtaskViewNameList = null;

    private Map<Object, SelectSet>  selectSets;
    private boolean enableLazyLoad = true;
    private Object defaultSelectSet = 0;

    /**
     * True if this view is read only.
     */
    private boolean isReadOnly = false;

    ViewImpl( TaskImpl task, LodDef lodDef )
    {
        super(task.getApplication() );
        this.lodDef = lodDef;
        id = task.getObjectEngine().getNextObjectKey();
        viewCursor = new ViewCursor( task, this, lodDef );
        task.addNewView( this );

        // The task for this view is the same as the OI so we'll rely on using the
        // task from the ViewCursor.
        this.task = null;
    }

    ViewImpl( TaskImpl task, ViewImpl source )
    {
        super(source.getTask().getApplication());
        this.lodDef = source.getLodDef();
        id = task.getObjectEngine().getNextObjectKey();
        viewCursor = new ViewCursor(this, source.viewCursor);
        task.addNewView( this );

        if ( task.equals( source.getTask() ) )
            this.task = null;
        else
            this.task = task;
    }

    ViewImpl( ObjectInstance oi )
    {
        super(oi.getLodDef().getApplication());
        task = oi.getTask();
        this.lodDef = oi.getLodDef();
        id = task.getObjectEngine().getNextObjectKey();
        viewCursor = new ViewCursor( this, oi );
        task.addNewView( this );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.TaskQualification#getTask()
     */
    @Override
    public TaskImpl getTask()
    {
        TaskImpl t = viewCursor.getTask();
        if ( ! t.isValid() )
            throw new ZeidonException("A view is referencing an OI that belongs to a dropped task.");

        if ( task != null )
            return task;

        return t;
    }

    @Override
    public Task getSystemTask()
    {
        return getTask().getSystemTask();
    }

    @Override
    public LodDef getLodDef()
    {
        return lodDef;
    }

    @Override
    public Application getApplication()
    {
        Application application = lodDef.getApplication();

        // If the application of the LodDef is the system application (i.e. ZeidonSystem)
        // then we'll return the default application for the the task instead of
        // ZeidonSystem.
        if ( application.equals( getSystemTask().getApplication() ) )
            return super.getApplication();

        return application;
    }

    ObjectInstance getObjectInstance()
    {
        return viewCursor.getObjectInstance();
    }

    @Override
    public EntityCursorImpl cursor(String entityName)
    {
        return viewCursor.getEntityCursor( entityName );
    }

    @Override
    public EntityCursorImpl cursor(EntityDef entityDef)
    {
        return viewCursor.getEntityCursor( entityDef );
    }

    @Override
    public void setName(String name)
    {
        getTask().setNameForView( name, this );
    }

    @Override
    public void dropNameForView(String name)
    {
        getTask().dropNameForView( name, this );
    }

    @Override
    public void drop()
    {
        dropDbLocks();
        getTask().dropView( this );
    }

    @Override
    public void logObjectInstance( )
    {
        logObjectInstance( 0 );
    }

    @Override
    public void logObjectInstance( long flags )
    {
        log().debug( "Displaying OI for %s", lodDef );

        for ( EntityInstanceImpl ei = viewCursor.getObjectInstance().getRootEntityInstance();
              ei != null;
              ei = ei.getNextTwin() )
        {
            ei.logEntity( true, 0 );
        }

        log().debug( "--- Done displaying ---" );
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
    public Iterable<EntityInstance> getHierEntityList()
    {
        return getHierEntityList( true, null );
    }

    @Override
    public Iterable<EntityInstance> getHierEntityList( boolean includeRoot )
    {
        return getHierEntityList( includeRoot, null );
    }

    @Override
    public Iterable<EntityInstance> getHierEntityList( boolean includeRoot, String entityName )
    {
        EntityDef entityDef = null;
        if ( ! StringUtils.isBlank( entityName ) )
            entityDef = getLodDef().getEntityDef( entityName );

        ObjectInstance oi = viewCursor.getObjectInstance();
        final EntityIterator<EntityInstanceImpl> iter = new IteratorBuilder(getObjectInstance())
                                                    .withOiScoping( oi )
                                                    .forEntityDef( entityDef )
                                                    .setLazyLoad( true )
                                                    .build();

        // Skip the root?
        if ( !includeRoot )
            iter.next();

        // We have to create an iterator so that we can set the cursor in next().
        return new Iterable<EntityInstance>()
        {
            @Override
            public Iterator<EntityInstance> iterator()
            {
                return new Iterator<EntityInstance>()
                {
                    @Override
                    public boolean hasNext()
                    {
                        return iter.hasNext();
                    }

                    @Override
                    public EntityInstance next()
                    {
                        EntityInstanceImpl ei = iter.next();
                        EntityDef entityDef = ei.getEntityDef();
                        EntityCursorImpl entityCursor = viewCursor.getEntityCursor( entityDef );
                        entityCursor.setCursor( ei );
                        return entityCursor.getEntityInstance();
                    }

                    @Override
                    public void remove()
                    {
                        iter.remove();
                    }
                };
            }
        };
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#writeOiToXml(java.lang.String, long)
     */
    @Override
    public void writeOiToXml(String filename, EnumSet<WriteOiFlags> control )
    {
        serializeOi().asXml().setFlags( control ).toFile( filename );
    }

    @Override
    public void writeOiToFile(String filename, EnumSet<WriteOiFlags> control)
    {
        serializeOi().setFlags( control ).toFile( filename );
    }

    @Override
    public Blob writeOiToBlob(long control)
    {
        OutputStreamWriter stream = null;
        try
        {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            stream = new OutputStreamWriter( byteArray );
            serializeOi().setFlags( control ).toWriter( stream );
            return new Blob( byteArray.toByteArray() );
        }
        finally
        {
            IOUtils.closeQuietly( stream );
        }
    }

    @Override
    public Collection<String> getNameList()
    {
        return getTask().getViewNameList( this );
    }

    @Override
    public int commit()
    {
        return getTask().commitMultipleOis( this );
    }

    @Override
    public int commit( CommitOptions options )
    {
        return getTask().commitMultipleOis( options, this );
    }

    @Override
    public void dropDbLocks()
    {
        ObjectInstance oi = getObjectInstance();
        synchronized ( oi )
        {
            if ( oi.isLocked() )
            {
                PessimisticLockingHandler releaser = getPessimisticLockingHandler();
                releaser.releaseLocks( this );
                getTask().removePessimisticLock( this );
                oi.setLocked( false );
            }
        }
    }

    @Override
    public boolean isReadOnly()
    {
        if ( isReadOnly )
            return true;

        return getObjectInstance().isReadOnly();
    }

    @Override
    public void setReadOnly( boolean b )
    {
        isReadOnly = b;
    }

    @Override
    public ViewImpl newView()
    {
        return newView( getTask(), false );
    }

    @Override
    public ViewImpl newView( TaskQualification owningTask )
    {
        return newView( owningTask, false );
    }

    @Override
    public ViewImpl newView( boolean readOnly )
    {
        return newView( getTask(), readOnly );
    }

    @Override
    public ViewImpl newView( TaskQualification owningTask, boolean readOnly )
    {
        // If the current view is locked then we can only create new views if they
        // are readOnly.
        if ( isLocked() && ! readOnly )
            throw new ZeidonException( "Views created from a locked view must be readOnly." );

        ViewImpl newView = new ViewImpl( (TaskImpl) owningTask.getTask(), this );
        if ( readOnly )
            newView.setReadOnly( true );

        return newView;
    }

    @Override
    public View getViewByName(String name)
    {
        return getTask().getViewByName( name );
    }

    @Override
    public View getViewByKey( long key )
    {
        return getTask().getViewByKey( key );
    }

    @Override
    public View getViewByNameForSubtask(String name)
    {
        return getSubtaskViewNameList().getViewByName( name );
    }

    @Override
    public void dropNameForSubtask(String name, View view )
    {
        getSubtaskViewNameList().dropNameForView( name, view );
    }

    @Override
    public Collection<String> getSubtaskNameList()
    {
        return getSubtaskViewNameList().getAllViewNames();
    }

    @Override
    public void setNameForSubtask(String name, View view)
    {
        getSubtaskViewNameList().setNameForView( name, view );
    }

    @Override
    public View getViewByName(String name, Level level)
    {
        switch ( level )
        {
            case SYSTEM:
                return getTask().getSystemTask().getViewByName( name );

            case APPLICATION:
                return getApplication().getViewByName( name );

            case TASK:
                return getViewByName( name );

            case SUBTASK:
                return getViewByNameForSubtask( name );
        }

        throw new ZeidonException("Internal error: unsupported level");
    }

    @Override
    public void setName(String name, Level level)
    {
        switch ( level )
        {
            case SYSTEM:
                getTask().getSystemTask().setNameForView( name, this );
                return;

            case APPLICATION:
                getApplication().setNameForView( name, this );
                return;

            case TASK:
                setName( name );
                return;

            case SUBTASK:
                setNameForSubtask( name, this );
                break;

            default:
                throw new ZeidonException( "Unknown level %s", level );
        }

        throw new ZeidonException("Level %s not valid for setNameForView", level );
    }


    @Override
    public View activateOiFromOi( ActivateFlags flag )
    {
        return activateOiFromOi( EnumSet.of( flag ) );
    }

    @Override
    public View activateOiFromOi(Set<ActivateFlags> flags)
    {
        ViewImpl srcView = this;

        // TODO: Make sure there are no outstanding temporal entities.

        LodDef lodDef = srcView.getLodDef();
        ViewImpl view = getTask().activateEmptyObjectInstance( lodDef );
        ObjectInstance oi = view.getObjectInstance();

        EntityInstanceImpl firstSrcEntityInstance;
        EntityInstanceImpl lastSrcEntityInstance;
        if ( flags.contains( ActivateFlags.fSINGLE ) )
        {
            // We only want the root pointed to by the root cursor.
            firstSrcEntityInstance = srcView.cursor( lodDef.getEntityDef( 0 ) ).getEntityInstance();
            lastSrcEntityInstance = firstSrcEntityInstance.getNextTwin();
        }
        else
        {
            // We want all entities.
            firstSrcEntityInstance = srcView.getObjectInstance().getRootEntityInstance();
            lastSrcEntityInstance = null;
        }

        // Set book-keeping flags in src OI.
        long hierIdx = 0;
        for ( EntityInstanceImpl srcEntityInstance = firstSrcEntityInstance;
              srcEntityInstance != lastSrcEntityInstance;
              srcEntityInstance = srcEntityInstance.getNextHier() )
        {
            srcEntityInstance.setHierIndex( hierIdx++ );
            srcEntityInstance.setRecordOwner( false );
        }

        // Copy source entities to target.
        for ( EntityInstanceImpl srcEntityInstance = firstSrcEntityInstance;
              srcEntityInstance != lastSrcEntityInstance;
              srcEntityInstance = srcEntityInstance.getNextHier() )
        {
            EntityDef entityDef = srcEntityInstance.getEntityDef();

            EntityCursorImpl cursor = view.cursor( entityDef );
            EntityInstanceImpl newInstance = cursor.createEntity( CursorPosition.NEXT, CREATE_OI_FLAGS );
            newInstance.setHierIndex( srcEntityInstance.getHierIndex() );

            // Find the record owner of srcEntityInstance.  Start out by assuming that
            // srcEntityInstance is the record owner.
            EntityInstanceImpl recordOwner = srcEntityInstance;
            if ( ! srcEntityInstance.isRecordOwner() )
            {
                for ( EntityInstanceImpl ei : srcEntityInstance.getAllLinkedInstances() )
                {
                    if ( ei.getObjectInstance() != srcEntityInstance.getObjectInstance() )
                        continue;

                    if ( ei.isRecordOwner() )
                    {
                        recordOwner = ei;
                        break;
                    }
                }
            }

            boolean copyPersist;
            if ( recordOwner == srcEntityInstance )
            {
                // srcEntityInstance is the owner.  Set flag and copy attributes.
                srcEntityInstance.setRecordOwner( true );
                copyPersist = true;
            }
            else
            {
                // srcEntityInstance is not the owner.  Find the corresponding entity
                // instance in the new OI by hierIndex.
                EntityInstanceImpl linked = oi.findByHierIndex( recordOwner.getHierIndex() );

                // Now link them.
                newInstance.linkInternalInstances( linked );

                // Copy only work attributes.
                copyPersist = false;
            }

            newInstance.copyAttributes( srcEntityInstance, copyPersist, true );
            newInstance.copyFlags( srcEntityInstance );
        }

        view.getObjectInstance().setUpdated( srcView.getObjectInstance().isUpdated() );
        view.getObjectInstance().setUpdatedFile( srcView.getObjectInstance().isUpdatedFile() );

        return view;
    }

    @Override
    public void resetSubobject()
    {
        viewCursor.resetSubobjectToParent();
    }

    @Override
    public void resetSubobjectTop()
    {
        viewCursor.resetSubobjectTop();
    }

    ViewCursor getViewCursor()
    {
        return viewCursor;
    }

    /**
     * Locking a view is locking the object instance.
     */
    @Override
    public ReentrantReadWriteLock getLock()
    {
        return getObjectInstance().getLock();
    }

    @Override
    public EntityCursor getCursor(String entityName)
    {
        return cursor( entityName );
    }

    @Override
    public EntityCursor getCursor(EntityDef entityDef)
    {
        return cursor( entityDef );
    }

    @Override
    public String toString()
    {
        return String.format( "%d %s", id, getLodDef() );
    }

    @Override
    public long getId()
    {
        return id;
    }

    @Override
    public long getOiId()
    {
        return getViewCursor().getObjectInstance().getId();
    }

    @Override
    public int compareTo(ViewImpl o)
    {
        return Long.valueOf( getId() ).compareTo( o.getId() );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( o instanceof ViewImpl )
            return compareTo( ((InternalView) o).getViewImpl() ) == 0;

        return false;
    }

    @Override
    public void reset()
    {
        viewCursor.reset();
    }

    /**
     * This is used to look for memory leaks of EntityInstanceImpl.
     *
     * @return
     */
    int countAllEntities()
    {
        int count = 0;
        HashSet<EntityInstanceImpl> allEntities = new HashSet<EntityInstanceImpl>();
        HashMap<String, Integer> entities = new HashMap<String, Integer>();
        for ( EntityInstanceImpl ei = getObjectInstance().getRootEntityInstance(); ei != null; ei = ei.getNextHier() )
        {
            ei.countAllVersions( allEntities );
            for ( EntityInstanceImpl linked : ei.getAllLinkedInstances( true ) )
            {
                if ( ! allEntities.contains( linked ) )
                    linked.countAllVersions( allEntities );
            }
        }

        for ( EntityInstanceImpl ei : allEntities )
        {
            String name = ( ei == null ) ? "null" : ei.getEntityDef().getName();
            Integer ec = entities.get( name );
            if ( ec == null )
                ec = 1;
            else
                ec = ec + 1;
            entities.put( name, ec );
        }

        count = allEntities.size();
        log().info( "Entities = " + entities.toString() );
        log().info( "Total entities = " + count );
        return count;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#copyCursors(com.quinsoft.zeidon.View)
     */
    @Override
    public void copyCursors(View src)
    {
        // TODO: should we attempt to copy cursor values using csr.getEntityInstance() if src
        // isn't a ViewImpl?

        ViewImpl source = ((InternalView) src).getViewImpl();
        if ( getLodDef() != source.getLodDef() )
            throw new ZeidonException( "Attempting to copy cursors from a different LodDef. Target = %s, source = %s", this, source );

        viewCursor = new ViewCursor(this, source.viewCursor);
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#createSelectSet()
     */
    @Override
    public SelectSet createSelectSet()
    {
        return new SelectSetImpl( this );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getSelectSet()
     */
    @Override
    public SelectSet getSelectSet()
    {
        return getSelectSet( defaultSelectSet );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getSelectSet(int)
     */
    @Override
    public SelectSet getSelectSet(Object index)
    {
        if ( selectSets == null )
            selectSets = new HashMap<Object,SelectSet>();

        SelectSet set = selectSets.get( index );
        if ( set == null )
        {
            set = createSelectSet();
            selectSets.put( index, set );
        }

        return set;
    }

    @Override
    public Object setCurrentSelectSet( Object key )
    {
        Object oldKey = defaultSelectSet;
        defaultSelectSet = key;
        return oldKey;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#getEntityByHierPosition(long)
     */
    @Override
    public EntityInstance getEntityByHierPosition( long position )
    {
        for ( EntityInstanceImpl ei = getObjectInstance().getRootEntityInstance();
              ei != null;
              ei = ei.getNextHier() )
        {
            if ( ei.isHidden() )
                continue;

            if ( position == 0 )
                return ei;

            position--;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#equals(com.quinsoft.zeidon.View)
     */
    @Override
    public boolean equalsOi(View v)
    {
        ViewImpl view = ((InternalView) v).getViewImpl();

        ObjectInstanceComparer comparer = new ObjectInstanceComparer( this.getObjectInstance(), view.getObjectInstance() );
//        comparer.displayDiff( getTask() );
        return comparer.areEqual();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.View#relinkOis(com.quinsoft.zeidon.View, com.quinsoft.zeidon.View[])
     */
    @Override
    public int relinkOis( View... views )
    {
        OiRelinker linker = new OiRelinker( this );
        linker.add( this.getObjectInstance() );
        if ( views != null )
        {
            for ( View v : views )
                linker.add( (((InternalView) v).getViewImpl()).getObjectInstance() );
        }

        return linker.relinkOis();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.InternalView#getViewImpl()
     */
    @Override
    public ViewImpl getViewImpl()
    {
        return this;
    }

    private synchronized ViewNameList getSubtaskViewNameList()
    {
        if ( subtaskViewNameList == null )
            subtaskViewNameList = new ViewNameList();

        return subtaskViewNameList;
    }

    /**
     * Reassigns the the OI referenced by this view to be part of 'task'.
     *
     * @param task
     */
    void reassignTask( Task task )
    {
        assert task == task.getSystemTask() : "Attempting to reassign to a non-system task.";
        getObjectInstance().setTask( (TaskImpl) task );
    }

    @Override
    public Collection<ZeidonException> validateOi()
    {
        Collection<ZeidonException> list = new ArrayList<ZeidonException>();

        // Run the validation on all the root entities.
        for ( EntityInstanceImpl ei = getObjectInstance().getRootEntityInstance(); ei != null; ei = ei.getNextTwin() )
        {
            if ( ei.isHidden() ) // Only validate non-hidden entities.
                continue;

            ei.validateSubobject( list );
        }

        if ( list.size() == 0 )
            return null;

        return list;
    }

    @Override
    public void setLazyLoad( boolean lazyLoad )
    {
        enableLazyLoad = lazyLoad;
    }

    @Override
    public boolean isLazyLoad()
    {
        return enableLazyLoad;
    }

    @Override
    public boolean isUpdated()
    {
        return getObjectInstance().isUpdated();
    }

    @Override
    public boolean isUpdatedFile()
    {
        return getObjectInstance().isUpdatedFile();
    }

    private EnumSet<WriteOiFlags> convertFlags( WriteOiFlags flag )
    {
        return EnumSet.of( flag );
    }

    private EnumSet<WriteOiFlags> convertFlags( WriteOiFlags... flags )
    {
        return EnumSet.copyOf( Arrays.asList( flags ) );
    }

    @Override
    public void writeOi( Writer writer, EnumSet<WriteOiFlags> flags )
    {
        serializeOi().setFlags( flags ).toWriter( writer );
    }

    @Override
    public void writeOi( Writer writer, WriteOiFlags flag )
    {
        writeOi( writer, convertFlags( flag ) );
    }

    @Override
    public void writeOi( Writer writer, WriteOiFlags... flags )
    {
        writeOi( writer, convertFlags( flags ) );
    }

    @Override
    public void writeOi( Writer writer )
    {
        writeOi( writer, WriteOiFlags.INCREMENTAL );
    }

    @Override
    public View duplicateOi( )
    {
        return duplicateOi( DEFAULT_COPY_OI_OPTIONS );
    }

    @Override
    public View duplicateOi( DuplicateOiOptions options )
    {
        Task owningTask = options.owningTask;
        if ( owningTask == null )
            owningTask = getTask();

        EntityDef rootEntityDef = getLodDef().getRoot();
        View copy = owningTask.activateEmptyObjectInstance( getLodDef() );
        EntityCursor rootCursor = copy.cursor( rootEntityDef );
        for ( EntityInstance srcInstance = getObjectInstance().getRootEntityInstance();
                             srcInstance != null;
                             srcInstance = srcInstance.getNextTwin() )
        {
            if ( srcInstance.isHidden() )
                continue;

            rootCursor.copySubobject( srcInstance, CursorPosition.NEXT );
        }

        return copy;
    }

    @Override
    public ActivateOptions getActivateOptions()
    {
        return getObjectInstance().getActivateOptions();
    }

    @Override
    public boolean isEmpty()
    {
        ObjectInstance oi = getObjectInstance();
        for ( EntityInstanceImpl ei = oi.getRootEntityInstance(); ei != null; ei = ei.getNextTwin() )
        {
            if ( ! ei.isHidden() )
                return false;  // OI is not empty
        }

        return true;  // If we get here then there are no non-hidden EIs.
    }

    @Override
    public SerializeOi serializeOi()
    {
        return new SerializeOi( this );
    }

    PessimisticLockingHandler getPessimisticLockingHandler()
    {
        //TODO: At some point we want to dynamically load the pessimistic locking handler.
        PessimisticLockingHandler lockingHandler = new PessimisticLockingViaDb();
        return lockingHandler;
    }

    @Override
    public boolean isLocked()
    {
        return getObjectInstance().isLocked();
    }

 //   @Override
    public void close() throws Exception
    {
        drop();
    }

    @Override
    public int getEntityCount( boolean includeHidden )
    {
        return getObjectInstance().getEntityCount( includeHidden );
    }
}