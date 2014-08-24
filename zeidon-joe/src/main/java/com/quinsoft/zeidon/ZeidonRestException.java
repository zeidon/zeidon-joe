package com.quinsoft.zeidon;

/**
 * @author dgc
 *
 */
public class ZeidonRestException extends ZeidonException
{
    private static final long serialVersionUID = 1L;

    /**
     * @param format
     * @param strings
     */
    public ZeidonRestException( String format, Object... strings )
    {
        super( format, strings );
    }
}
