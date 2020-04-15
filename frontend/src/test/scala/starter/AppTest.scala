package starter

import com.raquo.domtestutils.matching.RuleImplicits
import com.raquo.laminar.api.L._
import demo.App
import org.scalajs.dom.document
import scalajsjest.JestSuite
import typings.reactDom.testUtilsMod.act

class AppTest extends JestSuite with JestMountOps with RuleImplicits {

  beforeEach {
    resetDOM()
  }

  test("app adds 2 numbers") {
    val sum = App.add(1, 3)
    assert(sum == 4)
  }

  test("fails") {
    mount(span("Hello2!"))
    expectNode(span like ("Hello2!"))
  }

  test("react dom") {
    val container = document.createElement("div")
    document.body.appendChild(container)
    act(() => {
      val instance = App.setupReactDom("123", container);
    });

    expect(document.querySelector("h1").innerHTML)
      .toContain("123")

  }

}
