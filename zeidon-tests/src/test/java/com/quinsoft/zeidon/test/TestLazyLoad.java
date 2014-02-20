/**
 * 
 */
package com.quinsoft.zeidon.test;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.EntityCursor.CursorStatus;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.NullCursorException;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.QualificationBuilder;

/**
 * @author DG
 *
 */
public class TestLazyLoad
{
    Task         zencas;
    Task         zeidonSystem;
    ObjectEngine oe;

    @Before
    public void setUp() throws Exception
    {
        oe = JavaObjectEngine.getInstance();
        zencas = oe.createTask( "ZENCAs" );
        zeidonSystem = oe.getSystemTask();
    }
    @Test
    public void testLazyLoad()
    {
        View view = new QualificationBuilder( zencas )
                            .setViewOd( "LazyLoad" )
                            .addAttribQual( "ID", 1 )
                            .multipleRoots()
                            .activate();

        String lazyLoadEntity = "Registration";
        String lazyLoadChild  = "RegistrationCourse";
        
        view.logObjectInstance(); // Should not cause lazy load.
        CursorStatus status = view.cursor( lazyLoadEntity ).getStatus();
        Assert.assertEquals("'Category' status is not NOT_LOADED.", CursorStatus.NOT_LOADED, status );
        
        String id = view.cursor( lazyLoadEntity ).getStringFromAttribute( "ID" );
        status = view.cursor( lazyLoadEntity ).getStatus();
        Assert.assertEquals("'Category' status is not SET.", CursorStatus.SET, status );
        Assert.assertTrue( "ID is null after lazy load", ! StringUtils.isBlank( id ) );

        // Lets do it again and test cursor looping.
        view = new QualificationBuilder( zencas )
                            .setViewOd( "LazyLoad" )
                            .addAttribQual( "ID", 2 )
                            .multipleRoots()
                            .activate();
        int count = 0;
        for ( EntityInstance ei : view.cursor( lazyLoadEntity ).eachEntity() )
            count++;
        Assert.assertTrue( "# of lazy-loaded entities is wrong", count > 0 );
        view.logObjectInstance();

        // Lets do it again and test accessing a child of lazy-loaded object.
        view = new QualificationBuilder( zencas )
                            .setViewOd( "LazyLoad" )
                            .addAttribQual( "ID", 2 )
                            .multipleRoots()
                            .activate();
        
        status = view.cursor( lazyLoadChild ).getStatus();
        Assert.assertEquals( lazyLoadChild + " status is not NOT_LOADED.", CursorStatus.NOT_LOADED, status );
        id = view.cursor( lazyLoadChild ).getStringFromAttribute( "ID" );
        Assert.assertTrue( "ID is null after lazy load", ! StringUtils.isBlank( id ) );
        
        // Try again.  This time we'll turn off lazy loading.
        view = new QualificationBuilder( zencas )
                            .setViewOd( "LazyLoad" )
                            .addAttribQual( "ID", 1 )
                            .singleRoot()
                            .activate();
        
        view.setLazyLoad( false );
        status = view.cursor( lazyLoadEntity ).getStatus();
        Assert.assertEquals("'Category' status is not NOT_LOADED.", CursorStatus.NOT_LOADED, status );
        try
        {
            id = view.cursor( lazyLoadEntity ).getStringFromAttribute( "ID" );
            Assert.assertTrue( "Cursor was apparently set", false );
        }
        catch ( NullCursorException e )
        {
            // We expect this exception so just keep on trucking.
        }
//        view.logObjectInstance();
        status = view.cursor( lazyLoadEntity ).getStatus();
        Assert.assertEquals("'Category' status is not NOT_LOADED.", CursorStatus.NOT_LOADED, status );
        
    }
}
