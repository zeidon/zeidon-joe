/**
 * 
 */
package com.quinsoft.zeidon;

import java.sql.SQLException;

/**
 * @author dgc
 *
 */
public class DuplicateRootException extends ZeidonDbException
{

    /**
     * @param view
     * @param causedBy
     */
    public DuplicateRootException( View view, SQLException causedBy )
    {
        super( view, causedBy );
        // TODO Auto-generated constructor stub
    }

}
