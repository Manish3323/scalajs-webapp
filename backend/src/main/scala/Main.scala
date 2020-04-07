import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import routes.ServiceRoute

import scala.concurrent.ExecutionContextExecutor

object Main {
  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem = ActorSystem("backend-system")
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    Http().bindAndHandle(ServiceRoute.route, "localhost", 9000)

    println("Server online at http://localhost:8080/")
  }
}
