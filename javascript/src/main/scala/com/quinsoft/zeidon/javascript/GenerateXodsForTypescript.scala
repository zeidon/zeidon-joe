package com.quinsoft.zeidon.javascript

import com.quinsoft.zeidon.standardoe.JavaObjectEngine
import com.quinsoft.zeidon.scala.Implicits._
import scala.collection.JavaConversions._
import java.io.File
import com.quinsoft.zeidon.Application
import com.quinsoft.zeidon.Task
import java.time._;
import com.quinsoft.zeidon.objectdefinition.LodDef
import com.quinsoft.zeidon.objectdefinition.EntityDef
import com.quinsoft.zeidon.objectdefinition.AttributeDef
import org.apache.commons.lang3.StringUtils
import com.quinsoft.zeidon.domains.AbstractNumericDomain
import com.quinsoft.zeidon.domains.BooleanDomain
import com.quinsoft.zeidon.domains.DateTimeDomain

class GenerateXodsForTypescript( val applicationName: String, val destinationDir: String ) {
    val oe = JavaObjectEngine.getInstance
    val application = oe.getSystemTask.getApplication( applicationName )
    var lodDef: LodDef = null

    def generate() {
        oe.forTask( applicationName ) ( task => {
            task.log.info("Starting Typescript generation for application %s => %s", applicationName, destinationDir, "" )
            new File( destinationDir ).mkdir()
            application.getLodNameList(task).foreach( generateXod( task, _ ) )
            println( "Done" )
        })
    }

    private def generateXod( task: Task, lodName: String ) = {
        lodDef = application.getLodDef( task, lodName )
        printToFile( s"$destinationDir/$lodName.ts" )( writer => {
            writeStartingComment(writer)
            writer.println( s"""
import * as zeidon from '@zeidon/core';
import { ${applicationName}_DomainList } from './${applicationName}-DomainList';
import { ${applicationName}_DomainFunctions } from './${applicationName}-DomainFunctions';
""" );
            writer.println( "" )
            writeObjectInstance(writer)
            lodDef.getEntityDefs.foreach { writeEntityInstance( writer, _ ) }
            writeEntityPrototypes(writer)
            writeLodDef(writer)
        } )
    }

    private def writeStartingComment( writer: java.io.PrintWriter ) {
        writer.println( s"""
/*
  Generated from LOD ${lodDef.getName}

*/""" )
    }

    private def writeObjectInstance( writer: java.io.PrintWriter ) {
        val lodName = lodDef.getName
        val rootName = lodDef.getRoot.getName

        writer.println( s"""
// $lodName LOD.
export class $lodName extends zeidon.ObjectInstance {

    constructor( initialize = undefined, options: zeidon.CreateOptions = undefined ) {
        super( ${lodName}_LodDef, initialize, options );
    }

    get $rootName(): zeidon.EntityArray<${lodName}_${rootName}> {
        return this.roots as zeidon.EntityArray<${lodName}_${rootName}>;
    }

    get ${rootName}$$(): ${lodName}_${rootName} {
        return this.roots.selected() as ${lodName}_${rootName};
    }

    // Following allow accessing of child entity instances directly from the OI,
    // similar to Zeidon Views.
""" )

        lodDef.getEntityDefs.foreach { writeTopLevelEntityAccesors( writer, _ ) }

        writer.println( s"""
    public static activate( qual?: any ): Promise<$lodName> {
        return zeidon.ObjectInstance.activateOi( new $lodName(), qual );
    }
}
""" )
    }

    private def writeTopLevelEntityAccesors( writer: java.io.PrintWriter, entityDef: EntityDef ) {
        val lodName = lodDef.getName

        // Don't create one for the root.
        if ( entityDef.getParent() == null )
            return

        val sb = new java.lang.StringBuilder();
        var entity = entityDef
        while ( entity != null ) {
            if ( sb.toString() != "" )
                sb.insert( 0, "." )

            sb.insert( 0, "$?" )
            sb.insert( 0, entity.getName )

            entity = entity.getParent
        }

        // Remove the trailing ? since we don't want it for the last entityt.
        sb.setLength(sb.length() - 2);

        writer.println( s"""
    get ${entityDef.getName}(): zeidon.EntityArray<${lodName}_${entityDef.getName}> {
        return this.${sb.toString()};
    }

    get ${entityDef.getName}$$(): ${lodName}_${entityDef.getName} {
        return this.${sb.toString()}$$;
    }""" )

    }

    private def writeEntityInstance( writer: java.io.PrintWriter, entityDef: EntityDef ) {
        val entityName = entityDef.getName
        writer.println( s"""
export class ${lodDef.getName}_${entityName} extends zeidon.EntityInstance {
    public get entityName(): string { return "$entityName" };""" )

        entityDef.getAttributes.foreach { writeAttributeInstance( writer, _ ) }
        entityDef.getChildren.foreach { writeChildEntities( writer, _ ) }

        writer.println( "}" )
    }

    private def writeAttributeInstance( writer: java.io.PrintWriter, attributeDef: AttributeDef ) {
        if ( attributeDef.isHidden() )
            return

        val name = attributeDef.getName
        val jsType = javascriptType(attributeDef)

        writer.println( s"""
    get $name(): $jsType { return this.getAttribute("$name") };
    set $name(value: $jsType) { this.setAttribute("$name", value) };""" )
    }

