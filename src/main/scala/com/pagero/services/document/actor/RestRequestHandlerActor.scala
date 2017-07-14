package com.pagero.services.document.actor

import akka.actor.{Actor, Props}
import akka.event.slf4j.SLF4JLogging
import com.pagero.services.document.protocol.{Document, DocumentResponse, Meta, PartyInfo}
import spray.httpx.SprayJsonSupport._
import spray.routing.RequestContext

object RestRequestHandlerActor {

  case class Criteria(id: Option[Int], name: Option[String], docType: Option[String], from: Option[String], to: Option[String])

  case class Get(criteria: Criteria)

  case class Post(doc: String)

  case class Put(id: Int, doc: String)

  def props(requestContext: RequestContext) = Props(classOf[RestRequestHandlerActor], requestContext)

}

class RestRequestHandlerActor(requestContext: RequestContext) extends Actor with SLF4JLogging {

  import RestRequestHandlerActor._

  override def receive = {
    case Get(Criteria(None, name, docType, from, to)) =>
      import com.pagero.services.document.protocol.DocumentProtocol._

      // search docs
      // all docs
      log.info(s"GET document $name $docType $from $to")
      requestContext.complete(get(Criteria(None, name, docType, from, to)))

      context.stop(self)
    case Get(Criteria(Some(id), None, None, None, None)) =>
      // get specific doc
      log.info(s"GET document $id")
      requestContext.complete("GET doc HAHA")

      context.stop(self)
    case Post(doc) =>
      // create doc
      log.info(s"POST document")
      requestContext.complete("POST doc HAHA")

      context.stop(self)
    case Put(id, doc) =>
      // update doc
      log.info(s"PUT document $id")
      requestContext.complete("PUT doc HAHA")

      context.stop(self)
  }

  def get(criteria: Criteria) = {
    val meta = Meta(10, None, 0, None, 25)
    val doc1 = Document(Option(3), "telia", "INVOICE", "2017/08/13", PartyInfo(Option(2), "eranga", "688812", "231122"))
    val doc2 = Document(Option(4), "conviq", "INVOICE", "2017/08/24", PartyInfo(Option(3), "badara", "688812", "231122"))
    DocumentResponse(meta, List(doc1, doc2))
  }

}
