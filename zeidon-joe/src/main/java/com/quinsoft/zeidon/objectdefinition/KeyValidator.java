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
package com.quinsoft.zeidon.objectdefinition;

import java.util.HashMap;
import java.util.HashSet;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.KeyValidator.KeyValidationError;
import com.quinsoft.zeidon.utils.QualificationBuilder;

/**
 * Used to validate keys for an OI that originates from an unknown client.  It is
 * intended to be used to prevent a client from changing key values to maliciously
 * update entities that they don't have access to.
 *
 */
public class KeyValidator
{
    private final View sourceView;
    private       View persistentView;

    private final QualificationBuilder qualBuilder;
    private HashMap<String, String> parentKeys;
    private HashSet<EntityDef> excludedEntities;

    /**
     * @return The qualification view for this validation.  We're making it public to
     * make it easier to test.
     */
    public QualificationBuilder getQualBuilder()
    {
        return qualBuilder;
    }

    /**
     *
     */
    public KeyValidator( View sourceView )
    {
        this.sourceView = sourceView;

        // Load what's on the DB.  Load just keys for efficiency.
        qualBuilder = new QualificationBuilder( sourceView )
                .setLodDef( sourceView.getLodDef() )
                .fromEntityList( sourceView.root() )
                .keysOnly();
    }

    /**
     * Validates the keys with what's currently on the DB.  Will throw an exception if there
     * is an error.
     */
    public void validate() throws KeyValidationError
    {
        excludeEntities();
        persistentView = qualBuilder.activate();
        loadKeys();
        validateKeys();
        System.out.println( "here" );
    }

    /**
     * Do some analysis on the incoming OI.  We can skip loading some entities.
     */
    private void excludeEntities()
    {
        excludedEntities = new HashSet<EntityDef>();
        for ( EntityDef entityDef = sourceView.getLodDef().getRoot();
                entityDef != null;
                entityDef = entityDef.getNextHier() )
        {
            if ( entityDef.isReadOnlySubobjectRoot() )
                entityDef = excludeEntity( entityDef );
        }
    }

    private EntityDef excludeEntity( EntityDef entityDef )
    {
        excludedEntities.add( entityDef );
        qualBuilder.excludeEntity( entityDef.getName() );
        return entityDef.getLastChildHier();
    }

    private void loadKeys()
    {
        parentKeys = new HashMap<String, String>();
        for ( EntityInstance ei : persistentView.getHierEntityList() )
        {
            if ( ei.getParent() == null )
                continue;

            parentKeys.put( ei.toString(), ei.getParent().toString() );
        }
    }

    private void validateKeys() throws KeyValidationError
    {
        for ( EntityInstance ei : sourceView.getHierEntityList() )
        {
            if ( ei.getParent() == null )
                continue;

            EntityDef entityDef = ei.getEntityDef();
            if ( excludedEntities.contains( entityDef ) )
                continue;

            // New entities won't be in the DB so nothing to compare.
            if ( ei.isCreated() || ei.isIncluded() )
                continue;

            // Skip entities that haven't been changed and have no children that have changed..
            if ( ! ei.isDeleted() && ! ei.isExcluded() && ! ei.isUpdated() && ! ei.isChildUpdated() )
                continue;

            String parentKey = parentKeys.get( ei.toString() );
            if ( parentKey == null )
                throw new KeyValidationError( "Key does not exist in persistent view: " + ei.toString() );

            if ( ! parentKey.equals( ei.getParent().toString() ) )
                throw new KeyValidationError( "Key does not match expected value: " + parentKey );
        }
    }

    /**
     * This exception is returned by the validation.  It is not intended to be returned to a
     * client because it potentially exposes to the client that we know they're fooling with keys.
     *
     */
    public static class KeyValidationError extends Exception
    {
        private static final long serialVersionUID = 1L;

        public KeyValidationError( String message )
        {
            super( message );
        }
    }
}