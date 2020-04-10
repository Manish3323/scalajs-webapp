import http.Client
import io.bullet.borer.Json
import models.{Message, MessageCodec}
import org.scalajs.dom.document
import slinky.web.ReactDOM
import components.Printer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object App extends MessageCodec {

  def main(args: Array[String]): Unit = {
    {
      var text = ""
      Client
        .get("http://localhost:9000/hello")
        .onComplete {
          case Success(response) =>
            val message: Message =
              Json.decode(response.responseText.getBytes()).to[Message].value
            text = message.value
            val container = document.getElementById("container")
            ReactDOM.render(Printer(text), container)
          case Failure(exception) =>
            text = exception.getMessage
        }

    }
  }
}
