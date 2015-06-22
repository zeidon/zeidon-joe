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
 * Copyright 2009-2014 QuinSoft
 */
package com.quinsoft.zeidon.scala

import scala.language.dynamics
import com.quinsoft.zeidon.SelectSet
import com.quinsoft.zeidon.objectdefinition.EntityDef
import com.quinsoft.zeidon.objectdefinition.AttributeDef
import com.quinsoft.zeidon.ZeidonException

/**
 * Specifies a series of objects that make it easier to select entities in a
 * SelectSet.
 *
 */
class SelectQualification  private[scala] ( val selectSet: SelectSet,
                                            val select: Boolean = true,
                                            val selectSubobject: Boolean = false ) extends Dynamic {

    val jview = selectSet.getView()
    val lodDef = jview.getLodDef()

    def selectDynamic( entityName: String ) = {
        val jentityDef = lodDef.getEntityDef( entityName )
        new SelectEntityQual( jentityDef )
    }

    class SelectEntityQual private[scala] ( val jentityDef: EntityDef ) extends Dynamic {
        val jcursor = jview.cursor( jentityDef )
        var scopingEntity: EntityDef = null

        def under( scopingEntityName: String ) = {
            scopingEntity = lodDef.getEntityDef( scopingEntityName )
            this
        }

        def under( entitySelector: (EntitySelector) => EntityDef ) = {
            scopingEntity = entitySelector( new EntitySelector( lodDef ) )
            this
        }

        def selectDynamic( attribName: String ) = {
            val jattribDef = jentityDef.getAttribute( attribName )
            new SelectAttribQual( jattribDef )
        }

        /**
         * Add support for using "=" as a qualifier.
         *
         * {{{
         *    set.selectWhere( _.User.ID = 490 )
         * }}}
         */
        def updateDynamic( attribName: String)( other: Any ): SelectQualTerminator = {
            val jattribDef = jentityDef.getAttribute( attribName )
            val attrib = new SelectAttribQual( jattribDef )
            attrib.each { _.==( other ) }
        }

        class SelectAttribQual private[scala] ( val jattribDef: AttributeDef ) {

            override def equals(value: Any) = {
                throw new ZeidonException("Operators '==' and '!=' are invalid when selecting entities.  Use '=' and '<>' instead" )
            }

            private[scala] def each( predicate: (AttributeInstance) => Boolean ) = {
                val iter = new EntityInstanceIterator( jcursor.eachEntity( scopingEntity ) )
                iter.each {
                    val jattrib = jcursor.getAttribute( jattribDef )
                    val attrib = new AttributeInstance( jattrib )
                    val b = predicate( attrib )
                    if ( b ) {
                        if ( select )
                            selectSet.select( jcursor, selectSubobject )
                        else
                            selectSet.deselect( jcursor, selectSubobject )

                        println( jattrib + " is selected" )
                    }
                    else {
                        println( jattrib + " is NOT selected" )
                    }
                }

                SelectQualification.TERMINATOR
            }

            def >( other: Any ): SelectQualTerminator = each { _.>( other ) }
            def <( other: Any ): SelectQualTerminator = each { _.<( other ) }
            def <>( other: Any ): SelectQualTerminator = each { _.!=( other ) }
        }
    }
}

/**
 * This class is used to terminate qualification for a SelectSet.
 */
class SelectQualTerminator private [scala] {

}

object SelectQualification {
    val TERMINATOR = new SelectQualTerminator
}