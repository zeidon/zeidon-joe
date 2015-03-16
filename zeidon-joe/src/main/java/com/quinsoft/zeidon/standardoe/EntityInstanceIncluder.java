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

import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * This class defines the logic for including an entity instance into another
 * object.
 *
 * @author DGC
 *
 */
class EntityInstanceIncluder
{
    private final EntityInstanceImpl rootSource;
    private final CursorPosition rootPosition;
    private final EntityDef rootTargetEntityDef;
    private final EntityInstanceImpl rootTargetParent;
    private final EntityInstanceImpl rootTargetInstance;
    private final ObjectInstance targetOi;

    private EntityInstanceImpl rootInstance;

    /**
     * A convenience method for performing a full include.
     * @param targetInstance If specified, this is the twin that the new instance is inserted
     *                     before/after
     * @param targetEntityDef Target EntityDef of the include
     * @param targetParent Parent EI of the new instance.
     * @param targetOI Target OI of the include.
     * @param source The source of the include.  The new EI is linked to source.
     * @param position Specifies where the new instance is inserted relative to targetInstance.
     * @param rootOfInclude if true, then this is the root of the subobject being included.
     * @return Root of new entity instance.
     */
    static EntityInstanceImpl includeSubobject( EntityInstanceImpl targetInstance,
                                                EntityDef          targetEntityDef,
                                                EntityInstanceImpl targetParent,
                                                ObjectInstance     targetOi,
                                                EntityInstanceImpl source,
                                                CursorPosition     position,
                                                boolean            rootOfInclude )
    {
        // If targetInstance is specified then targetParent better be its parent.
        assert targetInstance == null || targetInstance.getParent() == targetParent;

        EntityInstanceIncluder includer =
                new EntityInstanceIncluder( source, position, targetEntityDef, targetParent, targetInstance, targetOi );

        // If this is the root validate that the includes is allowed.
        if ( rootOfInclude )
            includer.performValidation();

        EntityInstanceImpl newInstance = includer.performInclude();

        if ( ! rootOfInclude )
        {
            // This is not the root of the subobject.  If the targetEntityDef is flagged
            // as lazy load then tell the parent EI that this entityDef has already
            // been loaded.
            if ( targetEntityDef.getLazyLoadConfig().isLazyLoad() )
            {
                targetParent.getEntitiesLoadedLazily().add( targetEntityDef );
            }
        }

        EntitySpawner spawner = new EntitySpawner( newInstance );
        spawner.spawnInclude();
        includer.markRootIncluded();
        return newInstance;
    }

    /**
     * @param source The source of the include.  The new EI is linked to source.
     * @param targetOI Target OI of the include.
     * @param targetEntityDef Target EntityDef of the include
     * @param targetParent Parent EI of the new instance.
     * @param targetInstance If specified, this is the twin that the new instance is inserted
     *                     before/after
     * @param position Specifies where the new instance is inserted relative to targetInstance.
     */
    private EntityInstanceIncluder( EntityInstanceImpl source,
                                    CursorPosition position,
                                    EntityDef targetEntityDef,
                                    EntityInstanceImpl targetParent,
                                    EntityInstanceImpl targetInstance,
                                    ObjectInstance     targetOi )
    {
        this.rootSource = source;
        this.rootPosition = position;
        this.targetOi = targetOi;
        this.rootTargetEntityDef = targetEntityDef;
        this.rootTargetParent = targetParent;
        this.rootTargetInstance = targetInstance;

        rootInstance = null;
    }

    /**
     * Check to make sure the include is valid.
     */
    private void performValidation()
    {
        // Make sure entities are link compatible..
        EntityInstanceImpl.validateLinking( rootTargetEntityDef, rootSource.getEntityDef() );

        if ( ! rootTargetEntityDef.isInclude() && rootTargetEntityDef.getParent() != null )
            throw new ZeidonException( "Target Entity does not allow include." ).prependEntityDef( rootTargetEntityDef );

        if ( ! rootSource.getEntityDef().isIncludeSource() )
            throw new ZeidonException( "Source Entity is not flagged as include source." ).prependEntityDef( rootSource.getEntityDef() );

        // TODO: Check for read-only target view.

        // TODO: Check for DataRecords
        // TODO: Check for matching attributes if target LodDef does not have a datarecord.
        // TODO: Check for versioned instance.
        // TODO: Validate insert position (fnValidateInsertPosition).
        // TODO: Run constraints.
        // TODO: Implement fnValidSubobjectStructureMatch
        // TODO: Make sure source entity is not a child of the target's parent.
    }

    /**
     * Performs the include initialized by the constructor.
     *
     * Note: This does not automatically set the new included instance as "included". See
     *       markRootIncluded() for more.
     *
     * @return
     */
    private EntityInstanceImpl performInclude()
    {
        // Create the root instance and all children.  Sets rootInstance.
        createIncludedInstance( rootSource, rootTargetEntityDef, rootTargetParent, rootTargetInstance, rootPosition );

        // If the new entity is updated set OI flag to indicate it.
        // TODO: instance versioned?
        if ( ! targetOi.isUpdatedFile() && // Avoids the rest of the 'if' most of the time.
             ! rootInstance.getEntityDef().isDerived() && ! rootSource.getEntityDef().isDerived() &&
             ( rootInstance.isIncluded() || rootInstance.isCreated() || rootInstance.isUpdated() ) )
        {
            targetOi.setUpdated( true );
            targetOi.setUpdatedFile( true );
        }

        return rootInstance;
    }