    private def javascriptType( attributeDef: AttributeDef ) : String = {
        val domain = attributeDef.getDomain
        if ( domain.isInstanceOf[AbstractNumericDomain] )
            return "number"

        if ( domain.isInstanceOf[BooleanDomain] )
            return "boolean"

        if ( domain.isInstanceOf[BooleanDomain] )
            return "boolean"

        if ( domain.isInstanceOf[DateTimeDomain] )
            return "Date"

        return "string"
    }

    private def writeChildEntities( writer: java.io.PrintWriter, childEntityDef: EntityDef ) {
        val entityName = childEntityDef.getName
        val lodName = lodDef.getName

        writer.println( s"""
    get ${entityName}(): zeidon.EntityArray<${lodName}_$entityName> {
        return this.getChildEntityArray("$entityName") as zeidon.EntityArray<${lodName}_$entityName>;
    }


    get ${entityName}$$(): ${lodName}_$entityName {
        return this.getChildEntityArray("$entityName").selected() as ${lodName}_$entityName;
    }""" )
    }

    private def writeEntityPrototypes( writer: java.io.PrintWriter ) {
        writer.println( s"""
const ${lodDef.getName}EntityPrototypes = {""" )

        lodDef.getEntityDefs.foreach { entityDef => {
            writer.println( s"    ${entityDef.getName}: ${lodDef.getName}_${entityDef.getName}.prototype, " )
        } }

        writer.println( s"""}""" )
    }

    private def writeLodDef( writer: java.io.PrintWriter ) {
        val lodDefName = lodDef.getName
        val appName = lodDef.getApplication.getName
        writer.println( s"""
export const ${lodDefName}_LodDefStructure = {
    name: "${lodDefName}",
    root: "${lodDef.getRoot.getName}",
    applicationName: "${appName}",
    entities: {""" )

       lodDef.getEntityDefs.foreach { writeEntityDef( writer, _ ) }

        writer.println( "    }" )
        writer.println( s"""}

export const ${lodDefName}_LodDef = new zeidon.LodDef( ${lodDefName}_LodDefStructure, ${lodDefName}EntityPrototypes, ${appName}_DomainList, ${appName}_DomainFunctions );
        """ )
    }

    private def writeEntityDef( writer: java.io.PrintWriter, entityDef: EntityDef ) {
        val entityName = entityDef.getName
        writer.println( s"""        $entityName: {
            name:        "$entityName",
            erToken:     "${entityDef.getErEntityToken}",""" )

        if ( entityDef.getParent != null )
            writer.println( s"""            isErRelLink: ${entityDef.isErRelLink},
            relToken:    "${entityDef.getErRelToken}",""" )

        writer.println( s"""            create:      ${entityDef.isCreate()},
            cardMax:     ${entityDef.getMaxCardinality},
            hasInit:     ${entityDef.hasInitializedAttributes()},
            creatable:   ${entityDef.isDelete()},
            includable:  ${entityDef.isInclude()},
            deletable:   ${entityDef.isDelete()},
            excludable:  ${entityDef.isExclude()},
            updatable:   ${entityDef.isUpdate()},
            derived:     ${entityDef.isDerived()},
            parentDelete: ${entityDef.isParentDelete()},
            childEntities: {""" )

        entityDef.getChildren.foreach { writeChildEntityDefs( writer, _ ) }

        writer.print( s"""            },
            keys: [""" )

        entityDef.getAttributes.foreach { writeAttributeKey( writer, _ ) }

        writer.println( s""" ],
            attributes: {""" )

        entityDef.getAttributes.foreach { writeAttributeDef( writer, _ ) }

        writer.println( s"""            }
        },
""" )
    }

    private def writeChildEntityDefs( writer: java.io.PrintWriter, childEntityDef: EntityDef ) {
        writer.println( s"""                ${childEntityDef.getName}: {},""" )
    }

    private def writeAttributeKey( writer: java.io.PrintWriter, attributeDef: AttributeDef ) {
        if ( attributeDef.isKey ) {
            writer.print( s""" "${attributeDef.getName}", """ )
        }
    }

    private def writeAttributeDef( writer: java.io.PrintWriter, attributeDef: AttributeDef ) {
        val name = attributeDef.getName

        writer.println( s"""                $name: {
                    name:         "${name}",
                    hidden:       ${attributeDef.isHidden()},
                    required:     ${attributeDef.isRequired()},
                    domainName:   "${attributeDef.getDomain.getName}",
                    persistent:   ${attributeDef.isPersistent()},
                    key:          ${attributeDef.isKey},
                    update:       ${attributeDef.isUpdate},
                    foreignKey:   ${attributeDef.isForeignKey()},""" )

        if ( ! StringUtils.isBlank( attributeDef.getInitialValue ) )
          writer.println( s"""                    initialValue: "${attributeDef.getInitialValue}",""" )

        writer.println( s"""                },""" )
    }

    private def printToFile(filename: String)(op: java.io.PrintWriter => Unit) {
        val p = new java.io.PrintWriter(new File(filename))
        try { op(p) } finally { p.close() }
    }
}

object GenerateXodsForTypescript {
   def main(args: Array[String]) {
       val generator = new GenerateXodsForTypescript( args(0), args(1) )
       generator.generate();
       val xdmGenerator = new GenerateXdmForTypescript( args(0), args(1) )
       xdmGenerator.generate();
    }
}
