package scala.demo

import components.Printer
import http.Client
import io.bullet.borer.Json
import models.{Message, MessageCodec}
import org.scalajs.dom.document
import slinky.web.ReactDOM

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object App extends MessageCodec {

  def add(a: Int, b: Int): Int = {
    a + b
  }

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
            setupUI(text + add(1, 2))
          case Failure(exception) =>
            text = exception.getMessage
        }

    }
  }

  def setupUI(name: String): Unit = {
    val container = document.getElementById("container")
    ReactDOM.render(Printer(name), container)
  }
}
