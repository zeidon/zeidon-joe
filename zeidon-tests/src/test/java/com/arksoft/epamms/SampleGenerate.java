package com.arksoft.epamms;

import com.quinsoft.zeidon.*;
import com.quinsoft.zeidon.vml.*;
import com.quinsoft.zeidon.zeidonoperations.*;

/**
 * Sample generator file for Doug.
 * @author DG
 *
 */
public class SampleGenerate extends VmlOperation
{
    private final KZOEP1AA kzoep1aa;
    private final TZLODOPR tzlodopr;
    private final ZDRVROPR zdrvropr;
    
    public SampleGenerate(TaskQualification taskQual)
    {
        super( taskQual );
        kzoep1aa = new KZOEP1AA( taskQual );
        tzlodopr = new TZLODOPR( taskQual );
        zdrvropr = new ZDRVROPR( taskQual );
    }

    public int SomeGeneratedOperation( View ViewToWindow )
    {
        zdrvropr.ConvertXMLToPDF( "xml", "xsl", "pdf" );
        zdrvropr.SetWindowActionBehavior( ViewToWindow, 0, "abc", "xyz" );
        return 0;
    }
}
