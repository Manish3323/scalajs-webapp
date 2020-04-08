package routes

import akka.http.scaladsl.server.Directives.{complete, get, path}
import akka.http.scaladsl.server.Route
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
import models.{Message, MessageFormat}

object ServiceRoute extends MessageFormat {
  val route: Route =
    path("hello") {
      cors() {
        get {
          complete(Message("Hello World"))
        }
      }
    }
}
