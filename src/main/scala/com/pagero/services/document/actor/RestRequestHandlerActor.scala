package com.pagero.services.document.actor

import akka.actor.{Actor, Props}
import akka.event.slf4j.SLF4JLogging
import com.pagero.services.document.comp.{CassandraClusterComp, DocumentDbCompImpl}
import com.pagero.services.document.protocol.{Document, DocumentResponse, Meta}
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport._
import spray.routing.RequestContext

object RestRequestHandlerActor {

  case class Criteria(id: Option[Int], name: Option[String], docType: Option[String], partyName: Option[String], orgNo: Option[String])

  case class Get(criteria: Criteria)

  case class Post(document: Document)

  case class Put(id: Int, document: Document)

  def props(requestContext: RequestContext) = Props(classOf[RestRequestHandlerActor], requestContext)

}

class RestRequestHandlerActor(requestContext: RequestContext) extends Actor with DocumentDbCompImpl with CassandraClusterComp with SLF4JLogging {

  import RestRequestHandlerActor._

  override def receive = {
    case Get(Criteria(None, name, docType, partyName, orgNo)) =>
      import com.pagero.services.document.protocol.DocumentResponseProtocol._

      log.info(s"GET documents $name $docType $partyName $orgNo")

      // search docs and create response
      val docs = documentDb.getDocuments(Criteria(None, name, docType, partyName, orgNo))
      val meta = Meta(10, None, 0, None, 25)

      val resp = DocumentResponse(meta, docs)
      requestContext.complete(resp)

      context.stop(self)
    case Get(Criteria(Some(id), None, None, None, None)) =>
      import com.pagero.services.document.protocol.DocumentProtocol._

      log.info(s"GET document $id")

      // find specific doc
      val doc = documentDb.getDocument(id)
      doc match {
        case Some(d) =>
          // found doc
          requestContext.complete(d)
        case None =>
          // 404
          requestContext.complete(StatusCodes.NotFound -> "404")
      }

      context.stop(self)
    case Post(doc) =>
      // create doc
      log.info(s"POST document")

      documentDb.createDocument(doc)
      requestContext.complete(StatusCodes.Created -> "created")

      context.stop(self)
    case Put(id, doc) =>
      // update doc
      log.info(s"PUT document $id")
      requestContext.complete("PUT doc")

      context.stop(self)
  }

}
