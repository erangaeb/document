package com.pagero.services.document.config

import com.typesafe.config.ConfigFactory

import scala.util.Try

/**
  * Load configurations define in application.conf from here
  *
  * @author eranga herath(eranga.herath@pagero.com)
  */
trait Config {
  // config object
  val config = ConfigFactory.load()

  // epic config
  lazy val serviceHost = Try(config.getString("service.host")).getOrElse("dev.localhost")
  lazy val servicePort = Try(config.getInt("service.port")).getOrElse(8080)
}
