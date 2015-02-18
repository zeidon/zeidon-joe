package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon.ZeidonException

/**
 * Convenience class for asserting various things about a View.
 */
class AssertView( val view: View ) {
    /**
     * Throws an exception if the View does not have an instantiation associated with it.
     */
    def mustBeInstantiated = {
        if ( view.jview == null )
            throw new ZeidonException( "Calling an ObjectOperation with an uninstatiated view" )
        
        this
    }
    
    def lodName( lodName: String ) = {
        if ( view.lodName != lodName )
            throw new ZeidonException( "Unexpected LOD name for view.  Expected %s, found %s.",
                                       lodName, view.odName )
        
        this
    }
    
    def empty = {
        mustBeInstantiated
        if ( ! view.isEmpty )
            throw new ZeidonException( "View '%s' must be empty", view.odName )
        
        this
    }
    
    
    def notEmpty = {
        mustBeInstantiated
        if ( view.isEmpty )
            throw new ZeidonException( "View '%s' must not be empty", view.odName )
        
        this
    }
}