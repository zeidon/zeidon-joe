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
package com.quinsoft.zeidon.dbhandler;

import java.util.EnumSet;
import java.util.List;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * The interface between Zeidon OE and the databases.
 *
 * @author DG
 *
 */
public interface DbHandler
{
    static final int LOAD_DONE        = 0;
    static final int LOAD_NO_ENTITIES = 1;

    /**
     * This is used in the qualification object to indicate that the qualification
     * is for the root entity.
     */
    static final String ROOT_ENTITY = "*root*";

    /**
     * Begins a transaction.  If it returns true then endTransaction must
     * be called immediately; i.e. the transaction isn't saved for later.
     * @param view TODO
     */
    boolean beginTransaction(View view );
    void endTransaction( boolean commit );

    int beginActivate( View view, View qual, EnumSet<ActivateFlags> control );

    /**
     * Load all viewEntities under the parent.
     * @param view View being activated.
     * @param entityDef
     * @return LOAD_DONE (0)        - Entities loaded OK
     *         LOAD_NO_ENTITIES (1) - No entities loaded.
     */
    int loadEntity( View view, EntityDef entityDef );

    /**
     * Returns true if there was qualification on entityDef.
     */
    boolean isQualified( EntityDef entityDef );

    int insertEntity( View view, List<? extends EntityInstance> entityInstances );
    int insertRelationship( View view, EntityInstance entityInstance );
    int deleteEntity( View view, EntityInstance entityInstance );
    int deleteAllChildren( View view, EntityInstance parent );
    int deleteRelationship( View view, EntityInstance entityInstance );
    int updateEntity( View view, EntityInstance entityInstance );
    int copyEntity( View view, EntityInstance entityInstance );

    /**
     * Returns any new keys created by the DB as part of an insert.  Ignored by
     * dbhandlers that use genkey handlers.
     *
     * @return
     */
    List<Object> getKeysGeneratedByDb();

    /**
     * If 'set' is true then the DB will autogenerate the genkeys.
     * @param set
     */
    void setDbGenerateKeys( boolean set );

    PessimisticLockingHandler getPessimisticLockingHandler( ActivateOptions activateOptions , View view  );
}
