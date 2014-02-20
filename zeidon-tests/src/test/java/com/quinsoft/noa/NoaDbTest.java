package com.quinsoft.noa;


import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;

public class NoaDbTest
{
    ObjectEngine oe;
    Task         noa;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
        oe = JavaObjectEngine.getInstance();
        noa = oe.createTask( "NOA" );
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testActivate()
    {
        View account;
        if ( oe != null )
            return;
        
      account = noa.activateObjectInstance( "maccount", null, ActivateFlags.MULTIPLE );
//      account = noa.activateObjectInstance( "maccount", null, Activate.SINGLE );
//      account = noa.activateObjectInstance( "maccount", null, Activate.ROOT_ONLY_MULTIPLE);
      
      View qual;
      qual = noa.activateEmptyObjectInstance( "kzdbhqua", noa.getSystemTask().getApplication() );
      qual.cursor( "EntitySpec" ).createEntity().setAttribute( "EntityName" , "Account" );
      qual.cursor( "QualAttrib" ).createEntity()
                                 .setAttribute( "EntityName" , "Account" )
                                 .setAttribute( "AttributeName" , "AccountID" )
                                 .setAttribute( "Oper" , "<" )
                                 .setAttribute( "Value" ,  50  );
//      account = noa.activateObjectInstance( "maccount", qual, Activate.MULTIPLE );

      View qual2 = qual.activateOiFromOi( ActivateFlags.fMULTIPLE );
      qual2.cursor( "QualAttrib" ).setAttribute( "Oper" , "<>" )
                                  .setAttribute( "Value", null );
//      account = noa.activateObjectInstance( "maccount", qual2, Activate.MULTIPLE );

      qual.cursor( "QualAttrib" ).createEntity().setAttribute( "Oper" , "AND" );
      qual.cursor( "QualAttrib" ).createEntity()
                                  .setAttribute( "EntityName" , "Account" )
                                  .setAttribute( "AttributeName" , "AccountType" )
                                  .setAttribute( "Oper" , "=" )
                                  .setAttribute( "Value" , "A" );
      qual.cursor( "QualAttrib" ).createEntity().setAttribute( "Oper" , "AND" );
      qual.cursor( "QualAttrib" ).createEntity().setAttribute( "Oper" , "(" );
      qual.cursor( "QualAttrib" ).createEntity()
                                 .setAttribute( "EntityName" , "BankAccount" )
                                 .setAttribute( "AttributeName" , "AccountCode" )
                                 .setAttribute( "Oper" , "=" )
                                 .setAttribute( "Value" , "1014" );
      qual.cursor( "QualAttrib" ).createEntity().setAttribute( "Oper" , "OR" );
      qual.cursor( "QualAttrib" ).createEntity()
                                  .setAttribute( "EntityName" , "BankAccount" )
                                  .setAttribute( "AttributeName" , "AccountCode" )
                                  .setAttribute( "Oper" , "=" )
                                  .setAttribute( "Value" , "1015" );
      qual.cursor( "QualAttrib" ).createEntity().setAttribute( "Oper" , ")" );
      account = noa.activateObjectInstance( "maccount", qual, ActivateFlags.MULTIPLE );

      account.logObjectInstance();

    }
}
