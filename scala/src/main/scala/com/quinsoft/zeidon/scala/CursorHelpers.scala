package com.quinsoft.zeidon.scala

trait CursorHelpers {
    /**
     * For use inside of view.Entity.each{} and foreach loops, this will break execution of the
     * current entity cursor and continue with the next.
     */
    def next() = Nexts.next()

    /**
     * For use inside of view.Entity.each{} and FOREACH loops, this will break execution of the
     * current entity and stop looping.
     */
    def break() = util.control.Breaks.break()  
}