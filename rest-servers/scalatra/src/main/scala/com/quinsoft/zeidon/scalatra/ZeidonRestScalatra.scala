package com.quinsoft.zeidon.scalatra

import org.scalatra._
import java.net.URL
import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.scala.Implicits._
import com.quinsoft.zeidon.Task
import com.quinsoft.zeidon.scala.QualBuilder
import com.quinsoft.zeidon.ObjectEngine
import com.quinsoft.zeidon.PessimisticLockingException
import com.quinsoft.zeidon.ZeidonException


/**
 *
 */
trait ZeidonRestScalatra extends ScalatraServlet {

    def getObjectEngine(): ObjectEngine

    error {
      case e: PessimisticLockingException => {
          getObjectEngine().getSystemTask.log().debug( "LOD is locked" )
          Locked( "LOD is locked" )
      }

      case e: Throwable => {
        getObjectEngine().getSystemTask.log().error(e)
        e.printStackTrace();
        UnprocessableEntity( e.getMessage )
      }
    }

    // Before every action runs, set the content type to be in JSON format.
    before() {
        contentType = "text/json"
        val systemTask = getObjectEngine().getSystemTask
        systemTask.log().debug("Path   => %s", request.pathInfo )
        systemTask.log().debug("Params => %s", request.parameters )
        systemTask.log().debug("Body   => %s", request.body )
    }

    get("/:appName") {
        getObjectEngine().forTask( params( "appName" ) ) { task =>
            s"Application ${task.getApplication.getName} loaded"
        }
    }

    get("/:appName/:lod") {
        getObjectEngine().forTask( params( "appName" ) ) { task =>
            val view = new View( task ) basedOn params( "lod" )
            val qual = view.buildQual()

            if ( params.contains( "qual" ) ) {
                qual.fromJson( params( "qual" ) )
            }
            else
            if ( params.contains( "qualOi" ) ) {
                val qualStr = params( "qualOi" )
                qual.fromSerializedOi( qualStr )
            } else {
                qual.rootOnlyMultiple()
            }

            qual.activate()
            Ok( serializeResponse( view ) )
        }
    }

    get("/:appName/:lod/:id") {
        getObjectEngine().forTask( params( "appName" ) ) { task =>
            val lodName = params( "lod" )
            val view = task.newView( lodName )
                           .activateWhere( _.root.key = params( "id" ) )

            Ok( serializeResponse( view ) )
        }
    }

    delete("/:appName/:lod/:id") {
        getObjectEngine().forTask( params( "appName" ) ) { task =>
            val lodName = params( "lod" )
            val view = task.newView( lodName )
                           .activateWhere( _.root.key = params( "id" ) )

            view.root.deleteEntity()
            view.commit()
            Ok("")
        }
    }

    post("/:appName/:lod/dropLock") {
        getObjectEngine().forTask( params( "appName" ) ) { task =>
            val view = new View( task ) basedOn params( "lod" )
            val qual = view.buildQual()

            if ( params.contains( "qual" ) ) {
              qual.fromJson( params( "qual" ) )
            }
            else {
                throw new ZeidonException("dropLock requires qualification")
            }

            qual.dropLocks()
            Ok( "Locks dropped" )
        }
    }

    // POST action will save the OI passed via the body.
    post("/:appName/:lod") {
        getObjectEngine().forTask( params( "appName" ) ) { task =>
            task.log().debug( request.body )
            val view = task.deserializeOi()
                           .setLodDef( params( "lod") )
                           .setVersion("1")
                           .fromString( request.body )
                           .asJson()
                           .unpickle

            view.commit

            val serialized = view.serializeOi.asJson.withIncremental().toString()
            task.log().debug( serialized )
            Ok(serialized)
        }
    }

    get("/echo/:string") {
        Ok( params("string" ) )
    }

    get("/:appName/xod/:name") {
        getObjectEngine().forTask( params( "appName" ) ) { task =>
            // Load the XOD
            val xod = task.deserializeOi.setLodDef("ZeidonSystem", "tzzoxodo").fromAppResource( params("name" ) + ".XOD").activateFirst

            // Return it as JSON
            val serialized = xod.serializeOi.asJson.toString()
            task.log().debug( serialized )
            Ok( serialized )
        }
    }

    protected def serializeResponse( view: View,
                                     withIncrementals: Boolean = true ) : String = {
        if ( view.isEmpty )
            return "{}"

        val serialized = view.serializeOi
                             .asJson
                             .withIncremental( withIncrementals )
                             .toString()
        view.log().debug(serialized)
        return serialized
    }
}
