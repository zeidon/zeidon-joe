/**
 * Provides all the standard classes and interfaces necessary for client applications
 * written in Zeidon.
 *
 * <p>
 * Implementations of these interfaces are usually created by an Object Engine or a
 * Task created by an Object Engine.  Here is a simple example:
 * </p>
 * <pre>
 * {@code
 * ObjectEngine oe = JavaObjectEngine.getInstance();
 * Task zencas = oe.createTask( "ZENCAs" );
 * View view = zencas.activateEmptyObjectInstance( "mUser" );
 * }
 * </pre>
 * <p>
 *
 * </p>
 */
package com.quinsoft.zeidon;
