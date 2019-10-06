/**
 *
 */
package com.quinsoft.zeidon;

import java.sql.SQLException;

/**
 * Thrown when committing a new root entity triggers a duplicate key error.
 *
 */
public class DuplicateRootException extends ZeidonDbException
{

    private static final long serialVersionUID = 1L;

    /**
     * @param view
     * @param causedBy
     */
    public DuplicateRootException( View view, SQLException causedBy )
    {
        super( view, causedBy );
    }

}
