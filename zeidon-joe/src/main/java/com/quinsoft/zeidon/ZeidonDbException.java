/**
 *
 */
package com.quinsoft.zeidon;

import java.sql.SQLException;

/**
 * @author dgc
 *
 */
public class ZeidonDbException extends ZeidonException
{
    private static final long serialVersionUID = 1L;

    private final View view;

    public ZeidonDbException( View view, SQLException causedBy )
    {
        super( causedBy.getMessage() );
        this.view = view;
        this.initCause( causedBy );
    }

    public View getView()
    {
        return view;
    }
}
