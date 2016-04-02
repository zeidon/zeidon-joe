/**
 *
 */
package com.quinsoft.zeidon.test;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.CompareEntityOptions;
import com.quinsoft.zeidon.CreateEntityFlags;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.config.ZeidonPreferences;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;

// Just for temporary testing...
//import com.jacob.com.*;
//import com.jacob.activeX.*;


/**
 * This tests code using Zencas but doesn't require the DB.
 *
 */
public class TestNoDbZencas
{
	Task         zencas;
	Task         zeidonSystem;
	View         mFASrc;
	View         subtaskView;
	ObjectEngine oe;
    private String oldOiSourceUrl;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
        oe = JavaObjectEngine.getInstance();
        zencas = oe.createTask( "ZENCAs" );
        ZeidonPreferences pref = oe.getZeidonPreferences( zencas.getApplication() );
        oldOiSourceUrl = pref.get( "ZENCAs", "oiSourceUrl", "" );
        pref.set( "ZENCAs", "oiSourceUrl", "jdbc:sqlite:target/test-classes/zencasa-domains.sqlite" );
		zeidonSystem = oe.getSystemTask();
	}
	
	@After
	public void after()
	{
        ZeidonPreferences pref = oe.getZeidonPreferences( zencas.getApplication() );
        pref.set( "ZENCAs", "oiSourceUrl", oldOiSourceUrl );
	}

	@Test
	public void testCompare() throws IOException
	{
	    zencas.log().setLevel( "TRACE" );
        View prof1 = zencas.deserializeOi()
                              .fromFile( "target/test-classes/testdata//ZENCAs/TestInclude3-mFAProfO.por" )
                              .setLodDef( "mFAProf" )
                              .activateFirst();
        View prof2 = zencas.deserializeOi()
                            .fromFile( "target/test-classes/testdata//ZENCAs/TestInclude3-mFAProfO.por" )
                            .setLodDef( "mFAProf" )
                            .activateFirst();

        Assert.assertTrue( "Entity Compare doesn't work",
                           prof1.cursor( "FinAidProfile" ).compareEntity( prof2.cursor( "FinAidProfile" ) ) );

        prof2.cursor( "FinAidProfile" ).getAttribute( "Note" ).setValue( "New note" );

        Assert.assertFalse( "Entity Compare doesn't work",
                            prof1.cursor( "FinAidProfile" ).compareEntity( prof2.cursor( "FinAidProfile" ) ) );

        CompareEntityOptions options = new CompareEntityOptions().setIgnoreNullAttributes( true )
                                                                 .setCommonAttributesOnly( true );

        EntityCursor stud1 = zencas.activateEmptyObjectInstance( "mStudenC" ).cursor( "Student" );
        stud1.createEntity();
        stud1.getAttribute( "ID" ).setValue( 10 );
        EntityCursor stud2 = zencas.activateEmptyObjectInstance( "lAdvisee" ).cursor( "Advisee" );
        stud2.createEntity( CreateEntityFlags.IGNORE_PERMISSIONS );
        stud2.getAttribute( "ID" ).setValue( 10 );
        Assert.assertTrue( "Entity Compare doesn't work", stud1.compareEntity( stud2, options ) );
        Assert.assertTrue( "Entity Compare doesn't work", stud2.compareEntity( stud1, options ) );

        // Try setting a value in stud1 that is hidden in stud2.
        stud1.getAttribute( "FinalGPA" ).setValue( 3.4 );
        Assert.assertFalse( "Entity Compare doesn't work", stud1.compareEntity( stud2 ) );
        Assert.assertTrue( "Entity Compare doesn't work", stud2.compareEntity( stud1, options ) );

        Assert.assertTrue( "Entity Compare doesn't work", stud1.compareEntity( stud2, options) );

        // Linked entities should always compare as true.
        stud2.includeSubobject( stud1 );
        Assert.assertTrue( "Entity Compare doesn't work", stud1.compareEntity( stud2 ) );
        Assert.assertTrue( "Entity Compare doesn't work", stud2.compareEntity( stud1 ) );
	}
}
