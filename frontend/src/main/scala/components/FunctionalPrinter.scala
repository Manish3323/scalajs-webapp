package components

import slinky.core.{FunctionalComponent}
import slinky.core.annotations.react
import slinky.web.html.h1

@react object FunctionalPrinter {
  case class Props(name: String)
  val component: FunctionalComponent[Props] = FunctionalComponent[Props] {
    props =>
      h1(props.name)
  }

}
