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
import scala.collection.Iterable
import scala.collection.mutable.ListMap
import com.quinsoft.zeidon.CopyAttributesBuilder
import com.quinsoft.zeidon.CompareEntityOptions
import com.quinsoft.zeidon.objectdefinition._
import com.quinsoft.zeidon.HiddenAttributeException
import com.quinsoft.zeidon.ZeidonException

/**
 * This class implements methods that are common to both EntityCursor and EntityInstance.  This
 * is where the dynamic methods are implemented for finding cursor/entity and attribute instances.
 */
abstract class AbstractEntity( val jentityDef: EntityDef ) extends Dynamic {

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

    def attributeMap: ListMap[AttributeInstance, AnyRef] = {
        val map = ListMap[AttributeInstance, AnyRef]()
        this.attributes.each( a => map.put(a, a.value ) )
        map
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
        val jattributeDef = getAttributeDef( attributeName )
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
    def updateDynamic( attributeName: String)(value: Any): AbstractEntity = {
        var newValue = value
        if ( value.isInstanceOf[ AttributeInstance ] )
            newValue = value.asInstanceOf[ AttributeInstance ].jattributeInstance.getValue

        val jattributeDef = getAttributeDef( attributeName )
        return setValue( jattributeDef, newValue )
    }

    protected[scala] def setValue( jattributeDef: AttributeDef, value: Any ): AbstractEntity = {
        getEntityInstance.getAttribute( jattributeDef ).setValue( value )
        return this
    }

    private def getAttributeDef( attribName: String ): AttributeDef = {
        AbstractEntity.getAttributeDef( jentityDef, attribName )
    }

    /**
     * Retrieves an AttributeInstance by name.  Normally used as part of reflection.
     */
    def getAttribute( attribName: String ): AttributeInstance = {
        val jattributeDef = getAttributeDef( attribName )
        getAttribute(jattributeDef)
    }

    def getAttribute( jattributeDef: AttributeDef ): AttributeInstance = {
        new AttributeInstance( getEntityInstance.getAttribute( jattributeDef ) )
    }

    /**
     * Returns an iterator that will iterate through all the non-hdden attributes of this entity instance.
     */
    def attributes = new AttributeIterator( getEntityInstance, true )

    /**
     * Returns an iterator that will iterate through all the attributes of this entity instance,
     * possibly including hidden attributes.
     */
    def attributes( includeHidden: Boolean ) = new AttributeIterator( getEntityInstance, ! includeHidden )

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
     * Copies attributes by name.  Normal invocation is:
     * {{{
     * tgtView.Entity.copyAttributes( srcView.Entity )
     * }}}
     *
     * Use the CopyAttributesBuilder if you need to specify what/how attributes are copied:
     * {{{
     * tgtView.Entity.copyAttributes.from( srcView.Entity )
     * }}}
     */
    def copyAttributes( entity: AbstractEntity ): Int = {
        return getEntityInstance.copyAttributes().from( entity.getEntityInstance )
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

    /**
     * Returns true if all the attributes in this entity match the values in otherEntity.
     *
     * For additional options on how to compare see compareEntity
     */
    def === ( otherEntity : AbstractEntity ): Boolean = compareEntity( otherEntity )

    /**
     * Returns true if all the attributes in this entity match the values in otherEntity.
     */
    def compareEntity( otherEntity : AbstractEntity,
                       options : CompareEntityOptions = CompareEntityOptions.DEFAULT_OPTIONS ): Boolean = {
        getEntityInstance.compareEntity( otherEntity.getEntityInstance, options )
    }

    override def hashCode = getEntityInstance.hashCode()
}

object AbstractEntity {
    /**
     * Automatically converts a Scala EntityInstance to a Java EntityInstance
     */
    implicit def ei2jei( ei: AbstractEntity ) = ei.getEntityInstance

    def getAttributeDef( jentityDef: EntityDef, attribName: String ): AttributeDef = {
         if ( attribName == "key" ) {
            if ( jentityDef.getKeys.size() != 1 )
                throw new ZeidonException( "Cannot use 'key' attribute name for an entity that does not have one (and only one) key" )
                          .prependEntityDef(jentityDef)

            return jentityDef.getKeys.get(0)
        }

        val jattributeDef = jentityDef.getAttribute( attribName )
        if ( jattributeDef.isHidden() )
            throw new HiddenAttributeException( jattributeDef );

        jattributeDef
    }
}
