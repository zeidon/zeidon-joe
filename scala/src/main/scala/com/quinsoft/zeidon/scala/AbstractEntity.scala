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
private[scala] abstract class AbstractEntity( val jentityDef: com.quinsoft.zeidon.objectdefinition.EntityDef )
                        extends Dynamic {

    /**
     * The subclasses implement this method to get the Java EntityInstance.
     */
    def getEntityInstance: com.quinsoft.zeidon.EntityInstance
    def name = jentityDef.getName()
    def position = getEntityInstance.getPosition()
    def maxCardinality = jentityDef.getMaxCardinality()
    def logEntity( displayChildren: Boolean = false ) = getEntityInstance.logEntity( displayChildren )
    def entityDef = jentityDef
    def isUpdated = getEntityInstance.isUpdated()
    def isIncluded = getEntityInstance.isIncluded()
    def isCreated = getEntityInstance.isCreated()

    def parent = {
        val parent = getEntityInstance.getParent()
        if ( parent == null ) null else new EntityInstance( parent )
    }

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
     *      tgtView.Entity.copyAttributes.from( srcView.Entity )
     */
    def copyAttributes() : SetMatchingFlagsBuilder = {
        return getEntityInstance.setMatchingAttributesByName()
    }

    def childrenHier = new EntityInstanceIterator( getEntityInstance.getChildrenHier(false) )

    /**
     * Returns an iterator that will iterate through the direct children of the current entity.
     */
    def directChildren = new EntityInstanceIterator( getEntityInstance.getDirectChildren() )

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

    override def equals(o: Any) = o match {
        case that: EntityInstance => that.getEntityInstance == this.getEntityInstance
        case that: com.quinsoft.zeidon.EntityInstance => that == this.getEntityInstance
        case _ => false
    }

    override def hashCode = getEntityInstance.hashCode()
}

object AbstractEntity {
    implicit def ei2jei( ei: AbstractEntity ) = ei.getEntityInstance

}