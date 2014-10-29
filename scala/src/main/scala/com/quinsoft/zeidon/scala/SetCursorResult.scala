/**
 *
 */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon.CursorResult
import com.quinsoft.zeidon.objectdefinition.EntityDef

/**
 * @author dgc
 *
 */
class SetCursorResult( var jcursorResult: com.quinsoft.zeidon.CursorResult,
                       val cursor: EntityCursor ) {

    private var predicate: () => Boolean = {() => true}

    def isSet = jcursorResult.isSet()
    def isSetNewParent = jcursorResult.isSetNewParent()
    def isSetRecursiveChild = jcursorResult.isSetRecursiveChild()

    def WHERE( func: => Boolean ): SetCursorResult = {
        while ( isSet && !func )
            jcursorResult = cursor.jentityCursor.setNextContinue

        predicate = () => { func }
        this
    }

    def WHERE( func: ( EntityInstance ) => Boolean ): SetCursorResult = {
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
    def SETNEXT: SetCursorResult = {
        jcursorResult = cursor.jentityCursor.setNextContinue
        while ( isSet && !predicate() )
            jcursorResult = cursor.jentityCursor.setNextContinue

        this
    }

    def SETNEXT( func: => Boolean ): SetCursorResult = {
        jcursorResult = cursor.jentityCursor.setNextContinue
        while ( isSet && !func )
            jcursorResult = cursor.jentityCursor.setNextContinue

        predicate = () => { func }
        this
    }
}

object SetCursorResult {
    implicit def result2Boolean( result: SetCursorResult ): Boolean = result.isSet
    implicit def result2Int( result: SetCursorResult ): Int = result.jcursorResult.toInt()
}