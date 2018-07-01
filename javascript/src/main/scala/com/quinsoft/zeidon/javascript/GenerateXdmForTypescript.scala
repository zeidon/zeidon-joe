package com.quinsoft.zeidon.javascript

import com.quinsoft.zeidon.objectdefinition.LodDef
import com.quinsoft.zeidon.standardoe.JavaObjectEngine
import com.quinsoft.zeidon.scala.Implicits._
import scala.collection.JavaConversions._
import java.io.File
import com.quinsoft.zeidon.Task
import com.quinsoft.zeidon.scala.View

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
        val xdm = task.deserializeOi().setApplication( "ZeidonSystem" ).setLodDef( "tzdmxgpo" ).fromFile( filename ).unpickle
        printToFile( s"$destinationDir/${application.getName}-DomainList.ts" )( writer => {
            writer.println( s"""
// Generated from ${filename}
import { Domain } from '../zeidon';

export const ${application.getName()}_DomainList = {
""" );

            xdm.Domain.each{ writeDomain( writer, xdm ) }
            writer.println( s"""}""" )
        } )
    }

    private def writeDomain( writer: java.io.PrintWriter, xdm : View ) {
        writer.println( s"""    "${xdm.Domain.Name}" : {
        name:  "${xdm.Domain.Name}",
        class: "${xdm.Domain.JavaClass}", """ )

        if ( xdm.Domain.DomainType == "Table" ) {
            var defaultContext = ""

            writer.println( s"""        domainType:  "T",
        contexts: {""" )

            xdm.Context.each{
                writeTableContexts( writer, xdm )
                if ( xdm.Context.IsDefault == "Y" )
                    defaultContext = xdm.Context.Name
            }

            writer.println( s"""        },
        defaultContext: "$defaultContext" """ )
        }

        writer.println( s"""    } as Domain,
""" )
    }

    private def writeTableContexts( writer: java.io.PrintWriter, xdm : View ) {
        writer.println( s"""            "${xdm.Context.Name}": {
                name: "${xdm.Context.Name}",
                entries: {""" )

        xdm.TableEntry.each {
            writer.println( s"""                    "${xdm.TableEntry.InternalValue}": "${xdm.TableEntry.ExternalValue}",""" )
        }

        writer.println( s"""                }
            },""" )
    }

    private def printToFile(filename: String)(op: java.io.PrintWriter => Unit) {
        val p = new java.io.PrintWriter(new File(filename))
        try { op(p) } finally { p.close() }
    }

}
