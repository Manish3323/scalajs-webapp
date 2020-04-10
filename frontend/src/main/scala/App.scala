import components.Printer
import models.MessageCodec
import org.scalajs.dom.document
import slinky.web.ReactDOM

object App extends MessageCodec {

  def main(args: Array[String]): Unit = {
    {
      var text = ""
//      Client
//        .get("http://localhost:9000/hello")
//        .onComplete {
//          case Success(response) =>
//            val message: Message =
//              Json.decode(response.responseText.getBytes()).to[Message].value
//            text = message.value
//          case Failure(exception) =>
//            text = exception.getMessage
//        }

      val container = document.getElementById("container")
      ReactDOM.render(Printer("string from stateless component"), container)
    }
  }
}
