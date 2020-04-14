package demo

import components.Printer
import http.Client
import io.bullet.borer.Json
import models.{Message, MessageCodec}
import org.scalajs.dom.document
import slinky.web.ReactDOM
import typings.std.console

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import typings.rxjs.mod

object App extends MessageCodec {

  var counter = 0

  def add(a: Int, b: Int): Int = {
    a + b
  }

  def main(args: Array[String]): Unit = {
//    {
//      var text = ""
//      Client
//        .get("http://localhost:9000/hello")
//        .onComplete {
//          case Success(response) =>
//            val message: Message =
//              Json.decode(response.responseText.getBytes()).to[Message].value
//            text = message.value
//            setupUI(text + add(1, 2))
//          case Failure(exception) =>
//            text = exception.getMessage
//        }
//    }

    setupUI("abc")
  }

  def setupUI(name: String): Unit = {
    var node = document.createElement("h1")
    node.textContent = name
    document.body.appendChild(node)
    document.onclick = { _ =>
      counter += 1
      console.log("Clicked!")
      console.log(counter)
    }
    mod.fromEvent(document.asInstanceOf, "click")

  }
}
