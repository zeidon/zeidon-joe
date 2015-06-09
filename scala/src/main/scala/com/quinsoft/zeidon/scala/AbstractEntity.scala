/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import scala.collection.Iterable
import com.quinsoft.zeidon._
import com.quinsoft.zeidon.objectdefinition._

/**
 * This class implements methods that are common to both EntityCursor and EntityInstance.  This
 * is where the dynamic methods are implemented for finding cursor/entity and attribute instances.
 */
abstract class AbstractEntity( val jentityDef: com.quinsoft.zeidon.objectdefinition.EntityDef )
                        extends Dynamic {

    /**
     * Subclasses implement this method to get the Java EntityInstance.
     */
    def getEntityInstance: com.quinsoft.zeidon.EntityInstance
    
    /**
     * Returns the name of this entity instance from the LOD.
     */
    def name = jentityDef.getName()
    
    /**
     * Returns the position of this entity amongst its twins (i.e. its index).  First twin = 0;
     */
    def position = getEntityInstance.getPosition()
    
    /**
     * Returns the max cardinality of this entity. 
     */
    def maxCardinality = jentityDef.getMaxCardinality()
    
    /**
     * Write the instance data to the log.
     */
    def logEntity( displayChildren: Boolean = false ) = getEntityInstance.logEntity( displayChildren )
    
    /**
     * Returns the EntityDef for this instance.  Used for reflection.
     */
    def entityDef = jentityDef
    
    /**
     * Returns true if this entity has been updated since it was loaded from the DB.
     */
    def isUpdated = getEntityInstance.isUpdated()

    /**
     * Returns true if this entity has been included since the OI was loaded from the DB.
     */
    def isIncluded = getEntityInstance.isIncluded()
    
    /**
     * Returns true if this entity has been created since the OI was loaded from the DB.
     */
    def isCreated = getEntityInstance.isCreated()

    /**
     * Returns the parent EntityInstance.  If 'this' is a root then returns null.
     */
    def parent = {
        val parent = getEntityInstance.getParent()
        if ( parent == null ) null else new EntityInstance( parent )
    }

    /**
     * Called dynamically to convert an attribute name into a Scala AttributeInstance.
     * 
     * This allows the dynamic use of an attribute name as a method.  E.g.
     * {{{
     * val attr = myView.MyEntity.MyAttribute 
     * }}}
     */
    def selectDynamic( attributeName: String): AttributeInstance = {
        val jattributeDef = jentityDef.getAttribute( attributeName )
        new AttributeInstance( getEntityInstance.getAttribute( jattributeDef ) )
    }

    /**
     * Called dynamically to set an attribute value.
     * 
     * This allows the dynamic use of an attribute name as a method.  E.g.
     * {{{
     * myView.MyEntity.MyAttribute = "New Value"
     * }}}
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

    /**
     * Retrieves an AttributeInstance by name.  Normally used as part of reflection.
     */
    def getAttribute( attribName: String ): AttributeInstance = {
        val jattributeDef = jentityDef.getAttribute( attribName )
        getAttribute(jattributeDef)
    }

    protected[scala] def getAttribute( jattributeDef: AttributeDef ): AttributeInstance = {
        new AttributeInstance( getEntityInstance.getAttribute( jattributeDef ) )
    }

    /**
     * Returns an iterator that will iterate through all the attributes of this entity instance.
     */
    def attributes = new AttributeIterator( getEntityInstance )

    /**
     * Copies attributes by name.  Normal invocation is:
     * {{{
     * tgtView.Entity.copyAttributes.from( srcView.Entity )
     * }}}
     */
    def copyAttributes() : CopyAttributesBuilder = {
        return getEntityInstance.copyAttributes()
    }

    /**
     * Returns an iterator that will iterate through all the child entities of 'this'
     * in hierarchical order.  Does not include 'this'.
     */
    def childrenHier = new EntityInstanceIterator( getEntityInstance.getChildrenHier(false) )

    /**
     * Returns an iterator that will iterate through the direct children of the current entity.
     */
    def directChildren = new EntityInstanceIterator( getEntityInstance.getDirectChildren() )

    /**
     * Called dynamically to convert an attribute name with context value into a
     * Scala AttributeInstance.
    def applyDynamic( attributeName: String)(args: Any*): AttributeInstance = {
//        println( s"method '$attributeName' called with arguments ${args.mkString("'", "', '", "'")}" )
        val attr = getAttribute(attributeName)
        attr.contextName = args(0).toString
        return attr
    }
     */

    /**
     * Returns a string that identifies this entity instance.
     */
    override def toString: String = getEntityInstance.toString

    /**
     * Returns true if the underlying entity instance of 'other' matches this.
     */
    override def equals(other: Any) = other match {
        case that: EntityInstance => that.getEntityInstance == this.getEntityInstance
        case that: com.quinsoft.zeidon.EntityInstance => that == this.getEntityInstance
        case _ => false
    }

    override def hashCode = getEntityInstance.hashCode()
}

object AbstractEntity {
    /**
     * Automatically converts a Scala EntityInstance to a Java EntityInstance
     */
    implicit def ei2jei( ei: AbstractEntity ) = ei.getEntityInstance

}