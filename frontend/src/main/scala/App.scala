import http.Client
import models.{Message, MessageCodec}
import org.scalajs.dom.document

import scala.concurrent.ExecutionContext.Implicits.global
import io.bullet.borer.{DecodingSetup, Json}

import scala.util.{Failure, Success}

object App extends MessageCodec {

  def main(args: Array[String]): Unit = {
    {
      val parNode = document.createElement("p")
      Client
        .get("http://localhost:9000/hello")
        .onComplete {
          case Success(response) =>
            val message: Message =
              Json.decode(response.responseText.getBytes()).to[Message].value
            parNode.textContent = message.value
            document.body.appendChild(parNode)
          case Failure(exception) =>
            parNode.textContent = exception.getMessage
            document.body.appendChild(parNode)
        }
    }
  }
}
