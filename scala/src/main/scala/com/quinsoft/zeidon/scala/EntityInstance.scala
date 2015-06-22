/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import com.quinsoft.zeidon._
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