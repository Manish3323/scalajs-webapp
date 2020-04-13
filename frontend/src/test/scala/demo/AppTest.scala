package scala.demo
import org.scalajs.dom.window.document
import org.scalatest._
import org.scalatest.matchers.should.Matchers

class AppTest extends FlatSpec with Matchers {
  "it" should " add two numbers" in {
    val sum = App.add(1, 3)
    assert(sum == 4)
  }

  "it" should "have dom's h1 element" in {
    var node = document.createElement("h1")
    node.textContent = "test"
    document.body.appendChild(node)

    assert(document.querySelector("h1").textContent == "test")
  }
}
