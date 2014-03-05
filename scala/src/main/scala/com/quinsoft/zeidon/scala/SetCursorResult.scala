/**
 *
 */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon.CursorResult

/**
 * @author dgc
 *
 */
class SetCursorResult( var jcursorResult: com.quinsoft.zeidon.CursorResult,
                       val cursor: EntityCursor ) {

    var predicate: () => Boolean = {() => true}


    def isSet = jcursorResult == CursorResult.SET ||
                 jcursorResult == CursorResult.SET_NEWPARENT ||
                 jcursorResult == CursorResult.SET_RECURSIVE_CHILD

    def WHERE( func: => Boolean ): SetCursorResult = {
      while ( isSet && ! func )
        jcursorResult = cursor.jentityCursor.setNextContinue

      predicate = () => { func }
      this
    }

    /**
     * Set cursor to the next entity matching the predicate.
     */
    def SETNEXT: SetCursorResult = {
      jcursorResult = cursor.jentityCursor.setNextContinue
      while ( isSet && ! predicate() )
        jcursorResult = cursor.jentityCursor.setNextContinue

      this
    }

    def SETNEXT( func: => Boolean ): SetCursorResult = {
      jcursorResult = cursor.jentityCursor.setNextContinue
      while ( isSet && ! func )
        jcursorResult = cursor.jentityCursor.setNextContinue

      predicate = () => { func }
      this
    }
}

object SetCursorResult {
  implicit def result2Boolean( result: SetCursorResult ): Boolean = result.isSet
}