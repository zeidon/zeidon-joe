/**
 * 
 */
package com.quinsoft.zeidon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author DG
 *
 */
public class SubobjectValidationException extends ZeidonException
{
    private static final long serialVersionUID = 1L;

    private final List<ZeidonException> exceptions = new ArrayList<ZeidonException>();
    
    /**
     * @param format
     * @param strings
     */
    public SubobjectValidationException( Collection<ZeidonException> list )
    {
        super( "Subobject Validation exception.  See child exceptions for more." );
        addChildExceptions( list );
    }

    public SubobjectValidationException addChildExceptions( Collection<ZeidonException> list )
    {
        for ( ZeidonException ze : list )
            addChildException( ze );

        return this;
    }
    
    public SubobjectValidationException addChildException( ZeidonException exception )
    {
        exceptions.add( exception );
        appendMessage( "%d) %s", exceptions.size(), exception.toString() );
        return this;
    }
    
    /**
     * @return the exceptions
     */
    public List<ZeidonException> getChildExceptions()
    {
        return exceptions;
    }
}
