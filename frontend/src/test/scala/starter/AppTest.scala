package starter

import com.raquo.domtestutils.matching.RuleImplicits
import com.raquo.laminar.api.L._
import demo.App
import demo.components.Printer
import scalajsjest.JestSuite

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
}
