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

import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.dbhandler.DbHandler;
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
    private       View compareView;

    private final QualificationBuilder qualBuilder;

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
        compareView = qualBuilder.activate();
        System.out.println( "here" );
    }

    private void excludeEntities()
    {
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
        qualBuilder.excludeEntity( entityDef.getName() );
        return entityDef.getLastChildHier();
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
