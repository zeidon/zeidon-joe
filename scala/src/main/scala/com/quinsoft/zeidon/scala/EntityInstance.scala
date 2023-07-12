/**
  * This file is part of the Zeidon Java Object Engine (Zeidon JOE).
  *
  * Zeidon JOE is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Lesser General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * Zeidon JOE is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public License
  * along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.
  *
  * Copyright 2009-2015 QuinSoft
  */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import com.quinsoft.zeidon.objectdefinition._

/**
 * An instance of an Entity in an OI.
 *
 */
class EntityInstance( private[this] val jentityInstance: com.quinsoft.zeidon.EntityInstance)
            extends AbstractEntity( jentityInstance.getEntityDef() )
{
    /**
     * Returns the underlying Java EntityInstance.
     */
    def getEntityInstance: com.quinsoft.zeidon.EntityInstance = jentityInstance

    /**
     * Deletes this entity instance.  If the OI is committed then the record will
     * be deleted from the DB.
     */
    def delete: CursorResult = jentityInstance.deleteEntity()

    /**
     * Completely removes this entity instance from the OI.  If the OI is committed
     * this record WILL NOT be deleted from the DB.
     */
    def drop: CursorResult = jentityInstance.dropEntity()
}

object EntityInstance {

    implicit def jei2ei( jei: com.quinsoft.zeidon.EntityInstance ) = new EntityInstance( jei )

}
