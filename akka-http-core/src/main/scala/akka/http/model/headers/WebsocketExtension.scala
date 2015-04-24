/*
 * Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com>
 */

package akka.http.model.headers

import akka.http.util.{ Rendering, ValueRenderable }

import scala.collection.immutable

/**
 * A websocket extension as defined in http://tools.ietf.org/html/rfc6455#section-4.3
 */
final case class WebsocketExtension(name: String, params: immutable.Map[String, String] = Map.empty) extends ValueRenderable {
  def render[R <: Rendering](r: R): r.type = {
    r ~~ name
    if (params.nonEmpty)
      params.foreach {
        case (k, "") ⇒ r ~~ "; " ~~ k
        case (k, v)  ⇒ r ~~ "; " ~~ k ~~ '=' ~~# v
      }
    r
  }
}