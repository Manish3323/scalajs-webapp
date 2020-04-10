import org.scalajs.dom.document
import utest._
object AppTest extends TestSuite {
  App.setupUI("test")
  val tests: Tests = Tests {
    test("test1") {
      assert(document.querySelector("h1").textContent == "test")
    }
  }
}
