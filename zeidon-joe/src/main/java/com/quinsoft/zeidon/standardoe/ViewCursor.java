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
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * @author DG
 *
 */
class ViewCursor
{
    private final LodDef           lodDef;
    private final EntityCursorImpl cursorList[];
    private       ObjectInstance   objectInstance;

    /**
     * If this view was set to a recursive suboject this points to the root instance
     * of the subobject.
     */
    private EntityInstanceImpl recursiveRoot;

    /**
     * This is the parent of recursiveRoot.  This is only guaranteed to be correctly
     * set if recursiveRoot is null.  This is used to find the parent if we execute
     * recursiveRoot.create.
     */
    private EntityInstanceImpl recursiveRootParent;

    /**
     * When there is a subobject cursor defined, this represents the difference in levels
     * between recursiveRoot and its LodDef level.  When there's no subobject this is 0.
     */
    private int recursiveDiff;
    private int firstValidCursorIndex;
    private int lastValidCursorIndex;
    private ViewImpl view;

    ViewCursor( TaskImpl task, ViewImpl view, LodDef lodDef )
    {
        this( task, view, lodDef, null, null );
    }

    ViewCursor( ViewImpl view, ViewCursor sourceCursor )
    {
        this( sourceCursor.getTask(), view, sourceCursor.getLodDef(), sourceCursor, null );
    }

    ViewCursor( ViewImpl view, ObjectInstance oi )
    {
        this( oi.getTask(), view, oi.getLodDef(), null, oi );
    }

    /**
     * Create and initialize the entity cursors from a source cursor.
     *
     * @param task
     * @param lodDef
     * @param sourceCursor - If not null, then initialize the cursors in the new ViewCursor to point to the same entities.
     */
    private ViewCursor(TaskImpl task, ViewImpl view, LodDef lodDef, ViewCursor sourceCursor, ObjectInstance oi )
    {
        super();
        this.lodDef = lodDef;
        this.view = view;
        cursorList = new EntityCursorImpl[ lodDef.getEntityCount() ];

        for ( EntityDef entityDef : lodDef.getEntityDefs() )
        {
            int idx = entityDef.getHierIndex();

            if ( sourceCursor != null )
                cursorList[ idx ] = new EntityCursorImpl( this, sourceCursor.cursorList[ idx ] );
            else
                cursorList[ idx ] = new EntityCursorImpl( this, entityDef );
        }

        if ( oi != null )
        {
            objectInstance = oi;
            resetRecursiveParent();
        }
        else
        if ( sourceCursor != null )
        {
            objectInstance = sourceCursor.getObjectInstance();
            recursiveRoot = sourceCursor.getRecursiveRoot();
            recursiveRootParent = sourceCursor.getRecursiveRootParent();
            setRecursiveDiff( sourceCursor.getRecursiveDiff() );
            firstValidCursorIndex = sourceCursor.firstValidCursorIndex;
            lastValidCursorIndex = sourceCursor.lastValidCursorIndex;
        }
        else
        {
            objectInstance = new ObjectInstance(task, lodDef);
            resetRecursiveParent();
        }

        objectInstance.addReferringViewCursor( this );
    }

    TaskImpl getTask()
    {
        return objectInstance.getTask();
    }

    protected EntityCursorImpl getEntityCursor( String entityName )
    {
        return getEntityCursor( lodDef.getEntityDef( entityName ) );
    }

    protected EntityCursorImpl getEntityCursor( EntityDef entityDef )
    {
        return cursorList[ entityDef.getHierIndex() ];
    }

    protected ObjectInstance getObjectInstance()
    {
        return objectInstance;
    }

    protected LodDef getLodDef()
    {
        return lodDef;
    }

    void copyEntityCursors( ViewCursor source )
    {
        for ( int i = 0; i < cursorList.length; i++ )
            cursorList[ i ] = source.cursorList[ i ];
    }

    /**
     * Reset the subobject fields to indicate that we are no longer inside
     * a subobject.
     */
    void resetRecursiveParent()
    {
        recursiveRoot = null;
        recursiveRootParent = null;
        recursiveDiff = 0;
    }

    /**
     * Set the root of the recursive subobject to 'recursiveParent', which may be null.
     * If it is null, then recursiveGrandParent is the parent that will be used if
     * any recursiveParents are created after setting subobject.
     *
     * @param recursiveParent - The EI that will become the root of the new subobject.
     *                          May be null; if null then recursiveGrandParent MUST be
     *                          non-null.
     * @param recursiveParentEntityDef - The LodDef of recursiveParent.
     * @param recursiveGrandParent - The parent of recursiveParent.  Must be non-null if
     *                          recursiveParent is null.  Some subobject processing needs
     *                          to know that parent for creates/inserts.  This is undefined
     *                          if recursiveParent is not null.
     */
    void setRecursiveParent( EntityInstanceImpl recursiveParent,
                             EntityDef recursiveParentEntityDef,
                             EntityInstanceImpl recursiveGrandParent )
    {
        // One of the two EIs had better be non-null.
        assert recursiveParent != null || recursiveGrandParent != null : "Internal error: An EI must be non-null";
        assert recursiveParent == null || recursiveParent.getEntityDef() == recursiveParentEntityDef
                                       || recursiveParent.getEntityDef().getRecursiveParent() == recursiveParentEntityDef;

        this.recursiveRoot = recursiveParent;
        this.recursiveRootParent = recursiveGrandParent;
        int recursiveLevel;
        if ( recursiveParent == null )
            recursiveLevel = recursiveGrandParent.getDepth() + 1;
        else
            recursiveLevel = recursiveParent.getDepth();

        // Calculate the recursiveDiff.
        if ( recursiveParentEntityDef.getRecursiveParent() != null )
            recursiveParentEntityDef = recursiveParentEntityDef.getRecursiveParent();
        recursiveDiff = recursiveLevel - recursiveParentEntityDef.getDepth();
        if ( recursiveDiff == 0 )
        {
            // We've reset the cursor back to its "normal" state so there is no recursiveRoot.
            recursiveRoot = null;
            recursiveRootParent = null;
            return;
        }

        // Calculate which cursors are now valid with the new scope.
        firstValidCursorIndex = recursiveParentEntityDef.getHierIndex();
        lastValidCursorIndex = recursiveParentEntityDef.getLastChildHier().getHierIndex();
    }

