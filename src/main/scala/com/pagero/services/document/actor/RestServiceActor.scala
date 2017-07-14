package com.pagero.services.document.actor

import akka.actor.{Actor, Props}
import akka.event.slf4j.SLF4JLogging
import com.pagero.services.document.actor.RestRequestHandlerActor.{Criteria, Get, Post}
import com.pagero.services.document.protocol.Document
import spray.httpx.SprayJsonSupport._
import spray.routing.HttpService

object RestServiceActor {
  def props() = Props(classOf[RestServiceActor])
}

class RestServiceActor extends Actor with RestService {
  implicit def actorRefFactory = context

  override def receive = runRoute(router)
}

trait RestService extends HttpService with SLF4JLogging {
  implicit def executionContext = actorRefFactory.dispatcher

  val router = {
    pathPrefix("api" / "v1") {
      path("documents") {
        get {
          parameters("name" ?, "docType" ?, "from" ?, "to" ?) { (name, docType, from, to) => requestContext =>
            log.info(s"GET documents")
            actorRefFactory.actorOf(RestRequestHandlerActor.props(requestContext)) ! Get(Criteria(None, name, docType, from, to))
          }
        } ~
          post { requestContext =>
            import com.pagero.services.document.protocol.DocumentProtocol._
            entity(as[Document]) { document => requestContext =>
              log.info("POST document")
              actorRefFactory.actorOf(RestRequestHandlerActor.props(requestContext)) ! Post(document)
            }
          }
      } ~
        path("documents" / IntNumber) { docId =>
          get { requestContext =>
            log.info(s"GET document $docId")
            actorRefFactory.actorOf(RestRequestHandlerActor.props(requestContext)) ! Get(Criteria(Option(docId), None, None, None, None))
          } ~
            put {
              complete(s"Haha document PUT $docId")
            }
        }
    }
  }

}
