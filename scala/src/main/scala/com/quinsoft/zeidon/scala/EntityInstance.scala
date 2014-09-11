/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import com.quinsoft.zeidon._
import com.quinsoft.zeidon.objectdefinition._

/**
 * @author dgc
 *
 */
class EntityInstance( private[this] val jentityInstance: com.quinsoft.zeidon.EntityInstance)
            extends AbstractEntity( jentityInstance.getEntityDef() )
{    
    def getEntityInstance: com.quinsoft.zeidon.EntityInstance = jentityInstance
    def delete: CursorResult = jentityInstance.deleteEntity()
    def drop: CursorResult = jentityInstance.dropEntity()
}