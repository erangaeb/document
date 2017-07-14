package com.pagero.services.document

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import com.pagero.services.document.actor.RestServiceActor
import com.pagero.services.document.config.Config
import spray.can.Http

object Main extends App with Config {
  // create an actor system for application
  implicit val system = ActorSystem("document-service")

  // create and start rest service actor
  val restService = system.actorOf(Props[RestServiceActor], "rest-service-actor")

  // start HTTP server with rest service actor as a handler
  //IO(Http) ! Http.Bind(restService, serviceHost, servicePort)
  IO(Http) ! Http.Bind(restService, "localhost", 8080)
}
