/**
 * 
 */
package com.quinsoft.zeidon.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;

/**
 * @author dg
 *
 */
public class RubyScripting
{
    /**
     * @param args
     * @throws ScriptException 
     */
    public static void main( String[] args ) throws ScriptException
    {
        ObjectEngine oe = JavaObjectEngine.getInstance();
        Task task = oe.createTask( "ZENCAs" );
     
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("jruby");
        engine.put( "task", task );
        engine.eval("load '/home/dg/src/zeidon-dev/zruby/zeidon.rb'");
        engine.eval("def task() @task end");
        engine.eval("@task = Zeidon::Task.new( Zeidon.get_object_engine.get_app( $task.getApplication.getName ), $task)" );

        engine.eval("@v = task.activate_empty('wXferO')");
        engine.eval("@v.Root.createEntity");
        engine.eval("@v.Root.ActionFlag = 'Y'");
        engine.eval("@v.Root.WorkDecimal = 123.456");
        engine.eval("@v.Root.WorkDecimal *= 0.5");
        engine.eval("puts 'HERE' if @v.Root.ActionFlag == 'N'");
        engine.eval("@v.logObjectInstance");
    }
}
