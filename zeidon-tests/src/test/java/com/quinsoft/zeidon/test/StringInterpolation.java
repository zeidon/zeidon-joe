package com.quinsoft.zeidon.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.QualificationBuilder;

public class StringInterpolation
{

    private ObjectEngine oe;
    private Task northwind;

    @Before
    public void setUp() throws Exception
    {
        System.out.println( "CWD = " + System.getProperty("user.dir") );
        oe = JavaObjectEngine.getInstance();
        northwind = oe.createTask( "Northwind" );
    }

    @Test
    public void test()
    {
        // Load an Order.
        QualificationBuilder qb = new QualificationBuilder( northwind );
        qb.setLodDef( "Order" );
        qb.addAttribQual( "Order", "OrderId", "=", 10248 );
        View order = qb.activate();

        Map<String,Object> variables = new HashMap<>();
        variables.put( "int", 10 );
        variables.put( "view", order );

        String s = northwind.interpolate( "xxx" );
        Assert.assertEquals("xxx", s);

        s = northwind.interpolate( "int = ${int}, id = ${view.Order.OrderId}", variables );
        System.out.println( s );
        Assert.assertEquals("int = 10, id = 10248", s);

        order.setName( "TestOrder" );
        s = northwind.interpolate( "Order ID = ${TestOrder.Order.OrderId}" );
        Assert.assertEquals("Order ID = 10248", s);

        s = order.interpolate( "Order ID = ${Order.OrderId}" );
        Assert.assertEquals("Order ID = 10248", s);
    }
}
