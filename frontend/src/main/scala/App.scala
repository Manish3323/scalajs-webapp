import org.scalajs.dom
import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("App")
object App {

  def appendPar(targetNode: dom.Node): Unit = {
    val parNode = document.createElement("p")
//    parNode.textContent = Client.get("http://localhost:9000/hello")
    targetNode.appendChild(parNode)
  }

  @JSExport
  def main(args: Array[String]): Unit = {
    appendPar(document.body)
  }
}
