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
abstract class AbstractEntity( val jviewEntity: com.quinsoft.zeidon.objectdefinition.ViewEntity )
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
        val jviewAttribute = jviewEntity.getAttribute( attributeName )
        new AttributeInstance( getEntityInstance.getAttribute( jviewAttribute ) )
    }

    /**
     * Called dynamically to set an attribute value.
     */
    def updateDynamic( attributeName: String)(value: Any): Any = {
        var newValue = value
        if ( value.isInstanceOf[ AttributeInstance ] )
            newValue = value.asInstanceOf[ AttributeInstance ].jattributeInstance.getValue

        val jviewAttribute = jviewEntity.getAttribute( attributeName )
        return setValue( jviewAttribute, newValue )
    }

    def setValue( jviewAttribute: ViewAttribute, value: Any ): Any = {
        getEntityInstance.getAttribute( jviewAttribute ).setValue( value )
        return value
    }

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
        val jviewAttribute = jviewEntity.getAttribute( attributeName )
        val attr = new AttributeInstance( getEntityInstance.getAttribute( jviewAttribute ) )
        attr.contextName = args(0).toString
        return attr
    }

    override def toString: String = getEntityInstance.toString
}

object AbstractEntity {
    implicit def ei2jei( ei: AbstractEntity ) = ei.getEntityInstance

}