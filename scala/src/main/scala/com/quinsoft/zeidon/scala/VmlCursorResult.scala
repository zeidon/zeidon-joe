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

import com.quinsoft.zeidon.objectdefinition.EntityDef

/**
 * This class is used to dupilcate VML-like syntax when manipulating
 * cursors.  It is returned by SETFIRST and SETNEXT.
 *
 * @author dgc
 *
 */
class VmlCursorResult( var jcursorResult: com.quinsoft.zeidon.CursorResult,
                       val cursor: EntityCursor ) {

    private var predicate: () => Boolean = {() => true}

    def isSet = jcursorResult.isSet()
    def isSetNewParent = jcursorResult.isSetNewParent()
    def isSetRecursiveChild = jcursorResult.isSetRecursiveChild()

    def WHERE( func: => Boolean ): VmlCursorResult = {
        while ( isSet && !func )
            jcursorResult = cursor.jentityCursor.setNextContinue

        predicate = () => { func }
        this
    }

    def WHERE( func: ( EntityInstance ) => Boolean ): VmlCursorResult = {
        while ( isSet && !func( new EntityInstance( cursor.getEntityInstance ) ) )
            jcursorResult = cursor.jentityCursor.setNextContinue

        predicate = () => { func( new EntityInstance( cursor.getEntityInstance ) ) }
        this
    }

    def UNDER( scopingEntity: AbstractEntity ) = {
        jcursorResult = cursor.jentityCursor.setFirst( scopingEntity.entityDef )
        this
    }
    /**
     * Set cursor to the next entity matching the predicate.
     */
    def SETNEXT: VmlCursorResult = {
        jcursorResult = cursor.jentityCursor.setNextContinue
        while ( isSet && !predicate() )
            jcursorResult = cursor.jentityCursor.setNextContinue

        this
    }

    def SETNEXT( func: => Boolean ): VmlCursorResult = {
        jcursorResult = cursor.jentityCursor.setNextContinue
        while ( isSet && !func )
            jcursorResult = cursor.jentityCursor.setNextContinue

        predicate = () => { func }
        this
    }
}

object VmlCursorResult {
    implicit def result2Boolean( result: VmlCursorResult ): Boolean = result.isSet
    implicit def result2Int( result: VmlCursorResult ): Int = result.jcursorResult.toInt()
}