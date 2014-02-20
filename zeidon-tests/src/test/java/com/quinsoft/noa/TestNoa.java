/**
 * 
 */
package com.quinsoft.noa;

import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.QualificationBuilder;

/**
 * @author DG
 *
 */
public class TestNoa
{
    private ObjectEngine oe;
    private Task noa;

    @Before
    public void setUp() throws Exception
    {
        oe = JavaObjectEngine.getInstance();
        noa = oe.createTask( "NOA" );
    }
    
    @Test
    public void test1()
    {
        QualificationBuilder qual = new QualificationBuilder( noa, "mUser" )
                                            .forEntity( "User" )
                                            .addAttribQual( "User", "UserName", "=", "jbbeam" )
                                            .addAttribQual( "AND" )
                                            .addAttribQual( "User", "InActive", "=", "" );
        View view = qual.activate();
        System.out.println("here");
    }

}