    /**
     * Marks the root included instance as included so that a commit adds the relationship to the DB.  Some
     * logic (like relink) doesn't want the root marked as included.
     */
    private void markRootIncluded()
    {
        // Set the include flag only if:
        // o This is the root EI. All the other EIs added by "spawning" the
        // include have their
        // flags left alone.
        // o The target instance has a parent. If it has no parent then it is
        // a root EI and therefore won't be included into anything.
        if ( rootTargetParent != null )
            rootInstance.setIncluded( true );
    }

    /**
     * Checks to see if any of the direct children of targetParentEntityDef have the same relationship with
     * targetParentEntityDef that sourceEntityDef has with its parent.
     *
     * @param targetParentEntityDef
     * @param sourceEntityDef
     *
     * @return null if no entity with matching relationship found, otherwise the entity.
     */
    private EntityDef findChildIncludeEntityDef(EntityDef targetParentEntityDef, EntityDef sourceEntityDef)
    {
        EntityDef childByToken = null;
        for ( EntityDef childTgtEntityDef : targetParentEntityDef.getChildren() )
        {
            if ( sourceEntityDef.getErEntityToken() == childTgtEntityDef.getErEntityToken() &&
                 sourceEntityDef.getErRelToken() == childTgtEntityDef.getErRelToken() &&
                 sourceEntityDef.isErRelLink() == childTgtEntityDef.isErRelLink() )
            {
                // Check to see if the entities match by name also. If they do
                // then we've found our man. If not we'll save the current view
                // cursor in case we never find a match by name.
                //
                // TODO: Is it really possible for the same relationship to
                // exist with two different names?
                if ( sourceEntityDef.getName().equals( childTgtEntityDef.getName() ) )
                    return childTgtEntityDef;

                childByToken = childTgtEntityDef;
            }
        }

        return childByToken;
    }

    /**
     * Creates the new target instance from sourceInstance and all child entity instances.
     */
    private EntityInstanceImpl createIncludedInstance( final EntityInstanceImpl sourceInstance,
                                                       final EntityDef targetEntityDef,
                                                       final EntityInstanceImpl targetParent,
                                                       final EntityInstanceImpl targetInstance,
                                                       final CursorPosition position )
    {
        EntityInstanceImpl newInstance;
        newInstance = new EntityInstanceImpl( targetOi, targetEntityDef, targetParent, targetInstance, position );
        assert newInstance.getEntityDef() == targetEntityDef;

        // If rootInstance is null then this is the first EI that has been created so set rootInstance.
        if ( rootInstance == null )
            rootInstance = newInstance;
        else
        {
            if ( targetEntityDef.getLazyLoadConfig().isLazyLoad() )
            {
                newInstance.getParent().getEntitiesLoadedLazily().add( targetEntityDef );
            }
        }

        // TODO: Get oldest source version? .c code has this but it looks like
        // versioned instances aren't valid.

        newInstance.linkInternalInstances( sourceInstance );
        newInstance.copyFlags( sourceInstance );

        // Now loop through source's direct children and see if the need to be
        // copied to the current target.
        EntityInstanceImpl prevInstance = null;
        EntityDef sourceChildEntityDef = null;
        EntityDef targetChildEntityDef = null;
        for ( EntityInstanceImpl child : sourceInstance.getDirectChildren( false, false ) )
        {
            if ( child.isHidden() )
                continue;

            // Look for a child EntityDef of source that matches the child.
            // If sourceEntityDef = child.getEntityDef then we've already
            // found the target EntityDef.
            if ( sourceChildEntityDef != child.getEntityDef() )
            {
                sourceChildEntityDef = child.getEntityDef();
                targetChildEntityDef = findChildIncludeEntityDef( targetEntityDef, sourceChildEntityDef );

                // A new EntityDef means that prevInstance points to a
                // different EntityDef.
                // Null it out so that the next includeSubobject doesn't try to
                // link the new
                // instance with prevInstance.
                prevInstance = null;
            }

            if ( targetChildEntityDef == null )
                continue; // No matching EntityDef in the target object.

            if ( targetChildEntityDef.isDerived() )
                continue;

            // TODO: Check for versioning.

            prevInstance = createIncludedInstance( child, targetChildEntityDef, newInstance, prevInstance, CursorPosition.LAST );
        }

        // Now check to see if the parent of the source is a child of the target.  If so,
        // then it needs to be copied as well.
        EntityInstanceImpl sourceParent = sourceInstance.getParent();
        if ( sourceParent == null )
            return newInstance;

        // Look for a child entity of the target with the same ER/Rel tokens.  We're looking for a situation
        // like this:
        //
        //   Target:     Source:
        //
        //    (A)          C'
        //    / \          |
        //   B   C        (A')
        //                 |
        //                 B'
        //
        // If A is included from source to target we need to copy the C' entity
        // from the source.
        EntityDef sourceEntityDef = sourceInstance.getEntityDef();     // Source entity A'
        EntityDef sourceParentEntityDef = sourceParent.getEntityDef(); // Source entity C'
        for ( EntityDef childEntityDef : targetEntityDef.getChildren() )
        {
            // childEntityDef is one of the children of A.

            // Does C have same entity token as C'?
            if ( childEntityDef.getErEntityToken() != sourceParentEntityDef.getErEntityToken() )
                continue;

            // Is the relationship of C the same as A'?
            if ( childEntityDef.getErRelToken() != sourceEntityDef.getErRelToken() )
                continue;

            if ( childEntityDef.isErRelLink() == sourceEntityDef.isErRelLink() )
                continue;

            // If we get here then we need to copy C' to C.
            createIncludedInstance( sourceParent, childEntityDef, newInstance, null, CursorPosition.LAST );

            // We only need to copy one so we're done.
            break;
        }

        return newInstance;
    }
}
