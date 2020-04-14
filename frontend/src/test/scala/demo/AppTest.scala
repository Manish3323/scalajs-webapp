package demo

import org.scalajs.dom.window.document
import org.scalatest._
import org.scalatest.matchers.should.Matchers

class AppTest extends FlatSpec with Matchers {
  "it" should " add two numbers" in {
    val sum = App.add(1, 3)
    assert(sum == 4)
  }

  "it" should "have dom's h1 element" in {
    App.setupUI("test123")
    assert(document.querySelector("h1").textContent == "test123")
  }

  "it" should "demo" in {
    App.setupUI("test123")
    document.querySelector("h1").textContent shouldBe "test123"
    App.counter shouldBe 0
  }
}
