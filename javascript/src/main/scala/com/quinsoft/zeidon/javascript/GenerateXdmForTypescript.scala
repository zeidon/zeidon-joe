package com.quinsoft.zeidon.javascript

import com.quinsoft.zeidon.objectdefinition.LodDef
import com.quinsoft.zeidon.standardoe.JavaObjectEngine
import com.quinsoft.zeidon.scala.Implicits._
import scala.collection.JavaConversions._
import java.io.File
import com.quinsoft.zeidon.Task

class GenerateXdmForTypescript( val applicationName: String, val destinationDir: String ) {

    val oe = JavaObjectEngine.getInstance
    val application = oe.getSystemTask.getApplication( applicationName )
    var lodDef: LodDef = null

    def generate() {
        oe.forTask( applicationName ) ( task => {
            task.log.info("Starting Typescript generation" )
            new File( destinationDir ).mkdir()
            val xdmfile = application.getObjectDir() + "/zeidon.xdm"
            generateXdm( task, xdmfile )
            println( "Done" )
        })
    }

    private def generateXdm( task: Task, filename: String ) = {
        val xdm = task.deserializeOi().setApplication( "ZeidonSystem" ).setLodDef( "tzdmxgpo" ).fromFile( filename )
        printToFile( s"$destinationDir/${application.getName}-DomainLists.ts" )( writer => {
            writer.println( s"""
// Generated from ${filename}
import * as zeidon from '../zeidon';

export const ${application.getName()}_DomainList = {
""" );
            writer.println( "" )
            writer.println( s"""}""" )
        } )
    }

    private def printToFile(filename: String)(op: java.io.PrintWriter => Unit) {
        val p = new java.io.PrintWriter(new File(filename))
        try { op(p) } finally { p.close() }
    }

}
