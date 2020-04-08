import http.Client
import org.scalajs.dom.document

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.util.Success

object App {

  def main(args: Array[String]): Unit = {
    {
      val parNode = document.createElement("p")
      Client.get("http://localhost:9000/hello")
        .onComplete {
          case Success(value) =>
            parNode.textContent =
              js.JSON.parse(value.responseText).message.toString
            document.body.appendChild(parNode)
        }
    }
  }
}
