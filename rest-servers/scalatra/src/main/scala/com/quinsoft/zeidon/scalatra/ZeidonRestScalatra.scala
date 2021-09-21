package com.quinsoft.zeidon.scalatra

import com.quinsoft.zeidon.DuplicateRootException
import com.quinsoft.zeidon.ObjectEngine
import com.quinsoft.zeidon.PessimisticLockingException
import com.quinsoft.zeidon.Task
import com.quinsoft.zeidon.ZeidonException
import com.quinsoft.zeidon.http.ZeidonRestServerEngine
import com.quinsoft.zeidon.objectdefinition.KeyValidator
import com.quinsoft.zeidon.objectdefinition.KeyValidator.KeyValidationError
import com.quinsoft.zeidon.scala.Implicits._
import com.quinsoft.zeidon.scala.QualBuilder
import com.quinsoft.zeidon.scala.View
import org.apache.commons.lang3.StringUtils
import org.scalatra._

import java.net.URL
import javax.ws.rs.core.Response
import scala.collection.immutable.HashMap


/**
 * A HTTP gateway using Scalatra and the ZeidonRestServerEngine.
 */
trait ZeidonRestScalatra extends ScalatraServlet {

    var ZeidonRestServerEngine: ZeidonRestServerEngine = null

    def getObjectEngine(): ObjectEngine
    def getSystemTask = getObjectEngine().getSystemTask()
    def logger = getSystemTask.log()

    def getRestEngine(): ZeidonRestServerEngine = this.synchronized {
        if ( ZeidonRestServerEngine == null ) {
            ZeidonRestServerEngine = new ZeidonRestServerEngine( getObjectEngine() )
                    .addActivateValidation( handler => validateActivate( handler ) )
                    .addActivateResultValidation( handler => validateActivateResults( handler ) )
                    .addCommitValidation( handler => validateCommit( handler ) )
                    .addCommitResultValidation( handler => validateCommitResults( handler ) )
        }

        return ZeidonRestServerEngine
    }

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
        UnprocessableEntity( e.getMessage )
      }
    }

    after() {
        val systemTask = getObjectEngine().getSystemTask
        logger.debug("request %s %s returned with status code %s", request.getMethod, request.getRequestURL, response.getStatus.toString)
    }

    get("/:applicationName") {
        getObjectEngine().forTask( params( "applicationName" ) ) { task =>
            s"Application ${task.getApplication.getName} loaded"
        }
    }

    get("/:applicationName/:lodName") {
        getRestEngine().activate( request )
    }

    get("/:applicationName/:lodName/:id") {
        getRestEngine().activateByKey( request, params( "id" ) )
    }

    delete("/:applicationName/:lodName/:id") {
        getRestEngine().deleteByKey( request, params( "id" ) )
    }

    post("/:applicationName/:lodName/dropLock") {
        getObjectEngine().forTask( params( "applicationName" ) ) { task =>
            val lodName = params( "lodName" )
            val view = new View( task ) basedOn lodName
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
    post("/:applicationName/:lodName") {
        getRestEngine().commit( request, request.body )
    }

    get("/echo/:string") {
        Ok( params("string" ) )
    }

    get("/:applicationName/xod/:name") {
        getObjectEngine().forTask( params( "applicationName" ) ) { task =>
            // Load the XOD
            val xod = task.deserializeOi.setLodDef("ZeidonSystem", "tzzoxodo").fromAppResource( params("name" ) + ".XOD").activateFirst

            // Return it as JSON
            val serialized = xod.serializeOi.asJson.toString()
            task.log().debug( serialized )
            Ok( serialized )
        }
    }

    protected def validateActivate( handler: ZeidonRestServerEngine#RestEngineHandler ) {
        // By default do nothing.
    }

    protected def validateActivateResults( handler: ZeidonRestServerEngine#RestEngineHandler ) {
        // By default do nothing.
    }

    protected def validateCommit( handler: ZeidonRestServerEngine#RestEngineHandler ) {
        // By default do nothing.
    }

    protected def validateCommitResults( handler: ZeidonRestServerEngine#RestEngineHandler ) {
        // By default do nothing.
    }

    /**
      * Override invoke so we can add path params to the request attributes.
      */
    override def invoke(matchedRoute: MatchedRoute): Option[Any] = {
        withRouteMultiParams(Some(matchedRoute)) {
            params.foreach { case (param, value) =>
                if ( request.getParameter( param ) == null )
                    request.setAttribute( param, value )
            }
        }

        super.invoke(matchedRoute)
    }

    /**
      * Override renderResponnse so we can handle return values of javax.ws.rs.core.Response.
      *
      * @param actionResult
      */
    override protected def renderResponse(actionResult: Any): Unit = {
        if ( actionResult.isInstanceOf[Response] ) {
            val response = actionResult.asInstanceOf[Response]
            val headers = response.getHeaders()
            var headerMap = Map[String, String]()
            headers.keySet().forEach( key => headerMap = headerMap + ( key -> StringUtils.join( headers.get( key ) ) ) )
            val ar = ActionResult( response.getStatus(), response.getEntity(), headerMap )
            super.renderResponse( ar )
        }
        else
            super.renderResponse( actionResult )
    }
}
