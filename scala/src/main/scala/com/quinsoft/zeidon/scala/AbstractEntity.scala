/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import scala.collection.Iterable
import com.quinsoft.zeidon._
import com.quinsoft.zeidon.objectdefinition._

/**
 * @author dgc
 *
 */
abstract class AbstractEntity( val jviewEntity: com.quinsoft.zeidon.objectdefinition.ViewEntity )
                        extends Dynamic {

    def getEntityInstance: com.quinsoft.zeidon.EntityInstance

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
        getEntityInstance.getAttribute( jviewAttribute ).setValue( newValue )
        return value
    }

    /**
     * Called dynamically to convert an attribute name with context value into a
     * Scala AttributeInstance.
     */
    def applyDynamic( attributeName: String)(args: Any*): AttributeInstance = {
        println( s"method '$attributeName' called with arguments ${args.mkString("'", "', '", "'")}" )
        val jviewAttribute = jviewEntity.getAttribute( attributeName )
        val attr = new AttributeInstance( getEntityInstance.getAttribute( jviewAttribute ) )
        attr.contextName = args(0).toString
        return attr
    }

    override def toString: String = getEntityInstance.toString
}