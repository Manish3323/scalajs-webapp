package routes

import akka.http.scaladsl.server.Directives.{complete, get, path}
import akka.http.scaladsl.server.Route
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
import models.{Message, MessageCodec}
import io.bullet.borer.compat.akkaHttp._

object ServiceRoute extends MessageCodec {
  val route: Route =
    path("hello") {
      cors() {
        get {
          complete {
            Message("hello world")
          }
        }
      }
    }
}
