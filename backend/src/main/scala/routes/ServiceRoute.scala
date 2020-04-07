package routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, path}
import akka.http.scaladsl.server.Route
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors

object ServiceRoute {
  val route: Route =
    path("hello") {
      cors() {
        get {
          complete(
            HttpEntity(
              ContentTypes.`application/json`,
              """{"message":"#Message from Akka 123!"}"""
            )
          )
        }
      }
    }
}
