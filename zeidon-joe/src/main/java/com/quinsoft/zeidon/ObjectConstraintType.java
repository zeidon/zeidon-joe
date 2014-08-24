package com.quinsoft.zeidon;

/**
 * @author dgc
 *
 */
public enum ObjectConstraintType
{
    ACTIVATE( 1 ),
    ACTIVATE_EMPTY( 2 ),
    COMMIT( 3 ),
    DROP_OI( 4 );

    private final int vmlValue;

    private ObjectConstraintType( int vmlValue )
    {
        this.vmlValue = vmlValue;
    }

    public int getVmlValue()
    {
        return vmlValue;
    }
}
