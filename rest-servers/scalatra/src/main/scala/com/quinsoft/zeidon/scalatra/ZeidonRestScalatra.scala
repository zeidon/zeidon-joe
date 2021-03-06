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
import org.apache.commons.lang3.StringUtils
import com.quinsoft.zeidon.DuplicateRootException
import com.quinsoft.zeidon.objectdefinition.KeyValidator
import com.quinsoft.zeidon.objectdefinition.KeyValidator.KeyValidationError


/**
 *
 */
trait ZeidonRestScalatra extends ScalatraServlet {

    val lodWhitelist = scala.collection.mutable.Map[String, Any]()
    val lodBlacklist = scala.collection.mutable.Set[String]()

    def getObjectEngine(): ObjectEngine
    def getSystemTask = getObjectEngine().getSystemTask()
    def logger = getSystemTask.log()

    error {
      case e: PessimisticLockingException => {
          getObjectEngine().getSystemTask.log().debug( "LOD is locked" )
          Locked( "LOD is locked" )
      }

      case e: DuplicateRootException => {
          getObjectEngine().getSystemTask.log().error(e)
          BadRequest( "Duplicate key" )
      }

      case e: Throwable => {
        getObjectEngine().getSystemTask.log().error(e)
        e.printStackTrace();
        UnprocessableEntity( e.getMessage )
      }
    }

    after() {
        val systemTask = getObjectEngine().getSystemTask
        logger.debug("request %s %s returned with status code %s", request.getMethod, request.getRequestURL, response.getStatus.toString)
    }

    get("/:appName") {
        getObjectEngine().forTask( params( "appName" ) ) { task =>
            s"Application ${task.getApplication.getName} loaded"
        }
    }

    get("/:appName/:lod") {
        getObjectEngine().forTask( params( "appName" ) ) { task =>
            val lodName = params( "lod" )
            validateLodName( task, lodName )
            val view = new View( task ) basedOn lodName
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

            // By default don't allow Custom Queries.
            qual.allowCustomQuery( false )
            validateQualification( task, qual, lodName )
            qual.activate()
            Ok( serializeResponse( view ) )
        }
    }

    get("/:appName/:lod/:id") {
        getObjectEngine().forTask( params( "appName" ) ) { task =>
            val lodName = params( "lod" )
            validateLodName( task, lodName )
            val view = task.using( lodName )
                           .activate( _.root.key = params( "id" ) )

            Ok( serializeResponse( view ) )
        }
    }

    delete("/:appName/:lod/:id") {
        getObjectEngine().forTask( params( "appName" ) ) { task =>
            val lodName = params( "lod" )
            validateLodName( task, lodName )
            val view = task.using( lodName )
                           .activate( _.root.key = params( "id" ) )

            view.root.deleteEntity()
            view.commit()
            Ok("")
        }
    }

    post("/:appName/:lod/dropLock") {
        getObjectEngine().forTask( params( "appName" ) ) { task =>
            val lodName = params( "lod" )
            val view = new View( task ) basedOn lodName
            validateLodName( task, lodName )
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
            val lodName = params( "lod" )
            val view = task.viewFromJson( request.body )
            validateLodName( task, lodName )
            if ( StringUtils.compare( view.lodName, lodName ) != 0 )
                throw new ZeidonException( "View name in JSON does not match expected value of %s", lodName )

            validateViewForUpdate( task, view, lodName )
            view.commit

            val serialized = serializeResponse( view )
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
                             .setWriteHiddenAttributes( false )
                             .toString()
        view.log().debug(serialized)
        return serialized
    }

    /**
     * Verifies that the server can activate/commit the LOD.
     */
    protected def validateLodName( task: Task, expectedLodName: String ) {
        if ( StringUtils.isBlank( expectedLodName ) )
            halt( 422, "Missing LOD name" )

        if ( lodBlacklist.contains( expectedLodName ) ) {
            task.log().error(s"Lod ${expectedLodName} is black-listed")
            halt( 403 ) // Forbidden.
        }

        if ( lodWhitelist.size > 0 ) {
            if ( ! lodWhitelist.contains( expectedLodName ) ) {
                task.log().error(s"Lod ${expectedLodName} is not white-listed")
                halt( 403 ) // Forbidden.
            }
        }
    }

    protected def validateQualification( task: Task, qual: QualBuilder, lodName: String ) {
        // By default do nothing.
    }

    /**
     * Verifies that the data in the OI hasn't been tampered with other than in the
     * expected ways.  For example, makes sure the keys match up with the OIs on the DB.
     */
    protected def validateViewForUpdate( task: Task, view: View, expectedLodName: String ) {
        // Make sure the LOD name of the view matches the expected LOD name.
        if ( view != null && StringUtils.compare( expectedLodName, view.lodDef.getName ) != 0 ) {
            view.log().error(s"Expected Lod ${expectedLodName} does not match passed LOD ${view.lodDef.getName}")
            halt( 403 ) // Forbidden.
        }

        val keyValidator = new KeyValidator( view )
        try {
            keyValidator.validate()
        } catch {
            case e: KeyValidationError =>
                // Somebody is apparently trying to update a LOD but with edited keys.  Log an
                // error but don't return the message because it could contain sensitive key info.
                task.log().error( e.getMessage() )
                halt( 403 ) // Forbidden.
        }
    }
}
