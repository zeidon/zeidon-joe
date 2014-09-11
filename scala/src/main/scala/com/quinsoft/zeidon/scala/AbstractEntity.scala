/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import scala.collection.Iterable
import com.quinsoft.zeidon._
import com.quinsoft.zeidon.objectdefinition._

/**
 * This class implements methods that are common to both EntityCursor and EntityInstance.
 *
 * @author dgc
 *
 */
abstract class AbstractEntity( val jentityDef: com.quinsoft.zeidon.objectdefinition.EntityDef )
                        extends Dynamic {

    /**
     * The subclasses implement this method to get the Java EntityInstance.
     */
    def getEntityInstance: com.quinsoft.zeidon.EntityInstance

    def logEntity = getEntityInstance.logEntity()
    def logEntity( displayChildren: Boolean ) = getEntityInstance.logEntity( displayChildren )

    /**
     * Called dynamically to convert an attribute name into a Scala AttributeInstance.
     */
    def selectDynamic( attributeName: String): AttributeInstance = {
        val jattributeDef = jentityDef.getAttribute( attributeName )
        new AttributeInstance( getEntityInstance.getAttribute( jattributeDef ) )
    }

    /**
     * Called dynamically to set an attribute value.
     */
    def updateDynamic( attributeName: String)(value: Any): Any = {
        var newValue = value
        if ( value.isInstanceOf[ AttributeInstance ] )
            newValue = value.asInstanceOf[ AttributeInstance ].jattributeInstance.getValue

        val jattributeDef = jentityDef.getAttribute( attributeName )
        return setValue( jattributeDef, newValue )
    }

    protected[scala] def setValue( jattributeDef: AttributeDef, value: Any ): Any = {
        getEntityInstance.getAttribute( jattributeDef ).setValue( value )
        return value
    }

    def getAttribute( attribName: String ): AttributeInstance = {
        val jattributeDef = jentityDef.getAttribute( attribName )
        getAttribute(jattributeDef)
    }

    protected[scala] def getAttribute( jattributeDef: AttributeDef ): AttributeInstance = {
        new AttributeInstance( getEntityInstance.getAttribute( jattributeDef ) )
    }

    def attributes = new AttributeIterator( getEntityInstance )

    /**
     * Copies attributes by name.  Normal invocation is:
     *      tgtView.Entity copyAttributes from srcView.Entity
     */
    def copyAttributes() : SetMatchingFlagsBuilder = {
        return getEntityInstance.setMatchingAttributesByName()
    }

    /**
     * Called dynamically to convert an attribute name with context value into a
     * Scala AttributeInstance.
     */
    def applyDynamic( attributeName: String)(args: Any*): AttributeInstance = {
//        println( s"method '$attributeName' called with arguments ${args.mkString("'", "', '", "'")}" )
        val attr = getAttribute(attributeName)
        attr.contextName = args(0).toString
        return attr
    }

    override def toString: String = getEntityInstance.toString
}

object AbstractEntity {
    implicit def ei2jei( ei: AbstractEntity ) = ei.getEntityInstance

}