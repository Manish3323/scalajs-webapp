import http.Client
import io.circe.generic.auto._
import io.circe.parser._
import models.Message
import org.scalajs.dom.document

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object App {

  def main(args: Array[String]): Unit = {
    {
      val parNode = document.createElement("p")
      Client
        .get("http://localhost:9000/hello")
        .onComplete {
          case Success(response) =>
            val message = decode[Message](response.responseText).toTry.get
            parNode.textContent = message.value
            document.body.appendChild(parNode)
          case Failure(_) =>
            parNode.textContent = "Something went wrong!"
            document.body.appendChild(parNode)
        }
    }
  }
}
