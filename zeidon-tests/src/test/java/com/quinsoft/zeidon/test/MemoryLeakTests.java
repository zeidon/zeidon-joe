/**
    This file is part of the Zeidon Java Object Engine (Zeidon JOE).

    Zeidon JOE is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zeidon JOE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2009-2014 QuinSoft
 */

package com.quinsoft.zeidon.test;

import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.vml.VmlOperation;

/**
 * Run different tests to check for memory leaks.
 * 
 * @author DG
 *
 */
public class MemoryLeakTests
{
    Task         zencas;
    Task         zeidonSystem;
    View         mFASrc;
    ObjectEngine oe;
    volatile boolean done;

    static final String BIG_STRING = " : XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
            		                 "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    
    int bigStringCount = 0;
    
    private String newBigString()
    {
        return bigStringCount++ + BIG_STRING + bigStringCount;
    }
    
    public MemoryLeakTests()
    {
        oe = JavaObjectEngine.getInstance();
        zencas = oe.createTask( "ZENCAs" );
        zeidonSystem = oe.getSystemTask();
    }

    private View createTestOI()
    {
        mFASrc = zencas.activateEmptyObjectInstance( "mFASrc" );
        mFASrc.cursor( "FinAidSource" ).createEntity().setAttribute( "SourceFootnote", newBigString() );
        for ( int i = 0; i < 2; i++ )
        {
            mFASrc.cursor( "Scholarship" ).createEntity();
            mFASrc.cursor( "Scholarship" ).setAttribute( "ID" ,  i  );
            mFASrc.cursor( "Scholarship" ).setAttribute( "Name" ,  "ScholarshipName" + i  );
            mFASrc.cursor( "Scholarship" ).setAttribute( "Description" , newBigString() );
            for ( int j = 1; j < 3; j++ )
            {
                mFASrc.cursor( "Fund" ).createEntity().setAttribute( "Name" ,  "Fund" + Integer.toString( j  ) )
                                                      .setAttribute( "ID" ,  (100 * j) + i  )
                                                      .setAttribute( "Description", newBigString() );
            }
        }
        
        mFASrc.setName( BIG_STRING );
        return mFASrc;
    }

    private class SetDone implements Runnable
    {
        @Override
        public void run()
        {
            VmlOperation.SysMessageBox( null, "Running test", "Click OK to end test", 0 );
            done = true;
        }
    }
    
    void runTemporalTests( boolean accept ) throws InterruptedException
    {
        done = false;
        createTestOI();
        
        SetDone setDone = new SetDone();
        Thread t = new Thread( setDone );
        t.start();

        int count = 0;
        System.gc();
        int counter = 0;
        EntityCursor schol = mFASrc.cursor( "Scholarship" );
        EntityCursor fund  = mFASrc.cursor( "Fund" );
        while ( !done )
        {
            if ( ++counter % 1000 == 0 )
            {
                System.out.println( "pausing..." );
                Thread.sleep( 1000 );
            }
            
            schol.createTemporalSubobjectVersion();
            schol.setAttribute( "Description" , newBigString() );
            fund.setAttribute( "Description" , newBigString() );
            if ( accept )
                schol.acceptSubobject();
            else
                schol.cancelSubobject();
            
            if ( count++ % 1000 == 0 )
                System.out.println( "Entity count = " + ((JavaObjectEngine) oe).countAllEntities( mFASrc ) );
        }
        
        EntityInstance root = schol.getEntityInstance();
        mFASrc.reset();
        Thread.sleep( 1000 );
        System.gc();
        System.out.println( "Root = " + root.getEntityKey() );
        VmlOperation.SysMessageBox( "Running test", "Test is finished." );
        root = null;
        System.gc();
        Thread.sleep( 1000 );
        System.gc();
        VmlOperation.SysMessageBox( "Running test", "Test is finished." );
    }
    
    void runViewTests() throws InterruptedException
    {
        done = false;
        createTestOI();
        
        SetDone setDone = new SetDone();
        Thread t = new Thread( setDone );
        t.start();

        System.gc();
        int counter = 0;
        while ( !done )
        {
            if ( ++counter % 100 == 0 )
            {
                System.out.println( "Number of views = " + zencas.getViewList().size() );
                Thread.sleep( 1000 );
            }
            
            createTestOI();
        }

        Thread.sleep( 1000 );
        System.gc();
        VmlOperation.SysMessageBox( "Running test", "Test is finished." );
    }
    
    
    void runTaskTests() throws InterruptedException
    {
        done = false;
        createTestOI();
        
        SetDone setDone = new SetDone();
        Thread t = new Thread( setDone );
        t.start();

        System.gc();
        int counter = 0;
        while ( !done )
        {
            if ( ++counter % 1000 == 0 )
            {
                Thread.sleep( 10 );
                System.gc();
                System.out.println( "Number of tasks = " + oe.getTaskList().size() );
                System.out.println( "Number of system views = " + oe.getSystemTask().getViewList().size() );
                Thread.sleep( 1000 );
            }
            
            zencas.dropTask();
            zencas = oe.createTask( "ZENCAs" );
            createTestOI();
        }

        Thread.sleep( 1000 );
        System.gc();
        VmlOperation.SysMessageBox( "Running test", "Test is finished." );
    }
    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException
    {
        VmlOperation.SysMessageBox( "Waiting for user input", "Start JMX console" );
        MemoryLeakTests tester = new MemoryLeakTests();
        tester.runTaskTests();
    }

}
