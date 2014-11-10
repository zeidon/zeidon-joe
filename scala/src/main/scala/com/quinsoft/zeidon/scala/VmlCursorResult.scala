/**
 *
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