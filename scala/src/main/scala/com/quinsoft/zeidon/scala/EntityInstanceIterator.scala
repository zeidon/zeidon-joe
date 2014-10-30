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

import com.quinsoft.zeidon.EntityIterator
import util.control.Breaks._

/**
 * Scala iterator wrapper around Java entity iterator.
 *
 * @author dgc
 *
 */
class EntityInstanceIterator( val jiterator: EntityIterator[_]) extends Iterable[EntityInstance] {

    private var entityCursor: EntityCursor = null

    private[scala] def setCursor( cursor: EntityCursor ) = { entityCursor = cursor; this }

    def iterator = {
        new Iterator[EntityInstance] {
            def hasNext = jiterator.hasNext()
            def next = {
                val ei = new EntityInstance( jiterator.next().asInstanceOf[com.quinsoft.zeidon.EntityInstance] )
                if ( entityCursor != null )
                    entityCursor.jentityCursor.setCursor( ei )
                ei
            }

            /**
             * Override foreach so we can wrap the call to function f with breakable.
             */
            override def foreach[U](f: EntityInstance => U) { while (hasNext) breakable{ f(next()) } }
        }
    }

    def each( looper: => Any ) = {
        var any: Any = null
        val iter = iterator
        while ( iter.hasNext )
        {
            val ei = iter.next()
            breakable {
                any = looper
            }
        }

        any
    }
}