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

/**
 * @author dgc
 *
 */
class AttributeIterator( val jentityInstance: com.quinsoft.zeidon.EntityInstance, val excludeHidden: Boolean ) extends Iterable[AttributeInstance] {

    def iterator = {
        val entityInstance = new EntityInstance( jentityInstance )
        new Iterator[AttributeInstance] {
            var jattributeDef = entityInstance.getEntityInstance.getEntityDef().getAttributes( excludeHidden ).get(0)

            def hasNext = jattributeDef != null
            def next = {
                val nextAttr = jattributeDef
                jattributeDef = jattributeDef.getNextAttributeDef()
                entityInstance.getAttribute(nextAttr)
            }
        }
    }

    def each(f: AttributeInstance => Unit) = foreach(f)
}