    /**
     * Reset the subobjects.
     */
    void resetSubobjectTop()
    {
        EntityInstanceImpl currentRoot = getRecursiveRoot();
        if ( currentRoot == null && getRecursiveRootParent() == null ) // Nothing to do; it's already at the top.
            return;

        EntityDef entityDef;
        if ( currentRoot == null )
            entityDef = getRecursiveRootParent().getEntityDef();
        else
        {
            entityDef = currentRoot.getEntityDef();
            if ( entityDef.getRecursiveParent() != null )
                entityDef = entityDef.getRecursiveParent();
        }

        getEntityCursor( entityDef ).resetChildCursors( null );
        resetRecursiveParent();
        assert recursiveDiff == 0 : "recursiveDiff is not 0";
    }

    /**
     * @return true if set to parent, false if parent was already root of recursive subobject.
     */
    boolean resetSubobjectToParent()
    {
        EntityInstanceImpl currentRoot = getRecursiveRoot();
        if ( currentRoot == null && getRecursiveRootParent() == null )
            return false;

        if ( currentRoot != null )
        {
            // We need to find the ancestor of currentRoot that has the same ER entity token
            // as current root.
            EntityDef entityDef = currentRoot.getEntityDef();
            EntityDef recursiveParent = entityDef.getRecursiveParent();
            EntityInstanceImpl ancestor = currentRoot.findMatchingParent( recursiveParent );
            if ( ancestor == null )
                throw new ZeidonException( "Current subobject root has no valid ancestor" );

    //        setRecursiveParent( ancestor, ancestor.getEntityDef(), null );
    //        view.cursor( recursiveParent ).setCursor( ancestor );  // Set the cursor for the parent entity.
            getEntityCursor( entityDef ).setCursor( currentRoot );    // Set the cursor for the recursive child.
        }
        else
        {
            // currentRoot is null.  This means that recursiveRootParent must not be null and it points to
            // the parent entityDef of what currentRoot should be.
            currentRoot = getRecursiveRootParent();

            // Get the recursive parent entityDef.  It's possible that currentRoot does not point to the recursive
            // parent so we need to find it.
            EntityDef entityDef = currentRoot.getEntityDef();
            while ( ! entityDef.isRecursiveParent() )
                entityDef = entityDef.getParent();

            // Now find the parent EI.
            EntityInstanceImpl subobjectParent = currentRoot;
            if ( ! currentRoot.getEntityDef().isRecursive() )
                subobjectParent = currentRoot.findMatchingParent( entityDef );
            getEntityCursor( entityDef ).setCursor( subobjectParent );    // Set the cursor for the recursive child.
        }

        return true;
    }

    EntityInstanceImpl getRecursiveRoot()
    {
        return recursiveRoot;
    }

    private void setRecursiveDiff(int recursiveDiff)
    {
        this.recursiveDiff = recursiveDiff;
    }

    int getRecursiveDiff()
    {
        return recursiveDiff;
    }

    /**
     * Verifies that the supplied entity cursor is in scope.
     *
     * @param cursor
     * @return
     */
    boolean isCursorInScope( EntityCursor cursor )
    {
        if ( recursiveRoot == null && recursiveRootParent == null )
            return true;  // All cursors are in scope if there is no recursive subobject defined.

        EntityDef entityDef = cursor.getEntityDef();
        if ( entityDef.getHierIndex() < firstValidCursorIndex )
            return false;

        if ( entityDef.getHierIndex() > lastValidCursorIndex )
            return false;

        return true;
    }

    ViewImpl getView()
    {
        return view;
    }

    /**
     * Sets the root cursor to point to the first entity and all child cursors to NOT-SET.
     */
    void reset()
    {
        if ( objectInstance.getRootEntityInstance() == null )
            return;

        cursorList[0].setCursor( objectInstance.getRootEntityInstance() );
        resetRecursiveParent();
    }

    /**
     * Returns the parent of the subobject root.  This is specified when we
     * set subobject to a cursor that is null.  This is undefined if recursiveRoot
     * is not null.
     *
     * @return
     */
    EntityInstanceImpl getRecursiveRootParent()
    {
        return recursiveRootParent;
    }

    /**
     * Replaces the entity cursor with newCursor.
     */
    void replaceEntityCursor( EntityCursorImpl newCursor )
    {
        EntityDef entityDef = newCursor.getEntityDef();
        cursorList[ entityDef.getHierIndex() ] = newCursor;
    }
    
    void replaceObjectInstance( ObjectInstance newOi )
    {
        objectInstance = newOi;
        resetRecursiveParent();
    }
}
